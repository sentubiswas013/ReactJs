# ✅ Backend (Java / Spring Boot)

## 1. What is Dependency Injection (DI)?

**Answer:**
Dependency Injection is a design pattern where objects receive their dependencies from external sources rather than creating them internally. In Spring Boot, the framework automatically injects required dependencies into your classes, promoting loose coupling and easier testing.

**Example:**
```java
@Service
public class UserService {
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository; // Dependency injected
    }
}
```

---

## 2. How do you resolve Dependency Injection ambiguity in Spring Boot?

**Answer:**
When multiple beans of the same type exist, Spring doesn't know which one to inject. You can resolve this using @Primary, @Qualifier, or by naming the bean specifically.

**Example:**
```java
@Service
@Qualifier("emailService")
public class EmailNotificationService implements NotificationService { }

@Service
@Qualifier("smsService")
public class SmsNotificationService implements NotificationService { }

@Component
public class NotificationManager {
    @Autowired
    @Qualifier("emailService")
    private NotificationService notificationService;
}
```

---

## 3. What is Core Java and Advanced Java?

**Answer:**
Core Java covers fundamental concepts like OOP, collections, exception handling, multithreading, and basic I/O. Advanced Java includes enterprise technologies like JDBC, Servlets, JSP, Spring Framework, Hibernate, and web services used for building large-scale applications.

**Example:**
```java
// Core Java - Basic collection usage
List<String> names = new ArrayList<>();
names.add("John");

// Advanced Java - JDBC connection
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/db", "user", "pass"
);
```

---

## 4. What is Testing in Java?

**Answer:**
Testing in Java involves verifying that your code works correctly. Unit testing tests individual methods or classes in isolation, integration testing checks how components work together, and end-to-end testing validates the entire application flow.

**Example:**
```java
@Test
public void testAddition() {
    Calculator calc = new Calculator();
    int result = calc.add(5, 3);
    assertEquals(8, result);
}
```

---

## 5. What is Mockito?

**Answer:**
Mockito is a Java testing framework that allows you to create mock objects for testing. It helps isolate the class under test by simulating the behavior of dependencies without needing actual implementations.

**Example:**
```java
@Test
public void testUserService() {
    UserRepository mockRepo = Mockito.mock(UserRepository.class);
    when(mockRepo.findById(1)).thenReturn(new User("John"));
    
    UserService service = new UserService(mockRepo);
    User user = service.getUser(1);
    
    assertEquals("John", user.getName());
}
```

---

## 6. Can you write unit test cases using Spring Boot?

**Answer:**
Yes, Spring Boot provides excellent testing support with @SpringBootTest for integration tests and @WebMvcTest for controller testing. You can use JUnit and Mockito together with Spring's testing annotations.

**Example:**
```java
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    
    @MockBean
    private UserRepository userRepository;
    
    @Test
    public void testGetUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User("Alice")));
        User user = userService.getUserById(1L);
        assertNotNull(user);
    }
}
```

---

## 7. What is exception handling in Java?

**Answer:**
Exception handling is a mechanism to handle runtime errors gracefully, preventing application crashes. Java uses try-catch blocks to catch exceptions, and you can throw custom exceptions for specific business logic errors.

**Example:**
```java
public void readFile(String path) {
    try {
        FileReader file = new FileReader(path);
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    } catch (IOException e) {
        System.out.println("IO error: " + e.getMessage());
    }
}
```

---

## 8. How does try-catch-finally work?

**Answer:**
The try block contains code that might throw an exception. The catch block handles specific exceptions if they occur. The finally block always executes regardless of whether an exception occurred, typically used for cleanup operations like closing resources.

**Example:**
```java
public void processData() {
    Connection conn = null;
    try {
        conn = getConnection();
        // Process data
    } catch (SQLException e) {
        System.out.println("Database error: " + e.getMessage());
    } finally {
        if (conn != null) {
            conn.close(); // Always executes
        }
    }
}
```

---

## 9. What are SOLID principles?

**Answer:**
SOLID is an acronym for five design principles that make software more maintainable and scalable:
- **S**ingle Responsibility: A class should have one reason to change
- **O**pen/Closed: Open for extension, closed for modification
- **L**iskov Substitution: Subtypes must be substitutable for their base types
- **I**nterface Segregation: Many specific interfaces are better than one general interface
- **D**ependency Inversion: Depend on abstractions, not concrete implementations

**Example:**
```java
// Single Responsibility Principle
public class UserService {
    public void createUser(User user) { /* only user logic */ }
}

public class EmailService {
    public void sendEmail(String to, String message) { /* only email logic */ }
}

// Dependency Inversion Principle
public interface PaymentProcessor {
    void processPayment(double amount);
}

public class PayPalProcessor implements PaymentProcessor {
    public void processPayment(double amount) { /* PayPal logic */ }
}
```

## 10. What are functional interfaces in Java 8?

**Answer:**
A **functional interface** in Java is an interface that contains **exactly one abstract method**.
It is mainly used for **lambda expressions** and introduced in **Java 8**.

Common examples:

* `Runnable`
* `Callable`
* `Comparator`
* `Function`, `Predicate`, `Supplier`, `Consumer`

**Example:**
```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}

// Usage with lambda
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(add.calculate(5, 3));      // 8
System.out.println(multiply.calculate(5, 3)); // 15
```

---

## 11. What is the difference between lambda expressions and anonymous inner classes?

**Answer:**
Lambda expressions are more concise and can only be used with functional interfaces, while anonymous inner classes can implement any interface or extend any class. Lambdas don't create a new scope (this refers to enclosing class), whereas anonymous classes do. Lambdas are more memory efficient as they use invokedynamic, while anonymous classes create separate .class files.

**Example:**
```java
// Anonymous Inner Class
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Anonymous class");
    }
};

// Lambda Expression
Runnable r2 = () -> System.out.println("Lambda");

// Lambda with 'this' reference
public class Demo {
    private String name = "Demo";
    
    public void test() {
        Runnable r = () -> System.out.println(this.name); // 'this' refers to Demo
    }
}
```

---

## 12. What is the difference between map() and flatMap() in streams?

**Answer:**
map() transforms each element into another object, producing a one-to-one mapping. flatMap() transforms each element into a stream of objects and then flattens all streams into a single stream, useful when dealing with nested collections or when one element can produce multiple results.

**Example:**
```java
List<String> words = Arrays.asList("Hello", "World");

// map() - one-to-one transformation
List<Integer> lengths = words.stream()
    .map(String::length)
    .collect(Collectors.toList()); // [5, 5]

// flatMap() - one-to-many transformation
List<String> letters = words.stream()
    .flatMap(word -> Arrays.stream(word.split("")))
    .collect(Collectors.toList()); // [H, e, l, l, o, W, o, r, l, d]

// Practical example with nested lists
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4)
);
List<Integer> flat = nested.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList()); // [1, 2, 3, 4]
```

---

## 13. What is the volatile keyword in multithreading?

**Answer:**
The volatile keyword ensures that a variable's value is always read from and written to main memory, not from thread-local cache. This guarantees visibility of changes across threads. When one thread modifies a volatile variable, other threads immediately see the updated value. However, volatile doesn't guarantee atomicity for compound operations.

**Example:**
```java
public class VolatileExample {
    private volatile boolean running = true;
    
    public void stop() {
        running = false; // Change visible to all threads immediately
    }
    
    public void run() {
        while (running) {
            // Do work
        }
        System.out.println("Stopped");
    }
}

// Without volatile, the thread might cache 'running' and never see the change
```

---

## 14. How does JVM Garbage Collection work?

**Answer:**
JVM Garbage Collection automatically reclaims memory by removing objects that are no longer referenced. It works in generations: Young Generation (where new objects are created), Old Generation (for long-lived objects), and Permanent/Metaspace (for class metadata). Minor GC cleans Young Generation frequently, while Major/Full GC cleans the entire heap less frequently. Common algorithms include Serial, Parallel, CMS, and G1GC.

**Example:**
```java
public class GCDemo {
    public static void main(String[] args) {
        // Object created in Young Generation
        String str = new String("Hello");
        
        // Object becomes eligible for GC when no references exist
        str = null;
        
        // Suggest GC (not guaranteed to run immediately)
        System.gc();
        
        // Objects surviving multiple minor GCs move to Old Generation
        List<String> longLived = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            longLived.add("Item " + i);
        }
    }
}
```

---

## 15. Why is String immutable in Java?

**Answer:**
Strings are immutable for security, thread-safety, and performance. Since Strings are used in class loading, database connections, and network operations, immutability prevents malicious code from modifying them. Immutability makes Strings thread-safe without synchronization. The String pool can reuse String literals, saving memory. Immutable Strings have cached hashcodes, making them efficient for HashMap keys.

**Example:**
```java
String s1 = "Hello";
String s2 = "Hello"; // Points to same object in String pool

System.out.println(s1 == s2); // true (same reference)

// Any modification creates a new String
String s3 = s1.concat(" World");
System.out.println(s1); // "Hello" (unchanged)
System.out.println(s3); // "Hello World" (new object)

// Thread-safe without synchronization
public void shareString() {
    String shared = "Immutable";
    // Multiple threads can safely read 'shared'
}
```

---

## 16. What are the performance benefits of StringBuilder over String?

**Answer:**
StringBuilder is mutable and more efficient for string concatenation in loops or multiple operations. String concatenation creates new objects each time, causing memory overhead and garbage collection pressure. StringBuilder modifies the same object, using a resizable array internally. For single concatenations, the difference is negligible, but for loops with many iterations, StringBuilder is significantly faster.

**Example:**
```java
// Inefficient - creates multiple String objects
String result = "";
for (int i = 0; i < 1000; i++) {
    result += i; // Creates new String object each iteration
}

// Efficient - modifies same StringBuilder object
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i); // Modifies existing object
}
String result2 = sb.toString();

// Performance comparison
long start = System.currentTimeMillis();
String s = "";
for (int i = 0; i < 10000; i++) s += i;
System.out.println("String: " + (System.currentTimeMillis() - start) + "ms");

start = System.currentTimeMillis();
StringBuilder sb2 = new StringBuilder();
for (int i = 0; i < 10000; i++) sb2.append(i);
System.out.println("StringBuilder: " + (System.currentTimeMillis() - start) + "ms");
```

---

## 17. What is the difference between HashMap and HashSet?

**Answer:**
HashMap stores key-value pairs and implements the Map interface, allowing you to retrieve values using keys. HashSet stores unique elements and implements the Set interface, internally using a HashMap where elements are stored as keys with a dummy value. HashMap allows one null key and multiple null values, while HashSet allows only one null element. HashMap uses put() to add entries, HashSet uses add().

