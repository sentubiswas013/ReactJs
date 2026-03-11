# **1️⃣ Personal Questions**

## **Q0. Tell me about yourself (Java Developer)**

I am a Java Full Stack Developer with around **10+ years of experience** in designing and developing scalable web applications. My primary expertise is in **Java, Spring Boot, and microservices architecture** for backend development, and **React or Angular** for frontend development.

In my current role, I work on building **RESTful APIs using Spring Boot**, implementing business logic, and integrating services with **databases like MySQL or PostgreSQL**. On the frontend side, I develop responsive user interfaces and integrate them with backend APIs.

I also have experience with **microservices architecture**, where I work with tools like **Spring Cloud, Feign Client, Kafka for event-driven communication, and Redis for caching**. For security, I have implemented **JWT-based authentication using Spring Security**.

From a DevOps perspective, I have worked with **Docker, Kubernetes, and CI/CD pipelines using Jenkins or GitHub Actions**, and deployed applications on **cloud platforms like AWS**.

I follow **clean coding practices, unit testing with JUnit and Mockito, and Agile development methodologies**. I enjoy solving performance issues, improving system scalability, and collaborating with cross-functional teams to deliver reliable software solutions.


## **Q1. Current role responsibilities & day-to-day activity (Java Developer)**

In my current role, I mainly develop **REST APIs using Java and Spring Boot**, implement business logic, and work with **microservices architecture**.

I handle **database operations with MySQL/PostgreSQL**, and on the frontend side I build **UI components using React or Angular**.

I also write **unit tests, participate in code reviews, and support CI/CD deployments using Docker and Jenkins**.

Additionally, I work closely with QA and product teams in **Agile ceremonies like daily stand-ups and sprint planning** to deliver features efficiently.


## **Q2. Explain agile stategy**

In **Agile**, teams usually have **4–5 regular meetings (ceremonies/calls)** in each **Scrum** sprint.

1. **Sprint Planning :**
**Purpose:** Decide **what work will be done in the sprint** and how the team will complete it.
**Participants:** Product Owner, Scrum Master, Development Team.

2. **Daily Stand-up (Daily Scrum) :**
**Purpose:** Quick **15-minute daily call** to discuss:

* What you did yesterday
* What you will do today
* Any blockers

3. **Sprint Review :**
**Purpose:** Demonstrate the **completed work to stakeholders** and get feedback.

4. **Sprint Retrospective :**
**Purpose:** Team discusses **what went well, what didn’t, and improvements for next sprint**.

5. **Backlog Refinement (Grooming)  *(optional but common)* : **
**Purpose:** Review and **clarify upcoming backlog items** so they are ready for future sprints.


# **2️⃣ Core Java – Practical / Real-World Scenarios**

## **Q3. Your service is slow under high load. How did you identify and fix the performance issue?**

> When the service became slow under high load, I first monitored **metrics and logs using Spring Boot Actuator, Prometheus, and Grafana** to identify the bottleneck.
> I discovered that the issue was caused by **slow database queries and an N+1 problem in JPA**.
> I fixed it by **optimizing queries, adding indexes, implementing pagination, and introducing Redis caching** for frequently used data.
> After these improvements, the application performance improved significantly.

**Fixes Applied:**

* JProfiler / VisualVM
* JVM thread dumps
* Pagination
* Query optimization
* Redis caching
* Lazy loading fixes

**Short Example Code (Caching):**

```java
@Cacheable(value = "users", key = "#id")
public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow();
}
```

## **Q4. Your Java service started consuming high CPU in production. How did you identify the root cause and fix it?**

> When CPU usage spiked in production, I first checked **monitoring metrics and logs** to confirm the issue.
> Then I captured a **thread dump using jstack** and analyzed it with **VisualVM**.
> I found a thread stuck in an **inefficient loop caused by improper stream processing**.
> After fixing the logic and optimizing the code, the CPU usage returned to normal.

**Good tools to mention in interviews:**

* `jstack` → thread dump
* `top` / `htop` → OS-level CPU monitoring
* **VisualVM / JProfiler** → JVM analysis
* **Prometheus + Grafana** → metrics monitoring
* **Spring Boot Actuator** → application health metrics

**Example Code (Bug Fix):**

