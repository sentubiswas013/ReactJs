// ============================================================
// 1. Global Exception Handler usig Thread.UncaughtExceptionHandler
// ============================================================
class TestApp {
    public static void main(String[] args) {
    	Thread.setDefaultUncaughtExceptionHandler(new ExceptioHandler());
    	Thread tr = new Thread(() ->{
    		throw new RuntimeException("There is erro");
    	});
    	tr.start();
    }
}
class ExceptioHandler implements Thread.UncaughtExceptionHandler {
	public void uncaughtException (Thread t, Throwable e) {
		System.out.println("Global Exception caught " + e.getMessage());
	}
}

// ============================================================
// 2. Global Exception Handler using spring
// ============================================================
// package com.example.demo.exception;
// import org.springframework.web.bind.annotation.*;

// GlobalExceptionHandler.java
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAgeException.class)
    public String handleInvalidAge(InvalidAgeException ex) {
        return "Error: " + ex.getMessage();
    }
}
// VotingService.java
@Service
class VotingService {
    public void vote(int age) {
        if (age < 18) {
            throw new InvalidAgeException("Underage - Not eligible to vote");
        }
    }
}


// ============================================================
// 3. throw vs throws
// ============================================================
class BankService {

    // "throws" declares exception
    public void withdraw(double balance, double amount) throws IllegalArgumentException {

        // "throw" actually throws exception
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        System.out.println("Withdraw successful");
    }
}


// ============================================================
// 4. Custom Exception Handling
// ============================================================
class InvalidAgeExceptions extends RuntimeException {
    public InvalidAgeExceptions(String message) {
        super(message);
    }
}

class VotingService {
    public void vote(int age) {
        validateAge(age);
        System.out.println("Vote successful");
    }

    private void validateAge(int age) {
        if (age < 18) {
            throw new InvalidAgeExceptions("Underage - Not eligible to vote");
        }
    }
}

// Output
// Vote successful
// Exception: Underage - Not eligible to vote