**Example:**
```java
// HashMap - stores key-value pairs
HashMap<String, Integer> map = new HashMap<>();
map.put("Apple", 100);
map.put("Banana", 50);
System.out.println(map.get("Apple")); // 100

// HashSet - stores unique elements
HashSet<String> set = new HashSet<>();
set.add("Apple");
set.add("Banana");
set.add("Apple"); // Duplicate, won't be added
System.out.println(set.size()); // 2

// HashSet internally uses HashMap
// Equivalent to: HashMap<String, Object> internalMap = new HashMap<>();
```

---

## 18. What is the internal working of HashMap?

**Answer:**
HashMap uses an array of buckets where each bucket is a linked list or tree node. When you put a key-value pair, HashMap calculates the hashcode of the key, applies a hash function to determine the bucket index, and stores the entry there. If multiple keys hash to the same bucket (collision), they're stored in a linked list. From Java 8, if a bucket has more than 8 entries, it converts to a balanced tree for better performance. When retrieving, HashMap uses the same hash calculation to find the bucket and then searches within it.

**Example:**
```java
HashMap<String, Integer> map = new HashMap<>();

// Internal process when putting:
// 1. Calculate hashCode of key: "Apple".hashCode()
// 2. Apply hash function: hash(hashCode) to get bucket index
// 3. Check if bucket is empty or has collision
// 4. Store Entry(key, value) in bucket

map.put("Apple", 100);  // Stored at bucket index X
map.put("Banana", 50);  // Stored at bucket index Y

// If collision occurs (same bucket index):
map.put("Apple", 150);  // Updates existing entry
map.put("Elppa", 200);  // If hashes to same bucket, creates linked list

// Retrieval process:
Integer value = map.get("Apple");
// 1. Calculate hash of "Apple"
// 2. Find bucket index
// 3. Search in bucket (list/tree) using equals()
// 4. Return value

// Load factor (0.75) triggers rehashing when size > capacity * 0.75
```


## 19. What is the difference between shallow cloning and deep cloning?

**Answer:**
Shallow cloning creates a new object but copies only the references of nested objects, so both original and cloned objects share the same nested objects. Deep cloning creates a completely independent copy where all nested objects are also cloned recursively. Changes to nested objects in shallow clone affect the original, but in deep clone they don't.

**Example:**
```java
class Address {
    String city;
    Address(String city) { this.city = city; }
}

class Person implements Cloneable {
    String name;
    Address address;
    
    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }
    
    // Shallow Clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Only copies reference of address
    }
    
    // Deep Clone
    public Person deepClone() {
        return new Person(this.name, new Address(this.address.city));
    }
}

// Usage
Person p1 = new Person("John", new Address("NYC"));
Person p2 = (Person) p1.clone(); // Shallow
p2.address.city = "LA";
System.out.println(p1.address.city); // "LA" - affected!

Person p3 = p1.deepClone(); // Deep
p3.address.city = "Boston";
System.out.println(p1.address.city); // "LA" - not affected
```

---

## 20. What is a Marker Interface?

**Answer:**
A marker interface is an empty interface with no methods or fields, used to mark or tag a class with special behavior. The JVM or framework recognizes these markers and treats the class differently. Common examples include Serializable, Cloneable, and Remote. They signal intent or capability without enforcing any contract.

**Example:**
```java
// Marker Interface
public interface Deletable {
    // No methods - just marks the class
}

public class User implements Deletable {
    private String name;
}

public class Product {
    private String title;
}

// Usage in framework/utility
public class DataManager {
    public void delete(Object obj) {
        if (obj instanceof Deletable) {
            // Perform deletion
            System.out.println("Deleting: " + obj.getClass().getName());
        } else {
            throw new IllegalArgumentException("Object not deletable");
        }
    }
}

// Built-in example
class Employee implements Serializable {
    // Can be serialized because of marker interface
}
```

---

## 21. What is the difference between extending Thread and implementing Runnable?

**Answer:**
Extending Thread creates a thread class but limits inheritance since Java doesn't support multiple inheritance. Implementing Runnable is more flexible as the class can extend another class and implement multiple interfaces. Runnable promotes better design by separating the task from the thread mechanism. Runnable instances can be reused with ExecutorService, while Thread instances cannot.

**Example:**
```java
// Extending Thread
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName());
    }
}

// Implementing Runnable (Preferred)
class MyTask implements Runnable {
    public void run() {
        System.out.println("Runnable: " + Thread.currentThread().getName());
    }
}

// Usage
MyThread t1 = new MyThread();
t1.start();

Thread t2 = new Thread(new MyTask());
t2.start();

// Runnable advantage - can extend other classes
class MyClass extends SomeClass implements Runnable {
    public void run() {
        // Task logic
    }
}
```

---

## 22. What are the ways to create threads in Java?

**Answer:**
There are multiple ways to create threads: extending Thread class, implementing Runnable interface, implementing Callable interface with ExecutorService for return values, using lambda expressions with Runnable, using Thread pools via ExecutorService, and using CompletableFuture for asynchronous tasks. Modern applications prefer ExecutorService and CompletableFuture over direct thread creation.

**Example:**
```java
// 1. Extending Thread
class MyThread extends Thread {
    public void run() { System.out.println("Thread"); }
}
new MyThread().start();

// 2. Implementing Runnable
Thread t = new Thread(() -> System.out.println("Runnable"));
t.start();

// 3. Using Callable with ExecutorService
ExecutorService executor = Executors.newFixedThreadPool(2);
Future<Integer> future = executor.submit(() -> {
    return 42; // Can return value
});
Integer result = future.get();

// 4. Using Thread Pool
executor.execute(() -> System.out.println("Pool task"));

// 5. Using CompletableFuture
CompletableFuture.runAsync(() -> {
    System.out.println("Async task");
});

executor.shutdown();
```

---

## 23. What is concurrency in Java?

**Answer:**
Concurrency is the ability to execute multiple tasks simultaneously, improving application performance and responsiveness. Java provides tools like threads, synchronized blocks, locks, concurrent collections (ConcurrentHashMap, CopyOnWriteArrayList), atomic variables, and the java.util.concurrent package with ExecutorService, CountDownLatch, Semaphore, and more. Proper synchronization prevents race conditions and ensures thread safety.

**Example:**
```java
// Synchronized method
class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public int getCount() { return count; }
}

// Using Lock
class SafeCounter {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
}

// Atomic variable
AtomicInteger atomicCount = new AtomicInteger(0);
atomicCount.incrementAndGet();

// Concurrent collection
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("key", 1); // Thread-safe
```

---

## 24. How do you implement database connectivity in Java?

**Answer:**
Database connectivity in Java is implemented using JDBC (Java Database Connectivity). You load the database driver, establish a connection using DriverManager or DataSource, create Statement or PreparedStatement objects to execute queries, process ResultSet for query results, and close resources. Modern applications use connection pooling and ORM frameworks like Hibernate or Spring Data JPA for easier database operations.

**Example:**
```java
// Basic JDBC connection
public class DatabaseConnection {
    public void connectAndQuery() {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "password";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            
            stmt.setInt(1, 1);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Spring Boot with JPA (Modern approach)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // No manual JDBC code needed
}
```

---

## 25. How does JDBC connection pooling work?

**Answer:**
Connection pooling maintains a pool of reusable database connections instead of creating new connections for each request. When an application needs a connection, it borrows one from the pool; after use, it returns the connection to the pool rather than closing it. This significantly improves performance by avoiding the overhead of creating and destroying connections. Popular implementations include HikariCP, Apache DBCP, and C3P0.

**Example:**
```java
// HikariCP Configuration (Most popular)
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        config.setUsername("root");
        config.setPassword("password");
        config.setMaximumPoolSize(10); // Max 10 connections
        config.setMinimumIdle(5);      // Min 5 idle connections
        config.setConnectionTimeout(30000);
        
        return new HikariDataSource(config);
    }
}

// Spring Boot application.properties
// spring.datasource.hikari.maximum-pool-size=10
// spring.datasource.hikari.minimum-idle=5

// Usage - connection automatically managed
@Autowired
private DataSource dataSource;

public void query() throws SQLException {
    try (Connection conn = dataSource.getConnection()) {
        // Use connection - automatically returned to pool
    }
}
```

---

## 26. What is transaction management in Spring Boot?

**Answer:**
Transaction management ensures that a series of database operations either all succeed or all fail together, maintaining data consistency. Spring Boot provides declarative transaction management using @Transactional annotation. When a method is marked @Transactional, Spring automatically begins a transaction, commits on success, or rolls back on exception. You can configure propagation, isolation levels, and rollback rules.

**Example:**
```java
@Service
public class BankService {
    @Autowired
    private AccountRepository accountRepository;
    
    @Transactional
    public void transferMoney(Long fromId, Long toId, Double amount) {
        Account from = accountRepository.findById(fromId).orElseThrow();
        Account to = accountRepository.findById(toId).orElseThrow();
        
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        
        accountRepository.save(from);
        accountRepository.save(to);
        
        // If exception occurs here, both operations rollback
    }
    
    @Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = Exception.class
    )
    public void complexOperation() {
        // Transaction configuration
    }
}
```

---

## 27. Write business logic for a CRUD service in Java.

**Answer:**
A CRUD service implements Create, Read, Update, and Delete operations for an entity. In Spring Boot, this typically involves a Controller for REST endpoints, a Service layer for business logic, a Repository for database operations, and an Entity model. The service layer handles validation, business rules, and coordinates between controller and repository.

**Example:**
```java
// Entity
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    // Getters and setters
}

// Repository
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

// Service
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    
    public Product create(Product product) {
        return repository.save(product);
    }
    
    public List<Product> getAll() {
        return repository.findAll();
    }
    
    public Product getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    
    public Product update(Long id, Product product) {
        Product existing = getById(id);
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        return repository.save(existing);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

// Controller
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService service;
    
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }
    
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
```
# ✅ Spring Boot & Microservices

## 28. What is Spring Cloud?

**Answer:**
Spring Cloud is a framework that provides tools for building distributed systems and microservices. It offers solutions for common patterns like service discovery (Eureka), configuration management (Config Server), load balancing (Ribbon), circuit breakers (Hysteria/Resilience4j), API Gateway (Spring Cloud Gateway), and distributed tracing (Sleuth). It simplifies microservices development by handling infrastructure concerns so developers can focus on business logic.

**Example:**
```java
// Service Discovery with Eureka Server
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}

// Eureka Client (Microservice)
@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}

// application.yml
// eureka:
//   client:
//     service-url:
//       defaultZone: http://localhost:8761/eureka/

// Using Feign Client for inter-service communication
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id);
}
```

---

## 29. How do you design and implement backend APIs?

**Answer:**
Backend API design follows RESTful principles with proper HTTP methods (GET, POST, PUT, DELETE), meaningful URLs, status codes, and JSON responses. Implementation involves creating controllers for endpoints, services for business logic, repositories for data access, and DTOs for data transfer. Consider versioning, pagination, filtering, error handling, validation, security, and documentation using Swagger/OpenAPI. Follow consistent naming conventions and return appropriate status codes.

