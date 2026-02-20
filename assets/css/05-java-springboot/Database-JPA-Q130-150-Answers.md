# ✅ 11) Database Design & Optimization (SQL + JPA/Hibernate)

## 130. What is normalization (1NF, 2NF, 3NF)?

**Answer:** Normalization eliminates data redundancy. 1NF: atomic values, no repeating groups. 2NF: 1NF + no partial dependencies. 3NF: 2NF + no transitive dependencies.

**Example:**
```sql
-- Unnormalized
CREATE TABLE orders (
    id INT,
    customer_name VARCHAR(100),
    products VARCHAR(500)  -- "Laptop,Mouse,Keyboard"
);

-- 1NF: Atomic values
CREATE TABLE orders (
    id INT,
    customer_name VARCHAR(100),
    product VARCHAR(100)
);

-- 3NF: Separate entities
CREATE TABLE customers (id INT, name VARCHAR(100));
CREATE TABLE orders (id INT, customer_id INT);
CREATE TABLE order_items (order_id INT, product_id INT);
```

---

## 131. When should you denormalize?

**Answer:** Denormalize for read-heavy systems, reporting, reducing joins, or when query performance is critical. Trade consistency for speed.

**Example:**
```java
// Normalized - Multiple joins
@Entity
public class Order {
    @ManyToOne
    private Customer customer;
}

// Denormalized - Duplicate customer name for faster reads
@Entity
public class Order {
    @ManyToOne
    private Customer customer;
    private String customerName;  // Denormalized
    private String customerEmail; // Denormalized
}
```

---

## 132. Explain ACID properties.

**Answer:** ACID ensures reliable transactions. Atomicity: all or nothing. Consistency: valid state. Isolation: concurrent transactions don't interfere. Durability: committed data persists.

**Example:**
```java
@Service
public class BankService {
    @Transactional  // ACID guaranteed
    public void transfer(Account from, Account to, BigDecimal amount) {
        from.debit(amount);      // Atomicity: both succeed or both fail
        to.credit(amount);       // Consistency: balance rules maintained
        // Isolation: other transactions see consistent state
        // Durability: committed changes survive crashes
    }
}
```

---

## 133. What are database indexes and how do they work?

**Answer:** Indexes are data structures (B-Tree, Hash) that speed up data retrieval by creating pointers to table rows, trading write speed for read speed.

**Example:**
```sql
-- Without index: Full table scan O(n)
SELECT * FROM users WHERE email = 'john@example.com';

-- Create index
CREATE INDEX idx_email ON users(email);

-- With index: O(log n) lookup
SELECT * FROM users WHERE email = 'john@example.com';
```

```java
@Entity
@Table(indexes = @Index(name = "idx_email", columnList = "email"))
public class User {
    @Column(unique = true)
    private String email;
}
```

---

## 134. Clustered vs non-clustered index.

**Answer:** Clustered index determines physical row order (one per table, usually primary key). Non-clustered index creates separate structure with pointers (multiple allowed).

**Example:**
```sql
-- Clustered: Data sorted by primary key
CREATE TABLE users (
    id INT PRIMARY KEY,  -- Clustered index
    name VARCHAR(100)
);

-- Non-clustered: Separate index structure
CREATE INDEX idx_name ON users(name);  -- Non-clustered

-- Query uses non-clustered, then looks up clustered
SELECT * FROM users WHERE name = 'John';
```

---

## 135. How do you optimize slow SQL queries?

**Answer:** Add indexes, avoid SELECT *, use EXPLAIN, limit results, optimize joins, use covering indexes, partition tables, and cache results.

**Example:**
```sql
-- Slow: No index, SELECT *
SELECT * FROM orders WHERE customer_id = 123;

-- Optimized
CREATE INDEX idx_customer ON orders(customer_id);
SELECT id, total, created_at FROM orders WHERE customer_id = 123 LIMIT 100;

-- Use EXPLAIN to analyze
EXPLAIN SELECT id FROM orders WHERE customer_id = 123;
```

---

## 136. What is the N+1 query problem? How to fix it?

**Answer:** N+1 occurs when fetching a list (1 query) then loading related entities individually (N queries). Fix with JOIN FETCH or entity graphs.

**Example:**
```java
// N+1 Problem: 1 + N queries
List<Order> orders = orderRepo.findAll();  // 1 query
for (Order order : orders) {
    order.getCustomer().getName();  // N queries
}

// Solution 1: JOIN FETCH
@Query("SELECT o FROM Order o JOIN FETCH o.customer")
List<Order> findAllWithCustomer();

// Solution 2: Entity Graph
@EntityGraph(attributePaths = {"customer"})
List<Order> findAll();
```

