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
    public static void main(String[] args) {
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
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Lambda thread: " + Thread.currentThread().getName());
        });
        t1.start();
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

// ============================================================
// 4. Synchronization prevents multiple threads from accessing shared resources simultaneously, ensuring thread safety
// ============================================================
// Main Class (Simulation)
class SynchronizationExample {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        Thread user1 = new Thread(() -> account.withdraw(700), "User1");
        Thread user2 = new Thread(() -> account.withdraw(700), "User2");

        user1.start();
        user2.start();
    }
}
class BankAccount { 
    int balance = 1000;

    synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(
                Thread.currentThread().getName() + " withdrawing..."
            );
            balance = balance - amount;
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}


// ============================================================
// 5. Volatile ensures variable changes are immediately visible to all threads (prevents caching issues)
// ============================================================
class VolatileExp {
    public static void main(String[] args) throws Exception {
        Task task = new Task();
        Thread t1 = new Thread(task, "Worker-Thread");
        t1.start();

        Thread.sleep(3000);

        task.stop(); // proper stop method
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
            System.out.println("Task is running...");
            try {
                Thread.sleep(500); // prevent CPU overuse
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Task stopped");
    }
}



// ============================================================
// AtomicInteger provides thread-safe operations without synchronization - useful for counters in concurrent programming
// ============================================================
class AtomicExample {

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger count = new AtomicInteger(0);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Count: " + count.get());
    }
}


// ============================================================
// Sleep pauses thread execution for specified time but keeps locks (can cause blocking)// ============================================================
class SleepExample {
    public static void main(String[] args) {

        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1 acquired lock");
                try {
                    Thread.sleep(3000); // sleeping but STILL holding lock
                } catch (InterruptedException e) {}
                System.out.println("Thread 1 finished");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 acquired lock");
            }
        });

        t1.start();
        t2.start();
    }
}

// ============================================================
// 8. wait
// ============================================================
class WaitExample {
    public static void main(String[] args) {

        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 1 waiting...");
                    lock.wait(); // releases lock
                    System.out.println("Thread 1 resumed");
                } catch (InterruptedException e) {}
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 acquired lock");
                lock.notify(); // wakes up t1
                System.out.println("Thread 2 notified");
            }
        });

        t1.start();
        t2.start();
    }
}


// ============================================================
// 9. wait/notify
// ============================================================
class WaitNotifyExample {
    public static void main(String[] args) throws Exception {
        Message msg = new Message();

        new Thread(() -> {
            try { msg.send("Hello"); } catch (Exception e) {}
        }).start();

        new Thread(() -> {
            try { System.out.println(msg.receive()); } catch (Exception e) {}
        }).start();
    }
}

class Message {
    private String data;
    private boolean available = false;

    public synchronized void send(String msg) throws InterruptedException {
        if (available) wait();
        data = msg;
        available = true;
        notify();
    }

    public synchronized String receive() throws InterruptedException {
        if (!available) wait();
        available = false;
        notify();
        return data;
    }
}

// ============================================================
// ConcurrentHashMap: Thread-safe HashMap that allows multiple threads to read/write simultaneously without external synchronization
// ============================================================
class ConcurrentMapExample {
    static void run() throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        Thread t1 = new Thread(() -> map.put("A", 1000));
        Thread t2 = new Thread(() -> map.put("B", 2000));
        Thread t3 = new Thread(() -> map.put("C", 3000));

        t1.start(); t2.start(); t3.start();
        t1.join();  t2.join();  t3.join();

        System.out.println("ConcurrentHashMap: " + map);
    }

    public static void main(String[] args) throws InterruptedException {
        run();
    }
}


// Real-world Example: Counting word frequency in logs using ConcurrentHashMap
// import java.util.concurrent.*;

// import java.util.concurrent.ConcurrentHashMap;

class UserSession {
    String userId;
    long loginTime;

    UserSession(String userId) {
        this.userId = userId;
        this.loginTime = System.currentTimeMillis();
    }
}

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

// ============================================================
// ExecutorService: A thread pool manager that handles task execution without manually creating/managing threads. Provides submit(), execute(), and shutdown() methods for concurrent task processing.
// ============================================================
class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> System.out.println("Task 1: " + Thread.currentThread().getName()));
        executor.submit(() -> System.out.println("Task 2: " + Thread.currentThread().getName()));
        executor.execute(() -> System.out.println("Task 3"));

        executor.shutdown();
    }
}

// Real-world Example: Processing multiple orders in parallel using ExecutorService
// import java.util.concurrent.*;
class ExecutorServiceRealTimeExample {

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Callable<String> order1 = () -> processOrder("Order-1");
        Callable<String> order2 = () -> processOrder("Order-2");
        Callable<String> order3 = () -> processOrder("Order-3");

        Future<String> f1 = executor.submit(order1);
        Future<String> f2 = executor.submit(order2);
        Future<String> f3 = executor.submit(order3);

        // Get results
        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());

        executor.shutdown();
    }

    static String processOrder(String orderId) {
        try {
            System.out.println("Processing " + orderId + " by " + Thread.currentThread().getName());
            Thread.sleep(2000); // simulate work
        } catch (Exception e) {}

        return orderId + " processed";
    }
}

