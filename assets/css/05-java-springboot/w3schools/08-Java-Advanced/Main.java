public class Main {

    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[10]); // This will throw an exception
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index is out of bounds!");
        } finally {
            System.out.println("The 'try catch' is finished.");
        }
    }
}