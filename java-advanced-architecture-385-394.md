# 🔵 26. Advanced Architecture

# 🔹 System Design

### Question 385: What is system design for Java applications?

**Answer (40 seconds):**
* Process of defining architecture, components, and interfaces for Java systems
* **Scalability**: Design for horizontal and vertical scaling
* **Performance**: Optimize for throughput and latency requirements
* **Reliability**: Build fault-tolerant systems with proper error handling
* **Security**: Implement authentication, authorization, and data protection
* **Maintainability**: Use clean code principles and modular design
* **Technology Stack**: Choose appropriate frameworks, databases, and tools
* **Trade-offs**: Balance consistency, availability, and partition tolerance (CAP theorem)

```java
// System design example - E-commerce architecture
@RestController
@RequestMapping("/api")
public class OrderController {
    
    @Autowired private OrderService orderService;
    @Autowired private PaymentService paymentService;
    @Autowired private InventoryService inventoryService;
    @Autowired private NotificationService notificationService;
    
    @PostMapping("/orders")
    @Transactional
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        // 1. Validate inventory
        inventoryService.validateAvailability(request.getItems());
        
        // 2. Process payment
        PaymentResult payment = paymentService.processPayment(request.getPayment());
        
        // 3. Create order
        Order order = orderService.createOrder(request, payment);
        
        // 4. Send notifications (async)
        notificationService.sendOrderConfirmation(order);
        
        return ResponseEntity.ok(order);
    }
}
```

---

### Question 386: What is scalability design patterns?

**Answer (35 seconds):**
* Patterns that enable systems to handle increased load efficiently
* **Load Balancing**: Distribute requests across multiple instances
* **Caching**: Store frequently accessed data in memory
* **Database Sharding**: Split data across multiple databases
* **Asynchronous Processing**: Use message queues for non-blocking operations
* **Microservices**: Break monolith into independently scalable services
* **CDN**: Content delivery networks for static assets
* **Auto-scaling**: Automatically adjust resources based on demand

```java
// Caching pattern for scalability
@Service
public class UserService {
    
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    
    @CacheEvict(value = "users", key = "#user.id")
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}

// Asynchronous processing pattern
@Component
public class OrderProcessor {
    
    @Async
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Process order asynchronously
        emailService.sendConfirmation(event.getOrder());
        inventoryService.updateStock(event.getOrder().getItems());
    }
}
```

---

### Question 387: What is reliability design patterns?

**Answer (35 seconds):**
* Patterns that ensure system continues operating despite failures
* **Circuit Breaker**: Prevent cascading failures by failing fast
* **Retry Pattern**: Automatically retry failed operations with backoff
* **Bulkhead**: Isolate resources to prevent total system failure
* **Timeout**: Set time limits to prevent hanging operations
* **Health Check**: Monitor system health and remove unhealthy instances
* **Graceful Degradation**: Provide reduced functionality when components fail
* **Redundancy**: Multiple instances and failover mechanisms

```java
// Circuit breaker pattern
@Component
public class PaymentService {
    
    @CircuitBreaker(name = "payment-service", fallbackMethod = "fallbackPayment")
    @Retry(name = "payment-service")
    @TimeLimiter(name = "payment-service")
    public CompletableFuture<PaymentResult> processPayment(Payment payment) {
        return CompletableFuture.supplyAsync(() -> {
            return externalPaymentGateway.process(payment);
        });
    }
    
    public CompletableFuture<PaymentResult> fallbackPayment(Payment payment, Exception ex) {
        // Fallback to alternative payment method or queue for later
        return CompletableFuture.completedFuture(
            PaymentResult.pending("Payment queued for retry"));
    }
}
```

---

### Question 388: What is availability design patterns?

**Answer (35 seconds):**
* Patterns that maximize system uptime and minimize service interruptions
* **Active-Passive Failover**: Standby system takes over when primary fails
* **Active-Active**: Multiple systems handle load simultaneously
* **Geographic Distribution**: Deploy across multiple regions
* **Zero-Downtime Deployment**: Rolling updates without service interruption
* **Database Replication**: Master-slave or master-master setups
* **Load Balancer Health Checks**: Route traffic only to healthy instances
* **Disaster Recovery**: Backup and recovery procedures

```java
// Health check for availability
@Component
public class SystemHealthIndicator implements HealthIndicator {
    
    @Autowired private DatabaseHealthChecker dbChecker;
    @Autowired private ExternalServiceChecker serviceChecker;
    
    @Override
    public Health health() {
        boolean dbHealthy = dbChecker.isHealthy();
        boolean servicesHealthy = serviceChecker.areServicesHealthy();
        
        if (dbHealthy && servicesHealthy) {
            return Health.up()
                .withDetail("database", "UP")
                .withDetail("external-services", "UP")
                .build();
        }
        
        return Health.down()
            .withDetail("database", dbHealthy ? "UP" : "DOWN")
            .withDetail("external-services", servicesHealthy ? "UP" : "DOWN")
            .build();
    }
}
```

---

