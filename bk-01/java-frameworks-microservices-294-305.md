# 🔹 Other Frameworks

### Question 294: What is Struts framework?

**Answer (30 seconds):**
* MVC framework for building Java web applications
* Based on Model-View-Controller design pattern
* Uses Action classes to handle requests and ActionForm for data binding
* Configuration through XML files (struts-config.xml)
* Popular before Spring MVC, now largely replaced by modern frameworks
* Provides tag libraries for JSP pages

```java
public class LoginAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) {
        LoginForm loginForm = (LoginForm) form;
        // Process login logic
        return mapping.findForward("success");
    }
}
```

---

### Question 295: What is JSF (JavaServer Faces)?

**Answer (30 seconds):**
* Component-based web framework for building Java web applications
* Part of Java EE specification for creating user interfaces
* Uses component tree and event-driven programming model
* **Facelets**: View technology using XHTML templates
* **Managed Beans**: Server-side components for business logic
* Built-in validation, conversion, and internationalization support

```java
@ManagedBean
@RequestScoped
public class UserBean {
    private String name;
    private String email;
    
    public String submit() {
        // Process form submission
        return "success"; // Navigation outcome
    }
}
```

# 🔵 20. Microservices and Distributed Systems
---
# 🔹 ### Microservices Architecture

### Question 296: What are microservices?

**Answer (35 seconds):**
* Architectural approach where application is built as suite of small services
* Each service runs in its own process and communicates via APIs
* **Single Responsibility**: Each service handles one business capability
* **Independent Deployment**: Services can be deployed separately
* **Technology Diversity**: Different services can use different technologies
* **Decentralized**: No central coordination, services are autonomous

```java
// User Service
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) { return userService.findById(id); }
}

// Order Service (separate microservice)
@RestController
@RequestMapping("/orders")
public class OrderController {
    @PostMapping
    public Order createOrder(@RequestBody Order order) { return orderService.save(order); }
}
```

---

### Question 297: What are the advantages of microservices?

**Answer (40 seconds):**
* **Scalability**: Scale individual services based on demand
* **Technology Freedom**: Use different tech stacks per service
* **Independent Deployment**: Deploy services without affecting others
* **Fault Isolation**: Failure in one service doesn't crash entire system
* **Team Autonomy**: Small teams can own and develop services independently
* **Faster Development**: Parallel development and shorter release cycles
* **Better Testability**: Easier to test smaller, focused services

```java
// Each service can scale independently
// User Service - High read traffic
@Service
public class UserService {
    @Cacheable("users")
    public User findById(Long id) { return userRepository.findById(id); }
}

// Payment Service - High security requirements
@Service
public class PaymentService {
    @Transactional
    public Payment processPayment(PaymentRequest request) { /* secure processing */ }
}
```

---

### Question 298: What are the challenges of microservices?

**Answer (40 seconds):**
* **Distributed System Complexity**: Network latency, failures, consistency issues
* **Data Management**: Distributed transactions, eventual consistency
* **Service Communication**: API versioning, service contracts
* **Monitoring & Debugging**: Tracing requests across multiple services
* **Deployment Complexity**: Container orchestration, service mesh
* **Testing Challenges**: Integration testing across services
* **Operational Overhead**: More services to monitor and maintain

```java
// Challenges example - Distributed transaction
@Service
public class OrderService {
    public void createOrder(Order order) {
        // Challenge: What if payment succeeds but inventory fails?
        paymentService.processPayment(order.getPayment());
        inventoryService.reserveItems(order.getItems());
        orderRepository.save(order);
    }
}
```

---

### Question 299: What is service discovery?

**Answer (30 seconds):**
* Mechanism for services to find and communicate with each other
* Services register themselves with discovery server
* Clients query discovery server to find service instances
* **Dynamic**: Handles services starting/stopping automatically
* **Load Balancing**: Distributes requests across available instances
* Popular tools: Eureka, Consul, etcd

```java
// Service Registration
@EnableEurekaClient
@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}

// Service Discovery
@Autowired
private DiscoveryClient discoveryClient;

public List<ServiceInstance> getOrderServiceInstances() {
    return discoveryClient.getInstances("order-service");
}
```

