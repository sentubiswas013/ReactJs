# 🔹 Spring Framework

## Spring Core Concepts 

### 266. What is Spring Framework?

**Answer:** Spring is a comprehensive Java framework that provides infrastructure support for developing enterprise applications. It's built on dependency injection and aspect-oriented programming principles, making applications more modular and testable.

**Key Points:**
- Lightweight and non-invasive framework
- Promotes loose coupling through IoC
- Comprehensive ecosystem for enterprise development
- Supports various architectural patterns

---

### 267. What are the benefits of Spring?

**Answer:** Key benefits include loose coupling through dependency injection, simplified testing with mock objects, reduced boilerplate code, comprehensive transaction management, and excellent integration with other frameworks.

**Main Benefits:**
- **Loose Coupling:** Dependencies managed externally
- **Testability:** Easy unit testing with mocks
- **Modularity:** Clean separation of concerns
- **Integration:** Works well with other frameworks
- **Transaction Management:** Declarative transaction support

---

### 268. What is Inversion of Control (IoC)?

**Answer:** IoC is a design principle where object creation and dependency management is handled by an external container rather than the objects themselves. Spring's IoC container manages object lifecycles and dependencies.

**Core Concept:**
- Objects don't create their dependencies
- Container injects dependencies at runtime
- Promotes loose coupling and flexibility
- Enables easier testing and maintenance

---

### 269. What is Dependency Injection?

**Answer:** Dependency Injection is a technique where dependencies are provided to an object rather than the object creating them. This promotes loose coupling and makes code more testable and maintainable.

**Benefits:**
- Reduces tight coupling between classes
- Improves testability
- Enhances code reusability
- Simplifies configuration management

---

### 270. What are the types of dependency injection?

**Answer:** Three main types: Constructor injection (dependencies provided through constructor), Setter injection (dependencies set through setter methods), and Field injection (dependencies injected directly into fields using annotations).

**Types:**
1. **Constructor Injection:** `@Autowired` on constructor
2. **Setter Injection:** `@Autowired` on setter methods
3. **Field Injection:** `@Autowired` directly on fields

**Best Practice:** Constructor injection is preferred for mandatory dependencies.

---

### 271. What is ApplicationContext?

**Answer:** ApplicationContext is Spring's advanced IoC container that extends BeanFactory. It provides additional features like event propagation, internationalization, and application-layer specific contexts.

**Features:**
- Bean lifecycle management
- Event publishing and handling
- Internationalization support
- Resource loading capabilities
- AOP integration

---

### 272. What is BeanFactory?

**Answer:** BeanFactory is the basic IoC container in Spring that provides fundamental dependency injection features. It's the root interface for accessing Spring bean containers and uses lazy initialization.

**Characteristics:**
- Basic container functionality
- Lazy bean initialization
- Lightweight implementation
- Core dependency injection features

---

### 273. What is the difference between BeanFactory and ApplicationContext?

**Answer:** ApplicationContext extends BeanFactory with additional features: eager bean initialization, event publishing, internationalization support, and application-layer specific functionality. ApplicationContext is preferred for most applications.

**Key Differences:**

| Feature | BeanFactory | ApplicationContext |
|---------|-------------|-------------------|
| Initialization | Lazy | Eager |
| Event Publishing | No | Yes |
| Internationalization | No | Yes |
| AOP Support | Limited | Full |
| Resource Loading | Basic | Advanced |

---

### 274. What are Spring beans?

**Answer:** Spring beans are objects managed by the Spring IoC container. They are instantiated, configured, and managed by Spring based on configuration metadata provided through XML, annotations, or Java configuration.

**Bean Definition:**
- Managed by Spring container
- Configured through metadata
- Lifecycle controlled by Spring
- Can be injected into other beans

**Configuration Methods:**
- XML configuration
- Annotation-based (`@Component`, `@Service`, etc.)
- Java configuration (`@Configuration`, `@Bean`)

---

### 275. What are the bean scopes in Spring?

**Answer:** Main scopes include: Singleton (default, one instance per container), Prototype (new instance each time), Request (one per HTTP request), Session (one per HTTP session), and Application (one per ServletContext).

**Bean Scopes:**

1. **Singleton** (Default)
   - One instance per Spring container
   - Shared across the application

2. **Prototype**
   - New instance for each request
   - Not managed after creation

3. **Request** (Web-aware)
   - One instance per HTTP request
   - Web applications only

4. **Session** (Web-aware)
   - One instance per HTTP session
   - Web applications only

5. **Application** (Web-aware)
   - One instance per ServletContext
   - Web applications only

**Usage Example:**
```java
@Component
@Scope("prototype")
public class MyPrototypeBean {
    // Bean implementation
}
```

**Key Takeaways:**
- Spring Framework simplifies enterprise Java development
- IoC and DI are fundamental concepts
- ApplicationContext is preferred over BeanFactory
- Bean scopes control object lifecycle and sharing
- Proper understanding enables effective Spring application development