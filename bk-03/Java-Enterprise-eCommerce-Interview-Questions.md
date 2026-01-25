# Java Enterprise & eCommerce Interview Questions - Quick Answers

## 1. What are caching strategies in Java?

Caching strategies determine how and when data is stored in cache to improve application performance. Different strategies suit different use cases based on read/write patterns and consistency requirements.

**Main Caching Strategies:**
- **Cache-Aside:** Application manages cache manually
- **Write-Through:** Write to cache and database simultaneously
- **Write-Behind:** Write to cache first, database later
- **Read-Through:** Cache loads data on miss
- **Refresh-Ahead:** Proactively refresh before expiration

```java
// Cache-Aside pattern with Spring
@Service
public class ProductService {
    
    @Autowired
    private RedisTemplate<String, Product> redisTemplate;
    
    @Autowired
    private ProductRepository productRepository;
    
    public Product getProduct(Long id) {
        String key = "product:" + id;
        
        // Try cache first
        Product product = redisTemplate.opsForValue().get(key);
        if (product != null) {
            return product; // Cache hit
        }
        
        // Cache miss - load from database
        product = productRepository.findById(id).orElse(null);
        if (product != null) {
            // Store in cache
            redisTemplate.opsForValue().set(key, product, Duration.ofMinutes(30));
        }
        
        return product;
    }
    
    // Write-Through pattern
    @CachePut(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        return productRepository.save(product); // Updates both cache and DB
    }
    
    // Cache eviction
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
```

## 2. What are the main features of an eCommerce application?

An eCommerce application provides online buying and selling capabilities with features for customers, sellers, and administrators to manage the entire shopping experience.

**Core Features:**
- **Product Catalog:** Browse, search, filter products
- **Shopping Cart:** Add/remove items, calculate totals
- **User Management:** Registration, authentication, profiles
- **Order Management:** Checkout, payment processing, tracking
- **Inventory Management:** Stock tracking, availability
- **Payment Gateway:** Multiple payment methods
- **Admin Panel:** Manage products, orders, users
- **Reviews & Ratings:** Customer feedback system

**Advanced Features:**
- Recommendation engine
- Wishlist functionality
- Multi-vendor support
- Mobile responsiveness
- Analytics and reporting

## 3. Explain the flowchart of an eCommerce application (frontend and backend).

**Frontend Flow:**
1. User visits website → Product browsing
2. Search/Filter products → View product details
3. Add to cart → Review cart items
4. User login/registration → Checkout process
5. Payment → Order confirmation

**Backend Flow:**
1. API receives request → Authentication/Authorization
2. Business logic processing → Database operations
3. External service calls (payment, inventory)
4. Response generation → Frontend update

```
Frontend (React/Angular)
    ↓ HTTP Requests
API Gateway
    ↓ Route requests
Microservices:
├── User Service (Authentication)
├── Product Service (Catalog)
├── Cart Service (Shopping cart)
├── Order Service (Order processing)
├── Payment Service (Payment processing)
├── Inventory Service (Stock management)
└── Notification Service (Emails/SMS)
    ↓ Data persistence
Databases:
├── User DB (PostgreSQL)
├── Product DB (MongoDB)
├── Order DB (PostgreSQL)
└── Cache (Redis)
```

## 4. What are the components and tools used in the backend of an eCommerce application?

**Core Backend Components:**
- **Application Framework:** Spring Boot for REST APIs
- **Database:** PostgreSQL/MySQL for transactions, MongoDB for catalog
- **Caching:** Redis for session and data caching
- **Message Queue:** RabbitMQ/Kafka for async processing
- **Search Engine:** Elasticsearch for product search
- **File Storage:** AWS S3 for images and documents

**Infrastructure Tools:**
- **Containerization:** Docker for deployment
- **Orchestration:** Kubernetes for scaling
- **API Gateway:** Spring Cloud Gateway
- **Monitoring:** Prometheus, Grafana
- **CI/CD:** Jenkins, GitLab CI

```java
// Backend service example
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String category) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.getProducts(category, pageable);
        return ResponseEntity.ok(products);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}

// Configuration
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce
  redis:
    host: localhost
    port: 6379
  rabbitmq:
    host: localhost
    port: 5672
```

## 5. Explain the Git workflow used in an eCommerce application.

**Git Workflow (GitFlow Model):**
- **main/master:** Production-ready code
- **develop:** Integration branch for features
- **feature branches:** Individual feature development
- **release branches:** Prepare for production release
- **hotfix branches:** Critical production fixes

**Typical Workflow:**
1. Create feature branch from develop
2. Develop and test feature
3. Create pull request to develop
4. Code review and merge
5. Create release branch for deployment
6. Deploy to staging/production
7. Merge release to main and develop

