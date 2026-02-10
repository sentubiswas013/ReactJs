# Java – Object-Oriented Programming (OOP) & Design

## 1. How would you design a library management system using Java? Which classes and relationships would you use?

I'd create core classes for **Book, Member, and Library** with relationships showing ownership and borrowing. The Library manages books and members, while a Transaction class tracks who borrowed what.

```java
class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}

class Member {
    private String memberId;
    private String name;
    private List<Book> borrowedBooks = new ArrayList<>();

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public boolean borrowBook(String isbn, String memberId) {
        Book book = findBook(isbn);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            Member member = findMember(memberId);
            member.borrowBook(book);
            return true;
        }
        return false;
    }

    public void returnBook(String isbn, String memberId) {
        Book book = findBook(isbn);
        book.setAvailable(true);
        Member member = findMember(memberId);
        member.returnBook(book);
    }

    private Book findBook(String isbn) {
        return books.stream()
            .filter(b -> b.getIsbn().equals(isbn))
            .findFirst()
            .orElse(null);
    }

    private Member findMember(String memberId) {
        return members.stream()
            .filter(m -> m.getMemberId().equals(memberId))
            .findFirst()
            .orElse(null);
    }
}
```

**Key relationships:**
- Library **has-many** Books and Members (composition)
- Member **borrows-many** Books (association)
- Transaction tracks borrowing history

---

## 2. Explain how you would implement a parking lot system with multiple types of vehicles.

I'd use **inheritance** for different vehicle types and **composition** for parking spots. The ParkingLot manages spots and assigns them based on vehicle size.

```java
abstract class Vehicle {
    protected String licensePlate;
    protected VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public VehicleType getType() { return type; }
}

enum VehicleType {
    MOTORCYCLE, CAR, TRUCK
}

class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.MOTORCYCLE);
    }
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }
}

class ParkingSpot {
    private int spotNumber;
    private VehicleType type;
    private Vehicle vehicle;
    private boolean isOccupied;

    public boolean canFit(Vehicle vehicle) {
        return !isOccupied && this.type == vehicle.getType();
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }
}

class ParkingLot {
    private List<ParkingSpot> spots = new ArrayList<>();

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.canFit(vehicle)) {
                spot.park(vehicle);
                return true;
            }
        }
        return false;
    }

    public boolean removeVehicle(String licensePlate) {
        for (ParkingSpot spot : spots) {
            if (spot.getVehicle() != null && 
                spot.getVehicle().getLicensePlate().equals(licensePlate)) {
                spot.removeVehicle();
                return true;
            }
        }
        return false;
    }
}
```

**Key design:**
- Vehicle hierarchy for different types
- ParkingSpot knows which vehicle type it accepts
- ParkingLot manages spot allocation

---

## 3. How would you implement a restaurant ordering system with menus, orders, and customers?

I'd create **MenuItem, Order, and Customer** classes with Order acting as an aggregate of MenuItems. The Restaurant class coordinates everything.

```java
class MenuItem {
    private String id;
    private String name;
    private double price;

    public MenuItem(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }
}

class Order {
    private String orderId;
    private Customer customer;
    private List<MenuItem> items = new ArrayList<>();
    private OrderStatus status;

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public double calculateTotal() {
        return items.stream()
            .mapToDouble(MenuItem::getPrice)
            .sum();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

enum OrderStatus {
    PENDING, PREPARING, READY, DELIVERED
}

class Customer {
    private String customerId;
    private String name;
    private List<Order> orderHistory = new ArrayList<>();

    public void placeOrder(Order order) {
        orderHistory.add(order);
    }
}

class Restaurant {
    private List<MenuItem> menu = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public Order createOrder(Customer customer) {
        Order order = new Order(generateOrderId(), customer);
        orders.add(order);
        return order;
    }

    public void addItemToOrder(Order order, String menuItemId) {
        MenuItem item = menu.stream()
            .filter(m -> m.getId().equals(menuItemId))
            .findFirst()
            .orElse(null);
        if (item != null) {
            order.addItem(item);
        }
    }

    private String generateOrderId() {
        return "ORD" + System.currentTimeMillis();
    }
}
```

**Key relationships:**
- Order **has-many** MenuItems (composition)
- Customer **has-many** Orders (association)
- Restaurant manages menu and orders

---

## 4. How do you prevent a class from being subclassed? Give a real-world scenario where this is needed.

I use the **final** keyword to prevent inheritance. This is useful for immutable classes like String or when you want to ensure a class's behavior can't be altered through inheritance.

```java
// Prevent subclassing
final class SecurityToken {
    private final String token;
    private final long expiryTime;

    public SecurityToken(String token, long expiryTime) {
        this.token = token;
        this.expiryTime = expiryTime;
    }

    public boolean isValid() {
        return System.currentTimeMillis() < expiryTime;
    }
}

// This won't compile
// class ExtendedToken extends SecurityToken { }

// Real-world example: Immutable configuration
final class DatabaseConfig {
    private final String url;
    private final String username;
    private final String password;

    public DatabaseConfig(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() { return url; }
}
```

**Real-world scenarios:**
- **Security classes**: Prevent tampering with authentication logic
- **Immutable classes**: String, Integer, LocalDate
- **Utility classes**: Math, Collections (with private constructor)
- **Configuration classes**: Ensure settings can't be modified through inheritance

**Alternative - private constructor:**
```java
class Utility {
    private Utility() {} // Prevent instantiation and inheritance

    public static int add(int a, int b) {
        return a + b;
    }
}
```

---

## 5. Design a bank account system with checking and savings accounts using inheritance and polymorphism.

I'd create an **abstract Account** base class with common behavior, then extend it for CheckingAccount and SavingsAccount with their specific rules. Polymorphism allows treating all accounts uniformly.

```java
abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String owner;

    public Account(String accountNumber, String owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public abstract boolean withdraw(double amount);
    public abstract double calculateInterest();

    public double getBalance() { return balance; }
}

class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String owner, double overdraftLimit) {
        super(accountNumber, owner);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public double calculateInterest() {
        return 0; // No interest on checking
    }
}

class SavingsAccount extends Account {
    private double interestRate;
    private int withdrawalLimit = 6;
    private int withdrawalCount = 0;

    public SavingsAccount(String accountNumber, String owner, double interestRate) {
        super(accountNumber, owner);
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        if (withdrawalCount >= withdrawalLimit) {
            return false;
        }
        if (balance >= amount) {
            balance -= amount;
            withdrawalCount++;
            return true;
        }
        return false;
    }

    @Override
    public double calculateInterest() {
        return balance * interestRate;
    }

    public void resetWithdrawalCount() {
        withdrawalCount = 0;
    }
}

// Polymorphism in action
class Bank {
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void applyInterestToAll() {
        for (Account account : accounts) {
            double interest = account.calculateInterest();
            account.deposit(interest);
        }
    }

    public double getTotalBalance() {
        return accounts.stream()
            .mapToDouble(Account::getBalance)
            .sum();
    }
}
```

**Key OOP principles:**
- **Inheritance**: CheckingAccount and SavingsAccount extend Account
- **Polymorphism**: Bank treats all accounts uniformly through Account interface
- **Abstraction**: Abstract methods force subclasses to implement specific behavior
- **Encapsulation**: Balance and account details are protected

**Usage:**
```java
Bank bank = new Bank();
bank.addAccount(new CheckingAccount("CHK001", "John", 500));
bank.addAccount(new SavingsAccount("SAV001", "Jane", 0.03));
bank.applyInterestToAll(); // Polymorphic call
```


---

# Collections & Data Structures

## 6. How would you store a list of students and retrieve them efficiently by ID? Which collection would you use?

I'd use a **HashMap** with student ID as the key for O(1) retrieval time. This gives instant access to any student by their ID.

```java
class Student {
    private String id;
    private String name;
    private int age;

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() { return id; }
}

class StudentRepository {
    private Map<String, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student getStudentById(String id) {
        return students.get(id); // O(1) lookup
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public boolean removeStudent(String id) {
        return students.remove(id) != null;
    }
}

// Usage
StudentRepository repo = new StudentRepository();
repo.addStudent(new Student("S001", "John", 20));
Student student = repo.getStudentById("S001"); // Fast retrieval
```

**Key points:**
- HashMap provides O(1) average time for get/put operations
- Use student ID as key for direct access
- Alternative: ConcurrentHashMap for thread-safe operations

---

## 7. You need to remove duplicate elements from a list of objects. How would you do it?

I'd use a **HashSet** which automatically removes duplicates, or use Java Streams with distinct(). For custom objects, I need to override equals() and hashCode().

```java
class Student {
    private String id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

// Method 1: Using HashSet
List<Student> students = Arrays.asList(/* students with duplicates */);
List<Student> unique = new ArrayList<>(new HashSet<>(students));

// Method 2: Using Streams
List<Student> unique = students.stream()
    .distinct()
    .collect(Collectors.toList());

// Method 3: Using LinkedHashSet (preserves order)
List<Student> unique = new ArrayList<>(new LinkedHashSet<>(students));

// For simple types
List<String> names = Arrays.asList("John", "Jane", "John", "Bob");
List<String> uniqueNames = names.stream()
    .distinct()
    .collect(Collectors.toList());
```

**Key points:**
- HashSet automatically removes duplicates
- Must override equals() and hashCode() for custom objects
- LinkedHashSet preserves insertion order
- Stream distinct() uses equals() for comparison

---

## 8. How would you implement a LRU (Least Recently Used) cache in Java?

I'd use **LinkedHashMap** with accessOrder=true, which maintains insertion/access order. When capacity is reached, I remove the eldest entry.

```java
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // true = access order
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

// Usage
LRUCache<String, String> cache = new LRUCache<>(3);
cache.put("1", "One");
cache.put("2", "Two");
cache.put("3", "Three");
cache.get("1"); // Access "1", moves it to end
cache.put("4", "Four"); // "2" is removed (least recently used)
```

**Manual implementation with LinkedHashMap:**
```java
class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, V> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }
}
```

**Key points:**
- LinkedHashMap with accessOrder=true maintains access order
- removeEldestEntry() automatically removes oldest when capacity exceeded
- O(1) for get and put operations

