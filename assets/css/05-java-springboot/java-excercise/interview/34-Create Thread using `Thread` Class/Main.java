// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from Thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Main t = new Main();
        t.setName("PracticeThread");
        t.start();
        t.join();

        // Real-world: background report generation thread
        Thread reportThread = new Thread(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] Generating monthly sales report...");
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            System.out.println("[" + Thread.currentThread().getName() + "] Report ready.");
        }, "ReportGenerator");

        reportThread.setDaemon(true); // runs in background, won't block JVM exit
        reportThread.start();
        reportThread.join();
    }
}

// Output:
// Hello from Thread: PracticeThread
// [ReportGenerator] Generating monthly sales report...
// [ReportGenerator] Report ready.
