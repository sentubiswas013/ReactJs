public class Main {
    public static void main(String[] args) {
        Box a = new Box();
        Box b = new Box(2, 3);
        Box c = new Box(b);

        System.out.println(a.area() + " " + b.area() + " " + c.area());
    }
}

class Box {
    int w, h;

    Box() {
        this.w = 1;
        this.h = 1;
    }

    Box(int w, int h) {
        this.w = w;
        this.h = h;
    }

    Box(Box b) {
        this.w = b.w;
        this.h = b.h;
    }

    int area() {
        return w * h;
    }
}