---

## 9. Explain how you would find the top 5 highest scores from a large dataset of student scores.

I'd use a **PriorityQueue (min-heap)** of size 5 to efficiently track the top scores, or use Java Streams with sorted() and limit().

```java
class Student {
    String name;
    int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

// Method 1: Using PriorityQueue (efficient for large datasets)
public List<Student> getTop5(List<Student> students) {
    PriorityQueue<Student> minHeap = new PriorityQueue<>(
        5, 
        Comparator.comparingInt(s -> s.score)
    );

    for (Student student : students) {
        minHeap.offer(student);
        if (minHeap.size() > 5) {
            minHeap.poll(); // Remove lowest
        }
    }

    return new ArrayList<>(minHeap);
}

// Method 2: Using Streams (simpler but sorts entire list)
public List<Student> getTop5Streams(List<Student> students) {
    return students.stream()
        .sorted(Comparator.comparingInt(s -> -s.score))
        .limit(5)
        .collect(Collectors.toList());
}

// Method 3: For primitive scores
public List<Integer> getTop5Scores(List<Integer> scores) {
    return scores.stream()
        .sorted(Comparator.reverseOrder())
        .limit(5)
        .collect(Collectors.toList());
}
```

**Key points:**
- PriorityQueue approach: O(n log k) where k=5, memory efficient
- Stream approach: O(n log n), simpler but sorts entire list
- Use min-heap to maintain top K elements efficiently

---

## 10. How do you choose between HashMap, TreeMap, and LinkedHashMap in a real-world application?

I choose based on **ordering needs and performance**. HashMap for speed, TreeMap for sorted keys, LinkedHashMap for insertion order.

```java
// HashMap - No order, fastest (O(1))
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("banana", 2);
hashMap.put("apple", 1);
hashMap.put("cherry", 3);
// Order: unpredictable

// TreeMap - Sorted by key (O(log n))
Map<String, Integer> treeMap = new TreeMap<>();
treeMap.put("banana", 2);
treeMap.put("apple", 1);
treeMap.put("cherry", 3);
// Order: apple, banana, cherry (alphabetical)

// LinkedHashMap - Insertion order (O(1))
Map<String, Integer> linkedMap = new LinkedHashMap<>();
linkedMap.put("banana", 2);
linkedMap.put("apple", 1);
linkedMap.put("cherry", 3);
// Order: banana, apple, cherry (insertion order)
```

**Decision guide:**

**Use HashMap when:**
- Order doesn't matter
- Need fastest performance
- Example: User session storage, caching

```java
// Session management
Map<String, Session> sessions = new HashMap<>();
```

**Use TreeMap when:**
- Need sorted keys
- Need range queries (subMap, headMap, tailMap)
- Example: Leaderboard, time-series data

```java
// Leaderboard sorted by score
TreeMap<Integer, String> leaderboard = new TreeMap<>(Comparator.reverseOrder());
leaderboard.put(100, "Player1");
leaderboard.put(95, "Player2");
// Automatically sorted by score
```

**Use LinkedHashMap when:**
- Need insertion order preserved
- Implementing LRU cache
- Example: Recent searches, access history

```java
// Recent searches (maintains order)
Map<String, Long> recentSearches = new LinkedHashMap<>();
```

**Performance comparison:**
| Operation | HashMap | LinkedHashMap | TreeMap |
|-----------|---------|---------------|---------|
| get/put   | O(1)    | O(1)          | O(log n)|
| Order     | None    | Insertion     | Sorted  |
| Memory    | Low     | Medium        | High    |

**Real-world example:**
```java
class ProductCatalog {
    // Fast lookup by product ID
    private Map<String, Product> productsById = new HashMap<>();
    
    // Products sorted by price
    private TreeMap<Double, List<Product>> productsByPrice = new TreeMap<>();
    
    // Recently viewed products (in order)
    private LinkedHashMap<String, Product> recentlyViewed = 
        new LinkedHashMap<>(16, 0.75f, true);
}
```

**Key decision factors:**
- **Performance needs**: HashMap fastest
- **Ordering requirements**: TreeMap for sorted, LinkedHashMap for insertion order
- **Memory constraints**: HashMap uses least memory
- **Thread safety**: Use ConcurrentHashMap for concurrent access


---

# Concurrency & Multithreading

## 11. How would you implement a thread-safe singleton in Java?

I'd use **double-checked locking with volatile** or the **enum approach** which is the simplest and thread-safe by default.

```java
// Method 1: Double-checked locking (most common)
class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

// Method 2: Enum (best practice, simplest)
enum Singleton {
    INSTANCE;

    public void doSomething() {
        // Business logic
    }
}

// Method 3: Bill Pugh Singleton (lazy initialization)
class Singleton {
    private Singleton() {}

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

// Method 4: Eager initialization (simple but not lazy)
class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```

**Key points:**
- volatile prevents instruction reordering
- Double-checked locking reduces synchronization overhead
- Enum is simplest and prevents reflection/serialization attacks
- Bill Pugh uses inner class for lazy initialization without synchronization

---

## 12. Suppose multiple threads are writing to a shared bank account balance. How would you ensure correctness?

I'd use **synchronized methods** or **ReentrantLock** to ensure only one thread modifies the balance at a time. For simple operations, AtomicInteger works too.

```java
class BankAccount {
    private double balance;

    // Method 1: Synchronized methods
    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    public synchronized double getBalance() {
        return balance;
    }
}

// Method 2: ReentrantLock (more control)
class BankAccount {
    private double balance;
    private final Lock lock = new ReentrantLock();

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
            }
        } finally {
            lock.unlock();
        }
    }
}

// Method 3: AtomicInteger (for integer operations)
class BankAccount {
    private AtomicLong balance = new AtomicLong(0);

    public void deposit(long amount) {
        balance.addAndGet(amount);
    }

    public void withdraw(long amount) {
        balance.updateAndGet(current -> 
            current >= amount ? current - amount : current
        );
    }

    public long getBalance() {
        return balance.get();
    }
}
```

**Key points:**
- synchronized ensures mutual exclusion
- ReentrantLock provides try-lock and timed lock features
- AtomicLong for lock-free atomic operations
- Always use finally block with locks

---

## 13. How would you design a producer-consumer problem using Java threads?

I'd use **BlockingQueue** which handles synchronization automatically, or use **wait/notify** for manual control.

```java
// Method 1: Using BlockingQueue (recommended)
class ProducerConsumer {
    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    class Producer implements Runnable {
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    queue.put(i); // Blocks if queue is full
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    class Consumer implements Runnable {
        public void run() {
            try {
                while (true) {
                    Integer item = queue.take(); // Blocks if queue is empty
                    System.out.println("Consumed: " + item);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start() {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}

// Method 2: Using wait/notify (manual synchronization)
class ProducerConsumer {
    private Queue<Integer> queue = new LinkedList<>();
    private final int MAX_SIZE = 10;
    private final Object lock = new Object();

    class Producer implements Runnable {
        public void run() {
            for (int i = 0; i < 20; i++) {
                synchronized (lock) {
                    while (queue.size() == MAX_SIZE) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    queue.add(i);
                    System.out.println("Produced: " + i);
                    lock.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (queue.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    Integer item = queue.poll();
                    System.out.println("Consumed: " + item);
                    lock.notifyAll();
                }
            }
        }
    }
}
```

**Key points:**
- BlockingQueue handles all synchronization automatically
- put() blocks when full, take() blocks when empty
- wait/notify requires careful synchronization
- Always use while loop with wait() to handle spurious wakeups

---

## 14. Explain a scenario where synchronized blocks are preferred over synchronized methods.

I use **synchronized blocks** when I only need to synchronize a small portion of code, reducing lock contention. This gives better performance by minimizing the critical section.

```java
class UserService {
    private Map<String, User> users = new HashMap<>();
    private final Object lock = new Object();

    // BAD: Entire method synchronized (holds lock too long)
    public synchronized void updateUser(String id, User user) {
        // Expensive operation (doesn't need lock)
        String processedData = expensiveValidation(user);
        
        // Only this needs synchronization
        users.put(id, user);
        
        // Another expensive operation (doesn't need lock)
        sendNotification(user);
    }

    // GOOD: Only critical section synchronized
    public void updateUserOptimized(String id, User user) {
        // Expensive operation outside lock
        String processedData = expensiveValidation(user);
        
        // Only synchronize the critical section
        synchronized (lock) {
            users.put(id, user);
        }
        
        // Expensive operation outside lock
        sendNotification(user);
    }

    private String expensiveValidation(User user) {
        // Time-consuming validation
        return "validated";
    }

    private void sendNotification(User user) {
        // Time-consuming I/O operation
    }
}

// Real-world example: Multiple locks for different resources
class BankAccount {
    private double balance;
    private List<String> transactionHistory = new ArrayList<>();
    
    private final Object balanceLock = new Object();
    private final Object historyLock = new Object();

    public void deposit(double amount) {
        synchronized (balanceLock) {
            balance += amount;
        }
        
        // Different lock for history (allows concurrent access)
        synchronized (historyLock) {
            transactionHistory.add("Deposit: " + amount);
        }
    }

    public double getBalance() {
        synchronized (balanceLock) {
            return balance;
        }
    }

    public List<String> getHistory() {
        synchronized (historyLock) {
            return new ArrayList<>(transactionHistory);
        }
    }
}
```

**When to use synchronized blocks:**
- **Fine-grained locking**: Lock only what's necessary
- **Multiple locks**: Different locks for different resources
- **Performance**: Reduce time holding lock
- **Flexibility**: Lock on specific objects, not this

**Key points:**
- Synchronized blocks reduce lock contention
- Use separate locks for independent resources
- Minimize critical section size
- Better performance in high-concurrency scenarios

---

## 15. How would you limit the number of concurrent API calls in a multithreaded Java application?

I'd use a **Semaphore** to limit concurrent access or an **ExecutorService** with a fixed thread pool to control parallelism.

