# 17. Java Spring Framework 

## 1. What is Spring Framework?

Spring Framework is a comprehensive Java framework that provides infrastructure support for developing enterprise applications. It simplifies Java development through dependency injection and aspect-oriented programming.

- Lightweight and modular framework
- Provides dependency injection and IoC container
- Supports aspect-oriented programming (AOP)
- Integrates with various technologies
- Reduces boilerplate code and complexity

Spring makes Java development easier by handling common tasks and promoting best practices like loose coupling and testability.

## 2. What is Inversion of Control (IoC)?

Inversion of Control is a design principle where the control of object creation and dependency management is transferred from the application code to an external container or framework.

- Container manages object lifecycle
- Objects don't create their dependencies
- Dependencies are provided by external source
- Promotes loose coupling
- Makes code more testable and maintainable

Instead of objects creating their dependencies, the IoC container creates and injects them, inverting the traditional control flow.

## 3. What is Dependency Injection?

Dependency Injection is a technique where an object's dependencies are provided by an external source rather than the object creating them itself. It's a way to implement IoC.

**Types of DI:**
- Constructor injection (recommended)
- Setter injection
- Field injection

```java
// Without DI - tight coupling
class OrderService {
    private PaymentService paymentService = new PaymentService(); // Creates dependency
}

// With DI - loose coupling
class OrderService {
    private PaymentService paymentService;
    
    public OrderService(PaymentService paymentService) { // Injected
        this.paymentService = paymentService;
    }
}
```

## 4. What is the difference between BeanFactory and ApplicationContext?

**BeanFactory:**
- Basic IoC container
- Lazy initialization
- Limited functionality
- Suitable for resource-constrained environments

**ApplicationContext:**
- Advanced IoC container
- Eager initialization by default
- Additional features (event handling, internationalization)
- Preferred for enterprise applications

```java
// BeanFactory - basic container
BeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans.xml"));

// ApplicationContext - advanced container
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
```

ApplicationContext extends BeanFactory and provides more enterprise-ready features.

## 5. What are Spring beans?

Spring beans are objects that are managed by the Spring IoC container. They are created, configured, and managed by Spring based on configuration metadata.

- Objects managed by Spring container
- Defined through configuration (XML, annotations, Java config)
- Container handles lifecycle and dependencies
- Can be singleton or prototype scope
- Configured with metadata

```java
@Component
public class UserService { // This becomes a Spring bean
    // Bean managed by Spring container
}

// Or XML configuration
<bean id="userService" class="com.example.UserService"/>
```

## 6. What is Spring Boot?

Spring Boot is an opinionated framework built on top of Spring that simplifies the development of Spring applications by providing auto-configuration and embedded servers.

- Rapid application development
- Auto-configuration based on classpath
- Embedded servers (Tomcat, Jetty)
- Production-ready features out of the box
- Minimal configuration required

Spring Boot eliminates most boilerplate configuration and allows developers to focus on business logic rather than setup.

## 7. What is auto-configuration in Spring Boot?

Auto-configuration automatically configures Spring applications based on the dependencies present in the classpath. It reduces manual configuration by making intelligent assumptions.

- Automatic bean configuration
- Based on classpath dependencies
- Can be customized or disabled
- Uses conditional annotations
- Follows convention over configuration

```java
// If H2 database is on classpath, Spring Boot automatically configures:
// - DataSource bean
// - JdbcTemplate bean  
// - Transaction manager
// No manual configuration needed
```

## 8. What is @SpringBootApplication annotation?

@SpringBootApplication is a convenience annotation that combines three commonly used annotations: @Configuration, @EnableAutoConfiguration, and @ComponentScan.

```java
@SpringBootApplication // Combines three annotations
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}

// Equivalent to:
@Configuration
@EnableAutoConfiguration  
@ComponentScan
public class MyApplication { }
```

It's the standard annotation for Spring Boot main classes and enables all essential Spring Boot features.

## 9. What is the difference between @Component, @Service, and @Repository?

These are stereotype annotations that mark classes as Spring beans, but they serve different purposes and provide semantic meaning.

**@Component:**
- Generic stereotype for any Spring-managed component
- Base annotation for other stereotypes

**@Service:**
- Marks service layer classes
- Contains business logic
- Semantic specialization of @Component

**@Repository:**
- Marks data access layer classes
- Provides exception translation
- Semantic specialization of @Component

```java
@Component
public class UtilityClass { } // Generic component

@Service
public class UserService { } // Business logic

@Repository
public class UserRepository { } // Data access
```

## 10. What is @Autowired annotation?

@Autowired enables automatic dependency injection by type. Spring automatically injects the required dependencies into the annotated fields, constructors, or methods.

- Automatic dependency injection
- Injection by type
- Can be applied to fields, constructors, methods
- Required by default (can be made optional)

```java
@Service
public class OrderService {
    
    @Autowired
    private PaymentService paymentService; // Field injection
    
    private UserService userService;
    
    @Autowired
    public OrderService(UserService userService) { // Constructor injection
        this.userService = userService;
    }
}
```

## 11. What is @Qualifier annotation?

@Qualifier is used with @Autowired to specify which bean to inject when multiple beans of the same type exist. It resolves ambiguity in dependency injection.

```java
@Service
public class NotificationService {
    
    @Autowired
    @Qualifier("emailSender")
    private MessageSender messageSender; // Specifies which implementation
}

@Component("emailSender")
public class EmailSender implements MessageSender { }

@Component("smsSender") 
public class SmsSender implements MessageSender { }
```

Without @Qualifier, Spring would throw an exception due to multiple beans of type MessageSender.

## 12. What is ApplicationContext?

ApplicationContext is Spring's advanced IoC container that manages beans and provides enterprise features like event handling, internationalization, and resource loading.

- Central interface for Spring applications
- Manages bean lifecycle and dependencies
- Provides additional enterprise features
- Event publishing and handling
- Resource loading and internationalization

```java
@Component
public class MyService {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    public void doSomething() {
        // Get bean programmatically
        UserService userService = applicationContext.getBean(UserService.class);
        
        // Publish event
        applicationContext.publishEvent(new CustomEvent("data"));
    }
}
```

ApplicationContext is the heart of Spring applications and provides the foundation for all Spring features.