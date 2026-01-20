# 🔹 Testing

## Unit Testing Concepts - Questions & Answers

### 326. What is unit testing?

**Answer:** Unit testing is testing individual components or methods in isolation to verify they work correctly. It focuses on the smallest testable parts of an application, uses mocks for dependencies, runs fast and automatically, and provides quick feedback during development to catch bugs early.

**Key Characteristics:**
- **Isolation:** Tests single units without dependencies
- **Fast Execution:** Runs quickly for immediate feedback
- **Automated:** Can be run automatically in CI/CD pipelines
- **Repeatable:** Same results every time
- **Independent:** Tests don't affect each other

**Example Unit Test:**
```java
public class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    void shouldAddTwoNumbers() {
        // Arrange
        int a = 5, b = 3;
        
        // Act
        int result = calculator.add(a, b);
        
        // Assert
        assertEquals(8, result);
    }
}
```

**Benefits:**
- **Early Bug Detection:** Find issues during development
- **Documentation:** Tests serve as living documentation
- **Refactoring Safety:** Confidence when changing code
- **Design Improvement:** Forces better code design

---

### 327. What is JUnit?

**Answer:** JUnit is the most popular Java testing framework that provides annotations, assertions, and test runners for writing and executing unit tests. It supports test lifecycle management, parameterized tests, test suites, and integrates well with IDEs and build tools like Maven and Gradle.

**JUnit Evolution:**
- **JUnit 4:** Annotation-based testing
- **JUnit 5 (Jupiter):** Modern testing with lambda support

**Core Components:**
```xml
<!-- Maven dependency -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>
```

**Basic Test Structure:**
```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    
    @Test
    @DisplayName("Should create user with valid data")
    void shouldCreateUserWithValidData() {
        // Test implementation
        User user = new User("John", "john@example.com");
        assertNotNull(user);
        assertEquals("John", user.getName());
    }
}
```

**Key Features:**
- **Annotations:** @Test, @BeforeEach, @AfterEach
- **Assertions:** assertEquals, assertTrue, assertThrows
- **Test Lifecycle:** Setup and teardown methods
- **Parameterized Tests:** Data-driven testing
- **Test Suites:** Grouping related tests

---

### 328. What are the important JUnit annotations?

**Answer:** Key annotations include @Test for test methods, @BeforeEach and @AfterEach for setup and cleanup, @BeforeAll and @AfterAll for class-level setup, @DisplayName for readable test names, @ParameterizedTest for data-driven tests, and @Disabled to skip tests temporarily.

**Essential JUnit 5 Annotations:**

**Test Execution:**
```java
@Test
void shouldCalculateTotal() {
    // Test method
}

@ParameterizedTest
@ValueSource(ints = {1, 2, 3, 5, 8})
void shouldValidatePositiveNumbers(int number) {
    assertTrue(number > 0);
}
```

**Test Lifecycle:**
```java
class UserServiceTest {
    
    @BeforeAll
    static void setUpClass() {
        // Run once before all tests
        System.out.println("Setting up test class");
    }
    
    @BeforeEach
    void setUp() {
        // Run before each test
        userService = new UserService();
    }
    
    @AfterEach
    void tearDown() {
        // Run after each test
        userService.cleanup();
    }
    
    @AfterAll
    static void tearDownClass() {
        // Run once after all tests
        System.out.println("Cleaning up test class");
    }
}
```

**Test Configuration:**
```java
@DisplayName("User Service Tests")
class UserServiceTest {
    
    @Test
    @DisplayName("Should throw exception for invalid email")
    void shouldThrowExceptionForInvalidEmail() {
        assertThrows(IllegalArgumentException.class, 
                    () -> new User("John", "invalid-email"));
    }
    
    @Test
    @Disabled("Temporarily disabled due to external dependency")
    void shouldConnectToExternalService() {
        // Test implementation
    }
}
```

**Parameterized Tests:**
```java
@ParameterizedTest
@CsvSource({
    "1, 1, 2",
    "2, 3, 5", 
    "5, 7, 12"
})
void shouldAddNumbers(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}
```

---

### 329. What is the difference between @Before and @BeforeEach?

