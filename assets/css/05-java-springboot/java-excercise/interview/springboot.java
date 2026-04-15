// 1. Main Application
@SpringBootApplication
class Main {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// 2. User Entity
import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // only one field

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    // getters & setters
    public Long getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}

// Order Entity
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product; // only one field

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // getters & setters
    public Long getId() { return id; }
    public String getProduct() { return product; }

    public void setProduct(String product) { this.product = product; }
    public void setUser(User user) { this.user = user; }
}

// 5. Service Layer
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final OrderRepository orderRepo;

    public UserService(UserRepository userRepo, OrderRepository orderRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public Order addOrder(Long userId, Order order) {
        User user = userRepo.findById(userId).orElseThrow();
        order.setUser(user);
        return orderRepo.save(order);
    }
}

// 6. Controller
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/users/{id}/orders")
    public Order addOrder(@PathVariable Long id, @RequestBody Order order) {
        return service.addOrder(id, order);
    }
}