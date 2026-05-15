# Apache Kafka Interview Questions & Answers with Code

---

**1. What is Apache Kafka, and what are its main components?**
Kafka is a distributed publish-subscribe messaging system for high-throughput, fault-tolerant event streaming.
Components: Producer, Consumer, Broker, Topic, Partition, Offset, Consumer Group, ZooKeeper/KRaft.

```java
// Maven dependency
// <artifactId>spring-kafka</artifactId>
```

---

**2. Explain Kafka's architecture briefly.**
Producers → Topics (split into Partitions on Brokers) → Consumers pull via offsets. ZooKeeper/KRaft manages metadata.

```
Producer → [Broker1: Topic-P0, P1] → Consumer Group
                [Broker2: Topic-P2]
```

---

**3. What is a Kafka broker?**
A Kafka server that stores partitions and handles producer/consumer requests. Multiple brokers form a cluster.

```properties
# server.properties
broker.id=1
listeners=PLAINTEXT://localhost:9092
log.dirs=/var/kafka-logs
```

---

**4. What is a topic in Kafka?**
A named category/feed where producers write and consumers read messages. Topics are split into partitions.

```java
// Create topic programmatically
NewTopic topic = new NewTopic("orders", 3, (short) 2); // 3 partitions, replication=2
adminClient.createTopics(Collections.singleton(topic));
```

---

**5. What is the purpose of partitions in Kafka?**
Partitions enable parallelism — multiple producers/consumers can work simultaneously on different partitions.

```java
// Producer sends to specific partition using key
ProducerRecord<String, String> record =
    new ProducerRecord<>("orders", "user-123", "order-data");
producer.send(record); // same key → same partition always
```

---

**6. Explain the role of ZooKeeper in Kafka.**
ZooKeeper manages broker registration, leader election, and cluster metadata. Kafka 3.x+ supports KRaft (no ZooKeeper).

```properties
# server.properties (ZooKeeper mode)
zookeeper.connect=localhost:2181

# KRaft mode (no ZooKeeper)
process.roles=broker,controller
```

---

**7. What are producers and consumers in Kafka?**
Producer publishes messages; Consumer reads messages from topics using offsets.

```java
// Producer
KafkaProducer<String, String> producer = new KafkaProducer<>(props);
producer.send(new ProducerRecord<>("topic", "key", "value"));

// Consumer
KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(List.of("topic"));
ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
```

---

**8. Describe a Kafka cluster.**
A group of brokers sharing topic partitions. One broker is the controller managing partition leadership.

```properties
# Broker 1
broker.id=1
# Broker 2
broker.id=2
# Broker 3
broker.id=3
# All connect to same ZooKeeper
zookeeper.connect=localhost:2181
```

---

**9. What are consumer groups?**
A set of consumers sharing a topic's partitions. Each partition is consumed by exactly one consumer in the group.

```java
props.put(ConsumerConfig.GROUP_ID_CONFIG, "order-service-group");
KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(List.of("orders")); // partitions split across group members
```

---

**10. How does Kafka ensure message ordering?**
Ordering is guaranteed within a partition. Use the same key to route related messages to the same partition.

```java
// All events for "user-123" go to the same partition → ordered
producer.send(new ProducerRecord<>("events", "user-123", "login"));
producer.send(new ProducerRecord<>("events", "user-123", "purchase"));
```

---

**11. What is an offset in Kafka?**
A unique sequential ID per message within a partition. Consumers use offsets to track their read position.

```java
for (ConsumerRecord<String, String> record : records) {
    System.out.println("Partition: " + record.partition()
        + " Offset: " + record.offset()
        + " Value: " + record.value());
}
```

---

**12. How does Kafka handle data retention?**
Messages are retained by time or size. After threshold, old log segments are deleted.

```properties
# Retain for 7 days
log.retention.hours=168

# Or retain up to 1GB per partition
log.retention.bytes=1073741824
```

---

**13. Explain "at least once" and "exactly once" delivery in Kafka.**
- At least once: may re-deliver on failure (default).
- Exactly once: idempotent producer + transactions.

```java
// At least once (default)
props.put("acks", "all");

// Exactly once
props.put("enable.idempotence", "true");
props.put("transactional.id", "tx-1");
producer.initTransactions();
producer.beginTransaction();
producer.send(record);
producer.commitTransaction();
```

---

**14. What is Kafka Streams?**
A Java library for real-time stream processing directly on Kafka topics — no separate cluster needed.

