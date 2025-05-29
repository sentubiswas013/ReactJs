Here is a list of **75 Spring Boot interview questions** that cover a wide range of topics including core concepts, configuration, security, testing, and microservices:

### Basic Spring Boot Questions:
Here are the answers to the Spring Boot questions:

### 1. What is Spring Boot, and why is it used?
**Spring Boot** is an open-source Java-based framework used to create stand-alone, production-grade Spring-based applications. It simplifies the setup and configuration of Spring applications, reducing the complexity of development. It is often used to quickly build microservices or web applications with minimal setup.

### 2. What are the main features of Spring Boot?
- **Auto-Configuration:** Automatically configures Spring application based on the libraries available in the classpath.
- **Standalone:** Allows developers to create stand-alone applications with an embedded web server (e.g., Tomcat, Jetty).
- **Production-Ready Features:** Provides built-in features like metrics, health checks, and externalized configuration.
- **Embedded Server Support:** Eliminates the need to deploy applications to a web server.
- **Minimal Configuration:** Reduces the need for boilerplate XML or Java configuration.
- **Spring Boot Starters:** A set of pre-configured templates to integrate commonly used libraries into the project.

### 3. How does Spring Boot differ from the traditional Spring Framework?
- **Configuration:** In traditional Spring, a lot of XML-based configuration is required, while Spring Boot reduces this by providing **auto-configuration** and **convention over configuration** principles.
- **Setup and Deployment:** Spring Boot applications are stand-alone, with embedded servers (e.g., Tomcat), while traditional Spring applications require deployment to an external application server.
- **Ease of Use:** Spring Boot provides a more simplified approach with **Spring Boot starters** and **Spring Boot CLI**, reducing manual configuration and setup compared to traditional Spring.

### 4. What is the purpose of `@SpringBootApplication` annotation?
`@SpringBootApplication` is a convenience annotation that combines three important annotations:
- `@SpringBootConfiguration`: Marks the class as a configuration class.
- `@EnableAutoConfiguration`: Enables Spring Boot’s auto-configuration feature.
- `@ComponentScan`: Tells Spring to scan for components, configurations, and services in the same package or sub-packages.

This annotation is typically used in the main application class to bootstrap a Spring Boot application.

### 5. What are Spring Boot starters? Give some examples.
**Spring Boot starters** are a set of pre-configured dependencies that simplify the process of integrating common libraries or components into a Spring Boot project. Examples include:
- **spring-boot-starter-web:** For building web applications with RESTful services (includes Tomcat, Spring MVC, and JSON converters).
- **spring-boot-starter-data-jpa:** For integrating Spring Data JPA with Hibernate.
- **spring-boot-starter-test:** Provides testing support using libraries like JUnit and Mockito.
- **spring-boot-starter-security:** For adding security functionality like authentication and authorization.

### 6. What is the difference between `application.properties` and `application.yml` files in Spring Boot?
Both `application.properties` and `application.yml` are used for externalizing configuration in Spring Boot, but they differ in format:
- **`application.properties`:** A simpler, flat key-value pair configuration format.
  Example:
  ```properties
  server.port=8081
  spring.datasource.url=jdbc:mysql://localhost:3306/mydb
  ```
- **`application.yml`:** A more structured, hierarchical format based on YAML syntax, supporting nested properties.
  Example:
  ```yaml
  server:
    port: 8081
  spring:
    datasource:
      url: jdbc:mysql://localhost:3306/mydb
  ```
YAML is often preferred for complex configurations as it is more readable and supports hierarchical data structures.

### 7. What is the role of `@EnableAutoConfiguration` in Spring Boot?
`@EnableAutoConfiguration` is an annotation in Spring Boot that tells the framework to automatically configure application components based on the dependencies present on the classpath. For example, if Spring Boot detects that you're using a web framework, it will configure embedded Tomcat. This reduces the need for manual configuration and allows Spring Boot to intelligently configure your application.

