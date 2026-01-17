public class Main {
    public static void main(String[] args) {
        new Derived();
    }
}

class Base {
    int x = 10;

    Base(int x) {
        this.x = x;
    }
}

class Derived extends Base {
    int x = 20;

    Derived() {
        super(5);
        System.out.println(this.x + ", " + super.x);
    }
}

