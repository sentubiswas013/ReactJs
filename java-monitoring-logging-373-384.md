# 🔵 25. Monitoring and Logging

# 🔹 Logging

### Question 373: What is application monitoring?

**Answer (35 seconds):**
* Continuous observation of application performance, health, and behavior in production
* **Real-time Metrics**: CPU, memory, response times, error rates
* **Business Metrics**: User activity, transaction volumes, conversion rates
* **Alerting**: Proactive notifications when issues occur
* **Dashboards**: Visual representation of system health
* **Tools**: Prometheus, Grafana, New Relic, DataDog, AppDynamics
* **Observability**: Logs, metrics, and traces for complete visibility

```java
// Application monitoring with Micrometer
@RestController
public class UserController {
    
    private final MeterRegistry meterRegistry;
    private final Counter userCreationCounter;
    private final Timer responseTimer;
    
    public UserController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.userCreationCounter = Counter.builder("users.created")
            .description("Number of users created")
            .register(meterRegistry);
        this.responseTimer = Timer.builder("api.response.time")
            .register(meterRegistry);
    }
    
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return responseTimer.recordCallable(() -> {
            User created = userService.create(user);
            userCreationCounter.increment();
            return created;
        });
    }
}
```

---

### Question 374: What is logging framework?

**Answer (30 seconds):**
* Library that provides structured way to record application events and messages
* **Abstraction**: Separates logging API from implementation
* **Levels**: DEBUG, INFO, WARN, ERROR for message categorization
* **Appenders**: Output destinations (console, file, database)
* **Formatters**: Control log message format and structure
* **Configuration**: External configuration for runtime behavior
* **Popular Frameworks**: SLF4J, Log4j, Logback, java.util.logging

```java
// Logging framework usage
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    public User createUser(User user) {
        logger.info("Creating user with email: {}", user.getEmail());
        
        try {
            User created = userRepository.save(user);
            logger.info("User created successfully with ID: {}", created.getId());
            return created;
        } catch (Exception e) {
            logger.error("Failed to create user: {}", user.getEmail(), e);
            throw new UserCreationException("User creation failed", e);
        }
    }
}
```

---

### Question 375: What is Log4j?

**Answer (30 seconds):**
* Popular Java logging framework developed by Apache Software Foundation
* **Hierarchical Loggers**: Logger hierarchy based on package structure
* **Appenders**: Multiple output destinations (file, console, database, JMS)
* **Layouts**: Flexible message formatting (PatternLayout, XMLLayout)
* **Configuration**: XML, JSON, YAML, or properties files
* **Performance**: Asynchronous logging for high-performance applications
* **Version 2**: Complete rewrite with better performance and features

```xml
<!-- log4j2.xml configuration -->
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
        <File name="FileAppender" fileName="logs/application.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"/>
        </File>
    </Appenders>
    
    <Loggers>
        <Logger name="com.example" level="DEBUG"/>
        <Root level="INFO">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="FileAppender"/>
        </Root>
    </Loggers>
</Configuration>
```

---

### Question 376: What is SLF4J?

**Answer (30 seconds):**
* Simple Logging Facade for Java - abstraction layer for logging frameworks
* **Facade Pattern**: Provides common API regardless of underlying implementation
* **Binding**: Runtime binding to actual logging framework (Log4j, Logback)
* **Performance**: Parameterized messages avoid string concatenation
* **Flexibility**: Switch logging implementations without code changes
* **MDC**: Mapped Diagnostic Context for contextual logging
* **Industry Standard**: De facto standard for Java logging

```java
// SLF4J usage
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Service
public class OrderService {
    
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    
    public Order processOrder(Order order) {
        // Add context to all log messages in this thread
        MDC.put("orderId", order.getId().toString());
        MDC.put("userId", order.getUserId().toString());
        
        try {
            logger.info("Processing order for user: {}", order.getUserId());
            
            // Parameterized logging - efficient
            logger.debug("Order details: amount={}, items={}", 
                order.getAmount(), order.getItems().size());
            
            Order processed = processOrderInternal(order);
            logger.info("Order processed successfully");
            return processed;
            
        } finally {
            MDC.clear(); // Clean up context
        }
    }
}
```

