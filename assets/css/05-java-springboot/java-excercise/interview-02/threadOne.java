import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;

// ============================================================
// 1. Using Thread class
// ============================================================
class ThreadClassExample {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();

        MyThread t2 = new MyThread();
        t2.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

// ============================================================
// 2. Using Runnable interface
// ============================================================
class RunnableExample {
     public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyTask());
        t1.start();
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable thread: " + Thread.currentThread().getName());
    }
}

// ============================================================
// 3. Using lambda expression (Java 8+)
// ============================================================
class LambdaThreadExample1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Lambda thread: " + Thread.currentThread().getName());
        });
        t1.start();
        t1.join(); // wait for thread to finish
    }
}

// This is more concise and commonly used in modern Java code. You can also reuse the same lambda for multiple threads:
class LambdaThreadExample2 {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Running: " + Thread.currentThread().getName());
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}

// Loop to create multiple threads
class threadLoop {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            int threadNum = i; // need final or effectively final variable
            new Thread(() -> {
                System.out.println("Thread " + threadNum + " is running");
            }).start();
        }
    }
}

class threadRunnableLoop{
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            int threadNum = i; // need final or effectively final variable
            Runnable task = () -> {
                System.out.println("Thread Runnable " + threadNum + " is running");
            };
            new Thread(task).start();
        }
    }
     
}

// ============================================================
// 4. Race Condition and Synchronization 
// Synchronization :: prevents multiple threads from accessing shared resources simultaneously,  thread safety
// Use Case: ticket booking system where multiple users try to book the last seat simultaneously
// ============================================================
// Race Condition :: Two threads trying to withdraw money from the same bank account at the same time, leading to incorrect balance updates.
class RaceConditionExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Thread user1 = new Thread(() -> account.withdraw(700), "User1");
        Thread user2 = new Thread(() -> account.withdraw(700), "User2");

        user1.start();
        user2.start();
    }
}

class BankAccount {
    private int balance = 1000;
    void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " trying to withdraw " + amount);

        if (balance >= amount) {
            // Simulate processing delay
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance = balance - amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal");
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName()
                    + " -> Insufficient balance");
        }
    }
}

// To Fix race condition, we can synchronize the method:
class BankAccountFixed1 {
    synchronized void withdraw(int amount) {}
}

class BankAccountFixed2 {
    Object lock = new Object();
    void withdraw(int amount) {
        synchronized(lock) {}
    }
}


// ============================================================
// 5. ReentrantLock :: is a class in Java (java.util.concurrent.locks) that provides an explicit and more flexible locking mechanism than synchronized.
// Real-world Example: Bank account withdrawal with explicit locking
// ============================================================
class ReentrantLockExampleTwo {
    public static void main(String[] args) {
        BankAccountExm account = new BankAccountExm();

        Runnable task1 = () -> account.withdraw("User-1", 700);
        Runnable task2 = () -> account.withdraw("User-2", 500);

        new Thread(task1).start();
        new Thread(task2).start();
    }
}

class BankAccountExm {
    private int balance = 1000;
    private final ReentrantLock lock = new ReentrantLock();

    // withdraw money safely
    void withdraw(String user, int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                System.out.println(user + " is withdrawing " + amount);
                Thread.sleep(500); // simulate delay
                balance -= amount;
                System.out.println(user + " completed withdrawal. Remaining: " + balance);
            } else {
                System.out.println(user + " insufficient balance!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock(); // always release
        }
    }
}


// Output:
// User-1 booked seat
// User-2 booked seat   ❌ (wrong – only 1 seat!)
// User-1 booked seat


// ============================================================
// 6. AtomicInteger:: provides thread-safe operations without synchronization - useful for counters in concurrent programming
// Use Case: Counting the number of requests handled by a server in a multi-threaded environment without using synchronized blocks.
// Example: Ticket booking system where multiple users try to book the last seat simultaneously
// ============================================================
class AtomicIntegerExp {
    public static void main(String[] args) throws Exception {
        TicketBooking booking = new TicketBooking();

        Thread t1 = new Thread(() -> booking.bookTicket(), "User-1");
        Thread t2 = new Thread(() -> booking.bookTicket(), "User-2");

        t1.start();
        t2.start();

        t1.join();  // wait until thread completes
        t2.join();   // wait until thread completes
    }
}

