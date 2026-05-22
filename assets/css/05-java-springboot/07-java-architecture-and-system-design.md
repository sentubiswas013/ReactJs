# Java Architecture & System Design - Key Concepts

---

## 1. Load Balancing

**What it is:**
Load Balancing distributes incoming network traffic across multiple servers to ensure no single server is overwhelmed, improving availability and reliability.

**How it works:**
- A Load Balancer sits between the client and server pool
- It routes each request to one of the available servers based on an algorithm
- If a server goes down, traffic is rerouted to healthy servers automatically

**Algorithms:**
| Algorithm | Description |
|---|---|
| Round Robin | Requests distributed sequentially to each server |
| Least Connections | Routes to server with fewest active connections |
| IP Hash | Same client always goes to same server (sticky sessions) |
| Weighted Round Robin | Servers with higher capacity get more traffic |
| Random | Randomly picks a server |

**Types:**
- **Layer 4 (Transport):** Routes based on IP/TCP — fast, no content inspection (e.g., AWS NLB)
- **Layer 7 (Application):** Routes based on HTTP headers, URL, cookies — smarter routing (e.g., AWS ALB, Nginx)

**Real-world tools:** AWS ALB/NLB, Nginx, HAProxy, Traefik

**Example (Spring Boot + AWS ALB):**
```
Client → ALB → [Service Instance 1]
              → [Service Instance 2]
              → [Service Instance 3]
```

**Key benefits:**
- High availability — no single point of failure
- Horizontal scalability — add more servers as load grows
- Health checks — automatically removes unhealthy instances

---

## 2. Caching

**What it is:**
Caching stores frequently accessed data in fast storage (memory) to reduce latency and database load.

**Cache levels:**
| Level | Example | Latency |
|---|---|---|
| CPU Cache | L1/L2/L3 | Nanoseconds |
| In-process Cache | Guava Cache, Caffeine | Microseconds |
| Distributed Cache | Redis, Memcached | ~1ms |
| CDN Cache | CloudFront | Milliseconds |
| Database Cache | Query cache | Milliseconds |

**Caching strategies:**
- **Cache-Aside (Lazy Loading):** App checks cache first; on miss, loads from DB and populates cache
- **Write-Through:** Write to cache and DB simultaneously; always consistent, higher write latency
- **Write-Behind (Write-Back):** Write to cache first, async write to DB; fast writes, risk of data loss
- **Read-Through:** Cache sits in front of DB; cache handles DB reads automatically

**Cache eviction policies:**
- **LRU (Least Recently Used):** Evicts least recently accessed item — most common
- **LFU (Least Frequently Used):** Evicts least accessed item over time
- **TTL (Time To Live):** Expires after a set duration

**Cache invalidation challenges:**
> "There are only two hard things in Computer Science: cache invalidation and naming things." — Phil Karlton

- Stale data if not invalidated properly
- Solutions: TTL expiry, event-driven invalidation, versioned cache keys

**Spring Boot + Redis example:**
```java
@Cacheable(value = "products", key = "#id")
public Product getProduct(Long id) {
    return productRepository.findById(id).orElseThrow();
}

@CacheEvict(value = "products", key = "#id")
public void updateProduct(Long id, Product product) {
    productRepository.save(product);
}
```

**Key metrics:** Cache hit ratio (aim for > 90%), eviction rate, memory usage

---

## 3. Content Delivery Network (CDN)

**What it is:**
A CDN is a geographically distributed network of servers (edge nodes / PoPs) that cache and serve content to users from the nearest location, reducing latency.

**How it works:**
```
User (India) → DNS resolves to nearest edge (Mumbai PoP) → Cache Hit → Content served
                                                          → Cache Miss → Fetch from Origin → Cache → Serve
```

**What CDN caches:**
- Static assets: images, CSS, JS, fonts, videos
- Dynamic content (with proper cache headers)
- API responses (with short TTL)

