// public class Main {
//     public static void main(String[] args) {
//         System.out.println("Result :");
//     }
// }

// ============================================================
// Class, Constructor + Static + Final Variable
// ============================================================
// public class Main {
//     public static void main(String[] args) {
//         System.out.println("Result :");

//         Student s1 = new Student("Sentu", 25);
//         //Student s2 = new Student("Pintu", 35);
//         s1.display();
//         //s2.display();

//         Student.showCount();
//     }
// }


// class Student {
// 	String name;
// 	int age;

// 	static int count = 1;

// 	static {
// 		System.out.println("Static block executed");
// 	}

// 	Student() {
// 		 this("Default", 0);
// 	}

// 	Student (String name, int age) {
// 		this.name = name;
// 		this.age = age;
// 		count ++;
// 	}

// 	void display() {
// 		System.out.println("Student: " + name + ", " + age);
// 	}

// 	static void showCount() {
//         System.out.println("Total Students: " + count);
//     }

// 	static class staticHelper {
// 		void showMessage() {
// 			 System.out.println("Inside Static Nested Class");
// 		}
// 	}
// }


// ============================================================
// Final Variable + Final Method
// ============================================================
// public class Main {
//     public static void main(String[] args) {
//     	FinalExmB fn = new FinalExmB();
//     	fn.display();
//         // System.out.println("Result :" + fn.number);
//     }
// }

// class FinalExmA {
// 	final int number = 100;

// 	final void display() {
// 		System.out.println("Result :" + number);
// 	}
// }

// class FinalExmB extends FinalExmA {
// 	final void display() {
// 		System.out.println("Result 2:" + number);
// 	}
// }

// ============================================================
// Final Class (cannot be extended)
// ============================================================



// ============================================================
// Interface
// ============================================================
// public class Main {
//     public static void main(String[] args) {
//     	Car ch = new Car();
//     	// ch.start();
//     	System.out.println(ch.start());
//         // System.out.println("Result :" + fn.number);
//     }
// }

// interface vehicle {
// 	String start(); 
// }

// class Car implements vehicle {
// 	public String  start() {
// 		return "Car started";
// 		// System.out.println("Hello");
// 	}
// }


// ============================================================
// this & super
// ============================================================
// public class Main {
//     public static void main(String[] args) {
//     	B b = new B();
//     	b.display();
//         // System.out.println("Result :" + fn.number);
//     }
// }

// class A{
// 	String name = "Sentu";
// 	A() {
// 		System.out.println("display 1:");
// 	}
// }

// class B extends A{
// 	String name = "Biswas";

// 	B() {
//         super(); // calls Parent constructor
//     }

// 	void display() {
// 		System.out.println("display B2:" + this.name);
// 		System.out.println("display B2:" + super.name);
// 	}
// }



// Output
// Parent Constructor
// this.msg: Child
// super.msg: Parent


// ============================================================
// Immutable class is a class whose object state cannot be changed after it is created.
// ============================================================
public class Main {
    public static void main(String[] args) {
    	 Person pr = new Person("Pintu");
    	// pr.name = "Sentu Biswas";
        System.out.println("Result :" + pr.getName());
    }
}


final class Person {
	private final String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}