# Spring Boot Internals + Messaging + Microservices — Interview Answers

---

## 21. How Does `@Transactional` Work Internally?

Spring uses **AOP (Aspect-Oriented Programming)** under the hood. When you annotate a method with `@Transactional`, Spring creates a **proxy** around your bean.

When the method is called:
1. The proxy intercepts the call
2. Opens a transaction (or joins an existing one based on propagation)
3. Executes your method
4. If no exception → commits the transaction
5. If a `RuntimeException` is thrown → rolls back

```java
@Transactional
public void transferMoney(Long from, Long to, double amount) {
    debit(from, amount);
    credit(to, amount);  // if this throws, debit also rolls back
}
```

**Important gotcha:** `@Transactional` only works on **public methods** and only when called from **outside the class** (because the proxy is bypassed on self-invocation).

---

## 22. How Does `@EnableAutoConfiguration` Work Internally?

It's the magic behind Spring Boot's "convention over configuration."

When your app starts:
1. Spring Boot scans `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` (or `spring.factories` in older versions)
2. That file lists hundreds of auto-configuration classes
3. Each class is annotated with `@ConditionalOn...` — so it only activates if certain conditions are met (e.g., a class is on the classpath, a bean is missing, a property is set)

```java
@ConditionalOnClass(DataSource.class)
@ConditionalOnMissingBean(DataSource.class)
public class DataSourceAutoConfiguration { ... }
```

So if you add `spring-boot-starter-data-jpa`, Spring Boot automatically configures a `DataSource`, `EntityManagerFactory`, and transaction manager — without you writing any config.

`@SpringBootApplication` = `@Configuration` + `@ComponentScan` + `@EnableAutoConfiguration` combined.

---

## 23. What is `@Async` and How Does It Work?

`@Async` runs a method in a **separate thread** — so the caller doesn't wait for it to finish.

**Setup:**
```java
@SpringBootApplication
@EnableAsync
public class MyApp { }
```

```java
@Async
public void sendEmail(String to) {
    // runs in a background thread
    emailService.send(to);
}
```

Internally, Spring wraps the method in a proxy and submits it to a `TaskExecutor` (thread pool). By default it uses `SimpleAsyncTaskExecutor` — but in production, always configure a proper thread pool:

```java
@Bean
public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(10);
    executor.initialize();
    return executor;
}
```

**Gotcha:** Same as `@Transactional` — self-invocation won't work. Must be called from another bean.

If you need the result, return `CompletableFuture<T>` instead of `void`.

---

## 24. What is `@EventListener` in Spring Boot?

It's Spring's built-in **event-driven mechanism** — publish an event, and any listener picks it up.

**Create an event:**
```java
public class UserRegisteredEvent {
    private final String email;
    public UserRegisteredEvent(String email) { this.email = email; }
    public String getEmail() { return email; }
}
```

**Publish it:**
```java
@Autowired ApplicationEventPublisher publisher;

public void register(String email) {
    // save user...
    publisher.publishEvent(new UserRegisteredEvent(email));
}
```

**Listen to it:**
```java
@EventListener
public void onUserRegistered(UserRegisteredEvent event) {
    sendWelcomeEmail(event.getEmail());
}
```

By default it's **synchronous**. Combine with `@Async` to make it non-blocking.

Use it to decouple logic — like sending emails, notifications, or audit logging — without tight coupling between services.

---

## 25. What is `ResponseEntity` and When to Use It?

`ResponseEntity` gives you **full control** over the HTTP response — status code, headers, and body.

```java
// Simple return — no control
@GetMapping("/user/{id}")
public User getUser(@PathVariable Long id) {
    return userService.find(id);
}

// With ResponseEntity — full control
@GetMapping("/user/{id}")
public ResponseEntity<User> getUser(@PathVariable Long id) {
    User user = userService.find(id);
    if (user == null) {
        return ResponseEntity.notFound().build();           // 404
    }
    return ResponseEntity.ok(user);                        // 200
}
```

You can also set custom headers:
```java
return ResponseEntity
    .status(HttpStatus.CREATED)
    .header("X-Custom-Header", "value")
    .body(savedUser);
```

Use it when you need to return different status codes, add headers, or return an empty body with a specific status.

---

## 26. What is `@ExceptionHandler` vs `@ControllerAdvice`?

Both handle exceptions — but at different scopes.

**`@ExceptionHandler`** — handles exceptions **only within the same controller**.

```java
@RestController
public class UserController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
```

