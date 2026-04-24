// ============================================================
// 1. Global Exception Handler
// ============================================================

public class TestApp {
    public static void main(String[] args) {
    	Thread.setDefaultUncaughtExceptionHandler(new ExceptioHandler());
    	Thread tr = new Thread(() =>{
    		throw new RuntimeException("There is erro");
    	});
    	tr.start();
    }
}
class ExceptioHandler implements Thread.UncaughtExceptionHandler {
	public void uncaughtException (Thread t, Throwable e) {
		System.out.println("Global Exception caught" + e.getMessage());
	}
}

// ============================================================
// 2. Global Exception Handler using spring
// ============================================================
@ControllerAdvic
class GlobalExceptionHanlder {
	@ExceptioHandler(InvalidAgeException.class)
	public ResponEntity<String> HandleException(Exception e) {
		return new ResponeEntity<>(
				"Error " + e.getMessage(),
				httpStatus.Internal_SERVER_ERROR
			)
	}
}




// ============================================================
// 3. throw vs throws
// ============================================================

public void readFile() throws IOException {
	if (true) {
		throw new IOException("File Don't available");
	}
}


// ============================================================
// 4. Custom Exception Handling
// ============================================================

class InvalidAgeException extends Exception {
	public InvalidAgeException (String message) {
		super(message);
	}
}


// Output
// Vote successful
// Exception: Underage - Not eligible to vote