// ============================================================
// 🚀 MAIN CLASS
// ============================================================
class Main {
    public static void main(String[] args) {

        // PaymentService paymentService = new PaymentService();
        // BankService bankService = new BankService();
        // VotingService votingService = new VotingService();

        // 1. Exception Handling (try-catch-finally)
       

        // 2. throw vs throws
       

        // 3. Custom Exception
        
    }
}


// ============================================================
// 1. Exception Handling (SRP applied)
// ============================================================



// ============================================================
// 2. throw vs throws
// ============================================================


// ============================================================
// 3. Custom Exception Handling
// ============================================================



// ============================================================
// Global Exception Handler (Single Responsibility)
// ============================================================
@RestControllerAdvice
public class GlobalExpectionHandler {
    @ExceptionHandler(UserNotFoundException.clss)
    public ResponseEntity<String> handleNotFound
}
