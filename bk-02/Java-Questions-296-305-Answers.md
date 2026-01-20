## Microservices

### 296. What are microservices?

**Answer:** Microservices are an architectural approach where applications are built as a collection of small, independent services that communicate over well-defined APIs. Each service is responsible for a specific business capability and can be developed, deployed, and scaled independently.

**Key Characteristics:**
- **Single Responsibility:** Each service handles one business capability
- **Independence:** Services can be developed and deployed separately
- **Decentralized:** No central coordination required
- **Technology Agnostic:** Different services can use different technologies
- **Communication:** Services interact via APIs (REST, messaging)

**Example Structure:**
```
User Service     ←→    Order Service    ←→    Payment Service
     ↓                      ↓                      ↓
User Database        Order Database        Payment Database
```

---

### 297. What are the advantages of microservices?

**Answer:** Key advantages include independent deployment and scaling, technology diversity allowing different tech stacks per service, fault isolation preventing system-wide failures, team autonomy with smaller codebases, and better alignment with business domains through domain-driven design.

**Main Advantages:**

**1. Independent Deployment & Scaling:**
- Deploy services independently
- Scale only what needs scaling
- Faster release cycles

**2. Technology Diversity:**
- Choose best technology for each service
- Experiment with new technologies safely
- Avoid technology lock-in

**3. Fault Isolation:**
- Failure in one service doesn't crash entire system
- Better system resilience
- Easier to identify and fix issues

**4. Team Autonomy:**
- Small, focused teams
- Faster development cycles
- Clear ownership boundaries

**5. Business Alignment:**
- Services align with business domains
- Better understanding of business logic
- Easier to modify business processes

---

### 298. What are the challenges of microservices?

**Answer:** Main challenges include distributed system complexity, network latency and reliability issues, data consistency across services, increased operational overhead, service discovery and communication management, and debugging difficulties across multiple services.

**Key Challenges:**

**1. Distributed System Complexity:**
- Network calls can fail
- Partial failures are common
- Eventual consistency issues

**2. Operational Overhead:**
- More services to monitor and maintain
- Complex deployment pipelines
- Infrastructure management complexity

**3. Data Management:**
- No ACID transactions across services
- Data consistency challenges
- Distributed data queries are complex

**4. Service Communication:**
- Network latency impacts performance
- Service discovery complexity
- API versioning challenges

**5. Testing & Debugging:**
- End-to-end testing is complex
- Distributed debugging is difficult
- Tracing requests across services

---

### 299. What is service discovery?

**Answer:** Service discovery is a mechanism that allows services to find and communicate with each other dynamically. It maintains a registry of available services and their locations, enabling automatic detection when services start, stop, or change locations.

**How it works:**

**1. Service Registration:**
- Services register themselves on startup
- Include service name, IP, port, health check URL

**2. Service Discovery:**
- Clients query registry to find services
- Get current list of healthy service instances

**3. Health Monitoring:**
- Registry monitors service health
- Removes unhealthy instances automatically

**Popular Tools:**
- **Eureka** (Netflix)
- **Consul** (HashiCorp)
- **Zookeeper** (Apache)
- **etcd** (CoreOS)

**Spring Cloud Example:**
```java
@EnableEurekaServer
@SpringBootApplication
public class ServiceRegistryApplication { }

@EnableEurekaClient
@RestController
public class UserService {
    @Autowired
    private DiscoveryClient discoveryClient;
    
    public List<ServiceInstance> getOrderService() {
        return discoveryClient.getInstances("order-service");
    }
}
```

---

### 300. What is API Gateway?

**Answer:** API Gateway is a single entry point that routes requests to appropriate microservices. It handles cross-cutting concerns like authentication, rate limiting, request/response transformation, load balancing, and provides a unified interface to clients.

**Key Functions:**

**1. Request Routing:**
- Route requests to appropriate services
- Load balancing across service instances

**2. Cross-Cutting Concerns:**
- Authentication and authorization
- Rate limiting and throttling
- Request/response transformation
- Logging and monitoring

**3. Protocol Translation:**
- HTTP to different protocols
- API versioning support
- Content negotiation

**Benefits:**
- **Single Entry Point:** Simplified client interaction
- **Security:** Centralized authentication
- **Monitoring:** Centralized logging and metrics
- **Flexibility:** Easy to add new services