class TicketBooking {
    private AtomicInteger seat = new AtomicInteger(1);
    public void bookTicket() {
        int remaining = seat.getAndDecrement();
        if (remaining > 0) {
            System.out.println(Thread.currentThread().getName() + " booked seat.");
        } else {
            System.out.println(Thread.currentThread().getName() + " no seat available.");
        }
    }
}


// ============================================================
// 7. Volatile:: ensures variable changes are immediately visible to all threads (prevents caching issues)
// Use Case: A flag to stop a thread gracefully from another thread without using synchronization.
// Example: A background task that should stop when a flag is set to false.
// ============================================================
// Real-world Example: A flag to stop a thread gracefully from another thread without using synchronization.
class VolatileExample {
    public static void main(String[] args) throws Exception {
        Task task = new Task();
        Thread worker = new Thread(task);

        worker.start();

        Thread.sleep(2000); // let it run for 2 sec

        task.stop(); // stop from main thread
        System.out.println("Stopped by main thread");
    }
}

class Task implements Runnable {
    private volatile boolean running = true;

    public void stop() {
        running = false;
    }

    public void run() {
        while (running) {
            System.out.println("Task running...");
        }
        System.out.println("Task stopped");
    }
}

// ============================================================
// 8. sleep() :: pauses the current thread for a specified time but does not release the lock.
// wait() :: pauses the thread and releases the lock, allowing other threads to execute. It must be used inside a synchronized block.
// Example: printer class where one thread is printing and sleeps in between, while another thread tries to print but has to wait for the lock to be released.
// ============================================================
class WaitSleepNotifyExample {
    public static void main(String[] args) {
        Printer printer = new Printer();

        Thread t1 = new Thread(() -> {
            printer.printDocuments();
        });

        Thread t2 = new Thread(() -> {
            printer.addPaper();
        });

        t1.start();
        t2.start();
    }
}

class Printer {
    boolean hasPaper = false;
    synchronized void printDocuments() {
        System.out.println("Waiting for paper...");
        while (!hasPaper) {
            try {
                wait(); // releases lock and waits
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Printing documents...");
    }

    synchronized void addPaper() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hasPaper = true;
        System.out.println("Paper added");
        notify(); // wake up waiting thread
    }
}

// Output:
// Waiting for paper...
// Paper added
// Printing documents...


// ============================================================
// 9. ConcurrentHashMap: is a thread-safe implementation of Map that allows multiple threads to read and write data concurrently without locking the entire map.
// Example: A web application that maintains a concurrent cache of user sessions using ConcurrentHashMap.
// ============================================================
// Real-world Example: Counting word frequency in logs using ConcurrentHashMap
class SessionManager {
    private static ConcurrentHashMap<String, UserSession> sessionCache = new ConcurrentHashMap<>();

    // Add session
    public static void login(String userId) {
        sessionCache.put(userId, new UserSession(userId));
    }

    // Get session
    public static UserSession getSession(String userId) {
        return sessionCache.get(userId);
    }

    // Remove session
    public static void logout(String userId) {
        sessionCache.remove(userId);
    }

    public static void main(String[] args) {
        login("user1");
        login("user2");

        System.out.println(getSession("user1").userId);

        logout("user1");
    }
}

class UserSession {
    String userId;
    long loginTime;

