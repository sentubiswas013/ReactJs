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


## 6. Why strings is immutable in Java?



A **`String`** in Java is **immutable**, which means **once a `String` object is created, its value cannot be changed**. If you modify a string, Java creates a **new object** instead of changing the existing one.

**How It Works**

When you modify a string, Java does not update the existing object. Instead, it creates a **new object** with the updated value.

```java
String str = "Hello";
str = str + " World";
```

**Internally:**

1. `"Hello"` object is created.
2. `str + " World"` creates a **new object** `"Hello World"`.
3. The variable `str` now points to the new object.

**Key Features**

* **Immutable**: Value cannot be changed after creation.
* **Thread-Safe**: Multiple threads can safely share the same string object.
* **Memory Efficient**: Supports the **String Pool**, where identical string literals share the same object.
* **Secure**: Used for sensitive data like **class names, file paths, and URLs** because values cannot be altered.

**Why is String Immutable?**

1. **Security**

   * Prevents accidental or malicious modification of important values such as file paths, network connections, and class loading information.

2. **Thread Safety**

   * Since the object never changes, multiple threads can use the same string without synchronization.

3. **String Pool Optimization**

   * Java stores string literals in the **String Pool** and reuses them, saving memory.

4. **Caching**

   * Methods like `hashCode()` can cache their result because the string value never changes.

**When to Use**

* Use **`String`** for **fixed or constant text**.
* Use **`StringBuilder`** or **`StringBuffer`** when frequent modifications are required.

**Code Example**

```java
String s1 = "Java";
String s2 = s1.concat(" Programming");

System.out.println(s1); // Java
System.out.println(s2); // Java Programming
```

In the above example, `concat()` does **not** change `s1`. It creates a **new object** and stores it in `s2`.

**String Pool Example**

```java
String a = "Hello";
String b = "Hello";

System.out.println(a == b); // true
```

Both `a` and `b` point to the **same object** in the **String Pool**, which is possible because strings are immutable.

**Easy Way to Remember**

* **Immutable = Cannot Change**
* **Any modification = New Object Created**
* **Original String Always Remains Unchanged**


## 7. What is the difference between final, finally, and finalize?



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

**Why to Use**

* To define **logical equality** instead of reference equality.
* To ensure objects work correctly in **`HashMap`**, **`HashSet`**, and other hash-based collections.
* To improve lookup performance using hash codes.

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

**Why to Use**

* Ensures correct behavior of **`HashMap`**, **`HashSet`**, and other hash-based collections.
* Improves lookup performance.
* Maintains consistency between **logical equality** and **hash values**.

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

## 15. Summary Data Types and Variables

**🔹 Data Types**

* **Data Type:** Specifies what type of value a variable can store.
* **Primitive Data Types:** Basic types that store actual values (int, float, char, boolean).
* **Non-Primitive Data Types:** Store references to objects (String, Arrays, Classes).
* **Type Casting:** Converting one data type to another (implicit/explicit).


**🔹 Variables**

* **Variable:** A named memory location used to store data.
* **Local Variable:** Declared inside a method and accessible only within it.
* **Instance Variable:** Declared inside a class, unique for each object.
* **Static Variable:** Shared among all objects of a class.
* **Final Variable:** Value cannot be changed once assigned (constant).


**🔹 Memory Concept**

* **Primitive Variables:** Stored directly in stack memory.
* **Reference Variables:** Stored in stack but point to objects in heap memory.


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

**Why to Use**

* To represent real-world entities such as **User**, **Car**, or **Employee**.
* To organize data and behavior together.
* To support **Object-Oriented Programming (OOP)**.
* To improve code reusability and maintainability.

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


3. Difference between class and object?

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

**Why to use**

* **Class** helps in designing structure
* **Object** allows real-world usage of that structure in programs

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

**Why to use**

* Improves **code readability**
* Provides **flexibility** to handle different inputs with same operation name
* Reduces method naming complexity

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

**Why to use**

* To provide **specific implementation** in child class
* To achieve **runtime polymorphism**
* To extend or modify **parent behavior**

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

**Why to use**

* To support **dynamic loading of classes**
* To improve **modularity and flexibility**
* To separate **system, framework, and user classes**

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


## 6. How do you create an immutable class in Java?

An **immutable class** in Java is a class whose **state (data)** cannot be changed after they are created. To create an immutable class:


**Key Features**

* Object state is **final and unchangeable** after creation
* Class is usually marked as **final**
* Fields are **private and final**
* No **setter methods**
* Only **getter methods** are provided
* Ensures **thread-safety by default**

**How it works**
All values are set using a **constructor**, and after object creation, there is **no way to modify the internal state**.

**Why to use**

* Provides **thread safety without synchronization**
* Ensures **data security and consistency**
* Useful in **caching, multi-threading, and security-sensitive applications**

**When to use**

* When object data should remain **constant after creation**
* In classes like **String, Wrapper classes, Date-Time APIs**

**Code Example**

```java id="1i6tx1"
final class Student {
    private final String name;
    private final int age;

    public Student(String name, int age) {
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

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Rahul", 22);

        System.out.println(s1.getName());
        System.out.println(s1.getAge());
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

**Why to use**

* To ensure **proper initialization** of objects
* To reduce need for manual setup after object creation
* To enforce **consistent object state**

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

**Why to use**

* To allow object creation even when no initialization logic is written
* Provides **basic safe object state**

**When to use**

* When no custom initialization is needed
* When we want objects with **default values only**

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

**Why to use**

* To share common data across all objects
* To save memory using **single copy of variable/method**
* To provide **utility methods (e.g., Math class methods)**

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


## 19. XYZ

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


## 22. What is singleton class?

A **Singleton Class** is a class that **allows only one object (instance) to be created** throughout the application and provides a **global access point** to that instance.

**Key Features**

* Ensures **only one instance** of the class exists.
* Provides a **global access method** to get that instance.
* Constructor is made **`private`** to prevent object creation using `new`.
* Commonly used for **logging, configuration, caching, and database connection management**.

**How It Works**

1. Make the **constructor `private`** so no other class can create an object.
2. Create a **static instance** of the class.
3. Provide a **public static method** (e.g., `getInstance()`) to return that single instance.

**When to Use**

* When exactly **one shared object** is needed across the application.
* For **configuration classes**, **logging utilities**, **cache managers**, or **resource managers**.
* In **Spring**, beans are **singleton by default**.


Examples:

* **Logger**
* **Configuration Manager**
* **Cache Manager**
* **Database Connection Manager**

**Code Example**

```java
class Singleton {
    private static Singleton instance;

    private Singleton() { }   // private constructor

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// How t use
public class Test {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2); // true (same object)
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

## 31. Summary Classes and Objects

**🔹 Class**

* **Class:** A blueprint/template used to create objects.
* **Fields (Variables):** Represent the state of the object.
* **Methods:** Define the behavior of the object.
* **Constructor:** Special method used to initialize object values.


**🔹 Object**

* **Object:** Instance of a class with state and behavior.
* **State:** Represented by variables (data).
* **Behavior:** Represented by methods (functions).
* **Memory:** Objects are created in heap memory.


**🔹 Key Concepts**

* **Encapsulation:** Wrapping data and methods together and restricting access using private/public.
* **this keyword:** Refers to the current object.
* **new keyword:** Used to create an object.



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

**Why to Use**

* To reuse existing code
* To create a hierarchy between classes
* To avoid duplicate code
* To support polymorphism

**When to Use**

Use **Inheritance** when there is an **IS-A Relationship** between classes.

Examples:

* **Dog IS-A Animal**
* **Car IS-A Vehicle**
* **Manager IS-An Employee**



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

**Why to use**

* To achieve **code reusability**
* To reduce **duplication of code**
* To implement **inheritance and polymorphism**

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

**Why to use**

* To achieve **inheritance and reusability**
* To build **logical hierarchy of classes**
* To enable **polymorphism**

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


**Why to use**

* To represent **real-world relationships clearly**
* To improve **code reusability and modularity**
* To choose correct **dependency strength (strong vs weak)**


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


## 10. What is Multilevel Inheritance?

When a class inherits from a class, which already inherits another class.

```java
class Animal {
    void eat() {}
}

class Dog extends Animal {
    void bark() {}
}

class Puppy extends Dog {
    void weep() {}
}
```


## 11. What is Hierarchical Inheritance?

Multiple child classes inherit from one parent class.

```java
class Animal {
    void eat() {}
}

class Dog extends Animal {}
class Cat extends Animal {}
```

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


**Why to use:**

* To achieve **runtime polymorphism**
* To provide **specific behavior in child classes**
* To improve **flexibility and reusability**


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


## 24. Can we change access modifier while overriding?

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


**Why to use:**

* To initialize **common properties** for all child classes
* To reduce **code duplication**
* To ensure proper **object initialization in inheritance hierarchy**


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

## 32. Summary Java Inheritance

**🔹 Basics**

* **Inheritance:** Mechanism where one class acquires properties and behavior of another class.
* **Parent Class (Super Class):** Class whose properties are inherited.
* **Child Class (Sub Class):** Class that inherits from parent class.
* **extends keyword:** Used to inherit a class.


**🔹 Key Concepts**

* **Code Reusability:** Avoids duplication by reusing existing code.
* **Method Overriding:** Child class provides its own implementation of parent method.
* **super keyword:** Used to access parent class methods/constructors.
* **IS-A Relationship:** Represents inheritance relationship (e.g., Dog is an Animal).


**🔹 Types of Inheritance**

* **Single Inheritance:** One parent → one child.
* **Multilevel Inheritance:** Chain of inheritance (A → B → C).
* **Hierarchical Inheritance:** One parent → multiple children.
* **Multiple Inheritance:** Not supported with classes (supported via interfaces).


**⚠️ Important Points**

* Constructors are **not inherited**, but can be called using `super()`.
* Private members are **not directly accessible** in child class.



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

**Why to Use**

* To provide a common base for related classes
* To avoid code duplication
* To enforce certain methods in child classes
* To support code reusability

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

**Why to Use Interface**

* To achieve **Loose Coupling**
* To support **Multiple Inheritance**
* To define a common contract

**Why to Use Abstract Class**

* To share common code among subclasses
* To avoid code duplication
* To provide a common base implementation

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

**Why to Use**

* To handle errors gracefully
* To prevent application termination
* To improve application reliability
* To provide meaningful error messages

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

## 7. What is exception chaining?

**Exception chaining** links exceptions together, preserving the original cause when wrapping exceptions. It helps maintain the complete error trail for better debugging.

- Preserves original exception information
- Uses initCause() method or constructor parameter
- Helps in debugging complex error scenarios
- Maintains exception stack trace

```java
try {
    // Some database operation
    connectToDatabase();
} catch (SQLException e) {
    // Chain the original exception
    throw new ServiceException("Service failed", e);
}

// Or using initCause()
RuntimeException re = new RuntimeException("Wrapper exception");
re.initCause(originalException);
throw re;
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


## 11. What is try-with-resources?

**Try-with-resources** in **Java** is a feature used to **automatically close resources** (like files or database connections) after the program finishes using them.


```java
// Old way
FileReader file = null;
try {
    file = new FileReader("data.txt");
} finally {
    if (file != null) file.close();
}

// Try-with-resources
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    System.out.println(br.readLine());
} // Automatically closed
```

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

**How it Works**

The Collection Framework provides **interfaces** and their **implementations**:

| Interface | Implementation Examples   |
| --------- | ------------------------- |
| **List**  | ArrayList, LinkedList     |
| **Set**   | HashSet, TreeSet          |
| **Queue** | PriorityQueue, LinkedList |
| **Map**   | HashMap, TreeMap          |

You program against the interface and use a specific implementation based on your requirement.

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

**Why to Use**

* Use **`ArrayList`** when your application performs **frequent reads or searches**.
* Use **`LinkedList`** when your application performs **frequent insertions or deletions**, especially at the beginning or middle of the list.

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

**Why to Use**

* Use **`HashMap`** when you need **fast access** and do not care about the order of keys.
* Use **`TreeMap`** when you need **sorted keys** or operations like finding the smallest, largest, or a range of keys.

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

**Why to Use**

* Use **`HashMap`** for **single-threaded** applications or when synchronization is handled externally.
* Use **`Hashtable`** only for maintaining compatibility with legacy code. For modern multi-threaded applications, **`ConcurrentHashMap`** is preferred.

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

**Why to Use Collision Handling**

* Prevents data from being overwritten.
* Allows multiple keys to coexist in the same bucket.
* Maintains efficient lookup and insertion performance.

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

**Why to Use**

* Use **`Comparable`** when there is a **single, default sorting order**.
* Use **`Comparator`** when you need **multiple sorting criteria** or cannot modify the existing class.

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

**Why to Use**

* Prevents **memory leaks**.
* Useful for **caching** and storing temporary metadata.

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

**Why to Use**

* When object **identity** is more important than object content.
* Used internally by frameworks and serialization libraries.

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

**Why to Use**

* When the order of elements must be preserved.
* For implementing **LRU (Least Recently Used) caches**.

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

**Why to Use**

* Efficient priority-based processing.
* Frequently used in **scheduling**, **task management**, and **graph algorithms** like Dijkstra's algorithm.

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

**Why to Use**

* Use **`HashMap`** when thread safety is not required and maximum performance is needed.
* Use **`ConcurrentHashMap`** when multiple threads need to access and modify the map safely.

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

**Why to Use**

* Use **`List`** when order and duplicates matter.
* Use **`Set`** when uniqueness is required.
* Use **`Map`** when data should be stored and retrieved using a key.
* Use **`Queue`** for scheduling, buffering, or task processing.

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

**Why to Use**

* Use **`Array`** when the number of elements is known and fixed.
* Use **`List`** when the number of elements can change dynamically or when you need collection utility methods.

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

## 15. How does map.get(key) work internally?

`map.get(key)` first calculates the key's `hashCode()` to find the bucket. Then it uses `equals()` to locate the exact key in that bucket and returns the associated value. This is why HashMap retrieval is usually O(1).

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "John");

String name = map.get(101);
```

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

**Why to Use Big O Notation**

* Measures algorithm efficiency.
* Helps compare different solutions.
* Important for designing scalable and high-performance applications.

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




## 12. Java Collections Framework

**🔹 Basics**

* **Java Collections Framework (JCF):** A set of classes and interfaces used to store and manipulate groups of data.
* **Collection Interface:** Root interface for most collection types (List, Set, Queue).
* **Map Interface:** Stores key-value pairs (not part of Collection interface).
fWhat is JDBC

**🔹 List (Ordered, Allows Duplicates)**

* **List:** Maintains insertion order and allows duplicate elements.
* **ArrayList:** Dynamic array, fast for read operations.
* **LinkedList:** Doubly linked list, fast for insert/delete operations.
* **Vector:** Thread-safe but slower (legacy).


**🔹 Set (No Duplicates)**

* **Set:** Does not allow duplicate elements.
* **HashSet:** Unordered, fast performance.
* **LinkedHashSet:** Maintains insertion order.
* **TreeSet:** Sorted order (uses Red-Black Tree).


**🔹 Map (Key-Value Pair)**

* **Map:** Stores data as key-value pairs.
* **HashMap:** Unordered, allows one null key.
* **LinkedHashMap:** Maintains insertion order.
* **TreeMap:** Sorted by keys.
* **Hashtable:** Thread-safe but legacy.


**🔹 Queue**

* **Queue:** Follows FIFO (First In First Out).
* **PriorityQueue:** Elements processed based on priority.
* **Deque:** Double-ended queue (add/remove from both ends).


**🔹 Important Concepts**

* **Iterator:** Used to traverse collection elements.
* **Comparable:** Used for natural sorting.
* **Comparator:** Used for custom sorting.
* **Collections Class:** Utility methods (sort, reverse, shuffle).


**⚠️ Key Differences**

* **List vs Set:** List allows duplicates, Set does not.
* **ArrayList vs LinkedList:** ArrayList fast read, LinkedList fast insert/delete.
* **HashMap vs TreeMap:** HashMap unordered, TreeMap sorted.


# ✅ 08. Java Multithreading & Synchronization 

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


**Why to Use Threads**

* Improve **performance**
* Enable **concurrent execution**
* Better CPU utilization
* Faster task processing
* Responsive applications


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


## 4. What are `sleep()` vs `wait()` in Multithreading?


* **sleep()** is a method of the **Thread class** used to pause execution of a thread for a fixed time.
* **wait()** is a method of the **Object class** used for inter-thread communication, where a thread waits until it is notified.


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


**Why to Use**

* **sleep()** → Delay execution
* **wait()** → Coordinate between threads


**When to Use**

* **sleep()**

  * Adding delay
  * Retry mechanisms
  * Timed operations

* **wait()**

  * Producer-consumer problem
  * Thread communication
  * Shared resource coordination


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


**sleep() vs wait()**

| **sleep()**               | **wait()**                  |
| ------------------------- | --------------------------- |
| Thread class method       | Object class method         |
| Does NOT release lock     | Releases lock               |
| Fixed time delay          | Waits until notified        |
| No synchronization needed | Requires synchronized block |
| Used for pause            | Used for communication      |


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

**Why to Use**

* Prevent **data corruption**
* Ensure **consistency in shared resources**
* Avoid **race conditions**
* Maintain correct program behavior in multi-threading

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


**Why to Use**

* Ensure **data visibility** in multi-threading
* Avoid stale or cached values
* Lightweight alternative to locks for simple cases


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

**volatile vs synchronized**

| **volatile**                            | **synchronized**               |
| --------------------------------------- | ------------------------------ |
| Ensures visibility only                 | Ensures visibility + atomicity |
| No locking                              | Uses locks                     |
| Faster                                  | Slower                         |
| Not thread-safe for compound operations | Fully thread-safe              |


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


**Why to Use**

* To manage **limited resources safely**
* To avoid **system overload**
* To ensure **controlled concurrency**
* To improve **resource utilization efficiency**


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



# ✅ 09. Java Concurrency 

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

**Why to Use**

* Improve application **performance**
* Handle multiple tasks simultaneously
* Reduce user waiting time
* Better responsiveness in web and desktop applications

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

**Why to Use**

* Improve application responsiveness
* Handle many user requests efficiently
* Better resource utilization
* Increase overall throughput

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

**Why to Use**

* Prevent data corruption in multi-threaded environments
* Better performance than **Hashtable** or synchronized collections
* Safe concurrent access to shared data

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

**Why to Use**

* Improves application performance
* Reduces memory and CPU overhead
* Prevents creating too many threads
* Simplifies concurrent programming

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

**Why to Use**

* Avoid manual thread creation
* Better resource management
* Improves performance through thread reuse
* Simplifies concurrent programming

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

**Why to Use**

* Wait for multiple tasks to finish
* Coordinate thread execution
* Simplify multi-threaded workflows

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

**Why to Use**

* Coordinate multiple threads
* Ensure threads reach the same execution point
* Useful for phased processing

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

**Why to Use**

* More flexible than **synchronized**
* Better control over locking behavior
* Supports timeout and interrupt handling
* Useful in complex concurrent applications

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

**Why to Use**

* Improves **thread safety**
* Prevents accidental data modification
* Makes code easier to understand and maintain
* Reduces synchronization issues

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

**Why to Use**

* Improve application performance
* Avoid blocking the main thread
* Execute multiple tasks concurrently
* Simplify asynchronous programming

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



# ✅ 10. Java JVM & Memory Management 

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

**Why to Use**

* **Stack** provides **fast and efficient** memory management for method execution.
* **Heap** allows **dynamic object creation** and sharing of objects across methods and threads.

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

**Why to Use**

* Eliminates manual memory management
* Reduces memory-related bugs
* Prevents memory leaks
* Improves application reliability

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

**Why to Use**

* Improves **GC performance** by collecting short-lived objects quickly.
* Reduces the time spent scanning the entire heap.
* Minimizes application pause times and improves memory efficiency.

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

**Why to Use**

* Prevents accidental deletion of objects still in use.
* Enables efficient **memory management**.
* Helps avoid **memory leaks** and **OutOfMemoryError**.

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


## 9. Java Memory Management Summary?


**🔹 JVM Basics**

* **JVM:** Runtime engine that executes Java bytecode and manages memory.
* **JDK vs JRE vs JVM:** JDK = development tools, JRE = runtime, JVM = execution engine.
* **ClassLoader:** Loads class files into memory dynamically at runtime.


**📦 Memory Areas**

* **Heap:** Shared memory where objects are stored and managed by GC.
* **Stack:** Thread-specific memory for method calls and local variables.
* **Metaspace:** Stores class metadata in native memory (Java 8+).
* **PC Register:** Holds current executing instruction per thread.
* **Native Method Stack:** Executes native (C/C++) methods.


**🧠 PermGen vs Metaspace**

* **PermGen:** Fixed-size memory (pre-Java 8) for class metadata, prone to memory issues.
* **Metaspace:** Replaces PermGen, uses native memory and grows dynamically.


**⚖️ Heap vs Stack**

* **Heap:** Shared, object storage, GC-managed, slower access.
* **Stack:** Thread-local, method execution, fast and auto-managed.


**🧩 Heap Structure**

* **Young Generation:** Where new objects are created.
* **Eden Space:** Initial allocation area for new objects.
* **Survivor Space:** Stores objects that survive minor GC.
* **Old Generation:** Stores long-lived objects.


**♻️ Garbage Collection**

* **GC:** Removes unreachable objects to free memory.
* **Minor GC:** Cleans Young Generation.
* **Major GC:** Cleans Old Generation.
* **Full GC:** Cleans entire heap.
* **Stop-The-World:** Application pauses during GC execution.


**⚙️ GC Algorithms**

* **Serial GC:** Single-threaded, simple but slow.
* **Parallel GC:** Multi-threaded, high throughput.
* **G1 GC:** Region-based, low pause time (modern default).
* **CMS GC:** Low pause but deprecated.


**⚙️ JVM Internals**

* **JIT Compiler:** Converts bytecode to native code for faster execution.
* **Bytecode:** Platform-independent intermediate code.
* **Interpreter vs JIT:** Interpreter executes line-by-line, JIT optimizes frequently used code.


**⚠️ Errors**

* **OutOfMemoryError:** Heap or Metaspace memory exhausted.
* **StackOverflowError:** Stack memory exceeded (deep recursion).


**🔍 Advanced Concepts**

* **Memory Leak:** Objects not GC’d due to active references.
* **Heap Dump:** Memory snapshot used for debugging.
* **GC Tuning:** Adjusting JVM parameters for performance.


**⚙️ JVM Parameters**

* **-Xms:** Initial heap size.
* **-Xmx:** Maximum heap size.
* **-XX:+UseG1GC:** Enables G1 garbage collector.
* **-XX:MaxMetaspaceSize:** Limits Metaspace size.



# ✅ 11. Java Input/Output (I/O) 

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

# ✅ 12. Java Generics 

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

**Why to Use**

* Prevents **ClassCastException**.
* Reduces runtime errors.
* Makes code more reusable and maintainable.
* Improves compile-time checking.

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

# ✅ 13. Java Annotations & Reflection 


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

**Why to Use**

* Reduces boilerplate code and manual configuration.
* Makes code cleaner and easier to maintain.
* Enables features like **dependency injection**, **ORM mapping**, and **unit testing**.

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

**Why to Use**

* Prevents incorrect annotation usage with **`@Target`**.
* Improves API documentation with **`@Documented`**.
* Reduces duplicate annotations in inheritance hierarchies with **`@Inherited`**.

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

**Why to Use**

* Controls whether an annotation is needed only during compilation or also at runtime.
* Improves performance by keeping annotations only as long as required.
* Enables frameworks like **Spring** and **Hibernate** to read annotations dynamically.

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


# ✅ 14. Java - Servlets and JSP (Web Development)

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

**Why to Use**

* Reduces manual SQL writing
* Improves developer productivity
* Makes code more object-oriented
* Simplifies CRUD operations

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

**Why to Use**

* Simplifies database operations
* Reduces SQL and JDBC code
* Improves productivity
* Provides a standard persistence approach

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

**Why to Use**

* Reduces manual SQL writing
* Simplifies database interaction
* Improves productivity
* Provides advanced ORM features
* Makes applications easier to maintain

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


## 8. Difference between `save() and `persist()`


Both **`save()`** and **`persist()`** are used to store an entity in the database, but they belong to different APIs and have some behavioral differences.

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

* **JPQL** works with **Java Entity objects**.
* **Native Query** uses **database-specific SQL** directly.

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
@Query("SELECT u FROM User u WHERE u.name = :name")
User findByName(String name);
```

**Native Query:** uses actual SQL with real table and column names. Use it when you need database-specific features or complex queries JPQL can't handle.

```java id="m4x8qr"
@Query(value = "SELECT * FROM users WHERE name = :name",
       nativeQuery = true)
User findByNameNative(String name);
```

## 13. What are JPA Cascade Types?

Cascade means — *when you do an operation on a parent entity, automatically apply it to child entities too.*

| Type | Behavior |
|---|---|
| `PERSIST` | Save child when parent is saved |
| `MERGE` | Update child when parent is updated |
| `REMOVE` | Delete child when parent is deleted |
| `REFRESH` | Refresh child when parent is refreshed |
| `DETACH` | Detach child when parent is detached |
| `ALL` | Apply all of the above |

```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderItem> items;
```

Be careful with `REMOVE` — it can delete child records you didn't intend to delete.


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

**Why to Use**

* Reduces manual database updates
* Simplifies code
* Improves developer productivity
* Ensures entity changes are persisted automatically

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


# ✅ 15. Java Lambda Expressions & Streams API 

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


**Why to use:**

* To write **clean and readable code**
* To reduce **boilerplate loops**
* To improve **performance with parallel processing**
* To support **functional programming style**


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


**Why to use:**

* To improve **performance on large data sets**
* To utilize **multi-core CPU power**
* To reduce **processing time for heavy operations**


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

## 7. What is the difference between Collection and Stream?

A **Collection** is a **data structure** that stores elements in memory, like `List`, `Set`, or `Map`. It holds data and allows operations such as add, remove, or iterate, and it can be traversed multiple times.

A **Stream** is **not a data structure**; it’s a **data-processing abstraction**. It doesn’t store data but processes elements from a collection or other sources. Streams are **one-time use**, support **functional operations** like `filter` and `map`, and enable easy **parallel processing**.

| **Feature**      | **Collection**                                              | **Stream**                                                             |
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


A **Collection** is a data structure that stores and manages **data in memory**, while a **Stream** is a sequence of elements used to **process data in a functional way without storing it**.


**Key Differences:**

| **Aspect**       | **Collection**                                | **Stream**                                           |
| ---------------- | --------------------------------------------- | ---------------------------------------------------- |
| **Nature**       | Stores **data**                               | Processes **data**                                   |
| **Storage**      | Holds elements in **memory**                  | Does not store data, works as a **data pipeline**    |
| **Modification** | Can be **modified (add/remove elements)**     | **Immutable**, does not modify source data           |
| **Iteration**    | Uses **external iteration (for/while loops)** | Uses **internal iteration (Stream API operations)**  |
| **Reusability**  | Can be **reused multiple times**              | Can be used **only once**                            |
| **Processing**   | Processes data **manually**                   | Uses **functional operations (filter, map, reduce)** |


**How It Works**

1. Create a stream from a collection.
2. Apply one or more **intermediate operations** to build the pipeline.
3. Call a **terminal operation**, which executes all intermediate operations and produces the final output.


**When to use:**

* Use **Intermediate Operations** to **filter, transform, or sort** data.
* Use **Terminal Operations** when you need the **final result** or want to **perform an action**.

**Code Example:**

**Collection Example:**

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
```

**Stream Example:**

```java
import java.util.*;
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

numbers.stream()
    .filter(n -> n > 2)     // Intermediate - lazy
    .map(n -> n * 2)        // Intermediate - lazy
    .sorted()               // Intermediate - lazy
    .forEach(System.out::println); // Terminal - triggers execution
```

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


# ✅ 16. Java JDBC 

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

**Q: Why is `<scope>provided</scope>` used for the Servlet API?**

**Answer:** Because **Tomcat already provides the Servlet API** at runtime, so it should not be packaged inside the WAR.

**Q: What is the purpose of `server.xml`?**

**Answer:** It configures the **Tomcat server**, including **ports**, **connectors**, and server settings.

**Q: What is the purpose of `web.xml`?**

**Answer:** It defines **Servlets**, **URL mappings**, **filters**, **listeners**, and other web application configurations.



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



# ✅ 17. Java Design Patterns 

## 0. What are SOLID principles?

**SOLID** is a set of **five object-oriented design principles** that help developers write **clean, maintainable, scalable, and loosely coupled code**.

| **Principle**                                 | **Meaning**                                                                                                      |
| --------------------------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| **S - Single Responsibility Principle (SRP)** | A class should have **only one reason to change**, meaning it should have **one responsibility**.                |
| **O - Open/Closed Principle (OCP)**           | Software entities should be **open for extension but closed for modification**.                                  |
| **L - Liskov Substitution Principle (LSP)**   | A subclass should be able to **replace its parent class** without changing the program's behavior.               |
| **I - Interface Segregation Principle (ISP)** | Clients should not be forced to depend on **interfaces they do not use**. Create **small, specific interfaces**. |
| **D - Dependency Inversion Principle (DIP)**  | High-level modules should depend on **abstractions (interfaces)**, not concrete implementations.                 |

**Key Features:**

* Promotes **loose coupling**.
* Improves **code reusability** and **maintainability**.
* Makes applications easier to **test** and **extend**.
* Reduces the impact of future changes.

**How it Works:**

* Break responsibilities into smaller classes (**SRP**).
* Add new features by extending code instead of modifying existing code (**OCP**).
* Ensure subclasses behave correctly when replacing parent classes (**LSP**).
* Create focused interfaces instead of large, general ones (**ISP**).
* Depend on interfaces and use **Dependency Injection** (**DIP**).

**When to Use:**

* Designing **object-oriented applications**.
* Building **Spring Boot** or **enterprise Java** applications.
* When writing code that needs to be **scalable, testable, and maintainable**.

**Code Example (Dependency Inversion Principle):**

```java id="k3m8pq"
interface MessageService {
    void send(String message);
}

class EmailService implements MessageService {
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class Notification {
    private MessageService service;

    public Notification(MessageService service) {
        this.service = service;
    }

    public void notifyUser() {
        service.send("Hello User");
    }
}
```

**Easy Memory Trick:**
**S → One Responsibility**
**O → Open for Extension, Closed for Modification**
**L → Subclass should replace Parent**
**I → Small Specific Interfaces**
**D → Depend on Interfaces, Not Implementations**


**Example:**
**S — Single Responsibility Principle (SRP)**

One class should have only one responsibility.

use case:  

❌ Wrong:

```java
class OrderService {
    public void createOrder() {
        // create order
    }

    public void sendEmail() {
        // send email
    }
}
```

✅ Correct:

```java
class OrderService {
    public void createOrder() {
        // create order
    }
}

class EmailService {
    public void sendEmail() {
        // send email
    }
}
```


**O — Open/Closed Principle (OCP)**

Open for extension, closed for modification.

