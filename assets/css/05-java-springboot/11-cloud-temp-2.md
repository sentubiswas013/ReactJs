# Kafka Architecture Components Explained

## 1. Producer

Application that sends messages/events to Kafka topics.

### Real-Time Examples

* E-commerce app sending orders
* IoT devices sending sensor data
* Payment service sending transaction events

```text id="0qoz1g"
Producer → Kafka Topic
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

```text id="0k39eu"
Payment Service
Inventory Service
Email Service
```

consume order events.

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

```json id="l0s40f"
{
  "orderId": 101,
  "amount": 500
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

```text id="1wqjlwm"
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

# Interview One-Line Summary

> Producer sends events to Kafka topics, topics are divided into partitions, brokers store the data, consumers read the events, offsets track message positions, replication provides fault tolerance, and consumer groups enable parallel processing.
