## 🟢 1. SQL (Most Important ⭐)

1. What is the difference between `INNER JOIN`, `LEFT JOIN`, `RIGHT JOIN`, and `FULL OUTER JOIN`?
2. What is a window function? Explain `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()`.
3. What is the difference between `WHERE` and `HAVING`?
4. How do you find duplicate records in a table?
5. What is a CTE (Common Table Expression)? How is it different from a subquery?
6. Explain `GROUP BY` vs `PARTITION BY`.
7. How do you find the second highest salary?
8. What is indexing? How does it improve query performance?
9. What is the difference between `UNION` and `UNION ALL`?
10. Write a query to find customers who placed orders in the last 30 days.


## 🟢 2. Python / Programming

1. How do you read a large CSV file without loading it into memory?
2. What is the difference between a list, tuple, set, and dictionary?
3. How do you handle missing values in a Pandas DataFrame?
4. What are Python generators? Why are they useful in data engineering?
5. How do you merge two DataFrames in Pandas?
6. What is the difference between `map()`, `filter()`, and `reduce()`?
7. How do you write a Python function to flatten a nested JSON?
8. What is list comprehension? Give an example.
9. How do you connect to a database using Python (e.g., SQLAlchemy, psycopg2)?
10. What are decorators in Python?


## 🟢 3. Database Concepts

1. What is the difference between SQL and NoSQL databases?
2. Explain ACID properties.
3. What is normalization? Explain 1NF, 2NF, 3NF.
4. What is denormalization and when would you use it?
5. What is a primary key vs foreign key?
6. What is a transaction? How do you handle rollback?
7. What is the difference between OLTP and OLAP?
8. What are stored procedures and when should you use them?
9. What is a materialized view vs a regular view?
10. What is database sharding?


## 🟢 4. Data Warehousing

1. What is a data warehouse? How is it different from a database?
2. Explain Star Schema vs Snowflake Schema.
3. What is a fact table vs a dimension table?
4. What is SCD (Slowly Changing Dimension)? Explain Type 1, Type 2, Type 3.
5. What is a data mart?
6. What is the difference between a data warehouse and a data lake?
7. What is a data lakehouse?
8. What is partitioning and bucketing in a data warehouse?
9. How does columnar storage improve query performance?
10. What tools have you used for data warehousing? (Redshift, BigQuery, Snowflake)


## 🟢 5. ETL & Data Pipelines

1. What is ETL? What is ELT? When would you use each?
2. How do you handle data quality issues in a pipeline?
3. What is idempotency in a data pipeline?
4. How do you handle late-arriving data?
5. What is incremental loading vs full load?
6. How do you monitor and alert on pipeline failures?
7. What is data lineage?
8. How do you handle schema changes in a pipeline?
9. What tools have you used for ETL? (Airflow, Glue, dbt, Spark)
10. How do you ensure exactly-once processing in a pipeline?


## 🟢 6. Big Data (Very Important for Mid/Senior)

1. What is Apache Spark? How does it differ from Hadoop MapReduce?
2. Explain the difference between RDD, DataFrame, and Dataset in Spark.
3. What is lazy evaluation in Spark?
4. What is a shuffle in Spark? Why is it expensive?
5. How do you optimize a slow Spark job?
6. What is partitioning in Spark? How do you repartition data?
7. What is the difference between `cache()` and `persist()` in Spark?
8. What is HDFS? How does it store data?
9. What is the difference between narrow and wide transformations?
10. How does Spark handle fault tolerance?


## 🟢 7. Streaming / Real-Time Systems

1. What is Apache Kafka? Explain topics, partitions, producers, and consumers.
2. What is the difference between batch processing and stream processing?
3. What is exactly-once semantics in Kafka?
4. What is Apache Flink vs Spark Streaming?
5. What is a consumer group in Kafka?
6. How do you handle out-of-order events in a stream?
7. What is watermarking in stream processing?
8. What is the difference between event time and processing time?
9. How do you ensure low latency in a streaming pipeline?
10. What is Kafka offset management?


## 🟢 8. System Design (Data Engineering ⭐)

1. Design a data pipeline to ingest 1 billion records per day.
2. How would you design a real-time analytics dashboard?
3. Design a data warehouse for an e-commerce platform.
4. How would you handle duplicate data in a large-scale pipeline?
5. Design a system to track user clickstream data.
6. How would you build a scalable ETL pipeline on AWS/GCP/Azure?
7. How do you design for high availability and fault tolerance?
8. How would you migrate an on-premise data warehouse to the cloud?
9. Design a data lake architecture.
10. How do you handle PII (Personally Identifiable Information) in your pipeline?


## 🟢 9. Cloud (Must for modern roles)

1. What AWS/GCP/Azure services have you used for data engineering?
2. What is the difference between S3, Redshift, and Glue on AWS?
3. What is AWS Glue? How does it work?
4. What is Google BigQuery? How is it different from a traditional database?
5. What is Azure Data Factory?
6. How do you secure data in the cloud (IAM, encryption, VPC)?
7. What is serverless data processing? (AWS Lambda, Cloud Functions)
8. How do you optimize cloud storage costs?
9. What is the difference between hot, warm, and cold storage?
10. How do you handle cross-region data replication?


## 🟢 10. Data Modeling

1. What is data modeling? Why is it important?
2. What is the difference between conceptual, logical, and physical data models?
3. When would you use a relational model vs a document model?
4. What is an entity-relationship (ER) diagram?
5. How do you model many-to-many relationships?
6. What is a surrogate key vs a natural key?
7. How do you model time-series data?
8. What is a graph data model? When would you use it?
9. How do you handle hierarchical data in SQL?
10. What is data vault modeling?


## 🟢 11. DevOps & Tools

1. What is Apache Airflow? How do you define a DAG?
2. How do you version control your data pipelines?
3. What is dbt (data build tool)? How does it work?
4. How do you containerize a data pipeline using Docker?
5. What is CI/CD for data pipelines?
6. How do you use Git in a data engineering workflow?
7. What is infrastructure as code? (Terraform, CloudFormation)
8. How do you manage secrets and credentials in a pipeline?
9. What is a data catalog? (e.g., AWS Glue Catalog, Apache Atlas)
10. How do you do unit testing for data pipelines?


## 🟢 12. Behavioral & Scenario Questions

1. Tell me about a complex data pipeline you built from scratch.
2. How did you handle a production pipeline failure?
3. Describe a time you improved pipeline performance significantly.
4. How do you prioritize tasks when multiple pipelines are failing?
5. Tell me about a time you worked with stakeholders to define data requirements.
6. How do you ensure data quality in your pipelines?
7. Describe a time you had to learn a new technology quickly.
8. How do you document your data pipelines?
9. Tell me about a disagreement with a team member and how you resolved it.
10. Where do you see data engineering heading in the next 3 years?
