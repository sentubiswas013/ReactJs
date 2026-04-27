## 🔹 1. Environment & Configuration

---

### 1. What is the difference between `application.properties` and `application.yml`?

Both serve the same purpose — externalized configuration. Difference is format:

```properties
# application.properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost/db
```

```yaml
# application.yml
server:
  port: 8080
spring:
  datasource:
    url: jdbc:mysql://localhost/db
```

- `.yml` is more readable for nested configs, supports lists natively
- `.properties` is simpler for flat key-value pairs
- Both can coexist; `.properties` takes precedence if both exist

---

### 2. How do you manage multiple environments (dev, stage, prod) in Spring Boot?

Create profile-specific files:

```
application.properties          ← common/default
application-dev.properties      ← dev overrides
application-stage.properties    ← stage overrides
application-prod.properties     ← prod overrides
```

Activate via:
```properties
# application.properties
spring.profiles.active=dev
```
Or via command line:
```bash
java -jar app.jar --spring.profiles.active=prod
```

---

### 3. What is `spring.profiles.active` and how does it work?

It tells Spring Boot which profile to activate. When set to `dev`, Spring loads:
1. `application.properties` (base)
2. `application-dev.properties` (overrides base values)

You can also use `@Profile` on beans:
```java
@Bean
@Profile("dev")
public DataSource devDataSource() { ... }
```

---

### 4. How does Spring Boot decide which configuration file to load?

Spring Boot loads config in this priority order (higher overrides lower):

1. Command-line args (`--server.port=9090`)
2. `SPRING_APPLICATION_JSON` env variable
3. OS environment variables
4. `application-{profile}.properties` (profile-specific)
5. `application.properties` (default)
6. `@PropertySource` annotations
7. Default values in code

File locations searched (in order):
```
./config/
./
classpath:/config/
classpath:/          ← lowest priority
```

---

### 5. What is centralized configuration in microservices?

When you have 10+ microservices, managing config per service is painful. Centralized config means one place holds all configs.

Solution: **Spring Cloud Config Server**

```
[Config Server] ←→ [Git Repo with all configs]
      ↑
[Service A] [Service B] [Service C]  ← all fetch config from Config Server
```

```yaml
# bootstrap.yml in each microservice
spring:
  config:
    import: "configserver:http://config-server:8888"
  application:
    name: order-service   # fetches order-service.yml from git
```

---

### 6. How do you use `@ConfigurationProperties` in Spring Boot?

Binds a group of related properties to a POJO — cleaner than injecting one-by-one with `@Value`.

```yaml
# application.yml
app:
  name: MyApp
  timeout: 5000
  retry: 3
```

```java
@ConfigurationProperties(prefix = "app")
@Component
public class AppConfig {
    private String name;
    private int timeout;
    private int retry;
    // getters + setters
}
```

Inject and use:
```java
@Autowired
private AppConfig appConfig;

appConfig.getTimeout(); // 5000
```

---

### 7. What is the use of prefix in configuration properties?

The `prefix` acts as a namespace — it maps only the matching keys from the config file to the POJO, avoiding conflicts.

```yaml
mail:
  host: smtp.gmail.com
  port: 587

db:
  host: localhost
  port: 5432
```

```java
@ConfigurationProperties(prefix = "mail")  // only binds mail.* keys
public class MailConfig {
    private String host;  // smtp.gmail.com
    private int port;     // 587
}

@ConfigurationProperties(prefix = "db")    // only binds db.* keys
public class DbConfig {
    private String host;  // localhost
    private int port;     // 5432
}
```

This avoids collision when multiple configs share the same field names like `host` or `port`.

### 8. How to remove default server from springboot application?
By default, Spring Boot comes with an embedded server like Apache Tomcat. If we want to remove it, we exclude the default starter dependency and optionally add another server like Jetty or Undertow.

```xml
// Remove Inbuild Server
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>

//Add New Server
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```