## Integration Testing 

### 336. What is integration testing?

**Answer:** Integration testing verifies that different components or services work correctly together. It tests the interfaces and data flow between modules, validates external dependencies like databases and APIs, and ensures the integrated system functions as expected beyond individual unit tests.

**Key Characteristics:**
- **Component Integration:** Tests interaction between modules
- **External Dependencies:** Validates database, API, file system interactions
- **Data Flow:** Ensures correct data passing between components
- **System Behavior:** Verifies end-to-end functionality

**Integration Testing Levels:**
```
Unit Tests → Integration Tests → System Tests → E2E Tests
    ↑              ↑                ↑            ↑
Individual     Component        Full System   User Journey
Components     Integration      Integration   Validation
```

**Example Integration Test:**
```java
@SpringBootTest
@AutoConfigureTestDatabase
class UserServiceIntegrationTest {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    @Transactional
    void shouldCreateUserAndSaveToDatabase() {
        // Integration test - service + repository + database
        User user = userService.createUser("John", "john@example.com");
        
        assertThat(user.getId()).isNotNull();
        
        Optional<User> saved = userRepository.findById(user.getId());
        assertThat(saved).isPresent();
        assertThat(saved.get().getName()).isEqualTo("John");
    }
}
```

**Benefits:**
- **Real Interactions:** Tests actual component communication
- **Configuration Validation:** Ensures proper setup and wiring
- **Data Integrity:** Validates data consistency across layers
- **Interface Contracts:** Verifies API agreements between components

---

### 337. How do you test Spring Boot applications?

**Answer:** Use @SpringBootTest to load the full application context, @WebMvcTest for web layer testing, @DataJpaTest for repository testing, @TestPropertySource for test-specific configurations, and TestRestTemplate or WebTestClient for HTTP endpoint testing with real or mock services.

**Spring Boot Testing Annotations:**

**1. Full Integration Testing:**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void shouldGetUsers() {
        ResponseEntity<User[]> response = restTemplate.getForEntity("/api/users", User[].class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }
}
```

**2. Web Layer Testing:**
```java
@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Test
    void shouldReturnUser() throws Exception {
        when(userService.findById(1L)).thenReturn(new User("John"));
        
        mockMvc.perform(get("/api/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("John"));
    }
}
```

**3. Repository Layer Testing:**
```java
@DataJpaTest
class UserRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void shouldFindByEmail() {
        User user = new User("John", "john@example.com");
        entityManager.persistAndFlush(user);
        
        Optional<User> found = userRepository.findByEmail("john@example.com");
        
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("John");
    }
}
```

**4. Service Layer Testing:**
```java
@SpringBootTest
@Transactional
class UserServiceIntegrationTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    void shouldCreateAndRetrieveUser() {
        User created = userService.createUser("John", "john@example.com");
        User retrieved = userService.findById(created.getId());
        
        assertThat(retrieved.getName()).isEqualTo("John");
    }
}
```

**Test Configuration:**
```yaml
# application-test.yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
  logging:
    level:
      org.springframework.web: DEBUG
```

---

### 338. What is @SpringBootTest?

**Answer:** @SpringBootTest is a Spring Boot annotation that loads the complete application context for integration testing. It starts the embedded server, loads all beans and configurations, supports different web environments, and enables testing of the full application stack including auto-configuration.

**@SpringBootTest Features:**

**1. Web Environment Options:**
```java
// No web environment (default)
@SpringBootTest
class ServiceTest { }

// Mock web environment
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class MockWebTest { }

// Random port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RandomPortTest {
    @LocalServerPort
    private int port;
}

// Defined port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DefinedPortTest { }
```

**2. Configuration Options:**
```java
@SpringBootTest(
    classes = {UserService.class, UserRepository.class}, // Specific classes
    properties = {"spring.profiles.active=test"},         // Properties
    args = {"--debug"}                                    // Command line args
)
class CustomConfigTest { }
```

**3. Complete Integration Test:**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.properties")
class FullIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    @Sql("/test-data.sql")
    void shouldProcessCompleteUserWorkflow() {
        // Create user via REST API
        User newUser = new User("John", "john@example.com");
        ResponseEntity<User> createResponse = restTemplate.postForEntity("/api/users", newUser, User.class);
        
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        
        // Verify in database
        Optional<User> saved = userRepository.findByEmail("john@example.com");
        assertThat(saved).isPresent();
        
        // Retrieve via REST API
        ResponseEntity<User> getResponse = restTemplate.getForEntity(
            "/api/users/" + saved.get().getId(), User.class);
        
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getName()).isEqualTo("John");
    }
}
```