```java
StreamsBuilder builder = new StreamsBuilder();
KStream<String, String> stream = builder.stream("input-topic");
stream.filter((k, v) -> v.contains("ERROR"))
      .to("error-topic");
KafkaStreams streams = new KafkaStreams(builder.build(), props);
streams.start();
```

---

**15. How does Kafka ensure fault tolerance?**
Via replication — each partition has replicas on different brokers. If a broker fails, a replica becomes the new leader.

```properties
# Default replication factor for new topics
default.replication.factor=3
min.insync.replicas=2
```

---

**16. What is log compaction?**
Retains only the latest message per key, removing older duplicates. Useful for maintaining current state.

```properties
# Enable log compaction on a topic
log.cleanup.policy=compact

# Or per topic
cleanup.policy=compact
```

---

**17. Explain replication in Kafka.**
Each partition has 1 leader + N followers. Producers write to leader; followers replicate. Followers in ISR can become leader.

```java
// Create topic with replication factor 3
NewTopic topic = new NewTopic("payments", 4, (short) 3);
adminClient.createTopics(Collections.singleton(topic));
```

---

**18. What is an ISR (In-Sync Replica)?**
ISR = replicas fully caught up with the leader. Only ISR members are eligible for leader election.

```properties
# Minimum ISR replicas required to accept writes
min.insync.replicas=2
# Producer must wait for all ISR to acknowledge
acks=all
```

---

**19. How does leader election work in Kafka?**
When a leader fails, the controller picks the first replica from the ISR list as the new leader.

```java
// Check partition leader info via AdminClient
Map<String, TopicDescription> desc = adminClient.describeTopics(List.of("orders")).all().get();
desc.get("orders").partitions().forEach(p ->
    System.out.println("Leader: " + p.leader().id()));
```

---

**20. How do partitions help in Kafka scaling?**
More partitions = more parallelism. Each consumer in a group handles separate partitions simultaneously.

```java
// 6 partitions → up to 6 consumers can read in parallel
NewTopic topic = new NewTopic("high-volume-topic", 6, (short) 2);
adminClient.createTopics(Collections.singleton(topic));
```

---

**21. What is Kafka Connect?**
A framework to stream data between Kafka and external systems using Source/Sink connectors.

```json
// JDBC Source Connector config (REST API)
{
  "name": "jdbc-source",
  "config": {
    "connector.class": "io.confluent.connect.jdbc.JdbcSourceConnector",
    "connection.url": "jdbc:mysql://localhost/db",
    "table.whitelist": "orders",
    "mode": "incrementing",
    "incrementing.column.name": "id",
    "topic.prefix": "db-"
  }
}
```

---

**22. How does Kafka ensure message durability?**
Messages are written to disk + replicated. With `acks=all`, broker confirms only after all ISR replicas persist the message.

```java
props.put(ProducerConfig.ACKS_CONFIG, "all");
props.put(ProducerConfig.RETRIES_CONFIG, 3);
props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
```

---

**23. What's Kafka's role in stream processing?**
Kafka acts as the event backbone — ingesting, buffering, and delivering real-time streams to processors like Kafka Streams, Flink, or Spark.

```java
// Kafka Streams word count example
KStream<String, String> text = builder.stream("text-input");
text.flatMapValues(v -> Arrays.asList(v.split(" ")))
    .groupBy((k, v) -> v)
    .count()
    .toStream()
    .to("word-count-output");
```

---

**24. How is Kafka different from traditional messaging systems?**
Kafka persists messages to disk, supports replay, and scales horizontally via partitions. Traditional MQs delete messages after consumption.

```
Traditional MQ:  Producer → Queue → Consumer (message deleted)
Kafka:           Producer → Topic/Partition → Consumer (message retained, replayable)
```

---

**25. What is the replication factor?**
Number of copies of each partition across brokers. Factor of 3 = 1 leader + 2 followers.

```java
// Replication factor = 3
NewTopic topic = new NewTopic("orders", 3, (short) 3);
adminClient.createTopics(Collections.singleton(topic));
```

---

**26. How do leaders and followers work in Kafka replication?**
Leader handles all reads/writes. Followers replicate from leader. On leader failure, ISR follower is promoted.

```properties
# Ensure followers stay in sync
replica.lag.time.max.ms=10000
```

---

**27. How do consumers read data in Kafka?**
Consumers pull messages by specifying topic + partition + offset. Offsets are committed to `__consumer_offsets`.

