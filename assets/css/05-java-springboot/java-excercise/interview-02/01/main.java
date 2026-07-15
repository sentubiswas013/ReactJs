import java.util.*;

class main {
	public static void main(String[] args) {
		System.out.println("Hello world");

		// Person obj = new Person();
		User obj = new User();
		obj.age = 23432;
		System.out.println(obj.age);
	}
}

// class User extends Person {

// }
// class Person {
//     public final String name = "Sentu vv bb mm";
//     public String name = "Sentu";
// }

class User {
    final int age = 20;
    // age = 10;  
}