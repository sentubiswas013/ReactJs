# Java Interview Questions - Spoken Answers

## 1. What is coercion in Java?

Coercion is automatic type conversion that Java performs when you assign a smaller data type to a larger one. For example, when you put an int into a double variable, Java automatically converts it. This only works for widening conversions - for narrowing like double to int, you need explicit casting to prevent data loss.

```java
int num = 10;
double result = num; // Automatic coercion - int to double

double d = 10.5;
int i = (int) d; // Explicit casting required - double to int
```

## 2. What is Mockito?

Mockito is a testing framework that creates fake objects called mocks for unit testing. You can control what these mock objects return when their methods are called, which helps isolate the code you're testing from its dependencies. It's very popular for testing Spring applications.

```java
@Mock
private UserRepository userRepository;

@Test
public void testGetUser() {
    when(userRepository.findById(1L)).thenReturn(new User("John"));
    
    User result = userService.getUser(1L);
    
    assertEquals("John", result.getName());
    verify(userRepository).findById(1L);
}
```

## 3. What is BeanFactory?

BeanFactory is Spring's basic IoC container that manages object creation and dependency injection. It uses lazy initialization, meaning beans are created only when you request them. It's the foundation of Spring's dependency injection but ApplicationContext is more commonly used because it has additional features.

```java
BeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans.xml"));
UserService userService = (UserService) factory.getBean("userService");
```

## 4. What is a Java Bean?

A Java Bean is a class that follows specific conventions - it must have private fields with public getter and setter methods, a no-argument constructor, and should implement Serializable. These conventions make the class reusable and compatible with frameworks like Spring for dependency injection.

```java
public class User implements Serializable {
    private String name;
    
    public User() {} // No-arg constructor
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

## 5. What is ApplicationContext?

ApplicationContext is Spring's advanced IoC container that extends BeanFactory with additional enterprise features. Unlike BeanFactory, it eagerly initializes all beans at startup and provides features like event handling, internationalization, and resource loading. This is what you typically use in Spring applications.

```java
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
UserService userService = context.getBean(UserService.class);

// Can also publish events
context.publishEvent(new CustomEvent("data"));
```

## 6. What is the difference between @Primary, @Qualifier, @Component, @Service, @Repository and @PatchMapping?

These annotations serve different purposes in Spring. @Primary marks the preferred bean when multiple candidates exist. @Qualifier specifies exactly which bean to inject by name. @Component, @Service, and @Repository are stereotype annotations for different layers. @PatchMapping maps HTTP PATCH requests to controller methods.

```java
@Component("emailSender")
public class EmailSender implements MessageSender {}

@Component("smsSender")
@Primary // Default choice
public class SmsSender implements MessageSender {}

@Service
public class NotificationService {
    @Autowired
    @Qualifier("emailSender") // Specific choice
    private MessageSender sender;
}

@RestController
public class UserController {
    @PatchMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.partialUpdate(id, user);
    }
}
```

Key differences:
- @Primary: Default bean selection
- @Qualifier: Specific bean selection
- @Component: Generic Spring component
- @Service: Business logic layer
- @Repository: Data access layer
- @PatchMapping: HTTP PATCH method mapping

## 7. How do you integrate a Java application with a cloud environment?

Integrate by containerizing your application with Docker, using cloud databases and services instead of local ones, implementing health checks for monitoring, and configuring environment variables for different cloud environments. Use cloud-specific features like auto-scaling and load balancing.

```java
@RestController
public class HealthController {
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
```

```dockerfile
FROM openjdk:17-jre-slim
COPY target/myapp.jar app.jar
EXPOSE 8080
HEALTHCHECK CMD curl -f http://localhost:8080/health || exit 1
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Additional steps:
- Use cloud databases (RDS, Cloud SQL)
- Configure environment-specific properties
- Implement distributed logging
- Use cloud storage services
- Set up monitoring and alerting

## 8. How do you secure a Java Spring Boot application?

Secure Spring Boot applications using Spring Security for authentication and authorization, implement JWT tokens for stateless authentication, use role-based access control, validate all user inputs, and always use HTTPS in production. Configure CORS properly for web applications.

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

Security best practices:
- Use HTTPS everywhere
- Validate and sanitize inputs
- Implement proper session management
- Use security headers
- Regular security audits