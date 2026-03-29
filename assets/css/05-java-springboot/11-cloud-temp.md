
# ✅ 18. Java Spring Framework 

## 1. What is Spring Framework?

**Spring Framework** is a **comprehensive Java framework** for building enterprise applications.

It provides **infrastructure support**, uses **IoC and Dependency Injection**, has **modular architecture** (Core, MVC, Data, Security), and **simplifies Java EE development** with POJOs.

Spring makes Java development easier by handling common tasks and promoting best practices like loose coupling and testability.

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

## 2. What are the core features of Spring?

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

## 3. What is Inversion of Control (IoC)?

**Inversion of Control (IoC)** means the **control of object creation and dependency management is given to the framework instead of the developer**.

Normally, we create objects using `new`.
But in **IoC (like in Spring)**, the **framework creates the objects and gives them to our class**.

```java
// Without IoC
Service service = new Service();

// With IoC (Spring)
@Autowired
Service service;
```

## 4. What is Dependency Injection?

**Dependency Injection (DI)** is a design pattern where an object's **dependencies are provided externally** rather than the object creating them itself.

In Java and Spring, DI helps make code **loosely coupled, easier to test, and more maintainable**. It can be implemented via **constructor injection, setter injection, or field injection**.

**There are 3 main types of DI:**
1. Constructor Injection – dependencies injected through constructor (recommended)
2. Setter Injection – dependencies injected through setter method
3. Field Injection – dependencies injected directly into field using @Autowired

**1. Constructor Injection (Recommended)**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Engine {
    public void start() {
        System.out.println("Engine started");
    }
}

@Component
class Car {
    private Engine engine;

    @Autowired
    public Car(Engine engine) {   // Constructor Injection
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Car is running");
    }
}
```


**2. Setter Injection**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Car {
    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {   // Setter Injection
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Car is running");
    }
}
```


**3. Field Injection**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Car {

    @Autowired   // Field Injection
    private Engine engine;

    public void drive() {
        engine.start();
        System.out.println("Car is running");
    }
}
```

## 5. What is a Container (Spring Container)?

A **Spring Container** is the **core part of the Spring Framework that creates, manages, and controls the lifecycle of objects called Beans**.

It is responsible for:
* **Creating objects (Beans)**
* **Injecting dependencies**
* **Managing bean lifecycle**

```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
UserService service = context.getBean(UserService.class);
```
Here the **Spring Container creates the `UserService` object and provides it when needed**.

**Types of Spring Container** 

1. **BeanFactory** – Basic container
2. **ApplicationContext** – Advanced container (most commonly used)

## 6. What is BeanFactory vs ApplicationContext?

**BeanFactory** is the **basic IoC container in Spring** that creates and manages beans and performs **dependency injection**.
It uses **lazy initialization**, so beans are created **only when requested**.
It is lightweight, but **ApplicationContext** is preferred because it provides more features like event handling and annotation support.

**`ApplicationContext`** is a **Spring container** that manages the lifecycle of Spring beans. It loads configuration, creates objects, injects dependencies, and provides advanced features like **event handling, internationalization, and AOP**. It’s an enhanced version of `BeanFactory` and is commonly used in Spring applications.

**Using BeanFactory (Lazy Loading)**
```java
Resource resource = new ClassPathResource("beans.xml");
BeanFactory factory = new XmlBeanFactory(resource);

UserService service = (UserService) factory.getBean("userService");
service.print();
```

**Using ApplicationContext (Eager Loading)**
```java
ApplicationContext context =
        new ClassPathXmlApplicationContext("beans.xml");

UserService service = context.getBean(UserService.class);
service.print();
```

## 7. What is the difference between Java Bean and Spring Bean?

A **Java Bean** is a simple Java class with private fields, getters/setters, and a no-argument constructor, and it is created manually using the `new` keyword.

```java
public class User {
    private String name;

