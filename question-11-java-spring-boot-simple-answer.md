# Spring Boot Interview Answers (Questions 1-10)

## 1. What is Spring Boot, and why is it used?

Spring Boot is a framework that simplifies Spring application development by providing auto-configuration and embedded servers. It eliminates boilerplate code and XML configuration.

**Why use it?**
- Rapid development with minimal setup
- Production-ready applications out of the box
- Embedded servers (Tomcat, Jetty)

```java
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

## 2. What are the main features of Spring Boot?

**Key features:**
- **Auto-configuration** - Automatically configures beans based on classpath
- **Starter dependencies** - Pre-configured dependency bundles
- **Embedded servers** - No need for external server deployment
- **Actuator** - Production monitoring and management
- **CLI support** - Command-line interface for rapid prototyping

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

## 3. How does Spring Boot differ from the traditional Spring Framework?

**Traditional Spring:**
- Manual configuration with XML or Java config
- External server deployment required
- Complex dependency management

**Spring Boot:**
- Auto-configuration based on classpath
- Embedded servers included
- Starter dependencies simplify setup

```java
// Traditional Spring - lots of configuration
@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig implements WebMvcConfigurer { }

// Spring Boot - minimal configuration
@SpringBootApplication
public class App { }
```

## 4. What is the purpose of `@SpringBootApplication` annotation?

`@SpringBootApplication` is a convenience annotation that combines three annotations:

```java
@SpringBootApplication
// Equivalent to:
// @Configuration + @EnableAutoConfiguration + @ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

**What it does:**
- `@Configuration` - Marks class as configuration source
- `@EnableAutoConfiguration` - Enables auto-configuration
- `@ComponentScan` - Scans for components in current package

## 5. What are Spring Boot starters? Give some examples.

Starters are dependency descriptors that include all necessary dependencies for a specific functionality.

**Common starters:**
```xml
<!-- Web applications -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Database access -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## 6. What is the difference between `application.properties` and `application.yml` files?

Both are configuration files, but with different formats:

**application.properties:**
```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
```

**application.yml:**
```yaml
server:
  port: 8080
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
```

**Key differences:**
- YAML is more readable and supports hierarchical data
- Properties file is simpler for basic configurations
- Both serve the same purpose

## 7. What is the role of `@EnableAutoConfiguration` in Spring Boot?

`@EnableAutoConfiguration` tells Spring Boot to automatically configure beans based on classpath dependencies.

```java
@Configuration
@EnableAutoConfiguration
public class Config {
    // Spring Boot will auto-configure DataSource, 
    // JPA repositories, etc. based on dependencies
}
```

**How it works:**
- Scans classpath for dependencies
- Applies conditional configurations
- Creates beans automatically (e.g., DataSource if H2 is present)

## 8. How do you create a Spring Boot application from scratch?

**Step 1:** Use Spring Initializr (start.spring.io) or IDE

**Step 2:** Add dependencies in `pom.xml`:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.1.0</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

**Step 3:** Create main class:
```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

## 9. What are some commonly used Spring Boot starters?

**Most popular starters:**

```xml
<!-- Web development -->
<artifactId>spring-boot-starter-web</artifactId>

<!-- Database operations -->
<artifactId>spring-boot-starter-data-jpa</artifactId>

<!-- Security -->
<artifactId>spring-boot-starter-security</artifactId>

<!-- Testing -->
<artifactId>spring-boot-starter-test</artifactId>

<!-- Validation -->
<artifactId>spring-boot-starter-validation</artifactId>

<!-- Actuator for monitoring -->
<artifactId>spring-boot-starter-actuator</artifactId>
```

## 10. How can you run a Spring Boot application from the command line?

**Method 1:** Using Maven:
```bash
mvn spring-boot:run
```

**Method 2:** Using Gradle:
```bash
./gradlew bootRun
```

**Method 3:** Using JAR file:
```bash
# First build the JAR
mvn clean package

# Then run it
java -jar target/myapp-1.0.jar
```

**Method 4:** With custom port:
```bash
java -jar myapp.jar --server.port=9090
```

# Spring Boot Interview Answers (Questions 11-20)

## 11. How can you externalize configuration in Spring Boot?

Spring Boot supports multiple ways to externalize configuration, allowing you to use the same code across different environments.

**Configuration sources (in order of priority):**
- Command line arguments
- Environment variables
- application.properties/yml files
- @PropertySource annotations

```properties
# application.properties
app.name=MyApp
app.version=1.0

# Environment variable
export APP_NAME=MyApp

# Command line
java -jar app.jar --app.name=MyApp
```

## 12. What are profiles in Spring Boot, and how are they used?

Profiles allow you to segregate parts of your application configuration for different environments like dev, test, and production.

```properties
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

## 13. How do you inject properties from `application.properties` into Spring Beans?

Use `@Value` annotation to inject individual properties or `@ConfigurationProperties` for grouped properties.

```properties
# application.properties
app.name=MyApplication
app.timeout=5000
```

```java
@Component
public class AppService {
    @Value("${app.name}")
    private String appName;
    