**Example:**
```java
// DTO
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    // Getters and setters
}

// Service
@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    
    public Page<UserDTO> getUsers(Pageable pageable) {
        return repository.findAll(pageable)
            .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()));
    }
}

// Controller with REST best practices
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService service;
    
    @GetMapping
    public ResponseEntity<Page<UserDTO>> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.getUsers(PageRequest.of(page, size)));
    }
    
    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(e.getMessage()));
    }
}
```

---

## 30. How are your microservices structured?

**Answer:**
Microservices are structured with each service owning its own database, having independent deployment, and communicating via REST APIs or message queues. Typical structure includes API Gateway as entry point, service registry for discovery, config server for centralized configuration, and each microservice following layered architecture with controller, service, repository layers. Services are organized by business capability like user-service, order-service, payment-service, and notification-service.

**Example:**
```java
// Project Structure
// api-gateway/
// eureka-server/
// config-server/
// user-service/
//   ├── controller/
//   ├── service/
//   ├── repository/
//   ├── model/
//   └── application.yml
// order-service/
// payment-service/

// User Service
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}

// Order Service communicating with User Service
@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private OrderRepository orderRepository;
    
    public Order createOrder(OrderRequest request) {
        // Call User Service to validate user
        String userServiceUrl = "http://user-service/api/users/" + request.getUserId();
        User user = restTemplate.getForObject(userServiceUrl, User.class);
        
        Order order = new Order();
        order.setUserId(user.getId());
        order.setAmount(request.getAmount());
        return orderRepository.save(order);
    }
}

// Load balanced RestTemplate
@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

---

## 31. What design patterns are commonly used in Microservices architecture?

**Answer:**
Common microservices patterns include API Gateway (single entry point), Service Discovery (dynamic service location), Circuit Breaker (fault tolerance), Saga Pattern (distributed transactions), CQRS (Command Query Responsibility Segregation), Event Sourcing (state changes as events), Database per Service (data isolation), Strangler Pattern (gradual migration), and Bulkhead Pattern (resource isolation). These patterns solve challenges like service communication, data consistency, fault tolerance, and scalability.

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

// 2. Circuit Breaker Pattern (with Resilience4j)
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

// 3. Saga Pattern (Choreography)
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
```

---

## 32. What is Event-Driven Architecture in Java?

**Answer:**
Event-Driven Architecture is a design pattern where services communicate through events rather than direct calls. When something happens in one service, it publishes an event to a message broker like Kafka or RabbitMQ, and other services subscribe to these events and react accordingly. This provides loose coupling, scalability, and asynchronous processing. Events represent state changes or significant occurrences in the system.

**Example:**
```java
// Event
public class OrderCreatedEvent {
    private Long orderId;
    private Long userId;
    private Double amount;
    // Getters and setters
}

// Event Publisher (Order Service)
@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    
    public Order createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        
        // Publish event
        OrderCreatedEvent event = new OrderCreatedEvent(
            order.getId(), order.getUserId(), order.getAmount()
        );
        kafkaTemplate.send("order-events", event);
        
        return order;
    }
}

// Event Consumer (Notification Service)
@Service
public class NotificationEventListener {
    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Send notification to user
        emailService.sendOrderConfirmation(event.getUserId(), event.getOrderId());
    }
}

// Event Consumer (Inventory Service)
@Service
public class InventoryEventListener {
    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Update inventory
        inventoryService.reserveItems(event.getOrderId());
    }
}
```

---

## 33. What is Kafka Consumer?

**Answer:**
A Kafka Consumer is a client application that reads messages from Kafka topics. Consumers subscribe to one or more topics and process the stream of records. They belong to consumer groups for load balancing, where each partition is consumed by only one consumer in the group. Consumers track their position using offsets, allowing them to resume from where they left off. Spring Kafka provides @KafkaListener annotation for easy consumer implementation.

**Example:**
```java
// Kafka Consumer Configuration
@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, OrderEvent> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "order-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }
}

// Simple Consumer
@Service
public class OrderConsumer {
    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void consume(OrderEvent event) {
        System.out.println("Received: " + event.getOrderId());
        // Process the order event
    }
}

// Consumer with manual acknowledgment
@Service
public class PaymentConsumer {
    @KafkaListener(topics = "payment-topic")
    public void consume(ConsumerRecord<String, PaymentEvent> record) {
        PaymentEvent event = record.value();
        processPayment(event);
        // Offset committed automatically
    }
}
```

---

## 34. How do you implement messaging between microservices?

**Answer:**
Messaging between microservices can be implemented using synchronous communication (REST APIs with RestTemplate or Feign Client) or asynchronous communication (message brokers like Kafka, RabbitMQ, or AWS SQS). Synchronous is simpler but creates tight coupling and blocking calls. Asynchronous provides better scalability, fault tolerance, and decoupling. Choose based on requirements: use REST for immediate responses, messaging for eventual consistency and high throughput.

**Example:**
```java
// 1. Synchronous - Using Feign Client
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/users/{id}")
    User getUser(@PathVariable Long id);
}

@Service
public class OrderService {
    @Autowired
    private UserClient userClient;
    
    public Order createOrder(OrderRequest request) {
        User user = userClient.getUser(request.getUserId());
        // Create order
    }
}

// 2. Asynchronous - Using Kafka
@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaTemplate;
    
    public void createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        kafkaTemplate.send("order-topic", new OrderMessage(order));
    }
}

@Service
public class InventoryService {
    @KafkaListener(topics = "order-topic")
    public void handleOrder(OrderMessage message) {
        // Update inventory asynchronously
    }
}

// 3. Asynchronous - Using RabbitMQ
@Service
public class NotificationService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void sendNotification(String message) {
        rabbitTemplate.convertAndSend("notification-exchange", "email.key", message);
    }
}
```

---

## 35. What is Circuit Breaker pattern?

**Answer:**
Circuit Breaker pattern prevents cascading failures in microservices by monitoring service calls and stopping requests to failing services. It has three states: Closed (normal operation), Open (requests fail immediately without calling service), and Half-Open (testing if service recovered). When failures exceed a threshold, the circuit opens. After a timeout, it enters half-open to test recovery. This protects systems from waiting on unresponsive services and allows graceful degradation.

**Example:**
```java
// Using Resilience4j Circuit Breaker
@Service
public class PaymentService {
    @Autowired
    private PaymentClient paymentClient;
    
    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    @Retry(name = "paymentService")
    public PaymentResponse processPayment(PaymentRequest request) {
        return paymentClient.process(request);
    }
    
    // Fallback method when circuit is open
    public PaymentResponse paymentFallback(PaymentRequest request, Exception e) {
        return new PaymentResponse("FAILED", "Payment service unavailable, please try later");
    }
}

// application.yml configuration
// resilience4j:
//   circuitbreaker:
//     instances:
//       paymentService:
//         failure-rate-threshold: 50
//         wait-duration-in-open-state: 10000
//         sliding-window-size: 10
//         minimum-number-of-calls: 5

// Circuit Breaker Configuration
@Configuration
public class CircuitBreakerConfig {
    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofSeconds(10))
            .build();
        return CircuitBreakerRegistry.of(config);
    }
}
```

---

## 36. What is Resilience4j and why is it used?

**Answer:**
Resilience4j is a lightweight fault tolerance library for Java applications, designed for functional programming and Java 8+. It provides Circuit Breaker, Rate Limiter, Retry, Bulkhead, and Time Limiter patterns. It's used to make microservices resilient by handling failures gracefully, preventing cascading failures, and implementing fallback mechanisms. It's the recommended replacement for Netflix Hystrix, which is now in maintenance mode, and integrates seamlessly with Spring Boot.

**Example:**
```java
// Circuit Breaker
@Service
public class UserService {
    @CircuitBreaker(name = "userService", fallbackMethod = "getUserFallback")
    public User getUser(Long id) {
        return userClient.getUser(id);
    }
    
    public User getUserFallback(Long id, Exception e) {
        return new User(id, "Guest User");
    }
}

// Rate Limiter - Limit requests per second
@Service
public class ApiService {
    @RateLimiter(name = "apiService")
    public Response callExternalApi() {
        return externalApiClient.call();
    }
}

// Retry - Retry failed operations
@Service
public class OrderService {
    @Retry(name = "orderService", fallbackMethod = "createOrderFallback")
    public Order createOrder(OrderRequest request) {
        return orderClient.create(request);
    }
    
    public Order createOrderFallback(OrderRequest request, Exception e) {
        return new Order("PENDING");
    }
}

// Bulkhead - Limit concurrent calls
@Service
public class PaymentService {
    @Bulkhead(name = "paymentService", type = Bulkhead.Type.SEMAPHORE)
    public Payment process(PaymentRequest request) {
        return paymentProcessor.process(request);
    }
}

// application.yml
// resilience4j:
//   ratelimiter:
//     instances:
//       apiService:
//         limit-for-period: 10
//         limit-refresh-period: 1s
//   retry:
//     instances:
//       orderService:
//         max-attempts: 3
//         wait-duration: 1000
```

---

## 37. What is an API Gateway and its purpose?

**Answer:**
An API Gateway is a single entry point for all client requests in a microservices architecture. It routes requests to appropriate microservices, handles cross-cutting concerns like authentication, authorization, rate limiting, logging, and request/response transformation. It provides load balancing, protocol translation, and can aggregate responses from multiple services. Popular implementations include Spring Cloud Gateway, Netflix Zuul, and Kong. This simplifies client communication and centralizes common functionality.

**Example:**
```java
// Spring Cloud Gateway Configuration
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/api/users/**")
                .filters(f -> f.addRequestHeader("X-Gateway", "true")
                               .circuitBreaker(c -> c.setName("userServiceCB")))
                .uri("lb://user-service"))
            
            .route("order-service", r -> r.path("/api/orders/**")
                .filters(f -> f.rewritePath("/api/orders/(?<segment>.*)", "/${segment}"))
                .uri("lb://order-service"))
            
            .build();
    }
}

// Custom Global Filter
@Component
public class AuthenticationFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        
        if (token == null || !validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        
        return chain.filter(exchange);
    }
    
    private boolean validateToken(String token) {
        // JWT validation logic
        return true;
    }
}

// application.yml
// spring:
//   cloud:
//     gateway:
//       routes:
//         - id: user-service
//           uri: lb://user-service
//           predicates:
//             - Path=/api/users/**
//           filters:
//             - name: CircuitBreaker
//               args:
//                 name: userServiceCB
//             - name: RateLimiter
//               args:
//                 redis-rate-limiter.replenishRate: 10
//                 redis-rate-limiter.burstCapacity: 20
```
# ✅ Security

## 38. How do you secure a Spring Boot application?

