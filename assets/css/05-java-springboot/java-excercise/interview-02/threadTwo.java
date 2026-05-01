import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

// ============================================================
// 1. Using Thread class
// ============================================================
class ThreadExample {
    public static void main(String[] args) {
        new MyThread().start();
    }
}

class MyThread extends Thread {
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName());
    }
}

// ============================================================
// 2. Using Runnable (Preferred)
// ============================================================
class RunnableExample {
    public static void main(String[] args) {
        Thread t = new Thread(new MyTask());
        t.start();
    }
}

class MyTask implements Runnable {
    public void run() {
        System.out.println("Runnable: " + Thread.currentThread().getName());
    }
}

// ============================================================
// 3. Lambda (Modern way)
// ============================================================
class LambdaExample {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("Lambda Thread")).start();
    }
}

// ============================================================
// 4. Synchronization (Shared Resource Protection)
// Real-time: Bank account withdraw
// ============================================================
class SyncExample {
    public static void main(String[] args) {
        Bank account = new Bank();

        Runnable task = () -> account.withdraw(50);

        new Thread(task).start();
        new Thread(task).start();
    }
}

class Bank {
    private int balance = 100;

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " withdrawing");
            balance -= amount;
            System.out.println("Remaining: " + balance);
        }
    }
}

// ============================================================
// 5. Volatile (Visibility)
// Real-time: Stop background task
// ============================================================
class VolatileExample {
    public static void main(String[] args) throws Exception {
        Task task = new Task();
        new Thread(task).start();

        Thread.sleep(2000);
        task.stop();
    }
}

class Task implements Runnable {
    private volatile boolean running = true;

    public void stop() {
        running = false;
    }

    public void run() {
        while (running) {
            System.out.println("Running...");
        }
        System.out.println("Stopped");
    }
}

// ============================================================
// 6. AtomicInteger (Thread-safe counter)
// Real-time: API request count
// ============================================================
class AtomicExample {
    public static void main(String[] args) throws Exception {
        AtomicInteger count = new AtomicInteger(0);

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Count: " + count.get());
    }
}

// ============================================================
// 7. Sleep (Pause execution)
// ============================================================
class SleepExample {
    public static void main(String[] args) throws Exception {
        System.out.println("Start");
        Thread.sleep(1000);
        System.out.println("After 1 sec");
    }
}

// ============================================================
// 8. wait() / notify()
// Real-time: Producer-Consumer
// ============================================================
class WaitNotifyExample {
    public static void main(String[] args) {
        Shared resource = new Shared();

        new Thread(resource::produce).start();
        new Thread(resource::consume).start();
    }
}

class Shared {
    private boolean available = false;

    public synchronized void produce() {
        try {
            while (available) wait();
            System.out.println("Produced");
            available = true;
            notify();
        } catch (Exception e) {}
    }

    public synchronized void consume() {
        try {
            while (!available) wait();
            System.out.println("Consumed");
            available = false;
            notify();
        } catch (Exception e) {}
    }
}

// ============================================================
// 9. ConcurrentHashMap
// ============================================================
class ConcurrentMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "A");
        map.put(2, "B");

        map.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}

// ============================================================
// 10. ExecutorService (Thread Pool)
// Real-time: Handle multiple requests
// ============================================================
class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                System.out.println("Task: " + Thread.currentThread().getName());
            });
        }

        service.shutdown();
    }
}

// ============================================================
// 11. CompletableFuture (Async)
// ============================================================
class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture
            .supplyAsync(() -> "Hello")
            .thenApply(s -> s + " World")
            .thenAccept(System.out::println);
    }
}

// ============================================================
// 12. ReentrantLock
// ============================================================
class LockExample {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Runnable task = () -> {
            lock.lock();
            try {
                System.out.println("Locked by " + Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        };

        new Thread(task).start();
        new Thread(task).start();
    }
}

// ============================================================
// 13. Race Condition (Problem Example)
// ============================================================
class RaceConditionExample {
    static int count = 0;

    public static void main(String[] args) throws Exception {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                count++; // NOT safe
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Wrong Count: " + count);
    }
}

// ============================================================
// 14. LRU Cache (Real-time: caching)
// ============================================================
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(2);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.get(1);
        cache.put(3, "C");

        System.out.println(cache); // {1=A, 3=C}
    }
}