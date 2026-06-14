
#  These are common interview scenarios focusing on:

- Distributed Tracing (Zipkin, Jaeger, OpenTelemetry)
- Logging (ELK, Splunk)
- Kafka Debugging
- Performance Analysis
- Production Troubleshooting
- Memory & Thread Dump Analysis
- API Gateway Investigation
- Database Connection Issues
- Service Discovery Problems
- End-to-End Request Tracking
- Architect-Level

# 1. Distributed Tracing (Zipkin, Jaeger, OpenTelemetry)

## 1: A user reports that the Order API takes 20 seconds, but each microservice team says their service is fast. How would you identify where the delay occurs?


"I would use distributed tracing tools like Zipkin or Jaeger and follow the Trace ID across all services. The trace timeline shows how much time each service spends processing the request, helping me identify exactly where the delay occurs."


```java
@GetMapping("/orders/{id}")
public Order getOrder(@PathVariable Long id) {
    log.info("Fetching order {}", id);
    return service.getOrder(id);
}
```

The Trace ID is automatically propagated by OpenTelemetry.

---

## 2: A request passes through API Gateway → Order Service → Payment Service → Inventory Service. How would you trace the complete request flow across services?


"I would use the Trace ID generated at the API Gateway. The same Trace ID is propagated to all downstream services, allowing me to view the complete request journey in Zipkin or Jaeger."


```java
@Autowired
private Tracer tracer;

public void processOrder() {
    Span span = tracer.spanBuilder("processOrder").startSpan();
    try {
        // business logic
    } finally {
        span.end();
    }
}
```

---

## 3: Only 5% of requests are failing in production. How would distributed tracing help find the root cause?

"I would filter traces by error status and compare successful and failed requests. This helps identify which service or external dependency is causing failures for that specific 5% of requests."


```java
try {
    paymentClient.pay();
} catch (Exception e) {
    span.recordException(e);
    throw e;
}
```

The exception becomes visible in the trace.

---

## 4: After introducing OpenTelemetry, traces are missing for some services. What would you investigate?

"I would verify OpenTelemetry dependencies, exporter configuration, Trace ID propagation between services, and whether sampling settings are dropping traces."


```yaml
management:
  tracing:
    sampling:
      probability: 1.0
```

This ensures all requests are traced.



# 2. Logging (ELK, Splunk)

## 1: An error occurs in production but developers cannot reproduce it locally. How would you use ELK/Splunk to investigate?


"I would search the application logs in ELK or Splunk using the error message, timestamp, and service name. Then I would analyze stack traces, request details, and related logs around that time to identify the root cause."


```java
try {
    processOrder(orderId);
} catch (Exception e) {
    log.error("Error processing order: {}", orderId, e);
}
```

---

## 2: A customer provides an Order ID and says payment failed. How would you find all related logs?


"I would search ELK or Splunk using the Order ID or Correlation ID. This allows me to track all logs related to that order across multiple services and identify where the failure occurred."


```java
log.info("OrderId={} Payment started", orderId);
log.info("OrderId={} Payment completed", orderId);
```

Example Search:

```text
OrderId=12345
```

---

## 3: Application performance suddenly degrades. What logs would you analyze first?

"I would first check error logs, slow API logs, database query logs, timeout logs, and resource utilization logs. These usually reveal bottlenecks causing performance degradation."


```java
long start = System.currentTimeMillis();

service.process();

log.info("Execution Time={} ms",
         System.currentTimeMillis() - start);
```

This helps identify slow operations.

---

## 4: Log storage grows from 100GB to 1TB within a week. How would you troubleshoot?


"I would identify which application is generating excessive logs, check for debug logging enabled in production, analyze repeated error messages, and review log retention policies."


Bad Example:

```java
log.debug("User details {}", user);
```

Inside a loop:

```java
for(User user : users){
    log.debug("User {}", user);
}
```

This can generate millions of log entries.


# 3. Kafka Debugging

## 1: Messages are being produced but consumers are not receiving them


First, I would verify that messages are actually reaching the Kafka topic. Then I would check whether the consumer is subscribed to the correct topic, using the correct consumer group, and whether there are any offset or connectivity issues. I would also check consumer logs for errors.


```java
@KafkaListener(topics = "orders", groupId = "order-group")
public void consume(String message) {
    System.out.println("Received: " + message);
}
```

**Things to check:**

* Correct topic name
* Correct bootstrap server
* Consumer group configuration
* Consumer logs
* Kafka offsets