### Question 389: What is event-driven architecture?

**Answer (35 seconds):**
* Architecture where components communicate through events rather than direct calls
* **Loose Coupling**: Components don't need to know about each other
* **Asynchronous**: Events processed independently and asynchronously
* **Scalability**: Easy to add new event consumers without changing producers
* **Resilience**: System continues working even if some components are down
* **Event Store**: Persistent storage of events for replay and audit
* **Message Brokers**: Kafka, RabbitMQ, AWS SQS for event distribution

```java
// Event-driven architecture example
@Component
public class OrderEventPublisher {
    
    @Autowired private ApplicationEventPublisher eventPublisher;
    
    public Order createOrder(OrderRequest request) {
        Order order = new Order(request);
        orderRepository.save(order);
        
        // Publish event - other components will react
        eventPublisher.publishEvent(new OrderCreatedEvent(order));
        
        return order;
    }
}

// Event listeners
@Component
public class OrderEventHandlers {
    
    @EventListener
    @Async
    public void handleOrderCreated(OrderCreatedEvent event) {
        emailService.sendOrderConfirmation(event.getOrder());
    }
    
    @EventListener
    @Async
    public void updateInventory(OrderCreatedEvent event) {
        inventoryService.reserveItems(event.getOrder().getItems());
    }
    
    @EventListener
    @Async
    public void processPayment(OrderCreatedEvent event) {
        paymentService.processPayment(event.getOrder().getPayment());
    }
}
```

---

### Question 390: What is CQRS pattern?

**Answer (35 seconds):**
* Command Query Responsibility Segregation - separate read and write operations
* **Commands**: Operations that change state (Create, Update, Delete)
* **Queries**: Operations that read data without side effects
* **Separate Models**: Different models optimized for reads vs writes
* **Performance**: Optimize read and write operations independently
* **Scalability**: Scale read and write sides differently
* **Complexity**: Adds complexity but provides flexibility for complex domains

```java
// CQRS implementation
// Command side - for writes
@Component
public class OrderCommandHandler {
    
    @Autowired private OrderRepository orderRepository;
    @Autowired private EventPublisher eventPublisher;
    
    public void handle(CreateOrderCommand command) {
        Order order = new Order(command.getCustomerId(), command.getItems());
        orderRepository.save(order);
        
        eventPublisher.publish(new OrderCreatedEvent(order.getId()));
    }
}

// Query side - for reads
@Component
public class OrderQueryHandler {
    
    @Autowired private OrderReadModelRepository readRepository;
    
    public List<OrderSummary> getOrdersByCustomer(Long customerId) {
        return readRepository.findOrderSummariesByCustomerId(customerId);
    }
    
    public OrderDetails getOrderDetails(Long orderId) {
        return readRepository.findOrderDetailsById(orderId);
    }
}

// Separate read model optimized for queries
@Entity
public class OrderSummary {
    private Long orderId;
    private Long customerId;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
}
```

---

### Question 391: What is event sourcing?

**Answer (35 seconds):**
* Pattern where application state is stored as sequence of events
* **Event Store**: Immutable log of all events that occurred
* **State Reconstruction**: Current state derived by replaying events
* **Audit Trail**: Complete history of all changes for compliance
* **Time Travel**: Reconstruct state at any point in time
* **Scalability**: Events can be processed asynchronously
* **Complexity**: More complex than traditional CRUD operations

```java
// Event sourcing implementation
@Entity
public class OrderAggregate {
    private Long id;
    private List<Event> events = new ArrayList<>();
    
    public void createOrder(CreateOrderCommand command) {
        OrderCreatedEvent event = new OrderCreatedEvent(
            command.getCustomerId(), 
            command.getItems()
        );
        applyEvent(event);
    }
    
    public void addItem(AddItemCommand command) {
        ItemAddedEvent event = new ItemAddedEvent(
            command.getOrderId(), 
            command.getItem()
        );
        applyEvent(event);
    }
    
    private void applyEvent(Event event) {
        events.add(event);
        // Apply event to current state
        when(event);
    }
    
    // Reconstruct state from events
    public static OrderAggregate fromEvents(List<Event> events) {
        OrderAggregate aggregate = new OrderAggregate();
        events.forEach(aggregate::when);
        return aggregate;
    }
}

// Event store
@Repository
public class EventStore {
    
    public void saveEvents(Long aggregateId, List<Event> events) {
        events.forEach(event -> {
            EventRecord record = new EventRecord(aggregateId, event);
            eventRepository.save(record);
        });
    }
    
    public List<Event> getEvents(Long aggregateId) {
        return eventRepository.findByAggregateIdOrderByVersion(aggregateId)
            .stream()
            .map(EventRecord::getEvent)
            .collect(Collectors.toList());
    }
}
```

---

### Question 392: What is domain-driven design?

**Answer (40 seconds):**
* Software design approach focused on modeling complex business domains
* **Ubiquitous Language**: Common vocabulary between developers and domain experts
* **Bounded Context**: Clear boundaries around domain models
* **Aggregates**: Consistency boundaries for related entities
* **Domain Services**: Business logic that doesn't belong to entities
* **Repositories**: Abstract data access for aggregates
* **Value Objects**: Immutable objects representing domain concepts
* **Domain Events**: Capture important business events

