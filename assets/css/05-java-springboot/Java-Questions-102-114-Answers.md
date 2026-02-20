# ✅ 9) REST API Development & Best Practices

## 102. What are REST constraints?

**Answer:**
REST constraints are: Client-Server (separation), Stateless (no session on server), Cacheable (responses marked cacheable), Uniform Interface (standard HTTP methods), Layered System (intermediaries allowed), Code-on-Demand (optional, server can send executable code).

**Example:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // Stateless - no session stored on server
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        // Cacheable - add cache headers
        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
            .body(user);
    }
    
    // Uniform Interface - standard HTTP methods
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
```

---

## 103. Explain HTTP methods and their idempotency.

**Answer:**
- **GET**: Retrieve data (Idempotent, Safe)
- **POST**: Create resource (Not Idempotent)
- **PUT**: Update/Replace resource (Idempotent)
- **PATCH**: Partial update (Not Idempotent)
- **DELETE**: Remove resource (Idempotent)

Idempotent means multiple identical requests have same effect as single request.

**Example:**
```java
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @GetMapping("/{id}") // Idempotent, Safe
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }
    
    @PostMapping // Not Idempotent - creates new resource each time
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}") // Idempotent - same result on multiple calls
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }
    
    @DeleteMapping("/{id}") // Idempotent - deleting already deleted returns same state
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
```

---

## 104. Difference between PUT and PATCH?

**Answer:**
PUT replaces entire resource (send all fields). PATCH partially updates resource (send only changed fields). PUT is idempotent, PATCH may not be.

**Example:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // PUT - Replace entire resource
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // All fields must be provided
        return userService.replaceUser(id, user);
    }
    
    // PATCH - Partial update
    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // Only changed fields provided
        return userService.partialUpdate(id, updates);
    }
}

// Example requests:
// PUT /api/users/1
// { "name": "John", "email": "john@example.com", "age": 30 } // All fields

// PATCH /api/users/1
// { "email": "newemail@example.com" } // Only changed field
```

---

## 105. Explain HTTP status codes (200, 201, 204, 400, 401, 403, 404, 500).

**Answer:**
- **200 OK**: Success
- **201 Created**: Resource created
- **204 No Content**: Success, no response body
- **400 Bad Request**: Invalid input
- **401 Unauthorized**: Authentication required
- **403 Forbidden**: No permission
- **404 Not Found**: Resource doesn't exist
- **500 Internal Server Error**: Server error

**Example:**
```java
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.findById(id)
            .map(order -> ResponseEntity.ok(order)) // 200
            .orElse(ResponseEntity.notFound().build()); // 404
    }
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order created = orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse("Invalid input")); // 400
    }
    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Auth required")); // 401
    }
    
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("No permission")); // 403
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("Server error")); // 500
    }
}
```

---

## 106. How do you design RESTful URIs?

**Answer:**
Use nouns (not verbs), plural names, hierarchical structure, lowercase, hyphens for readability. Avoid file extensions and query strings for resource identification.

**Example:**
```java
@RestController
@RequestMapping("/api")
public class RestfulController {
    
    // Good: /api/users (plural noun)
    @GetMapping("/users")
    public List<User> getUsers() { }
    
    // Good: /api/users/123
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) { }
    
    // Good: Hierarchical - /api/users/123/orders
    @GetMapping("/users/{userId}/orders")
    public List<Order> getUserOrders(@PathVariable Long userId) { }
    
    // Good: /api/users/123/orders/456
    @GetMapping("/users/{userId}/orders/{orderId}")
    public Order getUserOrder(@PathVariable Long userId, @PathVariable Long orderId) { }
    
    // Good: Query params for filtering
    @GetMapping("/products")
    public List<Product> getProducts(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) Double minPrice) { }
    
    // Bad examples (avoid):
    // /api/getUsers (verb)
    // /api/user (singular)
    // /api/users.json (file extension)
}
```

---

## 107. What are API versioning strategies?

**Answer:**
- **URI versioning**: `/api/v1/users`
- **Header versioning**: `Accept: application/vnd.api.v1+json`
- **Query parameter**: `/api/users?version=1`
- **Content negotiation**: Custom media types

