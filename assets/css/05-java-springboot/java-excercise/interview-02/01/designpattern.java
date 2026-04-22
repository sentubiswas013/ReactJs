// class Main {
//     public static void main(String[] args) throws Exception {
        
//     }
// }


// ============================================================
// 1. Singleton Pattern (Thread-safe, Double-Checked Locking)
// Singleton Pattern is a design pattern that ensures a class has only one object (instance) and provides a global access point to that instance.

// Rules to create Singleton Pattern:
// Make class final to prevent subclassing
// Private constructor to prevent instantiation from outside the class
// Private static variable to hold the single instance of the class
// Public static method that returns the instance of the class, creating it if it doesn't exist yet


// When to use Singleton Pattern in real life:
// 1. When you want to control access to a shared resource (like a database connection).
// 2. When you want to ensure that only one instance of a class is created and used throughout the application (like a configuration manager).
// 3. When you want to implement a global point of access to a resource (like a logging service).   
// ============================================================




// Output: 
// Singleton instance created: 1530262698
// Thread-2 got instance: 1530262698
// Thread-1 got instance: 1530262698


// ============================================================
// 2. Factory Pattern (Best Practice using Enum)
// Factory Design Pattern is used to create objects without using new keyword directly, by using a factory method.

// Rules to create Factory Pattern:
// 1. Create an interface or abstract class for the type of object you want to create.
// 2. Create concrete classes that implement the interface or extend the abstract class.
// 3. Create a Factory class with a static method that takes input (like an enum


// When to use Factory Pattern:
// 1. When you have a super class with multiple sub-classes and based on input, you need to return one of the sub-class.
// 2. When you want to decouple the client code from the actual implementation of the objects it needs to create.

// ============================================================
class Main {
    public static void main(String[] args) throws Exception {
        Shape Shape1 = ShapeFactory.getShape();
    }
}

enum ShapeType {
	CIRCLE, SQUARE
}

interface shape {
	void draw();
}

class Circle implements shape{
	public void draw() {
		System.out.println("Drawing Circle");
	}
}


class ShapeFactory {
	public static shape getShape(ShapeType type) {
		switch(type) {
			case CIRCLE:
				return new Circle();
			default:
				throw new IllegalArgumentException("Invalid shape  type")
		}

	}

}

// Output:
// Drawing Circle
// Drawing Square


// ============================================================
// 3. Builder Pattern (Immutable Object - BEST PRACTICE)
// Builder Pattern is used to create complex objects step by step, especially when an object has many optional parameters.

// Rules to create Builder Pattern:
// 1. Create a static nested Builder class inside the main class.
// 2. The Builder class should have the same fields as the main class.
// 3. The Builder class should have setter-like methods that return the Builder instance (for chaining).
// 4. Create a build() method in the Builder class that returns the final object.

// When to use Builder Pattern:
// 1. When you have a class with many parameters (especially optional ones) and want to avoid constructor overloading.
// 2. When you want to create immutable objects with many parameters.

// ============================================================
class Main {
    public static void main(String[] args) throws Exception {
        
    }
}

// final class User {
// 	private final String name;
// 	private final int age;

// 	public void  User 
// }


// Output:
// User{name='Alice', age=0}
// User{name='Bob', age=25}

// ============================================================
// 4. Prototype Pattern (Cloning)
// Prototype Pattern is a Creational Design Pattern used to create new objects by copying (cloning) an existing object, instead of creating a new object from scratch.

// Rules to create Prototype Pattern:
// 1. Implement the Cloneable interface in the class you want to clone.
// 2. Override the clone() method to return a copy of the object.
// 3. Use the clone() method to create new objects by copying existing ones.    

// When to use Prototype Pattern:
// 1. When object creation is expensive and you want to create new objects by copying existing ones.
// 2. When you want to hide the creation logic from the client code.
// ============================================================

// class PrototypeDemo {
//     public static void main(String[] args) throws Exception {
//         Student s1 = new Student(1, "Sentu");
//         Student s2 = (Student) s1.clone();

//         System.out.println("Result " + s1.name);
//         System.out.println("Result " + s2.name);
//     }
// }

// class Student implements Cloneable {
// 	int id;
//     String name;

// 	Student(int id, String name) {
// 		this.id = id;
// 		this.name = name;
// 	}

// 	public Object clone() throws CloneNotSupportedException {
// 		return super.clone();
// 	}
// }


// Output:
// John
// John