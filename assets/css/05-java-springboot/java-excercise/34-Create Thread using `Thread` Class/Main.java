public class Main extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from Thread");
    }

    public static void main(String[] args) {
        new Main().start();
    }
}