```java
// BAD: infinite loop
while(list.stream().anyMatch(x -x.isActive())) {
    process();
}

// FIXED
list.stream()
    .filter(Item::isActive)
    .forEach(this::process);
```

## **Q5. Describe a situation where you faced a memory leak in Java. How did you detect and resolve it?**

> In one project, we noticed that **heap memory usage kept increasing and the application eventually crashed with OutOfMemoryError**.
> I captured a **heap dump using jmap** and analyzed it with **Eclipse MAT**.
> The root cause was a **static map holding references to objects that were never cleared**.
> I fixed it by removing unnecessary static references and using a **WeakHashMap-based caching approach**, which allowed garbage collection to free unused objects.

### **Tools Mentioned (Good for Interview)**

* `jmap` → capture heap dump
* **Eclipse MAT** → analyze memory usage
* **VisualVM / JProfiler** → memory profiling
* **Spring Boot Actuator** → monitor JVM memory metrics

**Example Code (Fix):**

```java
Map<String, Usercache = new WeakHashMap<();
```

## **Q6. How did you handle concurrency issues in a multi-threaded Java application you worked on? Give a real example.**

**Way One**
“In one of my projects, we had a **payment processing service** where multiple threads were updating the same user wallet balance at the same time. This caused **race conditions**, leading to incorrect balances.

To handle this, I first identified the shared critical section where the balance was being read and updated. I used **synchronization and concurrent utilities** to make the operation thread-safe.

For example, instead of doing a simple read-modify-write, I used a **`ReentrantLock`** to ensure only one thread could update a user’s balance at a time. In some cases where performance mattered, I used **`AtomicInteger` / `AtomicLong`** for counters and **`ConcurrentHashMap`** for shared in-memory data.

Here’s a simplified example from that scenario:”

```java
class WalletService {
    private final ReentrantLock lock = new ReentrantLock();
    private int balance = 1000;

    public void deductAmount(int amount) {
        lock.lock();
        try {
            if (balance = amount) {
                balance -= amount;
            }
        } finally {
            lock.unlock();
        }
    }
}
```
“Additionally, we avoided long synchronized blocks, reduced lock scope, and used **thread pools** (`ExecutorService`) instead of creating threads manually. This ensured data consistency while keeping the system performant under high load.”

**Way two**
I solved this by using **synchronization** and **database-level locking**. In some cases, I used **Optimistic Locking with @Version**, and for in-memory operations, I used **Atomic classes** and synchronized blocks.

**Short Example Code (Optimistic Locking):**

```java
@Entity
public class Account {
    @Id
    private Long id;

    @Version
    private int version; // Used by JPA for optimistic locking

    private BigDecimal balance;
}
```

**Atomic Example:**

```java
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();
```

## **Q7. You had to process a large file (millions of records). How did you design the Java code to avoid OutOfMemory errors?**

To process millions of records, I avoided loading the entire file into memory.

I used **streaming with BufferedReader**, processed data line by line, and batch-inserted records into the database.

This ensured constant memory usage.

**Example Code:**

```java
try (BufferedReader br = Files.newBufferedReader(path)) {
    String line;
    while ((line = br.readLine()) != null) {
        process(line);
    }
}
```

## 9.  Interview Question We have a table **`bollywood_movies`** with 10,00,000 records.
**Columns:**

* id (NOT NULL)
* movie_name
* lead_actor_name
* budget
* movie_collections
* imdb_rating

**Constraints:**

* imdb_rating should be ≥ 8
* Movie should be profitable (collections budget)
* Only id is NOT NULL
* Data comes from vendor API

**Requirement:**

Find **Top 10 most profitable lead actors** (based on total profit)
and fetch the **details of the movies they have done**
Use **cursor and temporary table** approach.

**Database Level Optimization (Very Important for 10L records)**

Since data is large (10,00,000+ records), we must:

### - Add Indexes

```sql
CREATE INDEX idx_imdb_rating ON bollywood_movies(imdb_rating);
CREATE INDEX idx_lead_actor ON bollywood_movies(lead_actor_name);
CREATE INDEX idx_profit ON bollywood_movies(movie_collections, budget);
```

**Profit Calculation Logic**

Profit =

```
movie_collections - budget
```

Only consider:

