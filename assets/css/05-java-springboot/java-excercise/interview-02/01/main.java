import java.util.*;

class main {
	public static void main(String[] args) {
		System.out.println("Hello world");
		B st = new B();
		st.methodA();
	}
}

class B extends A {
	public void methodA() {
		String name = null;
		Optional<String> optional = Optional.ofNullable(name);

		System.out.println("Result " + optional.isPresent());
		System.out.println("Result " + optional.isEmpty());
	}
}

abstract class A {
	abstract void methodA ();
}