**Answer:**
Spring Boot applications are secured using Spring Security, which provides authentication and authorization. Key security measures include implementing JWT or OAuth2 for authentication, using HTTPS for encrypted communication, enabling CSRF protection, configuring CORS policies, implementing role-based access control (RBAC), securing endpoints with method-level security, validating inputs to prevent SQL injection and XSS attacks, and using password encoders like BCrypt. You also configure security filters, handle authentication failures, and protect sensitive data.

**Example:**
```java
// Security Configuration
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// Method Level Security
@Service
public class UserService {
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
```

---

## 39. How is JWT authentication implemented?

**Answer:**
JWT (JSON Web Token) authentication involves generating a token when a user logs in successfully, which contains encoded user information and claims. The client stores this token and sends it in the Authorization header with each request. The server validates the token's signature, checks expiration, and extracts user details without querying the database. Implementation includes creating a JWT utility class for token generation and validation, a filter to intercept requests and validate tokens, and endpoints for login and token refresh.

**Example:**
```java
// JWT Utility
@Component
public class JwtUtil {
    private String secret = "mySecretKey";
    
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }
    
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret)
            .parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

// JWT Filter
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);
            
            if (username != null && jwtUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken auth = 
                    new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}

// Login Controller
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
```

---

## 40. What is CORS and how does it work?

**Answer:**
CORS (Cross-Origin Resource Sharing) is a security mechanism that allows or restricts web applications running on one domain to access resources from another domain. Browsers enforce the same-origin policy by default, blocking cross-origin requests. CORS works by adding HTTP headers that tell the browser which origins are allowed to access the resources. The server responds with Access-Control-Allow-Origin, Access-Control-Allow-Methods, and other headers. For complex requests, browsers send a preflight OPTIONS request first.

**Example:**
```java
// Global CORS Configuration
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:4200", "https://myapp.com")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600);
            }
        };
    }
}

// Controller Level CORS
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}

// Security Configuration with CORS
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and()
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
```

---

# ✅ eCommerce System Design

## 41. What are the main features of an eCommerce application?

**Answer:**
Main features of an eCommerce application include user registration and authentication, product catalog with search and filtering, shopping cart management, order processing and checkout, payment gateway integration, inventory management, order tracking, user reviews and ratings, wishlist functionality, admin dashboard for managing products and orders, email notifications, shipping integration, discount and coupon management, and analytics for sales reporting. The system should handle high traffic, ensure secure transactions, and provide a seamless user experience.

**Example:**
```java
// Product Service
@Service
public class ProductService {
    public Page<Product> searchProducts(String keyword, String category, 
                                       Double minPrice, Double maxPrice, Pageable pageable) {
        return productRepository.findByFilters(keyword, category, minPrice, maxPrice, pageable);
    }
}

// Cart Service
@Service
public class CartService {
    public Cart addToCart(Long userId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(userId));
        CartItem item = new CartItem(productId, quantity);
        cart.addItem(item);
        return cartRepository.save(cart);
    }
}

// Order Service
@Service
public class OrderService {
    @Transactional
    public Order checkout(Long userId, CheckoutRequest request) {
        Cart cart = cartService.getCart(userId);
        Order order = new Order(userId, cart.getItems(), request.getShippingAddress());
        
        // Process payment
        Payment payment = paymentService.processPayment(order.getTotalAmount(), request.getPaymentDetails());
        order.setPaymentId(payment.getId());
        
        // Update inventory
        inventoryService.reduceStock(cart.getItems());
        
        // Clear cart
        cartService.clearCart(userId);
        
        // Send notification
        notificationService.sendOrderConfirmation(order);
        
        return orderRepository.save(order);
    }
}

// Admin Controller
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }
    
    @GetMapping("/orders")
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }
}
```

---

## 42. Explain the frontend and backend flow of an eCommerce application.

**Answer:**
Frontend flow starts with users browsing products, adding items to cart, proceeding to checkout, entering shipping and payment details, and confirming the order. The UI displays product details, cart summary, and order confirmation. Backend flow involves receiving API requests, authenticating users, validating input, checking inventory availability, processing payments through payment gateway, creating order records, updating inventory, sending confirmation emails, and returning responses. The frontend makes REST API calls to backend services, which coordinate with database, payment services, and notification systems.

**Example:**
```java
// Frontend Flow (Angular/React - Conceptual)
// 1. User browses: GET /api/products
// 2. Add to cart: POST /api/cart/items
// 3. View cart: GET /api/cart
// 4. Checkout: POST /api/orders/checkout
// 5. View order: GET /api/orders/{orderId}

// Backend Flow - Order Processing
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @PostMapping("/checkout")
    public ResponseEntity<OrderResponse> checkout(@RequestBody CheckoutRequest request) {
        // 1. Validate user and cart
        User user = userService.getCurrentUser();
        Cart cart = cartService.getCart(user.getId());
        
        if (cart.isEmpty()) {
            return ResponseEntity.badRequest().body(new OrderResponse("Cart is empty"));
        }
        
        // 2. Validate inventory
        if (!inventoryService.checkAvailability(cart.getItems())) {
            return ResponseEntity.badRequest().body(new OrderResponse("Items out of stock"));
        }
        
        // 3. Process payment
        PaymentResponse payment = paymentService.charge(
            request.getPaymentToken(), 
            cart.getTotalAmount()
        );
        
        if (!payment.isSuccess()) {
            return ResponseEntity.badRequest().body(new OrderResponse("Payment failed"));
        }
        
        // 4. Create order
        Order order = orderService.createOrder(user.getId(), cart, request.getShippingAddress());
        
        // 5. Update inventory
        inventoryService.reduceStock(cart.getItems());
        
        // 6. Clear cart
        cartService.clearCart(user.getId());
        
        // 7. Send notifications
        emailService.sendOrderConfirmation(user.getEmail(), order);
        
        return ResponseEntity.ok(new OrderResponse(order.getId(), "Order placed successfully"));
    }
}

// Service Layer Coordination
@Service
public class OrderService {
    @Transactional
    public Order createOrder(Long userId, Cart cart, Address shippingAddress) {
        Order order = new Order();
        order.setUserId(userId);
        order.setItems(cart.getItems());
        order.setShippingAddress(shippingAddress);
        order.setStatus("PENDING");
        order.setTotalAmount(cart.getTotalAmount());
        
        return orderRepository.save(order);
    }
}
```

---

## 43. What are backend components and tools used in eCommerce?

**Answer:**
Backend components include API Gateway for routing, microservices for different domains (user, product, order, payment, inventory), databases (PostgreSQL/MySQL for transactional data, MongoDB for product catalog, Redis for caching), message queues (Kafka/RabbitMQ for async processing), payment gateway integration (Stripe, PayPal), search engine (Elasticsearch for product search), CDN for static content, email service (SendGrid, AWS SES), and monitoring tools (Prometheus, Grafana). The tech stack typically includes Spring Boot, Docker, Kubernetes, and cloud services (AWS/Azure).

**Example:**
```java
// Microservices Architecture Components

// 1. User Service
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    // Handles authentication, user profiles
}

// 2. Product Service with Elasticsearch
@Service
public class ProductSearchService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;
    
    public List<Product> searchProducts(String query) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.multiMatchQuery(query, "name", "description"))
            .build();
        return elasticsearchTemplate.search(searchQuery, Product.class)
            .stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}

// 3. Order Service with Kafka
@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    
    public Order createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        kafkaTemplate.send("order-events", new OrderEvent(order.getId(), "CREATED"));
        return order;
    }
}

// 4. Payment Service Integration
@Service
public class PaymentService {
    @Value("${stripe.api.key}")
    private String stripeApiKey;
    
    public PaymentResponse processPayment(PaymentRequest request) {
        // Stripe API integration
        Stripe.apiKey = stripeApiKey;
        Map<String, Object> params = new HashMap<>();
        params.put("amount", request.getAmount() * 100);
        params.put("currency", "usd");
        params.put("source", request.getToken());
        
        Charge charge = Charge.create(params);
        return new PaymentResponse(charge.getId(), charge.getStatus());
    }
}

// 5. Caching with Redis
@Service
public class ProductCacheService {
    @Autowired
    private RedisTemplate<String, Product> redisTemplate;
    
    @Cacheable(value = "products", key = "#id")
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}

// 6. API Gateway Configuration
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/api/users/**").uri("lb://user-service"))
            .route("product-service", r -> r.path("/api/products/**").uri("lb://product-service"))
            .route("order-service", r -> r.path("/api/orders/**").uri("lb://order-service"))
            .build();
    }
}
```

---

## 44. What Git workflow is used in an eCommerce application?

**Answer:**
eCommerce applications typically use Git Flow or GitHub Flow. The workflow includes main/master branch for production, develop branch for integration, feature branches for new features, release branches for preparing releases, and hotfix branches for urgent fixes. Developers create feature branches from develop, make changes, create pull requests for code review, merge to develop after approval, and then merge to main for production deployment. CI/CD pipelines automate testing and deployment. Tags are used for version management.

**Example:**
```bash
# Git Flow Workflow

# 1. Create feature branch
git checkout develop
git pull origin develop
git checkout -b feature/add-payment-gateway

# 2. Make changes and commit
git add .
git commit -m "feat: integrate Stripe payment gateway"

# 3. Push and create pull request
git push origin feature/add-payment-gateway
# Create PR on GitHub/GitLab: feature/add-payment-gateway -> develop

# 4. After code review and approval, merge to develop
git checkout develop
git merge feature/add-payment-gateway
git push origin develop

# 5. Create release branch
git checkout -b release/v1.2.0 develop
# Final testing and bug fixes

# 6. Merge to main and tag
git checkout main
git merge release/v1.2.0
git tag -a v1.2.0 -m "Release version 1.2.0"
git push origin main --tags

# 7. Merge back to develop
git checkout develop
git merge release/v1.2.0
git push origin develop

# 8. Hotfix for production issue
git checkout -b hotfix/payment-bug main
# Fix the bug
git commit -m "fix: resolve payment processing error"
git checkout main
git merge hotfix/payment-bug
git tag -a v1.2.1 -m "Hotfix for payment bug"
git push origin main --tags

# Branch naming conventions:
# feature/feature-name
# bugfix/bug-description
# hotfix/issue-description
# release/version-number
```

---

# ✅ Angular

## 45. What are Pipes in Angular?

**Answer:**
Pipes in Angular are functions that transform data in templates before displaying it. They take input data, process it, and return transformed output. Angular provides built-in pipes like DatePipe, UpperCasePipe, LowerCasePipe, CurrencyPipe, DecimalPipe, and PercentPipe. You use pipes with the pipe operator (|) in templates. Pipes can accept parameters and can be chained together. They're pure by default, meaning they only execute when input reference changes, improving performance.