### 8. How do you create a Spring Boot application from scratch?
To create a Spring Boot application from scratch:
1. **Create a new project**: Use [Spring Initializr](https://start.spring.io/) to generate a base project or create it manually with dependencies like Spring Web or Spring Data JPA.
2. **Add dependencies**: In the `pom.xml` (for Maven) or `build.gradle` (for Gradle), add necessary Spring Boot starters (e.g., `spring-boot-starter-web`).
3. **Create the main application class**: Annotate it with `@SpringBootApplication` and define the `main` method to run the application.
   ```java
   @SpringBootApplication
   public class MyApplication {
       public static void main(String[] args) {
           SpringApplication.run(MyApplication.class, args);
       }
   }
   ```
4. **Run the application**: Use your IDE or `mvn spring-boot:run` or `gradle bootRun` from the command line.

### 9. What are some of the commonly used Spring Boot starters?
Some commonly used Spring Boot starters include:
- **spring-boot-starter-web**: For web applications, including RESTful APIs.
- **spring-boot-starter-data-jpa**: For applications using JPA (e.g., Hibernate).
- **spring-boot-starter-thymeleaf**: For web applications using Thymeleaf as a template engine.
- **spring-boot-starter-test**: For testing with JUnit, Mockito, etc.
- **spring-boot-starter-logging**: For logging using Logback or SLF4J.
- **spring-boot-starter-security**: For adding security features.

### 10. How can you run a Spring Boot application from the command line?
You can run a Spring Boot application from the command line in the following ways:
- **Using Maven**:
  ```bash
  mvn spring-boot:run
  ```
- **Using Gradle**:
  ```bash
  gradle bootRun
  ```
- **Running the packaged JAR file** (after building with Maven or Gradle):
  ```bash
  java -jar target/myapp.jar
  ```

This will start the embedded web server (such as Tomcat) and launch the Spring Boot application.



### Spring Boot Configuration:
Here are the answers to the next set of Spring Boot questions:

### 11. How can you externalize configuration in Spring Boot?
In Spring Boot, configuration can be externalized in several ways:
- **`application.properties` or `application.yml`:** These are the default configuration files where you can define application-specific settings like server ports, database configurations, etc.
- **Command-line arguments:** Spring Boot allows you to override properties through command-line arguments when starting the application.
  ```bash
  java -jar myapp.jar --server.port=8081
  ```
- **Environment variables:** You can set properties via environment variables.
- **External configuration files:** You can specify external configuration files using the `spring.config.location` property.
  ```bash
  java -jar myapp.jar --spring.config.location=classpath:/config/application.yml
  ```
- **Spring Cloud Config:** For distributed systems or microservices, Spring Cloud Config can be used to manage configurations in a central repository.

### 12. What are profiles in Spring Boot, and how are they used?
**Profiles** in Spring Boot allow you to segregate configuration and beans for different environments (e.g., development, testing, production). You can define different sets of configurations based on the active profile:
- Profiles are specified in the `application.properties` or `application.yml` file.
- You can set the active profile using:
  - In `application.properties`:
    ```properties
    spring.profiles.active=dev
    ```
  - Or through command-line arguments:
    ```bash
    java -jar myapp.jar --spring.profiles.active=dev
    ```
- Profiles allow Spring Boot to load different configurations or beans based on the environment.

### 13. How do you inject properties from `application.properties` into Spring Beans?
You can inject properties from `application.properties` into Spring beans using the `@Value` annotation or `@ConfigurationProperties`.
- **Using `@Value`:**
  ```java
  @Value("${server.port}")
  private int port;
  ```
- **Using `@ConfigurationProperties`:**
  This allows you to bind multiple properties to a POJO.
  ```java
  @ConfigurationProperties(prefix = "server")
  public class ServerConfig {
      private int port;
      // getters and setters
  }
  ```

### 14. Explain the difference between `@Value` and `@ConfigurationProperties`.
- **`@Value`**: 
  - It is used for injecting individual property values into Spring beans.
  - It works well for simple properties.
  - Example:
    ```java
    @Value("${server.port}")
    private int port;
    ```
- **`@ConfigurationProperties`**:
  - It is used to bind a group of properties to a POJO class.
  - It is more suitable for complex or hierarchical configurations.
  - You need to enable it by adding `@EnableConfigurationProperties` on a configuration class or on the main class.
  - Example:
    ```java
    @ConfigurationProperties(prefix = "server")
    public class ServerConfig {
        private int port;
        // getters and setters
    }
    ```

### 15. How do you manage multiple configuration files in Spring Boot?
Spring Boot allows you to manage multiple configuration files through profiles and externalized configuration:
- Use multiple `application-{profile}.properties` or `application-{profile}.yml` files, where `{profile}` is the name of the active profile (e.g., `application-dev.properties`).
- Spring Boot will load the appropriate configuration file based on the active profile.
  - For example, if `spring.profiles.active=dev` is set, Spring Boot will load `application-dev.properties` and combine it with the main `application.properties` file.
- You can specify an external configuration file using the `spring.config.location` property.

### 16. How can you create a custom configuration in Spring Boot?
To create a custom configuration in Spring Boot:
1. **Define a configuration class**: Annotate it with `@Configuration` and define beans inside it.
   ```java
   @Configuration
   public class MyAppConfig {
       @Bean
       public MyBean myBean() {
           return new MyBean();
       }
   }
   ```
2. **Externalize properties**: You can create a `@ConfigurationProperties` class to bind properties from `application.properties` or `application.yml`.
   ```java
   @ConfigurationProperties(prefix = "myapp")
   public class MyAppConfigProperties {
       private String name;
       private int timeout;
       // getters and setters
   }
   ```

### 17. What is the purpose of `application.properties` or `application.yml` files in Spring Boot?
The **`application.properties`** or **`application.yml`** files are used for externalizing the configuration of a Spring Boot application. They allow you to specify configuration settings such as:
- Server settings (e.g., port, context path)
- Data source configurations (e.g., database URL, username, password)
- Logging configurations
- Security configurations

Spring Boot loads these files automatically at runtime to configure the application.

### 18. How do you configure logging in Spring Boot?
Spring Boot uses **Spring Boot Logging** (based on Logback by default) to handle logging. You can configure logging in `application.properties` or `application.yml`.
- **Basic Logging Configuration**:
  ```properties
  logging.level.org.springframework.web=DEBUG
  logging.level.com.example=INFO
  ```
  This will set the logging level for specific packages.
- **Using `logback-spring.xml`**: You can create a custom `logback-spring.xml` file to define more complex logging configurations, such as log file locations, formats, and appenders.
- **Change log level in `application.yml`**:
  ```yaml
  logging:
    level:
      org.springframework.web: DEBUG
  ```

### 19. How can you configure different profiles for different environments (e.g., dev, prod)?
Spring Boot allows you to configure different profiles for different environments through the use of profile-specific properties:
- Create separate `application-{profile}.properties` or `application-{profile}.yml` files:
  - `application-dev.properties`
  - `application-prod.properties`
- You can activate profiles using:
  - In `application.properties`:
    ```properties
    spring.profiles.active=dev
    ```
  - Or using the command line:
    ```bash
    java -jar myapp.jar --spring.profiles.active=prod
    ```

### 20. How would you set up a logging level for Spring Boot (e.g., debug, info, error)?
To set the logging level in Spring Boot, you can specify it in the `application.properties` or `application.yml` file. Example:
- In `application.properties`:
  ```properties
  logging.level.root=INFO
  logging.level.org.springframework.web=DEBUG
  logging.level.com.example=ERROR
  ```
- In `application.yml`:
  ```yaml
  logging:
    level:
      root: INFO
      org.springframework.web: DEBUG
      com.example: ERROR
  ```
You can use levels such as `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, or `FATAL`.

### Auto Configuration in Spring Boot:
Here are the answers to your Spring Boot questions about auto-configuration:

### 21. What is Spring Boot auto-configuration, and how does it work?
**Spring Boot auto-configuration** is a mechanism that automatically configures Spring application components based on the dependencies present in the classpath. It tries to automatically guess and configure components (like databases, messaging, or web servers) based on the environment and libraries available in the project, reducing the need for manual configuration.
- Auto-configuration is provided by Spring Boot through `@EnableAutoConfiguration` (or `@SpringBootApplication`, which includes `@EnableAutoConfiguration`).
- When the application starts, Spring Boot looks for specific conditions (like the presence of certain classes or libraries in the classpath) and applies configurations accordingly.
- For example, if Spring Boot detects that `spring-boot-starter-web` is on the classpath, it will automatically configure Tomcat and Spring MVC.

### 22. How can you disable specific auto-configurations in Spring Boot?
You can disable specific auto-configurations in Spring Boot by using the `@EnableAutoConfiguration` annotation with the `exclude` attribute or by setting properties in the `application.properties` file.
- **Using `@SpringBootApplication`** with the `exclude` attribute:
  ```java
  @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
  public class MyApp {
      public static void main(String[] args) {
          SpringApplication.run(MyApp.class, args);
      }
  }
  ```
  In this example, the auto-configuration for `DataSource` is disabled.
- **Using `application.properties`**:
  ```properties
  spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
  ```

### 23. How do you create a custom auto-configuration in Spring Boot?
To create a custom auto-configuration in Spring Boot, follow these steps:
1. **Create the configuration class**:
   - Create a configuration class and annotate it with `@Configuration` and `@ConditionalOnClass` (or other `@Conditional` annotations as needed).
   - Use `@EnableAutoConfiguration` or `@AutoConfiguration` annotations in the class.
2. **Create a service or bean** that should be automatically configured.
3. **Provide an `META-INF/spring.factories` file** to register your auto-configuration class.
   - In `META-INF/spring.factories`, add an entry for the auto-configuration class.
     ```properties
     org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.example.MyAutoConfiguration
     ```
4. **Ensure the condition for auto-configuration**:
   Use annotations like `@ConditionalOnClass`, `@ConditionalOnBean`, `@ConditionalOnMissingBean`, etc., to specify when the configuration should be applied.

Example of a custom auto-configuration:
```java
@Configuration
@ConditionalOnClass(MyService.class)
public class MyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MyService myService() {
        return new MyService();
    }
}
```

### 24. What is `@ConditionalOnProperty` in Spring Boot?
`@ConditionalOnProperty` is a Spring Boot condition used to enable or disable a bean based on a specific property value in `application.properties` or `application.yml`.
- It is commonly used in custom auto-configuration classes to conditionally load beans if a specific property is set or matches a given value.
- Example:
  ```java
  @Configuration
  @ConditionalOnProperty(name = "myapp.feature.enabled", havingValue = "true")
  public class FeatureConfiguration {
  
      @Bean
      public MyFeatureService featureService() {
          return new MyFeatureService();
      }
  }
  ```
  In this example, the `FeatureConfiguration` bean is only created if the property `myapp.feature.enabled=true` is defined in the configuration file.

### 25. Explain `@ConditionalOnClass` and `@ConditionalOnMissingBean` in Spring Boot auto-configuration.
- **`@ConditionalOnClass`**:
  - It is used to conditionally create a bean only if a specific class is present on the classpath.
  - This is useful for auto-configurations that require the presence of a certain library to function.
  - Example:
    ```java
    @Configuration
    @ConditionalOnClass(DataSource.class)
    public class DataSourceAutoConfiguration {
        @Bean
        public DataSource dataSource() {
            return new DataSource();
        }
    }
    ```
    In this example, the `DataSourceAutoConfiguration` is only enabled if `DataSource` class is available in the classpath.

- **`@ConditionalOnMissingBean`**:
  - This annotation ensures that a bean is only created if no bean of the same type is already defined in the Spring context.
  - It is often used in auto-configuration to ensure that the default bean is created only if a custom bean is not already present.
  - Example:
    ```java
    @Configuration
    public class MyAutoConfiguration {
  
        @Bean
        @ConditionalOnMissingBean
        public MyService myService() {
            return new MyService();
        }
    }
    ```
    In this example, `MyService` will be created only if no other `MyService` bean is already present in the application context.

These conditions are part of Spring Boot's powerful auto-configuration mechanism, allowing beans to be conditionally created based on various factors like classpath availability, properties, or the presence of other beans.

### Spring Boot Annotations:
Here are the answers to the additional Spring Boot questions:

### 26. What is the role of `@RestController` and `@Controller` in Spring Boot?
- **`@RestController`**:
  - It is a specialized version of the `@Controller` annotation, used to define a controller where each method is automatically treated as a `@ResponseBody`. 
  - It is typically used in RESTful web services where the response is returned as JSON or XML, and there's no need to return a view (like an HTML page).
  - Example:
    ```java
    @RestController
    public class MyController {
        @GetMapping("/hello")
        public String hello() {
            return "Hello, World!";
        }
    }
    ```
- **`@Controller`**:
  - It is used for web applications with views (such as JSP or Thymeleaf). It can handle HTTP requests and return views (HTML pages).
  - The methods in `@Controller` typically return view names or templates, and you need to combine it with `@ResponseBody` or a view resolver to render content.
  - Example:
    ```java
    @Controller
    public class MyController {
        @GetMapping("/home")
        public String home(Model model) {
            model.addAttribute("message", "Hello, World!");
            return "home";  // Will render the 'home' view
        }
    }
    ```

### 27. How does `@RequestMapping` differ from `@GetMapping`, `@PostMapping`, etc.?
- **`@RequestMapping`**:
  - It is a generic annotation used to map HTTP requests to handler methods of MVC controllers. It can be used for any HTTP method (GET, POST, PUT, DELETE, etc.).
  - Example:
    ```java
    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }
    ```
- **`@GetMapping`, `@PostMapping`, etc.**:
  - These are specialized versions of `@RequestMapping` that are used to handle specific HTTP methods.
  - **`@GetMapping`**: Maps HTTP GET requests to handler methods.
  - **`@PostMapping`**: Maps HTTP POST requests to handler methods.
  - **`@PutMapping`**: Maps HTTP PUT requests to handler methods.
  - **`@DeleteMapping`**: Maps HTTP DELETE requests to handler methods.
  - They are more concise and semantically clear for defining RESTful services.
  - Example:
    ```java
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
    @PostMapping("/hello")
    public String createHello() {
        return "Hello Created";
    }
    ```

### 28. What is the difference between `@RequestParam` and `@PathVariable`?
- **`@RequestParam`**:
  - It is used to extract query parameters or form data from the URL.
  - Example:
    ```java
    @GetMapping("/greet")
    public String greet(@RequestParam(name = "name") String name) {
        return "Hello, " + name;
    }
    ```
    In the URL `http://example.com/greet?name=John`, the value of `name` will be passed as a method parameter.
  
