# 🔵 23. Testing

# 🔹 Testing Fundamentals

### Question 346: What is unit testing in Java?

**Answer (30 seconds):**
* Testing individual components or methods in isolation
* **Fast Execution**: Tests run quickly without external dependencies
* **Automated**: Can be run automatically in CI/CD pipelines
* **Isolated**: Each test is independent and doesn't affect others
* **Frameworks**: JUnit, TestNG are popular Java testing frameworks
* **Best Practice**: Write tests before or alongside production code

```java
// Simple unit test example
public class CalculatorTest {
    private Calculator calculator = new Calculator();
    
    @Test
    public void testAddition() {
        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }
    
    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        });
    }
}
```

---

### Question 347: What is JUnit?

**Answer (25 seconds):**
* Most popular unit testing framework for Java applications
* **Annotations**: @Test, @BeforeEach, @AfterEach for test lifecycle
* **Assertions**: assertEquals, assertTrue, assertThrows for verification
* **Test Runners**: Execute tests and report results
* **Integration**: Works with IDEs, build tools, and CI systems
* **Current Version**: JUnit 5 (Jupiter) is the latest

```java
// JUnit 5 example
class UserServiceTest {
    
    @BeforeEach
    void setUp() {
        userService = new UserService();
    }
    
    @Test
    @DisplayName("Should create user with valid data")
    void shouldCreateUserWithValidData() {
        User user = userService.createUser("John", "john@email.com");
        
        assertAll(
            () -> assertNotNull(user.getId()),
            () -> assertEquals("John", user.getName()),
            () -> assertEquals("john@email.com", user.getEmail())
        );
    }
}
```

---

### Question 348: What are the annotations used in JUnit?

**Answer (35 seconds):**
* **@Test**: Marks method as test case
* **@BeforeEach**: Runs before each test method
* **@AfterEach**: Runs after each test method
* **@BeforeAll**: Runs once before all tests in class
* **@AfterAll**: Runs once after all tests in class
* **@DisplayName**: Custom test name for reports
* **@Disabled**: Skip test execution
* **@ParameterizedTest**: Run test with multiple parameters

```java
class JUnitAnnotationsExample {
    
    @BeforeAll
    static void initAll() {
        // Setup once for all tests
    }
    
    @BeforeEach
    void init() {
        // Setup before each test
    }
    
    @Test
    @DisplayName("Custom test name")
    void testMethod() {
        assertTrue(true);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"hello", "world"})
    void testWithParameters(String input) {
        assertNotNull(input);
    }
    
    @Disabled("Not implemented yet")
    @Test
    void skippedTest() {
        // This test will be skipped
    }
}
```

---

### Question 349: What is TestNG?

**Answer (30 seconds):**
* Alternative testing framework to JUnit with additional features
* **Flexible Configuration**: XML configuration files for test suites
* **Data Providers**: Built-in support for parameterized tests
* **Parallel Execution**: Run tests in parallel out of the box
* **Dependency Testing**: Tests can depend on other tests
* **Groups**: Organize tests into logical groups
* **Better Reporting**: Enhanced HTML reports

```java
// TestNG example
public class TestNGExample {
    
    @BeforeClass
    public void setUp() {
        // Setup for all tests in class
    }
    
    @Test(groups = "smoke")
    public void smokeTest() {
        assertTrue(true);
    }
    
    @Test(dependsOnMethods = "smokeTest")
    public void dependentTest() {
        // Runs only if smokeTest passes
    }
    
    @DataProvider
    public Object[][] testData() {
        return new Object[][] {{"test1"}, {"test2"}};
    }
    
    @Test(dataProvider = "testData")
    public void parameterizedTest(String input) {
        assertNotNull(input);
    }
}
```

---

### Question 350: What is the difference between JUnit and TestNG?