**4. Test Slices vs Full Context:**

| Annotation | Context Loaded | Use Case |
|------------|----------------|----------|
| @SpringBootTest | Full application | Integration testing |
| @WebMvcTest | Web layer only | Controller testing |
| @DataJpaTest | JPA repositories | Repository testing |
| @JsonTest | JSON serialization | JSON testing |

---

### 339. How do you test REST APIs?

**Answer:** Use TestRestTemplate or WebTestClient for HTTP requests, MockMvc for controller testing without server startup, @AutoConfigureTestDatabase for database integration, verify HTTP status codes and response bodies, and test both success and error scenarios with proper assertions.

**REST API Testing Approaches:**

**1. TestRestTemplate (Blocking):**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void shouldCreateUser() {
        User user = new User("John", "john@example.com");
        
        ResponseEntity<User> response = restTemplate.postForEntity("/api/users", user, User.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getName()).isEqualTo("John");
        assertThat(response.getHeaders().getLocation()).isNotNull();
    }
    
    @Test
    void shouldReturnNotFoundForInvalidUser() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/users/999", String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
```

**2. WebTestClient (Reactive):**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiWebTestClientTest {
    
    @Autowired
    private WebTestClient webTestClient;
    
    @Test
    void shouldGetAllUsers() {
        webTestClient.get()
                    .uri("/api/users")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBodyList(User.class)
                    .hasSize(2)
                    .contains(new User("John", "john@example.com"));
    }
}
```

**3. MockMvc (No Server):**
```java
@WebMvcTest(UserController.class)
class UserControllerMockMvcTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Test
    void shouldCreateUser() throws Exception {
        User user = new User("John", "john@example.com");
        when(userService.createUser(any())).thenReturn(user);
        
        mockMvc.perform(post("/api/users")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("""
                           {
                               "name": "John",
                               "email": "john@example.com"
                           }
                           """))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.name").value("John"))
               .andExpect(jsonPath("$.email").value("john@example.com"));
    }
}
```

**4. Error Handling Testing:**
```java
@Test
void shouldHandleValidationErrors() throws Exception {
    mockMvc.perform(post("/api/users")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content("""
                       {
                           "name": "",
                           "email": "invalid-email"
                       }
                       """))
           .andExpect(status().isBadRequest())
           .andExpect(jsonPath("$.errors").isArray())
           .andExpect(jsonPath("$.errors[*].field").value(containsInAnyOrder("name", "email")));
}
```

---

### 340. How do you test database operations?

**Answer:** Use @DataJpaTest for repository layer testing, @Sql annotations to execute test data scripts, @Transactional for automatic rollback, TestEntityManager for test-specific database operations, and in-memory databases like H2 for fast, isolated testing.

**Database Testing Strategies:**

**1. Repository Testing with @DataJpaTest:**
```java
@DataJpaTest
class UserRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void shouldFindUsersByAge() {
        // Given
        User user1 = new User("John", 25);
        User user2 = new User("Jane", 30);
        User user3 = new User("Bob", 25);
        
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.flush();
        
        // When
        List<User> users = userRepository.findByAge(25);
        
        // Then
        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::getName).containsExactlyInAnyOrder("John", "Bob");
    }
}
```

**2. SQL Script Testing:**
```java
@SpringBootTest
@Transactional
class UserServiceDatabaseTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    @Sql("/test-data/users.sql")
    void shouldFindUsersFromTestData() {
        List<User> users = userService.findAll();
        
        assertThat(users).hasSize(3);
        assertThat(users).extracting(User::getName)
                         .containsExactly("Alice", "Bob", "Charlie");
    }
    
    @Test
    @Sql(scripts = "/test-data/users.sql", 
         executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", 
         executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void shouldCleanupAfterTest() {
        // Test with setup and cleanup
    }
}
```

