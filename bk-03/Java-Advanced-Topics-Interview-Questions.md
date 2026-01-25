# Advanced Java Interview Questions - Quick Answers

## 1. What is CORS, and how does it work?

CORS (Cross-Origin Resource Sharing) is a security mechanism that allows web applications running on one domain to access resources from another domain. Browsers enforce same-origin policy by default, and CORS provides a way to relax this restriction.

CORS works through HTTP headers where the server specifies which origins, methods, and headers are allowed for cross-origin requests.

```java
// Spring Boot CORS configuration
@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow specific origin
public class ApiController {
    
    @GetMapping("/api/data")
    public ResponseEntity<Data> getData() {
        return ResponseEntity.ok(data);
    }
}

// Global CORS configuration
@Configuration
public class CorsConfig {
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://myapp.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}
```

## 2. Monolithic vs Microservices Architecture

**Monolithic Architecture:**
- Single deployable unit containing all functionality
- Shared database and runtime
- Simple deployment but difficult to scale individual components
- Technology stack consistency across entire application

**Microservices Architecture:**
- Multiple independent services with specific business responsibilities
- Each service has its own database and can use different technologies
- Independent deployment and scaling
- Increased complexity but better fault isolation

```java
// Monolithic approach - everything in one application
@RestController
public class MonolithController {
    @Autowired private UserService userService;
    @Autowired private OrderService orderService;
    @Autowired private PaymentService paymentService;
    @Autowired private NotificationService notificationService;
}

// Microservices approach - separate services
// User Service
@RestController
public class UserController {
    @Autowired private UserService userService;
}

// Order Service  
@RestController
public class OrderController {
    @Autowired private OrderService orderService;
    @Autowired private UserServiceClient userServiceClient; // External call
}
```

## 3. How did microservices communicate with each other?

Microservices communicate through well-defined APIs using synchronous and asynchronous patterns. Common approaches include REST APIs, message queues, and event-driven communication.

**Synchronous Communication:**
- REST APIs with HTTP
- gRPC for high-performance communication
- GraphQL for flexible data fetching

**Asynchronous Communication:**
- Message queues (RabbitMQ, Apache Kafka)
- Event-driven architecture
- Publish-subscribe patterns

```java
// Synchronous communication with RestTemplate
@Service
public class OrderService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    public Order createOrder(OrderRequest request) {
        // Call User Service to validate user
        User user = restTemplate.getForObject(
            "http://user-service/users/" + request.getUserId(), 
            User.class
        );
        
        // Call Payment Service
        PaymentResponse payment = restTemplate.postForObject(
            "http://payment-service/payments", 
            new PaymentRequest(request.getAmount()), 
            PaymentResponse.class
        );
        
        return new Order(user, payment);
    }
}

// Asynchronous communication with messaging
@Component
public class OrderEventPublisher {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void publishOrderCreated(Order order) {
        OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getUserId());
        rabbitTemplate.convertAndSend("order.exchange", "order.created", event);
    }
}

@RabbitListener(queues = "notification.queue")
public void handleOrderCreated(OrderCreatedEvent event) {
    // Send notification asynchronously
    notificationService.sendOrderConfirmation(event.getUserId(), event.getOrderId());
}
```

## 4. How do you Handle Failures in Microservices?

Failure handling in microservices involves implementing resilience patterns like circuit breakers, retries, timeouts, and fallback mechanisms to prevent cascading failures.

**Key Patterns:**
- Circuit Breaker pattern to prevent cascading failures
- Retry mechanisms with exponential backoff
- Timeout configurations
- Fallback responses
- Health checks and monitoring

```java
// Circuit breaker with Resilience4j
@Component
public class UserServiceClient {
    
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackUser")
    @Retry(name = "user-service")
    @TimeLimiter(name = "user-service")
    public CompletableFuture<User> getUser(Long userId) {
        return CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject("/users/" + userId, User.class);
        });
    }
    
    // Fallback method
    public CompletableFuture<User> fallbackUser(Long userId, Exception ex) {
        return CompletableFuture.completedFuture(
            new User(userId, "Unknown User", "unknown@example.com")
        );
    }
}

// Configuration
resilience4j:
  circuitbreaker:
    instances:
      user-service:
        failure-rate-threshold: 50
        wait-duration-in-open-state: 30s
        sliding-window-size: 10
  retry:
    instances:
      user-service:
        max-attempts: 3
        wait-duration: 1s
```

