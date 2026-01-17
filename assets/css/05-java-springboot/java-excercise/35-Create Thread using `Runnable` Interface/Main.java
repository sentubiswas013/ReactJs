public class Main {

    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello from Runnable");
        new Thread(r).start();
    }
}