**Popular API Gateways:**
- **Spring Cloud Gateway**
- **Netflix Zuul**
- **Kong**
- **AWS API Gateway**

**Example Configuration:**
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/users/**
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/api/orders/**
```

---

### 301. What is circuit breaker pattern?

**Answer:** Circuit breaker pattern prevents cascading failures by monitoring service calls and stopping requests to failing services. It has three states: closed (normal), open (failing), and half-open (testing recovery), protecting the system from repeated failures.

**Circuit Breaker States:**

**1. Closed State (Normal):**
- Requests pass through normally
- Monitor failure rate
- Switch to Open if threshold exceeded

**2. Open State (Failing):**
- Requests fail immediately
- No calls to downstream service
- Timeout period before trying again

**3. Half-Open State (Testing):**
- Limited requests allowed through
- Test if service has recovered
- Switch to Closed if successful, Open if still failing

**Implementation with Resilience4j:**
```java
@Component
public class UserService {
    
    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackUser")
    public User getUser(Long id) {
        return restTemplate.getForObject("/users/" + id, User.class);
    }
    
    public User fallbackUser(Long id, Exception ex) {
        return new User(id, "Default User");
    }
}
```

**Configuration:**
```yaml
resilience4j:
  circuitbreaker:
    instances:
      userService:
        failure-rate-threshold: 50
        wait-duration-in-open-state: 30s
        sliding-window-size: 10
```

---

### 302. What is distributed tracing?

**Answer:** Distributed tracing tracks requests as they flow through multiple microservices, providing visibility into the entire request journey. It helps identify performance bottlenecks, debug issues, and understand service dependencies in complex distributed systems.

**Key Concepts:**

**1. Trace:** Complete request journey across services
**2. Span:** Individual operation within a service
**3. Trace ID:** Unique identifier for entire request
**4. Span ID:** Unique identifier for each operation

**How it works:**
```
Client Request → API Gateway → User Service → Order Service → Payment Service
     |              |             |             |              |
   Trace ID      Span 1        Span 2        Span 3         Span 4
```

**Popular Tools:**
- **Jaeger** (Uber)
- **Zipkin** (Twitter)
- **AWS X-Ray**
- **Google Cloud Trace**

**Spring Cloud Sleuth Example:**
```java
@RestController
public class UserController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/users/{id}/orders")
    public List<Order> getUserOrders(@PathVariable Long id) {
        // Automatically creates spans and propagates trace context
        return orderService.getOrdersByUserId(id);
    }
}
```

**Benefits:**
- **Performance Monitoring:** Identify slow operations
- **Debugging:** Trace errors across services
- **Dependency Mapping:** Understand service relationships
- **Capacity Planning:** Analyze service usage patterns

---

### 303. How do you handle data consistency in microservices?

**Answer:** Use eventual consistency with event-driven architecture, implement saga pattern for distributed transactions, apply CQRS for read/write separation, use event sourcing for audit trails, and design for idempotency to handle duplicate operations safely.

**Data Consistency Strategies:**

**1. Eventual Consistency:**
- Accept temporary inconsistency
- Data becomes consistent over time
- Use event-driven updates

**2. Saga Pattern:**
- Coordinate distributed transactions
- Compensating actions for rollback
- Choreography or orchestration approach

**3. CQRS (Command Query Responsibility Segregation):**
- Separate read and write models
- Optimize each for specific use case
- Event-driven synchronization

**4. Event Sourcing:**
- Store events instead of current state
- Rebuild state from events
- Natural audit trail

**Saga Pattern Example:**
```java
// Order Saga Orchestrator
@Component
public class OrderSaga {
    
    public void processOrder(Order order) {
        try {
            paymentService.processPayment(order.getPayment());
            inventoryService.reserveItems(order.getItems());
            shippingService.scheduleShipment(order);
            orderService.confirmOrder(order.getId());
        } catch (Exception e) {
            // Compensating actions
            paymentService.refundPayment(order.getPayment());
            inventoryService.releaseItems(order.getItems());
            orderService.cancelOrder(order.getId());
        }
    }
}
```

**Event-Driven Consistency:**
```java
@EventHandler
public void handle(OrderCreatedEvent event) {
    // Update read model asynchronously
    orderReadModel.updateOrderView(event.getOrderId(), event.getOrderData());
}
```

---

### 304. What is event-driven architecture?

**Answer:** Event-driven architecture uses events to trigger and communicate between services. Services publish events when state changes occur, and other services subscribe to relevant events, enabling loose coupling and asynchronous communication patterns.

**Key Components:**

**1. Event Producer:** Service that publishes events
**2. Event Consumer:** Service that subscribes to events
**3. Event Broker:** Message broker that routes events
**4. Event Store:** Persistent storage for events

**Event Flow:**
```
Order Service → OrderCreated Event → Message Broker → Inventory Service
                                                   → Payment Service
                                                   → Notification Service
```

**Implementation with Spring Cloud Stream:**
```java
// Event Producer
@Component
public class OrderService {
    
    @Autowired
    private StreamBridge streamBridge;
    
    public void createOrder(Order order) {
        orderRepository.save(order);
        
        OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getUserId());
        streamBridge.send("order-events", event);
    }
}

// Event Consumer
@Component
public class InventoryService {
    
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        reserveInventory(event.getOrderId());
    }
}
```

**Benefits:**
- **Loose Coupling:** Services don't need direct knowledge of each other
- **Scalability:** Asynchronous processing improves performance
- **Flexibility:** Easy to add new event consumers
- **Resilience:** Events can be replayed if processing fails

---

### 305. How do you test microservices?

**Answer:** Implement multiple testing levels: unit tests for individual services, integration tests for service interactions, contract testing to verify API agreements, end-to-end tests for complete workflows, and use tools like TestContainers for isolated testing environments.

**Testing Pyramid for Microservices:**

**1. Unit Tests (Base):**
- Test individual service logic
- Mock external dependencies
- Fast and reliable

**2. Integration Tests:**
- Test service interactions
- Use TestContainers for databases
- Test API endpoints

**3. Contract Tests:**
- Verify API agreements between services
- Producer and consumer contracts
- Use Pact or Spring Cloud Contract

**4. End-to-End Tests (Top):**
- Test complete business workflows
- Minimal number due to complexity
- Test critical user journeys

**Testing Examples:**

**Unit Test:**
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void shouldCreateUser() {
        User user = new User("John", "john@example.com");
        when(userRepository.save(any())).thenReturn(user);
        
        User result = userService.createUser(user);
        
        assertThat(result.getName()).isEqualTo("John");
    }
}
```

**Integration Test with TestContainers:**
```java
@SpringBootTest
@Testcontainers
class UserServiceIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    
    @Test
    void shouldSaveAndRetrieveUser() {
        // Test with real database
    }
}
```

**Contract Test:**
```java
@ExtendWith(PactConsumerTestExt.class)
class UserServiceContractTest {
    
    @Pact(consumer = "order-service", provider = "user-service")
    public RequestResponsePact getUserPact(PactDslWithProvider builder) {
        return builder
            .given("user exists")
            .uponReceiving("get user request")
            .path("/users/123")
            .method("GET")
            .willRespondWith()
            .status(200)
            .body(newJsonBody(body -> {
                body.numberType("id", 123);
                body.stringType("name", "John");
            }).build())
            .toPact();
    }
}
```

**Testing Tools:**
- **JUnit 5** - Unit testing framework
- **Mockito** - Mocking framework
- **TestContainers** - Integration testing with real databases
- **Pact** - Contract testing
- **WireMock** - Service virtualization
- **RestAssured** - API testing

---

## Summary

Microservices architecture provides scalability and flexibility but introduces complexity:

**Core Concepts:**
- **Microservices:** Independent, single-responsibility services
- **Service Discovery:** Dynamic service location and communication
- **API Gateway:** Single entry point with cross-cutting concerns
- **Circuit Breaker:** Fault tolerance and cascading failure prevention

**Data & Communication:**
- **Event-Driven Architecture:** Loose coupling through events
- **Distributed Tracing:** Request visibility across services
- **Data Consistency:** Eventual consistency and saga patterns
- **Testing Strategy:** Multi-level testing approach

**Key Considerations:**
- **Benefits:** Independence, scalability, technology diversity
- **Challenges:** Complexity, data consistency, operational overhead
- **Patterns:** Circuit breaker, saga, CQRS, event sourcing
- **Testing:** Unit, integration, contract, and end-to-end tests

**Success Factors:**
- Proper service boundaries aligned with business domains
- Robust monitoring and observability
- Automated testing and deployment pipelines
- Team organization matching service ownership