    @Value("${app.timeout:3000}") // default value
    private int timeout;
}
```

## 14. Explain the difference between `@Value` and `@ConfigurationProperties`.

`@Value` injects individual properties, while `@ConfigurationProperties` binds a group of related properties to a class.

**@Value approach:**
```java
@Component
public class DatabaseConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
}
```

**@ConfigurationProperties approach:**
```java
@ConfigurationProperties(prefix = "db")
@Component
public class DatabaseProperties {
    private String url;
    private String username;
    // getters and setters
}
```

`@ConfigurationProperties` is better for grouped properties and provides type safety.

## 15. How do you manage multiple configuration files in Spring Boot?

Spring Boot automatically loads configuration files in this order: application.properties, then profile-specific files.

```
src/main/resources/
├── application.properties          # Base configuration
├── application-dev.properties      # Development
├── application-test.properties     # Testing
└── application-prod.properties     # Production
```

```java
// Activate multiple profiles
@ActiveProfiles({"dev", "debug"})
public class MyTest { }

// Or via properties
spring.profiles.active=dev,debug
```

## 16. How can you create a custom configuration in Spring Boot?

Create custom configuration using `@Configuration` and `@Bean` annotations.

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

## 17. What is the purpose of `application.properties` or `application.yml` files in Spring Boot?

These files contain application configuration that Spring Boot automatically loads at startup. They replace XML configuration files.

**Common configurations:**
```properties
# Server configuration
server.port=8080
server.servlet.context-path=/api

# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root

# JPA configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Benefits:**
- Centralized configuration
- Environment-specific overrides
- No code changes needed for different environments

## 18. How do you configure logging in Spring Boot?

Spring Boot uses Logback by default. Configure logging levels and patterns in application.properties.

```properties
# Logging levels
logging.level.com.mycompany=DEBUG
logging.level.org.springframework=INFO
logging.level.root=WARN

# Log file
logging.file.name=app.log
logging.file.path=/var/logs

# Log pattern
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

```java
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @GetMapping("/users")
    public List<User> getUsers() {
        logger.info("Fetching all users");
        return userService.findAll();
    }
}
```

## 19. How can you configure different profiles for different environments (e.g., dev, prod)?

Create separate property files for each environment and activate profiles based on deployment.

**File structure:**
```
application.properties          # Common properties
application-dev.properties      # Development environment
application-prod.properties     # Production environment
```

**Development (application-dev.properties):**
```properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:testdb
logging.level.com.myapp=DEBUG
```

**Production (application-prod.properties):**
```properties
server.port=80
spring.datasource.url=jdbc:mysql://prod-server:3306/mydb
logging.level.com.myapp=INFO
```

**Activation:**
```bash
# Command line
java -jar app.jar --spring.profiles.active=prod

# Environment variable
export SPRING_PROFILES_ACTIVE=prod
```

## 20. How would you set up a logging level for Spring Boot (e.g., debug, info, error)?

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

# Spring Boot Interview Answers (Questions 21-30)

## 21. What is Spring Boot auto-configuration, and how does it work?

Auto-configuration automatically configures Spring beans based on the dependencies present in your classpath. It uses conditional annotations to decide what to configure.

**How it works:**
- Scans classpath for dependencies
- Applies configuration classes with `@Conditional` annotations
- Creates beans only when conditions are met

```java
// Example: If H2 is on classpath, DataSource is auto-configured
@Configuration
@ConditionalOnClass(DataSource.class)
public class DataSourceAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}
```

## 22. How can you disable specific auto-configurations in Spring Boot?

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

## 23. How do you create a custom auto-configuration in Spring Boot?

Create a configuration class with conditional annotations and register it in `META-INF/spring.factories`.

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

## 24. What is `@ConditionalOnProperty` in Spring Boot?

`@ConditionalOnProperty` creates beans only when specific properties have certain values. It's used for feature toggles and conditional configuration.

```java
@Configuration
public class CacheConfig {
    
    @Bean
    @ConditionalOnProperty(name = "app.cache.enabled", havingValue = "true")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }
    
    @Bean
    @ConditionalOnProperty(name = "app.cache.type", havingValue = "redis")
    public RedisTemplate redisTemplate() {
        return new RedisTemplate();
    }
}
```

```properties
# application.properties
app.cache.enabled=true
app.cache.type=redis
```

## 25. Explain `@ConditionalOnClass` and `@ConditionalOnMissingBean` in Spring Boot auto-configuration.

These annotations control when beans are created based on classpath presence and existing bean definitions.

**@ConditionalOnClass:** Creates bean only if specified class is on classpath
**@ConditionalOnMissingBean:** Creates bean only if no bean of that type exists

```java
@Configuration
public class DatabaseConfig {
    
    @Bean
    @ConditionalOnClass(name = "org.h2.Driver")
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource h2DataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }
}
```

This creates H2 DataSource only if H2 driver is present and no other DataSource exists.

## 26. What is the role of `@RestController` and `@Controller` in Spring Boot?

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

## 27. How does `@RequestMapping` differ from `@GetMapping`, `@PostMapping`, etc.?

`@RequestMapping` is generic and can handle any HTTP method, while `@GetMapping`, `@PostMapping` are specialized shortcuts for specific methods.

```java
@RestController
public class UserController {
    
