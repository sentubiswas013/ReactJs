// public class Main {
//     public static void main(String[] args) {
//         System.out.println("Result :");
//     }
// }

// ============================================================
// Encapsulation: Data is private and accessed using getter and setter methods.
// ============================================================
// class Main {
//     public static void main(String[] args) {
//         Person user = new Person();
//         user.setName("Sentu");
//         System.out.println("Result :" + user.getname());
//     }
// }

// class Person {

// 	private String name;

// 	public void setName(String name) {
// 		this.name = name;
// 	}

// 	public String getname() {
// 		return name;
// 	}
// }

// ============================================================
// Inheritance: Child class inherits properties from parent class using extends.
// ============================================================
// class Main {
//     public static void main(String[] args) {
//     	Animal an = new Animal();
//     	Dog dg = new Dog();

//         an.eat();
//         dg.eat();
//         dg.bark();

//         A a = new A();
//         a.add(5,7);
//         a.add(5,7,9);
//     }
// }

// class Animal {
// 	void eat() {
// 		System.out.println("Eat :");
// 	}
// }

// class Dog extends Animal {
// 	void bark() {
// 		System.out.println("Bark :");
// 	}
// }


// ============================================================
// Polymorphism: Method Overloading
// Polymorphism means one method can perform different actions using overloading or overriding.
// ============================================================
// class A {
// 	void add(int a, int b) {
// 		int sum = a+b;
// 		System.out.println("x :" + sum);
// 	}
// 	void add(int a, int b, int c) {
// 		int sum = a+b+c;
// 		System.out.println("x :" + sum);
// 	}
// }


// ============================================================
// Abstraction abstract class or interface
// ============================================================
class Main {
    public static void main(String[] args) {
        // System.out.println("Result :");
        B b = new B();
        b.start();
    }
}
abstract class A {
	abstract void start();
}
class B extends A {
	void start() {
		System.out.println("B :");
	}
}



