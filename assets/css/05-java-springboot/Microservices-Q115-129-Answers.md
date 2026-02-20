# ✅ 10) Microservices Basics and Architecture

## 115. Monolithic vs microservices architecture.

**Answer:** Monolithic is a single deployable unit where all features are tightly coupled. Microservices split the application into independent services, each owning its domain and deployable separately.

**Example:**
```java
// Monolithic - All in one application
@SpringBootApplication
public class EcommerceApp {
    // Order, Payment, Inventory all in same codebase
}

// Microservices - Separate services
@SpringBootApplication
public class OrderService { }

@SpringBootApplication
public class PaymentService { }

@SpringBootApplication
public class InventoryService { }
```

---

## 116. When should you NOT use microservices?

**Answer:** Avoid microservices for small teams, simple applications, startups with unclear requirements, or when operational complexity exceeds benefits. Use monolith first.

**Example:**
```java
// DON'T use microservices for:
// - Team < 5 developers
// - Simple CRUD app
// - Tight budget/timeline
// - No DevOps expertise

// Start with modular monolith
@SpringBootApplication
public class SimpleApp {
    // Keep modules separated but in one deployment
}
```

---

## 117. How do you decompose a monolith?

**Answer:** Identify bounded contexts, extract services by business capability, use strangler pattern to gradually migrate functionality while keeping the monolith running.

**Example:**
```java
// Step 1: Identify bounded context
// Monolith has: User, Order, Payment

// Step 2: Extract one service
@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    
    @PostMapping("/orders")
    public Order create(@RequestBody Order order) {
        // Call new PaymentService instead of internal method
        restTemplate.postForObject("http://payment-service/pay", payment, Response.class);
        return orderRepo.save(order);
    }
}
```

---

## 118. What is Domain-Driven Design (DDD)?

**Answer:** DDD is an approach to software design that focuses on modeling business domains. Key concepts: Bounded Context, Entities, Aggregates, Value Objects, and Ubiquitous Language.

**Example:**
```java
// Bounded Context: Order Management
@Entity
public class Order {  // Aggregate Root
    @Id
    private Long id;
    private List<OrderItem> items;  // Entity
    private Money totalAmount;  // Value Object
}

@Embeddable
public class Money {  // Value Object
    private BigDecimal amount;
    private String currency;
}
```

---

## 119. What is service discovery (Eureka, Consul)?

**Answer:** Service discovery automatically detects network locations of service instances. Services register themselves, and clients query the registry to find available instances.

**Example:**
```java
// Eureka Server
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApp { }

// Service Registration
@EnableEurekaClient
@SpringBootApplication
public class OrderService { }

// application.yml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

---

## 120. Explain API Gateway pattern.

**Answer:** API Gateway is a single entry point for all clients. It routes requests, aggregates responses, handles authentication, rate limiting, and protocol translation.

**Example:**
```java
// Spring Cloud Gateway
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("order", r -> r.path("/orders/**")
                .uri("lb://ORDER-SERVICE"))
            .route("payment", r -> r.path("/payments/**")
                .uri("lb://PAYMENT-SERVICE"))
            .build();
    }
}
```

---

## 121. What is Circuit Breaker pattern (Resilience4j)?

**Answer:** Circuit Breaker prevents cascading failures by stopping calls to failing services. States: CLOSED (normal), OPEN (failing), HALF_OPEN (testing recovery).

**Example:**
```java
@Service
public class OrderService {
    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public Payment processPayment(Order order) {
        return restTemplate.postForObject("http://payment-service/pay", order, Payment.class);
    }
    
    public Payment paymentFallback(Order order, Exception e) {
        return new Payment("PENDING");
    }
}

// application.yml
resilience4j.circuitbreaker:
  instances:
    paymentService:
      failure-rate-threshold: 50
      wait-duration-in-open-state: 10000
```

---

## 122. Explain Saga pattern (choreography vs orchestration).

**Answer:** Saga manages distributed transactions across services. Choreography: services publish events, others react. Orchestration: central coordinator directs the flow.

**Example:**
```java
// Choreography - Event-driven
@Service
public class OrderService {
    public void createOrder(Order order) {
        orderRepo.save(order);
        eventPublisher.publish(new OrderCreatedEvent(order));
    }
}

