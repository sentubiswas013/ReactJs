## 7) Spring Framework (Core + MVC)

## 72. What is IoC and Dependency Injection?

**Answer:**
IoC (Inversion of Control) means the framework controls object creation instead of the developer. Dependency Injection is a pattern where objects receive their dependencies from external sources rather than creating them.

**Example:**
```java
// Without DI
public class UserService {
    private UserRepository repo = new UserRepository(); // tight coupling
}

// With DI
@Service
public class UserService {
    private final UserRepository repo;
    
    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo; // Spring injects dependency
    }
}
```

---

## 73. Explain Spring Bean lifecycle.

**Answer:**
Bean lifecycle: Instantiation → Populate Properties → setBeanName() → setBeanFactory() → setApplicationContext() → @PostConstruct → Bean Ready → @PreDestroy → Destroy.

**Example:**
```java
@Component
public class MyBean {
    
    @PostConstruct
    public void init() {
        System.out.println("Bean initialized");
    }
    
    @PreDestroy
    public void cleanup() {
        System.out.println("Bean destroyed");
    }
}
```

---

## 74. What are bean scopes (singleton, prototype, request, session)?

**Answer:**
- **Singleton**: One instance per Spring container (default)
- **Prototype**: New instance every time requested
- **Request**: One instance per HTTP request
- **Session**: One instance per HTTP session

**Example:**
```java
@Component
@Scope("singleton") // default
public class SingletonBean { }

@Component
@Scope("prototype")
public class PrototypeBean { }

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestBean { }
```

---

## 75. Difference between `@Component`, `@Service`, `@Repository`, `@Controller`.

**Answer:**
All are stereotypes for component scanning. `@Component` is generic, `@Service` for business logic, `@Repository` for data access (adds exception translation), `@Controller` for web layer.

**Example:**
```java
@Component
public class GenericComponent { }

@Service
public class UserService { }

@Repository
public class UserRepository { }

@Controller
public class UserController { }
```

---

## 76. Constructor vs setter vs field injection - which is preferred?

**Answer:**
Constructor injection is preferred because it ensures immutability, makes dependencies explicit, and allows final fields. Field injection is discouraged.

**Example:**
```java
// Constructor injection (Preferred)
@Service
public class UserService {
    private final UserRepository repo;
    
    public UserService(UserRepository repo) {
        this.repo = repo;
    }
}

// Setter injection
@Service
public class OrderService {
    private OrderRepository repo;
    
    @Autowired
    public void setRepo(OrderRepository repo) {
        this.repo = repo;
    }
}

// Field injection (Not recommended)
@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;
}
```

---

## 77. How do you resolve bean ambiguity (`@Primary`, `@Qualifier`)?

**Answer:**
Use `@Primary` to mark a default bean or `@Qualifier` to specify which bean to inject when multiple candidates exist.

**Example:**
```java
@Component
@Primary
public class MySQLDatabase implements Database { }

@Component
public class PostgresDatabase implements Database { }

@Service
public class DataService {
    private final Database db;
    
    public DataService(@Qualifier("postgresDatabase") Database db) {
        this.db = db;
    }
}
```

---

## 78. What is `@Configuration` and `@Bean`?

**Answer:**
`@Configuration` marks a class as a source of bean definitions. `@Bean` defines a method that returns a bean to be managed by Spring container.

**Example:**
```java
@Configuration
public class AppConfig {
    
    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/db");
        return ds;
    }
    
    @Bean
    public UserService userService() {
        return new UserService(dataSource());
    }
}
```

---

## 79. Explain Spring AOP concepts (Aspect, Advice, Pointcut, JoinPoint).

**Answer:**
- **Aspect**: Module containing cross-cutting concerns
- **Advice**: Action taken at a joinpoint
- **Pointcut**: Expression matching joinpoints
- **JoinPoint**: Point in program execution (method call)

**Example:**
```java
@Aspect
@Component
public class LoggingAspect {
    
    @Pointcut("execution(* com.example.service.*.*(..))")
    public void serviceMethods() { }
    
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Executing: " + joinPoint.getSignature());
    }
}
```

---

## 80. What are advice types (Before, After, Around)?

**Answer:**
- **@Before**: Runs before method execution
- **@After**: Runs after method (finally)
- **@AfterReturning**: Runs after successful return
- **@AfterThrowing**: Runs after exception
- **@Around**: Wraps method execution

**Example:**
```java
@Aspect
@Component
public class PerformanceAspect {
    
    @Before("execution(* com.example.service.*.*(..))")
    public void before() {
        System.out.println("Before method");
    }
    
    @After("execution(* com.example.service.*.*(..))")
    public void after() {
        System.out.println("After method");
    }
    
    @Around("execution(* com.example.service.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time + "ms");
        return result;
    }
}
```

---

## 81. Explain Spring MVC request flow and DispatcherServlet.

**Answer:**
Request → DispatcherServlet → HandlerMapping → Controller → ViewResolver → View → Response. DispatcherServlet is the front controller that routes requests.

**Example:**
```java
@Controller
public class UserController {
    
    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "userView"; // ViewResolver resolves to JSP/Thymeleaf
    }
}
```

---

## 82. What is `@PathVariable` vs `@RequestParam`?

**Answer:**
`@PathVariable` extracts values from URI path. `@RequestParam` extracts query parameters from URL.

**Example:**
```java
@RestController
public class ProductController {
    
    // PathVariable: /products/123
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }
    
    // RequestParam: /products?category=electronics&page=2
    @GetMapping("/products")
    public List<Product> getProducts(
        @RequestParam String category,
        @RequestParam(defaultValue = "0") int page) {
        return productService.findByCategory(category, page);
    }
}
```

---

## 83. How do you handle exceptions using `@ControllerAdvice`?

**Answer:**
`@ControllerAdvice` provides global exception handling across all controllers using `@ExceptionHandler` methods.

**Example:**
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse(500, "Internal error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

class ErrorResponse {
    private int status;
    private String message;
    
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
    // getters/setters
}
```

---

## 84. Explain Spring validation with `@Valid`.

**Answer:**
`@Valid` triggers validation on request body using Bean Validation annotations. Validation errors are captured in BindingResult.

**Example:**
```java
// DTO with validation
public class UserDTO {
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Invalid email")
    private String email;
    
    @Min(value = 18, message = "Age must be 18+")
    private int age;
    
    // getters/setters
}

// Controller
@RestController
public class UserController {
    
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user, 
                                        BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        userService.save(user);
        return ResponseEntity.ok("User created");
    }
}
```
