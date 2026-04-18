import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

// ============================================================
// 🚀 MAIN CLASS (Entry Point)
// ============================================================
class Main {
    public static void main(String[] args) throws Exception {

         System.out.println("Hello Java Threads!");
    }
}


// ============================================================
// 1. Using Thread class
// ============================================================
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // start new thread
    }
}


// ============================================================
// 2. Using Runnable interface
// ============================================================
class MyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Runnable thread: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyTask());
        t1.start();
    }
}

// ============================================================
// 2. Synchronization
// ============================================================
class BankAccount {
    private int balance = 1000;

    public synchronized void deposit(int amount) { // simpler
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}

public class Main {
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


// ============================================================
// 3. ConcurrentHashMap
// ============================================================
import java.util.concurrent.ConcurrentHashMap; // ✅ required import

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
        run(); // ✅ added main method
    }
}

// ============================================================
// 6. wait/notify
// ============================================================
class Main {
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
// 7. ExecutorService
// ============================================================
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            System.out.println("Task 1: " + Thread.currentThread().getName());
        });

        executor.submit(() -> {
            System.out.println("Task 2: " + Thread.currentThread().getName());
        });
        
        executor.execute(() -> System.out.println("Task 3"));

        executor.shutdown();
    }
}

// ============================================================
// 9. CompletableFuture
// ============================================================
import java.util.concurrent.CompletableFuture;

public class Main {
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
// 10. ReentrantLock
// ============================================================
import java.util.concurrent.locks.*;

public class Main {
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
// 11. Volatile
// ============================================================
public class Main {
    public static void main(String[] args) throws Exception {

        Flag flag = new Flag();

        new Thread(() -> {
            while (flag.running) {}   // keeps checking
            System.out.println("Stopped");
        }).start();

        Thread.sleep(1000);
        flag.running = false; // stop thread
    }
}

class Flag {
    volatile boolean running = true;
}


// ============================================================
// 12. Race Condition
// ============================================================
public class Main {
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
        count++; // not thread-safe
    }
}