**3. Transaction Testing:**
```java
@SpringBootTest
class TransactionTest {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    @Transactional
    @Rollback
    void shouldRollbackOnException() {
        long initialCount = userRepository.count();
        
        assertThrows(RuntimeException.class, () -> {
            userService.createUsersWithError();
        });
        
        // Verify rollback occurred
        assertThat(userRepository.count()).isEqualTo(initialCount);
    }
}
```

**4. Custom Database Configuration:**
```java
@TestConfiguration
public class TestDatabaseConfig {
    
    @Bean
    @Primary
    public DataSource testDataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("schema.sql")
            .addScript("test-data.sql")
            .build();
    }
}
```

**Test Data Files:**
```sql
-- test-data/users.sql
INSERT INTO users (name, email, age) VALUES 
('Alice', 'alice@example.com', 25),
('Bob', 'bob@example.com', 30),
('Charlie', 'charlie@example.com', 35);

-- cleanup.sql
DELETE FROM users;
```

---

### 341. What is TestContainers?

**Answer:** TestContainers is a library that provides lightweight, disposable containers for integration testing. It starts real database instances, message brokers, or other services in Docker containers, ensures test isolation, and automatically cleans up resources after tests complete.

**TestContainers Setup:**

**1. Dependencies:**
```xml
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <scope>test</scope>
</dependency>
```

**2. Database Container Testing:**
```java
@SpringBootTest
@Testcontainers
class UserServicePostgreSQLTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void shouldPersistUser() {
        User user = new User("John", "john@example.com");
        User saved = userRepository.save(user);
        
        assertThat(saved.getId()).isNotNull();
        
        Optional<User> found = userRepository.findById(saved.getId());
        assertThat(found).isPresent();
    }
}
```

**3. Multiple Containers:**
```java
@SpringBootTest
@Testcontainers
class IntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13");
    
    @Container
    static GenericContainer<?> redis = new GenericContainer<>("redis:6-alpine")
            .withExposedPorts(6379);
    
    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        // Database
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        
        // Redis
        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", redis::getFirstMappedPort);
        
        // Kafka
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }
}
```

**4. Custom Container:**
```java
@Testcontainers
class CustomServiceTest {
    
    @Container
    static GenericContainer<?> customService = new GenericContainer<>("my-service:latest")
            .withExposedPorts(8080)
            .withEnv("ENV", "test")
            .waitingFor(Wait.forHttp("/health").forStatusCode(200));
    
    @Test
    void shouldConnectToCustomService() {
        String serviceUrl = "http://" + customService.getHost() + ":" + 
                           customService.getMappedPort(8080);
        
        // Test integration with custom service
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(serviceUrl + "/api/data", String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
```

**Benefits:**
- **Real Dependencies:** Test against actual database/service versions
- **Isolation:** Each test gets fresh container instances
- **Portability:** Works on any machine with Docker
- **Cleanup:** Automatic container lifecycle management

---

### 342. How do you test microservices?

**Answer:** Implement contract testing with Pact or Spring Cloud Contract, use WireMock for service virtualization, test service boundaries and communication, validate circuit breakers and timeouts, and use TestContainers for dependent services in integration tests.

**Microservices Testing Strategies:**

**1. Contract Testing with Pact:**
```java
// Consumer test
@ExtendWith(PactConsumerTestExt.class)
class UserServiceConsumerTest {
    
    @Pact(consumer = "user-service", provider = "order-service")
    public RequestResponsePact getUserOrdersPact(PactDslWithProvider builder) {
        return builder
            .given("user has orders")
            .uponReceiving("get user orders")
            .path("/api/users/123/orders")
            .method("GET")
            .willRespondWith()
            .status(200)
            .body(newJsonArrayMinLike(1, object -> {
                object.numberType("id", 1);
                object.stringType("product", "Book");
            }).build())
            .toPact();
    }
    
    @Test
    @PactTestFor(pactMethod = "getUserOrdersPact")
    void shouldGetUserOrders(MockServer mockServer) {
        OrderServiceClient client = new OrderServiceClient(mockServer.getUrl());
        
        List<Order> orders = client.getUserOrders(123L);
        
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getProduct()).isEqualTo("Book");
    }
}
```