- **`@PathVariable`**:
  - It is used to extract values from the URI path itself, typically in RESTful URLs.
  - Example:
    ```java
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "Hello, " + name;
    }
    ```
    In the URL `http://example.com/greet/John`, `name` will be extracted from the path.

### 29. What are the key uses of `@Component`, `@Service`, `@Repository`, and `@Controller` annotations?
- **`@Component`**: 
  - A generic annotation for defining a Spring bean. It is the parent annotation for other specialized annotations.
  - Example:
    ```java
    @Component
    public class MyComponent {
    }
    ```

- **`@Service`**: 
  - A specialization of `@Component`, used to define a service layer bean. It is typically used in the business logic layer.
  - Example:
    ```java
    @Service
    public class MyService {
    }
    ```

- **`@Repository`**: 
  - A specialization of `@Component`, used to define a DAO (Data Access Object) bean. It is commonly used for database interactions.
  - Example:
    ```java
    @Repository
    public class MyRepository {
    }
    ```

- **`@Controller`**: 
  - Used to define a controller that handles web requests in a Spring MVC application. It is typically used for web applications.
  - Example:
    ```java
    @Controller
    public class MyController {
    }
    ```

### 30. What is the purpose of `@Autowired` in Spring Boot?
The `@Autowired` annotation is used for **dependency injection** in Spring. It tells Spring to automatically inject the dependency into the field, constructor, or setter method. This reduces the need for manual configuration of dependencies.
- **Field Injection**:
  ```java
  @Autowired
  private MyService myService;
  ```