**CDN components:**
| Component | Role |
|---|---|
| Origin Server | Source of truth for content |
| Edge Node (PoP) | Caches and serves content near users |
| DNS Routing | Directs user to nearest edge (Anycast/GeoDNS) |
| Cache Control | HTTP headers define what/how long to cache |

**Cache control headers:**
```
Cache-Control: public, max-age=86400       → cache for 1 day
Cache-Control: no-cache                    → always revalidate
Cache-Control: private                     → browser only, not CDN
ETag / Last-Modified                       → conditional requests
```

**Cache invalidation:**
- TTL expiry (automatic)
- Purge API (immediate, e.g., CloudFront invalidation)
- Versioned URLs: `app.v2.js` — no invalidation needed

**Real-world tools:** AWS CloudFront, Cloudflare, Akamai, Fastly

**Benefits:**
- Reduced latency (serve from 50ms vs 300ms)
- Reduced origin load (offload 80-90% of traffic)
- DDoS protection (absorbs traffic at edge)
- High availability (edge nodes independent of origin)

---

## 4. Message Queue

**What it is:**
A Message Queue is an asynchronous communication mechanism where producers send messages to a queue and consumers process them independently, decoupling services.

**How it works:**
```
Producer → [Queue] → Consumer
```
- Producer sends message and continues (fire and forget)
- Queue stores messages durably until consumed
- Consumer polls or receives messages and processes them

**Key concepts:**
| Concept | Description |
|---|---|
| Producer | Sends messages to the queue |
| Consumer | Reads and processes messages |
| Queue | Buffer that holds messages |
| Acknowledgement (ACK) | Consumer confirms successful processing |
| Dead Letter Queue (DLQ) | Holds failed/unprocessable messages |
| Visibility Timeout | Message hidden from other consumers while being processed |

**Message delivery guarantees:**
- **At-most-once:** Message delivered 0 or 1 time (possible loss)
- **At-least-once:** Message delivered 1 or more times (possible duplicates) — most common
- **Exactly-once:** Delivered exactly once — hardest, requires idempotency

**Use cases:**
- Order processing: Order service → Queue → Inventory, Payment, Notification services
- Email/SMS sending: API → Queue → Email worker
- Log processing: App → Queue → Log aggregator
- Decoupling microservices to handle traffic spikes

**Real-world tools:** AWS SQS, RabbitMQ, ActiveMQ, IBM MQ

**Spring Boot + SQS example:**
```java
// Producer
sqsTemplate.send("order-queue", orderEvent);

// Consumer
@SqsListener("order-queue")
public void processOrder(OrderEvent event) {
    orderService.process(event);
}
```

**Message Queue vs Message Broker:** Queue is point-to-point (one consumer); Broker (Kafka/RabbitMQ) supports routing, topics, multiple consumers

---

## 5. Publish-Subscribe (Pub/Sub)

**What it is:**
Pub/Sub is a messaging pattern where publishers send messages to a topic (not directly to consumers), and all subscribers to that topic receive the message — one-to-many communication.

**How it works:**
```
Publisher → [Topic] → Subscriber 1
                    → Subscriber 2
                    → Subscriber 3
```

**Pub/Sub vs Message Queue:**
| Feature | Message Queue | Pub/Sub |
|---|---|---|
| Consumers | One consumer per message | All subscribers get the message |
| Coupling | Point-to-point | Broadcast / fan-out |
| Use case | Task distribution | Event notification |
| Example | SQS | SNS, Kafka topics |

**Key concepts:**
- **Topic:** Named channel publishers send to
- **Subscription:** Consumer's interest in a topic
- **Fan-out:** One message delivered to N subscribers
- **Filtering:** Subscribers can filter messages by attributes

**Use cases:**
- User signup → Pub/Sub → Email service, Analytics service, CRM service all notified
- Order placed → Pub/Sub → Inventory, Shipping, Notification all react
- Real-time dashboards: metrics published → multiple dashboards subscribe
- Event-driven microservices architecture