**Example:**
```typescript
// Using built-in pipes in template
@Component({
  selector: 'app-product',
  template: `
    <h2>{{ productName | uppercase }}</h2>
    <p>Price: {{ price | currency:'USD':'symbol':'1.2-2' }}</p>
    <p>Discount: {{ discount | percent }}</p>
    <p>Launch Date: {{ launchDate | date:'fullDate' }}</p>
    <p>Description: {{ description | slice:0:100 }}</p>
  `
})
export class ProductComponent {
  productName = 'laptop';
  price = 1299.99;
  discount = 0.15;
  launchDate = new Date();
  description = 'This is a long description...';
}

// Chaining pipes
// {{ price | currency:'USD' | lowercase }}

// Pipe with parameters
// {{ date | date:'short':'UTC':'+0530' }}
```

---

## 46. What are Pure Pipes and Custom Pipes?

**Answer:**
Pure pipes only execute when Angular detects a pure change to the input value (primitive value change or object reference change). They're stateless and don't depend on external state, making them efficient. Custom pipes are user-defined pipes created using @Pipe decorator and implementing PipeTransform interface. You can make custom pipes impure by setting pure: false, which makes them execute on every change detection cycle, but this impacts performance.

**Example:**
```typescript
// Custom Pure Pipe
@Pipe({
  name: 'truncate',
  pure: true  // Default
})
export class TruncatePipe implements PipeTransform {
  transform(value: string, limit: number = 50): string {
    return value.length > limit ? value.substring(0, limit) + '...' : value;
  }
}

// Custom Impure Pipe
@Pipe({
  name: 'filter',
  pure: false  // Executes on every change detection
})
export class FilterPipe implements PipeTransform {
  transform(items: any[], searchText: string): any[] {
    if (!items || !searchText) return items;
    return items.filter(item => 
      item.name.toLowerCase().includes(searchText.toLowerCase())
    );
  }
}

// Register in module
@NgModule({
  declarations: [TruncatePipe, FilterPipe],
  exports: [TruncatePipe, FilterPipe]
})
export class SharedModule { }

// Usage in template
@Component({
  template: `
    <p>{{ longText | truncate:100 }}</p>
    <div *ngFor="let product of products | filter:searchTerm">
      {{ product.name }}
    </div>
  `
})
export class ProductListComponent {
  longText = 'Very long text...';
  searchTerm = '';
  products = [{name: 'Laptop'}, {name: 'Phone'}];
}
```

---

## 47. What is the role of Decorators in Angular?

**Answer:**
Decorators in Angular are special functions that add metadata to classes, methods, properties, or parameters. They tell Angular how to process and configure these elements. Common decorators include @Component for defining components, @Injectable for services, @NgModule for modules, @Input/@Output for component communication, @ViewChild for accessing child elements, and @HostListener for listening to host events. Decorators are a TypeScript feature that Angular extensively uses for dependency injection and component configuration.

**Example:**
```typescript
// Component Decorator
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent { }

// Injectable Decorator
@Injectable({
  providedIn: 'root'
})
export class UserService { }

// Input/Output Decorators
@Component({
  selector: 'app-child',
  template: `<button (click)="sendData()">Click</button>`
})
export class ChildComponent {
  @Input() userData: User;
  @Output() userSelected = new EventEmitter<User>();
  
  sendData() {
    this.userSelected.emit(this.userData);
  }
}

// ViewChild Decorator
@Component({
  template: `<input #nameInput type="text">`
})
export class FormComponent {
  @ViewChild('nameInput') nameInput: ElementRef;
  
  ngAfterViewInit() {
    this.nameInput.nativeElement.focus();
  }
}

// HostListener Decorator
@Component({
  selector: 'app-click-tracker'
})
export class ClickTrackerComponent {
  @HostListener('click', ['$event'])
  onClick(event: MouseEvent) {
    console.log('Element clicked');
  }
}
```

---

## 48. What is @Component decorator?

**Answer:**
@Component decorator marks a class as an Angular component and provides configuration metadata. It defines the component's selector (HTML tag name), template or templateUrl (view), styles or styleUrls (CSS), and other properties like providers, changeDetection, and encapsulation. The selector determines how the component is used in HTML, template defines the view, and styles define component-specific CSS. This decorator is essential for creating reusable UI components in Angular.

**Example:**
```typescript
// Basic Component
@Component({
  selector: 'app-product-card',
  template: `
    <div class="card">
      <h3>{{ product.name }}</h3>
      <p>{{ product.price | currency }}</p>
      <button (click)="addToCart()">Add to Cart</button>
    </div>
  `,
  styles: [`
    .card {
      border: 1px solid #ccc;
      padding: 16px;
      border-radius: 8px;
    }
  `]
})
export class ProductCardComponent {
  @Input() product: Product;
  @Output() cartUpdated = new EventEmitter<Product>();
  
  addToCart() {
    this.cartUpdated.emit(this.product);
  }
}

// Component with external files
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
  providers: [UserService],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserProfileComponent implements OnInit {
  user: User;
  
  constructor(private userService: UserService) { }
  
  ngOnInit() {
    this.user = this.userService.getCurrentUser();
  }
}

// Usage in parent template
@Component({
  template: `
    <app-product-card 
      [product]="selectedProduct" 
      (cartUpdated)="onCartUpdate($event)">
    </app-product-card>
  `
})
export class ShopComponent {
  selectedProduct: Product;
  
  onCartUpdate(product: Product) {
    console.log('Added to cart:', product);
  }
}
```

---

## 49. What is @Injectable decorator?

**Answer:**
@Injectable decorator marks a class as available for dependency injection. It tells Angular that this class can be injected as a dependency into other classes. The providedIn property determines where the service is provided - 'root' makes it a singleton available application-wide, or you can specify a module. Services marked with @Injectable can have their own dependencies injected through the constructor. This is fundamental to Angular's dependency injection system for creating reusable business logic.

**Example:**
```typescript
// Service with root provider
@Injectable({
  providedIn: 'root'  // Singleton across entire app
})
export class AuthService {
  private currentUser: User;
  
  login(username: string, password: string): Observable<User> {
    return this.http.post<User>('/api/login', { username, password });
  }
  
  logout() {
    this.currentUser = null;
  }
  
  isAuthenticated(): boolean {
    return !!this.currentUser;
  }
}

// Service with module provider
@Injectable({
  providedIn: ProductModule  // Only available in ProductModule
})
export class ProductService {
  constructor(private http: HttpClient) { }
  
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>('/api/products');
  }
}

// Service with dependencies
@Injectable({
  providedIn: 'root'
})
export class OrderService {
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private cartService: CartService
  ) { }
  
  createOrder(order: Order): Observable<Order> {
    const userId = this.authService.getCurrentUser().id;
    const cartItems = this.cartService.getItems();
    return this.http.post<Order>('/api/orders', { userId, items: cartItems });
  }
}

// Using service in component
@Component({
  selector: 'app-product-list'
})
export class ProductListComponent implements OnInit {
  products: Product[];
  
  constructor(private productService: ProductService) { }
  
  ngOnInit() {
    this.productService.getProducts().subscribe(
      data => this.products = data
    );
  }
}
```

# ✅ DevOps / Deployment

## 50. How is your application deployed?

**Answer:**
Application deployment typically follows a CI/CD pipeline approach. Code is pushed to Git repository, which triggers automated builds in Jenkins or GitLab CI. The application is built, tested, containerized using Docker, and pushed to a container registry. Then it's deployed to environments like development, staging, and production using Kubernetes or cloud services like AWS ECS, Azure App Service, or traditional servers. Deployment strategies include blue-green deployment, rolling updates, or canary releases. Configuration is managed through environment variables or config servers, and monitoring tools track application health.

**Example:**
```yaml
# Deployment Pipeline Overview

# 1. Git Push triggers CI/CD
git push origin main

# 2. Jenkins Pipeline (Jenkinsfile)
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
        
        stage('Docker Build') {
            steps {
                sh 'docker build -t myapp:${BUILD_NUMBER} .'
                sh 'docker tag myapp:${BUILD_NUMBER} myregistry/myapp:latest'
            }
        }
        
        stage('Push to Registry') {
            steps {
                sh 'docker push myregistry/myapp:latest'
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/deployment.yaml'
                sh 'kubectl rollout status deployment/myapp'
            }
        }
    }
}

# 3. Kubernetes Deployment
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
  template:
    metadata:
      labels:
        app: myapp
    spec:
      containers:
      - name: myapp
        image: myregistry/myapp:latest
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "production"

# 4. Service Exposure
apiVersion: v1
kind: Service
metadata:
  name: myapp-service
spec:
  type: LoadBalancer
  selector:
    app: myapp
  ports:
  - port: 80
    targetPort: 8080
```

---

## 51. How does Jenkins CI/CD pipeline work?

**Answer:**
Jenkins CI/CD pipeline automates the software delivery process from code commit to deployment. It starts when code is pushed to Git, triggering a webhook to Jenkins. Jenkins pulls the code, builds the application using Maven or Gradle, runs unit and integration tests, performs code quality checks with SonarQube, builds Docker images, pushes to container registry, and deploys to target environments. The pipeline is defined in a Jenkinsfile using declarative or scripted syntax with stages for build, test, package, and deploy. It provides feedback on build status and can rollback on failures.

**Example:**
```groovy
// Declarative Jenkins Pipeline
pipeline {
    agent any
    
    environment {
        DOCKER_REGISTRY = 'docker.io/myrepo'
        APP_NAME = 'spring-boot-app'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/user/repo.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        
        stage('Unit Tests') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }
        
        stage('Code Quality') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.host.url=http://sonarqube:9000'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_REGISTRY}/${APP_NAME}:${BUILD_NUMBER}")
                }
            }
        }
        
        stage('Push to Registry') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-credentials') {
                        docker.image("${DOCKER_REGISTRY}/${APP_NAME}:${BUILD_NUMBER}").push()
                        docker.image("${DOCKER_REGISTRY}/${APP_NAME}:${BUILD_NUMBER}").push('latest')
                    }
                }
            }
        }
        
        stage('Deploy to Dev') {
            steps {
                sh 'kubectl set image deployment/myapp myapp=${DOCKER_REGISTRY}/${APP_NAME}:${BUILD_NUMBER} -n dev'
            }
        }
        
        stage('Deploy to Production') {
            when {
                branch 'main'
            }
            steps {
                input message: 'Deploy to production?', ok: 'Deploy'
                sh 'kubectl set image deployment/myapp myapp=${DOCKER_REGISTRY}/${APP_NAME}:${BUILD_NUMBER} -n prod'
            }
        }
    }
    
    post {
        success {
            emailext subject: "Build Success: ${env.JOB_NAME}",
                     body: "Build ${env.BUILD_NUMBER} succeeded",
                     to: "team@example.com"
        }
        failure {
            emailext subject: "Build Failed: ${env.JOB_NAME}",
                     body: "Build ${env.BUILD_NUMBER} failed",
                     to: "team@example.com"
        }
    }
}
```

---

## 52. How do you deploy using Docker?

**Answer:**
Docker deployment involves creating a Dockerfile that defines the application environment, building a Docker image from the Dockerfile, pushing the image to a registry like Docker Hub or AWS ECR, and running containers from that image. The Dockerfile specifies the base image, copies application files, installs dependencies, exposes ports, and defines the startup command. You can use docker-compose for multi-container applications or deploy to orchestration platforms like Kubernetes. Docker ensures consistency across environments and simplifies deployment.

**Example:**
```dockerfile
# Dockerfile for Spring Boot Application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# Multi-stage build (optimized)
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

