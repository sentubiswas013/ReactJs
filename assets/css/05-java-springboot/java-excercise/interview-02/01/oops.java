public class Main {
    public static void main(String[] args) {
        // 01. Encapsulation ====================
        Student stu = new Student();
        stu.setName("sentu biswas");
        System.out.println(stu.getName());

        System.out.println("==================");
        // 02. Inheritance ====================
        Animal animal = new Animal();
        Dog dog = new Dog();

        animal.eat();
        dog.eat();

        System.out.println("==================");
        // 03. Polymorphism ====================
        Calculator cal = new Calculator();
        System.out.println(cal.add(5, 5));
        System.out.println(cal.add(5, 5, 5));

        System.out.println("==================");
        // 04. Abstraction ====================
        Vehicle car = new Car();
        car.start();
    }
}

// ============================================================
// Encapsulation: Data is private and accessed using getter and setter methods.
// ============================================================
class Student {
	private String name;
	Student () {}
	Student (String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}



// ============================================================
// Inheritance: Child class inherits properties from parent class using extends.
// ============================================================

class Animal {
	public void eat() {
		System.out.println("Eating");
	}
}
class Dog extends Animal {
	public void eat() {
		System.out.println("Dog is Eating");
	}
}


// ============================================================
// Polymorphism: Method Overloading
// Polymorphism means one method can perform different actions using overloading or overriding.
// ============================================================
class Calculator {
	int add (int a, int b) {
		return a + b;
	}
	int add (int a, int b, int c) {
		return a + b + c;
	}
}



// ============================================================
// Abstraction abstract class or interface
// 4. Abstraction abstract class or interface 👉 Hide complexity, show only needed parts. Show what is needed, hide the rest.
// ============================================================
abstract class Vehicle{
	abstract void start();
}

class Car extends Vehicle {
	public void start() {
		System.out.println("Hello");
	}
}



