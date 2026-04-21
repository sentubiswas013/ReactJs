// ============================================================
// 1. Global Exception Handler
// ============================================================
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
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
            throw new InvalidAgeException("Underage - Not eligible to vote");
        }
    }
}

// Global Exception Handler (Simulation)
class GlobalExceptionHandler {

    public static void handle(Exception e) {
        if (e instanceof InvalidAgeException) {
            System.out.println("Handled Globally: " + e.getMessage());
        } else {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}

class Main {
    public static void main(String[] args) {

        VotingService service = new VotingService();

        try {
            service.vote(16); // will throw exception
        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }

        try {
            service.vote(20); // valid case
        } catch (Exception e) {
            GlobalExceptionHandler.handle(e);
        }
    }
}


// ============================================================
// 2. Global Exception Handler using spring
// ============================================================
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

@SpringBootApplication
class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

// Controller
@RestController
@RequestMapping("/vote")
class VotingController {

    @GetMapping("/{age}")
    public String vote(@PathVariable int age) {
        if (age < 18) {
            throw new InvalidAgeException("Underage - Not eligible to vote");
        }
        return "Vote successful";
    }
}

// Custom Exception
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Global Exception Handler
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<String> handleInvalidAge(InvalidAgeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
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
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class VotingService {

    public void vote(int age) throws InvalidAgeException {
        validateAge(age);
        System.out.println("Vote successful");
    }

    private void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Underage - Not eligible to vote");
        }
    }
}

class CustomExceptionDemo {
    public static void main(String[] args) {

        VotingService service = new VotingService();

        try {
            service.vote(20);
            service.vote(16); // will throw exception
        } catch (InvalidAgeException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

// Output
// Vote successful
// Exception: Underage - Not eligible to vote