```java
// OCP: Open for extension, closed for modification

// This is the abstraction (interface)
// We can add new payment types without changing existing code
interface Payment {
    void pay(); // common method for all payment types
}

// Concrete implementation 1
class CardPayment implements Payment {
    public void pay() {
        // specific logic for card payment
        System.out.println("Paid by Card");
    }
}

// Concrete implementation 2
class UpiPayment implements Payment {
    public void pay() {
        // specific logic for UPI payment
        System.out.println("Paid by UPI");
    }
}

// This class uses the abstraction (Payment interface)
// It does NOT depend on concrete classes like CardPayment or UpiPayment
class PaymentService {

    // This method is CLOSED for modification
    // We don't need to change this method when new payment types are added
    public void processPayment(Payment payment) {
        // This is OPEN for extension because any new Payment type can be passed here
        payment.pay();
    }
}

public class Main {
    public static void main(String[] args) {

        // Create service object
        PaymentService service = new PaymentService();

        // Using Card payment
        Payment p1 = new CardPayment();
        service.processPayment(p1);

        // Using UPI payment
        Payment p2 = new UpiPayment();
        service.processPayment(p2);

        // If we add a new payment type (e.g., WalletPayment),
        // we DO NOT modify PaymentService
        // We only create a new class implementing Payment
    }
}

// Output:
// Paid by Card
// Paid by UPI
```

Now we can add **NetBankingPayment** without changing existing code.


**L — Liskov Substitution Principle (LSP)**

Child class should replace parent class without breaking code.

```java
// LSP: Liskov Substitution Principle
// Objects of a child class should be able to replace objects of the parent class
// WITHOUT breaking the expected behavior of the program.

class Bird {

    // Base class method
    // Defines general behavior that all birds SHOULD follow
    public void fly() {
        System.out.println("Bird can fly");
    }
}

// Child class extending Bird
class Sparrow extends Bird {

    // Overriding the parent method
    // Behavior is consistent with parent (still "fly")
    @Override
    public void fly() {
        System.out.println("Sparrow can fly");
    }
}

public class Main {
    public static void main(String[] args) {

        // Parent object
        Bird b = new Bird();
        b.fly(); 
        // Output: Bird can fly

        // Child object
        Sparrow s = new Sparrow();
        s.fly(); 
        // Output: Sparrow can fly

        // LSP in action (Runtime Polymorphism)
        // Parent reference holding child object
        Bird b2 = new Sparrow();

        // This should NOT break behavior
        // Even though reference is Bird, actual object is Sparrow
        b2.fly(); 
        // Output: Sparrow can fly

        // ✔ This works perfectly → LSP is satisfied
    }
}

/*
Key Interview Points:

1. LSP means:
   Child class must be substitutable for parent class.

2. In this example:
   - Sparrow IS-A Bird
   - Sparrow does not change expected behavior
   - So it follows LSP

3. Why this is correct:
   - No exception thrown
   - No unexpected behavior
   - Same logical meaning of "fly"

4. When LSP is violated:
   Example: If we create Penguin extends Bird but override fly() to throw exception
   → That breaks LSP because Penguin cannot truly replace Bird

5. Real-world idea:
   If parent says "can fly", child must honor that contract
*/


// Output:
Bird can fly
Sparrow can fly
Sparrow can fly
```

Bad example: Penguin cannot fly → violates LSP.


**I — Interface Segregation Principle (ISP)**

Create small interfaces.

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() {
        System.out.println("Human working");
    }

    public void eat() {
        System.out.println("Human eating");
    }
}

class Robot implements Workable {
    public void work() {
        System.out.println("Robot working");
    }
}

public class Main {
    public static void main(String[] args) {
        Workable w1 = new Human();
        w1.work();

        Eatable e1 = new Human();
        e1.eat();

        Workable w2 = new Robot();
        w2.work();
    }
}

// Output:
Human working
Human eating
Robot working
```

Robot does not implement eat() → Correct.


**D — Dependency Inversion Principle (DIP)**

Depend on abstraction, not concrete class.

```java
// DIP: Depend on abstraction, not concrete implementation

// Abstraction
interface Payment {
    void pay();
}

// Concrete implementation
class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

// High-level module (business logic)
class OrderService {

    // Depends on abstraction, not CardPayment directly
    private Payment payment;

    // Dependency is injected via constructor
    public OrderService(Payment payment) {
        this.payment = payment;
    }

    public void placeOrder() {
        payment.pay(); // uses abstraction
    }
}

public class Main {
    public static void main(String[] args) {

        // Inject concrete implementation at runtime
        Payment payment = new CardPayment();

        // OrderService is not tightly coupled to CardPayment
        OrderService orderService = new OrderService(payment);

        orderService.placeOrder(); // Output: Card payment
    }
}

/*
Interview Points (Quick):

- High-level class (OrderService) should NOT depend on low-level class (CardPayment)
- Both depend on abstraction (Payment)
- Easy to switch implementation (UPI, Wallet, etc.) without changing OrderService
*/

//Output:
Card payment
```

## 1. What are design patterns?

**Design Patterns** are **proven, reusable solutions** to common software design problems. They are **templates or best practices**, not complete code, that help developers write **clean, maintainable, and scalable** applications.

**Key Features:**

* Provide **standard solutions** to recurring problems.
* Promote **code reusability** and **loose coupling**.
* Improve **maintainability** and **readability**.
* Follow **object-oriented design principles** like **SOLID**.

**Types of Design Patterns:**

| **Category**   | **Purpose**                                   | **Examples**                            |
| -------------- | --------------------------------------------- | --------------------------------------- |
| **Creational** | Deals with **object creation**.               | **Singleton**, **Factory**, **Builder** |
| **Structural** | Deals with **class and object composition**.  | **Adapter**, **Decorator**, **Facade**  |
| **Behavioral** | Deals with **communication between objects**. | **Strategy**, **Observer**, **Command** |

**How it Works:**

* Identify a common design problem.
* Apply a suitable design pattern instead of creating a custom solution from scratch.
* The pattern provides a flexible and maintainable structure for the code.

**When to Use:**

* When building **large or scalable applications**.
* When the same design problem occurs repeatedly.
* To improve **code organization**, **extensibility**, and **maintainability**.
* Commonly used in **Spring Framework**, **Hibernate**, and enterprise Java applications.

**Code Example (Singleton Pattern):**

```java id="m8t2qx"
public class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

**Easy Memory Trick:**

* **Creational** → How objects are **created**.
* **Structural** → How objects are **connected**.
* **Behavioral** → How objects **communicate**.


1️⃣ **Creational Design Patterns**
* **Singleton** – Only one instance of a class is created.
* **Factory Method** – Creates objects without exposing creation logic.
* **Abstract Factory** – Creates families of related objects.
* **Builder** – Builds complex objects step by step.
* **Prototype** – Creates object by cloning existing object.

2️⃣ **Structural Design Patterns**
* **Adapter** – Converts one interface into another.
* **Bridge** – Separates abstraction from implementation.
* **Decorator** – Adds behavior dynamically.
* **Facade** – Provides simplified interface to complex system.
* **Proxy** – Controls access to an object.

3️⃣ **Behavioral Design Patterns**
* **Observer** – One-to-many dependency (used in event systems).
* **Strategy** – Select algorithm at runtime.
* **Command** – Encapsulates a request as an object.
* **State** – Changes behavior when state changes.
* **Template Method** – Defines skeleton of algorithm.
* **Iterator** – Sequential access to collection.


## 2. What is Singleton pattern?

The **Singleton Pattern** is a **Creational Design Pattern** that ensures a class has **only one instance** throughout the application and provides a **global access point** to that instance.

**Key Features:**

* Creates **only one object** of a class.
* Provides a **single global access point**.
* Saves **memory and resources**.
* Can be made **thread-safe** for multithreaded applications.

**How it Works:**

1. Make the **constructor `private`** so no other class can create an object.
2. Create a **static instance** of the class.
3. Provide a **public static method** (usually `getInstance()`) to return the single instance.

**When to Use:**

* For **logging** services.
* For **configuration** or **properties** management.
* For **cache** managers.
* When only **one shared instance** is required across the application.
* Commonly used in **Spring**, where beans are **singleton-scoped by default**.

**Code Example (Eager Initialization):**

```java id="f6w9kp"
public class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

**Thread-Safe Lazy Initialization:**

```java id="p4m8xt"
public class Singleton {
    private static Singleton instance;
    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2); // true (same object)
    }
}
```

**Advantages:**

* **Controlled access** to a single instance.
* **Reduces object creation overhead**.
* Easy to share common resources across the application.



## 3. How do you implement thread-safe Singleton?

**Thread-safe Singleton** can be implemented using synchronization, double-checked locking, or enum approach to prevent multiple instances in multithreaded environments.

**Methods:**
- Synchronized method (simple but slow)
- Double-checked locking (efficient)
- Enum singleton (best approach)
- Static inner class (lazy loading)

```java
// Double-checked locking
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;    
    private ThreadSafeSingleton() { }
    
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}

// Enum singleton - best approach
public enum EnumSingleton {
    INSTANCE;    
    public void doSomething() { }
}
```

## 4. What is Factory pattern?

The **Factory Pattern** is a **Creational Design Pattern** that provides a **centralized way to create objects** without exposing the object creation logic to the client. Instead of using `new` directly, the client asks the **factory** to create the required object.

**Key Features:**

* Encapsulates **object creation logic**.
* Promotes **loose coupling** between client and implementation classes.
* Makes code **easier to extend and maintain**.
* Follows the **Open/Closed Principle (OCP)**.

**How it Works:**

1. Create a **common interface** or abstract class.
2. Create multiple classes that implement the interface.
3. Create a **Factory class** that decides which object to create based on input.
4. The client requests an object from the factory instead of creating it directly.

**When to Use:**

* When the exact type of object is determined **at runtime**.
* When object creation logic is **complex**.
* When you want to reduce **tight coupling** between classes.
* Commonly used in **Spring**, **Hibernate**, and **JDBC DriverManager**.

**Code Example:**

```java
enum PaymentType {
    CARD, UPI
}

// Step 1: Interface
interface Payment {
    void pay();
}

// Step 2: Implementations
class CardPayment implements Payment {
    public void pay() {
        System.out.println("Card payment");
    }
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("UPI payment");
    }
}

// Step 3: Factory Class
class PaymentFactory {
    public static Payment getPayment(PaymentType type) {
        switch (type) {
            case CARD:
                return new CardPayment();

            case UPI:
                return new UpiPayment();

            default:
                throw new IllegalArgumentException("Invalid payment type");
        }
    }
}

// Step 4: Main Class
class FactoryPatternDemo {
    public static void main(String[] args) {
        Payment payment = PaymentFactory.getPayment(PaymentType.UPI);
        payment.pay();
    }
}
```

**Advantages:**

* Hides **object creation details**.
* Improves **code flexibility** and **reusability**.
* Makes it easier to add new implementations without changing client code.


## 5. What is Observer pattern?

The **Observer Pattern** is a **Behavioral Design Pattern** in which **one object (Subject)** automatically **notifies multiple dependent objects (Observers)** whenever its state changes. It establishes a **one-to-many relationship** between objects.

**Key Features:**

* Supports **one-to-many communication**.
* Promotes **loose coupling** between the subject and observers.
* Observers are **automatically updated** when the subject changes.
* Easy to **add or remove observers** without changing the subject.

**How it Works:**

1. A **Subject** maintains a list of registered **Observers**.
2. Observers **subscribe** to the subject.
3. When the subject's state changes, it calls the **`update()`** method on all observers.
4. Each observer performs its own action based on the notification.

**When to Use:**

* **Event-driven systems**.
* **Notification services** (Email, SMS, Push Notifications).
* **Stock price** or **weather update** applications.
* Commonly used in **Spring Event Listeners** and **GUI frameworks**.

**Code Example:**

```java id="n4v8kp"
interface Observer {
    void update(String message);
}

class EmailObserver implements Observer {
    public void update(String message) {
        System.out.println("Email received: " + message);
    }
}

class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
```

**Using the Observer Pattern:**

```java id="m7q2tx"
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.addObserver(new EmailObserver());

        subject.notifyObservers("Order Placed Successfully!");
    }
}
```

**Advantages:**

* Reduces **tight coupling** between objects.
* Makes the system **flexible** and **extensible**.
* Supports **event-based communication**.


## 6. What is Strategy pattern?

The **Strategy Pattern** is a **Behavioral Design Pattern** that defines a **family of algorithms**, encapsulates each one in a separate class, and allows them to be **interchanged at runtime** without changing the client code.

**Key Features:**

* Encapsulates **different algorithms** in separate classes.
* Allows changing the **behavior at runtime**.
* Promotes **loose coupling**.
* Follows the **Open/Closed Principle (OCP)** by allowing new strategies without modifying existing code.

**How it Works:**

1. Define a common **Strategy interface**.
2. Create multiple classes implementing the interface, each with a different algorithm.
3. The **Context** class holds a reference to a strategy.
4. At runtime, the client selects the required strategy, and the context delegates the work to it.

**When to Use:**

* When there are **multiple ways to perform the same task**.
* To avoid large **`if-else`** or **`switch`** statements.
* When the algorithm needs to be **changed dynamically**.
* Commonly used for **payment methods**, **sorting algorithms**, and **discount calculations**.

**Code Example:**

```java id="g8n4tx"
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class UpiPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

class PaymentService {
    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(int amount) {
        strategy.pay(amount);
    }
}
```

**Using the Strategy Pattern:**

```java id="d5k9mp"
public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService(new UpiPayment());
        service.processPayment(1000);
    }
}
```

**Advantages:**

* Eliminates complex **conditional logic**.
* Makes code **easy to extend and maintain**.
* Allows **runtime selection** of algorithms.



## 7. What is Adapter pattern?

The **Adapter Pattern** is a **Structural Design Pattern** that allows **two incompatible interfaces** to work together by acting as a **bridge** between them. It converts the interface of one class into another interface that the client expects.

**Key Features:**

* Connects **incompatible classes** without modifying their code.
* Promotes **code reusability**.
* Provides **loose coupling** between client and implementation.
* Also known as a **Wrapper Pattern**.

**How it Works:**

1. The client expects a specific interface.
2. An existing class has a different, incompatible interface.
3. The **Adapter** implements the expected interface and internally calls the existing class.
4. The client interacts with the adapter without knowing about the incompatible class.

**When to Use:**

* When integrating **third-party libraries** or **legacy systems**.
* When existing classes cannot be modified.
* To make incompatible interfaces work together.
* Commonly used in **Spring**, where adapters convert one API format into another.

**Code Example:**

```java id="w8n4kp"
interface Charger {
    void charge();
}

class MicroUsbCharger {
    public void microUsbCharge() {
        System.out.println("Charging with Micro USB");
    }
}

class ChargerAdapter implements Charger {
    private MicroUsbCharger charger = new MicroUsbCharger();

    @Override
    public void charge() {
        charger.microUsbCharge();
    }
}
```

**Using the Adapter:**

```java id="t3m7qx"
public class Main {
    public static void main(String[] args) {
        Charger charger = new ChargerAdapter();
        charger.charge();
    }
}
```

**Advantages:**

* Reuses existing code without modification.
* Improves **flexibility** and **maintainability**.
* Simplifies integration with **legacy or external systems**.


## 8. What is Decorator pattern?

The **Decorator Pattern** is a **Structural Design Pattern** that allows you to **add new functionality to an object dynamically** without changing its existing code. It works by **wrapping** the original object inside a decorator object.

**Key Features:**

* Adds **new behavior** without modifying the original class.
* Uses **composition over inheritance**.
* Promotes **flexibility** and **code reusability**.
* Follows the **Open/Closed Principle (OCP)**.

**How it Works:**

1. Define a common **interface**.
2. Create a **base implementation** of that interface.
3. Create a **Decorator** class that also implements the interface and holds a reference to the original object.
4. The decorator adds extra behavior before or after delegating the call to the wrapped object.

**When to Use:**

* When you need to **add features dynamically** at runtime.
* When using inheritance would create too many subclasses.
* For adding **logging**, **security**, **compression**, or **caching** functionality.
* Commonly used in Java I/O classes like **`BufferedReader`** and **`BufferedInputStream`**.

**Code Example:**

```java id="x7m4kp"
interface Coffee {
    String getDescription();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + " + Milk";
    }
}
```

**Using the Decorator:**

```java id="n5q8tx"
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new MilkDecorator(new SimpleCoffee());
        System.out.println(coffee.getDescription());
    }
}
```

**Advantages:**

* Adds functionality **without changing existing code**.
* Avoids creating many subclasses.
* Makes the system **flexible** and **easy to extend**.


## 8. What is Builder pattern?

The **Builder Pattern** is a **Creational Design Pattern** used to **construct complex objects step by step**. It is especially useful when an object has **many optional parameters** and you want to avoid multiple constructors.

**Key Features:**

* Builds objects **step by step**.
* Handles **many optional fields** cleanly.
* Creates **immutable objects** easily.
* Improves **code readability** and **maintainability**.
* Avoids the **Telescoping Constructor Problem** (too many constructor parameters).

**How it Works:**

1. Create a **Builder** class inside or outside the target class.
2. The builder contains methods to set each field.
3. Each method returns the **Builder object** to allow **method chaining**.
4. Call the **`build()`** method to create the final object.

**When to Use:**

* When a class has **many fields**, especially optional ones.
* When constructors become too large or confusing.
* When creating **immutable objects**.
* Commonly used in **Spring**, **Lombok (`@Builder`)**, and Java libraries.

**Code Example:**

```java id="r8m4kp"
public class User {
    private String name;
    private int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public static class Builder {
        private String name;
        private int age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```

**Using the Builder:**

```java id="w5q9tx"
public class Main {
    public static void main(String[] args) {
        User user = new User.Builder()
                        .name("Alice")
                        .age(25)
                        .build();
    }
}
```

**Advantages:**

* Makes object creation **clear and readable**.
* Eliminates constructors with too many parameters.
* Supports **immutable object creation**.
* Easy to extend with new optional fields.


## 8. What is Prototype pattern?

The **Prototype Pattern** is a **Creational Design Pattern** that creates new objects by **copying (cloning) an existing object** instead of creating a new one from scratch. It is useful when object creation is **expensive or complex**.

**Key Features:**

* Creates objects by **cloning existing instances**.
* Reduces the cost of **expensive object creation**.
* Promotes **code reusability**.
* Supports **shallow copy** and **deep copy**.

**How it Works:**

1. A class implements the **`Cloneable`** interface.
2. It overrides the **`clone()`** method.
3. Instead of using `new`, the client calls `clone()` on an existing object.
4. A new object is created as a copy of the original.

**When to Use:**

* When object creation is **time-consuming** or resource-intensive.
* When you need **multiple similar objects** with slight modifications.
* When creating objects from scratch is costly.
* Commonly used in **caching**, **game development**, and **object templates**.

**Code Example:**

```java id="k4m8xp"
class Employee implements Cloneable {
    String name;

    Employee(String name) {
        this.name = name;
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }
}
```

**Using the Prototype Pattern:**

```java id="v9q3tn"
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {

        Employee emp1 = new Employee("Alice");
        Employee emp2 = emp1.clone();

        System.out.println(emp2.name);
    }
}
```

**Advantages:**

* Improves performance by avoiding repeated object creation.
* Simplifies creating **similar objects**.
* Reduces the need for complex constructors.



# ✅ 18. Java Spring Framework 

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


**Inversion of Control (IoC)** is a design principle in which the **control of creating and managing objects is transferred from the application code to the Spring Container**. Instead of a class creating its own dependencies, **Spring creates the objects and injects the required dependencies automatically**.

In simple words, **you don't create objects; Spring creates and manages them for you.**

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

**Why to Use**

* Eliminates manual object creation using `new`.
* Promotes **loose coupling** and better maintainability.
* Simplifies dependency management.
* Makes applications easier to test and extend.

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

**Why to Use**

* Use **`BeanFactory`** when you need a lightweight container with lazy initialization.
* Use **`ApplicationContext`** for almost all enterprise applications because it provides additional Spring features.

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




# ✅ 19. Java Spring Boot 

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

**Why to Use Annotations?**

* Reduces **boilerplate code**.
* Eliminates complex **XML configurations**.
* Makes applications easier to configure and maintain.
* Enables powerful framework features like **Dependency Injection**, **AOP**, and **ORM mapping**.

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

**Why to Use Spring Boot?**

* Reduces **boilerplate code** and configuration.
* Speeds up application development.
* Makes deployment easier with a **self-contained JAR**.
* Simplifies microservices and enterprise application development.
* Integrates seamlessly with the Spring ecosystem.

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

**Why to Use This Architecture?**

* Promotes **Separation of Concerns (SoC)**.
* Makes applications **loosely coupled** and **easy to maintain**.
* Simplifies testing and future enhancements.
* Supports scalable **layered architecture**.

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

**Why to Use**

* To perform **resource initialization** (database connections, cache loading, etc.).
* To execute **cleanup tasks** before bean destruction.
* To customize bean behavior during **creation** and **destruction**.

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


## 5. What is @Component vs @Bean?

* **`@Component`**: An annotation used to tell **Spring** to automatically detect and create an object (bean) during **component scanning**.

* **`@Bean`**: An annotation used inside a **`@Configuration`** class to manually create and register a bean in the Spring container.

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

**Why to Use**

* Use **`@Component`** for your own service, repository, and controller classes because it is simple and requires less configuration.
* Use **`@Bean`** when you need **custom object creation**, complex initialization, or when the class belongs to an **external library** that you cannot modify.

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

**Code Example**

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

## 5. What is Java Bean, @Component and @Bean?

* **Java Bean** is a **plain Java class** that follows certain conventions: it has a **no-argument constructor**, **private fields**, and **public getter/setter methods**.
* **`@Component`** is a **Spring annotation** used to automatically register a class as a **Spring Bean** through **Component Scanning**.
* **`@Bean`** is a **Spring annotation** used on a method inside a **`@Configuration`** class to manually create and register a Spring Bean.

**Key Features**

| **Feature**           | **Java Bean**                         | **`@Component`**                       | **`@Bean`**                         |
| --------------------- | ------------------------------------- | -------------------------------------- | ----------------------------------- |
| **What it is**        | Java class following bean conventions | Annotation for automatic bean creation | Annotation for manual bean creation |
| **Managed By**        | JVM                                   | Spring Container                       | Spring Container                    |
| **Bean Registration** | Not automatic                         | Automatic via component scanning       | Manual via configuration method     |
| **Best For**          | Data/POJO classes                     | Your own application classes           | Third-party or customized objects   |
| **Customization**     | N/A                                   | Limited                                | Full control over object creation   |

**How it Works**

1. You create a **Java Bean** by following Java Bean conventions.
2. If the class is annotated with **`@Component`**, Spring automatically detects and registers it during startup.
3. If a method is annotated with **`@Bean`**, Spring executes that method and stores the returned object in the **IoC Container**.
4. The created Spring Beans can be injected into other classes using **Dependency Injection (DI)**.

**Why to Use**

* **Java Bean**: To create reusable and encapsulated data or business objects.
* **`@Component`**: To reduce manual configuration and let Spring automatically manage your classes.
* **`@Bean`**: To create beans with custom initialization logic or for classes you cannot modify.

**When to Use**

* Use **Java Bean** when creating a normal Java object with standard properties.
* Use **`@Component`** for your own classes like **Service**, **Repository**, or **Controller**.
* Use **`@Bean`** for **third-party libraries** or when bean creation needs custom logic.

**Code Example**

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

```java
// @Component
@Component
public class EmailService {

    public void sendEmail() {
        System.out.println("Email Sent");
    }
}
```

```java
// @Bean
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

```java
// Dependency Injection
@Service
public class UserService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private RestTemplate restTemplate;
}
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

## 8.  @Controller, @RestController, @Service, @Repository annotations?




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

**Why to Use**

* To follow a **layered architecture**.
* To make classes **Spring-managed beans**.
* To separate **presentation**, **business**, and **data access** logic.
* To get additional Spring features like **exception translation** and **automatic response conversion**.

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

**Why to Use**

* Use **`@Autowired`** when you are building a **Spring-only** application and want Spring-specific features.
* Use **`@Inject`** when you want your code to follow the **Java standard** and remain more **framework-independent**.

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

**Why to Use**

* To maintain **different configurations** for different environments.
* To avoid changing code manually when moving from **dev** to **test** or **production**.
* To load only the required beans, improving **maintainability** and **flexibility**.

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

**Why to Use**

* To let Spring **manage object creation and dependencies**.
* To avoid manual object creation using the **`new`** keyword.
* To get advanced Spring features like **AOP**, **events**, and **bean lifecycle management**.

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


**`@Component`** is a **Spring stereotype annotation** used to mark a class as a **Spring-managed bean**. Spring automatically detects it during **component scanning** and registers it in the **ApplicationContext**.

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


**`@Configuration`** indicates that a class contains **bean definitions**. It is used to define and configure beans using **`@Bean`** methods.

**Key Features**

* Used for **Java-based configuration**.
* Replaces XML configuration.
* Supports one or more **`@Bean`** methods.
* Classes are managed by the **Spring Container**.

**How it Works**

Spring loads the `@Configuration` class, executes its `@Bean` methods, and registers the returned objects as beans.

**Why to Use**

* To create beans manually.
* To configure **third-party or external library classes**.

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

**Why to Use**

* To update only selected fields instead of replacing the entire object.

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

**DispatcherServlet**

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


**HttpServletRequest**

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

**@PathVariable**

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


**Interceptor**

An **Interceptor** is a feature of **Spring MVC** that executes between the **DispatcherServlet** and the **Controller**.

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



# ✅ 20. Java RESTful Services 

## 1. What is CORS, and how does it work?

**CORS (Cross-Origin Resource Sharing)** is a **browser security mechanism** that allows or blocks a web application from making requests to a **different domain, protocol, or port** than the one from which it was loaded.

For example:

* Frontend: `http://localhost:3000`
* Backend: `http://localhost:8080`

Since the **ports are different**, they are considered **different origins**, and the browser blocks the request unless **CORS** is enabled.

**Key Features**

* Provides **secure cross-origin communication**.
* Controlled by **HTTP headers**.
* Prevents unauthorized websites from accessing resources.
* Works only in **web browsers** (not between backend services).
* Supports **Simple Requests** and **Preflight Requests**.

**How it Works**

1. The browser sends a request from the frontend to the backend.
2. The backend checks whether the origin is allowed.
3. If allowed, it returns headers like:

   ```text
   Access-Control-Allow-Origin: http://localhost:3000
   ```
4. The browser reads the header and allows the request.
5. If the header is missing or invalid, the browser blocks the request.

For **POST**, **PUT**, **DELETE**, or custom headers, the browser first sends an **OPTIONS** request called a **Preflight Request** to check whether the actual request is permitted.

**When to Use**

* Frontend and backend are hosted on **different domains or ports**.
* Building applications with **React**, **Angular**, or **Vue**.
* When APIs are consumed by external web applications.

**Spring Boot CORS Example**

**Using `@CrossOrigin` Annotation:**

```java
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "User List";
    }
}
```

**Global CORS Configuration:**

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
```

**Real-World Example**

A **React** application running on `http://localhost:3000` calls a **Spring Boot API** running on `http://localhost:8080`. Without CORS configuration, the browser blocks the request. By enabling CORS and allowing `http://localhost:3000`, the frontend can securely access the backend API.


## 2. What is an API and what are different type of API?


**API (Application Programming Interface)** is a set of **rules and protocols** that allows two software applications to **communicate and exchange data** with each other without knowing their internal implementation.

For example, when a mobile app requests user details from a backend server, it uses an **API**.

**Key Features**

* Enables **communication** between different applications.
* Hides the **internal implementation** of a system.
* Promotes **reusability** and **modularity**.
* Supports **platform-independent** integration.
* Can exchange data in formats like **JSON** or **XML**.

**How it Works**

1. A **Client** sends an API request.
2. The **Server** processes the request.
3. The server accesses the database or business logic.
4. The server returns a **response** to the client, usually in **JSON** format.

Example:

```text
Client  --->  API Request  --->  Server
Client  <---  JSON Response <--- Server
```

**When to Use**

* Building **web**, **mobile**, or **microservices** applications.
* Integrating with external services like payment gateways or maps.
* Exposing business functionality to other systems.

**Different Types of API**

| **API Type**      | **Description**                                                                             | **Example**                           |
| ----------------- | ------------------------------------------------------------------------------------------- | ------------------------------------- |
| **REST API**      | Uses **HTTP methods** (`GET`, `POST`, `PUT`, `DELETE`) and usually exchanges **JSON** data. | Spring Boot REST API                  |
| **SOAP API**      | Uses **XML** messages with strict standards and security features.                          | Banking and enterprise systems        |
| **GraphQL API**   | Client requests only the required data using a single endpoint.                             | Facebook GraphQL                      |
| **gRPC API**      | High-performance API using **Protocol Buffers** and HTTP/2.                                 | Internal microservices communication  |
| **WebSocket API** | Provides **real-time, two-way communication** between client and server.                    | Chat applications, live notifications |

**Types of APIs Based on Access**

| **Type**          | **Description**                                    |
| ----------------- | -------------------------------------------------- |
| **Public API**    | Available for external developers.                 |
| **Private API**   | Used only within an organization.                  |
| **Partner API**   | Shared with authorized business partners.          |
| **Composite API** | Combines multiple API calls into a single request. |

**Simple Spring Boot REST API Example**

```java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "User List";
    }
}
```

Request:

```http
GET /api/users
```

Response:

```json
"User List"
```



## 3. What are RESTful web services?

**What are RESTful Web Services?**

**RESTful Web Services** are web services that follow the **REST (Representational State Transfer)** architectural style. They allow applications to communicate over **HTTP** using standard methods like **GET**, **POST**, **PUT**, and **DELETE** to perform operations on resources.

A **resource** can be any data, such as a **User**, **Product**, or **Order**, and it is identified by a **URL**.

**Key Features**

* Uses standard **HTTP protocol**.
* Follows a **Resource-Based Architecture**.
* Supports **CRUD operations** using HTTP methods.
* **Stateless** – the server does not store client session information.
* Usually exchanges data in **JSON** format (can also support XML).
* Easy to build, consume, and scale.

**How it Works**

1. The **Client** sends an HTTP request to a URL.
2. The **REST API** processes the request.
3. The server performs the required business logic or database operation.
4. The server returns an HTTP response, usually with **JSON** data.

**Common HTTP Methods**

| **HTTP Method** | **Operation**        | **Example URL** |
| --------------- | -------------------- | --------------- |
| **GET**         | Retrieve data        | `/users/1`      |
| **POST**        | Create new data      | `/users`        |
| **PUT**         | Update existing data | `/users/1`      |
| **DELETE**      | Delete data          | `/users/1`      |


**When to Use**

* Building **Web APIs** and **Microservices**.
* Communication between **frontend and backend**.
* Mobile applications consuming backend services.
* Integration with third-party systems.

**Simple Spring Boot REST API Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
```

**Real-World Example**

In an **E-commerce Application**:

* `GET /products` → Get all products.
* `GET /products/101` → Get a specific product.
* `POST /orders` → Create a new order.
* `PUT /orders/101` → Update an order.
* `DELETE /orders/101` → Cancel an order.


## 4. What are the principles of REST?

REST (Representational State Transfer) is based on six key architectural principles that guide the design of web services.

**REST Principles:**
- **Stateless:** Each request contains all necessary information
- **Client-Server:** Separation of concerns between client and server
- **Cacheable:** Responses can be cached for better performance
- **Uniform Interface:** Consistent way to interact with resources
- **Layered System:** Architecture can have multiple layers
- **Code on Demand:** Optional - server can send executable code

These principles ensure scalability, reliability, and maintainability of web services.


## 5: What is XML how to return XML in response?

**XML (eXtensible Markup Language)** is a **markup language** for representing data using **tags**.

It is **verbose**, supports **attributes and namespaces**, is **self-documenting with schema validation**, and is used in **enterprise apps and SOAP services**.

```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```
```java
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "user")
public class User {
    private int id;
    private String name;

