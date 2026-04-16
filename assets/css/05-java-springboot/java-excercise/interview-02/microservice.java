// ============================================================
// FILE: MicroserviceInterviewDemo.java
// (Represents: MainApplication.java in real project)
// ============================================================

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import jakarta.persistence.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;

// Feign
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

// Resilience4j
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


// ============================================================
// MAIN APPLICATION
// FILE: MainApplication.java
// ============================================================
@EnableAsync
@EnableFeignClients
@SpringBootApplication
class MicroserviceInterviewDemo {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceInterviewDemo.class, args);
    }

    // FILE: AppConfig.java
    // RestTemplate Bean Configuration
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}


// ============================================================
// ENTITY: USER (Parent)
// FILE: entity/User.java
// ============================================================
@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Long getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}


// ============================================================
// ENTITY: ORDER (Child)
// FILE: entity/Order.java
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
// FILE: repository/UserRepository.java
// FILE: repository/OrderRepository.java
// ============================================================
interface UserRepository extends JpaRepository<User, Long> {}
interface OrderRepository extends JpaRepository<Order, Long> {}


// ============================================================
// REST TEMPLATE CLIENT (Legacy Communication)
// FILE: client/RestTemplateClient.java
// ============================================================
@Service
class RestTemplateClient {

    private final RestTemplate restTemplate;

    public RestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callPaymentService() {
        return restTemplate.getForObject(
                "http://localhost:8082/pay",
                String.class
        );
    }
}


// ============================================================
// FEIGN CLIENT (Modern Communication)
// FILE: client/PaymentClient.java
// ============================================================
@FeignClient(name = "payment-service", url = "http://localhost:8082")
interface PaymentClient {

    @PostMapping("/pay")
    String pay();
}


// ============================================================
// SERVICE: USER + ORDER (TRANSACTIONAL)
// FILE: service/UserService.java
// ============================================================
@Service
class UserService {

    private final UserRepository userRepo;
    private final OrderRepository orderRepo;

    public UserService(UserRepository userRepo,
                       OrderRepository orderRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }

    @Transactional
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
// SERVICE: PAYMENT (CIRCUIT BREAKER)
// FILE: service/PaymentService.java
// ============================================================
@Service
class PaymentService {

    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
    public String processPayment() {
        throw new RuntimeException("Payment Failed");
    }

    public String fallback(Exception ex) {
        return "Fallback: Payment Service Down";
    }
}


// ============================================================
// SERVICE: ASYNC
// FILE: service/AsyncService.java
// ============================================================
@Service
class AsyncService {

    @Async
    public CompletableFuture<String> asyncTask() {
        return CompletableFuture.completedFuture("Async Completed");
    }
}


// ============================================================
// SAGA ORCHESTRATOR (Distributed Transaction)
// FILE: service/OrderSagaService.java
// ============================================================
@Service
class OrderSagaService {

    private final PaymentService paymentService;
    private final RestTemplateClient restClient;
    private final PaymentClient feignClient;
    private final AsyncService asyncService;

    public OrderSagaService(PaymentService paymentService,
                            RestTemplateClient restClient,
                            PaymentClient feignClient,
                            AsyncService asyncService) {
        this.paymentService = paymentService;
        this.restClient = restClient;
        this.feignClient = feignClient;
        this.asyncService = asyncService;
    }

    public String executeSaga() {

        try {
            // Step 1: Order Created
            System.out.println("Order Created");

            // Step 2: Circuit Breaker Payment
            String payment = paymentService.processPayment();
            System.out.println(payment);

            // Step 3: RestTemplate Call
            String restResponse = restClient.callPaymentService();
            System.out.println("RestTemplate: " + restResponse);

            // Step 4: Feign Call
            String feignResponse = feignClient.pay();
            System.out.println("Feign: " + feignResponse);

            // Step 5: Async Processing
            asyncService.asyncTask();

            return "ORDER SUCCESS";

        } catch (Exception e) {

            // Compensation Logic
            System.out.println("Rolling back order...");

            return "ORDER FAILED - SAGA ROLLBACK";
        }
    }
}


// ============================================================
// CONTROLLER (API Layer)
// FILE: controller/UserController.java
// ============================================================
@RestController
@RequestMapping("/api")
class UserController {

    private final UserService userService;
    private final OrderSagaService sagaService;

    public UserController(UserService userService,
                          OrderSagaService sagaService) {
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
    public Order addOrder(@PathVariable Long id,
                          @RequestBody Order order) {
        return userService.addOrder(id, order);
    }

    // Run Saga Flow
    @PostMapping("/saga")
    public String runSaga() {
        return sagaService.executeSaga();
    }
}