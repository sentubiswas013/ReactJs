## Cloud and Modern Infrastructure 

### 351. What is containerization?

**Answer:** Containerization packages applications with their dependencies into lightweight, portable containers that run consistently across different environments. It uses OS-level virtualization, provides isolation without full VMs, ensures consistent deployment, and enables microservices architecture with tools like Docker.

**Key Containerization Concepts:**

**1. Container vs Virtual Machine:**
```
Virtual Machine:
Hardware → Host OS → Hypervisor → Guest OS → Application

Container:
Hardware → Host OS → Container Runtime → Application
```

**2. Benefits:**
- **Portability:** Run anywhere containers are supported
- **Consistency:** Same environment from dev to production
- **Efficiency:** Share OS kernel, lightweight
- **Scalability:** Quick startup and shutdown
- **Isolation:** Process and resource separation

**3. Container Components:**
```dockerfile
# Dockerfile example
FROM openjdk:17-jre-slim

WORKDIR /app

COPY target/myapp.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

**4. Java Application Containerization:**
```yaml
# docker-compose.yml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
    depends_on:
      - database
  
  database:
    image: postgres:13
    environment:
      POSTGRES_DB: myapp
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
```

**5. Container Lifecycle:**
```bash
# Build image
docker build -t myapp:latest .

# Run container
docker run -d -p 8080:8080 --name myapp-container myapp:latest

# View logs
docker logs myapp-container

# Stop and remove
docker stop myapp-container
docker rm myapp-container
```

---

### 352. What is Docker?

**Answer:** Docker is a containerization platform that packages applications into containers using images, Dockerfiles for build instructions, and registries for sharing. It provides lightweight virtualization, consistent environments across development and production, easy scaling, and simplified deployment processes.

**Docker Core Components:**

**1. Docker Image:**
```dockerfile
# Multi-stage build for Java application
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**2. Docker Commands:**
```bash
# Image management
docker build -t myapp:v1.0 .
docker images
docker rmi myapp:v1.0

# Container management
docker run -d --name myapp -p 8080:8080 myapp:v1.0
docker ps
docker stop myapp
docker start myapp
docker exec -it myapp /bin/bash

# Registry operations
docker push myregistry/myapp:v1.0
docker pull myregistry/myapp:v1.0
```

**3. Docker Networking:**
```bash
# Create custom network
docker network create myapp-network

# Run containers on same network
docker run -d --name database --network myapp-network postgres:13
docker run -d --name app --network myapp-network -p 8080:8080 myapp:latest
```

**4. Docker Volumes:**
```bash
# Named volume for data persistence
docker volume create myapp-data

# Mount volume
docker run -d --name database \
  -v myapp-data:/var/lib/postgresql/data \
  postgres:13

# Bind mount for development
docker run -d --name app \
  -v $(pwd)/src:/app/src \
  -p 8080:8080 myapp:dev
```

**5. Docker Compose for Multi-Service:**
```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - DATABASE_URL=jdbc:postgresql://database:5432/myapp
    depends_on:
      - database
      - redis
    networks:
      - app-network

  database:
    image: postgres:13
    environment:
      POSTGRES_DB: myapp
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
    volumes:
      - db-data:/var/lib/postgresql/data
    networks:
      - app-network

  redis:
    image: redis:6-alpine
    networks:
      - app-network

volumes:
  db-data:

networks:
  app-network:
```

---

### 353. What is Kubernetes?

**Answer:** Kubernetes is a container orchestration platform that manages containerized applications at scale. It handles automatic scaling, load balancing, service discovery, rolling deployments, self-healing capabilities, and provides declarative configuration for managing complex distributed systems.

**Kubernetes Core Concepts:**

**1. Basic Resources:**
```yaml
# Deployment
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
  template:
    metadata:
      labels:
        app: myapp
    spec:
      containers:
      - name: myapp
        image: myapp:latest
        ports:
        - containerPort: 8080
        env:
        - name: DATABASE_URL
          value: "jdbc:postgresql://database:5432/myapp"
```

**2. Service and Ingress:**
```yaml
# Service
apiVersion: v1
kind: Service
metadata:
  name: myapp-service
spec:
  selector:
    app: myapp
  ports:
  - port: 80
    targetPort: 8080
  type: ClusterIP

---
# Ingress
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: myapp-ingress
spec:
  rules:
  - host: myapp.example.com
    http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: myapp-service
            port:
              number: 80
```

**3. ConfigMap and Secret:**
```yaml
# ConfigMap
apiVersion: v1
kind: ConfigMap
metadata:
  name: myapp-config
data:
  application.properties: |
    server.port=8080
    logging.level.com.myapp=DEBUG

---
# Secret
apiVersion: v1
kind: Secret
metadata:
  name: myapp-secret
type: Opaque
data:
  database-password: cGFzc3dvcmQ= # base64 encoded
```