    // getters setters
}
```
```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {

    @GetMapping(value = "/student", produces = "application/xml")
    public Student getStudent() {
        Student s = new Student();
        s.setId(1);
        s.setName("John");
        return s;
    }
}

// Result
// <student>
//     <id>1</id>
//     <name>John</name>
// </student>
```


## 6. What are HTTP methods and their usage?

**HTTP Methods** are standard operations used by a client to communicate with a server in a **RESTful Web Service**. They define what action should be performed on a resource, such as **creating**, **reading**, **updating**, or **deleting** data.

**Key Features**

* Define the **type of operation** to perform.
* Work over the **HTTP protocol**.
* Used in **REST APIs** for CRUD operations.
* Help create **standardized and predictable APIs**.
* Can be **safe** and **idempotent** depending on the method.

**How it Works**

1. The **Client** sends an HTTP request with a specific method.
2. The **Server** identifies the method and processes the request.
3. The server returns an appropriate **HTTP response** with data or status.

For example:

```text
Client ---- GET /users/1 ----> Server
Client <--- User Data -------- Server
```

**Main HTTP Methods and Their Usage**

| **Method**  | **Purpose**                          | **CRUD Operation** | **Example**                         |
| ----------- | ------------------------------------ | ------------------ | ----------------------------------- |
| **GET**     | Retrieve data                        | Read               | Get user details                    |
| **POST**    | Create new data                      | Create             | Create a new user                   |
| **PUT**     | Update or replace existing data      | Update             | Update user details                 |
| **PATCH**   | Partially update existing data       | Update             | Update only user's email            |
| **DELETE**  | Remove data                          | Delete             | Delete a user                       |
| **OPTIONS** | Returns supported HTTP methods       | N/A                | Used in **CORS Preflight** requests |
| **HEAD**    | Same as GET but returns only headers | N/A                | Check if a resource exists          |


**When to Use**

* Building **RESTful APIs**.
* Communication between **frontend and backend**.
* **Microservices** and third-party API integrations.
* Web and mobile application development.

**Spring Boot Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return "Get User";
    }

    @PostMapping
    public String createUser() {
        return "Create User";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id) {
        return "Update User";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return "Delete User";
    }
}
```


## 7. What is the difference between PUT and POST?

**PUT:**
- Updates or replaces entire resource
- Idempotent (same result when called multiple times)
- Requires complete resource data
- Used for updates when you know the resource ID

**POST:**
- Creates new resources
- Not idempotent (creates new resource each time)
- Can send partial data
- Server typically generates resource ID

```java
// POST - Create new user (server generates ID)
POST /users
{
    "name": "John",
    "email": "john@example.com"
}
// Response: 201 Created with generated ID

// PUT - Update/replace entire user resource
PUT /users/123
{
    "id": 123,
    "name": "John Updated",
    "email": "john.updated@example.com"
}
// Response: 200 OK or 204 No Content
```

## 8. What are HTTP status codes?

**HTTP Status Codes** are **3-digit response codes** returned by a web server to indicate the **result of an HTTP request**. They help the client understand whether the request was **successful**, **redirected**, **invalid**, or **failed**.

**Key Features**

* **Standard HTTP Response Codes**
* **Indicate Request Status**
* **Used in REST APIs**
* **Help in Error Handling**
* **Improve Client-Server Communication**

**How It Works**

1. A client sends an **HTTP request**.
2. The server processes the request.
3. The server returns an **HTTP Status Code** along with the response.
4. The client uses the status code to decide the next action.

**Architecture Flow**

```text
Client
   │
HTTP Request
   │
   ▼
Server
   │
HTTP Status Code + Response
   │
   ▼
Client
```

**HTTP Status Code Categories**

| **Category** | **Range**   | **Meaning**   |
| ------------ | ----------- | ------------- |
| **1xx**      | **100–199** | Informational |
| **2xx**      | **200–299** | Success       |
| **3xx**      | **300–399** | Redirection   |
| **4xx**      | **400–499** | Client Error  |
| **5xx**      | **500–599** | Server Error  |

**Common HTTP Status Codes**

| **Code**                       | **Meaning**                          | **When Used**               |
| ------------------------------ | ------------------------------------ | --------------------------- |
| **200 OK**                     | Request successful                   | GET, PUT                    |
| **201 Created**                | Resource created                     | POST                        |
| **204 No Content**             | Success with no response body        | DELETE                      |
| **301 Moved Permanently**      | Resource moved permanently           | URL changed                 |
| **302 Found**                  | Temporary redirect                   | Temporary URL change        |
| **304 Not Modified**           | Cached resource is valid             | Browser caching             |
| **400 Bad Request**            | Invalid request                      | Invalid input               |
| **401 Unauthorized**           | Authentication required              | Missing/Invalid token       |
| **403 Forbidden**              | Access denied                        | User lacks permission       |
| **404 Not Found**              | Resource not found                   | Invalid URL or ID           |
| **405 Method Not Allowed**     | HTTP method not supported            | POST on GET endpoint        |
| **409 Conflict**               | Resource conflict                    | Duplicate record            |
| **415 Unsupported Media Type** | Invalid content type                 | Wrong Content-Type header   |
| **429 Too Many Requests**      | Rate limit exceeded                  | Too many API calls          |
| **500 Internal Server Error**  | Unexpected server error              | Application failure         |
| **502 Bad Gateway**            | Invalid response from another server | Gateway/Proxy issue         |
| **503 Service Unavailable**    | Service temporarily unavailable      | Server overload/maintenance |
| **504 Gateway Timeout**        | Upstream server timeout              | Microservice timeout        |

**When to Use**

* **REST APIs**
* **Web Applications**
* **Microservices**
* **API Error Handling**
* **Client-Server Communication**

**Spring Boot Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id) {

        if (id == 1) {
            return ResponseEntity.ok("User Found");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("User Not Found");
    }
}
```

**Possible Responses**

**Success**

```http
HTTP/1.1 200 OK

User Found
```

**Failure**

```http
HTTP/1.1 404 Not Found

User Not Found
```

**Advantages**

* **Standardized Communication**
* **Easy Error Handling**
* **Improves API Readability**
* **Helps in Debugging**
* **Widely Supported**


**Common Interview Follow-up**

**Q: What is the difference between 401 and 403?**

| **401 Unauthorized**          | **403 Forbidden**                            |
| ----------------------------- | -------------------------------------------- |
| User is **not authenticated** | User is **authenticated but not authorized** |
| Login or valid token required | User lacks permission                        |

**Q: What is the difference between 200 and 201?**

| **200 OK**                     | **201 Created**                   |
| ------------------------------ | --------------------------------- |
| Request processed successfully | New resource created successfully |
| Commonly used for **GET**      | Commonly used for **POST**        |

**Q: What is the difference between 404 and 500?**

| **404 Not Found**                    | **500 Internal Server Error** |
| ------------------------------------ | ----------------------------- |
| Resource does not exist              | Unexpected server-side error  |
| Client requested an invalid resource | Application or server failed  |

**Q: Which HTTP status codes are commonly used in REST APIs?**

* **200 OK** – Successful request
* **201 Created** – Resource created
* **204 No Content** – Successful deletion
* **400 Bad Request** – Invalid request
* **401 Unauthorized** – Authentication required
* **403 Forbidden** – Permission denied
* **404 Not Found** – Resource not found
* **409 Conflict** – Duplicate or conflicting resource
* **500 Internal Server Error** – Server error



## 9. Create API to create communicate in different table?


A **REST API** can interact with **multiple database tables** within a **single request**. This is commonly done in the **Service Layer** using **JPA Relationships** and **Transactions** to ensure data consistency.

**Example Scenario**

Create an **Order API** that saves data into:

* **orders** table
* **order_items** table

When a client creates an order, both tables should be updated together.

**Key Features**

* **Single API** updates multiple tables
* Uses **JPA Relationships** (`@OneToMany`, `@ManyToOne`)
* Uses **@Transactional** for **Atomicity**
* Maintains **Data Consistency**
* Supports **Rollback** if any operation fails

**How it Works**

1. Client sends a **POST** request.
2. **Controller** receives the request.
3. **Service Layer** starts a **Transaction**.
4. Save data into the **Parent Table**.
5. Save related data into the **Child Table**.
6. If all operations succeed, **Commit** the transaction.
7. If any operation fails, **Rollback** all changes.

**Example API**

```http
POST /orders
```

**Request**

```json
{
  "customerName": "John",
  "items": [
    {
      "product": "Laptop",
      "quantity": 1
    },
    {
      "product": "Mouse",
      "quantity": 2
    }
  ]
}
```

**Entity Relationship**

```java
@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String customerName;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
```

```java
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    private String product;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
```

**Service Example**

```java
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional
    public Order save(Order order) {
        order.getItems().forEach(item -> item.setOrder(order));
        return repository.save(order);
    }
}
```

**Controller Example**

```java
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return service.save(order);
    }
}
```

**When to Use**

* **Order** and **Order Items**
* **Employee** and **Address**
* **Customer** and **Account**
* **Student** and **Courses**
* Any **Parent-Child** relationship

**Advantages**

* **Single API** updates multiple tables
* Ensures **Data Consistency**
* Automatic **Rollback** on failure
* Easier maintenance using **JPA Relationships**


## 10. Create API for external API call(HttpClient)? 

An API can call an **External API** (Third-Party Service) from the **Service Layer** using **Java 11 HttpClient**, **Spring WebClient**, or **OpenFeign**. The application receives the client request, calls the external service, processes the response, and returns the result to the client.

**Key Features**

* Calls **Third-Party APIs**
* Supports **GET**, **POST**, **PUT**, and **DELETE**
* Handles **Timeouts** and **Error Responses**
* Can use **Authentication** (API Key, **JWT**, **OAuth2**)
* Supports **Synchronous** and **Asynchronous** communication

**How it Works**

1. Client sends a request to your API.
2. **Controller** receives the request.
3. **Service Layer** calls the **External API**.
4. External API returns the response.
5. Service processes the response.
6. Controller returns the final response to the client.

```text
Client
   |
Your API
   |
Service Layer
   |
HTTP Request
   |
External API
```

**Java 11 HttpClient Example**

```java
@Service
public class UserService {
    public String getUsers() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
```

**Controller Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public String getUsers() throws Exception {
        return service.getUsers();
    }
}
```

**Example Flow**

```text
Client
   |
GET /users
   |
User Service
   |
Java 11 HttpClient
   |
External User API
   |
JSON Response
   |
Client
```

**Best Practices**

* Configure **Connection Timeout** and **Read Timeout**
* Implement **Retry** for temporary failures
* Use a **Circuit Breaker** to prevent cascading failures
* Handle **HTTP Status Codes** properly
* Log **Request** and **Response**
* Secure APIs using **HTTPS**, **API Keys**, or **OAuth2**
* Use **Caching** if the data changes infrequently

**When to Use**

* Calling **Payment Gateway APIs**
* Fetching **Weather Data**
* Calling **Google Maps API**
* Integrating with **Banking APIs**
* Calling other **Microservices**
* Integrating with any **Third-Party Service**

**Advantages**

* Easy integration with external systems
* Real-time data retrieval
* Reusable service layer
* Supports secure communication



## 11. Create API for parallel API(Asynchronous) using HttpClient?

**Parallel API calls** allow you to call multiple external services **at the same time** instead of waiting for each one to finish. This improves **performance** and **reduces overall response time**.

**Key Features**

* **Runs multiple API calls concurrently**
* **Reduces total execution time**
* **Improves application performance**
* **Non-blocking** using **CompletableFuture**
* **Combines multiple API responses** into one result

**How It Works**

1. Receive a client request.
2. Start multiple API calls using **CompletableFuture.supplyAsync()**.
3. All APIs execute **in parallel**.
4. Wait for all responses using **CompletableFuture.allOf()**.
5. Merge the results and return a single response.

**When to Use**

* Calling **multiple microservices**
* Fetching data from **different external APIs**
* Building **dashboard or aggregated responses**
* Improving **response time** for independent API calls

**Code Example**

**Service Example (Asynchronous)**

```java
@Service
public class UserService {

    private final HttpClient client = HttpClient.newHttpClient();

    public CompletableFuture<String> getUsers() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> "API Failed: " + ex.getMessage());
    }
}
```

**Controller Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public CompletableFuture<String> getUsers() {
        return service.getUsers();
    }
}
```

**Multiple External APIs in Parallel**

```java
@Service
public class UserService {

    private final HttpClient client = HttpClient.newHttpClient();

    public CompletableFuture<Map<String, String>> getUserDetails() {

        HttpRequest profileRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.example.com/profile"))
                .GET()
                .build();

        HttpRequest orderRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.example.com/orders"))
                .GET()
                .build();

        CompletableFuture<String> profile =
                client.sendAsync(profileRequest, HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body);

        CompletableFuture<String> orders =
                client.sendAsync(orderRequest, HttpResponse.BodyHandlers.ofString())
                        .thenApply(HttpResponse::body);

        return CompletableFuture.allOf(profile, orders)
                .thenApply(v -> {
                    Map<String, String> result = new HashMap<>();
                    result.put("profile", profile.join());
                    result.put("orders", orders.join());
                    return result;
                });
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

    @GetMapping("/details")
    public CompletableFuture<Map<String, String>> getUserDetails() {
        return service.getUserDetails();
    }
}
```

**Execution Flow**

```text
Client
   |
GET /users/details
   |
Controller
   |
Service
   |
sendAsync()
   |
-----------------------------
|                           |
Profile API            Orders API
|                           |
-----------------------------
        |
CompletableFuture.allOf()
        |
Combined Response
        |
Controller
        |
Client
```

**Benefits**

* **Faster response time**
* **Better resource utilization**
* **Higher throughput**
* **Scalable** for microservices
* **Improved user experience**


## 12. Create API for Asynchronous Data Processing?


An **Asynchronous API** processes requests in the **Background** without making the client wait for the task to complete. In **Spring Boot**, this is commonly implemented using **@Async** and **CompletableFuture**.

**Key Features**

* **Non-Blocking** request processing
* Executes tasks in a **Background Thread**
* Uses **@Async** and **CompletableFuture**
* Faster **API Response**
* Improves **Scalability** and **Performance**

**How it Works**

1. Client sends a request.
2. **Controller** calls the **Service Layer**.
3. The service method annotated with **@Async** starts processing in a **Background Thread**.
4. API immediately returns an **Accepted** response.
5. Background task completes independently.

**Enable Async**

```java
@SpringBootApplication
@EnableAsync
public class Application {
}
```

**Service Example**

```java
@Service
public class ReportService {

    @Async
    public CompletableFuture<String> generateReport() throws Exception {
        Thread.sleep(5000); // Long-running task
        return CompletableFuture.completedFuture("Report Generated");
    }
}
```

**Controller Example**

```java
@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @GetMapping("/generate")
    public String generateReport() {

        service.generateReport();
        return "Report generation started";
    }
}
```

**Best Practices**

* Use **@Async** for long-running tasks
* Return **CompletableFuture** for asynchronous results
* Configure a custom **Thread Pool**
* Handle **Exceptions** properly
* Monitor background tasks using **Logging**
* Avoid blocking operations inside asynchronous methods

**When to Use**

* **Report Generation**
* **File Processing**
* **Email Notifications**
* **Data Import/Export**
* **Large File Uploads**
* **Long-Running Background Jobs**

**Advantages**

* Faster API response
* Better **User Experience**
* Improved **Performance**
* Better **Resource Utilization**
* Scalable for long-running tasks

**2. Way to CompletableFuture (Without @Async)**

**How it Works**

* Uses **CompletableFuture.supplyAsync()** with a **Thread Pool**.

**Best For**

* Parallel API calls
* Parallel database queries
* CPU-intensive tasks

```java
ExecutorService executor = Executors.newFixedThreadPool(5);

CompletableFuture<String> future =
    CompletableFuture.supplyAsync(() -> {
        return "Processing";
    }, executor);
```

**3. Way to ExecutorService**

**How it Works**

* Java's **Thread Pool** executes background tasks.

**Best For**

* Custom thread management
* Background processing in Core Java

```java
ExecutorService executor = Executors.newFixedThreadPool(5);

executor.submit(() -> {
    System.out.println("Background Task");
});
```


## 13. Create API to handle large data(Millions of records) efficiently?

* **Use Streaming** (`Stream<T>`, file streaming) instead of loading all records with `findAll()`
* **Use Batch Processing** to process records in chunks and reduce memory consumption
* **Use Database Pagination** (`Page<T>`, `Slice<T>`, `Stream<T>`) for large datasets
* **Use Async / Parallel Processing** (`@Async`, `CompletableFuture`, ExecutorService) for concurrent workloads
* **Use Caching** (Redis, Caffeine, EhCache, Spring Cache) to reduce repeated database calls
* **Use JDBC Batch Operations** (`spring.jpa.properties.hibernate.jdbc.batch_size`) for bulk inserts/updates
* **Use Sharding** when data becomes too large for a single database server


**Step 1 — Client Uploads File**

Controller accepts file without loading everything into memory.

```java id="v50d5s"
@RestController
@RequestMapping("/files")
class FileUploadController {
    private final FileProcessingService service;
    public FileUploadController(FileProcessingService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        service.processFile(file);
        return "File Accepted";
    }
}
```


**Step 2 — Service Layer**

```java id="cx88hj"
@Service
class FileProcessingService {
    private final DataRepository repository;
    private static final int BATCH_SIZE = 1000;

    public FileProcessingService(DataRepository repository) {
        this.repository = repository;
    }

    @Async
    public CompletableFuture<Void> processFile( MultipartFile file) throws Exception {
        List<DataEntity> batch = new ArrayList<>();

        try (
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    file.getInputStream()
                            )
                    )
        ) {

            String line;
            while ((line = reader.readLine()) != null) {
                DataEntity entity = mapToEntity(line);
                batch.add(entity);
                if (batch.size() == BATCH_SIZE) {
                    saveBatch(batch);
                    batch.clear();
                }
            }

            // remaining records
            if (!batch.isEmpty()) {
                saveBatch(batch);
            }
        }

        return CompletableFuture.completedFuture(null);
    }

    private DataEntity mapToEntity(String line) {
        DataEntity entity = new DataEntity();
        entity.setName(line);
        return entity;
    }

    private void saveBatch(List<DataEntity> batch) {
        repository.saveAll(batch);
        System.out.println(
                "Saved batch size: " + batch.size()
        );
    }
}
```


**Step 3 — Entity**

```java id="zwv22u"
@Entity
class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```


**Step 4 — Repository**

```java id="v74d5u"
@Repository
interface DataRepository extends JpaRepository<DataEntity, Long> {
}
```


**Step 5 — Enable Async**

```java id="9vkdpn"
@EnableAsync
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
```


## 14. Create API to process images into Oracle DB using Java APIs?

In a **Spring Boot REST API**, images are usually uploaded as **MultipartFile** and stored in an Oracle **BLOB** column. We use **JPA** or **JDBC** to save the image bytes into the database.

**Entity**

```java id="zx5b7g"
import jakarta.persistence.*;

@Entity
@Table(name = "employee_images")
public class EmployeeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    // getters and setters
}
```

**Repository**

```java id="5zj6bi"
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeImageRepository
        extends JpaRepository<EmployeeImage, Long> {
}
```

**Service**

```java id="t4p4u4"
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeImageService {

    private final EmployeeImageRepository repository;

    public EmployeeImageService(EmployeeImageRepository repository) {
        this.repository = repository;
    }

    public Long uploadImage(MultipartFile file) throws Exception {

        EmployeeImage image = new EmployeeImage();
        image.setFileName(file.getOriginalFilename());
        image.setImageData(file.getBytes());

        return repository.save(image).getId();
    }

    public EmployeeImage getImage(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
    }
}
```

**Controller**

```java id="j72oym"
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class EmployeeImageController {

    private final EmployeeImageService service;

    public EmployeeImageController(EmployeeImageService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file) throws Exception {

        Long id = service.uploadImage(file);

        return ResponseEntity.ok("Image uploaded. ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> download(
            @PathVariable Long id) {

        EmployeeImage image = service.getImage(id);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image.getImageData());
    }
}
```

**Oracle Table**

```sql id="uvcp58"
CREATE TABLE employee_images (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY,
    file_name VARCHAR2(255),
    image_data BLOB,
    PRIMARY KEY(id)
);
```

## 15. Create API to handle large files usign spring Batch processing?


To handle **large files** efficiently, I use **Spring Batch** for **batch processing** and **Async Processing** to run the job in the background without blocking the API request.

**Key Features**

* **Spring Batch** processes data in **chunks** instead of loading the entire file into memory.
* **Async Processing** runs the batch job in a separate thread.
* **Chunk Processing** improves **memory usage** and **performance**.
* Supports **restart**, **retry**, **skip failed records**, and **job monitoring**.
* Suitable for processing **millions of records**.

**How It Works**

1. Client uploads a large file.
2. API stores the file.
3. API starts a **Spring Batch Job** asynchronously.
4. **ItemReader** reads records chunk by chunk.
5. **ItemProcessor** validates/transforms data.
6. **ItemWriter** saves records in batches.
7. API immediately returns **Job Started** while processing continues in the background.

**When to Use**

* **CSV/Excel/XML** file processing.
* **Bulk database insert/update**.
* **Payroll processing**.
* **Bank transactions**.
* **Insurance claim processing**.
* **Report generation**.

**Architecture**

```
Client
   |
Upload File API
   |
JobLauncher (Async)
   |
Spring Batch Job
   |
Reader -> Processor -> Writer
   |
Database
```

**Step 1: Enable Async**

```java
@Configuration
@EnableAsync
public class AsyncConfig {
}
```

**Step 2: Async Job Launcher Service**

```java
@Service
public class BatchService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importJob;

    @Async
    public void startJob(String fileName) throws Exception {

        JobParameters params = new JobParametersBuilder()
                .addString("file", fileName)
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(importJob, params);
    }
}
```

**Step 3: REST API**

```java
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private BatchService batchService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload() throws Exception {

        batchService.startJob("employees.csv");

        return ResponseEntity.ok("Batch Job Started Successfully");
    }
}
```

**Step 4: Spring Batch Configuration**

```java
@Bean
public Step step(JobRepository jobRepository,
                 PlatformTransactionManager transactionManager) {

    return new StepBuilder("step", jobRepository)
            .<Employee, Employee>chunk(1000, transactionManager)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build();
}
```

**Reader**

```java
@Bean
public FlatFileItemReader<Employee> reader() {
    FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
    reader.setResource(new FileSystemResource("employees.csv"));
    return reader;
}
```

**Processor**

```java
@Component
public class EmployeeProcessor
        implements ItemProcessor<Employee, Employee> {

    @Override
    public Employee process(Employee employee) {
        employee.setName(employee.getName().toUpperCase());
        return employee;
    }
}
```

**Writer**

```java
@Bean
public JdbcBatchItemWriter<Employee> writer(DataSource dataSource) {

    JdbcBatchItemWriter<Employee> writer =
            new JdbcBatchItemWriter<>();

    writer.setDataSource(dataSource);
    writer.setSql("INSERT INTO employee(id,name) VALUES(:id,:name)");
    writer.setItemSqlParameterSourceProvider(
            new BeanPropertyItemSqlParameterSourceProvider<>());

    return writer;
}
```

**Example for Upload and Download**

**Controller for File Upload**

```java
@RestController
@RequestMapping("/files")
public class FileController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) throws IOException {

        Path path = Paths.get("uploads/" + file.getOriginalFilename());

        try (InputStream in = file.getInputStream();
             OutputStream out = Files.newOutputStream(path)) {

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        return ResponseEntity.ok("File uploaded successfully");
    }
}
```

**Controller for File Download**

```java
@GetMapping("/download/{fileName}")
public ResponseEntity<Resource> downloadFile(
        @PathVariable String fileName) throws IOException {

    Path path = Paths.get("uploads/" + fileName);

    Resource resource =
            new InputStreamResource(Files.newInputStream(path));

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=" + fileName)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
}
```



**Why Chunk Processing?**

Suppose the file contains **10 million records**.

* **Without Chunking:** Loads all records into memory, causing **high memory usage** and possible **OutOfMemoryError**.
* **With Chunk Size = 1000:** Reads **1000 records**, processes them, writes them to the database, clears memory, and continues with the next chunk.

This keeps **memory usage low** and improves **performance**.

**Advantages**

* **Processes very large files efficiently**
* **Low memory consumption**
* **Fast batch inserts**
* **Runs asynchronously**
* Supports **restart**, **retry**, and **skip logic**
* Easy to monitor **job status**


**Common Interview Follow-up Questions**

**1. Why use Spring Batch instead of normal Java loops?**

Because **Spring Batch** provides **chunk processing**, **restartability**, **retry**, **skip**, **transaction management**, and **job monitoring**, making it suitable for enterprise-scale batch processing.

**2. Why use Async with Spring Batch?**

So the **REST API** returns immediately while the batch job continues in the background, improving **user experience** and avoiding request timeouts.

**3. What is the ideal chunk size?**

It depends on the data and available memory. Common values are **100**, **500**, or **1000** records per chunk.

**4. How do you handle failed records?**

Use **Skip Policy**, **Retry Policy**, and **SkipListener** to skip invalid records, retry transient failures, and log failed records without stopping the entire job.

**5. Can multiple batch jobs run simultaneously?**

Yes. By combining **Spring Batch** with **Async TaskExecutor**, multiple jobs can run in parallel independently.


## 16. Create API to Handle Transaction Failure in Microservices Using Saga Pattern?


In **Microservices**, a transaction may involve multiple services, each with its own database. Since **`@Transactional`** cannot manage transactions across multiple services, we use the **Saga Pattern** to maintain **Eventual Consistency** using **Compensating Transactions**.

**Key Features**

* **Saga Pattern** for **distributed transactions**
* **Local Transaction** in each microservice
* **Compensating Transaction** to undo completed steps on failure
* **Eventual Consistency**
* **Retry** and **Circuit Breaker** for fault tolerance
* **Idempotency** to avoid duplicate processing

**How It Works**

Suppose we have three services:

* **Order Service**
* **Payment Service**
* **Inventory Service**

Flow:

1. Client calls **Create Order API**.
2. **Order Service** creates the order.
3. **Payment Service** deducts the payment.
4. **Inventory Service** reserves the stock.
5. If all succeed, the order status becomes **COMPLETED**.
6. If any step fails, **Saga** executes **Compensating Transactions**:

   * Refund payment
   * Release stock
   * Cancel order

**When to Use**

* **E-commerce Order Processing**
* **Banking Transactions**
* **Insurance Claims**
* **Flight/Hotel Booking**
* Any **Distributed Microservices** application

**Architecture**

```text
Client
   |
Create Order API
   |
Saga Orchestrator
   |
-------------------------------
|        |                    |
Order   Payment          Inventory
Service  Service          Service
-------------------------------
        |
If Failure
        |
Refund Payment
Release Stock
Cancel Order
```

**Step 1: Order API**

```java
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private SagaService sagaService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {

        sagaService.processOrder(request);

        return ResponseEntity.ok("Order Processing Started");
    }
}
```

**Step 2: Saga Orchestrator**

```java
@Service
public class SagaService {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InventoryService inventoryService;
    public void processOrder(OrderRequest request) {

        try {

            orderService.createOrder(request);
            paymentService.makePayment(request);
            inventoryService.reserveStock(request);
            orderService.completeOrder(request.getOrderId());

        } catch (Exception ex) {
            paymentService.refund(request);
            inventoryService.releaseStock(request);
            orderService.cancelOrder(request.getOrderId());
        }
    }
}
```

**Step 3: Payment Service**

```java
@Service
public class PaymentService {

    public void makePayment(OrderRequest request) {
        System.out.println("Payment Successful");
    }

    public void refund(OrderRequest request) {
        System.out.println("Payment Refunded");
    }
}
```

**Step 4: Inventory Service**

```java
@Service
public class InventoryService {
    public void reserveStock(OrderRequest request) {
        System.out.println("Stock Reserved");
    }

    public void releaseStock(OrderRequest request) {
        System.out.println("Stock Released");
    }
}
```

**Step 5: Order Service**

```java
@Service
public class OrderService {
    public void createOrder(OrderRequest request) {
        System.out.println("Order Created");
    }

    public void completeOrder(Long orderId) {
        System.out.println("Order Completed");
    }

    public void cancelOrder(Long orderId) {
        System.out.println("Order Cancelled");
    }
}
```

**Failure Example**

Suppose the flow is:

* **Order Created**
* **Payment Successful**
* **Inventory Reservation Failed**

Saga automatically performs:

* **Refund Payment**
* **Cancel Order**

The system remains **consistent** without using a distributed transaction.

**Advantages**

* Supports **distributed transactions**
* Prevents **partial updates**
* Improves **fault tolerance**
* Supports **Eventual Consistency**
* Easy to scale across multiple microservices


**Common Interview Follow-up Questions**

**1. Why use the Saga Pattern instead of `@Transactional`?**

Because **`@Transactional`** works only within a **single database**, whereas the **Saga Pattern** manages **distributed transactions** across multiple microservices.

**2. What is a Compensating Transaction?**

A **reverse operation** that undoes a previously completed transaction, such as **refund payment**, **release stock**, or **cancel an order**.

**3. What is the role of the Saga Orchestrator?**

The **Saga Orchestrator** coordinates the transaction flow, invokes each service in sequence, and triggers **Compensating Transactions** if any step fails.

**4. Can Saga work asynchronously?**

Yes. It is commonly implemented using **Kafka**, **RabbitMQ**, or other **message brokers**, allowing services to communicate through events asynchronously.

**5. What is the difference between Orchestration and Choreography?**

* **Orchestration:** A central **Saga Orchestrator** controls the workflow.
* **Choreography:** Services communicate through **events**, with no central coordinator.

**6. Which approach is more common in enterprise applications?**

**Saga Orchestration** is commonly used because it provides **centralized control**, **better monitoring**, and **easier error handling** for complex business workflows.


**7. What is the Saga Pattern?**

A **distributed transaction pattern** where each service performs a local transaction, and if a later step fails, **Compensating Transactions** undo the completed work.


**8. How do you handle temporary service failures?**

Use **Retry**, **Circuit Breaker**, **Timeout**, and **Fallback** mechanisms.

**9. What is Eventual Consistency?**

Instead of all services being updated instantly, the system guarantees that all services become **consistent over time**, even if temporary failures occur.

**10. Orchestration vs Choreography in Saga?**

* **Orchestration:** A central **Saga Orchestrator** controls the transaction flow.
* **Choreography:** Services communicate through **events** (for example, using **Kafka** or **RabbitMQ**) without a central coordinator.


## 17. Create API to roll back @Transaction for fail payment?

**`@Transactional`** ensures that **all database operations are treated as a single transaction**. If the **payment fails** or any exception occurs, **Spring automatically rolls back** all database changes, maintaining **data consistency**.

**Key Features**

* **Automatic Rollback** on exception
* **Maintains Data Consistency**
* **Atomic Transaction** (All or Nothing)
* **Simple to Implement**
* **Supports ACID Properties**

**How it Works**

1. User places an order.
2. Save the order to the database.
3. Process the payment.
4. If payment is **successful**, the transaction is **committed**.
5. If payment **fails**, an exception is thrown.
6. Spring **rolls back** the transaction, so the order is **not saved**.

**When to Use**

* **Order and Payment Processing**
* **Banking Transactions**
* **Inventory Management**
* **Money Transfer**
* **Any operation requiring data consistency**

**Controller**

```java
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/transfer")
    public String transfer() {
        service.transferMoney(1L, 2L, 500);
        return "Money transferred successfully";
    }
}
```

**Service**

```java
@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    @Transactional
    public void transferMoney(Long fromId, Long toId, double amount) {

        Account from = repository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Account to = repository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        from.debit(amount);
        to.credit(amount);

        repository.save(from);
        repository.save(to);
    }
}
```

**Repository**

```java
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
```


**Entity**

```java
@Entity
public class Account {