```bash
# Build Docker Image
docker build -t myapp:1.0 .

# Run Container
docker run -d -p 8080:8080 --name myapp-container myapp:1.0

# Run with environment variables
docker run -d -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_HOST=mysql \
  --name myapp myapp:1.0

# Push to Registry
docker tag myapp:1.0 myregistry/myapp:1.0
docker push myregistry/myapp:1.0
```

```yaml
# docker-compose.yml for multi-container setup
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/mydb
      - SPRING_PROFILES_ACTIVE=prod
    depends_on:
      - db
    networks:
      - app-network
  
  db:
    image: mysql:8.0
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=mydb
    ports:
      - "3306:3306"
    volumes:
      - db-data:/var/lib/mysql
    networks:
      - app-network

volumes:
  db-data:

networks:
  app-network:
    driver: bridge
```

```bash
# Deploy using docker-compose
docker-compose up -d

# Scale services
docker-compose up -d --scale app=3

# View logs
docker-compose logs -f app

# Stop and remove
docker-compose down
```

---

## 53. How do you deploy in AWS/Azure?

**Answer:**
AWS deployment options include EC2 instances for traditional deployments, Elastic Beanstalk for managed application hosting, ECS/EKS for container orchestration, Lambda for serverless functions, and RDS for databases. Azure offers similar services: Virtual Machines, App Service, AKS for Kubernetes, Azure Functions, and Azure SQL. Deployment involves setting up infrastructure using Terraform or CloudFormation, configuring load balancers, auto-scaling groups, security groups, and monitoring with CloudWatch or Azure Monitor. CI/CD pipelines deploy code automatically using AWS CodePipeline or Azure DevOps.

**Example:**
```yaml
# AWS Deployment - Elastic Beanstalk
# .ebextensions/environment.config
option_settings:
  aws:elasticbeanstalk:application:environment:
    SPRING_PROFILES_ACTIVE: production
    SERVER_PORT: 5000

# Deploy command
eb init -p java-17 myapp
eb create myapp-env
eb deploy

# AWS ECS Task Definition
{
  "family": "myapp-task",
  "containerDefinitions": [
    {
      "name": "myapp",
      "image": "myregistry/myapp:latest",
      "memory": 512,
      "cpu": 256,
      "essential": true,
      "portMappings": [
        {
          "containerPort": 8080,
          "protocol": "tcp"
        }
      ],
      "environment": [
        {
          "name": "SPRING_PROFILES_ACTIVE",
          "value": "production"
        }
      ]
    }
  ]
}

# AWS CLI Deployment
aws ecs register-task-definition --cli-input-json file://task-definition.json
aws ecs update-service --cluster myapp-cluster --service myapp-service --task-definition myapp-task
```

```yaml
# Azure App Service Deployment
# azure-pipelines.yml
trigger:
  - main

pool:
  vmImage: 'ubuntu-latest'

steps:
- task: Maven@3
  inputs:
    mavenPomFile: 'pom.xml'
    goals: 'clean package'

- task: Docker@2
  inputs:
    command: 'buildAndPush'
    repository: 'myapp'
    dockerfile: 'Dockerfile'
    containerRegistry: 'myacr'

- task: AzureWebAppContainer@1
  inputs:
    azureSubscription: 'my-subscription'
    appName: 'myapp'
    containers: 'myacr.azurecr.io/myapp:latest'
```

```bash
# Azure CLI Deployment
az login
az group create --name myapp-rg --location eastus
az appservice plan create --name myapp-plan --resource-group myapp-rg --sku B1 --is-linux
az webapp create --resource-group myapp-rg --plan myapp-plan --name myapp --deployment-container-image-name myregistry/myapp:latest

# Configure environment variables
az webapp config appsettings set --resource-group myapp-rg --name myapp --settings SPRING_PROFILES_ACTIVE=production

# Azure Kubernetes Service (AKS)
az aks create --resource-group myapp-rg --name myapp-cluster --node-count 3
az aks get-credentials --resource-group myapp-rg --name myapp-cluster
kubectl apply -f deployment.yaml
```

---

## 54. How are AWS services like S3 used in your project?

**Answer:**
AWS S3 is used for storing static files like images, videos, documents, and backups. In applications, S3 stores user-uploaded files, application logs, database backups, and serves static content for websites through CloudFront CDN. Implementation involves using AWS SDK to upload, download, and manage files programmatically. S3 provides durability, scalability, and cost-effective storage. You configure bucket policies for access control, enable versioning for file history, and use lifecycle policies to move old data to cheaper storage classes like Glacier.

**Example:**
```java
// AWS S3 Configuration
@Configuration
public class S3Config {
    @Value("${aws.access.key}")
    private String accessKey;
    
    @Value("${aws.secret.key}")
    private String secretKey;
    
    @Value("${aws.region}")
    private String region;
    
    @Bean
    public AmazonS3 s3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build();
    }
}

// S3 Service
@Service
public class S3Service {
    @Autowired
    private AmazonS3 s3Client;
    
    @Value("${aws.s3.bucket}")
    private String bucketName;
    
    // Upload file
    public String uploadFile(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            
            s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
            return s3Client.getUrl(bucketName, fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
    
    // Download file
    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        try (S3ObjectInputStream inputStream = s3Object.getObjectContent()) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to download file", e);
        }
    }
    
    // Delete file
    public void deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
    }
    
    // Generate presigned URL (temporary access)
    public String generatePresignedUrl(String fileName, int expirationMinutes) {
        Date expiration = new Date(System.currentTimeMillis() + expirationMinutes * 60000);
        return s3Client.generatePresignedUrl(bucketName, fileName, expiration).toString();
    }
}

// Controller
@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private S3Service s3Service;
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileUrl = s3Service.uploadFile(file);
        return ResponseEntity.ok(fileUrl);
    }
    
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        byte[] data = s3Service.downloadFile(fileName);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
            .body(data);
    }
    
    @DeleteMapping("/{fileName}")
    public ResponseEntity<Void> deleteFile(@PathVariable String fileName) {
        s3Service.deleteFile(fileName);
        return ResponseEntity.noContent().build();
    }
}
```

```properties
# application.properties
aws.access.key=YOUR_ACCESS_KEY
aws.secret.key=YOUR_SECRET_KEY
aws.region=us-east-1
aws.s3.bucket=myapp-bucket
```

---

# ✅ Coding & Problem Solving

## 55. Given an integer array with duplicates, find the missing number within a range.

**Answer:**
To find the missing number in an array with duplicates within a given range, you can use multiple approaches. The mathematical approach calculates the expected sum of the range and subtracts the actual sum of unique elements. The HashSet approach stores all numbers in a set and checks which number in the range is missing. The XOR approach works when there's exactly one missing number without duplicates. For arrays with duplicates, HashSet is most straightforward - iterate through the range and check if each number exists in the set.

**Example:**
```java
// Approach 1: Using HashSet (Best for duplicates)
public class MissingNumberFinder {
    
    public static List<Integer> findMissingNumbers(int[] arr, int start, int end) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : arr) {
            numSet.add(num);
        }
        
        List<Integer> missing = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (!numSet.contains(i)) {
                missing.add(i);
            }
        }
        return missing;
    }
    
    // Approach 2: Using boolean array (when range is small)
    public static List<Integer> findMissingNumbersArray(int[] arr, int start, int end) {
        boolean[] present = new boolean[end - start + 1];
        
        for (int num : arr) {
            if (num >= start && num <= end) {
                present[num - start] = true;
            }
        }
        
        List<Integer> missing = new ArrayList<>();
        for (int i = 0; i < present.length; i++) {
            if (!present[i]) {
                missing.add(start + i);
            }
        }
        return missing;
    }
    
    // Approach 3: Find single missing number (no duplicates)
    public static int findSingleMissing(int[] arr, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : arr) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }
    
    // Approach 4: Using XOR (single missing, no duplicates)
    public static int findMissingXOR(int[] arr, int n) {
        int xor = 0;
        
        // XOR all array elements
        for (int num : arr) {
            xor ^= num;
        }
        
        // XOR with all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        
        return xor;
    }
    
    public static void main(String[] args) {
        // Test Case 1: Array with duplicates
        int[] arr1 = {1, 2, 2, 3, 5, 5, 6, 8, 9};
        System.out.println("Missing numbers: " + findMissingNumbers(arr1, 1, 10));
        // Output: [4, 7, 10]
        
        // Test Case 2: Different range
        int[] arr2 = {5, 7, 7, 8, 10, 12};
        System.out.println("Missing numbers: " + findMissingNumbers(arr2, 5, 12));
        // Output: [6, 9, 11]
        
        // Test Case 3: Single missing (no duplicates)
        int[] arr3 = {1, 2, 3, 5, 6};
        System.out.println("Single missing: " + findSingleMissing(arr3, 6));
        // Output: 4
        
        // Test Case 4: Using XOR
        int[] arr4 = {1, 2, 4, 5};
        System.out.println("Missing using XOR: " + findMissingXOR(arr4, 5));
        // Output: 3
    }
}

// Optimized solution with Stream API
public class MissingNumberStream {
    public static List<Integer> findMissing(int[] arr, int start, int end) {
        Set<Integer> numSet = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        return IntStream.rangeClosed(start, end)
            .filter(i -> !numSet.contains(i))
            .boxed()
            .collect(Collectors.toList());
    }
}
```

**Time Complexity Analysis:**
```
HashSet Approach:
- Time: O(n + m) where n = array length, m = range size
- Space: O(n) for the HashSet

Boolean Array Approach:
- Time: O(n + m)
- Space: O(m) for the boolean array

Mathematical Sum (single missing):
- Time: O(n)
- Space: O(1)

XOR Approach (single missing):
- Time: O(n)
- Space: O(1)
```

# ✅ Database & System Design

## 56. How would you query a large movies database to find the top 10 lead actors with most profitable movies (IMDb ≥ 8)?

**Answer:**
To find the top 10 lead actors with the most profitable movies having IMDb rating of 8 or above, you need to join the movies table with actors table, filter by IMDb rating and lead actor role, calculate profitability (revenue minus budget), group by actor, sum their total profitability, and order by total profit descending with a limit of 10. The query involves multiple joins between movies, movie_actors, and actors tables, aggregation functions, and proper filtering conditions.