**Real-world tools:** AWS SNS, Google Pub/Sub, Kafka, Redis Pub/Sub, RabbitMQ (fanout exchange)

**AWS SNS + SQS Fan-out pattern:**
```
SNS Topic (order-events)
    ├── SQS Queue → Inventory Service
    ├── SQS Queue → Shipping Service
    └── SQS Queue → Notification Service
```

**Spring Boot + Kafka example:**
```java
// Publisher
kafkaTemplate.send("order-events", orderEvent);

// Subscriber
@KafkaListener(topics = "order-events", groupId = "inventory-service")
public void handleOrder(OrderEvent event) {
    inventoryService.reserve(event);
}
```

---

## 6. API Gateway

**What it is:**
An API Gateway is a single entry point for all client requests to backend microservices. It handles cross-cutting concerns like routing, authentication, rate limiting, and load balancing.

**How it works:**
```
Client → API Gateway → [Auth] → [Rate Limit] → [Route] → Microservice A
                                                         → Microservice B
                                                         → Microservice C
```

**Core responsibilities:**
| Function | Description |
|---|---|
| Routing | Forward requests to correct microservice |
| Authentication/Authorization | Validate JWT/OAuth tokens centrally |
| Rate Limiting | Throttle requests per client/IP |
| SSL Termination | Handle HTTPS, forward HTTP internally |
| Request/Response Transformation | Modify headers, body format |
| Load Balancing | Distribute across service instances |
| Caching | Cache responses for repeated requests |
| Logging & Monitoring | Centralized access logs, metrics |
| Circuit Breaking | Stop forwarding to unhealthy services |

**API Gateway vs Load Balancer:**
| | API Gateway | Load Balancer |
|---|---|---|
| Layer | Layer 7 (Application) | Layer 4 or 7 |
| Awareness | Business logic aware | Traffic distribution only |
| Features | Auth, rate limit, transform | Health check, routing |
| Example | AWS API Gateway, Kong | AWS ALB, Nginx |

**Real-world tools:** AWS API Gateway, Kong, Nginx, Spring Cloud Gateway, Zuul, Traefik