    @Id
    private Long id;

    private String name;
    private double balance;

    // Getters and Setters

    public void debit(double amount) {
        this.balance -= amount;
    }

    public void credit(double amount) {
        this.balance += amount;
    }
}
```

**Flow**

```text
Client
   │
   ▼
Controller
   │
   ▼
Spring AOP Proxy
   │
Transaction Begins
   │
   ▼
transferMoney()
   │
Find Accounts
Debit Account A
Credit Account B
Save Changes
   │
   ▼
No Exception?
   │
 ┌───────┴────────┐
 │                │
Yes             No
 │                │
Commit        Rollback
 │                │
 ▼                ▼
Database      Database Restored
```

**Important Note**

By default, **Spring rolls back only for unchecked exceptions (`RuntimeException`)**.

To roll back for **checked exceptions**, specify `rollbackFor`.

```java
@Transactional(rollbackFor = Exception.class)
public void placeOrder(Order order) {
    // Business logic
}
```

**Common Interview Follow-up Questions**

**1. When does `@Transactional` roll back?**

By default, it rolls back only for **`RuntimeException`** and **`Error`**.

**2. How do you roll back for checked exceptions?**

Use:

```java
@Transactional(rollbackFor = Exception.class)
```

**3. Will `@Transactional` roll back if I catch the exception?**

**No.** If you catch the exception and do not rethrow it, Spring considers the transaction successful and **commits** it.

```java
try {
    processPayment();
} catch (Exception e) {
    // Transaction will NOT roll back unless exception is rethrown
}
```

**4. Does `@Transactional` work across Microservices?**

**No.** `@Transactional` works only **within a single service and database**. For distributed transactions across multiple microservices, use the **Saga Pattern** or **Compensating Transactions**.


# ✅ 21. Java Microservices 

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

**@Transactional** is a Spring annotation used to manage **database transactions** automatically. It ensures that a group of database operations is executed as a single unit of work.

**How It Works**

When a method marked with **@Transactional** is called:

1. Spring starts a **Transaction**.
2. All database operations run inside that transaction.
3. If everything succeeds, Spring performs a **COMMIT**.
4. If an exception occurs, Spring performs a **ROLLBACK** and undoes all changes.


`@Transactional` is an annotation in **Spring Framework** that tells Spring:

👉 “Run this method inside a database transaction”

- All operations succeed → COMMIT
- Any operation fails → ROLLBACK


It follows **ACID properties:**

| Property    | Meaning                            |
| ----------- | ---------------------------------- |
| Atomicity   | All operations succeed or none (all-or-nothing). |
| Consistency | Data remains valid and follows all rules                 |
| Isolation   | Transactions do not interfere with each other       |
| Durability  | Once committed, data is permanently saved even after a crash          |


**Key Features**

* Automatic **Transaction Management**
* Supports **COMMIT** and **ROLLBACK**
* Ensures **ACID** properties
* Reduces boilerplate code
* Managed by Spring using **AOP (Proxy)**


**When to Use**

Use **@Transactional** when multiple database operations must be treated as one unit.

Examples:

* Money transfer between accounts
* Creating an order and updating inventory
* Saving data in multiple tables

**Code Example**

```java
@Service
public class BankService {

    @Transactional
    public void transferMoney() {

        withdrawMoney();
        depositMoney();
    }
}
```

If `depositMoney()` fails, Spring rolls back the transaction and `withdrawMoney()` is also undone.


**Example (Bank Transfer)** Transfer ₹100 from Account A → Account B:

1. Debit from A
2. Credit to B

If credit fails, debit must be undone → **Rollback**

**1. Using Spring `@Transactional` (Most Common)**
```java
@Service
public class BankService {

    @Transactional
    public void transferMoney() {
        debitFromAccountA();
        creditToAccountB();
    }
}
```


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

**Transaction Propagation** defines **how a transactional method should behave when it is called from another transactional method**. It decides whether to **join the existing transaction or create a new one**.

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


## 21. What happens if payment is successful but order update fails?

This is handled using **event-driven architecture and retry/compensation (Saga pattern)**. If order creation fails after payment, we retry or trigger refund.


**Event + Retry Example**

```java
@Service
public class PaymentHandler {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    public void handlePaymentSuccess(Payment payment) {
        try {
            orderService.createOrder(payment);
        } catch (Exception e) {

            // Retry logic (can be Kafka/RabbitMQ in real systems)
            retry(payment);

            // Or compensation (refund)
            paymentService.refund(payment.getTransactionId());
        }
    }

    private void retry(Payment payment) {
        // simple retry logic
        orderService.createOrder(payment);
    }
}
```

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



# ✅ 22. Java kafka and RabbitMQ


## 1. What is Kafka and How Does It Work?

**Apache Kafka** is a **distributed event streaming platform** used to **publish, store, and process real-time data streams**. It enables different applications to communicate **asynchronously** through messages.

**Key Features:**

* **High throughput** and **low latency**.
* Supports **asynchronous communication**.
* **Scalable** and **fault-tolerant**.
* Stores messages durably for a configurable period.
* Supports **multiple producers and consumers**.

**Main Components:**

* **Producer** → Sends messages to Kafka.
* **Topic** → A logical channel where messages are stored.
* **Partition** → A topic is divided into partitions for parallel processing.
* **Broker** → Kafka server that stores and manages messages.
* **Consumer** → Reads messages from a topic.
* **Consumer Group** → Multiple consumers working together to process messages.

**How it Works:**

1. A **Producer** sends a message to a **Topic**.
2. The message is stored in one of the topic's **Partitions** on a **Kafka Broker**.
3. Kafka persists the message for a configured retention period.
4. A **Consumer** or **Consumer Group** reads the message from the topic.
5. Multiple consumers can process messages independently and asynchronously.

**When to Use:**

* Building **microservices** with **event-driven architecture**.
* **Real-time data processing** and analytics.
* **Log aggregation** and monitoring.
* Processing **orders**, **payments**, **notifications**, or **user activity events**.
* Decoupling services for better **scalability** and **reliability**.

**Code Example (Spring Boot):**

**Producer:**

```java id="k8m4xp"
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

public void sendMessage() {
    kafkaTemplate.send("orders-topic", "Order Created");
}
```

**Consumer:**

```java id="v5q9tn"
@KafkaListener(topics = "orders-topic", groupId = "order-group")
public void consume(String message) {
    System.out.println("Received: " + message);
}
```


## 2. How does Kafka achieve high throughput and low latency?

**Kafka** achieves **high throughput** and **low latency** by using an efficient **distributed architecture** and optimized disk and network operations.

**Key Features:**

* **Sequential disk writes** instead of random writes.
* **Partitioning** for parallel processing.
* **Batch processing** of messages.
* Uses **zero-copy** data transfer.
* Supports **horizontal scaling** with multiple brokers.

**How it Works:**

1. **Producers** send messages to a **Topic**.
2. The topic is divided into **Partitions**, allowing multiple producers and consumers to work in parallel.
3. Kafka writes messages **sequentially** to disk, which is much faster than random writes.
4. Messages are sent and fetched in **batches**, reducing network overhead.
5. Kafka uses the **zero-copy** technique, transferring data directly from disk to the network without unnecessary copying between application and kernel memory.
6. Multiple **Consumers** in a **Consumer Group** read different partitions simultaneously, increasing throughput.

**Main Reasons for High Performance:**

* **Sequential I/O** → Fast disk operations.
* **Partitioning** → Parallel read and write operations.
* **Batching** → Fewer network requests.
* **Zero-Copy** → Reduced CPU and memory usage.
* **Distributed Brokers** → Easy horizontal scaling.

**When to Use:**

* **Real-time event streaming**.
* **High-volume message processing**.
* **Microservices communication**.
* **Log aggregation**, **analytics**, and **payment/order processing** systems.

**Code Example (Parallel Processing with Partitions):**

```java id="h7m4xp"
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

public void sendOrder(String orderId) {
    kafkaTemplate.send("orders-topic", orderId);
}
```

```java id="p5q8tn"
@KafkaListener(topics = "orders-topic", groupId = "order-group")
public void consume(String message) {
    System.out.println("Processing: " + message);
}
```


## 3. What is the difference between a topic and a partition?

A **Topic** is a **logical category or channel** where messages are published, while a **Partition** is a **physical subdivision of a topic** used to store and process messages in parallel.

| **Feature**      | **Topic**                                   | **Partition**                                       |
| ---------------- | ------------------------------------------- | --------------------------------------------------- |
|    | A logical stream of messages.               | A smaller unit inside a topic.                      |
| **Purpose**      | Organizes related messages.                 | Enables **parallel processing** and scalability.    |
| **Data Storage** | Does not store data directly.               | Actually stores the messages.                       |
| **Scalability**  | A topic can have multiple partitions.       | More partitions increase throughput.                |
| **Ordering**     | No global ordering across the entire topic. | Messages are ordered **within a single partition**. |

**How it Works:**

1. A **Producer** sends a message to a **Topic**.
2. Kafka places the message into one of the topic's **Partitions** (based on a key or round-robin).
3. Each partition stores messages in the order they arrive.
4. **Consumers** in a **Consumer Group** can read different partitions simultaneously, enabling parallel processing.

**Key Features:**

* **Topic** = Logical container for messages.
* **Partition** = Physical storage unit inside a topic.
* More **partitions** mean higher **throughput** and better **scalability**.
* Message ordering is guaranteed **only within a partition**.

**When to Use:**

* Create a **Topic** for each type of event (e.g., `orders`, `payments`, `notifications`).
* Increase the number of **Partitions** when you need higher parallelism and want multiple consumers to process data concurrently.

**Code Example:**

```java id="k8m3xp"
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

public void sendOrder() {
    kafkaTemplate.send("orders-topic", "Order Created");
}
```

In this example:

* **`orders-topic`** is the **Topic**.
* Kafka internally stores the message in one of the topic's **Partitions**.

**Easy Memory Trick:**

* **Topic = Folder** 📁 (logical category)
* **Partition = File inside the folder** 📄 (actual storage unit)



## 4. How does Kafka handle durability and fault tolerance?

**Kafka** achieves **durability** and **fault tolerance** by **persisting messages to disk** and **replicating partitions across multiple brokers**. This ensures that data is not lost even if a server fails.

**Key Features:**

* **Persistent storage** of messages on disk.
* **Partition replication** across multiple brokers.
* **Leader-Follower architecture**.
* Automatic **failover** if a broker goes down.
* Configurable **replication factor** for higher reliability.

**How it Works:**

1. A **Producer** sends a message to a **Topic Partition**.
2. The partition has one **Leader** and one or more **Follower replicas**.
3. The **Leader** writes the message to disk and replicates it to the followers.
4. If the leader broker fails, Kafka automatically promotes an **in-sync follower** to become the new leader.
5. Consumers continue reading from the new leader with minimal interruption.

**Key Concepts:**

* **Durability** → Messages are stored on disk and retained for a configured period.
* **Replication Factor** → Number of copies of a partition across brokers.
* **Leader Partition** → Handles all read and write requests.
* **Follower Partition** → Keeps a synchronized copy of the leader's data.
* **Fault Tolerance** → If one broker fails, another replica takes over automatically.

**When to Use:**

* **Critical business systems** like payments and order processing.
* **Event-driven microservices** requiring reliable messaging.
* Applications that require **high availability** and **data reliability**.

**Code Example (Spring Kafka Producer):**

```java id="k7m4xp"
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

public void sendOrder() {
    kafkaTemplate.send("orders-topic", "Order Created");
}
```

**Example Configuration:**

```properties
# Replicate each partition to 3 brokers
replication.factor=3

# Wait for all replicas to acknowledge
acks=all
```

**Easy Memory Trick:**

* **Durability = Store on Disk** 💾
* **Fault Tolerance = Replicate Across Brokers** 🔄



## 5. What is a consumer group and how does it work?

A **Consumer Group** in **Kafka** is a group of **consumers** that work together to read messages from a **topic**. Kafka distributes the topic's **partitions** among the consumers so that each message is processed **only once per group**.

**Key Features:**

* Enables **parallel message processing**.
* Provides **load balancing** across consumers.
* Ensures each partition is consumed by **only one consumer** within the same group.
* Supports **fault tolerance** through automatic **rebalancing**.

**How it Works:**

1. Multiple consumers join the same **Consumer Group** using a common **`groupId`**.
2. Kafka assigns the topic's **partitions** among the consumers.
3. Each consumer reads messages only from its assigned partitions.
4. If a consumer fails, Kafka automatically **reassigns** its partitions to the remaining consumers.
5. If a new consumer joins, Kafka performs **rebalancing** and redistributes the partitions.

**Example:**

* Topic: **`orders-topic`**
* Partitions: **4**
* Consumer Group: **`order-group`**
* Consumers: **2**

Kafka assigns:

* **Consumer 1** → Partition 0, 1
* **Consumer 2** → Partition 2, 3

This allows messages to be processed **in parallel**, increasing throughput.

**When to Use:**

* Building **scalable event-driven applications**.
* Processing **large volumes of messages**.
* **Microservices** that need load balancing and fault tolerance.
* Systems like **order processing**, **payment processing**, and **log analytics**.

**Code Example:**

```java id="k8m4xp"
@KafkaListener(
    topics = "orders-topic",
    groupId = "order-group"
)
public void consume(String message) {
    System.out.println("Received: " + message);
}
```


## 6. How does Kafka ensure message ordering?

**Kafka** guarantees **message ordering within a single partition**. Messages are stored and consumed in the **same order** they are written to that partition.

**Key Features:**

* **Ordering is guaranteed only within a partition**.
* Messages are assigned a unique **offset** in sequence.
* Using the same **message key** ensures related messages go to the same partition.
* Multiple partitions improve scalability but do **not** guarantee global ordering.

**How it Works:**

1. A **Producer** sends messages to a **Topic**.
2. Kafka assigns each message to a **Partition**.
3. Messages inside a partition are written sequentially and given increasing **offsets**.
4. A **Consumer** reads messages in offset order, preserving the original sequence.

**Example:**
If all messages for **Order ID = 101** use the same key:

```text
Order Created
Order Paid
Order Shipped
Order Delivered
```

Kafka sends them to the **same partition**, so they are consumed in the **exact same order**.

**When to Use:**

* **Order processing** systems.
* **Banking and payment** transactions.
* **Inventory management**.
* Any application where the **sequence of events matters**.

**Code Example:**

```java id="k7m4xp"
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

public void sendOrderEvent() {
    kafkaTemplate.send(
        "orders-topic",
        "101",                  // Message Key
        "Order Created"
    );
}
```

Using the same **key (`101`)** ensures all events for that order are routed to the **same partition**, maintaining their order.

**Important Point:**

* **Single Partition** → Ordering guaranteed.
* **Multiple Partitions** → Ordering guaranteed **only within each partition**, not across the entire topic.


## 7. What Happens If a Consumer Crashes Before Committing the Offset?

If a **Kafka Consumer** crashes **before committing the offset**, Kafka assumes the message was **not successfully processed**. When the consumer restarts (or another consumer in the same group takes over), it **re-reads the message from the last committed offset**.

**Key Features:**

* Kafka tracks the **last committed offset** for each consumer group.
* If the offset is **not committed**, the message will be **consumed again**.
* Prevents **message loss**.
* May result in **duplicate message processing**.

**How it Works:**

1. The consumer reads a message from a partition.
2. It starts processing the message.
3. Before committing the offset, the consumer crashes.
4. Kafka triggers a **rebalance** and assigns the partition to another consumer (or the same consumer after restart).
5. The new consumer starts reading from the **last committed offset**, so the uncommitted message is processed again.

**Example:**

* Last committed offset = **10**
* Consumer reads and processes offset **11**
* Consumer crashes before committing **11**
* After restart, Kafka starts reading again from **offset 11**

As a result, **offset 11 is processed twice**, but **no message is lost**.

**When to Use This Behavior:**

* Systems where **data loss is unacceptable**, such as:

  * **Payment processing**
  * **Order management**
  * **Banking transactions**
  * **Inventory updates**

In these cases, applications should implement **idempotency** to safely handle duplicate messages.

**Code Example (Manual Offset Commit):**

```java id="k8m4xp"
@KafkaListener(topics = "orders-topic")
public void consume(String message,
                    Acknowledgment ack) {

    processMessage(message);

    ack.acknowledge(); // Commit offset after successful processing
}
```

If the application crashes **before `ack.acknowledge()`**, the message will be consumed again after recovery.

**Easy Memory Trick:**

* **Commit Done** → Message will **not** be reprocessed.
* **Commit Not Done** → Message will be **read again**.



## 8. What is RabbitMQ and When to Use It Over Kafka?

**RabbitMQ** is an **open-source message broker** that enables applications to communicate by sending and receiving messages through **queues**. It is designed for **reliable message delivery** and **task-based communication**.

**Key Features:**

* Uses **queues** to store and deliver messages.
* Supports **message acknowledgments** and **retries**.
* Provides **routing**, **exchange types**, and **dead-letter queues (DLQ)**.
* Easy to set up for **request-response** and **task queue** scenarios.
* Supports multiple messaging protocols like **AMQP**.

**How it Works:**

1. A **Producer** sends a message to an **Exchange**.
2. The exchange routes the message to one or more **Queues** based on routing rules.
3. A **Consumer** reads the message from the queue.
4. After successful processing, the consumer sends an **acknowledgment**, and RabbitMQ removes the message from the queue.

**RabbitMQ vs Kafka:**

| **Feature**           | **RabbitMQ**                                  | **Kafka**                                            |
| --------------------- | --------------------------------------------- | ---------------------------------------------------- |
| **Model**             | Message Broker                                | Event Streaming Platform                             |
| **Message Storage**   | Queue-based                                   | Topic and Partition-based                            |
| **Throughput**        | Moderate                                      | Very High                                            |
| **Message Retention** | Removed after acknowledgment                  | Retained for a configured period                     |
| **Ordering**          | Queue order                                   | Guaranteed within a partition                        |
| **Best For**          | Task queues, request-response, job processing | Real-time streaming, event-driven systems, analytics |

**When to Use RabbitMQ Over Kafka:**

* **Task queues** and background job processing.
* **Request-response** communication.
* Systems requiring **complex message routing**.
* Applications where messages should be **removed after successful processing**.
* Email sending, notification services, and order processing workflows.

**When to Use Kafka Instead:**

* **High-volume event streaming**.
* **Microservices event-driven architecture**.
* **Real-time analytics** and log aggregation.
* Applications that need **high throughput** and **message retention**.

**Code Example (Spring Boot with RabbitMQ):**

```java id="k8m4xp"
@Autowired
private RabbitTemplate rabbitTemplate;

public void sendMessage() {
    rabbitTemplate.convertAndSend(
        "orderQueue",
        "Order Created"
    );
}
```

**Consumer:**

```java id="p5q9tn"
@RabbitListener(queues = "orderQueue")
public void receive(String message) {
    System.out.println("Received: " + message);
}
```

**Easy Memory Trick:**

* **RabbitMQ = Queue + Task Processing** 🐇
* **Kafka = Stream + Event Processing** 📡



## 9. What is gRPC and How Does It Differ from REST ?

**gRPC (Google Remote Procedure Call)** is a **high-performance communication framework** that allows one service to call methods on another service as if they were local. It uses **HTTP/2** for transport and **Protocol Buffers (Protobuf)** for efficient binary data serialization.

**Key Features:**

* Uses **HTTP/2** for fast communication.
* Uses **Protocol Buffers (Protobuf)** instead of JSON.
* Supports **bi-directional streaming**.
* Generates client and server code automatically.
* Ideal for **microservices** and **internal service-to-service communication**.

**How it Works:**

1. Define the service and message structure in a **`.proto`** file.
2. gRPC generates client and server code from the `.proto` definition.
3. The client calls a remote method.
4. Data is serialized using **Protobuf** and sent over **HTTP/2**.
5. The server processes the request and returns the response.

**gRPC vs REST:**

| **Feature**         | **gRPC**                              | **REST**                           |
| ------------------- | ------------------------------------- | ---------------------------------- |
| **Protocol**        | **HTTP/2**                            | **HTTP/1.1** (commonly)            |
| **Data Format**     | **Protocol Buffers (Binary)**         | **JSON (Text)**                    |
| **Performance**     | Faster and lightweight                | Slower due to JSON parsing         |
| **Streaming**       | Supports **bi-directional streaming** | Limited streaming support          |
| **Code Generation** | Automatic from `.proto` files         | Manual API client creation         |
| **Best For**        | Internal microservices communication  | Public APIs and web/mobile clients |

**When to Use:**

* Use **gRPC** for:

  * **Microservices** communication.
  * **Low-latency, high-performance** systems.
  * Real-time applications requiring **streaming**.
* Use **REST** for:

  * **Public APIs**.
  * Web and mobile applications.
  * Systems where **human-readable JSON** is preferred.

**Code Example (`.proto` file):**

```proto id="x7m4kp"
syntax = "proto3";

service UserService {
  rpc getUser(UserRequest) returns (UserResponse);
}

message UserRequest {
  int32 id = 1;
}

message UserResponse {
  string name = 1;
}
```

**Easy Memory Trick:**

* **gRPC = Fast + Binary + HTTP/2 + Microservices** ⚡
* **REST = Simple + JSON + HTTP + Public APIs** 🌐


## 10. What is a Service Mesh (Istio)?

A **Service Mesh** is an infrastructure layer that **manages communication between microservices**. **Istio** is one of the most popular service mesh implementations, providing features like **traffic management**, **security**, **load balancing**, and **monitoring** without changing application code.

**Key Features:**

* **Service-to-service communication** management.
* Built-in **load balancing** and **traffic routing**.
* **Mutual TLS (mTLS)** for secure communication.
* **Observability** with metrics, logs, and distributed tracing.
* Supports **circuit breaking**, **retries**, and **fault injection**.

**How it Works:**

1. Each microservice gets a lightweight **sidecar proxy** (usually **Envoy**) deployed alongside it.
2. All incoming and outgoing network traffic passes through the sidecar proxy.
3. **Istio's Control Plane** configures and manages these proxies.
4. The proxies handle tasks like **routing**, **security**, **monitoring**, and **traffic policies**, while the application focuses only on business logic.

**Main Components:**

* **Data Plane** → Collection of **Envoy sidecar proxies** handling traffic.
* **Control Plane** → **Istio** components that configure and manage the proxies.

**When to Use:**

* In **microservices architectures** with many services.
* When you need **secure service-to-service communication**.
* For **traffic management**, **canary deployments**, and **A/B testing**.
* When implementing **observability** and **distributed tracing**.

**Example:**
Suppose **Order Service** calls **Payment Service**:

* Without Istio: The application handles retries, security, and monitoring.
* With Istio: The **sidecar proxies** automatically manage **retries**, **load balancing**, **mTLS encryption**, and **metrics collection**.

**Advantages:**

* Removes networking concerns from application code.
* Improves **security**, **reliability**, and **observability**.
* Simplifies management of large-scale **microservices**.
* Supports advanced deployment strategies like **canary releases**.

**Code Example (Istio Virtual Service):**

```yaml id="k8m4xp"
apiVersion: networking.istio.io/v1beta1
kind: VirtualService
metadata:
  name: order-service
spec:
  hosts:
  - order-service
  http:
  - route:
    - destination:
        host: order-service
```

**Easy Memory Trick:**

* **Microservices = Cities** 🏙️
* **Service Mesh = Road Network** 🛣️
* **Istio = Smart Traffic Controller** 🚦



# ✅ 23. Java Features of Version wise.

## 1. What are the features in Java 8?

Java 8 was a major release that introduced functional programming features and significantly changed how Java code is written. It's one of the most important Java releases.

**Major Features:**
* **Lambda Expressions:** Anonymous functions used to write shorter and cleaner code.
* **Stream API:** Allows processing collections using operations like filter, map, and reduce.
* **Optional Class:** A container object used to handle possible null values safely.
* **Default Methods:** Methods in interfaces that have a default implementation.
* **Method References:** A shorter way to call existing methods using `::` syntax.
* **Date/Time API:** The `java.time` package provides improved classes for handling date and time.
* **Nashorn JavaScript Engine:** A Java engine that allows executing JavaScript code inside the JVM.
  
**Features in Java 8**

**Java 8** introduced several powerful features that make code more **concise**, **readable**, and **efficient**, especially for **functional programming** and **parallel data processing**.

**Key Features:**

1. **Lambda Expressions (`->`)**

   * Write anonymous functions with less code.
   * Reduces boilerplate code.

   ```java id="j8l1a2"
   List<String> names = Arrays.asList("A", "B", "C");
   names.forEach(name -> System.out.println(name));
   ```

2. **Functional Interfaces**

   * An interface with **only one abstract method**.
   * Annotated with **`@FunctionalInterface`**.
   * Examples: `Runnable`, `Callable`, `Comparator`.

   ```java id="j8f3b4"
   @FunctionalInterface
   interface MyInterface {
       void display();
   }
   ```

3. **Stream API**

   * Processes collections using **functional operations** like `filter()`, `map()`, and `collect()`.
   * Supports **parallel processing**.

   ```java id="j8s5c6"
   List<String> names = Arrays.asList("John", "Alex", "Bob");

   names.stream()
        .filter(name -> name.startsWith("J"))
        .forEach(System.out::println);
   ```

4. **Method References (`::`)**

   * A shorter way to refer to existing methods.

   ```java id="j8m7d8"
   List<String> names = Arrays.asList("A", "B", "C");
   names.forEach(System.out::println);
   ```

5. **Default and Static Methods in Interfaces**

   * Interfaces can now have method implementations.

   ```java id="j8i9e1"
   interface Vehicle {
       default void start() {
           System.out.println("Vehicle Started");
       }
   }
   ```

6. **Optional Class**

   * Helps avoid **`NullPointerException`** by representing optional values.

   ```java id="j8o2f3"
   Optional<String> name = Optional.ofNullable(null);
   System.out.println(name.orElse("Default Name"));
   ```

7. **New Date and Time API (`java.time`)**

   * Introduces immutable classes like `LocalDate`, `LocalTime`, and `LocalDateTime`.

   ```java id="j8d4g5"
   LocalDate today = LocalDate.now();
   System.out.println(today);
   ```

8. **Parallel Streams**

   * Enables parallel processing of collections using multiple CPU cores.

   ```java id="j8p6h7"
   List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

   numbers.parallelStream()
          .forEach(System.out::println);
   ```

9. **CompletableFuture**

   * Supports **asynchronous** and **non-blocking programming**.

   ```java id="j8c8i9"
   CompletableFuture.runAsync(() ->
       System.out.println("Async Task"));
   ```

**How It Works:**

* Java 8 adds **functional programming capabilities** using **lambda expressions** and **functional interfaces**.
* The **Stream API** processes collections efficiently.
* **Optional** handles null values safely.
* **Parallel Streams** and **CompletableFuture** improve concurrency and performance.

**When to Use:**

* Simplifying collection processing.
* Writing **clean and readable code**.
* Implementing **functional programming**.
* Performing **parallel and asynchronous operations**.
* Reducing **NullPointerException** using `Optional`.


## 2. What are the features in Java 11?

**Java 11** is a **Long-Term Support (LTS)** release that introduced several features to improve **developer productivity**, **performance**, and **HTTP communication**.

**Key Features:**
- **Local Variable Type Inference (var):** Enhanced for lambda parameters
- **HTTP Client API:** Built-in HTTP client
- **String Methods:** isBlank(), lines(), strip(), repeat()
- **Files Methods:** readString(), writeString()
- **Collection.toArray():** Enhanced method
- **Nest-Based Access Control:** Better inner class access
- **Java Flight Recorder (JFR):** is a built-in profiling and monitoring tool in Java.

1. **New HTTP Client API**

   * Introduced a modern **`HttpClient`** for sending **HTTP/2** and **WebSocket** requests.
   * Replaces the older `HttpURLConnection`.

   ```java id="j11h1"
   HttpClient client = HttpClient.newHttpClient();

   HttpRequest request = HttpRequest.newBuilder()
           .uri(URI.create("https://example.com"))
           .build();

   HttpResponse<String> response =
           client.send(request, HttpResponse.BodyHandlers.ofString());

   System.out.println(response.body());
   ```

2. **`var` in Lambda Parameters**

   * Allows using **`var`** for lambda expression parameters to improve readability and support annotations.

   ```java id="j11v2"
   List<String> names = Arrays.asList("Java", "Spring");
   names.forEach((var name) -> System.out.println(name));
   ```

3. **String Utility Methods**

   * Added useful methods like **`isBlank()`**, **`lines()`**, **`strip()`**, **`stripLeading()`**, and **`stripTrailing()`**.

   ```java id="j11s3"
   String text = "  Java  ";
   System.out.println(text.strip());
   System.out.println(text.isBlank());
   ```

4. **Files Utility Methods**

   * New methods **`readString()`** and **`writeString()`** simplify file handling.

   ```java id="j11f4"
   Path path = Path.of("data.txt");
   Files.writeString(path, "Hello Java 11");
   String content = Files.readString(path);
   ```

5. **Collection to Array**

   * Added a simpler way to convert collections to arrays using **`toArray()`**.

   ```java id="j11c5"
   List<String> list = List.of("A", "B", "C");
   String[] arr = list.toArray(String[]::new);
   ```

6. **Single-File Source Code Execution**

   * Java programs can be executed without explicit compilation.

   ```text id="j11t6"
   java HelloWorld.java
   ```

7. **Nested-Based Access Control**

   * Improves access between nested classes, reducing the need for compiler-generated bridge methods and improving performance.

**How It Works:**

* Java 11 extends Java 8 features with a **modern HTTP client**, **better String and File APIs**, and **simplified coding syntax**.
* The JVM and standard libraries provide these enhancements without changing existing application logic.

**When to Use:**

* Building **REST clients** using the new `HttpClient`.
* Simplifying **String** and **file operations**.
* Running small Java programs quickly with **single-file execution**.
* Developing enterprise applications on a stable **LTS version**.


## 3. What are the features in Java 17?

**Java 17** is a **Long-Term Support (LTS)** release that brings improvements in **code readability**, **performance**, **security**, and **developer productivity**. It is widely used for modern **Spring Boot** and **enterprise applications**.

**Major Features:**
- **Sealed Classes:** Restrict class inheritance
- **Pattern Matching for instanceof:** Simplified type checking
- **Records:** Immutable data classes
- **Text Blocks:** Multi-line string literals
- **Switch Expressions:** Enhanced switch statements
- **Helpful NullPointerExceptions:** Better error messages
- **Strong Encapsulation:** JDK internals encapsulated


1. **Sealed Classes**

   * Restrict which classes can extend or implement a class or interface.
   * Improves **inheritance control**.

   ```java id="j17s1"
   public sealed class Shape
       permits Circle, Rectangle {
   }

   final class Circle extends Shape { }
   final class Rectangle extends Shape { }
   ```

2. **Pattern Matching for `switch` (Preview)**

   * Simplifies complex `if-else` and `switch` statements by matching object types.

   ```java id="j17p2"
   Object obj = "Java";

   switch (obj) {
       case String s -> System.out.println(s.toUpperCase());
       default -> System.out.println("Unknown");
   }
   ```

3. **Enhanced `switch` Expressions**

   * Allows `switch` to return values with a cleaner syntax.

   ```java id="j17e3"
   int day = 1;

   String result = switch (day) {
       case 1 -> "Monday";
       default -> "Other Day";
   };
   ```

4. **Text Blocks**

   * Makes writing **multi-line strings** easier and more readable.

   ```java id="j17t4"
   String json = """
       {
         "name": "Java",
         "version": 17
       }
       """;
   ```

5. **Records**

   * A compact way to create **immutable data classes** without writing boilerplate code like getters, constructors, and `toString()`.

   ```java id="j17r5"
   public record Employee(
       int id,
       String name
   ) {}
   ```

6. **New Random Number Generator API**

   * Introduces improved and flexible random number generators.

   ```java id="j17n6"
   Random random = new Random();
   System.out.println(random.nextInt(100));
   ```

7. **Strong Encapsulation of JDK Internals**

   * Improves **security** by preventing direct access to internal JDK APIs.

**How It Works:**

* Java 17 extends previous Java versions with features that reduce **boilerplate code**, improve **type safety**, and provide **cleaner syntax**.
* The JVM and compiler support these enhancements while maintaining backward compatibility.

**When to Use:**

* Building modern **Spring Boot 3.x** applications.
* Creating **immutable data models** using Records.
* Controlling inheritance with **Sealed Classes**.
* Writing cleaner code with **Text Blocks** and **enhanced switch expressions**.
* Enterprise applications requiring a stable **LTS version**.


## 4. What are the features in Java 21?

**Java 21** is a **Long-Term Support (LTS)** release that focuses on **simpler concurrency**, **better performance**, and **improved developer productivity**. It introduces modern features that make Java applications easier to write and scale.

**Key Features:**

1. **Virtual Threads**

   * Lightweight threads managed by the JVM.
   * Allow applications to handle **millions of concurrent tasks** with lower memory usage.

   ```java id="j21v1"
   Thread.startVirtualThread(() -> {
       System.out.println("Running in a Virtual Thread");
   });
   ```

2. **Record Patterns**

   * Simplifies extracting data from **record objects**.

   ```java id="j21r2"
   record Person(String name, int age) {}

   Object obj = new Person("John", 25);

   if (obj instanceof Person(String name, int age)) {
       System.out.println(name + " " + age);
   }
   ```

3. **Pattern Matching for `switch`**

   * Allows `switch` statements to match object types directly, reducing complex `if-else` logic.

   ```java id="j21p3"
   Object obj = "Java";

   switch (obj) {
       case String s -> System.out.println(s.toUpperCase());
       default -> System.out.println("Unknown");
   }
   ```

4. **Sequenced Collections**

   * Introduces a common API for collections that have a defined encounter order, with methods like `getFirst()` and `getLast()`.

   ```java id="j21s4"
   List<String> list = List.of("A", "B", "C");
   System.out.println(list.getFirst());
   ```

5. **String Templates (Preview)**

   * Provides a cleaner and safer way to create dynamic strings.

   ```java id="j21t5"
   String name = "Java";
   String message = STR."Hello \{name}";
   ```

6. **Unnamed Patterns and Variables (Preview)**

   * Uses `_` to ignore unused variables, making code cleaner.

   ```java id="j21u6"
   if (obj instanceof Person(_, int age)) {
       System.out.println(age);
   }
   ```

**How It Works:**

* **Virtual Threads** improve concurrency by allowing the JVM to efficiently manage many lightweight threads.
* **Pattern Matching** and **Record Patterns** reduce boilerplate code.
* **Sequenced Collections** and **String Templates** make APIs easier to use and code more readable.

**When to Use:**

* Building **high-concurrency** applications and **microservices**.
* Developing **Spring Boot 3.x** applications on Java 21.
* Simplifying object processing with **Pattern Matching**.
* Writing cleaner and more maintainable Java code.
* Enterprise applications requiring a stable **LTS version**.


## 5. What is the Java release cycle and LTS versions?

Java moved to a 6-month release cycle in 2017, providing regular updates with new features while maintaining Long Term Support versions for enterprise stability.

**Release Cycle:**
- **6-month releases:** March and September each year
- **Feature releases:** Java 9, 10, 12, 13, 14, 15, 16, 18, 19, 20, 21...
- **LTS releases:** Every 3 years (Java 8, 11, 17, 21...)
- **Support timeline:** LTS versions supported for 8+ years

**LTS Versions:**
- **Java 8 (2014):** Lambda expressions, Stream API - supported until 2030+
- **Java 11 (2018):** HTTP Client, var enhancements - supported until 2026+
- **Java 17 (2021):** Sealed classes, Records, Pattern matching - supported until 2029+
- **Java 21 (2023):** Latest LTS with Virtual Threads, Pattern matching

**Benefits of 6-month cycle:**
- **Faster innovation:** New features delivered quickly
- **Predictable releases:** Regular schedule for planning
- **Reduced risk:** Smaller changes per release
- **LTS stability:** Enterprise applications can stick to LTS versions

**Choosing Java Version:**
- **Enterprise applications:** Use LTS versions (8, 11, 17, 21)
- **New projects:** Consider latest LTS (Java 17 or 21)
- **Experimentation:** Try latest feature releases for new capabilities
- **Migration strategy:** Plan upgrades around LTS releases




# ✅ 24. Java and Application Security

## 0. What are security vulnerability issues?

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


## 6: What is encryption and decryption in Java?


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

**SSL** stands for Secure Sockets Layer, and **TLS** stands for Transport Layer Security

**SSL/TLS in Java** are **secure communication protocols** for encrypted data transmission (e.g., **HTTPS**).

They use a **handshake process** and **certificates** to establish trust, supported by **JSSE**, with **KeyStore and TrustStore** for managing keys and certificates.

```java
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
</dependency>

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

