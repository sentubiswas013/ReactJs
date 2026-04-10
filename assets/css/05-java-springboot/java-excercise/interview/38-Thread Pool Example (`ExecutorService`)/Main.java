import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            pool.submit(() ->
                System.out.println("Task " + taskId + " executed by " +
                        Thread.currentThread().getName())
            );
        }

        pool.shutdown();
    }
}