**Answer:** @Before is JUnit 4 annotation while @BeforeEach is JUnit 5. Both run before each test method, but @BeforeEach is part of the newer Jupiter API with better functionality. @BeforeAll replaces @BeforeClass, running once before all tests in the class.

**JUnit Version Comparison:**

| JUnit 4 | JUnit 5 | Purpose |
|---------|---------|---------|
| @Before | @BeforeEach | Run before each test |
| @After | @AfterEach | Run after each test |
| @BeforeClass | @BeforeAll | Run once before all tests |
| @AfterClass | @AfterAll | Run once after all tests |
| @Test | @Test | Mark test method |
| @Ignore | @Disabled | Skip test |

**JUnit 4 Example:**
```java
public class UserServiceTest {
    
    @BeforeClass
    public static void setUpClass() {
        // Setup once for all tests
    }
    
    @Before
    public void setUp() {
        // Setup before each test
        userService = new UserService();
    }
    
    @Test
    public void shouldCreateUser() {
        // Test implementation
    }
    
    @After
    public void tearDown() {
        // Cleanup after each test
    }
}
```

**JUnit 5 Example:**
```java
class UserServiceTest {
    
    @BeforeAll
    static void setUpClass() {
        // Setup once for all tests
    }
    
    @BeforeEach
    void setUp() {
        // Setup before each test
        userService = new UserService();
    }
    
    @Test
    void shouldCreateUser() {
        // Test implementation
    }
    
    @AfterEach
    void tearDown() {
        // Cleanup after each test
    }
}
```

**Key Differences:**
- **Package:** JUnit 4 uses `org.junit`, JUnit 5 uses `org.junit.jupiter.api`
- **Visibility:** JUnit 5 methods can be package-private
- **Features:** JUnit 5 has better parameterized tests and assertions

---

### 330. What is mocking?

**Answer:** Mocking creates fake objects that simulate real dependencies for testing. Mock objects have predefined behaviors, allow you to verify interactions, isolate the unit under test, and enable testing without external dependencies like databases or web services.

**Mocking Concepts:**

**Why Use Mocks:**
- **Isolation:** Test units independently
- **Speed:** Avoid slow external calls
- **Control:** Predictable test conditions
- **Reliability:** No dependency on external systems

**Mock vs Real Objects:**
```java
// Real object (slow, unpredictable)
UserRepository realRepo = new DatabaseUserRepository();

// Mock object (fast, predictable)
UserRepository mockRepo = mock(UserRepository.class);
when(mockRepo.findById(1L)).thenReturn(new User("John"));
```

**Basic Mocking Example:**
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void shouldFindUserById() {
        // Arrange
        User expectedUser = new User("John", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(expectedUser);
        
        // Act
        User actualUser = userService.findById(1L);
        
        // Assert
        assertEquals(expectedUser, actualUser);
        verify(userRepository).findById(1L);
    }
}
```

**Mock Behaviors:**
```java
// Return values
when(mockService.getData()).thenReturn("test data");

// Throw exceptions
when(mockService.process()).thenThrow(new RuntimeException("Error"));

// Multiple calls
when(mockService.getCount())
    .thenReturn(1)
    .thenReturn(2)
    .thenReturn(3);
```

---

### 331. What is Mockito?

**Answer:** Mockito is the most popular Java mocking framework that creates mock objects, stubs method calls with when-then syntax, verifies method invocations, supports argument matchers, and integrates seamlessly with JUnit for comprehensive unit testing.

**Mockito Setup:**
```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.1.1</version>
    <scope>test</scope>
</dependency>
```

**Core Mockito Features:**

**1. Creating Mocks:**
```java
// Using annotation
@Mock
private UserRepository userRepository;

// Programmatic creation
UserRepository userRepository = mock(UserRepository.class);

// Mock with name (for debugging)
UserRepository userRepository = mock(UserRepository.class, "userRepo");
```

**2. Stubbing Methods:**
```java
// Simple stubbing
when(userRepository.findById(1L)).thenReturn(user);

// Argument matchers
when(userRepository.findById(anyLong())).thenReturn(user);
when(userRepository.findByEmail(contains("@gmail"))).thenReturn(users);

// Conditional stubbing
when(userRepository.save(argThat(u -> u.getAge() > 18))).thenReturn(user);
```

**3. Verification:**
```java
// Verify method was called
verify(userRepository).findById(1L);

