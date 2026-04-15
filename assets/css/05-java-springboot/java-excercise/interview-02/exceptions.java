// ============================================================
// 🚀 MAIN CLASS
// ============================================================
class Main {
    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();
        BankService bankService = new BankService();
        VotingService votingService = new VotingService();

        // 1. Exception Handling (try-catch-finally)
        paymentService.processPayment(-50);
        paymentService.processPayment(200);

        // 2. throw vs throws
        try {
            bankService.withdraw(500, 600);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 3. Custom Exception
        try {
            votingService.vote(16);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }
}


// ============================================================
// 1. Exception Handling (SRP applied)
// ============================================================
class PaymentService {

    public void processPayment(double amount) {
        try {
            validate(amount);
            System.out.println("Payment successful");

        } catch (IllegalArgumentException e) {
            System.out.println("Payment failed: " + e.getMessage());

        } finally {
            log();
        }
    }

    private void validate(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }

    private void log() {
        System.out.println("Transaction logged");
    }
}


// ============================================================
// 2. throw vs throws
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
// 3. Custom Exception Handling
// ============================================================
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


// ============================================================
// Custom Exception
// ============================================================
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}