```sql
WHERE imdb_rating = 8
AND movie_collections budget
```

**Optimized SQL Query (Best Practice – DB Level Aggregation)**

We should NOT fetch 10 lakh records into Java memory.

**Step 1: Get Top 10 Profitable Actors**

```sql
SELECT lead_actor_name,
       SUM(movie_collections - budget) AS total_profit
FROM bollywood_movies
WHERE imdb_rating = 8
AND movie_collections budget
AND lead_actor_name IS NOT NULL
GROUP BY lead_actor_name
ORDER BY total_profit DESC
LIMIT 10;
```

**Step 2: Fetch Movie Details of These Actors**

```sql
SELECT *
FROM bollywood_movies
WHERE lead_actor_name IN (top 10 actors)
AND imdb_rating = 8
AND movie_collections budget;
```

**Java Implementation (Spring Boot Style – Interview Level)**

### Entity Class

```java
@Entity
@Table(name = "bollywood_movies")
public class Movie {

    @Id
    private Long id;

    private String movieName;
    private String leadActorName;
    private Double budget;
    private Double movieCollections;
    private Double imdbRating;
}
```

### Repository (Using Native Query for Performance)

```java
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long{

    @Query(value = """
        SELECT lead_actor_name
        FROM bollywood_movies
        WHERE imdb_rating = 8
        AND movie_collections budget
        GROUP BY lead_actor_name
        ORDER BY SUM(movie_collections - budget) DESC
        LIMIT 10
        """, nativeQuery = true)
    List<StringfindTop10ProfitableActors();

    List<MoviefindByLeadActorNameInAndImdbRatingGreaterThanEqualAndMovieCollectionsGreaterThan(
            List<Stringactors, Double rating, Double collections);
}
```

### Service Layer

```java
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Map<String, List<MoviegetTopActorsWithMovies() {

        List<StringtopActors = movieRepository.findTop10ProfitableActors();

        List<Moviemovies = movieRepository
                .findByLeadActorNameInAndImdbRatingGreaterThanEqualAndMovieCollectionsGreaterThan(
                        topActors, 8.0, 0.0);

        return movies.stream()
                .collect(Collectors.groupingBy(Movie::getLeadActorName));
    }
}
```

**If Data Comes From Vendor API**

### Approach:

1. Fetch data in **pagination** (never load 10L at once)
2. Save into DB in batch
3. Process via DB query

```java
for(int page = 0; page < totalPages; page++) {
    List<MovieDTOmovies = vendorApi.fetchMovies(page);
    movieRepository.saveAll(movies);
}
```

Use:

```properties
spring.jpa.properties.hibernate.jdbc.batch_size=1000
```

**Performance & Scalability Points (Important for Interview)**

- Filtering & aggregation in DB (not Java memory)
- Use Indexes
- Use Pagination for Vendor API
- Use Batch Insert
- Use Native Query for heavy aggregation
- Use Projection DTO instead of full entity
- Use Read-only transaction

**Optional Advanced Optimization (Senior Level Answer)**

If query runs frequently:

* Create **Materialized View**
* Or maintain **precomputed profit table**
* Use Redis Cache for Top 10 actors

**Final Interview Summary Answer (Short Version)**

- Since we have 10 lakh records, I will push filtering and aggregation logic to the database using indexed columns.
- I will calculate profit as collections - budget and filter imdb -= 8 and profitable movies.
- Then group by lead actor and get top 10 by total profit.
- After that, fetch movie details for those actors.
- Data from vendor API will be inserted using batch processing and pagination.
- I will avoid loading large data into memory and ensure performance using indexing and native queries.


## 18. How to implement many to many, many to one and one to many in java interiew questions, give answer
**01. One-To-Many**

One **Order → Many Items**

**Order Entity**

```java
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String orderNumber;

    @OneToMany(mappedBy = "order")
    private List<Item> items;
}
```

**Item Entity**

```java
import jakarta.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
```

**02. Many-To-One**

Many **Employees → One Department**

### Department Entity

```java
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
```

**Employee Entity**

```java
import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
```

**03. Many-To-Many**

Many **Students ↔ Many Courses**

### Student Entity

```java
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
```

**Course Entity**

```java
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}
```