// ============================================================
// 12. CompletableFuture is a powerful class in Java that allows you to write asynchronous, non-blocking code. It provides a way to handle the result of an asynchronous computation and chain multiple computations together.
// ============================================================
class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture<String> future =
            CompletableFuture.supplyAsync(() -> {
                try { Thread.sleep(2000); } catch (Exception e) {}
                return "Hello";
            }).thenApply(s -> s + " World");

        System.out.println(future.join());
    }
}

// Real-world Example: Calling multiple APIs in parallel and combining results
// import java.util.concurrent.CompletableFuture;
class CompletableFutureRealTimeExample {

    public static void main(String[] args) {

        // Call APIs in parallel
        CompletableFuture<String> userFuture =
                CompletableFuture.supplyAsync(() -> getUser());

        CompletableFuture<String> orderFuture =
                CompletableFuture.supplyAsync(() -> getOrders());

        CompletableFuture<String> paymentFuture =
                CompletableFuture.supplyAsync(() -> getPayments());

        // Combine all results
        CompletableFuture<String> finalResult =
                userFuture.thenCombine(orderFuture, (user, orders) ->
                        user + " | " + orders)
                .thenCombine(paymentFuture, (combined, payment) ->
                        combined + " | " + payment);

        // Wait and print result
        System.out.println(finalResult.join());
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
// 13. ReentrantLock
// ============================================================
class ReentrantLockExampleOne {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        lock.lock();
        try {
            System.out.println("Task inside lock");
        } finally {
            lock.unlock();
        }
    }
}

//  Realtime Example:
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
        lock.lock(); // critical section
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


// ============================================================
// 15. Race Condition
// ============================================================
class Counter {
    int count = 0;

    void increment() {
        count++; // not thread-safe — intentional demo
    }
}
// Real-world Example: Booking system where multiple users try to book the last seat simultaneously
class RaceConditionExample {
    public static void main(String[] args) throws Exception {
        TicketBooking booking = new TicketBooking();

        Thread t1 = new Thread(() -> booking.bookTicket(), "User-1");
        Thread t2 = new Thread(() -> booking.bookTicket(), "User-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

class TicketBooking {
    int seats = 1;

    void bookTicket() {
        if (seats > 0) {
            System.out.println(Thread.currentThread().getName() + " booked seat");
            seats--;
        } else {
            System.out.println(Thread.currentThread().getName() + " → No seats available");
        }
    }
}

// To Fix race condition, we can synchronize the method:
class TicketBooking2 {
    int seats = 1;

    synchronized void bookTicket() {
        if (seats > 0) {
            System.out.println(Thread.currentThread().getName() + " booked seat");
            seats--;
        } else {
            System.out.println(Thread.currentThread().getName() + " → No seats available");
        }
    }
}

// Output:
// User-1 booked seat
// User-2 booked seat   ❌ (wrong – only 1 seat!)


// ============================================================
// 16. LRU Cache
// ============================================================
class LRUCacheExample {
    public static void main(String[] args) {
        run();
    }

    static void run() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);

        cache.put(1, "One");
        cache.put(2, "Two");

        cache.get(1);            // Access 1 → makes it MRU
        cache.put(3, "Three");   // Evicts key 2

        System.out.println("LRU Cache: " + cache);
    }
}

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    LRUCache(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

// Real-world Example: Caching DB results, API responses, etc.

// Fetching user data from DB is slow → cache recent results.
class DatabaseService {
    private final LRUCache<Integer, String> cache = new LRUCache<>(3);

    public String getUserById(int id) {
        if (cache.containsKey(id)) {
            System.out.println("Cache HIT for user " + id);
            return cache.get(id);
        }

        // Simulate DB call
        System.out.println("Fetching from DB for user " + id);
        String data = "User-" + id;

        cache.put(id, data);
        return data;
    }

    static void main(String[] args) {
        DatabaseService service = new DatabaseService();

        service.getUserById(1);
        service.getUserById(2);
        service.getUserById(1); // Cache hit
        service.getUserById(3);
        service.getUserById(4); // Evicts least used
        service.getUserById(2); // DB call again
    }
}

// Calling external APIs (payment/user service) is expensive → cache response.
class ApiService {
    private final LRUCache<String, String> cache = new LRUCache<>(2);

    public String fetchData(String url) {
        if (cache.containsKey(url)) {
            System.out.println("Cache HIT for API: " + url);
            return cache.get(url);
        }

        // Simulate API call
        System.out.println("Calling API: " + url);
        String response = "Response from " + url;

        cache.put(url, response);
        return response;
    }

    public static void main(String[] args) {
        ApiService api = new ApiService();

        api.fetchData("user/1");
        api.fetchData("user/2");
        api.fetchData("user/1"); // Cache hit
        api.fetchData("user/3"); // Evicts least used
    }
}

// Frequently viewed products → cache to reduce DB load.
class ProductService {
    private final LRUCache<Integer, String> cache = new LRUCache<>(2);

    public String getProduct(int id) {
        if (cache.containsKey(id)) {
            System.out.println("Cache HIT for product " + id);
            return cache.get(id);
        }

        // Simulate DB call
        System.out.println("Fetching product from DB: " + id);
        String product = "Product-" + id;

        cache.put(id, product);
        return product;
    }
}

// Session Cache: Store recently active user sessions.
class SessionService {
    private final LRUCache<String, String> sessionCache = new LRUCache<>(2);

    public void login(String userId) {
        sessionCache.put(userId, "ACTIVE");
        System.out.println("User logged in: " + userId);
    }

    public boolean isActive(String userId) {
        return sessionCache.containsKey(userId);
    }
}
