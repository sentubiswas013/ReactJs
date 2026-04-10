// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Shared shared = new Shared();

        Thread producer = new Thread(() -> {
            try { shared.produce(42); } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try { System.out.println("Consumed: " + shared.consume()); } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        // Real-world: payment gateway notifies order service when payment is confirmed
        PaymentChannel channel = new PaymentChannel();

        Thread paymentGateway = new Thread(() -> {
            try {
                Thread.sleep(200); // simulate payment processing delay
                channel.confirmPayment("TXN-9981", true);
            } catch (InterruptedException ignored) {}
        }, "PaymentGateway");

        Thread orderService = new Thread(() -> {
            try {
                String result = channel.awaitConfirmation();
                System.out.println("[OrderService] Payment result: " + result);
            } catch (InterruptedException ignored) {}
        }, "OrderService");

        orderService.start();
        paymentGateway.start();
        orderService.join();
        paymentGateway.join();
    }
}

class Shared {
    private int data;
    private boolean ready = false;

    synchronized void produce(int value) throws InterruptedException {
        while (ready) wait();
        data = value;
        ready = true;
        notify();
    }

    synchronized int consume() throws InterruptedException {
        while (!ready) wait();
        ready = false;
        notify();
        return data;
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Payment confirmation channel using wait/notify
// ─────────────────────────────────────────────────────────────
class PaymentChannel {
    private String txnId;
    private boolean confirmed = false;
    private boolean paid;

    synchronized void confirmPayment(String txnId, boolean paid) throws InterruptedException {
        this.txnId = txnId;
        this.paid = paid;
        this.confirmed = true;
        notifyAll();
    }

    synchronized String awaitConfirmation() throws InterruptedException {
        while (!confirmed) wait();
        return txnId + " → " + (paid ? "SUCCESS" : "FAILED");
    }
}

// Output:
// Consumed: 42
// [OrderService] Payment result: TXN-9981 → SUCCESS
