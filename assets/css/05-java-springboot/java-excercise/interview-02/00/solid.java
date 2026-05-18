class Solid {
	public static void main(String[] args) {

		SingleResponsibility();
		OpenClose();
		LiskovSubstitution();
		InterfaceSegregation();
		DependencyInversion();
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
public static void SingleResponsibility() {
    System.out.println("=========================== SingleResponsibility");

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
public static void OpenClose() {
    System.out.println("=========================== OpenClose");
    
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
public static void LiskovSubstitution() {
    System.out.println("=========================== LiskovSubstitution");
    
}



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
public static void InterfaceSegregation() {
    System.out.println("=========================== InterfaceSegregation");
    
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
public static void DependencyInversion() {
    System.out.println("=========================== DependencyInversion");
    
}



//Output:
// Card payment

}