    // Generic - can handle multiple methods
    @RequestMapping(value = "/users", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> handleUsers() { }
    
    // Specific shortcuts (preferred)
    @GetMapping("/users")
    public List<User> getUsers() { }
    
    @PostMapping("/users")
    public User createUser(@RequestBody User user) { }
    
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) { }
}
```

## 28. What is the difference between `@RequestParam` and `@PathVariable`?

`@RequestParam` extracts query parameters, while `@PathVariable` extracts values from the URL path.

```java
@RestController
public class SearchController {
    
    // PathVariable: /users/123
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    // RequestParam: /users?page=1&size=10
    @GetMapping("/users")
    public List<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return userService.findAll(page, size);
    }
    
    // Combined: /users/123/orders?status=active
    @GetMapping("/users/{userId}/orders")
    public List<Order> getUserOrders(
        @PathVariable Long userId,
        @RequestParam String status) {
        return orderService.findByUserAndStatus(userId, status);
    }
}
```

## 29. What are the key uses of `@Component`, `@Service`, `@Repository`, and `@Controller` annotations?

These are stereotype annotations that mark classes for Spring's component scanning. They're all specializations of `@Component`.

```java
@Component  // Generic Spring-managed component
public class EmailValidator {
    public boolean isValid(String email) { }
}

@Service    // Business logic layer
public class UserService {
    public User createUser(User user) { }
}

@Repository // Data access layer
public class UserRepository {
    public User findById(Long id) { }
}

@Controller // Web layer (returns views)
public class UserController {
    public String showUsers(Model model) { }
}
```

**Key differences:**
- `@Service`: Business logic, transaction boundaries
- `@Repository`: Data access, exception translation
- `@Controller`: Web requests, view resolution
- `@Component`: Generic components

## 30. What is the purpose of `@Autowired` in Spring Boot?

`@Autowired` enables automatic dependency injection. Spring automatically injects matching beans into fields, constructors, or methods.

```java
@Service
public class UserService {
    
    // Field injection (not recommended)
    @Autowired
    private UserRepository userRepository;
    
    // Constructor injection (recommended)
    private final EmailService emailService;
    
    @Autowired
    public UserService(EmailService emailService) {
        this.emailService = emailService;
    }
    
    // Setter injection
    @Autowired
    public void setNotificationService(NotificationService service) {
        this.notificationService = service;
    }
}
```

**Best practice:** Use constructor injection for required dependencies:
```java
@Service
public class UserService {
    private final UserRepository userRepository;
    
