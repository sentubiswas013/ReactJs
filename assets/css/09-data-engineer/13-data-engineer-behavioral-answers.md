## 🟢 12. Behavioral & Scenario Questions

## 1. Tell me about a complex data pipeline you built from scratch.

Sure. At my previous role, we needed to ingest clickstream data from multiple web properties — roughly 50 million events per day — transform it, and make it available for the analytics team in near real-time.

I designed and built the full pipeline:
- **Ingestion** — Kafka producers on the web servers published events to Kafka topics partitioned by event type.
- **Streaming layer** — Spark Structured Streaming consumed from Kafka, validated and enriched events, and wrote clean Parquet files to S3 partitioned by date and hour.
- **Batch layer** — Airflow triggered dbt jobs every hour to aggregate the Parquet data into Redshift summary tables.
- **Monitoring** — CloudWatch alerts on Kafka consumer lag and Airflow task failures. PagerDuty for on-call escalation.

The hardest part was handling late-arriving events and schema drift from different web teams. I solved late arrivals with watermarking in Spark Streaming, and schema drift with a schema registry and validation step at ingestion.

It went from zero to production in 6 weeks and reduced the analytics team's reporting lag from 24 hours to under 2 hours.

---

## 2. How did you handle a production pipeline failure?

We had a critical pipeline that loaded daily sales data into Redshift for the finance team. One morning it failed silently — the job completed but loaded zero rows. Finance noticed the dashboards were blank at 9 AM.

My immediate steps:
1. **Triage first** — checked Airflow logs, found the S3 source file was there but had a different delimiter than expected. The upstream team had changed CSV format without notice.
2. **Communicated quickly** — sent a Slack message to stakeholders within 10 minutes: "Pipeline failed, root cause identified, ETA for fix is 30 minutes."
3. **Fixed and reran** — updated the delimiter config, reran the pipeline manually, verified row counts matched the source.
4. **Post-mortem** — added a schema validation check at ingestion so format changes are caught immediately, not silently. Also set up a data freshness alert so we'd know within 15 minutes if the table hadn't been updated.

The lesson: silent failures are worse than loud ones. Always validate output, not just job completion status.

---

## 3. Describe a time you improved pipeline performance significantly.

We had a daily Spark job that processed 200GB of transaction data. It was taking 4 hours to complete, which was blocking downstream jobs and causing SLA breaches.

I profiled the job and found three problems:
1. **Data skew** — one partition had 10x more data than others because we were partitioning by `country_code` and the US had 80% of records.
2. **Too many small files** — the output stage was writing thousands of tiny Parquet files, causing slow reads downstream.
3. **Unnecessary shuffles** — we were calling `groupBy` twice on the same key in different steps.

Fixes:
- Added **salting** to the skewed partition to distribute the US data evenly.
- Used `coalesce()` before writing to reduce output to a manageable number of files.
- Refactored the two `groupBy` calls into one combined aggregation.

Result: job runtime dropped from 4 hours to 45 minutes. That's an 80% improvement. Downstream SLA breaches stopped completely.

---

## 4. How do you prioritize tasks when multiple pipelines are failing?

I triage by business impact, not by technical complexity.

My mental framework:
1. **What's the blast radius?** — Is this affecting finance reporting, customer-facing features, or an internal analyst dashboard? Finance and customer-facing always come first.
2. **Is there a hard deadline?** — End-of-day reports, regulatory submissions, or SLA commitments get priority over best-effort pipelines.
3. **Can it be mitigated quickly?** — Sometimes a manual data pull or a quick rerun buys time while you fix the root cause properly.

In practice: I quickly assess all failing pipelines, rank by impact, communicate status to stakeholders for the top ones immediately, then work through them in order. I also loop in teammates if multiple critical pipelines need parallel attention.

The worst thing you can do is go heads-down on a low-priority fix while a business-critical pipeline is bleeding.

---

## 5. Tell me about a time you worked with stakeholders to define data requirements.

The marketing team came to me saying they needed a "customer 360 dashboard." That's a very broad ask. I've learned that vague requirements lead to rework, so I pushed for a proper requirements session.

I set up a 1-hour working session with the marketing lead, a business analyst, and a data analyst. I came prepared with questions:
- What decisions will this dashboard drive?
- What metrics matter most — acquisition, retention, LTV?
- How fresh does the data need to be — real-time, daily, weekly?
- What's the source of truth for customer data?

Through that conversation, the "customer 360" became a concrete list: 8 specific metrics, daily refresh, sourced from CRM + transaction DB + web analytics.

I documented the requirements in Confluence, got sign-off, and built exactly that. No scope creep, no surprises at delivery.

The lesson: always translate business language into specific, measurable, agreed-upon data requirements before writing a single line of code.

---

## 6. How do you ensure data quality in your pipelines?

Data quality is not a one-time check — it's built into every layer of the pipeline.

