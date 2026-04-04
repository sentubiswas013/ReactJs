## 🟢 7. Streaming / Real-Time Systems

### Q1. What is Apache Kafka? Explain topics, partitions, producers, and consumers.

**Answer:**

Apache Kafka is a **distributed event streaming platform** — it's a high-throughput, fault-tolerant message queue designed to handle millions of events per second. Think of it as a durable, scalable pipe between systems.

**Core concepts:**

**Topic:**
- A named channel where messages are published — like a table in a database but for streams.
- Example: `orders`, `user-clicks`, `payment-events`.

**Partition:**
- A topic is split into partitions — each partition is an ordered, immutable log of messages.
- Partitions enable parallelism — multiple consumers can read different partitions simultaneously.
- Messages within a partition are ordered. Across partitions — no ordering guarantee.

**Producer:**
- Writes (publishes) messages to a topic.
- Decides which partition to write to — by key hash, round-robin, or custom logic.

**Consumer:**
- Reads (subscribes) messages from a topic.
- Tracks its position using an **offset** — the ID of the last message read.

```
Topic: orders (3 partitions)

Producer → Partition 0: [msg0, msg1, msg4, msg7]
Producer → Partition 1: [msg2, msg5, msg8]
Producer → Partition 2: [msg3, msg6, msg9]
                              ↑
                         Consumer reads from here (offset)
```

```python
# Kafka Producer (Python)
from kafka import KafkaProducer
import json

producer = KafkaProducer(
    bootstrap_servers='localhost:9092',
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)
producer.send('orders', {'order_id': 1001, 'amount': 99.99})

# Kafka Consumer (Python)
from kafka import KafkaConsumer

consumer = KafkaConsumer(
    'orders',
    bootstrap_servers='localhost:9092',
    group_id='order-processor',
    value_deserializer=lambda v: json.loads(v.decode('utf-8'))
)
for message in consumer:
    print(message.value)
```

> Kafka is the backbone of most real-time data pipelines I've worked on. It decouples producers from consumers — the source system doesn't need to know who's consuming the data or how fast they're processing it.

---

### Q2. What is the difference between batch processing and stream processing?

**Answer:**

The core difference is **when data is processed** — all at once vs continuously as it arrives.

**Batch Processing:**
- Collects data over a period (hourly, daily), then processes it all at once.
- High latency — results available after the batch completes.
- Simple, efficient for large historical datasets.
- Examples: nightly ETL jobs, monthly reports.

**Stream Processing:**
- Processes data continuously as it arrives — event by event or in micro-batches.
- Low latency — results available in milliseconds to seconds.
- More complex — must handle out-of-order events, failures mid-stream.
- Examples: fraud detection, real-time dashboards, live recommendations.

| | Batch Processing | Stream Processing |
|---|---|---|
| Data | Bounded (finite) | Unbounded (infinite) |
| Latency | High (minutes–hours) | Low (ms–seconds) |
| Complexity | Simple | Complex |
| Tools | Spark batch, Hive, dbt | Kafka, Flink, Spark Streaming |
| Use case | Reports, ETL, ML training | Fraud detection, alerts, live feeds |

```
Batch:
[Day's data] → [Process at midnight] → [Report ready next morning]

Stream:
[Event arrives] → [Process immediately] → [Result in milliseconds]
```

**Lambda Architecture — using both:**
```
Raw data → [Batch layer: accurate, slow]  ─┐
         → [Speed layer: fast, approximate] ┼→ Serving layer → Users
```

> In modern data engineering, the line is blurring. Spark Structured Streaming and Flink can do both batch and streaming with the same code. I choose based on the SLA — if the business needs results in under a minute, it's streaming. If daily is fine, batch is simpler and cheaper.

---

### Q3. What is exactly-once semantics in Kafka?

**Answer:**

Exactly-once semantics means **each message is delivered and processed exactly one time** — no duplicates, no data loss. It's the hardest guarantee to achieve in distributed systems.

