// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> System.out.println("Hello from Runnable: " + Thread.currentThread().getName());
        Thread t = new Thread(r, "PracticeThread");
        t.start();
        t.join();

        // Real-world: email notification task as Runnable (decoupled from Thread)
        Runnable emailTask = () -> {
            System.out.println("[" + Thread.currentThread().getName() + "] Sending welcome email to user@example.com");
        };

        Runnable smsTask = () -> {
            System.out.println("[" + Thread.currentThread().getName() + "] Sending SMS OTP to +1-XXX-XXX-XXXX");
        };

        Thread emailThread = new Thread(emailTask, "EmailService");
        Thread smsThread   = new Thread(smsTask,   "SMSService");

        emailThread.start();
        smsThread.start();
        emailThread.join();
        smsThread.join();
    }
}

// Output:
// Hello from Runnable: PracticeThread
// [EmailService] Sending welcome email to user@example.com
// [SMSService] Sending SMS OTP to +1-XXX-XXX-XXXX
