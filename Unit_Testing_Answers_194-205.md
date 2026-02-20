# ✅ 16) Unit Testing (JUnit, Mockito)

## 194. What is unit test vs integration test?

**Definition:** Unit tests verify individual components in isolation with mocked dependencies. Integration tests verify multiple components working together with real dependencies.

**Example:**
```java
// Unit Test - Tests service in isolation
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void shouldFindUserById() {
        User user = new User(1L, "John");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        
        User result = userService.findById(1L);
        
        assertEquals("John", result.getName());
    }
}

// Integration Test - Tests with real database
@SpringBootTest
@AutoConfigureTestDatabase
class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void shouldSaveAndRetrieveUser() {
        User user = new User(null, "John");
        User saved = userService.save(user);
        
        User found = userService.findById(saved.getId());
        
        assertEquals("John", found.getName());
    }
}
```

---

## 195. Explain AAA pattern (Arrange, Act, Assert).

**Definition:** AAA pattern structures tests into three sections: Arrange (setup), Act (execute), Assert (verify). It improves test readability and maintainability.

**Example:**
```java
@Test
void shouldCalculateTotalPrice() {
    // Arrange
    Order order = new Order();
    order.addItem(new Item("Book", 10.0));
    order.addItem(new Item("Pen", 2.0));
    
    // Act
    double total = order.calculateTotal();
    
    // Assert
    assertEquals(12.0, total);
}

@Test
void shouldApplyDiscount() {
    // Arrange
    Cart cart = new Cart();
    cart.addItem(100.0);
    DiscountService service = new DiscountService();
    
    // Act
    double discounted = service.applyDiscount(cart, 10);
    
    // Assert
    assertEquals(90.0, discounted);
}
```

---

## 196. What is JUnit 5 architecture?

**Definition:** JUnit 5 consists of three modules: JUnit Platform (test execution), JUnit Jupiter (new API), and JUnit Vintage (backward compatibility with JUnit 3/4).

**Example:**
```java
// JUnit Jupiter API
import org.junit.jupiter.api.*;

@DisplayName("Calculator Tests")
class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeAll
    static void setupAll() {
        System.out.println("Before all tests");
    }
    
    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Should add two numbers")
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }
    
    @AfterEach
    void tearDown() {
        calculator = null;
    }
    
    @AfterAll
    static void tearDownAll() {
        System.out.println("After all tests");
    }
}
```

---

## 197. Explain `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`.

**Definition:** These annotations control test lifecycle. @BeforeEach/@AfterEach run before/after each test. @BeforeAll/@AfterAll run once before/after all tests (must be static).

**Example:**
```java
class LifecycleTest {
    
    private Connection connection;
    private static Database database;
    
    @BeforeAll
    static void initDatabase() {
        database = new Database();
        database.start();
    }
    
    @BeforeEach
    void openConnection() {
        connection = database.getConnection();
    }
    
    @Test
    void testQuery1() {
        // connection is available
        assertNotNull(connection);
    }
    
    @Test
    void testQuery2() {
        // fresh connection for each test
        assertNotNull(connection);
    }
    
    @AfterEach
    void closeConnection() {
        connection.close();
    }
    
    @AfterAll
    static void shutdownDatabase() {
        database.stop();
    }
}
```

---

## 198. How do you write parameterized tests?

**Definition:** Parameterized tests run the same test logic with different input values, reducing code duplication and improving test coverage.

**Example:**
```java
class ParameterizedTests {
    
    @ParameterizedTest
    @ValueSource(strings = {"hello", "world", "test"})
    void testStringLength(String input) {
        assertTrue(input.length() > 0);
    }
    
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",
        "5, 5, 10",
        "10, 20, 30"
    })
    void testAddition(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }
    
    @ParameterizedTest
    @MethodSource("provideUsers")
    void testUserValidation(User user) {
        assertTrue(user.isValid());
    }
    
    static Stream<User> provideUsers() {
        return Stream.of(
            new User("John", 25),
            new User("Jane", 30)
        );
    }
}
```

---

## 199. What is Mockito? Explain `@Mock`, `@InjectMocks`, `@Spy`.

**Definition:** Mockito is a mocking framework for unit tests. @Mock creates mock objects, @InjectMocks injects mocks into the tested object, @Spy creates partial mocks.

**Example:**
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;  // Fully mocked
    
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private UserService userService;  // Mocks injected here
    
    @Spy
    private ValidationService validationService;  // Partial mock
    
    @Test
    void testCreateUser() {
        User user = new User("John");
        when(userRepository.save(any())).thenReturn(user);
        
        User created = userService.createUser(user);
        
        assertEquals("John", created.getName());
        verify(emailService).sendWelcomeEmail(user);
    }
}
```

---

## 200. Mock vs Spy - difference?

**Definition:** Mock creates a fake object with no real behavior. Spy wraps a real object, allowing real methods to be called unless stubbed.

**Example:**
```java
@ExtendWith(MockitoExtension.class)
class MockVsSpyTest {
    
    @Test
    void testMock() {
        List<String> mockList = mock(ArrayList.class);
        
        mockList.add("item");
        
        // Mock doesn't call real method
        assertEquals(0, mockList.size());  // Returns default value
        
        when(mockList.size()).thenReturn(5);
        assertEquals(5, mockList.size());
    }
    
