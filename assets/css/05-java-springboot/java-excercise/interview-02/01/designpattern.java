// public class Main {
//     public static void main(String[] args) {
//         System.out.println("Result :");
//     }
// }

// ============================================================
// 1. Singleton Pattern (Thread-safe, Double-Checked Locking)
// Singleton Pattern is a design pattern that ensures a class has only one object (instance) and provides a global access point to that instance.
// ============================================================lass SingletonDemo {
	
public class SingletonDemo {
    public static void main(String[] args) {
    	Singleton s1 = Singleton.getInstance();
    	Singleton s2 = Singleton.getInstance();

        System.out.println("Result 1 : " + s1);
        System.out.println("Result 2 : " + s2);
    }
}


class Singleton {
	private static volatile Singleton instance;
	private Singleton() {}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if(instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;	
	} 
}


// Output: 
// Singleton instance created: 1530262698
// Thread-2 got instance: 1530262698
// Thread-1 got instance: 1530262698


// ============================================================
// 2. Factory Pattern (Best Practice using Enum)
// Factory Design Pattern is used to create objects without using new keyword directly, by using a factory method.
// ============================================================


// Output:
// Drawing Circle
// Drawing Square


// ============================================================
// 3. Builder Pattern (Immutable Object - BEST PRACTICE)
// Builder Pattern is used to create complex objects step by step, especially when an object has many optional parameters.
// ============================================================


// Output:
// User{name='Alice', age=0}
// User{name='Bob', age=25}

// ============================================================
// 4. Prototype Pattern (Cloning)
// Prototype Pattern is a Creational Design Pattern used to create new objects by copying (cloning) an existing object, instead of creating a new object from scratch.
// ============================================================
