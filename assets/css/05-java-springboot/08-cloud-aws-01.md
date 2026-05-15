# Spring Data JPA - Interview Questions & Answers

---

## 51. What is Spring Data JPA and Difference between JPA and Hibernate?

- **JPA** – Specification (interface/contract) for ORM in Java (javax.persistence)
- **Hibernate** – Most popular JPA implementation
- **Spring Data JPA** – Abstraction on top of JPA that reduces boilerplate (auto-generates queries, provides repositories)

```java
// JPA Entity
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}

// Spring Data JPA Repository - no implementation needed
public interface UserRepository extends JpaRepository<User, Long> {}
```

---

## 53. CrudRepository vs JpaRepository?

| Feature | CrudRepository | JpaRepository |
|---|---|---|
| Basic CRUD | ✅ | ✅ |
| Pagination & Sorting | ❌ | ✅ |
| Batch operations | ❌ | ✅ |
| flush() / deleteInBatch() | ❌ | ✅ |

```java
// CrudRepository - basic only
public interface UserRepo extends CrudRepository<User, Long> {}

// JpaRepository - preferred, includes pagination
public interface UserRepo extends JpaRepository<User, Long> {
    Page<User> findByName(String name, Pageable pageable);
}
```

---

## 54. How transactions work internally?

Spring uses AOP proxy. When `@Transactional` method is called, Spring intercepts it, begins a transaction, executes the method, then commits or rolls back.

```java
@Service
public class UserService {
    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
        // if exception thrown here → auto rollback
    }
}
```

> ⚠️ Self-invocation (calling `@Transactional` method from same class) bypasses the proxy — transaction won't work.

---

## 56. Transaction propagation types?

| Type | Behavior |
|---|---|
| REQUIRED (default) | Join existing or create new |
| REQUIRES_NEW | Always create new, suspend existing |
| NESTED | Nested within existing |
| SUPPORTS | Use existing if present, else non-transactional |
| NOT_SUPPORTED | Suspend existing, run non-transactional |
| MANDATORY | Must have existing, else exception |
| NEVER | Must NOT have existing, else exception |

```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void auditLog(String msg) {
    // runs in its own transaction, even if caller rolls back
    auditRepo.save(new AuditLog(msg));
}
```

---

## 57. Transaction isolation levels?

| Level | Dirty Read | Non-Repeatable Read | Phantom Read |
|---|---|---|---|
| READ_UNCOMMITTED | ✅ possible | ✅ possible | ✅ possible |
| READ_COMMITTED | ❌ | ✅ possible | ✅ possible |
| REPEATABLE_READ | ❌ | ❌ | ✅ possible |
| SERIALIZABLE | ❌ | ❌ | ❌ |

```java
@Transactional(isolation = Isolation.READ_COMMITTED)
public User getUser(Long id) {
    return userRepository.findById(id).orElseThrow();
}
```

---

## 58. Lazy vs Eager loading?

- **EAGER** – Related entity loaded immediately with parent
- **LAZY** – Related entity loaded only when accessed (default for `@OneToMany`, `@ManyToMany`)

```java
@Entity
public class Order {
    @Id private Long id;

    @ManyToOne(fetch = FetchType.EAGER)   // loaded immediately
    private User user;

    @OneToMany(fetch = FetchType.LAZY)    // loaded on access
    private List<OrderItem> items;
}
```

---

## 59. What is N+1 problem?

When fetching N parent records triggers N additional queries to fetch each child — total N+1 queries.

```java
// PROBLEM: 1 query for all orders + N queries for each user
List<Order> orders = orderRepository.findAll();
orders.forEach(o -> System.out.println(o.getUser().getName())); // N extra queries
```

---

## 60. How to solve N+1 problem?

Use `JOIN FETCH` in JPQL or `@EntityGraph`.

```java
// Solution 1: JOIN FETCH
@Query("SELECT o FROM Order o JOIN FETCH o.user")
List<Order> findAllWithUser();

// Solution 2: EntityGraph
@EntityGraph(attributePaths = {"user"})
List<Order> findAll();
```

---

## 61. JPQL vs Native Query?

| | JPQL | Native Query |
|---|---|---|
| Syntax | Entity/field names | Table/column names |
| DB portable | ✅ | ❌ |
| Complex SQL | Limited | Full SQL support |