**Spring Cloud Gateway example:**
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: order-service
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/api/orders/**
          filters:
            - StripPrefix=1
            - name: CircuitBreaker
              args:
                name: orderCircuitBreaker
```

**Benefits:**
- Simplifies client (one endpoint instead of N service URLs)
- Centralized security and cross-cutting concerns
- Enables backend for frontend (BFF) pattern
- Decouples client from internal service topology

---

## 7. Circuit Breaker

**What it is:**
The Circuit Breaker pattern prevents cascading failures in distributed systems by stopping calls to a failing service and providing a fallback response, allowing the service time to recover.

**States:**
```
[CLOSED] → (failure threshold exceeded) → [OPEN] → (wait timeout) → [HALF-OPEN]
   ↑                                                                      |
   └──────────────── (test call succeeds) ──────────────────────────────┘
```

| State | Behavior |
|---|---|
| Closed | Normal operation; requests pass through; failures counted |
| Open | All requests fail fast (no call to service); fallback returned |
| Half-Open | Limited test requests allowed; if success → Closed; if fail → Open |

**Configuration parameters:**
- `failureRateThreshold`: % of failures to trip to OPEN (e.g., 50%)
- `slowCallRateThreshold`: % of slow calls to trip (e.g., 80%)
- `waitDurationInOpenState`: How long to stay OPEN before trying HALF-OPEN (e.g., 30s)
- `slidingWindowSize`: Number of calls to evaluate (e.g., last 10 calls)
- `permittedCallsInHalfOpenState`: Test calls in HALF-OPEN (e.g., 3)

**Spring Boot + Resilience4j:**
```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
public PaymentResponse processPayment(PaymentRequest request) {
    return paymentClient.process(request);
}

public PaymentResponse paymentFallback(PaymentRequest request, Exception ex) {
    return PaymentResponse.pending("Payment service unavailable, will retry");
}
```

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        failureRateThreshold: 50
        waitDurationInOpenState: 30s
        slidingWindowSize: 10
        permittedNumberOfCallsInHalfOpenState: 3
```

**Why it matters:**
- Without circuit breaker: one slow service causes thread pool exhaustion → entire app hangs
- With circuit breaker: failing service is isolated; rest of system continues normally

**Related patterns:** Retry (with backoff), Bulkhead (isolate thread pools), Timeout

---

## 8. Service Discovery

**What it is:**
Service Discovery is the mechanism by which microservices automatically find and communicate with each other without hardcoded IP addresses or ports, since instances are dynamic in cloud environments.

**The problem:**
```
# Static config (bad in cloud)
payment-service.url=192.168.1.10:8080  ← IP changes on restart/scaling
```

**Types:**

**Client-Side Discovery:**
- Client queries Service Registry to get list of instances
- Client performs load balancing and picks an instance
- Example: Netflix Eureka + Ribbon
```
Service A → Eureka (get instances of B) → picks instance → calls Service B
```

**Server-Side Discovery:**
- Client calls Load Balancer/Router
- Router queries registry and forwards request
- Client doesn't know about registry
- Example: AWS ALB + ECS, Kubernetes Service
```
Service A → Load Balancer → (queries registry internally) → Service B instance
```

**Service Registry:**
- Central store of service instances (name, IP, port, health status)
- Services register on startup, deregister on shutdown
- Heartbeat mechanism detects dead instances

**Real-world tools:** Netflix Eureka, Consul, Zookeeper, AWS Cloud Map, Kubernetes DNS

**Spring Boot + Eureka:**
```java
// Eureka Server
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServer { }

// Eureka Client (microservice)
@SpringBootApplication
@EnableDiscoveryClient
public class OrderService { }
```

```yaml
# application.yml
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
spring:
  application:
    name: order-service
```

**Calling another service by name (not IP):**
```java
@LoadBalanced  // Ribbon/Spring Cloud LoadBalancer resolves service name
RestTemplate restTemplate;

restTemplate.getForObject("http://payment-service/api/pay", PaymentResponse.class);
```

---

## 9. Sharding

**What it is:**
Sharding (horizontal partitioning) splits a large database into smaller, faster, more manageable pieces called shards, each holding a subset of the data, distributed across multiple servers.

**Why sharding:**
- Single DB server hits limits: storage, CPU, memory, connections
- Vertical scaling (bigger server) has limits and is expensive
- Sharding scales horizontally — add more servers as data grows

**Sharding strategies:**

**Range-Based Sharding:**
```
Shard 1: user_id 1 - 1,000,000
Shard 2: user_id 1,000,001 - 2,000,000
Shard 3: user_id 2,000,001 - 3,000,000
```
- ✅ Easy range queries
- ❌ Hotspots if data not evenly distributed

**Hash-Based Sharding:**
```
shard = hash(user_id) % number_of_shards
```
- ✅ Even distribution
- ❌ Range queries require hitting all shards
- ❌ Rebalancing when adding shards (use consistent hashing to solve)

**Directory-Based Sharding:**
- Lookup table maps key → shard
- ✅ Flexible, easy to move data
- ❌ Lookup table is a bottleneck/single point of failure

**Geo-Based Sharding:**
- Data partitioned by geography (US users → US shard, EU users → EU shard)
- ✅ Data residency compliance, low latency
- ❌ Uneven load if regions have different traffic

**Challenges:**
| Challenge | Solution |
|---|---|
| Cross-shard joins | Denormalize data, application-level joins |
| Distributed transactions | Saga pattern, avoid cross-shard transactions |
| Rebalancing shards | Consistent hashing minimizes data movement |
| Hotspot keys | Add random suffix to key, split hot shard |
| Schema changes | Apply to all shards, use online schema change tools |

**Sharding vs Partitioning:**
- Partitioning: splitting data within a single DB instance (logical)
- Sharding: splitting data across multiple DB instances (physical)

---

## 10. Rate Limiting

**What it is:**
Rate Limiting controls the number of requests a client can make to an API within a time window, protecting services from abuse, DDoS attacks, and ensuring fair usage.

**Why it matters:**
- Prevents API abuse and scraping
- Protects backend from traffic spikes
- Ensures fair resource allocation across clients
- Reduces infrastructure costs

**Rate limiting algorithms:**

**Token Bucket:**
```
Bucket capacity: 100 tokens
Refill rate: 10 tokens/second
Each request consumes 1 token
If bucket empty → reject request (429)
```
- ✅ Allows bursts up to bucket capacity
- ✅ Smooth average rate
- Most widely used algorithm

**Leaky Bucket:**
```
Requests enter bucket → processed at fixed rate → excess overflows (rejected)
```
- ✅ Smooths out traffic bursts
- ❌ No burst allowance

**Fixed Window Counter:**
```
Window: 0-60 seconds → max 100 requests
Counter resets at 60s boundary
```
- ✅ Simple to implement
- ❌ Boundary spike: 100 requests at 59s + 100 at 61s = 200 in 2 seconds

**Sliding Window Log:**
- Store timestamp of each request
- Count requests in last N seconds
- ✅ Accurate, no boundary spike
- ❌ High memory usage

**Sliding Window Counter:**
- Hybrid of fixed window + sliding calculation
- ✅ Accurate, memory efficient
- Most practical for production

**Implementation with Redis:**
```java
// Atomic increment with expiry (Fixed Window)
Long count = redisTemplate.opsForValue().increment("rate:" + userId);
if (count == 1) redisTemplate.expire("rate:" + userId, 60, TimeUnit.SECONDS);
if (count > 100) throw new RateLimitExceededException();
```

**Rate limit response:**
```
HTTP 429 Too Many Requests
Headers:
  X-RateLimit-Limit: 100
  X-RateLimit-Remaining: 0
  X-RateLimit-Reset: 1700000060
  Retry-After: 30
```

**Rate limiting levels:**
- Per IP address (unauthenticated)
- Per API key / user (authenticated)
- Per endpoint (stricter limits on expensive operations)
- Global (protect entire service)

**Tools:** AWS API Gateway throttling, Kong Rate Limiting plugin, Bucket4j (Java), Resilience4j RateLimiter

---

## 11. Consistent Hashing

**What it is:**
Consistent Hashing is a technique that minimizes data redistribution when nodes are added or removed from a distributed system, solving the rebalancing problem of simple modulo hashing.

**The problem with simple hashing:**
```
shard = hash(key) % N   (N = number of nodes)

If N changes from 3 to 4:
  Almost ALL keys map to different shards → massive data movement
```

**How Consistent Hashing works:**
1. Arrange a virtual ring of hash values (0 to 2³²)
2. Map each server node to a position on the ring using hash(node)
3. Map each key to a position using hash(key)
4. Key is assigned to the first node clockwise from its position

```
Ring: 0 ──────────────────────────── 2³²
         Node A    Node B    Node C
           │         │         │
    Key1──►A    Key2─►B   Key3─►C
```

**Adding a node:**
- New node takes over only the keys between it and its predecessor
- Only ~K/N keys need to move (K = total keys, N = nodes)
- All other keys unaffected

**Removing a node:**
- Only that node's keys move to the next node clockwise
- All other keys unaffected

**Virtual nodes (vnodes):**
- Each physical node maps to multiple positions on the ring (e.g., 150 virtual nodes)
- Ensures even distribution even with heterogeneous nodes
- Allows fine-grained load balancing

```
Node A → positions [45, 190, 320, 500, ...]
Node B → positions [80, 210, 380, 600, ...]
Node C → positions [30, 150, 290, 450, ...]
```

**Use cases:**
- Distributed caches (Memcached, Redis Cluster)
- Database sharding
- Distributed hash tables (DHT)
- Load balancing with session affinity
- Content routing in CDNs

**Real-world usage:**
- Amazon DynamoDB — consistent hashing for partition routing
- Apache Cassandra — token ring with virtual nodes
- Redis Cluster — hash slots (16384 slots distributed across nodes)

**Java implementation concept:**
```java
TreeMap<Long, String> ring = new TreeMap<>();

// Add node
void addNode(String node) {
    for (int i = 0; i < VIRTUAL_NODES; i++) {
        long hash = hash(node + "#" + i);
        ring.put(hash, node);
    }
}

// Get node for key
String getNode(String key) {
    long hash = hash(key);
    Map.Entry<Long, String> entry = ring.ceilingEntry(hash);
    return entry != null ? entry.getValue() : ring.firstEntry().getValue();
}
```

---

## 12. Auto Scaling

**What it is:**
Auto Scaling automatically adjusts the number of compute resources (servers/containers) based on current demand, ensuring performance during peaks and cost efficiency during low traffic.

**Why it matters:**
- Traffic is unpredictable — spikes during sales, events, viral moments
- Over-provisioning wastes money
- Under-provisioning causes outages
- Auto scaling handles both automatically

**Types of Auto Scaling:**

**Horizontal Scaling (Scale Out/In):**
- Add/remove instances (servers, containers, pods)
- ✅ No downtime, unlimited scale
- ✅ Preferred for stateless services
- Example: Add 5 more EC2 instances when CPU > 70%

**Vertical Scaling (Scale Up/Down):**
- Increase/decrease resources of existing instance (CPU, RAM)
- ❌ Requires restart, has upper limit
- Used for stateful services (DBs) or when horizontal isn't possible

**Scaling triggers (metrics):**
| Metric | Example Threshold |
|---|---|
| CPU Utilization | Scale out if > 70% for 5 min |
| Memory Usage | Scale out if > 80% |
| Request Count | Scale out if > 1000 req/sec |
| Queue Depth | Scale out if SQS queue > 100 messages |
| Custom Metrics | Business metrics via CloudWatch |
| Schedule | Scale out every weekday at 9 AM |

**Scaling policies:**

**Target Tracking:** Maintain a target metric value
```
Keep average CPU at 50% → automatically adds/removes instances
```

**Step Scaling:** Different actions at different thresholds
```
CPU 60-70% → add 1 instance
CPU 70-80% → add 2 instances
CPU > 80%  → add 4 instances
```

**Scheduled Scaling:** Pre-planned scaling for known traffic patterns
```
Every Friday 6 PM → scale to 10 instances (weekend traffic)
Every Monday 6 AM → scale back to 3 instances
```

**Predictive Scaling:** ML-based, forecasts future traffic and scales proactively

**AWS Auto Scaling components:**
```
Auto Scaling Group (ASG)
  ├── Launch Template (AMI, instance type, security groups)
  ├── Min capacity: 2
  ├── Max capacity: 20
  ├── Desired capacity: 4 (current)
  └── Scaling Policies (target tracking / step / scheduled)
```

**Kubernetes (HPA - Horizontal Pod Autoscaler):**
```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: order-service-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: order-service
  minReplicas: 2
  maxReplicas: 20
  metrics:
    - type: Resource
      resource:
        name: cpu
        target:
          type: Utilization
          averageUtilization: 70
```

**Best practices:**
- Always set min > 1 for high availability (multi-AZ)
- Use warm-up period — new instances need time to be ready
- Combine with load balancer health checks
- Test scaling policies with load testing before production
- Use lifecycle hooks for graceful startup/shutdown
- Monitor scale-in protection for instances processing long jobs

---