    UserSession(String userId) {
        this.userId = userId;
        this.loginTime = System.currentTimeMillis();
    }
}

// ============================================================
// 10. ExecutorService :: is a Java API to manage thread pools and execute tasks asynchronously, in the background.
// Example: A web server that uses ExecutorService to handle incoming HTTP requests concurrently without blocking the main thread.
// ============================================================
class ExecutorServiceRealTimeExample {
    public static void main(String[] args) {
        // Create thread pool with 3 threads
        ExecutorService executor = Executors.newSingleThreadExecutor(); // only 1 thread
        // ExecutorService executor = Executors.newFixedThreadPool(3); // 3 threads

        // Multiple orders
        for(int i = 0; i<=4; i++) {
            int orderId = i;

            executor.submit(() ->{
                System.out.println(Thread.currentThread().getName() + " Order " + orderId);
            });

            try {
                Thread.sleep(3000);
            } catch (Exception ex) {}

            System.out.println("Order completed " + orderId);
        }

        // Shutdown thread pool
        executor.shutdown();
    }
}

// ============================================================
// 11. CompletableFuture :: is used to run asynchronous tasks and combine multiple async operations.
// Example: A web application that retrieves user data from a database and then calls an external API to get additional information, all without blocking the main thread.
// ============================================================
// Real-world Example: Calling multiple APIs in parallel and combining results
class CompletableFutureRealTimeExample {
    public static void main(String[] args) {
        // Call APIs in parallel
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> getUser());
        CompletableFuture<String> orderFuture = CompletableFuture.supplyAsync(() -> getOrders());
        CompletableFuture<String> paymentFuture = CompletableFuture.supplyAsync(() -> getPayments());

        // Combine all results
        CompletableFuture<String> finalResult 
            = userFuture.thenCombine(orderFuture, (user, orders) -> user + " | " + orders )
            .thenCombine(paymentFuture, (combined, payment) -> combined + " | " + payment);

        // Wait and print result
        System.out.println(finalResult.join());  // wait until thread completes
    }

    static String getUser() {
        sleep(2);
        return "User: John";
    }

    static String getOrders() {
        sleep(3);
        return "Orders: 5";
    }

    static String getPayments() {
        sleep(1);
        return "Payments: Done";
    }

    static void sleep(int seconds) {
        try { Thread.sleep(seconds * 1000); } catch (Exception e) {}
    }
}

// ============================================================
// 12. LRU Cache
// ============================================================
// Calling external APIs (payment/user service) is expensive → cache response.

class LRUCacheExample {
    public static void main(String[] args) {

        ApiService api = new ApiService();
        api.fetchData("user/1");
        api.fetchData("user/2");
        api.fetchData("user/1"); // cache hit
        api.fetchData("user/3"); // evicts LRU

        System.out.println("-----------");

        ProductService product = new ProductService();
        product.getProduct(1);
        product.getProduct(2);
        product.getProduct(1); // cache hit
        product.getProduct(3); // evicts LRU

        System.out.println("-----------");

        SessionService session = new SessionService();
        session.login("user1");
        session.login("user2");
        session.login("user3"); // evicts user1

        System.out.println("user1 active? " + session.isActive("user1")); // false
        System.out.println("user2 active? " + session.isActive("user2")); // true
    }
}

// 1. Generic LRU Cache
// LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // access-order = true
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

// 2. API Cache Example
class ApiService {
    private final LRUCache<String, String> cache = new LRUCache<>(2);

    public String fetchData(String url) {
        if (cache.containsKey(url)) {
            System.out.println("Cache HIT: " + url);
            return cache.get(url); // updates LRU
        }

        System.out.println("Calling API: " + url);
        String response = "Response from " + url;

        cache.put(url, response);
        return response;
    }
}

// 3. Product Cache (DB Example)
class ProductService {
    private final LRUCache<Integer, String> cache = new LRUCache<>(2);

    public String getProduct(int id) {
        if (cache.containsKey(id)) {
            System.out.println("Cache HIT: Product " + id);
            return cache.get(id);
        }

        System.out.println("Fetching from DB: " + id);
        String product = "Product-" + id;

        cache.put(id, product);
        return product;
    }
}

// 4. Session Cache
class SessionService {
    private final LRUCache<String, String> cache = new LRUCache<>(2);

    public void login(String userId) {
        cache.put(userId, "ACTIVE");
        System.out.println("User logged in: " + userId);
    }

    public boolean isActive(String userId) {
        return cache.get(userId) != null; // important: use get()
    }
}
