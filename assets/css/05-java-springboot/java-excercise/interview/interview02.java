import java.util.*;
import java.util.concurrent.*;

class Main {

    public static void main(String[] args) throws Exception {

        concurrentHashMapDemo();
        lruCacheDemo();
        producerConsumerDemo();
        deadlockDemo();
        singletonDemo();
        threadClassDemo();
        runnableDemo();
        synchronizationDemo();
        waitNotifyDemo();
        threadPoolDemo();
    }

    // ─────────────────────────────────────────────
    // 26. ConcurrentHashMap
    // Thread-safe map (no full locking)
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
    // 30. LRU Cache (LinkedHashMap)
    // Removes least recently used item
    // ─────────────────────────────────────────────
    static void lruCacheDemo() {
        LRUCache<Integer, String> cache = new LRUCache<>(2);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.get(1);        // Access 1
        cache.put(3, "Three"); // Evicts 2

        System.out.println("LRU Cache: " + cache.keySet());
    }

    // ─────────────────────────────────────────────
    // 31. Producer-Consumer
    // BlockingQueue handles synchronization
    // ─────────────────────────────────────────────
    static void producerConsumerDemo() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                queue.put(1);
                System.out.println("Produced: 1");
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

    // ─────────────────────────────────────────────
    // 32. Deadlock Example
    // Two threads waiting on each other
    // ─────────────────────────────────────────────
    static void deadlockDemo() {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread1 locked lock1");
                synchronized (lock2) {
                    System.out.println("Thread1 locked lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread2 locked lock2");
                synchronized (lock1) {
                    System.out.println("Thread2 locked lock1");
                }
            }
        });

        t1.start();
        t2.start();

        // ⚠️ May cause deadlock (intentional for demo)
    }

    // ─────────────────────────────────────────────
    // 33. Singleton (Thread-safe)
    // Double-check locking
    // ─────────────────────────────────────────────
    static void singletonDemo() {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println("Same instance: " + (s1 == s2));
    }

    // ─────────────────────────────────────────────
    // 34. Thread Class
    // Extend Thread
    // ─────────────────────────────────────────────
    static void threadClassDemo() throws InterruptedException {
        MyThread t = new MyThread();
        t.start();
        t.join();
    }

    // ─────────────────────────────────────────────
    // 35. Runnable Interface
    // Preferred approach
    // ─────────────────────────────────────────────
    static void runnableDemo() throws InterruptedException {
        Thread t = new Thread(() ->
                System.out.println("Runnable Thread: " + Thread.currentThread().getName())
        );

        t.start();
        t.join();
    }

    // ─────────────────────────────────────────────
    // 36. Synchronization
    // Prevent race condition
    // ─────────────────────────────────────────────
    static void synchronizationDemo() throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> counter.increment());
        Thread t2 = new Thread(() -> counter.increment());

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Counter: " + counter.count);
    }

    // ─────────────────────────────────────────────
    // 37. wait() / notify()
    // Inter-thread communication
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
    // 38. Thread Pool
    // Reuse threads (best practice)
    // ─────────────────────────────────────────────
    static void threadPoolDemo() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(() ->
                System.out.println("Task executed by " + Thread.currentThread().getName())
        );

        pool.shutdown();
        pool.awaitTermination(2, TimeUnit.SECONDS);
    }
}


// ─────────────────────────────────────────────
// LRU Cache Class
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


// ─────────────────────────────────────────────
// Singleton Class
// ─────────────────────────────────────────────
class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}


// ─────────────────────────────────────────────
// Thread Class Example
// ─────────────────────────────────────────────
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread Class: " + getName());
    }
}


// ─────────────────────────────────────────────
// Counter (Synchronization)
// ─────────────────────────────────────────────
class Counter {
    int count = 0;

    synchronized void increment() {
        count++;
    }
}


// ─────────────────────────────────────────────
// Message (wait/notify)
// ─────────────────────────────────────────────
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