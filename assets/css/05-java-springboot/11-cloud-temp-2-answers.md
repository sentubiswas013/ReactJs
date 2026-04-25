# Spring Boot – Cloud, Config & Database Interview Answers

---

## Advanced 🔥

---

### What is Vault (HashiCorp)?

HashiCorp Vault is a secrets management tool.
It stores sensitive data like passwords, API keys, and certificates securely.
Spring Boot integrates with it via `spring-cloud-vault`.
At startup, the app fetches secrets from Vault instead of hardcoding them in config files.

```yaml
# bootstrap.yml
spring:
  cloud:
    vault:
      host: localhost
      port: 8200
      token: my-token
      kv:
        enabled: true
```

---

### How does AWS Secrets Manager work?

AWS Secrets Manager stores and manages secrets in the cloud.
You store a secret (like DB credentials) in AWS, and your app fetches it at runtime using the AWS SDK or Spring Cloud AWS.

```java
// Using AWS SDK
SecretsManagerClient client = SecretsManagerClient.create();
GetSecretValueRequest request = GetSecretValueRequest.builder()
    .secretId("my-db-secret")
    .build();
String secret = client.getSecretValue(request).secretString();
```

Spring Cloud AWS can auto-inject secrets directly into `application.properties`.

---

### How do you rotate secrets?

Secret rotation means updating credentials periodically without downtime.

- In **AWS Secrets Manager**: enable automatic rotation with a Lambda function — AWS handles it.
- In **Vault**: set a TTL (time-to-live) on secrets; Vault auto-renews or rotates them.
- In Spring Boot: use `@RefreshScope` so beans reload when secrets change without restarting the app.

```java
@RefreshScope
@Component
public class DbConfig {
    @Value("${db.password}")
    private String password;
}
```

---

### How do you inject secrets in Kubernetes?

Two main ways:

1. **Kubernetes Secrets** — store base64-encoded values, inject as env vars or volume mounts.

```yaml
env:
  - name: DB_PASSWORD
    valueFrom:
      secretKeyRef:
        name: my-secret
        key: password
```

2. **Vault Agent Sidecar** — Vault injects secrets directly into the pod's filesystem at runtime. More secure because secrets never touch etcd in plain text.

Best practice: use Vault or AWS Secrets Manager over plain Kubernetes Secrets for production.

---

## Environment & Config

---

### What is application.properties vs application.yaml?

Both serve the same purpose — externalizing Spring Boot configuration.

- `application.properties` — flat key-value format, simple and widely used.
- `application.yaml` — hierarchical format, cleaner for nested config.

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost/mydb
spring.datasource.username=root
```

```yaml
# application.yaml
spring:
  datasource:
    url: jdbc:mysql://localhost/mydb
    username: root
```

YAML is preferred for complex configs. Both can coexist, but properties takes precedence if both define the same key.

---

### What are Spring profiles and profile groups?

**Profiles** let you define environment-specific config.

```yaml
# application-dev.yaml
spring:
  datasource:
    url: jdbc:mysql://dev-db/mydb
```

Activate with: `--spring.profiles.active=dev`

**Profile Groups** (Spring Boot 2.4+) let you activate multiple profiles with one name.

```yaml
spring:
  profiles:
    group:
      production:
        - proddb
        - prodmq
```

Activating `production` automatically activates `proddb` and `prodmq`.

---

### Difference: @Value vs @ConfigurationProperties

| | `@Value` | `@ConfigurationProperties` |
|---|---|---|
| Use case | Single property | Group of related properties |
| Type-safe | No | Yes |
| Validation | No | Yes (`@Validated`) |
| Refactor-friendly | No | Yes |

```java
// @Value — simple, one-off
@Value("${app.name}")
private String appName;

// @ConfigurationProperties — preferred for groups
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String name;
    private String version;
}
```

Use `@ConfigurationProperties` for anything more than one or two properties.

---

### What is the priority order of configuration in Spring Boot?

From highest to lowest priority:

1. Command-line arguments (`--server.port=8081`)
2. `SPRING_APPLICATION_JSON` (env variable)
3. OS environment variables
4. `application-{profile}.properties` / `.yaml`
5. `application.properties` / `.yaml` (inside jar)
6. `@PropertySource` annotations
7. Default values in code

So command-line args always win. Profile-specific files override the base `application.properties`.

---

### How to externalize configuration?

Keep config outside the jar so you don't rebuild for every environment.

Options:
- **Environment variables** — simplest, works everywhere
- **Config files outside jar** — Spring Boot auto-reads `./config/application.yaml`
- **Spring Cloud Config Server** — centralized config for microservices
- **AWS Parameter Store / Secrets Manager** — for cloud deployments

```bash
# Pass config at runtime
java -jar app.jar --spring.datasource.url=jdbc:mysql://prod-db/mydb
```

---

### How to manage secrets securely?

Never put secrets in code or Git.

- Use **environment variables** for simple setups
- Use **AWS Secrets Manager** or **HashiCorp Vault** for production
- Use **Kubernetes Secrets** (with encryption at rest enabled)
- Use **Spring Cloud Vault** to auto-inject secrets at startup
- Rotate secrets regularly and audit access

---

### How to handle multiple environments?

Use Spring profiles — one profile per environment.

```
application.yaml          ← shared/base config
application-dev.yaml      ← dev overrides
application-staging.yaml  ← staging overrides
application-prod.yaml     ← prod overrides
```

Activate the right profile at deploy time:

```bash
java -jar app.jar --spring.profiles.active=prod
```

Or via environment variable:

```bash
export SPRING_PROFILES_ACTIVE=prod
```

---

### How to handle multiple databases?

Define multiple `DataSource` beans, each with its own `EntityManagerFactory` and `TransactionManager`.

```java
@Bean
@Primary
@ConfigurationProperties("spring.datasource.primary")
public DataSource primaryDataSource() {
    return DataSourceBuilder.create().build();
}

