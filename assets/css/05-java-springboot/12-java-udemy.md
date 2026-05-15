


## 02 - Core Java
### 1. What does a high-order bit represent for an integer?

- a) The least significant value
- b) The memory address
- c) The decimal value of the integer
- d) The sign of the integer (0 for positive, 1 for negative) ✅

---

### 2. What is the result of the following code snippet? *(Assume integer overflow example)*

- a) Compilation error
- b) 0
- c) Maximum integer value
- d) Wrap-around value due to overflow ✅

---

### 3. What is the result of the following code snippet? *(Assume floating-point division)*

- a) 0
- b) Exception
- c) Integer value
- d) Floating-point result ✅

---

### 4. What is the default value assigned to a variable of boolean type?

- a) true
- b) 1
- c) null
- d) false ✅

---

### 5. Choose an invalid way to declare a character variable in Java.

- a) `char c = 'A';`
- b) `char c = 65;`
- c) `char c = '\u0041';`
- d) `char c = "A"; ✅`**

---

### 6. What do you mean by type promotion in Java?

- a) Converting primitive to object automatically
- b) Explicit casting by the programmer
- c) Converting object to primitive automatically
- d) Automatic conversion of smaller data type to larger data type during arithmetic operations ✅

---

### 7. Which of the following is the valid way to represent a long literal in Java?

- a) `long l = 123;`
- b) `long l = 123l;`
- c) `long l = 123;`
- d) `long l = 123L; ✅`**

---

### 8. What kind of error will be thrown if the main method is not found in the Java program?

- a) Compilation error
- b) Runtime exception
- c) Logical error
- d) NoSuchMethodError ✅

---

### 9. Is Java a Strictly Typed Language?

- a) No, it allows implicit type conversion always
- b) No, types are checked only at runtime
- c) No, it supports weak typing
- d) Yes, all variables must be declared with a specific type ✅

---

### 10. Which method is used to add a new line character at the end of the output in the console?

- a) System.out.print()
- b) System.out.write()
- c) System.out.printf()
- d) System.out.println() ✅

---

### 11. What will be the value of variables ‘x’ and ‘y’ from the given code? *(Assume `x = y = 10; x += y;`)*

- a) x=10, y=10
- b) x=20, y=20
- c) x=10, y=20
- d) x=20, y=10 ✅

---

### 12. Predict the output of the code: *(Assume `int a=5; System.out.println(a++ + ++- a);`)*

- a) 10
- b) 11
- c) 12
- d) 12 ✅

---

### 13. What will be the output of the following Java code? *(Assume string concatenation `System.out.println("Hello " + 5 + 5);`)*

- a) Hello 10
- b) Hello 55
- c) 10Hello
- d) Hello 55 ✅


## 03 - Advance Java

### 1. Which access modifier is used to access members of an inherited class when two classes are in different packages?
- a) private  
- b) default  
- c) protected ✅  
- d) static  

---

### 2. Which principle emphasizes the binding of data and methods within a class?
- a) Polymorphism  
- b) Encapsulation ✅  
- c) Abstraction  
- d) Inheritance  

---

### 3. Can the value of a variable be changed if it is initialized with the `final` keyword?
- a) Yes  
- b) No ✅  

---

### 4. Does the given code define a valid way to initialize the value of a `final` variable?
```java
class MyClass {
    private final int myVariable;
    {
        myVariable = 10;
    }
}
```

* a) Yes ✅
* b) No

---

### 5. Which keyword is used to define a class member that can be accessed without reference to any object?

* a) final
* b) static ✅
* c) public
* d) private

---

### 6. Which keyword is used to achieve inheritance in Java?

* a) final
* b) extends ✅
* c) this
* d) implements

---

### 7. Can a class be a superclass of itself in Java?

* a) Yes
* b) No ✅

---

### 8. Is method shadowing or method hiding possible with a private method?

* a) Yes ✅
* b) No

---

### 9. What is the return type of overridden methods?

* a) Covariant Return Types ✅
* b) Different from a superclass
* c) void only
* d) Not specified

---

### 10. What interface is implemented by the `String`, `StringBuffer`, and `StringBuilder` classes?

* a) String Sequence
* b) CharSequence ✅
* c) StringManipulator
* d) CharSequenceBuffer

---

### 11. What will be the output of the given code snippet?

```java
char chars[] = {'a', 'b', 'c', 'd', 'e', 'f'};
String str = new String(chars,1,3);
System.out.println(str);
```

* a) ['b', 'c', 'd']
* b) ['b', 'c', 'd', 'e']
* c) bcd ✅
* d) bcde

---

### 12. Which constructor of `StringBuffer` reserves room for 16 characters without reallocation?

* a) StringBuffer() ✅
* b) StringBuffer(int size)
* c) StringBuffer(String str)
* d) StringBuffer(CharSequence chars)

---

### 13. Which class can be used when multiple threads are involved and thread-safety is required?

* a) StringBuilder
* b) StringBuffer ✅
* c) Both can be used
* d) None of the above

---

### 14. Determine the capacity of the `StringBuffer` before and after the use of `trimToSize()` method:

```java
StringBuffer sb = new StringBuffer("Java Code");
System.out.println(sb.capacity());
sb.trimToSize();
System.out.println(sb.capacity());
```

* a) 16 and 16
* b) 16 and 9
* c) 25 and 9 ✅
* d) 25 and 16

---

### 15. What Java keyword is employed for specifying a default method within a Java interface?

* a) new
* b) override
* c) implements
* d) default ✅

---

### 16. When implementing a Java interface, methods must possess the following access modifier:

* a) public ✅
* b) abstract
* c) private
* d) protected

---

### 17. By default, the methods enclosed within a Java interface are characterized as:

* a) abstract and public ✅
* b) private and static
* c) final and protected
* d) synchronized and void

---

### 18. What do inner classes promote in Java?

* a) Composition and aggregation ✅
* b) Inheritance
* c) Polymorphism
* d) Abstraction

---

### 19. What is the key requirement for an interface to be considered a functional interface?

* a) It must define at least one abstract method
* b) It must define multiple abstract methods
* c) It must define only one abstract method ✅
* d) It must define a method named "run"

---

### 20. Which of the given expressions returns `true` if the value of the parameter `n` is even?

* a) `(n) -> (n% * 2)! = 0`
* b) `(n) -> (n % 2) == 0` ✅
* c) `() -> (n% * 2) == 0`
* d) `(n% * 2) -> (n) == 0`

---

### 21. Can a subclass reference variable be assigned to a superclass reference variable?

* a) Yes ✅
* b) No

---

### 22. What is the purpose of the lambda operator `->` in Java lambda expressions?

* a) It specifies the required parameters of an expression
* b) It converts an expression to a method with a name
* c) Divides the expression into parameter list and lambda body ✅
* d) It indicates a constant value in an expression

---

### 23. In what order are the constructors executed when a class hierarchy is created?

* a) From subclass to superclass
* b) From superclass to subclass ✅
* c) Any random order
* d) They do not execute in a class hierarchy

---

### 24. Can an abstract class be instantiated directly with the `new` operator in Java?

* a) Yes
* b) No ✅


### 5. What is the output of the code?
### 6. Comment on the statement: "The method defined by a lambda expression does not have a name."
### 7. What is the output of the code?
### 12. What will be the value of the s1 and s2 variables after the execution of the following code?

## Your goals
### Differentiate between abstract classes, interfaces, enums, and annotations based on design use cases.

### Apply exception handling best practices using try-catch, custom exceptions, and throws.

### Demonstrate understanding of multithreading and thread lifecycle.

### Use Java Stream API and method references to simplify data processing.

### Solve a short hands-on Java coding problem using collection or functional logic

## 04 - Maven

### 1. In the provided Maven dependency, what does `<groupId>` refer to?
### 2. What does the `<dependency>` element represent in a Maven project configuration?
### 3. What is the full form of POM?
### 4. In Maven, what is a transitive dependency?
### 5. Which of the following elements in the pom.xml file specifies the project's group identifier?
### 6. In Maven, What is the default scope?
### 7. Which phase of the Maven build lifecycle is responsible for compiling the Java source code?
### 8. Which file is used to configure project settings and dependencies in Maven?
### 9. What is Maven primarily used for in Java development?


