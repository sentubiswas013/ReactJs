// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> { for (int i = 0; i < 1000; i++) counter.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 1000; i++) counter.increment(); });

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Count: " + counter.count); // always 2000

        // Real-world: ticket booking — prevent overselling
        TicketService service = new TicketService(5);

        Runnable buyTicket = () -> {
            for (int i = 0; i < 3; i++) {
                boolean success = service.book(Thread.currentThread().getName());
                if (!success) break;
            }
        };

        Thread user1 = new Thread(buyTicket, "User1");
        Thread user2 = new Thread(buyTicket, "User2");

        user1.start(); user2.start();
        user1.join();  user2.join();

        System.out.println("Remaining tickets: " + service.available());
    }
}

class Counter {
    int count = 0;
    synchronized void increment() { count++; }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Thread-safe ticket booking — synchronized prevents overselling
// ─────────────────────────────────────────────────────────────
class TicketService {
    private int tickets;

    TicketService(int tickets) { this.tickets = tickets; }

    synchronized boolean book(String user) {
        if (tickets <= 0) { System.out.println(user + ": No tickets left"); return false; }
        tickets--;
        System.out.println(user + " booked. Remaining: " + tickets);
        return true;
    }

    synchronized int available() { return tickets; }
}

// Output:
// Count: 2000
// User1 booked. Remaining: 4
// User1 booked. Remaining: 3
// User2 booked. Remaining: 2
// ... (order may vary, total always correct)
// Remaining tickets: 0