    // @Autowired optional on single constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

# Spring Boot Interview Answers (Questions 31-40)

## 31. How does Spring Boot handle dependency injection?

Spring Boot uses the IoC (Inversion of Control) container to manage dependencies automatically. It supports constructor, setter, and field injection with `@Autowired`.

**Injection types:**
```java
@Service
public class UserService {
    
    // Constructor injection (recommended)
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // Field injection
    @Autowired
    private EmailService emailService;
    
    // Setter injection
    @Autowired
    public void setNotificationService(NotificationService service) {
        this.notificationService = service;
    }
}
```

Spring automatically resolves dependencies by type and injects them at runtime.

## 32. What is the purpose of `@Value` annotation in Spring Boot?

`@Value` injects values from properties files, environment variables, or expressions into Spring beans.

```java
@Component
public class AppConfig {
    
    @Value("${app.name}")
    private String appName;
    
    @Value("${server.port:8080}") // default value
    private int port;
    
    @Value("#{systemProperties['user.home']}")
    private String userHome;
    
    @Value("${app.features:feature1,feature2}")
    private List<String> features;
}
```

```properties
# application.properties
app.name=MyApplication
server.port=9090
app.features=auth,logging,cache
```

## 33. Explain `@Bean` and `@Configuration` in Spring Boot.

`@Configuration` marks a class as a source of bean definitions. `@Bean` defines individual beans that Spring should manage.

```java
@Configuration
public class AppConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Primary
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:mysql://localhost:3306/primary")
            .build();
    }
}
```

`@Configuration` classes replace XML configuration files and provide type-safe bean definitions.

## 34. What is `@PostConstruct` and `@PreDestroy` in Spring Boot?

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

## 35. What is the `@Scope` annotation in Spring Boot?

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

**Common scopes:** singleton (default), prototype, request, session, application.

## 36. How do you manage Spring Beans in Spring Boot?

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

## 37. What is the default bean scope in Spring Boot?

The default bean scope in Spring Boot is **singleton**. This means Spring creates only one instance of the bean per Spring container.

```java
@Service
public class UserService {
    // This is singleton by default
    // Same instance used everywhere
}

// Explicitly showing default scope
@Service
@Scope("singleton")
public class EmailService {
    // Only one instance created
}
```

**Singleton characteristics:**
- One instance per Spring container
- Thread-safe by design (stateless recommended)
- Shared across all injection points
- Created at startup (eager) or first use (lazy)

## 38. Explain the difference between `@Singleton`, `@Prototype`, and `@RequestScope`.

These annotations control bean creation patterns and lifecycle management.

```java
@Component
@Scope("singleton") // Note: @Singleton doesn't exist in Spring
public class ConfigService {
    // One instance for entire application
}

@Component
@Scope("prototype")
public class TaskProcessor {
    // New instance every time it's injected
}

@Component
@Scope("request")
public class RequestContext {
    // New instance per HTTP request
}

@RestController
public class UserController {
    
    @Autowired
    private ConfigService config; // Same instance everywhere
    
    @Autowired
    private TaskProcessor processor; // New instance each injection
}
```

**Key differences:**
- **Singleton:** Shared instance, application-wide
- **Prototype:** New instance per injection
- **Request:** New instance per HTTP request

## 39. How does Spring Boot handle the lifecycle of beans?

Spring Boot manages bean lifecycle through several phases with hooks for customization.

```java
@Component
public class LifecycleBean implements InitializingBean, DisposableBean {
    
    public LifecycleBean() {
        System.out.println("1. Constructor called");
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("2. @PostConstruct called");
    }
    
    @Override
    public void afterPropertiesSet() {
        System.out.println("3. InitializingBean.afterPropertiesSet()");
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("4. @PreDestroy called");
    }
    
    @Override
    public void destroy() {
        System.out.println("5. DisposableBean.destroy()");
    }
}
```

**Lifecycle phases:** Instantiation → Dependency Injection → Initialization → Ready → Destruction

## 40. What are `@Primary` and `@Qualifier` annotations used for?

These annotations resolve ambiguity when multiple beans of the same type exist.

```java
// Multiple implementations
@Service
@Primary
public class EmailNotificationService implements NotificationService {
    // This will be injected by default
}

@Service
@Qualifier("sms")
public class SmsNotificationService implements NotificationService {
    // Named qualifier
}

@Service
public class PushNotificationService implements NotificationService {
    // Alternative implementation
}

// Using the beans
@RestController
public class NotificationController {
    
    @Autowired
    private NotificationService defaultService; // Gets @Primary
    
    @Autowired
    @Qualifier("sms")
    private NotificationService smsService; // Gets specific implementation
    
    @Autowired
    private List<NotificationService> allServices; // Gets all implementations
}
```

**@Primary:** Default choice when multiple candidates exist
**@Qualifier:** Specific selection by name or custom qualifier

# Spring Boot Interview Answers (Questions 41-50)

## 41. How do you manage circular dependencies in Spring Boot?

Circular dependencies occur when beans depend on each other. Spring Boot provides several solutions to resolve them.

**Solutions:**
```java
// Problem: Circular dependency
@Service
public class UserService {
    @Autowired
    private OrderService orderService; // Circular!
}

@Service
public class OrderService {
    @Autowired
    private UserService userService; // Circular!
}

// Solution 1: @Lazy annotation
@Service
public class UserService {
    @Autowired
    @Lazy
    private OrderService orderService;
}

// Solution 2: Setter injection
@Service
public class OrderService {
    private UserService userService;
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
```

## 42. How do you connect Spring Boot to a relational database?

Add database dependencies and configure connection properties. Spring Boot auto-configures DataSource and JPA.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Spring Boot automatically creates DataSource, EntityManager, and TransactionManager beans.

## 43. What is Spring Data JPA, and how does it integrate with Spring Boot?

Spring Data JPA provides repository abstraction over JPA, eliminating boilerplate code. Spring Boot auto-configures it when JPA starter is present.

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    // getters and setters
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    Optional<User> findByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findByNameContaining(@Param("name") String name);
}
```

No implementation needed - Spring Data generates it automatically based on method names.

## 44. What is the difference between `@OneToMany` and `@ManyToOne` in Spring Boot?

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

## 45. How can you configure pagination and sorting in Spring Boot with Spring Data JPA?

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

## 46. What is `@Entity` and `@Table` in Spring Boot JPA?

`@Entity` marks a class as a JPA entity, while `@Table` specifies the database table details.

```java
@Entity
@Table(name = "users", schema = "public")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    
    @Column(name = "user_name", nullable = false, length = 100)
    private String name;
    
    @Column(unique = true)
    private String email;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
```

**Key annotations:**
- `@Entity`: Makes class persistable
- `@Table`: Customizes table name and schema
- `@Column`: Customizes column properties
- `@Id`: Primary key field

## 47. What is `@Transactional` used for in Spring Boot?

`@Transactional` ensures database operations execute within a transaction, providing ACID properties and automatic rollback on exceptions.

```java
@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;
    
    @Transactional
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        emailService.sendWelcomeEmail(user.getEmail());
        return savedUser; // Commits if no exception
    }
    
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Read-only transaction
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        userRepository.save(user);
        // Rolls back on any exception
    }
}
```

## 48. How does Spring Boot handle database migrations (e.g., Flyway, Liquibase)?

Spring Boot auto-configures Flyway and Liquibase for database schema versioning and migrations.

**Flyway setup:**
```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

