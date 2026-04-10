public class Main {

    public static void main(String[] args) {
        try {
            vote(16);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void vote(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Underage");
        }
    }

    
}

class InvalidAgeException extends Exception {
    InvalidAgeException(String msg) {
        super(msg);
    }
}