**Three delivery guarantees:**
- **At-most-once** — messages may be lost, never duplicated. (fire and forget)
- **At-least-once** — messages never lost, but may be duplicated on retry.
- **Exactly-once** — never lost, never duplicated. Hardest to achieve.

**How Kafka achieves exactly-once (since Kafka 0.11):**

**1 — Idempotent Producer:**
Kafka assigns each producer a unique ID and sequence number. If a duplicate message arrives (due to retry), Kafka deduplicates it automatically.

```python
producer = KafkaProducer(
    bootstrap_servers='localhost:9092',
    enable_idempotence=True   # enables idempotent producer
)
```

**2 — Transactions (atomic read-process-write):**
Wrap the entire consume → process → produce cycle in a transaction. Either all steps commit or all roll back.

```python
producer = KafkaProducer(
    bootstrap_servers='localhost:9092',
    transactional_id='my-transactional-producer'
)
producer.init_transactions()

try:
    producer.begin_transaction()
    producer.send('output-topic', value=processed_record)
    producer.commit_transaction()
except Exception:
    producer.abort_transaction()
```

**3 — Exactly-once in Spark Structured Streaming:**

```python
# Spark: exactly-once with checkpointing + idempotent sink
df.writeStream \
  .format("delta") \
  .outputMode("append") \
  .option("checkpointLocation", "s3://bucket/checkpoints/") \
  .start("s3://bucket/output/")
```

> In practice, exactly-once end-to-end is very hard. My approach is idempotent writes + at-least-once delivery — it achieves the same outcome with less complexity. True Kafka transactions are worth the overhead only when financial accuracy is critical.

---

### Q4. What is Apache Flink vs Spark Streaming?

**Answer:**

Both are distributed stream processing frameworks — the key difference is their **processing model and latency**.

**Apache Spark Structured Streaming:**
- Processes streams as **micro-batches** — collects events for a short interval (e.g., 1 second), then processes as a mini-batch.
- Slightly higher latency (seconds) but very familiar — same DataFrame API as batch Spark.
- Great if your team already knows Spark.

```python
# Spark Structured Streaming
df = spark.readStream.format("kafka") \
    .option("kafka.bootstrap.servers", "localhost:9092") \
    .option("subscribe", "orders") \
    .load()

result = df.groupBy(window("timestamp", "1 minute")).count()

result.writeStream.format("console").outputMode("complete").start()
```

**Apache Flink:**
- True **event-by-event** stream processing — processes each event the moment it arrives.
- Sub-second latency — much lower than Spark Streaming.
- More powerful windowing, stateful processing, and event time handling.
- Steeper learning curve.

```java
// Flink: true event-by-event processing
DataStream<Order> orders = env.addSource(new FlinkKafkaConsumer<>(...));
orders
    .keyBy(Order::getCustomerId)
    .window(TumblingEventTimeWindows.of(Time.minutes(1)))
    .sum("amount")
    .print();
```

| | Spark Streaming | Apache Flink |
|---|---|---|
| Processing model | Micro-batch | True event-by-event |
| Latency | Seconds | Milliseconds |
| API familiarity | Same as Spark batch | Different API |
| Stateful processing | Good | Excellent |
| Exactly-once | ✅ | ✅ |
| Best for | Teams using Spark already | Ultra-low latency, complex CEP |

> I use Spark Streaming when the team is already on Databricks/EMR and latency of a few seconds is acceptable. I'd choose Flink for financial fraud detection or anything needing sub-second response times and complex event patterns.

---

### Q5. What is a consumer group in Kafka?

**Answer:**

A consumer group is a **group of consumers that work together to consume a topic** — each partition is assigned to exactly one consumer in the group at a time. This enables parallel processing.

**How it works:**
- Kafka assigns partitions to consumers in the group.
- Each partition is consumed by only one consumer in the group — no duplicate processing.
- If a consumer fails, Kafka **rebalances** — reassigns its partitions to other consumers.

```
Topic: orders (4 partitions)
Consumer Group: order-processors (3 consumers)

Partition 0 → Consumer A
Partition 1 → Consumer A   (Consumer A handles 2 partitions)
Partition 2 → Consumer B
Partition 3 → Consumer C
```

