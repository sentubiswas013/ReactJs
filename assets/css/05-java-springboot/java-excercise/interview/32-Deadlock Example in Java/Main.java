public class Main {
    public static void main(String[] args) throws InterruptedException {

        final Object A = new Object();
        final Object B = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try { Thread.sleep(100); } catch (Exception ignored) {}
                synchronized (B) {
                    System.out.println("Thread 1 acquired A and B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                try { Thread.sleep(100); } catch (Exception ignored) {}
                synchronized (A) {
                    System.out.println("Thread 2 acquired B and A");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}