# Java Learning Roadmap

## Phase 1: Java Fundamentals (2-3 months)

### Basic Syntax & Concepts
- Variables, data types, operators
- Control flow (if-else, switch, loops)
- Arrays and Strings
- Methods and parameter passing

**Interview Questions:**
1. What is the difference between JDK, JRE, and JVM?
2. Explain the difference between primitive and reference types.
3. What is the difference between == and equals()?
4. How does String pool work in Java?
5. What is the difference between break and continue?
6. Explain pass-by-value in Java.
7. What is the difference between final, finally, and finalize?
8. How does switch statement work with String?
9. What is autoboxing and unboxing?
10. Explain the difference between ++i and i++.

### Object-Oriented Programming
- Classes and Objects
- Constructors and initialization blocks
- Encapsulation, Inheritance, Polymorphism
- Abstraction (abstract classes and interfaces)
- Access modifiers

**Interview Questions:**
1. What are the four pillars of OOP?
2. Explain the difference between abstract class and interface.
3. What is method overloading vs method overriding?
4. Can we override static methods?
5. What is the purpose of the super keyword?
6. Explain constructor chaining.
7. What is the difference between composition and inheritance?
8. Can we have multiple inheritance in Java?
9. What is the diamond problem?
10. Explain the use of this keyword.

### Exception Handling
- try-catch-finally blocks
- Checked vs unchecked exceptions
- Custom exceptions
- throw vs throws

**Interview Questions:**
1. What is the difference between checked and unchecked exceptions?
2. Explain the exception hierarchy in Java.
3. What is the difference between throw and throws?
4. Can we have try without catch?
5. What happens if an exception occurs in finally block?
6. What is try-with-resources?
7. When should you create custom exceptions?
8. What is the difference between Error and Exception?
9. Can we catch multiple exceptions in one catch block?
10. What is exception propagation?

## Phase 2: Core Java (3-4 months)

### Collections Framework
- List (ArrayList, LinkedList)
- Set (HashSet, TreeSet, LinkedHashSet)
- Map (HashMap, TreeMap, LinkedHashMap)
- Queue and Deque
- Comparable vs Comparator

**Interview Questions:**
1. What is the difference between ArrayList and LinkedList?
2. How does HashMap work internally?
3. What is the difference between HashMap and Hashtable?
4. Explain the difference between HashSet and TreeSet.
5. What is the load factor in HashMap?
6. How does ConcurrentHashMap work?
7. What is the difference between Comparable and Comparator?
8. Explain fail-fast vs fail-safe iterators.
9. What is the difference between Collection and Collections?
10. How do you synchronize a collection?

### Generics
- Generic classes and methods
- Bounded type parameters
- Wildcards (?, extends, super)
- Type erasure

**Interview Questions:**
1. What are generics and why use them?
2. Explain type erasure in Java.
3. What is the difference between <?> and <? extends Object>?
4. What is PECS (Producer Extends Consumer Super)?
5. Can we create generic arrays?
6. What are bounded type parameters?
7. What is the difference between <T extends Comparable> and <T super Comparable>?
8. Can we use primitives with generics?
9. What is a raw type?
10. Explain bridge methods in generics.

### Multithreading & Concurrency
- Thread creation (Thread class, Runnable interface)
- Thread lifecycle and states
- Synchronization and locks
- wait(), notify(), notifyAll()
- Executor framework
- Concurrent collections

**Interview Questions:**
1. What is the difference between Thread and Runnable?
2. Explain the thread lifecycle in Java.
3. What is the difference between synchronized method and block?
4. What is deadlock and how to prevent it?
5. Explain the difference between wait() and sleep().
6. What is the volatile keyword?
7. What is ThreadLocal?
8. Explain the Executor framework.
9. What is the difference between Callable and Runnable?
10. What is a race condition?

### Java 8+ Features
- Lambda expressions
- Functional interfaces
- Stream API
- Optional class
- Method references
- Default and static methods in interfaces