**2. WireMock for Service Virtualization:**
```java
@SpringBootTest
class UserServiceWireMockTest {
    
    @RegisterExtension
    static WireMockExtension wireMock = WireMockExtension.newInstance()
            .options(wireMockConfig().port(8089))
            .build();
    
    @Test
    void shouldHandleExternalServiceCall() {
        // Setup mock response
        wireMock.stubFor(get(urlEqualTo("/api/external/data"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                                "id": 1,
                                "data": "test"
                            }
                            """)));
        
        // Test service that calls external API
        ExternalData result = userService.fetchExternalData();
        
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getData()).isEqualTo("test");
        
        // Verify the call was made
        wireMock.verify(getRequestedFor(urlEqualTo("/api/external/data")));
    }
}
```

**3. Circuit Breaker Testing:**
```java
@SpringBootTest
class CircuitBreakerTest {
    
    @Autowired
    private UserService userService;
    
    @RegisterExtension
    static WireMockExtension wireMock = WireMockExtension.newInstance().build();
    
    @Test
    void shouldOpenCircuitBreakerOnFailures() {
        // Setup service to fail
        wireMock.stubFor(get(anyUrl())
                .willReturn(aResponse().withStatus(500)));
        
        // Make calls to trigger circuit breaker
        for (int i = 0; i < 5; i++) {
            assertThrows(RuntimeException.class, () -> userService.callExternalService());
        }
        
        // Circuit should be open now - calls should fail fast
        long startTime = System.currentTimeMillis();
        assertThrows(RuntimeException.class, () -> userService.callExternalService());
        long duration = System.currentTimeMillis() - startTime;
        
        // Should fail fast (< 100ms) instead of waiting for timeout
        assertThat(duration).isLessThan(100);
    }
}
```

**4. End-to-End Microservice Testing:**
```java
@SpringBootTest
@Testcontainers
class MicroserviceIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:13");
    
    @Container
    static GenericContainer<?> orderService = new GenericContainer<>("order-service:latest")
            .withExposedPorts(8080)
            .dependsOn(database);
    
    @Container
    static GenericContainer<?> paymentService = new GenericContainer<>("payment-service:latest")
            .withExposedPorts(8080);
    
    @Test
    void shouldProcessCompleteOrderWorkflow() {
        // Test complete workflow across multiple services
        String orderServiceUrl = "http://" + orderService.getHost() + ":" + 
                                orderService.getMappedPort(8080);
        
        // Create order
        RestTemplate restTemplate = new RestTemplate();
        Order order = new Order("user123", "product456", 100.0);
        
        ResponseEntity<Order> orderResponse = restTemplate.postForEntity(
            orderServiceUrl + "/api/orders", order, Order.class);
        
        assertThat(orderResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        
        // Verify order processing triggered payment
        // This would involve checking payment service or message queues
    }
}
```

---

### 343. What is contract testing?

**Answer:** Contract testing verifies that services can communicate correctly by testing the contracts between consumer and provider. It uses tools like Pact to define expectations, generates tests for both sides, prevents breaking changes, and enables independent service development and deployment.

**Contract Testing Concepts:**

**1. Consumer-Driven Contracts:**
```java
// Consumer defines what it expects from provider
@ExtendWith(PactConsumerTestExt.class)
class OrderServiceConsumerTest {
    
    @Pact(consumer = "order-service", provider = "user-service")
    public RequestResponsePact getUserPact(PactDslWithProvider builder) {
        return builder
            .given("user exists with id 123")
            .uponReceiving("get user by id")
            .path("/api/users/123")
            .method("GET")
            .willRespondWith()
            .status(200)
            .headers(Map.of("Content-Type", "application/json"))
            .body(newJsonBody(body -> {
                body.numberType("id", 123);
                body.stringType("name", "John Doe");
                body.stringType("email", "john@example.com");
            }).build())
            .toPact();
    }
    
    @Test
    @PactTestFor(pactMethod = "getUserPact")
    void shouldGetUserById(MockServer mockServer) {
        UserServiceClient client = new UserServiceClient(mockServer.getUrl());
        
        User user = client.getUserById(123L);
        
        assertThat(user.getId()).isEqualTo(123L);
        assertThat(user.getName()).isEqualTo("John Doe");
        assertThat(user.getEmail()).isEqualTo("john@example.com");
    }
}
```