```java
// Method 1: Using Semaphore (most flexible)
class APIClient {
    private final Semaphore semaphore = new Semaphore(5); // Max 5 concurrent calls

    public String callAPI(String endpoint) {
        try {
            semaphore.acquire(); // Wait if 5 calls already running
            try {
                return makeAPICall(endpoint);
            } finally {
                semaphore.release(); // Always release
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    private String makeAPICall(String endpoint) {
        // Actual API call
        return "Response from " + endpoint;
    }
}

// Method 2: Using ExecutorService with fixed thread pool
class APIClient {
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public Future<String> callAPI(String endpoint) {
        return executor.submit(() -> makeAPICall(endpoint));
    }

    private String makeAPICall(String endpoint) {
        // Actual API call
        return "Response from " + endpoint;
    }

    public void shutdown() {
        executor.shutdown();
    }
}

// Method 3: Rate limiter with time-based limiting
class RateLimitedAPIClient {
    private final Semaphore semaphore;
    private final ScheduledExecutorService scheduler;

    public RateLimitedAPIClient(int maxConcurrent, int permitsPerSecond) {
        this.semaphore = new Semaphore(maxConcurrent);
        this.scheduler = Executors.newScheduledThreadPool(1);
        
        // Release permits periodically
        scheduler.scheduleAtFixedRate(() -> {
            semaphore.release(Math.min(permitsPerSecond, maxConcurrent - semaphore.availablePermits()));
        }, 0, 1, TimeUnit.SECONDS);
    }

    public String callAPI(String endpoint) {
        try {
            if (semaphore.tryAcquire(1, TimeUnit.SECONDS)) {
                try {
                    return makeAPICall(endpoint);
                } finally {
                    // Don't release here for rate limiting
                }
            } else {
                throw new RuntimeException("Rate limit exceeded");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    private String makeAPICall(String endpoint) {
        return "Response from " + endpoint;
    }
}

// Usage example
public class Main {
    public static void main(String[] args) {
        APIClient client = new APIClient();
        
        // Spawn 20 threads, but only 5 will run concurrently
        for (int i = 0; i < 20; i++) {
            final int id = i;
            new Thread(() -> {
                String response = client.callAPI("/api/endpoint" + id);
                System.out.println("Thread " + id + ": " + response);
            }).start();
        }
    }
}
```

**Comparison:**

| Approach | Use Case | Pros | Cons |
|----------|----------|------|------|
| Semaphore | Limit concurrent calls | Simple, flexible | Manual management |
| ExecutorService | Task queue management | Built-in queue, lifecycle | Less control |
| Rate Limiter | Time-based limiting | Prevents API throttling | More complex |

**Key points:**
- Semaphore controls number of concurrent permits
- ExecutorService manages thread pool automatically
- Always release semaphore in finally block
- Use tryAcquire() with timeout to avoid indefinite blocking


---

# Exception Handling & Debugging

## 16. How would you handle exceptions in a file upload service to ensure reliability?

I'd use **try-catch blocks** with specific exception handling for different failure scenarios, validate files before processing, and clean up resources properly using try-with-resources.

```java
class FileUploadService {
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final Set<String> ALLOWED_TYPES = Set.of("jpg", "png", "pdf");

    public UploadResult uploadFile(MultipartFile file) {
        try {
            // Validation
            validateFile(file);
            
            // Save file
            String filePath = saveFile(file);
            
            return new UploadResult(true, "File uploaded successfully", filePath);
            
        } catch (InvalidFileException e) {
            return new UploadResult(false, "Invalid file: " + e.getMessage(), null);
        } catch (IOException e) {
            return new UploadResult(false, "Failed to save file", null);
        } catch (Exception e) {
            return new UploadResult(false, "Unexpected error occurred", null);
        }
    }

    private void validateFile(MultipartFile file) throws InvalidFileException {
        if (file.isEmpty()) {
            throw new InvalidFileException("File is empty");
        }
        
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new InvalidFileException("File size exceeds limit");
        }
        
        String extension = getFileExtension(file.getOriginalFilename());
        if (!ALLOWED_TYPES.contains(extension)) {
            throw new InvalidFileException("File type not allowed");
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads", fileName);
        
        // Try-with-resources ensures stream is closed
        try (InputStream input = file.getInputStream()) {
            Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
        }
        
        return path.toString();
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }
}

class InvalidFileException extends Exception {
    public InvalidFileException(String message) {
        super(message);
    }
}

class UploadResult {
    private boolean success;
    private String message;
    private String filePath;

    public UploadResult(boolean success, String message, String filePath) {
        this.success = success;
        this.message = message;
        this.filePath = filePath;
    }
}
```

**Key points:**
- Validate before processing
- Use specific exceptions for different failures
- Try-with-resources for automatic cleanup
- Return result object instead of throwing exceptions to caller
- Log errors for debugging

---

## 17. You get a NullPointerException in a large application. How would you troubleshoot it?

I'd check the **stack trace** to find the exact line, use **logging** to trace variable values, and use **Optional** or null checks to prevent it. Debugger breakpoints help identify where null is introduced.

```java
// Troubleshooting steps:

// 1. Check stack trace
/*
Exception in thread "main" java.lang.NullPointerException
    at com.example.UserService.getUser(UserService.java:45)
    at com.example.Controller.handleRequest(Controller.java:23)
*/

// 2. Add logging to trace the issue
class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User getUser(String userId) {
        logger.debug("Getting user with ID: {}", userId);
        
        User user = userRepository.findById(userId);
        logger.debug("User found: {}", user != null);
        
        if (user == null) {
            logger.warn("User not found for ID: {}", userId);
            return null;
        }
        
        return user;
    }
}

// 3. Use Optional to handle nulls gracefully
class UserService {
    public Optional<User> getUser(String userId) {
        return Optional.ofNullable(userRepository.findById(userId));
    }

    public String getUserName(String userId) {
        return getUser(userId)
            .map(User::getName)
            .orElse("Unknown");
    }
}

// 4. Add null checks and validation
class UserService {
    public User getUser(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        
        User user = userRepository.findById(userId);
        
        if (user == null) {
            throw new UserNotFoundException("User not found: " + userId);
        }
        
        return user;
    }
}

// 5. Use Objects.requireNonNull for early detection
class UserService {
    public void updateUser(User user) {
        Objects.requireNonNull(user, "User cannot be null");
        Objects.requireNonNull(user.getId(), "User ID cannot be null");
        
        userRepository.save(user);
    }
}
```

**Troubleshooting checklist:**
1. **Read stack trace** - Find exact line and method
2. **Add logging** - Trace variable values before NPE
3. **Use debugger** - Set breakpoints and inspect variables
4. **Check recent changes** - Review commits that touched the code
5. **Reproduce** - Create minimal test case
6. **Prevent** - Use Optional, null checks, validation

---

## 18. How would you design a system to retry failed operations without crashing the application?

I'd use **exponential backoff** with a maximum retry count, wrap operations in try-catch, and use a retry mechanism that handles transient failures gracefully.

```java
class RetryService {
    private static final int MAX_RETRIES = 3;
    private static final long INITIAL_DELAY = 1000; // 1 second

    public <T> T executeWithRetry(Supplier<T> operation) {
        int attempt = 0;
        long delay = INITIAL_DELAY;

        while (attempt < MAX_RETRIES) {
            try {
                return operation.get();
            } catch (Exception e) {
                attempt++;
                if (attempt >= MAX_RETRIES) {
                    throw new RuntimeException("Operation failed after " + MAX_RETRIES + " attempts", e);
                }
                
                try {
                    Thread.sleep(delay);
                    delay *= 2; // Exponential backoff
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", ie);
                }
            }
        }
        
        throw new RuntimeException("Should not reach here");
    }
}

// Usage
class APIClient {
    private RetryService retryService = new RetryService();

    public String callAPI(String endpoint) {
        return retryService.executeWithRetry(() -> {
            // This will be retried on failure
            return makeAPICall(endpoint);
        });
    }

    private String makeAPICall(String endpoint) {
        // Actual API call that might fail
        return "Response";
    }
}

// Advanced: Retry only specific exceptions
class SmartRetryService {
    private static final int MAX_RETRIES = 3;

    public <T> T executeWithRetry(Supplier<T> operation, Class<?>... retryableExceptions) {
        int attempt = 0;
        long delay = 1000;

        while (attempt < MAX_RETRIES) {
            try {
                return operation.get();
            } catch (Exception e) {
                if (!isRetryable(e, retryableExceptions)) {
                    throw e; // Don't retry non-retryable exceptions
                }
                
                attempt++;
                if (attempt >= MAX_RETRIES) {
                    throw new RuntimeException("Failed after " + MAX_RETRIES + " attempts", e);
                }
                
                sleep(delay);
                delay *= 2;
            }
        }
        
        throw new RuntimeException("Should not reach here");
    }

    private boolean isRetryable(Exception e, Class<?>[] retryableExceptions) {
        for (Class<?> clazz : retryableExceptions) {
            if (clazz.isInstance(e)) {
                return true;
            }
        }
        return false;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Usage with specific exceptions
String result = smartRetryService.executeWithRetry(
    () -> apiClient.call(),
    IOException.class,
    TimeoutException.class
);
```

**Key points:**
- Exponential backoff prevents overwhelming the system
- Maximum retry limit prevents infinite loops
- Only retry transient failures (network, timeout)
- Don't retry validation errors or business logic failures
- Log each retry attempt for monitoring

---

## 19. Explain a scenario where custom exceptions are useful.

Custom exceptions are useful for **domain-specific errors** that need special handling. They make code more readable and allow catching specific business errors separately from technical errors.

```java
// Custom exceptions for business logic
class InsufficientFundsException extends Exception {
    private double balance;
    private double requestedAmount;

    public InsufficientFundsException(double balance, double requestedAmount) {
        super(String.format("Insufficient funds. Balance: %.2f, Requested: %.2f", 
            balance, requestedAmount));
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    public double getBalance() { return balance; }
    public double getRequestedAmount() { return requestedAmount; }
}

class AccountLockedException extends Exception {
    private String accountId;
    private String reason;

    public AccountLockedException(String accountId, String reason) {
        super("Account " + accountId + " is locked: " + reason);
        this.accountId = accountId;
        this.reason = reason;
    }
}

// Service using custom exceptions
class BankService {
    public void withdraw(String accountId, double amount) 
            throws InsufficientFundsException, AccountLockedException {
        
        Account account = getAccount(accountId);
        
        if (account.isLocked()) {
            throw new AccountLockedException(accountId, "Suspicious activity detected");
        }
        
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException(account.getBalance(), amount);
        }
        
        account.debit(amount);
    }
}

// Controller with specific exception handling
@RestController
class BankController {
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequest request) {
        try {
            bankService.withdraw(request.getAccountId(), request.getAmount());
            return ResponseEntity.ok("Withdrawal successful");
            
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                    "error", "Insufficient funds",
                    "balance", e.getBalance(),
                    "requested", e.getRequestedAmount()
                ));
                
        } catch (AccountLockedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("error", e.getMessage()));
                
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Internal error"));
        }
    }
}
```

