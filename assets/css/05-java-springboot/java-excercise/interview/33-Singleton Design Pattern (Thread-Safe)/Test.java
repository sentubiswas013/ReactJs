public class Test {
    public static void main(String[] args) {
        // TODO: practice Singleton Design Pattern (Thread-Safe)
    }
}


class Singleton {
    private static volatile Singleton instance;

    private Singleton() {};

    public static Singleton getInstance () {
        if(instance == null) {
            
        }
    }
}