**2. Provider Verification:**
```java
// Provider verifies it can fulfill the contract
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("user-service")
@PactFolder("pacts")
class UserServiceProviderTest {
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private UserRepository userRepository;
    
    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }
    
    @BeforeEach
    void setUp(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }
    
    @State("user exists with id 123")
    void userExistsWithId123() {
        User user = new User(123L, "John Doe", "john@example.com");
        userRepository.save(user);
    }
}
```

**3. Spring Cloud Contract:**
```groovy
// Contract definition (Groovy DSL)
Contract.make {
    description "should return user by id"
    request {
        method GET()
        url "/api/users/123"
        headers {
            accept(applicationJson())
        }
    }
    response {
        status OK()
        headers {
            contentType(applicationJson())
        }
        body(
            id: 123,
            name: "John Doe",
            email: "john@example.com"
        )
    }
}
```

**4. Contract Testing Benefits:**

| Benefit | Description |
|---------|-------------|
| **Independent Development** | Teams can develop services independently |
| **Breaking Change Detection** | Prevents incompatible API changes |
| **Documentation** | Contracts serve as API documentation |
| **Faster Feedback** | Catch integration issues early |
| **Confidence** | Deploy services knowing they'll work together |

---

### 344. What is end-to-end testing?

**Answer:** End-to-end testing validates complete user workflows across the entire application stack. It tests real user scenarios, includes all system components, uses production-like environments, and verifies that business requirements are met from user perspective to backend systems.

**E2E Testing Characteristics:**

**1. Complete System Testing:**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class OrderProcessingE2ETest {
    
    @Container
    static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:13");
    
    @Container
    static GenericContainer<?> redis = new GenericContainer<>("redis:6-alpine")
            .withExposedPorts(6379);
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void shouldProcessCompleteOrderWorkflow() {
        // 1. Create user
        User user = createUser("John Doe", "john@example.com");
        
        // 2. Add products to cart
        addProductToCart(user.getId(), "laptop", 1000.0);
        addProductToCart(user.getId(), "mouse", 25.0);
        
        // 3. Apply discount
        applyDiscount(user.getId(), "SAVE10");
        
        // 4. Process payment
        PaymentRequest payment = new PaymentRequest(user.getId(), "4111111111111111");
        ResponseEntity<PaymentResponse> paymentResponse = processPayment(payment);
        
        assertThat(paymentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(paymentResponse.getBody().isSuccessful()).isTrue();
        
        // 5. Verify order creation
        List<Order> orders = getUserOrders(user.getId());
        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getTotal()).isEqualTo(922.50); // With 10% discount
        
        // 6. Verify inventory update
        Product laptop = getProduct("laptop");
        assertThat(laptop.getStock()).isEqualTo(9); // Reduced by 1
        
        // 7. Verify notification sent
        verifyEmailSent(user.getEmail(), "Order Confirmation");
    }
    
    private User createUser(String name, String email) {
        User user = new User(name, email);
        ResponseEntity<User> response = restTemplate.postForEntity("/api/users", user, User.class);
        return response.getBody();
    }
    
    // Additional helper methods...
}
```

**2. Browser-Based E2E Testing (Selenium):**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebApplicationE2ETest {
    
    @LocalServerPort
    private int port;
    
    private WebDriver driver;
    
    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
    
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    void shouldCompleteUserRegistrationFlow() {
        // Navigate to registration page
        driver.get("http://localhost:" + port + "/register");
        
        // Fill registration form
        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("john@example.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        
        // Submit form
        driver.findElement(By.id("submit")).click();
        
        // Verify success page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("success-message"))
        );
        
        assertThat(successMessage.getText()).contains("Registration successful");
        
        // Verify redirect to dashboard
        assertThat(driver.getCurrentUrl()).contains("/dashboard");
    }
}
```

