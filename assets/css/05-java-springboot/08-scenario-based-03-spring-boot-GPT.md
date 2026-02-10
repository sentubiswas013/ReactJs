# Spring Boot – Practical Interview Questions

## 1. Application Design & Architecture

### 1. How would you design a RESTful API for a library management system using Spring Boot?

I'd create **controllers for each resource** (books, members, loans), use **DTOs** for request/response, implement **service layer** for business logic, and use **JPA repositories** for data access.

```java
// Entity
@Entity
class Book {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private boolean available;
}

// Repository
@Repository
interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAvailable(boolean available);
}

// Service
@Service
class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book borrowBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setAvailable(false);
        return bookRepository.save(book);
    }
}

// Controller
@RestController
@RequestMapping("/api/books")
class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<Book> borrowBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.borrowBook(id));
    }
}
```

**Key points:**
- RESTful endpoints: GET /books, POST /books/{id}/borrow
- Layered architecture: Controller → Service → Repository
- Use DTOs to separate API contract from entities
- Exception handling with @ControllerAdvice

---

### 2. How do you structure a Spring Boot project for a microservices architecture?

I'd create **separate Spring Boot applications** for each service, use **API Gateway** for routing, implement **service discovery** with Eureka, and use **Spring Cloud Config** for centralized configuration.

```
project-root/
├── api-gateway/          # Spring Cloud Gateway
├── config-server/        # Spring Cloud Config
├── discovery-server/     # Eureka Server
├── book-service/         # Microservice
│   ├── src/main/java/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   └── BookServiceApplication.java
│   └── application.yml
├── member-service/       # Microservice
└── loan-service/         # Microservice
```

```java
// API Gateway
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}

// application.yml for Gateway
spring:
  cloud:
    gateway:
      routes:
        - id: book-service
          uri: lb://BOOK-SERVICE
          predicates:
            - Path=/api/books/**

// Eureka Server
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }
}

// Microservice
@SpringBootApplication
@EnableDiscoveryClient
public class BookServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }
}
```

**Key points:**
- Each service is independent Spring Boot app
- API Gateway for single entry point
- Service discovery for dynamic routing
- Separate databases per service

---

### 3. Explain how you would implement multi-module Spring Boot applications.

I'd use **Maven/Gradle multi-module** structure with a parent POM and separate modules for common code, domain, and services. This promotes code reuse.

```
library-system/
├── pom.xml                    # Parent POM
├── library-common/            # Shared utilities
│   └── src/main/java/
│       └── dto/
│       └── exception/
├── library-domain/            # Entities
│   └── src/main/java/
│       └── entity/
└── library-service/           # Main application
    └── src/main/java/
        └── controller/
        └── service/
```

```xml
<!-- Parent pom.xml -->
<project>
    <groupId>com.library</groupId>
    <artifactId>library-system</artifactId>
    <packaging>pom</packaging>
    
    <modules>
        <module>library-common</module>
        <module>library-domain</module>
        <module>library-service</module>
    </modules>
    
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>3.1.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>

<!-- library-service/pom.xml -->
<project>
    <parent>
        <groupId>com.library</groupId>
        <artifactId>library-system</artifactId>
        <version>1.0.0</version>
    </parent>
    
    <artifactId>library-service</artifactId>
    
    <dependencies>
        <dependency>
            <groupId>com.library</groupId>
            <artifactId>library-common</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.library</groupId>
            <artifactId>library-domain</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
</project>
```

**Key points:**
- Parent POM manages dependencies
- Common module for shared utilities
- Domain module for entities
- Service module depends on others

---

### 4. How would you design a banking application to handle multiple types of transactions using Spring Boot?

I'd use **Strategy pattern** for transaction types, implement **@Transactional** for ACID properties, and use **event-driven architecture** for audit logging.

```java
// Transaction types
interface TransactionStrategy {
    void execute(Account account, double amount);
}

@Component
class DepositStrategy implements TransactionStrategy {
    @Override
    public void execute(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
    }
}

@Component
class WithdrawStrategy implements TransactionStrategy {
    @Override
    public void execute(Account account, double amount) {
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException();
        }
        account.setBalance(account.getBalance() - amount);
    }
}

// Service
@Service
class TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    private Map<String, TransactionStrategy> strategies;

    @Autowired
    public TransactionService(List<TransactionStrategy> strategyList) {
        this.strategies = strategyList.stream()
            .collect(Collectors.toMap(
                s -> s.getClass().getSimpleName(),
                s -> s
            ));
    }

    @Transactional
    public Transaction processTransaction(String accountId, double amount, String type) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException());
        
        TransactionStrategy strategy = strategies.get(type + "Strategy");
        strategy.execute(account, amount);
        
        accountRepository.save(account);
        
        Transaction transaction = new Transaction(accountId, amount, type);
        eventPublisher.publishEvent(new TransactionEvent(transaction));
        
        return transaction;
    }
}

// Controller
@RestController
@RequestMapping("/api/transactions")
class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> processTransaction(@RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.processTransaction(
            request.getAccountId(),
            request.getAmount(),
            request.getType()
        );
        return ResponseEntity.ok(transaction);
    }
}
```

**Key points:**
- Strategy pattern for different transaction types
- @Transactional ensures atomicity
- Event publishing for audit trail
- Validation and exception handling

---

### 5. How do you manage environment-specific configurations (dev, test, prod) in Spring Boot?

I'd use **Spring Profiles** with separate application-{profile}.yml files and externalize sensitive data using **environment variables** or **Spring Cloud Config**.

```yaml
# application.yml (common)
spring:
  application:
    name: library-service
  jpa:
    hibernate:
      ddl-auto: validate

# application-dev.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/library_dev
    username: dev_user
    password: dev_pass
  jpa:
    show-sql: true

# application-prod.yml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    show-sql: false
```

```java
// Profile-specific beans
@Configuration
@Profile("dev")
class DevConfig {
    @Bean
    public DataSource dataSource() {
        return new H2DataSource();
    }
}

@Configuration
@Profile("prod")
class ProdConfig {
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}

// Using @Value with defaults
@Component
class AppConfig {
    @Value("${app.max-connections:10}")
    private int maxConnections;
    
    @Value("${app.timeout:5000}")
    private int timeout;
}
```

**Activation:**
```bash
# Command line
java -jar app.jar --spring.profiles.active=prod

# Environment variable
export SPRING_PROFILES_ACTIVE=prod

# application.yml
spring:
  profiles:
    active: dev
```

**Key points:**
- Separate files per environment
- Use environment variables for secrets
- Profile-specific beans with @Profile
- Default values with @Value

---

## 2. Dependency Injection & Beans

### 6. What are the differences between @Component, @Service, @Repository, and @Controller? When would you use each?

They're all **stereotype annotations** for Spring beans, but have different semantic meanings. @Component is generic, @Service for business logic, @Repository for data access, @Controller for web layer.

```java
// @Component - Generic Spring bean
@Component
class EmailValidator {
    public boolean isValid(String email) {
        return email.contains("@");
    }
}

// @Service - Business logic layer
@Service
class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(User user) {
        // Business logic
        return userRepository.save(user);
    }
}

// @Repository - Data access layer
@Repository
interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

// @Controller - Web layer (returns views)
@Controller
class UserViewController {
    @GetMapping("/users")
    public String listUsers(Model model) {
        return "users";
    }
}

// @RestController - REST API (returns JSON)
@RestController
@RequestMapping("/api/users")
class UserApiController {
    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }
}
```

**Key differences:**
| Annotation | Layer | Purpose | Exception Translation |
|------------|-------|---------|----------------------|
| @Component | Any | Generic bean | No |
| @Service | Business | Business logic | No |
| @Repository | Data | Data access | Yes (DataAccessException) |
| @Controller | Web | MVC controller | No |
| @RestController | Web | REST API | No |

**Key points:**
- All are specializations of @Component
- @Repository adds exception translation
- @RestController = @Controller + @ResponseBody
- Use appropriate annotation for clarity

---

### 7. How would you inject different implementations of an interface dynamically?

I'd use **@Qualifier** to specify which implementation to inject, or use **@Primary** for default, or inject all implementations as a **List** and select at runtime.

```java
// Interface
interface PaymentService {
    void processPayment(double amount);
}

// Implementations
@Service("creditCard")
class CreditCardPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Credit card payment: " + amount);
    }
}

@Service("paypal")
class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("PayPal payment: " + amount);
    }
}

// Method 1: Using @Qualifier
@Service
class OrderService {
    @Autowired
    @Qualifier("creditCard")
    private PaymentService paymentService;
}

// Method 2: Using @Primary
@Service
@Primary
class DefaultPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Default payment: " + amount);
    }
}

// Method 3: Inject all and select at runtime
@Service
class DynamicOrderService {
    private Map<String, PaymentService> paymentServices;

    @Autowired
    public DynamicOrderService(List<PaymentService> services) {
        this.paymentServices = services.stream()
            .collect(Collectors.toMap(
                s -> s.getClass().getSimpleName(),
                s -> s
            ));
    }

    public void processOrder(String paymentType, double amount) {
        PaymentService service = paymentServices.get(paymentType);
        service.processPayment(amount);
    }
}

// Method 4: Using ApplicationContext
@Service
class ContextBasedService {
    @Autowired
    private ApplicationContext context;

    public void processPayment(String beanName, double amount) {
        PaymentService service = context.getBean(beanName, PaymentService.class);
        service.processPayment(amount);
    }
}
```

**Key points:**
- @Qualifier for specific bean selection
- @Primary for default implementation
- Inject List for runtime selection
- ApplicationContext for dynamic lookup

---

### 8. How do you handle circular dependencies in Spring Boot?

I'd use **@Lazy** annotation, **setter injection** instead of constructor, or **refactor** to remove the circular dependency (best approach).

```java
// Problem: Circular dependency
@Service
class ServiceA {
    @Autowired
    private ServiceB serviceB; // ServiceB depends on ServiceA
}

@Service
class ServiceB {
    @Autowired
    private ServiceA serviceA; // Circular!
}

// Solution 1: Use @Lazy
@Service
class ServiceA {
    @Autowired
    @Lazy
    private ServiceB serviceB;
}

// Solution 2: Setter injection
@Service
class ServiceA {
    private ServiceB serviceB;

    @Autowired
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}

// Solution 3: Refactor (BEST)
@Service
class ServiceA {
    @Autowired
    private CommonService commonService;
}

@Service
class ServiceB {
    @Autowired
    private CommonService commonService;
}

@Service
class CommonService {
    // Shared logic extracted here
}
```

