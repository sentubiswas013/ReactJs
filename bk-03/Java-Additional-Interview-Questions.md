# Additional Java Interview Questions - Quick Answers

## 1. What is coercion in Java?

Coercion in Java is the automatic conversion of one data type to another by the compiler. It happens implicitly when you assign a smaller data type to a larger one or in expressions with mixed types.

Java performs widening conversions automatically but requires explicit casting for narrowing conversions to prevent data loss.

```java
// Implicit coercion (widening)
int num = 10;
double result = num; // int automatically converted to double

// Mixed type expression
int a = 5;
double b = 2.5;
double sum = a + b; // int 'a' coerced to double before addition

// Explicit casting required (narrowing)
double d = 10.5;
int i = (int) d; // Must explicitly cast double to int
```

## 2. What is Mockito?

Mockito is a popular Java testing framework that creates mock objects for unit testing. It allows you to simulate dependencies and verify interactions without using real implementations.

Mockito helps isolate the code under test by replacing dependencies with controllable mock objects that return predefined responses.

```java
// Creating mocks
@Mock
private UserRepository userRepository;

@Test
public void testGetUser() {
    // Arrange - define mock behavior
    User mockUser = new User("John", "john@example.com");
    when(userRepository.findById(1L)).thenReturn(mockUser);
    
    // Act
    User result = userService.getUser(1L);
    
    // Assert
    assertEquals("John", result.getName());
    verify(userRepository).findById(1L); // Verify method was called
}
```

## 3. What is BeanFactory?

BeanFactory is the basic IoC container in Spring that manages object creation and dependency injection. It's the foundation of Spring's dependency injection framework.

BeanFactory uses lazy initialization - beans are created only when requested. It provides basic container functionality for managing bean lifecycle and dependencies.

```java
// Using BeanFactory
BeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans.xml"));
UserService userService = (UserService) factory.getBean("userService");
```

## 4. What is a Java Bean?

A Java Bean is a reusable software component that follows specific conventions: private fields, public getter/setter methods, no-argument constructor, and implements Serializable.

Java Beans are used in frameworks like Spring for dependency injection and in JSP for data binding. They provide a standard way to encapsulate data.

```java
public class User implements Serializable {
    private String name;
    private int age;
    
    // No-argument constructor
    public User() {}
    
    // Getter and setter methods
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
```

## 5. What is ApplicationContext?

ApplicationContext is Spring's advanced IoC container that extends BeanFactory with additional enterprise features like event handling, internationalization, and resource loading.

Unlike BeanFactory, ApplicationContext eagerly initializes beans at startup and provides more comprehensive container functionality for enterprise applications.

```java
// Creating ApplicationContext
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
UserService userService = context.getBean(UserService.class);

// Publishing events
context.publishEvent(new CustomEvent("data"));
```

## 6. What is the difference between @Primary, @Qualifier, @Component, @Service, @Repository and @PatchMapping?

These are different types of Spring annotations serving distinct purposes:

**Dependency Injection:**
- **@Primary:** Marks preferred bean when multiple candidates exist
- **@Qualifier:** Specifies which bean to inject by name

**Stereotype Annotations:**
- **@Component:** Generic Spring-managed component
- **@Service:** Business logic layer component
- **@Repository:** Data access layer component

**HTTP Mapping:**
- **@PatchMapping:** Maps HTTP PATCH requests to handler methods

```java
@Component("emailSender")
public class EmailSender implements MessageSender { }

@Component("smsSender")
@Primary // This will be injected by default
public class SmsSender implements MessageSender { }

@Service
public class NotificationService {
    @Autowired
    @Qualifier("emailSender") // Specify which implementation
    private MessageSender messageSender;
}

@RestController
public class UserController {
    @PatchMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.partialUpdate(id, user);
    }
}
```

## 7. How do you integrate a Java application with a cloud environment?

Cloud integration involves containerizing the application, using cloud services, and implementing cloud-native patterns for scalability and resilience.

Key steps include containerization with Docker, using cloud databases and messaging services, implementing health checks, and leveraging cloud-specific features.

```java
// Cloud-ready Spring Boot application
@SpringBootApplication
public class CloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
    }
}

// Health check endpoint
@RestController
public class HealthController {
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "timestamp", Instant.now().toString());
    }
}

// Cloud configuration
# application-cloud.yml
spring:
  datasource:
    url: ${DATABASE_URL}
  cloud:
    aws:
      region: us-east-1
      s3:
        bucket: my-app-bucket
```

```dockerfile
# Dockerfile for cloud deployment
FROM openjdk:17-jre-slim
COPY target/myapp.jar app.jar
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/health || exit 1
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 8. How do you secure a Java Spring Boot application?

Spring Boot security involves authentication, authorization, HTTPS configuration, and protection against common vulnerabilities using Spring Security framework.

Implement JWT tokens for stateless authentication, role-based access control, input validation, and secure communication protocols.

```java
// Security configuration
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        return http.build();
    }
}

// Secure REST endpoint
@RestController
@PreAuthorize("hasRole('USER')")
public class SecureController {
    
    @GetMapping("/api/user/profile")
    @PreAuthorize("hasRole('USER')")
    public UserProfile getProfile(Authentication auth) {
        return userService.getProfile(auth.getName());
    }
    
    @PostMapping("/api/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@Valid @RequestBody User user) {
        return userService.create(user);
    }
}

// Input validation
public class User {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50)
    private String name;
    
    @Email(message = "Invalid email format")
    private String email;
    
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", 
             message = "Password must be at least 8 characters with letters and numbers")
    private String password;
}
```

```yaml
# Security properties
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://your-auth-server.com
  
server:
  ssl:
    enabled: true
    key-store: classpath:keystore.p12
    key-store-password: ${SSL_PASSWORD}
    key-store-type: PKCS12
```

These security measures protect against common vulnerabilities like SQL injection, XSS, CSRF, and unauthorized access while ensuring secure communication and data validation.