---

## 2: Consumer lag suddenly increases during a sale event


Consumer lag usually increases when producers send messages faster than consumers can process them. I would check consumer processing time, database calls, thread count, partition count, and consumer scaling.


Increase consumer concurrency:

```java
@KafkaListener(
    topics = "orders",
    groupId = "order-group",
    concurrency = "5"
)
public void consume(String message) {
    processOrder(message);
}
```

**Possible reasons:**

* High incoming traffic
* Slow database operations
* Insufficient consumer instances
* GC pauses
* Long-running business logic

---

## 3: Duplicate messages are being processed


Kafka provides at-least-once delivery, so duplicates can occur. I would make the consumer idempotent by checking whether the message has already been processed before executing business logic.


```java
public void processOrder(OrderEvent event) {

    if(orderRepository.existsById(event.getOrderId())) {
        return; // already processed
    }

    orderRepository.save(event.toOrder());
}
```

**Common causes:**

* Consumer crash before offset commit
* Rebalancing
* Retry mechanism
* Network failures

---

## 4: One Kafka partition receives significantly more traffic than others


This is usually called partition skew. I would check the partition key being used. If most messages have the same key, Kafka sends them to the same partition. I would choose a better partition key or implement a custom partitioning strategy.


Problematic:

```java
kafkaTemplate.send(
    "orders",
    "CUSTOMER_1",   // same key always
    message
);
```

Better:

```java
kafkaTemplate.send(
    "orders",
    order.getOrderId(), // distributes traffic
    message
);
```

**Custom Partitioner Example**

```java
public class OrderPartitioner implements Partitioner {

    @Override
    public int partition(
            String topic,
            Object key,
            byte[] keyBytes,
            Object value,
            byte[] valueBytes,
            Cluster cluster) {

        return Math.abs(key.hashCode())
                % cluster.partitionCountForTopic(topic);
    }
}
```

**Things to check:**

* Message key distribution
* Number of partitions
* Consumer load per partition
* Producer partitioning strategy



# 4. Performance Analysis

## 1: API response time increased from 200ms to 5 seconds after deployment


First, I would compare the new release with the previous version. Then I would check application logs, distributed traces, database queries, external API calls, and JVM metrics. I would use profiling and tracing tools to identify where most of the response time is spent.


```java
long start = System.currentTimeMillis();

Order order = orderService.getOrder(id);

long end = System.currentTimeMillis();

log.info("Execution Time: {} ms", (end - start));
```

**Things to check:**

* Slow SQL queries
* External API latency
* New code changes
* Thread pool saturation
* GC activity

---

## 2: CPU usage reaches 95% during peak traffic


I would check JVM metrics, thread count, GC activity, database calls, and thread dumps. High CPU is often caused by excessive looping, too many threads, heavy computations, or frequent garbage collection.


```java
ThreadMXBean bean =
        ManagementFactory.getThreadMXBean();

System.out.println(
        "Thread Count: " +
        bean.getThreadCount()
);
```

**Metrics to check:**

* CPU utilization
* Thread count
* GC pause time
* Request throughput
* Response time
* Error rate

---

## 3: Application works fine in QA but becomes slow in Production


I would compare QA and Production environments, including data volume, traffic load, JVM settings, database size, indexes, and infrastructure resources. Most performance issues appear only when production data volume is much larger.


```java
@Timed(value = "order.api.time")
@GetMapping("/orders/{id}")
public Order getOrder(@PathVariable Long id) {
    return service.findById(id);
}
```

**Things to compare:**

* Database size
* JVM heap settings
* CPU and memory
* Network latency
* Number of users
* Cache configuration

---

## 4: A specific endpoint is slow only for large datasets


I would analyze database queries, pagination, indexing, memory usage, and algorithm complexity. Large datasets often reveal N+1 query problems, full table scans, or inefficient loops.


Bad:

```java
List<Orderorders =
        orderRepository.findAll();
```

Better:

```java
Page<Orderorders =
        orderRepository.findAll(
            PageRequest.of(0, 100)
        );
```

**Things to analyze:**

* Query execution plan
* Missing indexes
* Pagination
* N+1 queries
* Memory consumption
* Time complexity



# 5. Production Troubleshooting

## 1: Production application suddenly becomes unavailable at midnight every day


First, I would check scheduled jobs, batch processes, backups, log rotation, and database maintenance tasks running at midnight. Then I would analyze application logs, CPU, memory, thread dumps, and monitoring dashboards to identify what happens at that exact time.