My approach:

- **At ingestion** — validate schema, check for nulls in required fields, reject or quarantine malformed records. Don't let bad data enter the system silently.
- **At transformation** — use dbt tests: `not_null`, `unique`, `accepted_values`, referential integrity checks. Run these on every deployment.
- **At the output layer** — row count reconciliation. Compare source record count vs loaded record count. Alert if there's a mismatch beyond a threshold.
- **Monitoring** — track data freshness (when was the table last updated?), volume anomalies (did we get 10x fewer records than usual?), and null rates over time.
- **Great Expectations** — for more complex validation, I use Great Expectations to define and run data quality suites as part of the pipeline.

The mindset shift is: treat data quality like software quality. You wouldn't ship code without tests. Don't ship data without validation.

---

## 7. Describe a time you had to learn a new technology quickly.

We were migrating from an on-premise Hadoop cluster to GCP, and the team decided to use **Apache Beam with Dataflow** for the streaming pipelines. I had never used Beam before — I had Spark experience, but Beam has a different programming model.

I had two weeks before I needed to deliver the first pipeline.

What I did:
- Spent the first two days going through the official Apache Beam documentation and running the quickstart examples locally.
- Found a real use case from our migration backlog and built a small end-to-end pipeline with it — learning by doing, not just reading.
- Joined the Apache Beam Slack community and asked specific questions when I got stuck.
- Paired with a colleague who had Dataflow experience for code review on my first PR.

By the end of week two, I had a working pipeline in production. By week four, I was the team's go-to person for Beam questions.

The key is: don't try to learn everything. Learn just enough to solve the problem in front of you, then go deeper as you go.

---

## 8. How do you document your data pipelines?

Documentation is something I treat as part of the work, not an afterthought.

My approach at different levels:

- **Code-level** — clear variable names, small focused functions, and inline comments only where the *why* isn't obvious. Code should be readable without a manual.
- **dbt models** — every model has a description in the YAML file. Every column that isn't self-explanatory has a description. `dbt docs generate` gives you a browsable data dictionary for free.
- **Pipeline-level** — a README in the repo for each pipeline: what it does, data sources, schedule, dependencies, how to run locally, and how to debug common failures.
- **Architecture diagrams** — for complex systems, a simple diagram in Confluence or draw.io showing data flow from source to destination. A picture is worth a thousand words.
- **Runbooks** — for production pipelines, a runbook that says: "If this alert fires, check X, then Y, then escalate to Z." This is invaluable at 2 AM.

Good documentation means the next engineer — or future you — can understand and operate the pipeline without asking anyone.

---

## 9. Tell me about a disagreement with a team member and how you resolved it.

We were designing a new data warehouse schema and I wanted to use a Star Schema for simplicity and query performance. A senior colleague strongly preferred Data Vault, arguing it was more scalable and audit-friendly.

Both positions had merit, so instead of debating opinions, I suggested we make it a data-driven decision.

We agreed to:
1. Define our actual requirements — how many source systems, how often schema changes, audit requirements, team SQL skill level.
2. Each of us prototype one approach against a real use case from our backlog.
3. Present both to the team and decide together.

After the prototypes, it was clear that for our scale — 3 source systems, a small team, no strict audit requirements — Star Schema was the right fit. Data Vault would have added complexity we didn't need yet.

My colleague agreed once we looked at it objectively. We documented the decision and the reasoning so future team members would understand why we chose what we chose.

The lesson: technical disagreements are best resolved with evidence and shared criteria, not seniority or volume.

---

## 10. Where do you see data engineering heading in the next 3 years?

A few clear trends I'm watching:

**1. The rise of the data lakehouse** — the line between data lakes and data warehouses is blurring. Formats like Delta Lake, Apache Iceberg, and Apache Hudi bring ACID transactions and schema evolution to object storage. Tools like Databricks and Snowflake are converging on this model.

**2. Streaming becomes the default** — batch pipelines are giving way to streaming-first architectures. Tools like Apache Flink, Kafka Streams, and Spark Structured Streaming are maturing fast. The expectation for data freshness is shifting from daily to minutes.

**3. AI-assisted data engineering** — LLMs are already being used to generate SQL, write dbt models, and explain data lineage. This won't replace data engineers but will shift the role toward higher-level design and governance rather than writing boilerplate transformations.

**4. Data mesh and decentralization** — large organizations are moving away from centralized data teams toward domain-owned data products. Data engineers will increasingly work embedded in product teams, owning their domain's data end-to-end.

**5. Tighter governance and compliance** — with GDPR, CCPA, and AI regulations expanding, data lineage, PII handling, and audit trails are becoming non-negotiable. Data catalogs and governance tooling will be standard, not optional.

The engineers who thrive will be the ones who combine strong fundamentals with the ability to adapt quickly as the tooling evolves.

---