---

### Question 377: What is Logback?

**Answer (30 seconds):**
* Native implementation of SLF4J API, successor to Log4j 1.x
* **Performance**: Faster than Log4j with smaller memory footprint
* **Configuration**: Automatic configuration reload without restart
* **Groovy Config**: Powerful configuration using Groovy scripts
* **Conditional Processing**: Conditional logging based on runtime conditions
* **Compression**: Automatic log file compression and archival
* **Spring Boot Default**: Default logging framework in Spring Boot

```xml
<!-- logback-spring.xml configuration -->
<configuration>
    <springProfile name="dev">
        <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
            </encoder>
        </appender>
        <root level="DEBUG">
            <appender-ref ref="CONSOLE"/>
        </root>
    </springProfile>
    
    <springProfile name="prod">
        <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <file>logs/application.log</file>
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <fileNamePattern>logs/application.%d{yyyy-MM-dd}.%i.gz</fileNamePattern>
                <maxFileSize>100MB</maxFileSize>
                <maxHistory>30</maxHistory>
            </rollingPolicy>
            <encoder>
                <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
            </encoder>
        </appender>
        <root level="INFO">
            <appender-ref ref="FILE"/>
        </root>
    </springProfile>
</configuration>
```

---

### Question 378: What is structured logging?

**Answer (35 seconds):**
* Logging approach that produces machine-readable, consistent log format
* **JSON Format**: Logs as JSON objects for easy parsing
* **Key-Value Pairs**: Structured data instead of free-form text
* **Searchability**: Easy to search and filter in log aggregation tools
* **Correlation**: Include correlation IDs for request tracing
* **Metadata**: Rich context information (user ID, session, timestamp)
* **Tools**: ELK Stack, Splunk can easily parse structured logs

```java
// Structured logging with Logstash encoder
import net.logstash.logback.argument.StructuredArguments;

@Service
public class PaymentService {
    
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    
    public PaymentResult processPayment(Payment payment) {
        // Structured logging with key-value pairs
        logger.info("Processing payment",
            StructuredArguments.kv("paymentId", payment.getId()),
            StructuredArguments.kv("amount", payment.getAmount()),
            StructuredArguments.kv("currency", payment.getCurrency()),
            StructuredArguments.kv("userId", payment.getUserId()));
        
        try {
            PaymentResult result = paymentGateway.process(payment);
            
            logger.info("Payment processed",
                StructuredArguments.kv("paymentId", payment.getId()),
                StructuredArguments.kv("status", result.getStatus()),
                StructuredArguments.kv("transactionId", result.getTransactionId()));
            
            return result;
        } catch (PaymentException e) {
            logger.error("Payment failed",
                StructuredArguments.kv("paymentId", payment.getId()),
                StructuredArguments.kv("errorCode", e.getErrorCode()),
                StructuredArguments.kv("errorMessage", e.getMessage()));
            throw e;
        }
    }
}
```

---

### Question 379: What is centralized logging?

**Answer (35 seconds):**
* Collecting logs from multiple applications/servers into single location
* **Aggregation**: Combine logs from distributed systems
* **Correlation**: Track requests across multiple services
* **Search**: Unified search across all application logs
* **Retention**: Centralized log retention and archival policies
* **Security**: Secure log transmission and storage
* **Tools**: ELK Stack (Elasticsearch, Logstash, Kibana), Fluentd, Splunk

```yaml
# Docker Compose with centralized logging
version: '3'
services:
  app1:
    image: myapp:latest
    logging:
      driver: "fluentd"
      options:
        fluentd-address: localhost:24224
        tag: app1
        
  app2:
    image: myapp2:latest
    logging:
      driver: "fluentd"
      options:
        fluentd-address: localhost:24224
        tag: app2
        
  fluentd:
    image: fluent/fluentd:latest
    ports:
      - "24224:24224"
    volumes:
      - ./fluentd.conf:/fluentd/etc/fluent.conf
      
  elasticsearch:
    image: elasticsearch:7.9.0
    
  kibana:
    image: kibana:7.9.0
    ports:
      - "5601:5601"
```

