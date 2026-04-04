## 🟢 11. DevOps & Tools

## 1. What is Apache Airflow? How do you define a DAG?

Apache Airflow is an open-source workflow orchestration tool. You define, schedule, and monitor data pipelines as code. It's the industry standard for orchestrating ETL jobs, Spark jobs, dbt runs, and more.

A **DAG** (Directed Acyclic Graph) is a collection of tasks with defined dependencies — it tells Airflow what to run and in what order.

You define a DAG in Python:

```python
from airflow import DAG
from airflow.operators.python import PythonOperator
from datetime import datetime

def extract(): print("Extracting data...")
def load():    print("Loading data...")

with DAG(
    dag_id="my_pipeline",
    start_date=datetime(2024, 1, 1),
    schedule_interval="@daily",
    catchup=False
) as dag:

    t1 = PythonOperator(task_id="extract", python_callable=extract)
    t2 = PythonOperator(task_id="load",    python_callable=load)

    t1 >> t2  # t1 runs before t2
```

Key concepts: **Operators** (what to run), **Tasks** (instances of operators), **Dependencies** (`>>` or `<<`), **Scheduler** (triggers runs based on schedule).

---

## 2. How do you version control your data pipelines?

Same way you version control application code — using **Git**.

Practical approach:
- Store all pipeline code (Airflow DAGs, dbt models, Spark jobs, configs) in a Git repository.
- Use **feature branches** for new pipelines or changes. Never commit directly to `main`.
- Use **pull requests** with peer review before merging.
- Tag releases so you can roll back to a known-good version if a pipeline breaks in production.
- Store environment-specific configs (dev/staging/prod) separately — never hardcode environment values in pipeline code.
- Use `.gitignore` to exclude secrets, credentials, and large data files.

For dbt specifically, the entire project is version-controlled — models, tests, macros, and documentation all live in Git.

---

## 3. What is dbt (data build tool)? How does it work?

dbt is a transformation tool that lets data engineers and analysts write SQL `SELECT` statements and dbt handles the rest — creating tables, views, running tests, and generating documentation.

It sits in the **T** of ELT. Data is already in your warehouse (Redshift, BigQuery, Snowflake), and dbt transforms it in place.

How it works:
- You write `.sql` model files — each file is a `SELECT` query that defines a table or view.
- dbt compiles those into `CREATE TABLE AS` or `CREATE VIEW AS` statements and runs them against your warehouse.
- You define **tests** in YAML to assert data quality (not null, unique, accepted values, referential integrity).
- dbt builds a **DAG** of model dependencies automatically based on `{{ ref('model_name') }}` references.

```sql
-- models/orders_summary.sql
SELECT
    customer_id,
    COUNT(*)        AS total_orders,
    SUM(amount)     AS total_spent
FROM {{ ref('stg_orders') }}
GROUP BY customer_id
```

Run with: `dbt run`, test with: `dbt test`, document with: `dbt docs generate`.

---

## 4. How do you containerize a data pipeline using Docker?

Docker packages your pipeline code and all its dependencies into a portable container that runs the same everywhere — dev, CI, and production.

Steps:
1. Write a `Dockerfile` that defines the environment.
2. Build the image.
3. Run the container.

```dockerfile
# Dockerfile
FROM python:3.11-slim

WORKDIR /app

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

CMD ["python", "pipeline.py"]
```

```bash
docker build -t my-pipeline .
docker run --env-file .env my-pipeline
```

For Airflow, use the official `apache/airflow` Docker image and `docker-compose` to spin up the full stack locally. In production, deploy containers to **ECS**, **Kubernetes (EKS/GKE)**, or **Cloud Run**.

---

## 5. What is CI/CD for data pipelines?

CI/CD stands for Continuous Integration / Continuous Deployment. For data pipelines it means:

- **CI (Continuous Integration)** — every time you push code, automated checks run:
  - Linting (flake8, sqlfluff for SQL)
  - Unit tests (pytest)
  - dbt tests (`dbt test`)
  - Schema validation

- **CD (Continuous Deployment)** — after tests pass, the pipeline is automatically deployed to staging or production.

Typical flow using GitHub Actions:

```yaml
# .github/workflows/ci.yml
on: [push]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - run: pip install -r requirements.txt
      - run: pytest tests/
      - run: dbt test --profiles-dir .
```

The goal: no manual deployments, no untested code in production, fast feedback loops.

---

## 6. How do you use Git in a data engineering workflow?

Git is central to everything. Here's how I use it day-to-day:

- **Branching strategy** — `main` is production-ready. All work happens on feature branches (`feature/add-orders-pipeline`).
- **Commit messages** — descriptive and atomic. One logical change per commit.
- **Pull Requests** — all changes go through PR review. This catches bugs and shares knowledge.
- **Tagging** — tag stable releases (`v1.2.0`) so you can roll back if a deployment breaks something.
- **Monorepo vs multi-repo** — I prefer a monorepo for data projects: Airflow DAGs, dbt models, Spark jobs, and Terraform all in one repo with clear folder structure.

```
repo/
├── dags/           # Airflow DAGs
├── dbt/            # dbt models, tests, macros
├── spark/          # PySpark jobs
├── terraform/      # Infrastructure as code
└── tests/          # Unit tests
```

---

## 7. What is infrastructure as code? (Terraform, CloudFormation)

Infrastructure as Code (IaC) means you define and manage cloud infrastructure — servers, databases, S3 buckets, IAM roles — using code files instead of clicking through the console.

Benefits: version-controlled, repeatable, auditable, and you can spin up identical environments (dev/staging/prod) with one command.

**Terraform** — cloud-agnostic, works with AWS, GCP, Azure. Uses HCL (HashiCorp Configuration Language).

```hcl
# Create an S3 bucket
resource "aws_s3_bucket" "data_lake" {
  bucket = "my-data-lake-bucket"
}

# Create a Glue database
resource "aws_glue_catalog_database" "raw" {
  name = "raw_data"
}
```

**CloudFormation** — AWS-native IaC using YAML/JSON templates. Tightly integrated with AWS services.

In data engineering, I use Terraform to provision S3 buckets, Glue jobs, Redshift clusters, IAM roles, and VPCs — everything the pipeline needs to run.

---

## 8. How do you manage secrets and credentials in a pipeline?

Never hardcode credentials. Never commit them to Git. Full stop.

Practical approaches:

- **AWS Secrets Manager** — store DB passwords, API keys. Retrieve at runtime via SDK.
- **AWS Parameter Store (SSM)** — good for non-sensitive config and secrets. Cheaper than Secrets Manager.
- **Environment variables** — inject secrets as env vars at container/runtime level. Never in code.
- **IAM Roles** — for AWS services talking to each other (e.g., Glue reading S3), use IAM roles — no credentials needed at all.
- **Vault (HashiCorp)** — for multi-cloud or on-prem environments.

```python
import boto3

def get_secret(secret_name: str) -> str:
    client = boto3.client("secretsmanager", region_name="us-east-1")
    return client.get_secret_value(SecretId=secret_name)["SecretString"]

db_password = get_secret("prod/mydb/password")
```

Also: rotate secrets regularly, audit access logs, and use least-privilege IAM policies.

---

## 9. What is a data catalog? (e.g., AWS Glue Catalog, Apache Atlas)

A data catalog is a centralized inventory of all your data assets — tables, schemas, lineage, ownership, and descriptions. It answers the question: *"What data do we have, where is it, and what does it mean?"*

Key features:
- **Schema discovery** — automatically scans and registers table schemas.
- **Data lineage** — tracks where data came from and how it was transformed.
- **Search & discovery** — data consumers can find datasets without asking engineers.
- **Governance** — tag sensitive columns (PII), track ownership, enforce access policies.

Popular tools:
| Tool | Type |
|------|------|
| **AWS Glue Data Catalog** | Managed, integrates with Athena, Glue, Redshift Spectrum |
| **Apache Atlas** | Open-source, strong lineage and governance |
| **DataHub** (LinkedIn) | Open-source, modern UI, widely adopted |
| **Alation / Collibra** | Enterprise commercial tools |

In practice, I use the Glue Data Catalog as the metastore for all S3-based tables, queryable via Athena.

---

## 10. How do you do unit testing for data pipelines?

Testing data pipelines is critical — bad data is often worse than no data.

**For Python transformation logic** — use `pytest`:

```python
# pipeline.py
def clean_amount(value):
    return round(float(value), 2) if value else 0.0

# tests/test_pipeline.py
from pipeline import clean_amount

def test_clean_amount_valid():
    assert clean_amount("19.999") == 20.0

def test_clean_amount_none():
    assert clean_amount(None) == 0.0
```

**For PySpark jobs** — use `pyspark` with a local SparkSession in tests:

```python
from pyspark.sql import SparkSession

spark = SparkSession.builder.master("local").appName("test").getOrCreate()

def test_dedup():
    df = spark.createDataFrame([(1, "a"), (1, "a"), (2, "b")], ["id", "val"])
    result = df.dropDuplicates()
    assert result.count() == 2
```

**For dbt models** — use built-in dbt tests in YAML:

```yaml
models:
  - name: orders
    columns:
      - name: order_id
        tests:
          - unique
          - not_null
      - name: status
        tests:
          - accepted_values:
              values: ["pending", "shipped", "delivered"]
```

Run everything in CI so no untested code reaches production.

---