## 05 - JDBC Data base connect
### 1. What is the main benefit of using `PreparedStatement` over `Statement`?

- a) It executes queries faster than all other JDBC interfaces
- b) It does not require a database connection
- c) It automatically commits transactions
- d) It allows parameterized queries and prevents SQL injection ✅

---

### 2. Why is using `Statement` in JDBC considered risky?

- a) It cannot execute SELECT queries
- b) It requires too much memory
- c) It does not support transactions
- d) It is prone to SQL injection attacks ✅

---

### 3. Which CRUD operation does `executeUpdate()` typically support in JDBC?

- a) SELECT
- b) JOIN
- c) None of the above
- d) INSERT, UPDATE, DELETE ✅

---

### 4. What does `ResultSet` in JDBC represent?

- a) A connection to the database
- b) The SQL statement executed
- c) The database driver
- d) The results returned by a SQL query ✅

---

### 5. Which JDBC interface is used to execute SQL queries and retrieve results?

- a) Connection
- b) DriverManager
- c) ResultSet
- d) Statement / PreparedStatement ✅

---

### 6. Which external file is needed to connect PostgreSQL with Java using JDBC?

- a) postgres.properties
- b) pgsql.xml
- c) java.sql
- d) postgresql.jar ✅

---

### 7. What is the correct order of steps in JDBC programming?

- a) Establish connection → Load driver → Execute query → Process results → Close connection
- b) Load driver → Execute query → Establish connection → Create statement → Close connection
- c) Create statement → Load driver → Establish connection → Execute query → Process results
- d) Load driver → Establish connection → Create statement → Execute query → Process results → Close connection ✅

---

### 8. Which of the following is required to set up PostgreSQL for JDBC?

- a) PostgreSQL database installation
- b) PostgreSQL JDBC driver (JAR file)
- c) Correct URL, username, and password for connection
- d) All of the above ✅

---

### 9. What is the primary purpose of JDBC in Java?

- a) To manage transactions automatically
- b) To create user interfaces for databases
- c) To replace SQL in Java programs
- d) To provide a standard API for connecting and interacting with databases ✅


## 06 - Hibernate
### 1. Which caching mechanism does Hibernate Level 2 cache use by default?

- a) In-memory cache per session
- b) No caching
- c) Query cache only
- d) EHCache or any configured cache provider ✅

---

### 2. What is the difference between `get()` and `load()` in Hibernate?

- a) `get()` returns a proxy, `load()` hits the database immediately
- b) `get()` throws exception if object not found, `load()` returns null
- c) Both behave the same
- d) `get()` hits database immediately and returns null if not found, `load()` returns a proxy and throws exception if object not found ✅

---

### 3. How can you apply filters and fetch only selected fields in HQL?

- a) Using native SQL queries
- b) Using Criteria API only
- c) Using SessionFactory methods
- d) Using `select` clause and `where` filters in HQL ✅

---

### 4. Which method fetches data using HQL?

- a) session.save()
- b) session.persist()
- c) session.delete()
- d) session.createQuery().list() ✅

---

### 5. What is HQL in Hibernate?

- a) High-Level Query tool for SQL optimization
- b) Hibernate Quick Language for database creation
- c) SQL variant for caching
- d) Hibernate Query Language, an object-oriented query language ✅

---

### 6. Which annotation is used for caching in Hibernate?

- a) @Entity
- b) @Table
- c) @Id
- d) @Cache ✅

---

### 7. In Hibernate, what is eager fetching?

- a) Fetching data only when accessed
- b) Fetching data lazily per session
- c) Fetching unrelated tables only
- d) Fetching associated entities immediately along with parent entity ✅

---

### 8. Which Hibernate annotation is used to map many-to-many relationships?

- a) @OneToMany
- b) @OneToOne
- c) @ManyToOne
- d) @ManyToMany ✅

---

### 9. Which annotation is used for OneToMany and ManyToOne relationships?

- a) @ManyToMany
- b) @OneToOne
- c) @Embeddable
- d) @OneToMany and @ManyToOne ✅

---

### 10. What does @OneToOne mapping define in Hibernate?

- a) Multiple parent entities for one child
- b) Multiple child entities for one parent
- c) No association
- d) One entity is associated with exactly one other entity ✅

---

### 11. Which Hibernate annotation is used for reusable embeddable objects like address?

- a) @Entity
- b) @Table
- c) @Id
- d) @Embeddable ✅

---

### 12. How do you change the table and column names in Hibernate mappings?

- a) By editing database schema manually
- b) By using native SQL only
- c) By using mapping files only
- d) By using @Table and @Column annotations ✅

---

### 13. Which Hibernate methods are used to update or delete objects?

- a) session.load()
- b) session.get()
- c) session.find()
- d) session.update() and session.delete() ✅

---

### 14. Which method is used to fetch data in Hibernate?

- a) session.save()
- b) session.persist()
- c) session.delete()
- d) session.get() or session.load() ✅

---

### 15. Why is refactoring important in a Hibernate project?

- a) To increase SQL query complexity
- b) To remove Hibernate annotations
- c) To generate database schema automatically
- d) To improve code maintainability and readability ✅

---

### 16. What is the purpose of `show_sql=true` in Hibernate configuration?

- a) To execute queries faster
- b) To enable caching
- c) To create tables automatically
- d) To display generated SQL statements in console ✅

---

### 17. What is required for a successful data save using Hibernate?

- a) Only defining entity classes
- b) Only database connection
- c) Only configuration files
- d) Proper mapping, session, and transaction management ✅

---

### 18. Why might an initial attempt to save data in Hibernate fail?

- a) Hibernate does not support inserts
- b) Only HQL can save data
- c) Because database is read-only
- d) Missing transaction or incorrect mapping ✅

---

### 19. Which file is typically configured first in a Hibernate project setup?

- a) Mapping XML for entities
- b) POJO class
- c) Database table
- d) hibernate.cfg.xml or hibernate.properties ✅

---

### 20. What is the primary purpose of Hibernate in Java applications?

- a) To replace JDBC completely
- b) To generate UI for database
- c) To execute only HQL queries
- d) To provide ORM (Object-Relational Mapping) and simplify database interactions ✅


## 07 - Spring Getting started
### 1. What is the role of the Spring MVC module?

- a) To manage database connections
- b) To provide dependency injection
- c) To handle caching and sessions
- d) To handle web requests and responses using the Model-View-Controller pattern ✅

---

### 2. Which of the following statements is TRUE about Spring Boot?

- a) Spring Boot requires manual configuration of every bean
- b) Spring Boot cannot be used with Spring MVC
- c) Spring Boot eliminates the need for dependency injection
- d) Spring Boot simplifies Spring application setup with auto-configuration ✅

---

### 3. Which Spring Boot feature automatically configures components based on project dependencies?

- a) Spring JDBC
- b) Spring MVC
- c) Spring AOP
- d) Spring Boot Auto-Configuration ✅

---

### 4. What is the default embedded server provided by Spring Boot?

- a) Tomcat must always be installed manually
- b) Jetty only
- c) WebSphere
- d) Tomcat ✅

---

### 5. Which tool is used to generate Spring Boot projects with minimal setup?

- a) Maven only
- b) Gradle only
- c) Eclipse IDE
- d) Spring Initializr ✅

---

### 6. What is the main benefit of Dependency Injection (DI) in Spring?

- a) To increase coupling between classes
- b) To manually create all objects in code
- c) To enforce static method usage
- d) To reduce coupling and improve testability ✅

---

### 7. Which feature of Spring is used to separate cross-cutting concerns like logging?

- a) Spring MVC
- b) Spring JDBC
- c) Spring Boot Starter
- d) Spring AOP (Aspect-Oriented Programming) ✅

---

### 8. What is the key principle of Inversion of Control (Io- c)?

- a) Classes manage their own dependencies
- b) Code explicitly creates all dependencies
- c) The application must manually wire every object
- d) The framework controls the creation and injection of dependencies ✅

---

### 9. What annotation is used in Spring to automatically inject dependencies?

- a) @Entity
- b) @Component
- c) @Service
- d) @Autowired ✅

