# Java Interview Questions - Quick Answers

## 1. What is Dependency Injection (DI)?

**Spoken Answer (30 seconds):**
* Dependency Injection is a design pattern where objects don't create their dependencies themselves
* Instead, dependencies are provided from outside, usually by a framework
* It promotes loose coupling and makes code more testable and maintainable
* Think of it like ordering food - you don't cook it yourself, someone brings it to you

**Example:**
```java
// Without DI - tight coupling
class OrderService {
    private EmailService emailService = new EmailService(); // Bad
}

// With DI - loose coupling
class OrderService {
    private EmailService emailService;
    
    public OrderService(EmailService emailService) {
        this.emailService = emailService; // Good - injected
    }
}
```

## 2. What are the best ways to implement Dependency Injection in Java?

**Spoken Answer (35 seconds):**
* Three main types: Constructor injection, Setter injection, and Field injection
* Constructor injection is preferred - it ensures required dependencies are provided
* Use frameworks like Spring, Guice, or CDI for automatic injection
* Annotations like @Autowired, @Inject make it simple

**Example:**
```java
@Service
public class UserService {
    private final UserRepository userRepository;
    
    // Constructor injection - best practice
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // Setter injection
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
```

## 3. What is Core Java and Advanced Java?

**Spoken Answer (25 seconds):**
* Core Java covers fundamental concepts - OOP, collections, exceptions, multithreading
* It's the foundation every Java developer needs to know
* Advanced Java includes enterprise technologies - servlets, JSP, JDBC, frameworks
* Core Java is like learning to drive, Advanced Java is like learning to race

**Core Java Topics:**
- Classes, Objects, Inheritance
- Collections Framework
- Exception Handling
- Multithreading

**Advanced Java Topics:**
- Servlets, JSP
- JDBC, JPA
- Spring Framework
- Microservices

## 4. What is the difference between Core Java and Advanced Java?

**Spoken Answer (30 seconds):**
* Core Java focuses on language fundamentals and basic APIs
* Advanced Java deals with enterprise application development
* Core Java is platform-independent, Advanced Java often involves server-side technologies
* You need Core Java mastery before moving to Advanced Java

**Comparison:**
```java
// Core Java - Basic class
class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}

// Advanced Java - Web servlet
@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) {
        // Handle web requests
    }
}
```

## 5. What is Spring Cloud?

**Spoken Answer (35 seconds):**
* Spring Cloud is a framework for building distributed systems and microservices
* It provides tools for configuration management, service discovery, circuit breakers
* Built on top of Spring Boot, makes microservices development easier
* Includes Netflix OSS components like Eureka, Hystrix, Zuul

**Example:**
```java
// Service Discovery with Eureka
@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}

// Load balancing
@RestController
public class OrderController {
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;
    
    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable String id) {
        return restTemplate.getForObject(
            "http://inventory-service/items/" + id, Order.class);
    }
}
```

## 6. How do you integrate a Java application with a cloud environment?

**Spoken Answer (35 seconds):**
* Use cloud-native frameworks like Spring Boot with cloud connectors
* Deploy using containers (Docker) and orchestration (Kubernetes)
* Leverage cloud services - databases, messaging, storage through APIs
* Use configuration management for different environments
* Implement health checks and monitoring

**Example:**
```java
// Cloud-ready Spring Boot app
@SpringBootApplication
@EnableCloudConfig
public class CloudApp {
    @Value("${cloud.database.url}")
    private String dbUrl;
    
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .url(dbUrl)
            .build();
    }
}

// Docker deployment
// Dockerfile
FROM openjdk:11
COPY target/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## 7. What is Testing in Java?

**Spoken Answer (30 seconds):**
* Testing ensures your code works correctly and prevents bugs
* Main types: Unit tests (individual methods), Integration tests (components together)
* Use frameworks like JUnit for unit testing, TestNG for complex scenarios
* Follow AAA pattern - Arrange, Act, Assert
* Aim for high code coverage but focus on critical paths

**Example:**
```java
// JUnit test
@Test
public void testCalculatorAdd() {
    // Arrange
    Calculator calc = new Calculator();
    
    // Act
    int result = calc.add(5, 3);
    
    // Assert
    assertEquals(8, result);
}