@Bean
@ConfigurationProperties("spring.datasource.secondary")
public DataSource secondaryDataSource() {
    return DataSourceBuilder.create().build();
}
```

Use `@Primary` for the default one. Each repository package points to its own `EntityManagerFactory`.

---

## Database Scenarios

---

### How do you configure multiple databases in Spring Boot?

Create separate config classes for each database — each with its own `DataSource`, `EntityManagerFactory`, and `TransactionManager`.

```java
// PrimaryDbConfig.java
@Configuration
@EnableJpaRepositories(
    basePackages = "com.app.primary",
    entityManagerFactoryRef = "primaryEntityManager",
    transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDbConfig {
    @Bean @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
```

Repeat for secondary DB with different package and bean names.

---

### How do you route queries to different DBs dynamically?

Use `AbstractRoutingDataSource` — it picks the datasource at runtime based on a key.

```java
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType(); // "PRIMARY" or "SECONDARY"
    }
}
```

Set the context before each query:

```java
DbContextHolder.setDbType("SECONDARY");
// run query
DbContextHolder.clear();
```

Use a ThreadLocal to hold the current DB key.

---

### How do you handle read/write splitting?

Route write operations to the primary DB and reads to the replica.

```java
// In service layer
public void save(Entity e) {
    DbContextHolder.setDbType("PRIMARY"); // write
    repo.save(e);
}

public List<Entity> findAll() {
    DbContextHolder.setDbType("REPLICA"); // read
    return repo.findAll();
}
```

Or use Spring's `@Transactional(readOnly = true)` combined with `AbstractRoutingDataSource` to auto-route based on transaction type.

---

## Scenario-based Questions 🔥

---

### How do you manage dev, stage, prod environments?

Use Spring profiles with environment-specific yaml files.
Each environment gets its own file: `application-dev.yaml`, `application-staging.yaml`, `application-prod.yaml`.
Activate the profile via env variable or command-line arg at deploy time.
Sensitive values (passwords, keys) come from environment variables or secrets manager — never hardcoded.

---

### How to switch DB between dev and prod?

Each profile file has its own datasource config:

```yaml
# application-dev.yaml
spring:
  datasource:
    url: jdbc:h2:mem:devdb

# application-prod.yaml
spring:
  datasource:
    url: jdbc:mysql://prod-host/mydb
    username: ${DB_USER}
    password: ${DB_PASS}
```

Switch by activating the right profile. Prod credentials come from environment variables, not the yaml file.

---

### What happens if DB goes down?

- Active requests fail with a `DataAccessException` — handle it gracefully with proper error responses.
- Connection pool (HikariCP) keeps retrying to get a connection up to `connectionTimeout`.
- If the pool is exhausted, new requests fail fast.
- Use circuit breaker (Resilience4j) to stop hammering a dead DB.
- Health checks (`/actuator/health`) will show DB as DOWN.

---

### How to retry DB connections?

Use Spring Retry or Resilience4j.

```java
// With Spring Retry
@Retryable(value = DataAccessException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
public void saveData(Entity e) {
    repo.save(e);
}
```

For startup connection retry, configure HikariCP:

```yaml
spring:
  datasource:
    hikari:
      connection-timeout: 30000
      initialization-fail-timeout: 60000
```

For Spring Boot startup, use `spring.datasource.hikari.initialization-fail-timeout=-1` to keep retrying indefinitely.

---

### How to secure DB credentials in production?

- Never put credentials in `application.properties` committed to Git.
- Use **environment variables**: `${DB_USER}`, `${DB_PASS}` in yaml.
- Use **AWS Secrets Manager** or **Vault** to fetch credentials at runtime.
- Enable **SSL/TLS** for DB connections.
- Use a **dedicated DB user** with least-privilege access — no root.
- Rotate credentials regularly.

---

## Real-world

---

### How do you deploy the same code to multiple environments?

Build once, deploy everywhere — the jar doesn't change between environments.

- Externalize all environment-specific config (URLs, credentials, feature flags).
- Use Spring profiles activated via `SPRING_PROFILES_ACTIVE` env variable.
- In CI/CD (Jenkins, GitHub Actions), pass the profile and secrets as environment variables at deploy time.
- Use Docker: same image, different env vars per environment.

```bash
# Dev
docker run -e SPRING_PROFILES_ACTIVE=dev app.jar

# Prod
docker run -e SPRING_PROFILES_ACTIVE=prod -e DB_PASS=secret app.jar
```

---

### How do you avoid config duplication?

- Put **shared/common config** in `application.yaml`.
- Put only **overrides** in profile-specific files (`application-dev.yaml`, `application-prod.yaml`).
- Spring merges them — profile file overrides base file for matching keys.
- Use **Spring Cloud Config Server** for microservices — one central repo for all app configs, shared config in `application.yaml`, app-specific in `{app-name}.yaml`.

```yaml
# application.yaml (shared)
server:
  port: 8080
logging:
  level:
    root: INFO

# application-prod.yaml (only what changes)
logging:
  level:
    root: WARN
spring:
  datasource:
    url: jdbc:mysql://prod-db/mydb
```

---