**3. API-Based E2E Testing:**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiE2ETest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void shouldHandleCompleteBookingWorkflow() {
        // Create customer
        Customer customer = createCustomer();
        
        // Search available rooms
        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(3);
        
        ResponseEntity<Room[]> roomsResponse = restTemplate.getForEntity(
            "/api/rooms/available?checkIn={checkIn}&checkOut={checkOut}", 
            Room[].class, checkIn, checkOut);
        
        assertThat(roomsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(roomsResponse.getBody()).isNotEmpty();
        
        // Make booking
        Room selectedRoom = roomsResponse.getBody()[0];
        BookingRequest booking = new BookingRequest(
            customer.getId(), selectedRoom.getId(), checkIn, checkOut);
        
        ResponseEntity<Booking> bookingResponse = restTemplate.postForEntity(
            "/api/bookings", booking, Booking.class);
        
        assertThat(bookingResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        
        // Verify booking confirmation
        Booking confirmedBooking = bookingResponse.getBody();
        assertThat(confirmedBooking.getStatus()).isEqualTo(BookingStatus.CONFIRMED);
        
        // Verify room is no longer available
        ResponseEntity<Room[]> updatedRoomsResponse = restTemplate.getForEntity(
            "/api/rooms/available?checkIn={checkIn}&checkOut={checkOut}", 
            Room[].class, checkIn, checkOut);
        
        List<Room> availableRooms = Arrays.asList(updatedRoomsResponse.getBody());
        assertThat(availableRooms).doesNotContain(selectedRoom);
    }
}
```

**E2E Testing Best Practices:**
- **Focus on Critical Paths:** Test most important user journeys
- **Use Production-Like Data:** Realistic test scenarios
- **Minimize UI Tests:** Prefer API-based E2E when possible
- **Parallel Execution:** Run tests in parallel for faster feedback
- **Environment Management:** Use containers for consistent environments

---

### 345. How do you handle test data management?

**Answer:** Use @Sql scripts for consistent test data setup, implement test data builders for flexible object creation, use @Transactional with rollback for data isolation, create separate test databases, and use factories or fixtures to generate realistic test data efficiently.

**Test Data Management Strategies:**

**1. SQL Scripts with @Sql:**
```java
@SpringBootTest
@Transactional
class UserServiceTestDataTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    @Sql("/test-data/users-basic.sql")
    void shouldFindBasicUsers() {
        List<User> users = userService.findAll();
        assertThat(users).hasSize(3);
    }
    
    @Test
    @Sql(scripts = {
        "/test-data/users-basic.sql",
        "/test-data/orders.sql"
    })
    void shouldFindUsersWithOrders() {
        List<User> usersWithOrders = userService.findUsersWithOrders();
        assertThat(usersWithOrders).hasSize(2);
    }
    
    @Test
    @Sql(value = "/test-data/large-dataset.sql", 
         executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/cleanup/delete-all.sql", 
         executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void shouldHandleLargeDataset() {
        // Test with large dataset and cleanup
    }
}
```

**2. Test Data Builders:**
```java
public class UserTestDataBuilder {
    private String name = "Default User";
    private String email = "default@example.com";
    private int age = 25;
    private List<String> roles = new ArrayList<>();
    
    public static UserTestDataBuilder aUser() {
        return new UserTestDataBuilder();
    }
    
    public UserTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }
    
    public UserTestDataBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
    
    public UserTestDataBuilder withAge(int age) {
        this.age = age;
        return this;
    }
    
    public UserTestDataBuilder withRoles(String... roles) {
        this.roles = Arrays.asList(roles);
        return this;
    }
    
    public User build() {
        User user = new User(name, email, age);
        user.setRoles(roles);
        return user;
    }
    
    public User buildAndSave(UserRepository repository) {
        return repository.save(build());
    }
}

// Usage in tests
@Test
void shouldCreateAdminUser() {
    User admin = aUser()
        .withName("Admin User")
        .withEmail("admin@example.com")
        .withRoles("ADMIN", "USER")
        .buildAndSave(userRepository);
    
    assertThat(admin.hasRole("ADMIN")).isTrue();
}
```

**3. Test Fixtures and Factories:**
```java
@Component
public class TestDataFactory {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    public User createUser(String name, String email) {
        User user = new User(name, email);
        return userRepository.save(user);
    }
    
