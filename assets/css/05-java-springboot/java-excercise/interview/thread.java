import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class Main {

    public static void main(String[] args) throws Exception {

        concurrentHashMapDemo();
        lruCacheDemo();
        producerConsumerDemo();
        threadClassDemo();
        runnableDemo();
        synchronizationDemo();
        waitNotifyDemo();
        threadPoolDemo();

        // ✅ Newly Added Important Topics
        raceConditionDemo();
        callableFutureDemo();
        completableFutureDemo();
        reentrantLockDemo();
        volatileDemo();
    }

    // ─────────────────────────────────────────────
    // ConcurrentHashMap
    // ─────────────────────────────────────────────
    static void concurrentHashMapDemo() throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        Thread t1 = new Thread(() -> map.put("A", 1));
        Thread t2 = new Thread(() -> map.put("B", 2));

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("ConcurrentHashMap: " + map);
    }

    // ─────────────────────────────────────────────
    // LRU Cache
    // ─────────────────────────────────────────────
    static void lruCacheDemo() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.get(1);
        cache.put(3, "Three"); // removes 2

        System.out.println("LRU Cache: " + cache.keySet());
    }

    // ─────────────────────────────────────────────
    // Producer-Consumer
    // ─────────────────────────────────────────────
    static void producerConsumerDemo() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try { queue.put(1); } catch (Exception ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try { System.out.println("Consumed: " + queue.take()); } catch (Exception ignored) {}
        });

        producer.start(); consumer.start();
        producer.join();  consumer.join();
    }


    // ─────────────────────────────────────────────
    // Thread Class
    // ─────────────────────────────────────────────
    static void threadClassDemo() throws InterruptedException {
        MyThread t = new MyThread();
        t.start();
        t.join();
    }

    // ─────────────────────────────────────────────
    // Runnable
    // ─────────────────────────────────────────────
    static void runnableDemo() throws InterruptedException {
        Thread t = new Thread(() ->
                System.out.println("Runnable: " + Thread.currentThread().getName())
        );
        t.start();
        t.join();
    }

    // ─────────────────────────────────────────────
    // Synchronization
    // ─────────────────────────────────────────────
    static void synchronizationDemo() throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> counter.increment());
        Thread t2 = new Thread(() -> counter.increment());

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Sync Counter: " + counter.count);
    }

    // ─────────────────────────────────────────────
    // wait/notify
    // ─────────────────────────────────────────────
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

    // ─────────────────────────────────────────────
    // Thread Pool
    // ─────────────────────────────────────────────
    static void threadPoolDemo() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(() ->
                System.out.println("Task by " + Thread.currentThread().getName())
        );

        pool.shutdown();
        pool.awaitTermination(2, TimeUnit.SECONDS);
    }

    // ============================================================
    // 🔥 NEW IMPORTANT TOPICS
    // ============================================================

    // ─────────────────────────────────────────────
    // Race Condition (Problem + Fix)
    // ─────────────────────────────────────────────
    static void raceConditionDemo() throws InterruptedException {
        UnsafeCounter counter = new UnsafeCounter();

        Thread t1 = new Thread(counter::increment);
        Thread t2 = new Thread(counter::increment);

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Race Condition Count: " + counter.count);
    }

    // ─────────────────────────────────────────────
    // Callable + Future
    // ─────────────────────────────────────────────
    static void callableFutureDemo() throws Exception {
        ExecutorService ex = Executors.newSingleThreadExecutor();

        Future<Integer> result = ex.submit(() -> 10 + 20);

        System.out.println("Future Result: " + result.get());

        ex.shutdown();
    }

    // ─────────────────────────────────────────────
    // CompletableFuture
    // ─────────────────────────────────────────────
    static void completableFutureDemo() {
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "Async Task");

        System.out.println("CompletableFuture: " + future.join());
    }

    // ─────────────────────────────────────────────
    // ReentrantLock
    // ─────────────────────────────────────────────
    static void reentrantLockDemo() {
        Lock lock = new ReentrantLock();

        lock.lock();
        try {
            System.out.println("Inside Lock");
        } finally {
            lock.unlock();
        }
    }

    // ─────────────────────────────────────────────
    // volatile
    // ─────────────────────────────────────────────
    static void volatileDemo() throws InterruptedException {
        Flag flag = new Flag();

        Thread t = new Thread(() -> {
            while (flag.running) { }
            System.out.println("Stopped");
        });

        t.start();
        Thread.sleep(100);
        flag.running = false;
    }
}


// ─────────────────────────────────────────────
// Helper Classes
// ─────────────────────────────────────────────

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


class MyThread extends Thread {
    public void run() {
        System.out.println("Thread Class: " + getName());
    }
}

class Counter {
    int count = 0;
    synchronized void increment() { count++; }
}

class UnsafeCounter {
    int count = 0;
    void increment() { count++; } // no sync → race condition
}

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

class Flag {
    volatile boolean running = true;
}

// Quick Explanation Template ======
// ConcurrentHashMap → Thread-safe without full locking
// LRU Cache → Eviction using LinkedHashMap
// Producer-Consumer → BlockingQueue handles sync
// Deadlock → Circular locking problem
// Singleton → One instance using double-check locking
// Thread vs Runnable → Runnable preferred
// Synchronization → Prevent race condition
// wait/notify → Thread communication
// ThreadPool → Efficient thread reuse