**Answer (35 seconds):**
* **Configuration**: TestNG uses XML, JUnit uses annotations
* **Parallel Execution**: TestNG has built-in support, JUnit needs plugins
* **Test Dependencies**: TestNG supports dependent tests, JUnit doesn't
* **Data Providers**: TestNG has built-in data providers, JUnit uses parameters
* **Grouping**: TestNG has test groups, JUnit uses tags
* **Reporting**: TestNG has better default reports
* **Popularity**: JUnit is more widely used, TestNG popular in enterprise

```java
// JUnit approach
@ParameterizedTest
@ValueSource(ints = {1, 2, 3})
void junitParameterized(int value) {
    assertTrue(value > 0);
}

// TestNG approach
@DataProvider
public Object[][] data() {
    return new Object[][]{{1}, {2}, {3}};
}

@Test(dataProvider = "data", groups = "unit")
public void testngParameterized(int value) {
    assertTrue(value > 0);
}
```

---

### Question 351: What is mocking in Java testing?

**Answer (30 seconds):**
* Creating fake objects to simulate real dependencies in tests
* **Isolation**: Test units without depending on external systems
* **Control**: Define exact behavior of dependencies
* **Verification**: Check if methods were called with correct parameters
* **Frameworks**: Mockito, EasyMock, PowerMock
* **Types**: Mock, Stub, Spy objects with different behaviors

```java
// Mockito example
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void shouldFindUserById() {
        // Given
        User mockUser = new User(1L, "John");
        when(userRepository.findById(1L)).thenReturn(mockUser);
        
        // When
        User result = userService.findById(1L);
        
        // Then
        assertEquals("John", result.getName());
        verify(userRepository).findById(1L);
    }
}
```

---

### Question 352: What is Mockito?

**Answer (30 seconds):**
* Most popular mocking framework for Java unit testing
* **Easy Syntax**: Simple and readable mocking API
* **Annotations**: @Mock, @InjectMocks, @Spy for setup
* **Stubbing**: Define return values with when().thenReturn()
* **Verification**: Check method calls with verify()
* **Argument Matchers**: Flexible parameter matching
* **Spying**: Partial mocking of real objects

```java
// Mockito features
@ExtendWith(MockitoExtension.class)
class MockitoExample {
    
    @Mock
    private PaymentService paymentService;
    
    @Test
    void mockitoFeatures() {
        // Stubbing
        when(paymentService.processPayment(any(Payment.class)))
            .thenReturn(new PaymentResult(true));
        
        // Method call
        PaymentResult result = paymentService.processPayment(new Payment(100));
        
        // Verification
        verify(paymentService).processPayment(argThat(p -> p.getAmount() == 100));
        
        // Assertion
        assertTrue(result.isSuccess());
    }
}
```

---

### Question 353: What is integration testing?

**Answer (30 seconds):**
* Testing interaction between multiple components or systems
* **Real Dependencies**: Uses actual databases, web services, file systems
* **End-to-End**: Tests complete workflows across system boundaries
* **Spring Boot**: @SpringBootTest for full application context
* **Test Containers**: Docker containers for isolated test environments
* **Slower**: Takes more time than unit tests but provides higher confidence

```java
// Integration test example
@SpringBootTest
@Testcontainers
class UserIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    
    @Autowired
    private UserService userService;
    
    @Test
    void shouldSaveAndRetrieveUser() {
        // Given
        User user = new User("John", "john@email.com");
        
        // When
        User saved = userService.save(user);
        User retrieved = userService.findById(saved.getId());
        
        // Then
        assertEquals("John", retrieved.getName());
    }
}
```

---

### Question 354: What is test-driven development (TDD)?

**Answer (35 seconds):**
* Development approach where tests are written before production code
* **Red-Green-Refactor**: Write failing test, make it pass, improve code
* **Benefits**: Better design, higher test coverage, fewer bugs
* **Test First**: Forces thinking about requirements and API design
* **Small Steps**: Incremental development with immediate feedback
* **Confidence**: Refactoring is safer with comprehensive test suite

