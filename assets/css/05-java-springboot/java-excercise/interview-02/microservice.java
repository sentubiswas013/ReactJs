// ============================================================
// FILE: MicroserviceInterviewDemo.java
// (Includes: Caching + Microservices Concepts)
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

import org.springframework.cache.annotation.*;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

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
@EnableCaching  // ✅ ENABLE CACHING
@SpringBootApplication
class MicroserviceInterviewDemo {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceInterviewDemo.class, args);
    }

    // FILE: AppConfig.java
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // FILE: CacheConfig.java
    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager("users");
    }
}


// ============================================================
// ENTITY: USER
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
// ENTITY: ORDER
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
// REST TEMPLATE CLIENT
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
// FEIGN CLIENT
// FILE: client/PaymentClient.java
// ============================================================
@FeignClient(name = "payment-service", url = "http://localhost:8082")
interface PaymentClient {

    @PostMapping("/pay")
    String pay();
}


// ============================================================
// SERVICE: USER + CACHE + TRANSACTION
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

    // SAVE USER (Update Cache)
    @Transactional
    @CachePut(value = "users", key = "#result.id")
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    // GET USER (Cache Read)
    @Cacheable(value = "users", key = "#id")
    public User getUser(Long id) {
        System.out.println("Fetching from DB...");
        return userRepo.findById(id).orElseThrow();
    }

    // DELETE USER (Evict Cache)
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
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
// SAGA ORCHESTRATOR
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
            System.out.println("Order Created");

            String payment = paymentService.processPayment();
            System.out.println(payment);

            String restResponse = restClient.callPaymentService();
            System.out.println("RestTemplate: " + restResponse);

            String feignResponse = feignClient.pay();
            System.out.println("Feign: " + feignResponse);

            asyncService.asyncTask();

            return "ORDER SUCCESS";

        } catch (Exception e) {

            System.out.println("Rolling back order...");
            return "ORDER FAILED - SAGA ROLLBACK";
        }
    }
}


// ============================================================
// CONTROLLER
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

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "Deleted";
    }

    @PostMapping("/users/{id}/orders")
    public Order addOrder(@PathVariable Long id,
                          @RequestBody Order order) {
        return userService.addOrder(id, order);
    }

    @PostMapping("/saga")
    public String runSaga() {
        return sagaService.executeSaga();
    }
}