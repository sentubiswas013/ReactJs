import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            pool.submit(() ->
                System.out.println("Task " + taskId + " by " + Thread.currentThread().getName())
            );
        }

        pool.shutdown();
        pool.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);

        // Real-world: process multiple customer orders concurrently
        ExecutorService orderPool = Executors.newFixedThreadPool(3);

        String[] orders = {"Order#101", "Order#102", "Order#103", "Order#104", "Order#105"};

        for (String order : orders) {
            orderPool.submit(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] Processing " + order);
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}
                System.out.println("[" + Thread.currentThread().getName() + "] Completed " + order);
            });
        }

        orderPool.shutdown();
        orderPool.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }
}

// Output:
// Task 1 by pool-1-thread-1
// Task 2 by pool-1-thread-2
// Task 3 by pool-1-thread-1
// Task 4 by pool-1-thread-2
// [pool-2-thread-1] Processing Order#101
// [pool-2-thread-2] Processing Order#102
// [pool-2-thread-3] Processing Order#103
// ... (order may vary, 3 threads process 5 orders)