**Example:**
```java
// 1. URI Versioning (Most common)
@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {
    @GetMapping
    public List<UserV1> getUsers() {
        return userService.getAllUsersV1();
    }
}

@RestController
@RequestMapping("/api/v2/users")
public class UserControllerV2 {
    @GetMapping
    public List<UserV2> getUsers() {
        return userService.getAllUsersV2();
    }
}

// 2. Header Versioning
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping(headers = "X-API-VERSION=1")
    public List<UserV1> getUsersV1() {
        return userService.getAllUsersV1();
    }
    
    @GetMapping(headers = "X-API-VERSION=2")
    public List<UserV2> getUsersV2() {
        return userService.getAllUsersV2();
    }
}

// 3. Query Parameter Versioning
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "1") int version) {
        if (version == 1) {
            return ResponseEntity.ok(userService.getAllUsersV1());
        }
        return ResponseEntity.ok(userService.getAllUsersV2());
    }
}
```

---

## 108. How do you implement pagination, sorting, filtering?

**Answer:**
Use query parameters for pagination (page, size), sorting (sort), and filtering (field-specific params). Spring Data provides Pageable interface.

**Example:**
```java
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    // Pagination, Sorting, Filtering
    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id,asc") String[] sort) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(
            sort[1].equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
            sort[0]
        ));
        
        Page<Product> products = productService.findProducts(category, minPrice, pageable);
        return ResponseEntity.ok(products);
    }
}

// Service
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    
    public Page<Product> findProducts(String category, Double minPrice, Pageable pageable) {
        if (category != null && minPrice != null) {
            return repository.findByCategoryAndPriceGreaterThan(category, minPrice, pageable);
        }
        return repository.findAll(pageable);
    }
}

// Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryAndPriceGreaterThan(String category, Double price, Pageable pageable);
}

// Request: GET /api/products?category=electronics&minPrice=100&page=0&size=20&sort=price,desc
```

---

## 109. What is HATEOAS?

**Answer:**
HATEOAS (Hypermedia As The Engine Of Application State) means API responses include links to related resources, making API self-descriptive and discoverable.

**Example:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        
        EntityModel<User> resource = EntityModel.of(user);
        
        // Add links
        resource.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());
        resource.add(linkTo(methodOn(UserController.class).getUserOrders(id)).withRel("orders"));
        resource.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users"));
        
        return resource;
    }
    
    @GetMapping("/{id}/orders")
    public List<Order> getUserOrders(@PathVariable Long id) {
        return orderService.findByUserId(id);
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}

// Response:
// {
//   "id": 1,
//   "name": "John",
//   "_links": {
//     "self": { "href": "/api/users/1" },
//     "orders": { "href": "/api/users/1/orders" },
//     "all-users": { "href": "/api/users" }
//   }
// }
```

---

## 110. How do you handle error responses consistently?

**Answer:**
Use `@ControllerAdvice` with `@ExceptionHandler` to create global exception handling. Return consistent error response structure with status, message, timestamp.

**Example:**
```java
// Error Response DTO
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    
    public ErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }
    // getters/setters
}

// Global Exception Handler
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex, 
            HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            message,
            request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception ex,
            HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

---

## 111. What is content negotiation?

**Answer:**
Content negotiation allows client to specify desired response format (JSON, XML) using Accept header. Server responds with appropriate format based on client preference.

**Example:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // Produces JSON or XML based on Accept header
    @GetMapping(value = "/{id}", 
                produces = {MediaType.APPLICATION_JSON_VALUE, 
                           MediaType.APPLICATION_XML_VALUE})
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    // Consumes JSON or XML based on Content-Type header
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, 
                            MediaType.APPLICATION_XML_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE, 
                           MediaType.APPLICATION_XML_VALUE})
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}

// Configuration
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
            .favorParameter(false)
            .ignoreAcceptHeader(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType("json", MediaType.APPLICATION_JSON)
            .mediaType("xml", MediaType.APPLICATION_XML);
    }
}