---

## 137. Explain transaction isolation levels.

**Answer:** Isolation levels control concurrent transaction visibility. READ_UNCOMMITTED (dirty reads), READ_COMMITTED (default), REPEATABLE_READ (consistent reads), SERIALIZABLE (full isolation).

**Example:**
```java
@Transactional(isolation = Isolation.READ_COMMITTED)
public void processOrder(Long orderId) {
    // Sees only committed data
}

@Transactional(isolation = Isolation.REPEATABLE_READ)
public void generateReport() {
    // Same query returns same results within transaction
}

@Transactional(isolation = Isolation.SERIALIZABLE)
public void criticalOperation() {
    // Full isolation, prevents all anomalies
}
```

---

## 138. What is dirty read, non-repeatable read, phantom read?

**Answer:** Dirty read: reading uncommitted data. Non-repeatable read: same query returns different results. Phantom read: new rows appear in range queries.

**Example:**
```java
// Dirty Read (READ_UNCOMMITTED)
// T1: UPDATE balance = 100 (not committed)
// T2: SELECT balance -> sees 100 (dirty)

// Non-repeatable Read (READ_COMMITTED)
// T1: SELECT balance -> 100
// T2: UPDATE balance = 200, COMMIT
// T1: SELECT balance -> 200 (different)

// Phantom Read (REPEATABLE_READ)
// T1: SELECT COUNT(*) WHERE age > 20 -> 5
// T2: INSERT user age=25, COMMIT
// T1: SELECT COUNT(*) WHERE age > 20 -> 6 (phantom)
```

---

## 139. Optimistic locking vs pessimistic locking.

**Answer:** Optimistic: assumes no conflicts, checks version on update. Pessimistic: locks row immediately. Use optimistic for low contention, pessimistic for high contention.

**Example:**
```java
// Optimistic Locking
@Entity
public class Product {
    @Id
    private Long id;
    @Version
    private Long version;  // Auto-incremented on update
    private Integer stock;
}

// Pessimistic Locking
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT p FROM Product p WHERE p.id = :id")
Product findByIdWithLock(@Param("id") Long id);
```

---

## 140. How do you implement optimistic locking in JPA (`@Version`)?

**Answer:** Add @Version field. JPA automatically checks version on update and throws OptimisticLockException if changed by another transaction.

**Example:**
```java
@Entity
public class Account {
    @Id
    private Long id;
    @Version
    private Long version;
    private BigDecimal balance;
}

@Service
public class AccountService {
    @Transactional
    public void withdraw(Long id, BigDecimal amount) {
        Account account = accountRepo.findById(id).get();
        account.setBalance(account.getBalance().subtract(amount));
        accountRepo.save(account);  // Throws OptimisticLockException if version changed
    }
}
```

---

## 141. Explain JPA entity lifecycle states.

**Answer:** Transient: new object, not tracked. Managed: tracked by EntityManager. Detached: was managed, now disconnected. Removed: marked for deletion.

**Example:**
```java
// Transient
User user = new User("John");

// Managed
entityManager.persist(user);
user.setName("Jane");  // Auto-synced to DB

// Detached
entityManager.detach(user);
user.setName("Bob");  // Not synced

// Removed
entityManager.remove(user);  // Deleted on commit
```

---

## 142. Difference between `persist()`, `merge()`, `save()`.

**Answer:** persist(): makes transient entity managed. merge(): copies detached entity to managed. save(): Hibernate method, returns ID immediately.

**Example:**
```java
// persist() - Transient to Managed
User user = new User("John");
entityManager.persist(user);  // Now managed

// merge() - Detached to Managed
User detached = new User("Jane");
detached.setId(1L);
User managed = entityManager.merge(detached);  // Returns managed copy

// save() - Hibernate specific
User user = new User("Bob");
Long id = session.save(user);  // Returns generated ID
```

---

## 143. Explain JPA relationships (`@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`).

**Answer:** OneToOne: single entity on both sides. OneToMany/ManyToOne: parent-child. ManyToMany: multiple on both sides via join table.

**Example:**
```java
// OneToOne
@Entity
public class User {
    @OneToOne
    private Profile profile;
}

// OneToMany / ManyToOne
@Entity
public class Order {
    @ManyToOne
    private Customer customer;
}

@Entity
public class Customer {
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}

// ManyToMany
@Entity
public class Student {
    @ManyToMany
    @JoinTable(name = "student_course")
    private List<Course> courses;
}
```

---

## 144. What is owning side vs inverse side?

**Answer:** Owning side has the foreign key and controls the relationship. Inverse side uses mappedBy. Changes on owning side are persisted.