**When to use custom exceptions:**
- **Business rule violations**: InsufficientFundsException, InvalidOrderException
- **Domain-specific errors**: UserNotFoundException, DuplicateEmailException
- **Carry additional context**: Include relevant data in exception
- **Different handling**: Each exception type needs different response

---

## 20. How would you handle multiple exceptions differently in a REST API response?

I'd use **@ExceptionHandler** in Spring or try-catch blocks to map different exceptions to appropriate HTTP status codes and error messages.

```java
// Custom exception classes
class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

class ValidationException extends RuntimeException {
    private Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public Map<String, String> getErrors() { return errors; }
}

// Global exception handler
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException e) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            e.getMessage(),
            System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(ValidationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation failed",
            e.getErrors()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException e) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            e.getMessage(),
            System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception e) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal server error",
            System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

// Error response classes
class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    public ErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
    // Getters
}

class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> errors;

    public ValidationErrorResponse(int status, String message, Map<String, String> errors) {
        super(status, message, System.currentTimeMillis());
        this.errors = errors;
    }
    // Getters
}

// Controller using exceptions
@RestController
@RequestMapping("/api/users")
class UserController {

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found: " + id);
        }
        return user;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        Map<String, String> errors = validateUser(user);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        return userService.save(user);
    }

    private Map<String, String> validateUser(User user) {
        Map<String, String> errors = new HashMap<>();
        if (user.getName() == null || user.getName().isEmpty()) {
            errors.put("name", "Name is required");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            errors.put("email", "Valid email is required");
        }
        return errors;
    }
}
```

**Exception to HTTP status mapping:**
| Exception | HTTP Status | Use Case |
|-----------|-------------|----------|
| ResourceNotFoundException | 404 | Resource not found |
| ValidationException | 400 | Invalid input |
| IllegalArgumentException | 400 | Bad request |
| UnauthorizedException | 401 | Not authenticated |
| ForbiddenException | 403 | Not authorized |
| Exception | 500 | Server error |

**Key points:**
- Use @ExceptionHandler for centralized exception handling
- Map exceptions to appropriate HTTP status codes
- Include helpful error messages for clients
- Don't expose internal error details in production
- Log exceptions for debugging


---

# Streams, Lambdas & Functional Programming

## 21. How would you filter and sort a list of employees by salary using Java Streams?

I'd use **filter()** to select employees meeting criteria and **sorted()** with a comparator to order by salary. Streams make this clean and readable.

```java
class Employee {
    private String name;
    private double salary;
    private String department;

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public double getSalary() { return salary; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
}

// Filter and sort
List<Employee> employees = Arrays.asList(
    new Employee("John", 50000, "IT"),
    new Employee("Jane", 75000, "IT"),
    new Employee("Bob", 60000, "HR")
);

// Filter employees with salary > 55000 and sort by salary
List<Employee> filtered = employees.stream()
    .filter(e -> e.getSalary() > 55000)
    .sorted(Comparator.comparingDouble(Employee::getSalary))
    .collect(Collectors.toList());

// Sort in descending order
List<Employee> sortedDesc = employees.stream()
    .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
    .collect(Collectors.toList());

// Multiple filters and sort
List<Employee> result = employees.stream()
    .filter(e -> e.getSalary() > 50000)
    .filter(e -> e.getDepartment().equals("IT"))
    .sorted(Comparator.comparingDouble(Employee::getSalary))
    .collect(Collectors.toList());

// Get top 3 highest paid employees
List<Employee> top3 = employees.stream()
    .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
    .limit(3)
    .collect(Collectors.toList());
```

**Key points:**
- filter() selects elements matching predicate
- sorted() orders elements using comparator
- Comparator.comparingDouble() for numeric sorting
- reversed() for descending order
- Chain multiple operations for complex queries

---

## 22. How would you group a list of transactions by customer using Java Streams?

I'd use **Collectors.groupingBy()** which creates a Map with customer as key and list of transactions as value.

```java
class Transaction {
    private String customerId;
    private double amount;
    private String type;

    public Transaction(String customerId, double amount, String type) {
        this.customerId = customerId;
        this.amount = amount;
        this.type = type;
    }

    public String getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public String getType() { return type; }
}

List<Transaction> transactions = Arrays.asList(
    new Transaction("C001", 100, "DEBIT"),
    new Transaction("C002", 200, "CREDIT"),
    new Transaction("C001", 150, "CREDIT")
);

// Group by customer ID
Map<String, List<Transaction>> byCustomer = transactions.stream()
    .collect(Collectors.groupingBy(Transaction::getCustomerId));

// Group and count transactions per customer
Map<String, Long> countByCustomer = transactions.stream()
    .collect(Collectors.groupingBy(
        Transaction::getCustomerId,
        Collectors.counting()
    ));

// Group and sum amounts per customer
Map<String, Double> sumByCustomer = transactions.stream()
    .collect(Collectors.groupingBy(
        Transaction::getCustomerId,
        Collectors.summingDouble(Transaction::getAmount)
    ));

// Group by customer and transaction type
Map<String, Map<String, List<Transaction>>> byCustomerAndType = transactions.stream()
    .collect(Collectors.groupingBy(
        Transaction::getCustomerId,
        Collectors.groupingBy(Transaction::getType)
    ));

// Group and get average amount per customer
Map<String, Double> avgByCustomer = transactions.stream()
    .collect(Collectors.groupingBy(
        Transaction::getCustomerId,
        Collectors.averagingDouble(Transaction::getAmount)
    ));
```

**Key points:**
- groupingBy() creates Map<Key, List<Value>>
- Can combine with counting(), summingDouble(), averagingDouble()
- Supports nested grouping for multiple levels
- Returns immutable map by default

---

## 23. Write a Java function to convert a list of strings to uppercase using a functional approach.

I'd use **map()** with method reference or lambda to transform each string to uppercase.

```java
// Method 1: Using method reference (cleanest)
public List<String> toUpperCase(List<String> strings) {
    return strings.stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());
}

// Method 2: Using lambda
public List<String> toUpperCaseLambda(List<String> strings) {
    return strings.stream()
        .map(s -> s.toUpperCase())
        .collect(Collectors.toList());
}

// Usage
List<String> names = Arrays.asList("john", "jane", "bob");
List<String> upper = toUpperCase(names);
// Result: ["JOHN", "JANE", "BOB"]

// One-liner
List<String> upper = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Filter and transform
List<String> filtered = names.stream()
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Transform and join
String joined = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.joining(", "));
// Result: "JOHN, JANE, BOB"
```

**Key points:**
- map() transforms each element
- String::toUpperCase is a method reference
- More concise than traditional for loops
- Immutable - original list unchanged

---

## 24. Explain a scenario where parallel streams would improve performance.

Parallel streams improve performance for **CPU-intensive operations on large datasets** where tasks are independent. They use multiple threads to process elements concurrently.

```java
// Scenario: Processing large dataset with expensive operations
class DataProcessor {
    
    // Sequential stream - processes one by one
    public List<Result> processSequential(List<Data> dataset) {
        return dataset.stream()
            .map(this::expensiveOperation)
            .collect(Collectors.toList());
    }

    // Parallel stream - uses multiple threads
    public List<Result> processParallel(List<Data> dataset) {
        return dataset.parallelStream()
            .map(this::expensiveOperation)
            .collect(Collectors.toList());
    }

    private Result expensiveOperation(Data data) {
        // CPU-intensive calculation
        return new Result(data);
    }
}

// Real-world example: Image processing
class ImageProcessor {
    public List<ProcessedImage> processImages(List<Image> images) {
        return images.parallelStream()
            .map(this::resize)
            .map(this::applyFilter)
            .map(this::compress)
            .collect(Collectors.toList());
    }

    private ProcessedImage resize(Image img) { /* expensive */ return null; }
    private ProcessedImage applyFilter(ProcessedImage img) { /* expensive */ return null; }
    private ProcessedImage compress(ProcessedImage img) { /* expensive */ return null; }
}

// Example: Large number calculations
List<Integer> numbers = IntStream.range(1, 1000000).boxed().collect(Collectors.toList());

// Sequential - slower
long sum = numbers.stream()
    .mapToLong(n -> n * n)
    .sum();

// Parallel - faster for large datasets
long sumParallel = numbers.parallelStream()
    .mapToLong(n -> n * n)
    .sum();
```

**When to use parallel streams:**
- **Large datasets** (thousands+ elements)
- **CPU-intensive operations** (calculations, transformations)
- **Independent tasks** (no shared state)
- **Stateless operations** (no side effects)

**When NOT to use:**
- Small datasets (overhead > benefit)
- I/O operations (network, database)
- Operations with side effects
- Order-dependent operations

**Performance comparison:**
```java
// Benchmark example
List<Integer> data = IntStream.range(1, 10000000).boxed().collect(Collectors.toList());

// Sequential: ~500ms
long start = System.currentTimeMillis();
long sum1 = data.stream().mapToLong(n -> n * n).sum();
System.out.println("Sequential: " + (System.currentTimeMillis() - start) + "ms");

// Parallel: ~150ms (on 4-core CPU)
start = System.currentTimeMillis();
long sum2 = data.parallelStream().mapToLong(n -> n * n).sum();
System.out.println("Parallel: " + (System.currentTimeMillis() - start) + "ms");
```

**Key points:**
- Use for CPU-bound operations on large data
- Avoid for I/O-bound operations
- Test performance - not always faster
- Uses ForkJoinPool with available processors

