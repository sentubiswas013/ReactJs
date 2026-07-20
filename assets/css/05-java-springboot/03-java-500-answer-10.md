# ✅ 01. Java Basic Concepts 

## 1. What is Java and what are its key features?

**Java** is a **high-level, object-oriented, platform-independent programming language** developed by **Sun Microsystems** (now owned by Oracle). It is widely used for building **web applications, enterprise systems, mobile apps, and microservices**.

**Key Features**

* **Platform Independent** – Java code is compiled into **bytecode**, which runs on any system with a **JVM (Write Once, Run Anywhere)**.
* **Object-Oriented** – Supports **Encapsulation, Inheritance, Polymorphism, and Abstraction**.
* **Simple and Easy to Learn** – Has a clean syntax and automatic memory management.
* **Robust** – Provides **exception handling** and **Garbage Collection (GC)**.
* **Secure** – Runs inside the **JVM** with features like bytecode verification and no direct memory access.
* **Multithreaded** – Supports concurrent execution using multiple threads.
* **High Performance** – Uses the **JIT (Just-In-Time) Compiler** to convert bytecode into optimized machine code.
* **Distributed and Scalable** – Suitable for enterprise and microservice-based applications.

**How It Works**

1. Write Java code in a **`.java`** file.
2. The **Java Compiler (`javac`)** converts it into **bytecode (`.class`)**.
3. The **JVM** loads and executes the bytecode on any operating system.

**When to Use**

* Building **Spring Boot** and enterprise applications.
* Developing **REST APIs** and **microservices**.
* Creating **Android applications**.
* Developing **high-performance, scalable backend systems**.

**Code Example**

```java id="p6x3nk"
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

## 2. Explain the difference between JDK, JRE, and JVM.

These are the three main components of the Java platform:

* **JVM (Java Virtual Machine)**: Executes Java **bytecode** and provides platform independence.
* **JRE (Java Runtime Environment)**: Contains the **JVM + libraries** required to **run** Java applications.
* **JDK (Java Development Kit)**: Contains the **JRE + development tools** required to **develop, compile, and run** Java applications.


**Relationship**

```text
JDK
 ├── JRE
 │    ├── JVM
 │    └── Java Class Libraries
 └── Development Tools (javac, java, javadoc, jar, etc.)
```

**Key Features**

| **Component** | **Purpose**                      | **Contains**                                                     | **Used By**           |
| ------------- | -------------------------------- | ---------------------------------------------------------------- | --------------------- |
| **JVM**       | Executes bytecode                | Class Loader, Memory Management, Garbage Collector, JIT Compiler | Running Java programs |
| **JRE**       | Provides runtime environment     | JVM + Java libraries                                             | End users             |
| **JDK**       | Provides development environment | JRE + Compiler + Development tools                               | Java developers       |

**How It Works**

1. Developer writes Java code (`.java` file).
2. **JDK** uses the **`javac` compiler** to convert it into **bytecode** (`.class` file).
3. **JRE** provides the runtime environment to start the application.
4. **JVM** loads the bytecode, converts it into **machine code** using the **JIT (Just-In-Time) Compiler**, and executes it.


**When to Use**

* Use **JDK** when you are **developing or compiling** Java applications.
* Use **JRE** when you only need to **run** Java applications.
* **JVM** works internally whenever a Java program is executed.

**Common JDK Tools**

| **Tool**      | **Purpose**                                |
| ------------- | ------------------------------------------ |
| **`javac`**   | Compiles `.java` files into `.class` files |
| **`java`**    | Launches and runs Java applications        |
| **`jar`**     | Creates and manages JAR files              |
| **`javadoc`** | Generates API documentation                |
| **`jdb`**     | Java debugger                              |

**Code Example**

```java
// HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

**Compilation and Execution**

```bash
javac HelloWorld.java   // JDK compiler creates HelloWorld.class
java HelloWorld         // JRE starts JVM, JVM executes bytecode
```

**Easy Way to Remember**

* **JDK = Develop + Run**
* **JRE = Run Only**
* **JVM = Execute Bytecode**

```
JDK = JRE + Development Tools (javac, javadoc, jar, etc.)
JRE = JVM + Core Libraries (java.lang, java.util, etc.)
JVM = Runtime execution environment
```

## 3. How Does JVM Work?

**JVM (Java Virtual Machine)** is a part of the **JRE (Java Runtime Environment)** that **executes Java bytecode** and provides the **"Write Once, Run Anywhere"** capability.

**JVM Architecture  Diagram** 

```text
        Class Loader
             ↓
     Runtime Data Areas
   ------------------------
   Heap | Stack | Method Area
   ------------------------
             ↓
     Execution Engine
   (Interpreter + JIT)
             ↓
      Garbage Collector
```

**Key Features**

* Converts **bytecode (`.class` files)** into **machine code**.
* Provides **platform independence**.
* Handles **memory management** and **Garbage Collection (GC)**.
* Manages **thread execution** and runtime environment.
* Uses **JIT (Just-In-Time) Compiler** for better performance.

**How It Works**

1. Java source code (`.java`) is compiled by the **Java Compiler (`javac`)** into **bytecode (`.class`)**.
2. The **Class Loader** loads the bytecode into the JVM.
3. The **Bytecode Verifier** checks the code for security and correctness.
4. The **Execution Engine** executes the bytecode:

   * **Interpreter** reads and executes bytecode line by line.
   * **JIT Compiler** converts frequently used bytecode into **native machine code** for faster execution.
5. The **Garbage Collector (GC)** automatically removes unused objects from memory.

**JVM Internal Components**

* **Class Loader** → Loads `.class` files.
* **Method Area** → Stores class metadata and static variables.
* **Heap Memory** → Stores objects and instance variables.
* **Stack Memory** → Stores method calls and local variables.
* **PC Register** → Keeps track of the current instruction for each thread.
* **Native Method Stack** → Handles native (non-Java) method execution.
* **Execution Engine** → Executes bytecode using the Interpreter and JIT Compiler.

**When to Use**

* JVM works **automatically whenever a Java application runs**.
* It provides **memory management, portability, and optimized execution** without developer intervention.

**Code Example**

```java id="j4m8vk"
// Source Code
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, JVM!");
    }
}
```

**Execution Flow**

```text
.java Source Code
        ↓ (javac)
.class Bytecode
        ↓
   Class Loader
        ↓
Bytecode Verifier
        ↓
 Execution Engine
 (Interpreter + JIT)
        ↓
   Machine Code
        ↓
      Output
```

## 3. **Difference Between `JAR`, `WAR`, and `EAR`**




**`JAR`**, **`WAR`**, and **`EAR`** are Java packaging formats used to bundle and deploy applications.

* **`JAR (Java ARchive)`**: Packages Java classes and libraries.
* **`WAR (Web Application ARchive)`**: Packages Java web applications.
* **`EAR (Enterprise ARchive)`**: Packages complete enterprise applications containing multiple modules.


**Key Differences**

| **Feature**    | **JAR**                              | **WAR**                           | **EAR**                                       |
| -------------- | ------------------------------------ | --------------------------------- | --------------------------------------------- |
| **Full Form**  | Java ARchive                         | Web Application ARchive           | Enterprise ARchive                            |
| **Purpose**    | Package Java classes/libraries       | Package web applications          | Package enterprise applications               |
| **Extension**  | `.jar`                               | `.war`                            | `.ear`                                        |
| **Contains**   | `.class` files, resources, libraries | JARs + Servlets + JSP + `WEB-INF` | WARs + JARs + deployment descriptors          |
| **Deployment** | JVM                                  | Web Server (Tomcat, Jetty)        | Application Server (JBoss, WebLogic, WildFly) |
| **Main Use**   | Standalone apps and libraries        | Web applications                  | Multi-module enterprise systems               |

**How It Works**

* **`JAR`**

  * Bundles compiled Java classes and resources into a single file.
  * Can be executed directly if it contains a **`Main-Class`** entry in `MANIFEST.MF`.

* **`WAR`**

  * Packages all web components like **Servlets**, **JSP**, HTML, CSS, JavaScript, and libraries.
  * Deployed to a **Servlet Container** such as **Tomcat**.

* **`EAR`**

  * Bundles multiple **JAR** and **WAR** files into one deployment unit.
  * Deployed to a full **Java EE/Jakarta EE Application Server**.

**Typical Structure**

**JAR Structure**

```text id="epm6bq"
myapp.jar
 ├── META-INF/
 │    └── MANIFEST.MF
 └── com/example/*.class
```

**WAR Structure**

```text id="l2u4kq"
myapp.war
 ├── index.jsp
 ├── WEB-INF/
 │    ├── web.xml
 │    ├── classes/
 │    └── lib/
```

**EAR Structure**

```text id="6g4ovr"
myapp.ear
 ├── app.war
 ├── service.jar
 └── META-INF/
      └── application.xml
```

**When to Use**

| **Scenario**                                       | **Best Choice** |
| -------------------------------------------------- | --------------- |
| Java utility or standalone application             | **JAR**         |
| Spring Boot application (embedded server)          | **JAR**         |
| Traditional web application on Tomcat              | **WAR**         |
| Large enterprise application with multiple modules | **EAR**         |

**Code Example**

**Create a JAR using Maven**

```xml id="waw4me"
<packaging>jar</packaging>
```

**Create a WAR using Maven**

```xml id="tw3kwg"
<packaging>war</packaging>
```

**Create an EAR using Maven**

```xml id="6a3ukm"
<packaging>ear</packaging>
```

**Spring Boot Interview Point**

* **Spring Boot** applications are usually packaged as **`JAR`** because they include an **embedded Tomcat/Jetty server** and can run independently.
* Traditional **Spring MVC** applications are often packaged as **`WAR`** and deployed to an external server.

**Key Features**

* **`JAR`**

  * Lightweight and executable.
  * Commonly used for libraries and Spring Boot applications.

* **`WAR`**

  * Contains all web application resources.
  * Runs on a web server or servlet container.

* **`EAR`**

  * Supports multiple modules in a single deployment.
  * Used in large-scale enterprise applications.

**Easy Way to Remember**

* **`JAR` = Java Application or Library**
* **`WAR` = Web Application**
* **`EAR` = Enterprise Application**
* **Spring Boot → Usually `JAR`**
* **Traditional Web App → Usually `WAR`**


## 3. What is the difference between Structured Programming and OOP?




* **Structured Programming** is a programming paradigm that organizes code into **functions or procedures** and follows a **top-down approach**.
* **Object-Oriented Programming (OOP)** is a programming paradigm that organizes code into **objects and classes** and follows a **bottom-up approach**.


**Key Differences**

| **Feature**             | **Structured Programming**   | **Object-Oriented Programming (OOP)**    |
| ----------------------- | ---------------------------- | ---------------------------------------- |
| **Main Focus**          | Functions/Procedures         | Objects and Classes                      |
| **Approach**            | Top-Down                     | Bottom-Up                                |
| **Data & Functions**    | Separate                     | Combined into objects                    |
| **Reusability**         | Limited                      | High through inheritance and composition |
| **Data Security**       | Less secure                  | Better security through encapsulation    |
| **Code Maintenance**    | Difficult for large projects | Easier for large and complex projects    |
| **Real-World Modeling** | Not natural                  | Models real-world entities effectively   |
| **Examples**            | C, Pascal                    | Java, C++, C#, Python                    |

**How It Works**

* **Structured Programming**

  * Breaks a large problem into smaller **functions**.
  * Functions operate on shared data.
  * Execution flows sequentially using loops and conditional statements.

* **Object-Oriented Programming**

  * Breaks a problem into **objects**.
  * Each object contains **data (fields)** and **behavior (methods)**.
  * Objects interact with each other to perform tasks.

**When to Use**

| **Scenario**                          | **Best Choice**            |
| ------------------------------------- | -------------------------- |
| Small utility programs                | **Structured Programming** |
| Large enterprise applications         | **OOP**                    |
| Applications requiring code reuse     | **OOP**                    |
| Programs with simple sequential logic | **Structured Programming** |

**Code Example**

**Structured Programming (Function-Based)**

```java id="x3t9zk"
public class Calculator {

    static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println(add(10, 20));
    }
}
```

**Object-Oriented Programming (Object-Based)**

```java id="krz8xq"
class Calculator {
    int a, b;

    Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    int add() {
        return a + b;
    }
}

public class Demo {
    public static void main(String[] args) {
        Calculator calc = new Calculator(10, 20);
        System.out.println(calc.add());
    }
}
```

**Key Features**

* **Structured Programming**

  * Function-oriented.
  * Simple and easy for small programs.
  * Limited reusability.

* **Object-Oriented Programming**

  * Object-oriented and modular.
  * Supports **Encapsulation**, **Inheritance**, **Polymorphism**, and **Abstraction**.
  * Promotes high code reusability and maintainability.

**Advantages of OOP Over Structured Programming**

* Better **code reusability**.
* Improved **security** through encapsulation.
* Easier **maintenance** and **scalability**.
* Closer representation of **real-world problems**.

**Easy Way to Remember**

* **Structured Programming = Functions + Top-Down**
* **OOP = Objects + Bottom-Up**
* **Small Projects → Structured Programming**
* **Large, Scalable Projects → OOP**



## 3. What are the main principles of Object-Oriented Programming?

**Object-Oriented Programming** is based on four fundamental principles that promote code reusability, maintainability, and modularity.

**Four OOP Principles:**

* **Encapsulation:** Encapsulation means binding data and methods together and protecting data using access modifiers (`private`, `protected`, `public`) and access using getters and setters.
* **Inheritance:** Inheritance means a child class can use properties and methods of a parent class using `extends`.
* **Polymorphism:** Polymorphism means one method can perform different actions using overloading or overriding.
* **Abstraction:** Abstraction means hiding internal implementation and showing only necessary details using abstract class or interface.


- **1. Encapsulation:** Data is `private` and accessed using `getter and setter methods`.
👉 Hide data and control access. Keep data safe and access it through methods

```java
class Person {
    private String name;   // private variable

    public void setName(String name) {   // setter
        this.name = name;
    }

    public String getName() {   // getter
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("John");
        System.out.println(p.getName());
    }
}
```

- **2. Inheritance:** Child class `inherits properties from parent class` using `extends`.
👉 Reuse parent properties in child. Child gets features from parent

```java
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();   // inherited method
        d.bark();
    }
}
```

- **3. Polymorphism:** Method Overloading
👉 Same method, different behavior. One thing, many forms
```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.add(5, 10));
        System.out.println(c.add(5, 10, 15));
    }
}
```

- **4. Abstraction** abstract class or interface
👉 Hide complexity, show only needed parts. Show what is needed, hide the rest.
```java
abstract class Vehicle {
    abstract void start();   // abstract method
}

class Car extends Vehicle {
    void start() {
        System.out.println("Car starts with key");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle v = new Car();
        v.start();
    }
}
```

## 4. What is a package in Java? 
In Java, a **package** is a **namespace that groups related classes, interfaces, and sub-packages together**. It helps organize code, avoid naming conflicts, and control access to classes.

```java
package com.example.utils;

public class Helper {
    public static void printHello() {
        System.out.println("Hello!");
    }
}
```


## 7. How do you read user input from the console in Java?
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("You entered: " + input);

        scanner.close();
    }
}
```

## 8. How do you iterate through a collection in Java?
```java
// Example using for-each loop:
List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");
for (String fruit : fruits) {
   System.out.println(fruit);
}
// Example using regular for loop:
for (int i = 0; i < fruits.size(); i++) {
   System.out.println(fruits.get(i));
}
```

## 8.  Serialization and Deserialization in Java?

**Serialization** is the process of **converting a Java object into a byte stream** so it can be **stored in a file**, **saved to a database**, or **transmitted over a network**. 

**Deserialization** is the reverse process of converting the **Byte Stream** back into the original **Java Object**.


**Key Features**

* **Converts Object → Byte Stream**
* **Supports Object Persistence**
* **Enables Network Communication**
* **Uses `Serializable` Interface**
* **Supports Deserialization**
* **Can Skip Fields using `transient`**

**How it Works**

1. Create a class that implements **`Serializable`**.
2. Use **`ObjectOutputStream`** to serialize the object.
3. Store the byte stream in a file or send it over the network.
4. Use **`ObjectInputStream`** to deserialize the object.
5. The original object is reconstructed.

**When to Use**

* **Saving Objects to Files**
* **Caching Objects**
* **Sending Objects over Network**
* **Distributed Systems**
* **Session Replication**

**Example**


**Serialization**

```java
import java.io.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class SerializeDemo {
    public static void main(String[] args) throws Exception {

        Employee emp = new Employee(101, "John");

        FileOutputStream fos = new FileOutputStream("employee.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(emp);

        oos.close();
        System.out.println("Object Serialized");
    }
}
```

**Deserialization**

```java
import java.io.*;
public class DeserializeDemo {
    public static void main(String[] args) throws Exception {

        FileInputStream fis = new FileInputStream("employee.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Employee emp = (Employee) ois.readObject();

        ois.close();

        System.out.println(emp.id);
        System.out.println(emp.name);
    }
}
```

**Prevents a field from being serialized.**

```Java
class Employee implements Serializable {

    String name;

    transient String password;
}
```

**Advantages**

* **Easy Object Persistence**
* **Supports Network Communication**
* **Simple to Implement**
* **Useful for Caching and Distributed Systems**


**Common Serializable Follow-up Questions**

**1. What is the `Serializable` interface?**

It is a **marker interface** with **no methods**. It tells the JVM that the object can be serialized.

```java
public class Employee implements Serializable {
}
```

**2. What is `serialVersionUID`?**

It is a **unique version identifier** used during deserialization to verify that the sender and receiver are using **compatible class versions**.

```java
private static final long serialVersionUID = 1L;
```

**3. What is the `transient` keyword?**

A **`transient`** field is **not serialized**.

```java
class Employee implements Serializable {

    private String name;

    transient String password; // Not serialized
}
```

**4. What happens if a class does not implement `Serializable`?**

A **`NotSerializableException`** is thrown during serialization.

**5. What is the difference between Serialization and Deserialization?**

| **Serialization**         | **Deserialization**      |
| ------------------------- | ------------------------ |
| Object → Byte Stream      | Byte Stream → Object     |
| Uses `ObjectOutputStream` | Uses `ObjectInputStream` |
| Writes object             | Reads object             |


**Common Deserialization Follow-up Questions**

**1. Which class is used for Deserialization?**

**`ObjectInputStream`**

```java
ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.ser"));
```

**2. Which method is used for Deserialization?**

**`readObject()`**

```java
Employee emp = (Employee) in.readObject();
```

**3. Can every class be deserialized?**

**No.** The class must implement the **`Serializable`** interface.

**4. What happens if `serialVersionUID` is different?**

Java throws an **`InvalidClassException`** because the serialized object version does not match the current class version.

**5. What happens to `transient` fields after deserialization?**

They are **not restored** and receive their **default values**.

```java
transient String password;
```

After deserialization:

```java
password = null;
```



# ✅ 02. Java Data Types and Variables

## 1. What are data types in Java?

**Answer:**
There is two type of data Primitive Data and Non-Primitive Data

**8 Primitive Data :**
- **byte** - 8-bit integer (-128 to 127)
- **short** - 16-bit integer (-32,768 to 32,767)
- **int** - 32-bit integer (most commonly used)
- **long** - 64-bit integer (add 'L' suffix)
- **float** - 32-bit decimal (add 'f' suffix)
- **double** - 64-bit decimal (default for decimals)
- **char** - 16-bit Unicode character
- **boolean** - true or false

**8 Non-Primitive Data :**
- **String** - Used to store text
- **Array** - Used to store multiple values
- **Class** - Blueprint for objects
- **Interface** - Defines method structure for classes

**Example:**
```java
int age = 25;
double salary = 50000.50;
char grade = 'A';
boolean isActive = true;
long population = 1000000L;
```

## 2. What is the difference between primitive and non primitive(reference types)?
**Primitive types** in Java, like int, double, and boolean, store actual values in memory and are stored on the stack. They are fast and have a fixed size.

**Reference types**, like objects, arrays, and strings, store a reference or memory address pointing to the actual data in the heap. They can have methods, support polymorphism, and are generally more flexible but slightly slower.

```java
int x = 10;        // primitive - stores value 10
String name = "John"; // reference - stores address to "John" object
```

## 3. What is autoboxing and unboxing and Casting?

**Autoboxing** is the automatic conversion of a **primitive type** into its corresponding **wrapper class**.
  ```java
  int num = 10;
  Integer numObj = num;  // Autoboxing: int → Integer
  ```

**Unboxing** is the automatic conversion of a **wrapper class** back to its **primitive type**.
  ```java
  Integer numObj = 20;
  int num = numObj;  // Unboxing: Integer → int
  ```

**Casting** in Java means **converting one data type into another data type**.
```java
double a = 10.5;
int b = (int) a;  // Manual casting
System.out.println(b); // 10
```


## 5. Normal, final, static, static final, volatile, abstract, transient, Serializable?

**Normal Variable** Declared inside a class but outside methods.
```java
class Student {
    int age = 20;   // normal variable
}
```

**final** Once assigned, value cannot be changed.
```java
class Student {
    final int age = 20;
    age = 10;  // allowed only once
}
```

**static** Belongs to class, not object. Only one copy in memory.
```java
class Student {
    static String college = "IIT";
}
```

**static final** Variable (Constant) in Class-level
```java
class Student {
    static final double PI = 3.14;
}
```

**volatile** is a keyword used in multithreading. it ensures variable changes are immediately visible to all threads (prevents caching issues)
```java
class Test {
    volatile boolean flag = true;
}
```

**Abstract** Abstract is only for: Classes and  Methods
```java
abstract class Animal {
    abstract void sound();
}
```

**transient** Used for serialization and but not saved. Used for sensitive data.
```java
public class Student implements Serializable { 
    private String username;
    private transient String password; // If you do NOT want a field to serialize:
    // `transient` is a serialization keyword in Java. password will NOT be saved.
}
```

**Serializable** is a marker interface in Java used to convert an object into a byte stream so that it can be stored, transferred, or reconstructed later.

**Serialize Object**
```java
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Employee emp = new Employee(101, "John");
        FileOutputStream file = new FileOutputStream("employee.txt");

        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(emp);

        out.close();
        file.close();

        System.out.println("Object Serialized");
    }
}
```

**Deserialize** is the reverse process of converting the Byte Stream back into a Java Object.

```java
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadData {
    public static void main(String[] args) throws Exception {

        FileInputStream file = new FileInputStream("employee.txt");

        ObjectInputStream in = new ObjectInputStream(file);
        Employee emp = (Employee) in.readObject();

        System.out.println(emp.id);
        System.out.println(emp.name);

        in.close();
        file.close();
    }
}
```



**Difference between `transient` and `volatile`**

| Feature       | `transient`                                      | `volatile`                                          |
|---------------|--------------------------------------------------|-----------------------------------------------------|
| Purpose       | Excludes field from **serialization**            | Ensures **visibility** of field across threads      |
| Context       | Used with Java Serialization (`Serializable`)    | Used in multi-threaded programming                  |
| Effect        | Field is skipped when object is serialized       | Field is always read/written from main memory       |
| Thread safety | No effect on threads                             | Provides visibility guarantee (not atomicity)       |


## 6. What is Instance, Static, Abstract, and Final Methods?

**Instance Method:** A method that belongs to an object and is called using an instance of the class.
```java
class Student {
    void study() {   // Instance method
        System.out.println("Studying...");
    }
}

Student s = new Student();
s.study();   // Called using object
```

**Static Method:** A method that belongs to the class and is called using the class name.
```java
class Student {
    static void schoolName() {   // Static method
        System.out.println("ABC School");
    }
}

Student.schoolName();   // Called using class name
```

**Abstract Method:** A method declared without a body that must be implemented by a subclass.
```java
abstract class Animal {
    abstract void sound();   // Abstract method
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}
```

**Final Method:** A method that cannot be overridden by a subclass.
```java
class Animal {
    final void breathe() {   // Final method
        System.out.println("Breathing...");
    }
}

class Dog extends Animal {
    // void breathe() {}  ❌ Not allowed (Compile-time error)
}
```


## 9. What is coercion in Java?

**Coercion in Java** is the process of converting a value from one data type to another, either automatically by the compiler or manually by the programmer, to make data types compatible in expressions or assignments.


```java
int num = 10;
double result = num; // Automatic coercion - int to double

double d = 10.5;
int i = (int) d; // Explicit casting required - double to int
```

## 10. What is instance of in java

**`instanceof`** in Java is an **operator** used to check whether an object is an **instance of a specific class or interface**. it is introduce in Java 16+


```java
class Animal {}
class Dog extends Animal {}

Animal a = new Dog();

System.out.println(a instanceof Dog);    // true
System.out.println(a instanceof Animal); // true
```

## 11. What is Predicate in java?



A **`Predicate<T>`** is a **functional interface** introduced in **Java 8** in the `java.util.function` package. It takes **one input argument** and returns a **boolean (`true` or `false`)** result.

**Method Signature**

```java id="of0zyj"
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```

**Key Features**

* **Functional Interface** with a single abstract method: **`test()`**.
* Takes **one input** and returns **`true` or `false`**.
* Works seamlessly with **Lambda Expressions** and the **Stream API**.
* Supports combining multiple conditions using **`and()`**, **`or()`**, and **`negate()`**.

**How It Works**

* You define a condition using a lambda expression.
* The **`test()`** method checks whether the input satisfies that condition.
* It returns **`true`** if the condition matches, otherwise **`false`**.

```java id="iq2it6"
Predicate<Integer> isEven = n -> n % 2 == 0;

System.out.println(isEven.test(4)); // true
System.out.println(isEven.test(5)); // false
```


**When to Use**

| **Scenario**                            | **Use `Predicate`?**           |
| --------------------------------------- | ------------------------------ |
| Filter a list using Stream API          | **Yes**                        |
| Validate user input                     | **Yes**                        |
| Check if an object matches a condition  | **Yes**                        |
| Perform calculations and return a value | **No, use `Function` instead** |

**Code Example**

```java id="f4xrfb"
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25);

        Predicate<Integer> isEven = n -> n % 2 == 0;

        numbers.stream()
               .filter(isEven)
               .forEach(System.out::println);
    }
}
```

**Output:**

```text id="0lfew3"
10
20
```

**Combining Predicates**

```java id="p07m4e"
Predicate<Integer> greaterThan10 = n -> n > 10;
Predicate<Integer> isEven = n -> n % 2 == 0;

Predicate<Integer> condition =
        greaterThan10.and(isEven);

System.out.println(condition.test(12)); // true
System.out.println(condition.test(9));  // false
```

**Common Methods**

| **Method**      | **Purpose**                          |
| --------------- | ------------------------------------ |
| **`test(T t)`** | Evaluates the condition              |
| **`and()`**     | Combines two predicates with **AND** |
| **`or()`**      | Combines two predicates with **OR**  |
| **`negate()`**  | Reverses the result                  |
| **`isEqual()`** | Checks object equality               |

**Easy Way to Remember**

* **`Predicate` = One Input + Boolean Output**
* **Used for Filtering and Validation**
* **Main Method = `test()`**



## 14. What is `var` keyword in Java(10)?

**var** is a feature introduced in **Java 10** that allows **Local Variable Type Inference**, meaning the compiler automatically determines the variable's type from the assigned value.

**Key Features**

* **Reduces boilerplate code**.
* Type is inferred at **compile time**.
* Works only with **local variables**.
* Improves code readability when the type is obvious.
* Still maintains **strong typing**.

**How It Works**

The compiler looks at the value assigned to the variable and automatically determines its type.

```java
var name = "John";    // String
var age = 25;         // int
var salary = 5000.50; // double
```

The compiler internally treats them as:

```java
String name = "John";
int age = 25;
double salary = 5000.50;
```

**Why Use**

* Makes code shorter and cleaner.
* Useful when the type is long or obvious.
* Improves readability in loops and collections.

**When to Use**

* Local variables inside methods.
* Loop variables.
* Collection and Stream operations.
* When the variable type is clear from the assignment.

**Code Example**

```java
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        var name = "Java";
        var version = 21;

        List<String> languages =
                List.of("Java", "Python");

        for (var language : languages) {
            System.out.println(language);
        }
    }
}
```

**Limitations**

**var** cannot be used for:

* Instance variables.
* Static variables.
* Method parameters.
* Method return types.
* Variables without initialization.

```java
var x;        // Compilation Error

class Test {
    var name = "John"; // Compilation Error
}
```


# ✅ 03. HashMap / equals / hashCode

## 12. What are `equals()` and `hashCode()` in Java??

* **`equals()`** is a method from the `Object` class used to compare the **content or logical equality** of two objects.

* **`hashCode()`** is a method from the `Object` class that returns an **integer hash value** representing the object, mainly used in **hash-based collections** like `HashMap`, `HashSet`, and `Hashtable`.


**Key Features**

| **Feature**            | **`equals()`**          | **`hashCode()`**       |
| ---------------------- | ----------------------- | ---------------------- |
| **Purpose**            | Compares object content | Generates hash value   |
| **Return Type**        | `boolean`               | `int`                  |
| **Defined In**         | `Object` class          | `Object` class         |
| **Used By**            | Equality checking       | Hash-based collections |
| **Can Be Overridden?** | Yes                     | Yes                    |

**How It Works**

1. When an object is added to a **`HashMap`** or **`HashSet`**, Java first calls **`hashCode()`** to find the correct bucket.
2. If multiple objects have the same hash code, Java then calls **`equals()`** to compare their actual contents.
3. This combination provides **fast lookup** and **correct equality checking**.

**Contract Between `equals()` and `hashCode()`**

* If **two objects are equal (`equals()` returns `true`)**, they **must have the same `hashCode()`**.
* If two objects have the same `hashCode()`, they **may or may not be equal**.
* If you **override `equals()`**, you should also **override `hashCode()`**.


**When to Use**

| **Scenario**                        | **Use `equals()` and `hashCode()`?** |
| ----------------------------------- | ------------------------------------ |
| Comparing object data               | **Yes**                              |
| Using objects as `HashMap` keys     | **Yes**                              |
| Storing custom objects in `HashSet` | **Yes**                              |
| Comparing primitive values          | **No, use `==`**                     |

**Code Example**

```java id="n7z3ka"
class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;

        Employee emp = (Employee) obj;
        return id == emp.id && name.equals(emp.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name);
    }
}
```

**Usage Example**

```java id="jj71he"
Employee e1 = new Employee(1, "John");
Employee e2 = new Employee(1, "John");

System.out.println(e1.equals(e2));      // true
System.out.println(e1.hashCode());      // Same value
System.out.println(e2.hashCode());      // Same value
```

**What Happens in `HashMap`?**

```java id="3frbch"
Map<Employee, String> map = new HashMap<>();

map.put(new Employee(1, "John"), "Developer");

System.out.println(
    map.get(new Employee(1, "John"))
); // Developer
```

This works correctly because both **`equals()`** and **`hashCode()`** are overridden.

**Easy Way to Remember**

* **`equals()` = Compare Object Content**
* **`hashCode()` = Generate Hash Value**
* **Equal Objects = Same Hash Code**
* **Always override both together**


## 13. How to Override the hashCode Method Properly in Java?

The **`hashCode()`** method returns an **integer hash value** for an object. It is mainly used by **hash-based collections** like **`HashMap`**, **`HashSet`**, and **`Hashtable`** for fast storage and retrieval.


**Key Rules (hashCode Contract)**

1. If **`obj1.equals(obj2)` is `true`**, then **`obj1.hashCode() == obj2.hashCode()`**.
2. If two objects have the **same hash code**, they **may or may not be equal**.
3. If you **override `equals()`**, you **must also override `hashCode()`**.
4. Use only the **fields involved in `equals()`** when calculating the hash code.

**How It Works**

* When an object is inserted into a **`HashMap`** or **`HashSet`**, Java first calls **`hashCode()`** to find the bucket.
* If multiple objects have the same hash code, Java uses **`equals()`** to identify the correct object.


**When to Use**

* Whenever you override **`equals()`**.
* When custom objects are used as **keys in a `HashMap`** or **elements in a `HashSet`**.

**Recommended Way (Using `Objects.hash()`)**

```java id="qv6svj"
import java.util.Objects;

class Employee {
    private int id;
    private String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;

        Employee other = (Employee) obj;
        return id == other.id &&
               Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```

**Manual Way (How `hashCode()` is Traditionally Written)**

```java id="tq95bw"
@Override
public int hashCode() {
    int result = 17;
    result = 31 * result + id;
    result = 31 * result + (name == null ? 0 : name.hashCode());
    return result;
}
```

**Why use `31`?**

* **31** is a prime number that helps generate a better distribution of hash values and reduces collisions.

**Example**

```java id="f2xepm"
Employee e1 = new Employee(101, "Alice");
Employee e2 = new Employee(101, "Alice");

System.out.println(e1.equals(e2));    // true
System.out.println(e1.hashCode());    // Same hash code
System.out.println(e2.hashCode());    // Same hash code
```

**Common Mistake**

```java id="7bmsx6"
class Employee {
    int id;

    @Override
    public boolean equals(Object obj) {
        return true;
    }
    // hashCode() not overridden
}
```

This breaks the **`equals()`-`hashCode()` contract** and causes incorrect behavior in **`HashMap`** and **`HashSet`**.


**Easy Way to Remember**

* **`equals()` = Compare Object Content**
* **`hashCode()` = Find Object Bucket**
* **Same Content = Same Hash Code**
* **Override Both Together**


## 20. **What is a bucket in HashMap?**

A **bucket** is a place inside a **HashMap** where **key-value pairs** are stored.

* A **HashMap** consists of an **array of buckets**.
* Each bucket can store **one or more entries**.
* If multiple entries end up in the same bucket, they are stored together.

**Example**

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Apple");
map.put(2, "Banana");
map.put(3, "Orange");
```

Each key is placed into a bucket based on its **hash value**.


## 20. **How does HashMap determine which bucket to put an element in?**

A **HashMap** uses the key's **hashCode()** to calculate the bucket index.

**Steps**

1. Call **hashCode()** on the key.
2. Process the hash value internally.
3. Calculate the **bucket index** using the hash and the current array size.
4. Store the entry in that bucket.

**Simplified Formula**

```text
bucketIndex = hashCode % numberOfBuckets
```

In reality, Java uses a more optimized calculation:

```java
index = (n - 1) & hash
```

where **n** is the number of buckets.

**Example**

```java
Map<String, Integer> map = new HashMap<>();

map.put("Java", 1);
```

The **hashCode()** of `"Java"` is calculated, and the result determines which bucket stores the entry.

## 20. **What is a collision in HashMap?**

A **collision** happens when **two different keys** are assigned to the **same bucket**.

This does **not** mean the keys are equal. It only means their calculated bucket index is the same.

**Example**

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Apple");
map.put(17, "Banana"); // Assume both map to the same bucket
```

Both entries are stored in the same bucket.

In Java:

* Before **Java 8**, collided entries were stored as a **Linked List**.
* Since **Java 8**, if a bucket becomes large (more than **8** entries and the table is large enough), it is converted into a **Red-Black Tree**, improving lookup from **O(n)** to **O(log n)**.


## 20. **How does HashMap handle collisions?**

A **collision** happens when **different keys** are placed in the **same bucket**.

**HashMap** handles collisions by storing multiple entries in the same bucket.

* **Java 7 and earlier:** Entries are stored in a **Linked List**.
* **Java 8+:** Entries start in a **Linked List**. If the bucket becomes too large, it is converted into a **Red-Black Tree** for faster searching.

**Example**

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Apple");
map.put(17, "Banana"); // Assume same bucket
```

Both entries are stored in the same bucket and are distinguished using **equals()**.


## 20. **What happens when 8 elements are reached in one bucket?**

In **Java 8+**, if a bucket contains **more than 8 entries** and the **HashMap capacity is at least 64**, the bucket is converted from a **Linked List** to a **Red-Black Tree**.

This process is called **Treeification**.

**Why?**

* **Linked List** search: **O(n)**
* **Red-Black Tree** search: **O(log n)**

This improves performance when many collisions occur.

If the table size is **less than 64**, **HashMap** first **resizes** instead of treeifying.


## 20. **What is the equals() and hashCode() contract?**

The **equals()** and **hashCode()** methods must work together correctly.

**Rules**

* If **two objects are equal** using **equals()**, they **must have the same hashCode()**.
* If **two objects have the same hashCode()**, they are **not necessarily equal**.
* If you override **equals()**, you should also override **hashCode()**.

**Example**

```java
class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Person p = (Person) obj;
        return name.equals(p.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
```

**Usage**

```java
Map<Person, String> map = new HashMap<>();

map.put(new Person("John"), "Developer");

System.out.println(map.get(new Person("John"))); // Developer
```

Without overriding both methods correctly, the lookup may fail.



## 20. **If two objects are equal by equals(), what can you say about their hashCode()?**

If **two objects are equal** according to **equals()**, they **must have the same hashCode()**.

This is a requirement of the **equals() / hashCode() contract**.

**Example**

```java
class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Person &&
               name.equals(((Person) obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

Person p1 = new Person("John");
Person p2 = new Person("John");

System.out.println(p1.equals(p2));              // true
System.out.println(p1.hashCode() == p2.hashCode()); // true
```


## 20. **If two objects have the same hashCode(), are they necessarily equal by equals()?**

**No.**

Two different objects can have the **same hashCode()**, but **equals()** can still return **false**.

This situation is called a **hash collision**.

**Example**

```java
String s1 = "FB";
String s2 = "Ea";

System.out.println(s1.hashCode()); // Same hash
System.out.println(s2.hashCode()); // Same hash

System.out.println(s1.equals(s2)); // false
```


## 20. **What happens if you override equals() but not hashCode()?**

If you override **equals()** but **do not override hashCode()**, objects that are **equal** may have **different hash codes**.

This **breaks the equals() / hashCode() contract**.

As a result, collections like **HashMap** and **HashSet** may not find the object correctly.

**Example**

```java
class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Person &&
               name.equals(((Person) obj).name);
    }
    // hashCode() not overridden
}

Map<Person, String> map = new HashMap<>();

map.put(new Person("John"), "Developer");

System.out.println(map.get(new Person("John"))); // null
```

Although the two objects are **equal**, they have **different hash codes**, so **HashMap** looks in a different bucket.


## 20. **What happens if you override hashCode() but not equals()?**

If you override **hashCode()** but **do not override equals()**, objects may have the **same hash code**, but they are still compared using the default **equals()** from **Object**, which compares **memory addresses**.

As a result, logically identical objects are treated as **different**.

**Example**

```java
class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
    // equals() not overridden
}

Person p1 = new Person("John");
Person p2 = new Person("John");

System.out.println(p1.equals(p2)); // false
```

Even though both objects have the same **hashCode()**, they are **not equal** because **Object.equals()** checks reference equality.



## 20. **What is load factor in HashMap?**

The **load factor** determines **when a HashMap should resize**.

* The **default load factor** is **0.75**.
* It represents how full the **HashMap** can become before increasing its capacity.

**Formula**

```text
Threshold = Capacity × Load Factor
```

**Example**

```java id="mowvqu"
Map<Integer, String> map = new HashMap<>(16, 0.75f);
```

* **Capacity** = 16
* **Load Factor** = 0.75
* **Threshold** = 16 × 0.75 = 12

When the **13th element** is added, the **HashMap** resizes.



## 20. **What is capacity in HashMap?**

**Capacity** is the **number of buckets** in a **HashMap**.

* The **default capacity** is **16**.
* Capacity increases automatically when needed.
* It is usually **doubled** during resizing.

**Example**

```java id="ibmav7"
Map<Integer, String> map = new HashMap<>(32);
```

This creates a **HashMap** with an initial **capacity of 32 buckets**.

**Interview Answer**

The **capacity** is the **number of buckets** in a **HashMap**. The default capacity is **16**, and it usually **doubles** when the map is resized.

**When does rehashing occur in HashMap?**

**Rehashing** occurs when the number of stored elements **exceeds the threshold**.

The threshold is calculated as:

```text
Threshold = Capacity × Load Factor
```

With the default settings:

* **Capacity** = 16
* **Load Factor** = 0.75
* **Threshold** = 12

Adding the **13th element** triggers **rehashing**.



## 20. **What happens during rehashing?**

During **rehashing**:

1. A **new bucket array** is created, usually with **double the capacity**.
2. All existing entries are **recalculated**.
3. Each entry is placed into its **new bucket** based on the new capacity.

**Example**

```java id="xhk8rm"
Map<Integer, String> map = new HashMap<>(4);

map.put(1, "A");
map.put(2, "B");
map.put(3, "C");
map.put(4, "D"); // May trigger resizing
```

After resizing, the entries are redistributed across the new buckets.

**Interview Answer**

During **rehashing**, **HashMap** creates a **larger bucket array** (usually **double** the capacity), recalculates the bucket index for every entry, and moves all entries into their new buckets. This reduces **collisions** and maintains good performance.


## 20. **What is the difference between HashMap and Hashtable?**

| **Feature**       | **HashMap**                           | **Hashtable**                         |
| ----------------- | ------------------------------------- | ------------------------------------- |
| **Thread Safety** | **Not synchronized**                  | **Synchronized**                      |
| **Performance**   | **Faster**                            | **Slower** because of synchronization |
| **Null Keys**     | **One null key** allowed              | **Not allowed**                       |
| **Null Values**   | **Multiple null values** allowed      | **Not allowed**                       |
| **Introduced**    | Part of the **Collections Framework** | Legacy class from early Java          |

**Example**

```java id="v0f7pd"
Map<Integer, String> hashMap = new HashMap<>();
hashMap.put(null, "Java");     // Allowed

Map<Integer, String> hashtable = new Hashtable<>();
// hashtable.put(null, "Java"); // Throws NullPointerException
```


## 20. **How does HashMap work in a multi-threaded environment?**

A **HashMap** is **not thread-safe**.

If multiple threads **read and modify** a **HashMap** at the same time, it can lead to:

* **Data inconsistency**
* **Lost updates**
* **Unexpected behavior**

**Unsafe Example**

```java id="vwdak2"
Map<Integer, String> map = new HashMap<>();

Thread t1 = new Thread(() -> map.put(1, "A"));
Thread t2 = new Thread(() -> map.put(2, "B"));

t1.start();
t2.start();
```

This code is **not safe** if multiple threads modify the map simultaneously.

For concurrent access, use **ConcurrentHashMap**.

**Safe Example**

```java id="aykgzy"
Map<Integer, String> map = new ConcurrentHashMap<>();

map.put(1, "A");
map.put(2, "B");
```




# ✅ 03. Java Classes and Objects

## 4. What is a class in Java?

A **Class** in Java is a **blueprint or template** used to create **objects**. It defines the **properties (fields/variables)** and **behaviors (methods)** that an object will have.

**Key Features**

* **Blueprint** for creating objects.
* Contains **fields**, **methods**, **constructors**, and **blocks**.
* Supports **Object-Oriented Programming (OOP)** concepts like **encapsulation**, **inheritance**, and **polymorphism**.
* An object is an **instance of a class**.

**How It Works**

1. Define a **class** with variables and methods.
2. Create an **object** using the `new` keyword.
3. Access the object's fields and methods using the object reference.

**When to Use**

* Use a **class** whenever you want to model a **real-world entity** such as `Employee`, `Student`, `Car`, or `Product`.
* It helps organize code and promotes **reusability** and **maintainability**.

**Code Example**

```java id="bn5k2m"
class Student {
    String name;

    void display() {
        System.out.println("Name: " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student();   // Object creation
        s.name = "Alice";
        s.display();
    }
}
```

**Common Interview Follow-up Questions**

**1. What is the difference between a Class and an Object?**

| **Class**                          | **Object**                    |
| ---------------------------------- | ----------------------------- |
| **Blueprint** for creating objects | **Instance** of a class       |
| Logical entity                     | Physical entity               |
| No memory for instance data        | Occupies memory when created  |
| Declared using **class** keyword   | Created using **new** keyword |

**2. Can a Class exist without an Object?**

**Yes.** A class can exist without creating any objects. It is simply a **definition** until an object is instantiated.

**3. Can we create multiple Objects from one Class?**

**Yes.** A single class can create **any number of objects**, each having its own separate data.

**4. Can a Class have multiple constructors?**

**Yes.** This is called **Constructor Overloading**.

**5. Can a Java file contain multiple classes?**

**Yes.** A Java file can contain multiple classes, but only **one public class**, and the file name must match the **public class** name.

**6. What types of members can a Class contain?**

A class can contain **Fields**, **Methods**, **Constructors**, **Static Members**, **Initialization Blocks**, and **Nested Classes**.



## 2. What is an object?

An **Object** is an **instance of a Class**. A class is a blueprint, and an object is the actual entity created from that blueprint.

**Key Features**

* **State** – Data stored in fields.
* **Behavior** – Actions performed through methods.
* **Identity** – Each object is unique in memory.

**How It Works**

A **Class** defines the structure and behavior. An **Object** is created using that class and gets its own data and methods.

Example:

```java
Car car = new Car();
```


**When to Use**

* Use an **object** whenever you need to represent and work with a **real-world entity**, such as a `Student`, `Employee`, `Car`, or `Product`.
* Multiple objects can be created from the same class with different data.

**Code Example**

```java id="n5x8pk"
class Student {
    String name;

    void display() {
        System.out.println("Name: " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student();   // Object creation
        s.name = "Alice";
        s.display();
    }
}
```

**Common Interview Follow-up Questions**

**0. How is an Object created in Java?**
Using the **`new`** keyword, which allocates memory and calls the **Constructor**.

**1. What is the difference between a Class and an Object?**

| **Class**                          | **Object**                      |
| ---------------------------------- | ------------------------------- |
| **Blueprint** for creating objects | **Instance** of a class         |
| Logical entity                     | Physical entity                 |
| Does not store instance data       | Stores actual data              |
| No memory for instance data        | Occupies memory in the **Heap** |

**2. Where are Objects stored in Java?**

Objects are stored in the **Heap Memory**, while the **reference variable** is stored in the **Stack Memory** (for local variables).

```java
Student s1 = new Student();
```

* **`Student`** → Class
* **`new Student()`** → Creates an object in the **Heap**
* **`s1`** → Reference variable pointing to the object

**3. Can one Class create multiple Objects?**

**Yes.** A single class can create **multiple objects**, and each object has its own independent copy of the instance variables.


**4. What are the three characteristics of an Object?**
**State** (**fields/variables**), **Behavior** (**methods**), and **Identity** (unique reference).


## 3. Difference between class and object?

A **class** is a **blueprint or template** used to create objects.
An **object** is a **real instance of a class** created in memory.


**Key Features**

**Class**

* Acts as a **blueprint/template**
* Does **not occupy memory until objects are created**
* Defines **properties (variables) and behavior (methods)**

**Object**

* Is a **real runtime entity**
* Occupies **memory in heap**
* Represents a **specific instance of a class**

**How it works**
A **class defines structure**, and when we use `new`, Java creates an **object in memory** based on that class structure.


**When to use**

* Use **class** when defining a model (e.g., Student, Car)
* Use **object** when you need to **use or store actual data**

**Difference Table**

| **Class**                            | **Object**                  |
| ------------------------------------ | --------------------------- |
| Blueprint or template                | Real instance of a class    |
| Does not occupy memory directly      | Occupies memory in heap     |
| Declared using `class` keyword       | Created using `new` keyword |
| Defines structure (fields + methods) | Represents actual data      |
| Logical entity                       | Physical/runtime entity     |
| Used for designing                   | Used for execution          |

**Code Example**

```java id="1i6tx1"
class Student {   // Class
    String name;
    int age;

    void display() {
        System.out.println(name + " " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(); // Object
        s1.name = "Rahul";
        s1.age = 22;
        s1.display();
    }
}
```

## 4. What is method overloading?

**Method overloading** means having **multiple methods in the same class with the same name but different parameters**.

**Key Features**

* Same **method name**
* Different **parameter list (type, number, or order)**
* Happens in the **same class**
* Supports **compile-time polymorphism**
* Return type alone is **not enough to overload a method**

**How it works**

Java decides which method to call based on the **method signature (parameters)** during **compile time**.

**When to use**

* When the same operation needs to work with **different types or number of inputs**
* Example: adding numbers, printing data in different formats

**Code Example**

```java id="1i6tx1"
class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator c = new Calculator();

        System.out.println(c.add(2, 3));
        System.out.println(c.add(2, 3, 4));
        System.out.println(c.add(2.5, 3.5));
    }
}
```


**Common Interview Follow-up Questions**

**1. What is the difference between Method Overloading and Method Overriding?**

| **Method Overloading**                 | **Method Overriding**                       |
| -------------------------------------- | ------------------------------------------- |
| Occurs in the **same class**           | Occurs between **parent and child classes** |
| **Different** parameter list           | **Same** parameter list                     |
| Achieves **Compile-Time Polymorphism** | Achieves **Runtime Polymorphism**           |
| Inheritance is **not required**        | **Inheritance is required**                 |

**2. Can we overload methods by changing only the return type?**

**No.** Java does **not** allow overloading based only on the **return type** because it causes ambiguity.

**Invalid Example**

```java
int add(int a, int b) {
    return a + b;
}

double add(int a, int b) {   // Compile-time Error
    return a + b;
}
```

**3. Can constructors be overloaded?**

**Yes.** Constructors can be overloaded by providing **different parameter lists**.

```java
class Student {

    Student() {}

    Student(String name) {}

    Student(String name, int age) {}
}
```

**4. Can we overload a `static` method?**

**Yes.** **`static`** methods can also be overloaded as long as they have **different parameter lists**.



## 5. What is method overriding?

**Method overriding** means redefining a **parent class method in a child class with the same method signature** to provide a **specific implementation**.

**Key Features**

* Requires **inheritance (IS-A relationship)**
* Same **method name, parameters, and return type**
* Happens in **different classes (parent-child)**
* Supports **runtime polymorphism**
* Uses **@Override annotation (recommended)**

**How it works**
When a method is called using a **parent reference pointing to a child object**, Java executes the **child class overridden method at runtime**.


**When to use**

* When child class needs **different behavior from parent class method**
* When same method should behave differently in **different subclasses**

**Code Example**

```java id="1i6tx1"
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

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog(); // Parent reference, child object
        a.sound(); // Dog barks
    }
}
```

**Common Interview Follow-up Questions**

**1. What is the difference between Method Overloading and Method Overriding?**

| **Method Overloading**                 | **Method Overriding**                       |
| -------------------------------------- | ------------------------------------------- |
| Occurs in the **same class**           | Occurs between **parent and child classes** |
| **Different** parameter list           | **Same** parameter list                     |
| Achieves **Compile-Time Polymorphism** | Achieves **Runtime Polymorphism**           |
| Inheritance is **not required**        | **Inheritance is required**                 |

**2. Why do we use the `@Override` annotation?**

The **`@Override`** annotation tells the compiler that the method is intended to override a parent method. If the method signature is incorrect, the compiler reports an error.

**3. Can we override a `static` method?**

**No.** **`static`** methods belong to the class, not the object. They are **method hidden**, not overridden.

**4. Can we override a `final` method?**

**No.** A **`final`** method cannot be overridden because its implementation is fixed.

**5. Can constructors be overridden?**

**No.** Constructors are **not inherited**, so they cannot be overridden.


## 6. What is the difference between this and super keywords?

**this** refers to the current object instance,  
**super** refers to the immediate parent class object.

```java
class Parent {
    String name = "Parent";
}

class Child extends Parent {
    String name = "Child";
    
    void display() {
        System.out.println(this.name);  // Child
        System.out.println(super.name); // Parent
    }
}
```


## 7. What is class loader and how do they work?

A **Class Loader** is a part of the **JVM (Java Virtual Machine)** that is responsible for **loading .class files into memory at runtime**.

**Key Features**

* Part of the **JVM architecture**
* Loads classes **on demand (lazy loading)**
* Follows a **delegation hierarchy model**
* Types: **Bootstrap, Extension (Platform), Application ClassLoader**
* Enables **runtime class loading**

**How it works**

When a class is needed, the **ClassLoader loads it into JVM memory** in three steps:

1. **Loading** – Finds and loads `.class` file
2. **Linking** – Verifies and prepares class
3. **Initialization** – Assigns default/static values and executes static blocks

It follows a **parent delegation model**, meaning request is first sent to **parent ClassLoader**, then to child if not found.


**When to use**

* When classes are loaded **at runtime instead of compile time**
* In frameworks like **Spring, Hibernate, JDBC drivers**

**Types of Class Loaders**

* **Bootstrap ClassLoader** → Loads core Java classes (`java.lang.*`)
* **Platform ClassLoader** → Loads platform libraries
* **Application ClassLoader** → Loads application classes from classpath

**Code Example (Conceptual)**

```java id="1i6tx1"
public class Main {
    public static void main(String[] args) {
        ClassLoader loader = Main.class.getClassLoader();

        System.out.println(loader); // Application ClassLoader
        System.out.println(loader.getParent()); // Platform ClassLoader
        System.out.println(loader.getParent().getParent()); // null (Bootstrap)
    }
}
```


## 8. Difference between runtime vs compile-time class loading? 

**Compile-time class loading** happens when classes are loaded during **program compilation and startup preparation**

**Runtime class loading** happens when classes are loaded **dynamically during program execution**.


**Compile-time Class Loading**

**Key Features**

* Classes are loaded at **startup or early JVM phase**
* Done using **static linking by JVM**
* All required classes must be **available before execution**
* No dynamic behavior

**How it works**
JVM loads required classes during **program start**, before execution begins, using **ClassLoader chain automatically**.

**Why / When to use**

* When all dependencies are **known in advance**
* For **simple applications** with static structure
* Faster and predictable loading


**Runtime Class Loading**

**Key Features**

* Classes are loaded **during execution**
* Uses methods like **Class.forName() or custom ClassLoader**
* Supports **dynamic behavior and plugins**
* More flexible but slightly slower

**How it works**
JVM loads class **only when it is first referenced or explicitly requested** during execution.

**Why / When to use**

* When classes are **not known at compile time**
* In frameworks like **Spring, JDBC drivers, plugin systems**
* For **dynamic module loading**


**Difference Table**

| **Compile-time Loading**        | **Runtime Loading**                      |
| ------------------------------- | ---------------------------------------- |
| Happens before execution starts | Happens during execution                 |
| Static and fixed                | Dynamic and flexible                     |
| All classes must be known       | Classes can be unknown initially         |
| Faster startup                  | Slightly slower due to on-demand loading |
| No late binding                 | Supports late binding                    |


**Code Example**

```java id="1i6tx1"
class Demo {
    static {
        System.out.println("Class Loaded");
    }
}

public class Main {
    public static void main(String[] args) throws Exception {

        // Runtime class loading
        Class.forName("Demo");

        System.out.println("Main executed");
    }
}
```


## 9. How do you load a class dynamically in Java? 

In Java, we can load a **class dynamically** at runtime using `Class.forName()`. This is useful when the class name is not known at compile time. Once the class is loaded, we can create objects and invoke methods using Reflection.

A common real-world example is loading a JDBC driver.

```java
Class<?clazz = Class.forName("com.company.Employee");
```

**Creating an Object Dynamically**

```java
Class<?clazz = Class.forName("com.company.Employee");

Object obj = clazz.getDeclaredConstructor()
                  .newInstance();
```

**JDBC Example**

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```
This loads the MySQL driver at runtime.

## 10. What happens internally when you create an object using new? 


When we create an object using the `new` keyword, JVM first checks whether the class is loaded. If not, it loads the class into memory. Then it allocates memory in the Heap, initializes instance variables with default values, executes the constructor, and finally returns the object reference.

```java id="4v0xfi"
Employee emp = new Employee();
```

**Internal Steps**

```text id="f67d4p"
1. Load Employee class
2. Allocate memory in Heap
3. Initialize default values
4. Call constructor
5. Return object reference to emp
```

```java id="w5c7em"
class Employee {

    Employee() {
        System.out.println("Constructor Called");
    }
}
```



## 9. How to create a class and object?

We create a class using `class` keyword and object using `new` keyword.

```java
class Student {
    String name;
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student(); // object created
        s.name = "John";
        System.out.println(s.name);
    }
}
```


## 10. What are instance variables and methods?

- **Instance variables** — fields declared inside a class but outside methods. Each object gets its own copy.
- **Instance methods** — methods that operate on instance variables. Require an object to be called.

```java
class Employee {
    String name;    // instance variable
    int salary;     // instance variable

    void work() {   // instance method
        System.out.println(name + " is working");
    }
}

Employee e = new Employee();
e.name = "Bob";
e.work();   // Bob is working
```

## 11. What is a constructor? Types?


A **constructor** in Java is a special method used to **initialize objects** when a class is instantiated.

**constructor two Types:**

* Default
* Parameterized

**Key Features**

* Has the **same name as the class**
* Has **no return type (not even void)**
* Called **automatically** when an object is created
* Can be **parameterized or non-parameterized**
* Can be **overloaded**

**How it works**
When we create an object using `new`, Java automatically calls the **constructor** to set initial values for the object’s variables.


**When to use**

* When an object needs **initial values at creation time**
* When we want to **prepare object state automatically**

**Code Example**

```java
class Student {
    String name;
    int age;

    // Constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println(name + " " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Rahul", 22);
        s1.display();
    }
}
```

## 12. What is default constructor?

A **default constructor** is a constructor that is provided by the **Java compiler automatically** if no constructor is defined in the class. It initializes the object with **default values**.

**Key Features**

* Created **automatically by compiler** if no constructor exists
* Has **no parameters**
* Initializes variables with **default values (0, null, false)**
* Can be **explicitly defined by developer**
* Always has the **same name as the class**

**How it works**

When an object is created and no constructor is written, Java inserts a **default constructor internally** and assigns **default values** to instance variables.


**Code Example**

```java id="1i6tx1"
class Student {
    String name;
    int age;

    // No constructor defined → compiler adds default constructor
    void display() {
        System.out.println(name + " " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(); // default constructor called
        s1.display(); // null 0
    }
}
```


## 5. What is the static keyword?

The **static keyword** in Java is used to define **class-level members** that belong to the **class itself rather than any object**.

**Key Features**

* Belongs to the **class, not object**
* Shared across all **instances of the class**
* Can be applied to **variables, methods, blocks, and nested classes**
* Loaded into memory at **class loading time**
* Accessed using **class name**

**How it works**
When a class is loaded, all **static members are stored in method area memory**, and all objects share the same **static data**.


**When to use**

* When data should be **common for all objects (e.g., company name, counter)**
* When creating **utility/helper methods**

**Code Example**

```java id="1i6tx1"
class Student {
    static String college = "ABC College"; // static variable
    String name;

    Student(String name) {
        this.name = name;
    }

    static void displayCollege() { // static method
        System.out.println(college);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Rahul");
        Student s2 = new Student("Amit");

        Student.displayCollege(); // called using class name

        System.out.println(s1.name);
        System.out.println(s2.name);
    }
}
```



## 14. Difference: static vs non-static?

Static belongs to class, non-static belongs to object.

| Static | Non-Static |
|--------|-----------|
| Belongs to the class | Belongs to the object |
| Shared across all instances | Separate copy per object |
| Accessed via class name | Accessed via object reference |
| Cannot access `this` | Can access `this` |
| Loaded at class loading time | Created when object is created |

```java
class Demo {
    static int x = 10;   // static
    int y = 20;          // non-static

    static void show() { System.out.println(x); }   // static method
    void display()     { System.out.println(y); }   // non-static method
}

Demo.show();              // no object needed
new Demo().display();     // object needed
```


## 15. Can a class have multiple constructors?

Yes. This is called **constructor overloading** — multiple constructors with different parameter lists.

```java
class Student {
    Student() {}
    Student(String name) {}
    Student(int age) {}
}
```


## 16. Can a constructor be final, static, or abstract in Java?

No, a constructor cannot be **final, static, or abstract**.

**Why:**

* **final** → Constructor is not inherited, so it cannot be overridden.
* **static** → Static belongs to class, but constructor is used to create object.
* **abstract** → Abstract methods have no body, but constructor must have a body.



## 17. Can we make a class `final`? Why?

Yes. A `final` class **cannot be extended (subclassed)**. Used to:
- Prevent inheritance for security (e.g., `String`, `Integer`)
- Ensure immutability
- Prevent unintended behavior changes

```java
final class Constants {
    static final double PI = 3.14159;
}

// class MyConstants extends Constants { }  // ❌ Compile error
```


## 18. Can we make a class `abstract`?

Yes, abstract class cannot be instantiated and can have abstract and non-abstract methods.

```java
abstract class Animal {
    abstract void sound();            // must be implemented by subclass
    void display() {                 // concrete method
        System.out.println("Area: " + area());
    }
}
```



## 20. Can a class be both abstract and final? 

**No.** `abstract` requires the class to be subclassed (to implement abstract methods), while `final` prevents subclassing. They directly contradict each other — Java gives a compile error.


## 21. What is inner class? Types?

Inner class is a class inside another class, used for better grouping.

**Types:**

* Member inner class
* Static nested class
* Local inner class
* Anonymous class

```java
class Outer {
    class Inner {
        void show() {
            System.out.println("Inner class");
        }
    }
}
```



## 23. Is it allowed to overload main() method in Java?

**Yes, the `main()` method can be overloaded** in Java.

The JVM only calls **`public static void main(String[] args)`**, while other overloaded versions behave as **regular methods**.

```java
public class Test {
    public static void main(String[] args) { } // JVM entry point
    public static void main(int x) { }         // Overloaded
    public static void main() { }              // Overloaded
}
```


## 24. Is it allowed to override main() method in Java?

**No, we cannot override the `main()` method in Java** because it is **static**, and static methods cannot be overridden (they are hidden).

* The `main()` method is:

  ```java
  public static void main(String[] args)
  ```
* Since it is **static**, it belongs to the class, not the object.
* Static methods are resolved at **compile time**, so they cannot participate in runtime polymorphism.



## 25. Can you override static methods?

No, you cannot override static methods in Java. **Static methods belong to the class, not instances,** so they're resolved at compile time based on the reference type.

```java
class Parent {
    static void display() { System.out.println("Parent"); }
}

class Child extends Parent {
    static void display() { System.out.println("Child"); } // Hiding, not overriding
}

Parent p = new Child();
p.display(); // Prints "Parent" - based on reference type
```


## 26. Is it possible to execute a program without defining a main() method?

It is **technically possible** to run code in a **static block** without a `main()` method, but **modern Java requires `main()`** as the entry point.

Using static blocks alone is **not recommended** and violates standard practices.

```java
class NoMain {
    static {
        System.out.println("Executing without main");
        System.exit(0); // Required to prevent error
    }
}
// Works in older Java versions, not recommended
```


## 27. Can we create object without `new` keyword?

Yes, using methods like clone(), factory methods, reflection, or deserialization.

```java
// Example using clone
class A implements Cloneable {
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```


## 28. What happens if a class has no constructor?

Java automatically provides a **default no-argument constructor** that calls `super()` implicitly. All fields are initialized to their default values (`0`, `null`, `false`).

```java
class Box { int width; }       // Java adds: Box() { super(); }

Box b = new Box();             // works fine
System.out.println(b.width);  // 0
```


## 29. Can a class be private or protected?

Top-level class cannot be private or protected, only public or default. Inner classes can be private/protected.

```java
class Outer {
    private class Inner { }      // ✅ allowed
    protected class Helper { }   // ✅ allowed
}

// private class TopLevel { }   // ❌ compile error
```


## 30. What happens if a static block throws an unchecked exception?

If a static block throws an unchecked exception:

* Class will **fails to load**
* JVM will throws **ExceptionInInitializerError**
* Program stops and class cannot be used


```java
public class Main {
    public static void main(String[] args) {
        Test t = new Test();
    }
}

class Test {
    static {
        System.out.println("Static block running");
        int x = 10 / 0; // ArithmeticException
    }
}

// Output:
Exception in thread "main" java.lang.ExceptionInInitializerError
```


# ✅ 04. Java Inheritance 

## 1. What is Inheritance in Java?

**Inheritance** is an **OOP (Object-Oriented Programming)** concept that allows one class to acquire the properties and methods of another class.

The class being inherited from is called the **Parent Class (Superclass)**, and the class that inherits is called the **Child Class (Subclass)**.

**Key Features**

* Promotes **Code Reusability**
* Establishes an **IS-A Relationship**
* Supports **Method Overriding**
* Reduces code duplication
* Improves maintainability

**How It Works**

A child class uses the **extends** keyword to inherit fields and methods from the parent class.

**Code Example**

```java
class Animal {

    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {

    void bark() {
        System.out.println("Barking");
    }
}
```

Usage:

```java
Dog dog = new Dog();

dog.eat();   // Inherited method
dog.bark();  // Own method
```


**When to Use**

Use **Inheritance** when there is an **IS-A Relationship** between classes.

Examples:

* **Dog IS-A Animal**
* **Car IS-A Vehicle**
* **Manager IS-An Employee**

**Common Interview Follow-up Questions**

**2. What is the difference between `extends` and `implements`?**

* **`extends`** is used to inherit from a **Class**.
* **`implements`** is used to implement an **Interface**.

**3. Can a Child Class override Parent Class methods?**
**Yes.** This is called **Method Overriding**, which enables **Runtime Polymorphism**.

**4. Can Constructors be inherited?**
**No.** Constructors are **not inherited**, but they can be called using **`super()`**.

**5. Which class is the root of all classes in Java?**
The **`Object`** class is the **root class**, and every Java class directly or indirectly inherits from it.


## 2. Why doesn't Java support multiple inheritance (with classes)?

Java does not support **multiple inheritance (using classes)** to avoid **ambiguity and complexity** in the program.

**Key Features / Reason**

* Prevents **Diamond Problem**
* Avoids **method ambiguity (confusion which parent method to call)**
* Keeps Java **simple and clean OOP language**
* Supports multiple inheritance only through **interfaces**

**How it works (Problem scenario)**
If Java allowed multiple inheritance, a child class could inherit the **same method from two parent classes**, creating confusion about **which method to execute**.

This is called the **Diamond Problem**.

**Why not supported**

* To avoid **ambiguity in method resolution**
* To prevent **complexity in inheritance hierarchy**
* To ensure **better maintainability and readability**

**When to use alternative (Interfaces)**

* When we need multiple behavior inheritance
* When we want **loose coupling and abstraction**
* Use **interfaces instead of classes**

**Code Example (Problem Illustration in Theory)**

```java id="1i6tx1"
// Not allowed in Java (conceptual example)

class A {
    void show() {
        System.out.println("Class A");
    }
}

class B {
    void show() {
        System.out.println("Class B");
    }
}

// class C extends A, B  ❌ Not allowed in Java
```

**Correct Approach using Interface**

```java id="1i6tx1"
interface A {
    void show();
}

interface B {
    void show();
}

class C implements A, B {
    public void show() {
        System.out.println("Resolved single implementation");
    }
}

public class Main {
    public static void main(String[] args) {
        C obj = new C();
        obj.show();
    }
}
```


## 3. What is the diamond problem?


The **Diamond Problem** is an ambiguity issue in **multiple inheritance** where a class inherits the same method from **two parent classes**, causing confusion about **which method to execute**.

**Key Features**

* Occurs in **multiple inheritance scenarios**
* Creates **method ambiguity**
* Leads to **conflict in method resolution**
* Problem of **duplicate inheritance path (diamond shape structure)**
* Avoided in Java by **not supporting multiple inheritance with classes**

**How it works (Problem scenario)**
If a class **C inherits from two classes A and B**, and both A and B have the same method, then Java cannot decide **which parent method C should use**.

This forms a **diamond-like inheritance structure**, hence the name.

**Why it is a problem**

* Causes **confusion in method execution**
* Leads to **inconsistent behavior**
* Makes code **complex and error-prone**

**When it occurs**

* In languages that support **multiple inheritance (like C++)**
* When a child class inherits from **two parent classes having same method**

**How Java solves it**

* Java avoids this issue by **not allowing multiple inheritance with classes**
* Instead, it allows multiple inheritance using **interfaces**

**Code Example (Problem Illustration Concept)**

```
    A
   / \
  B   C
   \ /
    D
```

```java id="1i6tx1"
// Conceptual example (not allowed in Java)

class A {
    void show() {
        System.out.println("Class A");
    }
}

class B {
    void show() {
        System.out.println("Class B");
    }
}

// class C extends A, B  ❌ Not allowed due to Diamond Problem
```

**Solution using Interface**

```java id="1i6tx1"
interface A {
    void show();
}

interface B {
    void show();
}

class C implements A, B {
    public void show() {
        System.out.println("Resolved implementation in C");
    }
}

public class Main {
    public static void main(String[] args) {
        C obj = new C();
        obj.show();
    }
}
```

If classes B and C both override a method from A, and D inherits from both B and C, which version should D use? This creates confusion and compilation errors.

## 3. How does Java solve the diamond problem?

Java solves the **diamond problem** by not allowing multiple class inheritance but supporting multiple interface inheritance with **default methods**.


```java
interface A { default void method() { } }
interface B { default void method() { } }

class C implements A, B {
    @Override
    public void method() {
        A.super.method(); // Explicitly choose which to call
    }
}
```


## 4. Why do we use Inheritance?

**Inheritance** is an OOP concept where a **child class acquires properties and behaviors of a parent class** using the **extends keyword**.

**Key Features**

* Promotes **code reusability**
* Supports **method overriding (runtime polymorphism)**
* Establishes an **IS-A relationship**
* Reduces **code duplication**
* Improves **maintainability and scalability**
* To avoid **repeated code writing**
* To support **OOP principles like polymorphism**

**How it works**
A **child class inherits fields and methods of a parent class**, and can also **extend or override behavior**.


**When to use**

* When there is a clear **IS-A relationship (Dog IS-A Animal)**
* When multiple classes share **common behavior or properties**

**Code Example**

```java id="1i6tx1"
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();  // inherited method
        d.bark(); // child method
    }
}
```


## 5. What is `extends` keyword?

The **extends keyword** in Java is used to create **inheritance**, where a **child class inherits properties and behaviors of a parent class**.

**Key Features**

* Used for **class-to-class inheritance**
* Supports **IS-A relationship**
* Allows **code reuse from parent class**
* Enables **method overriding**
* A class can extend **only one class (single inheritance)**

**How it works**
When a class uses **extends**, it automatically gets access to the **non-private members (fields and methods)** of the parent class.


**When to use**

* When a class is a **specialized version of another class**
* When there is a clear **IS-A relationship**

**Code Example**

```java id="1i6tx1"
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal { // extends keyword used
    void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();  // inherited method
        d.bark(); // child method
    }
}
```


## 14. What is polymorphism?

**Polymorphism** is one of the **four pillars of Object-Oriented Programming (OOP)**. It means **"one object, many forms."**

It allows the **same method** to perform **different behaviors** depending on the object that calls it.

- Poly - many
- Morphism - Behaviour

**Key Features**

* Supports **method overloading and method overriding**
* Enables **code flexibility and reusability**
* Works through **compile-time and runtime polymorphism**
* Improves **maintainability and scalability**
* Core concept of **OOP (Object-Oriented Programming)**


**How it works**

* In **compile-time polymorphism**, method selection happens using **method overloading**
* In **runtime polymorphism**, method selection happens using **method overriding via dynamic method dispatch**

**When to use**

* When same method name should work with **different inputs (overloading)**
* When child class needs **different implementation (overriding)**
* When designing **scalable systems**
* In **Spring Framework**, where interfaces have multiple implementations.
* In **Microservices**, for different payment, notification, or storage implementations.


**Code Example**

**Compile-time Polymorphism (Method Overloading)**
The method name is the same, but parameters are different.
```java id="1i6tx1"
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

**Runtime Polymorphism (Method Overriding)**
A child class provides its own implementation of a method defined in the parent class.
```java id="1i6tx1"
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

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog(); // Runtime polymorphism
        a.sound();
    }
}
```


**Common Interview Follow-up Questions**

**1. What is the difference between Method Overloading and Method Overriding?**

| **Method Overloading**                     | **Method Overriding**                     |
| ------------------------------------------ | ----------------------------------------- |
| **Compile-time Polymorphism**              | **Runtime Polymorphism**                  |
| Same method name with different parameters | Same method signature in parent and child |
| Happens in the same class                  | Happens between parent and child classes  |
| Decided by the compiler                    | Decided by the JVM at runtime             |

**2. Can we achieve Polymorphism using Interfaces?**

**Yes.** Interfaces are one of the most common ways to achieve **Runtime Polymorphism** because multiple classes can implement the same interface differently.

**3. Why is Runtime Polymorphism important?**

It allows the program to choose the correct method **during execution**, making applications **flexible**, **scalable**, and easier to extend without changing existing code.


## 14. What is Dynamic Method Dispatch?

**Dynamic Method Dispatch** is the mechanism by which **Java decides at runtime** which **overridden method** to execute.

It is the **implementation of Runtime Polymorphism** in Java.

The method that gets executed depends on the **actual object**, **not** the reference type.


**Key Features**

* Achieves **Runtime Polymorphism**.
* Method call is resolved **at runtime**.
* Uses a **parent class reference** to refer to a **child class object**.
* Works only with **overridden methods**, not with overloaded methods.

**How It Works**

1. Create a **parent class reference**.
2. Assign it to a **child class object**.
3. When an overridden method is called, the **JVM checks the actual object type** and invokes the corresponding child class method.

**When to Use**

* Use **Dynamic Method Dispatch** when multiple classes share a common parent and you want **different behaviors for the same method call**.
* Commonly used in **polymorphism**, **frameworks**, and **design patterns**.

**Code Example**

```java id="k8p4mx"
interface Payment {
    void pay();
}

class CreditCardPayment implements Payment {
    public void pay() {
        System.out.println("Paid using Credit Card");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("Paid using UPI");
    }
}

public class Demo {
    public static void main(String[] args) {

        Payment payment = new UpiPayment();
        payment.pay();     // Paid using UPI
    }
}
```

Here, the **JVM** decides at **runtime** to call `UpiPayment.pay()` because the actual object is `UpiPayment`.



**Advantages**

* **Supports Runtime Polymorphism**
* **Loose coupling**
* **Easy to extend**
* **Improves code reusability**
* **Enhances maintainability**

**Limitations**

* Works only with **overridden instance methods**
* **Static**, **private**, and **final** methods are **not** dynamically dispatched because they cannot be overridden

**Common Interview Follow-up Questions**

**1. What is the difference between Dynamic Method Dispatch and Method Overriding?**

| **Method Overriding**                                          | **Dynamic Method Dispatch**                                                      |
| -------------------------------------------------------------- | -------------------------------------------------------------------------------- |
| A child class provides a new implementation of a parent method | The **JVM mechanism** that chooses which overridden method to execute at runtime |
| It is a programming concept                                    | It is the runtime process that supports overriding                               |

**2. Can Dynamic Method Dispatch work with Static Methods?**

**No.** **Static methods** belong to the class, not the object, so they are resolved at **compile time**.

**3. Can Dynamic Method Dispatch work with Private or Final Methods?**

**No.** **Private** and **final** methods cannot be overridden, so they cannot participate in **Dynamic Method Dispatch**.

## 6. What is IS-A Relationship?


**IS-A** is an inheritance relationship — it means **one object is a type of another**. Implemented using `extends` or `implements`. Used to check with `instanceof`.

**Key Features**

* Represents **inheritance (parent-child relationship)**
* Uses **extends (class inheritance)** or **implements (interface relationship)**
* Promotes **code reusability**
* Supports **polymorphism**
* Defines a **hierarchical relationship**

**How it works**
If class **Dog extends Animal**, then **Dog IS-A Animal**, meaning Dog inherits all **non-private properties and behaviors** of Animal.


**When to use**

* When one class is a **type of another class (Car IS-A Vehicle)**
* When common behavior should be **shared through inheritance**

**Code Example**

```java id="1i6tx1"
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal { // Dog IS-A Animal
    void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();   // inherited method
        d.bark();  // own method
    }
}
```

## 6. What is HAS-A (Composition) Relationship?

A **HAS-A Relationship** is an **Object-Oriented Programming (OOP)** concept where one class **contains** an object of another class. It represents a **part-of** or **ownership** relationship and is implemented using **Composition** or **Aggregation**.

**Example:** A **Car HAS-A Engine**.

**Key Features**

* Represents **ownership** or **containment**.
* Achieved by creating an object of one class inside another class.
* Promotes **Code Reusability**.
* Provides **Loose Coupling** compared to inheritance.
* Implemented using **Composition** or **Aggregation**.

**How It Works**

A class stores another class as a **member variable** and uses its functionality.

**When to Use**

* When one object is a **part of** another object.
* When classes have a **uses-a** or **contains-a** relationship.
* When you want **flexibility** and **better maintainability**.
* When **Inheritance (IS-A)** is not the right fit.

**Code Example**

```java
class Engine {
    void start() {
        System.out.println("Engine Started");
    }
}

class Car {
    private Engine engine = new Engine(); // HAS-A Relationship

    void drive() {
        engine.start();
        System.out.println("Car is moving");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.drive();
    }
}
```


**Common Interview Follow-up Questions**

**1. What is the difference between IS-A and HAS-A Relationship?**

| **IS-A**                   | **HAS-A**                              |
| -------------------------- | -------------------------------------- |
| Represents **Inheritance** | Represents **Composition/Aggregation** |
| Uses **extends** keyword   | Uses **object reference**              |
| Example: Dog IS-A Animal   | Example: Car HAS-A Engine              |

**2. What is Composition?**

**Composition** is a strong HAS-A relationship where the contained object cannot exist independently of the owner object.

**Example:** A House has Rooms.

**3. What is Aggregation?**

**Aggregation** is a weak HAS-A relationship where the contained object can exist independently.

**Example:** A Department has Employees.

**4. Why is Composition often preferred over Inheritance?**

Because it provides **Loose Coupling**, **Better Flexibility**, and avoids deep inheritance hierarchies.


## 7. Composition and Aggregation?

**Composition** and **Aggregation** are two types of **HAS-A relationships** in Java used to build objects using other objects.

* **Composition** → Strong relationship where the **child object cannot exist without the parent**
* **Aggregation** → Weak relationship where the **child object can exist independently**


**Key Features**

**Composition**

* Strong **ownership relationship**
* Child object's lifecycle depends on **parent object**
* If parent is destroyed, child is also **destroyed**
* Used for **tight coupling scenarios**

**Aggregation**

* Weak **association relationship**
* Child object can **exist independently**
* No strict lifecycle dependency
* Used for **loose coupling**


**How it works**

* In **Composition**, parent class creates and controls the **dependent objects**
* In **Aggregation**, parent class uses **external objects passed from outside**


**When to use**

**Composition**

* When child object is **fully dependent (Car HAS-A Engine created inside Car)**
* When lifecycle is **controlled by parent**

**Aggregation**

* When object is **shared or independent (Student HAS-A Address, but Address exists separately)**


**Code Example**

**Composition Example**

```java id="1i6tx1"
class Engine {
    void start() {
        System.out.println("Engine started");
    }
}

class Car {
    private Engine engine = new Engine(); // Composition

    void drive() {
        engine.start();
        System.out.println("Car is moving");
    }
}
```

**Aggregation Example**

```java id="1i6tx1"
class Address {
    String city;

    Address(String city) {
        this.city = city;
    }
}

class Student {
    private Address address; // Aggregation

    Student(Address address) {
        this.address = address;
    }

    void show() {
        System.out.println(address.city);
    }
}
```


## 8. Types of Inheritance(Single, Multilevel, Hierarchical) in Java

| Type | Description |
|------|-------------|
| Single | One parent → one child |
| Multilevel | A → B → C (chain) |
| Hierarchical | One parent → multiple children |
| Multiple | Not supported with classes; supported via interfaces |
| Hybrid | Combination — only via interfaces |

**Multilevel Inheritance :** When a class inherits from a class, which already inherits another class.

**Hierarchical Inheritance :** Multiple child classes inherit from one parent class.

```java
// Single
class A { }  
class B extends A { }

// Multilevel
class C extends B { }

// Hierarchical
class D extends A { }  
class E extends A { }
```


## 9. Does Java support Multiple Inheritance? Why?

❌ No, Java does not support multiple inheritance with classes.

👉 *Reason:* To avoid **ambiguity problem (Diamond problem)**.

✅ But it supports it using **interfaces**.



## 12. Can we override static methods?

❌ No, Static methods are **not overridden**, they are **hidden**.

```java
class Parent {
    static void show() { 
        System.out.println("Parent"); 
    }
}

class Child extends Parent {
    static void show() { 
        System.out.println("Child"); 
    }  // hiding
}

Parent p = new Child();
p.show();   // Parent — reference type decides, not object type
```


## 13. Can we override final methods?

❌ No, Final methods cannot be changed in child class.

```java
class Animal {
    final void run() {}
}

class Dog extends Animal {
    // void run() { }  ❌ Error: cannot override final method
}
```

## 15. How does method overriding work internally?

**Method Overriding** works using **Dynamic Method Dispatch (Runtime Polymorphism)**. When a **parent class reference** points to a **child class object**, Java decides which overridden method to execute **at runtime** based on the **actual object type**, not the reference type.

**Key Features**

* **Runtime Polymorphism**.
* Uses **Inheritance** and **Method Overriding**.
* Decision is made based on the **actual object**, not the reference.
* Improves **flexibility** and **extensibility**.

**How it works internally**

1. Java loads the **parent** and **child** classes into memory.
2. The child class **overrides** a method from the parent class.
3. A **parent reference** is assigned to a **child object**.
4. At runtime, the **JVM** checks the actual object type.
5. The JVM calls the **overridden method** from the child class using **Dynamic Method Dispatch**.


**When to use it?**

* When a child class needs to **customize or extend** the behavior of a parent class.
* When implementing **frameworks**, **interfaces**, and **design patterns**.
* When different objects should respond differently to the same method call.

**Example Code**

```java
// Parent class
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

// Child class
class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal obj = new Dog(); // Parent reference, child object
        obj.sound();            // JVM calls Dog's sound() at runtime
    }
}
```

**Output**

```text
Dog barks
```

**Internal Flow**

```text
Animal obj = new Dog();
        │
        ▼
Reference Type = Animal
Actual Object Type = Dog
        │
        ▼
obj.sound()
        │
        ▼
JVM checks actual object type at runtime
        │
        ▼
Calls Dog's overridden sound() method
```

## 16. What are rules for method overriding?


**Method Overriding** happens when a **child class provides a specific implementation** of a method that is already defined in its **parent class**, keeping the **same method signature**.


| Rule | Detail |
|------|--------|
| Same method name | Must match exactly |
| Same parameters | Same type, number, order |
| Return type | Same or covariant (subtype) |
| Access modifier | Same or more accessible (not more restrictive) |
| Exceptions | Can throw fewer/narrower checked exceptions |
| Cannot override | `static`, `final`, `private` methods |


**Key Rules:**

* **Method name must be same** in both parent and child class
* **Parameters must be same** (same type and order)
* Must have an **IS-A relationship (inheritance)**
* **Return type must be same or covariant** (child type allowed)
* **Access modifier cannot be more restrictive** (can be same or more visible)
* **Private, static, and final methods cannot be overridden**
* **Checked exceptions must not be broader** than parent method
* Overriding is based on **runtime polymorphism**


**How it works:**

* Parent class defines a method
* Child class **redefines the same method**
* At runtime, Java calls the method of the **actual object type**


**When to use:**

* When a **general behavior exists in parent class**
* But each child class needs a **different implementation**
* In frameworks like **Spring, Hibernate, and design patterns**


```java
class Parent {
    protected Number getValue() throws Exception { return 1; }
}

class Child extends Parent {
    @Override
    public Integer getValue() { return 42; }  // ✅ covariant return, wider access, fewer exceptions
}
```


## 17. What is covariant return type?

Child class can return a more specific type than parent method.

```java
class A {}
class B extends A {}

class Parent {
    A get() { return new A(); }
}

class Child extends Parent {
    B get() { return new B(); } // allowed
}
```


## 18. Can constructors be inherited?

**No.** Constructors are not inherited because they are tied to the class they belong to. 
However, a child class can **call the parent constructor** using `super()`.

```java
class Animal {
    Animal(String name) { System.out.println("Animal: " + name); }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);   // explicitly calling parent constructor
        System.out.println("Dog: " + name);
    }
}
```


## 19. What is the order of constructor execution?

Parent constructor executes first, then child constructor.

```java
class A {
    A() { System.out.println("Parent"); }
}

class B extends A {
    B() { System.out.println("Child"); }
}

//Output:
Parent
Child
```


## 20. What happens if parent constructor is not called?

Java automatically calls default parent constructor using super().

```java
class A {
    A() { System.out.println("Parent"); }
}

class B extends A {
    B() {
        // super() is added automatically
        System.out.println("Child");
    }
}
```


## 21. Can we extend multiple classes in Java?

**No.** Java does not support extending multiple classes. A class can only extend **one** class. For multiple inheritance of behavior, use **interfaces**.

```java
// class C extends A, B { }  // ❌ Not allowed

interface A { void methodA(); }
interface B { void methodB(); }

class C implements A, B {    // ✅ multiple interfaces allowed
    public void methodA() { }
    public void methodB() { }
}
```


## 22. Can we inherit private members?

**Yes, they are inherited** but **not directly accessible** in the child class. They can be accessed indirectly through `public` or `protected` getter/setter methods.

```java
class Parent {
    private int secret = 42;
    int getSecret() { return secret; }   // public accessor
}

class Child extends Parent {
    void show() {
        // System.out.println(secret);      // ❌ not accessible
        System.out.println(getSecret());    // ✅ accessed via method
    }
}
```


## 23. Can we override private methods?

“No, private methods are not visible, so they cannot be overridden.

```java 
class A {
    private void show() {}
}

class B extends A {
    // void show() {} ❌ not overriding, new method
}
```


## 24. What is access modifier and Can we change it while overriding?


**Access Modifiers** control the **visibility** and **accessibility** of **classes, methods, variables, and constructors**.


| **Modifier**              | **Same Class** | **Same Package** | **Subclass (Different Package)** | **Different Package** |
| ------------------------- | -------------- | ---------------- | -------------------------------- | --------------------- |
| **private**               | ✅              | ❌                | ❌                                | ❌                     |
| **default (no modifier)** | ✅              | ✅                | ❌                                | ❌                     |
| **protected**             | ✅              | ✅                | ✅                                | ❌                     |
| **public**                | ✅              | ✅                | ✅                                | ✅                     |


**Yes, but only to increase visibility** (make it more accessible). You cannot reduce the access level.

| Parent Modifier | Allowed in Child |
|----------------|-----------------|
| `private` | Cannot override |
| `default` | `default`, `protected`, `public` |
| `protected` | `protected`, `public` |
| `public` | `public` only |

```java
class Parent {
    protected void show() { }
}

class Child extends Parent {
    @Override
    public void show() { }    // ✅ widened from protected to public

    // private void show() { }  // ❌ narrowed — compile error
}
```

## 25. What happens if parent and child have same variable name?

This is called **variable hiding**. The variable accessed depends on the **reference type**, not the object type. Unlike methods, variables do not follow runtime polymorphism.

```java
class Parent { int x = 10; }
class Child extends Parent  { int x = 20; }

Parent p = new Child();
System.out.println(p.x);   // 10 — reference type (Parent) decides

Child c = new Child();
System.out.println(c.x);   // 20 — reference type (Child) decides
```


## 26. Can abstract class have constructor?


**Answer:**
Yes, an **abstract class can have a constructor** in Java. Even though you **cannot instantiate an abstract class directly**, its constructor is called when a **child class object is created**.


**Key Features:**

* **Abstract class can have constructors**
* Constructor is called during **child object creation**
* Used to initialize **common fields**
* Supports **code reuse in inheritance**
* Executes before child class constructor


**How it works:**

* You create an object of a **concrete child class**
* Java automatically calls the **parent (abstract class) constructor first**
* Then the **child class constructor** executes
* Used to initialize shared state in parent class


**When to use:**

* When multiple classes share **common initialization logic**
* When you want to enforce **base setup before child execution**
* In frameworks using **inheritance-based design**


**Code Example:**

```java
abstract class Vehicle {

    String type;

    Vehicle(String type) {
        this.type = type;
        System.out.println("Vehicle constructor called");
    }

    abstract void run();
}

class Bike extends Vehicle {

    Bike(String type) {
        super(type);
        System.out.println("Bike constructor called");
    }

    void run() {
        System.out.println(type + " is running");
    }
}

public class Main {
    public static void main(String[] args) {

        Bike b = new Bike("Two Wheeler");
        b.run();
    }
}
```



## 27. Can interface extend class?

**No.** An interface cannot extend a class. An interface can only **extend another interface**.

```java
// interface A extends B {} (only if B is interface)
```


## 27. Can an interface extend another interface?

Yes, in **Java**, an **interface can extend another interface** using the `extends` keyword.
A child interface inherits all methods from the parent interface.

```java
interface A {
    void methodA();
}

interface B extends A {
    void methodB();
}
```


## 28. Can class extend interface?

**No.** A class cannot `extend` an interface. A class must use `implements` to use an interface.

```java
interface Printable { void print(); }

// class Doc extends Printable { }     // ❌ Not allowed

class Doc implements Printable {       // ✅ correct
    public void print() { System.out.println("Printing..."); }
}
```

## 29. Which type of polymorphism does method overloading represent?

**Method overloading** represents **compile-time polymorphism** (also called static polymorphism or early binding).

The compiler resolves which overloaded method to call **at compile time** based on the method signature (number and types of parameters).

```java
class Calculator {
    int add(int a, int b)          { return a + b; }
    double add(double a, double b) { return a + b; }
}

// Resolved at compile time — no runtime decision needed
Calculator c = new Calculator();
c.add(1, 2);       // calls int version
c.add(1.0, 2.0);   // calls double version
```


## 30. If a class implements two interfaces with the same default method, what happens and how do you resolve it?

Java gives a **compile-time error** — the class must **override** the conflicting default method to resolve the ambiguity.

```java
interface A {
    default void greet() { System.out.println("Hello from A"); }
}

interface B {
    default void greet() { System.out.println("Hello from B"); }
}

// ❌ Compile error without override
class C implements A, B {

    // ✅ Must override to resolve conflict
    @Override
    public void greet() {
        A.super.greet();    // explicitly call A's version, OR
        // B.super.greet(); // explicitly call B's version, OR
        // write your own implementation
        System.out.println("Hello from C");
    }
}
```

## 31. Can an interface have a constructor in Java, and why or why not?

**No.** Interfaces cannot have constructors.

**Why:**
- A constructor's purpose is to **initialize an object's instance state**
- Interfaces cannot be instantiated directly — you never write `new Flyable()`
- Interfaces have **no instance variables** to initialize
- Only the implementing class is instantiated, and its constructor handles initialization

```java
interface Animal {
    // Animal() { }  // ❌ Compile error: interfaces cannot have constructors
    void sound();
}

class Dog implements Animal {
    String name;
    Dog(String name) { this.name = name; }  // ✅ constructor in the implementing class
    public void sound() { System.out.println("Woof"); }
}
```


## 31. Can an interface have instance variables in Java, and if not, what type of variables can it have?

**No.** Interfaces cannot have instance variables.

All variables in an interface are implicitly **`public static final`** (constants). They belong to the interface itself, not to any instance.

```java
interface MathConstants {
    double PI = 3.14159;        // public static final double PI = 3.14159
    int MAX_VALUE = 100;        // public static final int MAX_VALUE = 100

    // int counter;             // ❌ not allowed — would be an instance variable
    // private int x = 5;       // ❌ private instance variable not allowed
}

class Circle implements MathConstants {
    double area(double r) {
        return PI * r * r;      // access constant directly
    }
}

// Access without an instance
System.out.println(MathConstants.PI);       // 3.14159
System.out.println(MathConstants.MAX_VALUE); // 100
```


# ✅ 05. Java Interface & Abstract Class 


## 1. What is an Interface in Java?

An **Interface** in Java is a **blueprint of a class** that defines a set of **abstract methods** which implementing classes must provide. It is mainly used to achieve **abstraction** and **multiple inheritance**.

**Key Features**

* Defines a **contract** that implementing classes must follow.
* Supports **abstraction** by hiding implementation details.
* Allows **multiple inheritance** (a class can implement multiple interfaces).
* Can contain **abstract methods**, **default methods**, **static methods**, and **private methods** (Java 9+).
* Variables in an interface are **`public static final`** by default.

**How It Works**

1. Create an **interface** with method declarations.
2. A class uses the **`implements`** keyword to implement the interface.
3. The implementing class provides the method implementations.

**When to Use**

* Use an **Interface** when multiple classes should follow the **same contract** but may have different implementations.
* Commonly used for **loose coupling**, **Dependency Injection**, and **strategy design patterns**.

**Code Example**

```java id="f8k3mq"
interface Animal {
    void sound();
}

class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Bark");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.sound();
    }
}
```


## 2. Interface types in Java?

In Java, interfaces are mainly of four types:

**Normal Interface :**
A normal interface defines a contract with multiple abstract methods that a class must implement.

```java
interface Payment {
    void pay();
}
```

**Functional Interface :**
A functional interface contains exactly one abstract method and is used for lambda expressions.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b); // single abstract method
    
    default void print() { } // default methods allowed
}

// 1. Usage with lambda
Calculator add = (a, b) -> a + b;
int result = add.calculate(5, 3);

// 2. Using Separate Class
class AddCalculator implements Calculator {
    public int calculate(int a, int b) {
        return a + b;
    }

    public void print() {
        System.out.println("Print here");
    }
}

Calculator add = new AddCalculator();
int result = add.calculate(5, 3);
System.out.println(result);

add.print();
```

**Marker Interface :**
A marker interface is an empty interface used to tag a class for special behavior.

```java
import java.io.Serializable;

// Marker interface
interface Serializable {
    // Empty - just marks the class
}

class Student implements Serializable {
    String name;
    // This class can now be serialized
}
```

**Nested Interface :**
A nested interface is declared inside a class or another interface for logical grouping.

```java
class Bank {
    interface RBI {
        void rule();
    }
}
```

## 2. What is a Functional Interface?

A **Functional Interface** is an interface that contains **exactly one abstract method (SAM - Single Abstract Method)**. It was introduced in **Java 8** and is mainly used with **Lambda Expressions** and **Method References**.

**Key Features**

* Contains **only one abstract method**.
* Can have **multiple `default` and `static` methods**.
* Can be annotated with **`@FunctionalInterface`** (optional but recommended).
* Enables **functional programming** in Java.
* Used extensively in the **Stream API** and lambda expressions.

**How It Works**

* A functional interface defines a single behavior.
* A **lambda expression** provides the implementation of that abstract method at runtime.
* The compiler automatically maps the lambda to the functional interface.

**When to Use**

* Use **Functional Interfaces** when you want to pass **behavior as a parameter**.
* Commonly used with **Lambda Expressions**, **Stream API**, and **callbacks**.
* Examples: **`Runnable`**, **`Callable`**, **`Comparator`**, **`Consumer`**, **`Supplier`**, **`Function`**, and **`Predicate`**.

**Code Example**

```java id="x4m7pq"
@FunctionalInterface
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        Greeting greeting = () -> System.out.println("Hello!");
        greeting.sayHello();
    }
}
```

**Common Interview Follow-up Questions**

**1. Can a Functional Interface have multiple methods?**
**Yes.** It can have **multiple Default** and **Static Methods**, but **only one Abstract Method**.

**2. Is `@FunctionalInterface` mandatory?**
**No.** It is **optional**, but recommended because it provides **compile-time validation**.

**3. Can a Functional Interface extend another Interface?**
**Yes.** As long as the resulting interface still has **only one Abstract Method**.

**4. Name some built-in Functional Interfaces in Java.**
**`Predicate`**, **`Function`**, **`Consumer`**, **`Supplier`**, **`Runnable`**, and **`Callable`**.

**5. What is the main use of a Functional Interface?**
It is used to support **Lambda Expressions**, **Method References**, and **functional programming** features introduced in **Java 8**.


## 4. What is an Abstract Class?

An **Abstract Class** is a class that cannot be instantiated directly. It is used as a base class for other classes and can contain both **abstract methods** (without implementation) and **concrete methods** (with implementation).

**Abstract Class cannot be instantiated directly**
```java
abstract class Vehicle {
    abstract void start();
}

// ❌ Compilation Error
Vehicle v = new Vehicle();
```

**Key Features**

* Cannot create an object of an **Abstract Class**
* Can contain **abstract** and **non-abstract methods**
* Can have **fields**, **constructors**, and **methods**
* Supports **Inheritance**
* Child classes must implement abstract methods

**How It Works**

An **Abstract Class** defines common behavior that can be shared by multiple subclasses. The subclasses inherit from it and provide implementations for the abstract methods.


**When to Use**

Use an **Abstract Class** when multiple classes share common fields or methods, but some behavior must be implemented differently by each subclass.

**Code Example**

```java
abstract class Animal {
    abstract void makeSound();

    void sleep() {
        System.out.println("Sleeping...");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Bark");
    }
}
```

Usage:

```java
Animal dog = new Dog();
dog.makeSound();
dog.sleep();
```

Abstract class can have abstract and non-abstract methods.

```java
abstract class Animal {
    abstract void sound();            // must be implemented by subclass
    void display() {                 // concrete method
        System.out.println("Area: " + area());
    }
}
```

**Advantages**

* Promotes **Code Reusability**.
* Supports **Abstraction** by hiding implementation details.
* Reduces **code duplication**.
* Provides a **common base** for related classes.
* Ensures subclasses implement required behavior.



**Common Interview Follow-up Questions**

**0. Abstract Class vs Interface**

| **Abstract Class**                             | **Interface**                                              |
| ---------------------------------------------- | ---------------------------------------------------------- |
| Uses **`abstract`** keyword                    | Uses **`interface`** keyword                               |
| Can have **Abstract** and **Concrete Methods** | Can have **Abstract**, **Default**, and **Static Methods** |
| Can have **Instance Variables**                | Can have only **Constants (`public static final`)**        |
| Supports **Constructors**                      | **No Constructors**                                        |
| A class can extend **only one** abstract class | A class can implement **multiple interfaces**              |


**1. Can we create an object of an Abstract Class?**

**No.** An abstract class **cannot be instantiated**.

**2. Can an Abstract Class have Constructors?**

**Yes.** Constructors are used to initialize the object when a subclass is instantiated.

**3. Can an Abstract Class have Concrete Methods?**

**Yes.** It can contain both **Abstract Methods** and **Concrete Methods**.

**4. Can an Abstract Class have only Concrete Methods?**

**Yes.** A class can still be declared **abstract** even if it has no abstract methods, preventing direct instantiation.

**5. When should you use an Abstract Class instead of an Interface?**

Use an **Abstract Class** when related classes share **common state** and **implementation**. Use an **Interface** when multiple unrelated classes need to implement the same behavior.


## 4. When should you use an interface instead of an abstract class?


Both **Interface** and **Abstract Class** are used to achieve **Abstraction**, but they serve different purposes.

| **Interface**                                                       | **Abstract Class**                          |
| ------------------------------------------------------------------- | ------------------------------------------- |
| Defines a **contract**                                              | Defines a **base class**                    |
| Supports **multiple inheritance**                                   | Supports only **single inheritance**        |
| Focuses on **what to do**                                           | Focuses on **what to do and how to do it**  |
| Cannot have instance fields                                         | Can have instance fields                    |
| All methods are abstract by default (except default/static methods) | Can have both abstract and concrete methods |
| Implemented using **implements**                                    | Extended using **extends**                  |

**How It Works**

* Use an **Interface** when unrelated classes need to follow the same contract.
* Use an **Abstract Class** when classes share common state and behavior.


**When to Use Interface**

When different classes need the same behavior but may implement it differently.

Example:

```java
interface Animal {
    void sound();   // abstract method
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Dog barks");
    }
}
```

**When to Use Abstract Class**

When classes have common fields and methods.

Example:

```java
abstract class Animal {
    abstract void sound();   // abstract method

    void eat() {             // concrete method
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}
```


## 5. What are static methods in interfaces?

**Static methods in interfaces** are methods declared with the **`static`** keyword inside an interface in **Java**.
They **belong to the interface itself and are called using the interface name**, not by implementing classes.

```java
interface MyInterface {

    static void show() {
        System.out.println("Static method in interface");
    }
}

public class Test {
    public static void main(String[] args) {
        MyInterface.show();  // called using interface name
    }
}
```



## 6. Can we create object of interface or abstract class?

No, we cannot create objects of interface or abstract class directly. But we can create objects using their implementing or child classes.

```java
Animal a = new Dog(); // valid
```

## 7. Can an interface have methods with implementation? (Java 8+)
Yes, from Java 8 onwards, interfaces can have methods with implementation using default and static keywords.

```java
interface Animal {
    default void eat() {
        System.out.println("Eating");
    }
}
```

## 8. What are default and static methods in interface?

Default methods are instance methods with implementation that can be overridden. Static methods belong to the interface and cannot be overridden.

```java
interface Animal {
    default void eat() {
        System.out.println("Eating");
    }

    static void info() {
        System.out.println("Animal Info");
    }
}

class Dog implements Animal {}

public class Test {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();           // default method
        Animal.info();     // static method
    }
}
```

## 9. Can abstract class have constructor? Why?

Yes, abstract class can have a constructor because it is used to initialize common properties when a subclass object is created.

```java
abstract class Animal {
    Animal() {
        System.out.println("Animal constructor");
    }
}

class Dog extends Animal {
    Dog() {
        System.out.println("Dog constructor");
    }
}
```

## 10. Can abstract class have both abstract and concrete methods?

Yes, abstract class can have both abstract methods and concrete methods, which is why it provides partial abstraction.

```java
abstract class Animal {
    abstract void sound();

    void eat() {
        System.out.println("Eating");
    }
}
```

## 11. Can interface have variables? What type?

Yes, interface can have variables, but they are by default public, static, and final constants.

```java
interface Animal {
    int AGE = 5; // public static final by default
}
```

## 12. When should we use interface vs abstract class?

Use interface when we need 100% abstraction and multiple inheritance. Use abstract class when we need partial abstraction with shared code.

## 13. Can a class implement multiple interfaces?

Yes, a class can implement multiple interfaces to achieve multiple inheritance.

```java id="l3p9sx"
interface A { void show(); }
interface B { void display(); }

class C implements A, B {
    public void show() {}
    public void display() {}
}
```

## 14. Can an abstract class implement an interface?

Yes, abstract class can implement interface and may or may not provide implementation.

```java id="9v2tqg"
interface A {
    void show();
}

abstract class B implements A {
    // no implementation (allowed)
}
```

## 15. Can an interface extend another interface?

Yes, interface can extend one or more interfaces.

```java id="y6m2an"
interface A {
    void show();
}

interface B extends A {
    void display();
}
```


## 16. Can an interface extend a class? (Tricky)

**No.** An interface cannot extend a class. An interface can only extend another **interface**. A class uses `implements` for interfaces and `extends` for classes.

```java
class MyClass { }
// interface MyInterface extends MyClass { }   // ❌ compile error

interface A { void methodA(); }
interface B extends A { void methodB(); }       // ✅ interface extends interface
```


## 17. Can a class extend multiple abstract classes?

**No.** Java does not support multiple class inheritance — a class can only `extend` **one** class (abstract or concrete). Use interfaces for multiple inheritance of behavior.

```java
abstract class A { abstract void methodA(); }
abstract class B { abstract void methodB(); }

// class C extends A, B { }   // ❌ Not allowed

interface IA { void methodA(); }
interface IB { void methodB(); }
class C implements IA, IB {   // ✅ multiple interfaces allowed
    public void methodA() { }
    public void methodB() { }
}
```


## 18. Can interface have constructor?

**No.** Interfaces cannot have constructors because they cannot be instantiated. There is no object state to initialize. Only the implementing class is instantiated.

```java
interface Vehicle {
    // Vehicle() { }   // ❌ compile error
    void start();
}
```


## 19. Can abstract class have constructor?

**Yes.** Abstract classes can have constructors. They are invoked when a subclass object is created via `super()`. Used to initialize common fields.

```java
abstract class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    abstract void work();
}

class Developer extends Employee {
    Developer(String name, double salary) {
        super(name, salary);
    }
    void work() { System.out.println(name + " writes code"); }
}
```


## 20. Can interface have private methods?

**Yes**, from **Java 9** onwards. Private methods in interfaces are used as **helper methods** for `default` or `static` methods to avoid code duplication. They cannot be accessed by implementing classes.

```java
interface Logger {
    default void logInfo(String msg)  { log("INFO", msg); }
    default void logError(String msg) { log("ERROR", msg); }

    private void log(String level, String msg) {   // Java 9+
        System.out.println("[" + level + "] " + msg);
    }
}
```


## 21. Can we override static methods in interface?

**No.** Static methods in interfaces belong to the interface itself and **cannot be overridden** by implementing classes. If a class defines a method with the same name, it is a completely separate method — not an override.

```java
interface Printer {
    static void print() { 
        System.out.println("Interface print"); 
    }
}

class MyPrinter implements Printer {
    static void print() { 
        System.out.println("Class print"); 
    }  // new method, not override
}

Printer.print();        // Interface print
MyPrinter.print();      // Class print
```


## 22. What if two interfaces have same default method?

Java gives a **compile-time error**. The implementing class **must override** the conflicting method. It can then choose which interface's default to call using `InterfaceName.super.method()`.

```java
interface A { default void greet() { System.out.println("Hello from A"); } }
interface B { default void greet() { System.out.println("Hello from B"); } }

class C implements A, B {
    @Override
    public void greet() {
        A.super.greet();   // explicitly choose A's version
    }
}

new C().greet();   // Hello from A
```


## 23. Can abstract class be final?

**No.** `abstract` requires the class to be subclassed (to implement abstract methods), while `final` prevents subclassing. They directly contradict each other — Java gives a compile error.

```java
// abstract final class Shape { }   // ❌ compile error: illegal combination
```


## 24. Can interface have main method?

**Yes**, from **Java 8** onwards. Since interfaces can have `static` methods, they can have a `main` method. The JVM can execute it directly.

```java
interface App {
    static void main(String[] args) {
        System.out.println("Main method inside interface");
    }
}
// Run: java App  → Main method inside interface
```


## 25. Can we use both interface and abstract class together?

**Yes.** This is a very common pattern. An abstract class implements an interface partially, and concrete subclasses complete the implementation.

```java
interface Reportable {
    void generate();
    void export();
}

abstract class BaseReport implements Reportable {
    @Override
    public void export() {                          // common implementation
        System.out.println("Exporting to PDF...");
    }
    // generate() left for subclasses
}

class SalesReport extends BaseReport {
    @Override
    public void generate() {
        System.out.println("Generating Sales Report");
    }
}

SalesReport r = new SalesReport();
r.generate();   // Generating Sales Report
r.export();     // Exporting to PDF...
```


## 26. What access modifiers are allowed in interface?

**Access Modifiers** are keywords that define the **visibility** and **access level** of **Classes**, **Methods**, **Variables**, and **Constructors**.

Java provides **4 Access Modifiers**.

**Types of Access Modifiers**

| **Access Modifier**           | **Same Class** | **Same Package** | **Subclass (Different Package)** | **Different Package** |
| ----------------------------- | :------------: | :--------------: | :------------------------------: | :-------------------: |
| **public**                    |        ✅       |         ✅        |                 ✅                |           ✅           |
| **protected**                 |        ✅       |         ✅        |                 ✅                |           ❌           |
| **default (package-private)** |        ✅       |         ✅        |                 ❌                |           ❌           |
| **private**                   |        ✅       |         ❌        |                 ❌                |           ❌           |


**Allowed Members in an Interface**

| Member | Default Modifier | Allowed Modifiers |
|--------|-----------------|-------------------|
| Abstract methods | `public abstract` | `public` only |
| Default methods | `public` | `public` only |
| Static methods | `public` | `public` only |
| Private methods | `private` | `private` only (Java 9+) |
| Variables | `public static final` | `public static final` only |

```java
interface Demo {
    int VALUE = 10;                        // public static final
    void abstractMethod();                 // public abstract
    default void defaultMethod() { }       // public
    static void staticMethod() { }         // public
    private void helperMethod() { }        // private (Java 9+)
}
```


## 27. Can we instantiate interface using lambda?

**Yes**, but only for **functional interfaces** (interfaces with exactly one abstract method). A lambda expression provides the implementation of that single abstract method inline.

```java
@FunctionalInterface
interface Greeting {
    void greet(String name);
}

// Lambda instantiates the functional interface
Greeting g = name -> System.out.println("Hello, " + name + "!");
g.greet("Alice");   // Hello, Alice!

// Another example
Runnable r = () -> System.out.println("Running...");
r.run();
```



## 9. What is `.class` and When do we use in Java?

We use .class when we need to pass class metadata, for example in Spring configuration, exception handling, reflection, and getting beans from the Spring container.


**Common Places Where We Use `.class`**

**1. Spring Configuration**
```java
ApplicationContext context =
    new AnnotationConfigApplicationContext(AppConfig.class);
```
Spring uses it to read configuration from the class.


**2. Exception Handling**
```java
@ExceptionHandler(Exception.class)
```
Tells Spring: handle this type of exception.


**3. Getting Bean from Spring**
```java
Student s = context.getBean(Student.class);
```
Spring will find bean of type `Student`.


**4. Reflection**
```java
Class<?> c = Student.class;
```
Used in reflection to get class information.

**5. Annotations**
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
```
These also use `.class` internally to store metadata.


**`.class` vs `new`**

| `.class` | `new` |
|----------|------|
| Class metadata | Creates object |
| No memory for object | Object created |
| Used by frameworks | Used by developers |

Example:
```java
Student.class   // Class info
new Student()   // Object
```

## 10.  Summary Java Interface & Abstract Class

**🔹 Interface**

* **Interface:** A contract that defines methods a class must implement.
* **Methods:** By default abstract (Java 8+ supports default & static methods).
* **implements keyword:** Used to implement an interface.
* **Multiple Inheritance:** Supported via interfaces.
* **No Constructors:** Interfaces cannot have constructors.
* **Variables:** By default `public static final` (constants).

**🔹 Abstract Class**

* **Abstract Class:** A class that can have both abstract and concrete methods.
* **abstract keyword:** Used to declare abstract class/method.
* **Constructor:** Allowed and used for initialization.
* **Single Inheritance:** A class can extend only one abstract class.
* **Partial Abstraction:** Supports both implemented and unimplemented methods.


# ✅ 06. Java Immutability 


## 1. **What is an Immutable class (Unchangeable) Object?**

An **immutable object** is an object whose **state cannot be changed** after it is created.

If you want different data, you create a **new object** instead of modifying the existing one.

**Example:**

```java
String s1 = "Hello";
String s2 = s1.concat(" World");

System.out.println(s1); // Hello
System.out.println(s2); // Hello World
```

Here, `s1` is **not modified**. `concat()` creates a **new String**.

**Advantages of Immutable Objects**

**Immutability** provides several benefits:

* **Thread-safe** – Multiple threads can use the same object without synchronization.
* **Safe to share** – No one can accidentally modify the object.
* **Easy to cache** – Good for use as **HashMap keys**.
* **Simpler code** – No unexpected state changes.
* **More secure** – Sensitive data cannot be altered after creation.

**How to Create an Immutable Class in Java?**

Follow these rules:

* Make the class **final**.
* Make all fields **private** and **final**.
* Initialize fields through the **constructor**.
* Do **not** provide **setter** methods.
* Return **defensive copies** of mutable objects.

**Example:**

```java
public final class Employee {

    private final int id;
    private final String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

Usage:

```java
Employee emp = new Employee(101, "John");

// emp.setName("Mike"); // Not possible
```



## 1. **Why Must an Immutable Class Be `final`?**

An immutable class should be **final** so that **no subclass can change its behavior or state**.

Without `final`, a subclass could:

* Add **mutable fields**.
* Override methods.
* Break the **immutability** guarantee.

**Example (Problem):**

```java
class Employee {
    public String getName() {
        return "John";
    }
}

class Manager extends Employee {
    private String name = "Mike";

    @Override
    public String getName() {
        return name;
    }
}
```

The subclass changes the behavior, so the original class is no longer truly immutable.


## 1. **Why is the String Class Immutable?**

**String** is immutable for **performance, security, and thread safety**.

Reasons:

* **String Pool** works because Strings cannot change.
* **Thread-safe** without synchronization.
* **Secure** for passwords, file paths, URLs, and class loading.
* **Hash code** is cached, making lookups in **HashMap** faster.

**Example:**

```java
String s = "Java";
s.concat(" 21");

System.out.println(s); // Java
```

`concat()` creates a **new String**, so the original object remains unchanged.



## 1. **What are the Consequences of String Immutability?**

Because **String** is **immutable**, once created its value **cannot be changed**.

**Consequences:**

* **Thread-safe** – Safe to share between multiple threads.
* **Secure** – Values like passwords, URLs, and file paths cannot be modified.
* **String Pool** – Multiple variables can reuse the same String object, saving memory.
* **Hash code caching** – Improves performance in collections like **HashMap**.
* **Every modification creates a new object**, which may increase memory usage if many changes are made.

**Example:**

```java
String s = "Java";
s.concat(" 21");

System.out.println(s); // Java
```

A **new String** is created, while the original remains unchanged.

**Why are Immutable Objects Thread-Safe?**

An **immutable object** cannot change after it is created.

Since **no thread can modify its state**, multiple threads can safely read the same object **without synchronization**.

**Example:**

```java
String message = "Hello";

// Thread 1
System.out.println(message);

// Thread 2
System.out.println(message);
```

Both threads read the same object safely because it **never changes**.

**What is the `final` Keyword and How Does It Help Create Immutable Classes?**

The **final** keyword prevents changes.

It can be used in three ways:

* **final variable** → Value can be assigned only once.
* **final method** → Cannot be overridden.
* **final class** → Cannot be extended.

For **immutable classes**, making the class **final** prevents subclasses from changing its behavior.

**Example:**

```java
public final class Employee {

    private final int id;

    public Employee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
```

Here:

* **final class** → Cannot be inherited.
* **final field** → Cannot be reassigned.

**Is Making All Fields `final` Sufficient for Immutability?**

**No.**

Making all fields **final** only prevents **reassigning the references**. It does **not** prevent changing the contents of **mutable objects**.

**Wrong Example:**

```java
class Employee {
    private final List<String> skills = new ArrayList<>();
}
```

The reference is final, but the list can still be modified.

```java
employee.getSkills().add("Java");
```

The object's state changes, so it is **not immutable**.

**Correct Approach:**

```java
public final class Employee {

    private final List<String> skills;

    public Employee(List<String> skills) {
        this.skills = List.copyOf(skills);
    }

    public List<String> getSkills() {
        return List.copyOf(skills);
    }
}
```

Here, **defensive copies** prevent external modification.



## 1. **What to Do if a Class Field References a Mutable Object?**

If a field references a **mutable object** (like `List`, `Date`, or an array), do **not** expose the original object.

Instead:

* Make a **defensive copy** in the **constructor**.
* Return a **defensive copy** from the **getter**.

**Example:**

```java
public final class Employee {

    private final List<String> skills;

    public Employee(List<String> skills) {
        this.skills = List.copyOf(skills);
    }

    public List<String> getSkills() {
        return List.copyOf(skills);
    }
}
```

This prevents external code from modifying the object's state.


## 1. **What is a Defensive Copy?**

A **defensive copy** is a **new copy** of a mutable object created to protect the original object from modification.

**Example:**

```java
List<String> list = new ArrayList<>();
list.add("Java");

List<String> copy = List.copyOf(list);
```

Changes to `list` do **not** affect `copy`.


## 1. **When Do You Need to Make a Defensive Copy?**

Create a **defensive copy** whenever your class contains **mutable objects**.

Common cases:

* **Constructor** – Copy incoming mutable objects.
* **Getter** – Return a copy instead of the original.
* **Setter** (if used) – Store a copy instead of the passed object.

**Example:**

```java
public Employee(List<String> skills) {
    this.skills = List.copyOf(skills);
}
```

This ensures the original list cannot change the object's state.

**How to Protect a Collection from Modifications?**

There are two common approaches:

**1. Return an Unmodifiable Collection**

```java
private final List<String> skills = new ArrayList<>();

public List<String> getSkills() {
    return Collections.unmodifiableList(skills);
}
```

Attempting to modify it throws an exception.

```java
employee.getSkills().add("Spring");
// UnsupportedOperationException
```

**2. Return a Defensive Copy (Recommended for Immutable Classes)**

```java
public List<String> getSkills() {
    return List.copyOf(skills);
}
```

This returns a **new immutable copy**, keeping the original collection safe.



## 1. **What is `Collections.unmodifiableList()` and How Does It Work?**

**`Collections.unmodifiableList()`** returns a **read-only view** of a list.

* You can **read** elements.
* You **cannot modify** the returned list.
* If the **original list changes**, the unmodifiable view also reflects those changes.

**Example:**

```java
List<String> list = new ArrayList<>();
list.add("Java");

List<String> readOnly = Collections.unmodifiableList(list);

System.out.println(readOnly); // [Java]

// readOnly.add("Spring"); // UnsupportedOperationException

list.add("Spring");
System.out.println(readOnly); // [Java, Spring]
```



## 19. What is the Difference Between Shallow Copy and Deep Copy in Java?


**Shallow Copy** creates a new object, but the **nested objects are shared** between the original and copied object.

**Deep Copy** creates a new object along with **completely new copies of all nested objects**, so both objects are fully independent.

**Key Differences**

| Feature              | **Shallow Copy**                                                                | **Deep Copy**                                                                   |
| -------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| **Definition**       | Creates a **new object**, but copies only the **references** of nested objects. | Creates a **new object** and also creates **new copies of all nested objects**. |
| **Memory**           | Parent object is new, child objects are shared.                                 | Both parent and child objects are separate.                                     |
| **Object Reference** | Shared between original and copied object.                                      | Independent references.                                                         |
| **Modification**     | Changes in a child object affect both objects.                                  | Changes do not affect the other object.                                         |
| **Performance**      | Faster and uses less memory.                                                    | Slower and uses more memory.                                                    |
| **Use Case**         | When shared data is acceptable.                                                 | When complete independence is required.                                         |


**How it Works**

**Shallow Copy**

* Creates a **new parent object**.
* Copies **primitive values**.
* Copies **references** of nested objects.
* Changes to nested objects affect both copies.

**Deep Copy**

* Creates a **new parent object**.
* Creates **new copies of every nested object**.
* Changes in one object do **not** affect the other.

**Example**

```java
class Address {
    String city;

    Address(String city) {
        this.city = city;
    }

    Address(Address other) {      // Deep copy constructor
        this.city = other.city;
    }
}

class Employee {
    String name;
    Address address;

    Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow Copy
    Employee(Employee other) {
        this.name = other.name;
        this.address = other.address;
    }

    // Deep Copy
    Employee(Employee other, boolean deep) {
        this.name = other.name;
        this.address = new Address(other.address);
    }
}
```

**Example Usage**

```java
Address address = new Address("Bangalore");

Employee emp1 = new Employee("John", address);

// Shallow Copy
Employee emp2 = new Employee(emp1);

emp2.address.city = "Mumbai";

System.out.println(emp1.address.city); // Mumbai

// Deep Copy
Employee emp3 = new Employee(emp1, true);

emp3.address.city = "Delhi";

System.out.println(emp1.address.city); // Mumbai
System.out.println(emp3.address.city); // Delhi
```


**Common Interview Follow-up Questions**

**1. Does `Object.clone()` perform a shallow or deep copy?**

**Answer:** By default, `Object.clone()` performs a **Shallow Copy**.

**2. Why is `String` safe in a shallow copy?**

**Answer:** Because **String is immutable**, its value cannot be changed after creation.

**3. How can you implement a Deep Copy in Java?**

**Answer:** By **creating new instances** of all nested objects using **constructors**, **copy constructors**, or **serialization**.

**4. Which copy is faster?**

**Answer:** **Shallow Copy**, because it copies only object references instead of creating new nested objects.

**5. Which copy should be used for mutable objects?**

**Answer:** **Deep Copy**, because it prevents shared references and unintended modifications.




## 1. **What Happens if You Override a Getter in a Subclass of an Immutable Class?**

If an immutable class is **not `final`**, a subclass can **override getters** and return different or mutable values, breaking **immutability**.

**Example:**

```java
class Person {
    public String getName() {
        return "John";
    }
}

class Employee extends Person {
    @Override
    public String getName() {
        return "Mike";
    }
}
```

The subclass changes the behavior, so the class is **no longer truly immutable**.

**Solution:** Make the immutable class **final**.

**How Does String Pool Work and How Is It Related to Immutability?**

The **String Pool** is a special memory area that stores **one copy** of each unique String literal.

If the same String is created again, Java reuses the existing object.

**Example:**

```java
String s1 = "Java";
String s2 = "Java";

System.out.println(s1 == s2); // true
```

Both variables point to the **same object**.

This is safe only because **String is immutable**. If Strings could change, modifying one reference would affect all others sharing the same object.


## 1. **Can You Change a String Value via Reflection?**

**Technically yes (in older Java versions), but it is strongly discouraged.**

In modern Java (Java 9+), this is **much more difficult** due to stronger encapsulation and internal changes, and it should **never** be done in production code.

**Example (Old Java):**

```java
String s = "Hello";

// Using reflection to modify internal value
// Possible in older Java versions
// Not recommended
```

Reflection can break Java's assumptions and lead to **unexpected behavior**.


## 1. **What is Record and How Does It Help Create Immutable Classes?**

A **Record** is a special Java class (introduced in **Java 16**) designed to hold **immutable data**.

By default, a Record:

* Has **private final fields**.
* Generates **constructor**, **getters**, `equals()`, `hashCode()`, and `toString()`.
* Does **not** generate setters.

**Example:**

```java
public record Employee(int id, String name) {
}
```

Usage:

```java
Employee emp = new Employee(101, "John");

System.out.println(emp.id());   // 101
System.out.println(emp.name()); // John
```

Records greatly reduce boilerplate and make creating **immutable data classes** simple and concise.



## 1. **Why are `LocalDate` and `LocalDateTime` Immutable?**

`**LocalDate**` and `**LocalDateTime**` are **immutable** because date and time values should **never change** after creation.

Methods like `plusDays()` or `minusMonths()` create a **new object** instead of modifying the existing one.

**Example:**

```java
LocalDate date1 = LocalDate.of(2026, 7, 20);

LocalDate date2 = date1.plusDays(5);

System.out.println(date1); // 2026-07-20
System.out.println(date2); // 2026-07-25
```

The original object remains unchanged.

**Advantages of Immutable Objects for Caching**

**Immutable objects** are ideal for **caching** because their state **never changes**.

**Benefits:**

* **Safe to reuse** the same object.
* **No synchronization** is needed.
* **No risk** of cached data being modified.
* **Improves performance** by avoiding unnecessary object creation.

**Example:**

```java
Map<Integer, String> cache = new HashMap<>();

cache.put(1, "Java");

String value = cache.get(1);
```

The cached value is safe because a **String is immutable**.

**How Does Immutability Affect Performance?**

**Advantages:**

* **Thread-safe** without synchronization.
* **String Pool** reduces memory usage.
* **Hash code caching** improves lookup performance.
* **Safe object sharing** reduces copying.

**Disadvantages:**

* Every modification creates a **new object**.
* Frequent updates can create **more objects**, increasing **memory usage** and **GC activity**.

**Example:**

```java
String s = "Java";

s = s + " 21";
s = s + " LTS";
```

Each concatenation creates a **new String**.



# ✅ 06. Java String 

## 4. What is the difference between == and equals() method?

**`==` operator:**
The `==` operator is used to compare **references** of two objects. It checks whether both variables point to the **same memory location**. For primitive data types, it compares the **actual values**.

**`equals()` method:**
The `equals()` method is used to compare the **content or logical value** of two objects. It is defined in the `Object` class and can be **overridden** by classes (like `String`) to provide meaningful value-based comparison.


**Examples:**

```java
// Using ==
String s1 = new String("Hello");
String s2 = new String("Hello");
System.out.println(s1 == s2); // false, different objects

// Using equals()
System.out.println(s1.equals(s2)); // true, content is the same

// With primitives
int a = 5, b = 5;
System.out.println(a == b); // true, values are equal
```

## 5. What is the difference between String, StringBuilder, and StringBuffer?

All three are used to handle **text (sequence of characters)** in Java, but they differ in **mutability**, **performance**, and **thread safety**.

* **String**: **Immutable** (cannot be changed after creation).
* **StringBuilder**: **Mutable** and **not thread-safe**.
* **StringBuffer**: **Mutable** and **thread-safe** because its methods are **synchronized**.


**Key Differences**

| **Feature**       | **String**                         | **StringBuilder**                   | **StringBuffer**                   |
| ----------------- | ---------------------------------- | ----------------------------------- | ---------------------------------- |
| **Mutability**    | Immutable                          | Mutable                             | Mutable                            |
| **Thread Safety** | Yes (because immutable)            | No                                  | Yes (synchronized)                 |
| **Performance**   | Slow for frequent modifications    | Fastest                             | Slower than `StringBuilder`        |
| **Memory Usage**  | Creates new object on every change | Reuses same object                  | Reuses same object                 |
| **Use Case**      | Fixed text                         | Single-threaded string manipulation | Multi-threaded string manipulation |

**How It Works**

* **String**: Every operation like `+` or `concat()` creates a **new object** in memory.
* **StringBuilder**: Methods like `append()` and `insert()` modify the **same object**, avoiding unnecessary object creation.
* **StringBuffer**: Works like `StringBuilder`, but methods are **synchronized**, making it safe for multiple threads.

**When to Use**

| **Scenario**                                             | **Best Choice**   |
| -------------------------------------------------------- | ----------------- |
| Fixed message or constant value                          | **String**        |
| Building SQL queries, loops, or large text in one thread | **StringBuilder** |
| Shared string object modified by multiple threads        | **StringBuffer**  |

**Code Example**

```java
// String (Immutable)
String str = "Hello";
str = str + " World";   // Creates a new object
System.out.println(str);

// StringBuilder (Mutable, Fast)
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");    // Modifies same object
System.out.println(sb);

// StringBuffer (Mutable, Thread-safe)
StringBuffer sf = new StringBuffer("Hello");
sf.append(" World");    // Modifies same object with synchronization
System.out.println(sf);
```

**Performance Example**

```java
// Not efficient for repeated updates
String result = "";
for (int i = 0; i < 5; i++) {
    result += i;
}

// Efficient for repeated updates
StringBuilder builder = new StringBuilder();
for (int i = 0; i < 5; i++) {
    builder.append(i);
}
System.out.println(builder);
```

**Easy Way to Remember**

* **String = Immutable + Safe + Slow for updates**
* **StringBuilder = Mutable + Fast + Single Thread**
* **StringBuffer = Mutable + Thread-Safe + Multi Thread**



## 8. What is string pooling in java?

**String Pooling** is a Java memory optimization technique where **string literals are stored in a special memory area called the String Pool**. If the same string value already exists in the pool, Java reuses the existing object instead of creating a new one.


**How It Works**

* When a string literal is created using double quotes (`""`), Java first checks the **String Pool**.
* If the value already exists, Java returns a reference to the existing object.
* If it does not exist, Java creates a new object and stores it in the pool.

```java id="4skp0r"
String s1 = "Java";
String s2 = "Java";
```

Here, only **one object** `"Java"` is created in the **String Pool**, and both `s1` and `s2` point to the same object.

**Key Features**

* **Stores only one copy** of identical string literals.
* **Reduces memory usage** by avoiding duplicate objects.
* **Improves performance** because object creation is minimized.
* Works because **`String` is immutable**.

**When to Use**

* Whenever you create strings using **string literals** (`"Hello"`), Java automatically uses the String Pool.
* For dynamically created strings using `new String()`, use **`intern()`** if you want to place them into the String Pool.

**Code Example**

```java id="cwlfzr"
String s1 = "Hello";
String s2 = "Hello";

System.out.println(s1 == s2); // true
```

Both variables point to the **same pooled object**, so `==` returns `true`.

**Using `new String()`**

```java id="b5q7to"
String s1 = "Hello";
String s2 = new String("Hello");

System.out.println(s1 == s2); // false
System.out.println(s1.equals(s2)); // true
```

Here:

* `s1` points to the object in the **String Pool**.
* `s2` points to a **new object** created in the heap.
* `==` compares **references**, while `equals()` compares **content**.

**Using `intern()`**

```java id="t0odqg"
String s1 = new String("Java");
String s2 = s1.intern();

System.out.println(s2 == "Java"); // true
```

The **`intern()`** method returns the reference from the **String Pool** if it exists, or adds the string to the pool if it does not.

**Easy Way to Remember**

* **String Literal (`"Java"`) = Uses String Pool**
* **`new String("Java")` = Creates New Object**
* **`intern()` = Moves or Returns String from the Pool**

**Common Interview Follow-up Questions**

**1. Why is String Pooling possible in Java?**
Because **String** objects are **immutable**, so multiple references can safely share the same object.

**2. Where is the String Pool stored?**
The **String Pool** is stored in the **Heap memory** (since Java 7).

**3. What is the difference between `==` and `equals()` for Strings?**

* **`==`** compares **references**.
* **`equals()`** compares **content**.

**4. What does `intern()` do?**
It returns the **reference to the pooled String** if it exists; otherwise, it adds the string to the **String Pool** and returns that reference.

**5. Why does `new String("Java")` create a new object?**
Because the **`new`** keyword always creates a **new object** in the Heap, even if the same value exists in the **String Pool**.



## 1. **How does the String Pool work?**

The **String Pool** (also called the **String Constant Pool**) is a special area in the **Heap** where Java stores **String literals**. If the same literal already exists in the pool, Java reuses the existing object instead of creating a new one.

**How it works:**

1. Java checks whether the String literal already exists in the pool.
2. If it exists, it returns the existing reference.
3. If it doesn't exist, Java creates the String in the pool and returns its reference.

**Example:**

```java
String s1 = "Hello";
String s2 = "Hello";

System.out.println(s1 == s2); // true
```

Only **one** `"Hello"` object is created, and both `s1` and `s2` reference it.

**Benefits:**

* **Saves memory**
* **Improves performance**
* **Avoids duplicate String objects**

---

## 1. **What is the difference between creating a String via literal and via `new`?**

| **String Literal**                  | **new String()**                     |
| ----------------------------------- | ------------------------------------ |
| Stored in the **String Pool**       | Creates a **new object** in the heap |
| Reuses existing object if available | Always creates a new object          |
| Memory efficient                    | Uses more memory                     |
| Faster                              | Slightly slower                      |

**Example:**

```java
String s1 = "Java";
String s2 = "Java";
String s3 = new String("Java");

System.out.println(s1 == s2); // true
System.out.println(s1 == s3); // false
System.out.println(s1.equals(s3)); // true
```

---

## 1. **When should you use `intern()`?**

The **`intern()`** method returns the reference to the String from the **String Pool**. If the String is not already in the pool, it is added.

**Use `intern()` when:**

* You have many duplicate Strings created with `new`.
* You want to **reduce memory usage**.
* You need **reference equality (`==`)** for identical String values.

**Example:**

```java
String s1 = new String("Java");
String s2 = s1.intern();
String s3 = "Java";

System.out.println(s2 == s3); // true
```

Without `intern()`:

```java
System.out.println(s1 == s3); // false
```



## 1. **Why is String immutable?**

A **String is immutable**, meaning its value **cannot be changed** after it is created. Any modification creates a **new String object**.

**Example:**

```java
String s = "Java";
s = s.concat(" 21");

System.out.println(s); // Java 21
```

The original `"Java"` object remains unchanged.

**Why Java made String immutable:**

* **Security** – Prevents modification of sensitive values like file paths, URLs, and class names.
* **Thread Safety** – Multiple threads can safely share the same String without synchronization.
* **String Pool** – Safe object sharing is possible because String contents never change.
* **Caching** – `hashCode()` can be cached, improving the performance of collections like **HashMap**.
* **Reliability** – Once created, the value remains consistent throughout the program.


## 1. **When to use `StringBuilder` vs `StringBuffer`?**

Both **`StringBuilder`** and **`StringBuffer`** are mutable classes used to modify strings efficiently.

| **StringBuilder**                   | **StringBuffer**                   |
| ----------------------------------- | ---------------------------------- |
| **Not thread-safe**                 | **Thread-safe**                    |
| Faster                              | Slower                             |
| No synchronization                  | Methods are synchronized           |
| Use in single-threaded applications | Use in multi-threaded applications |

**Use `StringBuilder` when:**

* Performance is important.
* Only one thread modifies the string.

**Use `StringBuffer` when:**

* Multiple threads modify the same string.
* Thread safety is required.

**Example:**

```java
StringBuilder sb = new StringBuilder();
sb.append("Java");
sb.append(" 21");

System.out.println(sb); // Java 21
```



## 1. **Why is `StringBuffer` slower than `StringBuilder`?**

`StringBuffer` is slower because all its methods are **synchronized**, which ensures that only one thread can access them at a time.

Example:

```java
buffer.append("Hello"); // synchronized
builder.append("Hello"); // not synchronized
```

**Why synchronization adds overhead:**

* Acquires and releases locks.
* Performs thread-safety checks.
* Reduces performance when thread safety isn't needed.


## 1. **What happens when concatenating strings via the `+` operator?**

The `+` operator creates a **new String** because **String is immutable**.

Example:

```java
String s = "Java";
s = s + " 21";
```

The JVM effectively performs:

```java
StringBuilder sb = new StringBuilder();
sb.append("Java");
sb.append(" 21");
s = sb.toString();
```

A new String object is created for the result.

**Interview Tip:**
Using `+` inside a loop creates many temporary String objects, reducing performance.

**Bad Example:**

```java
String result = "";

for (int i = 0; i < 1000; i++) {
    result += i;
}
```

**Good Example:**

```java
StringBuilder sb = new StringBuilder();

for (int i = 0; i < 1000; i++) {
    sb.append(i);
}

String result = sb.toString();
```


## 1. **How does the Java compiler optimize string concatenation?**

The Java compiler automatically converts most `+` concatenations into **`StringBuilder`** operations.

**Source code:**

```java
String s = "Hello " + name;
```

**Compiler-generated equivalent:**

```java
String s = new StringBuilder()
        .append("Hello ")
        .append(name)
        .toString();
```

This avoids creating multiple intermediate String objects.

**Compile-time optimization for constants:**

If all values are **compile-time constants**, the compiler performs **constant folding** and creates a single String.

```java
String s = "Hello" + " World";
```

Becomes:

```java
String s = "Hello World";
```

No `StringBuilder` is created.



## 1. **Can you use `==` to compare Strings?**

Yes, but **only if you want to compare object references**, not the actual String content.

* `==` checks whether two references point to the **same object**.
* It does **not** compare the characters inside the String.

**Example:**

```java
String s1 = "Java";
String s2 = "Java";
String s3 = new String("Java");

System.out.println(s1 == s2); // true
System.out.println(s1 == s3); // false
```


## 1. **What is the difference between `==` and `equals()` for String?**

| **`==`**                                           | **`equals()`**                                     |
| -------------------------------------------------- | -------------------------------------------------- |
| Compares **references**                            | Compares **content**                               |
| Checks if both references point to the same object | Checks if both Strings contain the same characters |
| Can return `false` even if contents are identical  | Returns `true` if contents are identical           |

**Example:**

```java
String s1 = new String("Java");
String s2 = new String("Java");

System.out.println(s1 == s2);      // false
System.out.println(s1.equals(s2)); // true
```


## 1. **Where is the String Pool stored (in which memory area)?**

The **String Pool** is stored in the **Heap memory**.

**Java versions:**

* **Java 6 and earlier:** String Pool was stored in the **PermGen (Permanent Generation)**.
* **Java 7 and later:** String Pool was moved to the **Heap**.
* **Java 8+:** PermGen was removed and replaced by **Metaspace**, while the **String Pool remains in the Heap**.



## 1. **Can String Pool cause `OutOfMemoryError`?**

Yes.

Since the **String Pool is stored in the Heap**, creating or interning a very large number of unique Strings can eventually exhaust heap memory and cause an **`OutOfMemoryError: Java heap space`**.

**Example:**

```java
List<String> list = new ArrayList<>();

while (true) {
    list.add(UUID.randomUUID().toString().intern());
}
```

This continuously adds unique Strings to the String Pool until the heap is full.

**How to avoid it:**

* Avoid calling **`intern()`** on a large number of unique Strings.
* Remove unnecessary references so unused Strings can be garbage collected.
* Increase the heap size if appropriate using **`-Xmx`**.


## 1. **What does the `substring()` method do and how did it work before Java 7?**

The **`substring()`** method returns a **part of a String**.

**Syntax:**

```java
substring(beginIndex)
substring(beginIndex, endIndex)
```

* `beginIndex` → Inclusive
* `endIndex` → Exclusive

**Example:**

```java
String s = "Hello World";

System.out.println(s.substring(6));     // World
System.out.println(s.substring(0, 5));  // Hello
```

**Before Java 7**

`substring()` **did not create a new character array**. Instead, it shared the **same internal `char[]`** with the original String and only stored a different offset and length.

```java
String large = "Very large text...";
String small = large.substring(5, 10);
```

Both `large` and `small` pointed to the same `char[]`.

**Advantage:**

* Fast
* Memory efficient for large Strings

**Disadvantage:**

* A small substring could keep a very large original `char[]` in memory.

---

## 1. **Why was the `substring()` implementation changed in Java 7?**

Starting with **Java 7**, `substring()` creates a **new character array** for the substring instead of sharing the original one.

**Reason:**
To prevent **memory leaks**.

**Old behavior:**

```java
String large = loadLargeFile();   // 100 MB
String small = large.substring(0, 10);
```

Even if `large` was no longer used, the entire **100 MB** character array stayed in memory because `small` referenced it.

**New behavior (Java 7+):**

* A new `char[]` containing only the substring is created.
* The original large array can be garbage collected.

**Interview Tip:**

* **Java 6:** Shared `char[]`
* **Java 7+:** Copies characters into a new `char[]`


## 1. **How does the `split()` method work?**

The **`split()`** method divides a String into an array using a **regular expression (regex)** as the delimiter.

**Syntax:**

```java
String[] split(String regex)
```

**Example:**

```java
String s = "Java,Spring,Docker";

String[] arr = s.split(",");

for (String str : arr) {
    System.out.println(str);
}
```

**Output:**

```text
Java
Spring
Docker
```

**Example using regex:**

```java
String s = "Java  Spring   Docker";

String[] arr = s.split("\\s+");
```

This splits on one or more whitespace characters.


## 1. **What is the difference between `replace()` vs `replaceAll()`?**

| **`replace()`**                     | **`replaceAll()`**                                   |
| ----------------------------------- | ---------------------------------------------------- |
| Replaces literal text or characters | Replaces text using a **regular expression (regex)** |
| Does **not** interpret regex        | Interprets the first argument as a regex             |
| Faster                              | Slightly slower because of regex processing          |

**`replace()` Example:**

```java
String s = "Java Java";

System.out.println(s.replace("Java", "Spring"));
```

**Output:**

```text
Spring Spring
```

**`replaceAll()` Example:**

```java
String s = "Java123Spring456";

System.out.println(s.replaceAll("\\d+", ""));
```

**Output:**

```text
JavaSpring
```

Here, `\\d+` is a regex that matches one or more digits.


## 1. **What is String encoding?**

**String encoding** is the process of converting characters into **bytes** using a specific **character set (charset)**.

Common encodings:

* **UTF-8** – Most commonly used, variable-length (1–4 bytes per character)
* **UTF-16** – Java's internal character representation before Java 9
* **ISO-8859-1** – Single-byte encoding for Western European characters
* **US-ASCII** – 7-bit encoding for English characters

**Example:**

```java
String s = "Hello";

byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
```

Different encodings produce different byte sequences.


## 1. **How to properly convert a String to `byte[]` and back?**

Use the **same charset** for both encoding and decoding.

**String → `byte[]`:**

```java
import java.nio.charset.StandardCharsets;

String s = "Hello";

byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
```

**`byte[]` → String:**

```java
String result = new String(bytes, StandardCharsets.UTF_8);

System.out.println(result); // Hello
```

**Incorrect Example:**

```java
byte[] bytes = s.getBytes(StandardCharsets.UTF_8);

// Wrong charset
String result = new String(bytes, StandardCharsets.ISO_8859_1);
```

This may produce incorrect characters.



## 1. **What are Compact Strings in Java 9+?**

**Compact Strings** are a JVM optimization introduced in **Java 9** to reduce memory usage.

**Before Java 9:**

* Every `String` stored characters in a **`char[]`**.
* Each character used **2 bytes (UTF-16)**, even for simple ASCII text.

**Java 9+:**

* `String` stores characters in a **`byte[]`**.
* A small internal flag (`coder`) indicates the encoding:

  * **LATIN1 (1 byte per character)** for ASCII/Latin-1 characters.
  * **UTF16 (2 bytes per character)** for characters that require it.

**Example:**

```java
String s1 = "Hello"; // Stored as LATIN1 (1 byte/char)
String s2 = "你好";    // Stored as UTF16 (2 bytes/char)
```

**Benefits:**

* **Reduces memory usage** for most Strings.
* **Improves cache efficiency**.
* **No API changes**—completely transparent to developers.


## 1. **How to find out how much memory a String occupies?**

There is **no built-in Java API** that returns the exact memory used by a `String`.

**Approximate size:**

**Java 8:**

* Characters: `2 × length` bytes (`char[]`)
* Plus object headers and references.

**Java 9+:**

* ASCII/Latin-1 Strings: about `1 × length` bytes (`byte[]`)
* UTF-16 Strings: about `2 × length` bytes
* Plus object headers, references, and the `coder` field.

**Using Instrumentation API:**

```java
long size = instrumentation.getObjectSize(str);
```

This returns the shallow size of the `String` object, not the memory of referenced objects.

**Using tools:**

* **JOL (Java Object Layout)** – Inspect object memory layout.
* **VisualVM**
* **Eclipse Memory Analyzer (MAT)**
* **JProfiler**
* **YourKit**


## 1. **Can you change the content of a String via reflection?**

**Normally, no.** `String` is designed to be **immutable**, and modern Java strongly protects its internal state.

**Before Java 9**

It was possible (though strongly discouraged) to modify a `String` using reflection by accessing its internal `char[]`.

```java
Field field = String.class.getDeclaredField("value");
field.setAccessible(true);
```

This could change the contents of a `String`, breaking immutability and potentially causing unexpected behavior.

**Java 9+**

Java introduced **stronger encapsulation** (module system), and `String` now stores its data in a **`byte[]`** instead of `char[]` (Compact Strings). Accessing or modifying internal fields via reflection is much more restricted and may throw an exception unless special JVM options (such as `--add-opens`) are used.


## 1. **What is String deduplication in G1 GC?**

**String Deduplication** is a feature of the **G1 Garbage Collector** introduced in **Java 8 Update 20**.

Its purpose is to **reduce memory usage** by making multiple `String` objects with the same content share the **same internal character data**.

**Example:**

```java
String s1 = new String("Java");
String s2 = new String("Java");
```

Without deduplication:

* `s1` and `s2` are different objects.
* Each has its own internal character storage.

With G1 String Deduplication:

* `s1` and `s2` remain different `String` objects.
* They share the same internal `byte[]` (Java 9+) or `char[]` (Java 8).

**Benefits:**

* **Reduces heap memory usage**
* **Lowers GC pressure**
* Useful for applications with many duplicate Strings

**Enable it:**

```text
-XX:+UseG1GC
-XX:+UseStringDeduplication
```


## 1. **Why does String implement `Comparable` and `CharSequence`?**

**1. `Comparable<String>`**

`String` implements **`Comparable<String>`** so Strings can be **sorted naturally** in **lexicographical (dictionary) order**.

**Example:**

```java
List<String> list = List.of("Banana", "Apple", "Orange");

Collections.sort(list);

System.out.println(list);
```

**Output:**

```text
[Apple, Banana, Orange]
```

The `compareTo()` method compares Strings character by character using Unicode values.


**2. `CharSequence`**

`CharSequence` is an interface that represents a **read-only sequence of characters**.

It allows APIs to work with different character-based classes, not just `String`.

Other classes implementing `CharSequence` include:

* `String`
* `StringBuilder`
* `StringBuffer`
* `CharBuffer`

**Example:**

```java
public void print(CharSequence text) {
    System.out.println(text);
}

print("Java");
print(new StringBuilder("Spring"));
```

Both calls work because both classes implement `CharSequence`.

**Main methods of `CharSequence`:**

* `length()`
* `charAt()`
* `subSequence()`
* `toString()`





# ✅ 06. Java Exception Handling 

## 1. What is an exception in Java?

An **Exception** is an event that occurs during program execution and disrupts the normal flow of the application.

**Key Features**

* Represents runtime errors
* Can be **handled** using `try-catch`
* Helps prevent application crashes
* Provides information about the error

**How It Works**

When an error occurs, Java creates an **Exception Object** and throws it. The exception can be caught and handled using a **try-catch** block.

**Code Example**

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```


**When to Use**

Use **Exception Handling** whenever an operation may fail.

Examples:

* Reading a file
* Connecting to a database
* Calling an external API
* Performing mathematical operations

**Types of Exceptions**

* **Checked Exceptions** – Checked at compile time, e.g., `IOException`, `SQLException`
* **Unchecked Exceptions** – Occur at runtime, e.g., `NullPointerException`, `ArithmeticException`


## 2. What is the exception hierarchy in Java?

Java's **exception hierarchy** starts with Throwable class, which has two main branches: Error and Exception. Exception further divides into checked and unchecked exceptions.

```
Throwable
├── Error (unchecked)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception
    ├── IOException (checked)
    ├── SQLException (checked)
    └── RuntimeException (unchecked)
        ├── NullPointerException
        ├── ArithmeticException
        └── ArrayIndexOutOfBoundsException
```

- **Error:** System-level problems, usually unrecoverable
- **Exception:** Application-level problems that can be handled

## 3. What are checked and unchecked exceptions?

**Checked exceptions** must be handled at compile time(IOException, SQLExcepti).

**unchecked exceptions** occur at runtime(NullPointerException, ArithmeticException) and don't require mandatory handling.

```java
// Checked - must handle
try {
    FileReader file = new FileReader("file.txt");
} catch (IOException e) { }

// Unchecked - optional handling
int[] arr = {1, 2, 3};
int value = arr[5]; // ArrayIndexOutOfBoundsException
```

**Key Differences**

| **Feature**           | **Checked Exception**                                   | **Unchecked Exception**                                                                                     |
| --------------------- | ------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| **Checked At**        | **Compile Time**                                        | **Runtime**                                                                                                 |
| **Handling Required** | **Yes** (`try-catch` or `throws`)                       | **No** (Optional)                                                                                           |
| **Superclass**        | **Exception** (excluding `RuntimeException`)            | **RuntimeException**                                                                                        |
| **Cause**             | External conditions (File, Database, Network)           | Programming errors (Logic or Coding mistakes)                                                               |
| **Examples**          | `IOException`, `SQLException`, `ClassNotFoundException` | `NullPointerException`, `ArithmeticException`, `ArrayIndexOutOfBoundsException`, `IllegalArgumentException` |


**Common Interview Follow-up Questions**

**1. What is the main difference between Checked and Unchecked Exceptions?**
**Checked Exceptions** are checked at **Compile Time** and must be handled, whereas **Unchecked Exceptions** occur at **Runtime** and handling is optional.

**2. Can we create Custom Checked and Unchecked Exceptions?**
**Yes.**

* Extend **`Exception`** for a **Checked Exception**.
* Extend **`RuntimeException`** for an **Unchecked Exception**.

**3. Why are Checked Exceptions required to be handled?**
Because they represent **recoverable conditions** such as file, database, or network failures.

**4. Should we catch all Unchecked Exceptions?**
**No.** Only catch them when you can handle them meaningfully. Otherwise, fix the underlying programming issue.

**5. Give examples of Checked and Unchecked Exceptions.**

* **Checked:** `IOException`, `SQLException`, `ClassNotFoundException`
* **Unchecked:** `NullPointerException`, `ArithmeticException`, `ArrayIndexOutOfBoundsException`, `IllegalArgumentException`

## 4. What is the difference between throw and throws?

Both **`throw`** and **`throws`** are used in **Exception Handling**, but they serve different purposes.

**Key Difference**

* **`throw`** is used to explicitly throw an exception.
* **`throws`** is used to declare that a method may throw an exception.

**How It Works**

**`throw`**

Used inside a method body to create and throw an exception object.

```java
public void validateAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Age must be 18 or above");
    }
}
```

**`throws`**

Used in the method signature to indicate that the method may throw an exception.

```java
public void readFile() throws IOException {
    FileReader file = new FileReader("data.txt");
}
```

**Key Features**

| **throw**                             | **throws**                        |
| ------------------------------------- | --------------------------------- |
| Used to throw an exception            | Used to declare exceptions        |
| Used inside a method                  | Used in method signature          |
| Throws one exception object at a time | Can declare multiple exceptions   |
| Followed by an exception object       | Followed by exception class names |


**When to Use**

Use **`throw`** when you want to explicitly raise an exception.

Use **`throws`** when a method may produce an exception and you want the caller to handle it.


**Common Interview Follow-up Questions**

**1. Can `throw` throw multiple exceptions?**

**No.** It throws **one exception object** at a time.

**2. Can `throws` declare multiple exceptions?**

**Yes.** Multiple exceptions can be declared, separated by commas.

```java
void process() throws IOException, SQLException {
}
```

**3. Is `throws` mandatory for all exceptions?**

**No.** It is mainly required for **Checked Exceptions**. **Unchecked Exceptions** do not need to be declared.

**4. Can we use both `throw` and `throws` together?**

**Yes.** A method can declare an exception using **`throws`** and explicitly throw it using **`throw`**.

**5. What is the difference between Checked and Unchecked Exceptions?**

* **Checked Exceptions** are checked at **compile time** and must be handled or declared using **`throws`**.
* **Unchecked Exceptions** occur at **runtime** and are **not required** to be handled or declared.


## 5. What is try-catch-finally block?

**Try-catch-finally** is Java's **exception handling** mechanism where try contains risky code, catch handles exceptions, and finally executes cleanup code regardless of exceptions.

- **try:** Contains code that might throw exceptions
- **catch:** Handles specific exceptions
- **finally:** Always executes (cleanup code)

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("This always executes");
}
```


## 6. How do you create custom exceptions?

**Custom exceptions** are created by extending Exception class for checked exceptions or RuntimeException for unchecked exceptions. They provide specific error information for your application.

- Extend Exception (checked) or RuntimeException (unchecked)
- Provide constructors for different scenarios
- Add custom fields/methods if needed

```java
// Checked custom exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Unchecked custom exception
class InsufficientBalanceException extends RuntimeException {
    private double balance;
    
    public InsufficientBalanceException(String message, double balance) {
        super(message);
        this.balance = balance;
    }
}
```


## 8. How do you Handle Global Exception?

In Spring Boot, we handle exceptions using `@RestControllerAdvice` for global exception handling and `@ExceptionHandler` to catch specific exceptions. When an exception occurs in the controller, it is handled in one central place instead of writing try-catch everywhere.


**Global exception handling is implemented using @ControllerAdvice in Spring Boot to handle exceptions across the entire application in a centralized way.**

Other than this There are **two ways** depending on the application type:

| Application Type | Global Exception Handling                   |
| ---------------- | ------------------------------------------- |
| Core Java        | try-catch / Thread.UncaughtExceptionHandler |
| Spring Boot      | @ControllerAdvice                           |


**1. Global Exception Handling in Core Java**

Use **UncaughtExceptionHandler** to handle exceptions globally.

```java
class TestApp {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptioHandler());
        Thread tr = new Thread(() ->{
            throw new RuntimeException("There is erro");
        });
        tr.start();
    }
}
class ExceptioHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException (Thread t, Throwable e) {
        System.out.println("Global Exception caught " + e.getMessage());
    }
}
```

**2. Global Exception Handling in Spring Boot (@ControllerAdvice, @RestControllerAdvice
)**

This handles exceptions for the **entire application**

```java
import org.springframework.web.bind.annotation.ControllerAdvice;

// GlobalExceptionHandler.java
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}

// VotingController.java
@RestController
@RequestMapping("/api")
class VotingController {

    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @GetMapping("/vote")
    public ResponseEntity<String> vote(@RequestParam int age) {
        votingService.vote(age);
        return ResponseEntity.ok("Vote successful");
    }
}
```

**REST API Global Exception**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(),
                                     HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

## 9. Difference between ClassNotFoundException vs NoClassDefFoundError?


`ClassNotFoundException` occurs when JVM tries to load a class dynamically using `Class.forName()`, but the class is not found in the classpath.

`NoClassDefFoundError` occurs when the class was available during compilation, but it is missing at runtime during deployment.

In short, **ClassNotFoundException happens during dynamic loading, whereas NoClassDefFoundError happens when a required class is missing at runtime.**

**Example: ClassNotFoundException**

```java id="95q2oc"
Class.forName("com.company.Employee");
```

If the class does not exist:

```text id="kj1icn"
ClassNotFoundException
```

**Example: NoClassDefFoundError**

```java id="72tqok"
Employee emp = new Employee();
```

The code compiles successfully, but if `Employee.class` is missing from the deployed JAR:

```text id="xhk56m"
NoClassDefFoundError
```


## 10. What happens if a class is missing in a JAR during deployment?

If a class is available during compilation but missing in the deployed JAR, the application will fail at runtime with a `NoClassDefFoundError`.

This usually happens because a required dependency was not packaged, the wrong JAR version was deployed, or a JAR is missing from the classpath.

```java id="7uokki"
Employee emp = new Employee();
```

The code compiles successfully because `Employee.class` was available during compilation.

But if `Employee.class` is missing in production:

```text id="gdv5qi"
java.lang.NoClassDefFoundError:
com/company/Employee
```

**Common Reasons**

* Missing dependency JAR
* Incorrect JAR version
* Dependency not included in the build
* Classpath configuration issue


## 11. What is the difference between final, finally, and finalize?


Although they sound similar, **`final`**, **`finally`**, and **`finalize()`** are completely different concepts in Java.

* **`final`**: A **keyword** used to restrict modification.
* **`finally`**: A **block** used with exception handling to execute code regardless of whether an exception occurs.
* **`finalize()`**: A **method** that was called by the **Garbage Collector** before object destruction. It is **deprecated** and should not be used in modern Java.


**Key Differences**

| **Feature**       | **`final`**                 | **`finally`**                   | **`finalize()`**                          |
| ----------------- | --------------------------- | ------------------------------- | ----------------------------------------- |
| **Type**          | Keyword                     | Block                           | Method                                    |
| **Purpose**       | Prevent modification        | Execute cleanup code            | Perform cleanup before object destruction |
| **Used With**     | Variables, methods, classes | `try-catch` blocks              | Objects and Garbage Collector             |
| **Execution**     | At compile time             | Always runs after `try`/`catch` | Called by GC (not guaranteed)             |
| **Current Usage** | Commonly used               | Commonly used                   | **Deprecated** (avoid using)              |

**How It Works**

**1. `final`**

* A **`final` variable** cannot be reassigned.
* A **`final` method** cannot be overridden.
* A **`final` class** cannot be inherited.

```java id="rzm81o"
final int age = 25;
// age = 30; // Compilation Error

final class Animal {
}

// class Dog extends Animal {} // Compilation Error
```

**2. `finally`**

* The `finally` block executes whether an exception occurs or not.
* Commonly used to close files, database connections, or release resources.

```java id="0oxh3i"
try {
    int result = 10 / 2;
} catch (Exception e) {
    System.out.println("Exception occurred");
} finally {
    System.out.println("Cleanup code always executes");
}
```

**3. `finalize()`**

* `finalize()` was invoked by the **Garbage Collector** before reclaiming an object's memory.
* Its execution was **not guaranteed**, and it caused performance and reliability issues.
* It is **deprecated from Java 9** and should be replaced with **`try-with-resources`** or explicit cleanup methods.

```java id="jlwmau"
class Test {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize called");
    }
}
```


**When to Use**

| **Scenario**                                  | **Use**                                                                           |
| --------------------------------------------- | --------------------------------------------------------------------------------- |
| Constant value or read-only variable          | **`final`**                                                                       |
| Prevent overriding or inheritance             | **`final`**                                                                       |
| Close files, sockets, or database connections | **`finally`**                                                                     |
| Object cleanup before GC                      | **Do not use `finalize()`; use `try-with-resources` or explicit cleanup instead** |

**Code Example**

```java id="1c9p5g"
public class Demo {

    final int MAX = 100;

    public static void main(String[] args) {
        try {
            System.out.println("Inside try block");
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
```

**Easy Way to Remember**

* **`final` = Cannot Change**
* **`finally` = Always Executes**
* **`finalize()` = Garbage Collector Cleanup (Deprecated)**


**Common Interview Follow-up Questions**

**1. Can a `final` variable be changed?**

**No.** Once assigned, it cannot be reassigned.

**2. Does the `finally` block always execute?**

**Yes**, except in rare cases such as calling **`System.exit()`** or when the JVM terminates unexpectedly.

**3. Can a `final` method be overridden?**

**No.** A **`final`** method cannot be overridden by a subclass.

**4. Why is `finalize()` deprecated?**

Because its execution is **unpredictable**, may never occur, and it can negatively impact performance. Modern Java recommends **`try-with-resources`** and **`AutoCloseable`** instead.

**5. What is the best alternative to `finalize()`?**

Use **`try-with-resources`** with classes implementing **`AutoCloseable`** or **`Closeable`** for reliable resource management.



## 0. **What is exception wrapping?**

**Exception wrapping** is the practice of **catching one exception and throwing another**, while preserving the **original exception** as the **cause**.

It is commonly used to:

* Hide **low-level implementation details**.
* Throw a **more meaningful exception** at a higher layer.
* Preserve the **original exception** for debugging.

**Key Features:**

1. Preserves the **root cause** of the error.
2. Provides a **higher-level, meaningful exception**.
3. Improves **abstraction** between application layers.
4. Uses the **exception constructor** that accepts a **cause**.

**Example:**

```java id="aqpsuz"
public void readData() {
    try {
        // Simulate a low-level exception
        throw new java.io.IOException("File not found");
    } catch (java.io.IOException e) {
        throw new RuntimeException("Failed to read data", e);
    }
}
```

**Retrieve the Original Cause:**

```java id="qdk4my"
try {
    readData();
} catch (RuntimeException e) {
    System.out.println(e.getMessage());          // Failed to read data
    System.out.println(e.getCause().getMessage()); // File not found
}
```

**When to Use:**

* Convert **low-level exceptions** into **business exceptions**.
* Hide implementation details between **layers** (DAO → Service → Controller).
* Add more context while **preserving the original exception**.



## 0. **Why shouldn't you swallow exceptions (empty `catch`)?**

**Swallowing an exception** means catching it but **doing nothing** with it, such as using an **empty `catch` block**. This is considered a **bad practice** because it hides errors and makes debugging difficult.

**Bad Example:**

```java id="fwpf9d"
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    // Do nothing
}
```

**Why is it a problem?**

1. **Hides errors** – The exception is ignored.
2. Makes **debugging difficult** because there is no error information.
3. Can leave the application in an **inconsistent state**.
4. May cause **unexpected behavior** later in the program.

**Good Example:**

```java id="qv9rtn"
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.err.println("Error: " + e.getMessage());
    // or log the exception using a logging framework
}
```

**Best Practices:**

* **Handle** the exception if you can recover.
* **Log** the exception if it needs to be recorded.
* **Rethrow** or **wrap** it if a higher layer should handle it.
* Avoid empty `catch` blocks unless there is a **well-justified reason**, and document why.



## 0. **Can you throw a checked exception from a method without `throws`?**

**No.** A method **cannot throw a checked exception** unless it either:

1. **Handles** it using a `try-catch` block, or
2. **Declares** it using the **`throws`** keyword.

Otherwise, the code **will not compile**.

**Incorrect Example (Compilation Error):**

```java id="egxqhh"
import java.io.IOException;

public void readFile() {
    throw new IOException("File not found"); // Compilation error
}
```

**Correct Example (`throws`):**

```java id="7hnb3t"
import java.io.IOException;

public void readFile() throws IOException {
    throw new IOException("File not found");
}
```

**Correct Example (`try-catch`):**

```java id="qjlwm2"
import java.io.IOException;

public void readFile() {
    try {
        throw new IOException("File not found");
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}
```

**Note:** You **can** throw an **unchecked exception** (a subclass of **`RuntimeException`**) without using `throws`.

```java id="rkp9qa"
public void validate(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Invalid age");
    }
}
```



## 0. **What happens if an exception also occurs in the `finally` block?**

If an exception is thrown in the **`finally`** block, it **overrides** any exception thrown in the `try` or `catch` block.

As a result:

* The **original exception is lost** (unless it is explicitly preserved).
* The exception from the **`finally`** block is the one that propagates to the caller.

**Example:**

```java id="3h4x8v"
public class Main {
    public static void main(String[] args) {
        try {
            throw new RuntimeException("Exception from try");
        } finally {
            throw new RuntimeException("Exception from finally");
        }
    }
}
```

**Output:**

```text id="c6r5ta"
Exception in thread "main" java.lang.RuntimeException: Exception from finally
```

The exception from the `try` block is **not propagated**.

**How Java 7+ Handles This (try-with-resources):**

With **try-with-resources**, if both the `try` block and `close()` throw exceptions:

* The exception from the **`try`** block becomes the **main exception**.
* The exception from `close()` is stored as a **suppressed exception**.

**Example:**

```java id="u2q9mk"
try (MyResource r = new MyResource()) {
    throw new Exception("Main exception");
}
```

You can retrieve suppressed exceptions using:

```java id="q8n2yb"
exception.getSuppressed();
```

**Best Practice:**

* Avoid throwing exceptions from a **`finally`** block.
* Use **try-with-resources** for automatic resource cleanup, as it preserves the original exception.



## 0. **What are suppressed exceptions?**

**Suppressed exceptions** are exceptions that occur during **resource cleanup** (such as in `close()`) when another exception has already been thrown in the `try` block.

Introduced in **Java 7**, they are mainly used with **try-with-resources** to **preserve the original exception**.

**How It Works:**

1. The **`try`** block throws an exception.
2. While closing the resource, **`close()`** also throws an exception.
3. The exception from the **`try`** block becomes the **main exception**.
4. The exception from **`close()`** is stored as a **suppressed exception**.

**Example:**

```java id="9m3l1w"
class MyResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        throw new Exception("Exception while closing");
    }
}

public class Main {
    public static void main(String[] args) {
        try (MyResource r = new MyResource()) {
            throw new Exception("Exception in try block");
        } catch (Exception e) {
            System.out.println("Main: " + e.getMessage());

            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + t.getMessage());
            }
        }
    }
}
```

**Output:**

```text id="r2m0yb"
Main: Exception in try block
Suppressed: Exception while closing
```

**Useful Methods:**

* **`getSuppressed()`** – Returns all suppressed exceptions.
* **`addSuppressed(Throwable)`** – Adds a suppressed exception manually.

**Why Are They Important?**

* Preserve the **original exception**.
* Prevent cleanup exceptions from **hiding the real cause**.
* Make **debugging easier**.



## 0. **Can you have multiple `catch` blocks for a single `try`?**

**Yes.** A single **`try`** block can have **multiple `catch` blocks** to handle **different types of exceptions**.

Java checks the `catch` blocks **from top to bottom**. The **first matching** `catch` block is executed, and the rest are skipped.

**Example:**

```java id="v8n3qy"
try {
    String s = null;
    System.out.println(s.length());
} catch (ArithmeticException e) {
    System.out.println("Arithmetic error");
} catch (NullPointerException e) {
    System.out.println("Null pointer error");
} catch (Exception e) {
    System.out.println("General exception");
}
```

**Output:**

```text id="j5r9cb"
Null pointer error
```

**Rules:**

1. You can have **multiple `catch` blocks** for one `try`.
2. Catch **more specific exceptions first**.
3. Catch **more general exceptions** (like `Exception`) **last**.
4. Otherwise, the code **will not compile** because later `catch` blocks become unreachable.

**Incorrect Example:**

```java id="w7m1xp"
try {
    // code
} catch (Exception e) {
    // Handles all exceptions
} catch (ArithmeticException e) { // Compilation error
    // Unreachable code
}
```



## 0. **What is multi-catch?**

**Multi-catch** is a Java feature (introduced in **Java 7**) that allows **one `catch` block to handle multiple exception types**.

It reduces **duplicate code** when the handling logic is the same for different exceptions.

**Syntax:**

```java id="8b1xqt"
catch (ExceptionType1 | ExceptionType2 e) {
    // Handle both exceptions
}
```

**Example:**

```java id="u0q6ps"
try {
    String s = null;
    System.out.println(s.length());
} catch (NullPointerException | ArithmeticException e) {
    System.out.println("Exception occurred: " + e.getMessage());
}
```

**Rules:**

1. Separate exception types with the **`|`** operator.
2. The exception types **must not** have a **parent-child relationship**.
3. The exception variable (`e`) is **implicitly final**, so it **cannot be reassigned**.

**Incorrect Example:**

```java id="0xpkdh"
try {
    // code
} catch (Exception | IOException e) { // Compilation error
}
```

This is invalid because **`IOException`** is a subclass of **`Exception`**.

**When to Use:**

* When **multiple exceptions** require the **same handling logic**.
* To make the code **cleaner** and avoid duplicate `catch` blocks.



## 0. **In what order should you arrange `catch` blocks?**

`catch` blocks should be arranged from **most specific** exception to **most general** exception.

Java checks the `catch` blocks **from top to bottom** and executes the **first matching** one.

If a **general exception** is placed before a **specific exception**, the specific `catch` block becomes **unreachable**, causing a **compilation error**.

**Correct Order:**

```java id="9n5mzk"
try {
    String s = null;
    System.out.println(s.length());
} catch (NullPointerException e) {
    System.out.println("Null pointer");
} catch (RuntimeException e) {
    System.out.println("Runtime exception");
} catch (Exception e) {
    System.out.println("General exception");
}
```

**Incorrect Order:**

```java id="m7x4qc"
try {
    String s = null;
    System.out.println(s.length());
} catch (Exception e) {
    System.out.println("General exception");
} catch (NullPointerException e) { // Compilation error
    System.out.println("Null pointer");
}
```

**Why?**

* `Exception` catches **all exceptions**, including `NullPointerException`.
* Therefore, the second `catch` block can **never be reached**.

**Rule to Remember:**

1. **Most specific** exceptions first.
2. **More general** exceptions next.
3. **`Exception`** (or **`Throwable`**, if used) **last**.



## 0. **Can you re-throw an exception?**

**Yes.** You can **re-throw an exception** after catching it using the **`throw`** keyword.

This is useful when:

* You want to **log** the exception first.
* You want a **higher layer** to handle it.
* You want to **wrap** it in another exception.

**Example (Re-throw the Same Exception):**

```java id="5q8wzc"
public void process() throws Exception {
    try {
        throw new Exception("Something went wrong");
    } catch (Exception e) {
        System.out.println("Logging: " + e.getMessage());
        throw e; // Re-throw the same exception
    }
}
```

**Example (Wrap and Re-throw):**

```java id="9j2lkm"
try {
    throw new java.io.IOException("File not found");
} catch (java.io.IOException e) {
    throw new RuntimeException("Failed to process file", e);
}
```

**Why Re-throw an Exception?**

1. **Log** the exception before propagating it.
2. Let a **higher layer** handle it.
3. Add **more context** by wrapping it in another exception.
4. Preserve the **original cause** for debugging.


## 0. **What is exception chaining?**

**Exception chaining** is the process of **linking one exception to another** by storing the **original exception as the cause** of a new exception.

It helps preserve the **root cause** while providing a **higher-level, more meaningful exception**.

**Key Features:**

1. Preserves the **original exception**.
2. Adds **more context** for higher application layers.
3. Improves **debugging**.
4. Uses the exception constructor that accepts a **cause**.

**Example:**

```java id="w4n7kp"
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            try {
                throw new IOException("File not found");
            } catch (IOException e) {
                throw new RuntimeException("Failed to process file", e);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());              // Failed to process file
            System.out.println(e.getCause().getMessage());   // File not found
        }
    }
}
```

**Useful Methods:**

* **`getCause()`** – Returns the original exception.
* **`initCause()`** – Sets the cause (rarely used; constructors are preferred).

**When to Use:**

* Convert **low-level exceptions** into **business exceptions**.
* Add meaningful context between **DAO**, **Service**, and **Controller** layers.
* Preserve the **root cause** for troubleshooting.

**Exception Chaining vs Exception Wrapping:**

| **Exception Chaining**                                               | **Exception Wrapping**                                                        |
| -------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| Links a new exception to the original exception using the **cause**. | Catches one exception and throws another, usually while preserving the cause. |
| Focuses on maintaining the **cause relationship**.                   | Focuses on converting one exception into a more appropriate one.              |
| Uses **`getCause()`** to access the original exception.              | Typically implemented using **exception chaining**.                           |




# ✅ 07. Java Collections Framework

## 0. What is Java Collections?

**Java Collections Framework (JCF)** is a set of **classes** and **interfaces** used to store, manage, and manipulate groups of objects dynamically.

Instead of using arrays with fixed size, Collections provide flexible data structures such as **List**, **Set**, **Queue**, and **Map**.

**Key Features**

* Dynamic size
* Ready-made data structures
* Supports searching, sorting, and filtering
* Improves code reusability
* Provides high-performance implementations


**Why Use Collections?**

* To store multiple objects efficiently
* To avoid fixed-size limitations of arrays
* To perform common operations easily
* To improve readability and maintainability

**When to Use Which Collection?**

* Use **HashMap** for **fast key-value lookup**.
* Use **HashSet** for **unique elements**.
* Use **ArrayList** for **frequent reads**.
* Use **LinkedList** for **frequent insertions/deletions**.
* Use **ConcurrentHashMap** in **multithreaded applications**.
* Use **TreeMap** when **sorted data** is required.
* Use **LinkedHashMap** when **insertion order** must be preserved.
* Use **PriorityQueue** when processing items based on **priority**.
* Use **WeakHashMap** for **memory-sensitive caching**.
* Use **IdentityHashMap** only when **reference equality (`==`)** is required instead of **object equality (`equals()`)**.


**Example**

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("John");
        names.add("Alice");
        names.add("Bob");

        System.out.println(names);
    }
}
```

**Output:**

```java
[John, Alice, Bob]
```

**Important Interfaces**

```java
Collection
   ├── List
   ├── Set
   └── Queue

Map (separate hierarchy)
```

Here’s a cleaner and corrected version of your table:

```
Interface    | Common Classes(implementations)        | Description----------|----------------------------------------|-----------------------------------------
List         | ArrayList, LinkedList                  | Ordered collection that allows duplicates
Set          | HashSet, TreeSet, LinkedHashSet        | Collection of unique elements (no duplicates)
Map          | HashMap, TreeMap, LinkedHashMap        | Stores key-value pairs with unique keys
```

ArrayList : add(), get(), set(), and remove() to manage your list of elements.
LinkedList

**What operations does the Collection interface support?**

The **`Collection`** interface provides common operations such as **adding**, **removing**, **searching**, **iterating**, **checking size**, **clearing**, and **converting** collections, making it the **root interface** for most collection types in Java.


## 0. Main Interfaces of the Collection Framework?

The **Java Collection Framework** provides a set of **interfaces** for storing and manipulating groups of objects. Each interface is designed for a specific type of data structure.

**Main Interfaces**

1. **`Collection`** – The **root interface** of the Collection Framework. It represents a group of objects.
2. **`List`** – Stores **ordered** elements, allows **duplicates**, and supports **index-based access**.
3. **`Set`** – Stores **unique** elements and does **not allow duplicates**.
4. **`Queue`** – Stores elements for processing, usually in **FIFO (First In, First Out)** order.
5. **`Deque`** – A **double-ended queue** that allows insertion and removal from **both ends**.
6. **`Map`** – Stores data as **key-value pairs**. **Keys are unique**, while **values can be duplicated**. *(Note: `Map` is part of the Collection Framework but does **not** extend the `Collection` interface.)*

**Common Implementations**

| **Interface** | **Common Implementations**                                                 |
| ------------- | -------------------------------------------------------------------------- |
| **`List`**    | **`ArrayList`**, **`LinkedList`**, **`Vector`**                            |
| **`Set`**     | **`HashSet`**, **`LinkedHashSet`**, **`TreeSet`**                          |
| **`Queue`**   | **`PriorityQueue`**, **`LinkedList`**                                      |
| **`Deque`**   | **`ArrayDeque`**, **`LinkedList`**                                         |
| **`Map`**     | **`HashMap`**, **`LinkedHashMap`**, **`TreeMap`**, **`ConcurrentHashMap`** |

**Example**

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<Integer, String> map = new HashMap<>();

        list.add("Java");
        set.add("Spring");
        queue.offer("Docker");
        map.put(1, "Kubernetes");
    }
}
```

## 1. Java Collections Use Cases?

| **Requirement**                         | **Best Collection**   | **Why?**                                                                | **Mostly Used In**                                                                    |
| --------------------------------------- | --------------------- | ----------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| **Fast search/access by key**           | **HashMap**           | Average **O(1)** lookup using hashing                                   | **Caching**, **REST APIs**, **Lookup Tables**, **Configuration Data**                 |
| **Fast unique element lookup**          | **HashSet**           | Average **O(1)** lookup, stores only unique elements                    | **Removing Duplicates**, **Validation**, **Permission Checks**                        |
| **Fast indexed access**                 | **ArrayList**         | **O(1)** random access using index                                      | **UI Lists**, **Data Retrieval**, **API Responses**, **Read-heavy Applications**      |
| **Thread-safe fast access**             | **ConcurrentHashMap** | High-performance concurrent reads/writes without locking the entire map | **Multithreaded Applications**, **Caching**, **Session Management**                   |
| **Sorted key-value storage**            | **TreeMap**           | Keys are automatically stored in **sorted order**                       | **Leaderboards**, **Ranking Systems**, **Sorted Reports**                             |
| **Maintain insertion order**            | **LinkedHashMap**     | Preserves insertion order while providing fast lookup                   | **LRU Cache**, **Ordered APIs**, **Configuration Processing**                         |
| **Fast insert/delete at beginning/end** | **LinkedList**        | Efficient insertion/removal without shifting elements                   | **Queue**, **Deque**, **Stack**, **Undo/Redo Operations**                             |
| **Priority-based processing**           | **PriorityQueue**     | Automatically processes elements by **priority**                        | **Task Scheduling**, **Job Processing**, **CPU Scheduling**, **Dijkstra's Algorithm** |
| **Auto-remove unused keys**             | **WeakHashMap**       | Keys are automatically removed by the **Garbage Collector**             | **Memory-sensitive Cache**, **Metadata Storage**                                      |
| **Reference equality comparison**       | **IdentityHashMap**   | Compares keys using **`==`** instead of **`equals()`**                  | **Framework Internals**, **Object Tracking**, **Serialization**                       |



**Quick Complexity Overview**

| Collection      | Search     | Insert         | Ordered               |
| --------------- | ---------- | -------------- | --------------------- |
| `HashMap`       | O(1)       | O(1)           | No                    |
| `TreeMap`       | O(log n)   | O(log n)       | Yes (sorted)          |
| `LinkedHashMap` | O(1)       | O(1)           | Yes (insertion order) |
| `ArrayList`     | O(1) index | O(1) append    | Yes                   |
| `LinkedList`    | O(n)       | O(1) insertion | Yes                   |
| `HashSet`       | O(1)       | O(1)           | No                    |
| `PriorityQueue` | O(log n)   | O(log n)       | Priority order        |



## 2. What is the difference between ArrayList and LinkedList?


Both **`ArrayList`** and **`LinkedList`** are implementations of the **`List`** interface in the Java Collections Framework, but they use different internal data structures.

* **`ArrayList`** uses a **dynamic array**.
* **`LinkedList`** uses a **doubly linked list**.


**Key Differences**

| **Feature**                      | **ArrayList**           | **LinkedList**                        |
| -------------------------------- | ----------------------- | ------------------------------------- |
| **Internal Structure**           | Dynamic Array           | Doubly Linked List                    |
| **Access by Index (`get`)**      | **Fast - O(1)**         | **Slow - O(n)**                       |
| **Insertion/Deletion at End**    | Fast - O(1) (amortized) | Fast - O(1)                           |
| **Insertion/Deletion in Middle** | Slow - O(n)             | Faster - O(1) after reaching the node |
| **Memory Usage**                 | Less memory             | More memory (extra pointers)          |
| **Implements**                   | `List`                  | `List` and `Deque`                    |
| **Best For**                     | Frequent reading        | Frequent insertion/deletion           |

**How It Works**

* **`ArrayList`**

  * Stores elements in a **contiguous array**.
  * If the array becomes full, it automatically creates a larger array and copies existing elements.

* **`LinkedList`**

  * Stores each element in a **node**.
  * Each node contains the **data**, a **reference to the next node**, and a **reference to the previous node**.


**When to Use**

| **Scenario**                     | **Best Choice** |
| -------------------------------- | --------------- |
| Frequent element access by index | **ArrayList**   |
| Frequent insertion/deletion      | **LinkedList**  |
| Large number of read operations  | **ArrayList**   |
| Queue or Deque implementation    | **LinkedList**  |

**Code Example**

```java id="n5r7uk"
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Spring");

        List<String> linkedList = new LinkedList<>();
        linkedList.add("Java");
        linkedList.add("Spring");

        System.out.println(arrayList.get(1));   // Fast access
        System.out.println(linkedList.get(1));  // Slower access
    }
}
```

**Performance Comparison**

| **Operation**                         | **ArrayList**    | **LinkedList**       |
| ------------------------------------- | ---------------- | -------------------- |
| **Add at End**                        | O(1) (amortized) | O(1)                 |
| **Add/Delete at Beginning or Middle** | O(n)             | O(1) after traversal |
| **Get by Index**                      | O(1)             | O(n)                 |
| **Search**                            | O(n)             | O(n)                 |

**Key Features**

* **`ArrayList`**

  * Fast random access.
  * Better cache locality.
  * Lower memory overhead.

* **`LinkedList`**

  * Efficient insertion and deletion.
  * Can act as both a **List** and a **Queue/Deque**.
  * No need to resize an internal array.


## 3. What is the difference between HashMap and TreeMap?


Both **`HashMap`** and **`TreeMap`** are implementations of the **`Map`** interface used to store **key-value pairs**, but they differ in how they store and organize data.

* **`HashMap`** stores entries using a **hash table**.
* **`TreeMap`** stores entries using a **Red-Black Tree** (a self-balancing binary search tree).


**Key Differences**

| **Feature**                                  | **HashMap**                   | **TreeMap**                    |
| -------------------------------------------- | ----------------------------- | ------------------------------ |
| **Internal Structure**                       | Hash Table                    | Red-Black Tree                 |
| **Order of Keys**                            | No ordering                   | Keys are automatically sorted  |
| **Time Complexity (`put`, `get`, `remove`)** | **O(1)** average              | **O(log n)**                   |
| **Null Keys**                                | Allows **one** `null` key     | Does **not** allow `null` keys |
| **Null Values**                              | Allows multiple `null` values | Allows multiple `null` values  |
| **Implements**                               | `Map`                         | `NavigableMap`, `SortedMap`    |
| **Best For**                                 | Fast lookup and insertion     | Sorted data and range queries  |

**How It Works**

* **`HashMap`**

  * Calculates the **`hashCode()`** of the key.
  * Uses the hash value to find the appropriate bucket.
  * If multiple keys map to the same bucket, it uses **`equals()`** to find the correct entry.

* **`TreeMap`**

  * Stores keys in a **Red-Black Tree**.
  * Automatically keeps keys in **ascending order** (or custom order using a `Comparator`).


**When to Use**

| **Scenario**                         | **Best Choice** |
| ------------------------------------ | --------------- |
| Fast insert and lookup               | **HashMap**     |
| Maintain sorted order of keys        | **TreeMap**     |
| Range queries (`headMap`, `tailMap`) | **TreeMap**     |
| General-purpose key-value storage    | **HashMap**     |

**Code Example**

```java id="9ifxrf"
import java.util.HashMap;
import java.util.TreeMap;

public class Demo {
    public static void main(String[] args) {

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(3, "C");
        hashMap.put(1, "A");
        hashMap.put(2, "B");

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "C");
        treeMap.put(1, "A");
        treeMap.put(2, "B");

        System.out.println(hashMap); // Order not guaranteed
        System.out.println(treeMap); // {1=A, 2=B, 3=C}
    }
}
```

**Performance Comparison**

| **Operation**    | **HashMap**  | **TreeMap** |
| ---------------- | ------------ | ----------- |
| **`put()`**      | O(1) average | O(log n)    |
| **`get()`**      | O(1) average | O(log n)    |
| **`remove()`**   | O(1) average | O(log n)    |
| **Key Ordering** | No           | Yes         |

**Key Features**

* **`HashMap`**

  * Very fast access.
  * Unordered collection.
  * Uses **`hashCode()`** and **`equals()`** internally.

* **`TreeMap`**

  * Maintains keys in **sorted order**.
  * Supports **`firstKey()`**, **`lastKey()`**, **`headMap()`**, and **`tailMap()`**.
  * Uses **natural ordering** or a custom **`Comparator`**.


## 4. What is the difference between HashMap Hashtable?


Both **`HashMap`** and **`Hashtable`** are implementations of the **`Map`** interface used to store **key-value pairs**, but they differ mainly in **thread safety**, **performance**, and **handling of `null` values**.


**Key Differences**

| **Feature**                  | **HashMap**                   | **Hashtable**                                     |
| ---------------------------- | ----------------------------- | ------------------------------------------------- |
| **Thread Safety**            | Not synchronized              | Synchronized                                      |
| **Performance**              | Faster                        | Slower due to synchronization                     |
| **Null Key**                 | Allows **one** `null` key     | Not allowed                                       |
| **Null Values**              | Allows multiple `null` values | Not allowed                                       |
| **Introduced In**            | Java 1.2                      | Java 1.0                                          |
| **Inheritance**              | Extends `AbstractMap`         | Extends `Dictionary`                              |
| **Iterator Support**         | Uses `Iterator`               | Uses `Enumeration` and `Iterator`                 |
| **Recommended for New Code** | Yes                           | No (prefer `ConcurrentHashMap` for thread safety) |

**How It Works**

* **`HashMap`**

  * Uses a **hash table** and stores data based on the **`hashCode()`** of the key.
  * Not thread-safe, so multiple threads can cause inconsistent results without external synchronization.

* **`Hashtable`**

  * Also uses a **hash table**, but all its methods are **synchronized**.
  * Only one thread can access it at a time, making it thread-safe but reducing performance.


**When to Use**

| **Scenario**                          | **Best Choice**       |
| ------------------------------------- | --------------------- |
| Single-threaded application           | **HashMap**           |
| High-performance map operations       | **HashMap**           |
| Legacy code requiring synchronization | **Hashtable**         |
| Modern multi-threaded application     | **ConcurrentHashMap** |

**Code Example**

```java id="bzhv9w"
import java.util.HashMap;
import java.util.Hashtable;

public class Demo {
    public static void main(String[] args) {

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Java");
        hashMap.put(null, "Spring"); // Allowed

        Hashtable<Integer, String> hashtable = new Hashtable<>();
        hashtable.put(1, "Java");
        // hashtable.put(null, "Spring"); // Throws NullPointerException

        System.out.println(hashMap);
        System.out.println(hashtable);
    }
}
```

**Performance Comparison**

| **Operation**     | **HashMap**  | **Hashtable**                                |
| ----------------- | ------------ | -------------------------------------------- |
| **`put()`**       | O(1) average | O(1) average (slower due to synchronization) |
| **`get()`**       | O(1) average | O(1) average (slower due to synchronization) |
| **Thread Safety** | No           | Yes                                          |

**Key Features**

* **`HashMap`**

  * Fast and efficient.
  * Allows one `null` key and multiple `null` values.
  * Not synchronized.

* **`Hashtable`**

  * Thread-safe because methods are synchronized.
  * Does not allow `null` keys or values.
  * Considered a **legacy class**.


## 5. How does HashMap work internally?


A **`HashMap`** is a data structure in Java that stores data as **key-value pairs**. Internally, it uses a **hash table** to provide very fast **insertion**, **deletion**, and **lookup** operations.


**How It Works**

**1. Calculate `hashCode()`**

* When you call `put(key, value)`, Java calls the key's **`hashCode()`** method.

```java id="drgqhj"
int hash = key.hashCode();
```

**2. Find the Bucket Index**

* The hash value is converted into an array index (bucket index).

```java id="v7g0w7"
index = hash % arraySize;
```

(Java internally uses a more optimized calculation.)

**3. Store the Entry**

* If the bucket is empty, the key-value pair is stored there.
* If the bucket already contains data (**hash collision**), Java stores multiple entries in the same bucket.

**4. Handle Hash Collisions**

* In **Java 7 and earlier**, collisions are handled using a **Linked List**.
* In **Java 8 and later**, if the number of entries in a bucket exceeds a threshold (default **8**), the linked list is converted into a **Red-Black Tree** for faster searching.

**5. Retrieve the Value**

* When `get(key)` is called:

  1. Java calculates the key's **`hashCode()`**.
  2. Finds the correct bucket.
  3. Uses **`equals()`** to compare keys if multiple entries exist in the bucket.
  4. Returns the matching value.

**Internal Flow**

```text id="x6f7fh"
put(key, value)
      │
      ▼
Call key.hashCode()
      │
      ▼
Calculate Bucket Index
      │
      ▼
Bucket Empty?
   Yes      No
    │        │
 Store    Check equals()
 Object      │
             ▼
      Add to Linked List
      or Red-Black Tree
```

**Key Features**

* Stores data as **Key-Value Pairs**.
* Uses **`hashCode()`** and **`equals()`** internally.
* Average **O(1)** time complexity for `put()` and `get()`.
* Handles collisions using **Linked List** or **Red-Black Tree**.
* Allows **one `null` key** and multiple `null` values.
* Not thread-safe.


**When to Use**

| **Scenario**                          | **Use `HashMap`?**          |
| ------------------------------------- | --------------------------- |
| Fast key-value lookup                 | **Yes**                     |
| Data order is not important           | **Yes**                     |
| Frequent insert and search operations | **Yes**                     |
| Need sorted keys                      | **No, use `TreeMap`**       |
| Multi-threaded access                 | **Use `ConcurrentHashMap`** |

**Role of `equals()` and `hashCode()`**

* **`hashCode()`** determines the **bucket**.
* **`equals()`** identifies the **exact key** inside the bucket.

This is why **if you override `equals()`, you must also override `hashCode()`**.

**Code Example**

```java id="xk9yau"
import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();

        map.put(101, "Alice");
        map.put(102, "Bob");

        System.out.println(map.get(101)); // Alice
    }
}
```

**Time Complexity**

| **Operation**  | **Average Case** | **Worst Case**                 |
| -------------- | ---------------- | ------------------------------ |
| **`put()`**    | O(1)             | O(log n) (after treeification) |
| **`get()`**    | O(1)             | O(log n) (after treeification) |
| **`remove()`** | O(1)             | O(log n) (after treeification) |



## 5. Difference between HashSet, LinkedHashSet and TreeSet?

All three implement the **`Set`** interface and store **unique elements**, but they differ in **ordering**, **performance**, and **implementation**.

| **`HashSet`**                                               | **`LinkedHashSet`**                                | **`TreeSet`**                        |
| ----------------------------------------------------------- | -------------------------------------------------- | ------------------------------------ |
| Uses a **Hash Table**.                                      | Uses a **Hash Table + Linked List**.               | Uses a **Red-Black Tree**.           |
| **No insertion order** is maintained.                       | Maintains **insertion order**.                     | Stores elements in **sorted order**. |
| **Fastest** for add, remove, and search (**O(1)** average). | Slightly slower than `HashSet` (**O(1)** average). | Slower operations (**O(log n)**).    |
| Allows **one `null`** element.                              | Allows **one `null`** element.                     | Does **not allow `null`** elements.  |

**Example**

```java id="t3n8yu"
import java.util.*;

public class Demo {
    public static void main(String[] args) {

        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(30);
        hashSet.add(10);
        hashSet.add(20);

        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(30);
        linkedHashSet.add(10);
        linkedHashSet.add(20);

        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(20);

        System.out.println(hashSet);       // Order not guaranteed
        System.out.println(linkedHashSet); // [30, 10, 20]
        System.out.println(treeSet);       // [10, 20, 30]
    }
}
```

**When to Use**

* Use **`HashSet`** when you need **fast performance** and **order does not matter**.
* Use **`LinkedHashSet`** when you need to **preserve insertion order**.
* Use **`TreeSet`** when you need **automatically sorted** elements.


## 6. What is TreeSet and how does it work internally


**`TreeSet`** is a **Set** implementation that stores **unique elements** in **sorted order**. Internally, it uses a **Red-Black Tree**, which is a **self-balancing Binary Search Tree (BST)**.

**How It Works Internally**

1. When an element is added, **`TreeSet`** inserts it into a **Red-Black Tree**.
2. The tree automatically keeps elements **sorted** using their **natural ordering** or a **`Comparator`**.
3. If a duplicate element is added, it is **ignored** because a **Set** does not allow duplicates.
4. After every insertion or deletion, the **Red-Black Tree** rebalances itself to maintain efficient performance.

**Key Features**

* Stores **unique** elements.
* Maintains **sorted order**.
* Uses a **Red-Black Tree** internally.
* **Does not allow `null`** elements.
* **Add**, **Remove**, and **Search** operations take **O(log n)** time.

**Example**

```java
import java.util.TreeSet;

public class Demo {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();

        set.add(30);
        set.add(10);
        set.add(20);
        set.add(10); // Duplicate

        System.out.println(set);
    }
}
```

**Output**

```text
[10, 20, 30]
```

The elements are automatically **sorted**, and the duplicate **`10`** is ignored.

**When to Use**

* When you need **unique** elements.
* When data must always remain **sorted**.
* When you need operations like **`first()`**, **`last()`**, **`higher()`**, **`lower()`**, **`ceiling()`**, and **`floor()`**.


## 6. What is hash collision and how is it handled?

A **Hash Collision** occurs when **two different keys generate the same hash code or map to the same bucket** in a **`HashMap`**.


**How It Works**

1. Java calls the key's **`hashCode()`**.
2. It calculates the **bucket index**.
3. If the bucket is empty, the entry is stored there.
4. If another key maps to the same bucket, a **hash collision** occurs.
5. Java stores both entries in the same bucket and uses **`equals()`** to identify the correct key during retrieval.

**Example**

Suppose two different keys produce the same bucket index:

```text id="j8ed2u"
Key1.hashCode() ──► Bucket 3
Key2.hashCode() ──► Bucket 3
```

Both entries are stored in **Bucket 3**, creating a **hash collision**.

**Key Features**

* Occurs when multiple keys map to the **same bucket**.
* Handled internally by **Linked List** or **Red-Black Tree**.
* **`hashCode()`** finds the bucket, and **`equals()`** finds the exact key.
* Does not cause data loss because all colliding entries are stored.

**Collision Handling in Java**

| **Java Version**       | **Collision Handling**                                                    |
| ---------------------- | ------------------------------------------------------------------------- |
| **Java 7 and Earlier** | Linked List                                                               |
| **Java 8 and Later**   | Linked List, converted to **Red-Black Tree** if bucket size exceeds **8** |

The **Red-Black Tree** improves worst-case search performance from **O(n)** to **O(log n)**.


**When Does It Happen?**

* Different objects produce the same **`hashCode()`**.
* The calculated bucket index for different keys becomes the same.
* Poorly implemented **`hashCode()`** methods increase collisions.

**Code Example**

```java id="zqzhx7"
import java.util.HashMap;

class Employee {
    int id;

    Employee(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return 1; // Same hash code for all objects
    }

    @Override
    public boolean equals(Object obj) {
        return this.id == ((Employee) obj).id;
    }
}

public class Demo {
    public static void main(String[] args) {
        HashMap<Employee, String> map = new HashMap<>();

        map.put(new Employee(1), "Alice");
        map.put(new Employee(2), "Bob");

        System.out.println(map.size()); // 2
    }
}
```

In this example, both objects have the **same hash code**, causing a **hash collision**, but `HashMap` stores both entries correctly using `equals()`.

**How to Reduce Hash Collisions**

* Override **`hashCode()`** properly.
* Use **`Objects.hash()`** for generating good hash values.
* Ensure that fields used in **`equals()`** are also used in **`hashCode()`**.

**Easy Way to Remember**

* **`hashCode()` = Finds the Bucket**
* **Same Bucket = Hash Collision**
* **`equals()` = Finds the Correct Key**
* **Java 8+ = Linked List + Red-Black Tree**


## 7. What is the difference between Comparable and Comparator?



Both **`Comparable`** and **`Comparator`** are interfaces in Java used for **sorting objects**, but they differ in where and how the sorting logic is defined.

* **`Comparable`** defines the **natural/default ordering** of an object.
* **`Comparator`** defines a **custom ordering** and allows multiple sorting strategies.



**Key Differences**

| **Feature**                  | **Comparable**                     | **Comparator**                       |
| ---------------------------- | ---------------------------------- | ------------------------------------ |
| **Package**                  | `java.lang`                        | `java.util`                          |
| **Method**                   | `compareTo()`                      | `compare()`                          |
| **Sorting Type**             | Natural (default) ordering         | Custom ordering                      |
| **Modification Required**    | Requires modifying the class       | No need to modify the class          |
| **Number of Sorting Logics** | One                                | Multiple                             |
| **Used By**                  | `Collections.sort()` automatically | `Collections.sort(list, comparator)` |

**How It Works**

* **`Comparable`**

  * The class itself implements the **`Comparable`** interface.
  * Sorting logic is written inside the **`compareTo()`** method.

* **`Comparator`**

  * A separate class or lambda expression implements the **`Comparator`** interface.
  * Sorting logic is written inside the **`compare()`** method.

**When to Use**

| **Scenario**                           | **Best Choice** |
| -------------------------------------- | --------------- |
| Sort employees by ID (default order)   | **Comparable**  |
| Sort employees by Name, Salary, or Age | **Comparator**  |
| Cannot modify the original class       | **Comparator**  |
| Need only one natural ordering         | **Comparable**  |

**Code Example Using `Comparable`**

```java id="zz2vfh"
import java.util.*;

class Employee implements Comparable<Employee> {
    int id;

    Employee(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Employee e) {
        return this.id - e.id;
    }
}

public class Demo {
    public static void main(String[] args) {
        List<Employee> list = Arrays.asList(
            new Employee(3),
            new Employee(1),
            new Employee(2)
        );

        Collections.sort(list);

        for (Employee e : list) {
            System.out.println(e.id);
        }
    }
}
```

**Code Example Using `Comparator`**

```java id="glivuq"
import java.util.*;

class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class Demo {
    public static void main(String[] args) {

        List<Employee> list = Arrays.asList(
            new Employee(2, "Bob"),
            new Employee(1, "Alice")
        );

        Collections.sort(list, (e1, e2) -> e1.name.compareTo(e2.name));

        for (Employee e : list) {
            System.out.println(e.name);
        }
    }
}
```

**Method Signatures**

```java id="1c13x7"
// Comparable
public interface Comparable<T> {
    int compareTo(T o);
}

// Comparator
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

**Key Features**

* **`Comparable`**

  * Defines **natural ordering**.
  * Sorting logic is part of the class itself.
  * Uses **`compareTo()`**.

* **`Comparator`**

  * Defines **custom ordering**.
  * Supports multiple sorting rules.
  * Uses **`compare()`** and works well with **lambda expressions**.



## 8. What is WeakHashMap, IdentityHashMap, LinkedHashMap, PriorityQueue?

**`WeakHashMap`**



A **`WeakHashMap`** is a special implementation of the **`Map`** interface where the **keys are stored as weak references**. If a key is no longer referenced anywhere else, the **Garbage Collector (GC)** can automatically remove the entry from the map.


**Key Features**

* Keys use **weak references**.
* Entries are automatically removed by the **Garbage Collector**.
* Allows **one `null` key** and multiple `null` values.
* Not synchronized.

**How It Works**

* If a key object is no longer used outside the map, GC clears the key and its associated entry.


**When to Use**

* Cache implementations.
* Memory-sensitive applications.

**Code Example**

```java id="vslx4n"
import java.util.WeakHashMap;

WeakHashMap<String, String> map = new WeakHashMap<>();
String key = new String("A");

map.put(key, "Apple");
key = null;

System.gc(); // Entry may be removed by GC
```

---

**`IdentityHashMap`**



An **`IdentityHashMap`** is a `Map` implementation that compares keys using **`==` (reference equality)** instead of **`equals()`**.


**Key Features**

* Uses **reference equality (`==`)**.
* Does not use **`equals()`** for key comparison.
* Uses **`System.identityHashCode()`** instead of the object's `hashCode()`.

**How It Works**

```java id="2zw17k"
String s1 = new String("Java");
String s2 = new String("Java");
```

Although `s1.equals(s2)` is `true`, `s1 == s2` is `false`, so `IdentityHashMap` treats them as different keys.


**When to Use**

* Object graph processing.
* Maintaining object identity.

**Code Example**

```java id="tb4jqo"
import java.util.IdentityHashMap;

IdentityHashMap<String, Integer> map = new IdentityHashMap<>();

map.put(new String("Java"), 1);
map.put(new String("Java"), 2);

System.out.println(map.size()); // 2
```

---

**`LinkedHashMap`**



A **`LinkedHashMap`** is a `HashMap` implementation that maintains the **insertion order** (or access order) of elements by using a **doubly linked list** along with a hash table.


**Key Features**

* Maintains **insertion order**.
* Uses **Hash Table + Doubly Linked List**.
* Allows **one `null` key** and multiple `null` values.
* Slightly slower than `HashMap` because of ordering maintenance.

**How It Works**

* Internally uses a **hash table** for fast lookup and a **linked list** to preserve order.


**When to Use**

* Ordered map iteration.
* Cache implementations.

**Code Example**

```java id="zhf7dz"
import java.util.LinkedHashMap;

LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

map.put(3, "C");
map.put(1, "A");
map.put(2, "B");

System.out.println(map); // {3=C, 1=A, 2=B}
```

---

**`PriorityQueue`**



A **`PriorityQueue`** is a queue implementation that stores elements according to their **priority** rather than insertion order. Internally, it uses a **Binary Heap**.


**Key Features**

* Uses a **Binary Heap** internally.
* By default, follows **natural ascending order** (Min Heap).
* Can use a **`Comparator`** for custom ordering.
* Does not allow `null` elements.

**How It Works**

* Elements are inserted into the heap.
* The root element (highest priority) is always available at the front.
* `poll()` removes the highest-priority element.


**When to Use**

* Task scheduling.
* Job queues.
* Algorithms requiring priority-based retrieval.

**Code Example**

```java id="pnm7e5"
import java.util.PriorityQueue;

PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(30);
pq.offer(10);
pq.offer(20);

System.out.println(pq.poll()); // 10
System.out.println(pq.poll()); // 20
```

**Key Comparison Table**

| **Collection**        | **Internal Structure**          | **Special Feature**                      | **Best Use Case**                        |
| --------------------- | ------------------------------- | ---------------------------------------- | ---------------------------------------- |
| **`WeakHashMap`**     | Hash Table                      | Weak references for keys                 | Caching, memory-sensitive data           |
| **`IdentityHashMap`** | Hash Table                      | Compares keys using `==`                 | Object identity tracking                 |
| **`LinkedHashMap`**   | Hash Table + Doubly Linked List | Maintains insertion/access order         | Ordered maps, LRU cache                  |
| **`PriorityQueue`**   | Binary Heap                     | Retrieves highest-priority element first | Scheduling and priority-based processing |

**Easy Way to Remember**

* **`WeakHashMap` = Weak Keys + GC Removes Entries**
* **`IdentityHashMap` = `==` Comparison**
* **`LinkedHashMap` = Ordered `HashMap`**
* **`PriorityQueue` = Heap + Priority-Based Retrieval**


## 9. How to Implement LRU, LFU and TTL Cache

**# 1. LRU Cache (Least Recently Used) :** Removes the least recently accessed item.

```java id="m5nz5d"
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {

        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1);

        cache.put(4, "D");

        System.out.println(cache);
    }
}
// Output: {3=C, 1=A, 4=D}
// `2=B` removed because it was least recently used.
```


**2. LFU Cache (Least Frequently Used):** Removes least frequently accessed item.

```java id="gh5c2v"
import java.util.HashMap;
import java.util.Map;

class LFUCache {

    private Map<Integer, String> cache = new HashMap<>();
    private Map<Integer, Integer> frequency = new HashMap<>();

    public void put(int key, String value) {
        cache.put(key, value);
        frequency.put(key, 1);
    }

    public String get(int key) {

        if (!cache.containsKey(key)) {
            return null;
        }

        frequency.put(key, frequency.get(key) + 1);

        return cache.get(key);
    }

    public void removeLFU() {

        int minFreq = Integer.MAX_VALUE;
        int lfuKey = -1;

        for (int key : frequency.keySet()) {

            if (frequency.get(key) < minFreq) {
                minFreq = frequency.get(key);
                lfuKey = key;
            }
        }

        cache.remove(lfuKey);
        frequency.remove(lfuKey);
    }

    public void print() {
        System.out.println(cache);
    }

    public static void main(String[] args) {

        LFUCache cache = new LFUCache();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1);
        cache.get(1);

        cache.get(2);

        cache.removeLFU();

        cache.print();
    }
}
// Output: {1=A, 2=B}
// `3=C` removed because it was least frequently used.
```


**3. TTL Cache (Time To Live) :** Expires data after fixed duration.

```java id="9m7nlj"
import java.util.HashMap;
import java.util.Map;

class TTLCache {

    static class CacheObject {
        String value;
        long expiryTime;

        CacheObject(String value, long ttlMillis) {
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }
    }

    private Map<Integer, CacheObject> cache = new HashMap<>();

    public void put(int key, String value, long ttlMillis) {
        cache.put(key, new CacheObject(value, ttlMillis));
    }

    public String get(int key) {

        CacheObject obj = cache.get(key);

        if (obj == null) {
            return null;
        }

        if (System.currentTimeMillis() > obj.expiryTime) {
            cache.remove(key);
            return null;
        }

        return obj.value;
    }

    public static void main(String[] args) throws Exception {

        TTLCache cache = new TTLCache();

        cache.put(1, "Java", 3000);

        System.out.println(cache.get(1));

        Thread.sleep(4000);

        System.out.println(cache.get(1));
    }
}
// Output: 
// Java
// null
// After 3 seconds, value expires.
```


**Quick Interview Summary**

| Cache Type | Removes Based On      | Mostly Used In                    |
| ---------- | --------------------- | --------------------------------- |
| LRU        | Least recently used   | Browser cache, Redis              |
| LFU        | Least frequently used | Analytics, recommendation systems |
| TTL        | Time expiration       | Session cache, tokens, OTPs       |



## 10. Difference between ConcurrentHashMap and HashMap, and when to use what?




Both **`HashMap`** and **`ConcurrentHashMap`** are implementations of the **`Map`** interface used to store **key-value pairs**, but they differ mainly in **thread safety** and **concurrent access handling**.

* **`HashMap`** is **not thread-safe**.
* **`ConcurrentHashMap`** is **thread-safe** and designed for **multi-threaded environments**.


**Key Differences**

| **Feature**           | **HashMap**                            | **ConcurrentHashMap**                                                |
| --------------------- | -------------------------------------- | -------------------------------------------------------------------- |
| **Thread Safety**     | Not thread-safe                        | Thread-safe                                                          |
| **Synchronization**   | No synchronization                     | Fine-grained synchronization and CAS (Compare-And-Swap)              |
| **Performance**       | Faster in single-threaded applications | High performance in multi-threaded applications                      |
| **Multiple Threads**  | May cause data inconsistency           | Safe for concurrent access                                           |
| **Null Key**          | Allows one `null` key                  | Does **not** allow `null` keys                                       |
| **Null Values**       | Allows multiple `null` values          | Does **not** allow `null` values                                     |
| **Iterator Behavior** | Fail-fast                              | Weakly consistent (does not throw `ConcurrentModificationException`) |
| **Best Use Case**     | Single-threaded applications           | Multi-threaded applications                                          |

**How It Works**

* **`HashMap`**

  * Uses a **hash table** internally.
  * Multiple threads modifying the map simultaneously can lead to **data corruption** or inconsistent results.

* **`ConcurrentHashMap`**

  * Also uses a **hash table**, but supports concurrent access.
  * In **Java 8+**, it uses **CAS (Compare-And-Swap)** and **bucket-level locking** instead of locking the entire map.
  * Multiple threads can read and update different buckets simultaneously.


**When to Use**

| **Scenario**                  | **Best Choice**       |
| ----------------------------- | --------------------- |
| Single-threaded application   | **HashMap**           |
| Multi-threaded application    | **ConcurrentHashMap** |
| Shared cache between threads  | **ConcurrentHashMap** |
| High-speed local data storage | **HashMap**           |

**Code Example**

**Using `HashMap`**

```java
import java.util.HashMap;

HashMap<Integer, String> map = new HashMap<>();
map.put(1, "Java");
map.put(2, "Spring");

System.out.println(map.get(1));
```

**Using `ConcurrentHashMap`**

```java
import java.util.concurrent.ConcurrentHashMap;

ConcurrentHashMap<Integer, String> map =
        new ConcurrentHashMap<>();

map.put(1, "Java");
map.put(2, "Spring");

System.out.println(map.get(1));
```

**Iterator Behavior**

```java
import java.util.concurrent.ConcurrentHashMap;

ConcurrentHashMap<Integer, String> map =
        new ConcurrentHashMap<>();

map.put(1, "A");
map.put(2, "B");

for (Integer key : map.keySet()) {
    map.put(3, "C");   // No ConcurrentModificationException
}
```

A **`HashMap`** iterator is **fail-fast** and throws **`ConcurrentModificationException`** if the map is modified during iteration. A **`ConcurrentHashMap`** iterator is **weakly consistent** and safely reflects some or all updates.

**Key Features**

* **`HashMap`**

  * Fast and lightweight.
  * Allows one `null` key and multiple `null` values.
  * Best for non-concurrent environments.

* **`ConcurrentHashMap`**

  * Thread-safe with high concurrency.
  * Uses **CAS** and **fine-grained locking**.
  * Does not allow `null` keys or values.
  * Suitable for shared data in multi-threaded applications.

**Easy Way to Remember**

* **`HashMap` = Fast + Not Thread-Safe + Allows `null`**
* **`ConcurrentHashMap` = Thread-Safe + Concurrent Access + No `null`**
* **Single Thread → `HashMap`**
* **Multiple Threads → `ConcurrentHashMap`**


## 12. Difference between `List`, `Set`, `Map`, and `Queue`? 



**`List`**, **`Set`**, **`Map`**, and **`Queue`** are the main interfaces in the **Java Collections Framework** used to store and manage data in different ways.

* **`List`** stores an **ordered collection** of elements.
* **`Set`** stores **unique elements** without duplicates.
* **`Map`** stores data as **key-value pairs**.
* **`Queue`** stores elements for **processing in a specific order** (typically FIFO).


**Key Differences**

| **Feature**            | **List**              | **Set**                   | **Map**                                               | **Queue**                      |
| ---------------------- | --------------------- | ------------------------- | ----------------------------------------------------- | ------------------------------ |
| **Stores**             | Elements              | Unique Elements           | Key-Value Pairs                                       | Elements                       |
| **Duplicates Allowed** | Yes                   | No                        | Duplicate keys: No, Duplicate values: Yes             | Yes                            |
| **Maintains Order**    | Yes (insertion order) | Depends on implementation | Depends on implementation                             | Yes (FIFO by default)          |
| **Access by Index**    | Yes                   | No                        | Access by Key                                         | No                             |
| **Null Allowed**       | Yes                   | Usually one `null`        | One `null` key (`HashMap`) and multiple `null` values | Depends on implementation      |
| **Main Use**           | Ordered collection    | Unique collection         | Fast key-value lookup                                 | Task scheduling and processing |

**How It Works**

* **`List`**

  * Stores elements sequentially.
  * Allows duplicate values and index-based access.

* **`Set`**

  * Automatically prevents duplicate elements.
  * Uses hashing or sorting depending on the implementation.

* **`Map`**

  * Stores each value with a unique key.
  * Retrieves data quickly using the key.

* **`Queue`**

  * Follows the **FIFO (First In, First Out)** principle by default.
  * Elements are added at the rear and removed from the front.

**Popular Implementations**

| **Interface** | **Common Implementations**                                 |
| ------------- | ---------------------------------------------------------- |
| **`List`**    | `ArrayList`, `LinkedList`, `Vector`                        |
| **`Set`**     | `HashSet`, `LinkedHashSet`, `TreeSet`                      |
| **`Map`**     | `HashMap`, `LinkedHashMap`, `TreeMap`, `ConcurrentHashMap` |
| **`Queue`**   | `LinkedList`, `PriorityQueue`, `ArrayDeque`                |


**When to Use**

| **Scenario**                         | **Best Choice** |
| ------------------------------------ | --------------- |
| Store student names with duplicates  | **List**        |
| Store unique email IDs               | **Set**         |
| Store employee ID and employee name  | **Map**         |
| Process print jobs or tasks in order | **Queue**       |

**Code Example**

```java id="xg4h9m"
import java.util.*;

public class Demo {
    public static void main(String[] args) {

        // List
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Java");

        // Set
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Java");

        // Map
        Map<Integer, String> map = new HashMap<>();
        map.put(101, "Alice");
        map.put(102, "Bob");

        // Queue
        Queue<String> queue = new LinkedList<>();
        queue.offer("Task1");
        queue.offer("Task2");

        System.out.println(list);         // [Java, Java]
        System.out.println(set);          // [Java]
        System.out.println(map);          // {101=Alice, 102=Bob}
        System.out.println(queue.poll()); // Task1
    }
}
```

**Key Features**

* **`List`**

  * Ordered collection.
  * Allows duplicates.
  * Supports index-based access.

* **`Set`**

  * Stores only unique elements.
  * No duplicate values.
  * Fast search using hashing.

* **`Map`**

  * Stores **key-value pairs**.
  * Keys must be unique.
  * Fast retrieval using keys.

* **`Queue`**

  * Follows **FIFO** order by default.
  * Ideal for task scheduling and message processing.
  * `PriorityQueue` processes elements based on priority instead of insertion order.

**Easy Way to Remember**

* **`List` = Ordered + Duplicates Allowed**
* **`Set` = Unique Elements Only**
* **`Map` = Key + Value**
* **`Queue` = FIFO Processing**


## 13. Difference between List and Array? 



* **`Array`** is a built-in data structure in Java that stores a **fixed-size collection** of elements of the same type.

* **`List`** is an interface in the **Java Collections Framework** that stores an **ordered, dynamic collection** of elements.

**Key Differences**

| **Feature**                 | **Array**      | **List**                                           |
| --------------------------- | -------------- | -------------------------------------------------- |
| **Size**                    | Fixed          | Dynamic (Resizable)                                |
| **Part of**                 | Java Language  | Java Collections Framework                         |
| **Can Add/Remove Elements** | No             | Yes                                                |
| **Access by Index**         | Yes            | Yes                                                |
| **Stores Primitive Types**  | Yes            | No (stores objects, uses wrapper classes)          |
| **Built-in Methods**        | Very Limited   | Rich API (`add()`, `remove()`, `contains()`, etc.) |
| **Performance**             | Faster         | Slightly slower due to extra features              |
| **Type**                    | Data Structure | Interface                                          |

**How It Works**

* **`Array`**

  * Memory is allocated for a fixed number of elements when the array is created.
  * Size cannot be changed later.

* **`List`**

  * Implementations like **`ArrayList`** internally use a dynamic array.
  * When capacity is full, a larger array is created and existing elements are copied automatically.


**When to Use**

| **Scenario**                          | **Best Choice** |
| ------------------------------------- | --------------- |
| Fixed number of elements              | **Array**       |
| Frequent add/remove operations        | **List**        |
| Need built-in collection methods      | **List**        |
| High-performance, simple data storage | **Array**       |

**Code Example**

**Using an Array**

```java id="h4m9pr"
int[] numbers = {10, 20, 30};

System.out.println(numbers[1]); // 20
```

**Using a List**

```java id="8vzk3x"
import java.util.ArrayList;
import java.util.List;

List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.get(1)); // 20
```

**Key Features**

* **`Array`**

  * Fixed size.
  * Can store **primitive types** (`int`, `char`, `double`, etc.).
  * Faster access with lower memory overhead.

* **`List`**

  * Dynamic and resizable.
  * Provides many utility methods.
  * Can only store **objects** (primitive types are automatically converted using **autoboxing**).

**Example of Dynamic Behavior**

```java id="q2h1sk"
List<String> list = new ArrayList<>();
list.add("Java");
list.add("Spring");
list.remove("Java");

System.out.println(list); // [Spring]
```

The equivalent operation is not possible directly with a fixed-size array.

**Easy Way to Remember**

* **`Array` = Fixed Size + Faster + Stores Primitives**
* **`List` = Dynamic Size + Rich Methods + Stores Objects**
* **Known Size → `Array`**
* **Variable Size → `List`**



## 14. How do you convert a List to an Array? 

```java
List<String> names = List.of("John", "David", "Mike");

String[] arr = names.toArray(new String[0]);
// [John, David, Mike]
```
**Why new String[0]?**

The JVM creates an array of the correct size automatically.


## 16. What is O(1) and  O(n)?


**`O(1)`** and **`O(n)`** are examples of **Big O Notation**, which is used to describe the **time complexity** of an algorithm. It tells us how the execution time grows as the input size (`n`) increases.

**What is `O(1)`?**

**`O(1)` (Constant Time)** means the operation takes the **same amount of time**, whether there are **10 elements or 10 million elements**.

**How It Works**

* The algorithm performs a fixed number of operations.
* Execution time does not depend on the input size.

**Example**

```java id="4zqgxz"
int[] arr = {10, 20, 30, 40};

System.out.println(arr[2]); // O(1)
```

Accessing an array element by index always takes constant time.

**Common `O(1)` Operations**

* Array element access by index.
* `HashMap.get()` (average case).
* `HashMap.put()` (average case).
* `Stack.push()` and `Stack.pop()`.

**What is `O(n)`?**

**`O(n)` (Linear Time)** means the execution time increases **proportionally to the number of input elements**.

**How It Works**

* The algorithm processes each element one by one.
* If the input size doubles, the execution time roughly doubles.

**Example**

```java id="ghs6un"
int[] arr = {10, 20, 30, 40};

for (int num : arr) {
    System.out.println(num);
}
```

The loop visits every element once, so the time complexity is **`O(n)`**.

**Common `O(n)` Operations**

* Linear search in an array or list.
* Iterating through a collection.
* Finding the maximum or minimum element in an unsorted array.

**Key Differences**

| **Feature**                | **O(1)**           | **O(n)**              |
| -------------------------- | ------------------ | --------------------- |
| **Meaning**                | Constant Time      | Linear Time           |
| **Depends on Input Size?** | No                 | Yes                   |
| **Performance**            | Very Fast          | Slower as input grows |
| **Example**                | Array index access | Loop through an array |


**When to Use**

| **Scenario**                        | **Preferred Complexity** |
| ----------------------------------- | ------------------------ |
| Fast lookup by key                  | **O(1)**                 |
| Traversing all elements             | **O(n)**                 |
| Searching in an unsorted list       | **O(n)**                 |
| Accessing an array element by index | **O(1)**                 |

**Real-World Examples**

| **Operation**                              | **Time Complexity** |
| ------------------------------------------ | ------------------- |
| Open a book to page number 50 (known page) | **O(1)**            |
| Find a person in an unsorted contact list  | **O(n)**            |
| `HashMap.get()` (average case)             | **O(1)**            |
| Iterate through an `ArrayList`             | **O(n)**            |

**Code Example**

```java id="g6j6jm"
import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {

        // O(1)
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        System.out.println(map.get(1));

        // O(n)
        int[] numbers = {10, 20, 30, 40};
        for (int n : numbers) {
            System.out.println(n);
        }
    }
}
```

**Key Features**

* **`O(1)`**

  * Constant execution time.
  * Does not depend on input size.
  * Best possible time complexity for most operations.

* **`O(n)`**

  * Execution time grows linearly with input size.
  * Common in loops and linear searches.
  * Performance decreases as data size increases.

**Easy Way to Remember**

* **`O(1)` = One Step, No Matter How Much Data**
* **`O(n)` = One Step Per Element**
* **Fast Lookup → `O(1)`**
* **Full Traversal → `O(n)`**



## 7. What is the difference between Collection and Collections?

**`Collection`** is an **interface** that represents a group of objects.

**`Collections`** is a **utility class** that provides **static methods** to perform operations on collections.


| **Property**              | **Collection**                        | **Collections**                          |
|---------------------------|---------------------------------------|------------------------------------------|
| **Type**                   | `Collection` is an interface. It is the root interface in the collection framework and is implemented by other interfaces like `List`, `Set`, etc. | `Collections` is a **utility class** that provides static methods for operating on collections (e.g., sorting, searching). |
| **Purpose**                | Defines the basic operations for all collection types. | Provides utility methods for working with collections, like sorting, reversing, etc. |
| **Example**                | `List`, `Set`, `Queue` are types of collections. | `Collections.sort()`, `Collections.reverse()`, etc. |

Example:

```java
// Collection example (interface)
Collection<String> collection = new ArrayList<>();
collection.add("Apple");
collection.add("Banana");

// Collections example (utility class)
Collections.sort(new ArrayList<>(collection));
Collections.reverse(new ArrayList<>(collection));
```

**How it Works**

#**`Collection`**

* It is the **root interface** of the Java Collection Framework.
* Defines common operations like **adding**, **removing**, and **searching** elements.
* Extended by interfaces such as **`List`**, **`Set`**, and **`Queue`**.

#**`Collections`**

* It is a **final utility class**.
* Contains **only static methods**.
* Used to perform operations like **sorting**, **reversing**, **shuffling**, **finding minimum/maximum**, and creating **synchronized** or **unmodifiable** collections.

**Example**

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Collection Interface
        Collection<String> names = new ArrayList<>();
        names.add("John");
        names.add("Alice");
        names.add("Bob");

        // Collections Utility Class
        List<String> list = new ArrayList<>(names);

        Collections.sort(list);
        Collections.reverse(list);

        System.out.println(list);
    }
}
```

**Output**

```text
[John, Bob, Alice]
```

**Common Interview Follow-up Questions**

**1. Is `Collection` a class or an interface?**
**Answer:** **`Collection`** is an **interface**.

**2. Is `Collections` a class or an interface?**
**Answer:** **`Collections`** is a **final utility class** with **static methods**.

**3. Does `Map` extend `Collection`?**
**Answer:** **No.** **`Map`** is part of the Java Collections Framework but **does not extend `Collection`** because it stores **key-value pairs**, not individual elements.

**4. Name some commonly used methods of `Collections`.**
**Answer:** **`sort()`**, **`reverse()`**, **`shuffle()`**, **`binarySearch()`**, **`min()`**, **`max()`**, **`synchronizedList()`**, **`unmodifiableList()`**.

**5. What is the difference between `Collection` and `Collections` in one line?**
**Answer:** **`Collection`** is an **interface for storing data**, while **`Collections`** is a **utility class for performing operations on collections**.



## 13. What is fail-fast and fail-safe iterators?


**Fail-Fast** and **Fail-Safe** are behaviors of java collections when they are modified while being iterated.

* **Fail-Fast**: Immediately throws an exception if the collection is modified during iteration.
* **Fail-Safe**: Works on a copy of the collection, so modifications do not cause exceptions.


| **Feature**     | **Fail-Fast**                                                      | **Fail-Safe**                                                |
| --------------- | ------------------------------------------------------------------ | ------------------------------------------------------------ |
| **Behavior**    | Throws an exception if the collection is modified during iteration | Works on a copy of the collection, so no exception is thrown |
| **Exception**   | **`ConcurrentModificationException`**                              | No exception                                                 |
| **Data Access** | Iterates over the **original collection**                          | Iterates over a **cloned/copy collection**                   |
| **Performance** | Faster, no extra memory needed                                     | Slightly slower, uses extra memory for the copy              |
| **Examples**    | `ArrayList`, `HashMap`, `HashSet`                                  | `CopyOnWriteArrayList`, `ConcurrentHashMap`                  |

**How Fail-Fast Works**

* The iterator keeps track of a **modification count (`modCount`)**.
* If the collection is structurally modified after the iterator is created (except through the iterator's own `remove()` method), the iterator detects the change and throws a **`ConcurrentModificationException`**.

**Code Example (Fail-Fast)**

```java
List<String> list = new ArrayList<>();
list.add("A");
list.add("B");

for (String s : list) {
    list.add("C");   // Throws ConcurrentModificationException
}
```

**How Fail-Safe Works**

* The iterator works on a **snapshot (copy)** of the collection.
* Changes made to the original collection during iteration do not affect the iterator, so no exception occurs.

**Code Example (Fail-Safe)**

```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
list.add("A");
list.add("B");

for (String s : list) {
    list.add("C");   // No exception
    System.out.println(s);
}
```

**Key Features**

* **Fail-Fast**

  * Detects concurrent modifications quickly.
  * Prevents unpredictable behavior.
  * Does **not guarantee thread safety**.

* **Fail-Safe**

  * Safe for concurrent modifications.
  * Suitable for **multi-threaded environments**.
  * Uses extra memory because it creates a copy.

**When to Use**

* Use **Fail-Fast** for **single-threaded applications** where modifications during iteration should be detected immediately.

* Use **Fail-Safe** for **multi-threaded applications** where collections may be modified while iterating.



## 17. What is CopyOnWriteArrayList?

**`CopyOnWriteArrayList`** is a **thread-safe** implementation of the **`List`** interface in Java. Whenever an element is **added**, **updated**, or **removed**, it creates a **new copy** of the underlying array. This allows multiple threads to **read safely** without locking.

**How It Works**

1. **Read operations** use the existing array without locking.
2. **Write operations** create a **new copy** of the array, apply the change, and replace the old array.
3. Readers continue using the old array until the new one is ready.

**Key Features**

* **Thread-safe** without explicit synchronization.
* **Fast reads** because no locking is required.
* **Slow writes** because the entire array is copied on every modification.
* **Safe iteration** without **`ConcurrentModificationException`**.

**When to Use**

* When **read operations are much more frequent than write operations**.
* When multiple threads need to **iterate** over a list safely.
* Examples: **configuration data**, **cache entries**, **registered listeners**, or **read-mostly collections**.

**Example**

```java
import java.util.concurrent.CopyOnWriteArrayList;

public class Demo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("Java");
        list.add("Spring");

        for (String item : list) {
            System.out.println(item);

            // Safe modification during iteration
            list.add("Docker");
        }

        System.out.println(list);
    }
}
```

**Output:**

```text
Java
Spring
[Java, Spring, Docker, Docker]
```

The loop iterates over the **original snapshot**, so no **`ConcurrentModificationException`** occurs.


## 17. What is ConcurrentModificationException?


**`ConcurrentModificationException`** is a **runtime exception** that occurs when a collection is **modified while it is being iterated**, except through the iterator's own methods.

**How It Happens**

1. An **Iterator** starts traversing a collection.
2. The collection is modified using methods like **`add()`** or **`remove()`** directly on the collection.
3. The iterator detects the modification and throws **`ConcurrentModificationException`**.

**Example (Throws Exception)**

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Java");
        list.add("Spring");
        list.add("Docker");

        for (String item : list) {
            if (item.equals("Spring")) {
                list.remove(item); // Throws ConcurrentModificationException
            }
        }
    }
}
```

**Correct Way Using `Iterator`**

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Java");
        list.add("Spring");
        list.add("Docker");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals("Spring")) {
                iterator.remove(); // Safe removal
            }
        }

        System.out.println(list);
    }
}
```

**Output**

```text
[Java, Docker]
```

**How to Avoid It**

1. Use **`Iterator.remove()`** instead of **`Collection.remove()`** during iteration.
2. Use **`CopyOnWriteArrayList`** for **read-heavy, multi-threaded** applications.
3. Collect elements to remove and delete them **after** iteration.


## 21. How to properly remove elements during iteration?

The **safe** way to remove elements during iteration is to use the **`Iterator.remove()`** method. Removing elements directly using **`Collection.remove()`** while iterating can cause a **`ConcurrentModificationException`**.

**Using `Iterator.remove()` (Recommended)**

```java
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("Java");
        list.add("Spring");
        list.add("Docker");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String item = iterator.next();

            if (item.equals("Spring")) {
                iterator.remove(); // Safe removal
            }
        }

        System.out.println(list);
    }
}
```

**Output**

```text
[Java, Docker]
```

**Using `removeIf()` (Java 8+)**

```java
List<String> list = new ArrayList<>();

list.add("Java");
list.add("Spring");
list.add("Docker");

list.removeIf(item -> item.equals("Spring"));

System.out.println(list);
```

**Output**

```text
[Java, Docker]
```


## 23. What is the difference between Iterator and ListIterator?

**`Iterator`** is used to **traverse any Collection in the forward direction**, 

**`ListIterator`** works **only with `List`**, supports **forward and backward traversal**, and allows **adding**, **updating**, and **removing** elements during iteration.

| **`Iterator`**                                          | **`ListIterator`**                                                                                                       |
| ------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------ |
| Works with **all Collection** types.                    | Works **only with `List`** implementations.                                                                              |
| Traverses **only in the forward direction**.            | Traverses **both forward and backward**.                                                                                 |
| Supports **`remove()`** operation.                      | Supports **`remove()`**, **`add()`**, and **`set()`** operations.                                                        |
| Methods: **`hasNext()`**, **`next()`**, **`remove()`**. | Methods: **`hasNext()`**, **`next()`**, **`hasPrevious()`**, **`previous()`**, **`remove()`**, **`add()`**, **`set()`**. |

**Example Using `Iterator`**

```java id="a7d4pl"
import java.util.*;

List<String> list = new ArrayList<>();
list.add("Java");
list.add("Spring");

Iterator<String> iterator = list.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

**Example Using `ListIterator`**

```java id="2n4gkx"
import java.util.*;

List<String> list = new ArrayList<>();
list.add("Java");
list.add("Spring");

ListIterator<String> iterator = list.listIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

while (iterator.hasPrevious()) {
    System.out.println(iterator.previous());
}
```



## 11. What is diffence between Arrays.asList() vs List.of()?

`Arrays.asList()` creates a **fixed-size list** backed by the original array. You can modify the elements but cannot add or remove them. It allows `null` values.

`List.of()` creates an **immutable list** that does not allow `null` values. You cannot modify, add, or remove elements from this list.


| **Feature**       | **`Arrays.asList()`**                                                          | **`List.of()`**                                                    |
| ----------------- | ------------------------------------------------------------------------------ | ------------------------------------------------------------------ |
| **Introduced In** | **Java 5**                                                                     | **Java 9**                                                         |
| **Modifiable**    | **Fixed-size** list (cannot add/remove, but can update elements).              | **Completely immutable** (cannot add, remove, or update elements). |
| **Null Values**   | **Allows `null`** elements.                                                    | **Does not allow `null`** (throws `NullPointerException`).         |
| **Backed By**     | Backed by the **original array**, so changes to the array reflect in the list. | Creates an **independent immutable list**.                         |
| **Best Use Case** | When you need a **fixed-size view of an array**.                               | When you need a **read-only immutable list**.                      |

**How It Works**

* **`Arrays.asList()`** converts an array into a **fixed-size List**.
* **`List.of()`** creates an **immutable List** with the given elements.

**When to Use**

* Use **`Arrays.asList()`** when you need to work with an existing array and may want to **modify element values**.
* Use **`List.of()`** when you need a **safe, immutable list** that should not be changed.

**Code Example**

```java id="3s7kqa"
// Arrays.asList()
List<String> list1 = Arrays.asList("A", "B", "C");
list1.set(0, "X");          // Allowed
// list1.add("D");          // Throws UnsupportedOperationException

// List.of()
List<String> list2 = List.of("A", "B", "C");
// list2.set(0, "X");        // Throws UnsupportedOperationException
// list2.add("D");           // Throws UnsupportedOperationException
```

## 11. What is diffence between data structures Vector and an ArrayList?

`Vector` and `ArrayList` are both part of the Java collection framework and implement the `List` interface, but there are several differences between them:

| **Property**              | **Vector**                          | **ArrayList**                        |
|---------------------------|-------------------------------------|--------------------------------------|
| **Thread-safety**          | Vector is **synchronized**, making it thread-safe (but slower). | ArrayList is **not synchronized**, making it faster in single-threaded scenarios. |
| **Growth Policy**          | Vector doubles its size when it runs out of space. | ArrayList grows by 50% of its size when it runs out of space. |
| **Performance**            | Slower due to synchronization and larger size increase. | Faster as it is not synchronized and has a more efficient growth policy. |
| **Legacy**                 | Vector is considered a **legacy class** and is part of the original version of Java. | ArrayList is more commonly used today and is part of the modern Java collection framework. |
| **Use Case**               | Used in multi-threaded environments where thread safety is required. | Preferred for single-threaded applications where performance is critical. |

```java
// Vector example (Thread-safe)
Vector<Integer> vector = new Vector<>();
vector.add(10);
vector.add(20);

// ArrayList example (Not thread-safe)
ArrayList<Integer> arrayList = new ArrayList<>();
arrayList.add(10);
arrayList.add(20);
```


## 1. **What is `Stack`?**

A **`Stack`** is a **Linear Data Structure** that follows the **LIFO (Last In, First Out)** principle, meaning the **last element added is the first one removed**.

In Java, **`Stack`** is a class that extends **`Vector`**.

**How It Works**

1. **`push()`** – Adds an element to the **top** of the stack.
2. **`pop()`** – Removes and returns the **top** element.
3. **`peek()`** – Returns the **top** element without removing it.
4. **`isEmpty()`** – Checks if the stack is empty.
5. **`search()`** – Returns the position of an element from the top of the stack.

**Example**

```java
import java.util.Stack;

public class Demo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.push("Java");
        stack.push("Spring");
        stack.push("Docker");

        System.out.println(stack.peek()); // Docker
        System.out.println(stack.pop());  // Docker
        System.out.println(stack);        // [Java, Spring]
    }
}
```

**Output**

```text
Docker
Docker
[Java, Spring]
```

**When to Use**

* **Undo/Redo** functionality.
* **Browser Back/Forward** navigation.
* **Expression evaluation**.
* **Function call stack** in Java.


## 1. **What is `Queue` and What Implementations Exist?**

A **`Queue`** is a **Collection** that stores elements in **FIFO (First In, First Out)** order, meaning the **first element added is the first one removed**.

The **`Queue`** interface is commonly used for **task scheduling**, **message processing**, and **request handling**.

**Main Operations**

* **`offer()`** – Adds an element to the queue.
* **`poll()`** – Removes and returns the front element.
* **`peek()`** – Returns the front element without removing it.

**Common Implementations**

1. **`LinkedList`**

   * Implements the **`Queue`** interface.
   * Maintains **FIFO** order.
   * Suitable for general-purpose queue operations.

2. **`PriorityQueue`**

   * Stores elements based on their **priority** (natural ordering or a **`Comparator`**).
   * Does **not** maintain insertion order.
   * The **smallest** (or highest-priority) element is removed first.

3. **`ArrayDeque`**

   * Implements the **`Deque`** interface.
   * Can be used as both a **Queue** and a **Stack**.
   * Faster than **`LinkedList`** for most queue operations.

4. **`ConcurrentLinkedQueue`**

   * A **thread-safe**, **non-blocking** queue.
   * Suitable for **multi-threaded** applications.

**Example**

```java id="1hylq4"
import java.util.LinkedList;
import java.util.Queue;

public class Demo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.offer("Java");
        queue.offer("Spring");
        queue.offer("Docker");

        System.out.println(queue.peek()); // Java
        System.out.println(queue.poll()); // Java
        System.out.println(queue);        // [Spring, Docker]
    }
}
```

**Output**

```text id="k8zjtu"
Java
Java
[Spring, Docker]
```

**When to Use**

* **`LinkedList`** – General **FIFO** queue.
* **`PriorityQueue`** – When elements must be processed by **priority**.
* **`ArrayDeque`** – High-performance **Queue** or **Stack**.
* **`ConcurrentLinkedQueue`** – **Thread-safe** queue for concurrent applications.


## 1. **What is `Deque`?**

**`Deque`** (**Double-Ended Queue**) is an interface in the Java Collection Framework that allows elements to be **added** and **removed** from **both the front and the rear** of the collection.

It can work as both a **Queue (FIFO)** and a **Stack (LIFO)**.

**How It Works**

1. Add elements at the **front** or **rear**.
2. Remove elements from the **front** or **rear**.
3. Supports both **FIFO** and **LIFO** operations.

**Main Methods**

* **`offerFirst()`** – Adds an element at the front.
* **`offerLast()`** – Adds an element at the rear.
* **`pollFirst()`** – Removes and returns the front element.
* **`pollLast()`** – Removes and returns the rear element.
* **`peekFirst()`** – Returns the front element without removing it.
* **`peekLast()`** – Returns the rear element without removing it.

**Common Implementations**

* **`ArrayDeque`** – Fast and recommended implementation for most use cases.
* **`LinkedList`** – Can also be used as a **Deque**.

**Example**

```java id="vz5z6u"
import java.util.ArrayDeque;
import java.util.Deque;

public class Demo {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();

        deque.offerFirst("Java");
        deque.offerLast("Spring");
        deque.offerLast("Docker");

        System.out.println(deque);          // [Java, Spring, Docker]

        System.out.println(deque.pollFirst()); // Java
        System.out.println(deque.pollLast());  // Docker

        System.out.println(deque);          // [Spring]
    }
}
```

**Output**

```text id="zwsigx"
[Java, Spring, Docker]
Java
Docker
[Spring]
```

**When to Use**

* As a **Queue (FIFO)**.
* As a **Stack (LIFO)**.
* When you need to **insert** or **remove** elements from **both ends** efficiently.


# ✅ 08. Java Lambda Expres.. & Streams API 

## 2. What are lambda expressions?

**Lambda expressions** in Java are a short and clear way to represent **anonymous functions** (functions without a name).

They were introduced in **Java 8** to support **functional programming** and make code more readable and concise.

```java
(parameters) -> expression
```

```java
// Before lambda - anonymous class
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Hello");
    }
};

// With lambda - concise
Runnable r2 = () -> System.out.println("Hello");

// Lambda with parameters
List<String> names = Arrays.asList("John", "Jane");
names.forEach(name -> System.out.println(name));
```

## 3. What are method references?

A **Method Reference** is a **shorthand syntax of a lambda expression** that refers to an existing method using `::` operator.

**Types of Method References:**
- Static method: `ClassName::methodName`
- Instance method: `object::methodName`
- Instance method of arbitrary object: `ClassName::methodName`
- Constructor: `ClassName::new`

```java
// Lambda expression
OptionalInt maxAge = studlist.stream()
        .mapToInt(student -> student.getAge())
        .max();

// Method reference - more concise
OptionalInt maxAge = studlist.stream()
        .mapToInt(Student::getAge)
        .max();

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;
```

## 4. What is the difference between lambda and anonymous class?

**Lambda expressions** in Java are a short and clear way to represent **anonymous functions** (functions without a name).

**Anonymous Class** is a **class without a name** defined and instantiated in a single statement, used to provide an implementation of an interface or subclass.

```java
// Anonymous class - verbose
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println(this.getClass()); // Anonymous class
    }
};

// Lambda - concise
Runnable r2 = () -> {
    System.out.println(this.getClass()); // Enclosing class
};
```

## 5. What is Stream API?


The **Stream API** is introduced in Java 8 to process **collections of objects in a functional and declarative way**. It allows operations like **filtering, mapping, and reducing** without modifying the original data source.


**Key Features:**

* Supports **functional programming**
* Works with **Collections, Arrays, I/O**
* Uses **lambda expressions**
* Provides **lazy evaluation**
* Enables **parallel processing**
* Does not modify the **original data**


**How it works:**

* Data source (like **List, Set, Array**) is converted into a **Stream**
* A sequence of operations is applied:

  * **Intermediate operations** (filter, map)
  * **Terminal operations** (collect, forEach)
* Operations are executed only when a **terminal operation is called**



**When to use:**

* When processing **collections of data**
* When applying **filtering, transformation, aggregation**
* When you want **declarative code instead of loops**


**Code Example:**

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30);
        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)     // filter even numbers
                .map(n -> n * n)             // square them
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

**Key Features Loop and Stream API**

* **For Loop**

  * **Fastest execution**
  * **Low memory overhead**
  * Best for **performance-critical** or **simple iterative tasks**

* **Stream API**

  * **More readable** and **declarative**
  * Supports **functional programming** (`filter`, `map`, `reduce`)
  * Easy to use with **parallel processing** using `parallelStream()`

**When to Use loops and Stream**

* Use **`for` loops** when:

  * **Maximum performance** is required.
  * Working with **large datasets** in tight loops.
  * The logic is simple and straightforward.

* Use **Stream API** when:

  * **Code readability** and **maintainability** are more important.
  * Performing multiple operations like filtering, mapping, and collecting.
  * You want to leverage **parallel processing** easily.


## 7. What are Side Effects in Stream??

**Side Effects** are **changes to external state** made inside a **Stream** operation. This includes modifying external variables, collections, or objects while processing the stream.

Streams are designed to be **stateless** and **side-effect free**.

**Examples of Side Effects**

* Modifying an external **List**.
* Updating a **variable** outside the stream.
* Writing to a **file** or **database** inside stream operations.
* Printing to the console using **`forEach()`** (acceptable for debugging, but not for business logic).

**Bad Example (Side Effect)**

```java id="cldhgl"
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = new ArrayList<>();

        numbers.stream()
               .filter(n -> n % 2 == 0)
               .forEach(result::add); // Side effect

        System.out.println(result);
    }
}
```

**Good Example (No Side Effect)**

```java id="zwk24m"
import java.util.*;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = numbers.stream()
                                      .filter(n -> n % 2 == 0)
                                      .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

**Why Avoid Side Effects?**

1. Improves **readability** and **maintainability**.
2. Prevents **race conditions** in **Parallel Streams**.
3. Makes stream operations **predictable** and **thread-safe**.
4. Follows the **functional programming** style.

**When to Use Side Effects**

* **Logging** or **debugging** using **`forEach()`**.
* Avoid using side effects for **business logic** or **data processing**.


## 6. What is parallel streams? 

A **Parallel Stream** is a type of **Stream API** that processes data **concurrently using multiple threads**, dividing the task into smaller parts and executing them in parallel to improve performance.


**Key Features:**

* Uses **multiple threads (ForkJoinPool framework)**
* Automatically splits data into **subtasks**
* Improves **performance for large datasets**
* Supports **parallel processing**
* Works with **collections via .parallelStream()**
* Maintains **functional programming style**


**How it works:**

* Source collection is divided into **multiple chunks**
* Each chunk is processed in **separate threads**
* Results are combined using **merge operation**
* Uses **ForkJoin framework internally**


**When to use:**

* When working with **large collections**
* When operations are **CPU-intensive**
* When tasks are **independent and stateless**


**Code Example:**

```java id="k3p9qd"
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        numbers.parallelStream()
                .map(n -> {
                    System.out.println(Thread.currentThread().getName() + " processing " + n);
                    return n * n;
                })
                .forEach(System.out::println);
    }
}
```

**Common Problems of Parallel Streams**

1. **Overhead for Small Datasets**

   * Creating and managing multiple threads adds overhead.
   * For **small collections**, **`stream()`** is often faster than **`parallelStream()`**.

2. **Unpredictable Processing Order**

   * Elements may be processed in **any order**.
   * If order matters, use **`forEachOrdered()`** instead of **`forEach()`**.

3. **Thread Safety Issues**

   * Modifying **shared mutable data** can cause **race conditions** and incorrect results.

4. **Poor Performance for I/O Operations**

   * **Database calls**, **REST APIs**, and **file operations** do not benefit much from parallel streams.
   * They are best suited for **CPU-intensive** tasks.

5. **Uses Shared `ForkJoinPool`**

   * Parallel streams use the **common `ForkJoinPool`**.
   * Heavy parallel tasks can affect the performance of other tasks using the same pool.



## 7. What is ForkJoinPool and how is it related to parallel streams?

**`ForkJoinPool`** is a **thread pool** introduced in **Java 7** for executing tasks in **parallel**. It follows the **Fork/Join** framework, where a large task is **split (fork)** into smaller tasks, processed by multiple threads, and then **combined (join)** to produce the final result.

**How It Works**

1. A large task is **split** into smaller subtasks (**Fork**).
2. Multiple threads execute the subtasks **concurrently**.
3. The results of all subtasks are **combined** (**Join**).
4. It uses **Work Stealing**, where an idle thread can "steal" tasks from a busy thread, improving CPU utilization.

**How is it Related to Parallel Streams?**

* **`parallelStream()`** automatically uses the **common `ForkJoinPool`**.
* The collection is divided into **smaller tasks**.
* Multiple threads process the tasks in **parallel**.
* The results are merged and returned.

**Example**

```java id="qbgvbq"
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int sum = numbers.parallelStream()
                         .mapToInt(n -> n * 2)
                         .sum();

        System.out.println(sum);
    }
}
```

**Output**

```text id="1zv0tz"
30
```

In this example, **`parallelStream()`** uses the **common `ForkJoinPool`** to process the elements using **multiple threads**.

**Key Features**

* Uses a **pool of worker threads**.
* Supports **Fork/Join** processing.
* Uses **Work Stealing** for better performance.
* Used automatically by **Parallel Streams**.
* Best for **CPU-intensive** tasks.

**When to Use**

* **Large datasets**.
* **CPU-intensive** computations.
* Tasks that can be **split into independent subtasks**.


## 7. What is Lazy Evaluation in Stream?

**Lazy Evaluation** means that **Stream intermediate operations** are **not executed immediately**. They are executed **only when a terminal operation is called**.

This improves **performance** by processing only the required elements.

**How It Works**

1. A **Stream** is created.
2. **Intermediate operations** like **`filter()`**, **`map()`**, and **`sorted()`** are defined.
3. No processing happens until a **terminal operation** such as **`collect()`**, **`forEach()`**, **`count()`**, or **`reduce()`** is called.
4. When the terminal operation is executed, the entire pipeline is processed.

**Key Features**

1. **Execution is delayed** until a **terminal operation** is invoked.
2. Improves **performance** by avoiding unnecessary processing.
3. Processes elements **on demand**.
4. Supports **pipeline optimization**.

**Example**

```java id="jlwmwo"
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Java", "Spring", "Docker");

        List<String> result = names.stream()
                .filter(name -> {
                    System.out.println("Filtering: " + name);
                    return name.startsWith("J");
                })
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

**Output**

```text id="yqisv9"
Filtering: Java
Filtering: Spring
Filtering: Docker
[Java]
```

The **`filter()`** operation runs **only when** **`collect()`** is called.

**Example Without a Terminal Operation**

```java id="p6xsn6"
List<String> names = Arrays.asList("Java", "Spring", "Docker");

names.stream()
     .filter(name -> name.startsWith("J"));

// Nothing happens because there is no terminal operation.
```


## 7. Can you modify the state of external variables in Stream operations?

**Yes, but it is not recommended.** Stream operations should be **stateless** and **side-effect free**. Modifying external variables can lead to **incorrect results**, especially when using **Parallel Streams**.

**Why is it Not Recommended?**

1. Can cause **race conditions** in **Parallel Streams**.
2. Makes the code harder to **understand** and **maintain**.
3. Breaks the **functional programming** style of Streams.
4. May produce **unexpected results**.


## 7. What is the difference between Collection and Stream API?

A **Collection** is a **data structure** that stores elements in memory, like `List`, `Set`, or `Map`. It holds data and allows operations such as add, remove, or iterate, and it can be traversed multiple times.

A **Stream** is **not a data structure**; it’s a **data-processing abstraction**. It doesn’t store data but processes elements from a collection or other sources. Streams are **one-time use**, support **functional operations** like `filter` and `map`, and enable easy **parallel processing**.

| **Feature**      | **Collection**                                              | **Stream API**                                                             |
| ---------------- | ----------------------------------------------------------- | ---------------------------------------------------------------------- |
| **Purpose**      | Used to **store and manage data**.                          | Used to **process and transform data**.                                |
| **Data Storage** | **Stores elements** in memory.                              | **Does not store data**; it operates on a data source.                 |
| **Modification** | Supports **adding, removing, and updating** elements.       | Cannot modify the source; it only **processes** the data.              |
| **Iteration**    | Uses **external iteration** (e.g., `for` loop, `Iterator`). | Uses **internal iteration** managed by the Stream API.                 |
| **Reusability**  | Can be traversed **multiple times**.                        | A stream can be **consumed only once**.                                |
| **Performance**  | Best for **data storage and retrieval**.                    | Best for **filtering, mapping, aggregation, and parallel processing**. |

**How It Works**

* A **Collection** holds objects in memory (e.g., `List`, `Set`, `Map`).
* A **Stream** takes data from a collection or another source and performs operations like **`filter()`**, **`map()`**, and **`collect()`**.

**When to Use**

* Use **Collection** when you need to **store, update, or manage data**.
* Use **Stream** when you need to **process data efficiently** using functional-style operations.

**Code Example**

```java
List<String> names = List.of("Alice", "Bob", "Alex");

// Collection: stores data
System.out.println(names);

// Stream: processes data
List<String> result = names.stream()
                           .filter(name -> name.startsWith("A"))
                           .map(String::toUpperCase)
                           .toList();

System.out.println(result);   // [ALICE, ALEX]
```

```java
List<String> collection = Arrays.asList("a", "b", "c");
collection.add("d"); // Modifies collection

Stream<String> stream = collection.stream();
stream.filter(s -> s.length() > 1); // Doesn't modify collection
// stream.filter(...); // Error - stream already used
```


## 8. What are intermediate and terminal operations?

**Intermediate Operations** are operations that **transform** a stream and return **another Stream**. They are **lazy**, meaning they do **not execute immediately**.

**Terminal Operations** are operations that **produce the final result** or **perform an action**. They **trigger the execution** of the entire stream pipeline.

**How it Works**

1. Create a **Stream**.
2. Apply one or more **Intermediate Operations**.
3. Call a **Terminal Operation**.
4. The stream pipeline executes and returns the final result.

```text
Collection
    ↓
Stream
    ↓
Intermediate Operations
(filter → map → sorted)
    ↓
Terminal Operation
(collect / forEach / count)
    ↓
Result
```

**Key Features**

| **Intermediate Operations**              | **Terminal Operations**                    |
| ---------------------------------------- | ------------------------------------------ |
| Return a **Stream**                      | Return the **final result**                |
| **Lazy Execution**                       | **Triggers execution**                     |
| Can be **chained**                       | Ends the stream pipeline                   |
| Multiple intermediate operations allowed | Only **one terminal operation** per stream |

**Common Intermediate Operations**

* **filter()** – Filters elements
* **map()** – Transforms elements
* **sorted()** – Sorts elements
* **distinct()** – Removes duplicates
* **limit()** – Limits the number of elements
* **skip()** – Skips elements
* **peek()** – Performs an action while processing (mainly for debugging)

**Common Terminal Operations**

* **collect()** – Collects results into a collection
* **forEach()** – Performs an action on each element
* **count()** – Counts elements
* **reduce()** – Combines elements into one result
* **findFirst()** – Returns the first element
* **findAny()** – Returns any matching element
* **anyMatch()**, **allMatch()**, **noneMatch()** – Match operations
* **min()**, **max()** – Finds minimum or maximum value

**Example**

```java
List<String> names = List.of("John", "Alice", "Bob", "David");

List<String> result = names.stream()
        .filter(name -> name.length() > 3)   // Intermediate
        .map(String::toUpperCase)            // Intermediate
        .sorted()                            // Intermediate
        .collect(Collectors.toList());       // Terminal

System.out.println(result);
```

**Output**

```text
[ALICE, DAVID, JOHN]
```

**Lazy Execution Example**

```java
Stream.of(1, 2, 3, 4)
      .filter(n -> {
          System.out.println(n);
          return n > 2;
      });
```

**Output**

```text
No Output
```

Nothing is printed because there is **no Terminal Operation**.

Now add a terminal operation:

```java
Stream.of(1, 2, 3, 4)
      .filter(n -> {
          System.out.println(n);
          return n > 2;
      })
      .count();
```

**Output**

```text
1
2
3
4
```

The **count()** terminal operation triggers the execution.

**When to Use**

**Use Intermediate Operations when:**

* Filtering data
* Transforming objects
* Sorting elements
* Removing duplicates
* Building a processing pipeline

**Use Terminal Operations when:**

* Collecting results
* Printing data
* Counting elements
* Finding values
* Performing calculations



**Common Interview Follow-up Questions**

**Q: Why are Intermediate Operations called lazy?**

Because they **do not execute immediately**. They only execute when a **Terminal Operation** is invoked.

**Q: Can a Stream have multiple Intermediate Operations?**

Yes. You can chain **multiple Intermediate Operations** before calling a **Terminal Operation**.

```java
list.stream()
    .filter(...)
    .map(...)
    .sorted()
    .collect(Collectors.toList());
```

**Q: Can a Stream have multiple Terminal Operations?**

No. A **Stream can have only one Terminal Operation**. After it is executed, the stream is **consumed** and cannot be reused.

```java
Stream<String> stream = List.of("A", "B").stream();

stream.count();      // OK
stream.forEach(System.out::println); // Throws IllegalStateException
```

**Q: Which operation starts the execution of a Stream?**

A **Terminal Operation** starts the execution of the entire stream pipeline.



## 9. What is the difference between map() and flatMap()?

`map()` is used to **transform each element** in a stream into another form. It returns **one output for each input**, so the structure of the stream stays the same.

`flatMap()` is used when each element produces **another stream or collection**. It **flattens** those nested streams into a **single stream**, so you don’t end up with a stream of streams.

| **Feature**     | **`map()`**                                            | **`flatMap()`**                                                     |
| --------------- | ------------------------------------------------------ | ------------------------------------------------------------------- |
| **Purpose**     | Transforms **each element** into another element.      | Transforms and **flattens nested structures** into a single stream. |
| **Output**      | Returns **one output element for each input element**. | Returns **multiple elements and merges them into one stream**.      |
| **Use Case**    | Simple data transformation.                            | Working with **nested collections or streams**.                     |
| **Result Type** | `Stream<T> → Stream<R>`                                | `Stream<T> → Stream<R>` (after flattening nested streams).          |

**How It Works**

* **`map()`** applies a function to each element and returns the transformed result.
* **`flatMap()`** applies a function that returns a **Stream**, then combines all those streams into a **single stream**.

**When to Use**

* Use **`map()`** when converting one value to another (e.g., convert names to uppercase).
* Use **`flatMap()`** when dealing with **nested lists, arrays, or streams** and you want a single flattened result.

**Code Example**

```java id="8psr6z"
List<String> names = List.of("alice", "bob");

// map() - transforms each element
List<String> upperNames = names.stream()
                               .map(String::toUpperCase)
                               .toList();

System.out.println(upperNames);   // [ALICE, BOB]

// flatMap() - flattens nested lists
List<List<String>> nested = List.of(
    List.of("A", "B"),
    List.of("C", "D")
);

List<String> flatList = nested.stream()
                              .flatMap(List::stream)
                              .toList();

System.out.println(flatList);   // [A, B, C, D]
```


## 10. What is Optional class?

**`Optional`** is a **container object** introduced in **Java 8** that can either **hold a value or be empty**. It is mainly used to **avoid `NullPointerException`** and write cleaner, safer code.

**Key Features**

* **Prevents `NullPointerException`** by handling missing values explicitly.
* Can contain **a value or no value**.
* Provides utility methods like **`of()`**, **`ofNullable()`**, **`isPresent()`**, **`orElse()`**, and **`ifPresent()`**.
* Encourages **functional and readable code**.

**How It Works**

* Create an `Optional` object using `of()` or `ofNullable()`.
* Check or process the value using methods like `isPresent()`, `ifPresent()`, or provide a default value with `orElse()`.

**When to Use**

* Use **`Optional`** as a **return type** when a method may or may not return a value.
* Use it to **avoid explicit null checks**.
* Do **not** use it for class fields or method parameters in most cases.

**Code Example**

```java id="j9q4mk"
Optional<String> name = Optional.ofNullable(getName());
String result = name.orElse("Default User");
System.out.println(result);
```

Another common example:

```java id="l2v7np"
Optional<String> name = Optional.of("Alice");

name.ifPresent(System.out::println);   // Prints: Alice
```

```java
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        String name = null;
        Optional<String> optional = Optional.ofNullable(name);

        // optional way one
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println("Value is null");
        }

        // optional way two
        System.out.println(optional.orElse("Default Value"));
    }
}
```


## 10. What is the difference between reduce() and collect()?

Both **`reduce()`** and **`collect()`** are **terminal operations** in the Java Stream API, but they are used for different purposes.

| **`reduce()`**                                                                      | **`collect()`**                                                                                |
| ----------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| Combines stream elements into **a single result**.                                  | Collects stream elements into **a collection or another container**.                           |
| Used for **aggregation** such as **sum**, **product**, **minimum**, or **maximum**. | Used to create **`List`**, **`Set`**, **`Map`**, or perform **grouping** and **partitioning**. |
| Returns a **single value** (or `Optional`).                                         | Returns a **mutable result container**.                                                        |
| Best for **mathematical** or **aggregation** operations.                            | Best for collecting and organizing data.                                                       |

**Example Using `reduce()`**

```java id="pm6jlwm"
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int sum = numbers.stream()
                         .reduce(0, Integer::sum);

        System.out.println(sum);
    }
}
```

**Output**

```text id="2ab0sl"
15
```

**Example Using `collect()`**

```java id="4kxy6e"
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Java", "Spring", "Docker");

        List<String> result = names.stream()
                                   .filter(name -> name.length() > 5)
                                   .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

**Output**

```text id="pv9d9l"
[Spring, Docker]
```

**When to Use**

* Use **`reduce()`** when you need **one final value**, such as **sum**, **maximum**, **minimum**, or **product**.
* Use **`collect()`** when you need to **store**, **group**, or **transform** stream elements into a **List**, **Set**, **Map**, or another collection.


## 10. What is the peek() operation and when to use it??

**`peek()`** is an **intermediate operation** in the Java Stream API that allows you to **inspect** or **perform an action** on each element as it passes through the stream **without modifying** the stream.

It is mainly used for **debugging** and **logging**.

**Key Features**

1. **Intermediate operation** – Executes only when a **terminal operation** is called.
2. Does **not modify** the stream elements.
3. Returns the **same stream** for further processing.
4. Mainly used for **debugging** and **logging**.

**How It Works**

1. Each element passes through **`peek()`**.
2. The specified action (such as printing) is performed.
3. The element continues through the remaining stream pipeline unchanged.

**Example**

```java id="2b0uhw"
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<String> result = Arrays.asList("Java", "Spring", "Docker")
                .stream()
                .peek(System.out::println)
                .filter(name -> name.startsWith("J"))
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

**Output**

```text id="jlwmak"
Java
Spring
Docker
[Java]
```

**When to Use**

* **Debugging** stream pipelines.
* **Logging** elements as they flow through the stream.
* Understanding how intermediate operations are executed.

**When Not to Use**

* Do **not** use **`peek()`** to **modify** elements or update external state.
* Use **`map()`** to transform elements.
* Avoid using **`peek()`** for business logic.


## 0. How to collect a Stream into a Map?

To collect a **Stream** into a **`Map`**, use the **`Collectors.toMap()`** method. It converts stream elements into **key-value pairs**.

**Key Features**

1. Uses **`Collectors.toMap()`**.
2. Converts stream elements into **key-value pairs**.
3. **Keys must be unique**. If duplicate keys exist, provide a **merge function**.
4. Returns a **`Map`**.

**Syntax**

```java
Collectors.toMap(keyMapper, valueMapper)
```

**Example**

```java id="0w0wfw"
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Java", "Spring", "Docker");

        Map<String, Integer> map = names.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> name.length()
                ));

        System.out.println(map);
    }
}
```

**Output**

```text
{Java=4, Spring=6, Docker=6}
```

**Handling Duplicate Keys**

```java id="5czvjy"
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Java", "Java", "Spring");

        Map<String, Integer> map = names.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> name.length(),
                        (oldValue, newValue) -> oldValue
                ));

        System.out.println(map);
    }
}
```

**Output**

```text
{Java=4, Spring=6}
```

**When to Use**

* Convert a **List** into a **Map**.
* Create **lookup tables**.
* Map an object to one of its properties.


## 0. What to do with key collisions when collecting into a Map?

A **key collision** occurs when **`Collectors.toMap()`** generates the **same key** for multiple elements. By default, this throws an **`IllegalStateException`**.

To handle duplicate keys, provide a **merge function**.

**Key Features**

1. **Duplicate keys** cause an **`IllegalStateException`** by default.
2. Use a **merge function** to resolve duplicate keys.
3. The merge function decides which value to **keep** or **combine**.

**Example (Without Merge Function)**

```java id="8es6qk"
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Java", "Java", "Spring");

        Map<String, Integer> map = names.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> name.length()
                ));
    }
}
```

**Result:** Throws **`IllegalStateException`** because the key **`Java`** appears twice.

**Example (Keep the First Value)**

```java id="66djlwm"
Map<String, Integer> map = names.stream()
        .collect(Collectors.toMap(
                name -> name,
                name -> name.length(),
                (oldValue, newValue) -> oldValue
        ));
```

**Example (Keep the Latest Value)**

```java id="gjowrf"
Map<String, Integer> map = names.stream()
        .collect(Collectors.toMap(
                name -> name,
                name -> name.length(),
                (oldValue, newValue) -> newValue
        ));
```

**Example (Combine Values)**

```java id="u8y31n"
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java", "Java", "Spring");

        Map<String, Integer> map = words.stream()
                .collect(Collectors.toMap(
                        word -> word,
                        word -> 1,
                        Integer::sum
                ));

        System.out.println(map);
    }
}
```

**Output**

```text id="qzjlwm"
{Java=2, Spring=1}
```

**When to Use**

* Keep the **first value**: **`(oldValue, newValue) -> oldValue`**
* Keep the **latest value**: **`(oldValue, newValue) -> newValue`**
* **Combine values**: Use a merge function such as **`Integer::sum`**




# ✅ 09. Java JVM & Memory Management 

## 0. What is Java Memory Model (JMM)?

**Java Memory Model (JMM)** defines how threads interact with memory and ensures that changes made by one thread become visible to other threads in a predictable and safe way.

**Why is JMM Needed?**

In a multithreaded application:

* Each thread can have its own CPU cache.
* One thread may update a variable, but another thread may not see the latest value immediately.
* JMM provides rules for **visibility, ordering, and atomicity** to avoid inconsistent behavior.

**Key Concepts**

1. **Visibility** → Changes made by one thread are visible to other threads.
2. **Atomicity** → Operations happen completely or not at all.
3. **Ordering** → Instructions execute in the correct order.

`volatile` ensures that when one thread changes `running`, other threads immediately see the updated value.


```java id="kuyjci"
class SharedData {

    private volatile boolean flag = false;

    public void writer() {
        flag = true;
    }

    public void reader() {
        while (!flag) {
            // waiting
        }
        System.out.println("Flag changed");
    }
}
```

**Atomicity :** An operation completes entirely or not at all.

```java id="w6e1oi"
synchronized void increment() {
    count++;
}
```

**Ordering :** Ensures instructions execute in a predictable order using the "happens-before" relationship.

## 1. What are the different memory areas in JVM?

Java has only one memory that is JVM. and JVM memory is divided into different areas to store objects, class metadata, method execution data, and thread-specific information.

**JVM Memory Areas** manage program execution and memory:

**Easy Way to Remember**

* **Heap** → Objects
* **Stack** → Method Execution
* **Metaspace** → Class Metadata
* **PC Register** → Current Instruction
* **Native Stack** → Native Code Execution

| Memory Area             | Purpose                                                                              |
| ----------------------- | ------------------------------------------------------------------------------------ |
| **Heap**                | Stores objects and instance variables. Shared by all threads.                        |
| **Stack**               | Stores method calls, local variables, and references. Each thread has its own stack. |
| **Metaspace**           | Stores class metadata, method information, and static structures.                    |
| **PC Register**         | Stores the address of the current instruction being executed by a thread.            |
| **Native Method Stack** | Stores information for native methods written in C/C++ and called through JNI.       |


## 2. What is the difference between heap and stack?

**Heap** and **Stack** are two different memory areas in the **JVM**, and each serves a different purpose.

| Feature               | **Stack Memory**                                                   | **Heap Memory**                             |
| --------------------- | ------------------------------------------------------------------ | ------------------------------------------- |
| **Stores**            | Local variables, method calls, primitive values, object references | Objects and instance variables              |
| **Memory Allocation** | Automatic when a method is called                                  | Dynamic using **`new`** keyword             |
| **Access Speed**      | Very **fast**                                                      | Comparatively **slower**                    |
| **Thread Scope**      | **Thread-specific** (each thread has its own stack)                | **Shared** among all threads                |
| **Memory Management** | Automatically removed when method finishes                         | Managed by the **Garbage Collector (GC)**   |
| **Lifetime**          | Exists only during method execution                                | Exists until object is no longer referenced |
| **Error**             | **StackOverflowError** (deep recursion)                            | **OutOfMemoryError** (heap full)            |

**How it Works**

* When a method is called, a new **stack frame** is created in the **Stack Memory**.
* Local variables and references are stored in that stack frame.
* When an object is created using **`new`**, the actual object is stored in the **Heap Memory**, while its reference is stored in the **Stack**.
* After the method completes, the stack frame is removed automatically.
* Objects in the heap remain until the **Garbage Collector** frees the unused memory.


**When to Use**

* Use **Stack** for temporary data like **local variables** and **method execution**.
* Use **Heap** whenever you create **objects**, **arrays**, or any data that needs to live beyond a single method call.

**Example**

```java
public class MemoryExample {
    public static void main(String[] args) {
        int age = 25;                  // Stored in Stack
        Person p = new Person();       // Reference 'p' in Stack
                                      // Object 'Person' in Heap
        p.name = "John";
    }
}

class Person {
    String name;
}
```

**In the above example:**

* **`age`** is stored in the **Stack Memory**.
* Reference variable **`p`** is stored in the **Stack Memory**.
* The actual **`Person` object** and its field **`name`** are stored in the **Heap Memory**.



## 3. What is the difference between PermGen and Metaspace?

**PermGen** (Permanent Generation) is a fixed memory area in Java (up to Java 7) used to store class metadata, method information, and interned strings.

**Characteristics:**

* Fixed size (`-XX:PermSize`, `-XX:MaxPermSize`)
* Part of JVM memory
* Can cause `OutOfMemoryError: PermGen space` if full

**Metaspace** (Java 8 onwards) replaces PermGen, storing class metadata in native memory with dynamic sizing, improving memory management.
* Fixed size (`-XX:PermSize`, `-XX:MaxMetaspaceSize`)


| Feature             | **PermGen**                                          | **Metaspace**                               |
| ------------------- | ---------------------------------------------------- | ------------------------------------------- |
| **Available In**    | Java 7 and earlier                                   | Java 8 and later                            |
| **Memory Location** | Part of the **JVM Heap**                             | Uses **Native (OS) Memory**                 |
| **Stores**          | Class metadata, static variables, method information | Class metadata and method information       |
| **Size**            | Fixed, manually configured                           | Dynamically grows by default                |
| **Configuration**   | `-XX:MaxPermSize`                                    | `-XX:MaxMetaspaceSize`                      |
| **Common Error**    | **`java.lang.OutOfMemoryError: PermGen space`**      | **`java.lang.OutOfMemoryError: Metaspace`** |
| **Performance**     | More frequent tuning required                        | Better memory management and less tuning    |

**Key Features**

* **PermGen** had a **fixed size**, so applications with many dynamically loaded classes could run out of memory.
* **Metaspace** uses **native memory**, allowing it to grow automatically as needed.
* **Metaspace** reduces memory tuning and improves JVM flexibility.

**How it Works**

* When the JVM loads a class, it stores the class metadata (class name, methods, fields, etc.).
* In **Java 7**, this metadata was stored in **PermGen**.
* In **Java 8+**, it is stored in **Metaspace**, which allocates memory from the operating system instead of the heap.


## 4. What is garbage collection?

**Garbage Collection** is an automatic memory management process in **Java** that removes objects that are no longer being used by the application and frees their memory.

**Key Features**

* **Automatic Memory Management**
* Managed by the **JVM**
* Removes unused objects
* Helps prevent **Memory Leaks**
* Improves application performance and stability

**How It Works**

The **Garbage Collector** periodically checks objects in memory.

* If an object is still referenced, it remains in memory.
* If an object is no longer referenced, it becomes eligible for **Garbage Collection**.
* The JVM reclaims the memory used by that object.

**Code Example**

```java
public class Main {
    public static void main(String[] args) {
        String str = new String("Hello");

        str = null; // Object becomes eligible for GC
        System.gc(); // Request JVM to run GC
    }
}
```


**When to Use**

**Garbage Collection** runs automatically when the JVM determines that memory needs to be reclaimed. Developers do not usually manage memory manually in Java.


## 5. What are the types of garbage collectors?

In Java, **Garbage Collectors (GC)** are responsible for **automatically reclaiming memory** used by objects that are no longer needed. Java provides several types of garbage collectors.


Java provides different **Garbage Collectors (GCs)**, each optimized for different use cases.

**1. Serial Garbage Collector**

* Uses a **single thread** for garbage collection.
* Suitable for small applications with low memory usage.
* Simple and low overhead.

JVM Option:

```bash
-XX:+UseSerialGC
```

**2. Parallel Garbage Collector**

* Uses **multiple threads** for garbage collection.
* Focuses on maximizing **throughput**.
* Default GC in older Java versions.

JVM Option:

```bash
-XX:+UseParallelGC
```

**3. G1 Garbage Collector (Garbage First)**

* Divides the heap into regions.
* Prioritizes regions with the most garbage.
* Balances throughput and low pause times.
* Default GC since **Java 9**.

JVM Option:

```bash
-XX:+UseG1GC
```

**4. Z Garbage Collector (ZGC)**

* Designed for very large heaps.
* Provides extremely low pause times.
* Suitable for large-scale applications.

JVM Option:

```bash
-XX:+UseZGC
```

**5. Shenandoah Garbage Collector**

* Focuses on low pause times.
* Performs most GC work concurrently with application threads.
* Suitable for applications requiring high responsiveness.

JVM Option:

```bash
-XX:+UseShenandoahGC
```

**Summary Table**

| Garbage Collector | Threads    | Pause Time     | Use Case                   |
| ----------------- | ---------- | -------------- | -------------------------- |
| Serial GC         | Single     | Stop-the-world | Small apps, single CPU     |
| Parallel GC       | Multiple   | Stop-the-world | High throughput apps       |
| CMS GC            | Concurrent | Low-latency    | Responsive apps            |
| G1 GC             | Concurrent | Low-medium     | Large heaps, low pause     |
| ZGC               | Concurrent | Very low       | Very large heaps, scalable |
| Shenandoah GC     | Concurrent | Very low       | Low-latency applications   |


## 6. What is generational garbage collection?


**Generational Garbage Collection (GC)** is a JVM memory management technique that divides the **Heap Memory** into different generations because **most objects die young**.

**Key Features**

* **Heap** is divided into:

  * **Young Generation** (Eden + Survivor S0 + Survivor S1)
  * **Old (Tenured) Generation**
* Objects are first created in the **Eden Space**.
* Short-lived objects are removed quickly using **Minor GC**.
* Long-lived objects are moved (promoted) to the **Old Generation**.
* **Major/Full GC** cleans the Old Generation.

**How it Works**

1. A new object is created and stored in the **Eden Space**.
2. When Eden becomes full, a **Minor GC** runs.
3. Objects that are still in use survive and move to the **Survivor Space**.
4. After surviving several GC cycles, they are promoted to the **Old Generation**.
5. When the Old Generation becomes full, a **Major GC** or **Full GC** is triggered to reclaim memory.

**Memory Flow**

```text
Eden  →  Survivor (S0/S1)  →  Old Generation
   Minor GC         Minor GC        Major/Full GC
```


**When to Use**

* Used automatically by the **JVM** in almost all Java applications.
* Especially beneficial for applications that create many temporary objects, such as **web applications**, **microservices**, and **high-throughput systems**.

**Code Example**

```java
public class GCExample {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String str = new String("Java GC"); // Short-lived object
        }
        System.out.println("Objects created");
    }
}
```



## 7. What is the difference between minor GC and major GC?

A **Minor GC** occurs in the **Young Generation** of the heap and cleans up short-lived objects like temporary variables. It happens frequently and is usually very fast, causing minimal pause.

A **Major GC** (also called **Full GC**) runs on the **Old Generation** and removes long-lived objects that are no longer needed. It happens less often but takes more time and can significantly impact application performance.


```java
// Objects that survive multiple minor GCs get promoted to Old Generation
List<String> longLived = new ArrayList<>(); // Eventually moves to Old Gen
String temp = "temporary"; // Likely collected in minor GC
```

## 8. What are GC roots?

**GC Roots** are special references from which the **Garbage Collector (GC)** starts checking which objects are still **reachable** in memory. Any object that can be reached from a GC Root is **not eligible for garbage collection**.

**Key Features**

* **Starting point** for GC traversal.
* Objects reachable from GC Roots are **alive**.
* Unreachable objects become **eligible for garbage collection**.
* Helps JVM identify **unused memory**.

**Common GC Roots in Java**

* **Local variables** in active methods (stack references).
* **Static variables**.
* **Active threads**.
* **JNI (Native) references**.

**How It Works**

1. GC starts from all **GC Roots**.
2. It traverses all referenced objects.
3. Reachable objects are marked as **live**.
4. Unreachable objects are considered **garbage** and can be removed.

**When to Use**

* Understanding **Garbage Collection** internals.
* Analyzing **memory leaks**.
* Debugging JVM memory issues.

**Code Example**

```java
public class GCRootExample {
    static Object staticObj = new Object(); // GC Root
    public static void main(String[] args) {
        Object localObj = new Object(); // GC Root (local variable)

        localObj = null; // No longer referenced
    }
}
```


## 2. **What are `-Xms` and `-Xmx` Parameters?**

**`-Xms`** and **`-Xmx`** are **JVM Heap Memory** settings that control how much memory is allocated to a Java application.

**`-Xms` (Initial Heap Size)**

* Defines the **initial heap memory** allocated when the application starts.
* Example: `-Xms512m` starts the JVM with **512 MB** of heap memory.

**`-Xmx` (Maximum Heap Size)**

* Defines the **maximum heap memory** the JVM can use.
* The JVM **cannot allocate more heap** than this limit.
* Example: `-Xmx2g` allows the JVM to use up to **2 GB** of heap memory.

**Example**

```bash
java -Xms512m -Xmx2g MyApplication
```

**Explanation**

* **`-Xms512m`** → JVM starts with **512 MB** of heap memory.
* **`-Xmx2g`** → JVM can grow the heap up to **2 GB** if needed.

**Why are they important?**

* **`-Xms`** avoids frequent heap expansion at startup.
* **`-Xmx`** prevents the application from using unlimited memory.
* Proper values help improve **performance** and **memory management**.



## 2. **What happens on `OutOfMemoryError`?**

An **`OutOfMemoryError (OOM)`** occurs when the **JVM** cannot allocate more memory because the **Heap**, **Metaspace**, or another memory area has reached its limit and **Garbage Collection (GC)** cannot free enough memory.

**What happens when it occurs?**

1. The **JVM** tries to free memory by running **Garbage Collection (GC)**.
2. If there is still **not enough memory**, it throws an **`OutOfMemoryError`**.
3. If the error is **not handled**, the application may **crash** or stop working correctly.

**Common Causes**

* **Memory Leak** (objects are no longer needed but are still referenced)
* Creating **too many objects**
* **Heap size (`-Xmx`)** is too small
* Loading **too many classes** (Metaspace exhaustion)

**Example**

```java
import java.util.ArrayList;
import java.util.List;

public class OOMExample {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        while (true) {
            list.add(new byte[1024 * 1024]); // Allocate 1 MB repeatedly
        }
    }
}
```

Run with a small heap:

```bash
java -Xmx32m OOMExample
```

**Output:**

```text
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
```

**How to Prevent It**

* **Fix memory leaks**
* Remove **unused object references**
* Use **efficient data structures**
* Increase **Heap Size (`-Xmx`)** if needed
* Analyze memory usage with **Heap Dumps** and **Profiling Tools**



## 2. **What types of `OutOfMemoryError` exist?**

**`OutOfMemoryError`** has several types depending on **which JVM memory area is exhausted**.

**Common Types of `OutOfMemoryError`**

1. **`Java heap space`**

   * The **Heap Memory** is full.
   * Usually caused by **memory leaks**, creating **too many objects**, or a **small `-Xmx`**.

2. **`GC overhead limit exceeded`**

   * The **Garbage Collector (GC)** spends almost all its time cleaning memory but frees very little.
   * Usually indicates the application is running **out of heap memory**.

3. **`Metaspace`**

   * The **Metaspace** is full.
   * Usually caused by loading **too many classes** or **class loader leaks**.

4. **`Unable to create new native thread`**

   * The JVM cannot create more **threads**.
   * Usually caused by creating **too many threads** or reaching the **OS thread limit**.

5. **`Direct buffer memory`**

   * The JVM runs out of **Direct (Off-Heap) Memory**.
   * Usually caused by excessive use of **`ByteBuffer.allocateDirect()`**.

**Example**

```java
import java.util.ArrayList;
import java.util.List;

public class HeapOOMExample {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        while (true) {
            list.add(new byte[1024 * 1024]); // Allocate 1 MB repeatedly
        }
    }
}
```

Run with a small heap:

```bash
java -Xmx32m HeapOOMExample
```

**Output**

```text
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
```


## 2. **What is a Memory Leak and How to Detect It?**

A **Memory Leak** happens when **objects are no longer needed but are still referenced**, so the **Garbage Collector (GC)** cannot remove them. As a result, **Heap Memory** keeps growing and may eventually cause an **`OutOfMemoryError`**.

**Common Causes**

1. **Unused objects** are still stored in **Collections**.
2. **Static variables** keep references for the entire application lifetime.
3. **Listeners** or **callbacks** are registered but never removed.
4. Resources are not properly **closed**.

**Example**

```java
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakExample {
    private static final List<byte[]> cache = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            cache.add(new byte[1024 * 1024]); // Objects are never removed
        }
    }
}
```

Here, the **static List** keeps references to all objects, so the **GC** cannot free them.

**How to Detect a Memory Leak?**

1. Monitor **Heap Memory** usage.
2. Generate a **Heap Dump** when memory usage is high.
3. Analyze the **Heap Dump** using tools like **Eclipse MAT**, **VisualVM**, or **JProfiler**.
4. Look for objects that **keep growing** or are **unexpectedly retained**.

**How to Prevent It?**

* Remove **unused references**.
* Clear **Collections** when they are no longer needed.
* Close resources using **try-with-resources**.
* Avoid unnecessary **static references**.
* Remove **listeners** when they are no longer used.



## 2. **What Tools Help Analyze Memory?**

Several tools help analyze **JVM Memory**, detect **Memory Leaks**, and troubleshoot **OutOfMemoryError** issues.

**Common Memory Analysis Tools**

1. **VisualVM**

   * Monitors **Heap Memory**, **Threads**, **CPU**, and **Garbage Collection (GC)**.
   * Can capture and analyze **Heap Dumps**.

2. **Eclipse Memory Analyzer (MAT)**

   * Analyzes **Heap Dumps** to find **Memory Leaks**.
   * Shows which objects consume the most memory and why they are retained.

3. **JConsole**

   * Monitors **Heap Usage**, **GC**, **Threads**, and **JVM Performance** using **JMX**.

4. **JProfiler**

   * Commercial **JVM Profiler**.
   * Analyzes **Memory**, **CPU**, **Threads**, and **Heap Usage**.
   * Helps identify **Memory Leaks** and performance bottlenecks.

5. **Java Flight Recorder (JFR)** and **Java Mission Control (JMC)**

   * Built into the **JDK**.
   * Records **Memory**, **GC**, **CPU**, and **Thread** events with low overhead.
   * Used for production performance analysis.

**Example**

Generate a **Heap Dump** when an **`OutOfMemoryError`** occurs:

```bash
java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap.hprof MyApplication
```

Then open **`heap.hprof`** in **Eclipse MAT** or **VisualVM** to analyze memory usage.



## 2. **What is a Heap Dump and How to Get a Heap Dump?**

A **Heap Dump** is a **snapshot of the JVM Heap Memory** at a specific moment. It contains information about **all objects**, their **references**, and the **memory they occupy**. It is mainly used to analyze **Memory Leaks** and **OutOfMemoryError**.

**How to Get a Heap Dump?**

1. **Automatically** when an **`OutOfMemoryError`** occurs.

```bash
java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap.hprof MyApplication
```

2. **Using `jmap`**

```bash
jmap -dump:live,format=b,file=heap.hprof <PID>
```

* **`live`** → Dumps only **live objects**.
* **`<PID>`** → Process ID of the running Java application.

3. **Using `jcmd`** (Recommended)

```bash
jcmd <PID> GC.heap_dump heap.hprof
```

4. **Using VisualVM**

* Connect to the running **JVM**.
* Click **Heap Dump**.
* Save the generated **`.hprof`** file.

**How to Analyze It?**

Open the **`.hprof`** file in tools such as:

* **Eclipse MAT**
* **VisualVM**
* **JProfiler**

These tools help identify **Memory Leaks**, **large objects**, and **retained memory**.


## 2. **What is Reachability in the Context of GC?**

**Reachability** means whether an object can still be **accessed** through a chain of references starting from a **GC Root**.

* If an object is **reachable**, it is **alive** and **will not** be garbage collected.
* If an object is **unreachable**, it becomes **eligible for Garbage Collection**.

**Example**

```java
public class ReachabilityExample {
    public static void main(String[] args) {
        Object obj = new Object();

        obj = null; // Object becomes unreachable
    }
}
```

After **`obj = null`**, the object has **no references** from any **GC Root**, so it is **eligible for Garbage Collection**.



## 2. **Can You Manually Invoke GC?**

**Yes**, you can **request** the JVM to run **Garbage Collection** using:

* **`System.gc()`**
* **`Runtime.getRuntime().gc()`**

However, these methods only **request** GC. The **JVM is not required** to run it immediately and may **ignore the request**.

**Example**

```java
public class GCExample {
    public static void main(String[] args) {
        System.gc(); // Requests Garbage Collection
    }
}
```


## 2. **Why Shouldn't You Call `System.gc()`?**

In most applications, you **should not** call **`System.gc()`** because the **JVM's Garbage Collector** is better at deciding **when to run GC**.

**Reasons**

1. **Only a Request**

   * The JVM may **ignore** the request.

2. **Performance Impact**

   * Running **GC** can **pause** the application and reduce performance.

3. **JVM Optimizes GC**

   * Modern **GC algorithms** automatically choose the best time to collect memory.

4. **Usually Unnecessary**

   * Frequent calls to **`System.gc()`** rarely improve memory usage and may even make performance worse.




# ✅ 10. Java Multithreading & Synchronization 

## 1. What is thread and what are life cycle?

A **Thread** is a lightweight sub-process in Java that allows **concurrent execution** of tasks within a program. Multiple threads can run inside a single process sharing the same memory.

**Key Features of Thread**

* Enables **parallel execution**
* Shares **common memory space**
* Lightweight compared to processes
* Managed by **JVM and OS**
* Supports multitasking

**Thread Life Cycle**

A Java thread goes through different **states** during execution:

1. **New :** Thread is created but not started
2. **Runnable :** Thread is ready to run and waiting for CPU
3. **Running :** Thread is actively executing
4. **Blocked / Waiting :** Thread is waiting for resource or signal
5. **Terminated (Dead) :**  Thread has completed execution

**How It Works**

* A thread is created using **Thread class** or **Runnable interface**
* It enters **New state**
* After calling `start()`, it moves to **Runnable**
* CPU scheduler moves it to **Running**
* It may move between **Waiting/Blocked**
* After completion, it enters **Terminated state**



**When to Use Threads**

* Web servers handling multiple requests
* Background tasks
* File processing
* Real-time systems
* Parallel computations


**Example**

```java id="th1a2b"
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // Moves thread to Runnable state
    }
}
```

**Thread Life Cycle Diagram (Text View)**
```
New → Runnable → Running → Waiting/Blocked → Running → Terminated
```

## 2. How do you create threads in Java?

There are two main ways to create **threads** in Java: **extending Thread** class or implementing **Runnable interfac**e.

**Method 1: Extending Thread** means creating a new class that inherits from the `Thread` class to define the task in its `run()` method.

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}
MyThread t = new MyThread();
t.start();
```

**Method 2: Implementing Runnable** means creating a class that implements the `Runnable` interface and defines the task in its `run()` method, which can then be executed by a `Thread` object.

```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Task running");
    }
}
Thread t = new Thread(new MyTask());
t.start();
```

**Method3: Implementing Callable** is an interface and similar to Runnable but returns a result and can throw checked exceptions.

```java
// import java.util.concurrent.*;

// Callable works with:: ExecutorService + Future

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Callable Task Completed";
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new MyCallable());

        String result = future.get();
        System.out.println(result);
        service.shutdown();
    }
}
```

## 2. What is the difference between Thread and Runnable?

Both **`Thread`** and **`Runnable`** are used to create **multithreaded applications** in Java, but they have different responsibilities.

* **`Thread`** represents the **actual thread of execution**.
* **`Runnable`** represents **only the task** to be executed by a thread.

**Key Differences**

| **Thread**                                                                | **Runnable**                                                              |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| Represents a **thread of execution**.                                     | Represents a **task** to be executed.                                     |
| Created by **extending the `Thread` class**.                              | Created by **implementing the `Runnable` interface**.                     |
| Cannot extend another class because Java supports **single inheritance**. | Can still extend another class because it is an interface.                |
| Task and thread are **tightly coupled**.                                  | Task and thread are **separated**.                                        |
| Less flexible and less reusable.                                          | More flexible and reusable.                                               |
| Can be executed only by starting the thread.                              | Can be executed by a **Thread**, **ExecutorService**, or **Thread Pool**. |


## 2. Difference between Runnable and Callable interface?

**Runnable** is used when a task **does not return a result** and **cannot throw checked exceptions**. It has the **run()** method with a **void** return type.

**Callable** is used when a task **returns a result** or **throws checked exceptions**. It has the **call()** method, is executed using an **ExecutorService**, and the result is retrieved through a **Future** object.

| **Feature**          | **Runnable**                    | **Callable**                    |
| -------------------- | ------------------------------- | ------------------------------- |
| **Package**          | `java.lang`                     | `java.util.concurrent`          |
| **Method**           | `run()`                         | `call()`                        |
| **Return Type**      | `void`                          | Returns a value (`V`)           |
| **Throws Exception** | Cannot throw checked exceptions | Can throw checked exceptions    |
| **Result**           | No result                       | Returns result using **Future** |
| **Introduced In**    | Java 1.0                        | Java 5                          |
| **Executed By**      | `Thread` or `ExecutorService`   | `ExecutorService`               |

**Key Features**

**Runnable**

* **Does not return** any value.
* **Cannot throw** checked exceptions.
* Can be executed using **Thread** or **ExecutorService**.
* Best for **fire-and-forget** tasks.

**Callable**

* **Returns** a result.
* **Can throw** checked exceptions.
* Executed using **ExecutorService**.
* Result is obtained through a **Future** object.

**How It Works**

* **Runnable**

  1. Implement the **Runnable** interface.
  2. Override the **run()** method.
  3. Execute using **Thread** or **ExecutorService**.

* **Callable**

  1. Implement the **Callable** interface.
  2. Override the **call()** method.
  3. Submit to **ExecutorService** using `submit()`.
  4. Get the result using **Future.get()**.

**When to Use**

Use **Runnable** when:

* You **do not need a return value**.
* The task is simple, like **logging**, **sending emails**, or **background processing**.

Use **Callable** when:

* You **need a result** from the task.
* The task may **throw checked exceptions**.
* You need **asynchronous computation**.

**Runnable Example**

```java
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable Task");
    }
}

public class Demo {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyTask());
        thread.start();
    }
}
```

**Callable Example**

```java
import java.util.concurrent.*;

class MyTask implements Callable<Integer> {
    @Override
    public Integer call() {
        return 100;
    }
}

public class Demo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit(new MyTask());

        System.out.println(future.get()); // 100

        executor.shutdown();
    }
}
```

## 4. What are sleep() vs wait() in java?

* **sleep()** is a method of the **Thread class** used to pause execution of a thread for a fixed time.

* **wait()** is a method of the **Object class** used for inter-thread communication, where a thread waits until it is notified.


| **Feature**                            | **`sleep()`**                          | **`wait()`**                                                       |
| -------------------------------------- | -------------------------------------- | ------------------------------------------------------------------ |
| **Class**                              | **`Thread`**                           | **`Object`**                                                       |
| **Purpose**                            | Pause thread for a specific time       | Wait for another thread's notification                             |
| **Lock Release**                       | **Does not release** the lock          | **Releases** the lock                                              |
| **Must be inside synchronized block?** | **No**                                 | **Yes**                                                            |
| **How Thread Resumes**                 | Automatically after the specified time | After **`notify()`** or **`notifyAll()`** and reacquiring the lock |
| **Thread State**                       | **TIMED_WAITING**                      | **WAITING** (or **TIMED_WAITING** if timeout is used)              |
| **Used For**                           | Time delay                             | Inter-thread communication                                         |


**sleep() – Key Features**

* Belongs to **Thread class**
* Pauses thread for a **fixed time**
* Does not release **locks**
* Does not require synchronization
* Throws **InterruptedException**

**How it Works**

* Thread is paused for given milliseconds
* After time expires, it resumes automatically


**wait() – Key Features**

* Belongs to **Object class**
* Used for **inter-thread communication**
* Releases **lock (monitor)**
* Requires **synchronized block**
* Needs `notify()` or `notifyAll()` to resume

**How it Works**

* Thread releases lock and enters **waiting state**
* Another thread calls `notify()` / `notifyAll()`
* Waiting thread resumes execution


**Example: sleep()**

```java id="sl1a2b"
public class Main {
    public static void main(String[] args) {

        System.out.println("Start");

        try {
            Thread.sleep(2000); // pauses thread for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End");
    }
}
```


**Example: wait() / notify()**

```java id="wt3c4d"
class Shared {
    synchronized void waitMethod() {
        try {
            System.out.println("Waiting...");
            wait(); // releases lock
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Resumed");
    }

    synchronized void notifyMethod() {
        System.out.println("Notifying...");
        notify(); // wakes waiting thread
    }
}

public class Main {
    public static void main(String[] args) {
        Shared obj = new Shared();
        new Thread(obj::waitMethod).start();
        new Thread(() -> {
            try { Thread.sleep(1000); } catch (Exception e) {}
            obj.notifyMethod();
        }).start();
    }
}
```


## 3. What are notify() vs notifyAll() in java? 


Both **`notify()`** and **`notifyAll()`** are methods of the **`Object`** class used for **inter-thread communication**. They wake up threads that are waiting using the **`wait()`** method.

**notify()** wakes up **only one** waiting thread. The thread to wake up is **chosen by the JVM**, so you cannot control which one gets notified.

**notifyAll()** wakes up **all waiting threads**. They all become runnable, but only **one thread at a time** can acquire the lock and continue.

**Difference Table**

| **Feature**          | **`notify()`**                            | **`notifyAll()`**                         |
| -------------------- | ----------------------------------------- | ----------------------------------------- |
| **Threads Woken Up** | Wakes **one** waiting thread              | Wakes **all** waiting threads             |
| **Thread Selection** | **JVM** chooses one thread                | All waiting threads are awakened          |
| **Lock Release**     | **Does not release** the lock immediately | **Does not release** the lock immediately |
| **Performance**      | Faster                                    | Slower (more context switching)           |
| **Use Case**         | Only one thread needs to continue         | Multiple threads may need to continue     |

**How it Works**

#**`notify()`**

* Wakes **one** thread waiting on the object's monitor.
* The **JVM** decides which thread to wake.
* The awakened thread **cannot continue immediately**. It must first **reacquire the lock** after the synchronized block ends.

#**`notifyAll()`**

* Wakes **all** threads waiting on the object's monitor.
* All awakened threads become **eligible** to run.
* They **compete for the same lock**, and only one thread acquires it first. The others continue waiting until the lock is available.

**Example**

```java
class SharedResource {
    synchronized void waitForSignal() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is waiting");
        wait();
        System.out.println(Thread.currentThread().getName() + " resumed");
    }

    synchronized void signalOne() {
        notify();      // Change to notifyAll() to wake all threads
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        SharedResource resource = new SharedResource();
        Runnable task = () -> {
            try {
                resource.waitForSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(task, "Thread-1").start();
        new Thread(task, "Thread-2").start();
        new Thread(task, "Thread-3").start();

        Thread.sleep(1000);

        resource.signalOne();
    }
}
```

**Output**

**Using `notify()`**

```text
Thread-1 is waiting
Thread-2 is waiting
Thread-3 is waiting
Thread-2 resumed
```

**Using `notifyAll()`**

```text
Thread-1 is waiting
Thread-2 is waiting
Thread-3 is waiting
Thread-1 resumed
Thread-2 resumed
Thread-3 resumed
```

**Common Interview Follow-up Questions**

**1. Which class defines `notify()` and `notifyAll()`?**
**Answer:** Both methods are defined in the **`Object`** class.

**2. Can `notify()` or `notifyAll()` be called outside a synchronized block?**
**Answer:** **No.** Otherwise, **`IllegalMonitorStateException`** is thrown.

**3. Does `notify()` immediately wake up and run the thread?**
**Answer:** **No.** It only makes the thread **eligible** to run. The thread must first **reacquire the lock**.

**4. Why is `notifyAll()` considered safer than `notify()`?**
**Answer:** Because all waiting threads wake up and **recheck the condition**, avoiding **missed notifications** and **thread starvation**.

**5. What is the relationship between `wait()`, `notify()`, and `notifyAll()`?**
**Answer:** A thread calls **`wait()`** to release the lock and wait. Another thread calls **`notify()`** to wake **one** waiting thread or **`notifyAll()`** to wake **all** waiting threads.

## 3. How JVM Determines Which Thread Should Wake Up on `notify()`?


The **JVM does not guarantee** which waiting thread will wake up when **`notify()`** is called.

If **multiple threads** are waiting on the same object's monitor, the **JVM and Operating System's thread scheduler** choose **one** waiting thread. The selection is **implementation-dependent** and **unpredictable**.

**Key Features**

* **`notify()`** wakes **only one** waiting thread.
* The **JVM does not follow FIFO, LIFO, or priority order**.
* The selection depends on the **JVM implementation** and **OS thread scheduler**.
* The awakened thread becomes **RUNNABLE**, but it must **reacquire the lock** before executing.
* Therefore, **you should never write code that depends on which thread `notify()` wakes up**.

**How it Works**

1. Multiple threads call **`wait()`** on the same object.
2. They enter the **WAITING** state.
3. Another thread enters a **synchronized** block and calls **`notify()`**.
4. The **JVM randomly (implementation-dependent)** selects **one** waiting thread.
5. That thread becomes **RUNNABLE**.
6. It continues execution **only after** it successfully **reacquires the object's lock**.

**Example**

```java
class Shared {
    synchronized void waitForSignal() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is waiting");
        wait();
        System.out.println(Thread.currentThread().getName() + " resumed");
    }

    synchronized void signal() {
        notify();
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Shared shared = new Shared();
        Runnable task = () -> {
            try {
                shared.waitForSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(task, "Thread-1").start();
        new Thread(task, "Thread-2").start();
        new Thread(task, "Thread-3").start();

        Thread.sleep(1000);
        shared.signal();
    }
}
```

**Possible Output**

```text
Thread-1 is waiting
Thread-2 is waiting
Thread-3 is waiting
Thread-2 resumed
```

**Note:** On another execution, **Thread-1** or **Thread-3** may resume instead.


**Common Interview Follow-up Questions**

**1. Does `notify()` wake the first thread that called `wait()`?**
**Answer:** **No.** There is **no FIFO guarantee**.

**2. Does thread priority affect which thread `notify()` wakes?**
**Answer:** **No guarantee.** Thread priority may influence scheduling after a thread becomes runnable, but it **does not determine** which waiting thread `notify()` selects.

**3. Can the same program wake different threads on different executions?**
**Answer:** **Yes.** The selected thread can vary between runs.

**4. Why is `notifyAll()` often preferred over `notify()`?**
**Answer:** Because it wakes **all waiting threads**, allowing each to **recheck its condition**, which helps avoid **missed notifications** and **thread starvation**.

**5. Does the awakened thread execute immediately after `notify()`?**
**Answer:** **No.** It first becomes **RUNNABLE** and can continue only after **reacquiring the object's lock**.


## 3. Execute 10 Thread. 

**Execute Thread Without any scequence**
```java
class ThreadExample {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            int id = i;

            Thread t = new Thread(() -> {
                System.out.println("Task " + id);
            });

            t.start();
        }
    }
}
```

**Execute Thread in scequence**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 5; i++) {
            int index = i;

            executor.submit(() -> {
                System.out.println("Task-" + Thread.currentThread().getName());
                try { Thread.sleep(500); } catch (Exception e) {}
                System.out.println("End Task-" + index);
            });
        }

        executor.shutdown();
    }
}
```


```java
for(int i = 0; i < 5; i++) {
    int threadId = i;
    Object lock = new Object();
    Thread tr = new Thread(() -> {
        synchronized(lock) {
            System.out.println(threadId +"---"+ Thread.currentThread().getName());
        }
    });
    tr.start();
    tr.join();      
}
// Output::
// 0---Thread-1
// 1---Thread-2
// 2---Thread-3
// 3---Thread-4
// 4---Thread-5
```


## 4. When we have 5 threads, in which sequence will they execute? Will I get the same result every time?


* Threads run **concurrently**, not strictly one after another
* The CPU decides which thread runs first
* Execution order can change every time you run the program

```java
for (int i = 1; i <= 5; i++) {
    new Thread(() -> {
        System.out.println(Thread.currentThread().getName());
    }).start();
}

// Possible Outputs (different each time):
Thread-2
Thread-1
Thread-4
Thread-0
Thread-3

// OR ===

Thread-0
Thread-3
Thread-1
Thread-2
Thread-4
```

**When We want same result every time?**

✔️ Only if you control execution using:

* `synchronized`
* `Lock`
* `join()`
* `ExecutorService` (single thread)



**Using `synchronized` (Avoid race condition)**

👉 Ensures only one thread modifies shared data at a time

```java
class Counter {
    int count = 0;

    synchronized void increment() {
        count++;
    }
}

public class SyncExample {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) counter.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.count);
    }
}
// Output will ALWAYS be:
2000
```


**Using Single Thread Executor (Best practice)**

👉 Guarantees order automatically

```java
import java.util.concurrent.*;

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> System.out.println("Task 1"));
        executor.submit(() -> System.out.println("Task 2"));
        executor.submit(() -> System.out.println("Task 3"));

        executor.shutdown();
    }
}
// Output ALWAYS:

Task 1
Task 2
Task 3
```

## 5. What is synchronization in Java?

**Synchronization** in Java is a mechanism used to control **access of multiple threads** to a shared resource to prevent **data inconsistency**.

**Key Features**

* Ensures **thread safety**
* Allows only **one thread at a time** in critical section
* Prevents **race conditions**
* Can be applied using **synchronized method or block**
* Uses **object-level or class-level lock**

**How It Works**

* When a thread enters a **synchronized block/method**, it acquires a **lock (monitor)**.
* Other threads must wait until the lock is released.
* Once execution is complete, the lock is released automatically.


**When to Use**

* Shared variables in multi-threaded environment
* Banking transactions
* Inventory management
* Ticket booking systems
* Any critical section handling shared data

**Types of Synchronization**

* **Method Synchronization**
* **Block Synchronization**
* **Static Synchronization**

**Example: Synchronized Method**

```java id="sy1a2b"
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

**Example: Synchronized Block**

```java id="sy3c4d"
class Counter {
    private int count = 0;
    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
```

**How It Solves Problem**

**Without synchronization:** - Multiple threads update shared data → **inconsistent results**
**With synchronization:** - Only one thread updates data at a time → **correct output**

**Realtime Problem**
```java
// Multiple users try to withdraw money from the same account at the same time.

// Without Synchronization (Wrong Behavior)
class BankAccount {
    int balance = 1000;

    void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " withdrawing...");
            balance = balance - amount;
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

// With Synchronization (Correct Behavior)
class BankAccount {
    int balance = 1000;

    synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " withdrawing...");
            balance = balance - amount;
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

// Main Class (Simulation)
public class Test {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        Thread user1 = new Thread(() -> account.withdraw(700), "User1");
        Thread user2 = new Thread(() -> account.withdraw(700), "User2");

        user1.start();
        user2.start();
    }
}
```

## 6. What is volatile keyword?

**volatile** is a Java keyword used to make a variable always read from and written to **main memory**, ensuring **visibility of changes across threads**.


**Key Features**

* Ensures **visibility** between threads
* Prevents **thread caching issues**
* Reads/writes directly from **main memory**
* Does NOT provide **atomicity**
* Lightweight alternative to **synchronization** (for visibility only)


**How It Works**

* Normally, each thread stores a variable in its **local cache**
* With **volatile**, every read/write happens directly in **main memory**
* So all threads always see the **latest value**



**When to Use**

* Status flags (e.g., stop thread)
* Configuration updates
* Simple coordination between threads
* When only **visibility**, not atomicity, is needed


**How It Solves Problem**

Without **volatile**:

* Thread may keep using **cached value**
* Changes may not be visible

```java
class Task implements Runnable {
    boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Task is running...");
        }
        System.out.println("Task stopped");
    }
}
```

With **volatile**:

* Every thread sees **latest updated value immediately**

```java
class Task implements Runnable {
    volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Task is running...");
        }
        System.out.println("Task stopped");
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Task task = new Task();
        Thread t1 = new Thread(task);
        t1.start();

        Thread.sleep(3000);

        task.running = false; // stop thread
        System.out.println("Stopped by main thread");
    }
}
```

**What is the difference between `synchronized` and `volatile`?**

Both **`synchronized`** and **`volatile`** are used in **multithreading**, but they solve different problems.

* **`volatile`** ensures **visibility** of changes across threads.
* **`synchronized`** ensures **visibility**, **mutual exclusion**, and **thread safety**.

**Key Differences**

| **synchronized**                                                      | **volatile**                                           |
| --------------------------------------------------------------------- | ------------------------------------------------------ |
| Provides **mutual exclusion** (only one thread can access at a time). | Does **not** provide mutual exclusion.                 |
| Ensures **visibility** and **atomicity**.                             | Ensures only **visibility**.                           |
| Prevents **race conditions**.                                         | Does **not** prevent race conditions.                  |
| Can be applied to **methods** or **code blocks**.                     | Can be applied only to **variables**.                  |
| Slower due to **locking**.                                            | Faster because there is **no locking**.                |
| Best for updating **shared mutable state**.                           | Best for **status flags** or **configuration values**. |



## 7. What is Semaphore and how it works?

**A semaphore** is a synchronization mechanism used to control how many threads can access a shared resource at the same time using **permits**.

**Key Features**

* Uses **permits** to control access
* Part of **java.util.concurrent**
* Supports **binary semaphore (1 permit)** and **counting semaphore (multiple permits)**
* Prevents **resource overuse**
* Works with **acquire()** and **release()**


**How it Works**

* A Semaphore is initialized with a fixed number of **permits**
* A thread calls **acquire()** to take a permit
* If no permit is available, the thread waits (**blocked**)
* After execution, the thread calls **release()** to return the permit
* This ensures controlled **concurrent access**

**When to Use**

* Limiting **database connections**
* Controlling **API request rate**
* Managing **thread pool-like access control**
* Restricting access to **shared services or hardware**


**Code Example (Java)**

```java
import java.util.concurrent.Semaphore;
public class SemaphoreDemo {
    private static final Semaphore semaphore = new Semaphore(2); // 2 permits

    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting...");
                semaphore.acquire(); // acquire permit

                System.out.println(Thread.currentThread().getName() + " working...");
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing...");
                semaphore.release(); // release permit
            }
        };

        for (int i = 1; i <= 5; i++) {
            new Thread(task, "Thread-" + i).start();
        }
    }
}
```


## 8. What is the difference between synchronized and concurrent collections?


* **Synchronized Collections**: Thread-safe collections where **every operation is protected by a single lock**.

* **Concurrent Collections**: Thread-safe collections designed for **high concurrency** with better performance in multi-threaded environments.

* **Synchronized Collections**

  * `Collections.synchronizedList()`
  * `Collections.synchronizedMap()`
  * `Vector`
  * `Hashtable`

* **Concurrent Collections**

  * `ConcurrentHashMap`
  * `CopyOnWriteArrayList`
  * `ConcurrentLinkedQueue`
  * `BlockingQueue`



* **Synchronized Collections**

  * Every method acquires the **same lock**.
  * Other threads must wait until the lock is released.

* **Concurrent Collections**

  * Different parts of the collection can be accessed simultaneously.
  * Reduces thread contention and improves scalability.


**Key Features**

| **Feature**           | **Synchronized Collections**                                                            | **Concurrent Collections**                                                   |
| --------------------- | --------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------- |
| **Locking**           | Uses **one lock** for the entire collection                                             | Uses **multiple locks** or **lock-free** techniques                          |
| **Performance**       | **Slower** because only one thread can access at a time                                 | **Faster** because multiple threads can work simultaneously                  |
| **Read/Write Access** | Both **read** and **write** operations are synchronized                                 | Allows **concurrent reads** and efficient writes                             |
| **Iterator**          | **Fail-Fast** (throws **ConcurrentModificationException** if modified during iteration) | **Weakly Consistent** (doesn't throw exception and may reflect some updates) |
| **Scalability**       | Less suitable for high concurrency                                                      | Designed for high-concurrency applications                                   |
| **Examples**          | **Collections.synchronizedList()**, **Collections.synchronizedMap()**                   | **ConcurrentHashMap**, **CopyOnWriteArrayList**, **ConcurrentLinkedQueue**   |


**When to Use**

* Use **Synchronized Collections** when:

  * Application has **few threads**.
  * Simplicity is more important than performance.

* Use **Concurrent Collections** when:

  * Application has **many concurrent threads**.
  * High performance and scalability are required.

**Code Example**

**Synchronized Collection**

```java
List<String> list = Collections.synchronizedList(new ArrayList<>());

list.add("Java");
list.add("Spring");
```

**Concurrent Collection**

```java
ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

map.put(1, "Java");
map.put(2, "Spring");
```


## 9. What is ConcurrentHashMap and how is it different from HashMap?


**HashMap** is a **non-synchronized** collection in Java used to store **key-value pairs**. It allows **one null key** and multiple null values.

**ConcurrentHashMap** is a **thread-safe** version of HashMap designed for **high concurrency** without locking the entire map.


**Key Features**

| **Feature**       | **HashMap**                                                | **ConcurrentHashMap**                                                     |
| ----------------- | ---------------------------------------------------------- | ------------------------------------------------------------------------- |
| **Thread Safety** | **Not thread-safe**                                        | **Thread-safe**                                                           |
| **Performance**   | Faster in **single-threaded** applications                 | Optimized for **multi-threaded** applications                             |
| **Locking**       | **No locking**                                             | Uses **fine-grained locking** and **lock-free reads**                     |
| **Null Values**   | Allows **one null key** and **multiple null values**       | **Does not allow** null keys or null values                               |
| **Iterator**      | **Fail-Fast** (throws **ConcurrentModificationException**) | **Weakly Consistent** (doesn't throw exception during concurrent updates) |
| **Best Use**      | Single-threaded applications                               | Multi-threaded applications                                               |


**How It Works**

* **HashMap**

  * Stores data in **buckets** based on the key's **hash code**.
  * If multiple threads modify it simultaneously, it may produce **inconsistent data**.

* **ConcurrentHashMap**

  * Divides work internally using **fine-grained locking**.
  * Allows **multiple threads** to read and update different parts of the map at the same time.

**When to Use**

* Use **HashMap** when:

  * Only one thread accesses the map.
  * Maximum performance is needed without synchronization.

* Use **ConcurrentHashMap** when:

  * Multiple threads need to read and update data safely.
  * High concurrency and scalability are required.


**Code Example**

**HashMap Example**

```java
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");

        System.out.println(map);
    }
}
```

**ConcurrentHashMap Example**

```java
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");

        System.out.println(map);
    }
}
```

// Real-Time Use Case (Important for Interviews)
// 🧠 Scenario: Caching User Sessions
// 👉 In real applications (like backend APIs), multiple users access/update data simultaneously.

```Java
import java.util.concurrent.ConcurrentHashMap;

class UserSession {
    String userId;
    long loginTime;

    UserSession(String userId) {
        this.userId = userId;
        this.loginTime = System.currentTimeMillis();
    }
}

public class SessionManager {
    private static ConcurrentHashMap<String, UserSession> sessionCache = new ConcurrentHashMap<>();

    // Add session
    public static void login(String userId) {
        sessionCache.put(userId, new UserSession(userId));
    }

    // Get session
    public static UserSession getSession(String userId) {
        return sessionCache.get(userId);
    }

    // Remove session
    public static void logout(String userId) {
        sessionCache.remove(userId);
    }

    public static void main(String[] args) {
        login("user1");
        login("user2");

        System.out.println(getSession("user1").userId);

        logout("user1");
    }
}
// Why use ConcurrentHashMap here?
// Many users logging in/out at the same time
// No need to lock entire structure
// Faster than Collections.synchronizedMap()
```

## 10. What is deadlock and how do you prevent it?

**Deadlock** is a situation in **multithreading** where two or more threads are **waiting forever** for each other to release **locks**, causing the application to **freeze**.

**Key Features**

* Occurs in **multi-threaded environment**
* Involves **two or more threads**
* Requires **circular waiting condition**
* Threads are stuck in **blocked state permanently**
* No **automatic recovery**

**How it works**

Deadlock happens when these four conditions occur together:

* **Mutual Exclusion** → One resource can be used by only one thread
* **Hold and Wait** → Thread holds one lock and waits for another
* **No Preemption** → Locks cannot be forcibly taken away
* **Circular Wait** → Thread A waits for B, B waits for A

Example flow:

* Thread 1 locks **Resource A** and waits for **Resource B**
* Thread 2 locks **Resource B** and waits for **Resource A**
* Both threads wait forever → **Deadlock**


**When to use (real context awareness)**
Deadlock is not used directly, but you must consider it when:

* Working with **multiple locks**
* Designing **shared resource systems**
* Building **high concurrency applications**

**Code Example (Deadlock Scenario)**

```java id="deadlock1"
public class DeadlockExample {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 locked lock1");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (lock2) {
                    System.out.println("Thread 1 locked lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 locked lock2");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (lock1) {
                    System.out.println("Thread 2 locked lock1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```

**How to avoid Deadlock (Interview point)**

* Always acquire locks in **same order**
* Use **tryLock() (Lock interface)**
* Avoid **nested locks**
* Keep **lock scope minimal**


## 11. What is race condition and how to resolve it?

A **Race Condition** occurs when **two or more threads** access and modify the **same shared resource** at the same time, and the **final result depends on the order of execution**. This can lead to **unexpected or incorrect output**.

**Key Features**

* **Occurs in multithreading or concurrent programming**
* Multiple threads access **shared data**
* Results are **unpredictable** and may vary every time
* Can cause **data inconsistency** or corruption

**How it Works**

Suppose two threads try to increment the same variable:

* Initial value = `0`
* **Thread 1** reads `0`
* **Thread 2** also reads `0`
* Both increment it to `1`
* Both write back `1`

Expected result = `2`
Actual result = `1`

This happens because both threads access the variable **simultaneously**.

**How to Resolve It**

Use synchronization mechanisms to ensure that **only one thread can access the shared resource at a time**.

**Common Solutions:**

* **`synchronized` keyword**
* **`Lock` interface (`ReentrantLock`)**
* **Atomic classes (`AtomicInteger`, `AtomicLong`)**
* **Thread-safe collections (`ConcurrentHashMap`)**

**When to Use Synchronization**

Use it whenever **multiple threads share and update the same data**, such as:

* Counter variables
* Bank account balance updates
* Inventory management
* Shared collections

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

Here, the **`synchronized`** keyword ensures that **only one thread** can execute `increment()` at a time, preventing a **race condition**.


**How to Resolve Race Condition**

**1. Using synchronized**

```java
class Counter {
    int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```
**2. Using Atomic Variable (Best Practice)**

```java
import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.incrementAndGet();
    }
}
```


**3. Using Lock**

```java
import java.util.concurrent.locks.ReentrantLock;

class Counter {
    int count = 0;
    ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
}
```

## 11. Difference between a synchronized method and a synchronized block??

Both **`synchronized` methods** and **`synchronized` blocks** provide **thread safety** by allowing only one thread to execute the protected code at a time. The main difference is **how much code is locked** and **which object is used as the lock**.

**Key Differences**

| **Synchronized Method**                                                | **Synchronized Block**                                           |
| ---------------------------------------------------------------------- | ---------------------------------------------------------------- |
| Locks the **entire method**.                                           | Locks **only a specific block of code**.                         |
| Simpler to write.                                                      | More flexible.                                                   |
| Uses the **current object (`this`)** as the lock for instance methods. | Can lock **any object**.                                         |
| May reduce performance because the whole method is locked.             | Better performance because only the critical section is locked.  |
| Suitable when the **entire method** needs synchronization.             | Suitable when **only part of the method** needs synchronization. |

**How does a synchronized method work?**

When a thread enters a **synchronized method**, it acquires the object's **monitor lock**. Other threads must wait until the method finishes and the lock is released.

```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```

**How does a synchronized block work?**

Only the code inside the **synchronized block** is locked. The rest of the method can execute without holding the lock.

```java
class Counter {
    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        // Non-critical code

        synchronized (lock) {
            count++;
        }

        // Non-critical code
    }
}
```


## 12. Multithreading and Concurrency Summary?

**Thread** is the smallest unit of execution in a program that allows multiple tasks to run simultaneously.

**ThreadPool** is a group of pre-created threads used to execute tasks, which improves performance and avoids creating threads again and again.
 
**Multithreading** allows a program to run multiple threads concurrently within the same memory space, improving CPU utilization, performance, and responsiveness.

**Concurrency** is the ability of a program to execute multiple tasks at the same time by using multiple threads. These tasks can run in parallel on multiple CPU cores

**ConcurrentHashMap**  is a thread-safe implementation of Map that allows multiple threads to read and write data concurrently without locking the entire map.

**Runnable** is an interface used to create a thread in Java. It contains only one method: `run()`.

**Callable** is an interface and similar to Runnable but **returns a result** and can **throw exceptions**.

**ExecutorService** is a framework that manages threads and thread pools and execute tasks asynchronously, in the background.

**Volatile** is a keyword used in multithreading. Volatile ensures variable changes are immediately visible to all threads (prevents caching issues)

**Semaphore** is a synchronization mechanism used to control how many threads can access a shared resource at the same time.

**synchronized** is a keyword to ensures that only one thread at a time can access a shared resource, preventing race conditions to achieve thread safety.

**Lock** (ReentrantLock) is similar to synchronized but gives more control like lock, unlock, tryLock.

**CountDownLatch** is a synchronization utility that blocks threads until a set count reaches zero, using countDown() to decrement and await() to wait.

**Deadlock** is a situation where two or more threads are blocked forever waiting for each other’s resources.

**CompletableFuture** is used to run asynchronous tasks and combine multiple async operations.

**@Async**  is used in Spring Boot to execute a method asynchronously and used to run a method in **background thread** 



# ✅ 11. Java Concurrency 

## 0. What is multithreading?

**Multithreading** is a process where **multiple threads** run concurrently within the same application. It helps perform multiple tasks at the same time, improving **performance** and **resource utilization**.

**Key Features**

* **Concurrent execution** of tasks
* Threads share the same **memory space**
* Improves **CPU utilization**
* Faster execution for independent tasks
* Supports **parallel processing** on multi-core systems

**How It Works**

* A **Thread** is a lightweight sub-process.
* Multiple threads are created inside a single process.
* The **JVM Thread Scheduler** decides when each thread runs.
* Threads execute independently but share application resources.


**When to Use**

* Processing large amounts of data
* File uploads/downloads
* Background tasks
* Web servers handling multiple requests
* Real-time applications

**Example**

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // Creates a new thread
    }
}
```

**Using Runnable (Recommended)**

```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Task is running");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyTask());
        t1.start();
    }
}
```


## 1. What is **Concurrency in Java**?


**Concurrency**



**Concurrency** is the ability of a system to handle **multiple tasks at the same time** by making progress on each task. The tasks may not run simultaneously, but they are managed so that they appear to run together.

**Key Features**

* Multiple tasks are in progress at the same time
* Improves **responsiveness**
* Efficient use of **system resources**
* Supports **task switching**
* Can be achieved using **threads**, **thread pools**, or **asynchronous programming**

**How It Works**

* The CPU switches between multiple tasks.
* Each task gets a small amount of execution time.
* While one task waits (e.g., for I/O), another task can execute.
* This creates the illusion that tasks are running together.


**When to Use**

* Web applications handling multiple requests
* Database operations
* File processing
* Network communication
* Asynchronous and background tasks

**Example**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() ->
            System.out.println("Task 1 executing")
        );

        executor.submit(() ->
            System.out.println("Task 2 executing")
        );

        executor.shutdown();
    }
}
```

* **How concurrency is achieved in Java**

* **Thread & Runnable** – basic units of execution.
* **ExecutorService** – manages thread pools and asynchronous tasks.
* **Synchronization** – ensures **thread-safe access** to shared resources.
* **Locks (ReentrantLock)** – flexible locking with reentrant and fairness options.
* **Atomic variables** – **lock-free thread-safe operations** on single variables.
* **Concurrent collections** – thread-safe collections like `ConcurrentHashMap`.
* **CompletableFuture (Java 8+)** – **asynchronous computation** with callbacks and chaining.

**Concurrency vs Parallelism**

| **Concurrency**                       | **Parallelism**                           |
| ------------------------------------- | ----------------------------------------- |
| Multiple tasks make progress together | Multiple tasks run at the same time       |
| May use a single CPU core             | Requires multiple CPU cores               |
| Focuses on managing tasks             | Focuses on executing tasks simultaneously |


## 2.  What is ConcurrentHashMap and when to use?


**ConcurrentHashMap** is a **thread-safe** implementation of **Map** in Java that allows multiple threads to read and write data concurrently without causing data inconsistency.

**Key Features**

* **Thread-safe**
* High **performance** in multi-threaded applications
* Allows multiple threads to read and write simultaneously
* Does not lock the entire map for every operation
* Part of the **java.util.concurrent** package
* Does **not allow null keys or null values**

**How It Works**

* The map divides data internally into buckets.
* Multiple threads can access different buckets concurrently.
* Reads are usually performed without locking.
* Only the affected portion of the map is locked during updates, improving performance.


**When to Use**

* Caching
* Session management
* Shared application data
* High-concurrency applications
* Multi-threaded web applications

**Example**

```java
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {

        ConcurrentHashMap<Integer, String> map =
                new ConcurrentHashMap<>();

        map.put(1, "Java");
        map.put(2, "Spring");

        System.out.println(map.get(1));
    }
}
```

**ConcurrentHashMap vs HashMap**

| **HashMap**                                  | **ConcurrentHashMap**                    |
| -------------------------------------------- | ---------------------------------------- |
| Not thread-safe                              | Thread-safe                              |
| Suitable for single-threaded applications    | Suitable for multi-threaded applications |
| Faster in single-threaded use                | Optimized for concurrent access          |
| Allows one null key and multiple null values | Does not allow null keys or values       |


## 3.  What is a Thread Pool and What are the types of thread pools?


A **Thread Pool** is a collection of **pre-created worker threads** that are reused to execute multiple tasks. Instead of creating a new thread for every task, tasks are assigned to existing threads in the pool.

**Key Features**

* **Reuses threads**
* Improves **performance**
* Reduces thread creation overhead
* Better **resource management**
* Controls the number of concurrent threads
* Provided by **Executor Framework**

**How It Works**

1. Create a **Thread Pool** with a fixed or dynamic number of threads.
2. Submit tasks to the pool.
3. An available worker thread picks up and executes the task.
4. After completion, the thread returns to the pool and waits for the next task.
5. Threads are reused instead of being created repeatedly.


**When to Use**

* Web applications handling multiple requests
* Background jobs
* Batch processing
* Asynchronous tasks
* High-concurrency applications

**Example**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;

            executor.submit(() ->
                System.out.println(
                    "Executing Task " + taskId +
                    " by " + Thread.currentThread().getName()
                )
            );
        }

        executor.shutdown();
    }
}
```

**Common Types of Thread Pools**

* **FixedThreadPool** – Fixed number of threads
* **CachedThreadPool** – Creates threads as needed
* **SingleThreadExecutor** – Uses only one thread
* **ScheduledThreadPool** – Executes tasks after a delay or periodically

## 4. What is ExecutorService?

**ExecutorService** is a Java interface from the **Executor Framework** used to manage and execute asynchronous tasks using a **Thread Pool**. It simplifies thread management by handling thread creation, reuse, and shutdown automatically.

**Key Features**

* Manages a **Thread Pool**
* Executes tasks asynchronously
* Reuses existing threads
* Improves application performance
* Supports **Runnable** and **Callable** tasks
* Provides graceful thread shutdown

**How It Works**

1. Create an **ExecutorService**.
2. Submit tasks using `submit()` or `execute()`.
3. A thread from the pool executes the task.
4. After completion, the thread returns to the pool.
5. Call `shutdown()` when all tasks are finished.


**When to Use**

* Web applications
* Background processing
* Batch jobs
* Asynchronous operations
* High-concurrency systems

**Example**

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("Task 1"));
        executor.submit(() ->System.out.println("Task 2"));
        executor.shutdown();
    }
}
```

**Important Methods**

| **Method**      | **Purpose**                          |
| --------------- | ------------------------------------ |
| `execute()`     | Executes a Runnable task             |
| `submit()`      | Executes a task and returns a Future |
| `shutdown()`    | Stops accepting new tasks            |
| `shutdownNow()` | Attempts to stop all running tasks   |
| `invokeAll()`   | Executes multiple tasks              |

**ExecutorService vs Thread**

| **Thread**                   | **ExecutorService**         |
| ---------------------------- | --------------------------- |
| Manual thread management     | Automatic thread management |
| New thread per task          | Reuses threads              |
| Less efficient               | More efficient              |
| Difficult to manage at scale | Easy to manage at scale     |


## 5. Difference between Executors.newFixedThreadPool() and newCachedThreadPool()?

Both **`newFixedThreadPool()`** and **`newCachedThreadPool()`** create a **Thread Pool**, but they differ in **how they manage threads**.

* **`newFixedThreadPool()`** creates a **fixed number of threads**.
* **`newCachedThreadPool()`** creates **threads as needed** and reuses idle threads.

**Key Differences**

| **newFixedThreadPool()**                                 | **newCachedThreadPool()**                                      |
| -------------------------------------------------------- | -------------------------------------------------------------- |
| Creates a **fixed number of threads**.                   | Creates **new threads as needed**.                             |
| Uses a **fixed-size** thread pool.                       | Uses a **dynamic-size** thread pool.                           |
| Extra tasks wait in a **queue** if all threads are busy. | No task queue. If needed, new threads are created immediately. |
| Prevents creating too many threads.                      | Can create **a very large number of threads**.                 |
| Better for **predictable workloads**.                    | Better for **many short-lived asynchronous tasks**.            |

**How does `newFixedThreadPool()` work?**

* Creates a fixed number of worker threads.
* If all threads are busy, new tasks are placed in a **queue**.
* Threads are reused for future tasks.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            int task = i;
            executor.submit(() ->
                System.out.println("Task " + task +
                        " : " + Thread.currentThread().getName()));
        }
        executor.shutdown();
    }
}
```

**How does `newCachedThreadPool()` work?**

* Reuses existing idle threads.
* Creates a new thread if no idle thread is available.
* Idle threads are removed after about **60 seconds**.
* There is **no fixed upper limit** on the number of threads.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 5; i++) {
            int task = i;
            executor.submit(() ->
                System.out.println("Task " + task +
                        " : " + Thread.currentThread().getName()));
        }
        executor.shutdown();
    }
}
```

**When to use `newFixedThreadPool()`?**

* **Web servers**
* **Database operations**
* **Microservices**
* Long-running tasks
* When you want to **limit resource usage**

**When to use `newCachedThreadPool()`?**

* Many **short-lived** tasks
* Asynchronous processing
* Applications with **bursty traffic**
* When the number of concurrent tasks is unpredictable

**Advantages**

**`newFixedThreadPool()`**

* Predictable memory usage
* Prevents excessive thread creation
* Stable performance

**`newCachedThreadPool()`**

* Creates threads only when needed
* Reuses idle threads
* Good for lightweight, short tasks

**Disadvantages**

**`newFixedThreadPool()`**

* Tasks may wait in the queue if all threads are busy.

**`newCachedThreadPool()`**

* May create **too many threads**, increasing memory usage and CPU context switching under heavy load.


## 5. What is Virtual Threads in Java 21 and difference Virtual Threads over regular threads?

**Virtual Threads** are **lightweight threads** introduced in **Java 21** as part of **Project Loom**. They are managed by the **JVM** instead of the **Operating System (OS)**, allowing applications to create **millions of concurrent threads** with very low memory usage.

Unlike **Platform Threads (Regular Threads)**, which map **1:1** to OS threads, many **Virtual Threads** can run on a small number of **Platform Threads (Carrier Threads)**.

**Key Features**

* **Lightweight** and consume very little memory.
* Managed by the **JVM**, not directly by the **OS**.
* Can create **millions of concurrent threads**.
* Best for **I/O-bound** tasks such as **HTTP calls**, **database operations**, and **file I/O**.
* Simplifies concurrent programming without complex asynchronous code.

**How does it work?**

1. A **Virtual Thread** is created.
2. The **JVM** schedules it on a **Carrier Thread** (a Platform Thread).
3. If the Virtual Thread performs a blocking **I/O** operation, it is **suspended**.
4. The **Carrier Thread** is immediately reused to execute another Virtual Thread.
5. When the I/O operation completes, the Virtual Thread resumes execution.

This allows thousands or even millions of Virtual Threads to share a much smaller number of Platform Threads efficiently.

**Virtual Threads vs Regular Threads**

| **Virtual Threads**                                  | **Regular (Platform) Threads**        |
| ---------------------------------------------------- | ------------------------------------- |
| Managed by the **JVM**                               | Managed by the **Operating System**   |
| **Lightweight**                                      | **Heavyweight**                       |
| Can create **millions** of threads                   | Limited by OS resources               |
| Low memory usage                                     | Higher memory usage                   |
| Many Virtual Threads share a few **Carrier Threads** | One Java thread maps to one OS thread |
| Best for **I/O-bound** tasks                         | Better for **CPU-bound** tasks        |
| Faster thread creation                               | Slower thread creation                |
| High scalability                                     | Limited scalability                   |

**When to use Virtual Threads?**

* **REST APIs**
* **Database operations**
* **HTTP client calls**
* **File processing**
* High-concurrency server applications
* Applications with many **I/O-bound** tasks

**When not to use Virtual Threads?**

* **CPU-bound** tasks with heavy computations.
* Intensive parallel calculations where **ForkJoinPool** or a fixed **Thread Pool** is more appropriate.

**Code Example**

```java
public class VirtualThreadExample {

    public static void main(String[] args) {

        Thread.startVirtualThread(() -> {
            System.out.println("Running in: " + Thread.currentThread());
        });

        System.out.println("Main Thread");
    }
}
```

**Creating Multiple Virtual Threads**

```java
import java.util.concurrent.Executors;

public class Example {

    public static void main(String[] args) {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 1; i <= 5; i++) {
                int taskId = i;

                executor.submit(() -> {
                    System.out.println("Task " + taskId +
                            " executed by " + Thread.currentThread());
                    return null;
                });
            }
        }
    }
}
```

**Advantages**

* **Very low memory usage**
* **Highly scalable**
* Supports **millions of concurrent tasks**
* Simpler synchronous programming model
* No need for large **Thread Pools**
* Excellent for **I/O-bound** applications

**Disadvantages**

* Not ideal for **CPU-bound** tasks.
* Blocking operations that **pin** the carrier thread (e.g., some native calls or synchronized sections) can reduce scalability.


## 5. What is CountDownLatch and CyclicBarrier?

**CountDownLatch** is a synchronization utility that allows one or more threads to wait until a set of operations performed by other threads is completed.

**Key Features**

* **Thread synchronization**
* Uses a **counter**
* One-time use only
* Threads wait using `await()`
* Counter decreases using `countDown()`

**How It Works**

1. Create a latch with a count value.
2. Worker threads complete their tasks and call `countDown()`.
3. The waiting thread calls `await()`.
4. When the count reaches **0**, all waiting threads continue execution.

**When to Use**

* Application startup tasks
* Parallel data processing
* Waiting for multiple services to respond

**Example**

```java id="e4uyh6"
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws Exception {

        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            System.out.println("Task 1 done");
            latch.countDown();
        }).start();

        new Thread(() -> {
            System.out.println("Task 2 done");
            latch.countDown();
        }).start();

        latch.await();

        System.out.println("All tasks completed");
    }
}
```


**CyclicBarrier** is a synchronization utility that allows a group of threads to wait for each other at a common barrier point before continuing execution.

**Key Features**

* **Thread synchronization**
* Reusable (**cyclic**)
* All threads wait at a barrier
* Releases all threads together
* Can execute a barrier action

**How It Works**

1. Create a barrier with the number of participating threads.
2. Each thread calls `await()`.
3. Threads wait until all participants reach the barrier.
4. Once all arrive, the barrier opens and all threads continue.
5. The barrier automatically resets for reuse.


**When to Use**

* Parallel computations
* Multi-step processing
* Simulation systems
* Data aggregation tasks

**Example**

```java id="m6h4uz"
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(2);

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName()
                        + " reached barrier");

                barrier.await();

                System.out.println(Thread.currentThread().getName()
                        + " continues");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(task).start();
        new Thread(task).start();
    }
}
```


**CountDownLatch vs CyclicBarrier**

| **CountDownLatch**                   | **CyclicBarrier**                  |
| ------------------------------------ | ---------------------------------- |
| One-time use                         | Reusable                           |
| Counter decreases to zero            | Threads wait for each other        |
| One thread typically waits           | All threads wait                   |
| Uses `countDown()` and `await()`     | Uses `await()`                     |
| Suitable for task completion waiting | Suitable for phase synchronization |


## 6. What is ReentrantLock?


**ReentrantLock** is a **thread synchronization** mechanism from the `java.util.concurrent.locks` package that provides explicit locking and unlocking of shared resources. It is an advanced alternative to the **synchronized** keyword.

**Key Features**

* **Thread-safe**
* Supports **explicit lock** and unlock
* A thread can acquire the same lock multiple times (**reentrant**)
* Supports **fair locking**
* Supports **tryLock()**
* Supports **interruptible locking**

**How It Works**

1. A thread acquires the lock using `lock()`.
2. Other threads trying to acquire the same lock must wait.
3. The thread executes the critical section.
4. The lock is released using `unlock()`.
5. Waiting threads can then acquire the lock.


**When to Use**

* Multi-threaded applications
* Shared resource access
* Complex synchronization requirements
* When `tryLock()` or fair locking is needed

**Example**

```java id="x9w7k2"
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();

        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
```

**Using tryLock()**

```java id="y4n8m1"
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        if (lock.tryLock()) {
            try {
                System.out.println("Lock acquired");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Could not acquire lock");
        }
    }
}
```

**ReentrantLock vs synchronized**

| **ReentrantLock**                | **synchronized**    |
| -------------------------------- | ------------------- |
| Explicit `lock()` and `unlock()` | Automatic locking   |
| Supports `tryLock()`             | No `tryLock()`      |
| Supports fairness policy         | No fairness control |
| Supports interruptible locking   | Limited control     |
| More flexible                    | Simpler to use      |


## 7. How to call three APIs concurrently?

To call **three APIs concurrently**, we execute all API requests at the same time instead of waiting for one request to finish before starting the next. In Java, this is commonly done using **CompletableFuture**.

| **Method**      | **Purpose**                                         |
| --------------- | --------------------------------------------------- |
| `supplyAsync()` | Executes a task asynchronously and returns a result |
| `allOf()`       | Waits for all tasks to complete                     |
| `join()`        | Gets the result without checked exceptions          |
| `thenCombine()` | Combines results from multiple tasks                |

**Way One**

To call three APIs concurrently in Spring Boot, we can use `CompletableFuture` with `@Async` so that all services like User, Payment, and Inventory are called in parallel, improving performance.”

User Service
Payment Service
Inventory Service

**Step 1: Enable Async**

```java id="8k6y8x"
@SpringBootApplication
@EnableAsync
public class Application {
}
```

**Step 2: Create Async Service Methods**

```java id="c8s8l2"
@Service
public class ApiService {

    @Async
    public CompletableFuture<String> getUser() {
        // call User Service API
        return CompletableFuture.completedFuture("User Data");
    }

    @Async
    public CompletableFuture<String> getPayment() {
        // call Payment Service API
        return CompletableFuture.completedFuture("Payment Data");
    }

    @Async
    public CompletableFuture<String> getInventory() {
        // call Inventory Service API
        return CompletableFuture.completedFuture("Inventory Data");
    }
}
```

**Step 3: Call All APIs Concurrently**

```java id="fqhmr2"
@RestController
public class MyController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/getAllData")
    public String getAllData() throws Exception {

        CompletableFuture<String> user = apiService.getUser();
        CompletableFuture<String> payment = apiService.getPayment();
        CompletableFuture<String> inventory = apiService.getInventory();

        // Wait for all to complete
        CompletableFuture.allOf(user, payment, inventory).join();

        return user.get() + " | " + payment.get() + " | " + inventory.get();
    }
}
```

**Way two:: WebClient (Reactive - Non-Blocking)**

We can use WebClient from Spring WebFlux to make non-blocking concurrent API calls using Mono or Flux, which is more scalable than threads.


```java
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private WebClient webClient = WebClient.create();

    public Mono<String> getUser() {
        return webClient.get()
                .uri("http://user-service/api")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getPayment() {
        return webClient.get()
                .uri("http://payment-service/api")
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getInventory() {
        return webClient.get()
                .uri("http://inventory-service/api")
                .retrieve()
                .bodyToMono(String.class);
    }
}
```

```java
@GetMapping("/getAll")
public Mono<String> getAll() {

    Mono<String> user = apiService.getUser();
    Mono<String> payment = apiService.getPayment();
    Mono<String> inventory = apiService.getInventory();

    return Mono.zip(user, payment, inventory)
            .map(data -> data.getT1() + " | " +
                         data.getT2() + " | " +
                         data.getT3());
}
```


**Way Three::  ExecutorService (Manual Thread Pool)**

We can use ExecutorService to manually create threads and submit tasks to call APIs in parallel.

```java
import java.util.concurrent.*;

@Service
public class ApiService {

    ExecutorService executor = Executors.newFixedThreadPool(3);

    public String getAllData() throws Exception {

        Callable<String> userTask = () -> "User Data";
        Callable<String> paymentTask = () -> "Payment Data";
        Callable<String> inventoryTask = () -> "Inventory Data";

        Future<String> user = executor.submit(userTask);
        Future<String> payment = executor.submit(paymentTask);
        Future<String> inventory = executor.submit(inventoryTask);

        return user.get() + " | " + payment.get() + " | " + inventory.get();
    }
}
```



| Feature     | WebClient                | ExecutorService |
| ----------- | ------------------------ | --------------- |
| Type        | Non-blocking             | Blocking        |
| Threads     | Less usage               | More threads    |
| Performance | High scalability         | Moderate        |
| Complexity  | Reactive learning needed | Easy            |



## 8. What is immutability in Java? 

**Immutability** means an object's state **cannot be changed after it is created**. If a value needs to change, a **new object** is created instead of modifying the existing one.

**Key Features**

* Object state cannot be modified after creation
* Class is usually marked as **final**
* Fields are **private** and **final**
* No setter methods
* Thread-safe by nature

**How It Works**

1. Object is created with initial values.
2. Fields are assigned only once.
3. No methods allow modification of the object's state.
4. Any change requires creating a new object.


**When to Use**

* Value objects
* Configuration objects
* DTOs
* Multi-threaded applications
* Cache keys and shared data

**Example**

```java id="d7k4m1"
public final class Employee {

    private final String name;
    private final int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

**Usage**

```java id="x8n2p5"
Employee emp = new Employee("John", 25);

// Cannot modify values
// emp.setName("Mike"); // Not possible
```

**How to Create an Immutable Class**

* Make the class **final**
* Make all fields **private** and **final**
* Initialize fields through constructor
* Do not provide setter methods
* Return defensive copies for mutable objects

**Example: String**

```java id="a3v9q7"
String s1 = "Java";
String s2 = s1.concat(" Spring");

System.out.println(s1); // Java
System.out.println(s2); // Java Spring
```

Here, **String** is immutable. The original object is not changed; a new object is created.

## 9. What is CompletableFuture and how does it work?


**CompletableFuture** is a class in Java used for **asynchronous** and **non-blocking** programming. It allows tasks to run in the background and provides a way to process results when they become available.

**Key Features**

* **Asynchronous execution**
* **Non-blocking** operations
* Supports task chaining
* Combines multiple asynchronous tasks
* Built on the **Executor Framework**
* Better alternative to traditional **Future**

**How It Works**

1. Start a task asynchronously using `supplyAsync()` or `runAsync()`.
2. The task executes in a separate thread.
3. Continue other work without waiting.
4. Process the result when the task completes.
5. Combine multiple tasks if needed.


**When to Use**

* Calling multiple APIs
* Background processing
* Microservices communication
* Data aggregation
* Long-running operations

**Example**

```java id="cf7m2x"
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> {
                    return "Hello Java";
                });

        String result = future.join();

        System.out.println(result);
    }
}
```

**Task Chaining Example**

```java id="kp9v4n"
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "Java")
                        .thenApply(str -> str + " Spring");

        System.out.println(future.join());
    }
}
```

**Important Methods**

| **Method**      | **Purpose**                                |
| --------------- | ------------------------------------------ |
| `runAsync()`    | Executes a task without returning a result |
| `supplyAsync()` | Executes a task and returns a result       |
| `thenApply()`   | Transforms the result                      |
| `thenAccept()`  | Consumes the result                        |
| `thenCombine()` | Combines two tasks                         |
| `allOf()`       | Waits for multiple tasks                   |
| `join()`        | Retrieves the result                       |


## 10. Difference between Future and CompletableFuture

**`Future`** is used to get the result of an asynchronous task, but it supports only basic operations like `get()`. 

**`CompletableFuture`** is an enhanced version introduced in Java 8 that supports chaining, combining multiple async tasks, exception handling, and non-blocking programming.

**Future vs CompletableFuture**

| **Future**                 | **CompletableFuture**           |
| -------------------------- | ------------------------------- |
| Basic asynchronous support | Advanced asynchronous support   |
| Cannot easily chain tasks  | Supports task chaining          |
| Limited result processing  | Rich API for result handling    |
| More blocking operations   | Supports non-blocking workflows |

**Future**

```java
ExecutorService executor = Executors.newFixedThreadPool(1);

Future<String> future = executor.submit(() -> {
            Thread.sleep(1000);
            return "Hello";
        });

System.out.println(future.get());
// Output: Hello
// Problem: get() blocks the current thread, no chaining support
```

**CompletableFuture Example**

```java id="gw9h1k"
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
    .thenApply(s -> s + " Java");

System.out.println(future.get());
//Output: Hello Java
```


**Exception Handling**

```java id="sqh7g4"
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
        throw new RuntimeException();
    }).exceptionally(ex -> "Default Value");

System.out.println(future.get());
//Output: Default Value
```



## 12. Synchronous (Sync) and Asynchronous (Async)?

**Synchronous (Sync)** means a task waits for the current task to finish before starting the next one.

**Asynchronous (Async)** means a task can start and continue without waiting for another task to complete.

| **Feature**     | **Synchronous (Sync)**                        | **Asynchronous (Async)**                                       |
| --------------- | --------------------------------------------- | -------------------------------------------------------------- |
| **Execution**   | Tasks execute **one after another**.          | Tasks execute **independently without waiting**.               |
| **Waiting**     | The caller **waits** for the task to finish.  | The caller **does not wait** and can continue other work.      |
| **Performance** | Can be slower if operations take a long time. | Improves performance for **I/O-bound and long-running tasks**. |
| **Complexity**  | Simple and easy to understand.                | More complex because of concurrency and callbacks/futures.     |
| **Use Case**    | Simple operations with immediate results.     | Background tasks, API calls, email sending, file processing.   |

**How It Works**

* **Sync:** A request is sent, and the application **blocks** until it receives the response.
* **Async:** A request is sent, and the application continues executing other tasks. The result is handled later when it becomes available.

**When to Use**

* Use **Synchronous** communication for **simple, dependent operations** where the next step requires the current result.
* Use **Asynchronous** communication for **time-consuming tasks** such as sending emails, processing files, calling external APIs, or event-driven microservices.

**Code Example (Spring Boot Async)**

```java id="d7m4xp"
@Service
public class EmailService {

    @Async
    public void sendEmail() {
        System.out.println("Sending email...");
    }
}
```

```java id="q2k8vn"
@Configuration
@EnableAsync
public class AsyncConfig {
}
```

With `@Async`, the `sendEmail()` method runs in a **separate thread**, allowing the caller to continue without waiting.




**`@Async`** is a **Spring annotation** used to execute a method **asynchronously** in a **separate thread**. It allows the caller to continue execution without waiting for the method to finish.

**Key Features:**

* Executes methods in a **background thread**.
* Improves **application responsiveness**.
* Uses a **Thread Pool (`TaskExecutor`)** internally.
* Can return **`void`**, **`Future`**, or **`CompletableFuture`**.

**How it Works:**

1. Enable asynchronous processing using **`@EnableAsync`**.
2. Annotate a method with **`@Async`**.
3. Spring creates a **proxy** for the bean.
4. When the method is called from another bean, the proxy submits the task to a **thread pool**.
5. The method runs in a **different thread**, while the caller continues without blocking.

**When to Use:**

* Sending **emails** or **notifications**.
* Processing **background jobs**.
* Calling **external APIs**.
* Running **time-consuming tasks** without blocking the main request thread.

**Code Example:**

```java id="j8v2nq"
@Configuration
@EnableAsync
public class AsyncConfig {
}
```

```java id="w7s4mp"
@Service
public class EmailService {

    @Async
    public void sendEmail() {
        System.out.println("Sending email: " +
                Thread.currentThread().getName());
    }
}
```

```java id="w9k3lb"
@RestController
public class UserController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/register")
    public String register() {
        emailService.sendEmail(); // Runs asynchronously
        return "User registered!";
    }
}
```


## 14. What happens if the thread pool is exhausted?

A **thread pool is exhausted** when **all threads are busy** and the **task queue is full**, so the pool cannot accept any more tasks.

**How It Works**

1. A new task arrives.
2. If a free thread is available, the task is executed.
3. If all threads are busy, the task is placed in the **queue**.
4. If the queue is also full and the pool has reached its **maximum size**, the **rejection policy** is triggered.

**Key Features**

* Occurs when **`maxPoolSize`** is reached and the **queue is full**.
* New tasks cannot be processed immediately.
* The behavior depends on the configured **`RejectedExecutionHandler`**.
* Helps prevent the application from creating **unlimited threads** and exhausting system resources.

**Common Rejection Policies**

| **Policy**                | **Behavior**                                            |
| ------------------------- | ------------------------------------------------------- |
| **AbortPolicy** (Default) | Throws **`RejectedExecutionException`**                 |
| **CallerRunsPolicy**      | The **calling thread** executes the task                |
| **DiscardPolicy**         | Silently **discards** the new task                      |
| **DiscardOldestPolicy**   | Removes the **oldest queued task** and adds the new one |

**Code Example**

```java id="5ctgmu"
ThreadPoolExecutor executor = new ThreadPoolExecutor(
    2,                      // corePoolSize
    4,                      // maxPoolSize
    60, TimeUnit.SECONDS,
    new ArrayBlockingQueue<>(2), // queue capacity
    new ThreadPoolExecutor.CallerRunsPolicy()
);
```

In this example:

* **4 threads** can run simultaneously.
* **2 tasks** can wait in the queue.
* If both the threads and queue are full, **`CallerRunsPolicy`** makes the calling thread execute the task.

**When to Use Different Policies**

* **AbortPolicy:** When task rejection should be detected immediately.
* **CallerRunsPolicy:** To provide **backpressure** and slow down task submission.
* **DiscardPolicy:** For non-critical background tasks.
* **DiscardOldestPolicy:** When newer tasks are more important than older queued ones.


## 15. Fork/Join Framework

The **Fork/Join Framework** is a **parallel processing framework** introduced in **Java 7** that speeds up large tasks by **splitting them into smaller subtasks**, executing them concurrently, and then **combining the results**.

**Key Features**

* Uses the **Divide and Conquer** approach.
* Automatically utilizes **multiple CPU cores**.
* Uses a special thread pool called **`ForkJoinPool`**.
* Supports **work-stealing** for better performance.
* Best suited for **CPU-intensive** tasks, not I/O-intensive tasks.

**How It Works**

1. A large task is **forked** (split) into smaller subtasks.
2. Multiple worker threads execute these subtasks in parallel.
3. Idle threads can **steal work** from busy threads (**work-stealing algorithm**).
4. The results of the subtasks are **joined** to produce the final result.

**Main Classes**

* **`ForkJoinPool`** – Manages worker threads.
* **`RecursiveTask<V>`** – Used when the task returns a result.
* **`RecursiveAction`** – Used when the task does not return a result.

**Code Example**

```java id="kl2f7s"
class SumTask extends RecursiveTask<Integer> {

    private int start, end;

    SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 5) {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }

        int mid = (start + end) / 2;
        SumTask left = new SumTask(start, mid);
        SumTask right = new SumTask(mid + 1, end);

        left.fork();                 // Split left task
        int rightResult = right.compute();
        int leftResult = left.join(); // Combine result

        return leftResult + rightResult;
    }
}

ForkJoinPool pool = new ForkJoinPool();
int result = pool.invoke(new SumTask(1, 100));
System.out.println(result);
```

**When to Use**

* For **large computational tasks** that can be divided into independent subtasks.
* For **parallel processing** of arrays, collections, sorting, searching, and mathematical computations.
* Avoid using it for **database calls**, **network requests**, or other **I/O-bound operations**.


**Fork/Join vs Thread Pool**

| **Fork/Join**              | **Thread Pool**        |
| -------------------------- | ---------------------- |
| Designed for recursion     | General task execution |
| Uses work-stealing         | Fixed task assignment  |
| Best for CPU-heavy tasks   | Good for general tasks |
| Automatically splits tasks | Manual task management |




# ✅ 12. Java Input/Output (I/O) 

## 1. What are the different ways to read a file in Java?

Java provides multiple approaches to read files, each suitable for different scenarios and file sizes.

**Common Methods:**
- **FileInputStream:** Byte-based reading
- **FileReader:** Character-based reading
- **BufferedReader:** Efficient line-by-line reading
- **Scanner:** Convenient parsing with delimiters
- **Files.readAllLines():** Read entire file into List
- **Files.lines():** Stream-based reading (Java 8+)

```java
// BufferedReader - most common
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
}

// Files utility - modern approach
List<String> lines = Files.readAllLines(Paths.get("file.txt"));
```

## 2. What is the difference between InputStream and Reader?

**InputStream** is a byte-oriented class used to read raw **binary data** (like images, audio, or files) from a source.

**Reader** is a character-oriented class used to read **text data** (characters, strings) from a source, handling character encoding automatically.

```java
// InputStream - for binary data
InputStream is = new FileInputStream("image.jpg");
int byteData = is.read(); // Returns byte as int

// Reader - for text data
Reader reader = new FileReader("text.txt");
int charData = reader.read(); // Returns character as int
```

## 3. What is BufferedReader and BufferedWriter?

**BufferedReader** is a Java class used to read text from an input stream efficiently by **buffering characters**, reducing the number of I/O operations.

**BufferedWriter** is a Java class used to write text to an output stream efficiently by **buffering characters**, reducing the number of I/O operations.


```java
// BufferedReader - efficient reading
try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}

// BufferedWriter - efficient writing
try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
    bw.write("Hello World");
    bw.newLine();
}
```


## 6. What is NIO in Java?

**NIO (New I/O)** in Java is a **high-performance I/O API** introduced in Java 1.4 that provides **non-blocking, buffer-based, and scalable input/output operations**.

It uses **channels, buffers, and selectors** for efficient **file and network communication**.

**Benefits:**
- Better scalability for server applications
- Non-blocking I/O operations
- Memory-mapped file access
- More efficient for handling many connections

```java
// NIO file reading
try (FileChannel channel = FileChannel.open(Paths.get("file.txt"))) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    channel.read(buffer);
}
```

## 7. What is the difference between IO and NIO?

**IO (java.io)** is **blocking and stream-based**, meaning a thread waits until data is read or written. It’s easy to use but not efficient for handling many connections because each request usually needs a separate thread.

**NIO (java.nio)** is **non-blocking and buffer-based**. It uses **channels, buffers, and selectors**, allowing one thread to handle multiple connections. This makes it more scalable and better suited for high-performance applications.

```java
// Traditional I/O - blocking
InputStream is = new FileInputStream("file.txt");
int data = is.read(); // Blocks until data available

// NIO - non-blocking potential
FileChannel channel = FileChannel.open(Paths.get("file.txt"));
ByteBuffer buffer = ByteBuffer.allocate(1024);
channel.read(buffer); // Can be non-blocking with proper setup
```

## 8. When would you use NIO over traditional I/O?

Use **NIO** when you need **better performance and scalability**, especially for server applications handling many concurrent connections.

**Use NIO when:**
- Building high-performance servers
- Handling thousands of concurrent connections
- Need non-blocking I/O operations
- Working with large files (memory-mapped files)
- Building chat servers, web servers

**Use Traditional I/O when:**
- Simple file operations
- Few concurrent connections
- Straightforward read/write operations
- Rapid development needed
- Working with small to medium files

# ✅ 13. Java Records & Generics 



## 1. **What is Record in Java?**

A **Record** is a special type of **class** introduced in **Java 16** (preview in Java 14) that is designed to store **immutable data**.

It automatically generates common boilerplate code such as:

* **Constructor**
* **Getter methods** (called accessor methods)
* **equals()**
* **hashCode()**
* **toString()**

A Record is ideal for **DTOs (Data Transfer Objects)**, **API responses**, **configuration objects**, and any class whose main purpose is to hold data.

**Example**

```java
public record Employee(Long id, String name) {
}
```

Java automatically generates code similar to:

```java
public final class Employee {
    private final Long id;
    private final String name;

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    // equals(), hashCode(), toString()
}
```

**Usage**

```java
Employee emp = new Employee(1L, "John");

System.out.println(emp.id());      // 1
System.out.println(emp.name());    // John
System.out.println(emp);
```

**Key Features**

* **Immutable** by default.
* Automatically generates **constructor**, **equals()**, **hashCode()**, and **toString()**.
* Fields are **private final**.
* Accessor methods have the **same name as the field** (not `getName()`).
* Can contain **methods**, **static fields**, and **validation** in a compact constructor.

**Example with validation**

```java
public record Employee(Long id, String name) {

    public Employee {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
```



## 1. **What are the main differences between Record and a regular class?**

| **Record**                                                                            | **Regular Class**                       |
| ------------------------------------------------------------------------------------- | --------------------------------------- |
| Designed for **immutable data**                                                       | Can be mutable or immutable             |
| Automatically generates **constructor**, **equals()**, **hashCode()**, **toString()** | You write them manually                 |
| Fields are **private final**                                                          | Fields can be mutable                   |
| Class is **final**                                                                    | Can be final or non-final               |
| Accessor methods like `name()`                                                        | Usually getter methods like `getName()` |
| Less boilerplate                                                                      | More boilerplate                        |
| Best for **DTOs** and value objects                                                   | Suitable for any business logic         |



**Record Example**

```java
public record Person(String name, int age) {
}
```

**Regular Class Example**

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // equals(), hashCode(), toString()
}
```

**When to use Record**

* **DTOs**
* **API request/response objects**
* **Configuration objects**
* **Event objects**
* **Value objects**

**When to use a Regular Class**

* Objects with **mutable state**
* Complex **business logic**
* Need to **extend another class**
* Need **custom getters/setters**


## 1. **Can you inherit from Record or extend Record from another class?**

**No.**

A **Record cannot extend another class**, and **no class can extend a Record** because every Record is implicitly **final**.

However, a Record **can implement interfaces**.

**Not Allowed**

```java
record Employee(Long id, String name) extends Person {
}
```

```java
class Manager extends Employee {
}
```

Both will cause a **compile-time error**.

**Allowed**

```java
interface Printable {
    void print();
}

public record Employee(Long id, String name)
        implements Printable {

    @Override
    public void print() {
        System.out.println(name);
    }
}
```

**Why?**

* Every Record automatically extends **`java.lang.Record`**.
* Java supports **single inheritance**, so it cannot extend any other class.
* Since a Record is **final**, it cannot be subclassed.



## 1. **Can you add additional methods to Record?**

**Yes.**

A **Record** can contain:

* **Custom methods**
* **Static methods**
* **Static fields**
* **Validation logic**
* **Implemented interface methods**

The only restriction is that the record's fields remain **immutable**.

**Example**

```java
public record Employee(Long id, String name) {

    public String getDisplayName() {
        return id + " - " + name;
    }

    public static Employee empty() {
        return new Employee(0L, "Unknown");
    }
}
```

**Usage**

```java
Employee emp = new Employee(1L, "John");

System.out.println(emp.getDisplayName()); // 1 - John
System.out.println(Employee.empty());     // Employee[id=0, name=Unknown]
```




## 1. What are generics in Java?

**Generics** allow you to write **type-safe** classes, interfaces, and methods by specifying the **data type** at compile time. They help prevent type-casting errors and improve code reusability.

**Key Features**

* Provides **type safety**.
* Eliminates unnecessary **type casting**.
* Detects type errors at **compile time**.
* Improves **code reusability** and readability.

**How It Works**

* Use a **type parameter** (e.g., `<T>`) as a placeholder for a data type.
* The actual type is specified when creating an object or calling a method.
* The compiler ensures only the specified type is used.


**When to Use**

* Working with **Collections** like `List`, `Set`, and `Map`.
* Creating reusable classes and methods.
* When the same logic should work with different data types.

**Code Example**

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("John");
        names.add("Alice");

        String name = names.get(0); // No casting required
        System.out.println(name);
    }
}
```

**Generic Class Example**

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

public class Main {
    public static void main(String[] args) {
        Box<Integer> box = new Box<>();
        box.set(100);

        System.out.println(box.get());
    }
}
```


## 2. Why were generics introduced?

Generics were introduced to provide compile-time type safety, eliminate casting, and enable programmers to write more robust and readable code.

**Main Benefits:**
- **Type Safety:** Catch type errors at compile time
- **Eliminate Casting:** No need for explicit type casting
- **Code Reusability:** Same code works with different types
- **Better Performance:** No boxing/unboxing overhead
- **Clearer APIs:** Self-documenting code

```java
// Before generics - runtime error possible
List list = new ArrayList();
list.add("String");
list.add(123);
String str = (String) list.get(1); // ClassCastException at runtime

// With generics - compile-time error
List<String> list = new ArrayList<>();
list.add("String");
list.add(123); // Compile error - type mismatch
```

## 3. What is Type Erasure in Java?

**Type Erasure** is the process by which the Java compiler **removes generic type information** during compilation. After compilation, generic types are replaced with their **bounded type** (or **`Object`** if no bound is specified), making generics available only at **compile time**.

**Key Features:**

* **Generics exist only at compile time**.
* Generic type information is **removed at runtime**.
* Ensures **backward compatibility** with older Java versions.
* The compiler automatically inserts **type casts** where needed.

**How it Works:**

1. You write code using **Generics**.
2. During compilation, the compiler checks **type safety**.
3. The compiler replaces generic types with **`Object`** (or the bounded type).
4. The generated bytecode contains **no generic type information**.

For example:

```java
List<String> names = new ArrayList<>();
```

After **type erasure**, it is treated like:

```java
List names = new ArrayList();
```

**When to Use:**

* You don't use **type erasure** directly; it is an **internal mechanism** of Java Generics.
* Understanding it helps explain why:

  * You cannot create **`new T()`**.
  * You cannot use **`instanceof List<String>`**.
  * You cannot overload methods that differ only by generic type parameters.

**Code Example:**

```java id="b8p4zk"
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Java");

        String value = list.get(0); // Compiler adds type cast
        System.out.println(value);
    }
}
```

## 4. What is the Difference Between `<? extends T>` and `<? super T>`?

Both **`<? extends T>`** and **`<? super T>`** are **wildcards in Java Generics** used to provide flexibility when working with collections.

| **Feature**    | **`<? extends T>`**                                          | **`<? super T>`**                                                    |
| -------------- | ------------------------------------------------------------ | -------------------------------------------------------------------- |
| **Meaning**    | Any type that is **`T` or a subclass of `T`**.               | Any type that is **`T` or a superclass of `T`**.                     |
| **Read/Write** | **Can read**, but cannot safely add objects (except `null`). | **Can write** (`T` or its subclasses), but reading returns `Object`. |
| **Best For**   | **Producer** of data.                                        | **Consumer** of data.                                                |
| **PECS Rule**  | **Producer Extends**.                                        | **Consumer Super**.                                                  |

**How it Works:**

* **`<? extends T>`**

  * Used when the collection **produces** objects.
  * You can safely **read** elements as type `T`.
  * You cannot safely **add** elements because the exact subtype is unknown.

* **`<? super T>`**

  * Used when the collection **consumes** objects.
  * You can safely **add** objects of type `T` or its subclasses.
  * When reading, the returned type is only guaranteed to be `Object`.

**Key Features:**

* **`extends` = Read-only (Producer)**.
* **`super` = Write-friendly (Consumer)**.
* Follows the **PECS principle**: **Producer Extends, Consumer Super**.

**When to Use:**

* Use **`<? extends T>`** when you only need to **read data** from a collection.
* Use **`<? super T>`** when you need to **add data** to a collection.

**Code Example:**

```java id="k7v2xm"
import java.util.*;

public class Demo {
    public static void main(String[] args) {

        // Producer: Read only
        List<? extends Number> list1 = List.of(10, 20, 30);
        Number n = list1.get(0);

        // Consumer: Write allowed
        List<? super Integer> list2 = new ArrayList<Number>();
        list2.add(100);
        list2.add(200);
    }
}
```

**Easy Memory Trick:**

* **`<? extends T>` → Read (Producer)**
* **`<? super T>` → Write (Consumer)**
* **PECS = Producer Extends, Consumer Super**


## 5. What is PECS principle?

**PECS** stands for **"Producer Extends, Consumer Super"** - a guideline for choosing between extends and super wildcards based on how you use the collection.

**Producer Extends:**
- Use `<? extends T>` when you only read from collection
- Collection produces/provides elements
- You're consuming what the collection produces

**Consumer Super:**
- Use `<? super T>` when you only write to collection
- Collection consumes/accepts elements
- You're producing elements for the collection

```java
// Producer - use extends
public void processNumbers(List<? extends Number> numbers) {
    for (Number num : numbers) { // Reading - OK
        System.out.println(num);
    }
}

// Consumer - use super
public void addNumbers(List<? super Integer> numbers) {
    numbers.add(10); // Writing - OK
    numbers.add(20);
}
```

## 6. What are the limitations of generics?

**Generics** have several limitations due to type erasure and backward compatibility requirements.

**Key Limitations:**
- Cannot instantiate generic types: `new T()`
- Cannot create arrays of parameterized types: `new List<String>[10]`
- Cannot use primitives as type parameters: `List<int>` (use `List<Integer>`)
- Cannot catch parameterized exceptions
- Static context cannot access type parameters
- Cannot overload methods that differ only in generic parameters

```java
public class GenericClass<T> {
    // Cannot do these:
    // T instance = new T(); // Error - cannot instantiate
    // T[] array = new T[10]; // Error - cannot create array
    // static T staticField; // Error - static context
    
    // Cannot catch generic exception
    // try { } catch (T e) { } // Error
    
    // Cannot overload based on generics only
    // void method(List<String> list) { }
    // void method(List<Integer> list) { } // Error - same erasure
}

// Workarounds
public class GenericClass<T> {
    private Class<T> type;
    
    public GenericClass(Class<T> type) {
        this.type = type;
    }
    
    public T createInstance() throws Exception {
        return type.newInstance(); // Reflection workaround
    }
}
```




## 1. **Which methods are automatically generated for Record?**

Java automatically generates the following methods:

* **Canonical constructor**
* **Accessor methods** (one for each component)
* **equals()**
* **hashCode()**
* **toString()**

**Example**

```java
public record Employee(Long id, String name) {
}
```

Java generates code similar to:

```java
public Employee(Long id, String name) { ... }

public Long id() { ... }

public String name() { ... }

public boolean equals(Object obj) { ... }

public int hashCode() { ... }

public String toString() { ... }
```

**Example Usage**

```java
Employee e1 = new Employee(1L, "John");
Employee e2 = new Employee(1L, "John");

System.out.println(e1.id());          // 1
System.out.println(e1.name());        // John
System.out.println(e1.equals(e2));    // true
System.out.println(e1.hashCode());
System.out.println(e1);
```


## 1. **Can you override the constructor in Record?**

**Yes.**

A Record supports two types of constructors:

* **Compact constructor** (recommended for validation)
* **Canonical constructor** (full constructor)

**1. Compact Constructor**

You don't declare the parameters because they are taken from the record components automatically.

```java
public record Employee(Long id, String name) {

    public Employee {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
```

Java automatically assigns the fields after the validation.

**2. Canonical Constructor**

You explicitly declare all record components as parameters.

```java
public record Employee(Long id, String name) {

    public Employee(Long id, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.id = id;
        this.name = name;
    }
}
```

The parameter list **must exactly match** the record components.



## 1. **What is a compact constructor in Record?**

A **compact constructor** is a special constructor in a **Record** where you **do not declare the parameters**. Java automatically uses the record components as constructor parameters.

It is mainly used for **validation** or **normalizing data** before the fields are assigned.

**Syntax**

```java
public record Employee(Long id, String name) {

    public Employee {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        name = name.trim(); // Normalize input
    }
}
```

Java automatically performs:

```java
this.id = id;
this.name = name;
```

after the compact constructor finishes.

**Usage**

```java
Employee emp = new Employee(1L, " John ");

System.out.println(emp.name()); // John
```

**When to use**

* **Validate** input
* **Normalize** values
* Enforce **business rules**



## 1. **Can you declare static fields and methods in Record?**

**Yes.**

A Record can contain:

* **Static fields**
* **Static methods**
* **Static constants**

Just like a regular class.

**Example**

```java
public record Employee(Long id, String name) {

    public static final String COMPANY = "OpenAI";

    public static Employee createDefault() {
        return new Employee(0L, "Unknown");
    }
}
```

**Usage**

```java
System.out.println(Employee.COMPANY);

Employee emp = Employee.createDefault();
System.out.println(emp);
```


## 1. **Are Record fields final?**

**Yes.**

All Record components become **private final** fields automatically.

This makes a Record **immutable**.

**Example**

```java
public record Employee(Long id, String name) {
}
```

Internally, Java creates something similar to:

```java
private final Long id;
private final String name;
```

Trying to modify a field is **not allowed**.

```java
Employee emp = new Employee(1L, "John");

// Compile-time error
emp.name = "Mike";
```

You also cannot reassign the fields inside methods.



## 1. **Can you use Record as a key in HashMap?**

**Yes.**

In fact, Records are **excellent keys** for a **HashMap** because Java automatically generates correct **equals()** and **hashCode()** methods based on all record components.

**Example**

```java
import java.util.HashMap;
import java.util.Map;

public record Employee(Long id, String name) {}

public class Main {
    public static void main(String[] args) {

        Map<Employee, String> map = new HashMap<>();

        map.put(new Employee(1L, "John"), "Developer");

        System.out.println(map.get(new Employee(1L, "John")));
    }
}
```

**Output**

```text
Developer
```

The lookup works because both `Employee` objects have the same component values, so their **equals()** and **hashCode()** are equal.

**Why are Records good HashMap keys?**

* **Immutable** (key doesn't change after insertion)
* Automatically generated **equals()**
* Automatically generated **hashCode()**
* Less chance of bugs caused by mutable keys




# ✅ 14. Java Annotations & Reflection 


## 1. What are annotations in Java?

**Annotations** are **metadata** that provide additional information about classes, methods, fields, or parameters. They do **not directly change the program logic**, but they help the **compiler**, **JVM**, or **frameworks** process the code.

**Key Features**

* Provide **metadata** about the code.
* Improve **readability** and reduce XML/configuration files.
* Can be processed at **compile time** or **runtime**.
* Widely used in frameworks like **Spring**, **Hibernate**, and **JUnit**.
* Can create **custom annotations**.

**How it Works**

* An annotation is added using the **`@`** symbol.
* The **compiler**, **JVM**, or a **framework** reads the annotation.
* Based on the annotation, it performs specific actions automatically.


**When to Use**

* Use **`@Override`** to indicate method overriding.
* Use **`@Deprecated`** to mark old methods.
* Use **`@SuppressWarnings`** to suppress compiler warnings.
* In **Spring**, use annotations like **`@Component`**, **`@Service`**, and **`@Autowired`**.
* In **Hibernate**, use **`@Entity`**, **`@Table`**, and **`@Column`**.

**Common Built-in Annotations**

| **Annotation**             | **Purpose**                                       |
| -------------------------- | ------------------------------------------------- |
| **`@Override`**            | Indicates a method overrides a parent method      |
| **`@Deprecated`**          | Marks a method or class as outdated               |
| **`@SuppressWarnings`**    | Suppresses compiler warnings                      |
| **`@FunctionalInterface`** | Ensures an interface has only one abstract method |

**Code Example**

```java
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

In this example:

* **`@Override`** tells the compiler that `sound()` must override the parent class method.
* If the method signature is incorrect, the compiler will generate an error.

**Custom Annotation Example**

```java
@interface MyAnnotation {
    String value();
}

@MyAnnotation(value = "Java")
class Test {
}
```

## 2. What are built-in annotations?

Java provides several built-in annotations for common use cases like method overriding, deprecation warnings, and compiler instructions.

**Common Built-in Annotations:**
- **@Override:** Indicates method overrides parent method
- **@Deprecated:** Marks code as outdated
- **@SuppressWarnings:** Suppresses compiler warnings
- **@FunctionalInterface:** Marks functional interfaces
- **@SafeVarargs:** Suppresses varargs warnings

```java
@Override
public void method() { } // Ensures proper overriding

@Deprecated
public void legacyMethod() { } // Warns users about deprecation

@SuppressWarnings("unchecked")
List list = new ArrayList(); // Suppresses unchecked warning
```

## 3. What is @Target, @Documented, @Inherited?


These are **meta-annotations**, which means they are **annotations used to define the behavior of other annotations**.

| **Meta-Annotation** | **Purpose**                                                                |
| ------------------- | -------------------------------------------------------------------------- |
| **`@Target`**       | Specifies where an annotation can be applied (class, method, field, etc.). |
| **`@Documented`**   | Includes the annotation information in the generated **JavaDoc**.          |
| **`@Inherited`**    | Allows a custom annotation to be inherited by child classes.               |

**Key Features**

* **`@Target`** restricts the valid locations for an annotation.
* **`@Documented`** makes the annotation visible in API documentation.
* **`@Inherited`** allows subclasses to automatically inherit a class-level annotation.
* These are mainly used while creating **custom annotations**.

**How it Works**

* **`@Target`** defines where the annotation is allowed.
* **`@Documented`** tells the JavaDoc tool to include the annotation in generated documentation.
* **`@Inherited`** allows a subclass to access the annotation present on its parent class.


**When to Use**

* Use **`@Target`** when creating a custom annotation and you want to limit where it can be applied.
* Use **`@Documented`** when the annotation should appear in generated documentation.
* Use **`@Inherited`** when child classes should automatically inherit the parent class annotation.

**Code Example**

```java id="p8rj2z"
import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@interface MyAnnotation {
}

@MyAnnotation
class Parent {
}

class Child extends Parent {
}

public class Test {
    public static void main(String[] args) {
        System.out.println(
            Child.class.isAnnotationPresent(MyAnnotation.class)
        ); // true
    }
}
```

**In this example:**

* **`@Target(ElementType.TYPE)`** allows `@MyAnnotation` to be used only on **classes, interfaces, or enums**.
* **`@Documented`** ensures `@MyAnnotation` appears in the generated **JavaDoc**.
* **`@Inherited`** allows the `Child` class to inherit the annotation from `Parent`.

**Common `ElementType` Values for `@Target`**

| **ElementType**       | **Used On**            |
| --------------------- | ---------------------- |
| **`TYPE`**            | Class, interface, enum |
| **`METHOD`**          | Method                 |
| **`FIELD`**           | Field or variable      |
| **`PARAMETER`**       | Method parameter       |
| **`CONSTRUCTOR`**     | Constructor            |
| **`ANNOTATION_TYPE`** | Another annotation     |


## 4. How to create custom annotations in java?

**Yes, Java allows you to create custom annotations** using the **@interface** keyword. Custom annotations are used to add **metadata** to classes, methods, fields, or parameters.

**Creating a Custom Annotation**

```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String value();
}
```

**Explanation**

* **@interface** – used to create a custom annotation.
* **@Retention(RetentionPolicy.RUNTIME)** – makes the annotation available at runtime.
* **@Target(ElementType.METHOD)** – specifies that the annotation can be applied only to methods.
* **String value()** – defines an attribute for the annotation.

**Using the Annotation**

```java
public class EmployeeService {

    @MyAnnotation("Employee Validation")
    public void validate() {
        System.out.println("Validating employee...");
    }
}
```

**Reading the Annotation with Reflection**

```java
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {

        Method method = EmployeeService.class.getMethod("validate");

        if (method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation =
                    method.getAnnotation(MyAnnotation.class);

            System.out.println(annotation.value());
        }
    }
}
```


## 5. What is retention policy?

**Retention Policy** defines **how long an annotation is available** in the Java program. It is specified using the **`@Retention`** meta-annotation and the **`RetentionPolicy`** enum.

**Key Features**

* Controls the **lifecycle** of an annotation.
* Applied using the **`@Retention`** annotation.
* Has **three types**: **`SOURCE`**, **`CLASS`**, and **`RUNTIME`**.
* Important when creating **custom annotations**.

**Retention Policy Types**

| **Retention Policy** | **Available Until**                                  | **Use Case**                               |
| -------------------- | ---------------------------------------------------- | ------------------------------------------ |
| **`SOURCE`**         | Source code only                                     | Compiler checks, removed after compilation |
| **`CLASS`**          | Stored in `.class` file but not available at runtime | Default behavior                           |
| **`RUNTIME`**        | Available during runtime through **Reflection**      | Spring, Hibernate, JUnit                   |

**How it Works**

* The **compiler** reads the `@Retention` annotation.
* Based on the selected `RetentionPolicy`, it decides how long the annotation should be kept.
* If `RUNTIME` is used, the annotation can be accessed using **Reflection API**.


**When to Use**

* Use **`SOURCE`** for compile-time tools and checks.
* Use **`CLASS`** when the annotation should exist in the bytecode but is not needed at runtime.
* Use **`RUNTIME`** when frameworks or reflection need to access the annotation while the application is running.

**Code Example**

```java id="3r7n2f"
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
}

@MyAnnotation
class Test {
}

public class Main {
    public static void main(String[] args) {
        boolean present =
            Test.class.isAnnotationPresent(MyAnnotation.class);

        System.out.println(present); // true
    }
}
```


## 6. What is reflection in Java?

**Reflection in Java** is a feature that allows a program to **inspect and manipulate classes, methods, fields, and constructors at runtime**, even if they are **private**, enabling **dynamic behavior** and **runtime flexibility**.


```java
// Get class information
Class<?> clazz = String.class;
Method[] methods = clazz.getMethods();

// Create instance dynamically
Object obj = clazz.newInstance();

// Invoke method dynamically
Method method = clazz.getMethod("length");
int length = (int) method.invoke("Hello");
```

## 7. When should you use reflection?

Use **reflection** when you need dynamic behavior that cannot be achieved with normal Java code, typically in frameworks and libraries.

**Appropriate Use Cases:**
- Building frameworks (Spring, Hibernate)
- Serialization/deserialization libraries
- Testing frameworks (JUnit)
- Dependency injection containers
- Code analysis tools
- Plugin architectures

**Avoid reflection for:**
- Normal application logic
- Performance-critical code
- When compile-time solutions exist



# ✅ 15. Java JDBC 

## 1. What is JDBC ?

**What is JDBC?**

**JDBC (Java Database Connectivity)** is a **Java API** that allows Java applications to **connect to a database, execute SQL queries, and retrieve or update data**.

**Key Features**

* Provides a **standard API** for database access.
* Supports **CRUD operations** (**Create, Read, Update, Delete**).
* Works with different databases using **JDBC drivers** (MySQL, Oracle, PostgreSQL, etc.).
* Supports **transactions**, **batch processing**, and **prepared statements**.

**How It Works**

1. Load the **JDBC Driver**.
2. Create a **Connection** to the database.
3. Create a **Statement** or **PreparedStatement**.
4. Execute the **SQL query**.
5. Process the **ResultSet** (if any).
6. Close the resources.

**When to Use**

* Use **JDBC** when a Java application needs to **communicate directly with a relational database**.
* Commonly used in **Spring Boot**, **Hibernate**, and standalone Java applications for database operations.

**Code Example**

```java id="u8x3mv"
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/testdb", "root", "password");

PreparedStatement ps =
    con.prepareStatement("SELECT * FROM employee");

ResultSet rs = ps.executeQuery();

while (rs.next()) {
    System.out.println(rs.getString("name"));
}

rs.close();
ps.close();
con.close();
```

## 2. What are the steps to connect to a database using JDBC?

There are 5 main steps to establish database connectivity using JDBC.

**Steps:**
1. **Load Driver:** Register database driver
2. **Create Connection:** Establish database connection
3. **Create Statement:** Prepare SQL statement
4. **Execute Query:** Run SQL and get results
5. **Close Resources:** Clean up connections

```java
// Step 1: Load driver (optional in modern JDBC)
Class.forName("com.mysql.cj.jdbc.Driver");

// Step 2: Create connection
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", "user", "password");

// Step 3: Create statement
Statement stmt = conn.createStatement();

// Step 4: Execute query
ResultSet rs = stmt.executeQuery("SELECT * FROM users");

// Step 5: Close resources
rs.close(); stmt.close(); conn.close();
```

## 3. What is the difference between Statement and PreparedStatement?

A **Statement** is used to execute **static SQL queries**. The SQL query is sent to the database every time it runs, so it’s less efficient and more vulnerable to **SQL injection**.

A **PreparedStatement** is used for **parameterized queries**. The SQL is **precompiled and cached** by the database, which improves performance for repeated execution and **prevents SQL injection** by safely handling input values.


| **Feature**           | **`Statement`**                                           | **`PreparedStatement`**                                      |
| --------------------- | --------------------------------------------------------- | ------------------------------------------------------------ |
| **Query Compilation** | SQL query is **compiled every time** it is executed.      | SQL query is **precompiled once** and reused.                |
| **Parameters**        | Values are **concatenated directly** into the SQL string. | Uses **`?` placeholders** for parameters.                    |
| **Performance**       | **Slower** for repeated execution.                        | **Faster** for repeated execution because of precompilation. |
| **Security**          | **Vulnerable to SQL Injection**.                          | **Prevents SQL Injection** by parameter binding.             |
| **Best Use Case**     | Simple, one-time static queries.                          | Dynamic queries and frequently executed SQL statements.      |

**How It Works**

* **`Statement`** executes a complete SQL query passed as a string.
* **`PreparedStatement`** first compiles the SQL query with placeholders (`?`), then values are set using methods like `setString()` and `setInt()`.

**When to Use**

* Use **`Statement`** for **simple static queries** that run only once.
* Use **`PreparedStatement`** for **dynamic queries, user input, and repeated execution**. It is the **preferred choice** in real-world applications.

**Code Example**

```java id="3jh7xa"
// Statement
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(
    "SELECT * FROM employee WHERE id = 1");

// PreparedStatement
PreparedStatement ps =
    con.prepareStatement("SELECT * FROM employee WHERE id = ?");
ps.setInt(1, 1);
ResultSet rs2 = ps.executeQuery();
```

## 4. What is connection pooling and how it works internally?

**Connection Pooling** is a technique where a **pool of reusable database connections** is created and maintained, so the application can **reuse existing connections instead of creating a new one for every request**.

**Key Features**

* **Improves performance** by avoiding repeated connection creation.
* **Reduces database load** and resource usage.
* **Reuses existing connections** efficiently.
* Supports **maximum pool size**, **idle timeout**, and **connection validation**.
* Common implementations: **HikariCP**, **Apache DBCP**, and **C3P0**.

**How It Works Internally**

1. When the application starts, the pool creates a predefined number of **database connections**.
2. When a request needs database access, it **borrows** an available connection from the pool.
3. The application executes SQL operations using that connection.
4. Instead of closing it, the connection is **returned to the pool** after use.
5. The pool reuses the same connection for future requests. If all connections are busy, new requests wait until a connection becomes available (up to the configured limit).

**When to Use**

* Use **Connection Pooling** in **web applications, microservices, and enterprise systems** where the database is accessed frequently.
* It is recommended for **high-performance and high-concurrency** applications.

**Code Example (Spring Boot with HikariCP)**

```java id="k7m2pv"
// application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/testdb
spring.datasource.username=root
spring.datasource.password=password

// HikariCP Pool Settings
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
```


## 5. What is caching and how it works?

**Caching** is a technique of **storing frequently accessed data in fast memory** so that future requests can be served **without repeatedly querying the database or external API**.

**Key Features**

* **Improves performance** and reduces response time.
* **Reduces database load** and network calls.
* Stores **frequently read, rarely updated** data.
* Supports **automatic expiration (TTL)** and cache eviction.
* Common cache providers: **Caffeine**, **Redis**, and **Ehcache**.

**How It Works Internally**

1. A client requests data.
2. The application first checks the **cache**.
3. If the data exists (**Cache Hit**), it is returned immediately.
4. If the data is not found (**Cache Miss**), the application fetches it from the **database**.
5. The fetched data is stored in the cache for future requests.
6. When the data is updated or deleted, the cache is **updated (`@CachePut`)** or **removed (`@CacheEvict`)** to keep it consistent.

**Spring Cache Annotations**


* **`@Cacheable`** → Stores the method result in cache and returns cached data for the same request instead of executing the method again.
* **`@CachePut`** → Always executes the method and **updates the cache with the latest result**.
* **`@CacheEvict`** → Removes specific or all entries from the cache when data becomes invalid or is deleted.


**Types of Caching**

| **Cache Type**         | **Description**                                                           | **Example**                                |
| ---------------------- | ------------------------------------------------------------------------- | ------------------------------------------ |
| **Local Cache**        | Stored inside application memory. Best for a single application instance. | **Caffeine**                               |
| **Distributed Cache**  | Shared across multiple application servers.                               | **Redis**                                  |
| **Database/ORM Cache** | Caches database entities at the ORM level.                                | **Hibernate Second-Level Cache (Ehcache)** |

**When to Use**

* Use **Caffeine** for **single-instance applications** requiring ultra-fast access.
* Use **Redis** for **microservices or multiple server instances** where the cache must be shared.
* Use **Hibernate L2 Cache** to reduce repeated database queries for frequently accessed entities.
* Best suited for **frequently read and rarely updated data**.

**Code Example (Spring Boot with `@Cacheable`)**

```java id="r7n4xp"
@Service
public class UserService {

    @Cacheable(value = "users", key = "#id")
    public User getUser(Long id) {
        System.out.println("Fetching from DB...");
        return userRepository.findById(id)
                .orElse(new User(id, "Default User"));
    }
}
```

**Execution Flow**

* **First Call** → Cache Miss → Fetch from **Database** → Store in **Cache**.
* **Second Call** → Cache Hit → Return directly from **Cache**.

**Caffeine vs Redis**

| **Caffeine**                              | **Redis**                        |
| ----------------------------------------- | -------------------------------- |
| **Local in-memory cache**                 | **Distributed cache server**     |
| Extremely **fast**                        | Slight **network latency**       |
| Not shared across applications            | Shared across multiple services  |
| Best for **single-instance applications** | Best for **distributed systems** |


In Java, caching can be broadly categorized into **three main types** based on where the data is stored.

- **Local Cache (In-Memory Cache):** Data is stored **inside the application memory** for fast access. Example using Caffeine


**1. Local Cache – Caffeine (Real-Time Example: Product Catalog)**

**Use case:** Frequently accessed product data → store in local cache to avoid DB calls.

**Service Class**

```java
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class ProductService {

    private static Cache<Long, String> productCache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    public String getProduct(Long id) {

        String product = productCache.getIfPresent(id);

        if (product != null) {
            System.out.println("From Cache");
            return product;
        }

        // Simulate DB call
        System.out.println("From Database");
        product = "Laptop";

        productCache.put(id, product);

        return product;
    }

    public static void main(String[] args) {
        ProductService service = new ProductService();

        System.out.println(service.getProduct(1L)); // DB
        System.out.println(service.getProduct(1L)); // Cache
    }
}
```

**2. Distributed Cache – Redis (Real-Time Example: User Service)**

**Use case:** Multiple servers → shared cache → Redis

**application.properties**

```properties
spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

**Enable Cache**

```java
@Configuration
@EnableCaching
public class CacheConfig {
}
```

**Service**

```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Cacheable(value = "users", key = "#id")
    public User getUser(Long id) {
        System.out.println("Fetching from DB...");
        return userRepository.findById(id).orElse(new User(id, "Default User"));
    }
}
```

**Controller**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }
}
```

**First call → DB**
**Second call → Redis cache**


**3. Database Cache – Hibernate Second Level Cache (Real-Time Example: Product Table)**

**Entity**

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Cacheable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Product {

    @Id
    private Long id;
    private String name;

    // getters and setters
}
```

**application.properties**

```properties
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.jcache.JCacheRegionFactory
spring.cache.jcache.config=classpath:ehcache.xml
```

**ehcache.xml**

```xml
<config xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
        xmlns='http://www.ehcache.org/v3'>

    <cache alias="Product">
        <heap unit="entries">1000</heap>
        <expiry>
            <ttl unit="minutes">10</ttl>
        </expiry>
    </cache>
</config>
```

**First time → DB**
**Second time → Hibernate L2 Cache**


**Real-Time When to Use What**

| Scenario              | Cache Type   |
| --------------------- | ------------ |
| Single application    | Caffeine     |
| Multiple servers      | Redis        |
| ORM level caching     | Hibernate L2 |
| Frequently read data  | Cache        |
| Frequently write data | Avoid cache  |


**Caffeine vs Redis**

| Caffeine                   | Redis                        |
| -------------------------- | ---------------------------- |
| Local cache                | Distributed cache            |
| Stored inside app memory   | Separate server              |
| Ultra fast                 | Slight network latency       |
| Not shared across services | Shared across services       |
| Best for single instance   | Best for distributed systems |


## 6. How to implement Redis Cache?


**Redis Caching** is used to **store frequently accessed data in memory** so that the application can retrieve it much faster instead of querying the database every time.

**Key Features**

* **In-memory** storage for very fast access
* Reduces **database load**
* Improves **API response time**
* Supports **TTL (Time-To-Live)** for automatic cache expiration
* Easy integration with **Spring Boot Cache**

**How it Works**

1. Client calls an API.
2. Spring first checks if the data exists in **Redis**.
3. If found (**Cache Hit**), data is returned directly from Redis.
4. If not found (**Cache Miss**), data is fetched from the **Database**.
5. The result is stored in Redis for future requests.

**When to Use**

* **Frequently read** data
* **Rarely changing** data
* **Reference/Master** data (Countries, States, Product Categories)
* **User Profiles**
* **Configuration** data

**Implementation Steps**

**1. Add Dependency**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

**2. Configure Redis**

```properties
spring.redis.host=localhost
spring.redis.port=6379
```

**3. Enable Caching**

```java
@SpringBootApplication
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

**4. Use `@Cacheable`**

```java
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Cacheable(value = "employees", key = "#id")
    public Employee getEmployee(Long id) {
        System.out.println("Fetching from Database...");
        return repository.findById(id).orElse(null);
    }
}
```

**5. Use `@CachePut`**

```java
@CachePut(value = "products", key = "#product.id")
public Product updateProduct(Product product) {

    return productRepository.save(product);
}
```

**5. Use `@CacheEvict`**

```java
@CacheEvict(value = "products", key = "#id")
public void deleteProduct(Long id) {

    productRepository.deleteById(id);
}
```


**Step 7: Configure TTL (Time-To-Live)**

```java
@Bean
public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
    RedisCacheConfiguration config =
        RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10));

    return RedisCacheManager.builder(factory)
            .cacheDefaults(config)
            .build();
}
```

**First Call**

* Cache is empty.
* Data is fetched from the **Database**.
* Data is stored in **Redis**.

**Second Call**

* Data is returned directly from **Redis**.
* Database is not accessed.

**Updating Cache**

Use **`@CachePut`** to update both the **Database** and **Redis**.

```java
@CachePut(value = "employees", key = "#employee.id")
public Employee update(Employee employee) {
    return repository.save(employee);
}
```

**Removing Cache**

Use **`@CacheEvict`** when data is deleted or changed.

```java
@CacheEvict(value = "employees", key = "#id")
public void delete(Long id) {
    repository.deleteById(id);
}
```

**Annotations Used**

| Annotation         | Purpose                                                                 |
| ------------------ | ----------------------------------------------------------------------- |
| **@Cacheable**     | Reads from cache; if missing, fetches from DB and stores the result.    |
| **@CachePut**      | Updates the cache with the latest value every time the method executes. |
| **@CacheEvict**    | Removes specific or all cache entries.                                  |
| **@EnableCaching** | Enables Spring's caching support.                                       |


**Common Interview Follow-up Questions**

**Q: What is a Cache Hit?**

A **Cache Hit** occurs when the requested data is found in **Redis**, so the database is not queried.

**Q: What is a Cache Miss?**

A **Cache Miss** occurs when the data is not found in Redis. The application fetches it from the **Database** and stores it in Redis.

**Q: Why use Redis instead of storing everything in memory?**

Redis is a **centralized, distributed cache** shared across multiple application instances, while local memory cache is limited to a single application instance.

**Q: When should you avoid caching?**

Avoid caching **frequently changing**, **real-time**, or **sensitive** data where stale values are unacceptable.



## 7. What is @Profile Annotation?

**Profiles** allow you to segregate parts of your application configuration for different environments like dev, test, and production.

```java
# application.properties
spring.profiles.active=dev

# application-dev.properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb

# application-prod.properties
server.port=80
spring.datasource.url=jdbc:mysql://prod-server:3306/mydb
```

```java
@Component
@Profile("dev")
public class DevDataLoader {
    // Only loaded in dev profile
}
```

## 9. What is application.properties file and how value read from there?

`application.properties` is a **configuration file in Spring Boot** used to store **application settings**, such as database URLs, server ports, or custom values.

**Example (`application.properties`):**

```properties id="0z5kqg"
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
app.name=MySpringApp
```

**Read Values from `application.properties`**

**1. Using `@Value`**

```java id="4y9qmr"
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    @Value("${app.name}")
    private String appName;

    public void printName() {
        System.out.println("Application Name: " + appName);
    }
}
```

**2. Using `@ConfigurationProperties`**

```java id="1sy8y7"
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String name;

    // getter and setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

## 10. How does Spring Boot decide which configuration file to load?

Spring Boot loads config in this priority order (higher overrides lower):

1. Command-line args (`--server.port=9090`)
2. `SPRING_APPLICATION_JSON` env variable
3. OS environment variables
4. `application-{profile}.properties` (profile-specific)
5. `application.properties` (default)
6. `@PropertySource` annotations
7. Default values in code

File locations searched (in order):
```
./config/
./
classpath:/config/
classpath:/          ← lowest priority
```


## 11. What is centralized configuration in microservices?

Centralized configuration means storing all microservice configurations in a **single external location** (like a Git repo) instead of bundling them inside each service's JAR/WAR.

- All services fetch their config from a **Config Server** at startup
- Changes can be applied **without redeploying** services
- Supports environment-specific configs (dev, staging, prod)

Solution: **Spring Cloud Config Server**

```
[Config Server] ←→ [Git Repo with all configs]
      ↑
[Service A] [Service B] [Service C]  ← all fetch config from Config Server
```

```yaml
# bootstrap.yml in each microservice
spring:
  config:
    import: "configserver:http://config-server:8888"
  application:
    name: order-service   # fetches order-service.yml from git
```


## 13. How can we create a centralized configuration for all microservices?

**In microservive If I do change in application.yml file, do I need to restart the the service everytime. will that chagne to apply** 

By default, Spring Boot loads the configuration only during application startup, so runtime changes in `application.yml` are not automatically picked up.

However, using tools like `@RefreshScope`, Spring Cloud Config, and Actuator refresh endpoints, some configuration changes can be applied without restarting the service.


**Step 1: Create Config Server**

**pom.xml dependency:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

**Main class:**
```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

**application.yml:**
```yaml
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-org/config-repo
          default-label: main
```


**Step 2: Create Git Config Repository**

Create a GitHub repo (e.g., `config-repo`) and add config files:

```
config-repo/
  order-service.yml
  order-service-dev.yml
  order-service-prod.yml
```

**order-service.yml:**
```yaml
app:
  message: "Hello from Config Server"
  timeout: 5000
```


**Step 3: Configure Client Microservice**

**pom.xml dependency:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

**application.yml (client):**
```yaml
spring:
  application:
    name: order-service        # must match filename in config-repo
  config:
    import: "configserver:http://localhost:8888"
  profiles:
    active: dev
```


**Step 4: Use Config Values in Code**

```java
@RestController
@RefreshScope   // enables runtime refresh without restart
public class OrderController {

    @Value("${app.message}")
    private String message;

    @GetMapping("/info")
    public String info() {
        return message;
    }
}
```


**Step 5: Dynamic Refresh (Without Restart)**

Add Actuator dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Expose refresh endpoint in client's `application.yml`:
```yaml
management:
  endpoints:
    web:
      exposure:
        include: refresh
```

Trigger refresh after config change:
```bash
POST http://localhost:8080/actuator/refresh
```


**Config Resolution Order**

```
{app-name}-{profile}.yml  →  {app-name}.yml  →  application.yml
```

Example for `order-service` with `dev` profile:
1. `order-service-dev.yml`
2. `order-service.yml`
3. `application.yml`


**Interview Key Points**

| Point | Answer |
|---|---|
| Default Config Server port | 8888 |
| Annotation on server | `@EnableConfigServer` |
| Annotation for runtime refresh | `@RefreshScope` |
| Config file naming | `{spring.application.name}-{profile}.yml` |
| Refresh without restart | `POST /actuator/refresh` |
| Bus refresh (all instances) | Spring Cloud Bus + Kafka/RabbitMQ |

**Common Interview Questions**

**Q: What happens if Config Server is down at startup?**  
A: Service fails to start. Use `spring.cloud.config.fail-fast=true` to fail immediately, or `spring.cloud.config.retry.*` for retry logic.

**Q: How to secure Config Server?**  
A: Add Spring Security to Config Server, use Basic Auth or OAuth2. Encrypt sensitive values using `{cipher}` prefix with symmetric/asymmetric keys.

**Q: Difference between `@RefreshScope` and restart?**  
A: `@RefreshScope` re-initializes only the annotated bean at runtime via `/actuator/refresh`. Restart reloads the entire application context.

**Q: How to push config changes to all instances at once?**  
A: Use **Spring Cloud Bus** with a message broker (Kafka/RabbitMQ). One `POST /actuator/busrefresh` call propagates to all instances.


## 14. How do microservices load configuration from a central source?

On startup, each microservice:

```
Microservice starts
    ↓
Reads bootstrap.yml → finds Config Server URL
    ↓
HTTP GET http://config-server:8888/employee-service/prod
    ↓
Config Server fetches from Git repo
    ↓
Returns merged config (common + service-specific + profile-specific)
    ↓
Microservice loads properties into Spring Environment
    ↓
@Value, @ConfigurationProperties beans are populated
```

For **live refresh** without restart:
- Add `@RefreshScope` on beans that need to reload
- Call `POST /actuator/refresh` on the microservice
- Or use Spring Cloud Bus (broadcasts refresh to all instances via RabbitMQ/Kafka)

## 12. How do you use `@ConfigurationProperties` in Spring Boot?

Binds a group of related properties to a POJO — cleaner than injecting one-by-one with `@Value`.

```yaml
# application.yml
app:
  name: MyApp
  timeout: 5000
  retry: 3
```

```java
@ConfigurationProperties(prefix = "app")
@Component
public class AppConfig {
    private String name;
    private int timeout;
    private int retry;
    // getters + setters
}
```

Inject and use:
```java
@Autowired
private AppConfig appConfig;

appConfig.getTimeout(); // 5000
```


## 13. What is the use of prefix in configuration properties?

The `prefix` acts as a namespace — it maps only the matching keys from the config file to the POJO, avoiding conflicts.

```yaml
mail:
  host: smtp.gmail.com
  port: 587

db:
  host: localhost
  port: 5432
```

```java
@ConfigurationProperties(prefix = "mail")  // only binds mail.* keys
public class MailConfig {
    private String host;  // smtp.gmail.com
    private int port;     // 587
}

@ConfigurationProperties(prefix = "db")    // only binds db.* keys
public class DbConfig {
    private String host;  // localhost
    private int port;     // 5432
}
```

This avoids collision when multiple configs share the same field names like `host` or `port`.

## 14. How to setup tomcat server manually if I don't use spring boot?

Without **Spring Boot**, you need to **install and configure Apache Tomcat** yourself and deploy your application as a **WAR** file.


**How it works**

1. Install **JDK** and set **JAVA_HOME**.
2. Download and extract **Apache Tomcat**.
3. Set **CATALINA_HOME**.
4. Create the application as a **WAR** project.
5. Configure **web.xml** (or use `@WebServlet`).
6. Build the **WAR** file.
7. Copy the **WAR** to the **Tomcat webapps** folder.
8. Start **Tomcat**.
9. Access the application in the browser.

**Required Configuration**

**Manual Tomcat Configuration Steps (Without Spring Boot)**

**Step 1: Install JDK**

* Install **JDK 17** (or the required version).
* Set the **JAVA_HOME** environment variable.

Example:

```text
JAVA_HOME=C:\Program Files\Java\jdk-17
```

Verify:

```bash
java -version
```

**Step 2: Download Apache Tomcat**

* Download **Apache Tomcat**.
* Extract it to a folder.

Example:

```text
C:\apache-tomcat-10.1
```

**Step 3: Configure Environment Variables**

Set:

```text
JAVA_HOME=C:\Program Files\Java\jdk-17
CATALINA_HOME=C:\apache-tomcat-10.1
```

Add to **PATH**:

```text
%CATALINA_HOME%\bin
```

Verify:

```bash
echo %JAVA_HOME%
echo %CATALINA_HOME%
```

**Step 4: Create a Maven WAR Project**

In **pom.xml**:

```xml
<packaging>war</packaging>
```

Add Servlet dependency:

```xml
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>6.0.0</version>
    <scope>provided</scope>
</dependency>
```

**Step 5: Configure `web.xml`**

Location:

```text
src/main/webapp/WEB-INF/web.xml
```

Example:

```xml
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee">

    <servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>com.demo.HelloServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>

</web-app>
```

**Step 6: Create a Servlet**

```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {

        resp.getWriter().println("Hello Tomcat");
    }
}
```

**Step 7: Build the WAR File**

Using Maven:

```bash
mvn clean package
```

Generated file:

```text
target/myapp.war
```

**Step 8: Deploy the WAR**

Copy:

```text
target/myapp.war
```

To:

```text
apache-tomcat/webapps/
```

**Step 9: Configure Tomcat Port (Optional)**

File:

```text
apache-tomcat/conf/server.xml
```

Default:

```xml
<Connector port="8080" protocol="HTTP/1.1"/>
```

Change if needed:

```xml
<Connector port="9090" protocol="HTTP/1.1"/>
```

**Step 10: Configure DataSource (Optional)**

File:

```text
apache-tomcat/conf/context.xml
```

Example:

```xml
<Resource
    name="jdbc/MyDB"
    auth="Container"
    type="javax.sql.DataSource"
    driverClassName="com.mysql.cj.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/test"
    username="root"
    password="root"/>
```

**Step 11: Start Tomcat**

**Windows**

```bash
startup.bat
```

**Linux/Mac**

```bash
./startup.sh
```

Stop:

```bash
shutdown.bat
```

**Step 12: Verify Deployment**

Open:

```text
http://localhost:8080/myapp/hello
```

Output:

```text
Hello Tomcat
```

**Project Structure**

```text
MyApp
 ├── src
 │    ├── main
 │    │    ├── java
 │    │    ├── webapp
 │    │    │    ├── WEB-INF
 │    │    │    │    └── web.xml
 │    │    └── resources
 ├── pom.xml
 └── target
      └── myapp.war
```

**When to use**

* **Servlet** applications.
* **JSP** applications.
* **Spring MVC** (without Spring Boot).
* Applications deployed on an **organization-managed Tomcat server**.


**Common Interview Follow-up Questions**

**Q: Why do we package the application as a WAR?**

**Answer:** A **WAR** is the standard deployment format for applications running on an **external Tomcat** server.

**Q: Why is the Servlet API dependency marked as `provided`?**

**Answer:** Because the **Tomcat server already provides the Servlet API**, so it should not be included inside the WAR.

**Q: Which Tomcat configuration files are commonly used?**

**Answer:**

* **`server.xml`** – Configures **ports**, **connectors**, and server settings.
* **`web.xml`** – Configures **Servlets**, **filters**, and **URL mappings**.
* **`context.xml`** – Configures **DataSource** and other application resources.



## 14. How to remove default server from springboot application?

By default, **Spring Boot** uses **Embedded Tomcat**. If you want to deploy your application to an **external Tomcat server**, you need to **remove the embedded Tomcat** and package the application as a **WAR**.


**How it works**

1. Exclude the **Embedded Tomcat** dependency.
2. Change packaging from **JAR** to **WAR**.
3. Add **Tomcat** with **provided** scope.
4. Extend **SpringBootServletInitializer**.
5. Build the **WAR** and deploy it to the external **Tomcat** server.

**Step 1: Exclude Embedded Tomcat**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>

    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

**Step 2: Add Tomcat as `provided`**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```

**Step 3: Change Packaging**

```xml
<packaging>war</packaging>
```

**Step 4: Extend `SpringBootServletInitializer`**

```java
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

**Step 5: Build and Deploy**

Build:

```bash
mvn clean package
```

Generated:

```text
target/myapp.war
```

Copy the **WAR** file to:

```text
apache-tomcat/webapps/
```

Start **Tomcat** and access:

```text
http://localhost:8080/myapp
```


**Common Interview Follow-up Questions**

**Q: Why use `provided` scope?**

**Answer:** **`provided`** means **Tomcat is supplied by the external server**, so it is **not included** inside the WAR file.

**Q: Why change from JAR to WAR?**

**Answer:** A **JAR** is used with an **embedded server**, while a **WAR** is used for deployment to an **external servlet container** like Tomcat.

**Q: Is `SpringBootServletInitializer` required?**

**Answer:** **Yes**, when deploying a **Spring Boot** application to an **external Tomcat**, it initializes the application inside the servlet container.



## 16. How can we configure multiple databases in Spring Boot?

In **Spring Boot**, we can connect to **multiple databases** by creating separate configurations for each database. Each database has its own **DataSource**, **EntityManagerFactory**, **TransactionManager**, and **Repository** package.

**Key Features**

* **Supports multiple databases** in a single application.
* Each database has its own **DataSource**.
* Separate **EntityManagerFactory** for entity management.
* Separate **TransactionManager** for transaction handling.
* Repositories are mapped using **@EnableJpaRepositories**.

**How it works**

1. Configure multiple database properties in **application.yml** or **application.properties**.
2. Create a separate **DataSource** bean for each database.
3. Create an **EntityManagerFactory** for each database.
4. Create a **TransactionManager** for each database.
5. Enable repositories using **@EnableJpaRepositories** and specify the correct packages.

**Example**

**Project Structure**

```text
src/main/java
│
├── config
│   ├── MySqlConfig.java
│   └── PostgresConfig.java
│
├── user
│   ├── entity
│   │   └── User.java
│   ├── repository
│   │   └── UserRepository.java
│   └── service
│       └── UserService.java
│
├── order
│   ├── entity
│   │   └── Order.java
│   ├── repository
│   │   └── OrderRepository.java
│   └── service
│       └── OrderService.java
│
└── MultiDbApplication.java
```

**Step 1: Add Dependencies**

```xml
<dependencies>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>

    <!-- PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
    </dependency>

    <!-- Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

</dependencies>
```

---

**Step 2: Configure application.yml**

```yaml
spring:

  datasource:

    mysql:
      url: jdbc:mysql://localhost:3306/userdb
      username: root
      password: root
      driver-class-name: com.mysql.cj.jdbc.Driver

    postgres:
      url: jdbc:postgresql://localhost:5432/orderdb
      username: postgres
      password: postgres
      driver-class-name: org.postgresql.Driver
```

---

**Step 3: Create User Entity (MySQL)**

```java
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // getters and setters
}
```

---

**Step 4: Create Order Entity (PostgreSQL)**

```java
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    // getters and setters
}
```

---

**Step 5: Create Repositories**

#UserRepository

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

#OrderRepository

```java
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
```

---

**Step 6: Configure MySQL**

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
            "com.example.user.repository",
            "com.example.order.repository",
            "com.example.product.repository"
        },
        entityManagerFactoryRef = "userEntityManagerFactory",
        transactionManagerRef = "userTransactionManager"
)
public class MySqlConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(userDataSource())
                .packages("com.example.user.entity")
                .persistenceUnit("user")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("userEntityManagerFactory")
            EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }
}
```

---

**Step 7: Configure PostgreSQL**

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.order.repository",
        entityManagerFactoryRef = "orderEntityManagerFactory",
        transactionManagerRef = "orderTransactionManager"
)
public class PostgresConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.postgres")
    public DataSource orderDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(orderDataSource())
                .packages("com.example.order.entity")
                .persistenceUnit("order")
                .build();
    }

    @Bean
    public PlatformTransactionManager orderTransactionManager(
            @Qualifier("orderEntityManagerFactory")
            EntityManagerFactory emf) {

        return new JpaTransactionManager(emf);
    }
}
```

---

**Step 8: Create Services**

#UserService

```java
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional("userTransactionManager")
    public User save(User user) {
        return repository.save(user);
    }
}
```

#OrderService

```java
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional("orderTransactionManager")
    public Order save(Order order) {
        return repository.save(order);
    }
}
```

---

**Step 9: Create Controller**

```java
@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody Order order) {
        return orderService.save(order);
    }
}
```

---

**Step 10: Run the Application**

```java
@SpringBootApplication
public class MultiDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDbApplication.class, args);
    }
}
```


**Bean Relationship**

```text
                    MySQL
application.yml
      │
      ▼
userDataSource
      │
      ▼
userEntityManagerFactory
      │
      ▼
userTransactionManager
      │
      ▼
UserRepository
      │
      ▼
UserService


                 PostgreSQL
application.yml
      │
      ▼
orderDataSource
      │
      ▼
orderEntityManagerFactory
      │
      ▼
orderTransactionManager
      │
      ▼
OrderRepository
      │
      ▼
OrderService
```


**When to use**

* **Microservices** connecting to different databases.
* **Legacy system integration** with multiple data sources.
* **Reporting** database separate from the transactional database.
* Applications storing different modules in different databases.



**Common Interview Follow-up Questions**

**1. Why do we use `@Primary`?**

It tells Spring which **DataSource** should be the **default** when multiple beans of the same type exist.

**2. How do repositories know which database to use?**

Using **`@EnableJpaRepositories`**, we specify the repository package along with its **EntityManagerFactory** and **TransactionManager**.

**3. Can one transaction span multiple databases?**

**No**, a normal **`@Transactional`** works with **one TransactionManager**. For a single transaction across multiple databases, use a **Distributed Transaction** solution such as **JTA/XA** or a **Saga Pattern** in microservices.

**4. Can different databases use different database vendors?**

**Yes.** For example, one can be **MySQL** and another can be **PostgreSQL**, each with its own JDBC driver and configuration.


**5. What is the main challenge of multiple DBs?**

Managing **transactions, consistency, and configuration complexity**.

**6. When should you avoid multiple databases?**

When a single database can handle the workload, as multiple DBs increase **complexity and overhead**.


**7. Why separate Repository packages?**

* So each repository connects to the **correct database**.

**7. Can one Entity use both databases?**

**No.** A **JPA Entity** is mapped to **only one database** through a specific **EntityManagerFactory**.

**8. Can one service use both databases?**

* **Yes.** Inject both repositories or services and perform operations on each database.

**9. Does each database need its own TransactionManager?**

* **Yes.** Each **DataSource** should have its own **TransactionManager**.

**10. Can we use different database types together?**

* **Yes.** For example, **MySQL** and **PostgreSQL** can be used in the same Spring Boot application.



## 17. How to secure username and password?

To secure username and password, we should **never store passwords in plain text**.
We store **hashed passwords** using algorithms like **BCrypt**.
We also store secrets in **environment variables or secure vaults**, not in code.
We use **HTTPS** to encrypt data in transit and **Spring Security** for authentication and authorization.


** Simple Example (Spring Boot – BCrypt)**

```java
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "admin123";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Encoded Password: " + encodedPassword);

        // Check password
        boolean isMatch = encoder.matches("admin123", encodedPassword);
        System.out.println("Password Match: " + isMatch);
    }
}
```


* `encode()` → Hash password
* `matches()` → Verify password
* Even if DB is hacked, real password is not visible


** Where to Store Passwords Securely**

| Location | How                                   |
| -------- | ------------------------------------- |
| Local    | Environment Variables                 |
| Cloud    | AWS Secrets Manager / Azure Key Vault |
| Database | Store Hashed Password Only            |
| Network  | Use HTTPS                             |


## 18. What happens if DB goes down?


If the **database (DB) goes down**, the application cannot perform **data read/write operations**, leading to **request failures, errors, or partial system outage**, depending on how the system is designed.

**Simple Interview Definition**

When the **database is unavailable**, the application either **fails requests**, **returns errors**, or uses **fallback mechanisms like retry, caching, or circuit breakers** if implemented.

**Key Features**

* **Database connectivity failure**
* **Application requests fail**
* May cause **500 Internal Server Error**
* Impacts **read/write operations**
* Can trigger **retries or fallback mechanisms**
* Affects **availability and user experience**

**How It Works**

1. Application sends a request to the **database**.
2. DB is **down or unreachable**.
3. Connection attempt fails.
4. Application throws **SQL exception / timeout error**.
5. Response is either:

   * **Error response (default behavior)**
   * OR handled via **resilience patterns (retry, fallback, circuit breaker)**

**Flow**

```text id="dbfail1"
Client Request
      ↓
Application
      ↓
Database (DOWN)
      ↓
Connection Failure
      ↓
Exception / Timeout / 500 Error
```

**When to Use / Handle This Scenario**

You handle DB failure in:

* **Microservices systems**
* **High-availability applications**
* **Banking and payment systems**
* **Cloud-based applications**
* Systems requiring **zero downtime or resilience**

**Handling Techniques**

**1. Retry Mechanism**

* Automatically retries DB connection.

**2. Circuit Breaker**

* Stops requests temporarily when DB is down.
* Example: **Resilience4j, Hystrix (legacy)**

**3. Fallback Methods**

* Returns **cached or default response**.

**4. Connection Pooling**

* Helps manage DB connections efficiently (HikariCP).

**5. Caching**

* Uses **Redis or in-memory cache** to reduce DB dependency.

**Spring Boot Example (Circuit Breaker using Resilience4j)**

```java id="dbfail2"
@CircuitBreaker(name = "dbService", fallbackMethod = "fallbackResponse")
public String getDataFromDB() {
    return jdbcTemplate.queryForObject("SELECT name FROM users WHERE id=1", String.class);
}

public String fallbackResponse(Exception ex) {
    return "Database is currently unavailable. Showing cached data.";
}
```

**Explanation**

* If DB is **down**, circuit breaker triggers.
* Application calls **fallback method** instead of failing completely.
* Improves **system resilience**.

**What Happens Without Handling**

* **Request failures (500 errors)**
* **Application slowdown**
* **Thread blocking**
* Possible **system crash under load**
* Poor **user experience**

**What Happens With Proper Handling**

* Graceful **fallback response**
* Reduced system failure impact
* Better **availability**
* Improved **fault tolerance**

**Advantages of Handling DB Failure**

* Improves **system reliability**
* Prevents **complete application crash**
* Provides **graceful degradation**
* Enhances **user experience**
* Supports **high availability architecture**



**Common Interview Follow-up Questions**

**1. What error occurs when DB is down?**

Usually **SQLTimeoutException**, **ConnectionException**, or **500 Internal Server Error**.

**2. How do you prevent system failure when DB is down?**

Using **circuit breakers, caching, retries, and fallback mechanisms**.

**3. What is a circuit breaker?**

A pattern that **stops sending requests to a failing service (DB)** to prevent system overload.

**4. Can application run without database?**

Yes, partially, if **cache or fallback data** is available.

**5. What is the role of caching here?**

Caching reduces dependency on DB by serving **frequently used data from memory (Redis/in-memory cache)**.



## 19. How do you deploy the same code to multiple environments?


Deploying the same code to multiple environments means running the **same application build** (JAR/WAR) in different environments like **DEV, QA, STAGING, and PRODUCTION** by changing only **configuration settings**, not the code.

**Simple Interview Definition**

It is a practice where the **same Spring Boot codebase** is deployed across multiple environments using **externalized configuration and environment-specific profiles**.

**Key Features**

* Same **codebase across all environments**
* Different **configurations per environment**
* Uses **Spring Profiles**
* Supports **externalized configuration**
* Reduces **code duplication**
* Enables **CI/CD automation**

**How It Works**

1. Write a **single Spring Boot application**.
2. Define environment-specific configurations using **profiles**.
3. Use **application-dev.yml, application-qa.yml, application-prod.yml**.
4. Activate profile using **environment variable or command line**.
5. Same build is deployed everywhere with different configs.

**Flow**

```text id="env1"
Single Codebase
      ↓
Build (JAR/WAR)
      ↓
DEV / QA / PROD Environments
      ↓
Different Configurations (Profiles)
```

**When to Use**

* **Enterprise applications**
* **Microservices architecture**
* **CI/CD pipelines**
* Applications deployed in **multiple stages (Dev → QA → Prod)**
* Cloud deployments (**AWS, Azure, Kubernetes**)

**Spring Boot Profile Configuration Example**

**1. application-dev.yml**

```yaml id="env2"
server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/devdb
```

**2. application-prod.yml**

```yaml id="env3"
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://prod-db:3306/proddb
```

**3. Activating Profile**

```bash id="env4"
java -jar app.jar --spring.profiles.active=prod
```

OR

```bash id="env5"
export SPRING_PROFILES_ACTIVE=dev
```

**4. Code Example (Using Profiles)**

```java id="env6"
@Configuration
@Profile("dev")
public class DevConfig {
    public String getEnv() {
        return "Development Environment";
    }
}
```

```java id="env7"
@Configuration
@Profile("prod")
public class ProdConfig {
    public String getEnv() {
        return "Production Environment";
    }
}
```

**Explanation**

* Spring loads only the **active profile configuration**.
* Same application behaves differently based on **environment settings**.
* No code changes required for deployment.

**Deployment Approaches**

* **Spring Profiles (most common)**
* **Environment Variables**
* **Docker environment configs**
* **Kubernetes ConfigMaps & Secrets**
* **CI/CD pipelines (Jenkins, GitHub Actions)**

**Advantages**

* Same **artifact across environments**
* Easy **configuration management**
* Reduces **human errors**
* Supports **automated deployments**
* Improves **scalability and maintainability**

**Challenges**

* Misconfiguration risk in **production**
* Need proper **secret management**
* Requires strong **CI/CD discipline**



**Common Interview Follow-up Questions**

**1. What is Spring Profile?**

It is a feature that allows Spring to load **environment-specific configurations** like **dev, test, or prod**.

**2. Can we change configuration without rebuilding code?**

Yes, using **externalized configuration (YAML, properties, environment variables)**.

**3. How is this handled in CI/CD pipelines?**

The same artifact is deployed, and the environment is selected using **pipeline variables or deployment scripts**.

**4. What is the benefit of same code in all environments?**

It ensures **consistency**, reduces **deployment issues**, and avoids **code duplication**.

**5. What is the risk in multi-environment deployment?**

Incorrect **profile activation or configuration mismatch** can cause production issues.


# ✅ 16. Java - Servlets and JSP


## 1. What is servlet in Java?

A **Servlet** is a **Java class** that runs inside a **Servlet Container** (like **Tomcat**) and handles **client HTTP requests** and generates **HTTP responses**. It is the core technology behind many Java web applications.

**Key Features:**

* **Server-side** Java component.
* Handles **HTTP GET, POST, PUT, DELETE** requests.
* Supports **multithreading** (one servlet instance can serve multiple requests).
* Managed by a **Servlet Container**.

**How it Works:**

1. Client sends an **HTTP request**.
2. The **Servlet Container** receives the request.
3. It creates **`HttpServletRequest`** and **`HttpServletResponse`** objects.
4. The container calls the servlet's **`service()`** method.
5. Based on the request type, `service()` invokes **`doGet()`**, **`doPost()`**, etc.
6. The servlet processes the request and sends back an **HTTP response** to the client.

**Servlet Lifecycle:**

* **`init()`** → Called **once** when the servlet is created.
* **`service()`** → Called for **every incoming request**.
* **`destroy()`** → Called **once** before the servlet is removed.

**When to Use:**

* To build **Java web applications** and **REST APIs**.
* When you need to process **HTTP requests** and generate **dynamic web content**.
* It is the **foundation** on which frameworks like **Spring MVC** and **Spring Boot** work.

**Code Example:**

```java
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write("Hello, Servlet!");
    }
}
```

## 2. What is the servlet lifecycle?

The **Servlet Lifecycle** defines the stages a **Servlet** goes through from **creation** to **destruction**. The **Servlet Container** (like **Tomcat**) manages this lifecycle automatically.

**Lifecycle Methods:**

1. **`init()`**

   * Called **only once** when the servlet is loaded.
   * Used for **initialization**, such as loading configuration or creating resources.

2. **`service()`**

   * Called for **every client request**.
   * It processes the request and internally calls methods like **`doGet()`**, **`doPost()`**, **`doPut()`**, etc., based on the HTTP method.

3. **`destroy()`**

   * Called **only once** before the servlet is removed from memory.
   * Used to **release resources**, such as closing database connections or files.

**How it Works:**

1. The **Servlet Container** loads the servlet.
2. It creates **one servlet instance**.
3. Calls **`init()`** once.
4. For every incoming request, calls **`service()`**.
5. When the server shuts down or the servlet is undeployed, calls **`destroy()`**.

**Key Features:**

* **Managed by the Servlet Container**.
* **`init()`** and **`destroy()`** are called only once.
* **`service()`** is called for every request.
* A **single servlet instance** can handle **multiple requests using multiple threads**.

**When to Use:**

* When building **Java web applications** that process **HTTP requests and responses**.
* Understanding the lifecycle helps in **resource initialization**, **request handling**, and **resource cleanup**.

**Code Example:**

```java
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Handling GET request");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet destroyed");
    }
}
```

## 3. What is JSP (JavaServer Pages)?

**JSP (JavaServer Pages)** is a **server-side technology** used to create **dynamic web pages** by embedding **Java code** inside **HTML**. It simplifies web development by allowing developers to mix presentation (HTML) with Java logic.

**Key Features:**

* Creates **dynamic web content**.
* Combines **HTML** and **Java** in a single file.
* Runs on top of the **Servlet** technology.
* Supports **Expression Language (EL)** and **JSTL** for cleaner code.

**How it Works:**

1. A client requests a **`.jsp`** page.
2. The **Servlet Container** (e.g., Tomcat) translates the JSP into a **Servlet**.
3. The generated Servlet is **compiled** into a Java class.
4. The Servlet executes and generates an **HTML response**.
5. The HTML is sent back to the client browser.
6. On subsequent requests, the already compiled Servlet is reused unless the JSP file changes.

**When to Use:**

* To build **dynamic web pages** in traditional **Java web applications**.
* When you need to display data received from **Servlets** or **backend services**.
* Commonly used with the **MVC (Model-View-Controller)** pattern as the **View** layer.

**Code Example:**

```jsp
<html>
<body>
    <h2>Welcome to JSP!</h2>
    <p>Current Time: <%= new java.util.Date() %></p>
</body>
</html>
```


## 4. What is the difference between servlet and JSP?

| **Feature**                | **Servlet**                                             | **JSP (JavaServer Pages)**                                        |
| -------------------------- | ------------------------------------------------------- | ----------------------------------------------------------------- |
| **Purpose**                | Used for **request processing** and **business logic**. | Used for **presentation** and generating **dynamic web pages**.   |
| **Code Style**             | Written completely in **Java**.                         | Written mainly in **HTML** with embedded **Java/EL/JSTL**.        |
| **Compilation**            | Directly compiled into **bytecode**.                    | First **translated into a Servlet**, then compiled.               |
| **Ease of UI Development** | Difficult because HTML is written inside Java code.     | Easy because Java code is embedded inside HTML.                   |
| **Performance**            | Slightly faster since it is already compiled.           | First request is slower due to JSP-to-Servlet conversion.         |
| **Role in MVC**            | Usually acts as the **Controller**.                     | Usually acts as the **View**.                                     |
| **Extension**              | `.java`                                                 | `.jsp`                                                            |
| **Inheritance**            | Extends **`HttpServlet`**.                              | Internally converted into a class that extends **`HttpServlet`**. |

**Key Difference:**

* **Servlet** is mainly used for **handling requests and implementing business logic**.
* **JSP** is mainly used for **displaying data and creating dynamic user interfaces**.

**When to Use:**

* Use **Servlet** for **request handling, validation, and controller logic**.
* Use **JSP** for **rendering dynamic HTML pages**.



# ✅ 16. Java - Hibernate / JPA 

## 5. What is ORM?
**ORM(Object Relational Mapping)** is a technique that maps **Java Objects** to **Database Tables** and database records to Java objects.

**Key Features**

* Maps objects to database tables
* Reduces SQL boilerplate code
* Simplifies database operations
* Improves code readability and maintainability

**How It Works**

ORM automatically converts Java objects into database records and database records back into Java objects.

For example:

* **Class** → **Table**
* **Object** → **Row**
* **Field** → **Column**

**Code Example**

```java
@Entity
public class User {

    @Id
    private Long id;

    private String name;
}
```

```java
User user = new User();
user.setName("John");

userRepository.save(user);
```

ORM automatically generates and executes the SQL needed to save the object.


**When to Use**

Use **ORM** when working with relational databases and Java applications that need frequent database operations.

Examples:

* Spring Boot applications
* Enterprise applications
* Microservices with databases


## 6. What is JPA?

**JPA(Java Persistence API)** is a **Java Specification** for managing and persisting data between Java objects and relational databases. It provides a standard way to perform **ORM (Object Relational Mapping)**.

**Key Features**

* Standard API for database persistence
* Supports **ORM**
* Uses annotations such as **@Entity**, **@Id**, and **@Table**
* Reduces boilerplate JDBC code
* Database-independent

**How It Works**

JPA maps Java classes to database tables and Java objects to table rows.

* **Class** → **Table**
* **Object** → **Row**
* **Field** → **Column**

JPA defines the rules, while frameworks such as **Hibernate** implement those rules.

**Code Example**

```java
@Entity
public class User {

    @Id
    private Long id;

    private String name;
}
```

```java
userRepository.save(user);
```

JPA automatically maps the object and persists it to the database.


**When to Use**

Use **JPA** when building Java applications that interact with relational databases.

Examples:

* Spring Boot applications
* Enterprise applications
* Microservices with databases


## 6. What is Hibernate?

**Hibernate** is an **ORM (Object Relational Mapping) Framework** that simplifies database operations by mapping **Java Objects** to **Database Tables**. It is the most popular implementation of **JPA**.

**Key Features**

* Implements **JPA**
* Provides **ORM** functionality
* Automatically generates SQL queries
* Supports **Caching**
* Supports **Lazy Loading**
* Reduces JDBC boilerplate code

**How It Works**

Hibernate maps Java classes to database tables and Java objects to table rows.

* **Class** → **Table**
* **Object** → **Row**
* **Field** → **Column**

When you save an object, Hibernate automatically generates and executes the required SQL.

**Code Example**

```java
@Entity
public class User {

    @Id
    private Long id;

    private String name;
}
```

```java
User user = new User();
user.setName("John");

userRepository.save(user);
```

Hibernate automatically generates SQL similar to:

```sql
INSERT INTO user (name) VALUES ('John');
```

**When to Use**

Use **Hibernate** when working with relational databases in Java applications.

Examples:

* Spring Boot applications
* Enterprise applications
* Microservices with databases


**Analogy:**
- **ORM** = "Driving a car" (concept) 🚗
- **JPA** = "Driver's license test" (standard rules) 📋
- **Hibernate** = "Toyota Camry" (actual car implementing the rules) 🚙

| Concept   | Analogy                 |
| --------- | ----------------------- |
| ORM       | Online shopping concept |
| Hibernate | Amazon application      |


| | ORM | JPA | Hibernate |
|---|---|---|---|
| **What it is** | Concept/technique | Java specification | Framework/library |
| **Who defines it** | General pattern | Oracle / Jakarta EE | Red Hat |
| **Analogous to** | "web standards" | JDBC interface | A JDBC driver |


## 7. What is Projection in JPA?

**Projection** in JPA means **fetching only the required fields** instead of loading the entire **Entity**. This improves **performance** by reducing the amount of data retrieved from the database.

**Why Use Projection?**

1. **Fetches only required columns**.
2. **Improves performance**.
3. **Reduces memory usage**.
4. Ideal for **read-only** queries.

**Example**

**Entity**

```java
@Entity
public class Student {

    @Id
    private Long id;

    private String name;
    private int age;
    private String email;
}
```

**DTO Projection**

```java
public class StudentDto {

    private String name;
    private String email;

    public StudentDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
```

**JPQL Query**

```java
List<StudentDto> students = entityManager.createQuery(
    "SELECT new com.example.StudentDto(s.name, s.email) " +
    "FROM Student s",
    StudentDto.class
).getResultList();
```

Only **`name`** and **`email`** are fetched instead of the entire **Student** entity.

**Types of Projection**

1. **DTO Projection** – Returns a **custom DTO** using the **`new`** keyword.
2. **Interface Projection** – Returns data through an **interface** (commonly used in **Spring Data JPA**).
3. **Scalar Projection** – Returns selected values such as **`Object[]`** or **`Tuple`**.
4. **Entity Projection** – Returns the **entire Entity**.


## 7. Different between ORM, JPA and Hibernate?

| Aspect                 | ORM                                                           | JPA                                                                                                                                              | Hibernate                                                                                                                                                |
| ---------------------- | ------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------- |
| What it is             | Concept/technique for mapping objects to relational databases | Java specification (API) defining how ORM should work                                                                                            | Framework that implements JPA specification stackoverflow+1                                                                                              |
| Type                   | Approach/methodology (not a tool)                             | Specification/API (interface only)                                                                                                               | Full ORM framework/provider linkedin+1                                                                                                                   |
| Framework?             | ❌ No framework (it's the concept)                             | ❌ No framework (just API spec)                                                                                                                   | ✅ Yes - full ORM framework interviewbit+1                                                                                                                |
| Package                | N/A                                                           | javax.persistence or jakarta.persistence                                                                                                         | org.hibernate geeksforgeeks                                                                                                                              |
| Key Annotations        | N/A                                                           | @Entity, @Id, @Table, @Column, @OneToMany, @ManyToOne, @Transient, @GeneratedValue geeksforgeeks                                                 | All JPA annotations plus Hibernate-specific: @Lazy, @BatchSize, @Formula, @Where, @Cache geeksforgeeks                                                   |
| Main Interface         | N/A                                                           | EntityManager, EntityManagerFactory                                                                                                              | SessionFactory, Session (Hibernate-specific) + JPA EntityManager geeksforgeeks                                                                           |
| Query Language         | N/A                                                           | JPQL (Java Persistence Query Language)                                                                                                           | HQL (Hibernate Query Language) + supports JPQL geeksforgeeks                                                                                             |
| Setup/Maven Dependency | N/A                                                           | Need implementation (e.g., hibernate-core) + javax.persistence-api                                                                               | org.hibernate:hibernate-core or hibernate-entitymanager geeksforgeeks                                                                                    |
| Config File            | N/A                                                           | persistence.xml (defines persistence unit)                                                                                                       | hibernate.cfg.xml or persistence.xml (JPA mode) + application.properties in Spring Boot geeksforgeeks                                                    |
| Spring Boot Setup      | N/A                                                           | Auto-configured via spring-boot-starter-data-jpa                                                                                                 | Auto-configured as default JPA provider in Spring Boot youtube                                                                                           |
| Example Dependency     | N/A                                                           | xml<br><dependency><br>  <groupId>org.springframework.boot</groupId><br>  <artifactId>spring-boot-starter-data-jpa</artifactId><br></dependency> | xml<br><dependency><br>  <groupId>org.hibernate</groupId><br>  <artifactId>hibernate-core</artifactId><br>  <version>6.x.x</version><br></dependency>    |
| Entity Example         | N/A                                                           | java<br>@Entity<br>@Table(name="users")<br>public class User {<br>  @Id<br>  @GeneratedValue<br>  private Long id;<br>}                          | Same as JPA plus Hibernate extensions: java<br>@Entity<br>@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)<br>@Lazy(false)<br>public class User { ... } |
| Additional Features    | N/A                                                           | Standard ORM features only                                                                                                                       | Advanced caching, lazy loading strategies, batch processing, dirty checking, second-level cache, native SQL support stackoverflow+1                      |
| Switchability          | N/A                                                           | ✅ Easy to switch implementations (portable API)                                                                                                  | ❌ Tied to Hibernate if using Hibernate-specific features stackoverflow                                                                                   |

## 7. Why do we use Hibernate if we can use SQL?


**Hibernate** is an **ORM (Object Relational Mapping) Framework**. **SQL** and **JPQL** are **query languages**, not alternatives to Hibernate.

Think of it this way:

* **Hibernate** = Complete framework to manage database operations.
* **HQL/JPQL** = Languages used to write custom queries inside Hibernate/JPA.

**Why Hibernate?**

* **Object-Relational Mapping (ORM)** – Maps **Java objects** to **database tables**.
* **Less Boilerplate Code** – No need to write repetitive **JDBC** code for connections, statements, and result mapping.
* **Database Independence** – The same code works with **MySQL, PostgreSQL, Oracle**, etc., with minimal changes.
* **Automatic CRUD Operations** – Generates **INSERT, UPDATE, DELETE, SELECT** queries automatically.
* **Relationship Management** – Easily handles **One-to-One, One-to-Many, Many-to-Many** relationships using annotations.
* **Caching** – Supports **First-Level** and **Second-Level Cache** to improve performance.
* **Transaction Management** – Integrates well with **Spring Transaction Management**.
* **Lazy Loading** – Loads related data only when needed, reducing unnecessary database calls.

**When should you use SQL instead?**

Use **SQL (or Native SQL)** when:

* You need **complex joins** or database-specific features.
* You want **maximum performance** for critical queries.
* You need **stored procedures**, **window functions**, or advanced SQL features.
* Bulk operations are easier to write directly in SQL.

**Example**

**Using SQL (JDBC)**

```java
String sql = "SELECT * FROM employee WHERE id = ?";
PreparedStatement ps = connection.prepareStatement(sql);
ps.setInt(1, 1);
ResultSet rs = ps.executeQuery();
```

**Using Hibernate**

```java
Employee emp = entityManager.find(Employee.class, 1);
```

Hibernate automatically generates the required SQL behind the scenes.



**Common Interview Follow-up Questions**

1. **Can Hibernate completely replace SQL?**

   * **No.** Hibernate generates SQL internally, and **Native SQL** is still used for complex or performance-critical queries.

2. **Does Hibernate use SQL internally?**

   * **Yes.** Hibernate converts HQL/JPQL or entity operations into **SQL** executed by the database.

3. **When would you choose SQL over Hibernate?**

   * For **complex queries**, **bulk updates**, **stored procedures**, or **performance optimization**.

4. **Can we use Hibernate and SQL together?**

   * **Yes.** This is very common. Use Hibernate for normal CRUD operations and **Native SQL** for complex queries.

5. **What is the biggest advantage of Hibernate?**

   * **Faster development**, **less boilerplate code**, **automatic ORM mapping**, and **better maintainability**.


## 8. What Does `@BatchSize` Annotation Do?

**`@BatchSize`** is a Hibernate annotation that **reduces the N+1 Query Problem** by **loading multiple lazy entities or collections in a single query** instead of executing one query for each entity.

**Why Use `@BatchSize`?**

1. **Reduces the number of SQL queries**.
2. **Improves performance** when using **Lazy Loading**.
3. Helps minimize the **N+1 Query Problem**.

**Example**

```java id="7rv70q"
@Entity
public class Department {

    @Id
    private Long id;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<Employee> employees;
}
```

If you load **20 Departments** and access their **employees**:

* **Without `@BatchSize`** → **1 + 20 queries** (N+1 problem).
* **With `@BatchSize(size = 10)`** → **1 + 2 queries** to load employees in batches of **10**.

**How It Works**

* Hibernate collects multiple **lazy-loaded entities or collections**.
* It loads them in **batches** using **`IN (...)`** queries.
* The **`size`** defines the **maximum number of entities** loaded in one batch.


## 8. Difference between `save() and `persist()`

Both **`save()`** and **`persist()`** are used to store an entity in the database, but they belong to different APIs and have some behavioral differences.


* **Use `save()`** when working directly with **Hibernate `Session`** and you need the **generated ID** immediately.

* **Use `persist()`** when working with **JPA (`EntityManager`)** because it is the **standard JPA method** and is recommended in **Spring Boot/JPA** applications.


**Key Features**

| Feature      | **save()**                  | **persist()**               |
| ------------ | --------------------------- | --------------------------- |
| API          | **Hibernate** method        | **JPA** standard method     |
| Return Type  | Returns generated **ID**    | Returns **void**            |
| Standard     | Hibernate-specific          | JPA-standard                |
| Entity State | Makes entity **Persistent** | Makes entity **Persistent** |
| Portability  | Less portable               | More portable               |

**How It Works**

**- save()**

* A method provided by **Hibernate Session**.
* Saves the entity and returns the generated **primary key**.
* Can trigger an immediate insert to generate the ID.

```java
Session session = sessionFactory.openSession();

Employee emp = new Employee();
emp.setName("John");

Long id = (Long) session.save(emp);

System.out.println(id);
```

**- persist()**

* A method provided by **JPA EntityManager**.
* Makes the entity managed by the persistence context.
* Does not return the generated ID.
* The actual SQL INSERT usually happens during **flush** or **transaction commit**.

```java
EntityManager em = entityManagerFactory.createEntityManager();

Employee emp = new Employee();
emp.setName("John");

em.persist(emp);
```

**Why Use It**

* Use **`persist()`** when working with **JPA** because it follows the standard specification.
* Use **`save()`** when directly using **Hibernate APIs** and you need the generated ID immediately.

**When to Use**

**Use `persist()`**

* In **Spring Boot + JPA** applications.
* When writing **portable code** that can work with different JPA providers.
* Recommended for modern enterprise applications.

```java
@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Employee emp) {
        em.persist(emp);
    }
}
```

**Use `save()`**

* When working directly with **Hibernate Session**.
* When you need the generated ID returned immediately.

```java
Session session = sessionFactory.openSession();

Long id = (Long) session.save(employee);
```


## 9. Difference between save() and saveAndFlush()?

Both **`save()`** and **`saveAndFlush()`** are methods provided by **Spring Data JPA** to persist entities, but they differ in **when changes are written to the database**.


* **`save()`**: Saves the entity in the Persistence Context. The SQL is usually executed later during `flush()` or transaction commit.

* **`saveAndFlush()`**: Saves the entity and immediately flushes the Persistence Context, so the SQL is executed in the database right away.



**Key Features**

| Feature                   | **save()**                 | **saveAndFlush()**                     |
| ------------------------- | -------------------------- | -------------------------------------- |
| Save Entity               | Yes                        | Yes                                    |
| Flush Persistence Context | No                         | Yes                                    |
| SQL Execution             | At flush/commit time       | Immediately                            |
| Return Type               | Saved Entity               | Saved Entity                           |
| Performance               | Better for bulk operations | Slightly slower due to immediate flush |

**How It Works**

**save()**

* Saves the entity in the **Persistence Context**.
* SQL query is not necessarily executed immediately.
* Changes are usually sent to the database during **flush** or **transaction commit**.

```java
Employee emp = new Employee();
emp.setName("John");

employeeRepository.save(emp);
```

**saveAndFlush()**

* Saves the entity and immediately calls **flush()**.
* Forces Hibernate/JPA to execute the SQL statement right away.
* Changes become visible in the database immediately within the current transaction.

```java
Employee emp = new Employee();
emp.setName("John");

employeeRepository.saveAndFlush(emp);
```

**Why Use It**

**save()**

* Better performance.
* Lets Hibernate optimize SQL execution.
* Recommended for most CRUD operations.

**saveAndFlush()**

* Ensures data is immediately written to the database.
* Useful when subsequent code depends on the database state.

**When to Use**

**Use `save()`**

* Standard create/update operations.
* Batch inserts or updates.
* Most Spring Boot applications.

```java
employeeRepository.save(employee);
```

**Use `saveAndFlush()`**

* Need the data immediately available in the database.
* Calling a stored procedure after saving.
* Running a query in the same transaction that depends on the newly saved data.

```java
employeeRepository.saveAndFlush(employee);

Employee result =
        employeeRepository.findById(employee.getId()).get();
```

**Example**

```java
@Transactional
public void createEmployee() {

    Employee emp = new Employee();
    emp.setName("John");

    employeeRepository.saveAndFlush(emp);

    // SQL INSERT already executed here
    System.out.println("Employee saved in DB");
}
```

**Common Interview Follow-up Questions**

**1. Does `saveAndFlush()` commit the transaction?**

No. It only **flushes** changes to the database. The transaction is still committed later.

**2. Can the data be rolled back after `saveAndFlush()`?**

Yes. Even though the SQL has been executed, the transaction can still be **rolled back** if it has not been committed.

**3. Which method is faster?**

**`save()`** is generally faster because it avoids an immediate flush and allows Hibernate to optimize database operations.

**4. What is the difference between `flush()` and `commit()`?**

* **`flush()`** → Sends SQL statements to the database but **does not permanently save** the changes.
* **`commit()`** → Permanently saves the changes by **committing the transaction**.


**5. What is `flush()`?**

**A:** **`flush()`** synchronizes the **Persistence Context** with the **database** by executing all pending **INSERT**, **UPDATE**, and **DELETE** SQL statements without committing the transaction.



| Aspect               | save()                                               | saveAndFlush()                                                     |
| -------------------- | ---------------------------------------------------- | ------------------------------------------------------------------ |
| Repository Interface | CrudRepository                                       | JpaRepository                                                      |
| Flush Timing         | Delays until flush() or commit()                     | Flushes immediately during execution baeldung+1                    |
| Database Write       | Adds to transactional buffer (memory)                | Forces database to write changes to disk right away tutorialspoint |
| Data Visibility      | Changes not visible outside transaction until commit | Changes visible outside the transaction immediately tutorialspoint |
| Bulk Save            | Supports saveAll() for batch operations              | Doesn't support bulk operations tutorialspoint                     |
| Performance          | More efficient (allows batching)                     | Less efficient (immediate SQL execution) codemia                   |



## 10. What is the difference between DAO and DTO?

**DAO (Data Access Object)** and **DTO (Data Transfer Object)** are common design patterns used in Java applications.

* **DAO** is responsible for **accessing and managing data** in the database.
* **DTO** is a simple object used to **transfer data** between different layers of an application.

| **Feature**        | **DAO**                                    | **DTO**                                                    |
| ------------------ | ------------------------------------------ | ---------------------------------------------------------- |
| **Full Form**      | **Data Access Object**                     | **Data Transfer Object**                                   |
| **Purpose**        | Interacts with the **database**.           | Transfers **data between layers**.                         |
| **Contains**       | Database operations like **CRUD**.         | Only **fields, getters, and setters** (no business logic). |
| **Layer**          | **Persistence/Data Access Layer**.         | Used between **Controller, Service, and DAO** layers.      |
| **Responsibility** | Fetches, saves, updates, and deletes data. | Carries data from one layer to another.                    |

**How it Works:**

1. The **Controller** receives a request.
2. The **Service** layer processes the business logic.
3. The **DAO** fetches or updates data in the database.
4. The data is mapped to a **DTO**.
5. The **DTO** is returned to the client without exposing the internal entity.

**Key Features:**

* **DAO** separates **database logic** from business logic.
* **DTO** reduces unnecessary data transfer and improves security.
* Together, they help create a **clean and maintainable architecture**.

**When to Use:**

* Use **DAO** when interacting with a **database**.
* Use **DTO** when sending or receiving data between **application layers** or **REST APIs**.

**Code Example:**

**DTO:**

```java id="j4z8np"
public class UserDTO {
    private Long id;
    private String name;

    // Getters and Setters
}
```

**DAO:**

```java id="g6x3tr"
@Repository
public class UserDAO {

    @Autowired
    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
```


## 11. What is the N+1 Query Problem and How Do You Fix It?

The **N+1 Query Problem** is a performance issue in **JPA/Hibernate** where **1 query** is executed to fetch the parent records, and then **N additional queries** are executed to fetch the related child records. This results in **too many database calls** and slower performance.

**Key Features:**

* Commonly occurs with **`LAZY`** loading.
* Causes **multiple unnecessary SQL queries**.
* Degrades **application performance**.
* Frequently seen in **`@OneToMany`** and **`@ManyToOne`** relationships.

**How it Works:**

1. Fetch all parent entities with **1 query**.
2. Iterate over the parent entities.
3. Access a lazy-loaded child collection.
4. Hibernate executes **one extra query for each parent entity**.
5. Total queries = **1 + N**.

**Example:**
Suppose there are **10 Users**, and each has a list of **Orders**:

* `SELECT * FROM users;` → **1 query**
* For each user: `SELECT * FROM orders WHERE user_id = ?;` → **10 queries**

**Total = 11 queries (1 + N).**

**How to Fix It:**

* Use **`JOIN FETCH`** in JPQL.
* Use **`@EntityGraph`** to fetch related entities together.
* Use **DTO projections** when only specific data is needed.
* Avoid changing everything to **`EAGER`** fetching, as it can create other performance issues.

**When to Use the Fix:**

* When loading **parent and child entities together**.
* In APIs or reports where related data is always required.
* To reduce **database round trips** and improve performance.

**Code Example (Using `JOIN FETCH`):**

```java id="p8v4mq"
@Query("SELECT u FROM User u JOIN FETCH u.orders")
List<User> findAllUsersWithOrders();
```

**Code Example (Problem Scenario):**

```java id="x2n7kr"
List<User> users = userRepository.findAll();

for (User user : users) {
    System.out.println(user.getOrders().size()); // Triggers extra query for each user
}
```

Or use `@EntityGraph`:

```java
@EntityGraph(attributePaths = {"customer"})
List<Order> findAll();
```


## 12. What is JPQL vs Native Query?

**JPQL (Java Persistence Query Language)** and **Native Query** are two ways to query a database in **JPA/Hibernate**.

* **JPQL** is an object-oriented query language that works with **entity classes and their fields**, not database tables and columns.

* **Native Query** is a database-specific SQL query that works directly with **tables and columns**.

| **Feature**               | **JPQL**                                | **Native Query**                                                    |
| ------------------------- | --------------------------------------- | ------------------------------------------------------------------- |
| **Works On**              | **Entity classes and their fields**.    | **Database tables and columns**.                                    |
| **Syntax**                | Java object-oriented query language.    | Standard SQL syntax.                                                |
| **Database Independence** | **Database-independent**.               | May be **database-specific**.                                       |
| **Portability**           | High.                                   | Lower due to SQL dialect differences.                               |
| **Complex Queries**       | Limited for advanced database features. | Supports all SQL features, joins, functions, and stored procedures. |

**How it Works:**

* **JPQL:** Hibernate translates the JPQL statement into the appropriate SQL for the underlying database.
* **Native Query:** The SQL query is sent directly to the database without translation.

**Key Features:**

* **JPQL**

  * Works with **entities**.
  * Easy to maintain.
  * Portable across different databases.
* **Native Query**

  * Uses **raw SQL**.
  * Better for **complex queries** and database-specific features.
  * Can provide better performance in some cases.

**When to Use:**

* Use **JPQL** for most CRUD operations and when you want **database-independent** code.
* Use **Native Query** for **complex SQL**, **stored procedures**, or when you need **database-specific functions**.

**Code Example:**

**JPQL:** (Java Persistence Query Language) works with entity class names and field names — not table names. It's database-independent.

```java id="b7n3kp"
@Entity
public class Employee {

    @Id
    private Long id;

    private String name;
    private double salary;
}

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeesBySalary(@Param("salary") double salary);
}

// Usage
List<Employee> employees = employeeRepository.findEmployeesBySalary(50000);
```

**Native Query:** uses actual SQL with real table and column names. Use it when you need database-specific features or complex queries JPQL can't handle.

```java id="m4x8qr"
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee WHERE salary > :salary",
           nativeQuery = true)
    List<Employee> findEmployeesNative(@Param("salary") double salary);
}

// Usage
List<Employee> employees = employeeRepository.findEmployeesNative(50000);
```

## 12. What is JPQL and How Does It Differ from SQL??


**JPQL (Java Persistence Query Language)** is a **database-independent query language** used in **JPA/Hibernate**. It queries **Entity objects** instead of **database tables**.

**Example JPQL**

```java
String jpql = "SELECT s FROM Student s WHERE s.name = :name";

List<Student> students = entityManager
        .createQuery(jpql, Student.class)
        .setParameter("name", "John")
        .getResultList();
```

**Equivalent SQL**

```sql
SELECT * 
FROM student
WHERE name = 'John';
```

**JPQL vs SQL**

| **JPQL**                                  | **SQL**                                   |
| ----------------------------------------- | ----------------------------------------- |
| Works with **Entities**                   | Works with **Tables**                     |
| Uses **Entity names** and **field names** | Uses **Table names** and **column names** |
| **Database-independent**                  | **Database-specific**                     |
| Returns **Java objects**                  | Returns **Rows and columns**              |
| Used with **JPA/Hibernate**               | Used directly on the **Database**         |

**When to Use JPQL**

* Use **JPQL** when working with **JPA/Hibernate Entities**.
* Use **SQL (Native Query)** when you need:

  * **Database-specific features**
  * **Complex joins or functions**
  * **Better performance** for certain queries


## 13. What is JPA Cascade?


**Cascade** in JPA means that an operation performed on a **parent Entity** is **automatically applied** to its **child Entities**.

**Why Use Cascade?**

1. **Automatically propagates operations** from parent to child.
2. **Reduces boilerplate code**.
3. Keeps related entities **consistent**.

**Example**

```java
@Entity
public class Department {

    @Id
    private Long id;

    @OneToMany(mappedBy = "department",
               cascade = CascadeType.ALL)
    private List<Employee> employees;
}
```

```java
Department department = new Department();
Employee employee = new Employee();

department.setEmployees(List.of(employee));

entityManager.persist(department);
```

Because of **`CascadeType.ALL`**, the **Employee** is also saved automatically.

**Cascade Types**

1. **`PERSIST`** – Saves the child when the parent is saved.
2. **`MERGE`** – Updates the child when the parent is updated.
3. **`REMOVE`** – Deletes the child when the parent is deleted.
4. **`REFRESH`** – Reloads the child from the database.
5. **`DETACH`** – Detaches the child from the Persistence Context.
6. **`ALL`** – Applies **all** cascade operations.


## 14. **What is EntityManager and How Does It Differ from Session?**

**`EntityManager`** is the **JPA interface** used to manage **Entity lifecycle**, perform **CRUD operations**, execute **JPQL queries**, and manage the **Persistence Context**.

**`Session`** is the **Hibernate-specific implementation** that provides all `EntityManager` features plus additional Hibernate-specific functionality.

**Example**

```java
@PersistenceContext
private EntityManager entityManager;

Student student = new Student();
student.setName("John");

entityManager.persist(student);
```

**EntityManager vs Session**

| **EntityManager**                  | **Session**                                    |
| ---------------------------------- | ---------------------------------------------- |
| **JPA standard interface**         | **Hibernate-specific interface**               |
| **Database-independent**           | **Hibernate-specific**                         |
| Used in **JPA applications**       | Used in **Hibernate applications**             |
| Supports **standard JPA features** | Supports **JPA + Hibernate-specific features** |
| Can be **unwrapped** to `Session`  | Can be used directly                           |

**Getting Session from EntityManager**

```java
Session session = entityManager.unwrap(Session.class);
```

**When to Use?**

* Use **`EntityManager`** for **portable JPA applications**.
* Use **`Session`** only when you need **Hibernate-specific features**.

## 14. **What is `@Version` and Why Is It Needed?**

**`@Version`** is a JPA annotation used for **Optimistic Locking**. It prevents **lost updates** when **multiple users** try to update the same entity at the same time.

**Why Use `@Version`?**

1. Prevents **concurrent update conflicts**.
2. Ensures **data consistency**.
3. Automatically detects if another transaction has already modified the entity.

**Example**

```java id="gwgbf2"
@Entity
public class Product {

    @Id
    private Long id;

    private String name;

    @Version
    private Long version;
}
```

**How It Works**

1. Two users load the **same Entity** (version = **1**).
2. **User A** updates and saves it.
3. Hibernate updates the row and increments the version to **2**.
4. **User B** tries to save using version **1**.
5. Hibernate throws an **`OptimisticLockException`** because the entity was already modified.

**Example**

```java id="oof3k4"
Product product = entityManager.find(Product.class, 1L);

product.setName("Laptop");

entityManager.merge(product);
```

Hibernate automatically checks the **version** before updating.



## 14. **What Are the Peculiarities of Bidirectional Relationships?**

A **Bidirectional Relationship** means **both Entities reference each other**, allowing navigation from **parent to child** and **child to parent**.

**Example**

```java id="fj4x8t"
@Entity
public class Department {

    @Id
    private Long id;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();
}
```

```java id="d5y92m"
@Entity
public class Employee {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
```

**Important Peculiarities**

1. **One side must be the Owning Side**.

   * The **Owning Side** contains **`@JoinColumn`**.
   * It is responsible for updating the **Foreign Key**.

2. **The other side is the Inverse Side**.

   * Uses **`mappedBy`**.
   * It does **not** update the Foreign Key.

3. **Keep both sides synchronized**.

   * Update both references in code to avoid inconsistent object state.

```java id="md1f2z"
Department department = new Department();
Employee employee = new Employee();

employee.setDepartment(department);
department.getEmployees().add(employee);
```


## 14. **How to Avoid Infinite Recursion During Entity Serialization?**

**Infinite Recursion** happens when **bidirectional relationships** reference each other during **JSON serialization**.

For example:

* **Department** → **Employee**
* **Employee** → **Department**

Jackson keeps serializing both objects repeatedly, causing a **StackOverflowError**.

**Solution 1: `@JsonManagedReference` and `@JsonBackReference`**

* **`@JsonManagedReference`** → Parent side.
* **`@JsonBackReference`** → Child side (ignored during serialization).

```java
@Entity
public class Department {

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Employee> employees;
}
```

```java
@Entity
public class Employee {

    @ManyToOne
    @JsonBackReference
    private Department department;
}
```

**Solution 2: `@JsonIgnore`**

Ignore one side of the relationship.

```java
@ManyToOne
@JsonIgnore
private Department department;
```

**Solution 3: Use DTOs (Recommended)**

Instead of returning **Entities**, return **DTOs** containing only the required fields.

```java
public record EmployeeDto(Long id, String name) {}
```

## 14. What is Database Indexing and When to Use It?

A **Database Index** is a special **data structure** that improves the speed of **data retrieval** operations. It works like the **index of a book**, allowing the database to find rows quickly without scanning the entire table.

**Key Features:**

* Improves **`SELECT`** query performance.
* Reduces the need for **full table scans**.
* Can be created on one or more columns.
* Uses additional **storage space** and slightly slows down **`INSERT`**, **`UPDATE`**, and **`DELETE`** operations.

**How it Works:**

1. An index stores the values of indexed columns in a sorted structure (commonly a **B-Tree**).
2. When a query searches by an indexed column, the database looks up the index first.
3. The index quickly points to the required row location, avoiding a full table scan.

**Example:**
Without an index:

```sql
SELECT * FROM users WHERE email = 'abc@example.com';
```

The database may scan the **entire `users` table**.

With an index on the **`email`** column:

```sql
CREATE INDEX idx_email ON users(email);
```

The database can directly locate the matching row, making the query much faster.

**When to Use:**

* Columns frequently used in **`WHERE`** conditions.
* Columns used in **`JOIN`**, **`ORDER BY`**, or **`GROUP BY`** clauses.
* **Primary Keys** and **Foreign Keys**.
* Columns with **high search frequency**.

**When to Avoid:**

* Very small tables.
* Columns that are rarely queried.
* Columns with frequent updates if read performance is not critical, because indexes add maintenance overhead.

**Code Example (JPA):**

```java id="v3n8qy"
@Entity
@Table(name = "users",
       indexes = {
           @Index(name = "idx_email", columnList = "email")
       })
public class User {

    @Id
    private Long id;

    private String email;
}
```


## 16. What is LazyInitializationException?

**`LazyInitializationException`** occurs when Hibernate tries to load a lazily fetched entity or collection after the Hibernate session has already been closed.


```java
@Entity
class Employee {

    @OneToMany(fetch = FetchType.LAZY)
    private List<Address> addresses;
}
```

```java
Employee emp = employeeRepository.findById(1L).get();
// Session closes here

emp.getAddresses().size(); // LazyInitializationException
```

Since `addresses` is loaded lazily, Hibernate tries to fetch it only when `getAddresses()` is called. But the session is already closed, so it throws `LazyInitializationException`.

**How to Fix It**

    1. Access the lazy data inside a transaction.
    2. Use `JOIN FETCH` in JPQL.
       ```java
       SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = 1
       ```
    3. Use DTO projections instead of entities for web layer.
    4. Use `FetchType.EAGER` only when necessary.



## 17. Entity lifecycle states?

| State | Description |
|---|---|
| **Transient** | New object, not tracked by JPA |
| **Managed** | Tracked by persistence context |
| **Detached** | Was managed, now outside context |
| **Removed** | Scheduled for deletion |

```java
User user = new User("John");          // Transient

entityManager.persist(user);           // Managed - changes auto-synced

entityManager.detach(user);            // Detached - changes NOT tracked
user.setName("Jane");                  // won't be saved

entityManager.merge(user);             // Managed again

entityManager.remove(user);            // Removed
```


## 18. What is dirty checking?


**Dirty Checking** is a feature of **Hibernate/JPA** that automatically detects changes made to a managed entity and updates the database without explicitly calling `save()` or `update()`.

**Key Features**

* Automatic change detection
* Works inside a **Transaction**
* Reduces boilerplate code
* Managed by the **Persistence Context**
* Automatically generates **UPDATE** queries

**How It Works**

1. Hibernate loads an entity from the database.
2. The entity becomes **Managed** by the **Persistence Context**.
3. If the entity's data is modified, Hibernate tracks the changes.
4. At **Transaction Commit**, Hibernate compares the current state with the original state.
5. If changes are found, Hibernate automatically executes an **UPDATE** statement.

**Code Example**

```java
@Transactional
public void updateUser(Long id) {

    User user = userRepository.findById(id).get();
    // Hibernate creates snapshot: {id: 1, name: "John", salary: 50000}

    user.setName("John Updated");

    // Hibernate marks entity as "dirty"
    
    // No need to call employeeRepository.save()!
    // At transaction commit, Hibernate automatically executes:
    // UPDATE employee SET name = 'John Updated' WHERE id = 1
}
```

No explicit `save()` call is needed.

Hibernate automatically generates:

```sql
UPDATE users
SET name = 'John Updated'
WHERE id = 1;
```


**When to Use**

Use **Dirty Checking** when working with **managed entities** inside a **@Transactional** method.


## 19: What is lazy loading?

**Lazy Loading** is a technique where data or objects are **loaded only when they are actually needed**, instead of loading them immediately. It helps improve **performance** and reduces unnecessary memory usage.

**Key Features**

* Loads data **on demand**.
* Improves **application performance**.
* Reduces **memory consumption**.
* Decreases initial loading time.
* Commonly used in **Hibernate/JPA** relationships.

**How It Works**

1. The main object is loaded first.
2. Related objects are not fetched immediately.
3. When the related data is accessed, Hibernate executes a query and loads it.
4. Data is fetched only when required.

**Why Use Lazy Loading?**

* Improves performance by avoiding unnecessary database calls.
* Reduces memory usage.
* Faster application startup and response time.
* Efficient for large object graphs.

**When to Use**

* Large datasets.
* Relationships that are not always needed.
* Performance-sensitive applications.
* One-to-Many and Many-to-Many associations.

**Code Example**

```java
// Lazy loading examples
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    
    // Lazy loading - orders loaded only when accessed
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;
    // fetch = FetchType.LAZY means the related child data is not loaded immediately. It is fetched only when we access it.
    
    // Eager loading - always loaded with user
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;
}

@Service
public class UserService {
    
    // Lazy loading in action
    public void processUser(Long userId) {
        User user = userRepository.findById(userId);
        // Orders not loaded yet
        
        if (needsOrders(user)) {
            user.getOrders().size(); // Now orders are loaded
        }
    }
    
    // Avoid N+1 with explicit fetch
    public List<User> getUsersWithOrders() {
        return userRepository.findAllWithOrders(); // Single query with JOIN FETCH
    }
}
```

**Lazy vs Eager Loading**

| **Lazy Loading**                   | **Eager Loading**                       |
| ---------------------------------- | --------------------------------------- |
| Loads data when needed             | Loads data immediately                  |
| Better performance for unused data | Can load unnecessary data               |
| Lower memory usage                 | Higher memory usage                     |
| Default for collections in JPA     | Often used when data is always required |



## 20: What is eager loading?

**Eager Loading** is a technique where related data is **loaded immediately** along with the main entity in a **single query** or as soon as the entity is fetched.

**Key Features**

* **Loads related objects instantly**
* Reduces the **N+1 Query Problem**
* Improves performance when related data is definitely needed
* May load **extra data** that is not used

**How It Works**

When the main entity is fetched, its associated entities are fetched at the same time.

Example:

If you load an **Employee**, the related **Department** is also loaded immediately.

**Why Use It?**

* Reduces the number of database queries
* Improves performance for frequently accessed relationships
* Avoids additional database hits later

**When to Use**

* When related data is **always required**
* For reports, dashboards, and detailed views
* To avoid repeated database queries

**Example (JPA/Hibernate)**
```java
// Eager loading examples
@Entity
public class Order {
    @Id
    private Long id;
    
    // Eager loading - customer always loaded with order
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

     // fetch = FetchType.EAGER means the related entity is loaded immediately along with the parent entity
    
    // Lazy loading - items loaded on demand
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> items;

     // fetch = FetchType.LAZY means the related child data is not loaded immediately. It is fetched only when we access it.
}

@Repository
public class OrderRepository extends JpaRepository<Order, Long> {
    
    // Explicit eager loading with fetch join
    @Query("SELECT o FROM Order o JOIN FETCH o.customer JOIN FETCH o.items")
    List<Order> findAllOrdersWithDetails();
    
    // Conditional eager loading
    @EntityGraph(attributePaths = {"customer", "items"})
    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findByStatusWithDetails(@Param("status") OrderStatus status);
}
```


## 11. What is Optimistic vs Pessimistic Locking?

**Optimistic Locking** and **Pessimistic Locking** are concurrency control mechanisms used to prevent **data inconsistency** when multiple users or threads update the same data simultaneously.

**Optimistic locking** assumes **conflicts are rare**, so users can read and modify data without locking it immediately. Before updating, the system checks whether another transaction has already changed the data.


**Pessimistic Locking** — locking assumes **conflicts are common**, so data is locked immediately to prevent other transactions from modifying it until completion."


| **Feature**          | **Optimistic Locking**                                                           | **Pessimistic Locking**                                 |
| -------------------- | -------------------------------------------------------------------------------- | ------------------------------------------------------- |
| **Approach**         | Assumes **conflicts are rare**.                                                  | Assumes **conflicts are likely**.                       |
| **Locking**          | **Does not lock** the record while reading.                                      | **Locks** the record immediately.                       |
| **Performance**      | Better for **high-read, low-write** applications.                                | Better for **high-conflict** scenarios.                 |
| **Concurrency**      | Allows multiple users to read and update simultaneously.                         | Prevents other users from modifying the locked data.    |
| **Failure Handling** | Throws **`OptimisticLockException`** if data was changed by another transaction. | Other transactions **wait** until the lock is released. |

**How it Works:**

**Optimistic Locking:**

1. Read the record with a **version number**.
2. Modify the data.
3. Before updating, check if the **version is unchanged**.
4. If the version changed, the update fails and an exception is thrown.

**Pessimistic Locking:**

1. Read and **lock the record**.
2. No other transaction can update it until the lock is released.
3. Update the data and commit the transaction.

**Key Features:**

* **Optimistic Locking:** Uses a **`@Version`** field, provides **better performance**, and avoids unnecessary locks.
* **Pessimistic Locking:** Uses **database-level locks**, ensures strong consistency, but may reduce concurrency.

**When to Use:**

* Use **Optimistic Locking** when **read operations are frequent** and update conflicts are rare (e.g., **e-commerce product catalog**).
* Use **Pessimistic Locking** when **data conflicts are common** and consistency is critical (e.g., **banking transactions**).

**Code Example:**

**Optimistic Locking (JPA):**

```java
@Entity
public class Product {

    @Id
    private Long id;

    @Version
    private Integer version;

    private String name;
}
```

**Pessimistic Locking (JPA):**

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT p FROM Product p WHERE p.id = :id")
Product findByIdForUpdate(Long id);
```


## 21. What is auditing and How it works in JPA?

In Java (especially enterprise applications like Spring Boot), **auditing** means **tracking and recording changes made to data**, such as:

* **Who** created the record
* **When** the record was created
* **Who** updated the record
* **When** it was last updated


**How it works**

Spring Data JPA provides `@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, `@LastModifiedBy` via `@EnableJpaAuditing`.

```java
// 1. Enable auditing
@SpringBootApplication
@EnableJpaAuditing
public class App {}

// 2. Auditable base entity
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}

// 3. Extend it
@Entity
public class User extends Auditable {
    @Id @GeneratedValue private Long id;
    private String name;
}
```


## 14. **Describe the Entity Lifecycle in Hibernate**

An **Entity** in Hibernate goes through **4 lifecycle states** from creation until deletion.

**1. Transient**

* The object is created using **`new`**.
* It is **not managed** by Hibernate.
* It does **not exist** in the database.

```java id="kzk3px"
Student student = new Student();
student.setName("John");
```

**2. Persistent**

* The entity is **managed** by Hibernate.
* It exists in the **Persistence Context**.
* Changes are **automatically saved** to the database during **commit** or **flush**.

```java id="j6t3rf"
entityManager.persist(student);
```

**3. Detached**

* The entity was **managed before**, but is **no longer attached** to the Persistence Context.
* Changes are **not saved** automatically.
* Use **`merge()`** to attach it again.

```java id="wscwdi"
entityManager.detach(student);
```

```java id="uz8fx2"
student.setName("Mike");
entityManager.merge(student);
```

**4. Removed**

* The entity is marked for **deletion**.
* It is deleted from the database when the transaction is **committed**.

```java id="04up4x"
entityManager.remove(student);
```

**Entity Lifecycle Flow**

**Transient** → **Persistent** → **Detached** → **Persistent (using `merge()`)** → **Removed**



## 14. **What Is the First-Level Cache in Hibernate?**

The **First-Level Cache** is a **session-level cache** that is **enabled by default**. Every **`Session`** (or **`EntityManager`**) has its own cache and stores loaded entities during the session.

**Why Use First-Level Cache?**

1. **Reduces database queries**.
2. **Improves performance**.
3. Ensures the **same Entity instance** is returned within the same session.

**Example**

```java
Session session = sessionFactory.openSession();

Student s1 = session.get(Student.class, 1L);
Student s2 = session.get(Student.class, 1L);

System.out.println(s1 == s2); // true
```

The **first `get()`** executes a SQL query and stores the entity in the **First-Level Cache**.

The **second `get()`** returns the entity from the **cache**, so **no SQL query** is executed.

**When Is the Cache Cleared?**

* When the **Session** is **closed**.
* When **`session.clear()`** is called.
* When a specific entity is removed using **`session.evict(entity)`**.


## 14. **What Is the Second-Level Cache and When to Use It?**

The **Second-Level Cache** is a **SessionFactory-level cache** shared across **multiple Sessions**. Unlike the **First-Level Cache**, it is **disabled by default** and must be configured explicitly.

**Why Use Second-Level Cache?**

1. **Reduces database queries** across multiple sessions.
2. **Improves application performance**.
3. Stores **frequently accessed** and **rarely changing** data.

**Example**

```java id="d1f4kx"
@Entity
@Cacheable
@org.hibernate.annotations.Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Product {

    @Id
    private Long id;

    private String name;
}
```

When one session loads a **Product**, it is stored in the **Second-Level Cache**. Another session requesting the same product can retrieve it from the **cache** instead of querying the database.

**When to Use It?**

* **Reference data** (Countries, Cities).
* **Product Catalogs**.
* **Configuration data**.
* Data that is **read frequently** but **changes rarely**.

**When Not to Use It?**

* **Frequently updated** data.
* **Highly transactional** data.
* Data with **constant writes**, where cache synchronization becomes expensive.


## 14. **How to Configure the Second-Level Cache?**

To use the **Second-Level Cache** in Hibernate, you need to:

1. Add a **cache provider** (e.g., **Ehcache**, **Caffeine**, **Infinispan**).
2. Enable the **Second-Level Cache** in Hibernate.
3. Mark the required **Entities** as cacheable.

**Step 1: Enable Second-Level Cache**

```properties
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.jcache.JCacheRegionFactory
```

**Step 2: Mark the Entity as Cacheable**

```java
@Entity
@Cacheable
@org.hibernate.annotations.Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Product {

    @Id
    private Long id;

    private String name;
}
```

**Step 3: Use a Cache Provider**

Common providers:

- **Ehcache**
- **Caffeine**
- **Infinispan**

**Cache Concurrency Strategies**

1. **`READ_ONLY`** – For data that **never changes**.
2. **`READ_WRITE`** – For data that **can be updated**.
3. **`NONSTRICT_READ_WRITE`** – Allows occasional stale data for **better performance**.
4. **`TRANSACTIONAL`** – For **transaction-aware** cache providers.


## 14. **What Are the Peculiarities of Bidirectional Relationships?**

A **Bidirectional Relationship** means **both Entities reference each other**, so you can navigate from **parent to child** and from **child to parent**.

**Example**

```java
@Entity
public class Department {

    @Id
    private Long id;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();
}
```

```java
@Entity
public class Employee {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
```

**Peculiarities**

1. **One side is the Owning Side**

   * Contains **`@JoinColumn`**.
   * Updates the **Foreign Key** in the database.
   * In this example, **`Employee`** is the owning side.

2. **One side is the Inverse Side**

   * Uses **`mappedBy`**.
   * Does **not** update the Foreign Key.
   * In this example, **`Department`** is the inverse side.

3. **Keep Both Sides Synchronized**

   * Always update both sides of the relationship in your code.

```java
Department department = new Department();
Employee employee = new Employee();

employee.setDepartment(department);
department.getEmployees().add(employee);
```

4. **Can Cause Infinite Recursion**

   * During **JSON serialization**, parent and child may reference each other indefinitely.
   * Use **`@JsonManagedReference`**, **`@JsonBackReference`**, **`@JsonIgnore`**, or **DTOs**.


## 14. **How to Avoid Infinite Recursion During Entity Serialization?**

**Infinite Recursion** occurs when **bidirectional Entities** reference each other during **JSON serialization**, causing Jackson to serialize them repeatedly until a **`StackOverflowError`** occurs.

**Example**

```java
Department → Employee → Department → Employee ...
```

**Solution 1: `@JsonManagedReference` and `@JsonBackReference`**

* **`@JsonManagedReference`** → Parent side.
* **`@JsonBackReference`** → Child side (ignored during serialization).

```java
@Entity
public class Department {

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Employee> employees;
}
```

```java
@Entity
public class Employee {

    @ManyToOne
    @JsonBackReference
    private Department department;
}
```

**Solution 2: `@JsonIgnore`**

Ignore one side of the relationship.

```java
@ManyToOne
@JsonIgnore
private Department department;
```

**Solution 3: `@JsonIdentityInfo`**

Serialize each entity only once and use its **ID** for subsequent references.

```java
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
@Entity
public class Department {
}
```

**Solution 4: Use DTOs (Recommended)**

Return **DTOs** instead of **Entities** from your REST API.

```java
public record EmployeeDto(Long id, String name) {}
```



# ✅ 17. Java Spring Framework 

## 2. What is Spring Framework ?

**Spring Framework** is a **lightweight, open-source Java framework** used to build **enterprise and web applications**. It simplifies Java development by providing features like **Dependency Injection (DI)**, **Inversion of Control (IoC)**, **AOP**, and **transaction management**.


In simple words, **Spring manages the creation and wiring of objects, so developers can focus on business logic instead of infrastructure code.**


**Key Features**

* **IoC (Inversion of Control)** and **Dependency Injection (DI)**.
* Promotes **Loose Coupling** between components.
* Built-in support for **AOP (Aspect-Oriented Programming)**.
* Simplifies **database access** with **Spring Data** and JDBC support.
* Integrates easily with **Hibernate**, **JPA**, and other frameworks.
* Provides modules for **Security**, **Web MVC**, **Microservices**, and **Reactive Programming**.
* Supports **transaction management** and **testing**.

**How It Works**

1. The application starts and loads the **Spring Container**.
2. The container scans classes marked with annotations like `@Component`, `@Service`, `@Repository`, and `@Controller`.
3. Spring creates and manages these objects as **Beans**.
4. Dependencies are automatically injected using **Dependency Injection**.
5. The application uses these managed beans to execute business logic.

**Core Modules of Spring Framework**

| **Module**             | **Purpose**                                              |
| ---------------------- | -------------------------------------------------------- |
| **Spring Core**        | Provides IoC and Dependency Injection                    |
| **Spring AOP**         | Handles cross-cutting concerns like logging and security |
| **Spring MVC**         | Builds web applications and REST APIs                    |
| **Spring Data**        | Simplifies database operations                           |
| **Spring Security**    | Provides authentication and authorization                |
| **Spring Transaction** | Manages database transactions                            |
| **Spring Test**        | Supports unit and integration testing                    |

**When to Use Spring Framework?**

Use Spring Framework when:

* Building **enterprise Java applications**.
* Developing **REST APIs** or **web applications**.
* Creating **microservices** with Spring Boot and Spring Cloud.
* Building applications that require **security**, **database access**, and **transaction management**.
* You want a **loosely coupled** and **testable** architecture.

**Simple Example**

```java
// pom.xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.1.5</version>
    </dependency>
</dependencies>
```

**Service Class**

```java id="u4k8zp"
@Service
public class UserService {

    public String getUser() {
        return "John";
    }
}
```

**Controller Class**

```java id="b2m5lx"
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser() {
        return userService.getUser();
    }
}
```

In this example:

* `@Service` tells Spring to create and manage the `UserService` bean.
* `@Autowired` automatically injects the dependency into `UserController`.
* The developer does not need to manually create the `UserService` object.

**How Spring Framework Works Internally**

1. The **Spring Container** starts when the application launches.
2. It scans the classpath for Spring annotations.
3. It creates **Bean Definitions** and stores them in the **ApplicationContext**.
4. Dependencies are resolved and injected automatically using **IoC** and **DI**.
5. Additional features like **AOP**, **transactions**, and **security** are applied using **proxies** and interceptors.

**Advantages of Spring Framework**

* **Reduces boilerplate code**.
* Encourages **loose coupling** and **modular design**.
* Simplifies **testing** and maintenance.
* Provides excellent integration with third-party frameworks.
* Supports both **monolithic** and **microservices** architectures.


## 2. What are the features of Spring?


The **Spring Framework** is a **lightweight, open-source Java framework** used to build **enterprise-grade applications**. It provides **dependency management, modular architecture, and built-in support for web, data, and security layers**.

**Simple Interview Definition**

Spring is a **Java framework** that simplifies development using **dependency injection, modular design, and ready-to-use enterprise features**.

**Key Features of Spring**

* **Dependency Injection (DI)** – manages object creation and wiring automatically.
* **Inversion of Control (IoC)** – framework controls object lifecycle instead of developer.
* **Aspect-Oriented Programming (AOP)** – handles cross-cutting concerns like logging and security.
* **Modular Architecture** – use only required modules (Spring Core, MVC, Data, Security).
* **Spring MVC** – supports building **web applications and REST APIs**.
* **Spring Boot Support** – simplifies configuration and setup.
* **Transaction Management** – handles database transactions easily.
* **Data Access Integration** – supports JDBC, JPA, Hibernate.
* **Security Integration** – integrates with **Spring Security for authentication/authorization**.
* **Cloud & Microservices Support** – works well with distributed systems.

**How It Works**

1. Application starts with **Spring Container (IoC Container)**.
2. Spring reads **configuration (annotations or XML)**.
3. Objects (beans) are created and managed by Spring.
4. Dependencies are automatically **injected (DI)**.
5. Application runs with managed lifecycle and services.

**Flow**

```text id="spr1"
Spring Container
      ↓
Bean Creation
      ↓
Dependency Injection
      ↓
Application Execution
```

**When to Use Spring**

* Building **enterprise applications**
* Developing **REST APIs (Spring Boot)**
* Creating **microservices architecture**
* Database-driven applications
* Secure applications using **Spring Security**
* Scalable cloud-based systems

**Spring Example (Dependency Injection)**

```java id="spr2"
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
class UserService {
    public String getUser() {
        return "User Data";
    }
}

class UserController {

    @Autowired
    private UserService userService;

    public void showUser() {
        System.out.println(userService.getUser());
    }
}
```

**Explanation**

* **UserService** is a **Spring Bean**.
* Spring automatically injects it into **UserController** using **@Autowired**.
* Developer does not manually create objects.

**Core Spring Modules**

| **Module**          | **Purpose**                            |
| ------------------- | -------------------------------------- |
| **Spring Core**     | DI and IoC container                   |
| **Spring MVC**      | Web applications                       |
| **Spring Data**     | Database access                        |
| **Spring Security** | Authentication & Authorization         |
| **Spring AOP**      | Cross-cutting concerns                 |
| **Spring Boot**     | Auto-configuration & rapid development |

**Advantages**

* Reduces **boilerplate code**
* Improves **testability**
* Supports **loose coupling**
* Easy **integration with frameworks**
* Highly **scalable and modular**



**Common Interview Follow-up Questions**

**1. What is Dependency Injection in Spring?**

It is a design pattern where **Spring automatically injects dependencies** instead of creating them manually.

**2. What is IoC in Spring?**

**Inversion of Control (IoC)** means the **Spring container controls object creation and lifecycle**, not the developer.

**3. Difference between Spring and Spring Boot?**

| **Spring**                    | **Spring Boot**       |
| ----------------------------- | --------------------- |
| Requires manual configuration | Auto-configuration    |
| More setup needed             | Faster development    |
| More flexible                 | Opinionated framework |

**4. What is Spring MVC?**

A module used to build **web applications and REST APIs** using the **Model-View-Controller pattern**.

**5. Why is Spring widely used?**

Because it provides **loose coupling, modular design, easy integration, scalability, and enterprise-level support**.


## 3. What is Inversion of Control (IoC)?


**Inversion of Control (IoC)** is a **design principle** where the **Spring Container** controls the creation and lifecycle of objects instead of the application creating them.

In simple words, **you don't create objects; Spring creates and manages them for you.**

* **IoC** = **Who creates the object?** → **Spring Container**

**Key Features**

* Transfers object creation and management to the **Spring Container**.
* Promotes **Loose Coupling** between classes.
* Improves **code reusability**, **maintainability**, and **testability**.
* Works together with **Dependency Injection (DI)**.
* Makes it easy to replace or mock dependencies during testing.

**How It Works**

1. The application starts and initializes the **Spring Container** (`ApplicationContext`).
2. Spring scans classes annotated with `@Component`, `@Service`, `@Repository`, or `@Controller`.
3. The container creates these classes as **Beans**.
4. Required dependencies are automatically injected into the beans.
5. The application uses these managed objects instead of creating them manually.

**IoC vs Traditional Approach**

| **Traditional Java**                    | **Using IoC (Spring)**           |
| --------------------------------------- | -------------------------------- |
| Application creates objects using `new` | Spring Container creates objects |
| Tight coupling                          | Loose coupling                   |
| Manual dependency management            | Automatic dependency management  |
| Harder to test                          | Easier to test and maintain      |


**Inversion of Control (IoC) VS Dependency Injection (DI)**

Give a simple Polish clean short answer that is easy to explain in an interview with complete clarity(based one question type add Key Features, How it works, when to use and code example etc). if need add Common Interview Follow-up question as well.  Highlight the main keywords in bold. Do not use side notes, arrows, divider line ---, or blockquotes for title instead of ### ** ** format use ** **.**

| **IoC**                        | **DI**                               |
| ------------------------------ | ------------------------------------ |
| **Design Principle**           | **Implementation Technique**         |
| Spring manages object creation | Spring injects required dependencies |
| Controls bean lifecycle        | Reduces tight coupling               |
| Improves maintainability       | Makes testing easier                 |


**When to Use IoC?**

Use IoC when:

* Building **Spring-based applications**.
* You want **loosely coupled** components.
* You need easier **unit testing** and **mocking**.
* Developing **large enterprise** or **microservices** applications.
* Managing multiple object dependencies efficiently.

**Simple Example**

**Without IoC**

```java id="j8w3xp"
public class UserService {
    private UserRepository repository = new UserRepository();
    public void saveUser() {
        repository.save();
    }
}
```

Here, `UserService` creates its own dependency, causing **tight coupling**.

**With IoC (Spring)**

```java id="n5v2km"
@Repository
public class UserRepository {
    public void save() {
        System.out.println("User saved");
    }
}
```

```java id="r4q7yb"
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void saveUser() {
        repository.save();
    }
}
```

Here:

* Spring creates both `UserRepository` and `UserService` objects.
* `@Autowired` tells Spring to inject the required dependency automatically.
* The developer does not use the `new` keyword.

**How IoC Works Internally**

1. The **Spring Container** reads the application configuration.
2. It scans and identifies Spring-managed classes.
3. It creates **Bean Definitions** and instantiates the objects.
4. Dependencies are resolved and injected using **Dependency Injection**.
5. The container manages the complete lifecycle of these beans.

**Relationship Between IoC and DI**

* **IoC** is the **principle** of giving control of object creation to the container.
* **Dependency Injection (DI)** is the **mechanism** used by Spring to achieve IoC.

**In short: IoC is the concept, and DI is the implementation.**



## 4. What is Dependency Injection?

**Dependency Injection (DI)** is a design pattern in which the **Spring Container automatically provides the required dependencies (objects) to a class instead of the class creating them itself**.

In simple words, **a class does not create its dependent objects using `new`; Spring injects them automatically.**

* **DI** = **How are dependencies provided?** → **Automatically by Spring**

**Key Features**

* Promotes **Loose Coupling** between classes.
* Improves **code reusability** and **maintainability**.
* Makes **unit testing** easier by allowing mock objects.
* Reduces the use of the **`new` keyword**.
* Implemented by the **Spring IoC Container**.

**How It Works**

1. The **Spring Container** starts and scans the application.
2. It creates objects (called **Beans**) for classes annotated with `@Component`, `@Service`, `@Repository`, or `@Controller`.
3. Spring identifies the required dependencies.
4. The container automatically injects those dependencies into the target class.
5. The application uses these fully initialized beans.

**Types of Dependency Injection**

| **Type**                  | **Description**                                                      |
| ------------------------- | -------------------------------------------------------------------- |
| **Constructor Injection** | Dependencies are injected through the constructor. **(Recommended)** |
| **Setter Injection**      | Dependencies are injected using setter methods.                      |
| **Field Injection**       | Dependencies are injected directly into fields using `@Autowired`.   |

**When to Use Dependency Injection?**

Use DI when:

* Building **Spring or Spring Boot** applications.
* You want **loosely coupled** and **modular** code.
* You need easy **unit testing** and **mocking**.
* Managing multiple service or repository dependencies.

**Simple Example**

**Repository Class**

```java id="y7m4kp"
@Repository
public class UserRepository {
    public void save() {
        System.out.println("User saved");
    }
}
```

**Service Class Using Constructor Injection**

```java id="w2n8xr"
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser() {
        userRepository.save();
    }
}
```

In this example:

* Spring creates the `UserRepository` bean.
* Spring creates the `UserService` bean.
* The `UserRepository` object is automatically **injected** into `UserService` through the constructor.
* No need to manually create objects using the `new` keyword.

**How Dependency Injection Works Internally**

1. The **Spring IoC Container** scans the application for Spring-managed classes.
2. It creates **Bean Definitions** and instantiates the required beans.
3. When a bean depends on another bean, Spring resolves the dependency.
4. The container injects the dependency using **constructor**, **setter**, or **field injection**.
5. The fully initialized bean is stored in the **ApplicationContext** and used throughout the application.

**Constructor Injection vs Setter Injection**

| **Feature**             | **Constructor Injection** | **Setter Injection**           |
| ----------------------- | ------------------------- | ------------------------------ |
| **Dependency Required** | Yes                       | Optional                       |
| **Immutability**        | Supported                 | Not Supported                  |
| **Testing**             | Easier                    | Moderate                       |
| **Recommended**         | ✅ Yes                     | Only for optional dependencies |

**Relationship Between IoC and DI**

* **IoC (Inversion of Control)** is the **design principle** where object creation is controlled by the Spring Container.
* **DI (Dependency Injection)** is the **technique** used by Spring to implement IoC by injecting required dependencies automatically.

**In short: IoC is the concept, and DI is the implementation.**


**There are 3 main types of DI:**
1. Constructor Injection – dependencies injected through constructor (recommended)
2. Setter Injection – dependencies injected through setter method
3. Field Injection – dependencies injected directly into field using @Autowired

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Engine {
    public void start() {
        System.out.println("Engine started");
    }
}

// Instead of :
Engine engine = new Engine(); // Spring does: tightly coupled
Car car = new Car(engine);    // Spring does: injected
```

**1. Constructor Injection (Recommended)**

```java
@Component
class Car {
    private final Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }
}
👉 Best because: Mandatory dependency, Immutable (using final), Easy to test
Reason To use: 
```
Reason to use: 


**2. Setter Injection**

```java
@Autowired
public void setEngine(Engine engine) {
    this.engine = engine;
}
👉 Used when dependency is optional

```


**3. Field Injection**

```java
@Autowired
private Engine engine;
👉 Avoid because: Hard to test, Not immutable, Hidden dependency
```


## 5. What is a Spring Container?



A **Spring Container** is the **core component of the Spring Framework** that is responsible for **creating, configuring, managing, and destroying Spring beans**. It implements the **Inversion of Control (IoC)** and **Dependency Injection (DI)** principles.


**How It Works**

1. Spring reads the configuration (**XML**, **Java Configuration**, or **Annotations**).
2. It scans and identifies classes marked with annotations like **`@Component`**, **`@Service`**, **`@Repository`**, and **`@Controller`**.
3. The container creates the required **bean objects**.
4. It injects dependencies using **`@Autowired`**, constructor injection, or setter injection.
5. It manages the complete bean lifecycle until the application shuts down.

**Key Features**

* Implements **IoC (Inversion of Control)**.
* Supports **Dependency Injection (DI)**.
* Manages the **bean lifecycle**.
* Handles **bean creation, configuration, and destruction**.
* Supports **AOP (Aspect-Oriented Programming)**, **event handling**, and **transaction management**.
* Reduces **tight coupling** between classes.

**Types of Spring Container**

| **Container**            | **Description**                                                                                                  |
| ------------------------ | ---------------------------------------------------------------------------------------------------------------- |
| **`BeanFactory`**        | Basic IoC container with **lazy initialization**.                                                                |
| **`ApplicationContext`** | Advanced IoC container with features like **AOP, event handling, annotation support, and eager initialization**. |


**When to Use**

| **Scenario**                             | **Use Spring Container?** |
| ---------------------------------------- | ------------------------- |
| Managing object creation automatically   | **Yes**                   |
| Dependency Injection required            | **Yes**                   |
| Enterprise and Spring Boot applications  | **Yes**                   |
| Small standalone Java program without DI | **Optional**              |

**Code Example**

```java id="z4qk7x"
@Service
public class UserService {

    public void display() {
        System.out.println("User Service Called");
    }
}
```

```java id="u8c5nm"
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(DemoApplication.class, args);

        UserService service = context.getBean(UserService.class);
        service.display();
    }
}
```

In this example, the **Spring Container** automatically creates the **`UserService`** bean and returns it through **`getBean()`**.

**Bean Lifecycle Managed by Spring Container**

```text
Load Configuration
        ↓
Create Bean
        ↓
Inject Dependencies
        ↓
Initialize Bean
        ↓
Bean Ready to Use
        ↓
Destroy Bean (on application shutdown)
```

**Common Annotations Used by the Spring Container**

| **Annotation**       | **Purpose**                           |
| -------------------- | ------------------------------------- |
| **`@Component`**     | Marks a class as a Spring bean        |
| **`@Service`**       | Marks a service-layer bean            |
| **`@Repository`**    | Marks a DAO/repository bean           |
| **`@Controller`**    | Marks a web controller bean           |
| **`@Autowired`**     | Injects dependencies automatically    |
| **`@Configuration`** | Defines Spring configuration class    |
| **`@Bean`**          | Creates and registers a bean manually |

**Easy Way to Remember**

* **Spring Container = Bean Manager**
* **Creates Beans + Injects Dependencies + Manages Lifecycle**
* **`BeanFactory` = Basic Container**
* **`ApplicationContext` = Advanced Container**
* **Spring Boot Internally Uses `ApplicationContext`**



## 6. What is BeanFactory vs ApplicationContext?



Both **`BeanFactory`** and **`ApplicationContext`** are **Spring IoC (Inversion of Control) containers** used to create, configure, and manage Spring beans.

* **`BeanFactory`** is the **basic IoC container**.
* **`ApplicationContext`** is an **advanced IoC container** built on top of `BeanFactory` with many additional enterprise features.


**Key Differences**

| **Feature**                     | **BeanFactory**                                | **ApplicationContext**                                            |
| ------------------------------- | ---------------------------------------------- | ----------------------------------------------------------------- |
| **Type**                        | Basic IoC Container                            | Advanced IoC Container                                            |
| **Bean Initialization**         | **Lazy Loading** (creates bean when requested) | **Eager Loading** (creates singleton beans at startup by default) |
| **Performance at Startup**      | Faster                                         | Slightly slower                                                   |
| **Event Handling**              | Not Supported                                  | Supported                                                         |
| **Internationalization (i18n)** | Not Supported                                  | Supported                                                         |
| **Annotation Support**          | Limited                                        | Full Support                                                      |
| **AOP Integration**             | Basic                                          | Full Support                                                      |
| **Common Usage**                | Lightweight or legacy applications             | Modern Spring and Spring Boot applications                        |

**How It Works**

* **`BeanFactory`**

  * Reads bean configuration.
  * Creates a bean only when **`getBean()`** is called.

* **`ApplicationContext`**

  * Loads the configuration.
  * Creates all **singleton beans** during container startup (unless marked `@Lazy`).
  * Provides additional services such as event publishing and resource management.


**When to Use**

| **Scenario**                               | **Best Choice**        |
| ------------------------------------------ | ---------------------- |
| Learning basic Spring IoC                  | **BeanFactory**        |
| Spring Boot application                    | **ApplicationContext** |
| Enterprise application with AOP and events | **ApplicationContext** |
| Need lazy bean creation only               | **BeanFactory**        |

**Code Example**

**Using `BeanFactory`**

```java id="c0u1tk"
Resource resource = new ClassPathResource("beans.xml");
BeanFactory factory = new XmlBeanFactory(resource);

Student student = (Student) factory.getBean("student");
```

**Using `ApplicationContext`**

```java id="vkryie"
ApplicationContext context =
    new ClassPathXmlApplicationContext("beans.xml");

Student student = context.getBean("student", Student.class);
```

**Key Features**

* **`BeanFactory`**

  * Basic dependency injection container.
  * Supports lazy initialization.
  * Lower memory usage at startup.

* **`ApplicationContext`**

  * Extends `BeanFactory`.
  * Supports **AOP**, **events**, **i18n**, and **annotation-based configuration**.
  * Default container used by **Spring Boot**.

**How Spring Boot Uses It**

In **Spring Boot**, the framework automatically creates an **`ApplicationContext`** when the application starts.

```java id="k7t0dx"
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

Here, `SpringApplication.run()` internally creates and initializes the **`ApplicationContext`**.

**Easy Way to Remember**

* **`BeanFactory` = Basic + Lazy Loading**
* **`ApplicationContext` = BeanFactory + Extra Features**
* **Learning/Lightweight → `BeanFactory`**
* **Real Projects/Spring Boot → `ApplicationContext`**


## 9. What is AOP in Spring?

**AOP (Aspect-Oriented Programming)** is a programming technique in **Spring** that helps separate **cross-cutting concerns** (common functionalities) such as **logging, security, transaction management, caching, and exception handling** from the main business logic.

In simple words, **AOP allows you to add extra behavior to a method without modifying its actual code.**


**Key Features**

* **Separates business logic** from common functionalities.
* Reduces **duplicate code**.
* Improves **code reusability** and **maintainability**.
* Makes applications easier to **manage and extend**.
* Works using **dynamic proxies** in Spring.

**How AOP Works**

AOP is based on the following concepts:

* **Aspect**: A class that contains cross-cutting logic (e.g., logging).
* **Advice**: The action to perform (Before, After, Around, etc.).
* **Join Point**: A point during execution, usually a method call.
* **Pointcut**: Expression that decides where the advice should be applied.
* **Weaving**: The process of linking aspects with target objects at runtime.

**Types of Advice**

| **Advice Type**     | **When It Executes**                  |
| ------------------- | ------------------------------------- |
| **@Before**         | Before the target method runs         |
| **@After**          | After the target method completes     |
| **@AfterReturning** | After the method returns successfully |
| **@AfterThrowing**  | If the method throws an exception     |
| **@Around**         | Before and after the method execution |

**How Spring AOP Works Internally (Proxy Pattern)**

```
Your Code calls → Spring Proxy (AOP magic) → Real Bean

┌─────────────┐       ┌──────────────────────┐       ┌─────────────┐
│   Caller    │ ────▶ │   Spring Proxy       │ ────▶ │  Real Bean  │
│             │       │  1. Run @Before      │       │             │
│ orderSvc    │       │  2. Call real method │       │ placeOrder()│
│ .placeOrder │       │  3. Run @After       │       │             │
└─────────────┘       └──────────────────────┘       └─────────────┘
```

Spring creates a **proxy** around your bean and intercepts method calls to apply aspects.


**Setup in Spring Boot**

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

```java
// Enable AOP (auto-enabled in Spring Boot)
@SpringBootApplication
@EnableAspectJAutoProxy  // optional — Spring Boot enables this automatically
public class MyApp { }
```

**When to Use AOP?**

Use AOP when you need to implement functionalities that are common across multiple modules, such as:

* **Logging**
* **Authentication & Authorization**
* **Transaction Management**
* **Performance Monitoring**
* **Caching**
* **Exception Handling**
* **Audit Tracking**

**Simple Example**

```java
// WITHOUT AOP — same boilerplate repeated everywhere
public class OrderService {
    public void placeOrder(Order order) {
        log.info("Method started");          // 😫 repeated
        checkSecurity();                     // 😫 repeated
        startTransaction();                  // 😫 repeated

        // actual business logic (3 lines)
        validateOrder(order);
        saveOrder(order);
        notifyUser(order);

        commitTransaction();                 // 😫 repeated
        log.info("Method ended");            // 😫 repeated
    }
}
```

```java
// WITH AOP — same boilerplate repeated everywhere
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void logBeforeMethod() {
        System.out.println("Method execution started...");
    }
}
```

```java
@Service
public class UserService {

    public void createUser() {
        System.out.println("User created.");
    }
}
```

**Output:**

```
Method execution started...
User created.
```

Here, the **logging code is executed automatically before the `createUser()` method without changing the business logic.**

**How Spring AOP Works Internally**

1. Spring creates a **Proxy Object** for the target bean.
2. When a method is called, the call first goes to the **proxy**.
3. The proxy checks whether any **Aspect** matches the method.
4. If matched, the configured **Advice** executes.
5. Finally, the actual target method is invoked.


**Real-World Use Cases**

**✅ 1. Logging**
```java
@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(Loggable)")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        log.info(">> {} started", pjp.getSignature().getName());
        Object result = pjp.proceed();
        log.info("<< {} completed", pjp.getSignature().getName());
        return result;
    }
}
```

**✅ 2. Security Check**
```java
@Aspect
@Component
public class SecurityAspect {

    @Before("@annotation(secured)")
    public void checkRole(JoinPoint jp, Secured secured) {
        String requiredRole = secured.role();
        if (!currentUser.hasRole(requiredRole)) {
            throw new AccessDeniedException("Access denied!");
        }
    }
}
```

**✅ 3. Transaction Management (Spring does this internally)**
```java
// @Transactional is itself implemented as an AOP Aspect internally
@Transactional
public void transferMoney(Account from, Account to, double amount) {
    from.debit(amount);
    to.credit(amount);
}
```

**✅ 4. Performance Monitoring**
```java
@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* com.app.service.*.*(..))")
    public Object monitor(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object result = pjp.proceed();
        long elapsed = System.nanoTime() - start;

        if (elapsed > 1_000_000_000L) { // > 1 second
            log.warn("SLOW METHOD: {} took {}ms",
                pjp.getSignature(), elapsed / 1_000_000);
        }
        return result;
    }
}
```

## 10. What is Spring Data JPA?


**Spring Data JPA** is a **Spring module** that simplifies database access by reducing the amount of **boilerplate code** required for working with **JPA (Java Persistence API)**. It provides ready-made implementations for common **CRUD (Create, Read, Update, Delete)** operations and automatically generates queries based on method names.

In simple words, **you only define an interface, and Spring Data JPA automatically provides the implementation at runtime.**

**Key Features**

* Provides built-in **CRUD operations**.
* Automatically generates queries from **method names**.
* Supports **custom JPQL and native SQL queries** using `@Query`.
* Integrates seamlessly with **Hibernate** and other JPA providers.
* Supports **pagination**, **sorting**, and **auditing**.
* Reduces **boilerplate code** and improves maintainability.

**How It Works**

1. Create an **Entity** class mapped to a database table.
2. Create a **Repository Interface** that extends `JpaRepository`.
3. Spring automatically creates the implementation at runtime.
4. Use the repository methods to perform database operations without writing SQL.

**When to Use Spring Data JPA?**

Use Spring Data JPA when:

* Building **Spring Boot** applications with relational databases.
* You need standard **CRUD operations**.
* You want to reduce database-related boilerplate code.
* You need features like **pagination**, **sorting**, and **custom queries**.

**Simple Example**

**Entity Class**

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters and Setters
}
```

**Repository Interface**

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
```

**Service Class**

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser() {
        User user = new User();
        user.setName("John");
        userRepository.save(user);
    }
}
```

In the above example:

* `save()` is a built-in **CRUD** method provided by `JpaRepository`.
* `findByName()` is a **derived query method**. Spring automatically generates the SQL based on the method name.

**How Spring Data JPA Works Internally**

1. The application starts, and Spring scans for interfaces extending **`JpaRepository`**.
2. Spring creates a **proxy implementation** of the repository.
3. When a repository method is called, Spring converts it into the appropriate **JPQL/SQL query**.
4. The **JPA provider (usually Hibernate)** executes the query using the **EntityManager**.
5. The result is mapped back to Java objects (**Entities**).

**Common Repository Interfaces**

| **Interface**                  | **Purpose**                                              |
| ------------------------------ | -------------------------------------------------------- |
| **CrudRepository**             | Basic CRUD operations                                    |
| **PagingAndSortingRepository** | CRUD + Pagination + Sorting                              |
| **JpaRepository**              | Full JPA features + CRUD + Pagination + Batch operations |


## 11. What is Spring Security?

**Spring Security** is a powerful **Spring framework module** used to provide **authentication** and **authorization** for Java applications. It protects applications from unauthorized access and common security attacks such as **CSRF**, **session fixation**, and **clickjacking**.

In simple words, **Spring Security controls who can log in and what resources they are allowed to access.**



**Key Features**

* Provides **Authentication** (verifies user identity).
* Provides **Authorization** (controls user permissions).
* Supports **Role-Based Access Control (RBAC)**.
* Built-in support for **JWT**, **OAuth2**, and **LDAP**.
* Protects against **CSRF**, **Session Fixation**, and other common attacks.
* Supports **password encryption** using **BCrypt**.
* Easily integrates with **Spring Boot** and **Spring Data JPA**.

**How It Works**

1. The user sends a request to the application.
2. The request passes through the **Spring Security Filter Chain**.
3. Spring Security authenticates the user by validating credentials.
4. If authentication is successful, it checks the user's roles and permissions.
5. Based on authorization rules, access is either **granted** or **denied**.

**Authentication vs Authorization**

| **Concept**        | **Meaning**                                          |
| ------------------ | ---------------------------------------------------- |
| **Authentication** | Verifies **who the user is** (Login).                |
| **Authorization**  | Verifies **what the user can access** (Permissions). |

**When to Use Spring Security?**

Use Spring Security when:

* Building **REST APIs** or **web applications**.
* Implementing **login and registration** functionality.
* Restricting access based on **roles and permissions**.
* Securing applications using **JWT**, **OAuth2**, or **Basic Authentication**.
* Protecting sensitive business data and APIs.

**Simple Example**

**Security Configuration**

```java id="k9q8zw"
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin();

        return http.build();
    }
}
```

**Controller**

```java id="3bjwpi"
@RestController
public class UserController {

    @GetMapping("/user/profile")
    public String profile() {
        return "User Profile";
    }
}
```

In this example:

* Users with **ROLE_ADMIN** can access `/admin/**`.
* Users with **ROLE_USER** or **ROLE_ADMIN** can access `/user/**`.
* All other requests require the user to be **authenticated**.

**How Spring Security Works Internally**

1. Spring creates a **Security Filter Chain** that intercepts every HTTP request.
2. The filter extracts the user's credentials (username/password or JWT token).
3. The **AuthenticationManager** validates the credentials using a **UserDetailsService**.
4. If valid, an **Authentication Object** is stored in the **SecurityContext**.
5. Spring checks the user's roles and permissions before allowing access to the requested resource.

**Common Components**

| **Component**             | **Purpose**                           |
| ------------------------- | ------------------------------------- |
| **SecurityFilterChain**   | Intercepts and secures HTTP requests  |
| **AuthenticationManager** | Validates user credentials            |
| **UserDetailsService**    | Loads user details from database      |
| **PasswordEncoder**       | Encrypts and verifies passwords       |
| **SecurityContext**       | Stores authenticated user information |


## 12. What is Spring Cloud? 


**Spring Cloud** is a collection of **Spring-based tools and libraries** that helps developers build and manage **Microservices Architecture**. It provides ready-made solutions for common distributed system challenges such as **service discovery, configuration management, API gateway, load balancing, and fault tolerance**.

In simple words, **Spring Cloud makes it easier for multiple microservices to communicate, scale, and work together efficiently.**


**Key Features**

* **Centralized Configuration Management** using **Config Server**.
* **Service Discovery and Registration** using **Eureka** or other discovery services.
* **API Gateway** for routing client requests.
* **Client-Side Load Balancing**.
* **Fault Tolerance** and **Circuit Breaker** support (using **Resilience4j**).
* Supports **Distributed Tracing** and monitoring.
* Easy integration with **Spring Boot** applications.

**How It Works**

1. Each microservice registers itself with a **Service Registry** (like Eureka).
2. Services discover and communicate with each other using the registry.
3. Configuration is managed centrally through a **Config Server**.
4. Client requests pass through an **API Gateway**.
5. Spring Cloud handles **load balancing**, **fault tolerance**, and **service communication** automatically.

**When to Use Spring Cloud?**

Use Spring Cloud when:

* Building a **Microservices Architecture**.
* Managing multiple distributed services.
* You need **centralized configuration**.
* You require **service discovery**, **API gateway**, and **load balancing**.
* You want built-in support for **fault tolerance** and **resilience**.

**Common Spring Cloud Components**

| **Component**            | **Purpose**                                        |
| ------------------------ | -------------------------------------------------- |
| **Config Server**        | Centralized configuration management               |
| **Eureka Server**        | Service registration and discovery                 |
| **Spring Cloud Gateway** | API Gateway for routing requests                   |
| **OpenFeign**            | Simplifies REST API communication between services |
| **Resilience4j**         | Circuit breaker and fault tolerance                |
| **LoadBalancer**         | Distributes requests across service instances      |

**Simple Example**

**Enable Feign Client**

```java id="1m6s4r"
@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
```

**Feign Client**

```java id="a8h2xq"
@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{id}")
    String getProduct(@PathVariable Long id);
}
```

**Service Class**

```java id="fj4l8n"
@Service
public class OrderService {

    @Autowired
    private ProductClient productClient;

    public String getOrderDetails(Long id) {
        return productClient.getProduct(id);
    }
}
```

In this example:

* `@FeignClient` automatically creates a REST client.
* `product-service` is discovered through the **Service Registry**.
* The developer does not need to write HTTP connection code manually.

**How Spring Cloud Works Internally**

1. Microservices start and register themselves with **Eureka Server**.
2. **Config Server** provides centralized configuration to all services.
3. When one service calls another, it looks up the target service in Eureka.
4. **Spring Cloud LoadBalancer** selects an available instance.
5. If a service fails, **Resilience4j** can provide fallback logic and prevent cascading failures.


## 13. What is Spring WebFlux?

**Spring WebFlux** is a **reactive, non-blocking web framework** introduced in **Spring 5** for building **high-performance and scalable web applications and REST APIs**. It is based on the **Reactive Streams API** and uses **Mono** and **Flux** to handle asynchronous data processing.

In simple words, **Spring WebFlux can handle many requests concurrently without blocking threads, making it ideal for high-traffic applications.**


**Key Features**

* **Non-blocking** and **asynchronous** request processing.
* Built on the **Reactive Streams** specification.
* Uses **Mono** (0 or 1 result) and **Flux** (0 to many results).
* Supports **high concurrency** with fewer threads.
* Works with **Netty**, **Tomcat**, **Jetty**, and **Undertow** servers.
* Integrates with **Reactive databases** and **WebClient**.

**How It Works**

1. A client sends a request to the application.
2. The request is handled by an **event loop** instead of assigning a dedicated thread.
3. Processing continues asynchronously without blocking the thread.
4. The result is returned as a **Mono** or **Flux** object.
5. Once the data is ready, the response is sent back to the client.

**Mono vs Flux**

| **Reactive Type** | **Description**                   |
| ----------------- | --------------------------------- |
| **Mono<T>**       | Represents **0 or 1** result.     |
| **Flux<T>**       | Represents **0 to many** results. |

**When to Use Spring WebFlux?**

Use Spring WebFlux when:

* Building **high-concurrency REST APIs**.
* Developing **real-time applications** like chat or live notifications.
* Working with **streaming data**.
* Integrating with **reactive databases** or external asynchronous services.
* Building **microservices** that require better scalability.

Avoid WebFlux for simple CRUD applications where **Spring MVC** is sufficient and the application is mostly blocking.

**Simple Example**

**Controller Using `Mono`**

```java id="m8w4pl"
@RestController
public class UserController {

    @GetMapping("/user")
    public Mono<String> getUser() {
        return Mono.just("John");
    }
}
```

**Controller Using `Flux`**

```java id="h5x9qr"
@RestController
public class ProductController {

    @GetMapping("/products")
    public Flux<String> getProducts() {
        return Flux.just("Laptop", "Mobile", "Tablet");
    }
}
```

In this example:

* `Mono.just("John")` returns a **single asynchronous value**.
* `Flux.just(...)` returns **multiple asynchronous values**.

**How Spring WebFlux Works Internally**

1. A request reaches the **WebFlux Dispatcher Handler**.
2. Instead of allocating one thread per request, WebFlux uses an **event-loop architecture** (commonly powered by **Netty**).
3. Business logic returns a **Mono** or **Flux** rather than waiting for the result.
4. The thread is released while I/O operations are in progress.
5. When the operation completes, WebFlux resumes processing and sends the response.

**Spring MVC vs Spring WebFlux**

| **Feature**           | **Spring MVC**                | **Spring WebFlux**                         |
| --------------------- | ----------------------------- | ------------------------------------------ |
| **Programming Model** | Blocking                      | Non-blocking                               |
| **Thread Model**      | One thread per request        | Event-driven with fewer threads            |
| **Return Types**      | Objects                       | **Mono** / **Flux**                        |
| **Best For**          | Traditional CRUD applications | High-concurrency and reactive applications |
| **Scalability**       | Moderate                      | High                                       |


## 14. How Does Spring Handle Circular Dependency?

A **Circular Dependency** occurs when **two or more Spring beans depend on each other directly or indirectly**, creating a cycle. For example, **Bean A needs Bean B**, and **Bean B also needs Bean A** to be created.

In simple words, **Spring gets stuck because it cannot decide which bean to create first.**

**Why is Circular Dependency a Problem?**

* Prevents proper **bean initialization**.
* Makes the code **tightly coupled** and harder to maintain.
* Can cause **`BeanCurrentlyInCreationException`** during application startup.
* Indicates a poor class design that should usually be refactored.

**Key Features**

* Happens due to **mutual dependency** between beans.
* Most common with **constructor injection**.
* Can occur **directly** (`A → B → A`) or **indirectly** (`A → B → C → A`).
* **Setter injection** or **`@Lazy`** can sometimes resolve it.
* Best practice is to **redesign the application** to remove the dependency cycle.

**How It Works**

Suppose:

* **ServiceA** depends on **ServiceB**.
* **ServiceB** depends on **ServiceA**.

When Spring tries to create `ServiceA`, it first needs `ServiceB`. But to create `ServiceB`, it again needs `ServiceA`. This creates an infinite loop, resulting in a circular dependency.

**When Does It Occur?**

* Using **constructor injection** between mutually dependent beans.
* Poorly designed **service or component relationships**.
* Tight coupling between business modules.

**Simple Example**

**Problematic Code (Constructor Injection)**

```java id="t6x4pn"
@Service
public class ServiceA {

    private final ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
```

```java id="v2m8qk"
@Service
public class ServiceB {

    private final ServiceA serviceA;

    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
```

This causes a **Circular Dependency** because each service requires the other during object creation.

**How to Resolve Circular Dependency?**

#**1. Redesign the Application (Best Approach)**

Extract the common logic into a third service so that both classes depend on it instead of each other.

#**2. Use `@Lazy`**

```java id="k7f3ra"
@Service
public class ServiceB {

    private final ServiceA serviceA;

    public ServiceB(@Lazy ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
```

`@Lazy` delays the creation of `ServiceA` until it is actually needed.

#**3. Use Setter Injection**

```java id="d4n9jw"
@Service
public class ServiceA {

    private ServiceB serviceB;

    @Autowired
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
```

With setter injection, Spring can create the beans first and inject the dependencies afterward.

**How Spring Handles Circular Dependency Internally**

* For **singleton beans with setter injection**, Spring can temporarily expose an **early bean reference** and complete dependency injection later.
* For **constructor injection**, Spring cannot create a partially initialized object, so it throws a **`BeanCurrentlyInCreationException`**.
* In recent versions of **Spring Boot**, circular dependencies are **disabled by default**, encouraging better application design.

**When to Use `@Lazy` or Setter Injection?**

Use them only when:

* Refactoring is not immediately possible.
* Legacy applications already have circular dependencies.
* A temporary solution is needed while redesigning the architecture.


## 15. Spring Boot 2.7 vs Spring Boot 3.0

| Feature              | Spring Boot 2.7     | Spring Boot 3.0            |
| -------------------- | ------------------- | -------------------------- |
| Java Version         | Java 8, 11, 17      | Java 17+ only              |
| Spring Framework     | Spring 5.3          | Spring 6.0                 |
| Namespace            | `javax.*`           | `jakarta.*`                |
| Native Image Support | Limited             | Built-in GraalVM support   |
| Observability        | Micrometer only     | Micrometer + OpenTelemetry |
| Security             | Spring Security 5   | Spring Security 6          |
| Hibernate            | Hibernate 5         | Hibernate 6                |
| Servlet API          | Servlet 4.0         | Servlet 6.0                |
| Validation API       | `javax.validation`  | `jakarta.validation`       |
| JPA API              | `javax.persistence` | `jakarta.persistence`      |



## 16. Real Industry Practice?

Here are strong **real industry practice tables** you can use in final-round interviews for deep project discussions.


**Microservices Architecture**

| Component         | Local Development    | Cloud / Production                 |
| ----------------- | -------------------- | ---------------------------------- |
| Service Discovery | Eureka               | Kubernetes Service Discovery       |
| API Gateway       | Spring Cloud Gateway | Kong / Nginx Ingress / API Gateway |
| Config Management | Spring Config Server | ConfigMap + Secrets                |
| Load Balancer     | Local Nginx          | Kubernetes Ingress / AWS ALB       |
| Communication     | REST                 | REST + gRPC + Event Driven         |
| Authentication    | Local JWT            | OAuth2 + Keycloak/Auth0            |
| Circuit Breaker   | Resilience4j         | Resilience4j + Service Mesh        |
| Deployment        | Docker Compose       | Kubernetes                         |
| Containerization  | Docker               | Docker + Kubernetes                |
| Orchestration     | Manual               | Kubernetes                         |
| Monitoring        | Prometheus + Grafana | Prometheus + Grafana + ELK         |
| Logging           | Console Logs         | ELK / Loki / Splunk                |
| Tracing           | Basic Logs           | Zipkin / Jaeger                    |
| CI/CD             | Jenkins Local        | Jenkins/GitHub Actions + ArgoCD    |


**Database Handling**

| Component         | Local Development     | Cloud / Production           |
| ----------------- | --------------------- | ---------------------------- |
| Database          | MySQL/Postgres Docker | RDS / Cloud SQL              |
| Connection Pool   | HikariCP              | Optimized HikariCP           |
| Migration         | Flyway Local          | Flyway CI/CD Pipeline        |
| Read Scaling      | Single DB             | Read Replicas                |
| Backup            | Manual Dump           | Automated Snapshots          |
| High Availability | Not Required          | Multi-AZ Deployment          |
| Sharding          | Rare                  | Used for large-scale systems |
| Transactions      | Local Transactions    | Distributed/Saga Pattern     |


**Cache**

| Component       | Local Development | Cloud / Production |
| --------------- | ----------------- | ------------------ |
| Cache           | Local Redis       | Redis Cluster      |
| Session Storage | In-Memory         | Distributed Redis  |
| Token Cache     | Local Memory      | Redis              |
| Eviction Policy | Default           | LRU/LFU Tuned      |
| Scaling         | Single Node       | Clustered Redis    |
| HA              | Not Needed        | Sentinel/Cluster   |


**Messaging / Event Driven**

| Component           | Local Development     | Cloud / Production        |
| ------------------- | --------------------- | ------------------------- |
| Queue               | RabbitMQ/Kafka Docker | Kafka Cluster             |
| Retry               | Basic Retry           | DLQ + Retry Topics        |
| Ordering            | Single Partition      | Multi-partition Strategy  |
| Event Storage       | Temporary             | Persistent                |
| Consumer Scaling    | Single Consumer       | Consumer Groups           |
| Async Communication | Basic                 | Event-Driven Architecture |
| Cloud Native Queue  | Rare                  | AWS SQS/SNS               |


**Security**

| Component       | Local Development | Cloud / Production          |
| --------------- | ----------------- | --------------------------- |
| Authentication  | Basic JWT         | OAuth2/OpenID Connect       |
| Authorization   | Role Based        | RBAC + IAM                  |
| Secrets         | application.yml   | Vault / AWS Secrets Manager |
| HTTPS           | Optional          | Mandatory                   |
| API Security    | Simple Filters    | WAF + Rate Limiting         |
| User Management | Local DB          | Keycloak/Auth0/Cognito      |


**Deployment**

| Component             | Local Development | Cloud / Production |
| --------------------- | ----------------- | ------------------ |
| Deployment            | Manual Run        | CI/CD Pipeline     |
| Build Tool            | Maven/Gradle      | Maven + Jenkins    |
| Container             | Docker            | Docker             |
| Registry              | Local Images      | Docker Hub/ECR     |
| Infra                 | Local Machine     | Kubernetes/AWS     |
| Auto Scaling          | No                | HPA                |
| Rollback              | Manual            | Automated Rollback |
| Blue Green Deployment | Rare              | Common             |
| Canary Deployment     | Rare              | Common             |


**Monitoring & Observability**

| Component    | Local Development | Cloud / Production          |
| ------------ | ----------------- | --------------------------- |
| Metrics      | Actuator          | Prometheus                  |
| Dashboard    | Grafana           | Grafana                     |
| Logging      | Console           | ELK/Loki                    |
| Alerting     | Minimal           | PagerDuty/Slack Alerts      |
| Tracing      | Basic Logs        | Jaeger/Zipkin               |
| Health Check | /actuator/health  | Liveness + Readiness Probes |


**High Availability & Scalability**

| Component         | Local Development | Cloud / Production    |
| ----------------- | ----------------- | --------------------- |
| Scaling           | Single Instance   | Auto Scaling          |
| Failover          | Manual Restart    | Automatic Recovery    |
| Load Balancing    | Simple Nginx      | Ingress + ALB         |
| Redundancy        | Minimal           | Multi-node            |
| Disaster Recovery | Rare              | Multi-region Strategy |
| Zero Downtime     | Not Needed        | Rolling Updates       |



In local development we use lightweight containerized setups mainly for development convenience and debugging.

In production, we move toward cloud-native and distributed architecture. Kubernetes handles orchestration and service discovery, managed Redis and Kafka are used for scalability and fault tolerance, ConfigMaps and Secrets handle configuration securely, and monitoring/logging are centralized using Prometheus, Grafana, and ELK stack.


**Strong Senior-Level Keywords**

* Cloud-native architecture
* Distributed systems
* Fault tolerance
* High availability
* Horizontal scaling
* Stateless services
* Event-driven architecture
* Container orchestration
* Observability
* Resilience
* Auto-scaling
* Centralized logging
* Service mesh
* Distributed tracing



# ✅ 18. Java Spring Boot 

## 1. What is annotations in Java?

**Annotations** are **special metadata (information about the code)** that provide instructions to the **compiler**, **JVM**, or **frameworks** like Spring. They do not directly change the program logic but help automate configuration and processing.

**Key Features:**

* Add **metadata** to classes, methods, fields, or parameters.
* Reduce the need for **XML configuration**.
* Used by the **compiler**, **JVM**, and **frameworks** at runtime.
* Improve **readability** and **maintainability** of code.
* Support **compile-time** and **runtime** processing.

**How It Works:**

1. An annotation is added to a class, method, or field.
2. The **compiler** or a **framework** reads the annotation.
3. Based on the annotation, the required action is performed automatically.
4. For example, Spring scans `@Component` and creates a **Bean** in the Spring Container.


**When to Use?**

* To configure **Spring Beans** (`@Component`, `@Service`).
* For **Dependency Injection** (`@Autowired`).
* For **REST APIs** (`@RestController`, `@RequestMapping`).
* For **JPA/Hibernate** entity mapping (`@Entity`, `@Table`).
* For compiler instructions like `@Override` and `@Deprecated`.

**Common Built-in Annotations:**

| **Annotation**             | **Purpose**                                              |
| -------------------------- | -------------------------------------------------------- |
| **`@Override`**            | Indicates that a method overrides a parent class method. |
| **`@Deprecated`**          | Marks a method or class as outdated.                     |
| **`@SuppressWarnings`**    | Suppresses compiler warnings.                            |
| **`@FunctionalInterface`** | Declares an interface with exactly one abstract method.  |

**Common Spring Annotations:**

| **Annotation**                        | **Purpose**                              |
| ------------------------------------- | ---------------------------------------- |
| **`@Component`**                      | Marks a class as a Spring Bean.          |
| **`@Service`**                        | Indicates a service layer component.     |
| **`@Repository`**                     | Indicates a data access layer component. |
| **`@Controller` / `@RestController`** | Defines a web controller.                |
| **`@Autowired`**                      | Automatically injects dependencies.      |

**Simple Code Example:**

```java
class Animal {
    void sound() {
        System.out.println("Animal Sound");
    }
}

class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Dog Barks");
    }
}
```

Here, **`@Override`** tells the compiler that `sound()` is overriding a method from the parent class. If the method signature is incorrect, the compiler will generate an error.

**Spring Example:**

```java
@Component
class Engine {
}

@Service
class CarService {

    @Autowired
    private Engine engine;
}
```

Here, Spring reads the annotations and automatically creates the objects and injects the dependency.


## 2. Explain Spring Boot Lifecycle?

**Spring Boot** is an **extension of the Spring Framework** that simplifies the development of Java applications by providing **auto-configuration**, **embedded servers**, and **starter dependencies**. It helps developers build and run production-ready applications with **minimal configuration**.

**Key Features:**

* **Auto Configuration** – Automatically configures the application based on the dependencies available in the classpath.
* **Starter Dependencies** – Predefined dependency bundles like `spring-boot-starter-web` and `spring-boot-starter-data-jpa`.
* **Embedded Server** – Comes with built-in **Tomcat**, **Jetty**, or **Undertow**, so no external server setup is needed.
* **Production-Ready Features** – Provides **Actuator**, health checks, metrics, and monitoring.
* **Minimal Configuration** – Reduces XML and manual configuration using annotations.


**When to Use?**

* Building **REST APIs** and **Microservices**.
* Developing **Enterprise Applications**.
* Creating applications that require **quick setup and deployment**.
* When you want to avoid complex Spring XML configuration.

**How Spring Boot Works Internally (Lifecycle)?**

**1. Application Starts**

* The `main()` method calls **`SpringApplication.run()`**.
* This is the entry point of every Spring Boot application.

```java id="9qvfj8"
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

**2. Create and Initialize SpringApplication**

* `SpringApplication.run()` creates a **SpringApplication** object.
* It identifies whether the application is a **Web**, **Reactive**, or **Non-Web** application.

**3. Load Configuration**

* Spring Boot loads configuration from:

  * `application.properties`
  * `application.yml`
  * Environment variables
  * Command-line arguments

**4. Create the Spring IoC Container**

* An **ApplicationContext** (Spring Container) is created.
* This container will manage all **Spring Beans**.

**5. Component Scanning**

* `@SpringBootApplication` internally combines:

  * **`@Configuration`**
  * **`@EnableAutoConfiguration`**
  * **`@ComponentScan`**
* Spring scans the package and sub-packages for classes annotated with `@Component`, `@Service`, `@Repository`, `@Controller`, etc.

**6. Auto Configuration**

* **`@EnableAutoConfiguration`** checks the dependencies available in the project.
* Based on the classpath, Spring Boot automatically configures required components.
* Example: If `spring-boot-starter-web` is present, it automatically configures **DispatcherServlet** and an embedded **Tomcat** server.

**7. Bean Creation and Dependency Injection**

* The **Spring Container** creates all required **Beans**.
* Dependencies are injected automatically using **`@Autowired`** or constructor injection.

**8. Embedded Server Starts**

* The embedded **Tomcat/Jetty/Undertow** server starts.
* The application is deployed automatically inside the embedded server.

**9. Application Ready**

* All Beans are initialized.
* The application starts accepting HTTP requests and serving users.

**Simple Lifecycle Flow:**

```text
main()
   ↓
SpringApplication.run()
   ↓
Load Configuration
   ↓
Create ApplicationContext
   ↓
Component Scanning
   ↓
Auto Configuration
   ↓
Create Beans & Inject Dependencies
   ↓
Start Embedded Server
   ↓
Application Ready
```

**Important Annotation:**

```java id="xydn6m"
@SpringBootApplication
```

This single annotation is equivalent to:

```java id="7b8i5a"
@Configuration
@EnableAutoConfiguration
@ComponentScan
```

* **`@Configuration`** → Marks the class as a source of Bean definitions.
* **`@EnableAutoConfiguration`** → Enables automatic configuration.
* **`@ComponentScan`** → Scans and registers Spring Beans.

**Simple Code Example:**

```java id="29ybwv"
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
```

With `spring-boot-starter-web`, Spring Boot automatically:

* Creates the **Spring Container**.
* Registers `HelloController` as a Bean.
* Starts the embedded **Tomcat** server.
* Maps `/hello` to the `hello()` method.


## 3. Spring Boot Flow Architecture works?

**Spring Boot Flow Architecture** describes how a request travels through the application, from the moment the application starts until a response is returned to the client. Internally, Spring Boot uses the **Spring Container**, **DispatcherServlet**, and **IoC/DI** to manage the complete flow automatically.

**Key Features:**

* **Auto Configuration** for automatic setup.
* **Spring IoC Container** manages all Beans.
* **Embedded Server** (Tomcat/Jetty/Undertow).
* **DispatcherServlet** acts as the front controller.
* Supports **MVC Architecture** (Controller → Service → Repository → Database).

**How It Works (Application Startup):**

1. **`main()` Method Starts :** The application starts with `SpringApplication.run()`.

2. **Spring Boot Initializes :** Loads configuration from `application.properties` or `application.yml`.

3. **Spring Container (ApplicationContext) is Created :** Creates and manages all **Beans**.

4. **Component Scanning :** Scans classes annotated with `@Component`, `@Service`, `@Repository`, and `@RestController`.

5. **Auto Configuration :** Configures required components automatically based on project dependencies.

6. **Dependency Injection :** Injects required dependencies into Beans using **`@Autowired`** or constructor injection.

7. **Embedded Server Starts :** Starts the embedded **Tomcat/Jetty/Undertow** server and deploys the application.

8. **Application is Ready :** The application starts listening for incoming HTTP requests.

**Request Processing Flow:**

```text
Client Request
      ↓
Embedded Tomcat Server
      ↓
DispatcherServlet (Front Controller)
      ↓
Controller (@RestController)
      ↓
Service (@Service)
      ↓
Repository (@Repository)
      ↓
Database
      ↓
Repository
      ↓
Service
      ↓
Controller
      ↓
DispatcherServlet
      ↓
HTTP Response to Client
```

**How a Request Works Internally:**

* The **Client** sends an HTTP request.
* The **Embedded Tomcat Server** receives the request.
* The **DispatcherServlet** intercepts every request and acts as the **Front Controller**.
* It finds the appropriate **Controller** method using URL mappings (`@GetMapping`, `@PostMapping`).
* The **Controller** calls the **Service** layer for business logic.
* The **Service** layer interacts with the **Repository** layer.
* The **Repository** communicates with the **Database**.
* The result flows back through **Repository → Service → Controller**.
* The **DispatcherServlet** converts the result into an HTTP response (usually JSON) and sends it back to the client.


**When to Use?**

* Building **REST APIs**.
* Developing **Microservices**.
* Creating **Enterprise Java Applications**.
* Any application following the **Spring MVC** pattern.

**Simple Code Example:**

```java id="j3h0eo"
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getUsers() {
        return userService.getUsers();
    }
}
```

```java id="vhe9gu"
@Service
public class UserService {

    public String getUsers() {
        return "List of Users";
    }
}
```

**Flow for the Above Example:**

1. Client calls `/users`.
2. **DispatcherServlet** receives the request.
3. It forwards the request to `UserController`.
4. `UserController` calls `UserService`.
5. `UserService` processes the logic and returns data.
6. The response is sent back to the client as JSON or plain text.



## 4. What is Bean and explain Lifecycle?

The **Bean Lifecycle** is the sequence of steps that a **Spring Bean** goes through from its **creation** to its **destruction** inside the **Spring Container**.

**Bean Lifecycle Flow**

| **Stage**                   | **Description**                                                                                        |
| --------------------------- | ------------------------------------------------------------------------------------------------------ |
| **1. Bean Creation**        | Spring creates the bean object.                                                                        |
| **2. Dependency Injection** | Spring injects all required dependencies using **constructor**, **setter**, or **field injection**.    |
| **3. Initialization**       | Spring calls initialization methods such as **@PostConstruct** or **initMethod**.                      |
| **4. Bean Ready**           | The bean is fully initialized and ready to be used by the application.                                 |
| **5. Bean Destruction**     | When the container shuts down, Spring calls **@PreDestroy** or **destroyMethod** to release resources. |


**Bean Lifecycle in more details**

1. **Bean Instantiation** – Spring creates the bean object.
2. **Dependency Injection** – Required dependencies are injected using **constructor**, **setter**, or **field injection**.
3. **Aware Interfaces (Optional)** – Spring calls methods like `BeanNameAware`, `BeanFactoryAware`, etc., if implemented.
4. **BeanPostProcessor (Before Initialization)** – `postProcessBeforeInitialization()` is executed.
5. **Initialization** – Spring calls initialization methods:


   * `@PostConstruct`
   * `InitializingBean.afterPropertiesSet()`
   * Custom `init-method`
6. **Bean Ready for Use** – The bean is fully initialized and available in the **Spring Container**.
7. **BeanPostProcessor (After Initialization)** – `postProcessAfterInitialization()` is executed.
8. **Bean Destruction** – When the container shuts down, Spring calls:

   * `@PreDestroy`
   * `DisposableBean.destroy()`
   * Custom `destroy-method`

**Key Features**

* **Managed automatically** by the **Spring Container**.
* Supports **custom initialization** and **cleanup logic**.
* Allows **pre-processing** and **post-processing** of beans.
* Works with annotations like **`@PostConstruct`** and **`@PreDestroy`**.

**How it Works**

When the application starts, the **Spring Container** scans and creates beans, injects dependencies, performs initialization callbacks, and makes the bean available for use. When the application stops, it executes the bean's destruction callbacks and removes it from memory.


**When to Use**

* When a bean requires **setup** before use.
* When external resources need to be **released properly**.
* When implementing **custom initialization or cleanup logic**.

**Code Example**

```java
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @PostConstruct
    public void init() {
        System.out.println("Bean Initialized");
    }

    public void sendEmail() {
        System.out.println("Sending Email...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean Destroyed");
    }
}
```

**Simple Lifecycle Diagram**

```text
Bean Creation
      ↓
Dependency Injection
      ↓
@PostConstruct / afterPropertiesSet()
      ↓
Bean Ready for Use
      ↓
@PreDestroy / destroy()
      ↓
Bean Removed
```

## 4. What is BeanPostProcessor?

A **BeanPostProcessor** is a Spring interface that allows you to **customize or modify a bean** before and after its initialization.

It is commonly used for:

1. **Logging**
2. **Validation**
3. **Creating proxies (AOP)**
4. **Injecting additional behavior**

Spring automatically calls these two methods for every bean:

* **postProcessBeforeInitialization()** – Runs **before** initialization methods (such as **`@PostConstruct`**).
* **postProcessAfterInitialization()** – Runs **after** initialization and can return a **modified or proxied bean**.

**Example**

```java
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Before Initialization: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("After Initialization: " + beanName);
        return bean;
    }
}
```


## 4. What is Bean Scope in Spring?

**Bean Scope** defines the **lifecycle** and **visibility** of a Spring bean. It determines **how many instances** of a bean Spring creates and **how long** they live in the Spring container.

**Types of Bean Scopes**

| **Scope**       | **Description**                                                            |
| --------------- | -------------------------------------------------------------------------- |
| **Singleton**   | **One instance** is created for the entire Spring container (**Default**). |
| **Prototype**   | A **new instance** is created every time the bean is requested.            |
| **Request**     | One instance is created **per HTTP request**. (Web applications)           |
| **Session**     | One instance is created **per HTTP session**. (Web applications)           |
| **Application** | One instance is created for the **entire web application**.                |
| **WebSocket**   | One instance is created **per WebSocket session**.                         |

**Key Features**

* **Singleton** is the **default** scope.
* **Prototype** creates a **new object** every time.
* **Request**, **Session**, **Application**, and **WebSocket** are used only in **Spring Web** applications.
* Bean scope controls the bean's **lifecycle** and **object creation**.

**How It Works**

* When Spring starts, **Singleton** beans are created once and shared across the application.
* For **Prototype**, Spring creates a **new bean** whenever it is requested.
* For web scopes, Spring creates beans based on the **HTTP request**, **session**, or **application lifecycle**.

**When to Use**

* **Singleton**: For **stateless services**, repositories, and controllers.
* **Prototype**: For **stateful objects** where each user needs a separate instance.
* **Request**: Store data related to a **single HTTP request**.
* **Session**: Store **user-specific** data across multiple requests.
* **Application**: Share data across the **entire web application**.
* **WebSocket**: Maintain state for a **WebSocket connection**.

**Code Example**

**Singleton (Default)**

```java
import org.springframework.stereotype.Component;

@Component
public class UserService {
}
```

**Prototype**

```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class User {
}
```

**Request Scope**

```java
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RequestBean {
}
```

**Common Interview Follow-up Questions**

**1. What is the default Bean Scope in Spring?**

**`singleton`**.

**2. What is the difference between `singleton` and `prototype`?**

* **`singleton`** → One shared instance for the entire application.
* **`prototype`** → A new instance is created every time the bean is requested.

**3. Are Spring `@Service` beans singleton by default?**

**Yes.** All beans annotated with **`@Component`**, **`@Service`**, **`@Repository`**, and **`@Controller`** are **singleton** by default.

**4. Which Bean Scope is used most often?**

**`singleton`**, because service, repository, and controller beans are generally **stateless** and can be safely shared.

**5. Can we inject a `prototype` bean into a `singleton` bean?**

**Yes**, but the **prototype bean is created only once at injection time**. If you need a **new prototype instance on every use**, use **`ObjectProvider`**, **`ObjectFactory`**, or **`@Lookup`**.


## 5. What is @Component vs @Bean/Bean?

Both **`@Component`** and **`@Bean`** are used to create and manage **Spring Beans** in the **Spring IoC Container**, but they are used in different situations.

* **`@Component`**: An annotation used to tell **Spring** to automatically detect and create an object (bean) during **component scanning** and used for **our own classes**

* **`@Bean`**: An annotation used inside a **`@Configuration`** class to manually create and register a bean in the Spring container.

* **Java Bean** is a **plain Java class** that follows certain conventions: it has a **no-argument constructor**, **private fields**, and **public getter/setter methods**.


**Key Difference**

| **@Component**                     | **@Bean**                                       |
| ---------------------------------- | ----------------------------------------------- |
| **Automatic bean creation**        | **Manual bean creation**                        |
| Used on the **class**              | Used on a **method**                            |
| Detected by **component scanning** | Created when the **`@Bean` method** is executed |
| Best for classes you **own**       | Best for **third-party or external classes**    |

**How it Works**

* With **`@Component`**, Spring scans the package, finds the annotated class, creates an object, and stores it in the **Spring Container**.
* With **`@Bean`**, Spring loads the **`@Configuration`** class, calls the **`@Bean`** method, and registers the returned object as a bean.


**When to Use**

* **`@Component`**

  * Service classes (`@Service`)
  * DAO/Repository classes (`@Repository`)
  * Controller classes (`@Controller`, `@RestController`)
  * Custom utility or helper classes

* **`@Bean`**

  * Configuring **third-party libraries**
  * Creating objects with **custom constructor arguments**
  * When you need full control over bean creation

* **`Java Bean`**
* Use **Java Bean** when creating a normal Java object with standard properties.

**Code Example**

**Using `Java Bean`**
```java
// Java Bean
public class Employee {

    private String name;

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

**Using `@Component`**

```java
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    public void send() {
        System.out.println("Email Sent");
    }
}
```

**Using `@Bean`**

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EmailService emailService() {
        return new EmailService();
    }
}
```

**Common Interview Follow-up Questions**

**1. Can `@Service`, `@Repository`, and `@Controller` replace `@Component`?**

**Yes.** They are **specialized versions** of **`@Component`** with additional semantic meaning.

**2. Can we use `@Bean` for our own classes?**

**Yes.** Although **`@Component`** is usually simpler, **`@Bean`** is useful when you need custom object creation or configuration.

**3. Why can't we annotate third-party classes with `@Component`?**

Because **we don't own their source code**, so we register them using **`@Bean`**.

**4. Which annotation provides more control over object creation?**

**`@Bean`**, because you explicitly define how the object is created and configured.

**5. Can both `@Component` and `@Bean` create singleton beans?**

**Yes.** By default, both create **singleton** beans unless a different scope (such as **prototype**) is configured.

## 5. What is Bean and Object?

An **Object** is any instance of a class created using the `new` keyword.

A **Bean** is an object that is **created, managed, and controlled by the Spring IoC Container**.

| **Feature**              | **Object**                                                  | **Bean**                                                                           |
| ------------------------ | ----------------------------------------------------------- | ---------------------------------------------------------------------------------- |
|            | An **instance of a class** created using the `new` keyword. | An **object managed by the Spring IoC Container**.                                 |
| **Creation**             | Created manually by the developer.                          | Created and managed automatically by Spring.                                       |
| **Lifecycle**            | Developer is responsible for creation and destruction.      | Spring manages the **complete lifecycle** (creation, initialization, destruction). |
| **Dependency Injection** | Dependencies must be created manually.                      | Supports **Dependency Injection (DI)** automatically.                              |
| **Management**           | Not managed by the Spring container.                        | Managed inside the **ApplicationContext**.                                         |

**How It Works**

* An **Object** is created directly:

  ```java
  Student s = new Student();
  ```
* A **Bean** is created by Spring using annotations like **`@Component`**, **`@Service`**, or **`@Bean`**, and the Spring container injects it wherever needed.

**When to Use**

* Use an **Object** for simple Java programs where Spring is not involved.
* Use a **Bean** in **Spring Boot/Spring Framework** applications to take advantage of **IoC** and **Dependency Injection**.

**Code Example**

```java id="m4k8qw"
// Normal Object
Student student = new Student();

// Spring Bean
@Service
public class StudentService {
}
```

```java id="v9r2nh"
@Autowired
private StudentService studentService; // Spring injects the Bean
```


## 6. How can you disable specific auto-configurations in Spring Boot?

Use `@SpringBootApplication(exclude)` or `spring.autoconfigure.exclude` property to disable unwanted auto-configurations.

```java
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class,
    SecurityAutoConfiguration.class
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

```properties
# application.properties
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

## 7. What is @SpringBootApplication annotation?

@SpringBootApplication is a convenience annotation that combines three commonly used annotations: @Configuration, @EnableAutoConfiguration, and @ComponentScan.

```java
@SpringBootApplication // Combines three annotations
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}

// Equivalent to:
@Configuration
@EnableAutoConfiguration  
@ComponentScan
public class MyApplication { }
```

It's the standard annotation for Spring Boot main classes and enables all essential Spring Boot features.



## 8. **What is a Starter in Spring Boot?**

A **Spring Boot Starter** is a **predefined dependency package** that includes all the required libraries for a specific feature.

Instead of adding many dependencies manually, you add **one starter**, and Spring Boot brings the required libraries automatically.

**Common Starters**

1. **spring-boot-starter-web**

   * For building **REST APIs** and web applications.

2. **spring-boot-starter-data-jpa**

   * For **JPA**, **Hibernate**, and database access.

3. **spring-boot-starter-security**

   * For **authentication** and **authorization**.

4. **spring-boot-starter-test**

   * For **JUnit**, **Mockito**, and testing.

**Example**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```


**Common Interview Follow-up Questions**

1. **Why are starters useful?**

   * They **simplify dependency management** and ensure **compatible library versions**.

2. **Can you create a custom starter?**

   * **Yes**, Spring Boot allows creating **custom starters** for reusable configurations.

3. **Do starters contain code?**

   * They mainly contain **dependencies and configuration**, not business logic.



## 8. **What is Auto-Configuration in Spring Boot?**

**Auto-Configuration** is a feature that **automatically configures Spring Beans** based on:

* **Dependencies** available on the classpath.
* **Configuration properties** (such as `application.properties` or `application.yml`).

This reduces the need for manual configuration.

**Example**

If **`spring-boot-starter-web`** is on the classpath, Spring Boot automatically configures:

* **DispatcherServlet**
* **Tomcat**
* **Jackson**
* **Spring MVC**

without writing configuration code.

**Example**

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

`@SpringBootApplication` includes **`@EnableAutoConfiguration`**, which enables auto-configuration.


**Common Interview Follow-up Questions**

1. **Which annotation enables auto-configuration?**

   * **`@EnableAutoConfiguration`**.

2. **How can you disable an auto-configuration?**

   * Use:

   ```java
   @SpringBootApplication(
       exclude = DataSourceAutoConfiguration.class
   )
   ```

3. **Does auto-configuration override your custom beans?**

   * **No.** If you define your own bean, Spring Boot **backs off** and uses your bean instead.



## 8. **What does the @ComponentScan annotation do?**

**`@ComponentScan`** tells Spring **where to search for Beans**.

It scans the specified packages and automatically registers classes annotated with:

* **`@Component`**
* **`@Service`**
* **`@Repository`**
* **`@Controller`**
* **`@RestController`**

into the **Spring IoC Container**.

**Example**

```java
@ComponentScan("com.example.service")
@Configuration
public class AppConfig {
}
```

Spring scans the **`com.example.service`** package and registers all eligible beans.

**Default Behavior**

If no package is specified, Spring scans:

* The package containing the **main application class**.
* All its **sub-packages**.


**Common Interview Follow-up Questions**

1. **Which annotations are detected by `@ComponentScan`?**

   * **`@Component`**, **`@Service`**, **`@Repository`**, **`@Controller`**, and **`@RestController`**.

2. **What happens if a package is outside the scan path?**

   * Spring **does not detect or register** its beans.

3. **Does `@SpringBootApplication` include `@ComponentScan`?**

   * **Yes.** `@SpringBootApplication` includes **`@ComponentScan`**, **`@EnableAutoConfiguration`**, and **`@SpringBootConfiguration`**.


## 8. @Controller, @RestController, @Service, @Repository annotations?

These are **Spring Stereotype Annotations** used to mark different layers of an application. They help the **Spring Container** automatically detect and manage classes as **beans** during **component scanning**.

**@Controller (Returns View)r** is used to handle web requests and return views (like JSP/HTML).

**@RestController (Returns JSON)** is used to build REST APIs and returns JSON/XML data instead of views.

**@Service** is used for business logic layer.

**@Repository** is used for database/DAO layer and provides exception handling.

**Key Features**

* All are **specialized versions of `@Component`**.
* Automatically detected by **component scanning**.
* Improve **code organization** by separating application layers.
* Enable Spring-specific features based on the annotation used.

**How it Works**

When the application starts, the **Spring Container** scans the packages for these annotations, creates their objects (beans), and manages their lifecycle. Each annotation represents a specific responsibility in the application architecture.

**Difference Between Annotations**

| **Annotation**        | **Purpose**                                            | **Used In**        | **Special Feature**                                                 |
| --------------------- | ------------------------------------------------------ | ------------------ | ------------------------------------------------------------------- |
| **`@Controller`**     | Handles web requests and returns a **view (HTML/JSP)** | Presentation Layer | Used with **Spring MVC**                                            |
| **`@RestController`** | Handles REST APIs and returns **JSON/XML data**        | Presentation Layer | Combines **`@Controller` + `@ResponseBody`**                        |
| **`@Service`**        | Contains **business logic**                            | Service Layer      | Improves code readability and design                                |
| **`@Repository`**     | Handles **database operations**                        | Persistence Layer  | Automatically translates database exceptions into Spring exceptions |


**When to Use**

* Use **`@Controller`** when returning web pages (JSP, Thymeleaf, etc.).
* Use **`@RestController`** when building **RESTful APIs**.
* Use **`@Service`** for implementing business rules and application logic.
* Use **`@Repository`** for interacting with the database using **JPA**, **Hibernate**, or JDBC.

**Code Example**

**`@Controller`**

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
```

**`@RestController`**

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String getUser() {
        return "John";
    }
}
```

**`@Service`**

```java
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserName() {
        return "John";
    }
}
```

**`@Repository`**

```java
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public void save() {
        System.out.println("User Saved");
    }
}
```

**Simple Flow**

```text
Client Request
      ↓
@Controller / @RestController
      ↓
@Service
      ↓
@Repository
      ↓
Database
```

## 9. What is @Autowired vs @Inject annotation?



* **`@Autowired`** is a **Spring-specific** annotation used for **Dependency Injection (DI)**.
* **`@Inject`** is a **Java Standard (JSR-330)** annotation used for DI and is supported by Spring as well as other DI frameworks.

**Key Features**

* Both are used to **automatically inject dependencies**.
* Both can be applied to **constructor, setter, and field injection**.
* **Constructor injection** is the recommended approach with either annotation.
* **`@Autowired`** provides additional Spring-specific features.

**How it Works**

When the **Spring Container** creates a bean, it looks for `@Autowired` or `@Inject`. It then searches for a matching bean in the container and injects it automatically into the target class.

**Difference Between `@Autowired` and `@Inject`**

| **Feature**             | **`@Autowired`**                                | **`@Inject`**                                          |
| ----------------------- | ----------------------------------------------- | ------------------------------------------------------ |
| **Standard**            | Spring-specific                                 | Java Standard (**JSR-330**)                            |
| **Package**             | `org.springframework.beans.factory.annotation`  | `jakarta.inject` (or `javax.inject` in older versions) |
| **Required Dependency** | Supports `required = false`                     | No `required` attribute                                |
| **Optional Injection**  | `@Autowired(required = false)` or `Optional<T>` | Use `Optional<T>` or `Provider<T>`                     |
| **Qualifier Support**   | `@Qualifier`                                    | `@Named` (or Spring's `@Qualifier`)                    |
| **Portability**         | Works only with Spring                          | Works across multiple DI frameworks                    |


**When to Use**

* **`@Autowired`**

  * Spring Boot applications.
  * When using features like `required = false`.
  * When relying on Spring-specific annotations.

* **`@Inject`**

  * Projects following **JSR-330 standards**.
  * Applications that may switch between different DI frameworks.
  * When writing more portable and framework-agnostic code.

**Code Example**

**Using `@Autowired`**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}
```

**Using `@Inject`**

```java
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;
}
```

**Recommended Constructor Injection**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired    // Optional in Spring 4.3+ if only one constructor exists
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

## 10. What is @Profile Annotation?



**`@Profile`** is a **Spring annotation** used to activate or register a bean only for a specific **environment** such as **development (`dev`)**, **testing (`test`)**, or **production (`prod`)**.

**Key Features**

* Enables **environment-specific bean configuration**.
* Prevents loading unnecessary beans for other environments.
* Can be used on **`@Component`**, **`@Service`**, **`@Repository`**, **`@Configuration`**, or **`@Bean`** methods.
* Supports **multiple profiles** and profile expressions.

**How it Works**

When the application starts, Spring checks the **active profile** (configured using `spring.profiles.active`). It creates and registers only those beans whose `@Profile` value matches the active profile.


**When to Use**

* Different **database configurations** for development and production.
* Different implementations of a service for testing and real environments.
* Environment-specific settings such as **logging**, **security**, or **external APIs**.

**Code Example**

**Using `@Profile` on a Bean**

```java id="wokf0t"
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevEmailService {

    public void send() {
        System.out.println("Using Development Email Service");
    }
}
```

```java id="76xvjo"
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdEmailService {

    public void send() {
        System.out.println("Using Production Email Service");
    }
}
```

**Activate a Profile**

```properties
spring.profiles.active=dev
```

With the above configuration, Spring will create only the **`DevEmailService`** bean.

**Difference Between Common Profiles**

| **Profile** | **Purpose**                                |
| ----------- | ------------------------------------------ |
| **`dev`**   | Local development and debugging            |
| **`test`**  | Unit and integration testing               |
| **`prod`**  | Production environment with real resources |


```java
# application.properties
spring.profiles.active=dev

# application-dev.properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb

# application-prod.properties
server.port=80
spring.datasource.url=jdbc:mysql://prod-server:3306/mydb
```

```java
@Component
@Profile("dev")
public class DevDataLoader {
    // Only loaded in dev profile
}
```


## 11. What is ApplicationContext?



**`ApplicationContext`** is the **central interface of the Spring Container**. It is responsible for **creating, configuring, storing, and managing the lifecycle of Spring beans** and providing them to the application whenever needed.

**Key Features**

* Manages the complete **Bean Lifecycle**.
* Supports **Dependency Injection (DI)** and **Inversion of Control (IoC)**.
* Automatically performs **component scanning**.
* Supports **internationalization (i18n)**, **event publishing**, and **AOP integration**.
* Loads configuration from **Java Config**, **XML**, or **annotations**.

**How it Works**

When the application starts, **`ApplicationContext`** reads the configuration, scans for Spring annotations like **`@Component`**, **`@Service`**, **`@Repository`**, and **`@Controller`**, creates the required beans, injects dependencies, and stores them inside the **Spring Container**. Whenever a bean is requested, it returns the managed object.


**When to Use**

* In any **Spring or Spring Boot application**.
* When you need **Dependency Injection** and centralized bean management.
* When building applications with **loosely coupled components**.

**Common Methods**

| **Method**                     | **Purpose**                             |
| ------------------------------ | --------------------------------------- |
| **`getBean()`**                | Retrieves a bean from the container     |
| **`containsBean()`**           | Checks if a bean exists                 |
| **`getBeanDefinitionNames()`** | Returns all registered bean names       |
| **`close()`**                  | Closes the container and destroys beans |

**Code Example**

**Creating and Using `ApplicationContext`**

```java id="v4j7n8"
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

ApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);

UserService userService = context.getBean(UserService.class);

userService.display();
```

**Spring Managed Bean**

```java id="u4xkn1"
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void display() {
        System.out.println("Bean from ApplicationContext");
    }
}
```

**`BeanFactory` vs `ApplicationContext`**

| **BeanFactory**                          | **ApplicationContext**                           |
| ---------------------------------------- | ------------------------------------------------ |
| Basic IoC container                      | Advanced Spring container                        |
| Lazy bean initialization by default      | Eager singleton bean initialization by default   |
| Limited features                         | Supports **AOP**, **events**, **i18n**, and more |
| Mainly used for lightweight applications | Commonly used in all Spring Boot applications    |



## 12. What is @Component, @Configuration, @Primary, @Qualifier, @PatchMapping annotation?


* **@Component** creates a **Spring Bean** automatically during component scanning.
* **@Configuration** is used to define configuration classes that create beans using **@Bean** methods.
* **@Primary** makes one bean the **default choice** when multiple beans of the same type exist.
* **@Qualifier** specifies **which bean** should be injected when multiple beans are available.
**`@PatchMapping`** is a **Spring MVC** annotation used to handle **HTTP PATCH requests**. 


**@Component**  is a **Spring stereotype annotation** and tells Spring to automatically **detect and create a Bean** during component scanning and registers it in the **ApplicationContext**.

**Key Features**

* Enables **automatic bean creation**.
* Managed by the **Spring Container**.
* Parent annotation for **`@Service`**, **`@Repository`**, and **`@Controller`**.
* Supports **Dependency Injection (DI)**.

**How it Works**

Spring scans the package for classes annotated with `@Component`, creates their objects, and stores them as beans in the container.


**When to Use**

* To avoid manual bean configuration.
* To let Spring automatically manage object creation.
* For utility classes or custom components that need to be managed by Spring.

**Code Example**

```java
import org.springframework.stereotype.Component;

@Component
public class EmailService {
}
```


---


**@Configuration** marks a class that contains **@Bean methods**. Spring executes these methods and registers their returned objects as beans.

**Key Features**

* Used for **Java-based configuration**.
* Replaces XML configuration.
* Supports one or more **`@Bean`** methods.
* Classes are managed by the **Spring Container**.

**How it Works**

Spring loads the `@Configuration` class, executes its `@Bean` methods, and registers the returned objects as beans.

**When to Use**

* When custom bean creation or configuration is required.

**Code Example**

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EmailService emailService() {
        return new EmailService();
    }
}
```


---


**`@Primary`** tells Spring to use a particular bean **by default** when multiple beans of the same type are available.

**Key Features**

* Resolves **bean ambiguity**.
* Applied to a bean class or `@Bean` method.
* Works with **Dependency Injection**.

**How it Works**

If multiple beans match the required type, Spring automatically injects the bean marked with `@Primary`.


**When to Use**

* When multiple implementations of the same interface exist.

**Code Example**

```java
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MySqlDatabaseService implements DatabaseService {
}
```

---


**`@Qualifier`** is used with **`@Autowired`** to specify exactly which bean should be injected when multiple beans of the same type exist.

**Key Features**

* Resolves **multiple bean conflicts**.
* Works together with **`@Autowired`** or **`@Inject`**.
* Allows selecting a bean by **name**.

**How it Works**

Spring checks the value provided in `@Qualifier` and injects the matching bean.


**When to Use**

* When multiple implementations of an interface are available and `@Primary` is not sufficient.

**Code Example**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserService(@Qualifier("oracleDatabaseService")
                       DatabaseService databaseService) {
    }
}
```


---


**`@PatchMapping`** is a **Spring MVC** annotation used to handle **HTTP PATCH requests**. It is mainly used for **partial updates** of a resource.

**Key Features**

* Maps **HTTP PATCH** requests.
* Used in **REST APIs**.
* Shortcut for `@RequestMapping(method = RequestMethod.PATCH)`.
* Supports **partial resource modification**.

**How it Works**

When a client sends a **PATCH** request to a specific URL, Spring routes the request to the method annotated with `@PatchMapping`.


**When to Use**

* In REST APIs where partial updates are required, such as updating only a user's email or phone number.

**Code Example**

```java
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PatchMapping("/users/{id}")
    public String updateUser(@PathVariable Long id) {
        return "User updated successfully";
    }
}
```


## 13. Explain Spring Boot Actuator endpoints.


Actuator provides production-ready features like health checks, metrics, and monitoring endpoints. Common endpoints: `/health`, `/metrics`, `/info`, `/env`.

**Example:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```java
# application.properties
management.endpoints.web.exposure.include=health,metrics,info
management.endpoint.health.show-details=always
```

```java
// Custom health indicator
@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean isHealthy = checkService();
        if (isHealthy) {
            return Health.up().withDetail("service", "available").build();
        }
        return Health.down().withDetail("service", "unavailable").build();
    }
}
```

## 14. How do you ensure security a Spring Boot Microservices application?

To secure a **Spring Boot application**, you can use **Spring Security** to handle authentication and authorization. Common practices include:

* **Role-based access control** using annotations like `@PreAuthorize` or `@Secured`
* **JWT or OAuth2 tokens** for stateless authentication
* **Encrypting passwords** with BCrypt or Argon2
* **HTTPS/SSL** to secure data in transit
* **Input validation and CSRF protection** to prevent attacks

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt())
            .build();
    }
}

@RestController
@PreAuthorize("hasRole('USER')")
public class SecureController {
    
    @GetMapping("/api/user/profile")
    public UserProfile getProfile(Authentication auth) {
        return userService.getProfile(auth.getName());
    }
}
```
## 15. What is Lombok in Java and and whe can we use?

**Lombok** is a Java library that **reduces boilerplate code** by automatically generating code like **getters, setters, constructors, `toString()`, `equals()`, and `hashCode()`** at compile time using **annotations**.

**Key Features**

* **@Getter** and **@Setter** – Generate getters and setters.
* **@NoArgsConstructor**, **@AllArgsConstructor** – Generate constructors.
* **@ToString** – Generate `toString()` method.
* **@EqualsAndHashCode** – Generate `equals()` and `hashCode()`.
* **@Data** – Combines common annotations (`@Getter`, `@Setter`, `@ToString`, etc.).
* **@Builder** – Implements the Builder Pattern.

**How It Works**

* You add **Lombok annotations** to a class.
* During **compilation**, Lombok generates the required code automatically.
* The generated code behaves as if you had written it manually.


**When to Use**

* In **POJOs**, **DTOs**, **Entities**, and **Model classes** where repetitive code is common.

**Example**

```java
import lombok.Data;

@Data
public class Employee {
    private int id;
    private String name;
}
```

Lombok automatically generates:

```java
getId(), setId(),
getName(), setName(),
toString(),
equals(),
hashCode()
```

**Example Without Lombok**

```java
public class User {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
```

**Same Code Using Lombok**

```java
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
    private int age;
}
```


## 17. How can you create a custom configuration and auto-configuration in Spring Boot?

Create **custom configuration** using `@Configuration` and `@Bean` annotations.

```java
@Configuration
public class AppConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    @ConditionalOnProperty(name = "app.cache.enabled", havingValue = "true")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }
}
```

```properties
# application.properties
app.cache.enabled=true
```

Create a **Auto configuration** class with conditional annotations and register it in `META-INF/spring.factories`.

```java
@Configuration
@ConditionalOnClass(MyService.class)
@EnableConfigurationProperties(MyProperties.class)
public class MyAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public MyService myService(MyProperties properties) {
        return new MyService(properties);
    }
}
```

```properties
# META-INF/spring.factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.example.MyAutoConfiguration
```

## 16. Why do we use Long in JpaRepository<Employee, Long>?
In **`JpaRepository<Employee, Long>`**, the **first type (`Employee`)** is the **entity class** the repository manages, and the **second type (`Long`)** is the **type of the entity’s primary key (`@Id`)**.

Using `Long` tells Spring Data JPA what type of value to expect when performing operations like `findById()`, `deleteById()`, or `save()`.


## 18. What is Spring Scheduler?

**Spring Scheduler** is a feature in **Spring Framework** used to **run tasks automatically at a scheduled time or at fixed intervals**.

It is commonly used for **background jobs** like sending emails, cleaning logs, or running periodic tasks using the `@Scheduled` annotation.

**Real-Time Example**

* Sending **daily reports**
* **Cleaning temporary data** every night
* **Sending scheduled emails**
* **Database backup every day**

**Enable Scheduling**
```java
@EnableScheduling
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

**Create Scheduled Task**
```java
@Component
public class MyScheduler {

    @Scheduled(fixedRate = 5000)
    public void runTask() {
        System.out.println("Task running every 5 seconds");
    }
}

//Output
Task running every 5 seconds
Task running every 5 seconds
Task running every 5 seconds
```

## 19. How would you set up a logging level for Spring Boot (e.g., debug, info, error)?

Configure logging levels in application.properties for different packages and classes.

```properties
# Root level (applies to all)
logging.level.root=INFO

# Package-specific levels
logging.level.com.mycompany.service=DEBUG
logging.level.com.mycompany.controller=INFO

# Framework levels
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG

# Specific class
logging.level.com.mycompany.UserService=TRACE
```

**Available levels (in order):**
- TRACE (most detailed)
- DEBUG
- INFO
- WARN
- ERROR (least detailed)

```java
@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    public User findUser(Long id) {
        log.debug("Finding user with id: {}", id);
        log.info("User search completed");
        return user;
    }
}
```

 
## 20. What is `@Scope`, `@prototype`, `@PostConstruct` and `@PreDestroy` and  in Spring Boot?

**@Scope**

* Purpose: Defines the lifecycle and visibility context of a Spring bean.
* Default: singleton (one instance per container).
* Common Values: prototype, request, session, application.
* Example: Applied at class level alongside stereotype annotations.

```java
@Component
@Scope("prototype")public class ReportGenerator {}
```

**@prototype**

* Correction: There is no annotation named @prototype in Spring.
* Actual Use: It is a value passed into the @Scope annotation.
* Behavior: Instructs Spring to create a brand new instance every single time the bean is requested or injected.
* Example:
 
```java
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) or @Scope("prototype").
```

**@PostConstruct**

* Purpose: Marks a method to run exactly once, immediately after Spring finishes initializing the bean and injecting all dependencies.
* Common Use: Executing setup logic, populating caches, or validating that mandatory properties are not null.
* Rule: The method must be void and cannot accept any arguments.

```java
@PostConstructpublic void init() {
    this.connectionPool = initializePool();
}
```

**@PreDestroy**

* Purpose: Marks a method to run exactly once, right before the Spring container destroys the bean and removes it from memory.
* Common Use: Cleaning up resources, closing database connections, stopping background threads, or releasing file handles.
* Limitation: It does not trigger for prototype scoped beans because Spring loses track of them after creation.

```java
@PreDestroypublic void cleanup() {
    this.connectionPool.close();
}
```

**Singleton vs Prototype Scope**

| Feature              | Singleton                              | Prototype                                  |
|----------------------|----------------------------------------|--------------------------------------------|
| Instances created    | One per Spring container               | New instance every time it is requested    |
| Default scope        | Yes (default in Spring)                | No                                         |
| Memory               | Shared, memory efficient               | More memory usage                          |
| State                | Shared state — be careful with mutable fields | Each caller gets its own state        |
| Annotation           | `@Scope("singleton")` or default       | `@Scope("prototype")`                      |


**🔹 1. Singleton Scope (Default)**

👉 Only **one instance** is created and shared.

```java
import org.springframework.stereotype.Component;

@Component
class MyService {
    public void print() {
        System.out.println("Singleton instance: " + this);
    }
}
```

```java
@Autowired
MyService s1;

@Autowired
MyService s2;

s1.print();
s2.print();


// Output (same object)
Singleton instance: MyService@123
Singleton instance: MyService@123
```

**🔹 2. Prototype Scope**

👉 **New object every time** it is requested.

```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
class ReportGenerator {
    public void print() {
        System.out.println("Prototype instance: " + this);
    }
}
```

```java
import org.springframework.context.ApplicationContext;

@Autowired
ApplicationContext context;

ReportGenerator r1 = context.getBean(ReportGenerator.class);
ReportGenerator r2 = context.getBean(ReportGenerator.class);

r1.print();
r2.print();

//  Output (different objects)

Prototype instance: ReportGenerator@111
Prototype instance: ReportGenerator@222
```

## 21. How do you manage Spring Beans in Spring Boot?

Spring Boot automatically manages beans through component scanning and auto-configuration. You can also define custom beans explicitly.

```java
// Automatic bean management
@Service
public class UserService { }

@Repository
public class UserRepository { }

// Manual bean definition
@Configuration
public class BeanConfig {
    
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }
    
    @Bean
    @ConditionalOnProperty("app.redis.enabled")
    public RedisTemplate redisTemplate() {
        return new RedisTemplate();
    }
}
```

Spring manages the entire lifecycle: creation, dependency injection, initialization, and destruction.

## 22. What is the difference between `@OneToMany` and `@ManyToOne` in Spring Boot?

These annotations define JPA relationships between entities with different cardinalities.

```java
// One User has Many Orders
@Entity
public class User {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    // cascade = CascadeType.ALL means all operations performed on the parent entity will also be applied to the child entities automatically.
}

// Many Orders belong to One User
@Entity
public class Order {
    @Id
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

**Key differences:**
- `@OneToMany`: One entity relates to multiple entities
- `@ManyToOne`: Multiple entities relate to one entity
- They're opposite sides of the same relationship

## 23. How can you configure pagination and sorting in Spring Boot with Spring Data JPA?

Use `PagingAndSortingRepository` or `Pageable` parameter in repository methods for automatic pagination.

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByName(String name, Pageable pageable);
    Page<User> findAll(Pageable pageable);
}

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/users")
    public Page<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }
}
```

## 25. How Does `@EnableAutoConfiguration` Work Internally?

**`@EnableAutoConfiguration`** is a Spring Boot annotation that **automatically configures** beans based on the **dependencies** available in the project and the **application configuration**, reducing the need for manual configuration.

**Key Features**

* **Automatic Bean Configuration**
* **Reduces Manual Configuration**
* Uses **Classpath Detection**
* Uses **Conditional Annotations**
* Works with **Spring Boot Starters**
* Can be **customized or excluded**

**How It Works Internally**

1. The application starts with the **`@SpringBootApplication`** annotation.
2. **`@SpringBootApplication`** internally includes **`@EnableAutoConfiguration`**.
3. **`@EnableAutoConfiguration`** imports **`AutoConfigurationImportSelector`**.
4. **`AutoConfigurationImportSelector`** reads the list of auto-configuration classes from:

   * **Spring Boot 3.x:** `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
   * **Spring Boot 2.x:** `META-INF/spring.factories`
5. Spring checks the **classpath** and **application properties**.
6. **Conditional annotations** determine whether a configuration should be applied:

   * **`@ConditionalOnClass`**
   * **`@ConditionalOnMissingBean`**
   * **`@ConditionalOnProperty`**
   * **`@ConditionalOnBean`**
7. If all conditions are satisfied, Spring automatically creates the required **beans** and registers them in the **IoC Container**.

**Internal Flow**

```text
@SpringBootApplication
        │
        ▼
@EnableAutoConfiguration
        │
        ▼
AutoConfigurationImportSelector
        │
        ▼
Read Auto Configuration Classes
        │
        ▼
Check Classpath & Properties
        │
        ▼
Evaluate Conditional Annotations
        │
        ▼
Create Required Beans
        │
        ▼
Register Beans in IoC Container
```

**Common Conditional Annotations**

| **Annotation**                  | **Purpose**                                    |
| ------------------------------- | ---------------------------------------------- |
| **`@ConditionalOnClass`**       | Configure only if a class exists               |
| **`@ConditionalOnMissingBean`** | Create a bean only if it doesn't already exist |
| **`@ConditionalOnBean`**        | Configure only if another bean exists          |
| **`@ConditionalOnProperty`**    | Configure based on a property value            |

**When to Use**

* **Spring Boot Applications**
* **REST APIs**
* **Microservices**
* **Database Configuration**
* **Security Configuration**
* Any application that needs **minimal manual configuration**

**Code Example**

```java
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

**Disable Specific Auto Configuration**

```java
@SpringBootApplication(
    exclude = DataSourceAutoConfiguration.class
)
public class DemoApplication {
}
```

**Common Interview Follow-up**

**Q: What is the difference between `@ComponentScan` and `@EnableAutoConfiguration`?**

| **`@ComponentScan`**                                                                                      | **`@EnableAutoConfiguration`**                                                                             |
| --------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| Scans the project for **`@Component`**, **`@Service`**, **`@Repository`**, and **`@Controller`** classes. | Automatically configures **Spring-provided beans** based on the project's dependencies and configuration.  |
| Registers **application beans**.                                                                          | Registers **framework beans** such as **DataSource**, **DispatcherServlet**, and **Jackson ObjectMapper**. |


## 27. What is `@EventListener` in Spring Boot?

It's Spring's built-in **event-driven mechanism** — publish an event, and any listener picks it up.

**Create an event:**
```java
public class UserRegisteredEvent {
    private final String email;
    public UserRegisteredEvent(String email) { this.email = email; }
    public String getEmail() { return email; }
}
```

**Publish it:**
```java
@Autowired ApplicationEventPublisher publisher;

public void register(String email) {
    // save user...
    publisher.publishEvent(new UserRegisteredEvent(email));
}
```

**Listen to it:**
```java
@EventListener
public void onUserRegistered(UserRegisteredEvent event) {
    sendWelcomeEmail(event.getEmail());
}
```

By default it's **synchronous**. Combine with `@Async` to make it non-blocking.

Use it to decouple logic — like sending emails, notifications, or audit logging — without tight coupling between services.


## 28. What is `ResponseEntity` and When to Use It?

`ResponseEntity` gives you **full control** over the HTTP response — status code, headers, and body.

```java
// Simple return — no control
@GetMapping("/user/{id}")
public User getUser(@PathVariable Long id) {
    return userService.find(id);
}

// With ResponseEntity — full control
@GetMapping("/user/{id}")
public ResponseEntity<User> getUser(@PathVariable Long id) {
    User user = userService.find(id);
    if (user == null) {
        return ResponseEntity.notFound().build();           // 404
    }
    return ResponseEntity.ok(user);                        // 200
}
```

You can also set custom headers:
```java
return ResponseEntity
    .status(HttpStatus.CREATED)
    .header("X-Custom-Header", "value")
    .body(savedUser);
```

Use it when you need to return different status codes, add headers, or return an empty body with a specific status.


## 29. What is `@ExceptionHandler` vs `@ControllerAdvice`?

Both handle exceptions — but at different scopes.

**`@ExceptionHandler`** — handles exceptions **only within the same controller**.

```java
@RestController
public class UserController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
```

**`@ControllerAdvice`** — handles exceptions **globally across all controllers**. One place to handle all errors.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(500).body("Something went wrong");
    }
}
```

## 30. DispatcherServlet, HttpServletRequest, PathVariable, RequestParam, Interceptors, Filter


**DispatcherServlet** is the central **Front Controller** in **Spring MVC**. It receives all incoming HTTP requests and manages the request processing flow.

How it works:

1. Client sends an HTTP request.
2. **DispatcherServlet** receives the request.
3. It finds the appropriate **Controller**.
4. It calls the controller method.
5. The controller processes the request and returns a result.
6. **DispatcherServlet** sends the response back to the client.

Example:

```java
@RestController
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "User List";
    }
}
```

When `/users` is called, the request first reaches **DispatcherServlet**, which then forwards it to the `getUsers()` method.

**Key Point:**
**DispatcherServlet** is the heart of **Spring MVC**. It handles incoming requests, routes them to the correct controller, and returns the response to the client.



**HttpServletRequest** is an object that represents an incoming **HTTP request**. It provides access to all request data sent by the client.

Using **HttpServletRequest**, we can access:

* **Request parameters**
* **HTTP headers**
* **Request URL**
* **HTTP method** (GET, POST, PUT, DELETE)
* **Session information**

Example:

```java
@GetMapping("/hello")
public String hello(HttpServletRequest request) {

    String name = request.getParameter("name");

    return "Hello " + name;
}
```

Request:

```text
/hello?name=John
```

Response:

```text
Hello John
```


**@PathVariable** is used to get a value directly from the **URL path**.

Example URL:

```java
@GetMapping("/users/{id}")
public String getUser(@PathVariable Long id) {
    return "User ID: " + id;
}
```

Request:

```text
/users/10
```

Result:

```text
User ID: 10
```

In this example, **10** is taken from the URL and assigned to the variable **id**.

**Key Point:**
Use **@PathVariable** when the value is part of the URL path and identifies a specific resource.


**@RequestParam**

**@RequestParam** is used to get a value from a **query parameter** in the URL.

Example:

```java
@GetMapping("/users")
public String getUser(@RequestParam String name) {
    return "User: " + name;
}
```

Request:

```text
/users?name=John
```

Result:

```text
User: John
```

In this example, **John** is taken from the query parameter **name**.

**Key Point:**
Use **@RequestParam** when the value is sent as a query parameter after the `?` in the URL.


**Difference Between @PathVariable and @RequestParam**

**@PathVariable**

* Value comes from the **URL path**
* Used to identify a specific resource
* Example: `/users/10`

**@RequestParam**

* Value comes from the **query string**
* Used for filtering, searching, sorting, or optional inputs
* Example: `/users?name=John`


**Filter**

A **Filter** is part of the **Servlet API** and executes **before the request reaches the DispatcherServlet**. It can intercept and process every HTTP request and response.

Common use cases:

* **Logging**
* **Authentication**
* **CORS handling**
* **Header modification**
* **Request/Response preprocessing**

Example:

```java
@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Request received");

        chain.doFilter(request, response);
    }
}
```

**Key Point:**
A **Filter** works at the **Servlet Container** level and is executed before Spring MVC processes the request.



**Interceptor** is a feature of **Spring MVC** that executes between the **DispatcherServlet** and the **Controller**.

It allows us to execute logic:

* Before the controller method (`preHandle`)
* After the controller method (`postHandle`)
* After the complete request is finished (`afterCompletion`)

Example:

```java
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        System.out.println("Before Controller");
        return true;
    }
}
```

**Key Point:**
An **Interceptor** is Spring-specific and is mainly used for controller-related processing.


**Difference Between Filter and Interceptor**

| **Filter**                        | **Interceptor**                                        |
| --------------------------------- | ------------------------------------------------------ |
| Part of **Servlet API**           | Part of **Spring MVC**                                 |
| Runs before **DispatcherServlet** | Runs after **DispatcherServlet** and before Controller |
| Works for all HTTP requests       | Works for Spring MVC requests                          |
| Can modify Request and Response   | Mainly used for application logic                      |
| Independent of Spring             | Spring-specific                                        |



## 31. Top 50 Spring Boot Annotations 

| #  | Annotation                 | Purpose                                                                                                                                     |
| -- | -------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------- |
| 1  | `@SpringBootApplication`   | Marks the main class of a Spring Boot app; combines configuration, auto-configuration, and component scanning to bootstrap the application. |
| 2  | `@EnableAutoConfiguration` | Automatically configures Spring beans based on classpath dependencies, reducing manual setup.                                               |
| 3  | `@ComponentScan`           | Scans specified packages to detect and register Spring components (beans) automatically.                                                    |
| 4  | `@Configuration`           | Indicates a class contains bean definitions and acts as a source of Spring configuration.                                                   |
| 5  | `@Bean`                    | Declares a method that returns an object managed as a Spring bean in the application context.                                               |
| 6  | `@Component`               | Marks a class as a generic Spring-managed component for dependency injection.                                                               |
| 7  | `@Autowired`               | Automatically injects dependencies by type into constructors, fields, or setters.                                                           |
| 8  | `@Qualifier`               | Specifies which bean to inject when multiple beans of the same type exist.                                                                  |
| 9  | `@Primary`                 | Designates a bean as the default choice when multiple candidates are available.                                                             |
| 10 | `@Value`                   | Injects values from properties files, environment variables, or expressions into fields.                                                    |


**✅ Stereotype / Layer Annotations**

| #  | Annotation        | Purpose                                                                                      |
| -- | ----------------- | -------------------------------------------------------------------------------------------- |
| 11 | `@Controller`     | Handles incoming web requests and typically returns a view (like JSP/Thymeleaf).             |
| 12 | `@RestController` | Combines `@Controller` and `@ResponseBody` to return JSON/XML responses directly.            |
| 13 | `@Service`        | Marks a class as a service layer component containing business logic.                        |
| 14 | `@Repository`     | Indicates a data access component and enables exception translation for database operations. |


**✅ REST API Annotations**

| #  | Annotation        | Purpose                                                                            |
| -- | ----------------- | ---------------------------------------------------------------------------------- |
| 15 | `@RequestMapping` | Maps HTTP requests to handler methods with configurable URL, method, headers, etc. |
| 16 | `@GetMapping`     | Shortcut for handling HTTP GET requests for retrieving data.                       |
| 17 | `@PostMapping`    | Handles HTTP POST requests for creating resources.                                 |
| 18 | `@PutMapping`     | Handles HTTP PUT requests for updating entire resources.                           |
| 19 | `@DeleteMapping`  | Handles HTTP DELETE requests to remove resources.                                  |
| 20 | `@PatchMapping`   | Handles partial updates of a resource using HTTP PATCH.                            |
| 21 | `@PathVariable`   | Binds URI template variables from the URL to method parameters.                    |
| 22 | `@RequestParam`   | Extracts query parameters from the request URL into method parameters.             |
| 23 | `@RequestBody`    | Converts HTTP request body (JSON/XML) into a Java object.                          |
| 24 | `@ResponseBody`   | Converts Java object into HTTP response body (JSON/XML).                           |
| 25 | `@ResponseStatus` | Sets the HTTP response status code for a method or exception.                      |


**✅ Spring Data JPA**

| #  | Annotation        | Purpose                                                                       |
| -- | ----------------- | ----------------------------------------------------------------------------- |
| 26 | `@Entity`         | Marks a class as a JPA entity mapped to a database table.                     |
| 27 | `@Table`          | Specifies the table name and schema details for an entity.                    |
| 28 | `@Id`             | Defines the primary key of an entity.                                         |
| 29 | `@GeneratedValue` | Specifies strategy for auto-generating primary key values.                    |
| 30 | `@Column`         | Customizes column mapping such as name, length, nullable, unique constraints. |
| 31 | `@Transient`      | Excludes a field from being persisted to the database.                        |
| 32 | `@OneToOne`       | Defines a one-to-one relationship between two entities.                       |
| 33 | `@OneToMany`      | Defines a one-to-many relationship between entities.                          |
| 34 | `@ManyToOne`      | Defines a many-to-one relationship between entities.                          |
| 35 | `@ManyToMany`     | Defines a many-to-many relationship using a join table.                       |


**✅ Transaction & AOP**

| #  | Annotation                     | Purpose                                                                             |
| -- | ------------------------------ | ----------------------------------------------------------------------------------- |
| 36 | `@Transactional`               | Ensures methods execute within a database transaction with commit/rollback support. |
| 37 | `@EnableTransactionManagement` | Enables Spring’s annotation-driven transaction management.                          |
| 38 | `@Aspect`                      | Declares a class for cross-cutting concerns like logging, security, or auditing.    |
| 39 | `@Before`                      | Executes advice before the target method runs.                                      |
| 40 | `@After`                       | Executes advice after the target method completes (regardless of outcome).          |


**✅ Exception Handling**

| #  | Annotation              | Purpose                                                                 |
| -- | ----------------------- | ----------------------------------------------------------------------- |
| 41 | `@ControllerAdvice`     | Provides global exception handling and shared logic across controllers. |
| 42 | `@RestControllerAdvice` | Same as `@ControllerAdvice` but returns REST responses (JSON).          |
| 43 | `@ExceptionHandler`     | Handles specific exceptions and defines custom error responses.         |


**✅ Validation**

| #  | Annotation  | Purpose                                                                                |
| -- | ----------- | -------------------------------------------------------------------------------------- |
| 44 | `@Valid`    | Triggers validation of request body or method parameters using validation annotations. |
| 45 | `@NotNull`  | Ensures a field is not null during validation.                                         |
| 46 | `@NotBlank` | Ensures a string is not null, empty, or only whitespace.                               |
| 47 | `@Size`     | Validates size constraints for strings, collections, or arrays.                        |
| 48 | `@Email`    | Validates that a field contains a properly formatted email address.                    |


**✅ Async & Scheduling**

| #  | Annotation     | Purpose                                                   |
| -- | -------------- | --------------------------------------------------------- |
| 49 | `@EnableAsync` | Enables asynchronous method execution in the application. |
| 50 | `@Async`       | Executes a method in a separate thread asynchronously.    |



# ✅ 19. Java Microservices 

## 1. What are microservices?

**Microservices** is an architectural style where a large application is divided into **small, independent, and loosely coupled services**. Each service is responsible for a **single business function** and can be developed, deployed, and scaled independently.

**Key Features**

* **Independent services** with a single responsibility.
* **Loosely coupled** and communicate through **REST APIs**, **gRPC**, or **message queues**.
* Each service can have its own **database**.
* Services can be **developed, deployed, and scaled independently**.
* Supports **continuous deployment** and **fault isolation**.

**How it Works**

* The application is split into multiple small services such as **User Service**, **Order Service**, and **Payment Service**.
* Each service runs as a separate application.
* Services communicate with each other over the network using APIs or messaging systems.
* If one service fails, the others can continue working.

**Example Architecture**

```text
Client
   |
API Gateway
   |
-------------------------------
|          |          |
User     Order     Payment
Service  Service   Service
|          |          |
DB         DB         DB
```


**When to Use**

* Large and complex applications with multiple business modules.
* Applications requiring **high scalability** and **frequent deployments**.
* Projects where multiple teams work independently.
* Cloud-native and distributed systems.

**Monolithic vs Microservices**

| **Feature**        | **Monolithic**                     | **Microservices**                      |
| ------------------ | ---------------------------------- | -------------------------------------- |
| **Architecture**   | Single application                 | Multiple independent services          |
| **Deployment**     | Entire application                 | Individual service                     |
| **Scalability**    | Scale whole application            | Scale only required service            |
| **Database**       | Usually one shared database        | Each service can have its own database |
| **Failure Impact** | One issue can affect the whole app | Failure is isolated to one service     |

**Simple Spring Boot Example**

```java id="g5m8xq"
@RestController
public class UserController {

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable int id) {
        return "User ID: " + id;
    }
}
```

This can be a separate **User Microservice**. Similarly, **Order** and **Payment** services can be separate Spring Boot applications communicating through REST APIs.

**Common Technologies Used**

* **Spring Boot** for service development.
* **Spring Cloud** for distributed system features.
* **Eureka** for service discovery.
* **API Gateway** for request routing.
* **Kafka** or **RabbitMQ** for asynchronous communication.
* **Docker** and **Kubernetes** for containerization and orchestration.


**Advantages**

* **Independent Deployment**
* **Independent Scaling**
* **Better Fault Isolation**
* **Technology Flexibility**
* **Faster Development**
* **Easy Maintenance**
* **Improved Availability**

**Disadvantages**

* **Distributed System Complexity**
* **Network Latency**
* **Complex Data Management**
* **More Monitoring and Logging**
* **Inter-Service Communication Overhead**

**Common Interview Follow-up Questions**

**1. How do Microservices communicate?**

* **REST APIs**
* **Feign Client**
* **gRPC**
* **Kafka**
* **RabbitMQ**

**2. Why does each Microservice have its own database?**

To ensure **loose coupling**, **independent deployment**, and **independent scalability**. One service should not directly access another service's database.

**4. What are common Spring Cloud components used in Microservices?**

* **API Gateway**
* **Service Discovery (Eureka)**
* **Config Server**
* **OpenFeign**
* **Circuit Breaker (Resilience4j)**
* **Distributed Tracing (Zipkin/Micrometer)**


## 2. Monolithic vs Microservices Architecture

**Monolithic** and **Microservices** are two different software architecture styles. In a **Monolithic architecture**, the entire application is built and deployed as a **single unit**, while in **Microservices**, the application is divided into **small, independent services**.

| **Feature**          | **Monolithic**                             | **Microservices**                                 |
| -------------------- | ------------------------------------------ | ------------------------------------------------- |
| **Architecture**     | Single, unified application                | Collection of small independent services          |
| **Deployment**       | Entire application is deployed together    | Each service is deployed independently            |
| **Scalability**      | Scale the whole application                | Scale only the required service                   |
| **Codebase**         | Single codebase                            | Multiple smaller codebases                        |
| **Database**         | Usually one shared database                | Each service can have its own database            |
| **Technology Stack** | Generally one technology stack             | Different services can use different technologies |
| **Failure Impact**   | Failure can affect the entire application  | Failure is isolated to one service                |
| **Development**      | Simpler for small projects                 | Better for large and complex projects             |
| **Maintenance**      | Becomes difficult as the application grows | Easier because services are independent           |

**Key Features**

* **Monolithic:** Simple architecture, easy to develop and deploy initially.
* **Microservices:** Loosely coupled, independently deployable, and highly scalable.
* **Microservices** communicate using **REST APIs**, **gRPC**, or **message queues**.

**How it Works**

* In a **Monolithic application**, modules like User, Order, and Payment are all part of one application.
* In **Microservices**, each module runs as a separate service with its own logic and possibly its own database.

**Architecture Example**

```text
Monolithic:
+--------------------------------------+
| User | Order | Payment | Inventory   |
+--------------------------------------+
           Single Application
```

```text
Microservices:
Client
   |
API Gateway
   |
---------------------------------
|        |         |            |
User   Order    Payment    Inventory
Service Service  Service     Service
|        |         |            |
DB       DB        DB           DB
```


**When to Use**

* Choose **Monolithic** for:

  * Small or startup projects.
  * Simple business logic.
  * Small development teams.
* Choose **Microservices** for:

  * Large and complex applications.
  * Multiple independent teams.
  * High scalability and frequent deployments.
  * Cloud-native and distributed systems.

**Code Example**

**Monolithic (Single Application)**

```java
@RestController
public class AppController {

    @GetMapping("/user")
    public String getUser() {
        return "User Service";
    }

    @GetMapping("/order")
    public String getOrder() {
        return "Order Service";
    }
}
```

**Microservices (Separate Applications)**

```java
@RestController
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "User Microservice";
    }
}
```

```java
@RestController
public class OrderController {

    @GetMapping("/orders")
    public String getOrders() {
        return "Order Microservice";
    }
}
```

Each controller can run as a separate **Spring Boot** application and communicate through **REST APIs**.



## 3. How microservices communicate with each other?


In our system, microservices mainly communicated using **REST APIs over HTTP**.
For synchronous communication, we used **Feign Client** with service discovery through **Eureka**.

For asynchronous communication, especially for event-based workflows, we used **Kafka**. This helped us reduce tight coupling and improve scalability.

Microservices communicate in two ways:

1. **Synchronous (REST, Feign, WebClient)** – Request/Response model
2. **Asynchronous (Kafka, RabbitMQ)** – Event-driven model

**Synchronous Communication**


**Using Feign Client**

```java
// `- Step 1: Configure Feign Client`
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

// `- Step 2: Enable Feign Client`
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}

// `- Step 3: Create Feign Client Interface`
@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable int id);
}

// `- Step 4: use in controller`
@Service
public class OrderService {

    @Autowired
    private UserClient userClient;

    public Order getOrder(int userId) {
        User user = userClient.getUserById(userId);
        return new Order(user);
    }
}
```

**Using Spring RestTemplate (Spring Boot - Legacy, Sequential Calls (Blocking))**

```java
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

// Step 1: Configure as bean
@Configuration
public class RestConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

// Step 2: Usage in service
@Service
public class ExternalApiService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    // GET request
    public String getData() {
        String url = "https://api.example.com/data";
        return restTemplate.getForObject(url, String.class);
    }
    
    // GET with headers
    public String getDataWithHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
            "https://api.example.com/data",
            HttpMethod.GET,
            entity,
            String.class
        );
        return response.getBody();
    }
    
    // POST request
    public String postData(Map<String, Object> requestBody) {
        String url = "https://api.example.com/api";
        return restTemplate.postForObject(url, requestBody, String.class);
    }
    
    // POST with response entity
    public ResponseEntity<String> postWithResponse(Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity("https://api.example.com/api", entity, String.class);
    }
}
```

**Spring WebClient (Spring Boot 5+ - - Sequential Calls , Reactive, Recommended)**

```java
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

// Configure WebClient bean
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .baseUrl("https://api.example.com")
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}

// Usage in service
@Service
public class ExternalApiService {
    
    @Autowired
    private WebClient webClient;
    
    // GET request
    public Mono<String> getData() {
        return webClient.get()
            .uri("/data")
            .header("Authorization", "Bearer " + token)
            .retrieve()
            .bodyToMono(String.class);
    }
    
    // GET with response body object
    public Mono<User> getUser(String id) {
        return webClient.get()
            .uri("/users/{id}", id)
            .retrieve()
            .bodyToMono(User.class);
    }
    
    // POST request
    public Mono<String> postData(User user) {
        return webClient.post()
            .uri("/users")
            .body(Mono.just(user), User.class)
            .retrieve()
            .bodyToMono(String.class);
    }
    
    // POST with headers
    public Mono<ResponseEntity<Void>> postDataWithHeaders(User user) {
        return webClient.post()
            .uri("/users")
            .header("Authorization", "Bearer " + token)
            .body(Mono.just(user), User.class)
            .retrieve()
            .toBodilessEntity();
    }
    
    // With error handling
    public Mono<String> getDataWithErrors() {
        return webClient.get()
            .uri("/data")
            .retrieve()
            .onStatus(HttpStatusCode::isError, 
                response -> response.bodyToMono(String.class)
                    .flatMap(error -> Mono.error(new RuntimeException(error))))
            .bodyToMono(String.class);
    }
}
```


**Asynchronous Communication**
```java
// Producer
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;
public void sendMessage() {
    kafkaTemplate.send("order-topic", "Order Created");
}
```

```java
// Consumer
@KafkaListener(topics = "order-topic", groupId = "group1")
public void consume(String message) {
    System.out.println("Received: " + message);
}
```

## 4. What are Microservices Design Patterns?


**Microservices Design Patterns** are **proven architectural solutions** used to solve common challenges in **microservices**, such as **service communication**, **fault tolerance**, **data consistency**, **service discovery**, and **scalability**.

**Key Features**

* **Loose Coupling**
* **High Availability**
* **Fault Tolerance**
* **Scalability**
* **Independent Deployment**
* **Resilience**

**Common Microservices Design Patterns**

| **Pattern**                   | **Purpose**                                                  |
| ----------------------------- | ------------------------------------------------------------ |
| **API Gateway**               | A single entry point that routes client requests to appropriate microservices                   |
| **Service Discovery**         | A mechanism that automatically detects and locates available service instances.                        |
| **Circuit Breaker**           | A pattern that stops calls to a failing service to prevent system-wide failure.           |
| **Saga Pattern**              | A way to manage distributed transactions using a sequence of local transactions.      |
| **CQRS**                      | A pattern that separates read operations from write operations.                  |
| **Event-Driven Architecture** | Services communicate using **events**                        |
| **Database per Service**      | Each microservice has its own dedicated database for data isolation.                      |
| **Bulkhead**                  | A pattern that isolates resources to prevent one service failure from affecting others.      |
| **Retry Pattern**             | Retries failed requests automatically                        |
| **Strangler Pattern**         | Gradually migrates a monolithic application to microservices |

**How It Works**

```text id="kgzgka"
Client
   │
   ▼
API Gateway
   │
   ├────────► User Service
   ├────────► Order Service
   └────────► Payment Service
                 │
                 ▼
          Circuit Breaker
                 │
        Success / Fallback
```

**When to Use**

* **Microservices Architecture**
* **Cloud-Native Applications**
* **Distributed Systems**
* **Large-Scale Applications**
* Applications requiring **high availability** and **fault tolerance**

**Spring Boot Example (Circuit Breaker using Resilience4j)**

```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
public String processPayment() {
    return restTemplate.getForObject(
        "http://payment-service/pay",
        String.class
    );
}

public String fallback(Exception ex) {
    return "Payment service is unavailable.";
}
```

**Key Advantages:**
- **Independent deployment:** Deploy services separately
- **Technology diversity:** Different tech stacks per service
- **Scalability:** Scale individual services based on demand
- **Fault isolation:** Failure in one service doesn't crash entire system
- **Team autonomy:** Small teams own complete services
- **Faster development:** Parallel development of services

**Major Challenges:**
- **Distributed system complexity:** Network calls, latency, failures
- **Data consistency:** Managing transactions across services
- **Service communication:** Inter-service communication overhead
- **Monitoring and debugging:** Tracing requests across services
- **Deployment complexity:** Managing multiple services
- **Testing challenges:** Integration and end-to-end testing


**Example:**
```java
// 1. API Gateway Pattern
@RestController
public class ApiGatewayController {
    @Autowired
    private UserClient userClient;
    
    @Autowired
    private OrderClient orderClient;
    
    @GetMapping("/api/user-orders/{userId}")
    public UserOrdersResponse getUserWithOrders(@PathVariable Long userId) {
        User user = userClient.getUser(userId);
        List<Order> orders = orderClient.getOrdersByUser(userId);
        return new UserOrdersResponse(user, orders);
    }
}

// 2. Service Discovery Pattern, Commonly implemented using Netflix Eureka.
@EnableEurekaClient
@SpringBootApplication
public class OrderServiceApplication {
}
restTemplate.getForObject("http://PAYMENT-SERVICE/pay", String.class);

// 3. Circuit Breaker Pattern (with Resilience4j)
@Service
public class OrderService {
    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public Payment processPayment(PaymentRequest request) {
        return paymentClient.process(request);
    }
    
    public Payment paymentFallback(PaymentRequest request, Exception e) {
        return new Payment("PENDING", "Payment service unavailable");
    }
}

// 4. Saga Pattern (Choreography)
@Service
public class OrderSagaService {
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    
    public void createOrder(Order order) {
        orderRepository.save(order);
        kafkaTemplate.send("order-created", new OrderEvent(order.getId()));
    }
    
    @KafkaListener(topics = "payment-failed")
    public void handlePaymentFailed(PaymentEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).get();
        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }
}

// 5. CQRS (Command Query Responsibility Segregation)
// Command (Write)
@PostMapping("/orders")
public void createOrder(@RequestBody Order order) { }

// Query (Read)
@GetMapping("/orders/{id}")
public Order getOrder(@PathVariable Long id) { }

// 6. Database per Service
# order-service application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/orderdb

// 7. Bulkhead Pattern
// Example (Resilience4j)
@Bulkhead(name = "paymentService", type = Bulkhead.Type.THREADPOOL)
public String processPayment() {
    return "Processing payment";
}
```


## 5. What is API Gateway and predicates?

An **API Gateway** is a **single entry point** for all client requests in a **Microservices** architecture. It receives the request, applies rules, and forwards it to the appropriate microservice.

**Predicates** are **conditions or matching rules** used by the API Gateway to decide **which request should be routed to which service**.

**Key Features**

* **API Gateway**

  * Single entry point for all APIs.
  * Routes requests to the correct microservice.
  * Can handle **authentication**, **authorization**, **load balancing**, **logging**, and **rate limiting**.
* **Predicates**

  * Define routing conditions.
  * Match requests based on **Path**, **Method**, **Header**, **Host**, **Query Parameter**, etc.
  * Used before forwarding the request.

**How it Works**

1. The client sends a request to the **API Gateway**.
2. The gateway checks the configured **predicates**.
3. If a predicate matches, the request is routed to the corresponding microservice.
4. The microservice processes the request and returns the response through the gateway.

**Example Flow**

```text id="u6knw2"
Client
   |
API Gateway
   |
   |-- /users/**  ----> User Service
   |
   |-- /orders/** ----> Order Service
   |
   |-- /payment/** ---> Payment Service
```


**When to Use**

* In **Microservices architectures** with multiple backend services.
* When a single entry point for APIs is needed.
* When implementing centralized **security**, **monitoring**, or **request routing**.

**Common Predicate Types**

| **Predicate** | **Purpose**                           |
| ------------- | ------------------------------------- |
| **Path**      | Matches the URL path                  |
| **Method**    | Matches HTTP methods like GET or POST |
| **Header**    | Matches request headers               |
| **Host**      | Matches the host name                 |
| **Query**     | Matches query parameters              |

**Spring Cloud Gateway Example**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

**`application.yml`**

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: http://localhost:8081
          predicates:
            - Path=/users/**

        - id: order-service
          uri: http://localhost:8082
          predicates:
            - Path=/orders/**
```

```java
// API Gateway with Spring Cloud Gateway
@SpringBootApplication
public class ApiGatewayApplication {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/users/**")
                .uri("lb://user-service"))
            .route("order-service", r -> r.path("/orders/**")
                .uri("lb://order-service"))
            .build();
    }
}

```

For a typical Java/Spring Boot microservices application, commonly used API gateways are:

| Gateway              | Mostly Used With            |
| -------------------- | --------------------------- |
| Spring Cloud Gateway | Spring Boot microservices   |
| AWS API Gateway      | AWS cloud                   |
| Netflix Zuul         | Older Spring Cloud projects |
| Kong Gateway         | Cloud-native systems        |
| NGINX                | Reverse proxy/API gateway   |
| Azure API Management | Azure cloud                 |


**Advantages of API Gateway**

| Advantage                 | Explanation                         |
| ------------------------- | ----------------------------------- |
| Single Entry Point        | Easier client communication         |
| Better Security           | Centralized authentication          |
| Reduced Client Complexity | Client doesn't manage many services |
| Load Balancing            | Traffic distribution                |
| Centralized Logging       | Easier monitoring                   |
| Rate Limiting             | Prevent excessive requests          |
| API Aggregation           | Combine multiple service responses  |
| Version Management        | Easy API versioning                 |

**Disadvantages of API Gateway**

| Disadvantage            | Explanation                             |
| ----------------------- | --------------------------------------- |
| Single Point of Failure | If gateway fails, all services affected |
| Additional Latency      | One extra network hop                   |
| Complex Maintenance     | Gateway rules become complicated        |
| Bottleneck Risk         | Heavy traffic can overload gateway      |
| Deployment Complexity   | Requires scaling and monitoring         |


## 6. What is service discovery Design Pattern?

**Service Discovery** is a **Microservices Design Pattern** that allows services to **automatically find and communicate** with each other **without hardcoding IP addresses or URLs**.

Instead of calling a fixed endpoint, a service asks the **Service Registry** for the location of another service.

**Key Features**

* **Automatic Service Registration**
* **Dynamic Service Discovery**
* **Load Balancing**
* **Supports Auto Scaling**
* **No Hardcoded IP Addresses**
* **High Availability**

**How It Works**

1. A microservice starts.
2. It **registers** itself with a **Service Registry** (e.g., **Eureka**, **Consul**).
3. Another service requests the location of the target service from the registry.
4. The registry returns an available service instance.
5. The requesting service communicates with the selected instance.


**Architecture Flow**

```text
Order Service
      |
      | Request USER-SERVICE
      |
Service Registry (Eureka)
      |
      | Returns instance
      |
User Service (Instance 1)
User Service (Instance 2)
```

**Example**

* **Payment Service** starts and registers itself with **Eureka**.
* **Order Service** needs to call **Payment Service**.
* Instead of using a fixed URL, **Order Service** asks **Eureka** for the available **Payment Service** instance.
* **Eureka** returns the service address, and **Order Service** sends the request.

**When to Use**

* **Microservices Architecture**
* **Cloud Applications**
* **Kubernetes**
* Applications with **multiple service instances**
* Systems requiring **auto scaling** and **load balancing**

**Spring Boot Example**

**Enable Eureka Client**

**Add Dependency**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

```java
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
```

**Register with Eureka**

**application.yml**

```yaml
spring:
  application:
    name: ORDER-SERVICE

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

**Call Another Service**

```java
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/orders")
    public String getOrder() {
        return restTemplate.getForObject(
                "http://USER-SERVICE/users",
                String.class);
    }
}
```

**Load Balanced RestTemplate**

```java
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

Here, **`payment-service`** is the **service name**, not an IP address.

**Advantages**

* **Dynamic Service Discovery**
* **Supports Auto Scaling**
* **Built-in Load Balancing**
* **No Manual Configuration**
* **Improves High Availability**

**Disadvantages**

* Requires a **Service Registry**
* Adds **infrastructure complexity**
* Registry becomes a **critical component** (usually deployed with multiple instances)


**Common Interview Follow-up**

**Q. What is a Service Registry?**

A **Service Registry** is a central server where services **register themselves**, and other services **discover** them.

Examples:

* **Netflix Eureka**
* **Consul**
* **Nacos**
* **Apache ZooKeeper**

**Q. What is the difference between Client-side and Server-side Service Discovery?**

| **Client-side Discovery**                       | **Server-side Discovery**                                      |
| ----------------------------------------------- | -------------------------------------------------------------- |
| Client asks the registry for service instances  | Client sends request to a Load Balancer                        |
| Client performs load balancing                  | Load Balancer performs load balancing                          |
| Example: **Eureka + Spring Cloud LoadBalancer** | Example: **Kubernetes Service**, **AWS Elastic Load Balancer** |


**Q: Why do we need Service Discovery?**

In **Microservices**, service instances are created and removed dynamically due to **scaling** and **failures**, so their IP addresses change frequently. **Service Discovery** eliminates the need for hardcoded addresses by allowing services to **register** and **discover** each other automatically.

**Q: What is the difference between Eureka and Load Balancer?**

| **Eureka**                              | **Load Balancer**                                   |
| --------------------------------------- | --------------------------------------------------- |
| **Finds** available service instances.  | **Distributes** requests among available instances. |
| Acts as a **Service Registry**.         | Acts as a **traffic distributor**.                  |
| Example: **Netflix Eureka**, **Consul** | Example: **Spring Cloud LoadBalancer**, **NGINX**   |



## 6. What is circuit breaker pattern?

A **Circuit Breaker** is a **design pattern** used in **Microservices** to prevent repeated calls to a failing service. It detects failures and temporarily stops requests to avoid overloading the unavailable service.

**Key Features**

* Prevents **cascade failures** in distributed systems.
* Improves **fault tolerance** and **system stability**.
* Supports **fallback methods** when a service is unavailable.
* Automatically recovers when the failed service becomes healthy.
* Commonly implemented using **Resilience4j** or **Hystrix** (older).

**How it Works**

A Circuit Breaker has **three states**:

| **State**     | **Description**                                                                                                          |
| ------------- | ------------------------------------------------------------------------------------------------------------------------ |
| **Closed**    | Normal state. Requests are sent to the target service.                                                                   |
| **Open**      | Too many failures occurred. Requests are blocked, and a fallback response is returned.                                   |
| **Half-Open** | After a waiting period, a few test requests are allowed. If they succeed, the circuit closes; otherwise, it opens again. |

**Flow**

```text id="j9p4qs"
Closed  -->  (Many Failures)  -->  Open
   ^                                |
   |                                |
   +---- (Success in Half-Open) <---+
                Half-Open
```


**When to Use**

* In **Microservices** where services communicate over the network.
* When calling external APIs or third-party services.
* In systems where temporary failures or network issues are common.

**Spring Boot with Resilience4j Example**

```java id="cb9w2m"
@RestController
public class UserController {

    @CircuitBreaker(name = "userService", fallbackMethod = "fallback")
    @GetMapping("/users")
    public String getUsers() {
        // Call to another microservice
        throw new RuntimeException("Service Down");
    }

    public String fallback(Exception ex) {
        return "User Service is temporarily unavailable.";
    }
}
```

**How the Above Works**

* The `getUsers()` method calls another service.
* If failures exceed the configured threshold, the **Circuit Breaker** moves to the **Open** state.
* New requests do not call the failed service; instead, the **`fallback()`** method returns a default response.
* After a timeout, the breaker enters **Half-Open** state and checks whether the service has recovered.

**Common Configuration (application.yml)**

```yaml id="7khw6y"
resilience4j:
  circuitbreaker:
    instances:
      userService:
        failureRateThreshold: 50
        slidingWindowSize: 10
        waitDurationInOpenState: 10s
```

## 7. Circuitbreaker - How do you Handle Failures in Microservices?

**Circuit Breaker** is a **Microservices Resilience Design Pattern** that **prevents repeated calls to a failing service**. Instead of continuously sending requests to an unavailable service, it **temporarily stops the requests** and returns a **fallback response** or an error. This prevents **cascading failures** and improves system stability.

**Key Features**

* **Prevents Cascading Failures**
* **Stops Repeated Calls to Failed Services**
* **Automatic Recovery**
* **Fallback Support**
* **Improves System Availability**
* **Works Well with Retry and Bulkhead**

**How It Works**

A Circuit Breaker has **three states**:

**1. Closed State (Normal)**

* All requests are allowed.
* Requests go to the target service.
* Failure count is monitored.

**2. Open State (Failure)**

* If failures exceed a configured threshold, the circuit **opens**.
* New requests are **blocked immediately**.
* A **fallback response** or error is returned.

**3. Half-Open State (Recovery)**

* After a waiting period, a few test requests are allowed.
* If they succeed, the circuit **closes**.
* If they fail, the circuit **opens** again.

**Architecture Flow**

```text
           Request
              │
              ▼
      Circuit Breaker
              │
     ┌────────┴────────┐
     │                 │
 Circuit Closed   Circuit Open
     │                 │
     ▼                 ▼
Payment Service    Fallback/Error
```

**Example**

An **Order Service** calls the **Payment Service**.

* Payment Service becomes unavailable.
* Several requests fail.
* The **Circuit Breaker** opens.
* New requests are blocked immediately.
* Users receive a **fallback response** like **"Payment service is temporarily unavailable."**
* After a configured wait time, a few requests are tested.
* If successful, the circuit closes and normal traffic resumes.

**When to Use**

* **Microservices Communication**
* **External API Calls**
* **Cloud Applications**
* Services with **frequent network failures**
* Systems requiring **high availability**

**Spring Boot Example (Resilience4j Circuit Breaker)**

**Dependency**

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
```

**Circuit Breaker Annotation**

```java
@Service
public class PaymentService {

    @CircuitBreaker(
        name = "paymentService",
        fallbackMethod = "fallbackPayment"
    )
    public String processPayment() {
        return restTemplate.getForObject(
            "http://payment-service/pay",
            String.class
        );
    }

    public String fallbackPayment(Exception ex) {
        return "Payment service is temporarily unavailable.";
    }
}
```

**application.yml**

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        failureRateThreshold: 50
        waitDurationInOpenState: 10s
        slidingWindowSize: 10
```

If **50%** of the last **10 requests** fail, the circuit opens for **10 seconds** before testing recovery.

**Advantages**

* **Prevents Cascading Failures**
* **Improves Fault Tolerance**
* **Reduces Unnecessary Requests**
* **Supports Automatic Recovery**
* **Improves User Experience** with fallback responses

**Disadvantages**

* Additional **configuration**
* Requires a good **fallback strategy**
* Incorrect thresholds may block healthy services or delay recovery


**Common Interview Follow-up**

**Q: What are the three states of a Circuit Breaker?**

| **State**     | **Purpose**                                                            |
| ------------- | ---------------------------------------------------------------------- |
| **Closed**    | Normal operation. All requests are allowed.                            |
| **Open**      | Requests are blocked because the service is failing.                   |
| **Half-Open** | A few test requests are allowed to check if the service has recovered. |

**Q: What is a Fallback Method?**

A **Fallback Method** provides an **alternative response** when the target service is unavailable.

**Example:**

```java
public String fallbackPayment(Exception ex) {
    return "Payment service is temporarily unavailable.";
}
```

**Q: What is the difference between Retry and Circuit Breaker?**

| **Retry**                       | **Circuit Breaker**                       |
| ------------------------------- | ----------------------------------------- |
| **Retries** failed requests     | **Stops** requests to a failing service   |
| Used for **temporary failures** | Used for **continuous failures**          |
| May increase load if overused   | Reduces load on failing services          |
| Waits and retries               | Opens the circuit after repeated failures |

**Q: Can Retry and Circuit Breaker be used together?**

**Yes.**

* **Retry** handles **temporary failures** by retrying requests.
* If failures continue, the **Circuit Breaker** opens and blocks further requests.
* This combination provides **better resilience** and **fault tolerance**.


**1. Why use Circuit Breaker instead of Retry?**

* **Retry** is useful for **temporary failures**.
* **Circuit Breaker** prevents repeated calls to an unhealthy service, avoiding resource exhaustion.
* They are often used **together**.

**2. Which library did you use for Circuit Breaker?**

We used **Resilience4j** integrated with **Spring Boot**.

**3. Why can't @Transactional work across microservices?**

Because each microservice manages its **own database** and **local transaction**. Distributed transactions are avoided due to their complexity and performance overhead, so **Saga Pattern** or **event-driven communication** is preferred.


```java
// Steps 1: Add Dependencies (Maven)
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

// Step 2: Configure RestTemplate Bean
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
        // RestTemplate is now in maintenance mode. In modern Spring Boot, we prefer WebClient for non-blocking and reactive applications.
    }
}

// Inject RestTemplate in Your Client
@Component
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackUser")
    @Retry(name = "user-service")
    public User getUser(Long id) {
        return restTemplate.getForObject(
                "http://localhost:8081/users/" + id,
                User.class);
    }

    public User fallbackUser(Long id, Exception ex) {
        return new User(id, "Unknown User", "unknown@example.com");
    }
}

// application.yml Configuration
resilience4j:
  circuitbreaker:
    instances:
      user-service:
        registerHealthIndicator: true
        slidingWindowSize: 5
        minimumNumberOfCalls: 3
        failureRateThreshold: 50
        waitDurationInOpenState: 10s

  retry:
    instances:
      user-service:
        maxAttempts: 3
        waitDuration: 2s

management:
  endpoints:
    web:
      exposure:
        include: health,metrics
```

Configuration includes failure rate thresholds, wait durations, and retry attempts to control when circuits open and close.



## 8. What is Saga Pattern or How it handle payment failure?


**Saga** is a **Microservices Design Pattern** used to **manage distributed transactions** across multiple microservices. Instead of using one large transaction, it breaks the process into **multiple local transactions**. If any step fails, **compensating transactions** are executed to undo the completed steps.

**Key Features**

* **Distributed Transaction Management**
* **Local Transactions**
* **Compensating Transactions (Rollback)**
* **Event-Driven Communication**
* **Loose Coupling**
* **High Scalability**

**How It Works**

1. A business process starts (e.g., place an order).
2. Each microservice performs its own **local transaction**.
3. If successful, it triggers the next service.
4. If any service fails, **compensating transactions** are executed to undo the previous successful transactions.
5. The system reaches a **consistent state**.

**Architecture Flow**

```text
Order Service
      │
      ▼
Payment Service
      │
      ▼
Inventory Service
      │
      ▼
Shipping Service

If Inventory Fails:
Shipping Rollback (if completed)
Payment Refund
Order Cancel
```

**Example**

An **E-commerce Order Processing** system:

1. **Order Service** creates an order.
2. **Payment Service** deducts the payment.
3. **Inventory Service** reserves the product.
4. **Shipping Service** creates the shipment.

If **Inventory Service** fails:

* **Payment Service** refunds the payment.
* **Order Service** cancels the order.

This ensures **data consistency** without using a single distributed database transaction.

**When to Use**

* **Microservices Architecture**
* **Distributed Transactions**
* **Event-Driven Systems**
* **Cloud-Native Applications**
* Business processes involving **multiple services**

**Spring Boot Example (Event-Based Saga)**

**Order Service**

```java
@Service
public class OrderService {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void createOrder(Order order) {
        // Save order
        publisher.publishEvent(new OrderCreatedEvent(order.getId()));
    }
}
```

**Payment Service**

```java
@EventListener
public void processPayment(OrderCreatedEvent event) {
    // Deduct payment
}
```

**Compensating Transaction**

```java
public void refundPayment(Long orderId) {
    // Refund customer if later step fails
}
```

**Types of Saga**

**1. Choreography Saga**

* Services communicate using **events**.
* No central coordinator.
* Each service decides the next action.

**Flow**

```text
Order → Payment → Inventory → Shipping
```

**Advantages**

* **Simple**
* **Loosely Coupled**
* **Highly Scalable**

**Disadvantages**

* Harder to **track** and **debug**
* Complex event flow in large systems

**2. Orchestration Saga**

* A central **Saga Orchestrator** controls the workflow.
* It tells each service what to do next.
* Handles rollback when failures occur.

**Flow**

```text
Saga Orchestrator
       │
       ├── Order Service
       ├── Payment Service
       ├── Inventory Service
       └── Shipping Service
```

**Advantages**

* Easy to **monitor**
* Easier to **manage complex workflows**
* Centralized error handling

**Disadvantages**

* Additional **orchestrator service**
* Slightly more infrastructure

**Advantages**

* **No Distributed Database Transaction**
* **Better Scalability**
* **Fault Tolerance**
* **Data Consistency**
* **Supports Long-Running Business Processes**

**Disadvantages**

* More **complex** than a normal transaction
* Requires **compensating transactions**
* Event ordering can be challenging
* Debugging can be difficult


**Common Interview Follow-up**

**Q: Why can't we use a normal database transaction in Microservices?**

Each microservice has its **own database**, so a single **ACID transaction** cannot span multiple services. **Saga** provides **eventual consistency** by coordinating local transactions and rollback actions.

**Q: What is the difference between Choreography and Orchestration?**

| **Choreography**                      | **Orchestration**                               |
| ------------------------------------- | ----------------------------------------------- |
| **No central coordinator**            | **Central Saga Orchestrator** controls the flow |
| Services communicate using **events** | Services receive commands from the orchestrator |
| **Loosely coupled**                   | Easier to monitor and manage                    |
| Better for **simple workflows**       | Better for **complex business processes**       |

**Q: What is a Compensating Transaction?**

A **Compensating Transaction** is a **rollback action** that reverses a previously completed local transaction when a later step in the Saga fails.



If inventory fails, Saga will **refund payment and cancel the order**.

There are **two ways to implement Saga**:
- **Choreography**  – services communicate using events.
- **Orchestration** – a central service controls the workflow.

Order Created → Payment Done → Inventory Reserved
If **inventory fails → refund payment + cancel order**


**1. Choreography (Event-Based) – No Central Control**
```java
// Order → Payment → Inventory → (Failure → Compensation)

// Order Service
@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void createOrder() {
        System.out.println("Order Created");
        kafkaTemplate.send("order-created", "order123");
    }
}

// Payment Service (Listens to Order Event)
@KafkaListener(topics = "order-created")
public void processPayment(String orderId) {
    System.out.println("Payment Done for " + orderId);

    kafkaTemplate.send("payment-success", orderId);
}

// Inventory Service (Failure Scenario)
@KafkaListener(topics = "payment-success")
public void reserveInventory(String orderId) {
    System.out.println("Inventory Failed for " + orderId);

    kafkaTemplate.send("inventory-failed", orderId);
}

// Compensation (Refund + Cancel)
@KafkaListener(topics = "inventory-failed")
public void handleFailure(String orderId) {
    System.out.println("Refund Payment for " + orderId);
    System.out.println("Cancel Order for " + orderId);
}

```

**2. Using Orchestrator Service**
```java
// Flow: Orchestrator → Order → Payment → Inventory
@Service
public class OrderOrchestrator {

    @Autowired
    private RestTemplate restTemplate;

    public void placeOrder() {

        try {
            // Step 1: Create Order
            restTemplate.postForObject("http://order-service/create", null, String.class);

            // Step 2: Payment
            restTemplate.postForObject("http://payment-service/pay", null, String.class);

            // Step 3: Inventory
            restTemplate.postForObject("http://inventory-service/reserve", null, String.class);

            System.out.println("Order Completed");

        } catch (Exception e) {

            // Compensation logic
            restTemplate.postForObject("http://payment-service/refund", null, String.class);
            restTemplate.postForObject("http://order-service/cancel", null, String.class);

            System.out.println("Transaction Failed → Rolled Back");
        }
    }
}
```


## 9. What are CQRS principles?

**CQRS (Command Query Responsibility Segregation)** is an **architectural pattern** that separates **read operations (Queries)** from **write operations (Commands)**. Instead of using the same model for both reading and updating data, CQRS uses **different models and logic** for each responsibility.

**Key Features**

* Separates **Commands** (Write) and **Queries** (Read).
* Uses **different models** for updating and fetching data.
* Improves **scalability** and **performance**.
* Supports **Event-Driven Architecture** and **Microservices**.
* Often used with **Event Sourcing** (optional).

**How it Works**

1. A **Command** performs an action like **Create**, **Update**, or **Delete** data.
2. The command updates the **Write Database**.
3. An event may be published to synchronize the **Read Database**.
4. A **Query** retrieves data only from the **Read Database** without modifying it.

**Example Flow:**

```text
Client
   |
   |---- Command (Create Order) ----> Write Model ----> Write DB
   |
   |---- Query (Get Order) ---------> Read Model -----> Read DB
```

**When to Use**

* In **Microservices Architecture**.
* In applications with **high read and write traffic**.
* When **read and write operations have different performance requirements**.
* In systems using **Event-Driven Architecture** or **Event Sourcing**.

**Command vs Query**

| **Command**                        | **Query**                     |
| ---------------------------------- | ----------------------------- |
| Changes data                       | Reads data                    |
| Uses **POST**, **PUT**, **DELETE** | Uses **GET**                  |
| Updates the **Write Model**        | Reads from the **Read Model** |
| Returns success/failure            | Returns requested data        |

**Simple Java Example**

**Command Service (Write):**

```java
@Service
public class UserCommandService {

    public void createUser(User user) {
        // Save user to database
        System.out.println("User Created");
    }
}
```

**Query Service (Read):**

```java
@Service
public class UserQueryService {

    public User getUserById(Long id) {
        // Fetch user from database
        return new User(id, "John");
    }
}
```

**Controller:**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserCommandService commandService;

    @Autowired
    private UserQueryService queryService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        commandService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return queryService.getUserById(id);
    }
}
```

**Real-World Example**

In an **E-commerce Application**:

* **Command Side** handles operations like **Place Order**, **Update Inventory**, and **Process Payment**.
* **Query Side** handles operations like **View Order History** and **Search Products**.

The read database can be optimized for fast searches, while the write database focuses on data consistency.


```text
src/main/java/com/example/user

├── command
│   ├── controller
│   │   └── UserCommandController.java
│   ├── service
│   │   └── UserCommandService.java
│   ├── handler
│   │   └── CreateUserCommandHandler.java
│   └── model
│       └── CreateUserCommand.java
│
├── query
│   ├── controller
│   │   └── UserQueryController.java
│   ├── service
│   │   └── UserQueryService.java
│   ├── handler
│   │   └── GetUserQueryHandler.java
│   └── model
│       └── GetUserQuery.java
│
├── entity
│   └── User.java
│
├── repository
│   └── UserRepository.java
│
└── config
    └── ApplicationConfig.java
```



## 10. What is Event-Driven Architecture in Java?

**Event-Driven Architecture (EDA)** is an **architectural pattern** where different services communicate by **producing and consuming events**. Instead of calling each other directly, one service publishes an event, and other interested services react to it asynchronously.

**Key Features**

* **Asynchronous communication** between services.
* Uses **Events**, **Producers**, and **Consumers**.
* Services are **loosely coupled**.
* Supports **scalability** and **high availability**.
* Commonly implemented using **Kafka**, **RabbitMQ**, or other **message brokers**.

**How it Works**

1. A service performs an action (for example, an order is created).
2. It publishes an **event** like `OrderCreated`.
3. The **message broker** delivers the event.
4. Other services, such as **Payment Service**, **Inventory Service**, and **Notification Service**, consume the event and perform their tasks independently.

**Example Flow:**

```
Order Service
      |
      |  Publish: OrderCreated Event
      v
   Kafka / RabbitMQ
   /        |        \
  v         v         v
Payment  Inventory  Notification
Service   Service      Service
```


**When to Use**

* In **Microservices Architecture**.
* For **real-time applications** like order processing, notifications, and analytics.
* When multiple services need to react to the same event.
* When high **scalability** and **fault tolerance** are required.

**Simple Java Example with Spring Event**

```java
// Event
public class OrderCreatedEvent {
    private String orderId;

    public OrderCreatedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
```

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

// Event Publisher
@Autowired
private ApplicationEventPublisher publisher;

public void createOrder() {
    System.out.println("Order Created");
    publisher.publishEvent(new OrderCreatedEvent("ORD-101"));
}
```

```java
// Event Listener
@EventListener
public void handleOrderCreated(OrderCreatedEvent event) {
    System.out.println("Processing payment for: " + event.getOrderId());
}
```

**Real-World Example**

In an **E-commerce Application**:

* **Order Service** publishes an `OrderCreated` event.
* **Payment Service** processes the payment.
* **Inventory Service** updates stock.
* **Notification Service** sends an email or SMS.

All these services work **independently** without directly calling each other.


## 11. What is the Database per Service Design Pattern?

**Database per Service** is a **Microservices Design Pattern** where **each microservice owns its own database**. No other service can directly access that database. Services communicate through **APIs** or **events**, ensuring **loose coupling** and **independent deployment**.

**Key Features**

* **Each Service Owns Its Database**
* **No Shared Database**
* **Loose Coupling**
* **Independent Deployment**
* **Technology Flexibility** (SQL or NoSQL)
* **Better Scalability**

**How It Works**

1. Each microservice has its **own private database**.
2. Only the owning service can **read or write** its database.
3. Other services access data by calling the service's **REST API**, **gRPC**, or **events**.
4. Cross-service business transactions are handled using patterns like **Saga**.

**Architecture Flow**

```text
             Client
                │
        ┌───────┼────────┐
        │       │        │
        ▼       ▼        ▼
 Order Service  Payment Service  Inventory Service
        │            │                 │
        ▼            ▼                 ▼
 Order DB      Payment DB      Inventory DB
```

Each service manages its **own database**, and databases are **never shared**.

**Example**

An **E-commerce** application:

* **Order Service** → **OrderDB**
* **Payment Service** → **PaymentDB**
* **Inventory Service** → **InventoryDB**

If **Order Service** needs payment information, it **calls Payment Service API** instead of directly querying **PaymentDB**.

**When to Use**

* **Microservices Architecture**
* **Cloud-Native Applications**
* Applications requiring **independent scaling**
* Systems where services are developed by **different teams**
* Large distributed applications

**Spring Boot Example**

**Order Entity**

```java
@Entity
public class Order {

    @Id
    private Long id;

    private String product;
}
```

**Order Repository**

```java
public interface OrderRepository
        extends JpaRepository<Order, Long> {
}
```

**Calling Another Service**

```java
@Autowired
private RestTemplate restTemplate;

String paymentStatus = restTemplate.getForObject(
    "http://payment-service/payment/1",
    String.class
);
```

Here, **Order Service** does **not** access **PaymentDB** directly. It calls the **Payment Service API**.

**Advantages**

* **Loose Coupling**
* **Independent Deployment**
* **Independent Database Scaling**
* **Supports Different Database Technologies**
* **Improved Fault Isolation**
* **Better Security** (No direct database access)

**Disadvantages**

* **No Direct JOIN** across databases
* **Distributed Transactions** become complex
* Requires **API** or **Event-Based Communication**
* Data consistency is usually **Eventual Consistency**


**Common Interview Follow-up**

**Q: Why shouldn't multiple microservices share the same database?**

A **shared database** creates **tight coupling**. Changes made by one service can affect others, making **deployment**, **maintenance**, and **scaling** difficult.

**Q: How do services access data from another service's database?**

They **do not access the database directly**. Instead, they call the service's **REST API**, **gRPC**, or consume **events**.

**Q: Can different services use different databases?**

**Yes.** Each service can choose the database that best fits its needs.

**Example:**

* **Order Service** → **MySQL**
* **Inventory Service** → **MongoDB**
* **Payment Service** → **PostgreSQL**

This is called **Polyglot Persistence**.

**Q: How are transactions managed across multiple databases?**

Since each service has its own database, **distributed transactions** are managed using the **Saga Pattern**, which coordinates local transactions and performs **compensating transactions** if a failure occurs.


## 12. What is the Bulkhead Design Pattern?

**Bulkhead** is a **Microservices Resilience Design Pattern** that **isolates resources** (such as **threads**, **connection pools**, or **service instances**) so that a failure in one part of the application **does not affect other parts**. It prevents **cascading failures** and improves system reliability.

**Key Features**

* **Resource Isolation**
* **Fault Isolation**
* **Prevents Cascading Failures**
* **Improves System Availability**
* **Independent Thread Pools or Connection Pools**
* **Works Well with Circuit Breaker**

**How It Works**

1. The application is divided into **independent resource pools**.
2. Each service or operation gets its **own thread pool** or **connection pool**.
3. If one service becomes slow or fails, only its allocated resources are affected.
4. Other services continue to work normally using their own resources.

**Architecture Flow**

```text
                 Client
                    │
      ┌─────────────┼─────────────┐
      ▼             ▼             ▼
 Order Service  Payment Service  Inventory Service
(Thread Pool A) (Thread Pool B) (Thread Pool C)

If Payment Service is overloaded,
only Thread Pool B is affected.
Order and Inventory continue working.
```

**Example**

An **E-commerce** application has three services:

* **Order Service**
* **Payment Service**
* **Inventory Service**

If the **Payment Service** becomes slow, its **thread pool** gets exhausted. Because each service has its own resources, **Order Service** and **Inventory Service** continue processing requests without interruption.

**When to Use**

* **Microservices Architecture**
* Applications with **high traffic**
* Systems calling **external APIs**
* **Cloud-Native Applications**
* When preventing **cascading failures** is important

**Spring Boot Example (Resilience4j Bulkhead)**

**Dependency**

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
```

**Bulkhead Configuration**

```java
@Service
public class PaymentService {

    @Bulkhead(name = "paymentService")
    public String processPayment() {
        return "Payment Successful";
    }
}
```

**application.yml**

```yaml
resilience4j:
  bulkhead:
    instances:
      paymentService:
        maxConcurrentCalls: 5
        maxWaitDuration: 500ms
```

This configuration allows only **5 concurrent requests**. Additional requests wait for **500 ms** and then fail if no thread is available.

**Advantages**

* **Prevents Cascading Failures**
* **Improves Fault Isolation**
* **Better System Stability**
* **High Availability**
* **Independent Resource Management**

**Disadvantages**

* More **configuration** required
* Additional **resource management**
* Incorrect pool sizes may reduce performance


**Common Interview Follow-up**

**Q: Why is the Bulkhead Pattern needed?**

Without **Bulkhead**, one overloaded service can consume all available threads or connections, causing the **entire application** to become unresponsive.

**Q: What resources can be isolated?**

* **Thread Pools**
* **Connection Pools**
* **Service Instances**
* **Memory Resources**

**Q: What is the difference between Bulkhead and Circuit Breaker?**

| **Bulkhead**                              | **Circuit Breaker**                  |
| ----------------------------------------- | ------------------------------------ |
| **Isolates resources**                    | **Stops calls** to a failing service |
| Prevents **resource exhaustion**          | Prevents repeated failures           |
| Uses **separate thread/connection pools** | Opens the circuit after failures     |
| Focuses on **fault isolation**            | Focuses on **failure handling**      |

**Q: Can Bulkhead and Circuit Breaker be used together?**

**Yes.** They are commonly used together:

* **Bulkhead** isolates resources.
* **Circuit Breaker** stops requests to failing services.
* Together they provide **better resilience** and **high availability**.


## 13. What is the Retry Design Pattern?


**Retry** is a **Microservices Resilience Design Pattern** that **automatically retries a failed operation** after a temporary failure, such as a **network issue**, **timeout**, or **temporary service unavailability**. It improves system reliability by handling **transient failures** without user intervention.

**Key Features**

* **Automatic Retry**
* **Handles Transient Failures**
* **Configurable Retry Attempts**
* **Retry Delay (Backoff)**
* **Improves Reliability**
* **Works Well with Circuit Breaker**

**How It Works**

1. A service sends a request to another service.
2. If the request fails due to a **temporary error**, it waits for a configured delay.
3. The request is retried automatically.
4. If the request succeeds, the process continues.
5. If all retry attempts fail, the request returns an error or triggers a **fallback**.

**Architecture Flow**

```text
Order Service
      │
      ▼
Payment Service
      │
      ├── Attempt 1 ❌
      ├── Wait
      ├── Attempt 2 ❌
      ├── Wait
      └── Attempt 3 ✅
```

If all attempts fail, the request returns an error or executes a fallback.

**Example**

An **Order Service** calls the **Payment Service**.

* First request → **Timeout**
* Retry after **1 second**
* Second request → **Network Error**
* Retry again
* Third request → **Success**

The user receives a successful response without manually retrying.

**When to Use**

* **Temporary Network Failures**
* **Service Timeouts**
* **External API Calls**
* **Cloud Applications**
* **Microservices Communication**

**Do Not Use For**

* **Invalid Input**
* **Authentication Errors**
* **Business Validation Errors**
* **Permanent Failures**

Retrying these errors will not solve the problem.

**Spring Boot Example (Resilience4j Retry)**

**Dependency**

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
```

**Retry Annotation**

```java
@Service
public class PaymentService {

    @Retry(name = "paymentService")
    public String processPayment() {
        return restTemplate.getForObject(
            "http://payment-service/pay",
            String.class
        );
    }
}
```

**application.yml**

```yaml
resilience4j:
  retry:
    instances:
      paymentService:
        maxAttempts: 3
        waitDuration: 2s
```

This configuration retries the request **3 times** with a **2-second delay** between attempts.

**Advantages**

* **Improves Reliability**
* **Handles Temporary Failures Automatically**
* **Reduces User Errors**
* **Easy to Configure**
* **Works Well with Circuit Breaker**

**Disadvantages**

* Can increase **response time**
* Too many retries may overload a failing service
* Not suitable for **permanent failures**


**Common Interview Follow-up**

**Q: When should we use the Retry Pattern?**

Use it for **temporary failures** like:

* **Network Issues**
* **Timeouts**
* **Temporary Service Unavailability**
* **External API Failures**

**Q: When should we avoid Retry?**

Do **not** retry:

* **Authentication Failures (401)**
* **Authorization Failures (403)**
* **Invalid Requests (400)**
* **Business Validation Errors**

These are **permanent failures**, and retrying will not help.

**Q: What is Exponential Backoff?**

Instead of retrying immediately, the delay **increases after each failed attempt**.

**Example:**

* Retry 1 → **1 second**
* Retry 2 → **2 seconds**
* Retry 3 → **4 seconds**

This reduces pressure on an overloaded service.

**Q: What is the difference between Retry and Circuit Breaker?**

| **Retry**                       | **Circuit Breaker**                       |
| ------------------------------- | ----------------------------------------- |
| **Retries failed requests**     | **Stops requests** to a failing service   |
| Best for **temporary failures** | Best for **continuous failures**          |
| Improves success rate           | Prevents system overload                  |
| Waits and retries               | Opens the circuit after repeated failures |


## 14. Strangler Design Pattern

## 15: What is resilience4j pattern?

**Resilience4j** is a **lightweight fault-tolerance library** used in Java and **Spring Boot** applications. It helps make **Microservices** more reliable by handling failures gracefully using patterns like **Circuit Breaker**, **Retry**, **Rate Limiter**, **Bulkhead**, and **Time Limiter**.

**Key Features**

* Implements **Circuit Breaker** pattern.
* Supports **Retry** for failed requests.
* Provides **Rate Limiter** to control request traffic.
* Uses **Bulkhead** to isolate resources and prevent cascading failures.
* Supports **Time Limiter** for handling slow responses.
* Easy integration with **Spring Boot** and **Spring Cloud**.
* Lightweight and a modern replacement for **Hystrix**.

**How it Works**

* A request is wrapped with a **Resilience4j component** (for example, a Circuit Breaker).
* If the target service fails or becomes slow, Resilience4j applies the configured rule.
* It can **retry the request**, **return a fallback response**, or **block further calls** until the service recovers.

**Common Modules**

| **Module**          | **Purpose**                                        |
| ------------------- | -------------------------------------------------- |
| **Circuit Breaker** | Stops calls to a failing service                   |
| **Retry**           | Retries failed requests automatically              |
| **Rate Limiter**    | Limits the number of incoming requests             |
| **Bulkhead**        | Isolates resources to prevent system-wide failures |
| **Time Limiter**    | Sets a timeout for service calls                   |
| **Cache**           | Stores frequently used results                     |


**When to Use**

* In **Microservices** architectures.
* When calling external APIs or third-party services.
* In applications that require high availability and resilience.
* With **Spring Boot** applications using REST APIs.

**Spring Boot Example**

**1. Maven Dependency**

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
```

**2. application.yml Configuration**

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        failureRateThreshold: 50
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 3

  retry:
    instances:
      paymentService:
        maxAttempts: 3
        waitDuration: 2s

  ratelimiter:
    instances:
      paymentService:
        limitForPeriod: 5
        limitRefreshPeriod: 10s
        timeoutDuration: 1s

  timelimiter:
    instances:
      paymentService:
        timeoutDuration: 3s
```

**3. Service Class Example**

```java
@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
    @Retry(name = "paymentService")
    @RateLimiter(name = "paymentService")
    @TimeLimiter(name = "paymentService")
    public String callPaymentService() {
        return restTemplate.getForObject(
                "http://PAYMENT-SERVICE/pay", String.class);
    }

    public String fallback(Exception e) {
        return "Payment service is down. Try later.";
    }
}
```


## 16. What is a @Transactional (ACID properties)? How do you handle rollback?

**`@Transactional`** is a **Spring annotation** that manages **database transactions** automatically. It ensures that **all database operations inside a method either complete successfully (Commit) or all changes are rolled back (Rollback)** if an error occurs, keeping the data **consistent**.

**How It Works**

1. A method annotated with **`@Transactional`** is called.
2. Spring starts a **new transaction**.
3. All **database operations** execute within that transaction.
4. If the method completes successfully, Spring **commits** the transaction.
5. If an **unchecked exception (`RuntimeException`)** occurs, Spring **rolls back** the transaction.

It follows **ACID properties:**

| Property    | Meaning                            |
| ----------- | ---------------------------------- |
| Atomicity   | All operations succeed or none (all-or-nothing). |
| Consistency | Data remains valid and follows all rules                 |
| Isolation   | Transactions do not interfere with each other       |
| Durability  | Once committed, data is permanently saved even after a crash          |


**Flow**

```text
Method Called
      |
Start Transaction
      |
Execute Database Operations
      |
Success? ---- Yes ----> Commit
      |
      No
      |
Rollback
```

**Key Features**

* **Automatic Transaction Management**
* **Commit** on success.
* **Rollback** on failure.
* Maintains **Data Consistency**.
* Works with **Spring Data JPA**, **Hibernate**, and **JDBC**.
* Can be applied at **Method** or **Class** level.

**When to Use**

* **Bank money transfer**
* **Order placement**
* **Payment processing**
* Multiple **insert**, **update**, or **delete** operations that must succeed together.

**Example**

```java
@Service
public class BankService {

    @Transactional
    public void transferMoney(Long fromId, Long toId, double amount) {
        accountRepository.withdraw(fromId, amount);
        accountRepository.deposit(toId, amount);
    }
}
```

If **`deposit()`** fails, **`withdraw()`** is also rolled back, so no partial update occurs.

If any exception occurs → **Automatic Rollback**


**2. Force Rollback When Error Happens**

```java
@Transactional
public void transferMoney() {
    debit();

    if (true) {
        throw new RuntimeException("Error occurred");
    }

    credit();
}
```

→ Transaction will **rollback**


**3. Rollback for Checked Exception**

By default, Spring rolls back only for **RuntimeException**.

```java
@Transactional(rollbackFor = Exception.class)
public void transferMoney() throws Exception {
    debit();
    credit();
}
```


**4. Manual Transaction (JDBC)**

```java
Connection con = dataSource.getConnection();

try {
    con.setAutoCommit(false);

    debit(con);
    credit(con);

    con.commit();

} catch (Exception e) {
    con.rollback();
} finally {
    con.close();
}
```


**Common Interview Follow-up Questions**

**1. Where can we use `@Transactional`?**

At the **Method** level or **Class** level. It is commonly placed on the **Service layer**.

**2. Why is `@Transactional` mostly used in the Service layer?**

The **Service layer** contains **business logic** that may involve multiple database operations, which should be executed in a **single transaction**.

**3. Does `@Transactional` roll back for all exceptions?**

**No.** By default, it rolls back only for **unchecked exceptions (`RuntimeException` and `Error`)**. For **checked exceptions**, specify:

```java
@Transactional(rollbackFor = Exception.class)
```

**4. Can a private method be `@Transactional`?**

**No.** Spring uses **AOP proxies**, so **private methods** are not intercepted, and the transaction will not be applied.

**5. What happens if there is no `@Transactional`?**

Each database operation may execute **independently**, so if one operation fails after another has already succeeded, **partial data** may be saved, leading to **data inconsistency**.

**6. What is the difference between `@Transactional(readOnly = true)` and `@Transactional`?**

* **`@Transactional`** – Used for **Insert**, **Update**, and **Delete** operations.
* **`@Transactional(readOnly = true)`** – Used for **Read-only** operations. It can improve performance by preventing unnecessary write tracking.


**6. is @Transactional work on Monolothic or Microservice?**

Yes. **`@Transactional` works in both Monolithic and Microservices architectures**, but the scope is different.

| **Monolithic Application**                                                        | **Microservices**                                                       |
| --------------------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| **Fully Supported**                                                               | **Supported within a single microservice**                              |
| Manages transactions across multiple database operations in the same application. | Manages transactions only inside one microservice and its own database. |
| One transaction can cover multiple repositories.                                  | Cannot manage transactions across multiple microservices.               |

**Monolithic Example**

```java
@Transactional
public void createOrder() {
    orderRepository.save(order);
    paymentRepository.save(payment);
    inventoryRepository.updateStock();
}
```

All operations are in the **same application** and **same database transaction**. If one fails, **everything is rolled back**.

**Microservices Example**

To maintain consistency across multiple microservices, use patterns like:

* **Saga Pattern** (Most commonly used)
* **Event-Driven Architecture** (Kafka/RabbitMQ)
* **Compensating Transactions**



## 17. How Does `@Transactional` Work Internally?


**`@Transactional`** works using **Spring AOP (Proxy Pattern)**. When a method annotated with `@Transactional` is called, Spring creates a **proxy object** around the target class to manage the transaction automatically.

**How It Works**

1. Spring detects the **`@Transactional`** annotation during bean creation.
2. It creates a **proxy** (JDK Dynamic Proxy or CGLIB Proxy) for that bean.
3. When the transactional method is invoked, the call goes through the **proxy**.
4. The proxy asks the **Transaction Manager** to **start a transaction**.
5. The actual business method executes.
6. If the method completes successfully, the proxy **commits** the transaction.
7. If an **unchecked exception (`RuntimeException`)** occurs, the proxy **rolls back** the transaction.

**Internal Flow**

```text
Client
   ↓
Spring Proxy (AOP)
   ↓
Transaction Manager → Begin Transaction
   ↓
Target Method Execution
   ↓
Commit (Success) / Rollback (Exception)
```

**Key Components**

* **`@Transactional`** – Marks a method or class as transactional.
* **Spring AOP Proxy** – Intercepts method calls.
* **`PlatformTransactionManager`** – Starts, commits, or rolls back transactions.
* **Database Connection** – Executes SQL operations within the transaction.


**When to Use**

* In **Service layer** methods that perform **multiple database operations**.
* When all operations should either **succeed together or fail together**.

**Example**

```java
@Service
public class EmployeeService {

    @Transactional
    public void saveEmployee(Employee emp) {
        employeeRepository.save(emp);
        // other database operations
    }
}
```

If any operation inside `saveEmployee()` fails with a **`RuntimeException`**, Spring automatically **rolls back** the entire transaction.


## 18. What is **`@Transactional`** Propagation?

**Transaction Propagation** defines **how a transaction behaves when one `@Transactional` method calls another `@Transactional` method**. It determines whether the called method should **join the existing transaction**, **create a new one**, or **execute without a transaction**.

**How It Works**

When a method annotated with **`@Transactional`** is invoked, Spring checks if a transaction already exists. Based on the **`propagation`** setting, it either:

* **Joins** the current transaction.
* **Creates** a new transaction.
* **Executes without** a transaction.
* **Throws an exception** if transaction rules are violated.

**Common Propagation Types**

| **Propagation Type**       | **Behavior**                                                                            |
| -------------------------- | --------------------------------------------------------------------------------------- |
| **`REQUIRED`** *(Default)* | Joins the existing transaction; creates a new one if none exists.                       |
| **`REQUIRES_NEW`**         | Always creates a **new transaction** and suspends the current one.                      |
| **`SUPPORTS`**             | Uses the current transaction if available; otherwise runs without one.                  |
| **`NOT_SUPPORTED`**        | Always runs **without a transaction** and suspends any existing one.                    |
| **`MANDATORY`**            | Must run inside an existing transaction; otherwise throws an exception.                 |
| **`NEVER`**                | Must run without a transaction; throws an exception if one exists.                      |
| **`NESTED`**               | Runs inside the current transaction using a **savepoint**; can roll back independently. |

**Key Features**

* Controls **transaction boundaries** between method calls.
* Helps manage **nested service operations**.
* Provides flexibility for **commit** and **rollback** behavior.

**When to Use**

* **`REQUIRED`** – Most common choice for normal business logic.
* **`REQUIRES_NEW`** – For independent operations like **audit logs** or **notifications** that should commit even if the main transaction fails.
* **`NESTED`** – When a part of the transaction can be rolled back without affecting the whole transaction.

**Example**

```java
@Service
public class OrderService {
    @Transactional
    public void placeOrder() {
        saveOrder();
        auditService.saveAuditLog();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrder() {
        // joins current transaction
    }
}

@Service
public class AuditService {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAuditLog() {
        // executes in a new transaction
    }
}
```


## 19. What is a Transaction in SQL?

A **Transaction** in SQL is a **group of one or more database operations** that are executed as a **single unit of work**. It ensures that either **all operations are completed successfully** or **none of them are applied**, maintaining **data consistency**.

**Key Features (ACID Properties)**

* **Atomicity** – Either **all** operations succeed or **all** are rolled back.
* **Consistency** – The database remains in a **valid state** before and after the transaction.
* **Isolation** – Multiple transactions do not interfere with each other.
* **Durability** – Once a transaction is **committed**, the changes are permanently saved.

**How It Works**

1. **BEGIN TRANSACTION** (or `START TRANSACTION`) starts the transaction.
2. SQL statements (`INSERT`, `UPDATE`, `DELETE`, etc.) are executed.
3. If everything is successful, use **`COMMIT`** to save the changes.
4. If any error occurs, use **`ROLLBACK`** to undo all changes made during the transaction.


**When to Use**

Use transactions whenever multiple related operations must succeed together, such as:

* **Bank money transfers**
* **Order placement and payment processing**
* **Inventory updates**
* **Booking or reservation systems**

**Example**

```sql
START TRANSACTION;

UPDATE accounts
SET balance = balance - 1000
WHERE account_id = 1;

UPDATE accounts
SET balance = balance + 1000
WHERE account_id = 2;

COMMIT;
```

If any statement fails:

```sql
ROLLBACK;
```


## 8. **What is Rollback in Transactions?**

**Rollback** means **undoing all changes** made during a transaction when an error occurs.

It ensures the **database remains consistent** by restoring the data to its previous state.

**Example**

```java
@Transactional
public void transferMoney() {
    withdraw();
    deposit();

    throw new RuntimeException("Transfer failed");
}
```

If the exception occurs, both **`withdraw()`** and **`deposit()`** are **rolled back**, so no partial changes are saved.



**Common Interview Follow-up Questions**

1. **Why is rollback important?**

   * It prevents **partial updates** and maintains **data consistency**.

2. **When does rollback happen?**

   * When the transaction **fails** due to an exception configured for rollback.

3. **Can a rollback undo committed data?**

   * **No.** Once a transaction is **committed**, it cannot be rolled back.



## 8. **Which Exceptions Trigger Rollback by Default?**

By default, Spring rolls back a transaction only for:

1. **Unchecked exceptions (`RuntimeException`)**
2. **`Error`**

It **does not roll back** for **checked exceptions (`Exception`)** by default.

**Example**

```java
@Transactional
public void saveData() {
    throw new RuntimeException("Rollback");
}
```

**Result:** Transaction is **rolled back**.

```java
@Transactional
public void saveData() throws Exception {
    throw new Exception("Checked Exception");
}
```

**Result:** By default, the transaction is **not rolled back**.


**Common Interview Follow-up Questions**

1. **Does `NullPointerException` trigger rollback?**

   * **Yes**, because it is a **`RuntimeException`**.

2. **Does `IOException` trigger rollback by default?**

   * **No**, because it is a **checked exception**.

3. **Why doesn't Spring roll back checked exceptions by default?**

   * Because checked exceptions often represent **recoverable business conditions**, while unchecked exceptions usually indicate **programming or system failures**.



## 8. **How to Configure Rollback for Checked Exceptions?**

Use the **`rollbackFor`** attribute of **`@Transactional`**.

**Example**

```java
@Transactional(rollbackFor = Exception.class)
public void saveData() throws Exception {
    throw new Exception("Checked Exception");
}
```

Now the transaction **will be rolled back** even for a checked exception.

You can also specify a specific checked exception:

```java
@Transactional(rollbackFor = IOException.class)
public void processFile() throws IOException {
    throw new IOException("File error");
}
```

Example:

```java
@Transactional(rollbackFor = Exception.class)
```

This tells Spring to **roll back** even when a **checked exception** is thrown.

**Common Interview Follow-up Questions**

1. **How do you prevent rollback for a `RuntimeException`?**

   * Use:

   ```java
   @Transactional(noRollbackFor = RuntimeException.class)
   ```

2. **Can `rollbackFor` accept multiple exceptions?**

   * **Yes.**

   ```java
   @Transactional(
       rollbackFor = {IOException.class, SQLException.class}
   )
   ```

3. **What is the difference between `rollbackFor` and `noRollbackFor`?**

   * **`rollbackFor`** specifies exceptions that **should trigger rollback**.
   * **`noRollbackFor`** specifies exceptions that **should not trigger rollback**, even if they are normally rolled back.


## 20. How do you Prevent duplicate payment(idempotency)?

**Idempotency** is a technique that ensures **multiple identical requests produce the same result**. In payment systems, it prevents a customer from being **charged more than once** if the same request is retried due to network failures or timeouts.

**Key Features**

* **Unique Idempotency Key** for each payment request.
* **Single Processing** of the request.
* **Safe Retries** without creating duplicate payments.
* **Stored Response** is returned for repeated requests with the same key.

**How It Works**

1. The client generates and sends a unique **Idempotency Key** (for example, a UUID) with the payment request.
2. The server checks if this key already exists in the database or cache.
3. If the key is **new**, the payment is processed and the result is stored with that key.
4. If the same key is received again, the server **does not process the payment again** and simply returns the previously stored response.


**When to Use**

Use idempotency for operations that should happen **only once**, such as:

* **Payment processing**
* **Order creation**
* **Money transfers**
* **Ticket or seat booking**
* **API operations with retry mechanisms**

**Code Example (Spring Boot)**

**Entity with Unique Constraint**

```java
@Entity
@Table(name = "payments", uniqueConstraints = {
        @UniqueConstraint(columnNames = "transactionId")
})
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    private String transactionId;
    private double amount;
    private String status;
}
```

```java
@PostMapping("/pay")
public PaymentResponse makePayment(
        @RequestHeader("Idempotency-Key") String key,
        @RequestBody PaymentRequest request) {

    if (paymentRepository.existsByIdempotencyKey(key)) {
        return paymentRepository.getResponseByKey(key);
    }

    PaymentResponse response = paymentService.processPayment(request);
    paymentRepository.save(key, response);

    return response;
}
```

**Database Table Example**

| idempotency_key | payment_id | status  |
| --------------- | ---------- | ------- |
| abc123          | 1001       | SUCCESS |

A **UNIQUE constraint** on the **`idempotency_key`** column ensures that the same payment request cannot be stored twice.


## 21. How to handle payment failure in microservice using Saga Pattern?

This is a **Distributed Transaction** problem. Since each **Microservice** has its own **Database**, a normal database transaction cannot roll back changes across services.

The best solution is to use the **Saga Pattern** with **Compensating Transactions**.


**How it Works**

1. **Order Service** creates the order with **PENDING** status.
2. **Payment Service** tries to process the payment.
3. If **Payment succeeds**:

   * Publish **PaymentSuccess** event.
   * **Order Service** updates the order to **CONFIRMED**.
4. If **Payment fails**:

   * Publish **PaymentFailed** event.
   * **Order Service** performs a **Compensating Transaction** by **cancelling** the order or changing its status to **CANCELLED**.

**Flow**

```text
Create Order (PENDING)
        │
        ▼
Process Payment
   │           │
Success      Failure
   │           │
   ▼           ▼
Confirm     Cancel Order
 Order   (Compensation)
```

**Key Features**

* **Distributed Transaction** across multiple services.
* Uses **Event-Driven Communication** (Kafka, RabbitMQ, etc.).
* Supports **Compensating Transactions** instead of database rollback.
* Ensures **Eventual Consistency**.
* Each service remains **independent** with its own database.

**When to Use**

* **E-commerce** order processing.
* **Payment** systems.
* **Booking** systems (Flight, Hotel, Ticket).
* Any **Microservice** architecture involving multiple services.

**Example with Kafka**

**Order Service**

```java
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void createOrder(Order order) {
        order.setStatus("PENDING");
        repository.save(order);

        kafkaTemplate.send("payment-topic",
                new PaymentRequest(order.getId(), order.getAmount()));
    }

    @KafkaListener(topics = "payment-failed")
    public void paymentFailed(PaymentFailedEvent event) {
        Order order = repository.findById(event.getOrderId()).orElseThrow();
        order.setStatus("CANCELLED"); // Compensating Transaction
        repository.save(order);
    }

    @KafkaListener(topics = "payment-success")
    public void paymentSuccess(PaymentSuccessEvent event) {
        Order order = repository.findById(event.getOrderId()).orElseThrow();
        order.setStatus("CONFIRMED");
        repository.save(order);
    }
}
```

**Payment Service**

```java
@Service
public class PaymentService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "payment-topic")
    public void processPayment(PaymentRequest request) {

        boolean paymentSuccess = false; // Payment Gateway Result

        if (paymentSuccess) {
            kafkaTemplate.send("payment-success",
                    new PaymentSuccessEvent(request.getOrderId()));
        } else {
            kafkaTemplate.send("payment-failed",
                    new PaymentFailedEvent(request.getOrderId()));
        }
    }
}
```

**Common Interview Follow-up Questions**

**Can we implement Saga Pattern without Kafka or RabbitMQ?**

**Yes.** The **Saga Pattern** can be implemented **without Kafka or RabbitMQ**. Instead of **event-based communication**, you can use **synchronous REST API calls** between microservices.

   * Update the order to **CONFIRMED**.
4. If **Payment fails**:

   * Execute a **Compensating Transaction** by updating the order to **CANCELLED**.

**Example**

```java
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private PaymentClient paymentClient;

    public void placeOrder(Order order) {

        order.setStatus("PENDING");
        repository.save(order);

        boolean paymentSuccess = paymentClient.makePayment(order);

        if (paymentSuccess) {
            order.setStatus("CONFIRMED");
        } else {
            order.setStatus("CANCELLED"); // Compensating Transaction
        }

        repository.save(order);
    }
}
```

```java
@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/payment")
    boolean makePayment(Order order);
}
```

**Limitations**

* **Tight coupling** because services call each other directly.
* Higher **latency** due to synchronous requests.
* If the **Payment Service** is down, the request may fail unless you use **Retry**, **Circuit Breaker**, or **Timeout**.
* Less scalable than an **event-driven Saga**.


**Q. Which approach is better: REST or Kafka for Saga?**

* **REST-based Saga**: Best for **simple** workflows and **small applications**.
* **Kafka/RabbitMQ-based Saga**: Best for **large, distributed systems** requiring **high scalability**, **reliability**, and **loose coupling**.


**Q1: Why can't we use `@Transactional` across microservices?**
Because `@Transactional` works only within a **single database** and **single service**.

**Q2: What is a Compensating Transaction?**
It is an action that **reverses** a previously completed business operation, such as **refunding a payment**.

**Q3: Can Saga guarantee immediate consistency?**
No. It provides **Eventual Consistency**, not **Strong Consistency**.

**Q4: What are the two Saga implementation styles?**

* **Choreography** (Event-driven)
* **Orchestration** (Central Saga Coordinator)

**Q5: What if the refund also fails?**
The system should use **Retry**, **Dead Letter Queue (DLQ)**, **Idempotency**, and **Monitoring/Alerts** to ensure the compensating transaction eventually succeeds.



## 22. **What is the difference between Choreography and Orchestration in Saga?**

A **Saga** is a pattern for managing **distributed transactions** in **microservices**. It ensures data consistency by executing a sequence of **local transactions** and **compensating transactions** if something fails.

| **Choreography**                            | **Orchestration**                                  |
| ------------------------------------------- | -------------------------------------------------- |
| No **central coordinator**.                 | Has a **central orchestrator**.                    |
| Services communicate using **events**.      | Services communicate through the **orchestrator**. |
| Each service decides **what to do next**.   | The orchestrator decides **the workflow**.         |
| More **decentralized** and loosely coupled. | More **centralized** and easier to control.        |
| Harder to understand in large systems.      | Easier to monitor and debug.                       |

**Choreography Flow:**

```text id="jlwmg0"
Order Service
      │
      ▼
OrderCreated Event
      │
      ▼
Payment Service
      │
      ▼
PaymentCompleted Event
      │
      ▼
Inventory Service
      │
      ▼
InventoryReserved Event
      │
      ▼
Shipping Service
```

Each service listens for events and triggers the next step.

**Orchestration Flow:**

```text id="jmbfy5"
        Saga Orchestrator
              │
      ┌───────┼────────┐
      ▼       ▼        ▼
 Order   Payment   Inventory
 Service  Service   Service
              │
              ▼
         Shipping Service
```

The orchestrator tells each service what to do next.

**When to Use:**

**Choreography**

* Small or medium-sized workflows.
* Event-driven architectures (e.g., **Kafka**).
* Fewer services and simpler business flows.

**Orchestration**

* Complex business processes.
* Many microservices.
* Need for centralized monitoring, retries, and compensation logic.


## 22. **What are compensating transactions?**

**Compensating transactions** are **undo operations** used in the **Saga pattern** to **reverse previously completed local transactions** when a later step in the workflow fails.

Unlike a traditional database transaction, a **Saga** cannot perform a single rollback across multiple microservices. Instead, each completed step has a corresponding **compensating transaction** to restore the system to a consistent state.

**Key Features:**

1. **Undo** previously completed local transactions.
2. Used in **Saga** for **distributed transactions**.
3. Executed when a later step **fails**.
4. Help maintain **data consistency** across microservices.

**Example: Order Processing Saga**

```text id="fwl8ux"
1. Order Service      → Create Order ✅
2. Payment Service    → Charge Payment ✅
3. Inventory Service  → Reserve Stock ❌ (Fails)

Compensating Transactions:
← Refund Payment
← Cancel Order
```

Since **Inventory Service** failed:

* **Payment Service** performs a **refund**.
* **Order Service** **cancels** the order.

**Example (Pseudo Code):**

```java id="u52drk"
try {
    orderService.createOrder();
    paymentService.chargePayment();
    inventoryService.reserveStock();
} catch (Exception e) {
    paymentService.refundPayment(); // Compensating transaction
    orderService.cancelOrder();     // Compensating transaction
}
```

**Common Compensating Transactions:**

* **Refund payment**
* **Cancel order**
* **Release reserved inventory**
* **Cancel hotel booking**
* **Cancel flight reservation**

**Benefits:**

* Maintains **data consistency**.
* Avoids **distributed database transactions**.
* Supports **failure recovery** in microservices.
* Keeps services **independent**.


## 22. **What is the difference between Sharding and Partitioning?**

Both **Sharding** and **Partitioning** split data into smaller parts, but they differ in **where the data is stored**.

| **Partitioning**                                                        | **Sharding**                                                                  |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| Splits a table into **partitions** within the **same database/server**. | Splits data across **multiple databases/servers**.                            |
| Managed by the **database**.                                            | Managed by the **application** or middleware.                                 |
| Improves **query performance** and maintenance.                         | Improves **scalability**, performance, and storage capacity.                  |
| All partitions remain in **one database**.                              | Each shard is an **independent database**.                                    |
| Example: Partition orders by **month** or **year**.                     | Example: Store users by **region** or **user ID range** on different servers. |

**Partitioning Example:**

```text id="zjlwmr"
Database
   │
Orders Table
   ├── Partition 2024
   ├── Partition 2025
   └── Partition 2026
```

All partitions are stored in the **same database**.

**Sharding Example:**

```text id="8y3dwm"
Users
   │
   ├── Shard 1 (User ID 1–10000)   → Server A
   ├── Shard 2 (User ID 10001–20000) → Server B
   └── Shard 3 (User ID 20001+)      → Server C
```

Each shard is stored on a **different server**.

**When to Use:**

* **Partitioning**: Large tables in a **single database** where you want faster queries and easier maintenance.
* **Sharding**: Very large applications that need **horizontal scaling** across multiple servers.




## 22. Java 11 HttpClient API, and communication between multiple microservices without event and messaing system?

**Java 11 HttpClient API**

**Java 11 HttpClient** is a modern **HTTP Client API** introduced in **Java 11** to send **HTTP/HTTPS** requests. It replaces the older **HttpURLConnection** and supports both **Synchronous** and **Asynchronous** communication.

**Key Features**

* Supports **HTTP/1.1** and **HTTP/2**
* **Synchronous** and **Asynchronous** requests
* Built-in **SSL/TLS** support
* Supports **Timeouts**, **Authentication**, and **Redirects**
* Uses **CompletableFuture** for asynchronous calls
* Lightweight and part of the **Java Standard Library**

**How it Works**

1. Create an **HttpClient**.
2. Build an **HttpRequest**.
3. Send the request using **send()** or **sendAsync()**.
4. Receive the **HttpResponse**.

**Synchronous Example**

```java
import java.net.URI;
import java.net.http.*;

HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://user-service/users/1"))
        .GET()
        .build();

HttpResponse<String> response =
        client.send(request, HttpResponse.BodyHandlers.ofString());

System.out.println(response.statusCode());
System.out.println(response.body());
```

**Asynchronous Example**

```java
HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://user-service/users/1"))
        .GET()
        .build();

client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenAccept(System.out::println);
```

**When to Use**

* Calling **REST APIs**
* Communication between **Microservices**
* Calling **Third-Party APIs**
* Core Java applications without **Spring**

**Advantages**

* Modern API
* Better performance than **HttpURLConnection**
* Supports **HTTP/2**
* Easy **Async** programming using **CompletableFuture**

---

**Communication Between Multiple Microservices Without Event or Messaging System**

When **Kafka**, **RabbitMQ**, or any **Event/Messaging System** is not used, microservices communicate using **Synchronous REST APIs** over **HTTP/HTTPS**.

**How it Works**

1. **Client** sends a request to **Service A**.
2. **Service A** calls **Service B** using **HTTP REST API**.
3. **Service B** processes the request and returns a response.
4. If required, **Service B** calls **Service C**.
5. The response flows back to the client.

```text
Client
   |
Order Service
   |
HTTP REST
   |
Payment Service
   |
HTTP REST
   |
Inventory Service
```

**Example Flow**

* User places an order.
* **Order Service** calls **Payment Service** to process payment.
* After successful payment, **Order Service** calls **Inventory Service** to reserve stock.
* Finally, **Order Service** returns the response to the client.

**Communication Options**

* **Java 11 HttpClient**
* **Spring WebClient** (**Recommended**)
* **OpenFeign**
* **RestTemplate** (**Legacy**)

**Java 11 HttpClient Example**

```java
HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://payment-service/pay"))
        .POST(HttpRequest.BodyPublishers.ofString("{\"amount\":1000}"))
        .header("Content-Type", "application/json")
        .build();

HttpResponse<String> response =
        client.send(request, HttpResponse.BodyHandlers.ofString());

System.out.println(response.body());
```

**Best Practices**

* Use **Service Discovery** if services are **Dynamic** (their **IP**, **Port**, or **Instances** change frequently)
* Configure **Connection Timeout** and **Read Timeout**
* Implement **Retry** for temporary failures
* Use a **Circuit Breaker** to prevent cascading failures
* Use **Load Balancing** when multiple service instances exist
* Secure communication using **HTTPS**, **JWT**, or **OAuth2**
* Enable **Centralized Logging**, **Distributed Tracing**, and **Monitoring**

**When to Use**

* **Request-Response** communication
* Immediate response is required
* CRUD operations
* Simple microservice architectures

**Advantages**

* Simple to implement
* Real-time communication
* Easy to debug
* No messaging infrastructure required

**Disadvantages**

* **Tight Coupling** between services
* Higher latency due to multiple HTTP calls
* Failure of one service can impact others
* Less scalable than **Asynchronous Messaging**


# ✅ 21. Java and Application Security

## 0. What are vulnerability issues?

A **Security Vulnerability** is a **weakness or flaw** in an application, system, or code that attackers can exploit to **steal data, gain unauthorized access, or disrupt services**.

**Key Features**

* **Weakness** in code or configuration.
* Can lead to **data breaches** or **system compromise**.
* Should be identified using **security scans**, **code reviews**, and **penetration testing**.
* Fixed by following **secure coding practices**.

**Common Security Vulnerabilities**

| **Vulnerability**                     | **Description**                                                  | **Solution**                                       |
| ------------------------------------- | ---------------------------------------------------------------- | -------------------------------------------------- |
| **SQL Injection**                     | Attacker injects SQL queries to access database data.            | Use **Prepared Statements** / **JPA**              |
| **Cross-Site Scripting (XSS)**        | Malicious JavaScript is injected into web pages.                 | Validate input and **escape output**               |
| **Cross-Site Request Forgery (CSRF)** | Tricks users into performing unwanted actions.                   | Enable **CSRF protection**                         |
| **Broken Authentication**             | Weak login or password handling.                                 | Use **JWT**, **OAuth2**, **MFA**, strong passwords |
| **Broken Access Control**             | Users access resources without permission.                       | Use **Role-Based Access Control (RBAC)**           |
| **Sensitive Data Exposure**           | Passwords or personal data are exposed.                          | Use **HTTPS**, **encryption**, password hashing    |
| **Insecure Deserialization**          | Malicious serialized objects execute harmful code.               | Validate input and avoid untrusted deserialization |
| **Security Misconfiguration**         | Default settings or unnecessary services expose the application. | Secure configurations and regular updates          |
| **Using Vulnerable Dependencies**     | Old libraries contain known security flaws.                      | Regularly update dependencies and scan them        |

**How it works**

Example of **SQL Injection (Vulnerable Code)**

```java
String query = "SELECT * FROM users WHERE username='" + username + "'";
```

If the user enters:

```sql
' OR '1'='1
```

The attacker may retrieve all user records.

**Secure Code**

```java
String query = "SELECT * FROM users WHERE username = ?";
PreparedStatement ps = connection.prepareStatement(query);
ps.setString(1, username);
```

Or with **Spring Data JPA**:

```java
User user = userRepository.findByUsername(username);
```

**When to use Security Best Practices**

* **REST APIs**
* **Spring Boot Applications**
* **Microservices**
* **Banking Applications**
* **Healthcare Systems**
* Any application handling **user data** or **payments**

**How We Handle Security Vulnerabilities in Spring Boot**

* Use **Spring Security** for authentication and authorization.
* Use **JWT** or **OAuth2** for secure authentication.
* Validate all **user inputs**.
* Use **Prepared Statements** or **JPA** to prevent SQL Injection.
* Store passwords using **BCrypt**.
* Enable **HTTPS**.
* Keep dependencies updated using **Maven** or **Gradle**.
* Perform regular **SAST**, **DAST**, and dependency vulnerability scans.

**Common Interview Follow-up Questions**

**Q: Which security vulnerability is most common in web applications?**

**Answer:** **SQL Injection**, **XSS**, **Broken Authentication**, and **Broken Access Control** are among the most common vulnerabilities.

**Q: How do you identify security vulnerabilities?**

**Answer:** By using **code reviews**, **SAST** (Static Application Security Testing), **DAST** (Dynamic Application Security Testing), **dependency scanners**, and **penetration testing**.

**Q: How do you prevent SQL Injection?**

**Answer:** Use **Prepared Statements**, **Spring Data JPA**, parameterized queries, and never concatenate user input into SQL queries.



## 1. How to find Security Vulnerabilities in Production? Which tools do you use?


In **Production**, we identify **Security Vulnerabilities** using **security monitoring**, **dependency scanning**, **code analysis**, **application security testing**, and **log analysis**. These tools help detect vulnerabilities early so they can be fixed before they are exploited.

**Key Features**

* Monitor **Security Logs** and **Alerts**.
* Scan **Dependencies** for known **CVEs**.
* Perform **Static (SAST)** and **Dynamic (DAST)** security testing.
* Monitor **Authentication** failures and suspicious activities.
* Continuously scan **Containers** and **Cloud Infrastructure**.
* Integrate security scans into the **CI/CD Pipeline**.


**How it works**

1. **Monitor logs** for failed logins, unauthorized access, and suspicious requests.
2. **Scan dependencies** to identify vulnerable libraries.
3. Run **SAST** tools to detect security issues in source code.
4. Run **DAST** tools to test the running application.
5. Monitor **SIEM dashboards** for security alerts.
6. Patch vulnerable libraries or fix the code.
7. Test the fix and deploy it to production.

**Common Tools Used in Production**

| **Category**                        | **Tools**                                                                 | **Purpose**                                      |
| ----------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------ |
| **Dependency Scanning**             | **OWASP Dependency-Check**, **Snyk**, **Mend (WhiteSource)**              | Detect vulnerable libraries and **CVEs**         |
| **Static Code Analysis (SAST)**     | **SonarQube**, **Checkmarx**, **Fortify**                                 | Find security issues in source code              |
| **Dynamic Security Testing (DAST)** | **OWASP ZAP**, **Burp Suite**                                             | Find vulnerabilities in a running application    |
| **Container Scanning**              | **Trivy**, **Clair**                                                      | Scan Docker images for vulnerabilities           |
| **Cloud Security**                  | **AWS Inspector**, **AWS Security Hub**, **Microsoft Defender for Cloud** | Detect cloud security issues                     |
| **Monitoring / SIEM**               | **Splunk**, **ELK Stack**, **IBM QRadar**                                 | Detect suspicious activities and security alerts |

**Example: Scan Dependencies**

**Maven Plugin**

```xml
<plugin>
    <groupId>org.owasp</groupId>
    <artifactId>dependency-check-maven</artifactId>
    <version>12.1.8</version>
</plugin>
```

**Run Scan**

```bash
mvn dependency-check:check
```

The scan generates a report showing **vulnerable dependencies**, **CVE IDs**, and recommended fixes.

**Real-Time Example**

**Issue:** A new **Log4j** vulnerability (**CVE**) is announced.

**Steps:**

1. Run **OWASP Dependency-Check** or **Snyk**.
2. Verify whether the application uses the vulnerable **Log4j** version.
3. Check **Splunk/ELK** for any exploit attempts.
4. Upgrade to the patched version.
5. Test the application.
6. Deploy the fix and continue monitoring.

**When to use**

* Before every **Production Release**.
* During the **CI/CD Pipeline**.
* After a new **CVE** is published.
* During **Security Audits**.
* When suspicious activity is detected in production.


**Common Interview Follow-up Questions**

**1. Which tools have you used for security scanning?**

* **OWASP Dependency-Check**
* **Snyk**
* **SonarQube**
* **OWASP ZAP**
* **Burp Suite**
* **Splunk**
* **ELK Stack**

**2. What is a CVE?**

A **CVE (Common Vulnerabilities and Exposures)** is a publicly disclosed security vulnerability with a unique identifier.

**3. What is the difference between SAST and DAST?**

* **SAST** scans the **source code** without running the application.
* **DAST** tests the **running application** by simulating real attacks.

**4. Which tool is most commonly used in Spring Boot projects?**

**SonarQube** for code quality and security, **OWASP Dependency-Check** or **Snyk** for dependency scanning, and **OWASP ZAP** for API security testing.

**5. What do you do if a vulnerability is found in production?**

Analyze the impact, identify the affected component, apply the security patch or upgrade the vulnerable dependency, validate the fix in QA, deploy it to production, and continue monitoring to ensure the issue is resolved.



## 1: What is Java security model?

The **Java Security Model** is a set of **security mechanisms** that protect Java applications from **unauthorized access**, **malicious code**, and **security vulnerabilities**. It ensures that code runs in a **safe and controlled environment**.

**Key Features**

* **Authentication** – Verifies the identity of users.
* **Authorization** – Controls what users are allowed to access.
* **Access Control** – Restricts access to files, network, and system resources.
* **Class Loader** – Loads classes safely and prevents unauthorized classes from replacing trusted ones.
* **Bytecode Verifier** – Checks bytecode before execution to ensure it is valid and safe.
* **Cryptography API** – Supports **encryption**, **decryption**, **digital signatures**, and **hashing**.
* **Secure Communication** – Supports **SSL/TLS** for secure network communication.

**How it works**

1. **Class Loader** loads Java classes securely.
2. **Bytecode Verifier** checks that the bytecode is valid and follows Java rules.
3. **JVM** executes only verified bytecode.
4. **Access Control** checks whether the code has permission to access resources.
5. **Authentication** verifies the user, and **Authorization** determines what the user can do.

**Code Example**

Using **Spring Security** to restrict access based on roles:

```java
@RestController
public class AdminController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Welcome Admin";
    }
}
```

```java
// Security Manager example
import java.lang.SecurityManager;

public class MySecurityManager extends SecurityManager {
    @Override
    public void checkRead(String file) {
        if (file.startsWith("/etc/")) {
            throw new SecurityException("Access denied to system files");
        }
        super.checkRead(file);
    }
}

// Enable security manager
System.setSecurityManager(new MySecurityManager());
```

Only users with the **ADMIN** role can access the `/admin` endpoint.

**When to use**

* **Spring Boot REST APIs**
* **Microservices**
* **Banking Applications**
* **Healthcare Systems**
* Any application handling **sensitive data** or **user authentication**


**Common Interview Follow-up Questions**

**Q: What is the role of the Class Loader?**

**Answer:** The **Class Loader** securely loads Java classes into memory and prevents untrusted classes from replacing trusted ones.

**Q: What does the Bytecode Verifier do?**

**Answer:** It checks that the **bytecode** is valid, type-safe, and follows Java security rules before execution.

**Q: What is the difference between Authentication and Authorization?**

**Answer:**

* **Authentication** verifies **who the user is**.
* **Authorization** determines **what the user is allowed to access**.

**Q: How is the Java Security Model implemented in Spring Boot?**

**Answer:** By using **Spring Security**, **JWT/OAuth2**, **HTTPS**, **BCrypt** for password hashing, and **role-based access control (RBAC)** to secure APIs and resources.



## 2: What is sandbox in Java?

A **Sandbox** in Java is a **security mechanism** that runs code in a **restricted environment**, preventing it from accessing sensitive resources like the **file system**, **network**, or **operating system** without permission.

**Key Features:**

* **Restricts unauthorized access** to system resources.
* Provides **secure execution** of untrusted code.
* Uses **permissions and security policies** to control actions.
* Helps protect against **malicious or harmful code**.

**How It Works:**
Java executes code inside the **JVM (Java Virtual Machine)**. The **Security Manager** (used in older Java versions) and **class loaders** enforce rules that allow or deny operations such as reading files, opening network connections, or executing system commands.

**When to Use:**

* Running **third-party or untrusted code**.
* **Plugin-based** or **script execution** systems.
* **Online code execution** platforms.
* Applications that require an **extra security layer**.

**Simple Example:**

```java
try {
    System.getSecurityManager().checkRead("secret.txt");
    System.out.println("File access allowed.");
} catch (SecurityException e) {
    System.out.println("File access denied by the Java Sandbox.");
}
```

In this example, the **Java Sandbox** checks whether the application has permission to read the file. If not, a **SecurityException** is thrown.



## 3: What is bytecode verification?


**Bytecode Verification** is the process where the **JVM (Java Virtual Machine)** checks the compiled **`.class` (bytecode)** before execution to ensure it is **valid**, **safe**, and **does not violate Java security rules**.

**Simple Interview Definition**

**Bytecode Verification** is a **security mechanism** of the **JVM** that validates bytecode before execution to prevent **illegal**, **corrupted**, or **malicious code** from running.

**Key Features**

* **Ensures bytecode is safe** before execution.
* Prevents **illegal memory access**.
* Checks **type safety**.
* Protects against **malicious or corrupted class files**.
* Improves **JVM security** and **stability**.

**How It Works**

1. Java source code (`.java`) is compiled into **bytecode (`.class`)**.
2. The **Class Loader** loads the class.
3. The **Bytecode Verifier** checks whether the bytecode follows JVM rules.
4. If verification succeeds, the JVM executes the code.
5. If verification fails, the JVM throws a **`VerifyError`**.

**JVM Execution Flow**

```text
Java Source (.java)
        ↓
Compiler (javac)
        ↓
Bytecode (.class)
        ↓
Class Loader
        ↓
Bytecode Verifier
        ↓
Execution by JVM
```

**What Does the Bytecode Verifier Check?**

* **Type Safety** (correct data types are used).
* **Stack Integrity** (operand stack is used correctly).
* **Valid Method Calls**.
* **No Invalid Memory Access**.
* **Valid Branch Instructions**.
* **No Stack Overflow/Underflow** during bytecode execution.

**When to Use**

You **do not manually use** bytecode verification. It is **automatically performed** by the JVM whenever a class is loaded.

It is especially important in:

* **Web applications**
* **Spring Boot** applications
* **Microservices**
* **Downloaded libraries (JARs)**
* **Third-party code**

**Example**

```java
public class Demo {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        System.out.println(a + b);
    }
}
```

**Explanation**

* The compiler generates **bytecode**.
* Before execution, the **Bytecode Verifier** validates the bytecode.
* If it is valid, the JVM executes the program safely.

**If Verification Fails**

The JVM throws:

```java
java.lang.VerifyError
```

This indicates the **bytecode is invalid or corrupted**, and execution is stopped to protect the JVM.

**Advantages**

* **Improves Security**
* Prevents **malicious code execution**
* Ensures **type safety**
* Prevents **memory corruption**
* Makes Java **platform-independent** and **secure**



**Common Interview Follow-up Questions**

**1. When does Bytecode Verification happen?**

It happens **during class loading**, before the JVM executes the bytecode.

**2. Why is Bytecode Verification needed?**

To ensure the bytecode is **safe**, **valid**, and cannot perform **illegal operations** that could compromise the JVM.

**3. Which JVM component performs Bytecode Verification?**

The **Bytecode Verifier**, which is part of the **Class Loading** process.

**4. What happens if verification fails?**

The JVM throws a **`VerifyError`** and prevents the class from being executed.

**5. What is the difference between Compilation and Bytecode Verification?**

| **Compilation**                         | **Bytecode Verification**                      |
| --------------------------------------- | ---------------------------------------------- |
| Converts **`.java`** to **`.class`**    | Validates **`.class`** before execution        |
| Done by the **Java Compiler (`javac`)** | Done by the **JVM Bytecode Verifier**          |
| Generates bytecode                      | Ensures the bytecode is **safe** and **valid** |



## 4: What is the security manager?


**Security Manager** is a **JVM component** that defines and enforces a **security policy** to control what operations a Java program is allowed to perform, such as **file access**, **network access**, or **system resources usage**.

**Simple Interview Definition**

A **Security Manager** is a **security control mechanism in Java** that restricts or allows application actions based on a defined **security policy**.

**Key Features**

* Controls **access to system resources** (files, network, threads).
* Enforces **security policies at runtime**.
* Prevents **unauthorized operations**.
* Works with **Permission-based model**.
* Helps in running **untrusted code safely**.

**How It Works**

1. Java application starts under the **JVM**.
2. A **Security Policy** is defined.
3. The **Security Manager** checks every sensitive operation.
4. If permission is allowed → operation executes.
5. If not allowed → **SecurityException** is thrown.

**Security Flow**

```text id="sec1"
Application Request
        ↓
Security Manager
        ↓
Policy Check
   ↓        ↓
Allow     Deny (SecurityException)
```

**When to Use**

* Running **untrusted code (plugins, applets - legacy)**.
* **Sandbox environments**.
* Applications requiring **strict access control**.
* Legacy **Java Web Start / Applet-based systems**.
* Controlled enterprise environments (historically).

**Important Note (Modern Java)**

* **Security Manager is deprecated (Java 17+)**.
* Replaced by **container security**, **OS-level security**, and **framework-based security (Spring Security, IAM, OAuth2)**.

**Example Code**

```java id="sec2"
public class SecurityExample {

    public static void main(String[] args) {

        System.setSecurityManager(new SecurityManager());

        try {
            System.out.println("Trying file access...");

            java.io.File file = new java.io.File("test.txt");
            file.createNewFile();

        } catch (SecurityException e) {
            System.out.println("Access Denied: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

**Explanation**

* The **Security Manager** is enabled using `System.setSecurityManager()`.
* When file creation is attempted, JVM checks permissions.
* If not allowed → **SecurityException** is thrown.

**Types of Permissions Controlled**

* **File System Access**
* **Network Access**
* **System Properties**
* **Thread Operations**
* **Class Loading**

**Advantages**

* Provides **runtime security control**
* Protects against **malicious code**
* Enables **sandboxing**
* Fine-grained **permission management**

**Disadvantages**

* **Performance overhead**
* Complex **policy configuration**
* Now mostly **deprecated in modern Java**


**Common Interview Follow-up Questions**

**1. Is Security Manager still used in Java?**

No, it is **deprecated in Java 17+** and removed in newer versions.

**2. What happens if a permission is denied?**

The JVM throws a **SecurityException** and blocks the operation.

**3. What is the difference between Security Manager and Spring Security?**

| **Security Manager**      | **Spring Security**                     |
| ------------------------- | --------------------------------------- |
| JVM-level security        | Application-level security              |
| Controls system resources | Controls authentication & authorization |
| Now deprecated            | Widely used in modern apps              |

**4. What replaced Security Manager?**

* **Spring Security**
* **OAuth2 / JWT**
* **Container security (Docker/Kubernetes)**
* **Cloud IAM systems**

**5. Why was Security Manager deprecated?**

Because it was:

* **Complex to configure**
* **Hard to maintain**
* Added **performance overhead**
* Replaced by better modern security frameworks



## 5: What are digital signatures in Java?

**Digital Signatures in Java** are a **cryptographic mechanism** to verify **code authenticity and integrity**.

JAR files are **signed with a private key** and verified using a **public key certificate**, ensuring the code **has not been tampered with** and establishing **trust in the publisher**.

```java
// Creating digital signature
Signature signature = Signature.getInstance("SHA256withRSA");
signature.initSign(privateKey);
signature.update(data);
byte[] digitalSignature = signature.sign();

// Verifying signature
signature.initVerify(publicKey);
signature.update(data);
boolean isValid = signature.verify(digitalSignature);
```


## 6: What is encryption and decryption?


**Encryption** is the process of converting **plain text (readable data)** into **cipher text (unreadable format)** to protect sensitive information.
**Decryption** is the reverse process of converting **cipher text back to plain text** using a **key**.

In Java, encryption and decryption are used to secure **data in transit** and **data at rest**.

**Simple Interview Definition**

**Encryption** = converting **readable data into secure format**
**Decryption** = converting **secure data back to readable format**

**Key Features**

* Ensures **data confidentiality**
* Protects **sensitive information**
* Uses **cryptographic algorithms**
* Requires **keys (symmetric or asymmetric)**
* Prevents **unauthorized access**

**How It Works**

1. User provides **plain text data**.
2. Java uses an **encryption algorithm + key**.
3. Data is converted into **cipher text**.
4. Encrypted data is stored or transmitted.
5. Receiver uses a **decryption key** to convert it back to **plain text**.

**Flow**

```text id="enc1"
Plain Text → Encryption (Key + Algorithm) → Cipher Text
Cipher Text → Decryption (Key + Algorithm) → Plain Text
```

**Types of Encryption**

**1. Symmetric Encryption**

* Same **key** used for **encryption and decryption**
* Faster
* Example: **AES**

**2. Asymmetric Encryption**

* Uses **public key (encryption)** and **private key (decryption)**
* More secure
* Example: **RSA**

**When to Use**

* **Password storage (hashed/encrypted form)**
* **Secure API communication**
* **Banking and payment systems**
* **HTTPS (TLS encryption)**
* **Data protection in databases**
* **File encryption**

**Java Example (AES Symmetric Encryption)**

```java id="enc2"
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class EncryptionExample {

    public static void main(String[] args) throws Exception {

        // Generate Secret Key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        String plainText = "Hello Java Security";

        // Encrypt
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("Encrypted: " + encryptedText);

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes);

        System.out.println("Decrypted: " + decryptedText);
    }
}
```

**Explanation**

* **AES algorithm** is used for encryption.
* A **secret key** is generated.
* Data is converted to **cipher text** using encryption mode.
* The same key is used to **decrypt** back to original text.

**Important Algorithms in Java**

| **Algorithm** | **Type**        | **Use Case**                |
| ------------- | --------------- | --------------------------- |
| **AES**       | Symmetric       | Fast secure encryption      |
| **RSA**       | Asymmetric      | Secure key exchange         |
| **DES**       | Symmetric (old) | Legacy systems              |
| **SHA**       | Hashing         | Password hashing, integrity |

**Advantages**

* Protects **confidential data**
* Prevents **data breaches**
* Ensures **secure communication**
* Widely used in **web and enterprise systems**

**Disadvantages**

* Adds **performance overhead**
* Requires **secure key management**
* Complex **implementation for strong security**



**Common Interview Follow-up Questions**

**1. What is the difference between Encryption and Hashing?**

| **Encryption**           | **Hashing**                    |
| ------------------------ | ------------------------------ |
| Reversible               | Irreversible                   |
| Uses keys                | No key used                    |
| Used for data protection | Used for integrity & passwords |

**2. What is AES and RSA?**

* **AES** → Symmetric encryption (fast, same key)
* **RSA** → Asymmetric encryption (public/private key)

**3. Is encryption enough for password storage?**

No. Passwords should be stored using **hashing algorithms (like BCrypt)**, not encryption.

**4. Where is encryption used in Java applications?**

* **HTTPS (TLS)**
* **Spring Boot APIs**
* **Database encryption**
* **File security**
* **JWT token security**

**5. What happens if encryption key is lost?**

Encrypted data becomes **unrecoverable**, as decryption is not possible without the key.



## 7: What is SSL/TLS in Java?


**SSL (Secure Sockets Layer)** and **TLS (Transport Layer Security)** are security protocols used to **encrypt communication** between a **client** and a **server** over the internet.

**TLS** is the **newer and more secure** version of **SSL**. Today, almost all applications use **TLS**, although people still commonly say **SSL**.

**Key Features**

* **Encrypts** data during transmission.
* Protects against **data theft** and **man-in-the-middle attacks**.
* Ensures **Confidentiality**, **Integrity**, and **Authentication**.
* Used by **HTTPS**, secure APIs, online banking, and e-commerce websites.

**How It Works**

1. Client sends a request to the server using **HTTPS**.
2. Server sends its **SSL/TLS Certificate**.
3. Client verifies the certificate.
4. Client and server perform a **TLS Handshake** and generate a **shared session key**.
5. All communication is **encrypted** using the session key.

```text
Client → HTTPS Request
Server → SSL/TLS Certificate
Client → Verify Certificate
TLS Handshake
Shared Session Key Created
Encrypted Communication
```

**When to Use**

Use **SSL/TLS** when:

* Building **REST APIs**.
* Sending **login credentials**.
* Transferring **payment** or **personal data**.
* Communication between **Microservices**.
* Any application accessible over the **internet**.

**Spring Boot HTTPS Configuration**

**application.properties**

```properties
server.port=8443
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=password
server.ssl.key-store-type=PKCS12
```

**Common Interview Follow-up Questions**

**1. What is the difference between SSL and TLS?**

* **SSL** is the **older** protocol and is no longer considered secure.
* **TLS** is the **newer**, **faster**, and **more secure** protocol.
* Today, almost all systems use **TLS**.

**2. What is an SSL/TLS Certificate?**

It is a **digital certificate** that verifies the **server's identity** and contains the server's **public key**.

**3. What is HTTPS?**

**HTTPS = HTTP + TLS**. It encrypts communication between the client and server.

**4. Does JWT replace SSL/TLS?**

**No.** They solve different problems:

* **TLS** protects data **during transmission**.
* **JWT** is used for **authentication** and **authorization**.

**5. Can we use JWT without TLS?**

**Technically yes, but it is not recommended.** Without **TLS**, a JWT can be intercepted during transmission, creating a security risk. In production, **JWT should always be used over HTTPS (TLS)**.


## 8: What is authentication vs authorization?


* **Authentication** is the process of **verifying the identity** of a user.
* **Authorization** is the process of **determining what an authenticated user is allowed to access or perform**.

**Simple Interview Definition**

* **Authentication** = **Who are you?**
* **Authorization** = **What are you allowed to do?**

**Key Differences**

| **Authentication**                                             | **Authorization**                            |
| -------------------------------------------------------------- | -------------------------------------------- |
| Verifies **user identity**                                     | Verifies **user permissions**                |
| Happens **first**                                              | Happens **after authentication**             |
| Uses **username, password, OTP, biometrics, JWT, OAuth login** | Uses **roles, permissions, privileges**      |
| Returns **authenticated user**                                 | Grants or denies **access to resources**     |
| Example: User logs in                                          | Example: User can access **Admin Dashboard** |

**How It Works**

1. User enters **username and password**.
2. System verifies the credentials (**Authentication**).
3. If valid, the user is logged in.
4. System checks the user's **role or permissions** (**Authorization**).
5. Access is either **granted** or **denied**.

**Real-World Example**

Imagine entering an office building:

* Showing your **ID card** at the entrance is **Authentication**.
* Accessing only the **HR floor** or **Server Room** based on your role is **Authorization**.

**When to Use**

**Authentication**

* **Login pages**
* **Mobile apps**
* **Web applications**
* **Banking systems**
* **Online shopping**

**Authorization**

* **Role-Based Access Control (RBAC)**
* **Admin/User permissions**
* **API security**
* **Microservices**
* **Enterprise applications**

**Spring Boot Example**

```java
@RestController
public class UserController {

    @GetMapping("/profile")
    public String profile() {
        return "Authenticated User";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "User Deleted";
    }
}
```

**Explanation**

* Accessing **`/profile`** requires the user to be **authenticated**.
* Accessing **`/users/{id}`** requires the user to have the **ADMIN** role (**authorization**).

**How Authentication is Implemented**

* **Username & Password**
* **OTP**
* **JWT (JSON Web Token)**
* **OAuth 2.0**
* **Biometric Authentication**

**How Authorization is Implemented**

* **Roles** (ADMIN, USER)
* **Permissions** (READ, WRITE, DELETE)
* **Role-Based Access Control (RBAC)**
* **Attribute-Based Access Control (ABAC)**


**Common Interview Follow-up Questions**

**1. Which comes first: Authentication or Authorization?**

**Authentication** always happens **before** **Authorization**.

**2. Can Authorization happen without Authentication?**

**No.** The system must first know **who the user is** before deciding **what they can access**.

**3. What is RBAC?**

**Role-Based Access Control (RBAC)** assigns permissions based on **user roles** such as **ADMIN**, **USER**, or **MANAGER**.

**4. What is JWT used for?**

**JWT (JSON Web Token)** is commonly used for **Authentication**. After successful login, the server issues a token, which the client sends with subsequent requests. The server validates the token and then performs **Authorization** based on the user's roles or permissions.

**5. What HTTP status codes are returned?**

* **401 Unauthorized** → **Authentication failed** (invalid or missing credentials).
* **403 Forbidden** → **Authentication succeeded**, but the user **does not have permission** to access the resource.


## 9: What is OAuth?

**OAuth (Open Authorization)** is an **authorization framework** that allows a user to give a third-party application **limited access** to their resources **without sharing their password**.

**Example:** When you click **"Login with Google"** or **"Login with GitHub"**, OAuth is used to authorize the application.

**Key Features:**

* **Password is never shared** with the third-party application.
* Uses **access tokens** instead of credentials.
* Provides **limited and controlled access** through **scopes**.
* Supports **secure delegated authorization**.
* Widely used in **REST APIs** and **microservices**.

**How It Works:**

1. User requests to log in using an OAuth provider (e.g., Google).
2. The application redirects the user to the provider's login page.
3. User authenticates and grants permission.
4. The provider returns an **authorization code**.
5. The application exchanges the code for an **access token**.
6. The application uses the access token to access the user's allowed resources.

**Main OAuth Components:**

* **Resource Owner** – The user.
* **Client** – The application requesting access.
* **Authorization Server** – Issues access tokens.
* **Resource Server** – Hosts the protected resources.
* **Access Token** – Temporary token used to access resources.

**When to Use:**

* **Social login** (Google, GitHub, Facebook).
* Securing **REST APIs**.
* **Microservices** communication.
* Allowing third-party apps to access user data securely.

**Simple Spring Boot Example:**

**application.yml**

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_CLIENT_ID
            client-secret: YOUR_CLIENT_SECRET
```

**Security Configuration**

```java id="2lsn4f"
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated())
            .oauth2Login();

        return http.build();
    }
}
```

With this configuration, users can securely **log in using their Google account** without sharing their Google password with your application.


**OAuth 1.0** uses signature-based authentication and is complex, while **OAuth 2.0** is token-based, simpler, faster, and widely used in modern applications.

## 11: What is OAuth 2.0?

**OAuth 2.0** is the **latest version of the OAuth authorization framework** that allows a user to grant a third-party application **limited access** to their resources **without sharing their password**. It uses **access tokens** to securely authorize requests.

**Example:** **"Sign in with Google"** or **"Login with GitHub"** uses OAuth 2.0.

**Key Features:**

* Uses **access tokens** instead of usernames and passwords.
* Provides **secure delegated authorization**.
* Supports **scopes** to limit permissions.
* Supports **refresh tokens** to obtain new access tokens without logging in again.
* Widely used for **REST APIs**, **microservices**, and **social login**.

**How It Works:**

1. The user requests login through an OAuth 2.0 provider (e.g., Google).
2. The application redirects the user to the provider's login page.
3. The user authenticates and grants permission.
4. The provider returns an **authorization code**.
5. The application exchanges the code for an **access token** (and optionally a **refresh token**).
6. The application sends the access token to the **resource server** to access protected resources.

**Main Components:**

* **Resource Owner** – The user.
* **Client Application** – The app requesting access.
* **Authorization Server** – Authenticates the user and issues tokens.
* **Resource Server** – Stores protected resources.
* **Access Token** – Used to access resources.
* **Refresh Token** – Used to generate a new access token.

**Common OAuth 2.0 Grant Types:**

* **Authorization Code Grant** (most common and recommended).
* **Client Credentials Grant** (machine-to-machine communication).
* **Refresh Token Grant** (renew expired access tokens).

**When to Use:**

* **Social login** (Google, GitHub, Facebook).
* Securing **REST APIs**.
* **Microservices** authentication and authorization.
* Allowing third-party applications to access user data securely.

**Simple Spring Boot Example:**

**application.yml**

```yaml id="x9kl1a"
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_CLIENT_ID
            client-secret: YOUR_CLIENT_SECRET
```

**Security Configuration**

```java id="sh8w2m"
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated())
            .oauth2Login();

        return http.build();
    }
}
```

This configuration enables users to **log in with Google** using the **OAuth 2.0 Authorization Code Flow**.

**OAuth 1.0** uses signature-based authentication and is complex, while **OAuth 2.0** is token-based, simpler, faster, and widely used in modern applications.


## 11:  How JWT(JSON Web Token) Authentication works in Spring Boot?

**JWT (JSON Web Token)** authentication is a **stateless authentication mechanism** where, after successful login, the server generates a **signed token** and sends it to the client. The client includes this token in every request, and Spring Boot validates it before granting access.

**Key Features:**

* **Stateless** – No session is stored on the server.
* Uses **signed tokens** for security.
* Easy to use with **REST APIs** and **microservices**.
* Supports **user roles and permissions** through token claims.
* Improves **scalability** because the server does not maintain session data.

**How It Works:**

1. The user sends **username** and **password** to the login API.
2. **Spring Security** authenticates the user.
3. If authentication is successful, the server generates a **JWT token** containing user details and roles.
4. The client stores the token (usually in local storage or a secure cookie).
5. For every API request, the client sends the token in the **Authorization** header:

   ```
   Authorization: Bearer <JWT_TOKEN>
   ```
6. A **JWT Filter** intercepts the request, validates the token, and extracts user information.
7. If the token is valid, Spring Security sets the authentication in the **SecurityContext**, and the request is allowed. Otherwise, access is denied.

**JWT Structure:**
A JWT consists of **three parts** separated by dots (`.`):

* **Header** – Contains token type and signing algorithm.
* **Payload** – Contains user information (**claims**).
* **Signature** – Verifies that the token has not been modified.

Example:

```text
xxxxx.yyyyy.zzzzz
```

**When to Use:**

* **REST APIs**
* **Microservices architecture**
* **Single Page Applications (SPA)** like React or Angular
* **Mobile applications**
* Systems requiring **stateless authentication**


**How JWT Works**

1. **Login**: Client sends credentials → Server validates → Returns JWT token
2. **Authorization**: Client includes JWT in request headers → Server validates token → Grants access


The backend server checks credentials using:
- Database (MySQL, PostgreSQL, etc.)
- or external provider (LDAP, OAuth, etc.)

**JWT Structure**

A JWT consists of three Base64-encoded parts separated by dots:
HEADER.PAYLOAD.SIGNATURE

**Sample JWT Token:**
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huZG9lIiwiaWF0IjoxNjE2MjM5MDIyLCJleHAiOjE2MTYzMjU0MjIsInJvbGVzIjpbIlVTRVIiLCJBRE1JTiJdfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
**Decoded Structure:**

- **Header**: Contains algorithm information (e.g., HS256)
  ```json
  {
    "alg": "HS256",
    "typ": "JWT"
  }
  ```

- **Payload**: Contains claims (username, roles, expiration)
  ```json
  {
    "sub": "johndoe",
    "iat": 1616239022,
    "exp": 1616325422,
    "roles": ["USER", "ADMIN"]
  }
  ```

- **Signature**: Ensures token integrity and authenticity
  ```
  HMACSHA256(
    base64UrlEncode(header) + "." +
    base64UrlEncode(payload),
    secret
  )
  ```

**Implementation Flow**



**Project Flow**

```text
User
   │
   ▼
POST /auth/login
   │
   ▼
AuthenticationManager
   │
   ▼
UserDetailsService
   │
   ▼
Database
   │
   ▼
Validate Username & Password
   │
   ▼
Generate JWT Token
   │
   ▼
Client Stores JWT
   │
   ▼
Authorization: Bearer JWT
   │
   ▼
JwtAuthenticationFilter
   │
   ▼
Validate JWT
   │
   ▼
Spring Security Context
   │
   ▼
Authorization (@PreAuthorize / hasRole())
```

**Project Structure**

```
src/main/java
|
├── config
│      SecurityConfig.java
│
├── controller
│      AuthController.java
│      UserController.java
│
├── dto
│      LoginRequest.java
│      JwtResponse.java
│
├── entity
│      User.java
│
├── repository
│      UserRepository.java
│
├── security
│      JwtUtil.java
│      JwtAuthenticationFilter.java
│
├── service
│      CustomUserDetailsService.java
│
└── JwtApplication.java
```

---

**Step 1: Dependency**

```xml
<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>

    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <scope>runtime</scope>
    </dependency>

</dependencies>
```

---

**Step 2: User Entity**

```java
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String role;

    // getters setters
}
```

---

**Step 3: Repository**

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
```

---

**Step 4: UserDetailsService**

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = repo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
```

---

**Step 5: JWT Utility**

```java
@Component
public class JwtUtil {

    private final String SECRET =
            "mysecretkeymysecretkeymysecretkey123456";

    public String generateToken(String username){

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                    new Date(System.currentTimeMillis()+86400000))
                .signWith(Keys.hmacShaKeyFor(
                    SECRET.getBytes()),
                    SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){

        return Jwts.parserBuilder()
                .setSigningKey(
                    Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token,String username){

        return username.equals(extractUsername(token));
    }
}
```

---

**Step 6: JWT Filter**

```java
@Component
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");

        if(authHeader != null &&
                authHeader.startsWith("Bearer ")){

            String token =
                    authHeader.substring(7);

            String username =
                    jwtUtil.extractUsername(token);

            if(username!=null &&
               SecurityContextHolder
               .getContext()
               .getAuthentication()==null){

                UserDetails userDetails =
                        userDetailsService
                        .loadUserByUsername(username);

                if(jwtUtil.validateToken(
                        token,
                        userDetails.getUsername())){

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
```

---

**Step 7: Security Configuration**

```java
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthenticationFilter filter;

    @Autowired
    CustomUserDetailsService service;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http.csrf(csrf->csrf.disable());

        http.authorizeHttpRequests(auth->auth

                .requestMatchers("/auth/**")
                .permitAll()

                .requestMatchers("/admin/**")
                .hasRole("ADMIN")

                .requestMatchers("/user/**")
                .hasAnyRole("USER","ADMIN")

                .anyRequest()
                .authenticated()

        );

        http.sessionManagement(session->
                session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS));

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```

---

**Step 8: Login DTO**

```java
public class LoginRequest {

    private String username;

    private String password;

    // getters setters
}
```

---

**Step 9: JWT Response**

```java
public class JwtResponse {

    private String token;

    public JwtResponse(String token){
        this.token=token;
    }

    public String getToken(){
        return token;
    }
}
```

---

**Step 10: Authentication Controller**

```java
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public JwtResponse login(
            @RequestBody LoginRequest request){

        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        String token =
                jwtUtil.generateToken(
                        request.getUsername());

        return new JwtResponse(token);
    }
}
```

---

**Step 11: Protected Controller**

```java
@RestController
public class UserController {

    @GetMapping("/user/profile")
    public String profile(){

        return "User Profile";
    }

    @GetMapping("/admin/dashboard")
    public String admin(){

        return "Admin Dashboard";
    }
}
```

---

**Step 12: Method-Level Authorization**

```java
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/delete")
public String delete(){

    return "Only Admin";
}
```

```java
@PreAuthorize("hasAnyRole('USER','ADMIN')")
@GetMapping("/profile")
public String profile(){

    return "User Profile";
}
```

---

**Step 13: application.properties**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/test

spring.datasource.username=root

spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
```

---

**Database Data**

Password should be stored **encrypted** using **BCrypt**.

| username | password (BCrypt) | role  |
| -------- | ----------------- | ----- |
| admin    | $2a$...           | ADMIN |
| john     | $2a$...           | USER  |

Example to encode a password:

```java
String encoded = new BCryptPasswordEncoder().encode("password");
System.out.println(encoded);
```

---

**End-to-End Flow**

**1. Login Request**

```http
POST /auth/login
```

```json
{
  "username":"admin",
  "password":"password"
}
```

---

**2. Server Authenticates**

```text
AuthenticationManager
        ↓
UserDetailsService
        ↓
Database
        ↓
Password matches?
        ↓
Yes
        ↓
Generate JWT
```

---

**3. Login Response**

```json
{
   "token":"eyJhbGciOiJIUzI1NiJ9..."
}
```

---

**4. Client Stores JWT**

Typically in an **HttpOnly Secure Cookie** (recommended for web apps) or sends it in the `Authorization` header.

---

**5. Subsequent Request**

```http
GET /user/profile
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

**6. JWT Filter**

```text
Read Authorization Header
          ↓
Extract JWT
          ↓
Validate Signature
          ↓
Check Expiration
          ↓
Extract Username
          ↓
Load UserDetails
          ↓
Create Authentication
          ↓
Store in SecurityContext
```

---

**7. Authorization**

Spring Security checks the authenticated user's roles:

```java
.hasRole("ADMIN")
```

or

```java
@PreAuthorize("hasRole('ADMIN')")
```

If the user has the required role, access is granted; otherwise, Spring returns **403 Forbidden**.



## 12. JWT follow up common Questions?

**0. What is AuthenticationManager in Spring Security?**

**`AuthenticationManager`** is the **main authentication component** in Spring Security. Its job is to **verify the user's username and password** during login.

If the credentials are correct, it returns an **authenticated `Authentication` object**. Otherwise, it throws an exception such as `BadCredentialsException`.

**What Happens Internally?**

1. The client sends **username** and **password**.
2. `AuthenticationManager.authenticate()` receives a `UsernamePasswordAuthenticationToken`.
3. It delegates authentication to the configured `AuthenticationProvider`.
4. `DaoAuthenticationProvider` calls `UserDetailsService.loadUserByUsername()`.
5. User details are loaded from the database.
6. `BCryptPasswordEncoder` compares the raw password with the stored encrypted password.
7. If they match, authentication succeeds and an authenticated `Authentication` object is returned.
8. If they don't match, a `BadCredentialsException` is thrown.
9. After successful authentication, the application generates a JWT and returns it to the client.


**1. Why is JWT called Stateless?**

Because the **server does not store user sessions**. Every request contains the JWT, and the server validates it independently.

**2. Where is JWT stored?**

Usually in the **Authorization Header** (`Bearer Token`). It can also be stored in an **HttpOnly Cookie** for better security.

**3. What happens if the token expires?**

The server returns **401 Unauthorized**, and the client must authenticate again or use a **Refresh Token** to obtain a new JWT.

**4. What is the difference between Authentication and Authorization?**

* **Authentication** → Verifies **who the user is**.
* **Authorization** → Determines **what the user is allowed to access**.

**5. Why use `SessionCreationPolicy.STATELESS`?**

It tells Spring Security **not to create or use HTTP sessions**, making JWT-based authentication fully **stateless**.


**6. Where is JWT Stored in the Backend?**

**JWT is generally *not stored* in the backend.** This is what makes **JWT authentication stateless**.

**How it Works**

1. User logs in with **username** and **password**.
2. Server generates a **JWT** and sends it to the client.
3. The client stores the token (usually in the **Authorization Header** or an **HttpOnly Cookie**).
4. For every request, the client sends the JWT.
5. The backend **validates the token's signature and expiration** using the **secret key** (or **public/private key**) without looking it up in a database.

**7. What Does the Backend Store?**

Normally, the backend stores only:

* **Secret Key** (for HS256) or **Public/Private Keys** (for RS256)
* **User details** in the database
* **No JWT tokens** are stored

**8. When Can JWT Be Stored in the Backend?**

Although JWT is designed to be **stateless**, some applications store tokens for additional security:

* **Refresh Tokens** stored in the database
* **Token Blacklist** for logout or revoked tokens
* **Redis Cache** for token revocation or session tracking

**9. If JWT is not stored, how does the server verify it?**

The server verifies the **digital signature**, **expiration time**, and **claims** using the configured **secret key** or **public key**.

**10. Why is JWT called Stateless?**

Because the **server does not store session or access token information**. Every request contains all the information needed for authentication.

**11. JWT vs Session-based authentication?**

A: JWT is stateless (no server storage), while sessions require server-side storage. JWT is better for microservices and scalability.

**12. How do you handle token expiration?**

A: Implement refresh tokens or require re-authentication when tokens expire.

**13. Can JWT be revoked?**

A: JWT cannot be revoked by default. Implement token blacklisting or use short expiration times with refresh tokens.



## 13. Implement Roles Base Authentication(RBAC) (ADMIN, USER, MANAGER) in Spring Boot Using Spring Security?

In **Spring Boot**, **application roles** (such as **ADMIN**, **USER**, **MANAGER**) are typically stored in the **database**. When a user logs in, **Spring Security** loads the user's roles and checks them before allowing access to APIs.

**How it Works**

1. User logs in with **username** and **password**.
2. **Spring Security** authenticates the user.
3. It loads the user's **roles** from the **database**.
4. The roles are converted into **GrantedAuthority** objects.
5. Before accessing an API, Spring Security checks whether the user has the required role.
6. If the role matches, access is granted; otherwise, **403 Forbidden** is returned.

**Flow**

```text
User Login
     │
     ▼
Spring Security Authentication
     │
     ▼
Load User & Roles from Database
     │
     ▼
Convert Roles to GrantedAuthority
     │
     ▼
Check @PreAuthorize / hasRole()
     │
 ┌───┴────┐
 │        │
Allowed  Forbidden (403)
```

**Key Features**

* **Role-Based Access Control (RBAC)**.
* Roles stored in the **database**.
* Uses **Spring Security** for authorization.
* Supports **method-level** and **URL-level** security.
* Easy to add new roles without changing business logic.

**When to Use**

* Applications with multiple user types.
* **Admin Dashboard**.
* **HR**, **Banking**, **E-commerce**, **Healthcare** applications.
* Any application requiring different access levels.

**Database Tables**

**users**

| id | username | password          |
| -- | -------- | ----------------- |
| 1  | john     | encryptedPassword |

**roles**

| id | name       |
| -- | ---------- |
| 1  | ROLE_ADMIN |
| 2  | ROLE_USER  |

**user_roles**

| user_id | role_id |
| ------- | ------- |
| 1       | 1       |

**Example**

**Role Entity**

```java
@Entity
public class Role {

    @Id
    private Long id;

    private String name;   // ROLE_ADMIN, ROLE_USER
}
```

**User Entity**

```java
@Entity
public class User {

    @Id
    private Long id;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
```

**UserDetailsService**

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = repository.findByUsername(username);

        List<GrantedAuthority> authorities =
                user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
```

**Secure API**

```java
@RestController
public class UserController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String getUsers() {
        return "Only ADMIN can access";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/reports")
    public String getReports() {
        return "ADMIN and MANAGER can access";
    }
}
```



**Common Interview Follow-up Questions**

**1. Where are roles stored?**

Usually in a **roles** table with a **user_roles** mapping table.

**2. Why use `ROLE_ADMIN` instead of `ADMIN`?**

Spring Security convention is to store roles with the **`ROLE_`** prefix. Then `hasRole("ADMIN")` automatically matches **`ROLE_ADMIN`**.

**3. What is `GrantedAuthority`?**

It is the Spring Security object that represents the **permissions or roles** assigned to an authenticated user.

**4. What is the difference between `hasRole()` and `hasAuthority()`?**

* **`hasRole("ADMIN")`** checks for **`ROLE_ADMIN`** automatically.
* **`hasAuthority("ROLE_ADMIN")`** checks the exact authority string.

**5. Can one user have multiple roles?**

**Yes.** A user can have multiple roles (for example, **ROLE_USER** and **ROLE_MANAGER**) using a **`@ManyToMany`** relationship.


## 14. Implement AWS IAM Rolebase Control Access to AWS Resources?

**AWS IAM (Identity and Access Management)** controls **who** can access **AWS resources** and **what actions** they can perform.

The recommended approach is to **attach an IAM Role** to your **EC2**, **ECS**, **EKS**, or **Lambda** instead of storing **Access Keys** in your application.

**How it Works**

1. Create an **IAM Policy** with the required permissions.
2. Create an **IAM Role**.
3. Attach the **Policy** to the **Role**.
4. Attach the **Role** to the **EC2**, **ECS**, **EKS**, or **Lambda**.
5. The **AWS SDK** automatically retrieves **temporary credentials** from the IAM Role.
6. Your application securely accesses **S3**, **SQS**, **DynamoDB**, or **Secrets Manager**.

**Key Features**

* **Role-Based Access Control (RBAC)**.
* Uses **IAM Policies** to define permissions.
* Provides **temporary credentials**.
* No hardcoded **Access Key** or **Secret Key**.
* Follows the **Least Privilege Principle**.

**When to Use**

* Access files in **S3**.
* Send or receive messages from **SQS**.
* Read or write data in **DynamoDB**.
* Retrieve secrets from **Secrets Manager**.
* Any application running on **EC2**, **ECS**, **EKS**, or **Lambda**.

**Example**

**Step 1: Create an IAM Policy**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "s3:GetObject",
        "s3:PutObject"
      ],
      "Resource": "arn:aws:s3:::my-bucket/*"
    }
  ]
}
```

**Step 2: Attach the Policy to an IAM Role**

```text
Role Name : SpringBootS3Role

Permissions:
- s3:GetObject
- s3:PutObject
```

**Step 3: Attach the IAM Role to the EC2 Instance**

```text
EC2
 └── IAM Role: SpringBootS3Role
```

**Step 4: Spring Boot Code**

```java
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Paths;

public class S3Service {

    private final S3Client s3Client = S3Client.create();

    public void uploadFile() {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("my-bucket")
                .key("report.pdf")
                .build();

        s3Client.putObject(request, Paths.get("report.pdf"));
    }
}
```

**How Authentication Happens**

* The application **does not store AWS credentials**.
* The **AWS SDK** checks for credentials.
* It automatically retrieves **temporary credentials** from the attached **IAM Role**.
* AWS verifies the **IAM Policy**.
* If allowed, access to the resource is granted.


**Common Interview Follow-up Questions**

**1. Why use an IAM Role instead of Access Keys?**

**IAM Roles** provide **temporary credentials**, improve security, eliminate hardcoded secrets, and support the **Least Privilege Principle**.

**2. Does the AWS SDK automatically use the IAM Role?**

**Yes.** When running on **EC2**, **ECS**, **EKS**, or **Lambda**, the **AWS SDK** automatically retrieves the temporary credentials from the attached **IAM Role**.

**3. Can one IAM Role access multiple AWS services?**

**Yes.** A single IAM Role can have multiple permissions, such as access to **S3**, **SQS**, **DynamoDB**, and **Secrets Manager**, based on its attached **IAM Policies**.

**4. What happens if the IAM Role does not have permission?**

AWS returns an **AccessDenied** error, and the requested operation is rejected.




## 15. Difference between JWT and Session?

Both are used to **authenticate users**, but they store and manage user authentication differently.

| **Feature**        | **JWT Authentication**                                                       | **Session Authentication**                                    |
| ------------------ | ---------------------------------------------------------------------------- | ------------------------------------------------------------- |
| **Storage**        | Token stored on **client** (Browser LocalStorage, SessionStorage, or Cookie) | Session data stored on **server**                             |
| **Server State**   | **Stateless**                                                                | **Stateful**                                                  |
| **Authentication** | Client sends **JWT token** with every request                                | Client sends **Session ID** with every request                |
| **Scalability**    | Better for **Microservices** and distributed systems                         | Better for **Traditional Web Applications**                   |
| **Performance**    | Faster because server doesn't store session                                  | Slightly slower because server checks session store           |
| **Logout**         | Harder (token remains valid until expiry unless blacklisted)                 | Easy (server simply destroys the session)                     |
| **Security**       | Secure if token is protected and sent over **HTTPS**                         | More secure by default because sensitive data stays on server |

**How JWT Authentication Works**

1. User logs in with **username/password**.
2. Server validates credentials.
3. Server generates a **JWT Token**.
4. Client stores the token.
5. Client sends the token in the **Authorization** header for every request.
6. Server validates the token and allows access.

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
```

**Spring Boot Example (JWT)**

```java
@GetMapping("/profile")
public String profile() {
    return "Authenticated User";
}
```

The request contains:

```http
Authorization: Bearer <JWT_TOKEN>
```

Spring Security validates the token before accessing the API.

**How Session Authentication Works**

1. User logs in.
2. Server creates a **Session** and stores user data.
3. Server returns a **Session ID** in a cookie.
4. Browser automatically sends the Session ID with every request.
5. Server looks up the session and authenticates the user.

**Spring Boot Example (Session)**

```java
@PostMapping("/login")
public String login(HttpSession session) {
    session.setAttribute("user", "John");
    return "Login Successful";
}
```

Later:

```java
String user = (String) session.getAttribute("user");
```

**When to Use**

**Use JWT when:**

* Building **REST APIs**
* Using **Microservices**
* Supporting **Mobile Applications**
* Need **Stateless Authentication**
* Multiple services need to validate the same token

**Use Session when:**

* Building **Traditional Web Applications**
* Need easy **Logout**
* Want authentication managed completely by the server
* Small or medium-sized applications

**Key Features**

**JWT**

* **Stateless**
* **Token-based**
* No server session storage
* Best for **REST APIs** and **Microservices**
* Better scalability

**Session**

* **Stateful**
* **Server stores session**
* Easy logout and session invalidation
* Best for **Server-rendered Web Applications**


**Session Authentication** is **stateful**, where the server stores user session data and the client sends a **Session ID**. The server checks the session on every request, making it ideal for **traditional web applications**.

**Common Interview Follow-up Questions**

**Q: Which is more scalable?**
**JWT**, because the server does not store session data.

**Q: Which provides easier logout?**
**Session Authentication**, because the server can immediately destroy the session.

**Q: Why is JWT called stateless?**
Because the server **does not store user session information**. Each request contains all the authentication information in the **JWT token**.

**Q: Can we use JWT and Session together?**
Yes. A common approach is to use **Session Authentication** for a web application's UI and **JWT Authentication** for REST APIs or communication between **Microservices**.



## 16. What is CSRF Protection?

**CSRF (Cross-Site Request Forgery) Protection** is a **security mechanism** that prevents attackers from tricking an authenticated user into performing unwanted actions on a web application without their consent.

**Example:** A user is logged into a banking website. If they visit a malicious website, it could secretly send a money transfer request using the user's active session. **CSRF protection blocks this attack.**

**Key Features:**

* Prevents **unauthorized requests** from malicious websites.
* Uses a unique **CSRF token** to validate requests.
* Protects **state-changing operations** like **POST**, **PUT**, **PATCH**, and **DELETE**.
* Enabled **by default** in **Spring Security** for session-based applications.

**How It Works:**

1. The server generates a unique **CSRF token** for the user's session.
2. The token is sent to the client (usually in a hidden form field or HTTP header).
3. The client includes the token in every state-changing request.
4. Spring Security compares the received token with the stored token.
5. If the tokens match, the request is allowed; otherwise, it is rejected.

**When to Use:**

* **Session-based authentication** using cookies.
* Traditional **Spring MVC** or server-rendered web applications.
* Any application where the browser automatically sends **session cookies**.

**When CSRF Protection is Usually Disabled:**

* **Stateless REST APIs** using **JWT** or **OAuth 2.0 Bearer Tokens**.
* APIs where authentication is sent explicitly in the **Authorization** header instead of cookies.

**Simple Spring Security Example:**


**Step 1: Add Spring Security dependency**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

**CSRF Enabled (Default):**

```java id="r4t9mk"
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated())
        .formLogin();

    return http.build();
}
```

**Disable CSRF for JWT-Based REST API:**

```java id="v8n2qp"
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated());

    return http.build();
}
```

## 17. What is XSS Protection?

**XSS (Cross-Site Scripting) Protection** is a **security mechanism** that prevents attackers from injecting and executing **malicious JavaScript code** in a web application. It protects users from attacks such as **cookie theft**, **session hijacking**, and **data manipulation**.

**Example:** If a comment field allows users to submit `<script>alert('Hacked')</script>`, an attacker could execute JavaScript in other users' browsers. **XSS protection blocks or sanitizes such input.**

**Key Features:**

* Prevents **malicious script injection**.
* Protects against **session hijacking** and **cookie theft**.
* Uses **input validation** and **output encoding**.
* Can be enhanced with **Content Security Policy (CSP)** headers.
* Important for any application that displays **user-generated content**.

**How It Works:**

1. The application receives input from the user.
2. Before storing or displaying it, the input is **validated** or **sanitized**.
3. When rendering the data in HTML, special characters are **encoded** (e.g., `<` becomes `&lt;`).
4. The browser displays the text as plain content instead of executing it as JavaScript.

**Common Types of XSS:**

* **Stored XSS** – Malicious script is stored in the database and executed when users view the page.
* **Reflected XSS** – Malicious script comes from the request and is immediately reflected in the response.
* **DOM-based XSS** – The vulnerability exists in client-side JavaScript code.

**When to Use:**

* Applications with **forms**, **comments**, or **search fields**.
* Websites displaying **user-generated content**.
* Any **web application** that accepts and renders user input.

**Simple Example:**

**Step 1: Add security headers via Spring Security**
```java
@Bean
// filterChain 
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .headers(headers -> headers
            .xssProtection(xss -> xss.enable())                        // X-XSS-Protection header
            .contentSecurityPolicy(csp ->
                csp.policyDirectives("script-src 'self'"))             // CSP header
        );
    return http.build();
}
```

**Step 2: Sanitize user input using OWASP Java HTML Sanitizer**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>com.googlecode.owasp-java-html-sanitizer</groupId>
    <artifactId>owasp-java-html-sanitizer</artifactId>
    <version>20220608.1</version>
</dependency>
```

```java
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

@Service
public class SanitizationService {
    private static final PolicyFactory POLICY = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

    public String sanitize(String input) {
        return POLICY.sanitize(input);   // strips <script> tags etc.
    }
}
```

**Step 3: Encode output in Thymeleaf (auto-escapes by default)**
```html
<!-- Thymeleaf escapes HTML by default — safe -->
<p th:text="${userComment}"></p>

<!-- th:utext is UNSAFE — renders raw HTML, avoid it -->
<p th:utext="${userComment}"></p>
```

**Step 4: Use Content Security Policy (CSP) header**
```java
.contentSecurityPolicy(csp ->
    csp.policyDirectives("default-src 'self'; script-src 'self'; object-src 'none'"))
```

Now, `<script>` is displayed as plain text instead of being executed.

**How to Prevent XSS:**

* **Validate and sanitize user input**.
* **Encode output** before rendering HTML.
* Use **Content Security Policy (CSP)**.
* Avoid directly inserting untrusted data into the **DOM**.
* Keep frameworks and libraries **updated**.


## 18. What is Input Validation?

**Input Validation** — ensuring data received from the user is correct, safe, and expected before processing it.

**Why it matters:**
- Prevents SQL Injection, XSS, buffer overflows
- Ensures business rules are enforced (e.g., age > 0)
- Fails fast before bad data reaches DB


**Step 1: Add validation dependency**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

**Step 2: Annotate your DTO/model with constraints**
```java
public class UserRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 chars")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age must be under 120")
    private int age;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    // getters + setters
}
```

**Step 3: Enable validation in controller with `@Valid`**
```java
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest request) {
        // if validation fails, MethodArgumentNotValidException is thrown automatically
        return ResponseEntity.ok("User created");
    }
}
```

**Step 4: Handle validation errors globally**
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
          .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
```

**Step 5: Response when validation fails**
```json
{
  "name": "Name is required",
  "email": "Invalid email format",
  "age": "Age must be at least 18"
}
```


## 19: What is Filter Chain?

A **Filter Chain** is a sequence of **filters** that process an HTTP request and response **before** it reaches the controller and **after** the response is returned. In **Spring Security**, the filter chain is used to perform tasks like **authentication**, **authorization**, **JWT validation**, and **CSRF protection**.

**Key Features:**

* Processes requests in a **defined order**.
* Each filter performs a **specific task**.
* Can **modify**, **allow**, or **block** a request.
* Central part of **Spring Security**.
* Supports custom filters like **JWT authentication filters**.

**How It Works:**

1. A client sends an HTTP request.
2. The request passes through the **Filter Chain**.
3. Each filter checks or processes the request (e.g., validate JWT, check CSRF token).
4. If all filters pass, the request reaches the **Spring MVC Controller**.
5. The response travels back through the filter chain before being sent to the client.

**Typical Spring Security Filter Flow:**

```text id="j4v9qm"
Client Request
      │
      ▼
Security Filter Chain
      │
      ├── Authentication Filter
      ├── JWT Filter
      ├── CSRF Filter
      ├── Authorization Filter
      │
      ▼
Spring MVC Controller
      │
      ▼
Client Response
```

**When to Use:**

* **Authentication** and **authorization**.
* **JWT token validation**.
* **Logging** and **request tracking**.
* **CSRF**, **CORS**, and other security checks.
* Any requirement to process requests before they reach the controller.

**Simple Custom Filter Example:**

```java id="fc8p2x"
@Component
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Request URI: " + request.getRequestURI());

        filterChain.doFilter(request, response); // Pass request to next filter
    }
}
```

**Adding a JWT Filter to the Security Filter Chain:**

```java id="k7m3zn"
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated());

    return http.build();
}
```

## 20: What is SAML?

**SAML (Security Assertion Markup Language)** is an **XML-based authentication and authorization standard** that enables **Single Sign-On (SSO)**. It allows users to log in once and access multiple applications without entering their credentials again.

**Example:** A company employee logs into the corporate portal once and can then access applications like HR, email, and internal dashboards without separate logins.

**Key Features:**

* Provides **Single Sign-On (SSO)**.
* Uses **XML-based security assertions**.
* Eliminates the need to share passwords between applications.
* Supports **centralized authentication**.
* Widely used in **enterprise applications**.

**How It Works:**

1. The user tries to access an application (**Service Provider - SP**).
2. The application redirects the user to the **Identity Provider (IdP)**.
3. The user authenticates with the IdP.
4. The IdP generates a **SAML Assertion** (an XML document containing user identity and permissions).
5. The assertion is digitally signed and sent back to the Service Provider.
6. The Service Provider verifies the signature and grants access to the user.

**Main Components:**

* **Identity Provider (IdP)** – Authenticates the user (e.g., corporate login server).
* **Service Provider (SP)** – The application the user wants to access.
* **SAML Assertion** – XML document containing authentication and authorization information.

**When to Use:**

* **Enterprise Single Sign-On (SSO)**.
* Corporate applications with **centralized identity management**.
* Integration with identity providers like **Active Directory Federation Services (ADFS)** or **Okta**.
* Large organizations where users need access to multiple applications.

**Simple Spring Security SAML Configuration:**

```java id="saml9q"
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .anyRequest().authenticated())
        .saml2Login();

    return http.build();
}
```

The `.saml2Login()` configuration enables **SAML 2.0-based Single Sign-On** in a Spring Boot application.

**SAML Authentication Flow:**

```text id="sml4r8"
User
  │
  ▼
Service Provider (Application)
  │
  ▼
Identity Provider (Login Server)
  │
  ▼
SAML Assertion (XML)
  │
  ▼
Service Provider Validates Assertion
  │
  ▼
Access Granted
```