## 5. How do you Handle Exception Handling in Spring Boot?

Spring Boot exception handling uses @ControllerAdvice for global exception handling, custom exception classes, and proper HTTP status codes to provide consistent error responses.

**Approaches:**
- Global exception handler with @ControllerAdvice
- Custom exception classes
- Proper HTTP status codes
- Structured error responses

```java
// Custom exceptions
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

public class ValidationException extends RuntimeException {
    private final Map<String, String> errors;
    
    public ValidationException(Map<String, String> errors) {
        this.errors = errors;
    }
    
    public Map<String, String> getErrors() { return errors; }
}

// Global exception handler
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("USER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ValidationException ex) {
        ErrorResponse error = new ErrorResponse("VALIDATION_ERROR", "Invalid input", ex.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse("INTERNAL_ERROR", "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

// Error response structure
public class ErrorResponse {
    private String code;
    private String message;
    private Map<String, String> details;
    private LocalDateTime timestamp;
    
    // constructors and getters
}
```

## 6. How do you Improve Performance in Spring Boot Application?

Performance improvement involves optimizing database queries, implementing caching, using connection pooling, enabling compression, and monitoring application metrics.

**Key Strategies:**
- Database query optimization and indexing
- Caching frequently accessed data
- Connection pooling for database connections
- Async processing for non-blocking operations
- JVM tuning and monitoring

```java
// Caching configuration
@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofMinutes(10)));
        return cacheManager;
    }
}

// Service with caching
@Service
public class UserService {
    
    @Cacheable("users")
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    
    @Async
    public CompletableFuture<Void> sendNotificationAsync(String email) {
        // Non-blocking email sending
        emailService.sendEmail(email);
        return CompletableFuture.completedFuture(null);
    }
}

// Database optimization
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE u.status = :status")
    List<User> findActiveUsers(@Param("status") String status);
    
    @Query(value = "SELECT * FROM users WHERE created_date > ?1", nativeQuery = true)
    List<User> findRecentUsers(LocalDateTime date);
}

# Application properties for performance
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: false
  compression:
    enabled: true
server:
  compression:
    enabled: true
    mime-types: application/json,text/html,text/css,application/javascript
```

## 7. Have you worked with the Java 11 HTTP Client? How does it differ from the HTTP clients used in earlier Java versions?

Java 11 introduced a built-in HTTP Client that supports HTTP/2, WebSocket, and reactive programming. It's more modern and feature-rich compared to the legacy HttpURLConnection.

**Key Differences:**
- Built-in support for HTTP/2 and WebSocket
- Asynchronous and synchronous operations
- Better API design with builder pattern
- Support for reactive streams
- No external dependencies required

```java
// Java 11 HTTP Client - modern approach
HttpClient client = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2)
    .connectTimeout(Duration.ofSeconds(10))
    .build();

// Synchronous request
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Content-Type", "application/json")
    .GET()
    .build();

HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());

// Asynchronous request
CompletableFuture<HttpResponse<String>> futureResponse = 
    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

futureResponse.thenApply(HttpResponse::body)
              .thenAccept(System.out::println);

// POST request with JSON body
HttpRequest postRequest = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Content-Type", "application/json")
    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
    .build();

// Legacy HttpURLConnection - older approach
URL url = new URL("https://api.example.com/users");
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
connection.setRequestMethod("GET");
connection.setConnectTimeout(10000);

BufferedReader reader = new BufferedReader(
    new InputStreamReader(connection.getInputStream()));
String response = reader.lines().collect(Collectors.joining());
```

The Java 11 HTTP Client provides a more modern, efficient, and developer-friendly API compared to the legacy HttpURLConnection, with better support for modern web protocols and asynchronous programming patterns.