### 24. MSC

## 2. What are the main features of an eCommerce application?

An eCommerce application needs core features like product catalog for browsing items, shopping cart for collecting purchases, user management for accounts and authentication, order processing for checkout and payment, and inventory management for stock tracking.

Additional features include:
- Search and filtering capabilities
- Reviews and ratings system
- Admin panel for management
- Payment gateway integration
- Order tracking and notifications

## 3. Explain the flowchart of an eCommerce application (frontend and backend).

The flow starts with users browsing products on the frontend, adding items to cart, then proceeding to checkout. The frontend sends requests to an API gateway which routes them to appropriate backend services. These services handle business logic, interact with databases, process payments, and send responses back to the frontend.

```
User → Frontend → API Gateway → Backend Services → Database
                                ├── Product Service
                                ├── Cart Service  
                                ├── Order Service
                                └── Payment Service
```

## 4. What are the components and tools used in the backend of an eCommerce application?

Backend components include Spring Boot for REST APIs, PostgreSQL or MySQL for transactional data, Redis for caching, message queues like RabbitMQ for async processing, and Elasticsearch for product search.

Infrastructure tools:
- Docker for containerization
- Kubernetes for orchestration
- AWS S3 for file storage
- Monitoring tools like Prometheus

```java
@RestController
public class ProductController {
    @GetMapping("/api/products")
    public Page<Product> getProducts(@RequestParam String category) {
        return productService.getProducts(category);
    }
}
```

## 5. Explain the Git workflow used in an eCommerce application.

We use GitFlow with main branch for production, develop branch for integration, and feature branches for new functionality. Developers create feature branches from develop, work on features, then create pull requests for code review before merging back to develop.

```bash
git checkout develop
git checkout -b feature/shopping-cart
# Make changes
git commit -m "Add shopping cart"
git push origin feature/shopping-cart
# Create pull request
```

Release branches prepare for deployment, and hotfix branches handle urgent production fixes.

## 6. What is Event-Driven Architecture in Java?

Event-driven architecture means components communicate through events instead of direct calls. When something happens like an order creation, an event is published. Other services listen for these events and react accordingly, making the system loosely coupled and scalable.

```java
@Service
public class OrderService {
    public Order createOrder(OrderRequest request) {
        Order order = orderRepository.save(new Order(request));
        eventPublisher.publishEvent(new OrderCreatedEvent(order.getId()));
        return order;
    }
}

@EventListener
public void handleOrderCreated(OrderCreatedEvent event) {
    emailService.sendConfirmation(event.getOrderId());
}
```

## 7. Can you write the business logic for a CRUD service in Java?

A CRUD service handles Create, Read, Update, Delete operations with proper validation and error handling. I'll use JPA repository for database operations and add business logic for validation.

```java
@Service
public class ProductService {
    
    public Product createProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }
    
    public Product getProduct(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }
    
    public Product updateProduct(Long id, Product details) {
        Product product = getProduct(id);
        product.setName(details.getName());
        product.setPrice(details.getPrice());
        validateProduct(product);
        return productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    private void validateProduct(Product product) {
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
```

## 8. How do you migrate a Java application from a lower version to a higher version?

Migration involves analyzing current code for compatibility issues, updating build configuration like Maven or Gradle, replacing deprecated APIs with newer alternatives, updating third-party dependencies, and thorough testing before production deployment.

Key steps:
- Update Java runtime and build tools
- Replace deprecated APIs
- Update dependency versions
- Fix compilation errors
- Test thoroughly in staging environment

```java
// Before (Java 8)
List<String> names = Arrays.asList("John", "Jane");
List<String> result = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// After (Java 17)
var names = List.of("John", "Jane");
var result = names.stream()
    .map(String::toUpperCase)
    .toList();
```