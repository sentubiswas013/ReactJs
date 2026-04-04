## 🟢 6. Big Data (Very Important for Mid/Senior)

### Q1. What is Apache Spark? How does it differ from Hadoop MapReduce?

**Answer:**

Apache Spark is an **open-source distributed computing engine** for large-scale data processing. It processes data in-memory across a cluster of machines, making it dramatically faster than disk-based systems.

**Hadoop MapReduce** was the original big data processing framework — it reads from disk, processes, writes back to disk, reads again for the next step. Every intermediate result hits HDFS.

**Spark** keeps intermediate results **in memory** — no disk I/O between steps. That's the core reason it's 10–100x faster for iterative workloads.

| | Apache Spark | Hadoop MapReduce |
|---|---|---|
| Processing | In-memory | Disk-based (HDFS) |
| Speed | 10–100x faster | Slower |
| Ease of use | Python, Scala, SQL APIs | Java-heavy, verbose |
| Streaming | ✅ (Structured Streaming) | ❌ (batch only) |
| ML support | ✅ (MLlib) | ❌ |
| Fault tolerance | RDD lineage | Disk replication |
| Use case | Batch + streaming + ML | Batch only |

```python
# Spark: clean, concise API
from pyspark.sql import SparkSession
spark = SparkSession.builder.appName("example").getOrCreate()

df = spark.read.parquet("s3://bucket/orders/")
result = df.groupBy("customer_id").sum("amount")
result.write.parquet("s3://bucket/output/")
```

> MapReduce is largely obsolete now. Spark replaced it as the standard for big data processing. I use Spark on Databricks or AWS EMR — it handles batch, streaming, and ML in one unified engine.

---

### Q2. Explain the difference between RDD, DataFrame, and Dataset in Spark.

**Answer:**

These are Spark's three data abstractions — each generation improved on the last.

**RDD (Resilient Distributed Dataset) — Generation 1:**
- Low-level, distributed collection of objects.
- No schema, no optimization — you control everything manually.
- Fault-tolerant via lineage graph.
- Use when you need fine-grained control or work with unstructured data.

```python
rdd = spark.sparkContext.parallelize([1, 2, 3, 4, 5])
result = rdd.filter(lambda x: x > 2).map(lambda x: x * 2)
# [6, 8, 10]
```

**DataFrame — Generation 2:**
- Distributed table with named columns and schema — like a SQL table or Pandas DataFrame.
- Optimized by Catalyst query optimizer and Tungsten execution engine.
- Much faster than RDD for structured data.
- Available in Python, Scala, Java, R.

```python
df = spark.read.parquet("s3://bucket/orders/")
df.filter(df.amount > 100).groupBy("customer_id").sum("amount").show()
```

**Dataset — Generation 3 (Scala/Java only):**
- Combines DataFrame's optimization with RDD's type safety.
- Compile-time type checking — catch errors before runtime.
- Not available in Python (PySpark uses DataFrames only).

```scala
// Scala only
case class Order(order_id: Long, amount: Double)
val ds: Dataset[Order] = spark.read.parquet("s3://bucket/orders/").as[Order]
ds.filter(_.amount > 100)
```

| | RDD | DataFrame | Dataset |
|---|---|---|---|
| Schema | ❌ | ✅ | ✅ |
| Optimization | ❌ | ✅ Catalyst | ✅ Catalyst |
| Type safety | Runtime | Runtime | Compile-time |
| Language | All | All | Scala/Java only |
| Best for | Unstructured data | Structured analytics | Type-safe Scala pipelines |

> In practice, I use DataFrames for almost everything in PySpark. RDDs are only needed for very specific low-level operations. Datasets are great in Scala for production pipelines where type safety matters.

---

### Q3. What is lazy evaluation in Spark?

**Answer:**

Lazy evaluation means **Spark does not execute transformations immediately** when you call them. It builds up a logical plan — a DAG of operations — and only executes when you trigger an **action**.