// SSL/TLS client example
SSLContext sslContext = SSLContext.getInstance("TLS");
sslContext.init(null, null, null);

SSLSocketFactory factory = sslContext.getSocketFactory();
SSLSocket socket = (SSLSocket) factory.createSocket("example.com", 443);

// HTTPS with RestTemplate
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response = restTemplate.getForEntity(
    "https://api.example.com/data", String.class);
```

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

**1. User Authentication**
```Java
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    
    String token = jwtUtil.generateToken(request.getUsername());
    return ResponseEntity.ok(new AuthResponse(token));
}
```

**2. JWT Token Generation**

```Java
public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
}
```

**3. JWT Filter for Token Validation**

```Java
String authHeader = request.getHeader("Authorization");

if (authHeader != null && authHeader.startsWith("Bearer ")) {
    String token = authHeader.substring(7);

    String username = jwtUtil.extractUsername(token);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtUtil.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
}
```

**4. Security Configuration**
```Java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}
```

## 12. JWT follow up common Questions?

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


## 12. What is CSRF Protection?

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

## 13. What is XSS Protection?

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


## 14. What is Input Validation?

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


## 15: What is Filter Chain?

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

## 16: What is SAML?

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


# ✅ 25. Java Performance and Optimization

## 0: Monitor application performance in Java?

**Application monitoring** is the continuous tracking of an application's **performance, health, and behavior in production**.

It includes monitoring metrics like response time, errors, CPU, and memory, along with logs and traces, using tools like **Prometheus**, **Grafana**, **New Relic**, **Datadog**, and **AppDynamics** to detect and resolve issues proactively.

**Production Monitoring Tools (Simple Detailed Table)**

| Tool                 | Type                | Why We Use It                                 | What It Monitors                                      | Example                     |
| -------------------- | ------------------- | --------------------------------------------- | ----------------------------------------------------- | --------------------------- |
| Prometheus           | Metrics             | Collect metrics from application              | CPU, Memory, Request count, Error rate, Response time | API response time = 200ms   |
| Grafana              | Dashboard           | Show metrics in graphs                        | Dashboards, Alerts, Charts                            | CPU usage graph             |
| Micrometer           | Metrics Library     | Send metrics from Spring Boot to Prometheus   | JVM, Custom metrics                                   | Heap memory                 |
| ELK Stack            | Logging             | Store and search logs                         | Error logs, Application logs                          | Exception logs              |
| Splunk               | Logging / Analytics | Advanced log analysis & monitoring            | Logs, Events, Security data                           | Payment failure logs        |
| Spring Boot Admin    | Monitoring          | Monitor Spring Boot apps                      | Health, Beans, Endpoints                              | App status UP/DOWN          |
| Spring Boot Actuator | Monitoring          | Expose application health & metrics endpoints | Health, Metrics, Info, Thread dump                    | `/actuator/health`          |
| Zipkin               | Tracing             | Track request flow between services           | Service call flow                                     | Order → Payment → Inventory |
| Jaeger               | Tracing             | Track microservice request                    | API calls                                             | Request time per service    |
| Datadog              | APM                 | Full system monitoring                        | Infra, Logs, APIs                                     | Server CPU                  |
| New Relic            | APM                 | Application performance monitoring            | Slow API, DB calls                                    | Slow query                  |
| Dynatrace            | APM                 | AI-based full stack monitoring                | Full stack                                            | Root cause detection        |
| AWS CloudWatch       | Cloud               | Monitor AWS services                          | EC2, RDS, Logs                                        | EC2 CPU                     |



**What We Monitor in Production (Simple Table)**

| Area          | What We Monitor           | Example Alert       |
| ------------- | ------------------------- | ------------------- |
| Server        | CPU, Memory, Disk         | CPU > 80%           |
| Application   | Request count, Error rate | Error rate > 5%     |
| API           | Response time             | API > 3 sec         |
| Database      | Query time                | Query > 2 sec       |
| JVM           | Heap memory, GC           | Memory > 80%        |
| Logs          | Errors                    | Too many exceptions |
| Microservices | Service response          | Service down        |
| Business      | Orders, Payments          | Payment failure     |


## 1. What are performance issues and solutions?


* **OutOfMemoryError :** - This happens when the JVM heap memory is full and cannot allocate new objects.
* **Memory Leaks** – Objects stay in memory and are not removed by the Java Garbage Collector, increasing memory usage over time.
* **CPU Bottlenecks / Inefficient Algorithms** – Poor algorithms or unnecessary loops increase CPU usage and slow the application.
* **Database Issues** – Slow queries or poor connection pool management delay database responses.
* **Thread Contention** – Multiple threads compete for the same resource, causing delays and blocking.
* **Too Many Object Creations** – Creating many objects increases memory usage and garbage collection work.
* **Garbage Collection Overhead** – Frequent garbage collection pauses the application and affects performance.
* **Metaspace issues :** - In some applications (like servers), classes loaded by a ClassLoader are not released, causing Metaspace memory issues. Too many classes loaded
* **Improper Cache Management :** - If caching is implemented without limits, cached objects can keep growing and consume memory.
* **Blocking I/O Operations** – File, network, or API calls block threads and reduce application throughput.


```java
// Memory leak example
public class LeakExample {
    private static List<String> cache = new ArrayList<>();
    
    public void addToCache(String data) {
        cache.add(data); // Never cleared - memory leak
    }
}
```

**Steps to Improve Performance(optimize)**

Here are **key points with one-line explanations** for improving performance in a **Spring Boot application**:

1. **Optimize Database Queries** – Write efficient queries, use indexes, and avoid unnecessary joins to reduce database load.
2. **Use Caching** – Store frequently accessed data in cache (e.g., **Redis**) to reduce repeated database calls.
3. **Enable Connection Pooling** – Use connection pools like **HikariCP** to reuse database connections efficiently.
4. **Use Pagination** – Load data in smaller chunks instead of fetching large datasets at once.
5. **Enable Asynchronous Processing** – Use `@Async` to execute time-consuming tasks in background threads.
6. **Avoid N+1 Query Problem** – Use proper fetching strategies in **Hibernate** to prevent multiple unnecessary queries.
7. **Use DTOs Instead of Entities** – Transfer only required fields instead of full entity objects.
8. **Enable HTTP Compression** – Compress API responses to reduce network payload and improve response time.
9. **Reduce Logging in Production** – Use appropriate log levels to avoid performance overhead.
10. **Monitor Application Performance** – Use tools like **Spring Boot Actuator** to identify bottlenecks.
11. **Optimize Thread Pool Configuration** – Configure server thread pools to handle concurrent requests efficiently.
12. **Use Lazy Initialization** – Load objects only when needed to reduce memory usage and startup time.

**Tools Used:**

* VisualVM
* JConsole
* Eclipse Memory Analyzer

**I usually:**

1. Check heap usage.
2. Analyze GC logs.
3. Take heap dumps.
4. Find large object retainers and memory leaks.


## 2. What are Java Memory Leak Issues?

A **Memory Leak** in Java occurs when **objects are no longer needed but are still referenced**, so the **Garbage Collector (GC)** cannot remove them. Over time, unused objects accumulate, increasing memory usage and may eventually cause an **OutOfMemoryError**.

**Symptoms**

* Increasing heap memory usage
* Frequent Full GC
* Application slowdown
* High memory consumption
* `OutOfMemoryError`

**Key Features**

* Caused by **unused objects** that are still referenced.
* **Garbage Collector** cannot reclaim their memory.
* Increases **heap memory** usage over time.
* Can lead to **performance issues** and **OutOfMemoryError**.
* Common in **long-running applications**.

**How It Works**

1. An object is created in the **Heap**.
2. The application no longer needs the object.
3. A reference to the object still exists.
4. **Garbage Collector** considers the object reachable.
5. The object remains in memory, causing a **Memory Leak**.

**Example**

**Memory Leak**

```java
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakExample {
    private static final List<String> cache = new ArrayList<>();
    public static void main(String[] args) {
        while (true) {
            cache.add("Java"); // Objects are never removed
        }
    }
}
```

**Why it leaks?**

* The **static List** always holds references to the objects.
* Since the references exist, the **Garbage Collector** cannot free the memory.
* Eventually, the application throws **OutOfMemoryError**.

**Fixed Example**

```java
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakFixed {
    public static void main(String[] args) {
        List<String> cache = new ArrayList<>();

        cache.add("Java");
        cache.add("Spring");

        cache.clear(); // Remove references
    }
}
```

**Common Causes of Memory Leaks**

1. **Static Collections** – Objects stored in **static** `List`, `Map`, or `Set` remain referenced for the lifetime of the application, preventing **Garbage Collection (GC)**.

2. **Improper Cache Management** – Caches grow indefinitely when **TTL (Time-to-Live)**, **size limits**, or **eviction policies** are not configured, leading to excessive memory usage.

3. **Unclosed Resources** – Database connections, file streams, sockets, or other resources are not properly closed, causing memory and resource leaks. Use **try-with-resources** whenever possible.

4. **ThreadLocal Misuse** – `ThreadLocal` values are not removed after use. In **thread pools**, the values remain attached to reused threads, causing memory leaks.

5. **Event Listener Leaks** – Event listeners, callbacks, or observers are registered but never deregistered, keeping objects referenced and preventing **Garbage Collection (GC)**.


**How to Prevent Memory Leaks**

* Remove unused objects from **collections**.
* Close resources using **try-with-resources**.
* Deregister **listeners** when no longer needed.
* Call **ThreadLocal.remove()** after use.
* Avoid unnecessary **static** references.
* Use **WeakReference** when appropriate.
* Monitor memory using profiling tools.

**Common Interview Follow-up Questions**

**1. Does Java have memory leaks even with Garbage Collection?**

**Yes.** **Garbage Collection** removes only **unreachable objects**. If an unused object is still referenced, it cannot be collected, resulting in a **Memory Leak**.

**2. What is the difference between Memory Leak and OutOfMemoryError?**

| **Memory Leak**                           | **OutOfMemoryError**                                              |
| ----------------------------------------- | ----------------------------------------------------------------- |
| Unused objects remain referenced          | JVM cannot allocate more memory                                   |
| Memory usage gradually increases          | Application fails due to insufficient memory                      |
| Can eventually cause **OutOfMemoryError** | Often the result of severe memory leaks or insufficient heap size |

**3. How do you detect Memory Leaks?**

Common tools include:

* **JVisualVM**
* **JConsole**
* **Eclipse Memory Analyzer (MAT)**
* **Java Flight Recorder (JFR)**
* **Java Mission Control (JMC)**

## 3. What are Latency isuue in java?

A **Latency Issue** is a **delay in processing or responding to a request**. It occurs when an application takes **longer than expected** to complete an operation, resulting in **slow response times**.

**Key Features**

* Increases **response time**.
* Reduces application **performance**.
* Can affect **user experience**.
* May occur due to **CPU**, **Memory**, **Database**, **Network**, or **External APIs**.
* Common in **high-traffic** applications.

**How It Works**

1. A client sends a request.
2. The application processes the request.
3. One or more operations become slow (Database, API, Disk I/O, GC, etc.).
4. The response is delayed, increasing **latency**.

**Common Causes**

* **Slow Database Queries**
* **Long Garbage Collection (GC) pauses**
* **Blocking I/O** operations
* **Slow External API** calls
* **Network latency**
* **Thread contention** or **deadlocks**
* **Insufficient Thread Pool** size
* **Large file processing**
* **CPU** or **Memory** bottlenecks

**Example**

**Latency Due to Synchronous API Calls**

```java
public String getUserDetails() {
    User user = userService.getUser();
    // Slow external API call
    Address address = addressService.getAddress();

    return user.getName() + " - " + address.getCity();
}
```

If `getAddress()` takes **5 seconds**, the entire request waits **5 seconds** before responding.

**Better Approach (Asynchronous Processing)**

```java
CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> userService.getUser());

CompletableFuture<Address> addressFuture = CompletableFuture.supplyAsync(() -> addressService.getAddress());

CompletableFuture.allOf(userFuture, addressFuture).join();

System.out.println(userFuture.join().getName() + " - " +
                   addressFuture.join().getCity());
```

Both API calls execute **in parallel**, reducing overall response time.

**How to Reduce Latency**

* Optimize **SQL queries** and add **Indexes**.
* Use **Caching** (Redis, Caffeine, etc.).
* Execute independent tasks using **CompletableFuture**.
* Use **Connection Pooling** for databases.
* Tune the **JVM** and **Garbage Collector**.
* Increase or tune the **Thread Pool** size.
* Avoid unnecessary **blocking** operations.
* Compress large responses and use **pagination**.
* Monitor application performance using **APM tools**.

**Common Interview Follow-up Questions**

**1. What is the difference between Latency and Throughput?**

| **Latency**                            | **Throughput**                                                         |
| -------------------------------------- | ---------------------------------------------------------------------- |
| Time taken to complete **one request** | Number of requests processed **per second**                            |
| Measured in **milliseconds (ms)**      | Measured in **Requests/Second (RPS)** or **Transactions/Second (TPS)** |
| Lower is better                        | Higher is better                                                       |

**2. How do you identify latency issues?**

Use monitoring and profiling tools such as:

* **JVisualVM**
* **Java Flight Recorder (JFR)**
* **Java Mission Control (JMC)**
* **Prometheus + Grafana**
* **New Relic**, **Dynatrace**, or **AppDynamics**

Check:

* **GC logs**
* **Thread dumps**
* **Heap dumps**
* **Slow SQL queries**
* **API response times**
* **CPU** and **Memory** usage

**3. How does Caching reduce latency?**

**Caching** stores frequently accessed data in memory, reducing repeated **database** or **API** calls and providing much faster responses.


## 4: What is database optimization?

**Database optimization** is the process of improving database performance and query speed.

It involves **proper indexing, writing efficient SQL queries, using connection pooling, caching, and good database design** to reduce load and improve response time.


```java
// Database optimization techniques
@Repository
public class OptimizedUserRepository {
    
    // Use indexes effectively
    @Query("SELECT u FROM User u WHERE u.email = :email") // Index on email
    User findByEmail(@Param("email") String email);
    
    // Batch operations
    @Modifying
    @Query("UPDATE User u SET u.lastLogin = :now WHERE u.id IN :ids")
    void updateLastLogin(@Param("ids") List<Long> ids, @Param("now") LocalDateTime now);
    
    // Pagination for large datasets
    @Query("SELECT u FROM User u ORDER BY u.createdAt DESC")
    Page<User> findAllUsers(Pageable pageable);
    
    // Fetch joins to avoid N+1 queries
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.orders WHERE u.id = :id")
    User findUserWithOrders(@Param("id") Long id);
}
```


## 5: What is query optimization?

**Query optimization** is the process of improving SQL query performance so that data is retrieved faster and database resources are used efficiently.

Common techniques include creating proper indexes, writing efficient JOIN and WHERE clauses, avoiding SELECT *, fetching only required data, using pagination for large datasets, and analyzing execution plans.

For example, if users are frequently searched by email, adding an index on the email column can significantly reduce query execution time.

```java
// Query optimization examples
@Repository
public class OptimizedQueryRepository {
    
    // Bad: N+1 query problem
    // List<Order> orders = orderRepository.findAll();
    // orders.forEach(order -> order.getCustomer().getName()); // N queries
    
    // Use indexes effectively
    @Query("SELECT u FROM User u WHERE u.email = :email") // Index on email
    User findByEmail(@Param("email") String email);

    // Good: Single query with join
    @Query("SELECT o FROM Order o JOIN FETCH o.customer")
    List<Order> findAllOrdersWithCustomers();
    
    // Use specific columns instead of SELECT *
    @Query("SELECT new com.example.UserDto(u.id, u.name, u.email) FROM User u")
    List<UserDto> findUserSummaries();
    
    // Optimize with proper WHERE conditions
    @Query("SELECT u FROM User u WHERE u.active = true AND u.createdAt > :date")
    List<User> findActiveUsersAfter(@Param("date") LocalDateTime date);
    
    // Use native query for complex optimizations
    @Query(value = "SELECT * FROM users u WHERE u.score > (SELECT AVG(score) FROM users)", 
           nativeQuery = true)
    List<User> findAboveAverageUsers();
}
```


## 6: What is pagination?

**Pagination** is a technique used to split large datasets into **smaller chunks (pages)** instead of loading all data at once.

It improves **performance, memory usage, and user experience**, and is usually implemented using **LIMIT/OFFSET or cursor-based pagination**.

```java
// Pagination implementation
@RestController
public class UserController {
    
    // Basic pagination
    @GetMapping("/users")
    public Page<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.findAll(pageable);
    }
    
    // Cursor-based pagination for better performance
    @GetMapping("/users/cursor")
    public List<User> getUsersCursor(
        @RequestParam(required = false) Long lastId,
        @RequestParam(defaultValue = "20") int limit) {
        
        return userService.findUsersAfter(lastId, limit);
    }
}

@Repository
public class UserRepository extends JpaRepository<User, Long> {
    
    // Cursor pagination query
    @Query("SELECT u FROM User u WHERE (:lastId IS NULL OR u.id > :lastId) ORDER BY u.id")
    List<User> findUsersAfter(@Param("lastId") Long lastId, Pageable pageable);
}
```


## 7. What are common 10 Production Issues?

**1. 🔴 Memory Leaks**
**Symptom:** Heap memory keeps growing over time, leads to frequent Full GC and `OutOfMemoryError`

**Causes:**
- Unreleased object references
- Static collections holding objects
- `ThreadLocal` leaks


**2. 🗄️ Connection Pool Exhaustion**
**Symptom:** All DB connections are used up; requests start waiting and eventually fail

**Causes:**
- Too many concurrent DB requests
- Connections not released properly
- Pool size too small for the load


**3. ⏱️ High GC Pause Time**
**Symptom:** Application becomes slow or unresponsive

**Causes:**
- JVM spends too much time in Garbage Collection
- Large heap with too many short-lived objects
- Wrong GC algorithm for the workload


**4. 🔒 Deadlocks**
**Symptom:** No progress in the system; threads hang forever

**Causes:**
- Two or more threads waiting for each other's locks
- Inconsistent lock acquisition order
- `T1` holds lock A waiting for B, `T2` holds lock B waiting for A


**5. 🧵 Thread Pool Starvation**
**Symptom:** New tasks keep waiting in the queue indefinitely

**Causes:**
- All worker threads are busy or blocked
- Pool size too small
- Blocking I/O inside thread pool tasks


**6. 🐢 Slow SQL Queries**
**Symptom:** Increased response time, locked tables, degraded throughput

**Causes:**
- Unoptimized or inefficient queries
- Missing indexes
- Full table scans on large datasets


**7. 📨 Kafka Consumer Lag**
**Symptom:** Consumers can't keep up with incoming messages; data delays increase

**Causes:**
- Consumers too slow to process messages
- Insufficient consumer instances
- Heavy processing logic inside consumers


**8. 📈 CPU Spikes**
**Symptom:** Overall system performance degrades suddenly

**Causes:**
- Infinite loops in code
- Heavy or excessive logging
- Bad algorithms with high time complexity
- GC thrashing


**9. 🌊 Cascading Failures**
**Symptom:** System instability spreads across multiple services

**Causes:**
- One failing service impacts multiple downstream services
- No circuit breakers in place
- Retry storms amplifying the failure


**10. 🔕 Missing Monitoring & Alerts**
**Symptom:** Issues exist but nobody notices early; small problems become major outages

**Causes:**
- No alerting configured for key metrics
- Lack of observability (logs, metrics, traces)
- No dashboards tracking system health


## 8. What are Java concurrency issues?

Common **Java concurrency issues** occur when multiple threads work on shared resources without proper coordination. This can cause incorrect results, slow performance, or application crashes.

1. **race condition :** -  happens when multiple threads access and modify shared data at the same time, and the final result depends on the order of execution.
2. **Deadlock :** -   occurs when two or more threads are waiting for each other’s resources, and none of them can proceed.
3. **Thread Starvation :** -  happens when a thread does not get enough CPU time because other threads with higher priority keep running.
4. **Livelock :** -  threads keep responding to each other and changing states, but no thread makes progress.
5. **Thread Contention :** -  This happens when multiple threads try to access the same resource simultaneously, causing threads to wait and reducing performance.
6. **Visibility Issues :** -  Changes made by one thread may **not be visible to other threads** due to CPU caching. Solution often involves using `volatile` or synchronization.
7. **Improper Synchronization**
Using too many or incorrect `synchronized` blocks can lead to **performance issues or inconsistent data**.


```java
// Race condition fix
private volatile boolean flag = false;
private final Object lock = new Object();

public void safeMethod() {
    synchronized(lock) {
        // Thread-safe operation
        flag = !flag;
    }
}
```


## 9: What is JVM tuning and parameters for performance tuning?

**JVM Tuning** is the process of **configuring JVM parameters** to improve **application performance**, **reduce Garbage Collection (GC) pauses**, **optimize memory usage**, and **increase throughput**.

**Key Features**

* **Optimizes Heap Memory** allocation.
* **Reduces GC pause time**.
* **Improves application response time**.
* **Prevents OutOfMemoryError**.
* **Increases CPU and memory efficiency**.

**How It Works**

1. JVM starts with **default settings**.
2. We configure **JVM startup parameters** based on application requirements.
3. JVM uses these settings for **memory allocation**, **GC**, and **thread management**.
4. Proper tuning results in **better performance** and **stable memory usage**.

**Common JVM Performance Tuning Parameters**

| **Parameter**                       | **Purpose**                  | **Example**                       |
| ----------------------------------- | ---------------------------- | --------------------------------- |
| **-Xms**                            | Initial Heap Size            | `-Xms512m`                        |
| **-Xmx**                            | Maximum Heap Size            | `-Xmx2g`                          |
| **-Xss**                            | Thread Stack Size            | `-Xss1m`                          |
| **-XX:+UseG1GC**                    | Enable G1 Garbage Collector  | `-XX:+UseG1GC`                    |
| **-XX:MaxGCPauseMillis**            | Target Maximum GC Pause Time | `-XX:MaxGCPauseMillis=200`        |
| **-XX:ParallelGCThreads**           | Number of GC Threads         | `-XX:ParallelGCThreads=8`         |
| **-XX:+HeapDumpOnOutOfMemoryError** | Generate Heap Dump on OOM    | `-XX:+HeapDumpOnOutOfMemoryError` |
| **-XX:HeapDumpPath**                | Heap Dump Location           | `-XX:HeapDumpPath=/logs`          |
| **-XX:+PrintGCDetails** *(Java 8)*  | Print GC Logs                | `-XX:+PrintGCDetails`             |
| **-Xlog:gc** *(Java 9+)*            | Enable GC Logging            | `-Xlog:gc`                        |

**Most Common JVM Startup Example**

```bash
java -Xms512m -Xmx2g -Xss1m \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -jar application.jar
```

**Explanation**

* **-Xms512m** → JVM starts with **512 MB Heap**.
* **-Xmx2g** → Maximum Heap is **2 GB**.
* **-Xss1m** → Each thread gets **1 MB Stack**.
* **UseG1GC** → Uses the **G1 Garbage Collector**.
* **MaxGCPauseMillis=200** → Tries to keep GC pauses around **200 ms**.

**When to Use JVM Tuning**

* **Spring Boot** applications.
* **Microservices**.
* **High-traffic web applications**.
* **Large enterprise applications**.
* Applications with **high memory usage** or **frequent GC**.
* Systems requiring **low response time**.

**Common Performance Tuning Areas**

**1. Heap Memory Tuning**

* Configure **-Xms** and **-Xmx** properly.
* Keep **Xms = Xmx** in production to avoid heap resizing.

**2. Garbage Collector Tuning**

* Choose the right **GC algorithm**.
* **G1GC** is the default and recommended for most applications.
* Tune **MaxGCPauseMillis** if low latency is important.

**3. Thread Tuning**

* Configure **-Xss** based on thread count.
* Too many threads with large stacks can consume excessive memory.

**4. GC Logging**

Enable GC logs to analyze memory behavior.

**Java 17 Example**

```bash
-Xlog:gc
```

**Code Example (Setting JVM Options in Spring Boot)**

```bash
java -Xms1g -Xmx2g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -jar springboot-app.jar
```

**Popular Garbage Collectors**

| **Garbage Collector** | **Best For**                     |
| --------------------- | -------------------------------- |
| **Serial GC**         | Small applications               |
| **Parallel GC**       | High throughput                  |
| **G1 GC**             | Most enterprise applications     |
| **ZGC**               | Very low latency and large heaps |
| **Shenandoah GC**     | Low pause-time applications      |

**Best Practices**

* Set **-Xms** and **-Xmx** appropriately.
* Use **G1GC** for most production workloads.
* Enable **GC logging** for analysis.
* Monitor **Heap**, **GC**, and **CPU** usage regularly.
* Avoid allocating unnecessary objects.
* Analyze **Heap Dumps** if memory usage keeps increasing.


**Common Interview Follow-up Questions**

**1. What is the difference between `-Xms` and `-Xmx`?**

* **-Xms** → **Initial Heap Size**.
* **-Xmx** → **Maximum Heap Size**.

**2. Why is G1GC commonly used?**

Because it provides **predictable GC pause times**, handles **large heaps efficiently**, and offers a good balance between **throughput** and **low latency**.

**3. Why keep `-Xms` and `-Xmx` the same in production?**

It avoids **heap resizing**, reducing GC overhead and improving application stability.

**4. How do you identify JVM performance issues?**

By monitoring:

* **Heap usage**
* **GC logs**
* **CPU utilization**
* **Thread dumps**
* **Heap dumps**

using tools like **VisualVM**, **Java Flight Recorder (JFR)**, **Java Mission Control (JMC)**, and **Eclipse Memory Analyzer (MAT)**.

**5. Does increasing `-Xmx` always improve performance?**

**No.** A larger heap can reduce GC frequency but may increase **GC pause times**. The heap size should be tuned based on the application's memory usage and workload.



## 10. What is Distributed Tracing?

**Distributed Tracing** is a **monitoring technique** used in **microservices architecture** to track a request as it travels across multiple services. It helps developers understand the complete path of a request and quickly identify **performance bottlenecks** or **failures**.

**Example:** A request to place an order may go through **API Gateway → Order Service → Payment Service → Inventory Service → Notification Service**. Distributed tracing shows the complete flow of that request.

**Key Features:**

* Tracks a request across **multiple microservices**.
* Uses a unique **Trace ID** and **Span ID**.
* Helps identify **slow services** and **errors**.
* Improves **debugging** and **performance monitoring**.
* Integrates with tools like **Zipkin**, **Jaeger**, and **OpenTelemetry**.

**How It Works:**

1. A client sends a request to the application.
2. The first service generates a unique **Trace ID**.
3. As the request moves between services, the same Trace ID is propagated.
4. Each service creates a **Span** representing its individual operation.
5. All spans are collected and sent to a tracing system (e.g., Zipkin or Jaeger).
6. Developers can view the complete request flow and timing information.

**Important Terms:**

* **Trace ID** – A unique identifier for the entire request.
* **Span** – A single operation or unit of work within the trace.
* **Span ID** – A unique identifier for an individual span.

**When to Use:**

* **Microservices architecture**.
* Debugging **service-to-service communication**.
* Finding **latency issues** and **bottlenecks**.
* Monitoring **distributed systems** and **cloud-native applications**.


**Spring Boot Example with Micrometer Tracing**

**Maven Dependency**

```xml id="jzptg7"
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>