```java
// Application configuration for centralized logging
@Configuration
public class LoggingConfig {
    
    @Bean
    public Logger structuredLogger() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        
        // Add correlation ID to all logs
        context.putProperty("service.name", "user-service");
        context.putProperty("service.version", "1.0.0");
        
        return context.getLogger("STRUCTURED");
    }
}
```

# 🔹 Monitoring

### Question 380: What is metrics collection?

**Answer (35 seconds):**
* Systematic gathering of quantitative data about application performance
* **System Metrics**: CPU, memory, disk, network utilization
* **Application Metrics**: Response times, error rates, throughput
* **Business Metrics**: User registrations, orders, revenue
* **Custom Metrics**: Domain-specific measurements
* **Time Series**: Data points collected over time for trend analysis
* **Tools**: Micrometer, Prometheus, InfluxDB, CloudWatch

```java
// Metrics collection with Micrometer
@Component
public class MetricsCollector {
    
    private final MeterRegistry meterRegistry;
    private final Counter orderCounter;
    private final Timer orderProcessingTimer;
    private final Gauge activeUsers;
    
    public MetricsCollector(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        
        // Counter for total orders
        this.orderCounter = Counter.builder("orders.total")
            .description("Total number of orders")
            .tag("status", "created")
            .register(meterRegistry);
            
        // Timer for processing duration
        this.orderProcessingTimer = Timer.builder("orders.processing.time")
            .description("Order processing time")
            .register(meterRegistry);
            
        // Gauge for active users
        this.activeUsers = Gauge.builder("users.active")
            .description("Number of active users")
            .register(meterRegistry, this, MetricsCollector::getActiveUserCount);
    }
    
    public void recordOrderCreated() {
        orderCounter.increment();
    }
    
    public void recordOrderProcessingTime(Duration duration) {
        orderProcessingTimer.record(duration);
    }
    
    private double getActiveUserCount() {
        return userService.getActiveUserCount();
    }
}
```

---

### Question 381: What is JMX monitoring?

**Answer (30 seconds):**
* Java Management Extensions - standard for monitoring and managing Java applications
* **MBeans**: Managed Beans expose application metrics and operations
* **JConsole**: Built-in tool for JMX monitoring
* **Remote Monitoring**: Monitor applications running on remote servers
* **Operations**: Invoke management operations remotely
* **Notifications**: Event-driven monitoring with notifications
* **Integration**: Works with monitoring tools like Nagios, Zabbix

```java
// Custom MBean for monitoring
@Component
public class ApplicationMonitorMBean implements ApplicationMonitorMXBean {
    
    private final UserService userService;
    private final OrderService orderService;
    
    @Override
    public long getTotalUsers() {
        return userService.getTotalUserCount();
    }
    
    @Override
    public long getActiveOrders() {
        return orderService.getActiveOrderCount();
    }
    
    @Override
    public double getAverageResponseTime() {
        return performanceService.getAverageResponseTime();
    }
    
    @Override
    public void clearCache() {
        cacheService.clearAll();
    }
    
    @Override
    public String getApplicationStatus() {
        return healthService.getOverallStatus();
    }
}

// MBean interface
public interface ApplicationMonitorMXBean {
    long getTotalUsers();
    long getActiveOrders();
    double getAverageResponseTime();
    void clearCache();
    String getApplicationStatus();
}
```

---

### Question 382: What is health checks?

**Answer (30 seconds):**
* Automated tests to verify application and its dependencies are functioning correctly
* **Liveness**: Application is running and responsive
* **Readiness**: Application is ready to handle requests
* **Dependencies**: Database, external services, message queues status
* **Load Balancer**: Remove unhealthy instances from traffic
* **Kubernetes**: Built-in health check support for container orchestration
* **Spring Boot Actuator**: Provides health check endpoints