@Test
public void testDivideByZero() {
    Calculator calc = new Calculator();
    assertThrows(ArithmeticException.class, 
        () -> calc.divide(10, 0));
}
```

## 8. What is Mockito?

**Spoken Answer (25 seconds):**
* Mockito is a mocking framework for unit testing in Java
* It creates fake objects (mocks) to simulate dependencies
* Helps test your code in isolation without real database or external services
* Use @Mock annotation and when().thenReturn() for behavior

**Example:**
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void testFindUser() {
        // Mock behavior
        when(userRepository.findById(1L))
            .thenReturn(new User("John"));
        
        User user = userService.getUser(1L);
        assertEquals("John", user.getName());
        
        // Verify interaction
        verify(userRepository).findById(1L);
    }
}
```

## 9. What are the main features of an eCommerce application?

**Spoken Answer (35 seconds):**
* User management - registration, login, profiles
* Product catalog - browse, search, filter products
* Shopping cart and wishlist functionality
* Payment processing and order management
* Inventory tracking and admin dashboard
* Reviews, ratings, and recommendations

**Core Features:**
- User Authentication & Authorization
- Product Management (CRUD)
- Shopping Cart & Checkout
- Payment Gateway Integration
- Order Tracking
- Admin Panel
- Search & Filtering
- Reviews & Ratings

## 10. Explain the flowchart of an eCommerce application (frontend and backend).

**Spoken Answer (40 seconds):**
* Frontend: User browses products, adds to cart, proceeds to checkout
* Backend: Validates requests, processes payments, updates inventory
* Flow: Browse → Add to Cart → Login → Checkout → Payment → Order Confirmation
* Database stores users, products, orders, payments
* APIs connect frontend and backend for real-time updates

**Simple Flow:**
```java
// Backend API endpoints
@RestController
public class ECommerceController {
    
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
    
    @PostMapping("/cart/add")
    public Cart addToCart(@RequestBody CartItem item) {
        return cartService.addItem(item);
    }
    
    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderRequest request) {
        // 1. Validate cart
        // 2. Process payment
        // 3. Update inventory
        // 4. Create order
        return orderService.processOrder(request);
    }
}
```

**Flow Diagram:**
```
Frontend: Browse → Cart → Checkout → Payment
    ↓         ↓      ↓         ↓
Backend:  API → Validate → Process → Confirm
    ↓         ↓      ↓         ↓
Database: Products → Cart → Payment → Orders
```

## 11. What are the components and tools used in the backend of an eCommerce application?

**Spoken Answer (35 seconds):**
* Framework: Spring Boot for REST APIs and business logic
* Database: MySQL/PostgreSQL for data, Redis for caching
* Security: Spring Security for authentication and authorization
* Payment: Stripe, PayPal APIs for payment processing
* Message Queue: RabbitMQ for async processing
* Monitoring: Actuator for health checks

**Backend Stack:**
```java
// Main application
@SpringBootApplication
@EnableJpaRepositories
@EnableRedisRepositories
public class ECommerceBackend {
    
    @Autowired
    private PaymentService paymentService; // Stripe integration
    
    @Autowired
    private RedisTemplate redisTemplate; // Caching
    
    @Autowired
    private RabbitTemplate rabbitTemplate; // Messaging
}

// Database entities
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
}
```

## 12. Explain the Git workflow used in an eCommerce application.

**Spoken Answer (35 seconds):**
* Use GitFlow: main branch for production, develop for integration
* Feature branches for new features, hotfix branches for urgent fixes
* Pull requests for code review before merging
* CI/CD pipeline triggers on merge to develop/main
* Tag releases for version control