**Example:**
```java
// Owning side: Has @JoinColumn
@Entity
public class Order {
    @ManyToOne
    @JoinColumn(name = "customer_id")  // Owning side
    private Customer customer;
}

// Inverse side: Has mappedBy
@Entity
public class Customer {
    @OneToMany(mappedBy = "customer")  // Inverse side
    private List<Order> orders;
}

// Only changes on owning side persist
order.setCustomer(customer);  // Persisted
customer.getOrders().add(order);  // Not persisted unless owning side updated
```

---

## 145. Lazy loading vs eager loading - when to use which?

**Answer:** Lazy: loads related entities on access (default for collections). Eager: loads immediately (default for single entities). Use lazy to avoid unnecessary queries.

**Example:**
```java
// Lazy Loading (default for @OneToMany, @ManyToMany)
@Entity
public class Customer {
    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders;  // Loaded when accessed
}

// Eager Loading (default for @ManyToOne, @OneToOne)
@Entity
public class Order {
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;  // Loaded immediately
}

// Use lazy for large collections
// Use eager for frequently accessed single entities
```

---

## 146. How do you handle LazyInitializationException?

**Answer:** Occurs when accessing lazy entity outside transaction. Fix: use JOIN FETCH, @Transactional, entity graphs, or DTO projections.

**Example:**
```java
// Problem: LazyInitializationException
@Service
public class OrderService {
    public Order getOrder(Long id) {
        return orderRepo.findById(id).get();
    }  // Transaction ends
}
// controller: order.getCustomer().getName() -> Exception!

// Solution 1: JOIN FETCH
@Query("SELECT o FROM Order o JOIN FETCH o.customer WHERE o.id = :id")
Order findByIdWithCustomer(@Param("id") Long id);

// Solution 2: @Transactional on controller method
@Transactional(readOnly = true)
public OrderDTO getOrder(Long id) {
    Order order = orderRepo.findById(id).get();
    return new OrderDTO(order, order.getCustomer());
}
```

---

## 147. Explain `@Transactional` and propagation levels.

**Answer:** @Transactional manages transactions. Propagation: REQUIRED (default, join existing), REQUIRES_NEW (new transaction), NESTED (savepoint), SUPPORTS, MANDATORY, NEVER, NOT_SUPPORTED.

**Example:**
```java
@Service
public class OrderService {
    @Transactional(propagation = Propagation.REQUIRED)
    public void createOrder(Order order) {
        orderRepo.save(order);
        paymentService.processPayment(order);  // Joins this transaction
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAudit(String action) {
        auditRepo.save(new Audit(action));  // New transaction, commits independently
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void updateInventory(Long productId) {
        // Creates savepoint, can rollback without affecting parent
    }
}
```

---

## 148. JPQL vs Criteria API vs native SQL.

**Answer:** JPQL: object-oriented query language. Criteria API: type-safe programmatic queries. Native SQL: database-specific SQL. Use JPQL for simple, Criteria for dynamic, native for complex.

**Example:**
```java
// JPQL
@Query("SELECT o FROM Order o WHERE o.customer.name = :name")
List<Order> findByCustomerName(@Param("name") String name);

// Criteria API
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<Order> query = cb.createQuery(Order.class);
Root<Order> order = query.from(Order.class);
query.select(order).where(cb.equal(order.get("status"), "PENDING"));
List<Order> orders = em.createQuery(query).getResultList();

// Native SQL
@Query(value = "SELECT * FROM orders WHERE status = ?1", nativeQuery = true)
List<Order> findByStatusNative(String status);
```

---

## 149. How do you implement pagination in JPA?

**Answer:** Use Pageable parameter with Spring Data JPA or setFirstResult/setMaxResults with EntityManager.

**Example:**
```java
// Spring Data JPA
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByStatus(String status, Pageable pageable);
}

@Service
public class OrderService {
    public Page<Order> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return orderRepo.findByStatus("PENDING", pageable);
    }
}

// EntityManager
TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
query.setFirstResult(page * size);
query.setMaxResults(size);
List<Order> orders = query.getResultList();
```

---

## 150. How do you implement batch processing in JPA?

**Answer:** Configure batch size, flush and clear EntityManager periodically to avoid memory issues when processing large datasets.

**Example:**
```java
// application.properties
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true

@Service
public class BatchService {
    @Transactional
    public void importOrders(List<Order> orders) {
        int batchSize = 50;
        for (int i = 0; i < orders.size(); i++) {
            entityManager.persist(orders.get(i));
            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();  // Prevent memory issues
            }
        }
    }
}
```