```java
@Scheduled(cron = "0 0 0 * * ?")
public void nightlyJob() {
    log.info("Running nightly job...");
}
```

**Things to check:**

* Scheduled jobs
* Database backup jobs
* Server restart scripts
* Log rotation
* Resource spikes

---

## 2: Users receive intermittent 500 errors


I would first identify the affected APIs and check application logs for exceptions. Then I would analyze request patterns, database connectivity, external service failures, timeout issues, and distributed traces to find the root cause.


```java
@GetMapping("/orders/{id}")
public Order getOrder(@PathVariable Long id) {

    return orderService.findById(id);
}
```

Global exception handler:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Stringhandle(Exception ex) {
        log.error("Error occurred", ex);
        return ResponseEntity.internalServerError().body("Error");
    }
}
```

**Things to check:**

* Application logs
* Database errors
* Timeout exceptions
* External API failures
* Resource exhaustion

---

## 3: One microservice crashes repeatedly while others remain healthy


I would analyze the service logs, heap usage, CPU metrics, container events, and thread dumps. Common reasons are OutOfMemoryError, unhandled exceptions, configuration issues, or dependency failures affecting only that service.


```java
try {
    processOrder();
} catch (Exception ex) {
    log.error("Processing failed", ex);
}
```

Example OOM check:

```java
Runtime runtime = Runtime.getRuntime();

System.out.println(
    "Used Memory: " +
    (runtime.totalMemory() -
     runtime.freeMemory())
);
```

**Things to check:**

* OutOfMemoryError
* Container restart logs
* JVM crashes
* Dependency failures
* Configuration issues

---

## 4: After deployment, response times increase significantly


First, I would compare the new deployment with the previous version. Then I would check slow queries, external API calls, thread pools, cache performance, and distributed traces to identify what changed after deployment.


```java
long start = System.currentTimeMillis();

service.processOrder();

long end = System.currentTimeMillis();

log.info("Execution Time: {} ms",
        (end - start));
```

**Things to check first:**

* Recent code changes
* Database query performance
* Cache misses
* Thread pool utilization
* API latency
* JVM metrics


# 6. Memory & Thread Dump Analysis

## 1: Application memory usage continuously increases and never comes down


I would suspect a memory leak. First, I would monitor heap usage and GC behavior. Then I would capture a heap dump and analyze it using tools like Eclipse MAT to identify objects that are continuously growing and not being garbage collected.


Memory Monitoring:

```java
Runtime runtime = Runtime.getRuntime();

long usedMemory =
        runtime.totalMemory()
        - runtime.freeMemory();

System.out.println(
        "Used Memory: " + usedMemory
);
```

Memory Leak Example:

```java
public class MemoryLeak {

    private static List<Stringcache =
            new ArrayList<>();

    public void addData() {
        cache.add("Data");
    }
}
```

**Things to check:**

* Heap Dump
* GC Logs
* Static Collections
* Cache Growth
* ThreadLocal Leaks

---

## 2: CPU is low, but application requests are hanging


If CPU is low but requests are stuck, I would take a thread dump and look for threads in BLOCKED, WAITING, or TIMED_WAITING states. This usually indicates lock contention, deadlocks, database waits, or external service calls.


Generate Thread Dump:

```bash
jstack <PIDthreaddump.txt
```

Blocked Thread Example:

```java
synchronized(lock) {
    Thread.sleep(100000);
}
```

**Thread States to Check:**

* BLOCKED
* WAITING
* TIMED_WAITING
* Deadlocks
* Database Connection Waits

---

## 3: Users report APIs stop responding randomly


I would capture multiple thread dumps during the issue and compare them. If the same threads are stuck repeatedly, I can identify deadlocks, long-running queries, thread pool exhaustion, or external API waits.


Thread Pool Example:

```java
ExecutorService executor =
        Executors.newFixedThreadPool(5);
```

If all threads are busy:

```java
for(int i=0; i<1000; i++) {
    executor.submit(() -process());
}
```

**Things to Analyze:**

* Stuck Threads
* Deadlocks
* Thread Pool Exhaustion
* Database Calls
* External Service Calls

---

## 4: You find thousands of threads running in production


Thousands of threads usually indicate thread leaks, excessive thread creation, or misconfigured thread pools. I would check thread dumps, application code, executor services, and thread pool configurations.


Bad Practice:

```java
while(true) {
    new Thread(() -process()).start();
}
```

Better:

```java
ExecutorService executor =
        Executors.newFixedThreadPool(20);