**Scaling with consumer groups:**

```
# Scale up: add more consumers (up to number of partitions)
4 partitions + 4 consumers = 1 partition per consumer (max parallelism)
4 partitions + 5 consumers = 1 consumer sits idle (can't exceed partition count)
4 partitions + 2 consumers = 2 partitions per consumer
```

**Multiple independent consumer groups:**
Different applications can each have their own consumer group — they all read the full topic independently, at their own pace.

```
Topic: orders
  ├── Consumer Group: billing-service     (reads all messages for billing)
  ├── Consumer Group: inventory-service   (reads all messages for inventory)
  └── Consumer Group: analytics-pipeline  (reads all messages for analytics)
```

```python
# Each service has its own group_id — independent offset tracking
billing_consumer   = KafkaConsumer('orders', group_id='billing-service', ...)
inventory_consumer = KafkaConsumer('orders', group_id='inventory-service', ...)
```

> Consumer groups are one of Kafka's most powerful features. They let you scale consumers horizontally and fan out the same data to multiple independent systems — all without the producer knowing or caring who's consuming.

---

### Q6. How do you handle out-of-order events in a stream?

**Answer:**

Out-of-order events happen when events arrive at the processor in a different order than they occurred — due to network delays, mobile apps going offline, or distributed system lag.

**Example:**
```
Event occurred:  10:00, 10:01, 10:02, 10:03
Event arrived:   10:00, 10:02, 10:03, 10:01  ← 10:01 arrived late
```

**Strategy 1 — Watermarking (most common):**
Define how long to wait for late events before closing a time window. Events arriving after the watermark threshold are dropped or handled separately.

```python
# Spark: wait up to 10 minutes for late events
df.withWatermark("event_time", "10 minutes") \
  .groupBy(window("event_time", "5 minutes")) \
  .count()
```

**Strategy 2 — Reordering buffer:**
Hold events in a buffer for a short time, sort by event time, then process in order.

```python
from collections import deque
import heapq

buffer = []
BUFFER_WINDOW = 5  # seconds

def process_with_reorder(event):
    heapq.heappush(buffer, (event['event_time'], event))

    # Process events older than buffer window
    now = current_time()
    while buffer and (now - buffer[0][0]) > BUFFER_WINDOW:
        _, old_event = heapq.heappop(buffer)
        process(old_event)
```

**Strategy 3 — Idempotent upserts:**
Don't try to reorder — just upsert. If a late event arrives for an already-processed window, merge it in.

```sql
-- Late event arrives for yesterday's window
MERGE INTO daily_stats AS target
USING new_event AS source
ON target.date = source.event_date AND target.user_id = source.user_id
WHEN MATCHED THEN UPDATE SET total = total + source.amount
WHEN NOT MATCHED THEN INSERT ...;
```

**Strategy 4 — Separate late data lane:**

```python
# Route late events to a separate topic for reprocessing
if event['event_time'] < watermark:
    producer.send('late-events', event)   # handle separately
else:
    producer.send('processed-events', event)
```

> My default approach is watermarking for streaming and upserts for batch. The watermark threshold depends on the SLA — for mobile app events I use 30 minutes, for IoT sensors maybe 5 minutes. The key is agreeing with the upstream team on the expected latency.

---

### Q7. What is watermarking in stream processing?

**Answer:**

A watermark is a **threshold that tells the stream processor how long to wait for late-arriving events** before considering a time window complete and emitting results.

It's the answer to: "How late can an event arrive and still be included in its window?"

**Without watermark — problem:**
```
Window: 10:00–10:05
Events arrive at 10:06 for the 10:00–10:05 window
Should we include them? How long do we wait?
Without a watermark — we wait forever and never emit results.
```

**With watermark — solution:**
```
Watermark = 10 minutes
Current event time = 10:20
Watermark threshold = 10:20 - 10 minutes = 10:10

Any event with event_time < 10:10 is considered "too late" and dropped.
Windows up to 10:10 are now safe to close and emit.
```

