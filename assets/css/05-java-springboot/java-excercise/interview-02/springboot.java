// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.stereotype.Service;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.scheduling.annotation.EnableAsync;
// import org.springframework.scheduling.annotation.Async;

// import jakarta.persistence.*;
// import java.util.*;
// import java.util.concurrent.CompletableFuture;

// Resilience4j
// import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@EnableAsync
@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}


// ============================================================
// ENTITY: User (Parent)
// ============================================================
@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Long getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}


// ============================================================
// ENTITY: Order (Child)
// ============================================================
@Entity
@Table(name = "orders")
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() { return id; }
    public String getProduct() { return product; }

    public void setProduct(String product) { this.product = product; }
    public void setUser(User user) { this.user = user; }
}


// ============================================================
// REPOSITORIES
// ============================================================
interface UserRepository extends JpaRepository<User, Long> {}
interface OrderRepository extends JpaRepository<Order, Long> {}


// ============================================================
// SERVICE: User Service (Transactional)
// ============================================================
@Service
class UserService {

    private final UserRepository userRepo;
    private final OrderRepository orderRepo;

    public UserService(UserRepository userRepo, OrderRepository orderRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }

    @Transactional // ensures atomicity: both user and order saved or both rolled back
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Transactional  
    public Order addOrder(Long userId, Order order) {
        User user = userRepo.findById(userId).orElseThrow();
        order.setUser(user);
        return orderRepo.save(order);
    }
}


// ============================================================
// SERVICE: Payment (Circuit Breaker)
// ============================================================
@Service
class PaymentService {

    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
    // Simulate payment processing with potential failure
    public String processPayment() {
        // simulate failure
        if (true) throw new RuntimeException("Payment Failed");
        return "Payment Success";
    }

    // Fallback method for Circuit Breaker
    public String fallback(Exception ex) {
        return "Fallback: Payment Service Down";
    }
}


// ============================================================
// SERVICE: Async
// ============================================================
@Service
class AsyncService {

    // Simulate an asynchronous task
    @Async
    public CompletableFuture<String> asyncTask() {
        return CompletableFuture.supplyAsync(() -> "Async Task Completed");
    }
}


// ============================================================
// SAGA ORCHESTRATOR
// ============================================================
@Service
class OrderSagaService {

    private final PaymentService paymentService;
    private final AsyncService asyncService;

    public OrderSagaService(PaymentService paymentService, AsyncService asyncService) {
        this.paymentService = paymentService;
        this.asyncService = asyncService;
    }

    public String createOrderSaga() {

        try {
            // Step 1: Order Created
            System.out.println("Order Created");

            // Step 2: Payment with Circuit Breaker
            String payment = paymentService.processPayment();
            System.out.println(payment);

            // Step 3: Async Task
            asyncService.asyncTask();

            return "Order Completed";

        } catch (Exception e) {
            // Compensation
            return "Saga Failed → Rolling Back Order";
        }
    }
}


// ============================================================
// CONTROLLER
// ============================================================
@RestController
@RequestMapping("/api")
class UserController {

    private final UserService userService;
    private final OrderSagaService sagaService;

    public UserController(UserService userService, OrderSagaService sagaService) {
        this.userService = userService;
        this.sagaService = sagaService;
    }

    // Create User
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Add Order
    @PostMapping("/users/{id}/orders")
    public Order addOrder(@PathVariable Long id, @RequestBody Order order) {
        return userService.addOrder(id, order);
    }

    // Run Saga
    @PostMapping("/saga")
    public String runSaga() {
        return sagaService.createOrderSaga();
    }
}