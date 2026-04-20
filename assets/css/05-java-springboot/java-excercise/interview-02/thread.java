import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

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
// 2. Using lambda expression (Java 8+)
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
// 3. Synchronization
// ============================================================
class SynchronizationExample {
    public static void main(String[] args) throws Exception {
        BankAccount acc = new BankAccount();

        Thread t1 = new Thread(() -> acc.deposit(1000));
        Thread t2 = new Thread(() -> acc.deposit(1000));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(acc.getBalance());
    }
}

class BankAccount {
    private int balance = 1000;

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}

// ============================================================
// 4. ConcurrentHashMap
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

// ============================================================
// 5. Sleep
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
// 5. wait
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
// 5. wait/notify
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
// 6. ExecutorService
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

// ============================================================
// 7. CompletableFuture
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

// ============================================================
// 8. ReentrantLock
// ============================================================
class ReentrantLockExample {
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

// ============================================================
// 9. Volatile
// ============================================================
class VolatileExample {
    public static void main(String[] args) throws Exception {
        Flag flag = new Flag();

        new Thread(() -> {
            while (flag.running) {}
            System.out.println("Stopped");
        }).start();

        Thread.sleep(1000);
        flag.running = false;
    }
}

class Flag {
    volatile boolean running = true;
}

// ============================================================
// 10. Race Condition
// ============================================================
class RaceConditionExample {
    public static void main(String[] args) throws Exception {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> counter.increment());
        Thread t2 = new Thread(() -> counter.increment());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.count);
    }
}

class Counter {
    int count = 0;

    void increment() {
        count++; // not thread-safe — intentional demo
    }
}

// ============================================================
// 11. LRU Cache
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