**Interview Questions:**
1. What are lambda expressions?
2. What is a functional interface?
3. Explain the Stream API and its benefits.
4. What is the difference between map() and flatMap()?
5. What is Optional and why use it?
6. Explain method references with examples.
7. What are default methods in interfaces?
8. What is the difference between intermediate and terminal operations?
9. How does forEach() differ from for loop?
10. What is the difference between Predicate, Function, and Consumer?

## Phase 3: Advanced Java (2-3 months)

### I/O & Serialization
- File I/O (FileReader, FileWriter, BufferedReader)
- Byte streams vs character streams
- Serialization and deserialization
- NIO (New I/O)

**Interview Questions:**
1. What is the difference between InputStream and Reader?
2. Explain serialization in Java.
3. What is the purpose of serialVersionUID?
4. What is transient keyword?
5. What is the difference between Serializable and Externalizable?
6. Explain NIO vs traditional I/O.
7. What are channels and buffers in NIO?
8. How do you read a file line by line efficiently?
9. What is the difference between FileInputStream and FileReader?
10. Can we serialize static variables?

### JDBC & Database Connectivity
- JDBC drivers and connection
- Statement, PreparedStatement, CallableStatement
- ResultSet and metadata
- Transaction management
- Connection pooling

**Interview Questions:**
1. What are the types of JDBC drivers?
2. What is the difference between Statement and PreparedStatement?
3. How do you prevent SQL injection?
4. What is a ResultSet and its types?
5. Explain JDBC transaction management.
6. What is connection pooling?
7. What is the difference between execute(), executeQuery(), and executeUpdate()?
8. How do you handle database exceptions?
9. What is batch processing in JDBC?
10. Explain the DAO pattern.

### Design Patterns
- Singleton, Factory, Builder
- Observer, Strategy, Decorator
- MVC, DAO, DTO patterns
- Dependency Injection

**Interview Questions:**
1. What is the Singleton pattern and how to implement it?
2. Explain the Factory pattern with an example.
3. What is the difference between Factory and Abstract Factory?
4. Explain the Builder pattern.
5. What is the Observer pattern?
6. Explain the Strategy pattern.
7. What is Dependency Injection?
8. What is the difference between DAO and DTO?
9. Explain the Decorator pattern.
10. What is the MVC pattern?

### Reflection & Annotations
- Class, Method, Field reflection
- Creating custom annotations
- Annotation processing
- Dynamic proxy

**Interview Questions:**
1. What is reflection in Java?
2. How do you get Class object in Java?
3. What are the use cases of reflection?
4. What are annotations?
5. What is the difference between @Override and @Deprecated?
6. How do you create custom annotations?
7. What is retention policy in annotations?
8. What are the disadvantages of reflection?
9. How does Spring use reflection?
10. What is a dynamic proxy?

## Phase 4: Enterprise Java (3-4 months)

### Spring Framework
- Spring Core (IoC, DI)
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- RESTful web services

**Interview Questions:**
1. What is Inversion of Control (IoC)?
2. Explain Dependency Injection in Spring.
3. What is the difference between @Component, @Service, and @Repository?
4. What is Spring Boot and its advantages?
5. Explain the Spring Bean lifecycle.
6. What is @Autowired and its types?
7. What is the difference between @Controller and @RestController?
8. How does Spring Security work?
9. What is Spring Data JPA?
10. Explain the difference between @RequestParam and @PathVariable.

### Hibernate & JPA
- ORM concepts
- Entity mapping
- Relationships (OneToOne, OneToMany, ManyToMany)
- HQL and Criteria API
- Caching strategies

**Interview Questions:**
1. What is ORM and why use it?
2. What is the difference between JPA and Hibernate?
3. Explain the Hibernate architecture.
4. What is the difference between save() and persist()?
5. What are the states of a Hibernate entity?
6. Explain lazy loading vs eager loading.
7. What is the N+1 problem?
8. What is the difference between first-level and second-level cache?
9. What is HQL?
10. Explain cascade types in Hibernate.

### Microservices
- Microservices architecture
- Spring Cloud
- Service discovery (Eureka)
- API Gateway
- Circuit breaker (Resilience4j)
- Message queues (RabbitMQ, Kafka)