**Transformations** (lazy — just build the plan):
- `filter()`, `map()`, `select()`, `groupBy()`, `join()`, `withColumn()`

**Actions** (trigger execution):
- `show()`, `count()`, `collect()`, `write()`, `save()`

```python
df = spark.read.parquet("s3://bucket/orders/")   # lazy — no data read yet

df2 = df.filter(df.amount > 100)                 # lazy — just adds to plan
df3 = df2.groupBy("customer_id").sum("amount")   # lazy — still no execution

df3.show()   # ACTION — now Spark executes the entire plan
```

**Why lazy evaluation is powerful:**

1. **Optimization** — Spark's Catalyst optimizer sees the full plan before executing. It can reorder operations, push filters down, eliminate unnecessary steps.

2. **Efficiency** — if you filter early, Spark knows to skip irrelevant data before joining or aggregating.

3. **No wasted work** — if you build a transformation chain but never call an action, nothing runs.

```python
# Catalyst optimization example
# You write:
df.join(orders, "customer_id").filter(df.country == "US")

# Catalyst rewrites it as:
df.filter(df.country == "US").join(orders, "customer_id")
# Filter first = less data to join = much faster
```

> Lazy evaluation is one of Spark's biggest advantages. It's why you can chain 20 transformations and Spark still runs it efficiently — it optimizes the whole plan before touching a single byte of data.

---

### Q4. What is a shuffle in Spark? Why is it expensive?

**Answer:**

A shuffle is when Spark needs to **redistribute data across partitions and across nodes** in the cluster — moving data over the network so that related records end up on the same partition.

It happens during wide transformations like `groupBy()`, `join()`, `distinct()`, `repartition()`.

```
Before shuffle:                    After shuffle:
Node 1: [A=10, B=5, A=3]    →     Node 1: [A=10, A=3, A=7]  (all A's together)
Node 2: [B=8, A=7, C=2]     →     Node 2: [B=5, B=8]        (all B's together)
Node 3: [C=9, B=1, C=4]     →     Node 3: [C=2, C=9, C=4]   (all C's together)
```

**Why shuffles are expensive:**

1. **Network I/O** — data moves between nodes over the network — slow.
2. **Disk I/O** — shuffle data is written to disk before being sent — very slow.
3. **Serialization** — data must be serialized/deserialized for transfer.
4. **Memory pressure** — large shuffles can cause OOM (out of memory) errors.
5. **Skew** — if one key has far more records than others, one node gets overloaded.

```python
# This triggers a shuffle — all records with same customer_id must go to same partition
df.groupBy("customer_id").sum("amount")

# This also triggers a shuffle — matching keys must be co-located
df1.join(df2, "customer_id")
```

**How to minimize shuffles:**
- Filter data early before joins/groupBy.
- Use `broadcast join` for small tables — avoids shuffle entirely.
- Partition data by join key upfront.
- Use `reduceByKey` instead of `groupByKey` on RDDs.

```python
from pyspark.sql.functions import broadcast

# Broadcast join: small table sent to all nodes — no shuffle
result = large_df.join(broadcast(small_df), "customer_id")
```

> Shuffles are the #1 cause of slow Spark jobs. When I'm debugging a slow job, the first thing I check is the Spark UI — how many shuffle bytes were written? If it's in the hundreds of GBs, that's where the bottleneck is.

---

### Q5. How do you optimize a slow Spark job?

**Answer:**

When a Spark job is slow, I follow a systematic approach — check the Spark UI first, then fix the root cause.

**Step 1 — Check the Spark UI:**
- Look at stage timings — which stage is slowest?
- Check shuffle read/write bytes — high shuffle = bottleneck.
- Look for skewed tasks — one task taking 10x longer than others = data skew.

**Optimization 1 — Filter early (predicate pushdown):**

```python
# Bad: join first, filter after
df1.join(df2, "customer_id").filter(df1.country == "US")

# Good: filter first, then join — less data to shuffle
df1.filter(df1.country == "US").join(df2, "customer_id")
```

**Optimization 2 — Broadcast small tables:**

