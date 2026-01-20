## Spring Boot Concepts 

### 276. What is Spring Boot?

**Answer:** Spring Boot is an opinionated framework that simplifies Spring application development by providing auto-configuration, embedded servers, and production-ready features out of the box. It eliminates boilerplate configuration and enables rapid application development.

**Key Features:**
- Auto-configuration based on classpath
- Embedded web servers (Tomcat, Jetty, Undertow)
- Production-ready features (metrics, health checks)
- Minimal configuration required
- Convention over configuration

---

### 277. What are the advantages of Spring Boot?

**Answer:** Key advantages include auto-configuration that reduces setup time, embedded servers eliminating deployment complexity, starter dependencies for easy dependency management, production-ready features like health checks, and convention over configuration approach.

**Main Advantages:**
- **Rapid Development:** Minimal setup and configuration
- **Embedded Servers:** No external server deployment needed
- **Auto-Configuration:** Intelligent defaults based on dependencies
- **Production Ready:** Built-in monitoring and management features
- **Microservices Friendly:** Perfect for cloud-native applications

---

### 278. What is auto-configuration in Spring Boot?

**Answer:** Auto-configuration automatically configures Spring beans based on classpath dependencies and existing configurations. It uses conditional annotations to intelligently set up components, reducing manual configuration while allowing customization when needed.

**How it works:**
- Scans classpath for dependencies
- Uses `@Conditional` annotations
- Applies sensible defaults
- Can be overridden by explicit configuration

**Example:**
```java
@ConditionalOnClass(DataSource.class)
@ConditionalOnMissingBean(DataSource.class)
public class DataSourceAutoConfiguration {
    // Auto-configuration logic
}
```

---

### 279. What are Spring Boot starters?

**Answer:** Starters are dependency descriptors that provide a one-stop shop for Spring technologies. They include all necessary dependencies and auto-configuration for specific functionality like web development, data access, or security.

**Popular Starters:**
- `spring-boot-starter-web` - Web applications
- `spring-boot-starter-data-jpa` - JPA data access
- `spring-boot-starter-security` - Security features
- `spring-boot-starter-test` - Testing framework
- `spring-boot-starter-actuator` - Production features

**Benefits:**
- Simplified dependency management
- Compatible version combinations
- Reduced configuration overhead

---

### 280. What is @SpringBootApplication annotation?

**Answer:** @SpringBootApplication is a composite annotation combining @Configuration, @EnableAutoConfiguration, and @ComponentScan. It marks the main class and enables auto-configuration, component scanning, and configuration capabilities.

**Equivalent to:**
```java
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

**What it does:**
- **@Configuration:** Allows bean definitions
- **@EnableAutoConfiguration:** Enables auto-configuration
- **@ComponentScan:** Scans for components in current package and sub-packages

---

### 281. What is application.properties file?

**Answer:** application.properties is the main configuration file in Spring Boot where you define application settings, database connections, server ports, and custom properties. It supports environment-specific configurations and property placeholders.

**Common Properties:**
```properties
# Server configuration
server.port=8080

# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=user
spring.datasource.password=password

# Custom properties
app.name=MyApplication
app.version=1.0.0
```

**Features:**
- Environment-specific profiles (`application-dev.properties`)
- Property placeholders (`${app.name}`)
- Type-safe configuration with `@ConfigurationProperties`

---

### 282. What is the difference between @Component, @Service, and @Repository?

**Answer:** All are stereotype annotations for Spring beans. @Component is generic, @Service indicates business logic layer, and @Repository marks data access layer with additional exception translation capabilities. They're functionally similar but provide semantic meaning.

**Comparison:**

| Annotation | Purpose | Layer | Special Features |
|------------|---------|-------|------------------|
| @Component | Generic stereotype | Any | Basic bean registration |
| @Service | Business logic | Service | Semantic meaning only |
| @Repository | Data access | Persistence | Exception translation |

**Usage:**
```java
@Component
public class GenericComponent { }

@Service
public class UserService { }

@Repository
public class UserRepository { }
```

---

### 283. What is @Autowired annotation?

**Answer:** @Autowired enables automatic dependency injection by type. Spring automatically injects matching beans into fields, constructors, or setter methods. It supports required/optional injection and works with collections of beans.

**Usage Examples:**
```java
// Field injection
@Autowired
private UserService userService;

// Constructor injection (recommended)
@Autowired
public UserController(UserService userService) {
    this.userService = userService;
}

// Setter injection
@Autowired
public void setUserService(UserService userService) {
    this.userService = userService;
}

// Optional injection
@Autowired(required = false)
private OptionalService optionalService;
```

**Best Practice:** Use constructor injection for mandatory dependencies.

---

### 284. What is @Qualifier annotation?

**Answer:** @Qualifier resolves ambiguity when multiple beans of the same type exist. It specifies which exact bean to inject by name or custom qualifier, working alongside @Autowired to provide precise dependency resolution.

**Problem it solves:**
```java
// Multiple implementations
@Service("emailService")
public class EmailNotificationService implements NotificationService { }

@Service("smsService")
public class SmsNotificationService implements NotificationService { }
```

**Solution:**
```java
@Autowired
@Qualifier("emailService")
private NotificationService notificationService;
```

**Custom Qualifier:**
```java
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailNotification { }

@Service
@EmailNotification
public class EmailNotificationService implements NotificationService { }
```

---

### 285. How do you create REST APIs with Spring Boot?

**Answer:** Use @RestController for the class, @RequestMapping or specific annotations like @GetMapping for endpoints, return objects that are automatically serialized to JSON, and leverage Spring Boot's auto-configuration for web components.

**Basic REST Controller:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
```

**Key Annotations:**
- `@RestController` - Combines @Controller and @ResponseBody
- `@RequestMapping` - Maps HTTP requests to methods
- `@GetMapping`, `@PostMapping`, etc. - HTTP method-specific mappings
- `@PathVariable` - Extracts values from URL path
- `@RequestBody` - Binds request body to method parameter

---

## Summary

Spring Boot significantly simplifies Spring application development by providing:

**Key Benefits:**
- **Auto-Configuration:** Intelligent defaults based on classpath
- **Starters:** Curated dependency sets for common use cases
- **Embedded Servers:** No external deployment complexity
- **Production Features:** Built-in monitoring and management
- **Convention over Configuration:** Minimal setup required

**Essential Annotations:**
- `@SpringBootApplication` - Main application class
- `@RestController` - REST API endpoints
- `@Autowired` - Dependency injection
- `@Qualifier` - Resolves injection ambiguity
- `@Component/@Service/@Repository` - Bean stereotypes

**Configuration:**
- `application.properties` for external configuration
- Environment-specific profiles supported
- Type-safe configuration with `@ConfigurationProperties`

Spring Boot enables rapid development of production-ready applications with minimal configuration while maintaining the flexibility to customize when needed.