    public User createUserWithOrders(String name, int orderCount) {
        User user = createUser(name, name.toLowerCase() + "@example.com");
        
        for (int i = 0; i < orderCount; i++) {
            Order order = new Order(user, "Product " + i, 100.0 * (i + 1));
            orderRepository.save(order);
        }
        
        return user;
    }
    
    public List<User> createUsersWithVariousAges() {
        return Arrays.asList(
            createUser("Young User", "young@example.com").withAge(20),
            createUser("Middle User", "middle@example.com").withAge(35),
            createUser("Senior User", "senior@example.com").withAge(60)
        );
    }
}

@SpringBootTest
@Transactional
class UserServiceFactoryTest {
    
    @Autowired
    private TestDataFactory testDataFactory;
    
    @Autowired
    private UserService userService;
    
    @Test
    void shouldFindUsersByAgeRange() {
        // Setup test data
        testDataFactory.createUsersWithVariousAges();
        
        // Test
        List<User> middleAgedUsers = userService.findByAgeRange(30, 40);
        
        assertThat(middleAgedUsers).hasSize(1);
        assertThat(middleAgedUsers.get(0).getName()).isEqualTo("Middle User");
    }
}
```

**4. Database State Management:**
```java
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class DatabaseStateTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @BeforeEach
    void setUp() {
        // Clean state before each test
        userRepository.deleteAll();
    }
    
    @Test
    @Order(1)
    @Commit // Don't rollback this test
    void shouldCreateInitialData() {
        User user = new User("Persistent User", "persistent@example.com");
        userRepository.save(user);
        
        assertThat(userRepository.count()).isEqualTo(1);
    }
    
    @Test
    @Order(2)
    void shouldFindPersistentData() {
        // This test depends on previous test data
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getName()).isEqualTo("Persistent User");
    }
}
```

**5. Random Test Data Generation:**
```java
public class RandomDataGenerator {
    
    private static final Random random = new Random();
    private static final List<String> FIRST_NAMES = Arrays.asList(
        "John", "Jane", "Bob", "Alice", "Charlie", "Diana"
    );
    private static final List<String> DOMAINS = Arrays.asList(
        "example.com", "test.org", "demo.net"
    );
    
    public static User randomUser() {
        String firstName = FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size()));
        String domain = DOMAINS.get(random.nextInt(DOMAINS.size()));
        String email = firstName.toLowerCase() + random.nextInt(1000) + "@" + domain;
        int age = 18 + random.nextInt(50);
        
        return new User(firstName, email, age);
    }
    
    public static List<User> randomUsers(int count) {
        return IntStream.range(0, count)
                       .mapToObj(i -> randomUser())
                       .collect(Collectors.toList());
    }
}

@Test
void shouldHandleRandomUserData() {
    List<User> randomUsers = RandomDataGenerator.randomUsers(100);
    userRepository.saveAll(randomUsers);
    
    List<User> adults = userService.findAdults();
    assertThat(adults).allMatch(user -> user.getAge() >= 18);
}
```

---

## Summary

Integration testing ensures components work together correctly:

**Testing Levels:**
- **Integration Testing:** Component interaction validation
- **Spring Boot Testing:** Framework-specific testing approaches
- **API Testing:** REST endpoint validation
- **Database Testing:** Data persistence and retrieval
- **E2E Testing:** Complete user workflow validation

**Key Tools:**
- **@SpringBootTest:** Full application context loading
- **TestContainers:** Real service dependencies in containers
- **MockMvc/TestRestTemplate:** HTTP endpoint testing
- **Pact/Spring Cloud Contract:** Contract testing between services
- **WireMock:** Service virtualization and mocking

**Best Practices:**
- **Test Isolation:** Independent, repeatable tests
- **Data Management:** Consistent test data setup and cleanup
- **Environment Consistency:** Use containers for reliable environments
- **Contract Testing:** Prevent breaking changes between services
- **Focused Testing:** Test critical paths and integration points

**Data Management:**
- **SQL Scripts:** Consistent data setup with @Sql
- **Test Builders:** Flexible object creation patterns
- **Factories:** Reusable test data generation
- **Transactions:** Automatic rollback for isolation
- **Random Data:** Realistic test scenarios with generated data