**Git Workflow:**
```bash
# Create feature branch
git checkout -b feature/payment-integration
git add .
git commit -m "Add Stripe payment integration"
git push origin feature/payment-integration

# Create pull request, after review:
git checkout develop
git merge feature/payment-integration

# Release to production
git checkout main
git merge develop
git tag v1.2.0
```

## 13. What is CORS, and how does it work?

**Spoken Answer (30 seconds):**
* CORS stands for Cross-Origin Resource Sharing
* Browser security feature that blocks requests from different domains
* Backend must explicitly allow frontend domain to access APIs
* Use @CrossOrigin annotation or global configuration in Spring
* Prevents malicious websites from accessing your APIs

**Example:**
```java
// Method level CORS
@RestController
public class ProductController {
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
}

// Global CORS configuration
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
```

## 14. Can you write test cases using Spring Boot (or unit tests)?

**Spoken Answer (30 seconds):**
* Use @SpringBootTest for integration tests, @WebMvcTest for controller tests
* @MockBean to mock dependencies in Spring context
* TestRestTemplate or MockMvc for testing REST endpoints
* @DataJpaTest for repository layer testing
* Follow AAA pattern: Arrange, Act, Assert

**Example:**
```java
// Controller test
@WebMvcTest(ProductController.class)
class ProductControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProductService productService;
    
    @Test
    void testGetProducts() throws Exception {
        when(productService.getAllProducts())
            .thenReturn(Arrays.asList(new Product("Laptop")));
        
        mockMvc.perform(get("/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Laptop"));
    }
}

// Repository test
@DataJpaTest
class ProductRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ProductRepository repository;
    
    @Test
    void testFindByName() {
        Product product = new Product("iPhone");
        entityManager.persistAndFlush(product);
        
        Product found = repository.findByName("iPhone");
        assertThat(found.getName()).isEqualTo("iPhone");
    }
}
```

## 15. How do you handle security in a Java application?

**Spoken Answer (35 seconds):**
* Use Spring Security for authentication and authorization
* JWT tokens for stateless authentication
* HTTPS for encrypted communication
* Input validation to prevent SQL injection
* Role-based access control (RBAC)
* Password encryption with BCrypt

**Example:**
```java
// Security configuration
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt())
            .build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// JWT token validation
@RestController
public class AuthController {
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        // Validate credentials
        String token = jwtService.generateToken(request.getUsername());
        return ResponseEntity.ok(token);
    }
}
```

## 16. What is Kafka consumer?

**Spoken Answer (30 seconds):**
* Kafka consumer reads messages from Kafka topics
* It subscribes to one or more topics and processes messages in real-time
* Consumers can be part of consumer groups for load balancing
* Each message is processed only once per consumer group
* Use @KafkaListener annotation in Spring Boot for easy integration

**Example:**
```java
@Component
public class OrderConsumer {
    
    @KafkaListener(topics = "order-events", groupId = "order-service")
    public void processOrder(String orderData) {
        // Process the order message
        Order order = parseOrder(orderData);
        orderService.processOrder(order);
        log.info("Processed order: {}", order.getId());
    }
    
    @KafkaListener(topics = "payment-events")
    public void handlePayment(@Payload PaymentEvent event) {
        paymentService.updatePaymentStatus(event);
    }
}
```

## 17. How do you secure a Java Spring Boot application?

**Spoken Answer (35 seconds):**
* Use Spring Security for authentication and authorization
* Implement JWT or OAuth2 for token-based security
* Enable HTTPS and disable HTTP
* Validate all inputs to prevent injection attacks
* Use method-level security with @PreAuthorize
* Configure CORS properly and use security headers

**Example:**
```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
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
public class SecureController {
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
```

## 18. What is Event-Driven Architecture in Java?

