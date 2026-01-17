public class Main {
    public static void main(String[] args) {
        Shape s = new Circle(2.0);
        System.out.println(s.area());
    }
}

abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    double r;

    Circle(double r) {
        this.r = r;
    }

    double area() {
        return Math.PI * r * r;
    }
}