@Service
public class PaymentService {
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        processPayment(event.getOrder());
    }
}

// Orchestration - Central coordinator
@Service
public class OrderSaga {
    public void execute(Order order) {
        orderService.create(order);
        paymentService.process(order);
        inventoryService.reserve(order);
    }
}
```

---

## 123. How do you handle inter-service communication?

**Answer:** Use synchronous (REST, gRPC) for immediate responses or asynchronous (messaging) for decoupling. Choose based on latency, coupling, and reliability needs.

**Example:**
```java
// Synchronous - REST
@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;
    
    public Order create(Order order) {
        Payment payment = restTemplate.postForObject(
            "http://payment-service/pay", order, Payment.class);
        return orderRepo.save(order);
    }
}

// Asynchronous - Kafka
@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, Order> kafka;
    
    public void create(Order order) {
        orderRepo.save(order);
        kafka.send("order-created", order);
    }
}
```

---

## 124. REST vs messaging (Kafka, RabbitMQ) - when to use which?

**Answer:** Use REST for synchronous, request-response scenarios. Use messaging for asynchronous, event-driven, high-throughput, or decoupled communication.

**Example:**
```java
// REST - Immediate response needed
@GetMapping("/orders/{id}")
public Order getOrder(@PathVariable Long id) {
    return orderService.findById(id);
}

// Kafka - Fire and forget, high volume
@Service
public class NotificationService {
    @KafkaListener(topics = "order-created")
    public void sendEmail(Order order) {
        emailService.send(order.getCustomerEmail());
    }
}
```

---

## 125. What is event-driven architecture?

**Answer:** Event-driven architecture uses events to trigger and communicate between services. Services publish events when state changes; others subscribe and react asynchronously.

**Example:**
```java
// Event Publisher
@Service
public class OrderService {
    @Autowired
    private ApplicationEventPublisher publisher;
    
    public void placeOrder(Order order) {
        orderRepo.save(order);
        publisher.publishEvent(new OrderPlacedEvent(order));
    }
}

// Event Listener
@Service
public class InventoryService {
    @EventListener
    public void reserveStock(OrderPlacedEvent event) {
        inventoryRepo.reserve(event.getOrder().getItems());
    }
}
```

---

## 126. How do you implement distributed tracing?

**Answer:** Distributed tracing tracks requests across services using trace IDs. Tools like Zipkin or Jaeger collect and visualize traces to identify bottlenecks.

**Example:**
```java
// Add dependency: spring-cloud-starter-zipkin

// application.yml
spring:
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
      probability: 1.0

// Automatic tracing
@RestController
public class OrderController {
    @GetMapping("/orders/{id}")
    public Order get(@PathVariable Long id) {
        // Trace ID automatically propagated
        return orderService.findById(id);
    }
}
```

---

## 127. What is correlation ID?

**Answer:** Correlation ID is a unique identifier passed through all services in a request chain, enabling log aggregation and request tracking across distributed systems.

**Example:**
```java
@Component
public class CorrelationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String correlationId = request.getHeader("X-Correlation-ID");
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        MDC.put("correlationId", correlationId);
        return true;
    }
}

// Logging
log.info("Processing order"); // Logs: [correlationId=abc-123] Processing order
```

---

## 128. What is Spring Cloud Config Server?

**Answer:** Spring Cloud Config Server provides centralized external configuration management for distributed systems. Services fetch configuration from a central server backed by Git.

**Example:**
```java
// Config Server
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApp { }

// application.yml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/myorg/config-repo

// Client Service
@SpringBootApplication
public class OrderService { }

// bootstrap.yml
spring:
  cloud:
    config:
      uri: http://localhost:8888
      name: order-service
```

---

## 129. What is database per service pattern?

**Answer:** Each microservice owns its database, ensuring loose coupling and independent scaling. Services cannot directly access other service databases; use APIs or events.

**Example:**
```java
// Order Service - owns order_db
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private Long id;
}

// Payment Service - owns payment_db
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    private Long id;
}

// Communication via API, not direct DB access
@Service
public class OrderService {
    public Order create(Order order) {
        orderRepo.save(order);
        // Call Payment Service API, not its DB
        restTemplate.post("http://payment-service/pay", payment);
        return order;
    }
}
```