- **Constructor Injection** (preferred):
  ```java
  @Autowired
  public MyController(MyService myService) {
      this.myService = myService;
  }
  ```

### 31. How does Spring Boot handle dependency injection?
Spring Boot uses the **Spring Framework's dependency injection** (DI) mechanism. The two main types of DI are:
- **Constructor Injection**: The recommended approach for injecting dependencies. Spring automatically injects the required dependencies into the constructor.
- **Field Injection**: Spring injects dependencies directly into fields, using the `@Autowired` annotation.
- **Setter Injection**: Spring injects dependencies through setter methods.
Spring Boot handles this automatically by scanning for components and injecting beans where necessary, based on the configuration.

### 32. What is the purpose of `@Value` annotation in Spring Boot?
The `@Value` annotation is used to inject values from property files (`application.properties` or `application.yml`) or environment variables into Spring beans.
- Example:
  ```java
  @Value("${app.name}")
  private String appName;
  ```
  This will inject the value of `app.name` from `application.properties` or `application.yml` into the `appName` field.

### 33. Explain `@Bean` and `@Configuration` in Spring Boot.
- **`@Bean`**: 
  - It is used to define a bean inside a configuration class. This annotation tells Spring to manage the lifecycle of the object and make it available for dependency injection.
  - Example:
    ```java
    @Configuration
    public class AppConfig {
        @Bean
        public MyService myService() {
            return new MyService();
        }
    }
    ```

- **`@Configuration`**:
  - It is used to indicate that a class contains Spring configuration. It is a specialized version of `@Component`, and any beans defined with `@Bean` in that class will be registered in the Spring container.
  - Example:
    ```java
    @Configuration
    public class MyConfiguration {
    }
    ```

### 34. What is `@PostConstruct` and `@PreDestroy` in Spring Boot?
- **`@PostConstruct`**: 
  - This annotation is used to mark a method that should be executed after the bean’s initialization. It is often used for custom setup or initialization logic after the bean is created.
  - Example:
    ```java
    @PostConstruct
    public void init() {
        // Initialization logic
    }
    ```

- **`@PreDestroy`**:
  - This annotation is used to mark a method that should be executed before the bean is destroyed (i.e., when the application context is shut down).
  - Example:
    ```java
    @PreDestroy
    public void cleanup() {
        // Cleanup logic
    }
    ```

### 35. What is the `@Scope` annotation in Spring Boot?
The `@Scope` annotation is used to define the scope of a Spring bean. It determines the lifecycle and visibility of the bean within the application context. Common scopes are:
- **`@Scope("singleton")`**: The default scope; only one instance of the bean is created, shared across the entire application.
- **`@Scope("prototype")`**: A new instance of the bean is created every time it is requested.
- **`@Scope("request")`**: A new instance is created for each HTTP request (typically used in web applications).
- **`@Scope("session")`**: A new instance is created for each HTTP session.

Example:
```java
@Scope("prototype")
@Component
public class MyPrototypeBean {
}
```

### Dependency Injection and Spring Beans:
Here are the answers to the additional Spring Boot questions:

### 36. How do you manage Spring Beans in Spring Boot?
Spring Beans in Spring Boot are managed through the **Spring IoC (Inversion of Control) container**, which is responsible for their lifecycle, dependencies, and scopes. You manage beans in Spring Boot by:
- **Component Scanning**: Annotating classes with `@Component`, `@Service`, `@Repository`, or `@Controller` makes them eligible for component scanning, allowing Spring to detect and manage them as beans.
- **Java Config**: Using `@Configuration` and `@Bean` annotations to define beans manually.
- **Autowiring**: Using `@Autowired` to inject dependencies into beans.
- **Profiles**: Using `@Profile` to conditionally register beans based on environment or configuration profiles.

### 37. What is the default bean scope in Spring Boot?
The default scope for beans in Spring Boot is **singleton**. This means that a single instance of the bean is created and shared across the entire Spring application context. Each time the bean is injected, Spring will provide the same instance.

### 38. Explain the difference between `@Singleton`, `@Prototype`, and `@RequestScope`.
- **`@Singleton`** (default in Spring):
  - Only one instance of the bean is created throughout the entire Spring container. The same instance is shared wherever the bean is injected.
  - Example:
    ```java
    @Scope("singleton")
    @Component
    public class SingletonBean { }
    ```
- **`@Prototype`**:
  - A new instance of the bean is created every time it is requested from the Spring container. This scope is useful for beans that are stateful or when you want a fresh instance on each request.
  - Example:
    ```java
    @Scope("prototype")
    @Component
    public class PrototypeBean { }
    ```
- **`@RequestScope`**:
  - A new instance of the bean is created for each HTTP request. This scope is typically used in web applications, and each request gets its own instance.
  - Example:
    ```java
    @Scope("request")
    @Component
    public class RequestScopedBean { }
    ```

### 39. How does Spring Boot handle the lifecycle of beans?
Spring Boot handles the lifecycle of beans through the **Spring IoC container**, which follows these general steps:
1. **Instantiation**: Spring creates an instance of a bean as per the configuration (either via annotations like `@Component`, `@Service`, `@Repository`, or manual configuration).
2. **Dependency Injection**: Spring injects any required dependencies into the bean via constructors, setters, or fields (using `@Autowired`).
3. **Post-Processing**: Spring performs any post-processing through `BeanPostProcessor` or `@PostConstruct` methods after the bean is created.
4. **Initialization**: Any initialization logic, such as `@PostConstruct` or `@InitMethod`, is executed.
5. **Usage**: The bean is used in the application wherever it is injected.
6. **Destruction**: When the context is closed or the bean is no longer required, Spring performs cleanup via `@PreDestroy` or `DisposableBean` if applicable.

### 40. What are `@Primary` and `@Qualifier` annotations used for?
- **`@Primary`**:
  - This annotation is used when there are multiple beans of the same type, and you want Spring to automatically choose one as the default bean to be injected when no specific bean is mentioned.
  - Example:
    ```java
    @Primary
    @Component
    public class MyPrimaryService implements MyService { }
    ```
- **`@Qualifier`**:
  - It is used to specify which bean to inject when multiple beans of the same type are available in the application context.
  - Example:
    ```java
    @Autowired
    @Qualifier("myService1")
    private MyService myService;
    ```

### 41. How do you manage circular dependencies in Spring Boot?
Spring Boot, like Spring Framework, can manage circular dependencies through **constructor injection** or **setter injection**, but constructor injection requires special attention in the case of circular dependencies.
- **Setter Injection**: Spring can resolve circular dependencies more easily with setter injection, as the beans can be injected after their creation.
  - Example:
    ```java
    @Component
    public class A {
        private B b;
        @Autowired
        public void setB(B b) {
            this.b = b;
        }
    }

    @Component
    public class B {
        private A a;
        @Autowired
        public void setA(A a) {
            this.a = a;
        }
    }
    ```