<dependency>
    <groupId>io.zipkin.reporter2</groupId>
    <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

**application.yml**

```yaml id="dt1k8p"
management:
  tracing:
    sampling:
      probability: 1.0
```

**Service Class**

```java id="d36xf8"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    public void createOrder() {
        logger.info("Creating order");
        // Business Logic
        logger.info("Order created successfully");
    }
}
```

**application.properties**

```properties id="6fzpxt"
management.tracing.sampling.probability=1.0
management.zipkin.tracing.endpoint=http://localhost:9411/api/v2/spans
```

**Sample Log Output**

```text
2026-06-09 10:00:00
[traceId=abc123 spanId=xyz789]
Creating order
```

The **Trace ID** and **Span ID** automatically appear in logs, making it easy to track requests across services.

With **Spring Boot 3+** and **Micrometer Tracing/OpenTelemetry**, each request automatically gets a **Trace ID** that is propagated across services.

**Distributed Tracing Flow:**

```text id="dtr8w2"
Client Request
      │
      ▼
API Gateway
      │
      ▼
Order Service
      │
      ▼
Payment Service
      │
      ▼
Inventory Service
      │
      ▼
Notification Service
      │
      ▼
Response to Client
      │
      ▼
All Services Share the Same Trace ID
```


**Common Tools**

* **OpenTelemetry**
* **Zipkin**
* **Jaeger**
* **Micrometer Tracing**
* **Spring Boot Actuator**


## 11. What is Zipkin and how it Works?

**Zipkin** is a **distributed tracing tool** used in **microservices architecture** to track and monitor requests as they travel across multiple services.

It helps developers identify **latency issues**, **performance bottlenecks**, and **service failures**.

**Key Features**

* **Distributed Tracing**
* Tracks requests across multiple microservices
* Shows complete request flow
* Measures response time of each service
* Helps in debugging and performance monitoring
* Provides a visual trace through a web UI

**How It Works**

1. A request enters a microservice.
2. A unique **Trace ID** is generated.
3. Each service creates a **Span** (a unit of work).
4. Trace and span information are propagated to downstream services.
5. Services send tracing data to **Zipkin Server**.
6. Zipkin collects and displays the complete request journey.

**Example Flow**

```text
Client Request
      │
      ▼
Service A ──► Service B ──► Service C
  Span 1        Span 2        Span 3

      Same Trace ID: abc123
```

All services share the same **Trace ID**, while each service has its own **Span ID**.

**Important Terms**

* **Trace**: Complete journey of a request.
* **Span**: Single operation within a trace.
* **Trace ID**: Unique identifier for the entire request.
* **Span ID**: Unique identifier for a specific operation.

**When to Use**

* Microservices applications
* Debugging service-to-service communication
* Finding slow APIs
* Monitoring distributed systems
* Performance analysis and troubleshooting

**Spring Boot Example**

**Dependency**

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>

<dependency>
    <groupId>io.zipkin.reporter2</groupId>
    <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

**application.properties**

```properties
management.tracing.sampling.probability=1.0
management.zipkin.tracing.endpoint=http://localhost:9411/api/v2/spans
```

After starting the application, trace data is automatically sent to Zipkin.



## 12: What is profiling in Java?

**Profiling in Java** is the process of **analyzing the runtime behavior** of a Java application to identify **performance bottlenecks**, **high CPU usage**, **memory leaks**, and **slow methods**. It helps developers optimize application performance.

**Key Features:**

* Monitors **CPU usage**.
* Tracks **memory allocation** and **garbage collection (GC)**.
* Identifies **slow or frequently called methods**.
* Detects **memory leaks** and **thread issues**.
* Helps improve **application performance** and **resource utilization**.

**How It Works:**

1. A **Java Profiler** is attached to the running application.
2. The profiler collects runtime metrics such as CPU time, memory usage, thread activity, and method execution.
3. The collected data is analyzed to find bottlenecks.
4. Developers optimize the code based on the profiling results.

**Common Metrics Collected:**

* **CPU Usage** – Which methods consume the most CPU time.
* **Memory Usage** – How much memory objects occupy.
* **Heap Analysis** – Detects unnecessary object retention.
* **Thread Activity** – Finds blocked or deadlocked threads.
* **Garbage Collection (GC)** – Measures GC frequency and pause times.

**Popular Java Profiling Tools:**

* **JVisualVM**
* **JConsole**
* **Java Flight Recorder (JFR)**
* **YourKit Java Profiler**
* **JProfiler**

**When to Use:**

* Application is running **slowly**.
* Investigating **memory leaks**.
* High **CPU** or **heap memory** usage.
* Performance tuning before **production deployment**.
* Analyzing **thread contention** or **deadlocks**.

**Simple Example:**

```java id="jp7x2m"
public class Demo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            Math.sqrt(i);
        }

        long end = System.currentTimeMillis();
        System.out.println("Execution Time: " + (end - start) + " ms");
    }
}
```

A **Java Profiler** can analyze this program and show how much **CPU time** is spent inside the loop and whether there are any performance issues.


## 13: What is memory profiling?

**Memory Profiling** is the process of **analyzing how a Java application uses memory** during execution. It helps identify **memory leaks**, **excessive object creation**, and **high heap usage** to improve application performance and stability.

**Key Features:**

* Monitors **heap memory usage**.
* Tracks **object creation and allocation**.
* Detects **memory leaks**.
* Analyzes **Garbage Collection (GC)** behavior.
* Identifies objects that occupy the most memory.

**How It Works:**

1. A **memory profiler** is attached to the running Java application.
2. It collects information about **heap usage**, **object allocation**, and **GC activity**.
3. The profiler shows which objects are consuming memory and whether they are being released correctly.
4. Developers analyze the data to optimize memory usage and fix leaks.

**Common Metrics Monitored:**

* **Heap Memory Usage**
* **Object Allocation Rate**
* **Live Objects Count**
* **Garbage Collection Frequency**
* **Memory Leak Detection**

**Popular Memory Profiling Tools:**

* **JVisualVM**
* **Java Flight Recorder (JFR)**
* **JProfiler**
* **YourKit Java Profiler**
* **Eclipse Memory Analyzer (MAT)**

**When to Use:**

* Application is consuming **too much memory**.
* Investigating **OutOfMemoryError**.
* Finding **memory leaks**.
* Optimizing **heap usage** and **GC performance**.
* Performance tuning before **production deployment**.

**Simple Example:**

```java id="mp6r2k"
import java.util.ArrayList;
import java.util.List;

public class MemoryDemo {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(new byte[1024 * 1024]); // Allocate 1 MB
        }

        System.out.println("Objects created: " + list.size());
    }
}
```

A **memory profiler** can analyze this program and show how the `byte[]` objects are allocated in the **heap** and whether they are properly released by the **Garbage Collector**.


## 14: What is CPU profiling?

**CPU Profiling** is the process of **analyzing how a Java application uses CPU resources** during execution. It helps identify **slow methods**, **performance bottlenecks**, and **high CPU-consuming code** so that the application can be optimized.

**Key Features:**

* Monitors **CPU usage** of the application.
* Identifies **slow or frequently executed methods**.
* Detects **performance bottlenecks**.
* Tracks **method execution time** and **call frequency**.
* Helps improve **application speed** and **efficiency**.

**How It Works:**

1. A **CPU profiler** is attached to the running Java application.
2. The profiler records **method calls**, **execution time**, and **CPU consumption**.
3. It generates a report showing which methods consume the most CPU time.
4. Developers analyze the report and optimize the expensive code paths.

**Common Metrics Monitored:**

* **CPU Usage Percentage**
* **Method Execution Time**
* **Method Call Count**
* **Hotspots** (methods using the most CPU)
* **Thread CPU Utilization**

**Popular CPU Profiling Tools:**

* **JVisualVM**
* **Java Flight Recorder (JFR)**
* **JProfiler**
* **YourKit Java Profiler**
* **Async Profiler**

**When to Use:**

* Application is running **slowly**.
* Investigating **high CPU utilization**.
* Finding **performance bottlenecks**.
* Optimizing **algorithms** and **business logic**.
* Performance tuning before **production deployment**.

**Simple Example:**

```java id="cpu4x8"
public class CpuDemo {
    public static void main(String[] args) {
        long sum = 0;

        for (int i = 0; i < 10000000; i++) {
            sum += Math.sqrt(i);
        }

        System.out.println("Result: " + sum);
    }
}
```

A **CPU profiler** can analyze this program and show that the loop and the `Math.sqrt()` method consume most of the CPU time, helping developers optimize the code.



## 16: How do you find Security Vulnerabilities in Production? Which tools do you use?


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



## 17. What is JIT compilation?


**JIT (Just-In-Time) Compilation** is a feature of the **JVM** that **converts bytecode into native machine code at runtime** to make Java programs faster.

Java normally works like this:

```
.java → compiled → .class (bytecode) → JVM → Machine Code → Run
```

**How JIT Works**

1. Java code compiled → Bytecode
2. JVM runs bytecode
3. JIT finds frequently used code (hotspot)
4. JIT converts it to machine code
5. Execution becomes faster


**JIT vs Interpreter**

| Interpreter            | JIT                      |
| ---------------------- | ------------------------ |
| Line by line execution | Compiles to machine code |
| Slower                 | Faster                   |
| Starts fast            | Gets faster over time    |

```java
// JIT compilation example
public class JITExample {
    public static void main(String[] args) {
        // This loop will trigger JIT compilation
        for (int i = 0; i < 100000; i++) {
            calculateSum(i); // Method becomes "hot" and gets compiled
        }
    }
    
    private static int calculateSum(int n) {
        return n * (n + 1) / 2; // Simple calculation
    }
}

// JIT compilation flags
java -XX:+PrintCompilation \      # Print compilation events
     -XX:CompileThreshold=1000 \  # Compilation threshold
     JITExample
```


# ✅ 26. Java Monitoring and Logging

## 1: What is logging framework?

A **Logging Framework** is a library used to **record application events, errors, and execution details** into log files or monitoring systems. It helps developers **debug, monitor, and troubleshoot** applications.

**Key Features:**

* Records **application events** and **errors**.
* Supports different **log levels** such as **TRACE, DEBUG, INFO, WARN, and ERROR**.
* Can write logs to **console, files, or external systems**.
* Supports **log formatting** and **log rotation**.
* Integrates with monitoring tools like **ELK Stack** and **Splunk**.

**How it Works:**

1. The application generates log messages.
2. The **Logging Framework** captures the messages.
3. Based on the configured **log level**, it decides whether to record the message.
4. The logs are written to the configured destination (console, file, or centralized logging system).
5. Developers or monitoring tools analyze the logs for debugging and monitoring.

**Common Logging Frameworks in Java:**

* **SLF4J** – Logging API (facade).
* **Logback** – Default logging implementation in Spring Boot.
* **Log4j2** – High-performance logging framework.
* **java.util.logging (JUL)** – Built-in Java logging framework.

**When to Use:**

* To **debug application issues**.
* To monitor **application health and performance**.
* To track **errors, warnings, and user activities**.
* In **microservices** and **distributed systems** for centralized logging.

**Simple Spring Boot Logging Example:**

```java id="v3k8wp"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    private static final Logger logger =
        LoggerFactory.getLogger(UserService.class);

    public void createUser() {
        logger.info("User created successfully");
        logger.error("Error while creating user");
    }
}
```


## 2: What is Log4j?

**Log4j** is an **open-source Java Logging Framework** developed by the **Apache Software Foundation**. It is used to **generate, manage, and store application logs** for debugging, monitoring, and troubleshooting purposes.

**Key Features:**

* Supports different **Log Levels**: **TRACE, DEBUG, INFO, WARN, ERROR, FATAL**.
* Can write logs to **Console, File, Database, or Remote Servers**.
* Supports **Asynchronous Logging** for better performance.
* Provides flexible **log formatting** and **log rotation**.
* Integrates with **SLF4J** and many Java frameworks.

**How it Works:**

1. The application generates a log message.
2. **Log4j Logger** receives the message.
3. Based on the configured **log level**, it decides whether to log the message.
4. The message is passed to an **Appender** (Console, File, etc.).
5. A **Layout** formats the log before it is written to the destination.

**Core Components:**

* **Logger** – Creates log messages.
* **Appender** – Defines where logs are written.
* **Layout** – Defines how logs are formatted.

**When to Use:**

* To **debug and troubleshoot** Java applications.
* To monitor **application behavior and errors**.
* In **enterprise**, **microservices**, and **distributed systems**.
* When centralized logging and log management are required.

**Simple Log4j2 Example:**

```java id="l5q2vm"
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo {
    private static final Logger logger =
        LogManager.getLogger(Demo.class);

    public static void main(String[] args) {
        logger.info("Application started");
        logger.error("An error occurred");
    }
}
```

**Simple `log4j2.xml` Configuration:**

```xml id="y8r4pk"
<Configuration>
  <Appenders>
    <Console name="Console">
      <PatternLayout pattern="%d %p %c - %m%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <Root level="info">
      <AppenderRef ref="Console"/>
    </Root>
  </Loggers>
</Configuration>
```


## 3: What is SLF4J?

**SLF4J (Simple Logging Facade for Java)** is a **logging abstraction (facade)** that provides a **common API** for Java logging. Instead of directly depending on a specific logging framework like **Log4j** or **Logback**, applications use SLF4J, which can be connected to any logging implementation.

**Key Features:**

* Provides a **single logging API** for different logging frameworks.
* Allows easy switching between **Logback**, **Log4j2**, or **java.util.logging (JUL)** without changing application code.
* Supports **parameterized logging**, improving performance.
* Reduces dependency on a specific logging implementation.
* Used as the **default logging API** in Spring Boot.

**How it Works:**

1. The application writes log statements using the **SLF4J API**.
2. SLF4J forwards the log messages to a configured **logging implementation** (e.g., Logback or Log4j2).
3. The logging framework processes and writes the logs to the configured destination, such as the **console** or a **file**.

**SLF4J Architecture:**

```text id="f2m8rx"
Application
     |
   SLF4J API
     |
-----------------------
|          |          |
Logback   Log4j2    JUL
```

**When to Use:**

* In **Java** and **Spring Boot** applications.
* When you want the flexibility to change the logging framework later.
* In **enterprise** and **microservices** applications for standardized logging.
* To improve maintainability and reduce framework dependency.

**Simple SLF4J Example:**

```java id="k7w4ny"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger =
        LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        logger.info("Application started");
        logger.error("An error occurred");
    }
}
```


## 4: What is Logback?

**Logback** is an **open-source Java Logging Framework** and the **default logging implementation in Spring Boot**. It works together with **SLF4J** to provide fast, flexible, and efficient application logging.

**Key Features:**

* **Default Logging Framework** in Spring Boot.
* Works seamlessly with **SLF4J**.
* Supports different **Log Levels**: **TRACE, DEBUG, INFO, WARN, and ERROR**.
* Can write logs to **Console, Files, or External Systems**.
* Supports **Asynchronous Logging**, **Log Rotation**, and **Custom Log Patterns**.

**How it Works:**

1. The application writes log messages using the **SLF4J API**.
2. **SLF4J** forwards the messages to **Logback**.
3. Logback checks the configured **log level**.
4. It formats the message and sends it to the configured **Appender** (console, file, etc.).
5. The logs are stored or displayed for monitoring and debugging.

**Core Components:**

* **Logger** – Creates log messages.
* **Appender** – Defines where logs are written.
* **Encoder/Layout** – Defines how logs are formatted.

**When to Use:**

* In **Spring Boot** applications.
* For **application monitoring** and **debugging**.
* In **microservices** and **distributed systems** requiring centralized logging.
* When you need a **high-performance** and flexible logging solution.

**Simple Logback Example:**

```java id="q4n7vx"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger =
        LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        logger.info("Application started");
        logger.error("An error occurred");
    }
}
```

**Simple `logback-spring.xml` Configuration:**

```xml id="w8m2kp"
<configuration>
    <appender name="CONSOLE"
        class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d %-5level %logger - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
    </root>
</configuration>
```


## 5: What is structured logging?

**Structured Logging** is a logging approach where log data is stored in a **structured format (such as JSON)** instead of plain text. Each log entry contains **key-value pairs**, making it easier for machines and monitoring tools to **search, filter, and analyze** logs.

**Key Features:**

* Stores logs in a **structured format** (e.g., JSON).
* Uses **key-value pairs** instead of free-form text.
* Makes logs easier to **search, filter, and analyze**.
* Integrates well with centralized logging tools like **ELK Stack**, **Splunk**, and **Grafana Loki**.
* Improves debugging and monitoring in **microservices** and **distributed systems**.

**How it Works:**

1. The application generates a log event.
2. The logging framework (e.g., **Logback** or **Log4j2**) formats the log as a structured object.
3. The log is stored or sent to a centralized logging system.
4. Monitoring tools index the fields (e.g., `timestamp`, `userId`, `requestId`) for fast searching and analysis.

**Plain Log vs Structured Log:**

**Plain Log:**

```text
User 101 logged in successfully
```

**Structured Log (JSON):**

```json id="v6p3kj"
{
  "timestamp": "2026-06-13T10:30:00Z",
  "level": "INFO",
  "userId": 101,
  "action": "LOGIN",
  "message": "User logged in successfully"
}
```

**When to Use:**

* In **microservices** and **distributed systems**.
* For **centralized logging** and monitoring.
* When using tools like **ELK Stack**, **Splunk**, or **Grafana**.
* To simplify **debugging**, **troubleshooting**, and **log analytics**.

**Simple SLF4J Example:**

```java id="n4x8qw"
logger.info("UserId={} Action={} Status={}",
            101, "LOGIN", "SUCCESS");
```

## 6: What is centralized logging?

**Centralized Logging** is a technique where logs from all **Microservices** or applications are collected and stored in a **single central location**. Instead of checking logs on individual servers, developers can view and analyze all logs from one place.

**Key Features**

* Collects logs from multiple services into one system.
* Provides **centralized monitoring** and troubleshooting.
* Supports **searching**, **filtering**, and **visualizing** logs.
* Helps track requests across multiple microservices.
* Integrates with monitoring and alerting tools.

**How it Works**

1. Each microservice generates logs.
2. A **log collector** (such as **Filebeat** or **Fluentd**) gathers the logs.
3. Logs are sent to a centralized storage system like **Elasticsearch**.
4. A visualization tool like **Kibana** displays and searches the logs.

**Architecture Example**

```text id="1a5r9w"
User Service   Order Service   Payment Service
      |               |               |
      ---------------------------------
                    |
             Log Collector
          (Filebeat/Fluentd)
                    |
              Elasticsearch
                    |
                 Kibana
```

**When to Use**

* In **Microservices** architectures.
* In cloud-native or distributed applications.
* When multiple services generate large amounts of logs.
* For production systems requiring monitoring and auditing.

**Common Tools Used**

| **Tool**               | **Purpose**                       |
| ---------------------- | --------------------------------- |
| **Logback / Log4j2**   | Generate application logs         |
| **Filebeat / Fluentd** | Collect and forward logs          |
| **Elasticsearch**      | Store and index logs              |
| **Kibana**             | Search and visualize logs         |
| **ELK Stack**          | Elasticsearch + Logstash + Kibana |
| **EFK Stack**          | Elasticsearch + Fluentd + Kibana  |

**Spring Boot Logging Example**

```java id="2q8v7m"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public String getUsers() {
        logger.info("Fetching user details");
        return "Users List";
    }
}
```

**Spring Boot Logging Example**

**Service Class**

```java id="e8hzyt"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    public void createEmployee() {
        logger.info("Employee creation started");

        try {
            // Business logic
            logger.info("Employee created successfully");
        } catch (Exception e) {
            logger.error("Error while creating employee", e);
        }
    }
}
```

**Logback Configuration**

```xml id="jdr4c7"
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>logs/application.log</file>

        <encoder>
            <pattern>
                %d %-5level %logger - %msg%n
            </pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="FILE"/>
    </root>

</configuration>
```

**How It Works in Production**

1. **Spring Boot** application generates logs.
2. Logs are written using **SLF4J** and **Logback**.
3. A log collector such as **Logstash** or **Fluentd** reads the log files.
4. Logs are sent to **Elasticsearch**.
5. **Kibana** provides dashboards and search capabilities.


```yaml
# Docker Compose with centralized logging
version: '3'
services:
  app1:
    image: myapp:latest
    logging:
      driver: "fluentd"
      options:
        fluentd-address: localhost:24224
        tag: app1
        
  app2:
    image: myapp2:latest
    logging:
      driver: "fluentd"
      options:
        fluentd-address: localhost:24224
        tag: app2
        
  fluentd:
    image: fluent/fluentd:latest
    ports:
      - "24224:24224"
    volumes:
      - ./fluentd.conf:/fluentd/etc/fluent.conf
      
  elasticsearch:
    image: elasticsearch:7.9.0
    
  kibana:
    image: kibana:7.9.0
    ports:
      - "5601:5601"
```

```java
// Application configuration for centralized logging
@Configuration
public class LoggingConfig {    
    @Bean
    public Logger structuredLogger() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        
        // Add correlation ID to all logs
        context.putProperty("service.name", "user-service");
        context.putProperty("service.version", "1.0.0");
        
        return context.getLogger("STRUCTURED");
    }
}
```

## 7: What is Config Server?

A **Config Server** is a **centralized configuration management service** used in **Microservices**. It stores and manages configuration files (such as database URLs, API keys, and application properties) in one place and provides them to all microservices at runtime.

**Key Features**

* Provides **centralized configuration management**.
* Stores configuration in a **Git repository** or other external storage.
* Allows **multiple microservices** to share common configurations.
* Supports **environment-specific** configurations (Dev, QA, Prod).
* Configuration can be refreshed without rebuilding the application.

**How it Works**

1. Configuration files are stored in a **Git repository**.
2. The **Spring Cloud Config Server** reads these files.
3. When a microservice starts, it contacts the Config Server.
4. The Config Server returns the required configuration based on the application name and environment.
5. The microservice loads and uses the configuration.

**Architecture Example**

```text id="8mtvhi"
              Git Repository
          (application.yml)
                    |
            Config Server
                    |
      ---------------------------
      |            |            |
 User Service  Order Service  Payment Service
```

**When to Use**

* In **Microservices** architectures with multiple services.
* When the same configuration is shared across applications.
* When configurations need to be updated without changing application code.
* For centralized management of environment-specific settings.

**Common Technologies Used**

| **Component**                  | **Purpose**                                  |
| ------------------------------ | -------------------------------------------- |
| **Spring Cloud Config Server** | Centralized configuration service            |
| **Git**                        | Stores configuration files                   |
| **Spring Cloud Config Client** | Fetches configuration from the Config Server |
| **Spring Boot Actuator**       | Refreshes configuration dynamically          |

**Config Server Example**

**Enable Config Server**

```java id="j0k5vq"
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

**Config Server `application.yml`**

```yaml id="tk1l3m"
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/example/config-repo
```

**Client Configuration**

```yaml id="g8r4dw"
spring:
  application:
    name: user-service
  config:
    import: configserver:http://localhost:8888
```

## 8. What are Java deployment issues?

**Java Deployment Issues** are common problems that occur when deploying a Java application from development to testing or production environments. These issues can affect the application's **startup, performance, availability, or compatibility**.

**Key Features:**

* Usually related to **configuration**, **dependencies**, or **environment differences**.
* Can cause **application startup failures** or **runtime errors**.
* Often detected during **CI/CD deployments**.
* Can be minimized using **Docker**, **Kubernetes**, and automated deployment pipelines.

**Common Java Deployment Issues:**

| **Issue**                        | **Description**                                                       |
| -------------------------------- | --------------------------------------------------------------------- |
| **Dependency Conflicts**         | Different library versions cause runtime errors.                      |
| **Configuration Errors**         | Incorrect `application.properties` or environment variables.          |
| **Java Version Mismatch**        | Application is built with one JDK version but deployed on another.    |
| **Database Connection Issues**   | Wrong database URL, credentials, or unavailable database.             |
| **Port Conflicts**               | Another application is already using the required port.               |
| **Memory Issues**                | Insufficient JVM heap size causing `OutOfMemoryError`.                |
| **Deployment Failures**          | Incomplete builds, failed artifact uploads, or CI/CD pipeline issues. |
| **Logging or Permission Issues** | Application cannot write logs or access required files.               |

**How to Handle Them:**

1. Use **CI/CD pipelines** to automate build and deployment.
2. Use **Docker** to ensure consistent environments.
3. Manage database changes with **Flyway** or **Liquibase**.
4. Monitor logs using **SLF4J/Logback** and centralized logging tools.
5. Implement **health checks**, **rollback strategies**, and **zero downtime deployments**.

**When to Consider These Issues:**

* During **production deployments**.
* While migrating applications between environments.
* In **microservices** and **cloud-native** applications.
* When upgrading **Java versions** or dependencies.

**Simple Spring Boot Configuration Example:**

```properties id="d8m4rp"
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
```


## 9. What are debugging strategies?

**Debugging Strategies** are systematic approaches used to **identify, analyze, and fix bugs or issues** in an application. The goal is to quickly find the **root cause** and resolve the problem with minimal impact.

**Key Features:**

* Helps identify the **root cause** of issues.
* Uses **logs, breakpoints, and monitoring tools**.
* Reduces application downtime and troubleshooting time.
* Improves application **stability** and **reliability**.

**How it Works:**

1. **Reproduce the issue** consistently.
2. Check **application logs** and error messages.
3. Use a **debugger** and set breakpoints to inspect code execution.
4. Analyze **stack traces**, request flow, and variable values.
5. Verify external dependencies such as **database, APIs, and network connections**.
6. Fix the issue, test the solution, and deploy the update.

**Common Debugging Strategies:**

* **Log Analysis:** Check logs using **SLF4J/Logback** or centralized logging tools.
* **Breakpoint Debugging:** Pause execution and inspect variables using an IDE.
* **Stack Trace Analysis:** Identify the exact location of exceptions.
* **Monitoring and Metrics:** Use tools like **Prometheus** and **Grafana** to analyze application health.
* **Binary Search Debugging:** Disable or isolate parts of the code to narrow down the problem.
* **Root Cause Analysis (RCA):** Fix the underlying issue instead of only the symptom.

**When to Use:**

* When an application throws **exceptions or errors**.
* To investigate **performance issues** or memory leaks.
* During **production incident analysis**.
* When troubleshooting **deployment** or **integration** problems.

**Simple Java Debugging Example:**

```java id="d5q9vk"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger =
        LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        int value = 10;
        logger.debug("Value = {}", value);
    }
}
```




# ✅ 27. Java SQL

## 1. What is SQL?

**SQL (Structured Query Language)** is the standard language used to communicate with a **Relational Database**. It is used to **store**, **retrieve**, **update**, and **delete** data from database tables.

Databases such as MySQL, Oracle Database, PostgreSQL, and Microsoft SQL Server use SQL.


## 2. What is the Difference Between WHERE and HAVING?

Both filter rows — but at different stages.

- **WHERE** — filters rows **before** grouping (works on raw rows)
- **HAVING** — filters groups **after** `GROUP BY` (works on aggregated results)

```sql
-- WHERE: filter before grouping
SELECT dept_id, COUNT(*) FROM employee
WHERE salary > 50000
GROUP BY dept_id;

-- HAVING: filter after grouping
SELECT dept_id, COUNT(*) as total FROM employee
GROUP BY dept_id
HAVING COUNT(*) > 5;
```

Simple rule: if you're filtering on an aggregate function like `COUNT`, `SUM`, `AVG` — use `HAVING`. Otherwise use `WHERE`.


## 3. What is GROUP BY and ORDER BY?

- **GROUP BY** — groups rows with the same value into summary rows. Used with aggregate functions like `COUNT`, `SUM`, `AVG`.
- **ORDER BY** — sorts the result set by one or more columns (ASC by default, or DESC).

```sql
-- GROUP BY: count employees per department
SELECT dept_id, COUNT(*) as total
FROM employee
GROUP BY dept_id;

-- ORDER BY: sort by salary descending
SELECT name, salary FROM employee
ORDER BY salary DESC;

