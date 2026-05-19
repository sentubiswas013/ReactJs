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
    UserService1 s1 = new UserService1();
    UserService2 s2 = new UserService2();

    s1.create();
    s2.read();
}

static class UserService1 {
	public void create() {
		System.out.println("Create");
	}
}

static class UserService2 {
	public void read() {
		System.out.println("Read");
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
public static void OpenClose() {
    System.out.println("=========================== OpenClose");
    PaymentService service = new PaymentService();

    CardPayment payment1 = new CardPayment();
    service.ProcessPayment(payment1);

    Payment payment2 = new UpiPayment();
    service.ProcessPayment(payment2);
}

interface Payment{
	void pay();
}

static class CardPayment implements Payment{
	public void pay() {
		System.out.println("Card");
	}
}

static class UpiPayment implements Payment{
	public void pay() {
		System.out.println("Upi");
	}
}

static class PaymentService {
	public void ProcessPayment (Payment payment) {
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
public static void LiskovSubstitution() {
    System.out.println("=========================== LiskovSubstitution");
    Bird br1 = new Bird();
    br1.fly();
    Sparrow br2 = new Sparrow();
    br2.fly();
}

static class Bird {
	public void fly() {
		System.out.println("Bird can fly");
	}
}
static class Sparrow extends Bird{
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
    
    Workable hm = new Human();
    Eatable eat = new Human();
    Workable rb = new Robot();

    hm.work();
    eat.eat();

    rb.work();
}

interface Workable {
	void work();
}
interface Eatable {
	void eat();
}

static class Human implements Workable, Eatable {
	public void work () {
		System.out.println("Work");
	}
	public void eat () {
		System.out.println("Eat");
	}
}
static class Robot implements Workable {
	public void work () {
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
public static void DependencyInversion() {
    System.out.println("=========================== DependencyInversion");
    
    EmailService email = new EmailService(); 
    SmsService sms = new SmsService(); 
    NotificationService notification1  = new NotificationService(email);    
    NotificationService notification2  = new NotificationService(sms);    

    notification1.notifyUser();
    notification2.notifyUser();
}
interface MessageService {
	void send();
}

static class EmailService implements MessageService {
	public void send() {
		System.out.println("Email service");
	}
}

static class SmsService implements MessageService {
	public void send() {
		System.out.println("Ems service");
	}
}

static class NotificationService {
	private  MessageService messageService;
	NotificationService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void notifyUser() {
		messageService.send();
	}
}

//Output:
// Card payment

}