---

## 25. How would you combine multiple predicates in a filter operation?

I'd use **Predicate.and()**, **Predicate.or()**, or chain multiple **filter()** calls. This makes complex filtering logic clean and reusable.

```java
class Employee {
    private String name;
    private double salary;
    private String department;
    private int age;

    // Constructor and getters
}

List<Employee> employees = Arrays.asList(/* employees */);

// Method 1: Chain multiple filters
List<Employee> result = employees.stream()
    .filter(e -> e.getSalary() > 50000)
    .filter(e -> e.getDepartment().equals("IT"))
    .filter(e -> e.getAge() < 40)
    .collect(Collectors.toList());

// Method 2: Combine with && in single filter
List<Employee> result2 = employees.stream()
    .filter(e -> e.getSalary() > 50000 && 
                 e.getDepartment().equals("IT") && 
                 e.getAge() < 40)
    .collect(Collectors.toList());

// Method 3: Using Predicate.and()
Predicate<Employee> highSalary = e -> e.getSalary() > 50000;
Predicate<Employee> itDept = e -> e.getDepartment().equals("IT");
Predicate<Employee> youngAge = e -> e.getAge() < 40;

List<Employee> result3 = employees.stream()
    .filter(highSalary.and(itDept).and(youngAge))
    .collect(Collectors.toList());

// Method 4: Using Predicate.or()
Predicate<Employee> seniorOrHighPaid = 
    e -> e.getAge() > 50 || e.getSalary() > 100000;

List<Employee> result4 = employees.stream()
    .filter(seniorOrHighPaid)
    .collect(Collectors.toList());

// Method 5: Combining and/or
Predicate<Employee> itOrHR = 
    e -> e.getDepartment().equals("IT") || e.getDepartment().equals("HR");
Predicate<Employee> highPaid = e -> e.getSalary() > 70000;

List<Employee> result5 = employees.stream()
    .filter(itOrHR.and(highPaid))
    .collect(Collectors.toList());

// Method 6: Negate predicate
Predicate<Employee> notIT = e -> e.getDepartment().equals("IT");
List<Employee> nonIT = employees.stream()
    .filter(notIT.negate())
    .collect(Collectors.toList());

// Reusable predicates
class EmployeePredicates {
    public static Predicate<Employee> hasHighSalary(double threshold) {
        return e -> e.getSalary() > threshold;
    }

    public static Predicate<Employee> inDepartment(String dept) {
        return e -> e.getDepartment().equals(dept);
    }

    public static Predicate<Employee> ageRange(int min, int max) {
        return e -> e.getAge() >= min && e.getAge() <= max;
    }
}

// Usage with reusable predicates
List<Employee> filtered = employees.stream()
    .filter(EmployeePredicates.hasHighSalary(60000)
        .and(EmployeePredicates.inDepartment("IT"))
        .and(EmployeePredicates.ageRange(25, 45)))
    .collect(Collectors.toList());
```

**Predicate operations:**
| Method | Description | Example |
|--------|-------------|---------|
| and() | Both must be true | p1.and(p2) |
| or() | Either must be true | p1.or(p2) |
| negate() | Opposite of predicate | p1.negate() |

**Key points:**
- Chain filters for readability
- Use Predicate.and()/or() for reusable logic
- negate() for opposite condition
- Extract predicates to utility class for reuse
- Predicates are composable and testable


---

# File I/O & Serialization

## 26. How would you read a large CSV file and process it efficiently in Java?

I'd use **BufferedReader** with **Files.lines()** or stream processing to read line by line without loading the entire file into memory. This prevents OutOfMemoryError for large files.

```java
// Method 1: Using BufferedReader (traditional)
public void processCSV(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        reader.readLine(); // Skip header
        
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            processRecord(fields);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Method 2: Using Files.lines() with Stream (modern)
public void processCSVStream(String filePath) {
    try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
        lines.skip(1) // Skip header
             .map(line -> line.split(","))
             .forEach(this::processRecord);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Method 3: Parallel processing for large files
public void processCSVParallel(String filePath) {
    try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
        lines.skip(1)
             .parallel()
             .map(line -> line.split(","))
             .forEach(this::processRecord);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Method 4: Batch processing
public void processCSVBatch(String filePath, int batchSize) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        List<String[]> batch = new ArrayList<>();
        String line;
        reader.readLine(); // Skip header
        
        while ((line = reader.readLine()) != null) {
            batch.add(line.split(","));
            
            if (batch.size() >= batchSize) {
                processBatch(batch);
                batch.clear();
            }
        }
        
        if (!batch.isEmpty()) {
            processBatch(batch);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void processRecord(String[] fields) {
    // Process individual record
}

private void processBatch(List<String[]> batch) {
    // Process batch (e.g., bulk database insert)
}
```

**Key points:**
- BufferedReader reads line by line (memory efficient)
- Files.lines() returns Stream for functional processing
- Use parallel() for CPU-intensive processing
- Batch processing for database operations
- Always use try-with-resources for automatic closing

---

## 27. How would you write objects to a file and read them back using serialization?

I'd implement **Serializable** interface and use **ObjectOutputStream** to write and **ObjectInputStream** to read. The object must be serializable along with all its fields.

```java
// Serializable class
class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;
    private transient String password; // Won't be serialized

    public User(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
}

// Write objects to file
public void writeObjects(List<User> users, String filePath) {
    try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(filePath))) {
        oos.writeObject(users);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Read objects from file
public List<User> readObjects(String filePath) {
    try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(filePath))) {
        return (List<User>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}

// Write single object
public void writeUser(User user, String filePath) {
    try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(filePath))) {
        oos.writeObject(user);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Read single object
public User readUser(String filePath) {
    try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(filePath))) {
        return (User) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        return null;
    }
}

// Append objects to existing file
public void appendObject(User user, String filePath) {
    try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(filePath, true))) {
        oos.writeObject(user);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

**Key points:**
- Implement Serializable interface
- Use serialVersionUID for version control
- transient keyword excludes fields from serialization
- Handle ClassNotFoundException when reading
- Use try-with-resources for automatic stream closing

---

## 28. How would you implement a log rotation system in Java?

I'd check file size before writing and create a new log file when the current one exceeds the limit. Keep a maximum number of backup files.

```java
class LogRotationSystem {
    private final String logDir;
    private final String logFileName;
    private final long maxFileSize;
    private final int maxBackupFiles;
    private File currentLogFile;

    public LogRotationSystem(String logDir, String logFileName, 
                            long maxFileSize, int maxBackupFiles) {
        this.logDir = logDir;
        this.logFileName = logFileName;
        this.maxFileSize = maxFileSize;
        this.maxBackupFiles = maxBackupFiles;
        this.currentLogFile = new File(logDir, logFileName);
    }

