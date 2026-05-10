class Solid {
	public static void main(String[] args) {
		// 1: Single Responsibility ------------------------



		// 2: Open Close -----------------------------------
		PaymentService service = new PaymentService();
		CardPayment p1 = new CardPayment();
		UpiPayment p2 = new UpiPayment();
		service.PaymentProcess(p1);
		service.PaymentProcess(p2);


		// 3: Liskov Substitution --------------------------
		Bird b1 = new Bird();
		b1.fly();

		Sparrow sp = new Sparrow();
		sp.fly();

		Bird b2 = new Sparrow();
		b2.fly();



		// 4: Interface Segregation Principle --------------
		InputDevice device = new Keyboard();
		Computer computer = new Computer(device);

		computer.start();
		
	}
}

// ============================================================
// 1. SRP (Single Responsibility Principle)
// One class should have only one responsibility.
// ============================================================
// Bad Design: Invoice doing too many things ❌
// class UserService1 {

//     public void registerUser() {
//         System.out.println("User registered");
//     }

//     public void sendEmail() {
//         System.out.println("Email sent");
//     }
// }
// Problem:
// Handles user logic + email logic

// Good Design: Split responsibilities ✅
class UserService1 {
	public void registerUser() {
		System.out.println("Hello");
	}
}
class UserService2 {
	public void sendEmail() {
		System.out.println("Hello");
	}
}



// ============================================================
// 2. OCP (Open/Closed Principle)
// Open for extension, closed for modification
// ============================================================
// bad : Adding new payment type requires modifying existing code ❌
// class Payment1 {
//     void pay(String type) {
//         if(type.equals("UPI")) {}
//         else if(type.equals("CARD")) {}
//     }
// }

// Good : Easy to extend without modifying existing code ✅
interface Payment {
	void pay();
}

class CardPayment implements Payment {
	public void pay() {
		System.out.println("Card payment");
	}
}

class UpiPayment implements Payment {
	public void pay() {
		System.out.println("Upi payment");
	}
}

class PaymentService {
	public void PaymentProcess(Payment payment) {
		payment.pay();
	}
} 

// Output:
// Paid by Card
// Paid by UPI


// ============================================================
// 3. LSP (Liskov Substitution Principle)
// A child class should be able to replace its parent class without breaking the program behavior.
// ============================================================
// Bad: Ostrich can't fly, violates LSP ❌
// class Bird1 {
//     void fly() {}
// }

// class Ostrich extends Bird1 {
//     void fly() { throw new RuntimeException(); }
// }

// Good: Separate flying and non-flying birds ✅
class Bird {
	public void fly() {
		System.out.println("Bird can fly");
	}
}

class Sparrow extends Bird {
	@Override
	public void fly() {
		System.out.println("Sparrow can fly");
	}
}



// Output:
// Bird can fly
// Sparrow can fly
// Sparrow can fly


// ============================================================
// 4. ISP (Interface Segregation Principle)
// Child class should replace parent class without breaking code.
// ============================================================
// Bad : Worker interface has both work and eat methods ❌
// interface Worker {
//     void work();
//     void eat();
// }

// Good : Separate interfaces for different responsibilities ✅


interface workable {
	void work();
}
interface eatable {
	void eat();
}

class Human implements workable, eatable {
	public void work() {
		System.out.println("Work");
	}
	public void eat() {
		System.out.println("eat");
	}
}



// Output:
// Human working
// Human eating
// Robot working


// ============================================================
// 5. DIP (Dependency Inversion Principle)
// Depend on abstractions, not concrete implementations
// ============================================================
// Bad : Laptop directly depends on WiredMouse ❌
// class Laptop {
//     CardPaymentB mouse = new CardPaymentB();
// }

// Good : Laptop depends on Mouse interface, not specific implementation ✅
interface InputDevice {
	void type();
}

class Keyboard implements InputDevice {
	public void type() {
		System.out.println("Keyboard");
	}
}
class Computer {
	private InputDevice inputDevice;
	public Computer (InputDevice inputDevice) {
		this.inputDevice = inputDevice;
	}

	public void start() {
		inputDevice.type();
	}
}



//Output:
// Card payment