---

### Question 300: What is API gateway?

**Answer (35 seconds):**
* Single entry point for all client requests to microservices
* **Routing**: Directs requests to appropriate backend services
* **Authentication**: Centralized security and access control
* **Rate Limiting**: Controls request frequency per client
* **Load Balancing**: Distributes load across service instances
* **Request/Response Transformation**: Modify requests/responses
* Examples: Spring Cloud Gateway, Zuul, Kong

```java
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/users/**")
                .uri("lb://user-service"))
            .route("order-service", r -> r.path("/orders/**")
                .uri("lb://order-service"))
            .build();
    }
}
```

---

### Question 301: What is circuit breaker pattern?

**Answer (35 seconds):**
* Design pattern that prevents cascading failures in distributed systems
* **Closed**: Normal operation, requests pass through
* **Open**: Service is failing, requests fail fast without calling service
* **Half-Open**: Test if service has recovered
* Protects system from overloading failing services
* Provides fallback mechanisms for graceful degradation

```java
@Component
public class UserService {
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackUser")
    public User getUserById(Long id) {
        return restTemplate.getForObject("/users/" + id, User.class);
    }
    
    public User fallbackUser(Long id, Exception ex) {
        return new User(id, "Default User", "default@email.com");
    }
}
```

---

### Question 302: What is distributed tracing?

**Answer (30 seconds):**
* Technique to track requests as they flow through multiple microservices
* **Trace**: Complete journey of a request across services
* **Span**: Individual operation within a service
* Helps identify performance bottlenecks and failures
* Tools: Zipkin, Jaeger, Spring Cloud Sleuth
* Essential for debugging distributed systems

```java
@RestController
public class OrderController {
    @Autowired
    private Tracer tracer;
    
    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable Long id) {
        Span span = tracer.nextSpan().name("get-order").start();
        try {
            return orderService.findById(id);
        } finally {
            span.end();
        }
    }
}
```

---

### Question 303: What is service mesh architecture?

**Answer (35 seconds):**
* Infrastructure layer that handles service-to-service communication
* **Sidecar Proxy**: Each service has a proxy handling network communication
* **Traffic Management**: Load balancing, routing, retries
* **Security**: mTLS, authentication, authorization
* **Observability**: Metrics, logging, tracing
* Examples: Istio, Linkerd, Consul Connect
* Separates business logic from network concerns

```yaml
# Istio service mesh configuration
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: user-service
spec:
  http:
  - match:
    - headers:
        version:
          exact: v2
    route:
    - destination:
        host: user-service
        subset: v2
```

---

### Question 304: What is database per service pattern?

**Answer (35 seconds):**
* Each microservice owns and manages its own database
* **Data Isolation**: Services cannot directly access other service databases
* **Technology Choice**: Each service can use different database technology
* **Independent Scaling**: Scale database based on service needs
* **Challenge**: Cross-service queries and transactions become complex
* Requires API calls for data from other services

```java
// User Service - owns user database
@Entity
public class User {
    @Id private Long id;
    private String name, email;
}

// Order Service - owns order database, references user by ID only
@Entity
public class Order {
    @Id private Long id;
    private Long userId; // Reference, not foreign key
    private BigDecimal amount;
}
```

---

### Question 305: What is saga pattern for distributed transactions?

**Answer (40 seconds):**
* Pattern for managing distributed transactions across microservices
* **Choreography**: Services coordinate through events
* **Orchestration**: Central coordinator manages transaction flow
* **Compensating Actions**: Undo operations if transaction fails
* Ensures eventual consistency without distributed locks
* Each step is a local transaction with compensation logic

```java
// Saga Orchestrator
@Service
public class OrderSaga {
    public void processOrder(Order order) {
        try {
            paymentService.processPayment(order.getPayment());
            inventoryService.reserveItems(order.getItems());
            orderService.createOrder(order);
        } catch (Exception e) {
            // Compensate - undo previous operations
            paymentService.refundPayment(order.getPayment());
            inventoryService.releaseItems(order.getItems());
        }
    }
}
```