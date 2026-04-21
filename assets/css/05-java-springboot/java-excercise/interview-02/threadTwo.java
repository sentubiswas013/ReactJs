import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.*;

// ============================================================
// 1. Job Scheduler with Dependencies and Priorities
// Implement a job scheduler that can schedule and execute multiple jobs concurrently, taking into account dependencies and priorities.
// ============================================================
class JobScheduler {
    static class Job implements Comparable<Job> {
        String name;
        int priority;
        List<String> dependencies;
        Runnable task;

        Job(String name, int priority, List<String> dependencies, Runnable task) {
            this.name = name;
            this.priority = priority;
            this.dependencies = dependencies;
            this.task = task;
        }

        @Override
        public int compareTo(Job other) {
            return Integer.compare(other.priority, this.priority); // higher priority first
        }
    }

    private final PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();
    private final Set<String> completed = ConcurrentHashMap.newKeySet();
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    void submit(Job job) {
        queue.offer(job);
    }

    void start() throws InterruptedException {
        while (!queue.isEmpty()) {
            Job job = queue.poll(100, TimeUnit.MILLISECONDS);
            if (job == null) continue;

            if (!completed.containsAll(job.dependencies)) {
                queue.offer(job); // re-queue if deps not met
                Thread.sleep(50);
                continue;
            }

            executor.submit(() -> {
                System.out.println("Running job: " + job.name);
                job.task.run();
                completed.add(job.name);
            });
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        JobScheduler scheduler = new JobScheduler();

        scheduler.submit(new Job("C", 1, List.of("A", "B"), () -> System.out.println("Job C done")));
        scheduler.submit(new Job("A", 3, List.of(),         () -> System.out.println("Job A done")));
        scheduler.submit(new Job("B", 2, List.of("A"),      () -> System.out.println("Job B done")));

        scheduler.start();
    }
}


// ============================================================
// 2. Thread-Safe Job Queue
// Implement a thread-safe job queue in Java that supports adding jobs, removing jobs, and retrieving the next job to execute efficiently. My Solution on YouTube
// ============================================================
class ThreadSafeJobQueue {
    static class Job {
        String name;
        Job(String name) { this.name = name; }
    }

    private final LinkedList<Job> queue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();

    void add(Job job) {
        lock.lock();
        try {
            queue.addLast(job);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    void remove(String name) {
        lock.lock();
        try {
            queue.removeIf(j -> j.name.equals(name));
        } finally {
            lock.unlock();
        }
    }

    Job next() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) notEmpty.await();
            return queue.removeFirst();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeJobQueue jq = new ThreadSafeJobQueue();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                jq.add(new Job("Job-" + i));
                System.out.println("Added Job-" + i);
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    Job j = jq.next();
                    System.out.println("Processing: " + j.name);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }).start();
    }
}


// ============================================================
// 3. Thread-Safe LRU Cache
// Implement a thread-safe LRU (Least Recently Used) cache with support for concurrent read and write operations, considering efficiency and thread safety.
// ============================================================
class ThreadSafeLRUCache<K, V> {
    private final int capacity;
    private final LinkedHashMap<K, V> cache;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    ThreadSafeLRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    V get(K key) {
        rwLock.writeLock().lock(); // write lock because LinkedHashMap mutates on access
        try { return cache.get(key); }
        finally { rwLock.writeLock().unlock(); }
    }

    void put(K key, V value) {
        rwLock.writeLock().lock();
        try { cache.put(key, value); }
        finally { rwLock.writeLock().unlock(); }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeLRUCache<Integer, String> cache = new ThreadSafeLRUCache<>(2);

        Thread writer = new Thread(() -> {
            cache.put(1, "One");
            cache.put(2, "Two");
            cache.put(3, "Three"); // evicts 1
        });

        Thread reader = new Thread(() -> System.out.println("Get 2: " + cache.get(2)));

        writer.start(); writer.join();
        reader.start(); reader.join();
    }
}


// ============================================================
// 4. Concurrent Task Scheduler (max N parallel tasks)
// Implement a concurrent task scheduler that can execute multiple tasks concurrently while limiting the maximum number of parallel executions.
// ============================================================
class ConcurrentTaskScheduler {
    private final Semaphore semaphore;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    ConcurrentTaskScheduler(int maxParallel) {
        this.semaphore = new Semaphore(maxParallel);
    }

    void submit(Runnable task) {
        executor.submit(() -> {
            try {
                semaphore.acquire();
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        });
    }

    void shutdown() throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentTaskScheduler scheduler = new ConcurrentTaskScheduler(2); // max 2 parallel

        for (int i = 1; i <= 5; i++) {
            final int id = i;
            scheduler.submit(() -> {
                System.out.println("Task " + id + " running on " + Thread.currentThread().getName());
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            });
        }

        scheduler.shutdown();
    }
}


// ============================================================
// 5. Print N Numbers Using Even-Odd Threads
// The goal is to print the numbers in order, while one thread only prints the even numbers and the other thread only prints the odd numbers. My Solution on YouTube
// ============================================================
class EvenOddPrinter {
    private int current = 1;
    private final int max;
    private final Object lock = new Object();

    EvenOddPrinter(int max) { this.max = max; }

    void printOdd() throws InterruptedException {
        synchronized (lock) {
            while (current <= max) {
                if (current % 2 == 0) { lock.wait(); continue; }
                System.out.println("Odd:  " + current++);
                lock.notify();
            }
        }
    }

    void printEven() throws InterruptedException {
        synchronized (lock) {
            while (current <= max) {
                if (current % 2 != 0) { lock.wait(); continue; }
                System.out.println("Even: " + current++);
                lock.notify();
            }
        }
    }

