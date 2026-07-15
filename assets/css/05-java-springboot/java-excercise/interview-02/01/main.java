import java.util.*;

class main {
	public static void main(String[] args) {
		System.out.println("Hello world");

		Demo obj = new Demo();
		obj.display();
		// obj.display();
	}
}

class Demo {
    static int x = 10;   // static
    int y = 20;          // non-static

    static void show() { System.out.println(x); }   // static method
    void display()     { System.out.println(y); }   // non-static method
}