**4. Horizontal Pod Autoscaler:**
```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: myapp-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: myapp-deployment
  minReplicas: 2
  maxReplicas: 10
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 70
```

**5. Common kubectl Commands:**
```bash
# Deploy application
kubectl apply -f deployment.yaml

# View resources
kubectl get pods
kubectl get services
kubectl get deployments

# Scale deployment
kubectl scale deployment myapp-deployment --replicas=5

# View logs
kubectl logs -f deployment/myapp-deployment

# Execute commands in pod
kubectl exec -it pod-name -- /bin/bash

# Port forwarding for testing
kubectl port-forward service/myapp-service 8080:80
```

---

### 354. What is cloud computing?

**Answer:** Cloud computing delivers computing services over the internet including servers, storage, databases, and software. It offers on-demand resource provisioning, pay-as-you-use pricing, global accessibility, automatic scaling, and eliminates need for physical infrastructure management.

**Cloud Computing Models:**

**1. Service Models:**

**IaaS (Infrastructure as a Service):**
```yaml
# Example: AWS EC2, Google Compute Engine
- Virtual machines
- Storage (EBS, Cloud Storage)
- Networking (VPC, Load Balancers)
- Raw computing resources
```

**PaaS (Platform as a Service):**
```yaml
# Example: AWS Elastic Beanstalk, Google App Engine
- Runtime environments
- Development frameworks
- Database services
- Middleware
```

**SaaS (Software as a Service):**
```yaml
# Example: Gmail, Salesforce, Office 365
- Complete applications
- Web-based access
- No installation required
- Subscription-based
```

**2. Deployment Models:**

**Public Cloud:**
- Shared infrastructure
- Cost-effective
- High scalability
- Examples: AWS, Azure, GCP

**Private Cloud:**
- Dedicated infrastructure
- Enhanced security
- Greater control
- Higher cost

**Hybrid Cloud:**
- Combination of public and private
- Flexible workload placement
- Data sovereignty compliance

**3. Java Application on Cloud:**
```yaml
# Spring Boot application.yml for cloud
spring:
  profiles:
    active: cloud
  
  cloud:
    aws:
      region:
        static: us-east-1
      credentials:
        access-key: ${AWS_ACCESS_KEY}
        secret-key: ${AWS_SECRET_KEY}
  
  datasource:
    url: ${DATABASE_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```

**4. Cloud-Native Patterns:**
```java
// Circuit breaker for resilience
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/users/{id}")
    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackUser")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
    
    public ResponseEntity<User> fallbackUser(Long id, Exception ex) {
        return ResponseEntity.ok(new User(id, "Default User"));
    }
}
```

---

### 355. What is distributed system?

**Answer:** A distributed system consists of multiple independent computers that communicate over a network to achieve a common goal. It provides scalability, fault tolerance, and resource sharing, but introduces challenges like network latency, consistency, and complexity in coordination.

**Distributed System Characteristics:**

**1. Key Properties:**
- **Scalability:** Handle increased load by adding resources
- **Fault Tolerance:** Continue operating despite failures
- **Concurrency:** Multiple processes executing simultaneously
- **Transparency:** Hide complexity from users

**2. Challenges:**
```java
// CAP Theorem - Choose 2 of 3:
// Consistency, Availability, Partition Tolerance

// Example: Handling network partitions
@Service
public class OrderService {
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private InventoryService inventoryService;
    
    public Order processOrder(Order order) {
        try {
            // Distributed transaction challenges
            Payment payment = paymentService.processPayment(order.getPayment());
            inventoryService.reserveItems(order.getItems());
            
            return orderRepository.save(order.withStatus(CONFIRMED));
        } catch (ServiceUnavailableException e) {
            // Handle service unavailability
            return order.withStatus(PENDING);
        }
    }
}
```

**3. Communication Patterns:**
```java
// Synchronous communication
@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id);
}

// Asynchronous messaging
@Component
public class OrderEventPublisher {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void publishOrderCreated(Order order) {
        OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getUserId());
        rabbitTemplate.convertAndSend("order.exchange", "order.created", event);
    }
}
```

**4. Consistency Models:**
```java
// Eventual consistency example
@EventHandler
public class OrderProjectionHandler {
    
    @Autowired
    private OrderReadModelRepository repository;
    
    public void handle(OrderCreatedEvent event) {
        // Update read model asynchronously
        OrderReadModel readModel = new OrderReadModel(
            event.getOrderId(),
            event.getUserId(),
            event.getTimestamp()
        );
        repository.save(readModel);
    }
}
```