> In JPA relationships are defined using annotations like **@OneToMany, @ManyToOne, and @ManyToMany**.
> The owning side uses **@JoinColumn**, while the inverse side uses **mappedBy**, and for many-to-many we use **@JoinTable** to create a join table.


## **Q8. How to implement crud aplication give example code.**

A CRUD service handles Create, Read, Update, Delete operations with proper validation and error handling. I'll use JPA repository for database operations and add business logic for validation.

**Main Application**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(
        exclude = DataSourceAutoConfiguration.class
)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); // fixed args
        System.out.println("Hello World");
    }
}
```

**User Entity**

```java
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 12, nullable = false)
    private String username;

    @Column(name = "email", length = 300)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Roles getRole() { return role; }
    public void setRole(Roles role) { this.role = role; }
}
```

**Roles Entity**

```java
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

    public Roles() {}
    public Roles(String role) {
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
```

**Repository**

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long{

}
```

**Service**

```java
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create user
    public User createUser(String username, String email) {
        User user = new User(username, email);
        return userRepository.save(user);
    }

    // Fetch all users
    public List<UsergetUsers() {
        try {
            callExternalAPI();
            return userRepository.findAll();
        } catch (IOException e) {
            throw new RuntimeException("External API failed", e);
        } finally {
            System.out.println("Completed");
        }
    }

    // Simulated external API call
    private void callExternalAPI() throws IOException {
        // External API logic here
        System.out.println("Calling external API...");
    }
}
```

**Controller**

```java
import org.springframework.web.bind.annotation.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create User
    @PostMapping
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackCreateUser")
    @Retry(name = "user-service")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user.getUsername(), user.getEmail());
    }

    // Get All Users
    @GetMapping
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackGetUsers")
    @Retry(name = "user-service")
    public List<UsergetAllUsers() {
        return userService.getUsers();
    }

    // Fallback for createUser
    public User fallbackCreateUser(User user, Exception ex) {
        User fallback = new User();
        fallback.setUsername("fallback-user");
        fallback.setEmail("fallback@email.com");
        return fallback;
    }

    // Fallback for getAllUsers
    public List<UserfallbackGetUsers(Exception ex) {
        return List.of(new User("fallback-user", "fallback@email.com"));
    }
}
```

## **Q9. How do you migrate a legacy Java application to a newer Java version?**
> Migrating a legacy Java application to a newer Java version requires a step-by-step approach to avoid breaking existing functionality.
>
> First, I **analyze the current Java version and dependencies** used in the project and check compatibility with the target Java version.
>
> Next, I update the **build configuration** such as **Maven or Gradle** to use the new Java version and upgrade outdated libraries that may not support the newer JDK.
>
> Then I run the application and fix **deprecated APIs or removed features**. Tools like **jdeps and jlink** help identify incompatible modules.
>
> After that, I execute **unit tests and integration tests** to ensure that the application behaves correctly.
>
> Finally, I perform **performance testing and deploy the upgraded application in a staging environment** before releasing it to production.

**Analyze Current Application**

* Identify current Java version (e.g., Java 8)
* Review dependencies and frameworks

**Check Compatibility**

* Verify libraries support new Java version
* Upgrade frameworks (Spring Boot, Hibernate, etc.)

**Update Build Configuration**

Example (Maven):

```xml
<properties>
    <java.version>17</java.version>
</properties>
```

**Fix Deprecated APIs**

Example:

```java
// Old
Date date = new Date();

// New (Recommended)
LocalDate date = LocalDate.now();
```

**Run Static Analysis Tools**

* `jdeps` → find dependency issues
* `jdeprscan` → detect deprecated APIs

**Testing**

* Unit testing
* Integration testing
* Regression testing

**Deploy to Staging**

* Validate performance
* Monitor logs and memory usage

**Production Release**

* Gradual rollout
* Monitor metrics


## **Q10. Exception handling and logging in large applications**

“In large systems, bad logging is as dangerous as no logging. I used **centralized exception handling**, meaningful log levels, and correlation IDs. This made production debugging much easier.”

**Best Practices Used:**

* Global exception handler
* SLF4J + Logback
* Structured logs
* Avoided `e.printStackTrace()`

**Example:**

```java
try {
    processPayment();
} catch (PaymentException e) {
    logger.error("Payment failed for orderId={}", orderId, e);
    throw e;
}
```

**Spring Global Handler Example:**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Stringhandle(Exception ex) {
        return ResponseEntity.status(500).body("Internal Error");
    }
}
```


# **3️⃣ Spring Boot – Hands-On Implementation Questions**

## **Q13. Real scenario: Securing REST APIs using Spring Security**

> In one of our projects, we needed to secure REST APIs so that only authenticated users could access them.
> We implemented **Spring Security with JWT (JSON Web Token)** for authentication and authorization.
>
> First, users authenticate using **username and password** through a login API. After successful authentication, the server generates a **JWT token**.
> The client then sends this token in the **Authorization header** with each API request.
>
> Spring Security validates the token for every request using a **JWT filter**. If the token is valid, the request is allowed; otherwise, access is denied.
>
> We also implemented **role-based access control** so that certain APIs are accessible only to specific roles like **ADMIN or USER**.
>
> This approach ensures that our APIs are **stateless, secure, and scalable**.


**1 Spring Security Configuration**

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }
}
```