- **Constructor Injection**: Circular dependencies are not allowed with constructor injection by default because the constructor parameters cannot be resolved if they form a cycle. However, it can be resolved by using `@Lazy` on one of the dependencies to break the cycle.
  - Example with `@Lazy`:
    ```java
    @Component
    public class A {
        private final B b;

        @Autowired
        public A(@Lazy B b) {
            this.b = b;
        }
    }

    @Component
    public class B {
        private final A a;

        @Autowired
        public B(@Lazy A a) {
            this.a = a;
        }
    }
    ```

Using `@Lazy` ensures that the dependencies are resolved only when they are actually needed, thus preventing the circular dependency problem from blocking bean initialization.

### Spring Boot Data & Persistence:
Here are the answers to the Spring Boot questions related to databases and JPA:

### 42. How do you connect Spring Boot to a relational database?
To connect Spring Boot to a relational database, follow these steps:
1. **Add dependencies**: In your `pom.xml` (for Maven) or `build.gradle` (for Gradle), include dependencies like `spring-boot-starter-data-jpa` and a JDBC driver for your database (e.g., `spring-boot-starter-data-mysql` for MySQL).
   - Example (Maven):
     ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-data-jpa</artifactId>
     </dependency>
     <dependency>
         <groupId>mysql</groupId>
         <artifactId>mysql-connector-java</artifactId>
     </dependency>
     ```

2. **Configure database properties**: Add database connection details in the `application.properties` or `application.yml` file.
   - Example (`application.properties`):
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase
     spring.datasource.username=root
     spring.datasource.password=rootpassword
     spring.jpa.hibernate.ddl-auto=update
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     ```

3. **Define JPA entities**: Create Java classes that represent database tables using JPA annotations.

4. **Repository Layer**: Use Spring Data JPA repositories (`CrudRepository`, `JpaRepository`, etc.) to interact with the database.

### 43. What is Spring Data JPA, and how does it integrate with Spring Boot?
**Spring Data JPA** is a part of the Spring Data project that simplifies database access by using JPA (Java Persistence API) to interact with relational databases. It provides:
- **Repository support**: Allows you to create repositories for your entities without writing boilerplate code.
- **Automatic queries**: You can define methods in the repository interface, and Spring Data JPA automatically implements them.
- **Integration with Spring Boot**: Spring Boot simplifies configuration with automatic setup of data source and JPA configuration when you add the `spring-boot-starter-data-jpa` dependency.

Example:
```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastName(String lastName);
}
```

### 44. What is the difference between `@OneToMany` and `@ManyToOne` in Spring Boot?
- **`@OneToMany`**:
  - Specifies a one-to-many relationship where one entity is related to many other entities.
  - Example: A `Customer` can have many `Orders`.
  - The `@OneToMany` annotation is placed on the "one" side of the relationship.
  - Example:
    ```java
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
    ```
- **`@ManyToOne`**:
  - Specifies a many-to-one relationship where many entities are related to a single entity.
  - Example: Many `Orders` belong to one `Customer`.
  - The `@ManyToOne` annotation is placed on the "many" side of the relationship.
  - Example:
    ```java
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    ```

### 45. How can you configure pagination and sorting in Spring Boot with Spring Data JPA?
Spring Data JPA provides easy ways to add pagination and sorting through `Pageable` and `Sort` objects:
- **Pagination**: Use `Pageable` to specify the page number and size.
- **Sorting**: Use `Sort` to specify sorting criteria.

Example:
```java
public Page<User> findByLastName(String lastName, Pageable pageable);
```
Usage:
```java
Pageable pageable = PageRequest.of(0, 10, Sort.by("lastName").ascending());
Page<User> users = userRepository.findByLastName("Smith", pageable);
```

### 46. What is `@Entity` and `@Table` in Spring Boot JPA?
- **`@Entity`**:
  - Marks a Java class as an entity, meaning it is mapped to a database table. The class should have a primary key field annotated with `@Id`.
  - Example:
    ```java
    @Entity
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
    }
    ```
  
- **`@Table`**:
  - Specifies the name of the database table to which the entity is mapped. It is optional if the table name is the same as the entity name.
  - Example:
    ```java
    @Entity
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
    }
    ```

### 47. What is `@Transactional` used for in Spring Boot?
The `@Transactional` annotation is used to define the scope of a transaction in Spring. It ensures that operations within a method or class are executed within a transaction context. If any operation fails, all changes are rolled back, maintaining data integrity.
- It can be applied to methods or classes.
- **Example**:
  ```java
  @Transactional
  public void updateUserDetails(User user) {
      userRepository.save(user);
      // Other database operations
  }
  ```

### 48. How does Spring Boot handle database migrations (e.g., Flyway, Liquibase)?
Spring Boot provides automatic configuration for **Flyway** and **Liquibase** to handle database migrations. These tools help you manage database schema changes across different environments.

- **Flyway**: It uses SQL-based migrations. You create migration files (e.g., `V1__create_user_table.sql`) that Flyway runs automatically when the application starts.
  - To enable Flyway, add the following dependency:
    ```xml
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-core</artifactId>
    </dependency>
    ```
  - Migration files should be placed in `src/main/resources/db/migration`.

- **Liquibase**: It uses XML, YAML, or JSON-based migrations. Liquibase tracks schema changes in a database changelog file (e.g., `db.changelog-master.xml`).
  - To enable Liquibase, add the following dependency:
    ```xml
    <dependency>
        <groupId>org.liquibase</groupId>
        <artifactId>liquibase-core</artifactId>
    </dependency>
    ```

### 49. How do you implement a many-to-many relationship using JPA in Spring Boot?
To implement a **many-to-many** relationship in Spring Boot using JPA, you use the `@ManyToMany` annotation on both sides of the relationship. Typically, a join table is created to manage the relationship.

Example:
```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}
```

### 50. What are `CrudRepository`, `JpaRepository`, and `PagingAndSortingRepository` in Spring Data JPA?
- **`CrudRepository`**:
  - Provides basic CRUD (Create, Read, Update, Delete) operations.
  - Example:
    ```java
    public interface UserRepository extends CrudRepository<User, Long> {
    }
    ```

- **`JpaRepository`**:
  - Extends `CrudRepository` and adds JPA-specific methods, such as batch operations, flushing the persistence context, and more. It is the most commonly used repository interface.
  - Example:
    ```java
    public interface UserRepository extends JpaRepository<User, Long> {
    }
    ```