**5. Distributed System Patterns:**
- **Saga Pattern:** Manage distributed transactions
- **CQRS:** Separate read and write models
- **Event Sourcing:** Store events instead of state
- **Circuit Breaker:** Prevent cascading failures
- **Bulkhead:** Isolate resources

---

### 356. What is load balancing?

**Answer:** Load balancing distributes incoming requests across multiple servers to optimize resource utilization, maximize throughput, minimize response time, and ensure high availability. It uses algorithms like round-robin, least connections, and weighted distribution for traffic management.

**Load Balancing Concepts:**

**1. Load Balancing Algorithms:**
```java
// Round Robin implementation
public class RoundRobinLoadBalancer {
    private final List<Server> servers;
    private final AtomicInteger currentIndex = new AtomicInteger(0);
    
    public Server selectServer() {
        if (servers.isEmpty()) {
            return null;
        }
        
        int index = currentIndex.getAndIncrement() % servers.size();
        return servers.get(index);
    }
}

// Weighted Round Robin
public class WeightedRoundRobinLoadBalancer {
    private final List<WeightedServer> servers;
    
    public Server selectServer() {
        int totalWeight = servers.stream().mapToInt(WeightedServer::getWeight).sum();
        int randomWeight = ThreadLocalRandom.current().nextInt(totalWeight);
        
        int currentWeight = 0;
        for (WeightedServer server : servers) {
            currentWeight += server.getWeight();
            if (randomWeight < currentWeight) {
                return server.getServer();
            }
        }
        return servers.get(0).getServer();
    }
}
```

**2. Health Checking:**
```java
@Component
public class HealthChecker {
    
    private final RestTemplate restTemplate;
    
    @Scheduled(fixedRate = 30000) // Check every 30 seconds
    public void checkServerHealth() {
        for (Server server : servers) {
            try {
                ResponseEntity<String> response = restTemplate.getForEntity(
                    server.getUrl() + "/health", String.class);
                
                if (response.getStatusCode() == HttpStatus.OK) {
                    server.setHealthy(true);
                } else {
                    server.setHealthy(false);
                }
            } catch (Exception e) {
                server.setHealthy(false);
            }
        }
    }
}
```

**3. Spring Cloud Load Balancer:**
```java
@Configuration
public class LoadBalancerConfig {
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(
            Environment environment,
            LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RoundRobinLoadBalancer(
            loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class),
            name);
    }
}

// Usage
@RestController
public class UserController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        // Load balancer automatically selects instance
        return restTemplate.getForObject("http://user-service/users/" + id, User.class);
    }
}
```

**4. Load Balancer Types:**

**Layer 4 (Transport Layer):**
- Routes based on IP and port
- Faster, less CPU intensive
- No application awareness

**Layer 7 (Application Layer):**
- Routes based on content (HTTP headers, URLs)
- More intelligent routing
- Higher CPU usage

**5. Nginx Load Balancer Configuration:**
```nginx
upstream backend {
    least_conn;
    server backend1.example.com:8080 weight=3;
    server backend2.example.com:8080 weight=2;
    server backend3.example.com:8080 weight=1;
}

server {
    listen 80;
    server_name myapp.example.com;
    
    location / {
        proxy_pass http://backend;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

---

### 357. What are caching strategies?

**Answer:** Caching strategies determine how and when to store frequently accessed data in fast storage. Common strategies include cache-aside for application-controlled caching, write-through for immediate updates, write-behind for delayed writes, and refresh-ahead for proactive cache updates.

**Caching Strategies:**

**1. Cache-Aside (Lazy Loading):**
```java
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    
    public User findById(Long id) {
        String cacheKey = "user:" + id;
        
        // Try cache first
        User user = redisTemplate.opsForValue().get(cacheKey);
        if (user != null) {
            return user;
        }
        
        // Load from database
        user = userRepository.findById(id).orElse(null);
        if (user != null) {
            // Store in cache
            redisTemplate.opsForValue().set(cacheKey, user, Duration.ofMinutes(30));
        }
        
        return user;
    }
    
    public User updateUser(User user) {
        User updated = userRepository.save(user);
        
        // Invalidate cache
        String cacheKey = "user:" + user.getId();
        redisTemplate.delete(cacheKey);
        
        return updated;
    }
}
```

**2. Write-Through:**
```java
@Service
public class WriteThoughUserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    
    public User saveUser(User user) {
        // Write to database first
        User saved = userRepository.save(user);
        
        // Then write to cache
        String cacheKey = "user:" + saved.getId();
        redisTemplate.opsForValue().set(cacheKey, saved, Duration.ofMinutes(30));
        
        return saved;
    }
}
```

**3. Write-Behind (Write-Back):**
```java
@Service
public class WriteBehindUserService {
    
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    
    @Autowired
    private UserRepository userRepository;
    