```python
# Spark Structured Streaming: 10-minute watermark
from pyspark.sql.functions import window

df = spark.readStream.format("kafka").load()

result = df \
    .withWatermark("event_time", "10 minutes") \
    .groupBy(
        window("event_time", "5 minutes"),   # 5-minute tumbling window
        "user_id"
    ) \
    .count()

result.writeStream.format("delta").start("s3://bucket/output/")
```

**Types of windows:**

```python
# Tumbling window: fixed, non-overlapping
window("event_time", "5 minutes")

# Sliding window: overlapping
window("event_time", "10 minutes", "5 minutes")  # 10-min window, slides every 5 min

# Session window: gap-based (Flink)
# Window closes after X minutes of inactivity per user
```

**Trade-off:**
- **Large watermark** → wait longer → more complete results → higher latency.
- **Small watermark** → emit faster → lower latency → more late events dropped.

> I set watermarks based on the upstream SLA. If the mobile team says events can be delayed up to 15 minutes, I set a 20-minute watermark to be safe. It's always a trade-off between completeness and latency — I make that decision with the business, not unilaterally.

---

### Q8. What is the difference between event time and processing time?

**Answer:**

This is a fundamental concept in stream processing — there are actually three types of time:

**Event Time:**
- When the event **actually happened** — recorded by the source system or device.
- Most accurate for analytics — reflects real-world timing.
- Can arrive out of order or late.

**Processing Time:**
- When the event **arrives at the stream processor** — the system clock when Kafka/Flink/Spark receives it.
- Simple — no out-of-order issues, always monotonically increasing.
- Inaccurate for analytics — a mobile event from 2 hours ago processed now has wrong processing time.

**Ingestion Time:**
- When the event **enters the messaging system** (e.g., when Kafka receives it).
- Between event time and processing time.

```
Real world:     User clicks at 10:00 (event time)
Mobile app:     Offline — stores event locally
Network:        App reconnects at 10:45
Kafka:          Receives event at 10:45 (ingestion time)
Spark:          Processes event at 10:46 (processing time)

Event time  = 10:00  ← what actually happened
Processing time = 10:46 ← when we processed it
Difference  = 46 minutes late!
```

**Which to use:**

| | Event Time | Processing Time |
|---|---|---|
| Accuracy | ✅ Accurate | ❌ Inaccurate for late events |
| Complexity | High (need watermarks) | Low (no out-of-order handling) |
| Use case | Analytics, billing, reporting | Simple monitoring, alerting |

```python
# Spark: use event_time from the message payload
df = df.withColumn("event_time", col("payload.timestamp").cast("timestamp"))

# Group by event time — accurate analytics
df.withWatermark("event_time", "10 minutes") \
  .groupBy(window("event_time", "1 hour")) \
  .count()

# vs processing time — simpler but less accurate
df.groupBy(window(current_timestamp(), "1 hour")) \
  .count()
```

> I always use event time for analytics and reporting — it reflects what actually happened. Processing time is fine for operational monitoring where you just want to know "how many events are we receiving right now" — not when they occurred.

---

### Q9. How do you ensure low latency in a streaming pipeline?

**Answer:**

Low latency means minimizing the time from **event occurrence to result being available**. I optimize at every layer of the pipeline.

**1 — Minimize Kafka consumer lag:**

```python
# Tune consumer for throughput and latency
consumer = KafkaConsumer(
    'orders',
    fetch_min_bytes=1,          # don't wait for batch to fill — fetch immediately
    fetch_max_wait_ms=10,       # max 10ms wait before fetching
    max_poll_records=100        # process in small batches
)
```

**2 — Use small micro-batch intervals (Spark Streaming):**

```python
# Trigger every 1 second instead of default
df.writeStream \
  .trigger(processingTime='1 second') \
  .format("delta") \
  .start("s3://bucket/output/")

# Or continuous processing (experimental) — sub-millisecond
df.writeStream \
  .trigger(continuous='100 milliseconds') \
  .start(...)
```

**3 — Minimize processing complexity:**