**2 Login Controller**

```java
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        // validate username/password

        String token = jwtService.generateToken(request.getUsername());

        return token;
    }
}
```

**3 JWT Token Generation**

```java
@Service
public class JwtService {

    private String SECRET_KEY = "secret";

    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
```


**4 Secured API Example**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<String> getUsers() {
        return List.of("John", "Alice");
    }
}
```

Request must include header:

```
Authorization: Bearer <JWT_TOKEN>
```

**Security Flow**

1️⃣ User sends **login request**
2️⃣ Server **validates credentials**
3️⃣ Server generates **JWT token**
4️⃣ Client sends **token in Authorization header**
5️⃣ Spring Security **validates token** before allowing access

## **Q14. Externalizing configuration for different environments**

“We had separate environments like dev, QA, and prod. We externalized configuration using **Spring Profiles** so that environment-specific values like database URLs and API keys could be managed safely.”

**How We Did It:**

* `application-dev.yml`
* `application-qa.yml`
* `application-prod.yml`
* Active profile set via environment variable

**Example Configuration:**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/devdb
```

**Activate Profile:**

```bash
-Dspring.profiles.active=prod
```

**Accessing Properties:**

```java
@Value("${spring.datasource.url}")
private String dbUrl;
```

## **Q15. Pagination, sorting, and filtering in Spring Boot**

“For APIs returning large datasets, we implemented pagination and sorting using **Spring Data JPA**. Filtering was done using query parameters. This improved performance and user experience.”

**Controller Example:**

```java
@GetMapping("/products")
public Page<ProductgetProducts(
        @RequestParam int page,
        @RequestParam int size,
        @RequestParam(defaultValue = "name") String sortBy) {

    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return productRepository.findAll(pageable);
}
```

**Filtering Example:**

```java
@GetMapping("/products/search")
public List<Productsearch(@RequestParam String category) {
    return productRepository.findByCategory(category);
}
```

# **4️⃣ Microservices – Production Scenarios**

## **Q16. One microservice is down. How did you prevent system failure?**

In a microservices-based system, I used **Resilience patterns** to avoid cascading failures.

I implemented **Circuit Breaker**, **Retry**, and **Fallback mechanisms** using **Resilience4j**.
If a dependent service goes down, the circuit breaker opens and returns a fallback response instead of failing the entire system.

**What We Used:**

* Circuit breaker
* Fallback responses
* Timeouts
* Cached data

**Short Example Code (Circuit Breaker):**
```java
@Service
public class OrderService {

    private final InventoryClient inventoryClient;

    public OrderService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallback")
    @Retry(name = "inventoryService")
    public InventoryResponse checkInventory(String productId) {
        return inventoryClient.getInventory(productId);
    }

    public InventoryResponse fallback(String productId, Exception ex) {
        return new InventoryResponse(productId, 0, "Service unavailable");
    }
}
```

**application.yml Configuration**
```java
resilience4j:
  circuitbreaker:
    instances:
      inventoryService:
        slidingWindowSize: 10
        failureRateThreshold: 50
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 3
  retry:
    instances:
      inventoryService:
        maxAttempts: 3
        waitDuration: 2s

```