    public User saveUser(User user) {
        String cacheKey = "user:" + user.getId();
        
        // Write to cache immediately
        redisTemplate.opsForValue().set(cacheKey, user, Duration.ofMinutes(30));
        
        // Mark for async database write
        redisTemplate.opsForSet().add("dirty_users", user.getId().toString());
        
        return user;
    }
    
    @Scheduled(fixedRate = 60000) // Every minute
    public void flushDirtyUsers() {
        Set<String> dirtyUserIds = redisTemplate.opsForSet().members("dirty_users");
        
        for (String userId : dirtyUserIds) {
            String cacheKey = "user:" + userId;
            User user = redisTemplate.opsForValue().get(cacheKey);
            
            if (user != null) {
                userRepository.save(user);
                redisTemplate.opsForSet().remove("dirty_users", userId);
            }
        }
    }
}
```

**4. Refresh-Ahead:**
```java
@Service
public class RefreshAheadUserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    
    public User findById(Long id) {
        String cacheKey = "user:" + id;
        User user = redisTemplate.opsForValue().get(cacheKey);
        
        if (user != null) {
            // Check if cache entry is near expiration
            Long ttl = redisTemplate.getExpire(cacheKey);
            if (ttl != null && ttl < 300) { // Less than 5 minutes
                // Refresh asynchronously
                CompletableFuture.runAsync(() -> refreshCache(id));
            }
            return user;
        }
        
        // Cache miss - load synchronously
        return loadAndCache(id);
    }
    
    private void refreshCache(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            String cacheKey = "user:" + id;
            redisTemplate.opsForValue().set(cacheKey, user, Duration.ofMinutes(30));
        }
    }
}
```

**5. Multi-Level Caching:**
```java
@Service
public class MultiLevelCacheService {
    
    private final Map<String, User> l1Cache = new ConcurrentHashMap<>(); // In-memory
    
    @Autowired
    private RedisTemplate<String, User> l2Cache; // Redis
    
    @Autowired
    private UserRepository database; // Database
    
    public User findById(Long id) {
        String key = "user:" + id;
        
        // L1 Cache (In-memory)
        User user = l1Cache.get(key);
        if (user != null) {
            return user;
        }
        
        // L2 Cache (Redis)
        user = l2Cache.opsForValue().get(key);
        if (user != null) {
            l1Cache.put(key, user); // Populate L1
            return user;
        }
        
        // Database
        user = database.findById(id).orElse(null);
        if (user != null) {
            l1Cache.put(key, user); // Populate L1
            l2Cache.opsForValue().set(key, user, Duration.ofMinutes(30)); // Populate L2
        }
        
        return user;
    }
}
```

**6. Cache Strategy Comparison:**

| Strategy | Consistency | Performance | Complexity | Use Case |
|----------|-------------|-------------|------------|----------|
| **Cache-Aside** | High | Good | Low | Read-heavy workloads |
| **Write-Through** | High | Medium | Medium | Consistent reads required |
| **Write-Behind** | Eventual | High | High | Write-heavy workloads |
| **Refresh-Ahead** | Good | High | Medium | Predictable access patterns |

---

## Summary

Cloud and containerization technologies enable modern, scalable applications:

**Core Technologies:**
- **Containerization:** Lightweight, portable application packaging
- **Docker:** Industry-standard containerization platform
- **Kubernetes:** Container orchestration for production scale
- **Cloud Computing:** On-demand, scalable infrastructure services

**Distributed Systems:**
- **Architecture:** Multiple independent services working together
- **Challenges:** Network latency, consistency, fault tolerance
- **Patterns:** Microservices, event-driven architecture, CQRS
- **Load Balancing:** Traffic distribution for high availability

**Caching Strategies:**
- **Cache-Aside:** Application-controlled caching
- **Write-Through:** Immediate cache updates
- **Write-Behind:** Delayed database writes
- **Refresh-Ahead:** Proactive cache refresh

**Best Practices:**
- **Containerize applications** for consistent deployment
- **Use orchestration platforms** for production workloads
- **Implement proper caching** for performance optimization
- **Design for distributed systems** with resilience patterns
- **Monitor and observe** system behavior in production

**Modern Java Development:**
- **Cloud-native patterns** with Spring Cloud
- **Containerized deployments** with Docker and Kubernetes
- **Distributed caching** with Redis and Hazelcast
- **Microservices architecture** with proper service boundaries
- **Observability** with metrics, logging, and tracing