```java
// TDD example - Red phase (failing test)
@Test
void shouldCalculateAreaOfRectangle() {
    Rectangle rectangle = new Rectangle(5, 3);
    assertEquals(15, rectangle.getArea()); // This will fail initially
}

// Green phase - minimal implementation
public class Rectangle {
    private int width, height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public int getArea() {
        return width * height; // Make test pass
    }
}

// Refactor phase - improve code quality while keeping tests green
```

# 🔹 Advanced Testing

### Question 355: What is behavior-driven development (BDD)?

**Answer (35 seconds):**
* Extension of TDD focusing on behavior specification in natural language
* **Given-When-Then**: Structure tests as scenarios with clear steps
* **Collaboration**: Involves developers, testers, and business stakeholders
* **Living Documentation**: Tests serve as executable specifications
* **Tools**: Cucumber, JBehave for Java BDD frameworks
* **User Stories**: Tests written from user perspective

```java
// BDD with Cucumber
// Feature file (Gherkin syntax)
/*
Feature: User Registration
  Scenario: Successful user registration
    Given a new user with email "john@email.com"
    When the user registers with valid information
    Then the user should be created successfully
    And a welcome email should be sent
*/

// Step definitions
public class UserRegistrationSteps {
    
    @Given("a new user with email {string}")
    public void aNewUserWithEmail(String email) {
        this.user = new User(email);
    }
    
    @When("the user registers with valid information")
    public void theUserRegistersWithValidInformation() {
        this.result = userService.register(user);
    }
    
    @Then("the user should be created successfully")
    public void theUserShouldBeCreatedSuccessfully() {
        assertTrue(result.isSuccess());
    }
}
```

---

### Question 356: What is acceptance testing?

**Answer (30 seconds):**
* Testing to verify system meets business requirements and user needs
* **User Perspective**: Tests from end-user point of view
* **Business Criteria**: Validates acceptance criteria are met
* **Black Box**: Tests functionality without knowing internal implementation
* **Automated**: Often automated using tools like Selenium, REST Assured
* **Sign-off**: Final validation before production deployment

```java
// Acceptance test example
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserAcceptanceTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void userCanRegisterAndLogin() {
        // User registration
        UserRegistrationRequest request = new UserRegistrationRequest("john@email.com", "password");
        ResponseEntity<String> registerResponse = restTemplate.postForEntity("/api/register", request, String.class);
        assertEquals(HttpStatus.CREATED, registerResponse.getStatusCode());
        
        // User login
        LoginRequest loginRequest = new LoginRequest("john@email.com", "password");
        ResponseEntity<String> loginResponse = restTemplate.postForEntity("/api/login", loginRequest, String.class);
        assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        assertNotNull(loginResponse.getBody()); // JWT token
    }
}
```

---

### Question 357: What is contract testing?

**Answer (35 seconds):**
* Testing to ensure services can communicate correctly with each other
* **API Contracts**: Verify API specifications are followed
* **Consumer-Driven**: Consumers define expectations for providers
* **Pact**: Popular framework for contract testing
* **Microservices**: Essential for distributed system reliability
* **Early Detection**: Catch integration issues before deployment
* **Documentation**: Contracts serve as API documentation

```java
// Pact contract testing example
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "user-service")
class UserServiceContractTest {
    
    @Pact(consumer = "order-service")
    public RequestResponsePact getUserPact(PactDslWithProvider builder) {
        return builder
            .given("user exists")
            .uponReceiving("get user by id")
            .path("/users/1")
            .method("GET")
            .willRespondWith()
            .status(200)
            .body(newJsonBody(body -> {
                body.numberType("id", 1);
                body.stringType("name", "John");
                body.stringType("email", "john@email.com");
            }).build())
            .toPact();
    }
    
    @Test
    void testGetUser(MockServer mockServer) {
        UserServiceClient client = new UserServiceClient(mockServer.getUrl());
        User user = client.getUser(1L);
        assertEquals("John", user.getName());
    }
}
```

