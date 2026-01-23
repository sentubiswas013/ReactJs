
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello Worlld");

		Person obj = new Person();		
		System.out.println(obj.info());
	}
}

class Person {
	String name;
	int age; 

	void info () {
		return age + " -- " + name;
	}
}






// public class Main {
//     public static void main(String[] args) {
//         Person p = new Person();
//         p.name = "Alice";
//         p.age = 25;
//         p.introduce();
//     }
// }
// class Person {
//     String name;
//     int age;

//     void introduce() {
//         System.out.println(name + ", " + age);
//     }
// }
