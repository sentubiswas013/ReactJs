# ✅ 19) Monitoring, Logging, Observability & Debugging

## 223. What are the three pillars of observability?

**Logs**: Detailed event records of what happened.

**Metrics**: Numerical measurements over time (CPU, memory, request count).

**Traces**: Request flow across distributed services.

**Example:**
```java
// Logs
log.info("User {} logged in", userId);

// Metrics
meterRegistry.counter("user.login.count").increment();

// Traces
@NewSpan
public void processOrder(String orderId) {
    // Distributed trace created
}
```

---

## 224. How do you implement logging in Spring Boot?

Spring Boot uses SLF4J with Logback by default. Use @Slf4j annotation or LoggerFactory.

**Example:**
```java
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    public void createUser(String name) {
        log.info("Creating user: {}", name);
        log.debug("User details: {}", name);
        log.error("Failed to create user", exception);
    }
}
```

```properties
# application.properties
logging.level.com.example=DEBUG
logging.file.name=app.log
```

---

## 225. Explain logging levels (DEBUG, INFO, WARN, ERROR).

**DEBUG**: Detailed diagnostic info for development.

**INFO**: General informational messages about app flow.

**WARN**: Potentially harmful situations that aren't errors.

**ERROR**: Error events that might still allow app to continue.

**Example:**
```java
@Slf4j
public class OrderService {
    public void processOrder(Order order) {
        log.debug("Processing order: {}", order.getId());
        log.info("Order {} placed successfully", order.getId());
        log.warn("Order {} has low stock", order.getId());
        log.error("Failed to process order {}", order.getId(), exception);
    }
}
```

---

## 226. What is structured logging?

Logging in structured format (JSON) instead of plain text for easier parsing and analysis.

**Example:**
```java
// Plain text log
log.info("User john logged in from IP 192.168.1.1");

// Structured log (JSON)
log.info("User login", 
    kv("userId", "john"),
    kv("ip", "192.168.1.1"),
    kv("timestamp", Instant.now())
);
```

```json
// Output
{
  "timestamp": "2024-01-15T10:30:00Z",
  "level": "INFO",
  "message": "User login",
  "userId": "john",
  "ip": "192.168.1.1"
}
```

---

## 227. What is correlation ID and how to implement it?

Unique identifier passed through all services in a request chain to trace the entire flow.

**Example:**
```java
@Component
public class CorrelationIdFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain chain) {
        String correlationId = request.getHeader("X-Correlation-ID");
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        MDC.put("correlationId", correlationId);
        response.setHeader("X-Correlation-ID", correlationId);
        chain.doFilter(request, response);
        MDC.clear();
    }
}
```

```java
// Logs automatically include correlationId
log.info("Processing request"); // [correlationId=abc-123] Processing request
```

---

## 228. What is distributed tracing (Zipkin, Jaeger)?

Tracks requests as they flow through multiple microservices, showing latency and dependencies.

**Example:**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

```properties
# application.properties
spring.zipkin.base-url=http://localhost:9411
spring.sleuth.sampler.probability=1.0
```

```java
@RestController
public class OrderController {
    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable String id) {
        // Automatically traced across services
        return orderService.findById(id);
    }
}
```

**Zipkin UI shows**: Service A → Service B → Database (with timing for each hop)

---

## 229. What tools do you use for monitoring (Prometheus, Grafana)?

**Prometheus**: Collects and stores metrics as time-series data.

**Grafana**: Visualizes metrics with dashboards and alerts.

**Example:**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

```properties
# application.properties
management.endpoints.web.exposure.include=prometheus
management.metrics.export.prometheus.enabled=true
```

```java
@RestController
public class MetricsController {
    private final Counter orderCounter;
    
    public MetricsController(MeterRegistry registry) {
        this.orderCounter = registry.counter("orders.created");
    }
    
    @PostMapping("/order")
    public void createOrder() {
        orderCounter.increment();
    }
}
```

**Prometheus scrapes**: `http://localhost:8080/actuator/prometheus`

---

## 230. What metrics should you monitor in production?

**Application**: Request rate, error rate, response time, active users.

**System**: CPU, memory, disk, network usage.

**Business**: Orders/sec, revenue, conversion rate.

**Example:**
```java
@Component
public class ApplicationMetrics {
    private final MeterRegistry registry;
    
    public ApplicationMetrics(MeterRegistry registry) {
        this.registry = registry;
        // JVM metrics
        registry.gauge("jvm.memory.used", Runtime.getRuntime().totalMemory());
        
        // Business metrics
        registry.counter("orders.total");
        registry.timer("api.response.time");
        
        // Error rate
        registry.counter("errors.count");
    }
}
```

---

## 231. How do you analyze thread dumps and heap dumps?

**Thread dump**: Shows all threads and their states to identify deadlocks or blocked threads.

**Heap dump**: Shows memory usage to identify memory leaks.

**Example:**
```bash
# Generate thread dump
jstack <pid> > thread_dump.txt

# Generate heap dump
jmap -dump:live,format=b,file=heap.bin <pid>

# Analyze with tools
# Thread dump: Look for BLOCKED, WAITING states
# Heap dump: Use Eclipse MAT, VisualVM
```

```java
// Thread dump analysis
"Thread-1" #12 prio=5 os_prio=0 waiting on condition
   java.lang.Thread.State: WAITING (parking)
   
"Thread-2" #13 prio=5 os_prio=0 waiting for monitor entry
   java.lang.Thread.State: BLOCKED (on object monitor)
```

---

## 232. What is ELK stack?

**E**lasticsearch: Stores and indexes logs.

**L**ogstash: Collects, processes, and forwards logs.

**K**ibana: Visualizes logs with dashboards.

**Example:**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
</dependency>
```

```xml
<!-- logback-spring.xml -->
<appender name="LOGSTASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
    <destination>localhost:5000</destination>
    <encoder class="net.logstash.logback.encoder.LogstashEncoder"/>
</appender>
```

```java
@Slf4j
@RestController
public class UserController {
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        log.info("Fetching user: {}", id);
        return userService.findById(id);
    }
}
```