-- Combined
SELECT dept_id, AVG(salary) as avg_sal
FROM employee
GROUP BY dept_id
ORDER BY avg_sal DESC;
```

`GROUP BY` collapses rows. `ORDER BY` just sorts them.


## 4. What is Database Indexing and When to Use It?


A **Database Index** is a **data structure** that improves the **speed of data retrieval** from a table. It works like the **index of a book**, allowing the database to find rows quickly without scanning the entire table.

**Key Features**

* Improves **SELECT** query performance.
* Reduces the need for a **Full Table Scan**.
* Created on one or more **columns**.
* Uses additional **storage space**.
* Slightly slows down **INSERT, UPDATE, and DELETE** operations because the index must also be updated.

**How It Works**

1. Create an **index** on one or more columns.
2. The database stores the indexed column values in a **sorted data structure** (commonly a **B-Tree**).
3. When a query searches on the indexed column, the database uses the index to locate matching rows quickly.
4. The database retrieves only the required rows instead of scanning the entire table.

**Syntax**

**Create an Index**

```sql
CREATE INDEX idx_employee_name
ON Employee(Name);
```

**Create a Unique Index**

```sql
CREATE UNIQUE INDEX idx_employee_email
ON Employee(Email);
```

**Example**

Without an index:

```sql
SELECT *
FROM Employee
WHERE Email = 'john@example.com';
```

* The database may perform a **Full Table Scan**.

After creating an index:

```sql
CREATE INDEX idx_email
ON Employee(Email);
```

Now the same query:

```sql
SELECT *
FROM Employee
WHERE Email = 'john@example.com';
```

* The database uses the **index** to find the row much faster.

**When to Use**

* Columns frequently used in the **WHERE** clause.
* Columns used in **JOIN** conditions.
* Columns used in **ORDER BY**.
* Columns used in **GROUP BY**.
* Columns frequently searched for specific values.
* **Primary Key** and **Unique** columns (most databases create indexes automatically).

**When Not to Use**

* Small tables where a **Full Table Scan** is faster.
* Columns with frequent **INSERT, UPDATE, DELETE** operations.
* Columns with very few unique values (for example, **Gender**, **Status**, **Yes/No**).
* Columns that are rarely used in queries.

**Advantages**

* Faster **SELECT** queries.
* Improves **JOIN**, **ORDER BY**, and **GROUP BY** performance.
* Reduces query execution time.
* Helps optimize large tables.

**Disadvantages**

* Uses additional **disk space**.
* Slows down **INSERT**, **UPDATE**, and **DELETE** operations.
* Too many indexes can reduce overall database performance.

**Common Interview Follow-up Questions**

**1. Why does an index improve performance?**

Because the database searches the **index** instead of scanning every row in the table, reducing the amount of data it needs to examine.

**2. Why do indexes slow down INSERT, UPDATE, and DELETE?**

Whenever data changes, the database must also **update the index**, which adds extra work.

**3. Can a table have multiple indexes?**

**Yes.** A table can have **multiple indexes** on different columns or combinations of columns.

**4. What is the difference between a Primary Key and an Index?**

| **Primary Key**                         | **Index**                           |
| --------------------------------------- | ----------------------------------- |
| Ensures **uniqueness** and **NOT NULL** | Improves **query performance**      |
| Only **one** per table                  | Multiple indexes allowed            |
| Automatically creates an index          | Can be **unique** or **non-unique** |

**5. What are the common types of indexes?**

* **Clustered Index** – Stores the table data in the same order as the index (usually only one per table).
* **Non-Clustered Index** – Stores the index separately and points to the actual rows (multiple allowed).
* **Unique Index** – Ensures all indexed values are unique.
* **Composite Index** – Created on **multiple columns**.


## 5. What is the Difference Between Stored Procedure and Aggregate Function?

**Stored Procedure** is a **precompiled SQL program** stored in the database. It can accept **parameters**, contain **business logic (IF, loops)**, and return results.

**Aggregate Functions** perform calculations on multiple rows and return a **single value**.
Common examples: `COUNT`, `SUM`, `AVG`, `MAX`, `MIN`.

| | Stored Procedure | Function |
|---|---|---|
| Returns | Optional (0 or more values via OUT params) | Must return a single value |
| Can use in SELECT | No | Yes |
| Can have DML (INSERT/UPDATE) | Yes | Limited (depends on DB) |
| Called with | `CALL` / `EXEC` | Used in expressions |
| Transaction control | Yes | No |

```sql
-- Stored Procedure
CREATE PROCEDURE get_employee(IN emp_id INT)
BEGIN
  SELECT * FROM employee WHERE id = emp_id;
END;

CALL get_employee(1);

-- Function
CREATE FUNCTION get_salary(emp_id INT) RETURNS DECIMAL
BEGIN
  DECLARE sal DECIMAL;
  SELECT salary INTO sal FROM employee WHERE id = emp_id;
  RETURN sal;
END;

SELECT get_salary(1);  -- used inline
```

Use a **function** when you need a return value in a query. Use a **procedure** for business logic, batch operations, or multiple operations.


## 6. What is Normalization? Types (1NF, 2NF, 3NF)?

**Normalization** is the process of organizing data in a database to **reduce redundancy (duplicate data)** and **improve data integrity** by dividing data into related tables.

**Benefits**

* **Eliminates duplicate data**
* **Maintains data consistency**
* **Reduces update, insert, and delete anomalies**
* **Improves data integrity**

**1NF (First Normal Form)**

**Rule**

* Each column should contain **atomic (single) values**.
* No **repeating groups** or multiple values in a single column.

**Example (Not in 1NF)**

| StudentID | Name | Subjects  |
| --------- | ---- | --------- |
| 101       | John | Java, SQL |

**After 1NF**

| StudentID | Name | Subject |
| --------- | ---- | ------- |
| 101       | John | Java    |
| 101       | John | SQL     |

**2NF (Second Normal Form)**

**Rule**

* Must satisfy **1NF**.
* Remove **partial dependency**, where a non-key column depends on only part of a composite primary key.

**Example**

**Before 2NF**

| StudentID | CourseID | StudentName | CourseName |
| --------- | -------- | ----------- | ---------- |

Primary Key: **(StudentID, CourseID)**

Here:

* **StudentName** depends only on **StudentID**.
* **CourseName** depends only on **CourseID**.

**After 2NF**

**Student Table**

| StudentID | StudentName |
| --------- | ----------- |

**Course Table**

| CourseID | CourseName |
| -------- | ---------- |

**Enrollment Table**

| StudentID | CourseID |
| --------- | -------- |

**3NF (Third Normal Form)**

**Rule**

* Must satisfy **2NF**.
* Remove **transitive dependency**, where a non-key column depends on another non-key column.

**Example**

**Before 3NF**

| EmployeeID | EmployeeName | DepartmentID | DepartmentName |
| ---------- | ------------ | ------------ | -------------- |

Here:

* **DepartmentName** depends on **DepartmentID**, not directly on **EmployeeID**.

**After 3NF**

**Employee Table**

| EmployeeID | EmployeeName | DepartmentID |
| ---------- | ------------ | ------------ |

**Department Table**

| DepartmentID | DepartmentName |
| ------------ | -------------- |



## 7. What is the Between DELETE, TRUNCATE, and DROP?


These are SQL commands used to remove data or database objects, but they work differently.

| **Feature**      | **DELETE**                                | **TRUNCATE**                       | **DROP**                           |
| ---------------- | ----------------------------------------- | ---------------------------------- | ---------------------------------- |
| Removes          | **Selected rows**                         | **All rows**                       | **Entire table/object**            |
| **WHERE** clause | **Yes**                                   | **No**                             | **No**                             |
| Table structure  | **Remains**                               | **Remains**                        | **Deleted**                        |
| Rollback         | **Yes** (if transaction is not committed) | **Depends on the database**        | **Depends on the database**        |
| Speed            | **Slower**                                | **Faster**                         | **Fastest**                        |
| Type             | **DML (Data Manipulation Language)**      | **DDL (Data Definition Language)** | **DDL (Data Definition Language)** |

**1. DELETE**

**DELETE** removes **specific rows** or **all rows** from a table while keeping the **table structure** intact.

**How It Works**

* Deletes rows **one by one**.
* Can use a **WHERE** clause to delete selected records.
* Can be **rolled back** before the transaction is committed (in transactional databases).

**Syntax**

```sql
DELETE FROM Employee
WHERE Id = 101;
```

**Delete All Rows**

```sql
DELETE FROM Employee;
```

**When to Use**

* Delete **specific records**.
* When you need **rollback support**.
* When deleting based on a **condition**.

**2. TRUNCATE**

**TRUNCATE** removes **all rows** from a table but keeps the **table structure**.

**How It Works**

* Removes **all records at once**.
* Cannot use a **WHERE** clause.
* Resets the **identity/auto-increment** value in many databases.
* Much **faster** than **DELETE** because it deallocates data pages instead of deleting rows one by one.

**Syntax**

```sql
TRUNCATE TABLE Employee;
```

**When to Use**

* Remove **all records** quickly.
* Reset a table before loading fresh data.
* When individual row deletion is not required.

**3. DROP**

**DROP** permanently removes the **entire database object**, including its **data**, **structure**, indexes, constraints, and permissions.

**How It Works**

* Deletes the **table definition** from the database.
* The table no longer exists after execution.

**Syntax**

```sql
DROP TABLE Employee;
```

**When to Use**

* Remove a table that is **no longer needed**.
* Delete database objects permanently.

**Example**

Suppose the **Employee** table contains:

```text
Id   Name
101  John
102  Alice
103  Bob
```

**DELETE**

```sql
DELETE FROM Employee
WHERE Id = 101;
```

**Result**

```text
102  Alice
103  Bob
```

**TRUNCATE**

```sql
TRUNCATE TABLE Employee;
```

**Result**

```text
Table exists, but contains 0 rows.
```

**DROP**

```sql
DROP TABLE Employee;
```

**Result**

```text
Employee table no longer exists.
```

**When to Use Which?**

* Use **DELETE** when removing **specific rows**.
* Use **TRUNCATE** when removing **all rows** but keeping the **table**.
* Use **DROP** when removing the **entire table** permanently.

**Common Interview Follow-up Questions**

**1. Which command is the fastest?**

**DROP** is generally the **fastest**, followed by **TRUNCATE**, then **DELETE**.

**2. Which command supports the WHERE clause?**

Only **DELETE** supports the **WHERE** clause.

**3. Which command resets the identity (auto-increment) value?**

**TRUNCATE** resets the **identity/auto-increment** value in many databases.

**4. Which command removes the table structure?**

Only **DROP** removes the **table structure**.


```sql
DELETE FROM employee WHERE id = 5;     -- removes one row, can rollback

TRUNCATE TABLE employee;               -- removes all rows, fast, no rollback

DROP TABLE employee;                   -- removes table entirely
```

Use `DELETE` for selective removal. `TRUNCATE` to clear a table fast. `DROP` only when you want to remove the table completely.


## 8. What is a Subquery vs a JOIN?

Both retrieve related data — but differently.

**Subquery** — a query nested inside another query. Runs separately, result is used by the outer query.

```sql
-- Subquery: find employees earning more than average
SELECT name FROM employee
WHERE salary > (SELECT AVG(salary) FROM employee);
```

**JOIN** — combines rows from two tables in a single query execution.

```sql
-- JOIN: same result, often more efficient
SELECT e.name FROM employee e
INNER JOIN department d ON e.dept_id = d.id
WHERE d.name = 'Engineering';
```

**When to use which:**
- Use **JOIN** when you need columns from multiple tables — it's generally faster
- Use **subquery** when the inner result is a single value or a filtered set that's hard to express as a JOIN
- Correlated subqueries (referencing outer query) can be slow — prefer JOIN or CTEs


## 9. What is a View in SQL?

A **View** is a **virtual table** created from the result of a **SQL query**. It **does not store data** itself; instead, it displays data from one or more underlying tables whenever it is queried.

**Key Features**

* A **virtual table** based on a **SELECT** query.
* **Does not store data** (except **Materialized Views** in some databases).
* Can combine data from **multiple tables**.
* Improves **security** by exposing only required columns or rows.
* Simplifies **complex SQL queries**.

**How It Works**

1. Create a **View** using a **SELECT** statement.
2. The database stores only the **query definition**.
3. When the view is queried, the database executes the stored query.
4. The latest data from the underlying table(s) is returned.

**Syntax**

```sql
CREATE VIEW view_name AS
SELECT column1, column2
FROM table_name
WHERE condition;
```

**Example**

**Create a View**

```sql
CREATE VIEW EmployeeView AS
SELECT EmployeeId, EmployeeName, Department
FROM Employee
WHERE Department = 'IT';
```

**Query the View**

```sql
SELECT *
FROM EmployeeView;
```

**Output**

```text
EmployeeId  EmployeeName  Department
101         John          IT
102         Alice         IT
```

**When to Use**

* Simplify **complex JOIN** queries.
* Restrict access to sensitive columns (for example, **Salary**).
* Reuse frequently executed queries.
* Present customized data to different users.

**Advantages**

* Improves **security** by hiding sensitive data.
* Simplifies complex queries.
* Promotes **code reusability**.
* Always shows the **latest data** from the underlying tables.
* Makes SQL easier to maintain.

**Limitations**

* A standard **View** does **not store data**.
* Complex views may have slower performance.
* Some views are **not updatable**, especially those using **JOIN**, **GROUP BY**, or aggregate functions.



**Common Interview Follow-up Questions**

**1. Does a View store data?**

**No.** A standard **View** stores only the **SQL query**, not the actual data. Every time you query the view, it retrieves the latest data from the base table(s).

**2. Can we perform INSERT, UPDATE, or DELETE on a View?**

**Yes**, but only if the view is **updatable**. Views with **JOIN**, **GROUP BY**, **DISTINCT**, or aggregate functions are generally **not updatable**.

**3. What is the difference between a View and a Table?**

| **View**                        | **Table**                 |
| ------------------------------- | ------------------------- |
| **Virtual table**               | **Physical table**        |
| Usually **does not store data** | Stores actual data        |
| Created from a **SELECT** query | Stores records directly   |
| Always shows the latest data    | Data is physically stored |

**4. What is a Materialized View?**

A **Materialized View** **stores the query result physically**. It provides **faster read performance** but must be **refreshed** to reflect changes in the underlying tables.


## 11. What are different types of **JOINs**?

JOINs combine rows from two or more tables based on a related column.

- **INNER JOIN** — returns only matching rows from both tables
- **LEFT JOIN** — returns all rows from the left table + matching rows from right (nulls if no match)
- **RIGHT JOIN** — returns all rows from the right table + matching from left
- **FULL OUTER JOIN** — returns all rows from both tables (nulls where no match)

```sql
-- INNER JOIN
SELECT e.name, d.name FROM employee e
INNER JOIN department d ON e.dept_id = d.id;

-- LEFT JOIN (all employees, even without a department)
SELECT e.name, d.name FROM employee e
LEFT JOIN department d ON e.dept_id = d.id;

-- RIGHT JOIN (all employees, even without a department)
SELECT e.name, d.name FROM employee e
RIGHT JOIN department d ON e.dept_id = d.id;

-- FULL OUTER JOIN
SELECT e.name, d.name FROM employee e
FULL OUTER JOIN department d ON e.dept_id = d.id;
```

Think of it as a Venn diagram — INNER is the overlap, LEFT keeps the left circle, FULL keeps both circles.


## 12. What is a **Primary Key** and **Foreign Key** ?

A **Primary Key** is a column (or combination of columns) that **uniquely identifies** each record in a table.

A **Foreign Key** is a column that **references the Primary Key** of another table to establish a relationship.

| **Feature**      | **Primary Key**                             | **Foreign Key**                                      |
| ---------------- | ------------------------------------------- | ---------------------------------------------------- |
| Purpose          | **Uniquely identifies** each row            | **Creates a relationship** between tables            |
| Duplicate Values | **Not Allowed**                             | **Allowed**                                          |
| NULL Values      | **Not Allowed**                             | **Allowed** (unless restricted)                      |
| Number per Table | Only **one Primary Key** (can be composite) | Multiple **Foreign Keys** allowed                    |
| References       | Own table                                   | **Primary Key** (or **Unique Key**) of another table |


## 12. What is the Difference Between UNION and UNION ALL?

Both combine results of two SELECT queries — but handle duplicates differently.

- **UNION** — combines results and **removes duplicates** (slower, does a distinct sort)
- **UNION ALL** — combines results and **keeps all rows including duplicates** (faster)

```sql
-- UNION: removes duplicates
SELECT name FROM employee_india
UNION
SELECT name FROM employee_us;

-- UNION ALL: keeps duplicates
SELECT name FROM employee_india
UNION ALL
SELECT name FROM employee_us;
```

**Rules for both:**
- Same number of columns in both SELECT statements
- Columns must have compatible data types

Use `UNION ALL` when you know there are no duplicates or you want all rows — it's faster because it skips the deduplication step.



## 12. What is SQL injection and how to prevent it?

**SQL Injection** is a **security vulnerability** where an attacker injects malicious SQL code into application queries, to **manipulate or access the database illegally**.

It can be prevented by using **Prepared Statements (Parameterized Queries)**, **input validation**, **ORM frameworks (like JPA/Hibernate)**, **stored procedures**, and **proper access control**.


```java
// Vulnerable code - SQL injection possible
String userId = request.getParameter("id");
String sql = "SELECT * FROM users WHERE id = " + userId;
// If userId = "1 OR 1=1", returns all users!

// Safe code - using PreparedStatement
String sql = "SELECT * FROM users WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, userId); // Safe parameter binding
ResultSet rs = pstmt.executeQuery();
```


## 13. What is a Cursor in SQL and when should it be used ?

A **Cursor** is used to **process database rows one by one** instead of all at once.
It is useful when we need **row-by-row processing**, but it should be used carefully because it can be **slower than set-based queries**

**Why use it?**

* Prevents **OutOfMemoryError**
* Good for very large datasets
* Reduces heap usage


```java
DECLARE @name VARCHAR(50)

-- 1. Declare Cursor
DECLARE emp_cursor CURSOR FOR
SELECT name FROM employees

-- 2. Open Cursor
OPEN emp_cursor

-- 3. Fetch first row
FETCH NEXT FROM emp_cursor INTO @name

-- Loop through all rows
WHILE @@FETCH_STATUS = 0
BEGIN
    PRINT @name

    -- Fetch next row
    FETCH NEXT FROM emp_cursor INTO @name
END

-- 5. Close Cursor
CLOSE emp_cursor

-- 6. Deallocate Cursor
DEALLOCATE emp_cursor
```

```java
@Transactional
public void processProducts() {
    try (Stream<Product> stream = repo.findAllByStream()) {
        stream.forEach(product -> {
            // process record
        });
    }
}
```

## 14. What is Batch Processing?

**Batch Processing** is a technique where a **large number of records** are **processed together as a single batch** instead of processing each record individually. It is commonly used for **scheduled**, **repetitive**, and **high-volume** data processing.

**Key Features**

* **Processes Multiple Records** together
* **Scheduled Execution**
* **High Performance**
* **Automatic Retry** and **Restart**
* **Transaction Management**
* **Scalable** for large datasets

**How It Works**

1. A **Batch Job** is triggered manually or by a scheduler.
2. Data is **read** from a source (Database, CSV, File, API).
3. Data is **processed** (validate, transform, calculate).
4. Processed data is **written** to the destination.
5. The job completes and generates a **status/report**.

**Architecture Flow**

```text
Scheduler
    │
    ▼
Batch Job
    │
    ▼
Item Reader
    │
    ▼
Item Processor
    │
    ▼
Item Writer
    │
    ▼
Database / File
```

**Main Components (Spring Batch)**

| **Component**     | **Purpose**                           |
| ----------------- | ------------------------------------- |
| **Job**           | Represents the complete batch process |
| **Step**          | A single phase within the job         |
| **ItemReader**    | Reads data from the source            |
| **ItemProcessor** | Processes or transforms the data      |
| **ItemWriter**    | Writes data to the destination        |

**When to Use**

* **Payroll Processing**
* **Bank Transactions**
* **Invoice Generation**
* **Report Generation**
* **Data Migration**
* **ETL (Extract, Transform, Load)**

**Code Example**

```java
@Component
public class EmployeeProcessor
        implements ItemProcessor<Employee, Employee> {

    @Override
    public Employee process(Employee employee) {
        employee.setSalary(employee.getSalary() * 1.10);
        return employee;
    }
}
```

**Advantages**

* **Efficient** for large datasets
* **Improves Performance**
* **Reduces Resource Usage**
* **Supports Restart** after failure
* **Easy to Automate**

**Disadvantages**

* **Not Suitable** for real-time processing
* Large jobs may take **more time** to complete
* Error handling can be **complex**


**Common Interview Follow-up**

**Q: What is the difference between Batch Processing and Stream Processing?**

| **Batch Processing**                                   | **Stream Processing**                                                    |
| ------------------------------------------------------ | ------------------------------------------------------------------------ |
| Processes **data in batches**.                         | Processes **data continuously** as it arrives.                           |
| Used for **scheduled jobs**.                           | Used for **real-time applications**.                                     |
| Higher **throughput**.                                 | Lower **latency**.                                                       |
| Example: **Payroll**, **ETL**, **Invoice Generation**. | Example: **Fraud Detection**, **Live Notifications**, **Stock Trading**. |


## 15. What is sharding in databases?

**Sharding** is a **database scaling technique** where a large database is **split into smaller, independent databases (called shards)**. Each shard stores a **portion of the data**, allowing the system to handle more users and data efficiently.

**Key Features**

* **Horizontal Scaling**
* **Distributes Data** across multiple databases
* **Improves Performance**
* **Reduces Database Load**
* **Supports High Availability**
* Each **Shard** is an independent database

**How It Works**

1. The application receives a request.
2. A **Sharding Key** (such as **User ID** or **Customer ID**) is used.
3. The application determines which **Shard** contains the data.
4. The request is sent only to that specific **Shard**.
5. The shard processes the request and returns the result.

**Architecture Flow**

```text
           Client
              │
              ▼
        Application
              │
      Sharding Logic
              │
    ┌─────────┼─────────┐
    ▼         ▼         ▼
 Shard 1   Shard 2   Shard 3
(User 1-1000) (1001-2000) (2001-3000)
```

**Types of Sharding**

| **Type**               | **Description**                                           |
| ---------------------- | --------------------------------------------------------- |
| **Range Sharding**     | Data is divided by a range (e.g., IDs 1–1000, 1001–2000). |
| **Hash Sharding**      | A **hash function** decides which shard stores the data.  |
| **Directory Sharding** | A lookup table maps data to the correct shard.            |
| **Geo Sharding**       | Data is split based on **region** or **location**.        |

**When to Use**

* **Large Databases**
* **High Traffic Applications**
* **Microservices**
* **E-commerce Platforms**
* **Banking Systems**
* **Social Media Applications**

**Code Example**

```java
int shardId = userId % 3;

switch (shardId) {
    case 0:
        // Query Shard 1
        break;
    case 1:
        // Query Shard 2
        break;
    case 2:
        // Query Shard 3
        break;
}
```

**Advantages**

* **Better Performance**
* **Horizontal Scalability**
* **Lower Load** on each database
* **Faster Queries**
* **Supports Large Datasets**

**Disadvantages**

* **More Complex Architecture**
* **Cross-Shard Queries** are difficult
* **Data Rebalancing** is required when adding new shards
* **Joins Across Shards** are expensive



**Common Interview Follow-up**

**Q: What is the difference between Sharding and Partitioning?**

| **Sharding**                                              | **Partitioning**                                                |
| --------------------------------------------------------- | --------------------------------------------------------------- |
| **Data is distributed across multiple database servers.** | **Data is divided within the same database server.**            |
| Supports **Horizontal Scaling**.                          | Supports **Logical Organization** and performance optimization. |
| Each shard has its own **database instance**.             | All partitions belong to the **same database**.                 |
| Used for **very large, distributed systems**.             | Used for **managing large tables** within one database.         |


## 15. What is stored procedure in sql?


A **Stored Procedure** is a **precompiled collection of SQL statements** stored in the database that performs a **specific task**. It can accept **input parameters**, execute **multiple SQL operations**, and optionally return **output values**.

**Key Features**

* **Precompiled** for better performance.
* Stored and managed inside the **database**.
* Supports **input** and **output parameters**.
* Can contain **SELECT, INSERT, UPDATE, DELETE**, loops, conditions, and transactions.
* Improves **code reusability**, **security**, and **maintainability**.

**How It Works**

1. Create the stored procedure once in the database.
2. Pass required **input parameters** while calling it.
3. The database executes the stored SQL statements.
4. Returns **result sets**, **output parameters**, or a **status**.

**Syntax**

```sql
CREATE PROCEDURE procedure_name
    @parameter datatype
AS
BEGIN
    -- SQL Statements
END;
```

**Example**

**Create Procedure**

```sql
CREATE PROCEDURE GetEmployeeById
    @EmpId INT
AS
BEGIN
    SELECT *
    FROM Employee
    WHERE Id = @EmpId;
END;
```

**Execute Procedure**

```sql
EXEC GetEmployeeById @EmpId = 101;
```

**Output**

```text
Id   Name     Department
101  John     IT
```

**When to Use**

* When the **same SQL logic** is executed repeatedly.
* To improve **performance** by using precompiled execution plans.
* To **encapsulate business logic** inside the database.
* To restrict direct table access and improve **security**.
* To perform **complex database operations** involving multiple SQL statements.

**Advantages**

* **Faster execution** due to precompilation.
* **Reusable** and reduces duplicate SQL code.
* Better **security** by granting execute permission instead of table access.
* Easier **maintenance** since logic is stored in one place.
* Reduces **network traffic** because a single procedure call can execute multiple statements.

**Limitations**

* Can become difficult to maintain if business logic grows too large.
* Database-specific syntax may reduce portability between different databases.
* Debugging complex stored procedures can be challenging.

**Common Interview Follow-up Questions**

**1. What is the difference between a Stored Procedure and a Function?**

| **Stored Procedure**                   | **Function**                                      |
| -------------------------------------- | ------------------------------------------------- |
| May or may not return a value          | Must return a value                               |
| Can perform **INSERT, UPDATE, DELETE** | Mainly used to compute and return a value         |
| Called using **EXEC**                  | Can be used inside **SELECT** statements          |
| Can return multiple result sets        | Returns a single value or table (depending on DB) |

**2. Why are Stored Procedures faster?**

Because they are **precompiled**, the database can **reuse the execution plan**, reducing query parsing and optimization overhead.

**3. Can a Stored Procedure return multiple values?**

Yes. It can return:

* **Result sets**
* **Output parameters**
* **Return status code**



# ✅ 28. Java CI/CD and DevOp

## 1: What is CI/CD (Continuous Integration and Continuous Deployment)?

**CI/CD** is a **software development practice** that automates the process of **building, testing, and deploying** applications. It helps teams deliver code changes **quickly, reliably, and with fewer errors**.

* **CI (Continuous Integration):** Developers frequently **merge code changes** into a shared repository, and every commit automatically triggers **builds and tests**.
* **CD (Continuous Deployment/Delivery):** After successful testing, the application is **automatically delivered or deployed** to the target environment.

**Key Features:**

* **Automatic Build and Testing** after every code commit.
* **Fast Feedback** to detect bugs early.
* **Automated Deployment** to test, staging, or production environments.
* **Consistent and Reliable Releases** with minimal manual work.
* Integrates with tools like **Git, Jenkins, GitHub Actions, Docker, and Kubernetes**.

**How it Works:**

1. Developer **pushes code** to a Git repository.
2. **CI tool** (e.g., Jenkins) detects the change.
3. The application is **built and tested** automatically.
4. If all tests pass, the **CD pipeline** deploys the application.
5. The application becomes available in **staging or production**.

**When to Use:**

* In **Agile** and **DevOps** environments.
* When multiple developers work on the same project.
* For applications requiring **frequent releases**.
* To reduce **manual deployment errors** and improve release speed.

**Simple CI/CD Pipeline Example (`Jenkinsfile`):**

```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building application...'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
            }
        }
    }
}
```


## 3: What is Jenkins?

**Jenkins** is an **open-source Automation Server** used to automate the **Build, Test, and Deployment** process of applications. It is widely used for implementing **CI/CD (Continuous Integration and Continuous Deployment)** pipelines.

**Key Features:**

* **Automates** build, test, and deployment tasks.
* Supports **CI/CD Pipelines**.
* Large collection of **Plugins** for Git, Maven, Docker, Kubernetes, etc.
* Can run jobs on **multiple agents (distributed builds)**.
* Supports **Pipeline as Code** using a `Jenkinsfile`.

**How it Works:**

1. Developer pushes code to **Git**.
2. **Jenkins** detects the change (via webhook or polling).
3. Jenkins pulls the latest code.
4. It runs the **build**, **unit tests**, and **quality checks**.
5. If everything passes, Jenkins **deploys** the application automatically.

**When to Use:**

* When you want to **automate software delivery**.
* For **continuous integration** after every code commit.
* For **continuous deployment** to test or production environments.
* In projects requiring frequent and reliable releases.

**Simple Pipeline Example (`Jenkinsfile`):**
```groovy
// Jenkinsfile example
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh './mvnw clean compile'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Package') {
            steps {
                sh './mvnw package'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
        stage('Deploy') {
            when { branch 'main' }
            steps {
                sh 'docker build -t myapp .'
                sh 'kubectl apply -f k8s/'
            }
        }
    }
}
```


## 4: What is Git?

**Git** is a **Distributed Version Control System (DVCS)** used to **track changes in source code** and help multiple developers work on the same project without overwriting each other's work.

**Key Features:**

* **Version Control** to track code changes.
* **Distributed System** where every developer has a complete copy of the repository.
* Supports **Branching and Merging** for parallel development.
* Enables **Collaboration** among multiple developers.
* Provides **History and Rollback** to restore previous versions.

**How it Works:**

1. Create or clone a **Git repository**.
2. Make changes to the code.
3. Add changes to the staging area using `git add`.
4. Save the changes with `git commit`.
5. Push the commits to a remote repository (e.g., GitHub) using `git push`.
6. Other developers can pull the latest changes using `git pull`.

**When to Use:**

* For **source code management** in software projects.
* When multiple developers collaborate on the same codebase.
* To maintain **code history** and easily revert changes.
* For implementing **CI/CD pipelines** and automated deployments.

**Common Git Commands:**

```bash
# Basic Git commands
git init                          # Initialize repository
git add .                         # Stage changes
git commit -m "Add new feature"   # Commit changes
git branch feature-branch         # Create branch
git checkout feature-branch       # Switch branch
git merge feature-branch          # Merge branch
git push origin main              # Push to remote
git pull origin main              # Pull from remote
```


## 5: What is version control?

**Version Control** is a system that **tracks and manages changes** made to source code or files over time. It allows developers to **save different versions**, **collaborate safely**, and **restore previous versions** if needed.

**Key Features:**

* **Tracks Changes** made to files and code.
* Maintains a complete **history of versions**.
* Supports **multiple developers** working on the same project.
* Allows **rollback** to a previous stable version.
* Supports **branching and merging** for parallel development.

**How it Works:**

1. Developers make changes to the code.
2. The **Version Control System (VCS)** records each change as a new version.
3. Changes are saved with a **commit**.
4. Team members can **merge** their work into a shared codebase.
5. If an issue occurs, the project can be **reverted** to an earlier version.

**When to Use:**

* In **software development** projects.
* When multiple developers collaborate on the same codebase.
* To maintain a **history of code changes**.
* To safely experiment with new features using **branches**.


* System for tracking and managing changes to files over time
* **History**: Complete history of all changes and versions
* **Collaboration**: Multiple developers can work on same project
* **Branching**: Parallel development streams
* **Backup**: Distributed copies serve as backups
* **Rollback**: Ability to revert to previous versions
* **Types**: Centralized (SVN) vs Distributed (Git)

```bash
# Version control workflow
git status                    # Check current state
git log --oneline            # View commit history
git diff HEAD~1              # Compare with previous version
git checkout HEAD~2 -- file.java  # Restore file from 2 commits ago
git tag v1.0.0               # Tag release version
git revert abc123            # Revert specific commit
```


## 6: What is infrastructure as code?

**Infrastructure as Code (IaC)** is the practice of **managing and provisioning infrastructure using code instead of manual setup**.

It allows infrastructure to be **version-controlled, automated, and reproducible** across environments using tools like Terraform or CloudFormation.

* Managing and provisioning infrastructure through code rather than manual processes
* **Declarative**: Define desired state, tools ensure it's achieved
* **Version Control**: Infrastructure changes tracked like application code
* **Reproducible**: Consistent environments across dev, test, production
* **Automation**: Automated provisioning and configuration
* **Tools**: Terraform, CloudFormation, Ansible, Kubernetes manifests

```yaml
# Kubernetes deployment (IaC)
apiVersion: apps/v1
kind: Deployment
metadata:
  name: java-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: java-app
  template:
    spec:
      containers:
      - name: app
        image: myapp:1.0.0
        ports:
        - containerPort: 8080
        env:
        - name: DATABASE_URL
          value: "jdbc:postgresql://db:5432/mydb"