```java
// Domain-driven design example
// Aggregate root
@Entity
public class Order {
    @Id private OrderId id;
    private CustomerId customerId;
    private List<OrderItem> items;
    private OrderStatus status;
    private Money totalAmount;
    
    // Business logic in domain
    public void addItem(Product product, Quantity quantity) {
        if (status != OrderStatus.DRAFT) {
            throw new IllegalStateException("Cannot modify confirmed order");
        }
        
        OrderItem item = new OrderItem(product, quantity);
        items.add(item);
        recalculateTotal();
        
        // Domain event
        DomainEvents.raise(new ItemAddedToOrderEvent(id, item));
    }
    
    public void confirm() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot confirm empty order");
        }
        
        status = OrderStatus.CONFIRMED;
        DomainEvents.raise(new OrderConfirmedEvent(id));
    }
}

// Value object
public class Money {
    private final BigDecimal amount;
    private final Currency currency;
    
    public Money(BigDecimal amount, Currency currency) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
        this.currency = currency;
    }
    
    public Money add(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies");
        }
        return new Money(amount.add(other.amount), currency);
    }
}
```

---

### Question 393: What is clean architecture?

**Answer (35 seconds):**
* Architecture that separates concerns into concentric layers
* **Independence**: Business logic independent of frameworks and databases
* **Dependency Rule**: Dependencies point inward toward business logic
* **Entities**: Core business objects with enterprise-wide rules
* **Use Cases**: Application-specific business rules
* **Interface Adapters**: Convert data between use cases and external systems
* **Frameworks**: Outermost layer with databases, web frameworks, UI

```java
// Clean architecture layers

// 1. Entities (innermost layer)
public class User {
    private UserId id;
    private Email email;
    private String name;
    
    public boolean isValidForRegistration() {
        return email != null && name != null && !name.trim().isEmpty();
    }
}

// 2. Use Cases
@Component
public class RegisterUserUseCase {
    
    private final UserRepository userRepository;
    private final EmailService emailService;
    
    public User execute(RegisterUserRequest request) {
        // Business logic
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        
        User user = new User(request.getEmail(), request.getName());
        if (!user.isValidForRegistration()) {
            throw new InvalidUserDataException();
        }
        
        User savedUser = userRepository.save(user);
        emailService.sendWelcomeEmail(savedUser);
        
        return savedUser;
    }
}

// 3. Interface Adapters
@RestController
public class UserController {
    
    @Autowired private RegisterUserUseCase registerUserUseCase;
    
    @PostMapping("/users")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request) {
        RegisterUserRequest useCaseRequest = mapToUseCaseRequest(request);
        User user = registerUserUseCase.execute(useCaseRequest);
        UserResponse response = mapToResponse(user);
        return ResponseEntity.ok(response);
    }
}
```

---

### Question 394: What is hexagonal architecture?

**Answer (35 seconds):**
* Architecture pattern that isolates core business logic from external concerns
* **Ports**: Interfaces that define how application communicates with outside world
* **Adapters**: Implementations that connect ports to external systems
* **Inside**: Business logic, domain models, use cases
* **Outside**: Databases, web frameworks, message queues, external APIs
* **Testability**: Easy to test business logic in isolation
* **Flexibility**: Easy to swap external dependencies

```java
// Hexagonal architecture example

// Port (interface) - defines contract
public interface UserRepository {
    User save(User user);
    Optional<User> findById(UserId id);
    boolean existsByEmail(Email email);
}

// Core business logic (inside the hexagon)
@Component
public class UserService {
    
    private final UserRepository userRepository; // Port dependency
    private final NotificationPort notificationPort; // Another port
    
    public User createUser(String email, String name) {
        Email userEmail = new Email(email);
        
        if (userRepository.existsByEmail(userEmail)) {
            throw new UserAlreadyExistsException();
        }
        
        User user = new User(userEmail, name);
        User savedUser = userRepository.save(user);
        
        notificationPort.sendWelcomeNotification(savedUser);
        
        return savedUser;
    }
}

// Adapter (implementation) - connects to external system
@Repository
public class JpaUserRepositoryAdapter implements UserRepository {
    
    @Autowired private JpaUserRepository jpaRepository;
    
    @Override
    public User save(User user) {
        UserEntity entity = mapToEntity(user);
        UserEntity saved = jpaRepository.save(entity);
        return mapToDomain(saved);
    }
    
    @Override
    public Optional<User> findById(UserId id) {
        return jpaRepository.findById(id.getValue())
            .map(this::mapToDomain);
    }
}

// Another adapter for notifications
@Component
public class EmailNotificationAdapter implements NotificationPort {
    
    @Override
    public void sendWelcomeNotification(User user) {
        emailService.send(user.getEmail(), "Welcome!", "Welcome to our platform!");
    }
}
```