```bash
# Feature development workflow
git checkout develop
git pull origin develop
git checkout -b feature/shopping-cart

# Development work
git add .
git commit -m "Add shopping cart functionality"
git push origin feature/shopping-cart

# Create pull request to develop branch
# After review and approval, merge to develop

# Release workflow
git checkout develop
git checkout -b release/v1.2.0
# Final testing and bug fixes
git checkout main
git merge release/v1.2.0
git tag v1.2.0
git checkout develop
git merge release/v1.2.0

# Hotfix workflow
git checkout main
git checkout -b hotfix/critical-bug
# Fix the bug
git checkout main
git merge hotfix/critical-bug
git checkout develop
git merge hotfix/critical-bug
```

## 6. What is Event-Driven Architecture in Java?

Event-Driven Architecture is a design pattern where components communicate through events. When something happens, an event is published, and interested components react to it asynchronously.

**Benefits:**
- Loose coupling between components
- Scalability and resilience
- Real-time processing capabilities
- Easy to add new features

```java
// Event classes
public class OrderCreatedEvent {
    private Long orderId;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    
    // constructors, getters, setters
}

// Event publisher
@Service
public class OrderService {
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = new Order(request);
        order = orderRepository.save(order);
        
        // Publish event
        OrderCreatedEvent event = new OrderCreatedEvent(
            order.getId(), order.getUserId(), order.getAmount()
        );
        eventPublisher.publishEvent(event);
        
        return order;
    }
}

// Event listeners
@Component
public class OrderEventHandler {
    
    @EventListener
    @Async
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Send confirmation email
        emailService.sendOrderConfirmation(event.getUserId(), event.getOrderId());
    }
    
    @EventListener
    @Async
    public void updateInventory(OrderCreatedEvent event) {
        // Update product inventory
        inventoryService.reduceStock(event.getOrderId());
    }
    
    @EventListener
    @Async
    public void processPayment(OrderCreatedEvent event) {
        // Process payment
        paymentService.processPayment(event.getOrderId(), event.getAmount());
    }
}

// Message queue integration
@Component
public class EventPublisher {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @EventListener
    public void publishToQueue(OrderCreatedEvent event) {
        rabbitTemplate.convertAndSend("order.exchange", "order.created", event);
    }
}
```

## 7. Can you write the business logic for a CRUD service in Java?

```java
// Entity
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String name;
    
    private String description;
    
    @DecimalMin("0.0")
    private BigDecimal price;
    
    private Integer stock;
    
    // constructors, getters, setters
}

// Repository
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}

// Service with business logic
@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    // Create
    public Product createProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }
    
    // Read
    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
    }
    
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    
    // Update
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProduct(id);
        
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        
        validateProduct(product);
        return productRepository.save(product);
    }
    
    // Delete
    public void deleteProduct(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }
    
    // Business logic validation
    private void validateProduct(Product product) {
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
    }
}

// Controller
@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
    
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        Page<Product> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id, 
            @Valid @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
```

## 8. How do you migrate a Java application from a lower version to a higher version?

**Migration Process:**
1. **Assessment:** Analyze current codebase and dependencies
2. **Planning:** Create migration strategy and timeline
3. **Environment Setup:** Install new Java version
4. **Code Updates:** Fix compatibility issues
5. **Testing:** Comprehensive testing in new environment
6. **Deployment:** Gradual rollout to production

**Key Steps:**
- Update build tools (Maven/Gradle)
- Replace deprecated APIs
- Update third-party dependencies
- Test thoroughly
- Monitor performance

```java
// Example: Java 8 to Java 17 migration

// Before (Java 8)
List<String> names = Arrays.asList("John", "Jane", "Bob");
List<String> upperNames = names.stream()
    .map(name -> name.toUpperCase())
    .collect(Collectors.toList());

// After (Java 17) - can use var and text blocks
var names = List.of("John", "Jane", "Bob");
var upperNames = names.stream()
    .map(String::toUpperCase)
    .toList(); // New method in Java 16+

// Replace deprecated APIs
// Before
Date date = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

// After
LocalDate date = LocalDate.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

// Update Maven/Gradle
// pom.xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <java.version>17</java.version>
</properties>

// Migration checklist:
// 1. Update Java runtime
// 2. Update build configuration
// 3. Update dependencies
// 4. Fix compilation errors
// 5. Update deprecated API usage
// 6. Test thoroughly
// 7. Performance testing
// 8. Security review
```

The migration process requires careful planning, thorough testing, and gradual deployment to ensure application stability and performance in the new Java version.