// Verify number of calls
verify(userRepository, times(2)).findById(anyLong());
verify(userRepository, never()).deleteById(anyLong());

// Verify no more interactions
verifyNoMoreInteractions(userRepository);
```

**4. Argument Capture:**
```java
@Captor
private ArgumentCaptor<User> userCaptor;

@Test
void shouldSaveUserWithCorrectData() {
    userService.createUser("John", "john@example.com");
    
    verify(userRepository).save(userCaptor.capture());
    User capturedUser = userCaptor.getValue();
    
    assertEquals("John", capturedUser.getName());
    assertEquals("john@example.com", capturedUser.getEmail());
}
```

---

### 332. How do you mock static methods?

**Answer:** In Mockito 3.4+, use MockedStatic with try-with-resources to mock static methods. Create a MockedStatic instance, define behavior with when-then, execute test code within the try block, and the mock automatically closes to restore original behavior.

**Static Method Mocking:**

**Dependency:**
```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-inline</artifactId>
    <version>5.1.1</version>
    <scope>test</scope>
</dependency>
```

**Basic Static Mocking:**
```java
@Test
void shouldMockStaticMethod() {
    try (MockedStatic<Utils> mockedUtils = mockStatic(Utils.class)) {
        // Arrange
        mockedUtils.when(() -> Utils.getCurrentTime())
                  .thenReturn(LocalDateTime.of(2023, 1, 1, 12, 0));
        
        // Act
        String result = serviceUnderTest.processWithCurrentTime();
        
        // Assert
        assertEquals("Processed at 2023-01-01T12:00", result);
        
        // Verify static method was called
        mockedUtils.verify(() -> Utils.getCurrentTime());
    }
}
```

**Multiple Static Calls:**
```java
@Test
void shouldMockMultipleStaticCalls() {
    try (MockedStatic<FileUtils> mockedFileUtils = mockStatic(FileUtils.class)) {
        // Mock different methods
        mockedFileUtils.when(() -> FileUtils.exists("test.txt"))
                      .thenReturn(true);
        mockedFileUtils.when(() -> FileUtils.readFile("test.txt"))
                      .thenReturn("file content");
        
        // Test code that uses static methods
        String content = fileService.loadFile("test.txt");
        
        assertEquals("file content", content);
    }
}
```

**Static Method with Arguments:**
```java
@Test
void shouldMockStaticMethodWithArguments() {
    try (MockedStatic<StringUtils> mockedStringUtils = mockStatic(StringUtils.class)) {
        mockedStringUtils.when(() -> StringUtils.capitalize(anyString()))
                        .thenAnswer(invocation -> {
                            String arg = invocation.getArgument(0);
                            return arg.toUpperCase();
                        });
        
        String result = textProcessor.formatText("hello");
        
        assertEquals("HELLO", result);
    }
}
```

**Partial Static Mocking:**
```java
@Test
void shouldPartiallyMockStaticClass() {
    try (MockedStatic<MathUtils> mockedMath = mockStatic(MathUtils.class, CALLS_REAL_METHODS)) {
        // Mock only specific method
        mockedMath.when(() -> MathUtils.random()).thenReturn(0.5);
        
        // Other methods use real implementation
        double result = calculator.calculateWithRandom();
        
        assertTrue(result > 0);
    }
}
```

---

### 333. What is the difference between mock and spy?

**Answer:** Mocks are completely fake objects with no real implementation, while spies are partial mocks of real objects. Spies call real methods unless stubbed, mocks return null/default values unless stubbed. Use spies when you need some real behavior, mocks for complete isolation.

**Mock vs Spy Comparison:**

| Aspect | Mock | Spy |
|--------|------|-----|
| **Base Object** | Fake object | Real object |
| **Default Behavior** | Returns null/defaults | Calls real methods |
| **Use Case** | Complete isolation | Partial mocking |
| **Performance** | Faster | Slower (real methods) |

**Mock Example:**
```java
@Test
void shouldUseMock() {
    // Mock - completely fake
    List<String> mockList = mock(List.class);
    
    // Returns null by default
    assertNull(mockList.get(0));
    
    // Must stub all interactions
    when(mockList.size()).thenReturn(5);
    when(mockList.get(0)).thenReturn("first");
    
    assertEquals(5, mockList.size());
    assertEquals("first", mockList.get(0));
}
```

**Spy Example:**
```java
@Test
void shouldUseSpy() {
    // Spy - real object with selective stubbing
    List<String> spyList = spy(new ArrayList<>());
    
    // Real method calls work
    spyList.add("real item");
    assertEquals(1, spyList.size());
    assertEquals("real item", spyList.get(0));
    
    // Can stub specific methods
    when(spyList.size()).thenReturn(100);
    assertEquals(100, spyList.size()); // Stubbed
    assertEquals("real item", spyList.get(0)); // Real method
}
```

**Practical Usage:**

**Mock for External Dependencies:**
```java
@Mock
private EmailService emailService; // Don't want real emails

