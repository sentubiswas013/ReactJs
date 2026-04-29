## What is Centralized Configuration?

Centralized configuration means storing all microservice configurations in a **single external location** (like a Git repo) instead of bundling them inside each service's JAR/WAR.

- All services fetch their config from a **Config Server** at startup
- Changes can be applied **without redeploying** services
- Supports environment-specific configs (dev, staging, prod)

---

**Step 1: Create Config Server**

**pom.xml dependency:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

**Main class:**
```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

**application.yml:**
```yaml
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-org/config-repo
          default-label: main
```

---

**Step 2: Create Git Config Repository**

Create a GitHub repo (e.g., `config-repo`) and add config files:

```
config-repo/
  order-service.yml
  order-service-dev.yml
  order-service-prod.yml
```

**order-service.yml:**
```yaml
app:
  message: "Hello from Config Server"
  timeout: 5000
```

---

**Step 3: Configure Client Microservice**

**pom.xml dependency:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

**application.yml (client):**
```yaml
spring:
  application:
    name: order-service        # must match filename in config-repo
  config:
    import: "configserver:http://localhost:8888"
  profiles:
    active: dev
```

---

**Step 4: Use Config Values in Code**

```java
@RestController
@RefreshScope   // enables runtime refresh without restart
public class OrderController {

    @Value("${app.message}")
    private String message;

    @GetMapping("/info")
    public String info() {
        return message;
    }
}
```

---

**Step 5: Dynamic Refresh (Without Restart)**

Add Actuator dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Expose refresh endpoint in client's `application.yml`:
```yaml
management:
  endpoints:
    web:
      exposure:
        include: refresh
```

Trigger refresh after config change:
```bash
POST http://localhost:8080/actuator/refresh
```

---

## Config Resolution Order

```
{app-name}-{profile}.yml  →  {app-name}.yml  →  application.yml
```

Example for `order-service` with `dev` profile:
1. `order-service-dev.yml`
2. `order-service.yml`
3. `application.yml`

---

## Interview Key Points

| Point | Answer |
|---|---|
| Default Config Server port | 8888 |
| Annotation on server | `@EnableConfigServer` |
| Annotation for runtime refresh | `@RefreshScope` |
| Config file naming | `{spring.application.name}-{profile}.yml` |
| Refresh without restart | `POST /actuator/refresh` |
| Bus refresh (all instances) | Spring Cloud Bus + Kafka/RabbitMQ |

---

## Common Interview Questions

**Q: What happens if Config Server is down at startup?**  
A: Service fails to start. Use `spring.cloud.config.fail-fast=true` to fail immediately, or `spring.cloud.config.retry.*` for retry logic.

**Q: How to secure Config Server?**  
A: Add Spring Security to Config Server, use Basic Auth or OAuth2. Encrypt sensitive values using `{cipher}` prefix with symmetric/asymmetric keys.

**Q: Difference between `@RefreshScope` and restart?**  
A: `@RefreshScope` re-initializes only the annotated bean at runtime via `/actuator/refresh`. Restart reloads the entire application context.

**Q: How to push config changes to all instances at once?**  
A: Use **Spring Cloud Bus** with a message broker (Kafka/RabbitMQ). One `POST /actuator/busrefresh` call propagates to all instances.
