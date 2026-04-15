import java.util.*;
import java.util.ArrayList;

class Main {
    public static void main(String[] args)  {
		// Section 1: exception handling using try-catch-finally
        processPayment(-50);
        processPayment(200);


		// Section 2: trow-throws
		try {
            withdraw(500, 200);
            withdraw(500, 600);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }


		// Section 3: custom exception
		try {
            vote(6);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }

	// Section 1: exception handling using try-catch-finally
	static void processPayment(double amount) {
		try {
			if (amount <= 0)
				throw new IllegalArgumentException("Invalid amount: $" + amount);

			System.out.println("Payment successful: $" + amount);

		} catch (IllegalArgumentException e) {
			System.out.println("Payment failed: " + e.getMessage());

		} catch (Exception e) { // fallback
			System.out.println("Unexpected error: " + e.getMessage());

		} finally {
			System.out.println("Payment attempt logged");
		}
	}

	// Section 2: trow-throws
	static double withdraw(double balance, double amount) throws IllegalArgumentException {
        if (amount > balance) throw new IllegalArgumentException("Insufficient balance: $" + balance);
        balance -= amount;
        System.out.println("Withdrawn: $" + amount + " | Remaining: $" + balance);
        return balance;
    }

	// Section 3: custom exception
	static void vote(int age) throws InvalidAgeException {
        if (age < 18) throw new InvalidAgeException("Underage: " + age);
    }
}

class InvalidAgeException extends Exception {
    InvalidAgeException(String msg) { super(msg); }
}