    public synchronized void log(String message) {
        try {
            if (shouldRotate()) {
                rotate();
            }
            
            writeLog(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean shouldRotate() {
        return currentLogFile.exists() && currentLogFile.length() > maxFileSize;
    }

    private void rotate() throws IOException {
        // Delete oldest backup if max reached
        File oldestBackup = new File(logDir, logFileName + "." + maxBackupFiles);
        if (oldestBackup.exists()) {
            oldestBackup.delete();
        }

        // Shift existing backups
        for (int i = maxBackupFiles - 1; i > 0; i--) {
            File from = new File(logDir, logFileName + "." + i);
            File to = new File(logDir, logFileName + "." + (i + 1));
            if (from.exists()) {
                from.renameTo(to);
            }
        }

        // Rename current log to .1
        File backup = new File(logDir, logFileName + ".1");
        currentLogFile.renameTo(backup);

        // Create new log file
        currentLogFile = new File(logDir, logFileName);
    }

    private void writeLog(String message) throws IOException {
        try (FileWriter writer = new FileWriter(currentLogFile, true)) {
            writer.write(String.format("[%s] %s%n", 
                LocalDateTime.now(), message));
        }
    }
}

// Usage
LogRotationSystem logger = new LogRotationSystem(
    "logs", 
    "app.log", 
    1024 * 1024, // 1MB
    5            // Keep 5 backups
);

logger.log("Application started");
```

**Key points:**
- Check file size before writing
- Rotate when size exceeds limit
- Keep fixed number of backup files
- Synchronized for thread safety
- Timestamp each log entry

---

## 29. Explain how you would compress a file using Java I/O streams.

I'd use **GZIPOutputStream** to compress and **GZIPInputStream** to decompress. Wrap these around file streams for automatic compression.

```java
// Compress a file
public void compressFile(String sourceFile, String compressedFile) {
    try (FileInputStream fis = new FileInputStream(sourceFile);
         FileOutputStream fos = new FileOutputStream(compressedFile);
         GZIPOutputStream gzos = new GZIPOutputStream(fos)) {
        
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) > 0) {
            gzos.write(buffer, 0, len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Decompress a file
public void decompressFile(String compressedFile, String outputFile) {
    try (FileInputStream fis = new FileInputStream(compressedFile);
         GZIPInputStream gzis = new GZIPInputStream(fis);
         FileOutputStream fos = new FileOutputStream(outputFile)) {
        
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gzis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Compress string data
public byte[] compressString(String data) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (GZIPOutputStream gzos = new GZIPOutputStream(baos)) {
        gzos.write(data.getBytes());
    }
    return baos.toByteArray();
}

// Decompress string data
public String decompressString(byte[] compressed) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(compressed);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    
    try (GZIPInputStream gzis = new GZIPInputStream(bais)) {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gzis.read(buffer)) > 0) {
            baos.write(buffer, 0, len);
        }
    }
    
    return baos.toString();
}

// Compress multiple files into ZIP
public void compressMultipleFiles(List<String> files, String zipFile) {
    try (FileOutputStream fos = new FileOutputStream(zipFile);
         ZipOutputStream zos = new ZipOutputStream(fos)) {
        
        for (String file : files) {
            File fileToZip = new File(file);
            try (FileInputStream fis = new FileInputStream(fileToZip)) {
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zos.putNextEntry(zipEntry);
                
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

**Key points:**
- GZIPOutputStream for single file compression
- ZipOutputStream for multiple files
- Use buffered reading/writing for performance
- Try-with-resources ensures streams are closed
- Compression ratio depends on data type

---

## 30. How would you monitor a folder for new files and process them automatically?

I'd use **WatchService** API to monitor directory changes and process new files as they arrive. This is more efficient than polling.

```java
class FolderMonitor {
    private final Path directory;
    private final WatchService watchService;

    public FolderMonitor(String directoryPath) throws IOException {
        this.directory = Paths.get(directoryPath);
        this.watchService = FileSystems.getDefault().newWatchService();
        
        directory.register(watchService, 
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_MODIFY);
    }

    public void startMonitoring() {
        new Thread(() -> {
            try {
                while (true) {
                    WatchKey key = watchService.take();
                    
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        
                        if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                            Path fileName = (Path) event.context();
                            Path fullPath = directory.resolve(fileName);
                            processNewFile(fullPath);
                        }
                    }
                    
                    key.reset();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void processNewFile(Path filePath) {
        System.out.println("New file detected: " + filePath);
        // Process the file
    }

    public void stop() throws IOException {
        watchService.close();
    }
}

// Advanced: With file filter and processing
class AdvancedFolderMonitor {
    private final Path directory;
    private final WatchService watchService;
    private final ExecutorService executor;

    public AdvancedFolderMonitor(String directoryPath) throws IOException {
        this.directory = Paths.get(directoryPath);
        this.watchService = FileSystems.getDefault().newWatchService();
        this.executor = Executors.newFixedThreadPool(4);
        
        directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }

    public void startMonitoring() {
        new Thread(() -> {
            try {
                while (true) {
                    WatchKey key = watchService.take();
                    
                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                            Path fileName = (Path) event.context();
                            Path fullPath = directory.resolve(fileName);
                            
                            // Process in separate thread
                            executor.submit(() -> processFile(fullPath));
                        }
                    }
                    
                    key.reset();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void processFile(Path filePath) {
        try {
            // Wait for file to be fully written
            Thread.sleep(100);
            
            // Filter by extension
            if (filePath.toString().endsWith(".csv")) {
                System.out.println("Processing CSV: " + filePath);
                processCSV(filePath);
            } else if (filePath.toString().endsWith(".txt")) {
                System.out.println("Processing TXT: " + filePath);
                processText(filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processCSV(Path filePath) {
        // CSV processing logic
    }

    private void processText(Path filePath) {
        // Text processing logic
    }

    public void stop() throws IOException {
        watchService.close();
        executor.shutdown();
    }
}

// Usage
FolderMonitor monitor = new FolderMonitor("/path/to/folder");
monitor.startMonitoring();
```

**Key points:**
- WatchService monitors directory changes efficiently
- Register for ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE events
- Process files in separate threads for better performance
- Add delay to ensure file is fully written
- Filter files by extension or pattern
- Use ExecutorService for concurrent processing


---

# Real-World Algorithmic Scenarios

## 31. How would you find duplicate records in a large database and remove them efficiently?

I'd use a **HashSet** to track seen records while iterating, or use SQL GROUP BY with HAVING COUNT > 1 for database-level deduplication. For large datasets, process in batches.

```java
class Record {
    private String id;
    private String name;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return email.equals(record.email); // Duplicate by email
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

// Method 1: In-memory deduplication
public List<Record> removeDuplicates(List<Record> records) {
    Set<Record> seen = new HashSet<>();
    List<Record> unique = new ArrayList<>();
    
    for (Record record : records) {
        if (seen.add(record)) {
            unique.add(record);
        }
    }
    
    return unique;
}

// Method 2: Using Stream
public List<Record> removeDuplicatesStream(List<Record> records) {
    return records.stream()
        .distinct()
        .collect(Collectors.toList());
}

// Method 3: Database-level (SQL)
public void removeDatabaseDuplicates() {
    String sql = """
        DELETE FROM records
        WHERE id NOT IN (
            SELECT MIN(id)
            FROM records
            GROUP BY email
        )
    """;
    // Execute SQL
}

// Method 4: Batch processing for large datasets
public void removeDuplicatesBatch(int batchSize) {
    Set<String> seenEmails = new HashSet<>();
    int offset = 0;
    
    while (true) {
        List<Record> batch = fetchRecordsBatch(offset, batchSize);
        if (batch.isEmpty()) break;
        
        List<Long> duplicateIds = new ArrayList<>();
        
        for (Record record : batch) {
            if (!seenEmails.add(record.getEmail())) {
                duplicateIds.add(record.getId());
            }
        }
        
        if (!duplicateIds.isEmpty()) {
            deleteDuplicates(duplicateIds);
        }
        
        offset += batchSize;
    }
}

// Method 5: Find duplicates with details
public Map<String, List<Record>> findDuplicates(List<Record> records) {
    return records.stream()
        .collect(Collectors.groupingBy(Record::getEmail))
        .entrySet().stream()
        .filter(e -> e.getValue().size() > 1)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
}
```

**Key points:**
- HashSet for O(1) duplicate detection
- Override equals() and hashCode() for custom comparison
- Use SQL for database-level deduplication
- Process in batches for large datasets
- Stream distinct() for simple cases

---

## 32. How would you merge two sorted lists of orders in real-time?

I'd use a **two-pointer approach** to merge sorted lists in O(n+m) time, similar to merge sort. Compare elements from both lists and add the smaller one.

```java
class Order {
    private String id;
    private LocalDateTime timestamp;
    private double amount;

    public LocalDateTime getTimestamp() { return timestamp; }
}

// Merge two sorted lists
public List<Order> mergeSortedOrders(List<Order> list1, List<Order> list2) {
    List<Order> merged = new ArrayList<>();
    int i = 0, j = 0;
    
    while (i < list1.size() && j < list2.size()) {
        if (list1.get(i).getTimestamp().isBefore(list2.get(j).getTimestamp())) {
            merged.add(list1.get(i++));
        } else {
            merged.add(list2.get(j++));
        }
    }
    
    // Add remaining elements
    while (i < list1.size()) {
        merged.add(list1.get(i++));
    }
    
    while (j < list2.size()) {
        merged.add(list2.get(j++));
    }
    
    return merged;
}

// Using Stream (simpler but less efficient)
public List<Order> mergeSortedOrdersStream(List<Order> list1, List<Order> list2) {
    return Stream.concat(list1.stream(), list2.stream())
        .sorted(Comparator.comparing(Order::getTimestamp))
        .collect(Collectors.toList());
}

// Merge multiple sorted lists
public List<Order> mergeMultipleLists(List<List<Order>> lists) {
    PriorityQueue<OrderWithIndex> pq = new PriorityQueue<>(
        Comparator.comparing(o -> o.order.getTimestamp())
    );
    
    // Add first element from each list
    for (int i = 0; i < lists.size(); i++) {
        if (!lists.get(i).isEmpty()) {
            pq.offer(new OrderWithIndex(lists.get(i).get(0), i, 0));
        }
    }
    
    List<Order> merged = new ArrayList<>();
    
    while (!pq.isEmpty()) {
        OrderWithIndex current = pq.poll();
        merged.add(current.order);
        
        int nextIndex = current.indexInList + 1;
        if (nextIndex < lists.get(current.listIndex).size()) {
            pq.offer(new OrderWithIndex(
                lists.get(current.listIndex).get(nextIndex),
                current.listIndex,
                nextIndex
            ));
        }
    }
    
    return merged;
}

class OrderWithIndex {
    Order order;
    int listIndex;
    int indexInList;

    OrderWithIndex(Order order, int listIndex, int indexInList) {
        this.order = order;
        this.listIndex = listIndex;
        this.indexInList = indexInList;
    }
}
```

**Key points:**
- Two-pointer approach: O(n+m) time, O(n+m) space
- PriorityQueue for merging multiple lists
- Assumes input lists are already sorted
- Stream approach simpler but O((n+m)log(n+m))

---

## 33. How would you implement a rate limiter for API requests in Java?

I'd use a **token bucket** or **sliding window** algorithm with timestamps. Track request counts per time window and reject when limit exceeded.

```java
// Method 1: Token Bucket (simple)
class TokenBucketRateLimiter {
    private final int maxTokens;
    private final long refillIntervalMs;
    private int availableTokens;
    private long lastRefillTime;

    public TokenBucketRateLimiter(int maxTokens, long refillIntervalMs) {
        this.maxTokens = maxTokens;
        this.refillIntervalMs = refillIntervalMs;
        this.availableTokens = maxTokens;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        refillTokens();
        
        if (availableTokens > 0) {
            availableTokens--;
            return true;
        }
        
        return false;
    }

    private void refillTokens() {
        long now = System.currentTimeMillis();
        long timePassed = now - lastRefillTime;
        
        if (timePassed >= refillIntervalMs) {
            availableTokens = maxTokens;
            lastRefillTime = now;
        }
    }
}

// Method 2: Sliding Window (more accurate)
class SlidingWindowRateLimiter {
    private final int maxRequests;
    private final long windowMs;
    private final Queue<Long> requestTimestamps;

    public SlidingWindowRateLimiter(int maxRequests, long windowMs) {
        this.maxRequests = maxRequests;
        this.windowMs = windowMs;
        this.requestTimestamps = new LinkedList<>();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        
        // Remove old timestamps outside window
        while (!requestTimestamps.isEmpty() && 
               now - requestTimestamps.peek() > windowMs) {
            requestTimestamps.poll();
        }
        
        if (requestTimestamps.size() < maxRequests) {
            requestTimestamps.offer(now);
            return true;
        }
        
        return false;
    }
}

// Method 3: Per-user rate limiting
class UserRateLimiter {
    private final Map<String, SlidingWindowRateLimiter> userLimiters;
    private final int maxRequests;
    private final long windowMs;

    public UserRateLimiter(int maxRequests, long windowMs) {
        this.userLimiters = new ConcurrentHashMap<>();
        this.maxRequests = maxRequests;
        this.windowMs = windowMs;
    }

    public boolean allowRequest(String userId) {
        SlidingWindowRateLimiter limiter = userLimiters.computeIfAbsent(
            userId, 
            k -> new SlidingWindowRateLimiter(maxRequests, windowMs)
        );
        
        return limiter.allowRequest();
    }
}

// Usage in API
@RestController
class APIController {
    private final UserRateLimiter rateLimiter = new UserRateLimiter(100, 60000); // 100 req/min

    @GetMapping("/api/data")
    public ResponseEntity<?> getData(@RequestHeader("User-Id") String userId) {
        if (!rateLimiter.allowRequest(userId)) {
            return ResponseEntity.status(429).body("Rate limit exceeded");
        }
        
        return ResponseEntity.ok("Data");
    }
}
```

**Key points:**
- Token bucket: Simple, allows bursts
- Sliding window: More accurate, memory intensive
- Per-user limiting with ConcurrentHashMap
- Return 429 status when limit exceeded
- Consider distributed rate limiting with Redis for multiple servers

---

## 34. How would you detect a cycle in a dependency graph of tasks?

I'd use **DFS with color marking** (white/gray/black) or **topological sort**. If we encounter a gray node during DFS, there's a cycle.

```java
class Task {
    private String id;
    private List<Task> dependencies;

    public Task(String id) {
        this.id = id;
        this.dependencies = new ArrayList<>();
    }

    public void addDependency(Task task) {
        dependencies.add(task);
    }

    public List<Task> getDependencies() { return dependencies; }
    public String getId() { return id; }
}

// Method 1: DFS with color marking
class CycleDetector {
    enum Color { WHITE, GRAY, BLACK }
    
    public boolean hasCycle(List<Task> tasks) {
        Map<Task, Color> colors = new HashMap<>();
        
        // Initialize all as WHITE
        for (Task task : tasks) {
            colors.put(task, Color.WHITE);
        }
        
        // Check each task
        for (Task task : tasks) {
            if (colors.get(task) == Color.WHITE) {
                if (hasCycleDFS(task, colors)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean hasCycleDFS(Task task, Map<Task, Color> colors) {
        colors.put(task, Color.GRAY); // Mark as visiting
        
        for (Task dependency : task.getDependencies()) {
            Color color = colors.get(dependency);
            
            if (color == Color.GRAY) {
                return true; // Back edge found - cycle!
            }
            
            if (color == Color.WHITE) {
                if (hasCycleDFS(dependency, colors)) {
                    return true;
                }
            }
        }
        
        colors.put(task, Color.BLACK); // Mark as visited
        return false;
    }
}

// Method 2: Using visited and recursion stack
class SimpleCycleDetector {
    public boolean hasCycle(List<Task> tasks) {
        Set<Task> visited = new HashSet<>();
        Set<Task> recStack = new HashSet<>();
        
        for (Task task : tasks) {
            if (hasCycleDFS(task, visited, recStack)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean hasCycleDFS(Task task, Set<Task> visited, Set<Task> recStack) {
        if (recStack.contains(task)) {
            return true; // Cycle detected
        }
        
        if (visited.contains(task)) {
            return false; // Already processed
        }
        
        visited.add(task);
        recStack.add(task);
        
        for (Task dependency : task.getDependencies()) {
            if (hasCycleDFS(dependency, visited, recStack)) {
                return true;
            }
        }
        
        recStack.remove(task);
        return false;
    }
}

// Method 3: Topological sort (Kahn's algorithm)
class TopologicalSortDetector {
    public boolean hasCycle(List<Task> tasks) {
        Map<Task, Integer> inDegree = new HashMap<>();
        
        // Calculate in-degrees
        for (Task task : tasks) {
            inDegree.putIfAbsent(task, 0);
            for (Task dep : task.getDependencies()) {
                inDegree.put(dep, inDegree.getOrDefault(dep, 0) + 1);
            }
        }
        
        // Queue tasks with no dependencies
        Queue<Task> queue = new LinkedList<>();
        for (Task task : tasks) {
            if (inDegree.get(task) == 0) {
                queue.offer(task);
            }
        }
        
        int processedCount = 0;
        
        while (!queue.isEmpty()) {
            Task task = queue.poll();
            processedCount++;
            
            for (Task dep : task.getDependencies()) {
                inDegree.put(dep, inDegree.get(dep) - 1);
                if (inDegree.get(dep) == 0) {
                    queue.offer(dep);
                }
            }
        }
        
        return processedCount != tasks.size(); // Cycle if not all processed
    }
}
```

**Key points:**
- DFS with colors: WHITE (unvisited), GRAY (visiting), BLACK (visited)
- Gray node encountered = cycle detected
- Topological sort fails if cycle exists
- Time complexity: O(V + E)

---

## 35. How would you implement autocomplete suggestions from a large dictionary of words?

I'd use a **Trie (prefix tree)** for efficient prefix matching. This gives O(k) lookup time where k is the prefix length.

```java
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
    String word = null;
}

class AutocompleteSystem {
    private TrieNode root;

    public AutocompleteSystem() {
        root = new TrieNode();
    }

    // Insert word into trie
    public void addWord(String word) {
        TrieNode node = root;
        
        for (char c : word.toLowerCase().toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        
        node.isEndOfWord = true;
        node.word = word;
    }

    // Get suggestions for prefix
    public List<String> getSuggestions(String prefix, int maxResults) {
        List<String> suggestions = new ArrayList<>();
        TrieNode node = root;
        
        // Navigate to prefix node
        for (char c : prefix.toLowerCase().toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return suggestions; // Prefix not found
            }
        }
        
        // Collect all words with this prefix
        collectWords(node, suggestions, maxResults);
        return suggestions;
    }

    private void collectWords(TrieNode node, List<String> suggestions, int maxResults) {
        if (suggestions.size() >= maxResults) {
            return;
        }
        
        if (node.isEndOfWord) {
            suggestions.add(node.word);
        }
        
        for (TrieNode child : node.children.values()) {
            collectWords(child, suggestions, maxResults);
        }
    }
}

// Advanced: With frequency ranking
class RankedAutocomplete {
    private TrieNode root;

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        Map<String, Integer> wordFrequency = new HashMap<>();
    }

    public RankedAutocomplete() {
        root = new TrieNode();
    }

    public void addWord(String word, int frequency) {
        TrieNode node = root;
        
        for (char c : word.toLowerCase().toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.wordFrequency.put(word, frequency);
        }
    }

    public List<String> getTopSuggestions(String prefix, int limit) {
        TrieNode node = root;
        
        for (char c : prefix.toLowerCase().toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return new ArrayList<>();
            }
        }
        
        return node.wordFrequency.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(limit)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }
}

// Usage
AutocompleteSystem autocomplete = new AutocompleteSystem();
autocomplete.addWord("apple");
autocomplete.addWord("application");
autocomplete.addWord("apply");
autocomplete.addWord("banana");

List<String> suggestions = autocomplete.getSuggestions("app", 5);
// Returns: ["apple", "application", "apply"]
```

**Key points:**
- Trie provides O(k) prefix search where k = prefix length
- Space efficient for large dictionaries with common prefixes
- Can rank by frequency for better suggestions
- DFS to collect all words with given prefix
- Limit results to avoid overwhelming user


---

# Design Patterns in Practice

## 36. How would you use the Observer pattern to notify multiple services of a data change?

I'd create a **Subject** that maintains a list of observers and notifies them when data changes. Observers implement an interface to receive updates.

```java
// Observer interface
interface DataObserver {
    void onDataChanged(String data);
}

// Subject (Observable)
class DataService {
    private List<DataObserver> observers = new ArrayList<>();
    private String data;

    public void addObserver(DataObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(DataObserver observer) {
        observers.remove(observer);
    }

    public void setData(String newData) {
        this.data = newData;
        notifyObservers();
    }

    private void notifyObservers() {
        for (DataObserver observer : observers) {
            observer.onDataChanged(data);
        }
    }
}

// Concrete Observers
class EmailService implements DataObserver {
    @Override
    public void onDataChanged(String data) {
        System.out.println("EmailService: Sending email about " + data);
    }
}

class LoggingService implements DataObserver {
    @Override
    public void onDataChanged(String data) {
        System.out.println("LoggingService: Logging change " + data);
    }
}

class CacheService implements DataObserver {
    @Override
    public void onDataChanged(String data) {
        System.out.println("CacheService: Invalidating cache for " + data);
    }
}

// Usage
DataService dataService = new DataService();
dataService.addObserver(new EmailService());
dataService.addObserver(new LoggingService());
dataService.addObserver(new CacheService());

dataService.setData("User updated"); // All observers notified

// Real-world example: Order processing
interface OrderObserver {
    void onOrderPlaced(Order order);
}

class OrderService {
    private List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void placeOrder(Order order) {
        // Save order
        notifyObservers(order);
    }

    private void notifyObservers(Order order) {
        observers.forEach(o -> o.onOrderPlaced(order));
    }
}

class InventoryService implements OrderObserver {
    @Override
    public void onOrderPlaced(Order order) {
        // Update inventory
    }
}

class NotificationService implements OrderObserver {
    @Override
    public void onOrderPlaced(Order order) {
        // Send confirmation email
    }
}
```

**Key points:**
- Loose coupling between subject and observers
- Multiple observers can react to same event
- Easy to add/remove observers dynamically
- Used in event-driven architectures

---

## 37. When would you use the Factory pattern in a real-world Java application?

I'd use the **Factory pattern** when object creation logic is complex or when I need to create different types of objects based on input. It centralizes object creation.

```java
// Product interface
interface Payment {
    void processPayment(double amount);
}

// Concrete products
class CreditCardPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
    }
}

class PayPalPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
    }
}

class CryptoPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing crypto payment: $" + amount);
    }
}

// Factory
class PaymentFactory {
    public static Payment createPayment(String type) {
        switch (type.toLowerCase()) {
            case "credit":
                return new CreditCardPayment();
            case "paypal":
                return new PayPalPayment();
            case "crypto":
                return new CryptoPayment();
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}

// Usage
Payment payment = PaymentFactory.createPayment("credit");
payment.processPayment(100.0);

// Real-world example: Database connection
class DatabaseConnectionFactory {
    public static Connection createConnection(String dbType) {
        switch (dbType) {
            case "mysql":
                return new MySQLConnection();
            case "postgres":
                return new PostgresConnection();
            case "mongodb":
                return new MongoDBConnection();
            default:
                throw new IllegalArgumentException("Unsupported database");
        }
    }
}

// Real-world example: Document parser
class DocumentParserFactory {
    public static DocumentParser createParser(String fileExtension) {
        switch (fileExtension) {
            case "pdf":
                return new PDFParser();
            case "docx":
                return new WordParser();
            case "xlsx":
                return new ExcelParser();
            default:
                throw new IllegalArgumentException("Unsupported format");
        }
    }
}
```

**When to use Factory pattern:**
- **Complex object creation**: Multiple steps or configuration
- **Type selection at runtime**: Based on user input or config
- **Hide implementation details**: Client doesn't need to know concrete classes
- **Centralize creation logic**: Single place to modify object creation

**Key points:**
- Encapsulates object creation
- Promotes loose coupling
- Easy to add new types without changing client code
- Common in frameworks and libraries

---

## 38. How would you implement the Decorator pattern for adding features to a notification system?

I'd create a **base component** and **decorators** that wrap it to add functionality. Each decorator adds behavior while maintaining the same interface.

```java
// Component interface
interface Notification {
    void send(String message);
}

// Concrete component
class BasicNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending: " + message);
    }
}

// Base decorator
abstract class NotificationDecorator implements Notification {
    protected Notification wrapped;

    public NotificationDecorator(Notification notification) {
        this.wrapped = notification;
    }

    @Override
    public void send(String message) {
        wrapped.send(message);
    }
}

// Concrete decorators
class EmailDecorator extends NotificationDecorator {
    public EmailDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendEmail(message);
    }

    private void sendEmail(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SMSDecorator extends NotificationDecorator {
    public SMSDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSMS(message);
    }

    private void sendSMS(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class SlackDecorator extends NotificationDecorator {
    public SlackDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send(String message) {
        super.send(message);
        sendSlack(message);
    }

    private void sendSlack(String message) {
        System.out.println("Slack message sent: " + message);
    }
}

// Usage - Stack decorators
Notification notification = new BasicNotification();
notification = new EmailDecorator(notification);
notification = new SMSDecorator(notification);
notification = new SlackDecorator(notification);

notification.send("Server is down!");
// Output:
// Sending: Server is down!
// Email sent: Server is down!
// SMS sent: Server is down!
// Slack message sent: Server is down!

// Real-world example: Coffee shop
interface Coffee {
    double cost();
    String description();
}

class SimpleCoffee implements Coffee {
    @Override
    public double cost() { return 2.0; }
    
    @Override
    public String description() { return "Simple coffee"; }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }
    
    @Override
    public double cost() { return coffee.cost() + 0.5; }
    
    @Override
    public String description() { return coffee.description() + ", milk"; }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    
    @Override
    public double cost() { return coffee.cost() + 0.2; }
    
    @Override
    public String description() { return coffee.description() + ", sugar"; }
}

// Usage
Coffee coffee = new SimpleCoffee();
coffee = new MilkDecorator(coffee);
coffee = new SugarDecorator(coffee);
System.out.println(coffee.description() + " = $" + coffee.cost());
// Output: Simple coffee, milk, sugar = $2.7
```

**Key points:**
- Add functionality without modifying original class
- Stack multiple decorators for combined behavior
- Maintains same interface as base component
- More flexible than inheritance

---

## 39. Explain how Singleton pattern can be misused in a large-scale system.

Singleton can create **hidden dependencies**, make **testing difficult**, and cause **global state issues**. It's often overused when dependency injection would be better.

```java
// BAD: Singleton with global state
class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        // Initialize connection
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void executeQuery(String sql) {
        // Execute using shared connection
    }
}

// Problems:
// 1. Hidden dependency - hard to see what depends on it
class UserService {
    public void saveUser(User user) {
        // Hidden dependency on singleton
        DatabaseConnection.getInstance().executeQuery("INSERT...");
    }
}

// 2. Hard to test - can't mock
class UserServiceTest {
    @Test
    public void testSaveUser() {
        // Can't inject mock database connection
        UserService service = new UserService();
        service.saveUser(new User());
    }
}

// 3. Thread safety issues
class Counter {
    private static Counter instance;
    private int count = 0;

    public static Counter getInstance() {
        if (instance == null) {
            instance = new Counter();
        }
        return instance;
    }

    public void increment() {
        count++; // Not thread-safe!
    }
}

// 4. Tight coupling
class OrderService {
    public void processOrder(Order order) {
        // Tightly coupled to singleton
        Logger.getInstance().log("Processing order");
        EmailService.getInstance().send("Order confirmation");
        CacheService.getInstance().invalidate("orders");
    }
}

// BETTER: Use Dependency Injection
class UserService {
    private final DatabaseConnection connection;

    public UserService(DatabaseConnection connection) {
        this.connection = connection;
    }

    public void saveUser(User user) {
        connection.executeQuery("INSERT...");
    }
}

// Easy to test with mock
class UserServiceTest {
    @Test
    public void testSaveUser() {
        DatabaseConnection mockConnection = mock(DatabaseConnection.class);
        UserService service = new UserService(mockConnection);
        service.saveUser(new User());
        verify(mockConnection).executeQuery(anyString());
    }
}

// BETTER: Spring dependency injection
@Service
class OrderService {
    private final Logger logger;
    private final EmailService emailService;
    private final CacheService cacheService;

    @Autowired
    public OrderService(Logger logger, EmailService emailService, CacheService cacheService) {
        this.logger = logger;
        this.emailService = emailService;
        this.cacheService = cacheService;
    }
}
```

**Singleton misuse problems:**
- **Hidden dependencies**: Hard to see what class depends on
- **Testing difficulties**: Can't inject mocks
- **Global state**: Shared mutable state causes bugs
- **Tight coupling**: Hard to change or replace
- **Concurrency issues**: Shared state in multi-threaded environment

**When Singleton is acceptable:**
- Configuration objects (immutable)
- Logging (stateless)
- Thread pools (managed carefully)

**Key points:**
- Prefer dependency injection over Singleton
- Use Singleton sparingly for truly global resources
- Make Singletons immutable when possible
- Consider using Spring's @Bean with singleton scope instead

---

## 40. How would you combine Strategy and Template patterns in a payment processing system?

I'd use **Template pattern** to define the payment flow structure and **Strategy pattern** to vary the payment method. Template handles the process, Strategy handles the implementation.

```java
// Strategy pattern - Payment methods
interface PaymentStrategy {
    boolean validatePayment();
    void executePayment(double amount);
}

class CreditCardStrategy implements PaymentStrategy {
    private String cardNumber;

    public CreditCardStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean validatePayment() {
        System.out.println("Validating credit card: " + cardNumber);
        return cardNumber.length() == 16;
    }

    @Override
    public void executePayment(double amount) {
        System.out.println("Charging credit card: $" + amount);
    }
}

class PayPalStrategy implements PaymentStrategy {
    private String email;

    public PayPalStrategy(String email) {
        this.email = email;
    }

    @Override
    public boolean validatePayment() {
        System.out.println("Validating PayPal account: " + email);
        return email.contains("@");
    }

    @Override
    public void executePayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
    }
}

// Template pattern - Payment process
abstract class PaymentProcessor {
    // Template method - defines the algorithm structure
    public final boolean processPayment(double amount) {
        if (!checkInventory()) {
            return false;
        }

        if (!validatePayment()) {
            return false;
        }

        executePayment(amount);
        sendConfirmation();
        updateInventory();

        return true;
    }

    // Common steps
    private boolean checkInventory() {
        System.out.println("Checking inventory...");
        return true;
    }

    private void sendConfirmation() {
        System.out.println("Sending confirmation email...");
    }

    private void updateInventory() {
        System.out.println("Updating inventory...");
    }

    // Abstract methods - subclasses implement
    protected abstract boolean validatePayment();
    protected abstract void executePayment(double amount);
}

// Combining both patterns
class StrategyBasedPaymentProcessor extends PaymentProcessor {
    private PaymentStrategy strategy;

    public StrategyBasedPaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    protected boolean validatePayment() {
        return strategy.validatePayment();
    }

    @Override
    protected void executePayment(double amount) {
        strategy.executePayment(amount);
    }
}

// Usage
PaymentStrategy creditCard = new CreditCardStrategy("1234567890123456");
PaymentProcessor processor = new StrategyBasedPaymentProcessor(creditCard);
processor.processPayment(100.0);

// Switch strategy at runtime
PaymentStrategy paypal = new PayPalStrategy("user@example.com");
((StrategyBasedPaymentProcessor) processor).setStrategy(paypal);
processor.processPayment(50.0);

// Real-world example: Order processing with discounts
abstract class OrderProcessor {
    public final double processOrder(Order order) {
        validateOrder(order);
        double subtotal = calculateSubtotal(order);
        double discount = applyDiscount(subtotal); // Strategy
        double tax = calculateTax(subtotal - discount);
        double total = subtotal - discount + tax;
        processPayment(total);
        return total;
    }

    private void validateOrder(Order order) {
        System.out.println("Validating order...");
    }

    private double calculateSubtotal(Order order) {
        return order.getItems().stream()
            .mapToDouble(Item::getPrice)
            .sum();
    }

    private double calculateTax(double amount) {
        return amount * 0.1;
    }

    private void processPayment(double amount) {
        System.out.println("Processing payment: $" + amount);
    }

    // Strategy method - varies by customer type
    protected abstract double applyDiscount(double amount);
}

class RegularCustomerProcessor extends OrderProcessor {
    @Override
    protected double applyDiscount(double amount) {
        return amount * 0.05; // 5% discount
    }
}

class PremiumCustomerProcessor extends OrderProcessor {
    @Override
    protected double applyDiscount(double amount) {
        return amount * 0.15; // 15% discount
    }
}
```

**Key benefits of combining patterns:**
- **Template**: Defines consistent process flow
- **Strategy**: Allows flexible payment methods
- **Together**: Fixed structure with variable behavior
- **Extensibility**: Easy to add new payment methods
- **Maintainability**: Process logic in one place

**Key points:**
- Template controls the algorithm structure
- Strategy provides interchangeable implementations
- Combination gives both consistency and flexibility
- Common in frameworks (Spring, Hibernate)