    @Test
    void testSpy() {
        List<String> spyList = spy(new ArrayList<>());
        
        spyList.add("item");
        
        // Spy calls real method
        assertEquals(1, spyList.size());  // Real behavior
        
        when(spyList.size()).thenReturn(100);
        assertEquals(100, spyList.size());  // Stubbed behavior
    }
}
```

---

## 201. How do you stub method behavior (`when().thenReturn()`)?

**Definition:** Stubbing defines mock behavior by specifying what a method should return when called with specific arguments.

**Example:**
```java
@ExtendWith(MockitoExtension.class)
class StubbingTest {
    
    @Mock
    private UserRepository repository;
    
    @Test
    void testStubbing() {
        User user = new User(1L, "John");
        
        // Basic stubbing
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        
        // Multiple return values
        when(repository.count()).thenReturn(5L, 10L, 15L);
        
        // Throw exception
        when(repository.findById(999L))
            .thenThrow(new NotFoundException());
        
        // Argument matchers
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));
        
        // Verify
        assertEquals("John", repository.findById(1L).get().getName());
        assertEquals(5L, repository.count());
        assertEquals(10L, repository.count());
    }
}
```

---

## 202. How do you verify method invocations?

**Definition:** Verification confirms that specific methods were called with expected arguments and frequency during test execution.

**Example:**
```java
@ExtendWith(MockitoExtension.class)
class VerificationTest {
    
    @Mock
    private EmailService emailService;
    
    @Mock
    private UserRepository repository;
    
    @Test
    void testVerification() {
        User user = new User("John");
        
        emailService.send(user, "Welcome");
        repository.save(user);
        repository.save(user);
        
        // Verify method called once
        verify(emailService).send(user, "Welcome");
        
        // Verify method called twice
        verify(repository, times(2)).save(user);
        
        // Verify never called
        verify(emailService, never()).sendError(any());
        
        // Verify with argument matchers
        verify(emailService).send(any(User.class), eq("Welcome"));
        
        // Verify at least/at most
        verify(repository, atLeast(1)).save(user);
        verify(repository, atMost(3)).save(user);
    }
}
```

---

## 203. How do you test exceptions in JUnit 5?

**Definition:** JUnit 5 provides assertThrows to verify that code throws expected exceptions with specific messages.

**Example:**
```java
class ExceptionTest {
    
    @Test
    void shouldThrowException() {
        UserService service = new UserService();
        
        // Assert exception is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            service.createUser(null);
        });
    }
    
    @Test
    void shouldThrowExceptionWithMessage() {
        UserService service = new UserService();
        
        Exception exception = assertThrows(NotFoundException.class, () -> {
            service.findById(999L);
        });
        
        assertEquals("User not found", exception.getMessage());
    }
    
    @Test
    void shouldNotThrowException() {
        UserService service = new UserService();
        
        assertDoesNotThrow(() -> {
            service.findById(1L);
        });
    }
}
```

---

## 204. What is `@SpringBootTest`, `@WebMvcTest`, `@DataJpaTest`?

**Definition:** These are Spring Boot test slices. @SpringBootTest loads full context, @WebMvcTest tests web layer only, @DataJpaTest tests JPA repositories only.

**Example:**
```java
// Full application context
@SpringBootTest
class FullIntegrationTest {
    @Autowired
    private UserService userService;
    
    @Test
    void testFullStack() {
        User user = userService.createUser(new User("John"));
        assertNotNull(user.getId());
    }
}

// Web layer only
@WebMvcTest(UserController.class)
class WebLayerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Test
    void testGetUser() throws Exception {
        when(userService.findById(1L)).thenReturn(new User("John"));
        
        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John"));
    }
}

// JPA layer only
@DataJpaTest
class RepositoryTest {
    @Autowired
    private UserRepository repository;
    
    @Test
    void testSaveUser() {
        User user = repository.save(new User("John"));
        assertNotNull(user.getId());
    }
}
```

---

## 205. How do you test REST controllers using MockMvc?

**Definition:** MockMvc simulates HTTP requests to test REST controllers without starting a real server, verifying responses and status codes.

**Example:**
```java
@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Test
    void testGetUser() throws Exception {
        User user = new User(1L, "John");
        when(userService.findById(1L)).thenReturn(user);
        
        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("John"));
    }
    
    @Test
    void testCreateUser() throws Exception {
        User user = new User(null, "John");
        User saved = new User(1L, "John");
        when(userService.save(any())).thenReturn(saved);
        
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1));
    }
    
    @Test
    void testUpdateUser() throws Exception {
        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jane\"}"))
            .andExpect(status().isOk());
        
        verify(userService).update(eq(1L), any());
    }
    
    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
            .andExpect(status().isNoContent());
        
        verify(userService).delete(1L);
    }
    
    @Test
    void testNotFound() throws Exception {
        when(userService.findById(999L))
            .thenThrow(new NotFoundException());
        
        mockMvc.perform(get("/users/999"))
            .andExpect(status().isNotFound());
    }
}
```