```python
# Avoid expensive operations in the hot path
# Bad: complex join on every event
df.join(large_reference_table, "product_id")   # slow

# Good: pre-load reference data into memory
reference = spark.read.parquet("s3://bucket/products/").cache()
broadcast_ref = broadcast(reference)
df.join(broadcast_ref, "product_id")           # fast — no shuffle
```

**4 — Right-size partitions:**

```python
# Too few partitions = underutilized cores = slow
# Too many = scheduling overhead = slow
# Match partitions to number of Kafka partitions for 1:1 mapping
spark.conf.set("spark.sql.shuffle.partitions", str(kafka_partition_count))
```

**5 — Use fast sinks:**

```
Slow sinks:  S3 (high latency), HDFS
Fast sinks:  Redis, Cassandra, DynamoDB, Elasticsearch, Delta Lake
```

```python
# Write to Redis for sub-millisecond reads downstream
import redis
r = redis.Redis(host='localhost', port=6379)

def write_to_redis(batch_df, batch_id):
    for row in batch_df.collect():
        r.set(f"order:{row.order_id}", json.dumps(row.asDict()))

df.writeStream.foreachBatch(write_to_redis).start()
```

**6 — Co-locate producer and consumer:**
- Run Kafka brokers and stream processors in the same availability zone — reduces network hops.

> For sub-second latency I use Flink with Kafka. For 1–5 second latency, Spark Structured Streaming is simpler and sufficient. The biggest latency killers I've seen in production are: large shuffle operations, slow sinks, and consumer lag from under-provisioned clusters.

---

### Q10. What is Kafka offset management?

**Answer:**

An offset is a **unique sequential ID assigned to each message within a partition**. It's how Kafka tracks which messages a consumer has already read.

```
Partition 0:  [msg0=offset0, msg1=offset1, msg2=offset2, msg3=offset3]
                                                              ↑
                                              Consumer committed up to here
                                              Next read starts at offset3
```

**Two types of offset commits:**

**Auto commit (simple but risky):**
Kafka automatically commits the offset at a fixed interval. Risk: if the consumer crashes after auto-commit but before processing, messages are lost.

```python
consumer = KafkaConsumer(
    'orders',
    enable_auto_commit=True,
    auto_commit_interval_ms=5000   # commit every 5 seconds
)
```

**Manual commit (recommended for production):**
You control exactly when the offset is committed — after successful processing.

```python
consumer = KafkaConsumer(
    'orders',
    enable_auto_commit=False,      # manual control
    group_id='order-processor'
)

for message in consumer:
    try:
        process(message.value)     # process first
        consumer.commit()          # then commit offset
    except Exception as e:
        log_error(e)               # don't commit — message will be redelivered
        # at-least-once: message reprocessed on next poll
```

**Offset reset strategies:**
What to do when a consumer group starts fresh or falls too far behind:

```python
consumer = KafkaConsumer(
    'orders',
    auto_offset_reset='earliest',  # start from beginning of topic
    # auto_offset_reset='latest'   # start from newest messages only
)
```

**Monitoring consumer lag:**
Consumer lag = latest offset in partition - consumer's committed offset. High lag = consumer is falling behind.

```bash
# Check consumer group lag
kafka-consumer-groups.sh \
  --bootstrap-server localhost:9092 \
  --describe \
  --group order-processor

# Output:
# TOPIC   PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG
# orders  0          1000            1050            50   ← 50 messages behind
```

**Storing offsets externally:**
For exactly-once semantics, store offsets in the same database as your output — commit both atomically.

```python
def process_batch(messages):
    with db.transaction():
        for msg in messages:
            db.execute("INSERT INTO orders ...", msg.value)
            db.execute("UPDATE kafka_offsets SET offset = ? WHERE partition = ?",
                      msg.offset + 1, msg.partition)
        db.commit()   # offset and data committed together — exactly-once
```

> Offset management is critical for pipeline reliability. I always use manual commits in production — auto-commit is convenient but leads to data loss or duplicates under failure. I also monitor consumer lag as a key SLA metric — if lag grows, it means the consumer can't keep up and we need to scale.