```java
consumer.subscribe(List.of("orders"));
while (true) {
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
    for (ConsumerRecord<String, String> r : records)
        System.out.println(r.offset() + ": " + r.value());
}
```

---

**28. What are producer acknowledgments?**
`acks` controls when the broker confirms a write to the producer.

```java
props.put("acks", "0");   // no ack — fastest, data loss possible
props.put("acks", "1");   // leader ack — default
props.put("acks", "all"); // all ISR ack — safest
```

---

**29. What is the transaction API in Kafka?**
Allows atomic writes across multiple partitions/topics — all succeed or none are visible.

```java
props.put("transactional.id", "order-tx-1");
producer.initTransactions();
try {
    producer.beginTransaction();
    producer.send(new ProducerRecord<>("orders", "k", "v"));
    producer.send(new ProducerRecord<>("audit", "k", "v"));
    producer.commitTransaction();
} catch (Exception e) {
    producer.abortTransaction();
}
```

---

**30. Explain Kafka's "exactly once" semantics.**
Combines idempotent producer + transactions + consumer `read_committed` isolation.

```java
// Producer side
props.put("enable.idempotence", "true");
props.put("transactional.id", "tx-producer-1");

// Consumer side
props.put("isolation.level", "read_committed");
```

---

**31. How does Kafka manage backpressure?**
Pull-based consumers control their own pace. Producers buffer locally and block if buffer is full.

```java
props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);  // 32MB buffer
props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 5000);       // block 5s if buffer full
```

---

**32. What is linger.ms in Kafka producers?**
Time the producer waits to accumulate more messages into a batch before sending. Higher = better throughput, slightly more latency.

```java
props.put(ProducerConfig.LINGER_MS_CONFIG, 10);      // wait 10ms
props.put(ProducerConfig.BATCH_SIZE_CONFIG, 32768);  // 32KB batch
```

---

**33. How does Kafka handle message compression?**
Producer compresses batches; broker stores compressed. Consumer decompresses on read.

```java
props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy"); // gzip, lz4, zstd, snappy
```

---

**34. What is batch.size in producers?**
Max bytes per batch. Producer sends when batch is full or `linger.ms` expires.

```java
props.put(ProducerConfig.BATCH_SIZE_CONFIG, 65536);  // 64KB
props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
```

---

**35. How does Kafka handle consumer offsets?**
Offsets stored in internal topic `__consumer_offsets`. Can be committed automatically or manually.

```java
// Auto commit
props.put("enable.auto.commit", "true");
props.put("auto.commit.interval.ms", "1000");

// Manual commit
props.put("enable.auto.commit", "false");
consumer.commitSync();
```

---

**36. What is auto.offset.reset in Kafka consumers?**
Defines start position when no committed offset exists for a partition.

```java
props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // read from beginning
// props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); // read only new messages
// props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "none");   // throw exception
```

---

**37. How do rebalances work in consumer groups?**
Triggered when consumers join/leave or partitions change. Group coordinator reassigns partitions. Use `CooperativeStickyAssignor` to minimize disruption.

```java
props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,
    CooperativeStickyAssignor.class.getName()); // incremental rebalance
```

---

**38. What is the purpose of the Schema Registry?**
Stores and enforces Avro/JSON/Protobuf schemas. Ensures producers and consumers agree on message structure.

```java
props.put("schema.registry.url", "http://localhost:8081");
props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
```

---

**39. How does Kafka handle topic deletion?**
Marks topic for async deletion across all brokers. Requires `delete.topic.enable=true`.

```properties
# server.properties
delete.topic.enable=true
```

```java
// Delete via AdminClient
adminClient.deleteTopics(Collections.singleton("old-topic")).all().get();
```

---

**40. Explain commitSync() vs commitAsync() in Kafka consumers.**
- `commitSync()`: Blocks, retries on failure — safe but slower.
- `commitAsync()`: Non-blocking, no retry — faster but may miss commits.

```java
// commitSync — safe, used on shutdown
consumer.commitSync();

// commitAsync — fast, used during normal processing
consumer.commitAsync((offsets, e) -> {
    if (e != null) log.error("Commit failed", e);
});

// Best practice: async during loop, sync on close
try {
    while (running) {
        consumer.poll(Duration.ofMillis(100));
        consumer.commitAsync();
    }
} finally {
    consumer.commitSync(); // ensure final commit
    consumer.close();
}
```

---
