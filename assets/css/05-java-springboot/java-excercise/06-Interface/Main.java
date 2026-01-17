public class Main {
    public static void main(String[] args) {
        Drawable d = new Square();
        d.draw();
    }
}

interface Drawable {
    void draw();
}

class Square implements Drawable {
    public void draw() {
        System.out.println("Drawing square");
    }
}

