# 🔵 19. Frameworks
---
# 🔹 Spring Framework

### Question 281: What is Spring Framework?

**Answer (30 seconds):**
* Comprehensive Java framework for enterprise application development
* Provides infrastructure support so developers focus on business logic
* Based on Inversion of Control (IoC) and Dependency Injection principles
* Modular architecture with various modules like Core, MVC, Data, Security
* Simplifies Java EE development with POJO-based programming

```java
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User findById(Long id) {
        return userRepository.findById(id);
    }
}
```

---

### Question 282: What are the core features of Spring?

**Answer (35 seconds):**
* **IoC Container**: Manages object lifecycle and dependencies
* **Dependency Injection**: Automatic wiring of dependencies
* **AOP Support**: Cross-cutting concerns like logging, security
* **MVC Framework**: Web application development
* **Transaction Management**: Declarative transaction support
* **Integration**: Easy integration with other frameworks and technologies

```java
@Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }
}
```

---

### Question 283: What is dependency injection?

**Answer (30 seconds):**
* Design pattern where objects receive dependencies from external source
* Instead of creating dependencies, objects declare what they need
* Spring container provides required dependencies automatically
* **Types**: Constructor injection, Setter injection, Field injection
* Promotes loose coupling and easier testing

```java
@Service
public class OrderService {
    private final PaymentService paymentService;
    
    // Constructor injection (recommended)
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

---

### Question 284: What is inversion of control (IoC)?

**Answer (25 seconds):**
* Principle where control of object creation is inverted to framework
* Objects don't create their dependencies - container provides them
* Framework controls application flow instead of application code
* Spring IoC container manages bean lifecycle and dependencies
* Reduces coupling between components

```java
// Traditional approach - tight coupling
public class UserService {
    private UserRepository repo = new UserRepository(); // Creates dependency
}

// IoC approach - loose coupling
@Service
public class UserService {
    @Autowired
    private UserRepository repo; // Container injects dependency
}
```

---

### Question 285: What is Spring Boot?

**Answer (35 seconds):**
* Framework that simplifies Spring application development
* **Auto-configuration**: Automatically configures Spring based on dependencies
* **Starter Dependencies**: Pre-configured dependency bundles
* **Embedded Servers**: Built-in Tomcat, Jetty, Undertow
* **Production Ready**: Metrics, health checks, externalized configuration
* Minimal configuration with opinionated defaults

```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}

// application.properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost/mydb
```

---

### Question 286: What is Spring AOP (Aspect-Oriented Programming)?

**Answer (35 seconds):**
* Programming paradigm for handling cross-cutting concerns
* Separates business logic from system services like logging, security
* **Aspects**: Modularize cross-cutting concerns
* **Join Points**: Points where aspects can be applied
* **Advice**: Code executed at join points (Before, After, Around)
* Uses proxies or bytecode weaving

```java
@Aspect
@Component
public class LoggingAspect {
    @Before("@annotation(Loggable)")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Executing: " + joinPoint.getSignature().getName());
    }
}

@Service
public class UserService {
    @Loggable
    public User findUser(Long id) { return user; }
}
```

---

### Question 287: What is Spring Data JPA?

**Answer (30 seconds):**
* Spring module that simplifies JPA-based data access
* Provides repository abstraction over JPA
* **Auto-implementation**: Creates implementation from method names
* **Query Methods**: Derive queries from method names
* **Custom Queries**: Support for JPQL and native SQL
* Reduces boilerplate code significantly

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastName(String lastName);
    List<User> findByAgeGreaterThan(int age);
    
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
```

---

### Question 288: What is Spring Cloud?

**Answer (35 seconds):**
* Framework for building distributed systems and microservices
* Provides tools for common patterns in distributed systems
* **Service Discovery**: Eureka, Consul integration
* **Circuit Breaker**: Hystrix for fault tolerance
* **API Gateway**: Zuul, Spring Cloud Gateway
* **Configuration Management**: Centralized configuration
* **Load Balancing**: Client-side load balancing

```java
@EnableEurekaClient
@SpringBootApplication
public class UserServiceApplication {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

---

### Question 289: What is Spring Security?

**Answer (35 seconds):**
* Comprehensive security framework for Java applications
* Handles authentication and authorization
* **Authentication**: Verify user identity (login)
* **Authorization**: Control access to resources
* **Protection**: CSRF, session fixation, clickjacking protection
* Integrates with various authentication providers
* Annotation-based and configuration-based security

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated())
            .formLogin().and()
            .build();
    }
}
```

---

### Question 290: What is Spring WebFlux?

**Answer (35 seconds):**
* Reactive web framework for building non-blocking applications
* Alternative to Spring MVC for reactive programming
* **Non-blocking**: Handles more concurrent requests with fewer threads
* **Reactive Streams**: Built on Project Reactor
* **Functional Programming**: Supports functional routing
* Better performance for I/O intensive applications

```java
@RestController
public class UserController {
    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userService.findAll(); // Returns Flux<User>
    }
    
    @GetMapping("/users/{id}")
    public Mono<User> getUser(@PathVariable String id) {
        return userService.findById(id); // Returns Mono<User>
    }
}
```

# 🔹 Hibernate and JPA
### Question 291: What is Hibernate?

**Answer (30 seconds):**
* Object-Relational Mapping (ORM) framework for Java
* Maps Java objects to database tables automatically
* **HQL**: Hibernate Query Language - object-oriented SQL
* **Caching**: First-level and second-level caching
* **Lazy Loading**: Load data on-demand for performance
* Most popular JPA implementation

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_name")
    private String name;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;
}
```

---

### Question 292: What is JPA?

**Answer (25 seconds):**
* Java Persistence API - specification for ORM in Java
* Standard way to manage relational data in Java applications
* **Annotations**: @Entity, @Table, @Id for mapping
* **EntityManager**: Interface for persistence operations
* **JPQL**: Java Persistence Query Language
* Implementations: Hibernate, EclipseLink, OpenJPA

```java
@PersistenceContext
private EntityManager entityManager;

public User findUser(Long id) {
    return entityManager.find(User.class, id);
}

public List<User> findUsers() {
    return entityManager.createQuery("SELECT u FROM User u", User.class)
                       .getResultList();
}
```

---

### Question 293: What is the difference between Hibernate and JPA?

**Answer (35 seconds):**
* **JPA**: Specification/standard for ORM in Java
* **Hibernate**: Implementation of JPA specification
* **Features**: Hibernate has additional features beyond JPA
* **Portability**: JPA code works with any JPA provider
* **Vendor Lock-in**: Pure JPA avoids vendor lock-in
* **Performance**: Hibernate-specific features may offer better performance
* **Best Practice**: Use JPA annotations with Hibernate as provider

```java
// JPA standard approach
@PersistenceContext
private EntityManager entityManager;

// Hibernate-specific approach
@Autowired
private SessionFactory sessionFactory;

// Recommended: JPA with Hibernate as provider
public interface UserRepository extends JpaRepository<User, Long> {
    // Uses JPA but Hibernate implements it
}
```