## **Q17. How did microservices communicate with each other?**

In our system, microservices mainly communicated using **REST APIs over HTTP**.
For synchronous communication, we used **Feign Client** with service discovery through **Eureka**.

For asynchronous communication, especially for event-based workflows, we used **Kafka**. This helped us reduce tight coupling and improve scalability.

**Example Code (Feign Client):**

```java
@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/payments/{orderId}")
    PaymentResponse getPayment(@PathVariable Long orderId);
}

// Service Layer
@Autowired
private PaymentClient paymentClient;

public PaymentResponse getPaymentDetails(Long orderId) {
    return paymentClient.getPayment(orderId);
}
```

Use synchronous (REST, gRPC) for immediate responses or asynchronous (messaging) for decoupling. Choose based on latency, coupling, and reliability needs.

**Example:**
```java
// Synchronous - REST
@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;
    
    public Order create(Order order) {
        Payment payment = restTemplate.postForObject(
            "http://payment-service/pay", order, Payment.class);
        return orderRepo.save(order);
    }
}

// Asynchronous - Kafka
@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, Orderkafka;
    
    public void create(Order order) {
        orderRepo.save(order);
        kafka.send("order-created", order);
    }
}
```

## **Q18. How did you handle distributed transactions?**

Since microservices have separate databases, we avoided traditional distributed transactions like 2PC.

Instead, we used the **Saga pattern**. Each service performed a local transaction and published an event. If any step failed, we executed a **compensating transaction** to rollback previous steps.

This approach ensured **eventual consistency** without blocking services.

**Example Code (Saga Event Publish):**

```java
@Transactional
public void createOrder(Order order) {
    orderRepository.save(order);
    kafkaTemplate.send("order-created", order.getId());
}
```

## **Q19. One microservice was frequently failing and impacting others. How did you isolate it?**

“One unstable service was causing cascading failures. We isolated it using **circuit breakers**, **bulkheads**, and **rate limiting**. Once failures crossed a threshold, calls to that service were stopped temporarily.”

**Isolation Techniques Used:**

* Circuit breaker
* Thread pool isolation
* Bulkhead pattern

**Example (Bulkhead):**

```java
@Bulkhead(name = "paymentService", type = Bulkhead.Type.THREADPOOL)
public PaymentResponse processPayment() {
    return paymentClient.pay();
}
```

## **Q20. How did you manage communication between microservices? Why synchronous vs asynchronous?**

“We used **both synchronous and asynchronous communication**, based on the use case.

* For real-time user requests → **REST (synchronous)**
* For event-based workflows → **Kafka (asynchronous)**”

**Why This Approach:**

* REST → simple, immediate response
* Kafka → decoupled, scalable, fault-tolerant

**REST Example:**

```java
OrderResponse response = restTemplate.getForObject(
    "http://payment-service/pay", OrderResponse.class);
```

**Kafka Producer Example:**

```java
kafkaTemplate.send("order-events", orderEvent);
```

## **Q21. Real scenario: circuit breaker, retry, timeout**

“In production, a downstream service was slow and unstable. We implemented **timeouts to avoid blocking**, **retries for transient failures**, and **circuit breakers** to stop repeated failures.”

**Configuration Example (Resilience4j):**

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        failureRateThreshold: 50
        waitDurationInOpenState: 10s
```

**Retry Example:**

```java
@Retry(name = "paymentService", maxAttempts = 3)
public PaymentResponse pay() {
    return paymentClient.pay();
}
```

## **Q22. Distributed transactions without a single DB transaction**

“We avoided distributed database transactions because they don’t scale. Instead, we used the **Saga pattern**. Each microservice performed its local transaction and published an event. If something failed, compensating actions were triggered.”

**Saga Flow Example:**

1. Order Service → creates order
2. Payment Service → charges payment
3. Inventory Service → reserves stock
4. Failure → compensating rollback

**Event Example:**

```java
kafkaTemplate.send("payment-failed", orderId);
```

**Compensation Example:**

```java
public void rollbackOrder(String orderId) {
    orderRepository.cancel(orderId);
}
```

## **Q23. Deploying multiple microservices — service discovery & routing**

As the number of services grew, hardcoded URLs became unmanageable. We solved this using **service discovery and an API gateway**. Services registered themselves dynamically, and routing was handled centrally.

**Challenges Faced:**

* Dynamic service IPs
* Load balancing
* Routing
* Versioning

**Solutions Used:**

* Eureka / Consul (service discovery)
* Spring Cloud Gateway
* Load balancing

**Service Discovery Example:**

```java
@EnableDiscoveryClient
@SpringBootApplication
public class OrderServiceApplication {
}
```

**Gateway Route Example:**

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: order-service
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/orders/**
```