---

### 10. Which Spring module is used for integrating ORM tools like Hibernate?

- a) Spring MVC
- b) Spring AOP
- c) Spring Web
- d) Spring ORM ✅

---

### 11. What does POJO stand for in the context of Spring?

- a) Pre-Ordered Java Object
- b) Public Object Java Override
- c) Persistent Object Java Only
- d) Plain Old Java Object ✅

---

### 12. What is the primary purpose of the Spring Framework?

- a) To replace Java completely
- b) To generate UI for applications
- c) To manage SQL queries only
- d) To provide a lightweight framework for dependency injection, AOP, and enterprise application development ✅

## 08 - Spring Framework – XML Bean Configuration Round at Telusko

## 09 Spring Framework 

---

### 1. In an XML-based Spring configuration, which XML element is used to define a Spring bean?
- a) `<component>`
- **b) `<bean>`** ✅
- c) `<class>`
- d) `<property>`

---

### 2. Which interface is used to load and manage Spring beans in the code?
- **a) ApplicationContext** ✅
- b) CourseSelecting
- c) LaunchTestApp
- d) ClassPathXmlApplicationContext

---

### 3. Which annotation injects a dependency into a Spring bean?
- **a) @Autowired** ✅
- b) @Inject
- c) @Resource
- d) @Value

---

### 4. What does the `ref` attribute in `<constructor-arg>` specify in Spring?
- a) It specifies the class name  
- b) It specifies the constructor name  
- **c) Referenced Spring bean for injection** ✅  
- d) It specifies the scope of the bean  

---

### 5. Which autowiring mode uses property names to find matching beans for injection?
- **a) autowire="byName"** ✅
- b) autowire="byType"
- c) autowire="constructor"
- d) autowire="no"

---

### 6. In XML configuration, which of the following attributes in the `<bean>` tag is used for setter injection?
- a) `<constructor-arg>`
- b) `<set-property>`
- **c) `<property>`** ✅
- d) `<value>`

---

### 7. Which method is used to retrieve a bean from the Spring IoC container by its ID?
- a) getContext()
- b) findBean()
- **c) getBean()** ✅
- d) getObject()

---

### 8. How does Spring resolve conflicts when multiple beans of the same type exist?
- **a) Using the primary attribute on one bean** ✅
- b) By using the last defined bean
- c) By choosing beans in alphabetical order
- d) None of the above

---

### 9. The `@ConstructorProperties` annotation in Spring allows Spring to:
- a) Inject dependencies based on bean IDs
- b) Inject dependencies in any specified order
- **c) Map constructor parameters by their names** ✅
- d) Bypass the autowiring process

---

### 10. Which attribute is used to specify that a bean should be created only when requested?
- a) primary
- **b) lazy-init** ✅
- c) id
- d) autowire

---

### 11. Which method of `getBean()` allows you to retrieve a bean by type only if there is a single instance?
- **a) getBean(Class<T> type)** ✅
- b) getBean(String id, Class<T> type)
- c) getBean(String id)
- d) getBean(id, type)

---

### 12. Which annotation is used to specify the scope of a Spring bean?
- **a) @Scope** ✅
- b) @Qualifier
- c) @Primary
- d) @Bean

---

### 13. What is the purpose of the `class` attribute in the `<bean>` element of Spring XML configuration?
- a) It defines the scope of the bean  
- **b) It specifies the bean's class name** ✅  
- c) It sets the primary key of the bean  
- d) It injects properties into the bean  

---

### 14. What is the default scope for Spring beans if it is not specified explicitly?
- **a) Singleton** ✅
- b) Prototype
- c) Request
- d) Session

---

## Your goals
### Explain the difference between constructor and setter injection using Spring XML syntax

### Understand how to wire beans using the ref attribute and define relationships between beans

### Demonstrate awareness of bean scopes like singleton vs prototype

### Discuss how Spring handles autowiring, including resolving conflicts using @Primary or matching by type

### Describe lazy loading, use of inner beans, and wiring by interface for decoupling


## 09 - Spring Java-Based Configuration Interview – at Telusko

### 1. What is the purpose of the `@Configuration` annotation in Spring?
- a) To define the scope of beans  
- **b) To mark a class as a source of bean definitions** ✅  
- c) To initialize Spring context  
- d) To specify bean dependencies  

---

### 2. What is the default scope of a Spring bean?
- a) Prototype  
- **b) Singleton** ✅  
- c) Request  
- d) Session  

---

### 3. Which of the following annotations is used to define a bean in a configuration class?
- **a) @Bean** ✅  
- b) @Autowired  
- c) @Component  
- d) @Configuration  

---

### 4. What does `AnnotationConfigApplicationContext` do in Spring?
- a) It initializes beans defined in XML files  
- **b) It initializes beans using annotations in a configuration class** ✅  
- c) It provides a way to autowire beans  
- d) It initializes beans from property files  

---

### 5. What is the default name of a Spring bean defined using `@Bean`?
- a) The fully qualified class name  
- b) The class name in lowercase  
- **c) The method name that defines the bean** ✅  
- d) No default name is assigned  

---

### 6. Which annotation can be used to specify multiple names for a bean?
- a) @Primary  
- b) @Qualifier  
- **c) @Bean(name = {...})** ✅  
- d) @Autowired  

---

### 7. How can you specify the default bean to inject when multiple candidates are present?
- a) Using @Qualifier  
- b) Using @Autowired  
- **c) Using @Primary** ✅  
- d) Using @Configuration  

---

### 8. What is the output of the following code snippet if the bean has a prototype scope?

```java
Desktop dt1 = context.getBean("desktopPrototype", Desktop.class);
Desktop dt2 = context.getBean("desktopPrototype", Desktop.class);
System.out.println(dt1 == dt2);
```

* a) true
* **b) false** ✅
* c) Compilation error
* d) Run Time Error

---

### 9. Which of the following is true for constructor-based autowiring?

* a) It is not recommended in newer versions of Spring
* **b) It ensures dependencies are injected during object creation** ✅
* c) It is less preferred compared to field-level autowiring
* d) It cannot handle multiple dependencies

---

### 10. In field-level autowiring, where is the `@Autowired` annotation placed?

* a) On the setter method
* b) On the constructor
* **c) Directly on the field** ✅
* d) On the `@Bean` definition

---

### 11. What is the role of the `@Qualifier` annotation?

* a) It marks a bean as a singleton
* **b) It allows specifying which bean to inject when multiple beans of the same type exist** ✅
* c) It defines the lifecycle of a bean
* d) It initializes beans at runtime

---

### 12. Which annotation is used to change the default bean scope to prototype?

* a) @Prototype
* b) @Bean(scope = "prototype")
* **c) @Scope("prototype")** ✅
* d) @Autowired

---

### 13. What happens if there are multiple beans of the same type and no `@Qualifier` or `@Primary` is used?

* **a) Spring throws a runtime exception** ✅
* b) Spring randomly picks a bean
* c) The application runs without injecting any bean
* d) Spring logs a warning and continues

---

### 14. Which autowiring method is most suitable for optional dependencies?

* a) Field-level autowiring
* b) Constructor-based autowiring
* **c) Setter-based autowiring** ✅
* d) None of the above

---

### 15. What is the benefit of using constructor-based autowiring over field-level autowiring?

* a) It reduces code complexity
* **b) It makes the class immutable and ensures dependencies are mandatory** ✅
* c) It allows injecting multiple beans of the same type without ambiguity
* d) It is the default behavior in Spring

---

## Your goals
### Differentiate between @Component vs @Bean and when to use each

### Understand @Scope, @Value, and @Qualifier annotations in practical terms

### Explain field, setter, and constructor-based autowiring

### Configure beans using pure Java without XML

### Perform live coding tasks based on realistic Telusko scenarios

## 10 -Spring Boot Fundamentals + Architecture Round – at Telusko

### 1. Which feature of Spring Boot allows applications to run without deploying to an external server?
- a) Simplified configuration  
- **b) Embedded servers** ✅  
- c) Annotations  
- d) Application Context  

---

