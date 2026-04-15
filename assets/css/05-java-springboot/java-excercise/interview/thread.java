import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

// ============================================================
// 🔥 JAVA CONCURRENCY - INTERVIEW MASTER FILE
// Covers: Basics → Advanced → Real-world patterns
// ============================================================

class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("===== BASIC CONCURRENCY =====");
        threadVsRunnable();
        synchronizationDemo();

        System.out.println("\n===== COLLECTIONS =====");
        concurrentHashMapDemo();
        lruCacheDemo();

        System.out.println("\n===== THREAD COMMUNICATION =====");
        producerConsumerDemo();
        waitNotifyDemo();

        System.out.println("\n===== EXECUTORS =====");
        threadPoolDemo();
        callableFutureDemo();
        completableFutureDemo();

        System.out.println("\n===== LOCKS & VISIBILITY =====");
        reentrantLockDemo();
        volatileDemo();

        System.out.println("\n===== CRITICAL INTERVIEW TOPIC =====");
        raceConditionDemo();
    }

    // ============================================================
    // 1. Thread vs Runnable
    // ============================================================
    static void threadVsRunnable() throws InterruptedException {

        // ❌ Extending Thread (less flexible)
        Thread t1 = new MyThread();

        // ✅ Runnable (recommended)
        Thread t2 = new Thread(() ->
                System.out.println("Runnable: " + Thread.currentThread().getName())
        );

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    // ============================================================
    // 2. Synchronization (Fix Race Condition)
    // ============================================================
    static void synchronizationDemo() throws InterruptedException {
        SafeCounter counter = new SafeCounter();

        Thread t1 = new Thread(counter::increment);
        Thread t2 = new Thread(counter::increment);

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Safe Counter: " + counter.count);
    }

    // ============================================================
    // 3. ConcurrentHashMap
    // ============================================================
    static void concurrentHashMapDemo() throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        Thread t1 = new Thread(() -> map.put("A", 1));
        Thread t2 = new Thread(() -> map.put("B", 2));

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("ConcurrentHashMap: " + map);
    }

    // ============================================================
    // 4. LRU Cache (Frequently Asked)
    // ============================================================
    static void lruCacheDemo() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.get(1);        // makes 1 most recently used
        cache.put(3, "Three"); // removes 2

        System.out.println("LRU Cache: " + cache.keySet());
    }

    // ============================================================
    // 5. Producer-Consumer (BlockingQueue - BEST APPROACH)
    // ============================================================
    static void producerConsumerDemo() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                queue.put(1);
                System.out.println("Produced");
            } catch (Exception ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                System.out.println("Consumed: " + queue.take());
            } catch (Exception ignored) {}
        });

        producer.start(); consumer.start();
        producer.join();  consumer.join();
    }

    // ============================================================
    // 6. wait/notify (Low-level sync)
    // ============================================================
    static void waitNotifyDemo() throws InterruptedException {
        Message msg = new Message();

        Thread sender = new Thread(() -> {
            try { msg.send("Hello"); } catch (Exception ignored) {}
        });

        Thread receiver = new Thread(() -> {
            try { System.out.println("Received: " + msg.receive()); } catch (Exception ignored) {}
        });

        sender.start(); receiver.start();
        sender.join();  receiver.join();
    }

    // ============================================================
    // 7. Thread Pool (ExecutorService)
    // ============================================================
    static void threadPoolDemo() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(() ->
                System.out.println("Task by " + Thread.currentThread().getName())
        );

        pool.shutdown();
        pool.awaitTermination(2, TimeUnit.SECONDS);
    }

    // ============================================================
    // 8. Callable + Future
    // ============================================================
    static void callableFutureDemo() throws Exception {
        ExecutorService ex = Executors.newSingleThreadExecutor();

        Future<Integer> result = ex.submit(() -> 10 + 20);

        System.out.println("Future Result: " + result.get());

        ex.shutdown();
    }

    // ============================================================
    // 9. CompletableFuture (Async chaining)
    // ============================================================
    static void completableFutureDemo() {
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "Task")
                        .thenApply(s -> s + " Processed");

        System.out.println("CompletableFuture: " + future.join());
    }

    // ============================================================
    // 10. ReentrantLock (Advanced locking)
    // ============================================================
    static void reentrantLockDemo() {
        Lock lock = new ReentrantLock();

        lock.lock();
        try {
            System.out.println("Inside Lock");
        } finally {
            lock.unlock();
        }
    }

    // ============================================================
    // 11. volatile (Visibility guarantee)
    // ============================================================
    static void volatileDemo() throws InterruptedException {
        Flag flag = new Flag();

        Thread t = new Thread(() -> {
            while (flag.running) {}
            System.out.println("Stopped Thread");
        });

        t.start();
        Thread.sleep(100);
        flag.running = false;
    }

    // ============================================================
    // 12. Race Condition (Problem Demo)
    // ============================================================
    static void raceConditionDemo() throws InterruptedException {
        UnsafeCounter counter = new UnsafeCounter();

        Thread t1 = new Thread(counter::increment);
        Thread t2 = new Thread(counter::increment);

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Race Condition Count (Wrong): " + counter.count);
    }
}


// ============================================================
// 🔧 HELPER CLASSES
// ============================================================

// Thread class
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread: " + getName());
    }
}

// Safe counter (synchronized)
class SafeCounter {
    int count = 0;
    synchronized void increment() { count++; }
}

// Unsafe counter (race condition)
class UnsafeCounter {
    int count = 0;
    void increment() { count++; }
}

// LRU Cache
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

// wait/notify example
class Message {
    private String message;
    private boolean hasMessage = false;

    synchronized void send(String msg) throws InterruptedException {
        while (hasMessage) wait();
        message = msg;
        hasMessage = true;
        notify();
    }

    synchronized String receive() throws InterruptedException {
        while (!hasMessage) wait();
        hasMessage = false;
        notify();
        return message;
    }
}

// volatile example
class Flag {
    volatile boolean running = true;
}