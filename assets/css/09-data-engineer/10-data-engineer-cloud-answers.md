## 🟢 9. Cloud (Must for modern roles)

## 1. What AWS/GCP/Azure services have you used for data engineering?

On AWS, I've worked with S3 for storage, Glue for ETL, Redshift for warehousing, Lambda for serverless processing, and Kinesis for streaming. On GCP, I've used BigQuery for analytics and Dataflow for pipeline processing. On Azure, I've worked with Azure Data Factory for orchestration and Azure Blob Storage. The choice of cloud usually depends on the client's existing infrastructure.

---

## 2. What is the difference between S3, Redshift, and Glue on AWS?

Simple answer — they serve three different purposes:

- **S3** is object storage — you store raw files like CSVs, Parquet, JSON. It's your data lake layer.
- **Redshift** is a columnar data warehouse — you run analytical SQL queries on structured data at scale.
- **Glue** is a managed ETL service — it reads from S3, transforms data using Spark under the hood, and loads it into Redshift or another target.

Think of it as: S3 stores it, Glue transforms it, Redshift queries it.

---

## 3. What is AWS Glue? How does it work?

AWS Glue is a fully managed serverless ETL service. You define jobs in Python or Scala using Spark, and Glue runs them on a managed cluster — no infrastructure to manage.

It has three main components:
- **Glue Crawler** — scans your S3 data and auto-discovers schema, populating the Glue Data Catalog.
- **Glue Data Catalog** — a central metadata repository, like a Hive metastore.
- **Glue Jobs** — the actual ETL scripts that read, transform, and write data.

You can trigger jobs on a schedule, via events, or from Airflow/Step Functions.

---

## 4. What is Google BigQuery? How is it different from a traditional database?

BigQuery is a fully managed, serverless data warehouse on GCP. The key differences from a traditional database:

- **No infrastructure** — you don't provision servers or manage clusters.
- **Columnar storage** — optimized for analytical queries, not row-level transactions.
- **Separation of compute and storage** — you pay per query scanned, not for idle servers.
- **Massive scale** — can query petabytes in seconds using distributed compute.
- **No indexes needed** — BigQuery uses partitioning and clustering instead.

Traditional databases like MySQL or PostgreSQL are built for OLTP — frequent small reads/writes. BigQuery is built for OLAP — large analytical scans.

---

## 5. What is Azure Data Factory?

Azure Data Factory (ADF) is Microsoft's cloud-based ETL and data integration service. It lets you build data pipelines visually or via code to move and transform data across sources.

Key concepts:
- **Pipelines** — logical grouping of activities.
- **Activities** — individual steps like Copy Data, Data Flow, or running a stored procedure.
- **Linked Services** — connections to data sources like Blob Storage, SQL Server, or REST APIs.
- **Triggers** — schedule or event-based pipeline execution.

It's similar to Apache Airflow but fully managed and tightly integrated with the Azure ecosystem.

---

## 6. How do you secure data in the cloud (IAM, encryption, VPC)?

I follow a layered security approach:

- **IAM** — least privilege access. Each service or user gets only the permissions they need. Use roles, not long-lived access keys.
- **Encryption** — encrypt data at rest using KMS-managed keys (SSE-KMS on S3, TDE on Redshift). Encrypt data in transit using TLS/HTTPS.
- **VPC** — run compute resources inside a private VPC. Use VPC endpoints so S3/Redshift traffic never leaves the AWS network.
- **Secrets Manager / Parameter Store** — never hardcode credentials. Store DB passwords and API keys in Secrets Manager.
- **Audit Logging** — enable CloudTrail on AWS to log all API calls for compliance and forensics.

---

## 7. What is serverless data processing? (AWS Lambda, Cloud Functions)

Serverless means you run code without managing servers. You just deploy a function, and the cloud provider handles scaling, patching, and availability.

- **AWS Lambda** — runs Python/Node/Java functions triggered by S3 events, API Gateway, Kinesis, etc. Great for lightweight transformations, file processing, or triggering pipelines.
- **GCP Cloud Functions** — same concept on GCP, triggered by Pub/Sub, GCS events, or HTTP.

Serverless is ideal for event-driven, short-duration tasks. For heavy Spark jobs or long-running pipelines, you'd still use EMR, Dataproc, or Glue.

---

## 8. How do you optimize cloud storage costs?

A few practical strategies I use:

- **Use the right storage class** — S3 Intelligent-Tiering automatically moves data between frequent and infrequent access tiers. For archival, use Glacier.
- **Compress data** — store files in Parquet or ORC with Snappy/GZIP compression. Reduces storage and query costs.
- **Partition data** — partition by date or region so queries scan less data.
- **Lifecycle policies** — automatically move old data to cheaper tiers or delete it after a retention period.
- **Avoid small files** — many small files are expensive to list and process. Compact them into larger files.
- **Right-size compute** — use Spot/Preemptible instances for batch jobs to cut compute costs by 60–80%.

---

## 9. What is the difference between hot, warm, and cold storage?

These refer to how frequently data is accessed and the cost/performance tradeoff:

| Tier | Access Frequency | Latency | Cost | Example |
|------|-----------------|---------|------|---------|
| **Hot** | Frequent (daily) | Milliseconds | High | S3 Standard, Azure Hot Blob |
| **Warm** | Occasional (monthly) | Seconds | Medium | S3 Standard-IA, Azure Cool Blob |
| **Cold** | Rare (yearly/archival) | Minutes to hours | Very Low | S3 Glacier, Azure Archive |

In a data pipeline, recent data lives in hot storage for fast queries. Older data gets tiered down automatically using lifecycle policies.

---

## 10. How do you handle cross-region data replication?

Cross-region replication is needed for disaster recovery, compliance, or reducing latency for global users.

On AWS:
- **S3 Cross-Region Replication (CRR)** — automatically replicates objects to a bucket in another region. Enable versioning on both buckets.
- **Redshift** — use snapshots and restore to another region, or use Redshift Data Sharing for live cross-region access.
- **RDS/Aurora** — enable read replicas in another region for DR.

Key considerations:
- **Data residency laws** — some data (GDPR, HIPAA) cannot leave certain regions. Always check compliance before replicating.
- **Replication lag** — async replication has lag. Design your system to tolerate eventual consistency.
- **Cost** — cross-region data transfer has egress charges. Factor this into architecture decisions.

---