    public User() {}   // no-arg constructor

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

Usage:

```java
User user = new User();
user.setName("John");
```

A **Spring Bean** is an object that is created and managed by the **Spring IoC container**, and it supports features like dependency injection, lifecycle management, and configuration.

```java
@Component
public class UserService {

    public void printUser() {
        System.out.println("User Service Running");
    }
}
```

Usage (Dependency Injection):

```java
@Autowired
private UserService userService;
```

## 8. What is the Bean Lifecycle in Spring Boot?

The **bean lifecycle** describes the steps a bean goes through from **creation to destruction** inside the Spring **IoC container**.

**Steps in Bean Lifecycle**

1. **Bean Instantiation:** Spring creates the bean object.
2. **Dependency Injection:** Required dependencies are injected using `@Autowired`.
3. **Bean Initialization:** Initialization methods run using `@PostConstruct` or `afterPropertiesSet()`.
4. **Bean Ready for Use:** The bean is now fully initialized and used by the application.
5. **Bean Destruction:** When the application shuts down, cleanup happens using `@PreDestroy`.

```java
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    public MyBean() {
        System.out.println("Bean Created");
    }

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

## 9. What is AOP in Spring?

**AOP (Aspect Oriented Programming)** is a programming concept used to **separate cross-cutting concerns** like **logging, security, transactions, and exception handling** from the main business logic.

Instead of writing this code in every method, AOP lets us **apply it automatically across multiple methods**.

**Key Concepts in AOP**

* **Aspect** – Class that contains cross-cutting logic (e.g., logging).
* **Advice** – When the code should run (before, after, around).
* **Join Point** – A point in program execution (method call).
* **Pointcut** – Expression that selects join points.

```java
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature().getName());
    }
}
```

## 10. What is Spring Data JPA?

**Spring Data JPA** is a Spring module that **simplifies JPA-based data access**.

It provides **repository abstraction**, **auto-implements methods from names**, supports **query methods, JPQL, and native SQL**, and **reduces boilerplate code**.

* **Auto-implementation**: Creates implementation from method names
* **Query Methods**: Derive queries from method names
* **Custom Queries**: Support for JPQL and native SQL

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastName(String lastName);
    List<User> findByAgeGreaterThan(int age);
    
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
```

## 11. What is Spring Security?

**Spring Security** is a **Java security framework** that handles **authentication** (user identity) and **authorization** (access control).

It provides **protection** against CSRF, session fixation, clickjacking, integrates with multiple authentication providers, and supports **annotation- and configuration-based security**.

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
                .formLogin()
                .and()
                .build();
    }
}
```

## 12. What is Spring Cloud? - asked

**Spring Cloud** is a framework for **building distributed systems and microservices**.

It provides tools for **service discovery (Eureka/Consul)**, **circuit breakers (Hystrix)**, **API gateways (Zuul/Gateway)**, **centralized configuration**, and **client-side load balancing**.

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

## 13. What is Spring WebFlux?

**Spring WebFlux** is a **reactive, non-blocking web framework** for building high-performance applications.

It's an alternative to Spring MVC, uses **Reactive Streams** (Project Reactor), supports **functional routing**, and handles **more concurrent requests with fewer threads**.

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

# ✅ 19. Java Spring Boot 

## 1. What is annotations in Java?
**An annotation** is a special type of metadata in Java that provides additional information about classes, methods, or variables to the compiler or framework.

**Example Annotations:**
* `@Override`
* `@Autowired`
* `@Component`
* `@Service`

## 2. What is Spring Boot?

**Spring Boot** is a framework built on top of Spring that simplifies application development. It provides **auto-configuration**, **embedded servers**, and **starter dependencies**, allowing developers to build production-ready applications quickly with minimal configuration.

**Design patterns:**
Spring Boot mainly uses **MVC, Dependency Injection, Singleton, Factory, and DAO** design patterns.

**Design Principle:**
Spring Boot follows principles like Convention over **Configuration, Dependency Injection, Auto-Configuration, and Standalone** Applications.

- Rapid application development
- Auto-configuration based on classpath
- Embedded servers (Tomcat, Jetty)
- Production-ready features out of the box
- Minimal configuration required

Spring Boot eliminates most boilerplate configuration and allows developers to focus on business logic rather than setup.

## 3. How does Spring Boot Flow Architecture works?

Spring Boot follows a **layered architecture** where a request flows through different layers:

**Flow:**

**Client → DispatcherServlet → Controller → Service → Repository → Database → Response**

**Short Explanation:**

1. **Client** – Sends HTTP request (browser/Postman).
2. **DispatcherServlet** (from Spring MVC) – Receives the request and routes it.
3. **Controller** – Handles the API request.
4. **Service** – Contains business logic.
5. **Repository/DAO** – Interacts with the database using Spring Data JPA.
6. **Database** – Stores and retrieves data.
7. **Response** – Data returns back to the client.


## 4. How does Spring Boot Works Internally?

**Spring Boot** starts with `SpringApplication.run()`, which initializes the Spring context. It performs auto-configuration based on project dependencies, scans components to create beans in the IoC container, and starts an embedded server like Apache Tomcat. After that, the application becomes ready to handle requests.

**Internal Flow:**

1. **Application Starts :** The `main()` method calls **`SpringApplication.run()`** to start the application.
2. **Auto Configuration** Spring Boot automatically configures beans based on dependencies using **`@EnableAutoConfiguration`**.
3. **Component Scanning :** It scans packages for classes annotated with **`@Component`**, **`@Service`**, **`@Repository`**, and **`@Controller`**.
4. **Bean Creation (IoC Container) :** Spring creates and manages objects (beans) inside the **Spring IoC container**.
5. **Embedded Server Starts :** Spring Boot starts an embedded server like **Apache Tomcat**, **Jetty**, or **Undertow**.
6. **Application Ready :** The application is ready to handle HTTP requests.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Combines three annotations
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

// Equivalent to:
@Configuration
@EnableAutoConfiguration  
@ComponentScan
public class MyApplication { }
```