**Spoken Answer (35 seconds):**
* Event-driven architecture uses events to trigger actions between services
* Services communicate through events instead of direct API calls
* Promotes loose coupling and scalability
* Use Spring Events for internal events, Kafka for external events
* Events are immutable and represent something that happened

**Example:**
```java
// Event class
public class OrderCreatedEvent {
    private final String orderId;
    private final String customerId;
    private final LocalDateTime timestamp;
    
    // constructor, getters
}

// Event publisher
@Service
public class OrderService {
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    public Order createOrder(OrderRequest request) {
        Order order = new Order(request);
        orderRepository.save(order);
        
        // Publish event
        eventPublisher.publishEvent(
            new OrderCreatedEvent(order.getId(), order.getCustomerId())
        );
        return order;
    }
}

// Event listener
@Component
public class EmailNotificationService {
    
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        emailService.sendOrderConfirmation(event.getCustomerId());
    }
}
```

## 19. Can you write the business logic for a CRUD service in Java?

**Spoken Answer (30 seconds):**
* CRUD means Create, Read, Update, Delete operations
* Use Spring Boot with JPA for database operations
* Create service layer for business logic, repository for data access
* Add validation and error handling
* Return appropriate HTTP status codes

**Example:**
```java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    // getters, setters
}

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
}

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;
    
    public Product create(Product product) {
        return repository.save(product);
    }
    
    public List<Product> getAll() {
        return repository.findAll();
    }
    
    public Product getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }
    
    public Product update(Long id, Product product) {
        Product existing = getById(id);
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        return repository.save(existing);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService service;
    
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        return ResponseEntity.status(201).body(service.create(product));
    }
    
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
```

## 20. What is the difference between Direct Servlet and JSP?

**Spoken Answer (30 seconds):**
* Servlets are Java classes that handle HTTP requests programmatically
* JSP (JavaServer Pages) mixes HTML with Java code for dynamic web pages
* Servlets are better for business logic, JSP for presentation layer
* JSP gets compiled to servlets behind the scenes
* Modern apps use REST APIs instead of JSP for frontend separation

**Example:**
```java
// Direct Servlet
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello from Servlet!</h1>");
        out.println("<p>User: " + request.getParameter("name") + "</p>");
    }
}

// JSP (hello.jsp)
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Hello JSP</title></head>
<body>
    <h1>Hello from JSP!</h1>
    <p>User: <%= request.getParameter("name") %></p>
    <p>Current time: <%= new java.util.Date() %></p>
</body>
</html>
```

**Key Differences:**
- **Servlet:** Pure Java code, handles logic and generates HTML
- **JSP:** HTML with embedded Java, easier for UI development
- **Performance:** Servlets are faster, JSP has compilation overhead
- **Maintenance:** JSP separates presentation from logic better
- **Modern Usage:** Both replaced by REST APIs + frontend frameworks

## 21. How does JDBC connection pooling work? Explain the main points or use cases.

**Spoken Answer (35 seconds):**
* Connection pooling reuses database connections instead of creating new ones
* Pool maintains a set of pre-established connections ready for use
* When app needs connection, it borrows from pool; returns it when done
* Prevents expensive connection creation/destruction overhead
* Configure min/max pool size, timeout, and validation queries
* Essential for high-traffic applications

**Example:**
```java
// HikariCP configuration
@Configuration
public class DatabaseConfig {
    
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/ecommerce");
        config.setUsername("user");
        config.setPassword("password");
        
        // Pool settings
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        
        return new HikariDataSource(config);
    }
}

// Usage in service
@Service
public class UserService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate; // Uses connection pool
    
    public User findById(Long id) {
        // Connection borrowed from pool automatically
        return jdbcTemplate.queryForObject(
            "SELECT * FROM users WHERE id = ?", 
            new UserRowMapper(), id);
        // Connection returned to pool automatically
    }
}
```

## 22. Explain the hierarchy of connection pools (e.g., HikariCP).