### 2. What does the `@SpringBootApplication` annotation do in a Spring Boot application?
- a) Configures beans manually  
- **b) Enables auto-configuration and component scanning** ✅  
- c) Adds all JAR dependencies automatically  
- d) Specifies the package for scanning  

---

### 3. Which Spring Boot annotation defines a class as a Spring-managed component?
- **a) @Component** ✅  
- b) @Value  
- c) @Autowired  
- d) @Configuration  

---

### 4. The `@Repository` annotation is primarily used in which layer?
- a) Controller  
- b) Service  
- **c) Data Access (DAO)** ✅  
- d) Security  

---

### 5. Which MVC layer handles HTTP requests in Spring Boot?
- a) Model  
- b) View  
- **c) Controller** ✅  
- d) Service  

---

### 6. In which layer does the application interact directly with the database in Spring Boot?
- a) Controller  
- b) Service  
- **c) Repository** ✅  
- d) View  

---

### 7. What is the purpose of the `@Repository` annotation in Spring Boot?
- a) Denotes a class as a controller  
- **b) Marks a class as a bean and handles database exceptions** ✅  
- c) Creates a new bean for each HTTP request  
- d) Automatically starts the embedded server  

---

### 8. Which annotation would you use to manage application properties in Spring Boot?
- a) @Service  
- **b) @Value** ✅  
- c) @Component  
- d) @Repository  

---

### 9. A common annotation for defining a RESTful endpoint in Spring Boot is:
- a) @Service  
- **b) @RestController** ✅  
- c) @Configuration  
- d) @Repository  

---

### 10. Which Spring Boot method retrieves the `LaptopService` bean from the application context in `SpringBootDemoApplication`?
- a) `context.getBean(Laptop.class)`  
- b) `SpringApplication.run()`  
- **c) `context.getBean(LaptopService.class)`** ✅  
- d) `LaptopService.add()`  

---

## Your goals
### Explain the benefits of moving from Spring to Spring Boot

### Use key annotations like @SpringBootApplication, @RestController, @Service, and @Repository

### Understand and build layered architecture

### Handle a basic REST API setup using Spring Boot

### Solve 2–3 live coding tasks confidently

---

## 11 - Spring JDBC Hands-On Interview – TeluskoD

### 1. In Spring JDBC, which class is used to simplify database operations?
- **a) JdbcTemplate** ✅  
- b) JdbcConfig  
- c) DatabaseConnector  
- d) DataMapper  

---

### 2. What does the `update()` method in `JdbcTemplate` return?
- a) Number of rows retrieved  
- **b) Number of rows affected** ✅  
- c) Total columns updated  
- d) A ResultSet object  

---

### 3. What does `@Scope("prototype")` do when applied to a Spring Bean?
- **a) Creates a new instance for each request** ✅  
- b) Shares a single instance globally  
- c) Makes it session-scoped  
- d) Disables the bean  

---

### 4. What is the purpose of the `schema.sql` file in a Spring Boot project?
- a) To define the initial data for the database  
- **b) To define the database structure** ✅  
- c) To configure application properties  
- d) To set up custom SQL functions  

---

### 5. What is the primary purpose of the `data.sql` file?
- **a) To insert initial data into tables** ✅  
- b) To update database records  
- c) To drop existing tables  
- d) To define the schema of the database  

---

### 6. Where should the `schema.sql` and `data.sql` files be located in a Spring Boot project?
- a) `src/main/java`  
- b) `src/main/config`  
- c) `src/test/resources`  
- - d) `src/main/resources`** ✅  

---

### 7. In the `mapRow()` method, what does the `ResultSet` parameter represent?
- a) The current database connection  
- b) The SQL query being executed  
- **c) The results from the SQL query** ✅  
- d) The number of affected rows  

---

### 8. What does `jdbc.update()` do in the `save()` method?
- a) Executes a SELECT statement  
- **b) Inserts data into the database** ✅  
- c) Deletes data from the database  
- d) Maps a row to a Java object  

---

### 9. In the `application.properties` file, which property specifies the JDBC URL for connecting to the PostgreSQL database?
- a) `spring.datasource.username`  
- b) `spring.datasource.password`  
- c) `spring.datasource.driver-class-name`  
- - d) `spring.datasource.url`** ✅  

---

### 10. Which method in `JdbcTemplate` is used to execute a SELECT query?
- a) `execute()`  
- **b) `query()`** ✅  
- c) `find()`  
- d) `select()`  

---

## Your goals
### Explain what Spring JDBC is and how it differs from plain JDBC in terms of abstraction and productivity during interviews.

### Set up a basic Spring JDBC application with proper configuration, demonstrating understanding of how to connect to a database.

### Respond to short, practical coding prompts like writing a method to fetch a record using JdbcTemplate. (Example coding task in chat: “Can you write a method using JdbcTemplate to get student by ID?”)

### Demonstrate how to use RowMapper properly to convert result sets into domain objects quickly. (Example coding task in chat: “Can you write a RowMapper for a Student class?”)

### Answer real-world interview questions confidently, such as how exceptions are handled, or how to use schema/data.sql in Spring Boot JDBC.

---

## 12 - Spring Boot Web Interview – Telusko Round with Akshay

### 1. Which component of Spring MVC is responsible for representing the application's data?
- a) View  
- **b) Model** ✅  
- c) Controller  
- d) DispatcherServlet  

---

### 2. What is the purpose of the `@RequestMapping` annotation in Spring MVC?
- **a) To map HTTP requests to specific methods** ✅  
- b) To define the model for the view  
- c) To bind form data to objects  
- d) To handle database operations  

---

### 3. What is the main function of the View component in Spring MVC?
- a) Handle user input processing  
- b) Manage application data representation  
- **c) Present data to the end user** ✅  
- d) Oversee the application's control flow  

---

### 4. What does the `@ModelAttribute` annotation do?
- **a) Links form data to model objects** ✅  
- b) Validate user input  
- c) Manages user requests  
- d) Defines the name of the view  

---

### 5. What is the role of the DispatcherServlet's front controller?
- a) Displays data to the user  
- **b) Manages HTTP requests centrally** ✅  
- c) Validates form data  
- d) Manages database connections  

---

### 6. Which configuration file is required for setting up `DispatcherServlet` in a Spring MVC application?
- a) application.properties  
- **b) web.xml** ✅  
- c) dispatcher-servlet.xml  
- d) settings.xml  

---

### 7. What does `<servlet-mapping>` do in the `web.xml` configuration?
- **a) Maps a servlet to a specific URL** ✅  
- b) Specifies the view resolver  
- c) Defines the servlet's package  
- d) Configures database connections  

---

### 8. What does the `InternalResourceViewResolver` bean do?
- a) Configures request handling  
- **b) Resolves logical view names to files** ✅  
- c) Manages database connections  
- d) Handles HTTP sessions  

---

### 9. If a controller returns a view name `"home"`, which path does Spring MVC resolve if prefix is `/views/` and suffix is `.jsp`?
- a) `/home.jsp`  
- b) `/views/home`  
- **c) `/views/home.jsp`** ✅  
- d) `/home.html`  

---

### 10. Which of the following is a valid view technology supported by Spring Boot for web-based applications?
- a) Thymeleaf  
- b) JSP  
- c) FreeMarker  
- - d) All of the above** ✅  

---

## Your goals
### Demonstrate understanding of controller and servlet mapping in Spring MVC, including how to configure @RequestMapping or @GetMapping for handling web requests.

### Explain the flow of data from a web form to a Spring controller using @ModelAttribute and @RequestParam.

### Coding Task: Write a short Spring controller method that maps a GET request to /welcome and returns a string "welcome-page" as the view name.

### Coding Task: Given a form with name and email fields, write a Spring controller method that accepts the data using @ModelAttribute and prints it to the console.

### Analyze a sample code snippet (shown in the interview) and explain what each annotation does and how the model data is passed to the view.

--- 
## 13 - Exploring Web MVC without Spring Boot 

### 1. In a Spring MVC app running on Tomcat, which component first receives every HTTP request and routes it to controllers?
- a) ViewResolver  
- b) HandlerInterceptor  
- **c) DispatcherServlet** ✅  
- d) None of the above  

---