    public static void main(String[] args) {
        EvenOddPrinter printer = new EvenOddPrinter(10);

        new Thread(() -> { try { printer.printOdd();  } catch (InterruptedException e) {} }).start();
        new Thread(() -> { try { printer.printEven(); } catch (InterruptedException e) {} }).start();
    }
}


// ============================================================
// 6. Parallel Matrix Multiplication
// Implement a program that performs matrix multiplication using multiple threads to achieve parallel processing and improve performance.
// ============================================================
class ParallelMatrixMultiply {
    static int[][] multiply(int[][] A, int[][] B) throws InterruptedException {
        int n = A.length;
        int[][] result = new int[n][n];
        ExecutorService executor = Executors.newFixedThreadPool(n);

        for (int i = 0; i < n; i++) {
            final int row = i;
            executor.submit(() -> {
                for (int j = 0; j < n; j++)
                    for (int k = 0; k < n; k++)
                        result[row][j] += A[row][k] * B[k][j];
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        int[][] C = multiply(A, B);

        for (int[] row : C) System.out.println(Arrays.toString(row));
        // [19, 22]
        // [43, 50]
    }
}


// ============================================================
// 7. Thread-Safe Singleton
// Design a thread-safe singleton class in Java, ensuring that only a single instance is created even in a multi-threaded environment.
// ============================================================
class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) instance = new ThreadSafeSingleton(); // double-checked locking
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> System.out.println(
            Thread.currentThread().getName() + " → " + ThreadSafeSingleton.getInstance().hashCode()
        );

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");
        t1.start(); t2.start();
        t1.join();  t2.join();
        // Both threads print the same hashCode
    }
}


// ============================================================
// 8. Priority Job Scheduler (dynamic priority change)
// Implement a job scheduler that supports job priorities and allows for dynamically changing the priority of running jobs.
// ============================================================
class PriorityJobScheduler {
    static class PriorityJob {
        String name;
        AtomicInteger priority;
        Runnable task;

        PriorityJob(String name, int priority, Runnable task) {
            this.name = name;
            this.priority = new AtomicInteger(priority);
            this.task = task;
        }
    }

    private final PriorityBlockingQueue<PriorityJob> queue = new PriorityBlockingQueue<>(
        10, (a, b) -> Integer.compare(b.priority.get(), a.priority.get())
    );
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    void submit(PriorityJob job) { queue.offer(job); }

    void changePriority(PriorityJob job, int newPriority) {
        queue.remove(job);
        job.priority.set(newPriority);
        queue.offer(job);
    }

    void start() throws InterruptedException {
        while (!queue.isEmpty()) {
            PriorityJob job = queue.poll(100, TimeUnit.MILLISECONDS);
            if (job == null) continue;
            executor.submit(() -> {
                System.out.println("Running [priority=" + job.priority + "]: " + job.name);
                job.task.run();
            });
        }
        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        PriorityJobScheduler scheduler = new PriorityJobScheduler();

        PriorityJob low    = new PriorityJob("LowJob",    1, () -> System.out.println("Low done"));
        PriorityJob medium = new PriorityJob("MediumJob", 5, () -> System.out.println("Medium done"));
        PriorityJob high   = new PriorityJob("HighJob",   3, () -> System.out.println("High done"));

        scheduler.submit(low);
        scheduler.submit(medium);
        scheduler.submit(high);

        scheduler.changePriority(high, 10); // boost high job

        scheduler.start();
    }
}


// ============================================================
// 9. Distributed Lock Manager (single-JVM simulation)
// Design a distributed lock manager that can coordinate job execution across multiple nodes to prevent concurrent execution of conflicting jobs.
// ============================================================
class DistributedLockManager {
    private final ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    void lock(String resource) {
        locks.computeIfAbsent(resource, k -> new ReentrantLock()).lock();
        System.out.println(Thread.currentThread().getName() + " acquired lock on: " + resource);
    }

    void unlock(String resource) {
        ReentrantLock lock = locks.get(resource);
        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " released lock on: " + resource);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DistributedLockManager manager = new DistributedLockManager();

        Runnable task = () -> {
            manager.lock("order-service");
            try {
                System.out.println(Thread.currentThread().getName() + " processing...");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                manager.unlock("order-service");
            }
        };

        Thread t1 = new Thread(task, "Node-1");
        Thread t2 = new Thread(task, "Node-2");
        t1.start(); t2.start();
        t1.join();  t2.join();
    }
}


// ============================================================
// 10. Rate Limiter (Token Bucket)
// Implement a rate limiter that can control the rate of job execution, ensuring that jobs are not executed more frequently than a specified limit
// ============================================================
class RateLimiter {
    private final int maxTokens;
    private int tokens;
    private final long refillIntervalMs;
    private long lastRefillTime;

    RateLimiter(int maxTokens, long refillIntervalMs) {
        this.maxTokens = maxTokens;
        this.tokens = maxTokens;
        this.refillIntervalMs = refillIntervalMs;
        this.lastRefillTime = System.currentTimeMillis();
    }

    synchronized boolean tryAcquire() {
        refill();
        if (tokens > 0) { tokens--; return true; }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastRefillTime;
        if (elapsed >= refillIntervalMs) {
            tokens = maxTokens;
            lastRefillTime = now;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(3, 1000); // 3 requests per second

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 7; i++) {
            final int id = i;
            executor.submit(() -> {
                if (limiter.tryAcquire()) {
                    System.out.println("Request " + id + " allowed");
                } else {
                    System.out.println("Request " + id + " REJECTED (rate limited)");
                }
            });
            Thread.sleep(100);
        }

        executor.shutdown();
    }
}