## 5. How can you disable specific auto-configurations in Spring Boot?

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

## 6. What is @SpringBootApplication annotation?

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

## 7. @Component vs @Service vs @Repository vs @Controller vs @RestController annotations?


> All these are Spring stereotype annotations used to create Spring beans.

**@Component** is a generic annotation for any bean.

**@Service** is used for business logic layer.

**@Repository** is used for database/DAO layer and provides exception handling.

**@Controller** is used to handle web requests and return views (like JSP/HTML).

**@RestController** is used to build REST APIs and returns JSON/XML data instead of views.

Internally, all of them are specialized types of @Component.

**Simple Flow (Easy to Remember)**

```text
Controller / RestController → Service → Repository → Database
```

**Repository Layer**

```java
@Repository
public class UserRepository {
    public String getUser() {
        return "User from DB";
    }
}
```

**Service Layer**

```java
@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public String getUser() {
        return repo.getUser();
    }
}
```

**Controller (Returns View)**

```java
@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("user", service.getUser());
        return "home"; // returns HTML/JSP page
    }
}
```

**RestController (Returns JSON)**

```java
@RestController
public class UserRestController {
    @Autowired
    private UserService service;

    @GetMapping("/api/user")
    public String getUser() {
        return service.getUser(); // returns JSON/text
    }
}
```

**Generic Component**

```java
@Component
public class EmailUtil {
    public void sendEmail() {
        System.out.println("Sending Email");
    }
}
```
> @RestController = @Controller + @ResponseBody


## 8. What is @Autowired vs @Inject annotation?

**`@Autowired`** is an annotation in **Spring Framework** that enables **automatic dependency injection (DI)**.
It tells the Spring container to automatically inject a required bean into a class.

```java
// Using @Autowired (Spring)
@Service
public class OrderService {
    
    @Autowired
    private PaymentService paymentService; // Field injection
    
    private UserService userService;
    
    @Autowired
    public OrderService(UserService userService) { // Constructor injection
        this.userService = userService;
    }
}
```

**@Autowired** also used used for dependency injection in Spring, it is provided by Spring Framework, while @Inject is provided by Java (JSR-330).

**Using @Inject (Java JSR-330)**

```java
import jakarta.inject.Inject;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    public void printUser() {
        System.out.println(userRepository.getUser());
    }
}
```

## 9. What is @Profile Annotation?

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


## 10. What is ApplicationContext?

`ApplicationContext` is a **Spring container** that manages the lifecycle of Spring beans. It loads configuration, creates objects, injects dependencies, and provides advanced features like **event handling, internationalization, and AOP**. It’s an enhanced version of `BeanFactory` and is commonly used in Spring applications.

- Central interface for Spring applications
- Manages bean lifecycle and dependencies
- Provides additional enterprise features
- Event publishing and handling
- Resource loading and internationalization

```java
@Component
public class MyService {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    public void doSomething() {
        // Get bean programmatically
        UserService userService = applicationContext.getBean(UserService.class);
        
        // Publish event
        applicationContext.publishEvent(new CustomEvent("data"));
    }
}
```


## 11. What is @Primary, @Qualifier, @Component, @Configuration, @PatchMapping annotation?

**@Component** is used to tell Spring that this class is a bean and should be managed by the Spring container. Spring automatically detects it during component scanning.”

```java
@Component
public class EmailService {
    public void send() {
        System.out.println("Sending email");
    }
}
```

**@Configuration**  is used when we want to define beans explicitly using `@Bean` methods. It’s mainly used for Java-based configuration instead of XML.”

```java
@Configuration
public class AppConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService();
    }
}
```

**@Primary** When multiple beans of the same type exist and Spring gets confused, `@Primary` tells Spring which bean should be chosen by default.

```java
@Component
@Primary
public class CreditCardPayment implements PaymentService {
}
```

```java
@Component
public class UpiPayment implements PaymentService {
}
```

**@Qualifier** is used when we want to explicitly specify which bean to inject when multiple beans of the same type are present.”

```java
@Autowired
@Qualifier("upiPayment")
private PaymentService paymentService;
```
**@PatchMapping** is used for partial updates of a resource in REST APIs, where only specific fields are modified instead of replacing the entire object.”