// Request with JSON: Accept: application/json
// Request with XML: Accept: application/xml
```

---

## 112. How do you document APIs (Swagger/OpenAPI)?

**Answer:**
Use SpringDoc OpenAPI (Swagger) to auto-generate API documentation. Add annotations for detailed descriptions. Access UI at `/swagger-ui.html`.

**Example:**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version>
</dependency>
```

```java
// Configuration
@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("User API")
                .version("1.0")
                .description("User management API"));
    }
}

// Controller with documentation
@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management APIs")
public class UserController {
    
    @Operation(summary = "Get user by ID", description = "Returns a single user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public User getUser(
        @Parameter(description = "User ID") @PathVariable Long id) {
        return userService.findById(id);
    }
    
    @Operation(summary = "Create new user")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}

// DTO with schema documentation
public class User {
    @Schema(description = "User ID", example = "1")
    private Long id;
    
    @Schema(description = "User name", example = "John Doe")
    private String name;
    
    @Schema(description = "Email address", example = "john@example.com")
    private String email;
}

// Access: http://localhost:8080/swagger-ui.html
```

---

## 113. How do you implement rate limiting?

**Answer:**
Rate limiting restricts number of requests per time window. Implement using filters, interceptors, or libraries like Bucket4j. Return 429 (Too Many Requests) when limit exceeded.

**Example:**
```java
// Using Bucket4j
@Component
public class RateLimitFilter extends OncePerRequestFilter {
    
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain chain) throws ServletException, IOException {
        String clientId = getClientId(request);
        Bucket bucket = resolveBucket(clientId);
        
        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded");
        }
    }
    
    private Bucket resolveBucket(String clientId) {
        return cache.computeIfAbsent(clientId, k -> createBucket());
    }
    
    private Bucket createBucket() {
        // 10 requests per minute
        Bandwidth limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }
    
    private String getClientId(HttpServletRequest request) {
        return request.getRemoteAddr(); // or use API key
    }
}

// Simple implementation without library
@Component
public class SimpleRateLimiter {
    private final Map<String, List<Long>> requestCounts = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS = 10;
    private static final long TIME_WINDOW = 60000; // 1 minute
    
    public boolean allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        requestCounts.putIfAbsent(clientId, new ArrayList<>());
        
        List<Long> timestamps = requestCounts.get(clientId);
        timestamps.removeIf(time -> now - time > TIME_WINDOW);
        
        if (timestamps.size() < MAX_REQUESTS) {
            timestamps.add(now);
            return true;
        }
        return false;
    }
}
```

---

## 114. What is API Gateway?

**Answer:**
API Gateway is a single entry point for all client requests. It handles routing, authentication, rate limiting, load balancing, and request/response transformation.

**Example:**
```java
// Using Spring Cloud Gateway
@Configuration
public class GatewayConfig {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Route to User Service
            .route("user-service", r -> r
                .path("/api/users/**")
                .filters(f -> f
                    .addRequestHeader("X-Gateway", "true")
                    .circuitBreaker(c -> c.setName("userServiceCB"))
                )
                .uri("lb://USER-SERVICE"))
            
            // Route to Order Service
            .route("order-service", r -> r
                .path("/api/orders/**")
                .filters(f -> f
                    .rewritePath("/api/orders/(?<segment>.*)", "/orders/${segment}")
                    .addResponseHeader("X-Response-Time", String.valueOf(System.currentTimeMillis()))
                )
                .uri("lb://ORDER-SERVICE"))
            
            // Route with rate limiting
            .route("product-service", r -> r
                .path("/api/products/**")
                .filters(f -> f
                    .requestRateLimiter(c -> c
                        .setRateLimiter(redisRateLimiter())
                    )
                )
                .uri("lb://PRODUCT-SERVICE"))
            .build();
    }
    
    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(10, 20); // replenishRate, burstCapacity
    }
}

// Global Filter for authentication
@Component
public class AuthenticationFilter implements GlobalFilter {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        
        if (token == null || !validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        
        return chain.filter(exchange);
    }
    
    private boolean validateToken(String token) {
        // Validate JWT token
        return true;
    }
}

// application.yml
// spring:
//   cloud:
//     gateway:
//       routes:
//         - id: user-service
//           uri: lb://USER-SERVICE
//           predicates:
//             - Path=/api/users/**
```