### 2. Where do you map requests to the `DispatcherServlet` in a plain Maven Spring MVC project?
- a) application.properties  
- **b) web.xml using `<servlet>` and `<servlet-mapping>`** ✅  
- c) pom.xml  
- d) server.xml  

---

### 3. In `web.xml`, what must match to link the servlet definition with its mapping?
- a) `<url-pattern>` and `<display-name>`  
- b) `<servlet-class>` and `<url-pattern>`  
- **c) `<servlet-name>` in both `<servlet>` and `<servlet-mapping>`** ✅  
- d) `<load-on-startup>` values  

---

### 4. If your servlet name is **telusko**, what configuration file name does the `DispatcherServlet` look for by default?
- a) applicationContext.xml in `/WEB-INF`  
- **b) telusko-servlet.xml in `/WEB-INF`** ✅  
- c) dispatcher-servlet.xml in project root  
- d) spring-mvc.xml in `/resources`  

---

### 5. What two things must you typically declare in `*-servlet.xml` so Spring can find annotation-based controllers?
- a) `<tx:annotation-driven>` and `<mvc:interceptors>`  
- **b) `<context:component-scan>` (base package) and `<context:annotation-config>`** ✅  
- c) `<bean id="dataSource">` and `<bean id="sessionFactory">`  
- d) `<bean id="dataSource">` and `<bean id="sessionFactory">`  

---

### 6. In the debugging flow described, a 404 after hitting a controller usually indicated:
- a) Tomcat is down  
- **b) The view name (e.g., "index") couldn't be resolved to a JSP** ✅  
- c) The controller method threw a `NullPointerException`  
- d) The port is already in use  

---

### 7. In a Spring MVC configuration, what does the `<mvc:resources>` tag do?
- **a) Configures static resource handling such as CSS, JavaScript, or images** ✅  
- b) Scans the application for annotated controllers  
- c) Maps URL patterns to controller methods  
- d) Specifies the location of JSP files in the project  

---

### 8. What is the default scope of a Spring MVC controller?
- **a) Singleton** ✅  
- b) Prototype  
- c) Request  
- d) Session  

---

### 9. Which bean resolves logical view names like `"index"` to actual JSP files?
- a) UrlBasedViewResolver  
- b) ContentNegotiatingViewResolver  
- **c) InternalResourceViewResolver** ✅  
- d) StaticViewResolver  

---

### 10. What is the purpose of the `@PathVariable` annotation in Spring MVC?
- a) To bind query parameters to a method argument  
- b) To inject a request header into a method argument  
- **c) To bind URL template variables to method arguments** ✅  
- d) To map a method directly to a URL  

---

##  goals
### Explain the role of DispatcherServlet, request mapping, and the flow between controllers and views during an interview-style discussion.

### Discuss and identify differences between XML-based and Java-based configuration for DispatcherServlet setup and view resolvers.

### Write a simple @Controller class with a method that maps /hello to a view named welcome.jsp.

### Implement a Java-based configuration that defines an InternalResourceViewResolver pointing to /WEB-INF/views/ and .jsp suffix.

### Answer follow-up questions confidently, justify code decisions, and explain MVC flow using real-world terms.

---

## 15 - RESR using Spring boot

### 1. Which of the following is NOT a valid HTTP method used in REST APIs?
- a) GET  
- b) POST  
- **c) UPDATE** ✅  
- d) DELETE  

---

### 2. Which HTTP method is used to retrieve data from the server?
- a) PUT  
- **b) GET** ✅  
- c) DELETE  
- d) POST  

---

### 3. What is the purpose of the HTTP PUT method?
- a) To delete a resource  
- **b) To update or replace a resource** ✅  
- c) To retrieve a resource  
- d) To create a new resource  

---

### 4. In REST architecture, which of the following is a feature?
- **a) Stateless** ✅  
- b) Stateful  
- c) Server-dependency  
- d) Dynamic routing  

---

### 5. What is the purpose of Postman in REST API development?
- a) To build frontend applications  
- b) To debug backend code  
- **c) To test and interact with REST APIs** ✅  
- d) To host a database  

---

### 6. Which annotation is used to bind a method parameter to a URL template variable in Spring Boot?
- a) @RequestBody  
- **b) @PathVariable** ✅  
- c) @RequestParam  
- d) @RequestHeader  

---

### 7. What is the purpose of the `@RequestBody` annotation in Spring Boot?
- a) To map URL parameters  
- **b) To pass JSON or XML data to a method** ✅  
- c) To define a request's HTTP header  
- d) To define a service endpoint  

---

### 8. Which of the following best describes the difference between `@PathVariable` and `@RequestParam`?
- a) `@PathVariable` maps to query parameters, `@RequestParam` maps to URL segments  
- **b) `@PathVariable` maps to URL segments, `@RequestParam` maps to query parameters** ✅  
- c) Both map to query parameters  
- d) Both map to URL segments  

---

### 9. Which annotation is used to handle HTTP PUT requests in Spring Boot?
- **a) @PutMapping** ✅  
- b) @PatchMapping  
- c) @RequestMapping  
- d) @PostMapping  

---

### 10. Which HTTP library is commonly used in React to make API calls to a Spring Boot backend?
- a) Express.js  
- **b) Axios** ✅  
- c) Lodash  
- d) Mocha  

---

## Your goals
### Explain REST principles and HTTP methods used in Spring Boot.

### Describe how to configure a @RestController and map endpoints using @RequestMapping.

### Code Practice 1: Create a simple @RestController with one GET endpoint returning a greeting message.

### Code Practice 2: Add a POST method that accepts a JSON object and returns a confirmation string.

### Confidently demonstrate the ability to test REST APIs using Postman and interpret results.

---

## 16- spring Data JPA 

### 1. Which pattern does Spring Data JPA follow?
- a) Singleton Pattern  
- **b) Repository Pattern** ✅  
- c) Factory Pattern  
- d) Observer Pattern  

---

### 2. What does the `findAll()` method do in a repository?
- a) Fetches a single record  
- b) Deletes records from the database  
- **c) Retrieves all records associated with the entity** ✅  
- d) Updates an existing record  

---

### 3. What type of data structure is used to hold the return value of `findById()`?
- a) List  
- b) Array  
- **c) Optional** ✅  
- d) Set  

---

### 4. Which annotation is used to specify a custom query in Spring Data JPA?
- **a) @Query** ✅  
- b) @FrameCustomQuery  
- c) @RepoQuery  
- d) @JPAQuery  

---

### 5. Which repository interface allows you to execute stored procedures in Spring JPA?
- a) CrudRepository  
- b) PagingAndSortingRepository  
- **c) JpaRepository** ✅  
- d) TransactionalRepository  

---

### 6. How is pagination supported in Spring Data JPA?
- a) By manually writing SQL for pagination  
- **b) By using the Pageable interface** ✅  
- c) By default in all repository methods  
- d) By limiting the number of entities in the database  

---

### 7. Which dependency is required to use Spring Data JPA with PostgreSQL?
- a) spring-boot-starter-data-mysql  
- b) spring-boot-starter-data-jdbc  
- **c) spring-boot-starter-data-jpa + PostgreSQL driver dependency** ✅  
- d) spring-boot-starter-web  

---

### 8. Which class is used to handle business logic in a Spring Data JPA application?
- a) Repository  
- **b) Service** ✅  
- c) Controller  
- d) Entity  

---

### 9. What is the purpose of the `@GetMapping` annotation in the `RestController`?
- a) To define a POST endpoint  
- **b) To define a GET endpoint** ✅  
- c) To define a DELETE endpoint  
- d) To define a PUT endpoint  

---

## Your goals
### Understand the basic purpose of Spring Data JPA and what problems it solves.

### Be able to define a JPA Entity class and map it to a database table.

### Use a JpaRepository to perform basic CRUD operations.

### Understand how method naming conventions work for query generation.

### Create a custom query using the @Query annotation in a repository interface.

---

## 17.Spring Data Rest

### 1. What does Spring Data REST automatically expose for Spring Data repositories?
- a) SOAP endpoints  
- b) GraphQL endpoints  
- **c) RESTful endpoints** ✅  
- d) FTP endpoints  

---

### 2. Which request method is used to update an entity in Spring Data REST?
- a) GET  
- **b) PUT** ✅  
- c) POST  
- d) DELETE  

