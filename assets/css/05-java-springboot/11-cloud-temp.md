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

**Types of DI:**
- Constructor injection (recommended)
- Setter injection
- Field injection

```java
// Without DI - tight coupling
class OrderService {
    private PaymentService paymentService = new PaymentService(); // Creates dependency
}

// With DI - loose coupling
class OrderService {
    private PaymentService paymentService;
    
    public OrderService(PaymentService paymentService) { // Injected
        this.paymentService = paymentService;
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

## 6. What is BeanFactory?

**BeanFactory** is the **basic IoC container in Spring** that creates and manages beans and performs **dependency injection**.
It uses **lazy initialization**, so beans are created **only when requested**.
It is lightweight, but **ApplicationContext** is preferred because it provides more features like event handling and annotation support.

```java
BeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans.xml"));
UserService userService = (UserService) factory.getBean("userService");
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