**Example:**
```sql
-- Database Schema
CREATE TABLE movies (
    movie_id INT PRIMARY KEY,
    title VARCHAR(255),
    budget DECIMAL(15,2),
    revenue DECIMAL(15,2),
    imdb_rating DECIMAL(3,1),
    release_year INT
);

CREATE TABLE actors (
    actor_id INT PRIMARY KEY,
    actor_name VARCHAR(255)
);

CREATE TABLE movie_actors (
    movie_id INT,
    actor_id INT,
    role_type VARCHAR(50), -- 'lead', 'supporting', etc.
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    FOREIGN KEY (actor_id) REFERENCES actors(actor_id)
);

-- Query to find top 10 lead actors with most profitable movies (IMDb ≥ 8)
SELECT 
    a.actor_id,
    a.actor_name,
    COUNT(DISTINCT m.movie_id) AS movie_count,
    SUM(m.revenue - m.budget) AS total_profit,
    AVG(m.imdb_rating) AS avg_rating
FROM actors a
INNER JOIN movie_actors ma ON a.actor_id = ma.actor_id
INNER JOIN movies m ON ma.movie_id = m.movie_id
WHERE m.imdb_rating >= 8.0
    AND ma.role_type = 'lead'
    AND m.revenue > 0
    AND m.budget > 0
GROUP BY a.actor_id, a.actor_name
HAVING SUM(m.revenue - m.budget) > 0
ORDER BY total_profit DESC
LIMIT 10;

-- Alternative with CTE for better readability
WITH profitable_movies AS (
    SELECT 
        movie_id,
        title,
        (revenue - budget) AS profit,
        imdb_rating
    FROM movies
    WHERE imdb_rating >= 8.0
        AND revenue > budget
        AND revenue > 0
        AND budget > 0
)
SELECT 
    a.actor_id,
    a.actor_name,
    COUNT(pm.movie_id) AS profitable_movie_count,
    SUM(pm.profit) AS total_profit,
    ROUND(AVG(pm.imdb_rating), 2) AS avg_rating
FROM actors a
INNER JOIN movie_actors ma ON a.actor_id = ma.actor_id
INNER JOIN profitable_movies pm ON ma.movie_id = pm.movie_id
WHERE ma.role_type = 'lead'
GROUP BY a.actor_id, a.actor_name
ORDER BY total_profit DESC
LIMIT 10;
```

---

## 57. How would you define profitability in the query?

**Answer:**
Profitability in a movie database is typically defined as the difference between revenue and budget (revenue - budget), representing the net profit. You can also calculate profit margin as (revenue - budget) / budget * 100 for percentage return on investment. Some queries might use ROI (Return on Investment) which is (revenue / budget) to show how many times the investment was returned. For more accurate analysis, you might consider adjusted values for inflation, marketing costs, or use worldwide gross revenue. The definition should handle edge cases like zero or null budgets.

**Example:**
```sql
-- Different profitability metrics

-- 1. Simple Profit (Revenue - Budget)
SELECT 
    movie_id,
    title,
    revenue,
    budget,
    (revenue - budget) AS profit
FROM movies
WHERE revenue > 0 AND budget > 0;

-- 2. Profit Margin (Percentage)
SELECT 
    movie_id,
    title,
    revenue,
    budget,
    (revenue - budget) AS profit,
    ROUND(((revenue - budget) / budget * 100), 2) AS profit_margin_percent
FROM movies
WHERE budget > 0;

-- 3. ROI (Return on Investment)
SELECT 
    movie_id,
    title,
    revenue,
    budget,
    ROUND((revenue / budget), 2) AS roi_multiplier,
    ROUND(((revenue - budget) / budget * 100), 2) AS roi_percent
FROM movies
WHERE budget > 0;

-- 4. Comprehensive profitability with actor analysis
SELECT 
    a.actor_name,
    COUNT(m.movie_id) AS movie_count,
    SUM(m.revenue - m.budget) AS total_profit,
    AVG(m.revenue - m.budget) AS avg_profit_per_movie,
    ROUND(AVG((m.revenue - m.budget) / NULLIF(m.budget, 0) * 100), 2) AS avg_profit_margin,
    SUM(m.revenue) AS total_revenue,
    SUM(m.budget) AS total_budget
FROM actors a
JOIN movie_actors ma ON a.actor_id = ma.actor_id
JOIN movies m ON ma.movie_id = m.movie_id
WHERE m.imdb_rating >= 8.0
    AND ma.role_type = 'lead'
    AND m.budget > 0
    AND m.revenue > m.budget
GROUP BY a.actor_id, a.actor_name
ORDER BY total_profit DESC
LIMIT 10;

-- 5. Handling edge cases
SELECT 
    movie_id,
    title,
    COALESCE(revenue, 0) AS revenue,
    COALESCE(budget, 0) AS budget,
    CASE 
        WHEN budget IS NULL OR budget = 0 THEN NULL
        WHEN revenue IS NULL THEN -budget
        ELSE (revenue - budget)
    END AS profit,
    CASE 
        WHEN budget > 0 AND revenue > 0 
        THEN ROUND(((revenue - budget) / budget * 100), 2)
        ELSE NULL
    END AS profit_margin
FROM movies;
```

---

## 58. How would you optimize the query for large datasets?

**Answer:**
For large datasets with millions of records, optimization involves creating appropriate indexes on frequently queried columns (imdb_rating, role_type, actor_id, movie_id), using query execution plans to identify bottlenecks, partitioning tables by year or rating ranges, implementing materialized views for pre-computed aggregations, using database-specific optimizations like query hints, limiting result sets early in the query, avoiding SELECT *, and considering denormalization for read-heavy operations. You can also implement caching layers with Redis, use read replicas for analytics queries, and employ database sharding for horizontal scaling.

**Example:**
```sql
-- 1. Create Indexes for optimization
CREATE INDEX idx_movies_rating ON movies(imdb_rating);
CREATE INDEX idx_movies_revenue_budget ON movies(revenue, budget);
CREATE INDEX idx_movie_actors_role ON movie_actors(role_type, actor_id);
CREATE INDEX idx_movie_actors_composite ON movie_actors(movie_id, actor_id, role_type);
CREATE INDEX idx_movies_composite ON movies(imdb_rating, revenue, budget);

-- 2. Analyze query execution plan
EXPLAIN ANALYZE
SELECT 
    a.actor_name,
    SUM(m.revenue - m.budget) AS total_profit
FROM actors a
INNER JOIN movie_actors ma ON a.actor_id = ma.actor_id
INNER JOIN movies m ON ma.movie_id = m.movie_id
WHERE m.imdb_rating >= 8.0 AND ma.role_type = 'lead'
GROUP BY a.actor_id, a.actor_name
ORDER BY total_profit DESC
LIMIT 10;

-- 3. Optimized query with filtering early
SELECT 
    a.actor_id,
    a.actor_name,
    SUM(profit) AS total_profit,
    COUNT(*) AS movie_count
FROM (
    SELECT 
        ma.actor_id,
        (m.revenue - m.budget) AS profit
    FROM movies m
    INNER JOIN movie_actors ma ON m.movie_id = ma.movie_id
    WHERE m.imdb_rating >= 8.0
        AND ma.role_type = 'lead'
        AND m.revenue > m.budget
) AS filtered_data
INNER JOIN actors a ON filtered_data.actor_id = a.actor_id
GROUP BY a.actor_id, a.actor_name
ORDER BY total_profit DESC
LIMIT 10;

-- 4. Materialized View for pre-computed results
CREATE MATERIALIZED VIEW mv_actor_profitability AS
SELECT 
    a.actor_id,
    a.actor_name,
    COUNT(m.movie_id) AS movie_count,
    SUM(m.revenue - m.budget) AS total_profit,
    AVG(m.imdb_rating) AS avg_rating
FROM actors a
INNER JOIN movie_actors ma ON a.actor_id = ma.actor_id
INNER JOIN movies m ON ma.movie_id = m.movie_id
WHERE m.imdb_rating >= 8.0
    AND ma.role_type = 'lead'
    AND m.revenue > m.budget
GROUP BY a.actor_id, a.actor_name;

-- Refresh materialized view periodically
REFRESH MATERIALIZED VIEW mv_actor_profitability;

-- Query the materialized view (much faster)
SELECT * FROM mv_actor_profitability
ORDER BY total_profit DESC
LIMIT 10;

-- 5. Partitioning strategy
CREATE TABLE movies_partitioned (
    movie_id INT,
    title VARCHAR(255),
    budget DECIMAL(15,2),
    revenue DECIMAL(15,2),
    imdb_rating DECIMAL(3,1),
    release_year INT
) PARTITION BY RANGE (release_year) (
    PARTITION p_before_2000 VALUES LESS THAN (2000),
    PARTITION p_2000_2010 VALUES LESS THAN (2010),
    PARTITION p_2010_2020 VALUES LESS THAN (2020),
    PARTITION p_2020_onwards VALUES LESS THAN MAXVALUE
);

-- 6. Using query hints (database-specific)
-- PostgreSQL
SELECT /*+ SeqScan(movies) */ * FROM movies WHERE imdb_rating >= 8.0;

-- MySQL
SELECT SQL_NO_CACHE actor_name FROM actors WHERE actor_id IN (
    SELECT DISTINCT actor_id FROM movie_actors USE INDEX (idx_movie_actors_role)
    WHERE role_type = 'lead'
);
```

---

## 59. What indexing strategy would you use?

**Answer:**
The indexing strategy should include composite indexes on frequently joined and filtered columns, covering indexes that include all columns needed in a query to avoid table lookups, indexes on foreign keys for join optimization, and filtered indexes for specific conditions. For this movies query, create indexes on movies(imdb_rating, revenue, budget), movie_actors(role_type, actor_id, movie_id), and actors(actor_id). Use B-tree indexes for range queries and equality checks, consider bitmap indexes for low-cardinality columns, and monitor index usage to remove unused indexes that slow down writes.