executor.submit(() -process());
```

Check Thread Count:

```java
ThreadMXBean bean =
        ManagementFactory.getThreadMXBean();

System.out.println(
        bean.getThreadCount()
);
```

**Possible Causes:**

* Thread Leak
* Infinite Thread Creation
* Unclosed Executors
* Blocking Operations
* Misconfigured Thread Pools



# 7. API Gateway Investigation

## 1: All backend services are healthy, but users receive 502/504 errors


First, I would check API Gateway logs and metrics. A 502 usually indicates a bad response from the backend, while a 504 indicates a timeout. I would verify routing, network connectivity, timeout settings, and load balancer configurations.


Gateway Timeout Configuration:

```yaml
spring:
  cloud:
    gateway:
      httpclient:
        connect-timeout: 5000
        response-timeout: 10s
```

**Things to check:**

* Gateway logs
* Timeout settings
* Load balancer
* Network issues
* Service response time

---

## 2: A request reaches the Gateway but never reaches the target service


I would verify route configuration, service discovery registration, load balancing, and Gateway filters. Then I would trace the request using logs and distributed tracing to find where it stops.


Route Configuration:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: order-service
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/orders/**
```

Logging Filter:

```java
@Component
public class LoggingFilter implements GlobalFilter {

    public Mono<Voidfilter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        log.info("Request: {}",
                 exchange.getRequest().getURI());

        return chain.filter(exchange);
    }
}
```

**Things to check:**

* Route mapping
* Eureka registration
* Gateway filters
* Service discovery
* Firewall/network rules

---

## 3: Authentication works directly against the service but fails through the Gateway


I would check whether the Authorization header is being forwarded correctly by the Gateway. Then I would verify JWT validation, security filters, token expiration, and gateway authentication configuration.


Forward Authorization Header:

```java
@Bean
public RouteLocator routes(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("order-service",
            r -r.path("/orders/**")
            .uri("lb://ORDER-SERVICE"))
        .build();
}
```

Read JWT Token:

```java
String authHeader =
    request.getHeader("Authorization");
```

**Things to check:**

* Authorization header
* JWT validation
* Gateway security filters
* Token expiration
* OAuth configuration

---

## 4: Gateway latency is higher than service latency


If the service responds in 100ms but Gateway takes 500ms, I would investigate authentication, rate limiting, logging filters, network hops, and load balancer delays. Gateway processing itself may be causing the latency.


Measure Gateway Processing Time:

```java
long start = System.currentTimeMillis();

return chain.filter(exchange)
    .then(Mono.fromRunnable(() -{
        long end =
            System.currentTimeMillis();

        log.info("Gateway Time: {} ms",
                (end - start));
    }));
```

**Possible causes:**

* JWT validation overhead
* Rate limiting filters
* Logging filters
* Load balancer delays
* Network latency
* Service discovery delays



# 8. Database Connection Issues

## 1: Application throws "Cannot get JDBC connection" errors intermittently


First, I would check the connection pool metrics and database availability. Then I would verify whether connections are being leaked, long-running transactions are holding connections, or the pool size is too small for the workload.


HikariCP Configuration:

```java
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.connection-timeout=30000
```

Using try-with-resources:

```java
try (Connection conn = dataSource.getConnection()) {
    // Database operations
}
```

**Things to check:**

* Connection pool usage
* Database availability
* Connection leaks
* Long-running queries
* Pool size configuration

---

## 2: Connection pool becomes exhausted during peak traffic


Connection pool exhaustion usually happens when requests are holding database connections for too long or when traffic exceeds the configured pool size. I would check slow queries, transaction duration, and pool metrics.


Bad Practice:

```java
Connection conn = dataSource.getConnection();

// Long processing
Thread.sleep(10000);

conn.close();
```

Better:

```java
try (Connection conn = dataSource.getConnection()) {

    // Quick DB work

}
```

**Possible causes:**

* Slow SQL queries
* Long transactions
* Connection leaks
* Small pool size
* Traffic spikes

---

## 3: Database CPU is normal, but API response times are very slow


If database CPU is normal, I would investigate connection wait time, network latency, lock contention, slow external services, and application thread pools. The issue may not be database processing itself.


Measure Query Time:

```java
long start = System.currentTimeMillis();

repository.findById(id);

long end = System.currentTimeMillis();

log.info("DB Time: {} ms",
        (end - start));
```

