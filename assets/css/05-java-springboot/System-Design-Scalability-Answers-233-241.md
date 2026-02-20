# ✅ 20) Basic System Design and Scalability

## 233. How do you approach system design problems?

**Steps**: Understand requirements → Define scope → Estimate scale → Design high-level architecture → Deep dive components → Identify bottlenecks → Scale and optimize.

**Example:**
```
Design URL Shortener:

1. Requirements: Shorten URL, redirect, analytics
2. Scale: 100M URLs/month, 10:1 read:write ratio
3. Architecture:
   - API Gateway → Load Balancer
   - App Servers (Spring Boot)
   - Database (PostgreSQL) + Cache (Redis)
   - CDN for static content

4. Components:
   - POST /shorten → Generate short code → Store mapping
   - GET /{code} → Lookup → Redirect (302)
   
5. Bottlenecks: Database reads → Add Redis cache
6. Scale: Horizontal scaling + DB sharding by hash
```

---

## 234. What is load balancing?

Distributes incoming traffic across multiple servers to ensure no single server is overwhelmed.

**Example:**
```yaml
# Nginx Load Balancer
upstream backend {
    server app1.example.com:8080;
    server app2.example.com:8080;
    server app3.example.com:8080;
}

server {
    listen 80;
    location / {
        proxy_pass http://backend;
    }
}
```

```java
// Spring Boot app runs on multiple instances
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// Deploy on app1:8080, app2:8080, app3:8080
```

**Algorithms**: Round-robin, least connections, IP hash.

---

## 235. Horizontal vs vertical scaling.

**Vertical**: Add more CPU/RAM to existing server (scale up).

**Horizontal**: Add more servers (scale out).

**Example:**
```
Vertical Scaling:
Server: 4 CPU, 8GB RAM → 16 CPU, 64GB RAM
Pros: Simple, no code changes
Cons: Hardware limits, single point of failure

Horizontal Scaling:
1 Server → 10 Servers behind load balancer
Pros: No limits, fault tolerant
Cons: Complex, requires stateless design
```

```java
// Design for horizontal scaling - stateless
@RestController
public class OrderController {
    @Autowired
    private RedisTemplate<String, Object> redis; // Shared state
    
    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order) {
        // No local state - can run on any server
        redis.opsForValue().set("order:" + order.getId(), order);
        return order;
    }
}
```

---

## 236. Explain CAP theorem.

**C**onsistency: All nodes see same data at same time.

**A**vailability: Every request gets a response.

**P**artition tolerance: System works despite network failures.

**You can only choose 2 of 3.**

**Example:**
```
CA (Consistency + Availability): Traditional RDBMS
- PostgreSQL, MySQL
- No partition tolerance

CP (Consistency + Partition tolerance): 
- MongoDB, HBase
- May reject requests during partition

AP (Availability + Partition tolerance):
- Cassandra, DynamoDB
- Eventually consistent
```

```java
// AP System - Eventually consistent
@Service
public class UserService {
    @Autowired
    private CassandraTemplate cassandra;
    
    public void updateUser(User user) {
        cassandra.update(user); // Available but may be stale
    }
}
```

---

## 237. What is eventual consistency?

Data will become consistent across all nodes eventually, but not immediately. Prioritizes availability over immediate consistency.

**Example:**
```java
// Social media post - eventual consistency
@Service
public class PostService {
    @Autowired
    private KafkaTemplate<String, Post> kafka;
    
    public void createPost(Post post) {
        // Write to primary DB
        postRepository.save(post);
        
        // Async propagate to replicas
        kafka.send("post-created", post);
        
        // Users may see old data for few seconds
    }
}
```

```
Timeline:
T0: User A posts → Saved to DB1
T1: User B reads → Sees old data (DB2 not updated yet)
T2: Replication completes
T3: User B reads → Sees new post
```

---

## 238. How do you design for high availability?

Eliminate single points of failure through redundancy, failover, and geographic distribution.

**Example:**
```yaml
# High Availability Architecture
Components:
- Load Balancer: 2 instances (active-passive)
- App Servers: 3+ instances across availability zones
- Database: Master-slave replication + auto-failover
- Cache: Redis Sentinel (3 nodes)
- Message Queue: Kafka cluster (3 brokers)

Availability = 99.99% (4 nines) = 52 min downtime/year
```

```java
// Health check for failover
@RestController
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        // Load balancer checks this
        return ResponseEntity.ok("UP");
    }
}
```

```properties
# Spring Boot - Multiple DB connections
spring.datasource.url=jdbc:mysql://primary:3306,secondary:3306/db
spring.datasource.hikari.connection-timeout=5000
```

---

## 239. What is database sharding?

Splitting database horizontally across multiple servers, each holding a subset of data.

**Example:**
```java
// Shard by user ID
@Service
public class UserService {
    private Map<Integer, DataSource> shards = new HashMap<>();
    
    public User getUser(Long userId) {
        int shardId = (int) (userId % 4); // 4 shards
        DataSource ds = shards.get(shardId);
        // Query from specific shard
        return jdbcTemplate.queryForObject(ds, 
            "SELECT * FROM users WHERE id = ?", userId);
    }
}
```

```
Sharding Strategy:
- Shard 0: userId % 4 = 0 (users 0, 4, 8, 12...)
- Shard 1: userId % 4 = 1 (users 1, 5, 9, 13...)
- Shard 2: userId % 4 = 2 (users 2, 6, 10, 14...)
- Shard 3: userId % 4 = 3 (users 3, 7, 11, 15...)

Pros: Horizontal scaling, better performance
Cons: Complex queries, rebalancing difficult
```

---

## 240. What is database replication?

Copying data from primary database to one or more replica databases for redundancy and read scaling.

**Example:**
```java
// Master-Slave replication
@Configuration
public class DataSourceConfig {
    @Bean
    @Primary
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:mysql://master:3306/db").build();
    }
    
    @Bean
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:mysql://slave:3306/db").build();
    }
}

@Service
public class UserService {
    @Autowired
    @Qualifier("masterDataSource")
    private DataSource master;
    
    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slave;
    
    public void createUser(User user) {
        // Write to master
        masterJdbc.update("INSERT INTO users...", user);
    }
    
    public User getUser(Long id) {
        // Read from slave
        return slaveJdbc.queryForObject("SELECT * FROM users WHERE id=?", id);
    }
}
```

**Types**: Master-Slave, Master-Master, Multi-Master.

---

## 241. What is message queue (Kafka vs RabbitMQ)?

Asynchronous communication between services using queues. Decouples producers and consumers.

**Kafka**: High-throughput, distributed streaming platform for event logs.

**RabbitMQ**: Traditional message broker with complex routing.

**Example:**
```java
// Kafka - Event streaming
@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, Order> kafka;
    
    public void createOrder(Order order) {
        orderRepository.save(order);
        kafka.send("order-events", order); // Fire and forget
    }
}

@KafkaListener(topics = "order-events")
public void handleOrder(Order order) {
    // Process asynchronously
    emailService.sendConfirmation(order);
}
```

```java
// RabbitMQ - Task queue
@Service
public class NotificationService {
    @Autowired
    private RabbitTemplate rabbit;
    
    public void sendNotification(String message) {
        rabbit.convertAndSend("notifications", message);
    }
}

@RabbitListener(queues = "notifications")
public void processNotification(String message) {
    // Worker processes message
    smsService.send(message);
}
```