**Example:**
```sql
-- 1. Primary Indexes (already exist on primary keys)
-- movies(movie_id), actors(actor_id)

-- 2. Foreign Key Indexes
CREATE INDEX idx_movie_actors_movie_id ON movie_actors(movie_id);
CREATE INDEX idx_movie_actors_actor_id ON movie_actors(actor_id);

-- 3. Composite Index for filtering (most selective columns first)
CREATE INDEX idx_movies_rating_revenue_budget 
ON movies(imdb_rating, revenue, budget);

-- 4. Composite Index for joins and filtering
CREATE INDEX idx_movie_actors_role_actor 
ON movie_actors(role_type, actor_id, movie_id);

-- 5. Covering Index (includes all columns needed in query)
CREATE INDEX idx_movies_covering 
ON movies(imdb_rating, movie_id, revenue, budget, title);

-- 6. Partial/Filtered Index (only for high-rated movies)
CREATE INDEX idx_movies_high_rated 
ON movies(movie_id, revenue, budget)
WHERE imdb_rating >= 8.0;

-- 7. Index on computed column (if supported)
CREATE INDEX idx_movies_profit 
ON movies((revenue - budget))
WHERE imdb_rating >= 8.0;

-- 8. Index monitoring and analysis
-- Check index usage (PostgreSQL)
SELECT 
    schemaname,
    tablename,
    indexname,
    idx_scan AS index_scans,
    idx_tup_read AS tuples_read,
    idx_tup_fetch AS tuples_fetched
FROM pg_stat_user_indexes
WHERE schemaname = 'public'
ORDER BY idx_scan ASC;

-- Find unused indexes
SELECT 
    schemaname,
    tablename,
    indexname
FROM pg_stat_user_indexes
WHERE idx_scan = 0
    AND indexname NOT LIKE '%_pkey';

-- 9. Index maintenance
-- Rebuild fragmented indexes
REINDEX TABLE movies;
ANALYZE movies;

-- Update statistics
ANALYZE TABLE movies;
ANALYZE TABLE movie_actors;
ANALYZE TABLE actors;

-- 10. Index strategy summary for the query
/*
Recommended indexes:
1. movies(imdb_rating, revenue, budget) - for WHERE clause filtering
2. movie_actors(role_type, actor_id) - for filtering and grouping
3. movie_actors(movie_id, actor_id) - for joins
4. actors(actor_id) - already exists as PK

These indexes will:
- Speed up filtering by imdb_rating >= 8.0
- Optimize joins between tables
- Accelerate GROUP BY operations
- Improve ORDER BY performance
*/
```

---

## 60. How would you handle constraints and performance optimization?

**Answer:**
Constraints ensure data integrity through primary keys, foreign keys, unique constraints, check constraints, and not null constraints. For performance optimization with constraints, use deferred constraint checking for bulk operations, consider disabling constraints during large data loads and re-enabling them after, implement application-level validation to reduce database load, use triggers judiciously as they impact write performance, and balance between data integrity and performance needs. Monitor constraint violations, use appropriate data types to minimize storage, implement connection pooling, and use batch operations for bulk inserts.

**Example:**
```sql
-- 1. Define Constraints
CREATE TABLE movies (
    movie_id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    budget DECIMAL(15,2) CHECK (budget >= 0),
    revenue DECIMAL(15,2) CHECK (revenue >= 0),
    imdb_rating DECIMAL(3,1) CHECK (imdb_rating >= 0 AND imdb_rating <= 10),
    release_year INT CHECK (release_year >= 1888 AND release_year <= YEAR(CURRENT_DATE)),
    CONSTRAINT chk_profit CHECK (revenue >= budget OR budget = 0)
);

CREATE TABLE actors (
    actor_id INT PRIMARY KEY,
    actor_name VARCHAR(255) NOT NULL,
    birth_year INT,
    CONSTRAINT uq_actor_name UNIQUE (actor_name)
);

CREATE TABLE movie_actors (
    movie_id INT,
    actor_id INT,
    role_type VARCHAR(50) NOT NULL CHECK (role_type IN ('lead', 'supporting', 'cameo')),
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE,
    FOREIGN KEY (actor_id) REFERENCES actors(actor_id) ON DELETE CASCADE
);

-- 2. Deferred Constraints for bulk operations
BEGIN TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;

-- Bulk insert operations
INSERT INTO movies VALUES (...);
INSERT INTO movie_actors VALUES (...);

COMMIT; -- Constraints checked here

-- 3. Disable constraints for large data loads (use carefully)
ALTER TABLE movie_actors DISABLE TRIGGER ALL;
-- Bulk load data
COPY movie_actors FROM '/path/to/data.csv' CSV;
-- Re-enable and validate
ALTER TABLE movie_actors ENABLE TRIGGER ALL;

-- 4. Performance optimization techniques
-- Use EXPLAIN to analyze query performance
EXPLAIN (ANALYZE, BUFFERS) 
SELECT a.actor_name, SUM(m.revenue - m.budget) AS profit
FROM actors a
JOIN movie_actors ma ON a.actor_id = ma.actor_id
JOIN movies m ON ma.movie_id = m.movie_id
WHERE m.imdb_rating >= 8.0
GROUP BY a.actor_name
ORDER BY profit DESC
LIMIT 10;

-- 5. Batch operations for better performance
-- Instead of individual inserts
INSERT INTO movies VALUES (1, 'Movie1', 1000000, 5000000, 8.5, 2020);
INSERT INTO movies VALUES (2, 'Movie2', 2000000, 8000000, 9.0, 2021);

-- Use batch insert
INSERT INTO movies (movie_id, title, budget, revenue, imdb_rating, release_year) VALUES
(1, 'Movie1', 1000000, 5000000, 8.5, 2020),
(2, 'Movie2', 2000000, 8000000, 9.0, 2021),
(3, 'Movie3', 1500000, 6000000, 8.8, 2022);

-- 6. Connection pooling configuration (application.properties)
-- spring.datasource.hikari.maximum-pool-size=20
-- spring.datasource.hikari.minimum-idle=5
-- spring.datasource.hikari.connection-timeout=30000
-- spring.datasource.hikari.idle-timeout=600000
-- spring.datasource.hikari.max-lifetime=1800000

-- 7. Query optimization with proper data types
-- Use appropriate data types to save space
ALTER TABLE movies MODIFY COLUMN imdb_rating DECIMAL(3,1); -- Instead of FLOAT
ALTER TABLE movies MODIFY COLUMN release_year SMALLINT; -- Instead of INT

-- 8. Implement caching strategy
-- Application-level caching with Spring Boot
```

```java
// Spring Boot caching configuration
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("actors", "movies");
    }
}

@Service
public class MovieService {
    
    @Cacheable(value = "actors", key = "#actorId")
    public Actor getActor(Long actorId) {
        return actorRepository.findById(actorId).orElseThrow();
    }
    
    @Cacheable(value = "movies", key = "'top10profitable'")
    public List<ActorProfitDTO> getTop10ProfitableActors() {
        return jdbcTemplate.query(
            "SELECT a.actor_name, SUM(m.revenue - m.budget) AS total_profit " +
            "FROM actors a " +
            "JOIN movie_actors ma ON a.actor_id = ma.actor_id " +
            "JOIN movies m ON ma.movie_id = m.movie_id " +
            "WHERE m.imdb_rating >= 8.0 AND ma.role_type = 'lead' " +
            "GROUP BY a.actor_name " +
            "ORDER BY total_profit DESC LIMIT 10",
            new ActorProfitRowMapper()
        );
    }
    
    @CacheEvict(value = "movies", allEntries = true)
    @Scheduled(fixedRate = 3600000) // Clear cache every hour
    public void clearCache() {
        // Cache cleared automatically
    }
}

// Pagination for large result sets
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT new com.example.dto.ActorProfitDTO(a.actorName, SUM(m.revenue - m.budget)) " +
           "FROM Actor a " +
           "JOIN a.movieActors ma " +
           "JOIN ma.movie m " +
           "WHERE m.imdbRating >= 8.0 AND ma.roleType = 'lead' " +
           "GROUP BY a.actorName " +
           "ORDER BY SUM(m.revenue - m.budget) DESC")
    Page<ActorProfitDTO> findTop10ProfitableActors(Pageable pageable);
}
```

---

# ✅ Web Technologies

## 61. What is the difference between Servlet and JSP?

**Answer:**
Servlet is a Java class that handles HTTP requests and responses programmatically, where you write Java code to generate HTML output. JSP (JavaServer Pages) is a text-based document that allows you to embed Java code within HTML using special tags, making it easier to create dynamic web pages. Servlets are better for business logic and controller layer, while JSP is better for presentation layer. JSP is internally converted to a Servlet by the container. Servlets require recompilation for changes, while JSP changes are automatically detected. Modern applications use Servlets with template engines or REST APIs instead of JSP.

**Example:**
```java
// SERVLET - Java code with HTML embedded
@WebServlet("/users")
public class UserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Business logic
        List<User> users = getUsersFromDatabase();
        
        // Generate HTML in Java code
        out.println("<html>");
        out.println("<head><title>Users</title></head>");
        out.println("<body>");
        out.println("<h1>User List</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Email</th></tr>");
        
        for (User user : users) {
            out.println("<tr>");
            out.println("<td>" + user.getId() + "</td>");
            out.println("<td>" + user.getName() + "</td>");
            out.println("<td>" + user.getEmail() + "</td>");
            out.println("</tr>");
        }
        
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        User user = new User(name, email);
        saveUser(user);
        
        response.sendRedirect("users");
    }
    
    private List<User> getUsersFromDatabase() {
        // Database logic
        return Arrays.asList(
            new User(1, "John", "john@example.com"),
            new User(2, "Jane", "jane@example.com")
        );
    }
}
```

```jsp
<!-- JSP - HTML with Java code embedded -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.example.model.User" %>

<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <h1>User List</h1>
    
    <%
        // Java code in JSP (Scriptlet)
        List<User> users = (List<User>) request.getAttribute("users");
    %>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        
        <% for (User user : users) { %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
            </tr>
        <% } %>
    </table>
    
    <!-- Using JSTL (better practice) -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <h2>User List with JSTL</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
    
    <!-- Form submission -->
    <h2>Add New User</h2>
    <form action="users" method="post">
        Name: <input type="text" name="name" required><br>
        Email: <input type="email" name="email" required><br>
        <input type="submit" value="Add User">
    </form>
</body>
</html>
```

```java
// Modern approach: Servlet as Controller + JSP as View (MVC Pattern)
@WebServlet("/users")
public class UserController extends HttpServlet {
    
    private UserService userService = new UserService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Controller logic - fetch data
        List<User> users = userService.getAllUsers();
        
        // Set data as request attribute
        request.setAttribute("users", users);
        
        // Forward to JSP for presentation
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        userService.createUser(new User(name, email));
        
        response.sendRedirect("users");
    }
}
```

**Key Differences Summary:**

| Aspect | Servlet | JSP |
|--------|---------|-----|
| **Type** | Java class | Text document with HTML |
| **Purpose** | Business logic, Controller | Presentation, View |
| **Code Style** | Java code with HTML strings | HTML with embedded Java |
| **Compilation** | Compiled by developer | Auto-compiled by container |
| **Modification** | Requires recompilation | Auto-reloaded on change |
| **Performance** | Slightly faster | Converted to Servlet first |
| **Best Use** | Request handling, logic | UI rendering, display |
| **Maintainability** | Hard to maintain HTML | Easier for designers |

**Modern Alternative:**
```java
// Modern Spring Boot approach (no JSP/Servlet needed)
@RestController
@RequestMapping("/api/users")
public class UserRestController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.createUser(user));
    }
}

// Frontend handles presentation (React, Angular, Vue)
```
