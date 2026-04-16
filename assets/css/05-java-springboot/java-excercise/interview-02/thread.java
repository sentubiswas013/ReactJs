import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

// ============================================================
// 🚀 MAIN CLASS (Entry Point)
// ============================================================
class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("===== BASIC =====");
        ThreadExample.run();
        SynchronizationExample.run();

        System.out.println("\n===== COLLECTIONS =====");
        ConcurrentMapExample.run();
        LRUCacheExample.run();

        System.out.println("\n===== COMMUNICATION =====");
        ProducerConsumerExample.run();
        WaitNotifyExample.run();

        System.out.println("\n===== EXECUTORS =====");
        ThreadPoolExample.run();
        FutureExample.run();
        CompletableFutureExample.run();

        System.out.println("\n===== LOCKS =====");
        ReentrantLockExample.run();
        VolatileExample.run();

        System.out.println("\n===== RACE CONDITION =====");
        RaceConditionExample.run();
    }
}


// ============================================================
// 1. Thread vs Runnable
// ============================================================
class ThreadExample {
    static void run() throws InterruptedException {

        Thread t1 = new MyThread();

        Thread t2 = new Thread(() ->
                System.out.println("Runnable: " + Thread.currentThread().getName())
        );

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

class MyThread extends Thread {
    public void run() {
        System.out.println("Thread: " + getName());
    }
}


// ============================================================
// 2. Synchronization
// ============================================================
class SynchronizationExample {
    static void run() throws InterruptedException {
        SafeCounter counter = new SafeCounter();

        Thread t1 = new Thread(counter::increment);
        Thread t2 = new Thread(counter::increment);

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Safe Counter: " + counter.count);
    }
}

class SafeCounter {
    int count = 0;
    synchronized void increment() { count++; }
}


// ============================================================
// 3. ConcurrentHashMap
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
}


// ============================================================
// 4. LRU Cache
// ============================================================
class LRUCacheExample {
    static void run() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.get(1);
        cache.put(3, "Three");

        System.out.println("LRU Cache: " + cache.keySet());
    }
}

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


// ============================================================
// 5. Producer-Consumer
// ============================================================
class ProducerConsumerExample {
    static void run() throws InterruptedException {
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
}


// ============================================================
// 6. wait/notify
// ============================================================
class WaitNotifyExample {
    static void run() throws InterruptedException {
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


// ============================================================
// 7. Thread Pool
// ============================================================
class ThreadPoolExample {
    static void run() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(() ->
                System.out.println("Task: " + Thread.currentThread().getName())
        );

        pool.shutdown();
        pool.awaitTermination(2, TimeUnit.SECONDS);
    }
}


// ============================================================
// 8. Future
// ============================================================
class FutureExample {
    static void run() throws Exception {
        ExecutorService ex = Executors.newSingleThreadExecutor();

        Future<Integer> result = ex.submit(() -> 10 + 20);

        System.out.println("Future Result: " + result.get());

        ex.shutdown();
    }
}


// ============================================================
// 9. CompletableFuture
// ============================================================
class CompletableFutureExample {
    static void run() {
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "Task")
                        .thenApply(s -> s + " Done");

        System.out.println("CompletableFuture: " + future.join());
    }
}


// ============================================================
// 10. ReentrantLock
// ============================================================
class ReentrantLockExample {
    static void run() {
        Lock lock = new ReentrantLock();

        lock.lock();
        try {
            System.out.println("Inside Lock");
        } finally {
            lock.unlock();
        }
    }
}


// ============================================================
// 11. Volatile
// ============================================================
class VolatileExample {
    static void run() throws InterruptedException {
        Flag flag = new Flag();

        Thread t = new Thread(() -> {
            while (flag.running) {}
            System.out.println("Stopped");
        });

        t.start();
        Thread.sleep(100);
        flag.running = false;
    }
}

class Flag {
    volatile boolean running = true;
}


// ============================================================
// 12. Race Condition
// ============================================================
class RaceConditionExample {
    static void run() throws InterruptedException {
        UnsafeCounter counter = new UnsafeCounter();

        Thread t1 = new Thread(counter::increment);
        Thread t2 = new Thread(counter::increment);

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Race Condition Count: " + counter.count);
    }
}

class UnsafeCounter {
    int count = 0;
    void increment() { count++; }
}