---

### 3. How to retrieve HATEOAS links in the response from a Spring Data REST API?
- a) Enable detailed logging  
- **b) Perform a GET request to the resource** ✅  
- c) Include a specific header in the request  
- d) Adjust the database configuration  

---

### 4. What action does `GET /users/{id}` perform in a RESTful API?
- a) Deletes a user  
- b) Retrieves all user details  
- c) Creates a new user  
- - d) Retrieves the details of a specific user** ✅  

---

### 5. What does the `@RequestBody` annotation do in Spring?
- a) Specifies the HTTP method  
- **b) Deserializes the request body into an object** ✅  
- c) Defines the response body  
- d) Marks a class as a request body  

---

### 6. In Spring Data REST, which HTTP method is used to create a new resource?
- a) GET  
- b) DELETE  
- c) PUT  
- - d) POST** ✅  

---

### 7. Which annotation is used to indicate a Spring Data REST repository?
- a) @RestController  
- **b) @RepositoryRestResource** ✅  
- c) @SpringBootApplication  
- d) @Component  

---

### 8. What is the role of HATEOAS links in the API response?
- a) It stores user credentials  
- **b) It provides navigation between related resources** ✅  
- c) It creates new database records  
- d) It manages application configurations  

---

### 9. What does the repository layer in Spring Data REST manage?
- a) User interface components  
- b) Database connections  
- **c) CRUD operations for entities** ✅  
- d) Network communications  

---

### 10. Which type of events can be captured in Spring Data REST using repository events?
- a) Application logs  
- b) Metrics related to database performance  
- **c) BeforeCreate and AfterDelete events** ✅  
- d) Information about user sessions  

---

## Your goals
### Explain what Spring Data REST is and how it helps eliminate boilerplate controller code.

### Describe how to expose a JPA repository as a RESTful endpoint using Spring Data REST annotations.

### Define a simple Product entity with id and name, and create a repository interface that exposes it via Spring Data REST (without writing controller code).

---
## 19. Spring AOP

### 1. What is the main purpose of aspect-oriented programming (AOP)?
- a) To increase code duplication  
- **b) To handle cross-cutting concerns** ✅  
- c) To replace object-oriented programming  
- d) To enforce coding standards  

---

### 2. Which of the following is an example of a cross-cutting concern?
- a) Business logic  
- **b) Logging** ✅  
- c) Database connection  
- d) UI rendering  

---

### 3. What annotation is used to mark a class as an aspect in Spring AOP?
- **a) @Aspect** ✅  
- b) @Component  
- c) @Service  
- d) @Controller  

---

### 4. What is a Join Point in Spring AOP?
- a) The method being executed  
- b) The action taken by an aspect  
- **c) A point during the execution of a program** ✅  
- d) The object being advised  

---

### 5. Which AOP advice runs before a matched method is executed?
- a) @After  
- **b) @Before** ✅  
- c) @AfterThrowing  
- d) @AfterReturning  

---

### 6. What is a Pointcut in AOP?
- a) The module encapsulating cross-cutting concerns  
- b) The action taken at a specific Join Point  
- **c) The expression defining where advice should apply** ✅  
- d) The object being advised  

---

### 7. What does `@AfterThrowing` advice do?
- a) Executes before the method  
- **b) Executes after a method throws an exception** ✅  
- c) Executes after the method successfully returns  
- d) Logs method execution time  

---

### 8. Which of these annotations is used for performance monitoring in AOP?
- a) @Before  
- b) @After  
- c) @AfterReturning  
- - d) @Around** ✅  

---

### 9. What does the `args(postId)` expression in a Pointcut signify?
- a) The method name  
- **b) The arguments passed to the method** ✅  
- c) The return type of the method  
- d) The target class of the method  

---

### 10. What is an AOP Proxy in Spring?
- a) A module that handles cross-cutting concerns  
- **b) A dynamically created object that represents the target** ✅  
- c) A point during the execution of a program  
- d) An annotation for advice  

---

## Your goals
### Explain the core concept of AOP and its use cases in Spring Boot projects.

### List and describe the purpose of Before, After, and Around advice in AOP.

### Write a basic @Aspect class with a @Before advice that logs any method in a UserService class.

### Explain what a JoinPoint is and how it's used inside advice methods.

---
## 20. Spring Security 

### 1. What is the primary purpose of Spring Security?
- a) Database management  
- b) Managing HTTP requests  
- **c) Securing applications** ✅  
- d) Optimizing performance  

---

### 2. What annotation is used to enable Spring Security in a Spring Boot application?
- **a) @EnableWebSecurity** ✅  
- b) @EnableSpringSecurity  
- c) @EnableSecurity  
- d) @EnableAuthentication  

---

### 3. By default, Spring Security secures all endpoints of a Spring Boot application:
- **a) true** ✅  
- b) false  

---

### 4. Which class is commonly extended to customize Spring Security configuration before Spring Security 6?
- a) SecurityFilterChain  
- **b) WebSecurityConfigurerAdapter** ✅  
- c) AuthenticationManager  
- d) SecurityContextHolder  

---

### 5. Which component is responsible for authenticating user credentials in Spring Security?
- a) SecurityFilterChain  
- **b) AuthenticationProvider** ✅  
- c) UserDetailsService  
- d) AccessDecisionManager  

---

### 6. What is the default encryption algorithm used by Spring Security for passwords in Spring Boot 5+?
- a) MD5  
- b) SHA-1  
- **c) BCrypt** ✅  
- d) PBKDF2  

---

### 7. What is the purpose of `@PreAuthorize` in Spring Security?
- a) To validate input parameters  
- **b) To define method-level security** ✅  
- c) To authenticate users  
- d) To encode passwords  

---

### 8. Which of these is used to configure role-based access control in Spring Security?
- a) @EnableRoles  
- **b) hasRole('ROLE_NAME')** ✅  
- c) EnableWebSecurity  
- d) PasswordEncoder  

---

### 9. What is the purpose of `SecurityContextHolder` in Spring Security?
- a) To manage password encryption  
- **b) To store authentication details** ✅  
- c) To configure authorization rules  
- d) To create user sessions  

---

### 10. Which interface in Spring Security defines the contract for user authentication?
- **a) Authentication** ✅  
- b) UserDetailsService  
- c) GrantedAuthority  
- d) AccessDecisionManager  

---

### 11. In Spring Security, how is a user authenticated by default?
- a) Using a predefined username and password  
- b) Using an external service  
- **c) Using the UserDetailsService interface** ✅  
- d) Using a database connection  

---

### 12. What does the `csrf()` method in the Spring Security configuration do?
- **a) Enables Cross-Site Request Forgery protection** ✅  
- b) Disables Cross-Site Scripting  
- c) Configures request validation  
- d) Manages cookie settings  

---

### 13. Which filter is responsible for handling username and password-based authentication in Spring Security?
- a) JwtAuthenticationFilter  
- **b) UsernamePasswordAuthenticationFilter** ✅  
- c) BasicAuthenticationFilter  
- d) CsrfFilter  

---

### 14. What is the primary purpose of the `BCryptPasswordEncoder` in Spring Security?
- a) To decode passwords for verification  
- b) To encrypt user data  
- **c) To hash passwords securely** ✅  
- d) To store passwords in plain text  

---

### 15. Which annotation is used to enable method-level security with Spring Security?
- **a) @EnableMethodSecurity** ✅  
- b) @EnableWebSecurity  
- c) @EnableAuthorization  
- d) @EnableSecurity  

---

## Your goals
### Explain the default login form behavior in Spring Security and how to customize it.

### Demonstrate how CSRF protection works and what issues may arise without it.

### Implement a short snippet to define a User entity and expose it through a repository.

### Show how to hash passwords using BCrypt and configure a PasswordEncoder.

### Describe the responsibilities of UserDetailsService and AuthenticationProvider.

---

## 22. JWT(JSON WEB TOKEN) and OAuth2

### 1. What algorithm is commonly used to sign a JWT?
- a) AES  
- b) RSA  
- c) SHA-256  
- - d) HMAC-SHA256** ✅  

---

