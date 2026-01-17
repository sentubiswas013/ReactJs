import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(1);
        queue.addFirst(0);
        queue.addLast(2);
        
        System.out.println(queue);
    }
}