```java
// Custom health check with Spring Boot Actuator
@Component
public class DatabaseHealthIndicator implements HealthIndicator {
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    public Health health() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(1)) {
                return Health.up()
                    .withDetail("database", "Available")
                    .withDetail("validationQuery", "SELECT 1")
                    .build();
            }
        } catch (SQLException e) {
            return Health.down()
                .withDetail("database", "Unavailable")
                .withDetail("error", e.getMessage())
                .build();
        }
        
        return Health.down()
            .withDetail("database", "Connection invalid")
            .build();
    }
}

// Kubernetes health check configuration
/*
livenessProbe:
  httpGet:
    path: /actuator/health/liveness
    port: 8080
  initialDelaySeconds: 30
  periodSeconds: 10

readinessProbe:
  httpGet:
    path: /actuator/health/readiness
    port: 8080
  initialDelaySeconds: 5
  periodSeconds: 5
*/
```

---

### Question 383: What is distributed monitoring?

**Answer (35 seconds):**
* Monitoring approach for applications spread across multiple servers/services
* **Distributed Tracing**: Track requests across service boundaries
* **Correlation IDs**: Link related operations across services
* **Service Mesh**: Monitor inter-service communication
* **Centralized Metrics**: Aggregate metrics from all services
* **End-to-End Visibility**: Complete view of distributed transactions
* **Tools**: Jaeger, Zipkin, OpenTelemetry, Istio service mesh

```java
// Distributed tracing with Spring Cloud Sleuth
@RestController
public class OrderController {
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private InventoryService inventoryService;
    
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        // Trace automatically propagated across service calls
        
        // Call to payment service (different microservice)
        PaymentResult payment = paymentService.processPayment(order.getPayment());
        
        // Call to inventory service (different microservice)
        InventoryResult inventory = inventoryService.reserveItems(order.getItems());
        
        return orderService.createOrder(order, payment, inventory);
    }
}

// Custom span for detailed tracing
@Service
public class OrderService {
    
    @Autowired
    private Tracer tracer;
    
    public Order processOrder(Order order) {
        Span span = tracer.nextSpan()
            .name("order-processing")
            .tag("order.id", order.getId().toString())
            .tag("user.id", order.getUserId().toString())
            .start();
            
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(span)) {
            // Processing logic
            return processOrderInternal(order);
        } finally {
            span.end();
        }
    }
}
```

---

### Question 384: What is alerting strategies?

**Answer (40 seconds):**
* Systematic approach to notify teams when issues occur in production
* **Thresholds**: Define when alerts should trigger (error rate > 5%)
* **Severity Levels**: Critical, warning, info based on impact
* **Escalation**: Route alerts to appropriate teams/individuals
* **Noise Reduction**: Avoid alert fatigue with smart filtering
* **Runbooks**: Documented procedures for handling alerts
* **Channels**: Email, SMS, Slack, PagerDuty for notifications
* **SLA/SLO**: Service level objectives drive alerting rules

```yaml
# Prometheus alerting rules
groups:
- name: application.rules
  rules:
  - alert: HighErrorRate
    expr: rate(http_requests_total{status=~"5.."}[5m]) > 0.05
    for: 2m
    labels:
      severity: critical
    annotations:
      summary: "High error rate detected"
      description: "Error rate is {{ $value }} for the last 5 minutes"
      
  - alert: HighResponseTime
    expr: histogram_quantile(0.95, rate(http_request_duration_seconds_bucket[5m])) > 0.5
    for: 5m
    labels:
      severity: warning
    annotations:
      summary: "High response time"
      description: "95th percentile response time is {{ $value }}s"
      
  - alert: DatabaseConnectionsHigh
    expr: database_connections_active / database_connections_max > 0.8
    for: 1m
    labels:
      severity: warning
    annotations:
      summary: "Database connection pool nearly exhausted"
```

```java
// Custom alerting in application
@Component
public class AlertingService {
    
    private final NotificationService notificationService;
    private final MeterRegistry meterRegistry;
    
    @EventListener
    public void handleCriticalError(CriticalErrorEvent event) {
        // Increment error counter
        meterRegistry.counter("errors.critical", 
            "service", event.getServiceName(),
            "error.type", event.getErrorType())
            .increment();
            
        // Send immediate alert for critical errors
        Alert alert = Alert.builder()
            .severity(Severity.CRITICAL)
            .title("Critical Error in " + event.getServiceName())
            .description(event.getMessage())
            .timestamp(Instant.now())
            .build();
            
        notificationService.sendAlert(alert);
    }
}
```