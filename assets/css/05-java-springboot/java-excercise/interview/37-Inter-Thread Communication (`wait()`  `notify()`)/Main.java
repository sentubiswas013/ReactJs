class Shared {

    private int data;
    private boolean ready = false;

    synchronized void produce(int value) throws InterruptedException {
        while (ready) {
            wait();
        }
        data = value;
        ready = true;
        notify();
    }

    synchronized int consume() throws InterruptedException {
        while (!ready) {
            wait();
        }
        ready = false;
        notify();
        return data;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Shared shared = new Shared();

        Thread producer = new Thread(() -> {
            try {
                shared.produce(42);
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                System.out.println(shared.consume());
            } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}