```properties
# application.properties
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
```

**Migration files:**
```sql
-- src/main/resources/db/migration/V1__Create_users_table.sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE
);

-- V2__Add_created_at_column.sql
ALTER TABLE users ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
```

Flyway automatically runs migrations on application startup.

## 49. How do you implement a many-to-many relationship using JPA in Spring Boot?

Use `@ManyToMany` with a join table to represent many-to-many relationships between entities.

```java
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    
    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}

@Entity
public class Role {
    @Id
    private Long id;
    private String name;
    
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
```

**Database structure:**
- `users` table
- `roles` table  
- `user_roles` join table with `user_id` and `role_id`

## 50. What are `CrudRepository`, `JpaRepository`, and `PagingAndSortingRepository` in Spring Data JPA?

These are repository interfaces providing different levels of functionality for data access.

```java
// Basic CRUD operations
public interface UserCrudRepository extends CrudRepository<User, Long> {
    // save(), findById(), findAll(), delete(), etc.
}

// CRUD + Pagination and Sorting
public interface UserPagingRepository extends PagingAndSortingRepository<User, Long> {
    // All CRUD + findAll(Pageable), findAll(Sort)
}

// Most comprehensive - includes JPA specific methods
public interface UserRepository extends JpaRepository<User, Long> {
    // All above + flush(), saveAndFlush(), deleteInBatch(), etc.
}

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll(); // JpaRepository method
    }
    
    @PostMapping("/users/batch")
    public void saveUsers(@RequestBody List<User> users) {
        userRepository.saveAll(users);
        userRepository.flush(); // JPA-specific method
    }
}
```

**Hierarchy:** `CrudRepository` → `PagingAndSortingRepository` → `JpaRepository`

# Spring Boot Interview Answers (Questions 51-60)

## 51. How do you configure Spring Security in Spring Boot?

Add Spring Security starter dependency and create a security configuration class. Spring Boot auto-configures basic security by default.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
```

## 52. What is `@PreAuthorize` annotation in Spring Security?

`@PreAuthorize` provides method-level security by evaluating expressions before method execution. It's used for fine-grained access control.

```java
@RestController
public class UserController {
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
    
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
}
```

Enable method security with `@EnableMethodSecurity` on configuration class.

## 53. What are the steps to enable basic authentication in Spring Boot?

Basic authentication is enabled by default when Spring Security is added. Configure users and customize as needed.

```java
@Configuration
@EnableWebSecurity
public class BasicAuthConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

## 54. How can you configure JWT (JSON Web Tokens) authentication in Spring Boot?

Implement JWT authentication using a custom filter and JWT utility class for token generation and validation.

```java
@Component
public class JwtUtil {
    private String secret = "mySecret";
    
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }
    
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret)
            .parseClaimsJws(token).getBody().getSubject();
    }
}

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
            HttpServletResponse response, FilterChain chain) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            String username = jwtUtil.extractUsername(token.substring(7));
            // Set authentication in SecurityContext
        }
        chain.doFilter(request, response);
    }
}
```

## 55. What is OAuth2, and how do you implement it in Spring Boot?