```

```java
# Terraform example
resource "aws_instance" "web" {
  ami           = "ami-0c55b159cbfafe1d0"
  instance_type = "t2.micro"
  
  tags = {
    Name = "JavaApp"
  }
}
```


## 7: What is deployment strategies?

**Deployment Strategies** are different methods of **releasing a new version of an application** to users while minimizing **downtime, risk, and failures**.

**Key Features:**

* Reduces **deployment risk**.
* Minimizes **application downtime**.
* Enables **easy rollback** if issues occur.
* Improves **availability and user experience**.

**Common Deployment Strategies:**

| **Strategy**              | **How it Works**                                                                                                                   | **When to Use**                                              |
| ------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------ |
| **Recreate**              | Stops the old version and deploys the new version.                                                                                 | Small applications where short downtime is acceptable.       |
| **Rolling Deployment**    | Gradually replaces old instances with new ones.                                                                                    | Most common choice for microservices and cloud applications. |
| **Blue-Green Deployment** | Maintains two identical environments (**Blue** = current, **Green** = new). Traffic switches to the new environment after testing. | When near-zero downtime and quick rollback are required.     |
| **Canary Deployment**     | Releases the new version to a **small percentage of users** first. If successful, it is rolled out to everyone.                    | High-risk or large-scale production deployments.             |
| **A/B Testing**           | Different user groups receive different versions to compare behavior or performance.                                               | Feature testing and product experimentation.                 |

**How it Works:**

1. Build and test the new application version.
2. Choose a suitable **deployment strategy**.
3. Deploy the new version based on the selected approach.
4. Monitor application health and user traffic.
5. If issues occur, **rollback** to the previous stable version.

**When to Use:**

* During **CI/CD pipelines** for automated releases.
* For **microservices** and **cloud-native** applications.
* When high availability and minimal downtime are required.
* To safely release new features in production.

* Different approaches for releasing applications to production

* **Rolling Deployment**: Gradually replace old instances with new ones
* **Blue-Green**: Switch between two identical environments
* **Canary**: Deploy to small subset of users first
* **A/B Testing**: Compare different versions with user groups
* **Recreate**: Stop old version, start new version (downtime)
* **Shadow**: Route copy of traffic to new version for testing

```yaml
# Rolling deployment strategy
apiVersion: apps/v1
kind: Deployment
spec:
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxUnavailable: 1
      maxSurge: 1
  replicas: 5
  template:
    spec:
      containers:
      - name: app
        image: myapp:v2.0.0
```

## 10: What is containerization?

**Containerization** is a technology that packages an application along with its **code, libraries, dependencies, and configuration files** into a lightweight, isolated unit called a **Container**. This ensures the application runs **consistently across different environments**.

**Key Features:**

* **Portable** – Runs the same on development, testing, and production environments.
* **Lightweight** – Shares the host operating system kernel, so it uses fewer resources than virtual machines.
* **Isolated** – Each container has its own dependencies and does not interfere with others.
* **Fast Startup** – Containers start in seconds.
* Works well with **Docker** and orchestration tools like **Kubernetes**.

**How it Works:**

1. Create an application and define its dependencies.
2. Write a **Dockerfile** to describe how to build the container.
3. Build a **Container Image** from the Dockerfile.
4. Run the image as a **Container**.
5. The same container can be deployed on any system with a container runtime (e.g., Docker).

**When to Use:**

* For **Microservices Architecture**.
* To ensure **environment consistency** across development and production.
* In **CI/CD pipelines** for automated deployment.
* For **cloud-native** and scalable applications.

**Simple Dockerfile Example:**

```dockerfile id="n7k2xq"
FROM openjdk:21-jdk-slim
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

**Build and Run Commands:**

```bash id="h4v9mp"
# Build the image
docker build -t my-app .

# Run the container
docker run -p 8080:8080 my-app
```


## 11: What is Docker?

**Docker** is an **open-source Containerization Platform** that allows developers to **build, package, ship, and run applications** inside lightweight, isolated units called **Containers**. It ensures the application runs the same way across all environments.

**Key Features:**

* **Containerization** of applications and dependencies.
* **Portable** – Runs consistently on any system with Docker installed.
* **Lightweight** – Shares the host OS kernel, using fewer resources than virtual machines.
* **Fast Deployment** and startup time.
* Integrates easily with **CI/CD**, **Kubernetes**, and cloud platforms.

**How it Works:**

1. Create a **Dockerfile** that defines the application environment.
2. Build a **Docker Image** from the Dockerfile.
3. Run the image to create a **Docker Container**.
4. The container executes the application in an isolated environment.
5. The same image can be deployed anywhere without changing the code.

**When to Use:**

* For **Microservices Architecture**.
* To ensure **consistent environments** across development, testing, and production.
* In **CI/CD pipelines** for automated builds and deployments.
* For **cloud-native** and scalable applications.

**Simple Dockerfile Example:**

```dockerfile id="t5m8kq"
FROM openjdk:21-jdk-slim
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

**Common Docker Commands:**

```bash
# Build and run Java application
docker build -t myapp:latest .
docker run -p 8080:8080 myapp:latest

# Docker Compose for multi-service setup
version: '3'
services:
  app:
    build: .
    ports: ["8080:8080"]
  db:
    image: mysql:8.0
```

## 12. What is Kubernetes?

**Kubernetes (K8s)** is an **open-source Container Orchestration Platform** used to **automate the deployment, scaling, management, and monitoring of containerized applications**. It helps manage large numbers of Docker containers across multiple servers.

**Key Features:**

* **Automatic Deployment** and management of containers.
* **Auto Scaling** based on application load.
* **Self-Healing** by restarting or replacing failed containers.
* **Load Balancing** to distribute traffic across containers.
* Supports **Rolling Updates** and **Rollback** without downtime.

**How it Works:**

1. Developers package the application into **Docker Containers**.
2. Kubernetes groups containers into **Pods**.
3. The **Master (Control Plane)** schedules and manages Pods across **Worker Nodes**.
4. Kubernetes continuously monitors the application and automatically recovers from failures.
5. It can scale the number of Pods up or down based on demand.

**When to Use:**

* For **Microservices Architecture**.
* When managing **multiple containers** across servers.
* For **high availability** and **auto-scaling** requirements.
* In **cloud-native** applications and **CI/CD pipelines**.

**Simple Kubernetes Deployment Example:**

```yaml id="k8f3wd"
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: my-app
  template:
    metadata:
      labels:
        app: my-app
    spec:
      containers:
      - name: my-app
        image: my-app:latest
```


## 13. What is cloud computing?

**Cloud Computing** is the delivery of **computing services** such as **servers, storage, databases, networking, and software** over the **Internet (Cloud)** instead of using local machines or on-premise infrastructure.

**Key Features:**

* **On-Demand Access** to computing resources.
* **Scalability** to increase or decrease resources as needed.
* **Pay-as-You-Go** pricing model.
* **High Availability** and reliability.
* Easy integration with **CI/CD, Docker, and Kubernetes**.

**How it Works:**

1. A **Cloud Provider** (e.g., AWS, Azure, GCP) offers infrastructure and services.
2. Users request resources such as virtual machines, databases, or storage.
3. The cloud platform automatically provisions and manages these resources.
4. Applications are deployed and accessed over the Internet.
5. Resources can be scaled up or down based on demand.

**When to Use:**

* For **web and enterprise applications**.
* When you need **high scalability** and **high availability**.
* To reduce the cost of maintaining physical servers.
* For **microservices**, **containerized applications**, and **CI/CD pipelines**.

**Simple Example (Deploying a Docker Container on the Cloud):**

```bash id="c7m4kx"
# Build Docker image
docker build -t my-app .

# Push image to a cloud container registry
docker push my-app:latest

# Deploy the image to a cloud platform
kubectl apply -f deployment.yaml
```

**Common Cloud Service Models:**

| **Model**                              | **Description**                                       | **Example**                          |
| -------------------------------------- | ----------------------------------------------------- | ------------------------------------ |
| **IaaS (Infrastructure as a Service)** | Provides virtual servers, storage, and networking.    | AWS EC2, Azure VM                    |
| **PaaS (Platform as a Service)**       | Provides a platform to build and deploy applications. | Google App Engine, Azure App Service |
| **SaaS (Software as a Service)**       | Provides ready-to-use software over the Internet.     | Gmail, Microsoft 365                 |


## 14. What is distributed system?

A **Distributed System** is a collection of **multiple independent computers (nodes)** that work together and communicate over a network to function as a **single system**. The workload is shared across multiple machines, improving **scalability, availability, and fault tolerance**.

**Key Features:**

* **Multiple Nodes** work together as one system.
* **Scalability** by adding more servers when demand increases.
* **Fault Tolerance** because failure of one node does not stop the entire system.
* **High Availability** through redundancy and replication.
* **Resource Sharing** across different machines.

**How it Works:**

1. A client sends a request to the system.
2. A **Load Balancer** distributes the request to one of the available servers.
3. Multiple servers (nodes) process requests and may communicate with each other.
4. Data can be stored across multiple databases or replicated for reliability.
5. If one server fails, another server continues handling requests.

**When to Use:**

* For **large-scale web applications** and **microservices**.
* When high **scalability** and **availability** are required.
* For **cloud-native** applications running on multiple servers.
* In systems handling **high traffic** and **large amounts of data**.

**Simple Example:**

```text
Client
   |
Load Balancer
   |
-------------------------
|           |           |
Server 1   Server 2   Server 3
```

In this example, the **Load Balancer** distributes incoming requests among multiple servers, allowing the system to handle more users and continue running even if one server fails.


## 15. What is load balancing?


**Load Balancing** is the process of **distributing incoming client requests across multiple servers** so that no single server becomes overloaded. It improves **performance, scalability, and high availability** of an application.

**Key Features:**

* **Distributes Traffic** evenly across multiple servers.
* Improves **High Availability** by preventing a single point of failure.
* Supports **Scalability** by adding or removing servers easily.
* Increases **Performance** and reduces response time.
* Provides **Fault Tolerance** by redirecting traffic if a server fails.

**How it Works:**

1. A client sends a request to the application.
2. The **Load Balancer** receives the request.
3. It selects a healthy server using an algorithm such as **Round Robin**, **Least Connections**, or **IP Hash**.
4. The selected server processes the request and returns the response.
5. If a server becomes unavailable, the load balancer automatically redirects traffic to other healthy servers.

**When to Use:**

* For **high-traffic web applications**.
* In **Distributed Systems** and **Microservices Architectures**.
* When **high availability** and **fault tolerance** are required.
* For applications running on multiple servers or containers.

**Simple Example:**

```text id="l4d8wn"
          Client Requests
                 |
          Load Balancer
          /     |      \
    Server1  Server2  Server3
```

In this setup, the **Load Balancer** distributes requests among **Server1**, **Server2**, and **Server3**, ensuring that the workload is shared evenly.


**Load Balancing Algorithms:**
- **Round Robin:** Requests distributed sequentially
- **Least Connections:** Route to server with fewest active connections
- **Weighted Round Robin:** Assign weights based on server capacity
- **IP Hash:** Route based on client IP hash
- **Health Check:** Only route to healthy servers

**Types:**
- **Layer 4 (Transport):** Routes based on IP and port
- **Layer 7 (Application):** Routes based on HTTP content
- **Hardware Load Balancers:** Dedicated physical devices
- **Software Load Balancers:** Nginx, HAProxy, AWS ALB

```java
# Nginx load balancer configuration
upstream backend {
    server backend1.example.com weight=3;
    server backend2.example.com weight=2;
    server backend3.example.com weight=1;
}

server {
    listen 80;
    location / {
        proxy_pass http://backend;
    }
}
```

## **16. How do you handle rollback strategies?**

A **Rollback Strategy** is a process of **reverting an application to the last stable version** if a new deployment causes failures or unexpected issues. The goal is to **minimize downtime and restore service quickly**.

**Key Features:**

* **Quick Recovery** from failed deployments.
* **Minimal Downtime** for users.
* **Risk Reduction** during releases.
* Works well with **CI/CD pipelines** and **automated deployments**.
* Supports **versioned artifacts** and **deployment history**.

**How it Works:**

1. Deploy the new application version.
2. Continuously monitor **health checks**, logs, and metrics.
3. If errors or failures are detected, stop routing traffic to the new version.
4. **Rollback** to the previously stable version.
5. Investigate and fix the issue before redeploying.

**Common Rollback Approaches:**

* **Blue-Green Deployment:** Switch traffic back from the **Green** environment to the stable **Blue** environment.
* **Canary Deployment:** Roll back if the small group of users experiences issues.
* **Rolling Deployment:** Gradually replace the new instances with the old stable version.
* **Versioned Artifacts:** Redeploy the previous application version stored in the artifact repository.

**When to Use:**

* During **production deployments**.
* In **CI/CD pipelines** with automated releases.
* For **microservices** and **cloud-native** applications.
* Whenever high availability and business continuity are critical.

**Simple Kubernetes Rollback Example:**

```bash id="r6m9tx"
# Check deployment history
kubectl rollout history deployment/my-app

# Roll back to the previous version
kubectl rollout undo deployment/my-app
```


## 17. How do you manage database migrations?

**Database Migration** is the process of **applying version-controlled changes** to the database schema, such as creating or modifying tables, columns, indexes, or constraints, without losing existing data.

**Key Features:**

* **Version Control** for database changes.
* **Automated Execution** during application deployment.
* Ensures **schema consistency** across all environments.
* Supports **rollback** of failed changes.
* Commonly managed using tools like **Flyway** or **Liquibase**.

**How it Works:**

1. Create a migration script with the required database changes.
2. Store the script in the project repository.
3. During application startup or CI/CD deployment, the migration tool checks the database version.
4. Only **new migration scripts** are executed in sequence.
5. The tool records executed migrations to avoid running them again.

**When to Use:**

* When modifying the **database schema**.
* During **application deployments** in CI/CD pipelines.
* To keep **development, testing, and production databases synchronized**.
* In projects where multiple developers work on the same database.

**Simple Flyway Migration Example:**

```sql id="f8k2wp"
-- File: V1__create_employee_table.sql
CREATE TABLE employee (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(50)
);
```

**Spring Boot Configuration Example:**

```properties id="m3x7qa"
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

```
**Step 1 → Add Dependency :: Maven**

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

**Step 2 → Configure Database :: application.yml**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: root

  flyway:
    enabled: true
```

**Step 3 → Create Migration Folder :: Create folder**

```txt
src/main/resources/db/migration
```

**Step 4 → Create SQL Migration File :: File name format**

```txt
V1__create_employee_table.sql
```

```txt
// Important:
V<version>__<description>.sql
```

**Step 5 → Add SQL :: V1__create_employee_table.sql**

```sql
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    salary DOUBLE
);
```

**Step 6 → Start Application :: When Spring Boot starts:**

```txt
Flyway automatically:
- checks migration history
- executes new scripts
- updates flyway_schema_history table
```

**Step 7 → Add New Migration :: Create new file:**

```txt
V2__add_email_column.sql
```

```sql
ALTER TABLE employee
ADD email VARCHAR(100);

Restart app → Flyway runs only V2.
```

**Internal Working**

```txt
Application Start
      ↓
Check flyway_schema_history
      ↓
Find pending migrations
      ↓
Execute SQL scripts
      ↓
Update migration history
```

**Generated Table :: Flyway creates:**

```txt
flyway_schema_history
```


## **18. How do you ensure zero downtime deployments?**

**Zero Downtime Deployment** is a deployment approach where a new application version is released **without interrupting service** for users. The old version continues serving requests until the new version is fully ready.

**Key Features:**

* **No Service Interruption** during deployment.
* **High Availability** for end users.
* Supports **Automatic Rollback** if issues are detected.
* Uses **Load Balancing** to redirect traffic.
* Commonly implemented with **Blue-Green**, **Canary**, or **Rolling Deployments**.

**How it Works:**

1. Deploy the new application version alongside the current one.
2. Run **health checks** and validate the new version.
3. Gradually or instantly switch traffic using a **Load Balancer**.
4. Monitor logs and application metrics.
5. If any problem occurs, automatically **rollback** to the previous stable version.

**Common Techniques:**

* **Blue-Green Deployment:** Keep two environments and switch traffic to the new one after validation.
* **Canary Deployment:** Release the new version to a small percentage of users before a full rollout.
* **Rolling Deployment:** Replace old instances with new ones gradually, one by one.

**When to Use:**

* For **production deployments** with high user traffic.
* In **microservices** and **cloud-native** applications.
* When **high availability** and **business continuity** are critical.
* In **CI/CD pipelines** with automated deployments.

**Simple Kubernetes Rolling Update Example:**

```yaml id="z4n8qy"
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-app
spec:
  strategy:
    type: RollingUpdate
```

## 9. What is Autoscaling and How is it Implemented??

**Autoscaling** is a cloud and microservices feature that automatically **increases** or **decreases** the number of application instances based on traffic, CPU usage, memory usage, or custom metrics.

**Key Features**

* **Automatic Scaling** based on workload.
* Improves **Performance** and **Availability**.
* Reduces **Infrastructure Cost** by scaling down when traffic is low.
* Supports **Horizontal Scaling** (adding/removing instances).
* Ensures better **Resource Utilization**.

**How It Works**

1. **Monitor Metrics** such as CPU, Memory, Request Count, or Response Time.
2. Define **Scaling Rules** and thresholds.
3. When the threshold is exceeded, new instances are automatically created (**Scale Out**).
4. When load decreases, unnecessary instances are removed (**Scale In**).

**When to Use**

* Applications with **variable traffic**.
* **Microservices** and **Cloud-native** applications.
* High-availability systems.
* E-commerce, banking, and streaming platforms.

**Kubernetes Autoscaling Example**

```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: user-service-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: user-service
  minReplicas: 2
  maxReplicas: 10
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 70
```

**How the Above Example Works**

* Minimum **2 Pods** always run.
* Maximum **10 Pods** can be created.
* If **CPU Usage** exceeds **70%**, Kubernetes automatically adds more pods.
* When CPU usage drops, extra pods are removed.


**Common Interview Follow-up Questions**

**1. What is Horizontal Scaling vs Vertical Scaling?**

| **Horizontal Scaling**  | **Vertical Scaling**                |
| ----------------------- | ----------------------------------- |
| Add more instances/pods | Increase CPU/RAM of existing server |
| Better fault tolerance  | Limited by hardware                 |
| Used in Microservices   | Used in Monolithic applications     |

**2. What is HPA in Kubernetes?**

**Horizontal Pod Autoscaler (HPA)** automatically scales the number of pods based on metrics such as **CPU**, **Memory**, or **Custom Metrics**.

**3. What metrics can be used for Autoscaling?**

* **CPU Usage**
* **Memory Usage**
* **Request Count**
* **Response Time**
* **Custom Business Metrics**

**4. What are the benefits of Autoscaling?**

* **High Availability**
* **Better Performance**
* **Cost Optimization**
* **Efficient Resource Utilization**


## 10. What is Horizontal scaling?

**Horizontal Scaling (Scale Out)** means **adding more servers/instances** to distribute the application load instead of increasing the resources of a single server.

**Key Features**

* **Adds multiple application instances**
* **Uses a Load Balancer** to distribute requests
* **High Availability**
* **Fault Tolerance**
* **Better Scalability**
* **Supports Cloud Environments** (AWS, Azure, Kubernetes)

**How it Works**

1. Deploy multiple instances of the application.
2. Place a **Load Balancer** in front of them.
3. The Load Balancer distributes incoming requests across all instances.
4. If traffic increases, add more instances.
5. If one instance fails, traffic is routed to the remaining healthy instances.

**When to Use**

* **High-Traffic Applications**
* **Microservices**
* **Cloud-Native Applications**
* **E-commerce Websites**
* **Banking and Payment Systems**

**Architecture**

```text
              Client Requests
                     |
              Load Balancer
           /       |        \
      App-1     App-2     App-3
           \       |        /
              Shared Database
```

**Spring Boot Example**

Run multiple instances of the same application on different ports.

**Instance 1**

```properties
server.port=8081
```

**Instance 2**

```properties
server.port=8082
```

**Instance 3**

```properties
server.port=8083
```

Configure a **Load Balancer** (such as **Nginx**) to distribute requests.

```nginx
upstream springboot-app {
    server localhost:8081;
    server localhost:8082;
    server localhost:8083;
}

server {
    listen 80;

    location / {
        proxy_pass http://springboot-app;
    }
}
```

Now, requests sent to **port 80** are automatically distributed across all three Spring Boot instances.

**Advantages**

* **Handles more concurrent users**
* **Easy to add or remove servers**
* **No single point of failure**
* **Improves application availability**
* **Ideal for cloud deployments**



## 10. How do you implement auto-scaling, Horizontal and vertical scaling?

**Auto-Scaling** is the process of **automatically increasing or decreasing application resources** based on workload. It is commonly implemented using cloud platforms or **Kubernetes Horizontal Pod Autoscaler (HPA)**.

**Key Features:**

* **Automatic Resource Adjustment** based on CPU, memory, or custom metrics.
* Improves **Performance** during traffic spikes.
* Optimizes **Cost** by reducing unused resources.
* Provides **High Availability** and better user experience.

**How it Works:**

1. The system continuously monitors metrics such as **CPU** or **Memory Usage**.
2. If usage exceeds a defined threshold, additional instances or containers are created.
3. When the load decreases, extra instances are automatically removed.
4. A **Load Balancer** distributes traffic across the available instances.

**Horizontal Scaling (Scale Out / Scale In):**

*  Add or remove **multiple servers or containers**.
* **Example:** Increase Pods from **3 to 6** in Kubernetes.
* **Best For:** **Microservices**, cloud applications, and distributed systems.
* **Advantage:** Better fault tolerance and almost unlimited scalability.

**Vertical Scaling (Scale Up / Scale Down):**

*  Increase or decrease the **CPU, RAM, or storage** of an existing server.
* **Example:** Upgrade a server from **4 GB RAM to 16 GB RAM**.
* **Best For:** Traditional applications or databases that cannot be easily distributed.
* **Limitation:** Has a hardware limit and may require downtime.

| **Scaling Type**       | **How it Works**                       | **Example**          |
| ---------------------- | -------------------------------------- | -------------------- |
| **Horizontal Scaling** | Add or remove servers/containers.      | 3 Pods → 6 Pods      |
| **Vertical Scaling**   | Increase or decrease server resources. | 4 GB RAM → 16 GB RAM |

**When to Use:**

* **Auto-Scaling:** For applications with changing traffic patterns.
* **Horizontal Scaling:** For cloud-native, microservices, and highly available systems.
* **Vertical Scaling:** For monolithic applications or databases requiring more resources.

**Simple Kubernetes Auto-Scaling Example:**

```bash id="a7k5mq"
# Create Horizontal Pod Autoscaler
kubectl autoscale deployment my-app \
  --cpu-percent=70 \
  --min=2 \
  --max=10
```

This configuration automatically keeps CPU usage around **70%** by scaling the number of Pods between **2 and 10**.


## 11. Blue-Green deployment strategy?

**Blue-Green Deployment** is a deployment technique where two identical production environments are maintained:

* **Blue Environment** = Current live version serving users.
* **Green Environment** = New version deployed and tested.

Once the new version is verified, traffic is switched from **Blue** to **Green** with minimal or no downtime.

**Key Features**

* **Zero or Near-Zero Downtime**
* **Quick Rollback**
* **Reduced Deployment Risk**
* **High Availability**
* **Production Testing Before Release**

**How It Works**

1. **Blue** environment is serving live traffic.
2. Deploy the new application version to **Green** environment.
3. Perform testing and validation on Green.
4. Switch traffic from Blue to Green using a **Load Balancer** or **Ingress Controller**.
5. If issues occur, route traffic back to Blue immediately.

**When to Use**

* **Production deployments** requiring high availability.
* Applications where **downtime is unacceptable**.
* **Microservices** and **Cloud-Native** applications.
* Systems requiring **fast rollback** capabilities.

**Example Flow**

```text
Before Deployment

Users
   |
 Blue (v1 - Live)

After Deployment

Users
   |
 Green (v2 - Live)

Blue remains available for rollback.
```

**Kubernetes Example**

**Blue Deployment**

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-blue
spec:
  replicas: 3
```

**Green Deployment**

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-green
spec:
  replicas: 3
```

**Service Initially Points to Blue**

```yaml
apiVersion: v1
kind: Service
metadata:
  name: app-service
spec:
  selector:
    version: blue
```

**Switch Traffic to Green**

```yaml
spec:
  selector:
    version: green
```

**Advantages**

* **No Downtime Deployments**
* **Instant Rollback**
* Safer releases with pre-production validation.
* Better user experience during deployments.

**Disadvantages**

* Requires **double infrastructure** during deployment.
* Higher infrastructure cost.
* Database changes must be handled carefully.


**Common Interview Follow-up Questions**

**1. What is the biggest advantage of Blue-Green Deployment?**

**Zero Downtime** and **Instant Rollback**.

**2. How is traffic switched between Blue and Green?**

Using a **Load Balancer**, **Reverse Proxy**, **DNS Switch**, or **Kubernetes Service/Ingress**.

**3. What is the difference between Blue-Green and Canary Deployment?**

| **Blue-Green**               | **Canary**                                    |
| ---------------------------- | --------------------------------------------- |
| Switches all traffic at once | Releases to a small percentage of users first |
| Simple rollback              | Gradual rollout                               |
| Requires two environments    | Requires traffic splitting                    |

**4. What challenge exists with databases in Blue-Green Deployment?**

**Database schema changes** must be backward compatible because both Blue and Green environments may need to work with the same database during the transition.



## 12. What is Rate Limiting and how does it work? Where do you implement it?

**Rate Limiting** is a technique used to **control the number of requests** a client can make to a service within a specific time period. It helps protect the system from **overload, abuse, and DDoS attacks**.

**Key Features:**

* Prevents **API abuse** and excessive traffic.
* Protects against **DDoS and brute-force attacks**.
* Improves **system stability** and **resource utilization**.
* Ensures **fair usage** among all users.
* Commonly implemented using **API Gateways**, **Load Balancers**, or **Redis**.

**How it Works:**

1. A client sends a request to the application.
2. The **Rate Limiter** checks how many requests the client has already made within the configured time window.
3. If the request count is below the limit, the request is processed.
4. If the limit is exceeded, the server rejects the request and returns **HTTP 429 (Too Many Requests)**.

**Common Rate Limiting Algorithms:**

* **Fixed Window Counter:** Allows a fixed number of requests per time window.
* **Sliding Window:** Tracks requests over a moving time window for smoother limiting.
* **Token Bucket:** Tokens are added at a fixed rate, and each request consumes a token.
* **Leaky Bucket:** Processes requests at a constant rate, smoothing traffic spikes.

**Where Do You Implement It?**

* **API Gateway** (Preferred) – e.g., Spring Cloud Gateway, Kong, NGINX.
* **Load Balancer** – To filter excessive requests before they reach the application.
* **Application Layer** – Using libraries such as **Bucket4j** or **Resilience4j**.
* **Distributed Cache (Redis)** – To maintain request counts across multiple application instances.

**When to Use:**

* For **public APIs** and microservices.
* To prevent **brute-force login attempts**.
* To protect systems from **traffic spikes** and malicious users.
* In **high-traffic distributed systems** and cloud applications.

**Simple Spring Boot + Bucket4j Example:**

```java id="t8p4xk"
Bucket bucket = Bucket.builder()
    .addLimit(limit -> limit.capacity(100)
    .refillGreedy(100, Duration.ofMinutes(1)))
    .build();

if (bucket.tryConsume(1)) {
    return "Request Allowed";
} else {
    return "HTTP 429 - Too Many Requests";
}
```


# ✅ 29. Java Testing

##  1. What is unit testing in Java?
Unit testing is a software testing technique where individual components or modules of a software application are tested in isolation
It helps ensure that each unit of code performs as expected and catches bugs early in developmentjava

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}

@Test
public void testAdd() {
    Calculator calc = new Calculator();
    assertEquals(5, calc.add(2, 3));
}
```

## 2. What is JUnit?
JUnit is a popular open-source testing framework for Java that provides annotations and assertions for writing and running unit testsjava

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
    @Test
    public void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("hello"));
    }
}
```

## 3. What are the annotations used in JUnit?
Common JUnit annotations include @Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll, @DisplayName, 

```java
@Disabledjava
public class LifecycleTest {
    @BeforeAll
    static void setupAll() { /* runs once before all tests */ }
    
    @BeforeEach
    void setup() { /* runs before each test */ }
    
    @Test
    @DisplayName("Test addition operation")
    void testAddition() { /* test method */ }
    
    @AfterEach
    void tearDown() { /* runs after each test */ }
    
    @AfterAll
    static void tearDownAll() { /* runs once after all tests */ }
}
```

## 4. What is TestNG?

TestNG is a testing framework inspired by JUnit but with more powerful features like data providers, parallel execution, and flexible test configurationjava

```java
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class TestNGExample {
    @DataProvider
    public Object[][] testData() {
        return new Object[][]{{1, 2, 3}, {4, 5, 9}};
    }
    
    @Test(dataProvider = "testData")
    public void testAdd(int a, int b, int expected) {
        assertEquals(a + b, expected);
    }
}
```

## 5. What is the difference between JUnit and TestNG?
JUnit is simpler and widely adopted, while TestNG offers more advanced features like data providers, parallel execution, and better test configurationjava

```java
// JUnit 5
@ParameterizedTest
@ValueSource(strings = {"hello", "world"})
void testWithJUnit(String word) {
    assertNotNull(word);
}

// TestNG
@Test(dataProvider = "words")
void testWithTestNG(String word) {
    assertNotNull(word);
}
```

## 6. What is mocking in Java testing?
Mocking is creating fake objects that simulate the behavior of real objects to isolate the unit being tested from its dependenciesjava

```java
// Using Mockito
@Mock
private UserRepository userRepository;

@Test
void testGetUser() {
    User mockUser = new User("John", "john@email.com");
    when(userRepository.findById(1L)).thenReturn(mockUser);
    
    User result = userService.getUser(1L);
    assertEquals("John", result.getName());
}
```

## 7. What is Mockito?
Mockito is a popular Java mocking framework that allows you to create mock objects and define their behavior for testingjava
import static org.mockito.Mockito.*

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void testSendWelcomeEmail() {
        userService.registerUser("john@email.com");
        verify(emailService).sendEmail("john@email.com", "Welcome!");
    }
}
```

## 8. What is integration testing?
Integration testing verifies that different modules or services work correctly when integrated togetherjava

```java
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testCreateUser() {
        User user = new User("John", "john@email.com");
        ResponseEntity<User> response = restTemplate.postForEntity("/users", user, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
```

## 9. What is test-driven development (TDD)?
TDD is a development approach where you write tests first, then write the minimum code to make tests pass, then refactorjava

```java
// Step 1: Write failing test
@Test
void testCalculateArea() {
    Circle circle = new Circle(5);
    assertEquals(78.54, circle.calculateArea(), 0.01);
}

// Step 2: Write minimum code to pass
public class Circle {
    private double radius;
    
    public Circle(double radius) { this.radius = radius; }
    
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}
```

## 10. What is behavior-driven development (BDD)?
BDD extends TDD by writing tests in natural language that describes the behavior of the application from user's perspectivejava

```java
// Using Cucumber with Java
@Given("a user with email {string}")
public void aUserWithEmail(String email) {
    user = new User(email);
}

@When("the user logs in")
public void theUserLogsIn() {
    loginResult = authService.login(user);
}

@Then("the login should be successful")
public void theLoginShouldBeSuccessful() {
    assertTrue(loginResult.isSuccess());
}
```