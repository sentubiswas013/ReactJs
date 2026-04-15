
// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
 // Demonstrates classic deadlock scenario - Thread 1 locks A then waits for B, Thread 2 locks B then waits for A
class Main {
    public static void main(String[] args) throws InterruptedException {

        final Object A = new Object();
        final Object B = new Object();

        // Thread 1: acquires A first, then tries to acquire B
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try { Thread.sleep(100); } catch (Exception ignored) {}
                synchronized (B) { System.out.println("Thread 1 acquired A and B"); }
            }
        });

        // Thread 2: acquires B first, then tries to acquire A - creates circular wait condition
        Thread t2 = new Thread(() -> {
            synchronized (B) {
                try { Thread.sleep(100); } catch (Exception ignored) {}
                synchronized (A) { System.out.println("Thread 2 acquired B and A"); }
            }
        });

        t1.start(); t2.start();
        t1.join();  t2.join();
    }
}


// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Two bank accounts transferring to each other simultaneously
// ─────────────────────────────────────────────────────────────

class MainTwo {

    // ❌ DEADLOCK — t1 locks A waits for B, t2 locks B waits for A
    static void transfer(Account from, Account to, int amount) throws InterruptedException {
        synchronized (from) {
            Thread.sleep(100);
            synchronized (to) {                        // circular wait → DEADLOCK
                from.balance -= amount;
                to.balance   += amount;
            }
        }
    }

    // ✅ FIX — always lock lower id first → same order for both threads → no circular wait
    static void transferSafe(Account from, Account to, int amount) throws InterruptedException {
        Account first  = from.id < to.id ? from : to;
        Account second = from.id < to.id ? to   : from;

        synchronized (first) {
            Thread.sleep(100);
            synchronized (second) {
                from.balance -= amount;
                to.balance   += amount;
                System.out.println(Thread.currentThread().getName() + ": transferred $" + amount);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1, 1000);
        Account b = new Account(2, 1000);

        Thread t1 = new Thread(() -> { try { transferSafe(a, b, 100); } catch (Exception ignored) {} }, "Thread-1");
        Thread t2 = new Thread(() -> { try { transferSafe(b, a, 200); } catch (Exception ignored) {} }, "Thread-2");

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("A: $" + a.balance);
        System.out.println("B: $" + b.balance);
    }
}

class Account {
    int id, balance;
    Account(int id, int balance) { this.id = id; this.balance = balance; }
}


// Output:
// Thread-1: transferred $100
// Thread-2: transferred $200
// A: $1100
// B: $900