@Mock
private PaymentGateway paymentGateway; // Don't want real payments

@Test
void shouldProcessOrder() {
    when(paymentGateway.charge(any())).thenReturn(true);
    
    orderService.processOrder(order);
    
    verify(emailService).sendConfirmation(any());
}
```

**Spy for Partial Testing:**
```java
@Spy
private OrderService orderService; // Want most real behavior

@Test
void shouldCalculateDiscountButSkipEmail() {
    // Use real calculation logic
    doNothing().when(orderService).sendNotification(any()); // Skip email
    
    Order result = orderService.processOrderWithDiscount(order);
    
    // Real calculation happened, but no email sent
    assertTrue(result.getTotal() < order.getTotal());
    verify(orderService).sendNotification(any());
}
```

---

### 334. What is test-driven development (TDD)?

**Answer:** TDD is a development approach where you write tests before implementation. Follow Red-Green-Refactor cycle: write failing test (Red), write minimal code to pass (Green), then improve code quality (Refactor). This ensures better design, full test coverage, and confidence in changes.

**TDD Cycle:**

**1. Red Phase (Write Failing Test):**
```java
@Test
void shouldCalculateAreaOfRectangle() {
    // Test written first - will fail
    Rectangle rectangle = new Rectangle(5, 3);
    
    double area = rectangle.calculateArea();
    
    assertEquals(15.0, area);
}
```

**2. Green Phase (Make Test Pass):**
```java
public class Rectangle {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    public double calculateArea() {
        return width * height; // Minimal implementation
    }
}
```

**3. Refactor Phase (Improve Code):**
```java
public class Rectangle {
    private final double width;
    private final double height;
    
    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        this.width = width;
        this.height = height;
    }
    
    public double calculateArea() {
        return width * height;
    }
    
    // Add more methods as needed
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}
```

**TDD Benefits:**
- **Better Design:** Forces thinking about API first
- **Full Coverage:** Tests written for all functionality
- **Confidence:** Safe refactoring with test safety net
- **Documentation:** Tests serve as specifications

**TDD Example - Complete Cycle:**
```java
// 1. RED: Write failing test
@Test
void shouldValidateEmailFormat() {
    User user = new User("John", "invalid-email");
    
    assertThrows(IllegalArgumentException.class, 
                () -> user.validate());
}

// 2. GREEN: Minimal implementation
public class User {
    private String name;
    private String email;
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public void validate() {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}

// 3. REFACTOR: Improve implementation
public void validate() {
    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
        throw new IllegalArgumentException("Invalid email format");
    }
}
```

---

### 335. What are testing best practices?

**Answer:** Write independent tests that don't depend on each other, use descriptive test names that explain what's being tested, follow AAA pattern (Arrange-Act-Assert), keep tests simple and focused, use mocks for external dependencies, and maintain fast execution times for quick feedback.

**Testing Best Practices:**

**1. Test Independence:**
```java
// BAD: Tests depend on each other
class UserServiceTest {
    private static User createdUser;
    
    @Test
    void shouldCreateUser() {
        createdUser = userService.create("John");
        assertNotNull(createdUser);
    }
    
    @Test
    void shouldFindCreatedUser() {
        // Depends on previous test
        User found = userService.findById(createdUser.getId());
        assertEquals("John", found.getName());
    }
}

// GOOD: Independent tests
class UserServiceTest {
    