**Spoken Answer (30 seconds):**
* HikariCP is the fastest JDBC connection pool library
* Spring Boot uses HikariCP as default since version 2.0
* Hierarchy: DataSource → HikariDataSource → HikariPool → PoolEntry
* PoolEntry wraps actual JDBC Connection with metadata
* ConcurrentBag manages available connections efficiently
* FastList provides lock-free operations for better performance

**Hierarchy Structure:**
```java
// Connection Pool Hierarchy
DataSource (Interface)
    ↓
HikariDataSource (Implementation)
    ↓
HikariPool (Core pool management)
    ↓
ConcurrentBag<PoolEntry> (Thread-safe collection)
    ↓
PoolEntry (Wrapper around Connection)
    ↓
Connection (Actual JDBC connection)

// Configuration example
@ConfigurationProperties("spring.datasource.hikari")
public class HikariProperties {
    private int maximumPoolSize = 10;
    private int minimumIdle = 10;
    private long connectionTimeout = 30000;
    private long idleTimeout = 600000;
    private long maxLifetime = 1800000;
    
    // getters, setters
}

// Pool monitoring
@Component
public class PoolMonitor {
    
    @Autowired
    private HikariDataSource dataSource;
    
    public void logPoolStats() {
        HikariPoolMXBean poolBean = dataSource.getHikariPoolMXBean();
        log.info("Active: {}, Idle: {}, Total: {}", 
            poolBean.getActiveConnections(),
            poolBean.getIdleConnections(),
            poolBean.getTotalConnections());
    }
}
```

## 23. What is the difference between JavaBeans and Spring Beans?

**Spoken Answer (30 seconds):**
* JavaBeans are simple POJOs with getters/setters and no-arg constructor
* Spring Beans are objects managed by Spring IoC container
* JavaBeans follow naming conventions, Spring Beans have lifecycle management
* Spring Beans support dependency injection, scoping, and AOP
* JavaBeans are just data containers, Spring Beans are full-featured components

**Example:**
```java
// JavaBean - simple POJO
public class UserBean {
    private String name;
    private int age;
    
    public UserBean() {} // No-arg constructor required
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

// Spring Bean - managed by container
@Component
@Scope("singleton")
public class UserService {
    
    @Autowired
    private UserRepository userRepository; // DI
    
    @PostConstruct
    public void init() {
        log.info("UserService initialized");
    }
    
    @PreDestroy
    public void cleanup() {
        log.info("UserService destroyed");
    }
}

// Bean configuration
@Configuration
public class AppConfig {
    
    @Bean
    @Scope("prototype")
    public EmailService emailService() {
        return new EmailService();
    }
}
```

## 24. How do you secure REST APIs in a Spring Boot application?

**Spoken Answer (35 seconds):**
* Use Spring Security with JWT or OAuth2 tokens
* Implement authentication filters and authorization rules
* Validate input data and sanitize outputs
* Enable HTTPS and configure CORS properly
* Use rate limiting and API versioning
* Add security headers and audit logging

**Example:**
```java
// Security configuration
@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt())
            .headers(headers -> headers.frameOptions().deny())
            .build();
    }
}

// Secured REST controller
@RestController
@RequestMapping("/api")
@Validated
public class SecureApiController {
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/profile")
    public UserProfile getProfile(Authentication auth) {
        return userService.getProfile(auth.getName());
    }
    
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(
            @Valid @RequestBody OrderRequest request,
            Authentication auth) {
        Order order = orderService.createOrder(request, auth.getName());
        return ResponseEntity.status(201).body(order);
    }
}

// Input validation
public class OrderRequest {
    @NotBlank
    @Size(max = 100)
    private String productName;
    
    @Positive
    private BigDecimal amount;
    
    // getters, setters
}
```

## 25. What design patterns are commonly used in Microservices architecture?

