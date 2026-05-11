class Solid {
	public static void main(String[] args) {
		// 1: Single Responsibility ------------------------




		System.out.println("==============================");
		// 2: Open Close -----------------------------------
		PaymentService service = new PaymentService();

		CardPayment p1 = new CardPayment(); 
		service.ProcessPayment(p1);

		UpiPayment p2  = new UpiPayment(); 
		service.ProcessPayment(p2);
		

		System.out.println("==============================");
		// 3: Liskov Substitution --------------------------
		Bird b = new Bird();
		b.fly();

		Sparrow s = new Sparrow();
		s.fly();

		Bird b2 = new Sparrow();
		b2.fly();



		System.out.println("==============================");
		// 4: Interface Segregation Principle --------------
		Workable w1 = new Human();
        w1.work();

        Eatable e1 = new Human();
        e1.Eat();

        Workable w2 = new Human();
        w2.work();
		

		System.out.println("==============================");
		// 5: DIP (Dependency Inversion Principle) ---------

		MessageService message = new EmailService();
		NootificationService notification = new NootificationService(message);
		notification.notiFyUser();

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

class RegisterService {
	public void register() {}
}
// class EmailService {
// 	public void createEmail() {}
// }

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
		System.out.println("Card");
	};
}

class UpiPayment implements Payment {
	public void pay() {
		System.out.println("Upi");
	};
}

class PaymentService {
	public void ProcessPayment(Payment payment) {
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
		System.out.println("Fly");
	}
}

class Sparrow extends Bird {
	@Override
	public void fly() {
		System.out.println("Sparrow can Fly");
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
interface Workable {
	void work();
}

interface Eatable {
	void Eat();
}

class Human implements Workable, Eatable {
	public void work() {
		System.out.println("Work");
	}
	public void Eat() {
		System.out.println("Eat");
	}
}

class Robot implements Workable {
	public void work() {
		System.out.println("Work");
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
interface MessageService {
	void send();
}

class EmailService implements MessageService {
	public void send() {
		System.out.println("Hello");
	}
}

class SmsService implements MessageService {
	public void send() {
		System.out.println("Sms");
	}
}

class NootificationService {
	private MessageService messageService;
	NootificationService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void notiFyUser() {
		messageService.send();
	}
}



//Output:
// Card payment