**Things to investigate:**

* Connection acquisition time
* Lock waits
* Network latency
* Thread pool exhaustion
* External API calls

---

## 4: After deployment, database connections continuously increase and never decrease


This usually indicates a connection leak. The new code may be opening connections without closing them. I would compare the deployment changes, review database access code, and monitor active connections in the pool.


Connection Leak Example:

```java
public void saveOrder() throws Exception {

    Connection conn =
        dataSource.getConnection();

    // Missing conn.close()
}
```

Correct Version:

```java
public void saveOrder() throws Exception {

    try (Connection conn =
            dataSource.getConnection()) {

        // Database operations
    }
}
```

Monitor Pool Usage:

```java
HikariDataSource ds =
    (HikariDataSource) dataSource;

System.out.println(
    ds.getHikariPoolMXBean()
      .getActiveConnections()
);
```

**Things to check:**

* Connection leaks
* New deployment changes
* Unclosed ResultSet/Statement
* Transaction handling
* Pool metrics



# 9. Service Discovery Problems

## 1: A service registers successfully in Eureka but cannot be discovered by other services


First, I would verify that the service is registered in Eureka and appears as UP. Then I would check the service name, Eureka client configuration, network connectivity, and load balancer configuration. Sometimes service name mismatches cause discovery failures.


Service Registration:

```java
@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication {
}
```

Discovery Client:

```java
@Autowired
private DiscoveryClient discoveryClient;

public void checkService() {
    System.out.println(
        discoveryClient.getInstances("ORDER-SERVICE")
    );
}
```

**Things to check:**

* Service name mismatch
* Eureka registration status
* Network connectivity
* Load balancer configuration
* Eureka client logs

---

## 2: Some requests fail because traffic is routed to unhealthy instances


I would check health check configurations and Eureka heartbeat updates. If unhealthy instances are still marked UP, traffic may continue routing to them. I would verify health endpoints and instance status updates.


Health Endpoint:

```java
@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}
```

Spring Boot Actuator:

```yaml
management:
  endpoint:
    health:
      show-details: always
```

**Things to check:**

* Health check configuration
* Heartbeat intervals
* Eureka instance status
* Load balancer cache
* Failed instance removal

---

## 3: A newly deployed service never appears in the service registry


I would verify Eureka client configuration, network access to the Eureka server, startup logs, and registration settings. Usually incorrect Eureka URLs or network issues prevent registration.


Eureka Configuration:

```yaml
eureka:
  client:
    service-url:
      defaultZone:
        http://localhost:8761/eureka/
```

Service Name:

```yaml
spring:
  application:
    name: PAYMENT-SERVICE
```

**Things to check:**

* Eureka server URL
* Network/firewall rules
* Service startup logs
* Application name
* Eureka client enabled

---

## 4: Inter-service communication works locally but fails in Kubernetes


I would verify Kubernetes service names, DNS resolution, namespaces, and service exposure. Most issues are caused by incorrect service names, namespace mismatches, or DNS problems.


Kubernetes Service:

```yaml
apiVersion: v1
kind: Service
metadata:
  name: order-service
spec:
  selector:
    app: order-service
```

Service Call:

```java
String url =
    "http://order-service/api/orders";
```

DNS Check:

```bash
kubectl exec -it pod-name -- nslookup order-service
```

**Things to check:**

* Service name
* Namespace
* DNS resolution
* Pod health
* Service endpoints



# 10. End-to-End Request Tracking

## 1: A customer reports that an order was placed but payment was not completed


I would use the Correlation ID or Trace ID to track the request across all services. I would check logs, distributed traces, Kafka events, and database records to identify where the process stopped.


Generate Correlation ID:

```java
String correlationId =
        UUID.randomUUID().toString();
```

Pass in Header:

```java
headers.add(
    "X-Correlation-ID",
    correlationId
);
```

Log Correlation ID:

```java
log.info(
    "Order Created, CorrelationId={}",
    correlationId
);
```

**Things to check:**

* Order Service logs
* Payment Service logs
* Kafka events
* Database records
* Distributed traces

---

## 2: An API call passes through 8 microservices


I would use distributed tracing tools like Zipkin or OpenTelemetry. The trace shows every service involved and the response time of each service, making it easy to identify where the failure occurred.


Add Trace Context:

```java
@GetMapping("/orders")
public String getOrders() {

    log.info("Processing Order");

    return "Success";
}
```

