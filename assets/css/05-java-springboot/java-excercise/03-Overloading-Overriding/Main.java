public class Main {
    public static void main(String[] args) {
        Calc c = new Calc();
        AdvCalc ac = new AdvCalc();

        System.out.println(c.add(1, 2));
        System.out.println(ac.add(1, 2));
    }
}

class Calc {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {   // Overloading
        return a + b + c;
    }
}

class AdvCalc extends Calc {
    @Override
    int add(int a, int b) {           // Overriding
        return super.add(a, b) + 1;
    }
}