**Key points:**
- @Lazy delays bean initialization
- Setter injection breaks circular chain
- Refactoring is the best solution
- Circular dependencies indicate design issue

---

### 9. Explain the difference between singleton and prototype beans and when you would use each.

**Singleton** creates one instance per Spring container (default), **prototype** creates new instance each time. Use singleton for stateless beans, prototype for stateful.

```java
// Singleton (default)
@Service
@Scope("singleton")
class UserService {
    // Shared instance, stateless
}

// Prototype
@Component
@Scope("prototype")
class ShoppingCart {
    private List<Item> items = new ArrayList<>();
    // New instance per user
}

// Request scope (web apps)
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
class RequestContext {
    private String requestId;
}

// Session scope
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
class UserSession {
    private User currentUser;
}

// Usage
@RestController
class CartController {
    @Autowired
    private ApplicationContext context;

    @PostMapping("/cart")
    public ShoppingCart createCart() {
        // New instance each time
        return context.getBean(ShoppingCart.class);
    }
}
```

**Scope comparison:**
| Scope | Instances | Use Case |
|-------|-----------|----------|
| singleton | 1 per container | Stateless services |
| prototype | New per request | Stateful objects |
| request | 1 per HTTP request | Request data |
| session | 1 per HTTP session | User session |

**Key points:**
- Singleton is default and most common
- Prototype for stateful beans
- Use proxyMode for request/session scopes
- Thread-safety important for singleton

---

### 10. How would you conditionally create beans based on application properties?

I'd use **@ConditionalOnProperty** to create beans based on configuration values, or **@Profile** for environment-based creation.

```java
// Method 1: @ConditionalOnProperty
@Configuration
class CacheConfig {
    @Bean
    @ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
    public CacheManager redisCacheManager() {
        return new RedisCacheManager();
    }

    @Bean
    @ConditionalOnProperty(name = "cache.enabled", havingValue = "false", matchIfMissing = true)
    public CacheManager noCacheManager() {
        return new NoOpCacheManager();
    }
}

// Method 2: @ConditionalOnMissingBean
@Configuration
class DatabaseConfig {
    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}

// Method 3: Custom condition
class OnDatabaseTypeCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String dbType = context.getEnvironment().getProperty("database.type");
        return "mysql".equals(dbType);
    }
}

@Configuration
class CustomConfig {
    @Bean
    @Conditional(OnDatabaseTypeCondition.class)
    public DataSource mysqlDataSource() {
        return new MySQLDataSource();
    }
}

// Method 4: @ConditionalOnClass
@Configuration
@ConditionalOnClass(RedisTemplate.class)
class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        return new RedisTemplate<>();
    }
}
```

**application.yml:**
```yaml
cache:
  enabled: true
database:
  type: mysql
```

**Common conditional annotations:**
- @ConditionalOnProperty - Based on property value
- @ConditionalOnClass - If class is present
- @ConditionalOnMissingBean - If bean doesn't exist
- @ConditionalOnExpression - SpEL expression
- @Profile - Environment-based

**Key points:**
- Conditional beans for flexible configuration
- Use matchIfMissing for default behavior
- Custom conditions for complex logic
- Combine multiple conditions with @Conditional


---

## 3. REST APIs & Controllers

### 11. How would you implement exception handling globally in a REST API?

I'd use **@RestControllerAdvice** with **@ExceptionHandler** methods to handle exceptions globally and return consistent error responses.

```java
// Custom exception
class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

// Error response DTO
class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
    // Getters
}

// Global exception handler
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(", "));
        ErrorResponse error = new ErrorResponse(400, message);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse(500, "Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

**Key points:**
- @RestControllerAdvice applies to all controllers
- Specific exception handlers for different error types
- Return consistent error response structure
- Log exceptions for debugging

---

### 12. How would you implement versioning for REST APIs in Spring Boot?

I'd use **URI versioning** (most common), **header versioning**, or **media type versioning**. URI versioning is simplest and most visible.

```java
// Method 1: URI versioning (recommended)
@RestController
@RequestMapping("/api/v1/users")
class UserControllerV1 {
    @GetMapping
    public List<UserV1> getUsers() {
        return userService.getAllUsersV1();
    }
}

@RestController
@RequestMapping("/api/v2/users")
class UserControllerV2 {
    @GetMapping
    public List<UserV2> getUsers() {
        return userService.getAllUsersV2();
    }
}

// Method 2: Request parameter versioning
@RestController
@RequestMapping("/api/users")
class UserController {
    @GetMapping(params = "version=1")
    public List<UserV1> getUsersV1() {
        return userService.getAllUsersV1();
    }

    @GetMapping(params = "version=2")
    public List<UserV2> getUsersV2() {
        return userService.getAllUsersV2();
    }
}

// Method 3: Header versioning
@RestController
@RequestMapping("/api/users")
class UserController {
    @GetMapping(headers = "X-API-VERSION=1")
    public List<UserV1> getUsersV1() {
        return userService.getAllUsersV1();
    }

    @GetMapping(headers = "X-API-VERSION=2")
    public List<UserV2> getUsersV2() {
        return userService.getAllUsersV2();
    }
}

// Method 4: Media type versioning
@RestController
@RequestMapping("/api/users")
class UserController {
    @GetMapping(produces = "application/vnd.company.v1+json")
    public List<UserV1> getUsersV1() {
        return userService.getAllUsersV1();
    }

    @GetMapping(produces = "application/vnd.company.v2+json")
    public List<UserV2> getUsersV2() {
        return userService.getAllUsersV2();
    }
}
```

**Key points:**
- URI versioning: /api/v1/users (most common)
- Header versioning: X-API-VERSION header
- Media type: Accept header with custom type
- Maintain backward compatibility

---

### 13. How do you handle pagination and sorting in a REST API?

I'd use **Pageable** parameter in controller methods and **Page** return type. Spring Data automatically handles pagination and sorting.

```java
// Repository
@Repository
interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByActive(boolean active, Pageable pageable);
}

// Service
@Service
class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }
}

// Controller
@RestController
@RequestMapping("/api/users")
class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "ASC") String direction
    ) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<User> users = userRepository.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    // Alternative: Using Pageable directly
    @GetMapping("/v2")
    public ResponseEntity<Page<User>> getUsersV2(Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(pageable));
    }
}
```

**Request examples:**
```
GET /api/users?page=0&size=10&sortBy=name&direction=ASC
GET /api/users/v2?page=0&size=20&sort=name,asc&sort=email,desc
```

**Response:**
```json
{
  "content": [...],
  "pageable": {...},
  "totalPages": 5,
  "totalElements": 50,
  "size": 10,
  "number": 0
}
```

**Key points:**
- Pageable parameter for automatic pagination
- Page return type includes metadata
- Default values for page and size
- Multiple sort fields supported

---

### 14. How would you secure a REST API using JWT or OAuth2?

I'd use **Spring Security** with **JWT tokens** for stateless authentication. Generate token on login, validate on each request using a filter.

```java
// Dependencies in pom.xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>

// JWT Utility
@Component
class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
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
class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                UsernamePasswordAuthenticationToken auth = 
                    new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        
        chain.doFilter(request, response);
    }
}

// Security Configuration
@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}

// Auth Controller
@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // Validate credentials
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
```

**Key points:**
- JWT for stateless authentication
- Filter validates token on each request
- Store token in Authorization header
- CSRF disabled for stateless APIs

---

### 15. How do you return custom response objects with metadata and error messages?

I'd create a **generic response wrapper** that includes data, status, message, and metadata. This provides consistent API responses.

```java
// Generic response wrapper
class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Map<String, Object> metadata;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.metadata = new HashMap<>();
    }

    public void addMetadata(String key, Object value) {
        metadata.put(key, value);
    }
    // Getters and setters
}

// Paginated response
class PagedResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public PagedResponse(Page<T> page) {
        this.content = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
    // Getters
}

// Controller
@RestController
@RequestMapping("/api/users")
class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        ApiResponse<User> response = new ApiResponse<>(true, "User found", user);
        response.addMetadata("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<User>>> getUsers(Pageable pageable) {
        Page<User> page = userService.findAll(pageable);
        PagedResponse<User> pagedData = new PagedResponse<>(page);
        ApiResponse<PagedResponse<User>> response = 
            new ApiResponse<>(true, "Users retrieved", pagedData);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody User user) {
        User created = userService.save(user);
        ApiResponse<User> response = new ApiResponse<>(true, "User created", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

// Error response
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex) {
        ApiResponse<Void> response = new ApiResponse<>(false, ex.getMessage(), null);
        response.addMetadata("errorCode", "RESOURCE_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
```

**Response examples:**
```json
// Success
{
  "success": true,
  "message": "User found",
  "data": { "id": 1, "name": "John" },
  "metadata": { "timestamp": 1234567890 }
}

// Error
{
  "success": false,
  "message": "User not found",
  "data": null,
  "metadata": { "errorCode": "RESOURCE_NOT_FOUND" }
}
```

**Key points:**
- Consistent response structure
- Include success flag and message
- Metadata for additional info
- Separate wrappers for paginated data

---

## 4. Spring Data & Database Interaction

### 16. How would you implement CRUD operations using Spring Data JPA?

I'd extend **JpaRepository** which provides built-in CRUD methods, and add custom query methods using method naming conventions or **@Query**.

```java
// Entity
@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true)
    private String email;
    
    private boolean active;
    // Getters and setters
}

// Repository
@Repository
interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods
    List<User> findByActive(boolean active);
    Optional<User> findByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> searchByName(@Param("name") String name);
}

// Service
@Service
class UserService {
    @Autowired
    private UserRepository userRepository;

    // Create
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Read
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    // Delete
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

// Controller
@RestController
@RequestMapping("/api/users")
class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
```

**Key points:**
- JpaRepository provides save, findById, findAll, deleteById
- Custom queries with method naming or @Query
- @Valid for request validation
- Return appropriate HTTP status codes

---

### 17. How do you optimize queries to avoid the N+1 select problem?

I'd use **@EntityGraph** or **JOIN FETCH** in JPQL to eagerly load related entities in a single query instead of multiple queries.

```java
// Entities with relationship
@Entity
class Author {
    @Id @GeneratedValue
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;
}

@Entity
class Book {
    @Id @GeneratedValue
    private Long id;
    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
}

// Problem: N+1 queries
List<Author> authors = authorRepository.findAll();
for (Author author : authors) {
    author.getBooks().size(); // Triggers separate query for each author!
}

// Solution 1: @EntityGraph
@Repository
interface AuthorRepository extends JpaRepository<Author, Long> {
    @EntityGraph(attributePaths = {"books"})
    List<Author> findAll();
    
