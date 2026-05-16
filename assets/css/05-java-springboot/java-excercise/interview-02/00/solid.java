class Solid {
	public static void main(String[] args) {
		// 1: Single Responsibility ------------------------




		System.out.println("==============================");
		// 2: Open Close -----------------------------------
		
		

		System.out.println("==============================");
		// 3: Liskov Substitution --------------------------
		



		System.out.println("==============================");
		// 4: Interface Segregation Principle --------------
		
		

		System.out.println("==============================");
		// 5: DIP (Dependency Inversion Principle) ---------

		

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




// Output:
// Bird can fly
// Sparrow can fly
// Sparrow can fly


// ============================================================
// 4. ISP (Interface Segregation Principle)
// Many specific interfaces are better than one general interface
// ============================================================
// Bad : Worker interface has both work and eat methods ❌
// interface Worker {
//     void work();
//     void eat();
// }

// Good : Separate interfaces for different responsibilities ✅




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




//Output:
// Card payment