```python
from pyspark.sql.functions import broadcast

# Avoids shuffle entirely for the small table
result = large_orders.join(broadcast(dim_product), "product_id")
```

**Optimization 3 — Fix data skew:**

```python
# Skew: one customer_id has 90% of records — one task gets all the work
# Fix: add salt to distribute the skewed key
from pyspark.sql.functions import concat, lit, (rand() * 10).cast("int")

df = df.withColumn("salted_key", concat(col("customer_id"), lit("_"), (rand()*10).cast("int")))
df.groupBy("salted_key").sum("amount")
```

**Optimization 4 — Tune partitions:**

```python
# Default 200 shuffle partitions is often wrong
spark.conf.set("spark.sql.shuffle.partitions", "50")   # smaller dataset
spark.conf.set("spark.sql.shuffle.partitions", "2000") # larger dataset

# Rule of thumb: partition size should be 100–200MB each
```

**Optimization 5 — Cache repeated DataFrames:**

```python
# If you use the same DataFrame multiple times, cache it
df = spark.read.parquet("s3://bucket/orders/").cache()
df.count()   # trigger cache
# Now all subsequent operations on df read from memory
```

**Optimization 6 — Use columnar formats:**

```python
# Always read/write Parquet — columnar, compressed, fast
df.write.mode("overwrite").parquet("s3://bucket/output/")
# Never use CSV for large datasets in Spark
```

**Optimization 7 — Avoid UDFs when possible:**

```python
# Bad: Python UDF — breaks JVM optimization, slow
from pyspark.sql.functions import udf
upper_udf = udf(lambda x: x.upper())
df.withColumn("name_upper", upper_udf(col("name")))

# Good: use built-in Spark functions — stay in JVM
from pyspark.sql.functions import upper
df.withColumn("name_upper", upper(col("name")))
```

> My optimization checklist: filter early → broadcast small tables → fix skew → tune partition count → cache reused DataFrames → use Parquet → avoid Python UDFs. In 90% of cases, one of these fixes the problem.

---

### Q6. What is partitioning in Spark? How do you repartition data?

**Answer:**

Partitioning is how Spark **divides data across nodes and cores** in the cluster. Each partition is processed independently by one task on one core. More partitions = more parallelism.

**Two types of partitioning:**

- **Hash partitioning** — default. Rows are assigned to partitions based on a hash of a key column. Ensures same key always goes to same partition.
- **Range partitioning** — rows sorted and split into ranges. Good for ordered data.

**Checking current partitions:**

```python
df = spark.read.parquet("s3://bucket/orders/")
print(df.rdd.getNumPartitions())   # e.g., 200
```

**repartition() — full shuffle, even distribution:**

```python
# Increase partitions — more parallelism for large data
df = df.repartition(500)

# Repartition by column — co-locate same keys (good before joins)
df = df.repartition(200, "customer_id")
```

**coalesce() — reduce partitions, no full shuffle:**

```python
# Reduce partitions efficiently — no shuffle, just merge
df = df.coalesce(10)   # use before writing small output files
```

**When to use which:**

| | repartition() | coalesce() |
|---|---|---|
| Shuffle | Full shuffle | No shuffle (merge only) |
| Use for | Increasing partitions | Decreasing partitions |
| Distribution | Even | May be uneven |
| Cost | Expensive | Cheap |

**Write partitioned files to storage:**

```python
# Partition output files by date — enables partition pruning on read
df.write \
  .partitionBy("order_date") \
  .mode("overwrite") \
  .parquet("s3://bucket/orders/")
```

> Rule of thumb: aim for partition sizes of 100–200MB. Too few partitions = underutilized cluster. Too many = overhead from task scheduling. I always repartition by join key before a large join to avoid shuffle.

---

### Q7. What is the difference between `cache()` and `persist()` in Spark?

**Answer:**

Both store a DataFrame/RDD in memory so it doesn't get recomputed on every action — the difference is the **storage level**.