# **5️⃣ Cloud (AWS / GCP) – Practical Experience**

## **Q24. How did you deploy a Spring Boot microservice to AWS/GCP? Walk me through the complete flow.**

“I’ll explain it end to end. First, we built the Spring Boot application and containerized it using Docker. Then we pushed the image to a container registry. After that, we deployed it to Kubernetes (EKS on AWS / GKE on GCP). Traffic came through a load balancer, and pods were auto-scaled based on load.”

**Step-by-Step Flow**

1. Build Spring Boot JAR
2. Create Docker image
3. Push image to ECR (AWS) / Artifact Registry (GCP)
4. Deploy to EKS / GKE
5. Expose service via Load Balancer / Ingress
6. Enable auto-scaling

**Dockerfile Example:**

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

**Kubernetes Deployment Example:**

```yaml
apiVersion: apps/v1
kind: Deployment
spec:
  replicas: 3
  template:
    spec:
      containers:
        - name: order-service
          image: myrepo/order-service:1.0
          ports:
            - containerPort: 8080
```

## **Q25. How did you manage configuration in cloud environments?**

We managed configurations using **Spring Cloud Config Server**.
All environment-specific properties were stored in a Git repository.

This allowed us to change configurations dynamically without redeploying services.
Sensitive values like passwords were stored in **Vault or Kubernetes Secrets**.

**Example Code (bootstrap.yml):**

```yaml
spring:
  application:
    name: order-service
  cloud:
    config:
      uri: http://config-server:8888
```

## **Q26. How did you handle application secrets in cloud environments?**

We never stored secrets in code or Git. In AWS/GCP, we used **managed secret services** and injected them securely at runtime.

**Tools Used**

* AWS Secrets Manager / Parameter Store
* GCP Secret Manager
* Kubernetes Secrets
* IAM-based access

**Spring Boot Example:**

```yaml
spring:
  datasource:
    password: ${DB_PASSWORD}
```

**Kubernetes Secret Example:**

```bash
kubectl create secret generic db-secret \
--from-literal=DB_PASSWORD=secret123
```

**Why This Works:**

* Secrets encrypted
* No hardcoding
* Environment-specific


## **Q27. Production cloud outage — how did you troubleshoot and restore?**

When the application went down, the first priority was restoration, not root cause. I checked health checks, pod status, and logs. Once the service was restored, we did a post-mortem.

**Troubleshooting Steps**

1. Check Load Balancer health
2. Check pod status (`CrashLoopBackOff`)
3. Review logs & metrics
4. Roll back to last stable version
5. Scale replicas if needed

**Command Examples:**

```bash
kubectl get pods
kubectl logs order-service-pod
kubectl rollout undo deployment order-service
```

## **Q28. Logging, monitoring, and alerting in AWS/GCP**

In microservices, observability is critical. We implemented centralized logging, real-time monitoring, and proactive alerting.

**What We Used**

* Logs → ELK / CloudWatch / GCP Logging
* Metrics → Prometheus + Grafana
* Alerts → CloudWatch Alarms / GCP Alerting

**Logback Example:**

```xml
<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender"
    <encoder
        <pattern%d %-5level %logger - %msg%n</pattern
    </encoder
</appender
```

**Health Endpoint:**

```java
management.endpoints.web.exposure.include=health,metrics
```

## **Q29. Designing for scalability and high availability**

“We designed the system assuming traffic spikes and failures. The application was **stateless**, horizontally scalable, and deployed across multiple zones.”

**Key Design Decisions**

* Stateless microservices
* Auto-scaling
* Load balancing
* Multi-AZ deployment
* Caching
* Circuit breakers

**Horizontal Pod Autoscaler Example:**

```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
spec:
  minReplicas: 2
  maxReplicas: 10
```