**Spoken Answer (35 seconds):**
* API Gateway pattern for single entry point and routing
* Circuit Breaker pattern for fault tolerance (Hystrix/Resilience4j)
* Saga pattern for distributed transactions
* CQRS for separating read/write operations
* Event Sourcing for audit trails and state reconstruction
* Database per Service for data isolation

**Example:**
```java
// Circuit Breaker pattern
@Component
public class PaymentService {
    
    @CircuitBreaker(name = "payment-service", fallbackMethod = "fallbackPayment")
    @Retry(name = "payment-service")
    public PaymentResponse processPayment(PaymentRequest request) {
        return paymentClient.processPayment(request);
    }
    
    public PaymentResponse fallbackPayment(PaymentRequest request, Exception ex) {
        return PaymentResponse.failed("Payment service unavailable");
    }
}

// Saga pattern - Order orchestrator
@Component
public class OrderSaga {
    
    public void processOrder(OrderCreatedEvent event) {
        try {
            // Step 1: Reserve inventory
            inventoryService.reserveItems(event.getItems());
            
            // Step 2: Process payment
            paymentService.processPayment(event.getPayment());
            
            // Step 3: Confirm order
            orderService.confirmOrder(event.getOrderId());
            
        } catch (Exception e) {
            // Compensating actions
            inventoryService.releaseItems(event.getItems());
            paymentService.refundPayment(event.getPayment());
            orderService.cancelOrder(event.getOrderId());
        }
    }
}

// CQRS pattern
@RestController
public class ProductController {
    
    // Command side - writes
    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@RequestBody CreateProductCommand cmd) {
        commandBus.send(cmd);
        return ResponseEntity.accepted().build();
    }
    
    // Query side - reads
    @GetMapping("/products")
    public List<ProductView> getProducts() {
        return productQueryService.getAllProducts();
    }
}
```

## 26. What is an API Gateway, and what is its purpose?

**Spoken Answer (30 seconds):**
* API Gateway is a single entry point for all client requests to microservices
* It handles routing, authentication, rate limiting, and load balancing
* Provides cross-cutting concerns like logging, monitoring, and caching
* Simplifies client code by aggregating multiple service calls
* Popular solutions: Spring Cloud Gateway, Zuul, Kong, AWS API Gateway

**Example:**
```java
// Spring Cloud Gateway configuration
@Configuration
public class GatewayConfig {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/api/users/**")
                .filters(f -> f
                    .addRequestHeader("X-Gateway", "Spring-Cloud")
                    .circuitBreaker(config -> config.setName("user-cb"))
                    .retry(3)
                )
                .uri("lb://user-service")
            )
            .route("order-service", r -> r.path("/api/orders/**")
                .filters(f -> f.requestRateLimiter(config -> {
                    config.setRateLimiter(redisRateLimiter());
                    config.setKeyResolver(userKeyResolver());
                }))
                .uri("lb://order-service")
            )
            .build();
    }
    
    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(10, 20); // 10 requests per second
    }
}

// Custom filter for authentication
@Component
public class AuthenticationFilter implements GlobalFilter {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        
        if (isValidToken(token)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}
```

## 27. Have you worked with the Java 11 HTTP Client? How does it differ from the HTTP clients used in earlier Java versions?

* The Java 11 HTTP Client (java.net.http.HttpClient) introduced several key improvements:
* 1. Built-in support for HTTP/2 and WebSocket protocols
* 2. Synchronous and asynchronous request handling via CompletableFuture 
* 3. Fluent builder API for constructing requests
* 4. Better performance and connection pooling compared to HttpURLConnection
* 5. Support for both text and binary data
* 6. Native support for request/response body handlers


// Example usage:
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .build();
    
// Synchronous:
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

// Asynchronous:
CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, 
    HttpResponse.BodyHandlers.ofString());
```

 * Compared to older HttpURLConnection:
 * - No need for manual connection management
 * - Cleaner API without checked exceptions
 * - Built-in support for modern HTTP features
 * - Better error handling and timeout management