**cache():**
- Shortcut for `persist(StorageLevel.MEMORY_AND_DISK)`.
- Stores in memory first, spills to disk if memory is full.
- Simple, no configuration needed.

```python
df = spark.read.parquet("s3://bucket/orders/").cache()
df.count()        # triggers caching — data loaded into memory
df.show()         # reads from cache — fast
df.groupBy("customer_id").count().show()  # reads from cache again
```

**persist():**
- Gives you control over the storage level.
- Choose based on your memory/disk/speed trade-off.

```python
from pyspark import StorageLevel

df.persist(StorageLevel.MEMORY_ONLY)          # memory only, recompute if evicted
df.persist(StorageLevel.MEMORY_AND_DISK)      # memory first, spill to disk
df.persist(StorageLevel.DISK_ONLY)            # disk only, slower but saves memory
df.persist(StorageLevel.MEMORY_AND_DISK_SER)  # serialized — less memory, more CPU
df.persist(StorageLevel.OFF_HEAP)             # off-heap memory — avoids GC pressure
```

**Storage levels comparison:**

| Level | Memory | Disk | CPU cost | Best for |
|---|---|---|---|---|
| MEMORY_ONLY | ✅ | ❌ | Low | Small DataFrames that fit in memory |
| MEMORY_AND_DISK | ✅ | ✅ (spill) | Low | Default — safe choice |
| DISK_ONLY | ❌ | ✅ | Low | Very large DataFrames |
| MEMORY_AND_DISK_SER | ✅ (serialized) | ✅ | Medium | Memory-constrained clusters |

**Always unpersist when done:**

```python
df.unpersist()   # free up memory for other operations
```

> I use `cache()` for DataFrames I reuse 2+ times in the same job — like a filtered base table used in multiple aggregations. Without caching, Spark re-reads from S3 and re-applies all transformations every single time. On large datasets that's a huge waste.

---

### Q8. What is HDFS? How does it store data?

**Answer:**

HDFS stands for **Hadoop Distributed File System** — it's a distributed storage system designed to store very large files across a cluster of commodity machines, with built-in fault tolerance.

**Architecture — two main components:**

- **NameNode** — the master. Stores metadata — file names, directory structure, block locations. Does NOT store actual data.
- **DataNodes** — the workers. Store the actual data blocks. Report to NameNode regularly (heartbeat).

**How HDFS stores data:**

1. A large file is split into fixed-size **blocks** (default 128MB).
2. Each block is **replicated** across multiple DataNodes (default replication factor = 3).
3. NameNode tracks which DataNode has which block.

```
File: orders.csv (500MB)
        │
        ▼
Block 1 (128MB) → DataNode 1, DataNode 3, DataNode 5
Block 2 (128MB) → DataNode 2, DataNode 4, DataNode 1
Block 3 (128MB) → DataNode 3, DataNode 5, DataNode 2
Block 4 (116MB) → DataNode 4, DataNode 1, DataNode 3
```

**Fault tolerance:**
- If a DataNode fails, the NameNode detects it (missed heartbeat) and re-replicates the blocks from surviving copies.
- If the NameNode fails — that's a single point of failure. Modern Hadoop uses **NameNode HA** (High Availability) with a standby NameNode.

**HDFS vs Cloud Object Storage (S3/GCS):**

| | HDFS | S3/GCS/ADLS |
|---|---|---|
| Location | On-premise cluster | Cloud |
| Cost | Hardware + ops | Pay per use |
| Scalability | Manual | Infinite |
| Modern usage | Legacy Hadoop | Preferred today |

> HDFS was the foundation of the Hadoop ecosystem. In modern data engineering, most teams have moved to cloud object storage like S3 — it's cheaper, infinitely scalable, and decouples storage from compute. But understanding HDFS is still important for interviews and legacy system work.

---

### Q9. What is the difference between narrow and wide transformations?

**Answer:**

This is about **whether a transformation requires data to move between partitions** — which determines whether a shuffle happens.