OAuth2 is an authorization framework that allows third-party applications to access user resources. Spring Boot provides OAuth2 client and resource server support.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```

```properties
# application.properties
spring.security.oauth2.client.registration.google.client-id=your-client-id
spring.security.oauth2.client.registration.google.client-secret=your-secret
spring.security.oauth2.client.registration.google.scope=profile,email
```

```java
@Configuration
public class OAuth2Config {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
```

## 56. How do you manage authorization and roles in Spring Security?

Define roles and authorities, then use them in security configuration and method-level security.

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

@Entity
public class Role {
    @Id
    private Long id;
    private String name; // ROLE_USER, ROLE_ADMIN
}

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
```

## 57. How can you protect REST APIs with Spring Security in Spring Boot?

Configure stateless security with JWT or API keys for REST API protection without sessions.

```java
@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {
    
    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/users").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public List<User> getUsers() {
        return userService.findAll();
    }
}
```

## 58. How do you customize the login page in Spring Security with Spring Boot?

Create a custom login page and configure Spring Security to use it instead of the default.

```java
@Configuration
public class LoginConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/perform-login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error=true")
            );
        return http.build();
    }
}

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login() {
        return "custom-login"; // custom-login.html template
    }
}
```

```html
<!-- custom-login.html -->
<form action="/perform-login" method="post">
    <input type="text" name="username" placeholder="Username" required>
    <input type="password" name="password" placeholder="Password" required>
    <button type="submit">Login</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
```

## 59. What is CSRF protection in Spring Boot, and how is it implemented?

CSRF (Cross-Site Request Forgery) protection prevents unauthorized commands from being transmitted from a user that the web application trusts.

```java
@Configuration
public class CsrfConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers("/api/**") // Disable for REST APIs
            )
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
```

```html
<!-- Include CSRF token in forms -->
<form method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <!-- form fields -->
</form>
```

**For REST APIs:** Disable CSRF and use stateless authentication (JWT/API keys).

## 60. How do you secure the Spring Boot Actuator endpoints?

Configure security for Actuator endpoints to restrict access to sensitive monitoring information.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```properties
# application.properties
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=when-authorized
management.endpoints.web.base-path=/actuator
```

```java
@Configuration
public class ActuatorSecurityConfig {
    
    @Bean
    public SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception {
        http
            .requestMatcher(EndpointRequest.toAnyEndpoint())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(EndpointRequest.to("health", "info")).permitAll()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
```

This restricts sensitive endpoints like `/actuator/metrics` to ADMIN role while keeping `/actuator/health` public.

# Spring Boot Interview Answers (Questions 61-70)

## 61. What is `@SpringBootTest` used for in testing Spring Boot applications?

`@SpringBootTest` loads the complete Spring application context for integration testing. It starts the entire Spring Boot application with all configurations.

```java
@SpringBootTest
class UserServiceIntegrationTest {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void shouldCreateUser() {
        User user = new User("John", "john@example.com");
        User saved = userService.createUser(user);
        
        assertThat(saved.getId()).isNotNull();
        assertThat(userRepository.findById(saved.getId())).isPresent();
    }
}

// For web environment testing
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void shouldReturnUsers() {
        ResponseEntity<String> response = restTemplate.getForEntity("/users", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
```

## 62. How do you test a Spring Boot controller using `MockMvc`?

`MockMvc` allows testing controllers without starting a full HTTP server. It simulates HTTP requests and validates responses.

```java
@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Test
    void shouldReturnUsers() throws Exception {
        List<User> users = Arrays.asList(new User("John", "john@example.com"));
        when(userService.findAll()).thenReturn(users);
        
        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", is("John")));
    }
    
    @Test
    void shouldCreateUser() throws Exception {
        User user = new User("Jane", "jane@example.com");
        when(userService.createUser(any(User.class))).thenReturn(user);
        
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jane\",\"email\":\"jane@example.com\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", is("Jane")));
    }
}
```

## 63. How do you mock services and repositories in Spring Boot tests?

Use `@MockBean` to replace Spring beans with mocks in the application context, or `@Mock` for unit tests.

```java
@SpringBootTest
class UserServiceTest {
    
    @MockBean
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    @Test
    void shouldFindUserById() {
        User user = new User("John", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        User found = userService.findById(1L);
        
        assertThat(found.getName()).isEqualTo("John");
        verify(userRepository).findById(1L);
    }
}

// Unit test with @Mock
@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void shouldCreateUser() {
        User user = new User("Jane", "jane@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        User created = userService.createUser(user);
        
        assertThat(created.getName()).isEqualTo("Jane");
    }
}
```

## 64. What is the difference between `@WebMvcTest` and `@DataJpaTest`?

These are slice tests that load only specific parts of the Spring context for focused testing.

```java
// @WebMvcTest - Tests only web layer (controllers)
@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService; // Service layer is mocked
    
    @Test
    void shouldReturnUsers() throws Exception {
        mockMvc.perform(get("/users"))
            .andExpect(status().isOk());
    }
}

// @DataJpaTest - Tests only JPA repositories
@DataJpaTest
class UserRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void shouldFindByEmail() {
        User user = new User("John", "john@example.com");
        entityManager.persistAndFlush(user);
        
        Optional<User> found = userRepository.findByEmail("john@example.com");
        
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("John");
    }
}
```

**Key differences:**
- `@WebMvcTest`: Web layer only, mocks services
- `@DataJpaTest`: JPA layer only, uses in-memory database

## 65. How can you write unit tests for Spring Boot services using `@MockBean`?

`@MockBean` replaces beans in the Spring context with Mockito mocks, perfect for testing services with mocked dependencies.

```java
@SpringBootTest
class OrderServiceTest {
    
    @MockBean
    private UserRepository userRepository;
    
    @MockBean
    private EmailService emailService;
    
    @Autowired
    private OrderService orderService;
    
    @Test
    void shouldCreateOrderAndSendEmail() {
        User user = new User("John", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        Order order = orderService.createOrder(1L, "Product A");
        
        assertThat(order.getUserId()).isEqualTo(1L);
        assertThat(order.getProduct()).isEqualTo("Product A");
        
        verify(emailService).sendOrderConfirmation(user.getEmail(), order);
    }
    
    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> orderService.createOrder(1L, "Product A"))
            .isInstanceOf(UserNotFoundException.class);
    }
}
```

## 66. What are the best practices for testing Spring Boot applications?

Follow the testing pyramid with unit tests, integration tests, and end-to-end tests in appropriate proportions.

**Best practices:**
```java
// 1. Use slice tests for focused testing
@WebMvcTest // For controllers
@DataJpaTest // For repositories
@JsonTest   // For JSON serialization

// 2. Use @TestConfiguration for test-specific beans
@TestConfiguration
public class TestConfig {
    @Bean
    @Primary
    public Clock testClock() {
        return Clock.fixed(Instant.parse("2023-01-01T00:00:00Z"), ZoneOffset.UTC);
    }
}

// 3. Use test profiles
@ActiveProfiles("test")
@SpringBootTest
class IntegrationTest { }

// 4. Use @DirtiesContext when needed
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StatefulTest { }

// 5. Use TestContainers for real database testing
@Testcontainers
@SpringBootTest
class DatabaseIntegrationTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13");
}
```

## 67. How do you perform integration testing in Spring Boot?

Integration tests verify that different components work together correctly. Use `@SpringBootTest` with real or test configurations.

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void shouldCreateUserEndToEnd() {
        User user = new User("John", "john@example.com");
        
        ResponseEntity<User> response = restTemplate.postForEntity(
            "/users", user, User.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        
        // Verify in database
        Optional<User> saved = userRepository.findById(response.getBody().getId());
        assertThat(saved).isPresent();
        assertThat(saved.get().getName()).isEqualTo("John");
    }
}

// With test database
@SpringBootTest
@Sql("/test-data.sql") // Load test data
class DataIntegrationTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    void shouldFindUsersFromTestData() {
        List<User> users = userService.findAll();
        assertThat(users).hasSize(3); // From test-data.sql
    }
}
```

## 68. How do you use `@AutoConfigureMockMvc` for testing controllers in Spring Boot?

`@AutoConfigureMockMvc` configures MockMvc automatically when used with `@SpringBootTest`, enabling controller testing with full context.

```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void shouldCreateUserWithRealService() throws Exception {
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\",\"email\":\"john@example.com\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", is("John")));
        
        // Verify in real database
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getName()).isEqualTo("John");
    }
}