Trace Example:

```text
Gateway
   ↓
Order Service
   ↓
Inventory Service
   ↓
Payment Service ❌
```

**Things to check:**

* Trace ID
* Failed span
* Exception details
* Service response times
* Downstream dependencies

---

## 3: One transaction takes 30 seconds while similar transactions take 2 seconds


I would compare the slow trace with a normal trace. Then I would identify which service or database query consumed most of the time and investigate that component.


Measure Execution Time:

```java
long start =
        System.currentTimeMillis();

processOrder();

long end =
        System.currentTimeMillis();

log.info(
    "Execution Time={}ms",
    (end - start)
);
```

Slow Query Example:

```java
List<Orderorders =
        repository.findAll();
```

Better:

```java
Page<Orderorders =
        repository.findAll(
            PageRequest.of(0, 100)
        );
```

**Things to check:**

* Slow database queries
* External API calls
* Kafka delays
* Network latency
* Thread pool waits

---

## 4: You need to correlate logs, traces, and metrics for a failed transaction


I would use a common Correlation ID or Trace ID across logs, traces, and monitoring systems. This allows me to connect metrics, logs, and distributed traces for the same transaction.


MDC Logging:

```java
MDC.put(
    "correlationId",
    correlationId
);

log.info("Order Processing");
```

Retrieve Correlation ID:

```java
String correlationId =
        MDC.get("correlationId");
```

Sample Log:

```text
2026-06-05 INFO
CorrelationId=12345
Order Created
```

**Things to correlate:**

* Application logs
* Distributed traces
* Prometheus metrics
* Kafka events
* Database transactions


# Architect-Level Bonus Scenarios

## 1: Black Friday traffic is expected to increase by 10x


I would scale horizontally by adding more service instances, use caching with Redis, enable auto-scaling, optimize database queries, use read replicas, and process non-critical tasks asynchronously through Kafka.


Caching with Redis:

```java
@Cacheable("products")
public Product getProduct(Long id) {
    return repository.findById(id).orElse(null);
}
```

Async Processing:

```java
@Async
public void sendEmail(Order order) {
    emailService.send(order);
}
```

**Architecture Changes:**

* Auto Scaling
* Redis Cache
* Kafka Async Processing
* Load Balancer
* Database Read Replicas
* CDN

---

## 2: A critical service becomes unavailable


I would implement high availability using multiple service instances, load balancing, circuit breakers, and disaster recovery in another region. If one instance fails, traffic automatically moves to healthy instances.


Circuit Breaker:

```java
@CircuitBreaker(name = "paymentService",
                fallbackMethod = "fallback")
public String processPayment() {
    return paymentClient.pay();
}

public String fallback(Exception ex) {
    return "Payment Service Unavailable";
}
```

Kubernetes Deployment:

```yaml
replicas: 3
```

**Failover Strategy:**

* Multiple Instances
* Load Balancer
* Circuit Breaker
* Multi-Region Deployment
* Automated Recovery

---

## 3: A database becomes the system bottleneck


I would first optimize queries and indexes. Then I would introduce caching, read replicas, database partitioning, and eventually sharding if the data volume continues growing.


Pagination:

```java
Page<Orderorders =
    repository.findAll(
        PageRequest.of(0, 100)
    );
```

Read Replica Example:

```java
@Transactional(readOnly = true)
public Order getOrder(Long id) {
    return repository.findById(id)
                     .orElse(null);
}
```

**Scaling Strategies:**

* Query Optimization
* Indexing
* Redis Cache
* Read Replicas
* Partitioning
* Sharding

---

## 4: A distributed transaction spans 5 microservices


I would use the Saga Pattern instead of a distributed database transaction. Each service performs a local transaction and publishes an event. If a failure occurs, compensating transactions roll back previous actions. For traceability, I would propagate a Correlation ID across all services.


Order Created Event:

```java
OrderCreatedEvent event =
    new OrderCreatedEvent(orderId);

kafkaTemplate.send(
    "order-events",
    event
);
```

Saga Flow:

```text
Order Service
      ↓
Payment Service
      ↓
Inventory Service
      ↓
Shipping Service
      ↓
Notification Service
```

Correlation ID:

```java
String traceId =
    UUID.randomUUID().toString();

MDC.put("traceId", traceId);
```

**Consistency Strategy:**

* Saga Pattern
* Kafka Events
* Compensating Transactions
* Correlation IDs
* Distributed Tracing