---

### Question 358: What is mutation testing?

**Answer (30 seconds):**
* Testing technique that evaluates quality of test suite by introducing bugs
* **Mutants**: Modified versions of code with small changes
* **Mutation Score**: Percentage of mutants killed by tests
* **Test Quality**: Measures how well tests detect defects
* **Tools**: PIT (Pitest) is popular Java mutation testing tool
* **Expensive**: Computationally intensive but provides valuable insights

```java
// Original code
public class Calculator {
    public int add(int a, int b) {
        return a + b; // Mutant might change + to -
    }
    
    public boolean isPositive(int number) {
        return number > 0; // Mutant might change > to >=
    }
}

// Test that would catch mutations
@Test
void testAddition() {
    assertEquals(5, calculator.add(2, 3)); // Would catch + to - mutation
    assertEquals(0, calculator.add(-2, 2)); // Edge case
}

@Test
void testIsPositive() {
    assertTrue(calculator.isPositive(1));   // Would catch > to >= mutation
    assertFalse(calculator.isPositive(0));  // Critical for boundary
    assertFalse(calculator.isPositive(-1));
}
```

---

### Question 359: What is performance testing?

**Answer (35 seconds):**
* Testing to evaluate system performance under various load conditions
* **Load Testing**: Normal expected load
* **Stress Testing**: Beyond normal capacity to find breaking point
* **Volume Testing**: Large amounts of data
* **Spike Testing**: Sudden load increases
* **Tools**: JMeter, Gatling for Java applications
* **Metrics**: Response time, throughput, resource utilization

```java
// JUnit performance test
@Test
@Timeout(value = 2, unit = TimeUnit.SECONDS)
void shouldCompleteWithinTimeLimit() {
    // Test must complete within 2 seconds
    String result = expensiveOperation();
    assertNotNull(result);
}

// Gatling performance test (Scala-based but for Java apps)
/*
class UserSimulation extends Simulation {
  val httpProtocol = http.baseUrl("http://localhost:8080")
  
  val scn = scenario("User Load Test")
    .exec(http("Get Users").get("/api/users"))
    .pause(1)
    .exec(http("Create User").post("/api/users").body(StringBody("""{"name":"test"}""")))
  
  setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}
*/

// Microbenchmark with JMH
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringConcatenationBenchmark {
    
    @Benchmark
    public String stringBuilder() {
        return new StringBuilder().append("Hello").append(" World").toString();
    }
}
```

---

### Question 360: What is security testing?

**Answer (35 seconds):**
* Testing to identify security vulnerabilities and ensure data protection
* **Authentication**: Verify login mechanisms work correctly
* **Authorization**: Ensure users can only access permitted resources
* **Input Validation**: Test for SQL injection, XSS vulnerabilities
* **Session Management**: Verify secure session handling
* **Tools**: OWASP ZAP, SonarQube for security analysis
* **Penetration Testing**: Simulated attacks to find weaknesses

```java
// Security testing examples
@SpringBootTest
class SecurityTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void shouldRequireAuthenticationForProtectedEndpoint() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
            .andExpect(status().isUnauthorized());
    }
    
    @Test
    void shouldPreventSQLInjection() throws Exception {
        String maliciousInput = "'; DROP TABLE users; --";
        
        mockMvc.perform(get("/api/users/search")
                .param("name", maliciousInput))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(0)); // No results, table not dropped
    }
    
    @Test
    void shouldPreventXSS() throws Exception {
        String xssPayload = "<script>alert('xss')</script>";
        
        mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"text\":\"" + xssPayload + "\"}"))
            .andExpect(status().isBadRequest()); // Input validation should reject
    }
    
    @Test
    @WithMockUser(roles = "USER")
    void shouldDenyAccessToAdminEndpoint() throws Exception {
        mockMvc.perform(get("/api/admin/settings"))
            .andExpect(status().isForbidden());
    }
}
```