// With security testing
@SpringBootTest
@AutoConfigureMockMvc
class SecureControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldAllowAdminAccess() throws Exception {
        mockMvc.perform(get("/admin/users"))
            .andExpected(status().isOk());
    }
}
```

## 69. How do you handle database integration tests in Spring Boot without polluting the real database?

Use test-specific database configurations, in-memory databases, or TestContainers to isolate test data.

```java
// Method 1: In-memory database
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class InMemoryDatabaseTest {
    // Uses H2 in-memory database automatically
}

// Method 2: Test-specific properties
@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class TestDatabaseTest { }

// Method 3: TestContainers
@Testcontainers
@SpringBootTest
class ContainerDatabaseTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
}

// Method 4: @Transactional with rollback
@SpringBootTest
@Transactional
class TransactionalTest {
    // Each test runs in transaction and rolls back
}
```

## 70. What is Spring Boot Actuator, and how does it enhance your application?

Spring Boot Actuator provides production-ready features like monitoring, metrics, and health checks out of the box.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```properties
# application.properties
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.health.show-details=always
management.info.env.enabled=true
```

```java
// Custom health indicator
@Component
public class DatabaseHealthIndicator implements HealthIndicator {
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    public Health health() {
        try {
            dataSource.getConnection().close();
            return Health.up()
                .withDetail("database", "Available")
                .build();
        } catch (Exception e) {
            return Health.down()
                .withDetail("database", "Unavailable")
                .withException(e)
                .build();
        }
    }
}

// Custom metrics
@RestController
public class UserController {
    
    private final MeterRegistry meterRegistry;
    private final Counter userCreationCounter;
    
    public UserController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.userCreationCounter = Counter.builder("users.created")
            .description("Number of users created")
            .register(meterRegistry);
    }
    
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        userCreationCounter.increment();
        return userService.createUser(user);
    }
}
```

**Key endpoints:**
- `/actuator/health` - Application health status
- `/actuator/metrics` - Application metrics
- `/actuator/info` - Application information
- `/actuator/prometheus` - Prometheus metrics format

# Spring Boot Interview Answers (Questions 71-75)

## 71. What are the default endpoints provided by Spring Boot Actuator?

Spring Boot Actuator provides several built-in endpoints for monitoring and managing your application. Most are disabled by default for security.

**Default endpoints:**
```properties
# application.properties - Enable specific endpoints
management.endpoints.web.exposure.include=health,info,metrics,env,beans

# Enable all endpoints (not recommended for production)
management.endpoints.web.exposure.include=*
```

**Key default endpoints:**
- `/actuator/health` - Application health status
- `/actuator/info` - Application information
- `/actuator/metrics` - Application metrics
- `/actuator/env` - Environment properties
- `/actuator/beans` - Spring beans information
- `/actuator/configprops` - Configuration properties
- `/actuator/loggers` - Logger configuration
- `/actuator/httptrace` - HTTP request traces
- `/actuator/threaddump` - Thread dump
- `/actuator/heapdump` - Heap dump

**Usage example:**
```bash
curl http://localhost:8080/actuator/health
curl http://localhost:8080/actuator/metrics/jvm.memory.used
```

## 72. How can you expose custom Actuator endpoints in Spring Boot?

Create custom endpoints using `@Endpoint` annotation to expose application-specific monitoring information.

```java
@Component
@Endpoint(id = "custom")
public class CustomEndpoint {
    
