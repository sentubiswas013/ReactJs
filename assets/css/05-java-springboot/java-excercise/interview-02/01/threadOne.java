import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;

// class MyThread {
//     public static void main(String[] args) {
//         System.out.println("Hello");
//     }
// }

// ============================================================
// 1. Using Thread class
// ============================================================
// class ThreadExp {
//     public static void main(String[] args) throws Exception{
//         Thread tr1 = new Thread(() -> { 
//             System.out.println("Lambda thread: " + Thread.currentThread().getName());
//         });


//         Thread tr2 = new Thread(() -> { 
//             System.out.println("Lambda thread: " + Thread.currentThread().getName());
//         });

//         tr1.start();
//         tr1.join();

//         tr2.start();        
//         tr2.join();

//         MyThread tr2 = new MyThread();
//         tr2.start();
//     }
// }

// class MyThread extends Thread {
//     public void run () {
//         System.out.println("Hello world.");
//     }
// }

// ============================================================
// 2. Using Runnable interface
// ============================================================



// ============================================================
// 3. Using lambda expression (Java 8+)
// ============================================================



// This is more concise and commonly used in modern Java code. You can also reuse the same lambda for multiple threads:



// ============================================================
// 4. Synchronization prevents multiple threads from accessing shared resources simultaneously, ensuring thread safety
// ============================================================
// class SynchronizationExp {
//     public static void main(String[] args) throws Exception {
//         // System.out.println("Hello");
//         BankAccount account = new BankAccount();

//         Thread tr1 = new Thread(() -> account.withdraw(700), "User1");
//         Thread tr2 = new Thread(() -> account.withdraw(600), "User2");

//         tr1.start();
//         tr2.start();

//         tr1.join();
//         tr2.join();

//     }
// }

// class BankAccount {
//     private int balance = 1000;

//     public synchronized void withdraw(int amount) {
//         if (balance >= amount) {
//             System.out.println("withdraw : " + Thread.currentThread().getName());
//             balance = balance - amount;
//              System.out.println("Remaining balance: " + balance);
//         } else {
//             System.out.println("There is no enought balance ");
//         }
//     }
// }


// ============================================================
// 5. Volatile ensures variable changes are immediately visible to all threads (prevents caching issues)
// ============================================================




// ============================================================
// AtomicInteger provides thread-safe operations without synchronization - useful for counters in concurrent programming
// ============================================================




// ============================================================
// Sleep pauses thread execution for specified time but keeps locks (can cause blocking)// ============================================================




// ============================================================
// 8. wait
// ============================================================





// ============================================================
// 9. wait/notify
// ============================================================





// ============================================================
// ConcurrentHashMap: Thread-safe HashMap that allows multiple threads to read/write simultaneously without external synchronization
// ============================================================





// Real-world Example: Counting word frequency in logs using ConcurrentHashMap
// import java.util.concurrent.*;

// import java.util.concurrent.ConcurrentHashMap;





// ============================================================
// ExecutorService: A thread pool manager that handles task execution without manually creating/managing threads. Provides submit(), execute(), and shutdown() methods for concurrent task processing.
// ============================================================




// Real-world Example: Processing multiple orders in parallel using ExecutorService
// import java.util.concurrent.*;




// ============================================================
// 12. CompletableFuture is a powerful class in Java that allows you to write asynchronous, non-blocking code. It provides a way to handle the result of an asynchronous computation and chain multiple computations together.
// ============================================================




// Real-world Example: Calling multiple APIs in parallel and combining results
// import java.util.concurrent.CompletableFuture;




// ============================================================
// 13. ReentrantLock
// ============================================================




//  Realtime Example:




// ============================================================
// 15. Race Condition
// ============================================================
// class RaceConditionExp {
//     public static void main(String[] args) throws Exception {
//         TicketBooking booking = new TicketBooking();

//         // Thread tr1 = new Thread(() ->  booking.bookTicket(), "User-1");

//         Thread tr1 = new Thread(() ->  booking.bookTicket(), "user 1 ");
//         Thread tr2 = new Thread(() ->  booking.bookTicket(), "user 2 ");

//         tr1.start();
//         tr2.start(); 

//         tr1.join();
//         tr2.join();
//     } 
// }


// class  TicketBooking {
//     private int seat = 1;

//     public synchronized void bookTicket() {
//         if(seat > 0) {
//             System.out.println(Thread.currentThread().getName() + "Book seat.");
//             seat --;
//         } else {
//             System.out.println(Thread.currentThread().getName() + "No seat available.");
//         }
//     }
// }


// To Fix race condition, we can synchronize the method:



// Output:
// User-1 booked seat
// User-2 booked seat   ❌ (wrong – only 1 seat!)


// ============================================================
// 16. LRU Cache
// ============================================================
class LRUCacheExample {
    public static void main(String[] args) {
        SessionService session = new SessionService();
        session.login("user 1");
        session.login("user 2");
        session.login("user 3");
        session.login("user 4");

        System.out.println("user1 active? " + session.isActive("user1")); // false
        System.out.println("user2 active? " + session.isActive("user2")); // true
    }
}


// Real-world Example: Caching DB results, API responses, etc.
class LRUCache<k, v> extends LinkedHashMap<k, v> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public boolean removeOldEntry (Map.Entry<k, v> eldest) {
        return size() > capacity;
    }
}

// Fetching user data from DB is slow → cache recent results.



// Calling external APIs (payment/user service) is expensive → cache response.




// Frequently viewed products → cache to reduce DB load.




// Session Cache: Store recently active user sessions.
class SessionService {
    private final LRUCache<String, String> cache = new LRUCache<>(2);

    public void login (String userId) {
        cache.put(userId, "ACTIVE");
        // cache.put(userId, "ACTIVE");
        System.out.println("User id " + userId);
    } 

    public boolean isActive(String userId) {
        return cache.get(userId) != null;
    }
}


// ============================================================
// 17. You need to implement a caching mechanism without using external libraries. How do you do it?
// ============================================================