- **`PagingAndSortingRepository`**:
  - Extends `CrudRepository` and adds methods for pagination and sorting.
  - Example:
    ```java
    public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    }
    ```

These interfaces provide a convenient way to interact with the database without needing to write boilerplate code for common database operations.

### Spring Boot Security:
Here are the answers to the Spring Boot security-related questions:

### 51. How do you configure Spring Security in Spring Boot?
To configure **Spring Security** in Spring Boot, follow these steps:
1. **Add the dependency**: Include `spring-boot-starter-security` in your `pom.xml` or `build.gradle`.
   - Example (Maven):
     ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-security</artifactId>
     </dependency>
     ```
2. **Create a Security Configuration Class**: Extend `WebSecurityConfigurerAdapter` and override the `configure` methods to set up authentication, authorization, and other security settings.
   - Example:
     ```java
     @Configuration
     @EnableWebSecurity
     public class SecurityConfig extends WebSecurityConfigurerAdapter {
         @Override
         protected void configure(HttpSecurity http) throws Exception {
             http
                 .authorizeRequests()
                 .antMatchers("/public/**").permitAll()
                 .anyRequest().authenticated()
                 .and()
                 .formLogin().permitAll();
         }
     }
     ```

3. **Configure authentication**: You can configure in-memory authentication or integrate with a database for user details.

### 52. What is `@PreAuthorize` annotation in Spring Security?
The `@PreAuthorize` annotation is used to secure methods by defining access control based on the user's roles or permissions. It is evaluated before the method execution.
- It allows you to specify a SpEL (Spring Expression Language) expression to determine if the user has the required permission.
- Example:
  ```java
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteUser(Long userId) {
      // Method logic
  }
  ```

### 53. What are the steps to enable basic authentication in Spring Boot?
To enable **basic authentication** in Spring Boot with Spring Security:
1. **Add the Spring Security starter dependency**:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-security</artifactId>
   </dependency>
   ```

2. **Configure in-memory authentication** (or JDBC/LDAP for a real database):
   Example:
   ```java
   @Configuration
   @EnableWebSecurity
   public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
       @Override
       protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           auth.inMemoryAuthentication()
               .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
               .and()
               .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
       }

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .authorizeRequests()
               .anyRequest().authenticated()
               .and()
               .httpBasic();
       }

       @Bean
       public PasswordEncoder passwordEncoder() {
           return new BCryptPasswordEncoder();
       }
   }
   ```

### 54. How can you configure JWT (JSON Web Tokens) authentication in Spring Boot?
To configure **JWT authentication** in Spring Boot:
1. **Add dependencies** for JWT parsing (e.g., `jjwt` or `spring-security-oauth2`).
   Example (Maven):
   ```xml
   <dependency>
       <groupId>io.jsonwebtoken</groupId>
       <artifactId>jjwt</artifactId>
       <version>0.11.5</version>
   </dependency>
   ```

2. **Create a JWT Utility class**: This class will handle the creation and validation of JWT tokens.
   Example:
   ```java
   public class JwtUtil {
       private String secretKey = "your_secret_key";

       public String generateToken(String username) {
           return Jwts.builder()
               .setSubject(username)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
               .signWith(SignatureAlgorithm.HS256, secretKey)
               .compact();
       }

       public String extractUsername(String token) {
           return Jwts.parser()
               .setSigningKey(secretKey)
               .parseClaimsJws(token)
               .getBody()
               .getSubject();
       }

       public Boolean isTokenExpired(String token) {
           return extractExpiration(token).before(new Date());
       }

       private Date extractExpiration(String token) {
           return Jwts.parser()
               .setSigningKey(secretKey)
               .parseClaimsJws(token)
               .getBody()
               .getExpiration();
       }
   }
   ```

3. **Implement a filter for JWT authentication**: The filter intercepts requests to validate the JWT and authenticate the user.
   Example:
   ```java
   @Component
   public class JwtRequestFilter extends OncePerRequestFilter {
       @Autowired
       private JwtUtil jwtUtil;

       @Autowired
       private CustomUserDetailsService userDetailsService;

       @Override
       protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
               throws ServletException, IOException {
           final String token = request.getHeader("Authorization");

           if (token != null && token.startsWith("Bearer ")) {
               String jwt = token.substring(7);
               String username = jwtUtil.extractUsername(jwt);

               if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                   UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                   if (jwtUtil.isTokenExpired(jwt)) {
                       // Handle expired token
                   } else {
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                               new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                       usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                       SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                   }
               }
           }
           chain.doFilter(request, response);
       }
   }
   ```

4. **Configure the filter** in Spring Security:
   Example:
   ```java
   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {
       @Autowired
       private JwtRequestFilter jwtRequestFilter;

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
       }
   }
   ```

### 55. What is OAuth2, and how do you implement it in Spring Boot?
**OAuth2** is an authorization framework that allows third-party services to exchange resources on behalf of a user. In Spring Boot, you can implement OAuth2 authentication with Spring Security's support for **OAuth2 Login** or **Resource Server**.
1. **Add OAuth2 dependencies**: 
   - Example (Maven):
     ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-oauth2-client</artifactId>
     </dependency>
     ```

2. **Configure OAuth2 client** in `application.properties`:
   - Example:
     ```properties
     spring.security.oauth2.client.registration.google.client-id=your-client-id
     spring.security.oauth2.client.registration.google.client-secret=your-client-secret
     spring.security.oauth2.client.registration.google.scope=profile,email
     spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/auth
     ```

3. **Configure OAuth2 login** in your `SecurityConfig`:
   Example:
   ```java
   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {
       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .authorizeRequests()
               .antMatchers("/login**").permitAll()
               .anyRequest().authenticated()
               .and()
               .oauth2Login();
       }
   }
   ```

### 56. How do you manage authorization and roles in Spring Security?
Spring Security uses roles to manage access control by restricting or allowing access to resources based on the user's roles. You can define roles in `@PreAuthorize`, `@Secured`, or directly in the `HttpSecurity` configuration.
- Example of role-based access control in `HttpSecurity`:
  ```java
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
          .authorizeRequests()
          .antMatchers("/admin/**").hasRole("ADMIN")
          .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
          .anyRequest().authenticated();
  }
  ```

### 57. How can you protect REST APIs with Spring Security in Spring Boot?
To protect REST APIs:
1. Configure Spring Security to restrict access to certain endpoints using roles.
2. For JWT-based security, use a custom filter to authenticate requests.
3. Example of securing REST API endpoints:
   ```java
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
           .authorizeRequests()
           .antMatchers("/api/public/**").permitAll()
           .antMatchers("/api/private/**").hasRole("USER")
           .anyRequest().authenticated()
           .and()
           .httpBasic(); // Or use JWT filter for token-based security
   }
   ```

### 58. How do you customize the login page in Spring Security with Spring Boot?
To customize the login page:
1. Create a custom HTML login page (e.g., `login.html`).
2. Configure Spring Security to use this page:
   ```java
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
           .formLogin()
           .loginPage("/login") //

 Specify your custom login page URL
           .permitAll();
   }
   ```

3. **Create a controller** for the login page:
   ```java
   @Controller
   public class LoginController {
       @GetMapping("/login")
       public String loginPage() {
           return "login"; // Return the custom login page
       }
   }
   ```

### 59. What is CSRF protection in Spring Boot, and how is it implemented?
**CSRF (Cross-Site Request Forgery)** protection ensures that a malicious actor cannot perform unwanted actions on behalf of an authenticated user. By default, Spring Security enables CSRF protection.
To disable CSRF protection (for stateless applications like REST APIs), use:
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable(); // Disable CSRF for REST APIs
}
```