    @EntityGraph(attributePaths = {"books"})
    Optional<Author> findById(Long id);
}

// Solution 2: JOIN FETCH in JPQL
@Repository
interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books")
    List<Author> findAllWithBooks();
    
    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.id = :id")
    Optional<Author> findByIdWithBooks(@Param("id") Long id);
}

// Solution 3: @NamedEntityGraph
@Entity
@NamedEntityGraph(
    name = "Author.books",
    attributeNodes = @NamedAttributeNode("books")
)
class Author {
    // ...
}

@Repository
interface AuthorRepository extends JpaRepository<Author, Long> {
    @EntityGraph("Author.books")
    List<Author> findAll();
}

// Solution 4: Batch fetching
@Entity
class Author {
    @OneToMany(mappedBy = "author")
    @BatchSize(size = 10)
    private List<Book> books;
}
```

**Key points:**
- @EntityGraph loads associations in single query
- JOIN FETCH in JPQL for custom queries
- @BatchSize reduces queries but doesn't eliminate N+1
- Use only when you need the associations

---

### 18. How would you implement soft deletes in a database using Spring Boot?

I'd add a **deleted flag** and **deletedAt timestamp** to entities, override delete methods, and filter deleted records in queries using **@Where** or **@SQLDelete**.

```java
// Entity with soft delete
@Entity
@SQLDelete(sql = "UPDATE users SET deleted = true, deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted = false")
class User {
    @Id @GeneratedValue
    private Long id;
    
    private String name;
    
    @Column(name = "deleted")
    private boolean deleted = false;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    public void softDelete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}

// Repository
@Repository
interface UserRepository extends JpaRepository<User, Long> {
    // Automatically filters deleted records due to @Where
    List<User> findAll();
    
    // Find including deleted
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByIdIncludingDeleted(@Param("id") Long id);
    
    // Find only deleted
    @Query("SELECT u FROM User u WHERE u.deleted = true")
    List<User> findDeleted();
}

// Service
@Service
class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void softDelete(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.softDelete();
        userRepository.save(user);
    }

    @Transactional
    public void restore(Long id) {
        User user = userRepository.findByIdIncludingDeleted(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setDeleted(false);
        user.setDeletedAt(null);
        userRepository.save(user);
    }
}

// Alternative: Base entity for reusability
@MappedSuperclass
abstract class SoftDeletableEntity {
    @Column(name = "deleted")
    private boolean deleted = false;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    public void softDelete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }
    
    public boolean isDeleted() {
        return deleted;
    }
}

@Entity
class User extends SoftDeletableEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
}
```

**Key points:**
- @SQLDelete intercepts delete operations
- @Where filters deleted records automatically
- Keep deleted records for audit/recovery
- Provide restore functionality

---

### 19. How do you handle transactions across multiple services?

I'd use **@Transactional** for single service transactions, **Saga pattern** for distributed transactions, or **event-driven approach** with eventual consistency.

```java
// Single service transaction
@Service
class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private InventoryService inventoryService;

    @Transactional
    public Order createOrder(OrderRequest request) {
        // Both operations in same transaction
        Order order = orderRepository.save(new Order(request));
        inventoryService.reduceStock(request.getProductId(), request.getQuantity());
        return order;
    }
}

// Distributed transaction - Saga pattern (Choreography)
@Service
class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        eventPublisher.publishEvent(new OrderCreatedEvent(order));
        return order;
    }

    @EventListener
    @Transactional
    public void handleInventoryReserved(InventoryReservedEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow();
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
    }

    @EventListener
    @Transactional
    public void handleInventoryFailed(InventoryFailedEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow();
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}

// Outbox pattern for reliable messaging
@Entity
class OutboxEvent {
    @Id @GeneratedValue
    private Long id;
    private String aggregateType;
    private String aggregateId;
    private String eventType;
    private String payload;
    private boolean processed;
}

@Service
class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OutboxRepository outboxRepository;

    @Transactional
    public Order createOrder(OrderRequest request) {
        // Save order and event in same transaction
        Order order = orderRepository.save(new Order(request));
        
        OutboxEvent event = new OutboxEvent();
        event.setAggregateType("Order");
        event.setAggregateId(order.getId().toString());
        event.setEventType("OrderCreated");
        event.setPayload(toJson(order));
        outboxRepository.save(event);
        
        return order;
    }
}

// Scheduled job to process outbox
@Component
class OutboxProcessor {
    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void processOutbox() {
        List<OutboxEvent> events = outboxRepository.findByProcessedFalse();
        for (OutboxEvent event : events) {
            // Publish to message broker
            messagePublisher.publish(event);
            event.setProcessed(true);
            outboxRepository.save(event);
        }
    }
}
```

**Key points:**
- @Transactional for single database transactions
- Saga pattern for distributed transactions
- Outbox pattern for reliable messaging
- Eventual consistency in microservices

---

### 20. How would you implement batch inserts or updates efficiently in Spring Boot?

I'd use **saveAll()** for small batches, configure **batch size** in Hibernate properties, or use **JdbcTemplate** for large batches.

```java
// Method 1: JPA saveAll (simple)
@Service
class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUsers(List<User> users) {
        userRepository.saveAll(users);
    }
}

// Method 2: Configure batch size
// application.yml
spring:
  jpa:
    properties:
      hibernate:
        jdbc:
          batch_size: 50
        order_inserts: true
        order_updates: true

// Method 3: Manual batching with EntityManager
@Service
class BatchService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void batchInsert(List<User> users) {
        int batchSize = 50;
        for (int i = 0; i < users.size(); i++) {
            entityManager.persist(users.get(i));
            
            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}

// Method 4: JdbcTemplate for large batches (fastest)
@Service
class JdbcBatchService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void batchInsert(List<User> users) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        
        jdbcTemplate.batchUpdate(sql, users, users.size(),
            (PreparedStatement ps, User user) -> {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
            });
    }

    public void batchUpdate(List<User> users) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        
        jdbcTemplate.batchUpdate(sql, users, users.size(),
            (PreparedStatement ps, User user) -> {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setLong(3, user.getId());
            });
    }
}
```

**Performance comparison:**
| Method | Speed | Use Case |
|--------|-------|----------|
| saveAll() | Slow | < 100 records |
| Batch with EntityManager | Medium | 100-1000 records |
| JdbcTemplate | Fast | > 1000 records |

**Key points:**
- Configure hibernate.jdbc.batch_size
- Flush and clear EntityManager periodically
- JdbcTemplate for best performance
- Use @Transactional for consistency


---

## 5. Security

### 21. How would you implement role-based access control (RBAC) in a Spring Boot application?

I'd use **Spring Security** with **UserDetails** and **GrantedAuthority** to define roles, then use **@PreAuthorize** or **hasRole()** to restrict access.

```java
// User entity with roles
@Entity
class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}

@Entity
class Role {
    @Id @GeneratedValue
    private Long id;
    private String name; // ROLE_USER, ROLE_ADMIN
}

// UserDetails implementation
@Service
class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities(user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList()))
            .build();
    }
}