    @ReadOperation
    public Map<String, Object> customInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("status", "running");
        info.put("uptime", getUptime());
        info.put("activeUsers", getUserCount());
        return info;
    }
    
    @WriteOperation
    public void resetCounters() {
        // Reset application counters
        System.out.println("Counters reset");
    }
    
    @DeleteOperation
    public void clearCache(@Selector String cacheName) {
        // Clear specific cache
        System.out.println("Cache cleared: " + cacheName);
    }
}
```

**Access the endpoint:**
```bash
# GET operation
curl http://localhost:8080/actuator/custom

# POST operation (write)
curl -X POST http://localhost:8080/actuator/custom

# DELETE operation with selector
curl -X DELETE http://localhost:8080/actuator/custom/userCache
```

## 73. What is the role of `health` and `metrics` endpoints in Spring Boot Actuator?

The `health` endpoint shows application health status, while `metrics` provides detailed performance and resource usage data.

**Health endpoint:**
```java
// Custom health indicator
@Component
public class DatabaseHealthIndicator implements HealthIndicator {
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    public Health health() {
        try {
            Connection connection = dataSource.getConnection();
            connection.close();
            return Health.up()
                .withDetail("database", "Available")
                .withDetail("connections", getConnectionCount())
                .build();
        } catch (Exception e) {
            return Health.down()
                .withDetail("database", "Unavailable")
                .withException(e)
                .build();
        }
    }
}
```

**Metrics endpoint:**
```java
@RestController
public class UserController {
    
    private final Counter userCounter;
    private final Timer requestTimer;
    
    public UserController(MeterRegistry meterRegistry) {
        this.userCounter = Counter.builder("users.created")
            .description("Total users created")
            .register(meterRegistry);
        this.requestTimer = Timer.builder("user.requests")
            .register(meterRegistry);
    }
    
    @GetMapping("/users")
    public List<User> getUsers() {
        return Timer.Sample.start(requestTimer)
            .stop(requestTimer, () -> userService.findAll());
    }
}
```

**Configuration:**
```properties
management.endpoint.health.show-details=always
management.endpoint.metrics.enabled=true
```

## 74. How do you secure actuator endpoints in a production environment?

Secure Actuator endpoints by restricting access, enabling authentication, and exposing only necessary endpoints.

```java
@Configuration
public class ActuatorSecurityConfig {
    
    @Bean
    @Order(1)
    public SecurityFilterChain actuatorFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher(EndpointRequest.toAnyEndpoint())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(EndpointRequest.to("health", "info")).permitAll()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR")
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
```

**Production configuration:**
```properties
# Expose only essential endpoints
management.endpoints.web.exposure.include=health,info,metrics

# Change actuator base path
management.endpoints.web.base-path=/manage

# Use different port for actuator
management.server.port=9090

# Restrict health details
management.endpoint.health.show-details=when-authorized

# Disable sensitive endpoints
management.endpoint.env.enabled=false
management.endpoint.configprops.enabled=false
```

**Network security:**
```yaml
# application.yml
management:
  server:
    port: 9090
    address: 127.0.0.1  # Only localhost access
  endpoints:
    web:
      exposure:
        include: health,metrics
      base-path: /admin
```

## 75. How can you monitor and gather metrics using Spring Boot Actuator?

Use Actuator with monitoring systems like Prometheus, Grafana, or Micrometer for comprehensive application monitoring.

**Prometheus integration:**
```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

```properties
# application.properties
management.endpoints.web.exposure.include=health,info,metrics,prometheus
management.endpoint.prometheus.enabled=true
management.metrics.export.prometheus.enabled=true
```

**Custom metrics:**
```java
@Service
public class OrderService {
    
    private final Counter orderCounter;
    private final Gauge activeOrders;
    private final Timer orderProcessingTimer;
    
    public OrderService(MeterRegistry meterRegistry) {
        this.orderCounter = Counter.builder("orders.created")
            .tag("type", "online")
            .register(meterRegistry);
            
        this.activeOrders = Gauge.builder("orders.active")
            .register(meterRegistry, this, OrderService::getActiveOrderCount);
            
        this.orderProcessingTimer = Timer.builder("orders.processing.time")
            .register(meterRegistry);
    }
    
    public Order createOrder(Order order) {
        return Timer.Sample.start(orderProcessingTimer)
            .stop(orderProcessingTimer, () -> {
                orderCounter.increment();
                return orderRepository.save(order);
            });
    }
    
    private double getActiveOrderCount() {
        return orderRepository.countByStatus("ACTIVE");
    }
}
```

**Monitoring setup:**
```yaml
# prometheus.yml
scrape_configs:
  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']
```

**Key metrics to monitor:**
- JVM memory usage: `jvm.memory.used`
- HTTP requests: `http.server.requests`
- Database connections: `hikaricp.connections.active`
- Custom business metrics: `orders.created`, `users.active`

**Access metrics:**
```bash
# All metrics
curl http://localhost:8080/actuator/metrics

# Specific metric
curl http://localhost:8080/actuator/metrics/jvm.memory.used

# Prometheus format
curl http://localhost:8080/actuator/prometheus
```

