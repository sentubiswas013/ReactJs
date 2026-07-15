import java.util.*;

class main {
	public static void main(String[] args) {
		System.out.println("Hello world");

		Parent p = new Child();
		System.out.println(p.x);   // 10 — reference type (Parent) decides

		Child c = new Child();
		System.out.println(c.x);   // 20 — reference type (Child) decides
	}
}

class Parent { 
	int x = 10; 
}

class Child extends Parent  { 
	int x = 20; 
}