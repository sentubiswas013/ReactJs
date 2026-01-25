# 19. Microservices 

## 1. What are microservices?

Microservices is an architectural approach where applications are built as a collection of small, independent services that communicate over well-defined APIs. Each service is responsible for a specific business function.

- Small, independent services
- Single business responsibility
- Communicate via APIs (usually HTTP/REST)
- Independently deployable
- Technology agnostic
- Owned by small teams

Microservices break down monolithic applications into smaller, manageable pieces that can be developed, deployed, and scaled independently.

## 2. What are the advantages of microservices?

Microservices offer several benefits over monolithic architectures, particularly for large, complex applications and organizations.

**Key Advantages:**
- **Independent deployment:** Deploy services separately
- **Technology diversity:** Different tech stacks per service
- **Scalability:** Scale individual services based on demand
- **Fault isolation:** Failure in one service doesn't crash entire system
- **Team autonomy:** Small teams own complete services
- **Faster development:** Parallel development of services

These benefits enable organizations to move faster, scale better, and maintain more resilient systems.

## 3. What are the challenges of microservices?

While microservices offer many benefits, they also introduce complexity and challenges that must be carefully managed.

**Major Challenges:**
- **Distributed system complexity:** Network calls, latency, failures
- **Data consistency:** Managing transactions across services
- **Service communication:** Inter-service communication overhead
- **Monitoring and debugging:** Tracing requests across services
- **Deployment complexity:** Managing multiple services
- **Testing challenges:** Integration and end-to-end testing

Organizations need proper tooling, processes, and expertise to handle these challenges effectively.

## 4. What is service discovery?

Service discovery is a mechanism that allows services to find and communicate with each other dynamically without hardcoding network locations. It's essential in microservices architectures.

**How it works:**
- Services register themselves with discovery server
- Services query discovery server to find other services
- Handles dynamic IP addresses and scaling
- Provides health checking and load balancing

**Popular Tools:**
- Netflix Eureka
- Consul
- Kubernetes DNS
- AWS Service Discovery

```java
// Service registration with Eureka
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}

// Service discovery usage
@RestController
public class OrderController {
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    public void callUserService() {
        List<ServiceInstance> instances = 
            discoveryClient.getInstances("user-service");
        String url = instances.get(0).getUri().toString();
        // Make HTTP call to user service
    }
}
```

## 5. What is API Gateway?

API Gateway is a server that acts as a single entry point for all client requests to microservices. It routes requests to appropriate services and provides cross-cutting concerns.

**Key Functions:**
- **Request routing:** Direct requests to correct services
- **Authentication and authorization:** Centralized security
- **Rate limiting:** Control request rates
- **Load balancing:** Distribute requests across instances
- **Request/response transformation:** Modify data formats
- **Monitoring and analytics:** Track API usage

**Benefits:**
- Single entry point for clients
- Centralized cross-cutting concerns
- Simplified client code
- Better security and monitoring

```java
// API Gateway with Spring Cloud Gateway
@SpringBootApplication
public class ApiGatewayApplication {
    
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

## 6. What is circuit breaker pattern?

Circuit breaker pattern prevents cascading failures in microservices by monitoring service calls and "opening the circuit" when failures exceed a threshold, allowing the system to fail fast and recover gracefully.

**Circuit States:**
- **Closed:** Normal operation, requests pass through
- **Open:** Failures detected, requests fail immediately
- **Half-Open:** Testing if service has recovered

**Benefits:**
- Prevents cascading failures
- Faster failure detection
- Automatic recovery
- Improves system resilience

**Popular Implementations:**
- Netflix Hystrix (deprecated)
- Resilience4j
- Spring Cloud Circuit Breaker

```java
// Circuit breaker with Resilience4j
@Component
public class UserServiceClient {
    
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackUser")
    @TimeLimiter(name = "user-service")
    public CompletableFuture<User> getUser(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            // Call to user service
            return restTemplate.getForObject("/users/" + id, User.class);
        });
    }
    
    // Fallback method when circuit is open
    public CompletableFuture<User> fallbackUser(Long id, Exception ex) {
        return CompletableFuture.completedFuture(
            new User(id, "Default User", "default@example.com")
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
```

Circuit breaker pattern is essential for building resilient microservices that can handle failures gracefully and maintain system stability.