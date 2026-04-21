// ============================================================
// 1. SRP (Single Responsibility Principle)
// One class should have only one responsibility.
// ============================================================

// class UserService {
// 	public void registerUser() {
// 		System.out.println("Register user");
// 	}
// }

// class EmailService {
// 	public void SendEmail() {
// 		System.out.println("Send Email.");
// 	}
// }

// ============================================================
// 2. OCP (Open/Closed Principle)
// Open for extension, closed for modification
// ============================================================
// Bad
// class Payment1 {
// 	void pay(String type) {
// 		if(type.equals("UPI")) {}
// 		else if(type.equals("card")) {};
// 	}
// }

// Good
// class OpenClosedDemo {
//     public static void main(String[] args) {
//     	PaymentService service = new PaymentService();
//     	CardPayment p1 = new CardPayment();    	
//     	service.processPayment(p1);

//     	UpiPayment p2 = new UpiPayment();
//     	service.processPayment(p2);


//     }
// }

// interface Payment {
// 	void pay();
// }

// class CardPayment implements Payment {
// 	public void pay() {
// 		System.out.println("Card ");
// 	}
// }
// class UpiPayment implements Payment {
// 	public void pay() {
// 		System.out.println("Upi ");
// 	}
// }

// class PaymentService {
// 	public void processPayment(Payment payment) {
// 		payment.pay();
// 	}
// }

// Output:
// Paid by Card
// Paid by UPI


// ============================================================
// 3. LSP (Liskov Substitution Principle)
// Child class should replace parent class without breaking code.
// ============================================================
// class LiskovSubstitutionDemo {
// 	public static void main(String[] args) {
// 		Bird b = new Bird();
// 		b.fly();

// 		Sparrow sp = new Sparrow();
// 		sp.fly();

// 		// Bird b2 = new Sparrow();
// 		// br.fly();
// 	}
// }

// class Bird {
// 	public void fly() {
// 		System.out.println("Bird Fly ");
// 	}
// }

// class Sparrow extends Bird {
// 	@Override
// 	public void fly() {
// 		System.out.println("Sparrow Fly ");
// 	}
// }



// Output:
// Bird can fly
// Sparrow can fly
// Sparrow can fly


// ============================================================
// 4. ISP (Interface Segregation Principle)
// Child class should replace parent class without breaking code.
// ============================================================
// class LiskovSubstitutionDemo {
// 	public static void main(String[] args) {
// 		Human hm = new Human();
// 		hm.work();
// 		hm.eat();

// 		Robot rb = new Robot();
// 		rb.work();
// 	}
// }

// interface Workable {
// 	public void work();
// }
// interface Eatable {
// 	public void eat();
// }

// class Human implements Workable, Eatable {
// 	public void work() {
// 		System.out.println("Human Work.");
// 	}
// 	public void eat() {
// 		System.out.println("HUman Eat.");
// 	}
// }

// class Robot implements Workable{
// 	public void work() {
// 		System.out.println("Robot Work.");
// 	}
// }


// Output:
// Human working
// Human eating
// Robot working


// ============================================================
// 5. DIP (Dependency Inversion Principle)
// Depend on abstractions, not concrete implementations
// ============================================================
class DependencyInversionDemo {
	public static void main(String[] args) {
		CardPayment pay = new CardPayment();
		OderService order = new OderService(pay);
		order.placeOrder();
	}
}

interface Payment {
	void pay();
}

class CardPayment implements Payment{
	public void pay() {
		System.out.println("Pay");
	}
}

class OderService {
	private Payment payment;

	public OderService (Payment payment) {
		this.payment = payment;
	}

	public void placeOrder() {
		payment.pay();
	}
}


//Output:
// Card payment