    @Test
    void shouldCreateUser() {
        User user = userService.create("John");
        assertNotNull(user);
        assertEquals("John", user.getName());
    }
    
    @Test
    void shouldFindUserById() {
        // Setup own test data
        User user = userService.create("Jane");
        
        User found = userService.findById(user.getId());
        assertEquals("Jane", found.getName());
    }
}
```

**2. Descriptive Test Names:**
```java
// BAD: Unclear test names
@Test
void test1() { }

@Test
void testUser() { }

// GOOD: Descriptive names
@Test
void shouldThrowExceptionWhenEmailIsNull() { }

@Test
void shouldReturnEmptyListWhenNoUsersExist() { }

@Test
@DisplayName("Should calculate 10% discount for premium users")
void shouldCalculateDiscountForPremiumUsers() { }
```

**3. AAA Pattern (Arrange-Act-Assert):**
```java
@Test
void shouldCalculateTotalWithTax() {
    // Arrange
    Order order = new Order();
    order.addItem(new Item("Book", 20.00));
    order.addItem(new Item("Pen", 5.00));
    TaxCalculator calculator = new TaxCalculator(0.1); // 10% tax
    
    // Act
    double total = calculator.calculateTotal(order);
    
    // Assert
    assertEquals(27.50, total, 0.01);
}
```

**4. Single Responsibility per Test:**
```java
// BAD: Testing multiple things
@Test
void shouldCreateAndUpdateAndDeleteUser() {
    User user = userService.create("John");
    assertNotNull(user);
    
    user.setName("Jane");
    userService.update(user);
    assertEquals("Jane", user.getName());
    
    userService.delete(user.getId());
    assertNull(userService.findById(user.getId()));
}

// GOOD: Separate tests
@Test
void shouldCreateUser() {
    User user = userService.create("John");
    assertNotNull(user);
    assertEquals("John", user.getName());
}

@Test
void shouldUpdateUserName() {
    User user = userService.create("John");
    
    user.setName("Jane");
    userService.update(user);
    
    assertEquals("Jane", user.getName());
}
```

**5. Use Test Data Builders:**
```java
// Test data builder pattern
public class UserTestDataBuilder {
    private String name = "Default Name";
    private String email = "default@example.com";
    private int age = 25;
    
    public UserTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }
    
    public UserTestDataBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
    
    public User build() {
        return new User(name, email, age);
    }
}

// Usage in tests
@Test
void shouldValidateUserAge() {
    User user = new UserTestDataBuilder()
        .withName("John")
        .withAge(17)
        .build();
    
    assertThrows(ValidationException.class, () -> user.validate());
}
```

**6. Fast and Reliable Tests:**
```java
// Use mocks for external dependencies
@Mock
private DatabaseConnection database;

@Mock
private EmailService emailService;

@Test
void shouldProcessOrderQuickly() {
    // Fast test with mocks
    when(database.save(any())).thenReturn(true);
    
    orderService.processOrder(order);
    
    verify(emailService).sendConfirmation(any());
}
```

---

## Summary

Unit testing is fundamental to quality software development:

**Core Concepts:**
- **Unit Testing:** Testing individual components in isolation
- **JUnit:** Primary Java testing framework with annotations and assertions
- **Mocking:** Creating fake objects to isolate units under test
- **TDD:** Test-first development approach for better design

**Essential Tools:**
- **JUnit 5:** Modern testing framework with Jupiter API
- **Mockito:** Powerful mocking framework for Java
- **AssertJ:** Fluent assertions for better readability

**Best Practices:**
- **Independence:** Tests should not depend on each other
- **Clarity:** Use descriptive names and follow AAA pattern
- **Focus:** One assertion per test, single responsibility
- **Speed:** Fast execution with mocks for external dependencies
- **Maintainability:** Keep tests simple and well-organized

**Key Annotations:**
- **@Test:** Mark test methods
- **@BeforeEach/@AfterEach:** Setup and cleanup
- **@Mock/@InjectMocks:** Mockito integration
- **@ParameterizedTest:** Data-driven testing

**Testing Strategy:**
1. Write tests for all public methods
2. Test edge cases and error conditions
3. Use mocks for external dependencies
4. Maintain high test coverage
5. Keep tests fast and reliable