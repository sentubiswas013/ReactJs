public class Main {

    static int count = 0;

    public Main() {
        count++;
    }

    public static void main(String[] args) {
        new Main();
        new Main();

        System.out.println(Main.count);
    }
}