**`@ControllerAdvice`** — handles exceptions **globally across all controllers**. One place to handle all errors.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(500).body("Something went wrong");
    }
}
```

In real projects, always use `@ControllerAdvice` (`@RestControllerAdvice` for REST APIs) to centralize error handling.

---

## 18. What is Kafka and How Does It Work?

Kafka is a **distributed event streaming platform** — used for high-throughput, real-time data pipelines.

**Core concepts:**
- **Producer** — sends messages to a topic
- **Topic** — a named channel (like a queue, but persistent)
- **Partition** — topics are split into partitions for parallelism
- **Consumer** — reads messages from a topic
- **Consumer Group** — multiple consumers share the load; each partition is read by one consumer in the group
- **Broker** — a Kafka server that stores messages

```
Producer → Topic (Partitions) → Consumer Group → Consumers
```

Messages are **persisted on disk** and retained for a configurable period — so consumers can replay events.

```java
// Producer
kafkaTemplate.send("order-topic", "order-created");

// Consumer
@KafkaListener(topics = "order-topic", groupId = "order-group")
public void consume(String message) {
    System.out.println("Received: " + message);
}
```

Use Kafka for: event sourcing, log aggregation, real-time analytics, microservice communication at scale.

---

## 19. What is RabbitMQ and When to Use It Over Kafka?

RabbitMQ is a **traditional message broker** — it routes messages between producers and consumers using queues and exchanges.

**Core concepts:**
- **Producer** → sends to an **Exchange**
- **Exchange** → routes to one or more **Queues** (based on routing rules)
- **Consumer** → reads from a Queue
- Once a message is consumed and acknowledged, it's **deleted**

```java
// Send
rabbitTemplate.convertAndSend("order-exchange", "order.created", payload);

// Receive
@RabbitListener(queues = "order-queue")
public void receive(String message) { }
```

**RabbitMQ vs Kafka:**

| | RabbitMQ | Kafka |
|---|---|---|
| Message retention | Deleted after consumed | Retained (replayable) |
| Throughput | Moderate | Very high |
| Use case | Task queues, RPC, routing | Event streaming, audit logs |
| Ordering | Per-queue | Per-partition |
| Complexity | Simpler | More complex |

Use **RabbitMQ** for task queues, job processing, and complex routing.
Use **Kafka** for event streaming, high-volume data, and replay scenarios.

---

## 20. What is gRPC and How Does It Differ from REST?

gRPC is a **high-performance RPC framework** by Google. It uses **Protocol Buffers (protobuf)** for serialization and **HTTP/2** for transport.

You define your API in a `.proto` file:
```proto
service OrderService {
  rpc GetOrder (OrderRequest) returns (OrderResponse);
}
```

gRPC generates client and server code automatically from this contract.

**gRPC vs REST:**

| | REST | gRPC |
|---|---|---|
| Protocol | HTTP/1.1 | HTTP/2 |
| Format | JSON (text) | Protobuf (binary) |
| Speed | Slower | Much faster |
| Streaming | Limited | Built-in (bi-directional) |
| Browser support | Full | Limited |
| Contract | Optional (OpenAPI) | Strict (.proto file) |

Use gRPC for **internal microservice communication** where performance matters. Use REST for **public APIs** and browser clients.

---

## 21. What is a Service Mesh (Istio)?

A service mesh is an **infrastructure layer** that handles service-to-service communication in microservices — without changing your application code.

**Istio** is the most popular service mesh. It works by injecting a **sidecar proxy (Envoy)** alongside every service pod in Kubernetes.

All traffic goes through the sidecar, which handles:
- **Load balancing**
- **mTLS (mutual TLS)** — encrypted, authenticated service-to-service calls
- **Traffic management** — canary deployments, retries, timeouts, circuit breaking
- **Observability** — metrics, logs, distributed tracing automatically

```
Service A → [Envoy Sidecar] ──── [Envoy Sidecar] → Service B
                    ↕                       ↕
               Istio Control Plane (Istiod)
```

Without Istio, you'd have to implement retries, timeouts, and mTLS in every service manually.

Use it in large Kubernetes-based microservice architectures where you need security, observability, and traffic control at scale.

---

## 22. What is Zipkin and How Does Distributed Tracing Work?

In microservices, a single request can pass through 5–10 services. When something fails or is slow, how do you find where? That's what **distributed tracing** solves.

**Zipkin** is a distributed tracing system. It collects timing data from all services and lets you visualize the full request journey.

**How it works:**
1. Each request gets a unique **Trace ID**
2. Each service call within that request gets a **Span ID**
3. Services report their spans (start time, duration, status) to Zipkin
4. Zipkin assembles them into a full trace timeline

**Spring Boot setup:**
```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>
```

```yaml
management:
  tracing:
    sampling:
      probability: 1.0  # trace 100% of requests
```

```
Request → [Service A] → [Service B] → [Service C]
              span1          span2          span3
              └──────── Trace ID: abc123 ──────────┘
```

In Zipkin UI, you can see exactly which service was slow or failed. Often used with **Sleuth** (older) or **Micrometer Tracing** (Spring Boot 3+).

---