**Narrow Transformations:**
- Each input partition contributes to **only one output partition**.
- No data movement across the network.
- Fast — can be pipelined together.
- Examples: `filter()`, `map()`, `select()`, `withColumn()`, `union()`

```python
# Narrow: each partition processed independently, no shuffle
df.filter(col("amount") > 100)    # each partition filtered in place
df.withColumn("tax", col("amount") * 0.1)  # computed per partition
```

**Wide Transformations:**
- Input partitions contribute to **multiple output partitions**.
- Requires a **shuffle** — data moves across the network.
- Slow — creates a stage boundary in the DAG.
- Examples: `groupBy()`, `join()`, `distinct()`, `repartition()`, `orderBy()`

```python
# Wide: data must be shuffled across partitions
df.groupBy("customer_id").sum("amount")   # all same customer_id must go to same partition
df.join(other_df, "customer_id")          # matching keys must be co-located
df.distinct()                             # must compare across all partitions
```

**Visual:**

```
Narrow (filter):                Wide (groupBy):
Partition 1 → Partition 1       Partition 1 ─┐
Partition 2 → Partition 2       Partition 2 ─┼→ Partition A (all key=A)
Partition 3 → Partition 3       Partition 3 ─┘→ Partition B (all key=B)
(no data movement)              (shuffle — data crosses nodes)
```

**Why it matters:**
- Spark groups consecutive narrow transformations into a single **stage** — no shuffle between them.
- Each wide transformation creates a new **stage boundary** — triggers a shuffle.
- Fewer stages = fewer shuffles = faster job.

> When I'm optimizing a Spark job, I look at the DAG in the Spark UI and count the stage boundaries — each one is a shuffle. My goal is to minimize wide transformations and push filters (narrow) as early as possible.

---

### Q10. How does Spark handle fault tolerance?

**Answer:**

Spark handles fault tolerance through **RDD lineage** — it remembers how every piece of data was created, so it can recompute lost data without storing everything to disk.

**Core mechanism — Lineage Graph (DAG):**
Every RDD/DataFrame knows its parent and the transformation applied to create it. If a partition is lost (node failure), Spark recomputes only that partition by replaying the lineage from the original source.

```
Source (S3) → filter() → groupBy() → result
                                         ↑
                              If this partition is lost,
                              Spark replays: read from S3 → filter → groupBy
                              Only for the lost partition — not the whole dataset
```

**How it works in practice:**

1. **Task failure** — Spark retries the failed task on another node (default 4 retries).
2. **Executor failure** — Spark reschedules all tasks from that executor on other nodes. Recomputes lost partitions from lineage.
3. **Driver failure** — job fails. Needs external checkpointing to recover.

**Checkpointing — breaking the lineage chain:**

For very long lineage chains (iterative ML algorithms), recomputing from the start is expensive. Checkpointing saves the data to disk and cuts the lineage.

```python
spark.sparkContext.setCheckpointDir("s3://bucket/checkpoints/")

df = spark.read.parquet("s3://bucket/orders/")
# ... many transformations ...
df.checkpoint()   # save to disk, cut lineage here
# subsequent failures only recompute from checkpoint, not from S3
```

**Structured Streaming fault tolerance:**

```python
# Checkpointing for streaming — tracks progress, enables recovery
df.writeStream \
  .format("delta") \
  .option("checkpointLocation", "s3://bucket/checkpoints/stream/") \
  .start("s3://bucket/output/")
# If stream fails and restarts, it picks up exactly where it left off
```

**Comparison with Hadoop MapReduce:**

| | Spark | MapReduce |
|---|---|---|
| Fault tolerance | RDD lineage recomputation | Writes every step to HDFS |
| Recovery cost | Recompute lost partitions | Re-read from disk |
| Overhead | Low (in-memory lineage) | High (disk I/O every step) |

> Spark's lineage-based fault tolerance is elegant — it's why Spark doesn't need to write intermediate results to disk like MapReduce. The trade-off is that very long lineage chains can be slow to recompute, which is why I use checkpointing for iterative jobs or long streaming pipelines.