// Security configuration
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/public/**").permitAll()
            .requestMatchers("/api/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
        );
        return http.build();
    }
}

// Controller with method-level security
@RestController
@RequestMapping("/api")
class UserController {
    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public User getProfile() {
        return userService.getCurrentUser();
    }

    @DeleteMapping("/admin/users/{id}")
    @PreAuthorize("hasRole('ADMIN') and #id != authentication.principal.id")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
```

**Key points:**
- Store roles in database
- UserDetailsService loads user and authorities
- hasRole() for URL-based security
- @PreAuthorize for method-level security

---

### 22. How would you secure REST APIs to prevent unauthorized access?

I'd use **JWT tokens** with Spring Security, implement **authentication filter**, and validate tokens on each request.

```java
// JWT filter
@Component
class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain chain) throws ServletException, IOException {
        String token = extractToken(request);
        
        if (token != null && jwtUtil.validateToken(token)) {
            String username = jwtUtil.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            UsernamePasswordAuthenticationToken auth = 
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        
        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}

// Security config
@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}

// Auth controller
@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        String token = jwtUtil.generateToken(auth.getName());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
```

**Key points:**
- JWT for stateless authentication
- Filter validates token on each request
- CSRF disabled for stateless APIs
- Secure endpoints with authentication

---

### 23. How would you implement password encryption and storage securely?

I'd use **BCryptPasswordEncoder** to hash passwords before storing. Never store plain text passwords.

```java
// Security configuration
@Configuration
class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// User service
@Service
class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow();
        
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid old password");
        }
        
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}

// Password validation
@Component
class PasswordValidator {
    private static final String PASSWORD_PATTERN = 
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public boolean isValid(String password) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }
}
```

**Key points:**
- BCryptPasswordEncoder for hashing
- Never store plain text passwords
- Validate password strength
- Use matches() to verify passwords

---

### 24. How do you implement refresh tokens in JWT authentication?

I'd generate both **access token** (short-lived) and **refresh token** (long-lived), store refresh tokens, and provide endpoint to get new access token.

```java
// Token service
@Service
class TokenService {
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public AuthResponse generateTokens(String username) {
        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);
        
        RefreshToken token = new RefreshToken();
        token.setToken(refreshToken);
        token.setUsername(username);
        token.setExpiryDate(LocalDateTime.now().plusDays(7));
        refreshTokenRepository.save(token);
        
        return new AuthResponse(accessToken, refreshToken);
    }

    public String refreshAccessToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
            .orElseThrow(() -> new TokenException("Invalid refresh token"));
        
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            throw new TokenException("Refresh token expired");
        }
        
        return jwtUtil.generateAccessToken(token.getUsername());
    }
}

// JWT utility
@Component
class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String generateAccessToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 900000)) // 15 min
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 604800000)) // 7 days
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }
}

// Controller
@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // Authenticate user
        AuthResponse response = tokenService.generateTokens(request.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshRequest request) {
        String newAccessToken = tokenService.refreshAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(new TokenResponse(newAccessToken));
    }
}
```

**Key points:**
- Access token: short-lived (15 min)
- Refresh token: long-lived (7 days)
- Store refresh tokens in database
- Provide refresh endpoint

---

### 25. How would you prevent common security vulnerabilities like CSRF or SQL injection in Spring Boot?

I'd use **Spring Security** for CSRF protection, **parameterized queries** for SQL injection, and **input validation** for XSS prevention.

```java
// CSRF protection (enabled by default for non-stateless)
@Configuration
class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        
        return http.build();
    }
}

// SQL injection prevention - use JPA/parameterized queries
@Repository
interface UserRepository extends JpaRepository<User, Long> {
    // Safe - parameterized
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
    
    // UNSAFE - string concatenation
    // @Query("SELECT u FROM User u WHERE u.email = '" + email + "'")
}

// Input validation
@RestController
class UserController {
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }
}

class UserRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    
    @Email
    private String email;
    
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;
}

// XSS prevention
@Component
class XssFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        XssRequestWrapper wrappedRequest = new XssRequestWrapper((HttpServletRequest) request);
        chain.doFilter(wrappedRequest, response);
    }
}

class XssRequestWrapper extends HttpServletRequestWrapper {
    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return sanitize(super.getParameter(name));
    }

    private String sanitize(String value) {
        if (value == null) return null;
        return value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}

// Security headers
@Configuration
class SecurityHeadersConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(headers -> headers
            .contentSecurityPolicy("default-src 'self'")
            .xssProtection()
            .frameOptions().deny()
        );
        return http.build();
    }
}
```

**Key points:**
- CSRF: Use tokens for state-changing operations
- SQL Injection: Always use parameterized queries
- XSS: Validate and sanitize input
- Security headers: CSP, X-Frame-Options


---

## 6. Exception Handling & Logging

### 26. How would you implement centralized exception handling for a microservices system?

I'd use **@RestControllerAdvice** in each service for local handling, and **API Gateway** to handle cross-cutting concerns with consistent error format.

```java
// Common error response (shared library)
class ErrorResponse {
    private String errorCode;
    private String message;
    private long timestamp;
    private String service;
    
    public ErrorResponse(String errorCode, String message, String service) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.service = service;
    }
}

// Service-level exception handler
@RestControllerAdvice
class ServiceExceptionHandler {
    @Value("${spring.application.name}")
    private String serviceName;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("NOT_FOUND", ex.getMessage(), serviceName);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse("INTERNAL_ERROR", "An error occurred", serviceName);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

// API Gateway error handler
@Component
class GatewayErrorWebExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ErrorResponse error = new ErrorResponse(
            "GATEWAY_ERROR",
            ex.getMessage(),
            "api-gateway"
        );
        
        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return exchange.getResponse().writeWith(
            Mono.just(exchange.getResponse().bufferFactory()
                .wrap(toJson(error).getBytes()))
        );
    }
}

// Feign client error handling
@Component
class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new ResourceNotFoundException("Resource not found in downstream service");
        }
        return new FeignException.FeignServerException(
            response.status(), "Service call failed", response.request(), null, null
        );
    }
}
```

**Key points:**
- @RestControllerAdvice in each microservice
- Consistent error response format
- API Gateway handles gateway-level errors
- Feign error decoder for service-to-service calls

---

### 27. How would you log requests and responses in a REST API?

I'd use **Filter** or **Interceptor** to log HTTP requests/responses, or use **Spring AOP** for method-level logging.

```java
// Request/Response logging filter
@Component
@Order(1)
class LoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        long startTime = System.currentTimeMillis();
        
        log.info("Request: {} {} from {}", 
            req.getMethod(), req.getRequestURI(), req.getRemoteAddr());
        
        chain.doFilter(request, response);
        
        long duration = System.currentTimeMillis() - startTime;
        log.info("Response: {} {} - Status: {} - Duration: {}ms",
            req.getMethod(), req.getRequestURI(), res.getStatus(), duration);
    }
}

// Interceptor approach
@Component
class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
                            Object handler) {
        request.setAttribute("startTime", System.currentTimeMillis());
        log.info("Incoming: {} {}", request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                               Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;
        
        log.info("Completed: {} {} - Status: {} - Duration: {}ms",
            request.getMethod(), request.getRequestURI(), response.getStatus(), duration);
    }
}

@Configuration
class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }
}

// AOP for method-level logging
@Aspect
@Component
class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method: {} - Args: {}", 
            joinPoint.getSignature().getName(), 
            Arrays.toString(joinPoint.getArgs()));
        
        Object result = joinPoint.proceed();
        
        log.info("Method: {} - Result: {}", 
            joinPoint.getSignature().getName(), result);
        
        return result;
    }
}
```

**Key points:**
- Filter for HTTP-level logging
- Interceptor for handler-level logging
- AOP for method-level logging
- Log request/response times for monitoring

---

### 28. How do you implement custom exceptions with meaningful error codes?

I'd create **custom exception classes** with error codes, use **enum** for error codes, and map them in exception handler.

```java
// Error code enum
enum ErrorCode {
    USER_NOT_FOUND("USR001", "User not found"),
    INVALID_CREDENTIALS("AUTH001", "Invalid credentials"),
    INSUFFICIENT_FUNDS("ACC001", "Insufficient funds"),
    DUPLICATE_EMAIL("USR002", "Email already exists");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
}

// Base exception
abstract class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

// Specific exceptions
class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}

class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL);
    }
}

// Error response
class ApiErrorResponse {
    private String errorCode;
    private String message;
    private long timestamp;
    private Map<String, String> details;

    public ApiErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.timestamp = System.currentTimeMillis();
        this.details = new HashMap<>();
    }
}

// Exception handler
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(BusinessException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}

// Usage in service
@Service
class UserService {
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException());
    }

    public User createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException();
        }
        return userRepository.save(new User(request));
    }
}
```

**Key points:**
- Enum for centralized error codes
- Custom exceptions extend base exception
- Error codes help with internationalization
- Consistent error response structure

---

### 29. How do you integrate Spring Boot logging with ELK stack?

I'd use **Logstash encoder** to format logs as JSON and configure **Filebeat** or **Logstash** to ship logs to Elasticsearch.

```xml
<!-- pom.xml -->
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
    <version>7.3</version>
</dependency>
```

```xml
<!-- logback-spring.xml -->
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/application.log</file>
        <encoder class="net.logstash.logback.encoder.LogstashEncoder">
            <customFields>{"app":"library-service","env":"${ENVIRONMENT}"}</customFields>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/application-%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
    </appender>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="net.logstash.logback.encoder.LogstashEncoder"/>
    </appender>

    <root level="INFO">
        <appender-ref ref="FILE"/>
        <appender-ref ref="CONSOLE"/>
    </root>
</configuration>
```

```yaml
# filebeat.yml
filebeat.inputs:
  - type: log
    enabled: true
    paths:
      - /app/logs/*.log
    json.keys_under_root: true
    json.add_error_key: true

output.elasticsearch:
  hosts: ["elasticsearch:9200"]
  index: "library-service-%{+yyyy.MM.dd}"
```

```java
// Structured logging in code
@Service
class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public User createUser(UserRequest request) {
        log.info("Creating user: username={}, email={}", 
            request.getUsername(), request.getEmail());
        
        try {
            User user = userRepository.save(new User(request));
            log.info("User created successfully: userId={}", user.getId());
            return user;
        } catch (Exception e) {
            log.error("Failed to create user: username={}, error={}", 
                request.getUsername(), e.getMessage(), e);
            throw e;
        }
    }
}

// MDC for request tracking
@Component
class RequestIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);
        
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
```

**Key points:**
- Logstash encoder for JSON format
- Filebeat ships logs to Elasticsearch
- MDC for request correlation
- Structured logging with key-value pairs

---

### 30. How would you handle exceptions in a scheduled task or background job?

I'd wrap task logic in **try-catch**, log errors, and optionally implement **retry mechanism** or **dead letter queue** for failed tasks.

```java
// Scheduled task with exception handling
@Component
class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(fixedRate = 60000)
    public void processOrders() {
        try {
            log.info("Starting order processing job");
            orderService.processOrders();
            log.info("Order processing completed successfully");
        } catch (Exception e) {
            log.error("Order processing failed", e);
            // Send alert or notification
            alertService.sendAlert("Order processing failed: " + e.getMessage());
        }
    }
}

// Async task with exception handling
@Service
class AsyncTaskService {
    private static final Logger log = LoggerFactory.getLogger(AsyncTaskService.class);

    @Async
    public CompletableFuture<Void> processLargeFile(String filePath) {
        try {
            log.info("Processing file: {}", filePath);
            fileProcessor.process(filePath);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            log.error("File processing failed: {}", filePath, e);
            return CompletableFuture.failedFuture(e);
        }
    }
}

// Retry mechanism
@Component
class RetryableTask {
    private static final Logger log = LoggerFactory.getLogger(RetryableTask.class);
    private static final int MAX_RETRIES = 3;

    @Scheduled(fixedRate = 300000)
    public void syncData() {
        int attempt = 0;
        while (attempt < MAX_RETRIES) {
            try {
                dataService.sync();
                log.info("Data sync successful");
                return;
            } catch (Exception e) {
                attempt++;
                log.warn("Data sync failed, attempt {}/{}: {}", 
                    attempt, MAX_RETRIES, e.getMessage());
                
                if (attempt >= MAX_RETRIES) {
                    log.error("Data sync failed after {} attempts", MAX_RETRIES, e);
                    alertService.sendAlert("Data sync failed permanently");
                } else {
                    sleep(5000 * attempt); // Exponential backoff
                }
            }
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Global async exception handler
@Configuration
@EnableAsync
class AsyncConfig implements AsyncConfigurer {
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.error("Async method {} failed with params {}", 
                method.getName(), Arrays.toString(params), ex);
        };
    }
}
```

**Key points:**
- Wrap scheduled tasks in try-catch
- Log all exceptions for monitoring
- Implement retry with exponential backoff
- Send alerts for critical failures
- Use AsyncUncaughtExceptionHandler for async tasks


---

## 7. Caching & Performance

### 31. How would you implement caching using Spring Cache or Redis?

I'd use **@EnableCaching** with **@Cacheable** annotations for simple caching, or **RedisTemplate** for more control over cache operations.

```java
// Enable caching
@Configuration
@EnableCaching
class CacheConfig {
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10))
            .serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer()));
        
        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(config)
            .build();
    }
}

// Service with caching
@Service
class UserService {
    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Cacheable(value = "users", key = "'all'")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @CachePut(value = "users", key = "#user.id")
    public User update(User user) {
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", key = "#id")
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void clearCache() {
        // Cache cleared
    }
}

// Manual Redis operations
@Service
class ProductService {
    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    public Product getProduct(String id) {
        String key = "product:" + id;
        Product product = redisTemplate.opsForValue().get(key);
        
        if (product == null) {
            product = productRepository.findById(id).orElseThrow();
            redisTemplate.opsForValue().set(key, product, 10, TimeUnit.MINUTES);
        }
        
        return product;
    }
}
```

**application.yml:**
```yaml
spring:
  cache:
    type: redis
  redis:
    host: localhost
    port: 6379
```

**Key points:**
- @Cacheable caches method results
- @CachePut updates cache
- @CacheEvict removes from cache
- RedisTemplate for manual control

---

### 32. How do you handle cache eviction and expiration policies?

I'd configure **TTL** (time-to-live) for automatic expiration, use **@CacheEvict** for manual eviction, and implement **cache warming** strategies.

```java
// Cache configuration with TTL
@Configuration
@EnableCaching
class CacheConfig {
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        
        // Different TTL for different caches
        cacheConfigs.put("users", RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(30)));
        
        cacheConfigs.put("products", RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofHours(1)));
        
        cacheConfigs.put("sessions", RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(15)));
        
        return RedisCacheManager.builder(factory)
            .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)))
            .withInitialCacheConfigurations(cacheConfigs)
            .build();
    }
}

// Conditional eviction
@Service
class UserService {
    @CacheEvict(value = "users", key = "#id", condition = "#result != null")
    public User updateUser(Long id, User user) {
        return userRepository.save(user);
    }

    // Evict multiple caches
    @Caching(evict = {
        @CacheEvict(value = "users", key = "#id"),
        @CacheEvict(value = "userStats", key = "#id")
    })
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

// Scheduled cache eviction
@Component
class CacheEvictionScheduler {
    @Autowired
    private CacheManager cacheManager;

    @Scheduled(cron = "0 0 2 * * ?") // 2 AM daily
    public void evictAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        });
    }
}

// Cache warming on startup
@Component
class CacheWarmer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Warm up cache with frequently accessed data
        userService.findAll();
    }
}
```

**Key points:**
- Configure TTL per cache
- Use @CacheEvict for manual eviction
- Schedule periodic cache clearing
- Warm cache on startup for hot data

---

### 33. How do you optimize database access using lazy/eager loading?

I'd use **FetchType.LAZY** by default and **@EntityGraph** or **JOIN FETCH** when I need related entities to avoid N+1 queries.

```java
// Entities with lazy loading (default)
@Entity
class Author {
    @Id @GeneratedValue
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;
}

@Entity
class Book {
    @Id @GeneratedValue
    private Long id;
    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
}

// Repository with selective eager loading
@Repository
interface AuthorRepository extends JpaRepository<Author, Long> {
    // Lazy - only loads author
    Optional<Author> findById(Long id);
    
    // Eager - loads author with books
    @EntityGraph(attributePaths = {"books"})
    Optional<Author> findWithBooksById(Long id);
    
    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.id = :id")
    Optional<Author> findByIdWithBooks(@Param("id") Long id);
}

// Service layer decides loading strategy
@Service
class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    // Use lazy when books not needed
    public Author getAuthorBasicInfo(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    // Use eager when books needed
    public Author getAuthorWithBooks(Long id) {
        return authorRepository.findWithBooksById(id).orElseThrow();
    }
}

// Batch fetching to reduce queries
@Entity
class Author {
    @OneToMany(mappedBy = "author")
    @BatchSize(size = 10)
    private List<Book> books;
}
```

**Key points:**
- Default to LAZY loading
- Use @EntityGraph for selective eager loading
- JOIN FETCH in JPQL when needed
- @BatchSize reduces N+1 queries

---

### 34. How do you implement rate limiting for REST APIs in Spring Boot?

I'd use **Bucket4j** library or implement custom rate limiting with **Redis** to track request counts per user/IP.

```java
// Dependencies
<dependency>
    <groupId>com.github.vladimir-bukhtoyarov</groupId>
    <artifactId>bucket4j-core</artifactId>
    <version>7.6.0</version>
</dependency>

// Rate limiter service
@Service
class RateLimiterService {
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String key) {
        return cache.computeIfAbsent(key, k -> createBucket());
    }

    private Bucket createBucket() {
        Bandwidth limit = Bandwidth.classic(100, Refill.intervally(100, Duration.ofMinutes(1)));
        return Bucket.builder()
            .addLimit(limit)
            .build();
    }
}

// Rate limiting filter
@Component
class RateLimitFilter implements Filter {
    @Autowired
    private RateLimiterService rateLimiterService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String key = getClientKey(httpRequest);
        
        Bucket bucket = rateLimiterService.resolveBucket(key);
        
        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(429);
            httpResponse.getWriter().write("Too many requests");
        }
    }

    private String getClientKey(HttpServletRequest request) {
        String userId = request.getHeader("User-Id");
        return userId != null ? userId : request.getRemoteAddr();
    }
}

// Redis-based rate limiter
@Service
class RedisRateLimiter {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean allowRequest(String key, int maxRequests, int windowSeconds) {
        String redisKey = "rate_limit:" + key;
        Long count = redisTemplate.opsForValue().increment(redisKey);
        
        if (count == 1) {
            redisTemplate.expire(redisKey, windowSeconds, TimeUnit.SECONDS);
        }
        
        return count <= maxRequests;
    }
}

// Annotation-based rate limiting
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RateLimit {
    int value() default 100;
}

@Aspect
@Component
class RateLimitAspect {
    @Autowired
    private RateLimiterService rateLimiterService;

    @Around("@annotation(rateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = getCurrentUserId();
        Bucket bucket = rateLimiterService.resolveBucket(key);
        
        if (bucket.tryConsume(1)) {
            return joinPoint.proceed();
        }
        
        throw new RateLimitExceededException("Rate limit exceeded");
    }
}

// Usage
@RestController
class ApiController {
    @GetMapping("/api/data")
    @RateLimit(100)
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("Data");
    }
}
```

**Key points:**
- Bucket4j for token bucket algorithm
- Redis for distributed rate limiting
- Track by user ID or IP address
- Return 429 status when limit exceeded

---

### 35. How would you profile and monitor a Spring Boot application for performance bottlenecks?

I'd use **Spring Boot Actuator** for metrics, **Micrometer** for monitoring, and **profiling tools** like JProfiler or VisualVM.

```java
// Enable actuator
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>

// application.yml
management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus,info
  metrics:
    export:
      prometheus:
        enabled: true

// Custom metrics
@Service
class UserService {
    private final MeterRegistry meterRegistry;
    private final Counter userCreationCounter;
    private final Timer userFetchTimer;

    public UserService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.userCreationCounter = Counter.builder("users.created")
            .description("Number of users created")
            .register(meterRegistry);
        this.userFetchTimer = Timer.builder("users.fetch.time")
            .description("Time to fetch user")
            .register(meterRegistry);
    }

    public User createUser(User user) {
        User created = userRepository.save(user);
        userCreationCounter.increment();
        return created;
    }

    public User findById(Long id) {
        return userFetchTimer.record(() -> 
            userRepository.findById(id).orElseThrow()
        );
    }
}

// Performance monitoring aspect
@Aspect
@Component
class PerformanceMonitoringAspect {
    private static final Logger log = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        Object result = joinPoint.proceed();
        
        long duration = System.currentTimeMillis() - start;
        if (duration > 1000) {
            log.warn("Slow method: {} took {}ms", 
                joinPoint.getSignature().getName(), duration);
        }
        
        return result;
    }
}

// Database query monitoring
@Configuration
class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/db");
        config.setMetricRegistry(new MetricRegistry());
        return new HikariDataSource(config);
    }
}

// Health indicators
@Component
class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        // Check external dependencies
        boolean databaseUp = checkDatabase();
        boolean redisUp = checkRedis();
        
        if (databaseUp && redisUp) {
            return Health.up().build();
        }
        
        return Health.down()
            .withDetail("database", databaseUp)
            .withDetail("redis", redisUp)
            .build();
    }
}
```

**Monitoring tools:**
- **Actuator**: /actuator/metrics, /actuator/health
- **Prometheus**: Scrape metrics endpoint
- **Grafana**: Visualize metrics
- **APM tools**: New Relic, Datadog, Dynatrace

**Key points:**
- Use Actuator for built-in metrics
- Custom metrics with Micrometer
- Monitor slow queries and methods
- Set up alerts for performance degradation


---

## 8. Messaging & Asynchronous Processing

### 36. How would you implement async processing using @Async in Spring Boot?

I'd enable async with **@EnableAsync**, annotate methods with **@Async**, and configure a **thread pool executor** for better control.

```java
// Enable async
@Configuration
@EnableAsync
class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-");
        executor.initialize();
        return executor;
    }
}

// Async service
@Service
class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Async("taskExecutor")
    public CompletableFuture<Void> sendEmail(String to, String subject) {
        log.info("Sending email to: {}", to);
        // Simulate email sending
        sleep(2000);
        log.info("Email sent to: {}", to);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public void sendBulkEmails(List<String> recipients) {
        recipients.forEach(recipient -> {
            log.info("Sending to: {}", recipient);
            sleep(1000);
        });
    }
}

// Controller using async
@RestController
class OrderController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        // Process order synchronously
        orderService.save(order);
        
        // Send email asynchronously
        emailService.sendEmail(order.getCustomerEmail(), "Order Confirmation");
        
        return ResponseEntity.ok("Order created");
    }
}

// Async with return value
@Service
class DataService {
    @Async
    public CompletableFuture<List<User>> fetchUsers() {
        List<User> users = userRepository.findAll();
        return CompletableFuture.completedFuture(users);
    }

    @Async
    public CompletableFuture<List<Product>> fetchProducts() {
        List<Product> products = productRepository.findAll();
        return CompletableFuture.completedFuture(products);
    }
}

// Combine multiple async calls
@Service
class AggregationService {
    @Autowired
    private DataService dataService;

    public DashboardData getDashboardData() {
        CompletableFuture<List<User>> usersFuture = dataService.fetchUsers();
        CompletableFuture<List<Product>> productsFuture = dataService.fetchProducts();
        
        CompletableFuture.allOf(usersFuture, productsFuture).join();
        
        return new DashboardData(
            usersFuture.join(),
            productsFuture.join()
        );
    }
}
```

**Key points:**
- @EnableAsync enables async processing
- @Async makes method run in separate thread
- Configure thread pool for better control
- Use CompletableFuture for return values

---

### 37. How would you design a notification system using RabbitMQ or Kafka?

I'd use **RabbitMQ** for reliable message delivery with queues, or **Kafka** for high-throughput event streaming.

```java
// RabbitMQ Configuration
@Configuration
class RabbitMQConfig {
    @Bean
    public Queue notificationQueue() {
        return new Queue("notifications", true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("notification-exchange");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("notification.*");
    }
}

// Producer
@Service
class NotificationProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNotification(Notification notification) {
        rabbitTemplate.convertAndSend(
            "notification-exchange",
            "notification." + notification.getType(),
            notification
        );
    }
}

// Consumer
@Component
class NotificationConsumer {
    @RabbitListener(queues = "notifications")
    public void handleNotification(Notification notification) {
        switch (notification.getType()) {
            case EMAIL:
                emailService.send(notification);
                break;
            case SMS:
                smsService.send(notification);
                break;
            case PUSH:
                pushService.send(notification);
                break;
        }
    }
}

// Kafka Configuration
@Configuration
@EnableKafka
class KafkaConfig {
    @Bean
    public ProducerFactory<String, Notification> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Notification> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

// Kafka Producer
@Service
class KafkaNotificationProducer {
    @Autowired
    private KafkaTemplate<String, Notification> kafkaTemplate;

    public void sendNotification(Notification notification) {
        kafkaTemplate.send("notifications", notification.getUserId(), notification);
    }
}

// Kafka Consumer
@Component
class KafkaNotificationConsumer {
    @KafkaListener(topics = "notifications", groupId = "notification-group")
    public void consume(Notification notification) {
        processNotification(notification);
    }
}
```

**Key points:**
- RabbitMQ for reliable delivery with queues
- Kafka for high-throughput streaming
- Use exchanges/topics for routing
- Consumers process messages asynchronously

---

### 38. How do you handle retry mechanisms for failed message processing?

I'd use **@Retryable** annotation or configure **dead letter queue** (DLQ) for failed messages.

```java
// Enable retry
@Configuration
@EnableRetry
class RetryConfig {}

// Service with retry
@Service
class MessageProcessor {
    @Retryable(
        value = {TransientException.class},
        maxAttempts = 3,
        backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public void processMessage(Message message) {
        // Process message
        if (shouldFail()) {
            throw new TransientException("Temporary failure");
        }
    }

    @Recover
    public void recover(TransientException e, Message message) {
        // Called after all retries exhausted
        log.error("Failed to process message after retries: {}", message);
        deadLetterService.send(message);
    }
}

// RabbitMQ with DLQ
@Configuration
class RabbitMQRetryConfig {
    @Bean
    public Queue mainQueue() {
        return QueueBuilder.durable("main-queue")
            .withArgument("x-dead-letter-exchange", "dlx-exchange")
            .withArgument("x-dead-letter-routing-key", "dlq")
            .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue("dead-letter-queue", true);
    }

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange("dlx-exchange");
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(deadLetterQueue())
            .to(dlxExchange())
            .with("dlq");
    }
}

// Consumer with error handling
@Component
class ResilientConsumer {
    private static final int MAX_RETRIES = 3;

    @RabbitListener(queues = "main-queue")
    public void handleMessage(Message message, 
                             @Header(name = "x-retry-count", required = false) Integer retryCount) {
        try {
            processMessage(message);
        } catch (Exception e) {
            int currentRetry = retryCount != null ? retryCount : 0;
            
            if (currentRetry < MAX_RETRIES) {
                // Retry with delay
                rabbitTemplate.convertAndSend("main-queue", message, msg -> {
                    msg.getMessageProperties().setHeader("x-retry-count", currentRetry + 1);
                    msg.getMessageProperties().setExpiration(String.valueOf(2000 * (currentRetry + 1)));
                    return msg;
                });
            } else {
                // Send to DLQ
                log.error("Max retries exceeded, sending to DLQ");
            }
        }
    }
}

// Kafka retry with error handler
@Configuration
class KafkaRetryConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Message> factory = 
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setCommonErrorHandler(new DefaultErrorHandler(
            new FixedBackOff(2000L, 3L)
        ));
        return factory;
    }
}
```

**Key points:**
- @Retryable for automatic retries
- Configure backoff strategy
- Dead letter queue for failed messages
- Log failures for monitoring

---

### 39. How would you implement event-driven architecture using Spring Boot?

I'd use **Spring Events** for in-process events or **message brokers** (Kafka/RabbitMQ) for distributed events.

```java
// Event class
class OrderCreatedEvent {
    private final Order order;

    public OrderCreatedEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() { return order; }
}

// Publisher
@Service
class OrderService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        eventPublisher.publishEvent(new OrderCreatedEvent(order));
        return order;
    }
}

// Listeners
@Component
class InventoryEventListener {
    @EventListener
    @Async
    public void handleOrderCreated(OrderCreatedEvent event) {
        inventoryService.reserveStock(event.getOrder());
    }
}

@Component
class NotificationEventListener {
    @EventListener
    @Async
    public void handleOrderCreated(OrderCreatedEvent event) {
        emailService.sendOrderConfirmation(event.getOrder());
    }
}

// Transactional events
@Component
class PaymentEventListener {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCreated(OrderCreatedEvent event) {
        paymentService.processPayment(event.getOrder());
    }
}

// Distributed events with Kafka
@Service
class DistributedEventPublisher {
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void publishOrderCreated(Order order) {
        OrderEvent event = new OrderEvent("ORDER_CREATED", order);
        kafkaTemplate.send("order-events", event);
    }
}

@Component
class DistributedEventConsumer {
    @KafkaListener(topics = "order-events", groupId = "inventory-service")
    public void handleOrderEvent(OrderEvent event) {
        if ("ORDER_CREATED".equals(event.getType())) {
            inventoryService.reserveStock(event.getOrder());
        }
    }
}
```

**Key points:**
- Spring Events for in-process communication
- @EventListener for handling events
- @Async for non-blocking processing
- Kafka/RabbitMQ for distributed events

---

### 40. How would you design a system to process large files asynchronously?

I'd use **@Async** for file processing, **streaming** to handle large files, and **progress tracking** for monitoring.

```java
// File processing service
@Service
class FileProcessingService {
    @Autowired
    private FileRepository fileRepository;

    @Async
    public CompletableFuture<Void> processFile(Long fileId) {
        FileEntity file = fileRepository.findById(fileId).orElseThrow();
        
        try {
            file.setStatus(FileStatus.PROCESSING);
            fileRepository.save(file);
            
            processLargeFile(file.getPath(), fileId);
            
            file.setStatus(FileStatus.COMPLETED);
            fileRepository.save(file);
        } catch (Exception e) {
            file.setStatus(FileStatus.FAILED);
            file.setErrorMessage(e.getMessage());
            fileRepository.save(file);
        }
        
        return CompletableFuture.completedFuture(null);
    }

    private void processLargeFile(String filePath, Long fileId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            int batchSize = 1000;
            List<Record> batch = new ArrayList<>();
            
            while ((line = reader.readLine()) != null) {
                batch.add(parseLine(line));
                lineNumber++;
                
                if (batch.size() >= batchSize) {
                    processBatch(batch);
                    batch.clear();
                    updateProgress(fileId, lineNumber);
                }
            }
            
            if (!batch.isEmpty()) {
                processBatch(batch);
            }
        }
    }

    private void updateProgress(Long fileId, int processedLines) {
        FileEntity file = fileRepository.findById(fileId).orElseThrow();
        file.setProcessedLines(processedLines);
        fileRepository.save(file);
    }
}

// Controller
@RestController
@RequestMapping("/api/files")
class FileController {
    @Autowired
    private FileProcessingService fileProcessingService;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file) 
            throws IOException {
        // Save file
        String filePath = saveFile(file);
        FileEntity fileEntity = fileRepository.save(new FileEntity(filePath));
        
        // Process asynchronously
        fileProcessingService.processFile(fileEntity.getId());
        
        return ResponseEntity.accepted()
            .body(new FileResponse(fileEntity.getId(), "Processing started"));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<FileStatus> getStatus(@PathVariable Long id) {
        FileEntity file = fileRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new FileStatus(
            file.getStatus(),
            file.getProcessedLines(),
            file.getTotalLines()
        ));
    }
}

// Streaming large file download
@GetMapping("/download/{id}")
public void downloadFile(@PathVariable Long id, HttpServletResponse response) 
        throws IOException {
    FileEntity file = fileRepository.findById(id).orElseThrow();
    
    response.setContentType("application/octet-stream");
    response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
    
    try (InputStream inputStream = new FileInputStream(file.getPath());
         OutputStream outputStream = response.getOutputStream()) {
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }
}
```

**Key points:**
- Process files asynchronously with @Async
- Stream large files to avoid memory issues
- Batch processing for efficiency
- Track progress for user feedback
- Return 202 Accepted for async operations


---

## 9. Testing & Quality Assurance

### 41. How do you write unit tests for services in Spring Boot?

I'd use **JUnit 5** with **Mockito** to mock dependencies and test service logic in isolation without loading Spring context.

```java
// Service to test
@Service
class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;

    public User createUser(UserRequest request) {
        User user = new User(request);
        User saved = userRepository.save(user);
        emailService.sendWelcomeEmail(saved.getEmail());
        return saved;
    }

    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}

// Unit test
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private UserService userService;

    @Test
    void createUser_ShouldSaveAndSendEmail() {
        // Arrange
        UserRequest request = new UserRequest("John", "john@example.com");
        User user = new User(request);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User result = userService.createUser(request);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(userRepository).save(any(User.class));
        verify(emailService).sendWelcomeEmail("john@example.com");
    }

    @Test
    void findById_WhenUserNotFound_ShouldThrowException() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.findById(1L));
    }
}
```

**Key points:**
- @ExtendWith(MockitoExtension.class) for Mockito
- @Mock for dependencies
- @InjectMocks for service under test
- verify() to check method calls

---

### 42. How do you write integration tests for REST APIs?

I'd use **@SpringBootTest** with **MockMvc** or **TestRestTemplate** to test the full API stack including controllers, services, and database.

```java
// Integration test with MockMvc
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void createUser_ShouldReturn201() throws Exception {
        String userJson = """
            {
                "name": "John",
                "email": "john@example.com"
            }
            """;

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("John"))
            .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void getUser_WhenExists_ShouldReturn200() throws Exception {
        User user = userRepository.save(new User("John", "john@example.com"));

        mockMvc.perform(get("/api/users/" + user.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void getUser_WhenNotExists_ShouldReturn404() throws Exception {
        mockMvc.perform(get("/api/users/999"))
            .andExpect(status().isNotFound());
    }
}

// Integration test with TestRestTemplate
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser_ShouldReturnCreatedUser() {
        UserRequest request = new UserRequest("John", "john@example.com");

        ResponseEntity<User> response = restTemplate.postForEntity(
            "/api/users",
            request,
            User.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().getName());
    }
}

// Test with H2 in-memory database
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_ShouldReturnUser() {
        User user = userRepository.save(new User("John", "john@example.com"));

        Optional<User> found = userRepository.findByEmail("john@example.com");

        assertTrue(found.isPresent());
        assertEquals("John", found.get().getName());
    }
}
```

**Key points:**
- @SpringBootTest loads full application context
- MockMvc for testing without HTTP server
- TestRestTemplate for real HTTP calls
- @DataJpaTest for repository tests

---

### 43. How do you mock repositories and external services in Spring Boot tests?

I'd use **@MockBean** to replace Spring beans with mocks in integration tests, or **Mockito** for unit tests.

```java
// Integration test with mocked repository
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserRepository userRepository;
    
    @MockBean
    private EmailService emailService;

    @Test
    void createUser_ShouldCallEmailService() throws Exception {
        User user = new User("John", "john@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\",\"email\":\"john@example.com\"}"))
            .andExpect(status().isCreated());

        verify(emailService).sendWelcomeEmail("john@example.com");
    }
}

// Mock external REST API
@SpringBootTest
class ExternalApiTest {
    @Autowired
    private ExternalApiClient apiClient;
    
    @MockBean
    private RestTemplate restTemplate;

    @Test
    void fetchData_ShouldReturnData() {
        ExternalData mockData = new ExternalData("test");
        when(restTemplate.getForObject(anyString(), eq(ExternalData.class)))
            .thenReturn(mockData);

        ExternalData result = apiClient.fetchData();

        assertEquals("test", result.getValue());
    }
}

// WireMock for external HTTP services
@SpringBootTest
@AutoConfigureWireMock(port = 0)
class ExternalServiceTest {
    @Autowired
    private ExternalService externalService;

    @Test
    void callExternalApi_ShouldReturnResponse() {
        stubFor(get(urlEqualTo("/api/data"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"value\":\"test\"}")));

        String result = externalService.getData();

        assertEquals("test", result);
    }
}
```

**Key points:**
- @MockBean replaces Spring beans with mocks
- Use Mockito for unit test mocks
- WireMock for external HTTP services
- Verify interactions with verify()

---

### 44. How do you test asynchronous methods in Spring Boot?

I'd use **CompletableFuture.get()** to wait for async completion, or **Awaitility** library for polling-based assertions.

```java
// Async service
@Service
class AsyncService {
    @Async
    public CompletableFuture<String> processAsync(String input) {
        // Simulate processing
        sleep(1000);
        return CompletableFuture.completedFuture("Processed: " + input);
    }

    @Async
    public void processWithoutReturn(String input) {
        sleep(1000);
        // Process
    }
}

// Test async with CompletableFuture
@SpringBootTest
class AsyncServiceTest {
    @Autowired
    private AsyncService asyncService;

    @Test
    void processAsync_ShouldReturnResult() throws Exception {
        CompletableFuture<String> future = asyncService.processAsync("test");

        String result = future.get(2, TimeUnit.SECONDS);

        assertEquals("Processed: test", result);
    }
}

// Test with Awaitility
@SpringBootTest
class AsyncServiceAwaitilityTest {
    @Autowired
    private AsyncService asyncService;
    
    @Autowired
    private ResultRepository resultRepository;

    @Test
    void processWithoutReturn_ShouldSaveResult() {
        asyncService.processWithoutReturn("test");

        await().atMost(3, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                Optional<Result> result = resultRepository.findByInput("test");
                assertTrue(result.isPresent());
            });
    }
}

// Test async with @Async disabled
@SpringBootTest(properties = "spring.task.execution.pool.core-size=0")
class SyncAsyncTest {
    @Autowired
    private AsyncService asyncService;

    @Test
    void processAsync_RunsSynchronously() {
        long start = System.currentTimeMillis();
        
        asyncService.processAsync("test");
        
        long duration = System.currentTimeMillis() - start;
        assertTrue(duration >= 1000); // Runs synchronously
    }
}
```

**Dependencies:**
```xml
<dependency>
    <groupId>org.awaitility</groupId>
    <artifactId>awaitility</artifactId>
    <scope>test</scope>
</dependency>
```

**Key points:**
- Use CompletableFuture.get() with timeout
- Awaitility for polling assertions
- Test both success and timeout scenarios
- Consider disabling async for simpler tests

---

### 45. How do you ensure end-to-end testing for a Spring Boot microservice?

I'd use **@SpringBootTest** with real dependencies, **Testcontainers** for database/message brokers, and test the full flow from API to database.

```java
// E2E test with Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class UserServiceE2ETest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");

    @Container
    static GenericContainer<?> redis = new GenericContainer<>("redis:7")
        .withExposedPorts(6379);

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", redis::getFirstMappedPort);
    }

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private UserRepository userRepository;

    @Test
    void completeUserFlow_ShouldWork() {
        // Create user
        UserRequest request = new UserRequest("John", "john@example.com");
        ResponseEntity<User> createResponse = restTemplate.postForEntity(
            "/api/users",
            request,
            User.class
        );
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        Long userId = createResponse.getBody().getId();

        // Get user
        ResponseEntity<User> getResponse = restTemplate.getForEntity(
            "/api/users/" + userId,
            User.class
        );
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("John", getResponse.getBody().getName());

        // Update user
        User updateRequest = new User("John Updated", "john@example.com");
        restTemplate.put("/api/users/" + userId, updateRequest);

        // Verify in database
        User dbUser = userRepository.findById(userId).orElseThrow();
        assertEquals("John Updated", dbUser.getName());

        // Delete user
        restTemplate.delete("/api/users/" + userId);

        // Verify deleted
        ResponseEntity<User> deletedResponse = restTemplate.getForEntity(
            "/api/users/" + userId,
            User.class
        );
        assertEquals(HttpStatus.NOT_FOUND, deletedResponse.getStatusCode());
    }
}

// E2E with message broker
@SpringBootTest
@Testcontainers
class OrderProcessingE2ETest {
    @Container
    static GenericContainer<?> rabbitmq = new GenericContainer<>("rabbitmq:3-management")
        .withExposedPorts(5672);

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void orderProcessing_ShouldTriggerNotification() throws Exception {
        Order order = orderService.createOrder(new OrderRequest());

        // Wait for async message processing
        await().atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                Message message = rabbitTemplate.receive("notifications");
                assertNotNull(message);
            });
    }
}
```

**Key points:**
- Testcontainers for real dependencies
- Test complete user flows
- Verify database state
- Test async message processing
- Use real HTTP calls with TestRestTemplate


---

## 10. Real-World Scenario Questions

### 46. How would you handle a database migration without downtime in production?

I'd use **Flyway or Liquibase** for versioned migrations, apply **backward-compatible changes**, and use **blue-green deployment** strategy.

```java
// Flyway configuration
spring:
  flyway:
    enabled: true
    baseline-on-migrate: true
    locations: classpath:db/migration

// Migration file: V1__create_users_table.sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);

// Safe migration: Add column (backward compatible)
// V2__add_phone_column.sql
ALTER TABLE users ADD COLUMN phone VARCHAR(20);

// Multi-step migration for breaking changes
// Step 1: Add new column
// V3__add_full_name_column.sql
ALTER TABLE users ADD COLUMN full_name VARCHAR(255);

// Step 2: Migrate data (run in application)
@Component
class DataMigration implements ApplicationRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) {
        jdbcTemplate.update(
            "UPDATE users SET full_name = name WHERE full_name IS NULL"
        );
    }
}

// Step 3: Remove old column (after deployment)
// V4__remove_name_column.sql
ALTER TABLE users DROP COLUMN name;

// Zero-downtime deployment strategy
@Configuration
class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(20);
        config.setConnectionTimeout(30000);
        return new HikariDataSource(config);
    }
}
```

**Migration best practices:**
1. **Add columns** (not remove) in first deployment
2. **Migrate data** in application code
3. **Remove old columns** in next deployment
4. **Test migrations** on staging first
5. **Backup database** before migration

**Key points:**
- Use Flyway/Liquibase for versioning
- Backward-compatible changes only
- Multi-step for breaking changes
- Always have rollback plan

---

### 47. How would you handle large file uploads in a Spring Boot application?

I'd use **streaming** to avoid loading entire file in memory, configure **multipart settings**, and process files **asynchronously**.

```java
// Configure multipart
spring:
  servlet:
    multipart:
      max-file-size: 100MB
      max-request-size: 100MB
      file-size-threshold: 10MB

// Controller with streaming
@RestController
@RequestMapping("/api/files")
class FileUploadController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(
            @RequestParam("file") MultipartFile file) throws IOException {
        
        // Validate
        if (file.isEmpty()) {
            throw new BadRequestException("File is empty");
        }

        // Save with streaming
        String fileId = fileStorageService.store(file);
        
        return ResponseEntity.ok(new FileResponse(fileId, "Upload successful"));
    }

    @PostMapping("/upload-chunked")
    public ResponseEntity<String> uploadChunk(
            @RequestParam("file") MultipartFile chunk,
            @RequestParam("chunkNumber") int chunkNumber,
            @RequestParam("totalChunks") int totalChunks,
            @RequestParam("fileId") String fileId) throws IOException {
        
        fileStorageService.storeChunk(chunk, chunkNumber, fileId);
        
        if (chunkNumber == totalChunks - 1) {
            fileStorageService.mergeChunks(fileId, totalChunks);
            return ResponseEntity.ok("Upload complete");
        }
        
        return ResponseEntity.ok("Chunk uploaded");
    }
}

// Storage service with streaming
@Service
class FileStorageService {
    private final Path uploadDir = Paths.get("uploads");

    public String store(MultipartFile file) throws IOException {
        String fileId = UUID.randomUUID().toString();
        Path targetPath = uploadDir.resolve(fileId);
        
        // Stream to disk
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
        
        return fileId;
    }

    public void storeChunk(MultipartFile chunk, int chunkNumber, String fileId) 
            throws IOException {
        Path chunkPath = uploadDir.resolve(fileId + "_chunk_" + chunkNumber);
        Files.copy(chunk.getInputStream(), chunkPath);
    }

    public void mergeChunks(String fileId, int totalChunks) throws IOException {
        Path finalPath = uploadDir.resolve(fileId);
        
        try (OutputStream output = Files.newOutputStream(finalPath)) {
            for (int i = 0; i < totalChunks; i++) {
                Path chunkPath = uploadDir.resolve(fileId + "_chunk_" + i);
                Files.copy(chunkPath, output);
                Files.delete(chunkPath);
            }
        }
    }
}

// Async processing
@Service
class FileProcessingService {
    @Async
    public CompletableFuture<Void> processFile(String fileId) {
        // Process large file in background
        return CompletableFuture.completedFuture(null);
    }
}
```

**Key points:**
- Stream files to avoid memory issues
- Configure multipart limits
- Chunked upload for very large files
- Process asynchronously
- Store in cloud storage (S3) for production

---

### 48. How would you integrate Spring Boot with a legacy system?

I'd use **REST adapters**, **message queues** for async communication, or **database integration** depending on legacy system capabilities.

```java
// REST adapter for legacy SOAP service
@Service
class LegacySystemAdapter {
    private final WebServiceTemplate webServiceTemplate;

    public LegacySystemAdapter() {
        this.webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri("http://legacy-system/soap");
    }

    public LegacyResponse callLegacySystem(LegacyRequest request) {
        return (LegacyResponse) webServiceTemplate.marshalSendAndReceive(request);
    }
}

// Wrapper service
@Service
class CustomerService {
    @Autowired
    private LegacySystemAdapter legacyAdapter;

    public Customer getCustomer(String customerId) {
        LegacyRequest request = new LegacyRequest(customerId);
        LegacyResponse response = legacyAdapter.callLegacySystem(request);
        return mapToCustomer(response);
    }

    private Customer mapToCustomer(LegacyResponse response) {
        // Map legacy format to modern format
        return new Customer(response.getId(), response.getName());
    }
}

// Database integration with legacy schema
@Entity
@Table(name = "LEGACY_CUSTOMERS", schema = "legacy")
class LegacyCustomer {
    @Id
    @Column(name = "CUST_ID")
    private String customerId;
    
    @Column(name = "CUST_NAME")
    private String customerName;
}

@Repository
interface LegacyCustomerRepository extends JpaRepository<LegacyCustomer, String> {
    @Query(value = "SELECT * FROM legacy.LEGACY_CUSTOMERS WHERE CUST_ID = ?1", 
           nativeQuery = true)
    Optional<LegacyCustomer> findByCustomerId(String customerId);
}

// Message queue integration
@Service
class LegacyMessageService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendToLegacy(Order order) {
        LegacyMessage message = convertToLegacyFormat(order);
        jmsTemplate.convertAndSend("legacy.queue", message);
    }

    @JmsListener(destination = "legacy.response.queue")
    public void handleLegacyResponse(LegacyResponse response) {
        processLegacyResponse(response);
    }
}

// Anti-corruption layer
@Service
class LegacyIntegrationFacade {
    @Autowired
    private LegacySystemAdapter legacyAdapter;

    public ModernCustomer getCustomer(String id) {
        try {
            LegacyResponse response = legacyAdapter.callLegacySystem(
                new LegacyRequest(id)
            );
            return translateToModern(response);
        } catch (Exception e) {
            log.error("Legacy system call failed", e);
            throw new IntegrationException("Failed to fetch customer");
        }
    }
}
```

**Key points:**
- Adapter pattern for legacy APIs
- Anti-corruption layer for translation
- Message queues for async integration
- Database views for legacy schema
- Circuit breaker for resilience

---

### 49. How do you monitor and alert when a Spring Boot service is failing in production?

I'd use **Spring Boot Actuator** for health checks, **Prometheus + Grafana** for metrics, and **alerting tools** for notifications.

```java
// Actuator configuration
management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus
  health:
    defaults:
      enabled: true
  metrics:
    export:
      prometheus:
        enabled: true

// Custom health indicator
@Component
class DatabaseHealthIndicator implements HealthIndicator {
    @Autowired
    private DataSource dataSource;

    @Override
    public Health health() {
        try (Connection conn = dataSource.getConnection()) {
            if (conn.isValid(1)) {
                return Health.up()
                    .withDetail("database", "Available")
                    .build();
            }
        } catch (Exception e) {
            return Health.down()
                .withDetail("error", e.getMessage())
                .build();
        }
        return Health.down().build();
    }
}

// Custom metrics
@Service
class OrderService {
    private final Counter orderCounter;
    private final Timer orderProcessingTimer;

    public OrderService(MeterRegistry registry) {
        this.orderCounter = Counter.builder("orders.created")
            .description("Total orders created")
            .register(registry);
        
        this.orderProcessingTimer = Timer.builder("orders.processing.time")
            .description("Order processing time")
            .register(registry);
    }

    public Order createOrder(OrderRequest request) {
        return orderProcessingTimer.record(() -> {
            Order order = processOrder(request);
            orderCounter.increment();
            return order;
        });
    }
}

// Alerting with custom endpoint
@RestController
@RequestMapping("/api/alerts")
class AlertController {
    @Autowired
    private AlertService alertService;

    @PostMapping("/critical")
    public void sendCriticalAlert(@RequestBody AlertRequest request) {
        alertService.sendAlert(AlertLevel.CRITICAL, request.getMessage());
    }
}

@Service
class AlertService {
    public void sendAlert(AlertLevel level, String message) {
        // Send to Slack, PagerDuty, etc.
        if (level == AlertLevel.CRITICAL) {
            slackClient.sendMessage("#alerts", message);
            pagerDutyClient.triggerIncident(message);
        }
    }
}

// Prometheus alert rules (prometheus.yml)
groups:
  - name: spring_boot_alerts
    rules:
      - alert: HighErrorRate
        expr: rate(http_server_requests_seconds_count{status="500"}[5m]) > 0.1
        for: 5m
        annotations:
          summary: "High error rate detected"
      
      - alert: ServiceDown
        expr: up{job="spring-boot-app"} == 0
        for: 1m
        annotations:
          summary: "Service is down"
```

**Monitoring stack:**
- **Actuator**: Health and metrics endpoints
- **Prometheus**: Metrics collection
- **Grafana**: Visualization
- **AlertManager**: Alert routing
- **PagerDuty/Slack**: Notifications

**Key points:**
- Custom health indicators
- Business metrics tracking
- Alert on error rates and downtime
- Integrate with incident management

---

### 50. How would you implement circuit breaker and fallback mechanisms in a Spring Boot microservice?

I'd use **Resilience4j** to implement circuit breaker pattern with fallback methods for graceful degradation.

```java
// Dependencies
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot2</artifactId>
</dependency>

// Configuration
resilience4j:
  circuitbreaker:
    instances:
      externalService:
        sliding-window-size: 10
        failure-rate-threshold: 50
        wait-duration-in-open-state: 10000
        permitted-number-of-calls-in-half-open-state: 3

// Service with circuit breaker
@Service
class ExternalServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "externalService", fallbackMethod = "fallbackGetData")
    @Retry(name = "externalService")
    public String getData(String id) {
        return restTemplate.getForObject(
            "http://external-service/api/data/" + id,
            String.class
        );
    }

    private String fallbackGetData(String id, Exception e) {
        log.warn("Circuit breaker activated, using fallback for id: {}", id);
        return "Fallback data for " + id;
    }
}

// Multiple fallback levels
@Service
class OrderService {
    @Autowired
    private InventoryServiceClient inventoryClient;
    
    @Autowired
    private CacheService cacheService;

    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackCheckStock")
    public boolean checkStock(String productId) {
        return inventoryClient.checkStock(productId);
    }

    private boolean fallbackCheckStock(String productId, Exception e) {
        // Try cache first
        Optional<Boolean> cached = cacheService.getStockStatus(productId);
        if (cached.isPresent()) {
            return cached.get();
        }
        
        // Default to available
        log.warn("Using default stock status for product: {}", productId);
        return true;
    }
}

// Circuit breaker events
@Component
class CircuitBreakerEventListener {
    @EventListener
    public void onCircuitBreakerEvent(CircuitBreakerOnStateTransitionEvent event) {
        log.info("Circuit breaker state changed: {} -> {}",
            event.getStateTransition().getFromState(),
            event.getStateTransition().getToState());
        
        if (event.getStateTransition().getToState() == CircuitBreaker.State.OPEN) {
            alertService.sendAlert("Circuit breaker opened for " + 
                event.getCircuitBreakerName());
        }
    }
}

// Bulkhead pattern for resource isolation
@Service
class ResourceIntensiveService {
    @Bulkhead(name = "heavyOperation", fallbackMethod = "fallbackHeavyOperation")
    public String heavyOperation(String input) {
        // Resource-intensive operation
        return processHeavyTask(input);
    }

    private String fallbackHeavyOperation(String input, BulkheadFullException e) {
        return "Service busy, try again later";
    }
}

// Rate limiter
@Service
class ApiService {
    @RateLimiter(name = "api", fallbackMethod = "fallbackRateLimit")
    public String callApi(String request) {
        return externalApi.call(request);
    }

    private String fallbackRateLimit(String request, RequestNotPermitted e) {
        return "Rate limit exceeded, please try again later";
    }
}
```

**Key points:**
- Circuit breaker prevents cascading failures
- Fallback methods provide graceful degradation
- Retry for transient failures
- Bulkhead for resource isolation
- Monitor circuit breaker state changes

---

## Summary

This comprehensive guide covers 50 essential Spring Boot interview questions across 10 categories:

1. **Application Design & Architecture** - RESTful APIs, microservices, multi-module apps
2. **Dependency Injection & Beans** - Stereotypes, scopes, conditional beans
3. **REST APIs & Controllers** - Exception handling, versioning, pagination, security
4. **Spring Data & Database** - CRUD, N+1 problem, soft deletes, transactions
5. **Security** - RBAC, JWT, password encryption, vulnerability prevention
6. **Exception Handling & Logging** - Global handlers, custom exceptions, ELK integration
7. **Caching & Performance** - Redis, rate limiting, profiling, monitoring
8. **Messaging & Async** - @Async, RabbitMQ, Kafka, event-driven architecture
9. **Testing** - Unit tests, integration tests, mocking, E2E testing
10. **Real-World Scenarios** - Migrations, file uploads, legacy integration, monitoring, circuit breakers

Each answer provides practical, production-ready code examples with best practices for building robust Spring Boot applications.