For stateful web applications, CSRF tokens are automatically generated and validated by Spring Security.

### 60. How do you secure the Spring Boot Actuator endpoints?
Spring Boot Actuator endpoints can be secured by configuring HTTP security for the /actuator/** paths:
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/actuator/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .httpBasic();
}
```

Additionally, make sure to enable specific actuator endpoints by modifying `application.properties`:
```properties
management.endpoints.web.exposure.include=health,info
```

### Spring Boot Testing:
Here are the answers to the Spring Boot testing-related questions:

### 61. What is `@SpringBootTest` used for in testing Spring Boot applications?
`@SpringBootTest` is used for **integration testing** in Spring Boot applications. It loads the entire application context, including all the beans, configurations, and dependencies, making it ideal for testing the application as a whole. This annotation starts the full Spring context and makes the entire application available for testing.
- Example usage:
  ```java
  @SpringBootTest
  class MyApplicationTests {
      @Autowired
      private MyService myService;
  
      @Test
      void contextLoads() {
          assertThat(myService).isNotNull();
      }
  }
  ```
  
### 62. How do you test a Spring Boot controller using `MockMvc`?
`MockMvc` is used for **unit testing Spring MVC controllers** without needing to start a full HTTP server. It allows you to mock HTTP requests and verify the responses.
1. **Set up the test with `@WebMvcTest`**:
   - Example:
     ```java
     @WebMvcTest(MyController.class)
     class MyControllerTests {
  
         @Autowired
         private MockMvc mockMvc;
  
         @MockBean
         private MyService myService;
  
         @Test
         void testGetEndpoint() throws Exception {
             // Mock the service response
             when(myService.getData()).thenReturn("some data");
  
             // Perform a GET request and verify the result
             mockMvc.perform(get("/api/data"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("some data"));
         }
     }
     ```

### 63. How do you mock services and repositories in Spring Boot tests?
In Spring Boot tests, you can use `@MockBean` to mock services and repositories.
1. **Mocking services**: 
   - Example:
     ```java
     @SpringBootTest
     class ServiceTests {
         @MockBean
         private MyService myService;  // Mock service
         
         @Autowired
         private MyController myController;
  
         @Test
         void testServiceMock() {
             when(myService.getData()).thenReturn("Mocked Data");
             assertEquals("Mocked Data", myController.getData());
         }
     }
     ```

2. **Mocking repositories**: 
   - Example:
     ```java
     @DataJpaTest
     class RepositoryTests {
         @MockBean
         private MyRepository myRepository;  // Mock repository
         
         @Autowired
         private MyService myService;
  
         @Test
         void testRepositoryMock() {
             when(myRepository.findById(1L)).thenReturn(Optional.of(new MyEntity()));
             assertTrue(myService.findById(1L).isPresent());
         }
     }
     ```

### 64. What is the difference between `@WebMvcTest` and `@DataJpaTest`?
- **`@WebMvcTest`**: Focuses on testing Spring MVC components (controllers, filters, etc.). It does not load the entire context but only the web layer and related components.
  - **Usage**: Ideal for controller unit tests where you don't need access to service or repository layers.
  
- **`@DataJpaTest`**: Focuses on testing **JPA repositories**. It configures an in-memory database and only loads the data-related components such as `@Entity`, `@Repository`, and database configurations.
  - **Usage**: Ideal for repository unit tests where you want to verify the interaction with the database.

### 65. How can you write unit tests for Spring Boot services using `@MockBean`?
You can use `@MockBean` to mock service dependencies in unit tests. This is especially useful for isolating the service layer from other components.
1. **Example of a service unit test**:
   ```java
   @SpringBootTest
   class MyServiceTest {
       @Autowired
       private MyService myService;  // The service to be tested
  
       @MockBean
       private MyRepository myRepository;  // Mocked repository dependency
  
       @Test
       void testServiceMethod() {
           // Mock the repository behavior
           when(myRepository.findById(1L)).thenReturn(Optional.of(new MyEntity()));
  
           // Call the service method and verify results
           MyEntity result = myService.findById(1L);
           assertNotNull(result);
       }
   }
   ```

### 66. What are the best practices for testing Spring Boot applications?
1. **Use `@SpringBootTest` for integration tests** to test the entire context.
2. **Use `@WebMvcTest` for controller tests** to isolate the web layer.
3. **Use `@DataJpaTest` for repository tests** to focus on the database layer.
4. **Mock external services** using `@MockBean` to avoid dependencies on external systems.
5. **Write meaningful assertions** to validate the behavior of your application.
6. **Use `@Test` annotations for defining test methods**.
7. **Use `@BeforeEach` and `@AfterEach` for setting up and tearing down test data**.

### 67. How do you perform integration testing in Spring Boot?
To perform integration testing:
1. Use `@SpringBootTest` to load the complete application context.
2. Test multiple layers together (controller, service, repository).
3. Use `TestRestTemplate` or `MockMvc` for making HTTP requests in integration tests.
- Example:
  ```java
  @SpringBootTest
  @AutoConfigureMockMvc
  class MyIntegrationTest {
      @Autowired
      private MockMvc mockMvc;
  
      @Test
      void testIntegration() throws Exception {
          mockMvc.perform(get("/api/data"))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.name").value("Test"));
      }
  }
  ```

### 68. How do you use `@AutoConfigureMockMvc` for testing controllers in Spring Boot?
`@AutoConfigureMockMvc` automatically configures `MockMvc` for testing Spring MVC controllers. This allows you to test controllers without needing a running server.
1. **Use `@AutoConfigureMockMvc` with `@SpringBootTest`**:
   - Example:
     ```java
     @SpringBootTest
     @AutoConfigureMockMvc
     class MyControllerTest {
         @Autowired
         private MockMvc mockMvc;
  
         @Test
         void testController() throws Exception {
             mockMvc.perform(get("/api/endpoint"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("response"));
         }
     }
     ```

### 69. How do you handle database integration tests in Spring Boot without polluting the real database?
To avoid polluting the real database during integration tests:
1. **Use an in-memory database** (like H2 or HSQL) for tests:
   - In `application.properties`:
     ```properties
     spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
     spring.datasource.driverClassName=org.h2.Driver
     spring.datasource.username=sa
     spring.datasource.password=password
     spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
     ```
2. **Use `@Transactional`**: This ensures that changes made during tests are rolled back after each test method.
   - Example:
     ```java
     @Transactional
     @SpringBootTest
     class MyRepositoryTest {
         @Autowired
         private MyRepository myRepository;
  
         @Test
         void testRepository() {
             // Test repository methods
         }
     }
     ```
3. **Use test containers** for more complex scenarios (like integration with Docker databases).

These strategies help in isolating the database used for testing from the production or real databases, ensuring data consistency.

### Spring Boot Actuator:
### 70. What is Spring Boot Actuator, and how does it enhance your application?
**Spring Boot Actuator** is a set of tools in Spring Boot that helps to monitor and manage applications in production. It provides built-in endpoints for gathering application health status, metrics, environment information, and more. These endpoints provide valuable operational insights into the application, such as performance, usage, and resource consumption.

**How it enhances your application**:
- Provides operational endpoints to monitor the health, metrics, and configurations of your Spring Boot application.
- Helps manage your application by exposing important details like environment properties, active profiles, etc.
- Integrates with monitoring systems (like Prometheus, Grafana) for advanced analytics and monitoring.
- Can be customized to expose business-specific metrics or other runtime data.

### 71. What are the default endpoints provided by Spring Boot Actuator?
Spring Boot Actuator comes with several default endpoints. Some of the most commonly used ones include:

- **`/actuator/health`**: Displays the health status of the application.
- **`/actuator/info`**: Provides general information about the application (can be customized).
- **`/actuator/metrics`**: Exposes metrics about the application's performance and resource usage.
- **`/actuator/env`**: Displays the application's environment properties.
- **`/actuator/beans`**: Lists all the beans in the application context.
- **`/actuator/auditevents`**: Displays audit logs if enabled.
- **`/actuator/loggers`**: Allows you to view and modify logging levels at runtime.
- **`/actuator/threaddump`**: Provides a thread dump of the application.
- **`/actuator/heapdump`**: Generates a heap dump of the application.

These endpoints are all exposed over HTTP by default, but they can be configured and customized as needed.

### 72. How can you expose custom Actuator endpoints in Spring Boot?
You can create custom Actuator endpoints by defining your own `@Endpoint` beans. Here's how to expose custom endpoints:

1. **Create a custom endpoint class**:
   ```java
   @Component
   @Endpoint(id = "custom")
   public class CustomEndpoint {
       
       @ReadOperation
       public String customEndpoint() {
           return "Custom Endpoint Response";
       }
   }
   ```

2. **Enable Actuator and the custom endpoint in `application.properties`**:
   ```properties
   management.endpoints.web.exposure.include=health,info,custom
   ```

3. **Access the custom endpoint** via `http://localhost:8080/actuator/custom`.

This allows you to expose any functionality or data specific to your application in a controlled, manageable way.

### 73. What is the role of `health` and `metrics` endpoints in Spring Boot Actuator?
- **`/actuator/health`**: This endpoint provides the health status of the application. It performs a series of checks (database, disk space, etc.) and returns a status (such as `UP`, `DOWN`, or `UNKNOWN`) based on those checks. This is crucial for ensuring that your application is running correctly, especially in a production environment.

  - **Example**: If your database is down, the health endpoint will report `DOWN`.
  
- **`/actuator/metrics`**: This endpoint exposes a wide range of metrics regarding the application's performance. Metrics include memory usage, active threads, HTTP request counts, garbage collection, and more. These can be used for monitoring the system's resource consumption and performance trends over time.

  - **Example**: `http://localhost:8080/actuator/metrics/jvm.memory.used` provides memory usage information.

### 74. How do you secure actuator endpoints in a production environment?
Securing Spring Boot Actuator endpoints in production is important because they expose sensitive information about your application. You can secure them by:

1. **Basic Authentication**:
   Configure HTTP security to require authentication for accessing actuator endpoints.
   ```java
   @Configuration
   public class SecurityConfig extends WebSecurityConfigurerAdapter {
       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .authorizeRequests()
               .antMatchers("/actuator/**").hasRole("ADMIN")
               .anyRequest().authenticated()
               .and()
               .httpBasic(); // Enable basic authentication
       }
   }
   ```

2. **Disable sensitive endpoints**:
   You can disable certain actuator endpoints for production environments.
   ```properties
   management.endpoints.web.exposure.exclude=beans,env,heapdump
   ```

3. **Use HTTPS**:
   Ensure that actuator endpoints are exposed over HTTPS, to secure data transmission.

4. **Custom Access Control**:
   You can also create more granular access control for specific actuator endpoints by using `@PreAuthorize` annotations or configuring a custom security policy.

### 75. How can you monitor and gather metrics using Spring Boot Actuator?
Spring Boot Actuator provides powerful monitoring capabilities by exposing metrics about your application. You can use the `/actuator/metrics` endpoint to gather various built-in metrics.

**Steps to monitor and gather metrics**:

1. **Enable metrics in `application.properties`**:
   Make sure you include the metrics endpoint:
   ```properties
   management.endpoints.web.exposure.include=metrics
   ```

2. **Access the metrics endpoint**:
   Visit `http://localhost:8080/actuator/metrics` to view various application metrics like memory usage, garbage collection, and thread count.

3. **Custom metrics**:
   You can define custom metrics using `MeterRegistry` (provided by Micrometer, which integrates with Spring Boot Actuator):
   ```java
   @Component
   public class CustomMetrics {
       private final MeterRegistry meterRegistry;

       public CustomMetrics(MeterRegistry meterRegistry) {
           this.meterRegistry = meterRegistry;
       }

       @PostConstruct
       public void init() {
           meterRegistry.gauge("custom.metric", 100);
       }
   }
   ```

4. **Integrate with monitoring tools**:
   You can use monitoring tools like **Prometheus**, **Grafana**, or **Micrometer** to aggregate and visualize these metrics over time. These tools allow you to track the health and performance of your Spring Boot application in real-time.

By exposing metrics and leveraging integration with tools like Prometheus, you can gain deeper insights into your application’s behavior and performance, which is essential for managing applications in production environments.

These questions are designed to cover everything from the basics to advanced topics in Spring Boot. They will help you prepare for technical interviews related to Spring Boot development, configuration, security, testing, and microservices.