```java
// JPQL - uses entity class name
@Query("SELECT u FROM User u WHERE u.name = :name")
List<User> findByName(@Param("name") String name);

// Native Query - uses table name
@Query(value = "SELECT * FROM users WHERE name = :name", nativeQuery = true)
List<User> findByNameNative(@Param("name") String name);
```

---

## 62. How @Query works?

`@Query` lets you define custom JPQL or SQL on repository methods. Spring Data parses and executes it at runtime.

```java
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.active = true")
    Optional<User> findActiveByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.active = false WHERE u.id = :id")
    void deactivate(@Param("id") Long id);
}
```

---

## 63. Pagination with JPA?

```java
// Repository
public interface UserRepository extends JpaRepository<User, Long> {}

// Service
public Page<User> getUsers(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
    return userRepository.findAll(pageable);
}

// Response contains: content, totalPages, totalElements, number
```

---

## 64. Optimistic vs Pessimistic locking?

| | Optimistic | Pessimistic |
|---|---|---|
| Mechanism | Version check at commit | DB-level lock (SELECT FOR UPDATE) |
| Use case | Low contention | High contention |
| Performance | Better | Slower |

```java
// Optimistic - uses @Version
@Entity
public class Product {
    @Id private Long id;
    @Version private int version; // auto-checked on update
    private int stock;
}

// Pessimistic - locks row in DB
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT p FROM Product p WHERE p.id = :id")
Product findByIdForUpdate(@Param("id") Long id);
```

---

## 65. What is @Version?

`@Version` enables optimistic locking. Hibernate auto-increments the version on each update and throws `OptimisticLockException` if two transactions update the same record.

```java
@Entity
public class Account {
    @Id private Long id;
    @Version private Long version;  // Hibernate manages this
    private double balance;
}

// If two threads update same Account simultaneously,
// second one gets: javax.persistence.OptimisticLockException
```

---

## 66. Entity lifecycle states?

| State | Description |
|---|---|
| **Transient** | New object, not tracked by JPA |
| **Managed** | Tracked by persistence context |
| **Detached** | Was managed, now outside context |
| **Removed** | Scheduled for deletion |

```java
User user = new User("John");          // Transient

entityManager.persist(user);           // Managed - changes auto-synced

entityManager.detach(user);            // Detached - changes NOT tracked
user.setName("Jane");                  // won't be saved

entityManager.merge(user);             // Managed again

entityManager.remove(user);            // Removed
```

---

## 67. What is dirty checking?

Hibernate automatically detects changes to managed entities and syncs them to DB at flush time — no explicit `save()` needed.

```java
@Transactional
public void updateName(Long id, String newName) {
    User user = userRepository.findById(id).get(); // now Managed
    user.setName(newName); // dirty checking detects this change
    // NO save() needed — Hibernate auto-flushes on commit
}
```

---

## 68. Difference between save() and saveAndFlush()?

| | save() | saveAndFlush() |
|---|---|---|
| Flush to DB | At end of transaction | Immediately |
| Use case | Normal saves | When you need DB state right away |

```java
// save() - flushed at transaction commit
User u1 = userRepository.save(new User("Alice"));

// saveAndFlush() - immediately sent to DB (useful before native queries)
User u2 = userRepository.saveAndFlush(new User("Bob"));
```

---

## 69. How auditing works in JPA?

Spring Data JPA provides `@CreatedDate`, `@LastModifiedDate`, `@CreatedBy`, `@LastModifiedBy` via `@EnableJpaAuditing`.

```java
// 1. Enable auditing
@SpringBootApplication
@EnableJpaAuditing
public class App {}

// 2. Auditable base entity
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}

// 3. Extend it
@Entity
public class User extends Auditable {
    @Id @GeneratedValue private Long id;
    private String name;
}
```

---

## 70. How to handle large data efficiently?

- Use **pagination** instead of `findAll()`
- Use **projections** to fetch only needed columns
- Use **streaming** with `Stream<T>` or `Slice<T>`
- Use **batch inserts** with `spring.jpa.properties.hibernate.jdbc.batch_size`

```java
// Projection - fetch only needed fields
public interface UserSummary {
    String getName();
    String getEmail();
}
public interface UserRepository extends JpaRepository<User, Long> {
    List<UserSummary> findAllProjectedBy();
}

// Streaming large result sets
@Query("SELECT u FROM User u")
@QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "500"))
Stream<User> streamAll();

// application.properties - batch inserts
// spring.jpa.properties.hibernate.jdbc.batch_size=50
// spring.jpa.properties.hibernate.order_inserts=true
```
