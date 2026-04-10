import java.util.concurrent.ArrayBlockingQueue;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Consumed: " + queue.take());
                }
            } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        // Real-world: order processing pipeline
        // Orders placed by users (producer) → processed by fulfillment (consumer)
        ArrayBlockingQueue<String> orders = new ArrayBlockingQueue<>(3);

        Thread orderProducer = new Thread(() -> {
            String[] newOrders = {"Order#101", "Order#102", "Order#103", "Order#104"};
            try {
                for (String order : newOrders) {
                    orders.put(order);
                    System.out.println("[Placed]    " + order);
                }
            } catch (InterruptedException ignored) {}
        }, "OrderService");

        Thread orderConsumer = new Thread(() -> {
            try {
                for (int i = 0; i < 4; i++) {
                    String order = orders.take();
                    System.out.println("[Fulfilled] " + order);
                }
            } catch (InterruptedException ignored) {}
        }, "FulfillmentService");

        orderProducer.start();
        orderConsumer.start();
        orderProducer.join();
        orderConsumer.join();
    }
}

// Output:
// Produced: 1 ... Consumed: 1 ... (interleaved)
// [Placed]    Order#101
// [Fulfilled] Order#101
// [Placed]    Order#102 ... (interleaved)