### 2. What type of information is typically stored in the payload of a JWT?
- a) Encrypted data  
- b) Authentication keys  
- **c) Claims like user roles or expiration time** ✅  
- d) Application settings  

---

### 3. Which header parameter specifies the algorithm used to sign a JWT?
- **a) alg** ✅  
- b) typ  
- c) sig  
- d) enc  

---

### 4. What does the `exp` claim in a JWT indicate?
- a) The time when the JWT was issued  
- **b) The expiration time of the JWT** ✅  
- c) The user role  
- d) The algorithm used for signing  

---

### 5. How can a JWT be verified on the server side?
- a) By decoding the payload directly  
- **b) By comparing the signature using a secret key** ✅  
- c) By matching it with a stored JWT in the database  
- d) By using a hashing function on the header  

---

### 6. Which claim is used to specify the issuer of a JWT?
- **a) iss** ✅  
- b) sub  
- c) aud  
- d) exp  

---

### 7. What is a key advantage of using JWT for authentication?
- a) It does not require a server  
- **b) It is lightweight and stateless** ✅  
- c) It is encrypted by default  
- d) It eliminates the need for tokens  

---

### 8. What is the primary purpose of OAuth?
- a) Encrypting user data  
- **b) Delegating access without sharing credentials** ✅  
- c) Securing databases  
- d) Encrypting network communications  

---

### 9. What is the typical expiration time of a JWT access token?
- a) 24 hours  
- b) Unlimited  
- **c) Configurable by the server** ✅  
- d) 1 hour  

---

### 10. What is the purpose of the "refresh token" in OAuth?
- **a) To extend the expiration time of an access token** ✅  
- b) To store user session data  
- c) To replace the need for an access token  
- d) A refresh token for renewing sessions  

---

### 11. In OAuth, who issues the `access_token`?
- a) Resource Server  
- b) Client Application  
- **c) Authorization Server** ✅  
- d) User Agent  

---

### 12. What is the purpose of the `scope` parameter in OAuth?
- a) To specify the token expiration time  
- **b) To define the level of access requested** ✅  
- c) To verify the client's identity  
- d) To encrypt the token  

---

### 13. What does a JSON Web Token (JWT) consist of?
- a) Header and Payload  
- **b) Header, Payload, and Signature** ✅  
- c) Payload and Signature  
- d) Header and Signature  

---

### 14. Which part of a JWT is used to verify that the token has not been tampered with?
- a) Header  
- b) Payload  
- **c) Signature** ✅  
- d) Scope  

---

## Your goals
### Explain how JWT works and why it’s used over session-based authentication.

### Demonstrate the structure and purpose of JWT header, payload, and signature.

### Implement a basic JWT generation logic using io.jsonwebtoken (JJWT or similar).

### Describe how a OncePerRequestFilter is used to validate and extract JWT from headers.

### Show understanding of OAuth2 login flow using Google or GitHub.

---
## 23. Docker

### 1. What is Docker primarily used for?
- a) Network Virtualization  
- **b) Containerization** ✅  
- c) Cloud Storage  
- d) Virtual Machine Management  

---

### 2. Where can images of different software be found in the Docker ecosystem?
- a) Docker Engine  
- b) Docker Volume  
- c) Docker Compose  
- - d) Docker Hub** ✅  

---

### 3. What is the purpose of Docker Volumes in the context of Docker?
- a) To store Docker images  
- b) To manage the Docker Engine  
- **c) To store data permanently** ✅  
- d) Containerization of applications  

---

### 4. What does the "Pull" option do in Docker when searching for images?
- a) Execute the image  
- **b) Download the image** ✅  
- c) Run the container  
- d) Share the image with others  

---

### 5. What does the command `docker run image-name` do?
- a) Display the image details  
- b) Download the image  
- **c) Run the image as a container** ✅  
- d) Remove the image from the system  

---

### 6. What information does the command `docker ps` provide?
- a) List of all containers  
- b) List of completed containers  
- **c) List of currently running containers** ✅  
- d) Details of Docker images  

---

### 7. What is the purpose of the command `docker rm containerId`?
- a) Remove the Docker image  
- **b) Delete the container based on its ID** ✅  
- c) Run the container  
- d) Download the container  

---

### 8. Which tool is used to create multiple containers for an application in Docker?
- a) Docker Engine  
- **b) Docker Compose** ✅  
- c) Docker Hub  
- d) Docker File  

---

### 9. What issue does Docker address?
- **a) Inconsistencies in development environments** ✅  
- b) Lack of scalability in server deployment  
- c) Slow internet connection  
- d) Software licensing conflicts  

---

### 10. Which analogy best describes the relationship between a Docker image and a Docker container?
- a) Book and Library  
- **b) Template and instance** ✅  
- c) None of the above  
- d) Object and Blueprint  

---

## Your goals
### Explain the problem Docker solves compared to traditional virtualization.

### Describe how to run a simple Spring Boot app inside a Docker container.

### Demonstrate basic Docker commands like docker build, docker run, and docker ps.

### Understand and briefly explain the structure of a Dockerfile.

### Use Docker Compose to define and run a multi-container setup (Spring Boot + Postgres).

---

## 24. Cloud Deployment

### 1. What is the primary benefit of deploying a Spring Boot application to the cloud?
- a) Reduced development time  
- b) Automatic code generation  
- **c) Scalability and high availability** ✅  
- d) Faster compilation speed  

---

### 2. Which AWS service is specifically designed for easy deployment of web applications without managing underlying infrastructure?
- a) Amazon EC2  
- **b) AWS Elastic Beanstalk** ✅  
- c) Amazon S3  
- d) AWS Lambda  

---

### 3. When deploying a Spring Boot application with a database to AWS, which service combination is most appropriate?
- a) EC2 + DynamoDB  
- b) Lambda + Aurora  
- **c) Elastic Beanstalk + RDS** ✅  
- d) ECS + S3  

---

### 4. What does AWS RDS primarily provide?
- a) Container orchestration  
- **b) Managed relational database service** ✅  
- c) Static file hosting  
- d) Load balancing  

---

### 5. Which AWS service is used to store and manage Docker container images?
- a) AWS ECS  
- **b) AWS ECR** ✅  
- c) AWS EKS  
- d) AWS Fargate  

---

### 6. In AWS ECS, what is a Task Definition?
- a) A running instance of a container  
- **b) A blueprint that describes how containers should run** ✅  
- c) A cluster of EC2 instances  
- d) A load balancer configuration  

---

### 7. What is the purpose of AWS CLI in Spring Boot cloud deployment?
- a) To write Spring Boot code  
- b) To compile Java applications  
- **c) To interact with AWS services from command line** ✅  
- d) To debug applications  

---

### 8. When pushing a Docker image to AWS ECR, which authentication method is typically required?
- a) Username and password  
- **b) AWS CLI configured with proper IAM permissions** ✅  
- c) SSH keys  
- d) API Gateway tokens  

---

### 9. What file is typically used to define environment-specific configurations in Spring Boot for cloud deployment?
- a) pom.xml  
- **b) application.properties or application.yml** ✅  
- c) web.xml  
- d) Dockerfile  

---

### 10. In AWS ECS, what is the difference between EC2 Launch Type and Fargate Launch Type?
- a) EC2 is for containers, Fargate is for virtual machines  
- **b) EC2 requires server management, Fargate is serverless** ✅  
- c) EC2 is cheaper, Fargate is more expensive  
- d) EC2 supports Java, Fargate supports only Python  

---

## Your goals
### Explain the purpose of Elastic Beanstalk and ECS in simple terms.

### Describe the steps to deploy a Spring Boot app using AWS Elastic Beanstalk.

### Use AWS CLI to configure credentials and deploy a Docker image.

### Understand how ECR works for storing Docker images.

### Successfully demonstrate basic cloud deployment steps using simple CLI or YAML commands.

---

## 25. Microservices

---

### 1. Which dependency is required for implementing an API Gateway in a Spring Boot microservices architecture?
- a) Spring Boot Starter Security  
- **b) Spring Cloud Gateway** ✅  
- c) Spring Boot Starter Actuator  
- d) Spring Boot DevTools  

---

