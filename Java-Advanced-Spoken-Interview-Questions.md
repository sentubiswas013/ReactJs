# Advanced Java Interview Questions - Spoken Answers

## 1. What is CORS, and how does it work?

CORS stands for Cross-Origin Resource Sharing. It's a browser security feature that blocks requests from one domain to another by default. CORS allows servers to specify which domains can access their resources by sending special HTTP headers. This is common when your frontend runs on localhost:3000 and backend on localhost:8080.

```java
@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        return source;
    }
}
```

## 2. Monolithic vs Microservices Architecture

Monolithic architecture is one big application where everything is bundled together - all features, database access, and business logic in a single deployable unit. Microservices break the application into small, independent services that communicate over APIs. Monolithic is simpler to develop initially but harder to scale. Microservices are more complex but offer better scalability and team independence.

Trade-offs:
- Monolithic: Simple deployment, shared database, single technology stack
- Microservices: Independent scaling, technology diversity, complex deployment
- Choose monolithic for small teams, microservices for large organizations

## 3. How did microservices communicate with each other?

Microservices communicate mainly through REST APIs over HTTP for synchronous calls and message queues for asynchronous communication. For real-time needs, they use messaging systems like RabbitMQ or Kafka. Service discovery helps services find each other dynamically without hardcoding URLs.

```java
// Synchronous communication
@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;
    
    public User getUser(Long id) {
        return restTemplate.getForObject("http://user-service/users/" + id, User.class);
    }
}

// Asynchronous messaging
@Component
public class OrderEventPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void publishOrderCreated(Order order) {
        rabbitTemplate.convertAndSend("order.exchange", "order.created", order);
    }
}
```

