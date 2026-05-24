# Kafka Architecture Components Explained

## 1. Producer

Application that sends messages/events to Kafka topics.

### Real-Time Examples

* E-commerce app sending orders
* IoT devices sending sensor data
* Payment service sending transaction events

```text
Producer → Kafka Topic
```

### Spring Boot Producer Code

**pom.xml**
```xml
<dependency>
  <groupId>org.springframework.kafka</groupId>
  <artifactId>spring-kafka</artifactId>
</dependency>
```

**application.yml**
```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
```

**Producer Service**
```java
@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrder(String order) {
        kafkaTemplate.send("orders", order);
    }
}
```

**Controller**
```java
@RestController
public class OrderController {

    @Autowired
    private OrderProducer producer;

    @PostMapping("/order")
    public String placeOrder(@RequestBody String order) {
        producer.sendOrder(order);
        return "Order sent to Kafka";
    }
}
```

---

## 2. Topic

A logical channel where messages are stored.

### Example

```text id="x2z19g"
orders
payments
notifications
```

All order-related events go into:

```text id="4t0qvj"
orders topic
```

---

## 3. Partition

A topic is divided into partitions for parallel processing.

### Why?

* Better scalability
* Parallel consumers
* Ordered messages within partition

```text id="c8gk5z"
Orders Topic
 ├── Partition 1
 ├── Partition 2
 └── Partition 3
```

---

## 4. Consumer

Reads data from Kafka topics.

### Example

```text
Payment Service
Inventory Service
Email Service
```

consume order events.

### Spring Boot Consumer Code

**application.yml**
```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: order-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
```

**Consumer Service**
```java
@Service
public class OrderConsumer {

    @KafkaListener(topics = "orders", groupId = "order-group")
    public void consumeOrder(String order) {
        System.out.println("Received Order: " + order);
    }
}
```

---

## 5. Consumer Group

Multiple consumers working together.

Kafka distributes partitions among consumers.

```text id="z84brx"
Consumer Group
   ├── Consumer 1
   ├── Consumer 2
   └── Consumer 3
```

### Benefit

* Load balancing
* Parallel processing

---

## 6. Broker

Kafka server that stores topic data.

```text id="c7yqca"
Broker 1
Broker 2
Broker 3
```

Each broker manages partitions.

---

## 7. Cluster

Collection of multiple Kafka brokers.

### Why?

* High availability
* Fault tolerance
* Scalability

```text id="8lf1vx"
Kafka Cluster = Multiple Brokers
```

---

## 8. Offset

Unique position of each message inside partition.

```text id="0dht4x"
0 1 2 3 4 5 6
```

If consumer processed till:

```text id="6r2smj"
4
```

then current offset = 4.

### Benefit

Consumer can resume from last processed message.

---

## 9. Replication

Copies partition data across brokers.

```text id="1jgx8j"
Leader Partition
Follower Partition
```

### Why?

* Data safety
* Fault tolerance
* Broker failure recovery

---

## 10. Retention

Defines how long Kafka keeps messages.

### Types

* Time-based retention
* Size-based retention

### Example

```text id="jnt8zv"
Keep messages for 7 days
```

Even after consumers read them, Kafka can retain data.

---

## 11. Schema Registry

Manages event structure/schema.

Ensures producers and consumers use same format.

### Example

```json
{
  "orderId": 101,
  "amount": 500
}
```

### Sending JSON Object (Spring Boot)

**Order DTO**
```java
public class Order {
    private int orderId;
    private double amount;
    // getters, setters
}
```

**application.yml for JSON**
```yaml
spring:
  kafka:
    producer:
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
    consumer:
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "*"
```

**Producer**
```java
@Autowired
private KafkaTemplate<String, Order> kafkaTemplate;

public void sendOrder(Order order) {
    kafkaTemplate.send("orders", order);
}
```

**Consumer**
```java
@KafkaListener(topics = "orders", groupId = "order-group")
public void consumeOrder(Order order) {
    System.out.println("Order ID: " + order.getOrderId());
}
```

### Benefit

* Prevents schema mismatch
* Version management

---

## 12. Kafka Connect

Used to move data between Kafka and external systems.

### Example

```text id="l4f6vr"
Database ↔ Kafka
ElasticSearch ↔ Kafka
S3 ↔ Kafka
```

### Benefit

No custom integration code needed.

---

# Simple Kafka Flow

```text
Producer
   ↓
Topic
   ↓
Partition
   ↓
Broker
   ↓
Consumer Group
   ↓
Consumers
```

---

## 13. Create Topic Programmatically

```java
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic ordersTopic() {
        return TopicBuilder.name("orders")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
```

---

## 14. Error Handling & Manual Offset Commit

**application.yml**
```yaml
spring:
  kafka:
    consumer:
      enable-auto-commit: false
    listener:
      ack-mode: manual
```

**Consumer**
```java
@KafkaListener(topics = "orders", groupId = "order-group")
public void consumeOrder(String order, Acknowledgment ack) {
    try {
        System.out.println("Processing: " + order);
        ack.acknowledge();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
```

---

## 15. Send Message with Key

```java
// Same key → same partition → guaranteed ordering
kafkaTemplate.send("orders", "user-101", order);
```

---

# Interview Q&A

**Q1. What is Kafka?**
> Distributed event streaming platform for high-throughput, fault-tolerant, real-time data pipelines.

**Q2. Kafka vs RabbitMQ?**

| Feature | Kafka | RabbitMQ |
|---|---|---|
| Type | Log-based | Message queue |
| Retention | Keeps after consume | Deletes after consume |
| Throughput | Very high | Moderate |
| Use case | Event streaming | Task queues |

**Q3. What is a Consumer Group?**
> Group of consumers sharing work of reading a topic. Each partition assigned to only one consumer in the group.

**Q4. What is an Offset?**
> Unique sequential ID per message in a partition. Consumers track offsets to know what they've processed.

**Q5. What happens if a broker goes down?**
> Kafka replicates partitions. If leader fails, a follower is elected as new leader automatically.

**Q6. What is Zookeeper's role?**
> Manages broker metadata and leader election. Kafka 3.x+ replaces it with KRaft mode.

**Q7. How does Kafka guarantee ordering?**
> Only within a single partition. Use the same key for messages that must be ordered.

**Q8. What is `auto.offset.reset`?**
> - `earliest` → read from beginning
> - `latest` → read only new messages

**Q9. What is Kafka Streams?**
> Client library for real-time stream processing inside Kafka — filter, transform, aggregate without external tools.

**Q10. How to retry failed messages?**
```java
@Bean
public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
        ConsumerFactory<String, String> cf) {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(cf);
    factory.setCommonErrorHandler(new DefaultErrorHandler(
            new FixedBackOff(1000L, 3))); // retry 3 times, 1s apart
    return factory;
}
```

---

# Interview One-Line Summary

> Producer sends events to Kafka topics, topics are divided into partitions, brokers store the data, consumers read the events, offsets track message positions, replication provides fault tolerance, and consumer groups enable parallel processing.