**Interview Questions:**
1. What are microservices?
2. What is the difference between monolithic and microservices architecture?
3. What is service discovery?
4. Explain the API Gateway pattern.
5. What is a circuit breaker?
6. How do microservices communicate?
7. What is Spring Cloud?
8. Explain the saga pattern.
9. What is eventual consistency?
10. How do you handle distributed transactions?

### Testing
- JUnit and TestNG
- Mockito for mocking
- Integration testing
- Test-driven development (TDD)

**Interview Questions:**
1. What is the difference between JUnit and TestNG?
2. What are the JUnit annotations?
3. What is mocking and why use Mockito?
4. What is the difference between @Mock and @InjectMocks?
5. What is test-driven development?
6. What is the difference between unit and integration testing?
7. How do you test private methods?
8. What is code coverage?
9. Explain the AAA pattern in testing.
10. What is the difference between @Before and @BeforeClass?

## Phase 5: Advanced Topics (2-3 months)

### Performance & Optimization
- JVM internals and memory management
- Garbage collection algorithms
- Profiling and monitoring tools
- Performance tuning

**Interview Questions:**
1. Explain the JVM memory structure.
2. What are the different garbage collectors?
3. What is the difference between heap and stack memory?
4. Explain the G1 garbage collector.
5. What is memory leak and how to detect it?
6. What are JVM tuning parameters?
7. What is the difference between minor and major GC?
8. How does the JIT compiler work?
9. What are weak, soft, and phantom references?
10. Explain the metaspace in Java 8+.

### Security
- Authentication and authorization
- JWT tokens
- OAuth 2.0
- Encryption and hashing
- Secure coding practices

**Interview Questions:**
1. What is the difference between authentication and authorization?
2. How does JWT work?
3. What is OAuth 2.0?
4. Explain different OAuth grant types.
5. What is the difference between encryption and hashing?
6. What is HTTPS and how does it work?
7. What is CSRF and how to prevent it?
8. What is XSS attack?
9. How do you store passwords securely?
10. What is the principle of least privilege?

### Build Tools & DevOps
- Maven and Gradle
- Docker containerization
- CI/CD pipelines
- Jenkins
- Git version control

**Interview Questions:**
1. What is Maven and its lifecycle?
2. What is the difference between Maven and Gradle?
3. What is a Docker container?
4. What is the difference between Docker image and container?
5. What is CI/CD?
6. Explain the Jenkins pipeline.
7. What is the difference between Git merge and rebase?
8. What is a Dockerfile?
9. What is Docker Compose?
10. Explain the concept of infrastructure as code.

### Cloud & Distributed Systems
- AWS/Azure/GCP basics
- Distributed caching (Redis)
- Message brokers (Kafka, RabbitMQ)
- Containerization and orchestration (Kubernetes)

**Interview Questions:**
1. What is cloud computing?
2. What are the types of cloud services (IaaS, PaaS, SaaS)?
3. What is Redis and its use cases?
4. What is the difference between Kafka and RabbitMQ?
5. What is Kubernetes?
6. Explain pods and services in Kubernetes.
7. What is horizontal vs vertical scaling?
8. What is a load balancer?
9. What is CAP theorem?
10. Explain eventual consistency vs strong consistency.

## Learning Resources

### Books
- "Effective Java" by Joshua Bloch
- "Java Concurrency in Practice" by Brian Goetz
- "Head First Design Patterns"
- "Spring in Action" by Craig Walls

### Online Platforms
- Oracle Java Documentation
- Baeldung
- JavaBrains
- Udemy, Coursera, Pluralsight

### Practice
- LeetCode, HackerRank
- Build real-world projects
- Contribute to open-source
- Code reviews and pair programming

## Career Path

### Junior Java Developer (0-2 years)
- Core Java fundamentals
- Basic Spring/Spring Boot
- Database basics
- Version control

### Mid-Level Java Developer (2-5 years)
- Advanced Spring ecosystem
- Microservices architecture
- Design patterns
- Testing and debugging

### Senior Java Developer (5+ years)
- System design and architecture
- Performance optimization
- Team leadership
- Cloud and DevOps

### Java Architect (8+ years)
- Enterprise architecture
- Technology strategy
- Cross-functional leadership
- Innovation and best practices