```java
@PatchMapping("/users/{id}")
public ResponseEntity<User> updateEmail(
        @PathVariable Long id,
        @RequestBody Map<String, Object> updates) {

    User updatedUser = userService.updateUser(id, updates);
    return ResponseEntity.ok(updatedUser);
}
```

## 12. Explain Spring Boot Actuator endpoints.

**Answer:**
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

## 13. How do you secure a Java Spring Boot application?

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
## 14. What is Lombok in Java and and whe can we use?

**Lombok** is a Java library that reduces boilerplate code by automatically generating getters, setters, constructors, and other methods using annotations.

If we don’t want to manually write **getters and setters** in Java, we can use **Lombok’s `@Getter` and `@Setter` annotations** on the class or fields.

Alternatively, `@Data` generates **getters, setters, `toString()`, `equals()`, and `hashCode()`** all at once.

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

## 15. Why do we use Long in JpaRepository<Employee, Long>?
In **`JpaRepository<Employee, Long>`**, the **first type (`Employee`)** is the **entity class** the repository manages, and the **second type (`Long`)** is the **type of the entity’s primary key (`@Id`)**.

Using `Long` tells Spring Data JPA what type of value to expect when performing operations like `findById()`, `deleteById()`, or `save()`.

## 16. What is Transactional and Why @Transactional Matters in Spring Boot?
**@Transactional** is an annotation in **Spring Boot** used to manage **database transactions automatically**. 
A **transaction** means a group of database operations that must **all succeed or all fail together**.


Why **@Transactional** Matters

1. **Data Consistency**
   Ensures all database operations are completed successfully. If one fails, everything is rolled back.

2. **Automatic Rollback**
   If an exception occurs, Spring automatically **rolls back the transaction**.

3. **Simplifies Code**
   Developers don't need to manually write **commit or rollback logic**.

4. **Maintains Data Integrity**
   Prevents partial updates in the database.

```java
@Service
public class PaymentService {

    @Transactional
    public void transferMoney() {
        debitAccount();
        creditAccount();
    }
}
```

If **creditAccount() fails**, Spring will **rollback debitAccount()** automatically.

## 17. What is Distributed Tracing?

**Distributed Tracing** is a technique used in **microservices architecture** to track and monitor a request as it travels across multiple services.

It helps developers **identify performance issues, delays, and failures** by showing the **complete flow of a request across different services in a system**.

**Real-Time Example**

1. **API Gateway** receives request
2. **Order Service** processes order
3. **Payment Service** processes payment
4. **Inventory Service** updates stock
5. **Notification Service** sends email/SMS

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

## 20. What is the role of `@RestController` and `@Controller` in Spring Boot?

Both handle HTTP requests, but `@RestController` automatically serializes return values to JSON/XML, while `@Controller` returns view names.

```java
@Controller
public class WebController {
    @GetMapping("/home")
    public String home(Model model) {
        return "home"; // Returns view name
    }
}

@RestController
public class ApiController {
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll(); // Returns JSON
    }
}
```

`@RestController` = `@Controller` + `@ResponseBody`

 
## 21. What is `@PostConstruct`, `@PreDestroy` and `@Scope` in Spring Boot?

These annotations define lifecycle callbacks that execute after bean initialization and before destruction.

```java
@Service
public class DatabaseService {
    
    private Connection connection;
    
    @PostConstruct
    public void init() {
        // Called after dependency injection
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb");
        System.out.println("Database connection established");
    }
    
    @PreDestroy
    public void cleanup() {
        // Called before bean destruction
        if (connection != null) {
            connection.close();
        }
        System.out.println("Database connection closed");
    }
}
```
**Lifecycle order:** Constructor → Dependency Injection → @PostConstruct → Bean Ready → @PreDestroy → Destruction

`@Scope` defines the lifecycle and visibility of Spring beans. It controls how many instances Spring creates.

```java
@Component
@Scope("singleton") // Default scope
public class ConfigService {
    // One instance per Spring container
}

@Component
@Scope("prototype")
public class TaskProcessor {
    // New instance every time it's requested
}

@Component
@Scope("request")
public class RequestContext {
    // One instance per HTTP request
}

@Component
@Scope("session")
public class UserSession {
    // One instance per HTTP session
}
```

## 22. How do you manage Spring Beans in Spring Boot?

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

## 23. What is the difference between `@OneToMany` and `@ManyToOne` in Spring Boot?

These annotations define JPA relationships between entities with different cardinalities.

```java
// One User has Many Orders
@Entity
public class User {
    @Id
    private Long id;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
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

## 24. How can you configure pagination and sorting in Spring Boot with Spring Data JPA?

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
**URL example:** `/users?page=0&size=5&sortBy=name`