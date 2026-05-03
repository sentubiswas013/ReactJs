import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;

// class MyThread {
//     public static void main(String[] args) {
//         System.out.println("Hello");
//     }
// }

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

// ============================================================
// 4. Synchronization prevents multiple threads from accessing shared resources simultaneously, ensuring thread safety
// ============================================================
// Real-world Example: Bank account withdrawal where multiple users try to withdraw money at the same time, leading to inconsistent balance if not synchronized.
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
// AtomicInteger provides thread-safe operations without synchronization - useful for counters in concurrent programming
// ============================================================
// Real-world Example: Counting the number of requests handled by a server in a multi-threaded environment without using synchronized blocks.
// import java.util.concurrent.atomic.AtomicInteger;
class AtomicIntegerExp {
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
// Sleep pauses thread execution for specified time but keeps locks (can cause blocking)// 
// ============================================================
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
// Real-world Example: Counting word frequency in logs using ConcurrentHashMap
// import java.util.concurrent.*;

// import java.util.concurrent.ConcurrentHashMap;
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
// ExecutorService: A thread pool manager that handles task execution without manually creating/managing threads. Provides submit(), execute(), and shutdown() methods for concurrent task processing.
// ============================================================
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
// Real-world Example: Calling multiple APIs in parallel and combining results
// import java.util.concurrent.CompletableFuture;
class CompletableFutureRealTimeExample {

    public static void main(String[] args) {
        // Call APIs in parallel
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> getUser());
        CompletableFuture<String> orderFuture = CompletableFuture.supplyAsync(() -> getOrders());
        CompletableFuture<String> paymentFuture = CompletableFuture.supplyAsync(() -> getPayments());

        // Combine all results
        CompletableFuture<String> finalResult = userFuture.thenCombine(
                    orderFuture, (user, orders) ->
                        user + " | " + orders
                    )
                    .thenCombine(paymentFuture, (combined, payment) -> combined + " | " + payment);

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
// Real-world Example: Booking system where multiple users try to book the last seat simultaneously
class RaceConditionExample {
    public static void main(String[] args) throws Exception {
        TicketBooking booking = new TicketBooking();

        Thread t1 = new Thread(() -> booking.bookTicket(), "User-1 ");
        Thread t2 = new Thread(() -> booking.bookTicket(), "User-2 ");

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
// **** Calling external APIs (payment/user service) is expensive → cache response.

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

