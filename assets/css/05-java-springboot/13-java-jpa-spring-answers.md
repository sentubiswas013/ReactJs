# Java / Spring Boot / JPA — Interview Answers

---

## 10. What is the N+1 Query Problem and How Do You Fix It?

So the N+1 problem happens when you load a list of entities and then for each entity, JPA fires a separate query to load its related data.

For example, you load 10 orders — that's 1 query. Then for each order, it loads the customer — that's 10 more queries. Total = 11 queries. That's N+1.

**Fix it using JOIN FETCH:**

```java
@Query("SELECT o FROM Order o JOIN FETCH o.customer")
List<Order> findAllWithCustomer();
```

Or use `@EntityGraph`:

```java
@EntityGraph(attributePaths = {"customer"})
List<Order> findAll();
```

This loads everything in a single query instead of N+1 separate ones.

---

## 11. What is `@Transactional` Propagation Levels?

Propagation tells Spring — *what should happen to the transaction when one transactional method calls another?*

| Level | Behavior |
|---|---|
| `REQUIRED` (default) | Join existing transaction, or create new one |
| `REQUIRES_NEW` | Always create a new transaction, suspend the existing one |
| `NESTED` | Run inside a nested transaction (savepoint) |
| `SUPPORTS` | Use existing transaction if present, else run without |
| `NOT_SUPPORTED` | Suspend existing transaction, run without |
| `MANDATORY` | Must have an existing transaction, else throw exception |
| `NEVER` | Must NOT have a transaction, else throw exception |

```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void saveAuditLog() {
    // always runs in its own transaction
}
```

Most of the time you'll use `REQUIRED` or `REQUIRES_NEW`.

---

## 12. What is Optimistic vs Pessimistic Locking?

Both handle concurrent access to the same data — but differently.

**Optimistic Locking** — assumes conflicts are rare. It uses a `@Version` field. When you update, it checks if the version matches. If someone else already updated it, it throws `OptimisticLockException`.

```java
@Entity
public class Product {
    @Version
    private int version;
}
```

**Pessimistic Locking** — assumes conflicts are likely. It locks the row in the database immediately using `SELECT FOR UPDATE`.

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT p FROM Product p WHERE p.id = :id")
Product findByIdWithLock(@Param("id") Long id);
```

Use optimistic for read-heavy apps, pessimistic for write-heavy or financial systems.

---

## 13. What is JPQL vs Native Query?

**JPQL** (Java Persistence Query Language) works with entity class names and field names — not table names. It's database-independent.

```java
@Query("SELECT e FROM Employee e WHERE e.department = :dept")
List<Employee> findByDept(@Param("dept") String dept);
```

**Native Query** uses actual SQL with real table and column names. Use it when you need database-specific features or complex queries JPQL can't handle.

```java
@Query(value = "SELECT * FROM employee WHERE department = :dept", nativeQuery = true)
List<Employee> findByDeptNative(@Param("dept") String dept);
```

Prefer JPQL for portability. Use native query only when needed.

---

## 14. What are JPA Cascade Types?

Cascade means — *when you do an operation on a parent entity, automatically apply it to child entities too.*

| Type | Behavior |
|---|---|
| `PERSIST` | Save child when parent is saved |
| `MERGE` | Update child when parent is updated |
| `REMOVE` | Delete child when parent is deleted |
| `REFRESH` | Refresh child when parent is refreshed |
| `DETACH` | Detach child when parent is detached |
| `ALL` | Apply all of the above |

```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderItem> items;
```

Be careful with `REMOVE` — it can delete child records you didn't intend to delete.

---

## 15. What is Database Indexing and When to Use It?

An index is like a book's table of contents — it helps the database find rows faster without scanning the whole table.

**When to use:**
- Columns used in `WHERE`, `JOIN`, `ORDER BY`, or `GROUP BY`
- Foreign key columns
- Columns with high cardinality (many unique values)

**When NOT to use:**
- Small tables
- Columns that are updated very frequently
- Columns with very low cardinality (like boolean flags)

```sql
CREATE INDEX idx_employee_email ON employee(email);
```

In JPA:
```java
@Table(indexes = @Index(name = "idx_email", columnList = "email"))
public class Employee { }
```

Indexes speed up reads but slow down writes — so use them wisely.

---

## 16. What is `FetchType.LAZY` vs `FetchType.EAGER` In Depth?

**EAGER** — loads related data immediately when the parent is loaded, even if you don't need it.

**LAZY** — loads related data only when you actually access it (on-demand).

```java
@OneToMany(fetch = FetchType.LAZY)   // default for collections
private List<Order> orders;

@ManyToOne(fetch = FetchType.EAGER)  // default for single associations
private Department department;
```

**The problem with LAZY** — if you access lazy data outside a transaction (after the session is closed), you get `LazyInitializationException`.

**Fix:** Use `JOIN FETCH` or `@Transactional` to keep the session open, or use DTOs.

**Best practice:** Always prefer LAZY. Load eagerly only when you always need the related data together.

---

## 13 (Spring). How Does Spring Handle Circular Dependency?

A circular dependency is when Bean A depends on Bean B, and Bean B depends on Bean A.

**With constructor injection** — Spring throws `BeanCurrentlyInCreationException` because it can't create either bean first.

**With field/setter injection** — Spring can handle it using a 3-level cache (early bean references). It creates a partially initialized bean and injects it.

```java
// Avoid this with constructor injection — causes error
@Component
public class A {
    @Autowired B b;
}

@Component
public class B {
    @Autowired A a;
}
```

**Best fix:** Refactor to remove the circular dependency. If you must keep it, use `@Lazy` on one of the injections:

```java
@Autowired
@Lazy
private B b;
```

`@Lazy` tells Spring to inject a proxy first and resolve the real bean later.

In Spring Boot 2.6+, circular dependencies are disabled by default. You need to explicitly enable them or fix the design.

---