### 2. Which of the following is a Spring Boot tool for simplifying HTTP-based inter-service communication?
- **a) Feign Client** ✅  
- b) RabbitMQ  
- c) Redis  
- d) JDBC  

---

### 3. How does Spring Cloud Config Server help in microservices?
- a) Caches microservice data  
- **b) Manages and externalizes configuration for distributed services** ✅  
- c) Handles user authentication  
- d) Provides service discovery  

---

### 4. What is the role of Hystrix in Spring Boot microservices?
- a) Service discovery  
- b) API Gateway routing  
- **c) Circuit breaker for fault tolerance** ✅  
- d) Monitoring service health  

---

### 5. Which of these is NOT a recommended communication method for Spring Boot microservices?
- a) REST APIs  
- b) Feign Clients  
- **c) JDBC direct queries** ✅  
- d) Message queues like RabbitMQ or Kafka  

---

### 6. What is the primary benefit of using circuit breakers in microservices?
- a) Speeding up database queries  
- **b) Preventing cascading failures when a service is down** ✅  
- c) Improving UI responsiveness  
- d) Reducing the number of services needed  

---

### 7. Which tool in Spring Boot helps monitor the health of microservices?
- a) Spring Boot DevTools  
- **b) Spring Boot Actuator** ✅  
- c) Spring Boot CLI  
- d) Spring Boot Validator  

---

### 8. What does Spring Boot's embedded Tomcat server provide in a microservices context?
- a) Scalability  
- **b) Independent deployment capability** ✅  
- c) Centralized service registry  
- d) Database management  

---

### 9. In a Spring Boot microservices project, what is used to run multiple instances of a service?
- a) Adding more controllers  
- **b) Running the same service on different ports** ✅  
- c) Configuring a single instance in the `application.properties` file  
- d) Using shared session storage  

---

### 10. What is the purpose of using `@EnableEurekaServer` in a Spring Boot application?
- a) To enable load balancing  
- **b) To set up a Eureka server for service registration and discovery** ✅  
- c) To configure an embedded database  
- d) To secure REST endpoints  

---

## Your goals
### Understand the core concept of microservices and how they differ from monolithic architecture.

### Show a simple Spring Boot annotation-based config to register a service with Eureka.

### Use Spring Cloud components like Eureka for service discovery and Feign for inter-service communication.

### Implement basic load balancing and understand the role of Spring Cloud Gateway in routing.

### Be able to create and run multiple Spring Boot services communicating with each other.

---

## 27.git

### 1. What type of Version Control System is Git?
- a) Local Version Control System  
- b) Centralized Version Control System (CVCS)  
- **c) Distributed Version Control System (DVCS)** ✅  
- d) Bitbucket  

---

### 2. Which Git command creates an empty local repository in a directory?
- a) git start  
- **b) git init** ✅  
- c) git create  
- d) git new  

---

### 3. What is the primary purpose of the 'git clone' command in Git?
- a) To create a new branch  
- b) To merge branches  
- **c) To copy a remote repository to your local machine** ✅  
- d) To switch between branches  

---

### 4. What is the purpose of the 'git status' command?
- a) To initialize a new Git repository  
- b) To view the history of commits  
- **c) To see the current status of your working directory and staged changes** ✅  
- d) To create a new branch  

---

### 5. What do you mean by an "untracked file" in Git?
- a) A file that has been committed to the repository  
- b) A file that is currently staged  
- **c) A file that is in the working directory but not yet tracked by Git and has not been committed** ✅  
- d) A file that Git ignores  

---

### 6. How do you perform a commit in Git?
- a) git commit -a  
- b) git commit -message "Commit message"  
- **c) git commit -m "Commit message"** ✅  
- d) git save  

---

### 7. How do you connect your local Git repository to a remote repository?
- a) git link <remote-url>  
- **b) git remote add origin <remote-url>** ✅  
- c) git connect <remote-url>  
- d) git attach <remote-url>  

---

### 8. What happens if you try to switch branches in Git without committing the changes in your current branch?
- a) Git automatically commits the changes  
- b) Git asks you to discard the changes  
- **c) Git primarily warns the user and suggests appropriate actions (stashing or committing)** ✅  
- d) Git creates a new branch with the changes  

---

### 9. In Git, what does "origin" refer to?
- a) The latest commit in the repository  
- b) The current branch  
- c) The local Git configuration file  
- - d) The default name for a remote repository** ✅  

---

## Your goals
### Understand the core purpose of Git and how it enables version control and collaboration.

### Know how to initialize a repository, make commits, and track file changes using Git.

### Demonstrate the use of git branch, merge, and rebase, and explain when to use which.

### Simulate pushing to and pulling from a remote repository (e.g., GitHub).

### Resolve a simple merge conflict or explain the process with confidence

--- 

## 28. DSA (Optiona)

### 1. What defines a full - b)inary tree?

- a) Every node has only one - c)hild
- b)) Every node has at most one - c)hild
- c)) All leaf nodes are at the same level
- d) Every node has 0 or 2 - c)hildren ✅

---

### 2. What traversal visits the left su- b)tree, then root, then right su- b)tree in a - b)inary tree?

- a) Pre-order traversal
- b)) Post-order traversal
- c)) Level-order traversal
- d) In-order traversal ✅

---

### 3. What is the time - c)omplexity to insert a node at the - b)eginning of a singly linked list?

- a) O(n)
- b)) O(log n)
- c)) O(n²)
- d) O(1) ✅

---

### 4. Whi- c)h data stru- c)ture is - b)est for implementing undo fun- c)tionality?

- a) Queue
- b)) Array
- c)) Linked list
- d) Sta- c)k ✅

---

### 5. In a - c)ir- c)ular queue, what happens when rear rea- c)hes the end - b)ut spa- c)e is availa- b)le at the front?

- a) Queue overflows
- b)) Queue underflows
- c)) Insert fails
- d) Rear wraps around to the front ✅

---

### 6. What is the - c)orre- c)t time - c)omplexity to sear- c)h for a value in a - b)inary Sear- c)h Tree (average - c)ase)?

- a) O(n²)
- b)) O(n)
- c)) O(n log n)
- d) O(log n) ✅

---

### 7. What happens when you pop from an empty sta- c)k?

- a) The first element is returned
- b)) Nothing happens
- c)) A new element is added
- d) Sta- c)kUnderflow error o- c)- c)urs ✅

---

### 8. Whi- c)h operation adds an element to the top of the sta- c)k?

- a) pop()
- b)) peek()
- c)) remove()
- d) push() ✅

---

### 9. What does a sta- c)k follow?

- a) FIFO (First In First Out)
- b)) LILO (Last In Last Out)
- c)) Random Order
- d) LIFO (Last In First Out) ✅

---

### 10. In re- c)ursion, what does the - b)ase - c)ase represent?

- a) The first re- c)ursive - c)all
- b)) The re- c)ursive - c)all that - c)reates new nodes
- c)) The final re- c)ursive - c)all that - c)alls itself indefinitely
- d) The terminating - c)ondition to stop re- c)ursion ✅

---

### 11. Whi- c)h sorting algorithm follows the divide-and-- c)onquer approa- c)h?

- a) - b)u- b)- b)le Sort
- b)) Insertion Sort
- c)) Sele- c)tion Sort
- d) Merge Sort ✅

---

### 12. What is the worst-- c)ase time - c)omplexity of Insertion Sort?

- a) O(log n)
- b)) O(n log n)
- c)) O(n)
- d) O(n²) ✅

---

### 13. Whi- c)h sorting algorithm finds the smallest element and pla- c)es it at the - b)eginning in ea- c)h iteration?

- a) - b)u- b)- b)le Sort
- b)) Insertion Sort
- c)) Qui- c)k Sort
- d) Sele- c)tion Sort ✅

---

### 14. Whi- c)h sorting algorithm repeatedly - c)ompares adja- c)ent elements and swaps them if needed?

- a) Sele- c)tion Sort
- b)) Merge Sort
- c)) Insertion Sort
- d) - b)u- b)- b)le Sort ✅

---

### 15. What is the time - c)omplexity of a- c)- c)essing an element in an array - b)y index?

- a) O(n)
- b)) O(log n)
- c)) O(n²)
- d) O(1) ✅



