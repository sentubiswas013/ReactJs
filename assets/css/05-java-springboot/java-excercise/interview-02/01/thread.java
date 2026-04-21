import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;


// public class Main {
//     public static void main(String[] args) {
//     	LambdaThreadExample1 tr = new LambdaThreadExample1();
//         Thread t = new Thread(tr.tasks);
// 		t.start();		
//     }
// }

// ============================================================
// 1. Using Thread class
// ============================================================


// ============================================================
// 2. Using Runnable interface
// ============================================================



// ============================================================
// 3. Using lambda expression (Java 8+)
// ============================================================
// class LambdaThreadExample1 {
// 	Runnable tasks = () -> {
// 		// System.out.println("Running " + Thread.currentThread().getName());
// 		System.out.println("Running " + Thread.currentThread().getName());
// 	};		
// }

// ============================================================
// 4. Synchronization
// ============================================================



// ============================================================
// 5. Volatile
// ============================================================



// ============================================================
// 6. AtomicInteger example
// ============================================================


// ============================================================
// 7. Sleep
// ============================================================



// ============================================================
// 8. wait
// ============================================================
// class WaitExample {
//     public static void main(String[] args) {

//         Object lock = new Object();

//         Thread t1 = new Thread(() -> {
//             synchronized (lock) {
//                 try {
//                     System.out.println("Thread 1 waiting...");
//                     lock.wait(); // releases lock
//                     System.out.println("Thread 1 resumed");
//                 } catch (InterruptedException e) {}
//             }
//         });

//         Thread t2 = new Thread(() -> {
//             synchronized (lock) {
//                 System.out.println("Thread 2 acquired lock");
//                 lock.notify(); // wakes up t1
//                 System.out.println("Thread 2 notified");
//             }
//         });

//         Thread t3 = new Thread(() ->{
//         	synchronized(lock) {
//         		System.out.println("Thread 3 acquired lock");
//         		lock.notify();
//         		System.out.println("Thread 3 notified");
//         	}
//         });

//         t1.start();
//         t2.start();
//         t3.start();
//     }
// }


// ============================================================
// 9. wait/notify
// ============================================================
// class WaitNotifyExample {
//     public static void main(String[] args) {
// 		Message msg = new Message();

//         Thread sender = new Thread(() -> {
//             try {
//                 msg.send("Hello");
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });

//         Thread receiver = new Thread(() -> {
//             try {
//                 System.out.println("Received: " + msg.receive());
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });

//         sender.start();
//         receiver.start();
//     }
// }

// class Message {
// 	private String data;
// 	private boolean available = false;

// 	public synchronized void send(String msg) throws InterruptedException {
// 		if (available) wait();
//         data = msg;
//         available = true;
//         notify();
// 	}

// 	public synchronized String receive() throws InterruptedException {
// 		if (!available) wait();	
//         available = false;
//         notify();
//         return data;
// 	}
// }


// ============================================================
// 10. ConcurrentHashMap
// ============================================================
// class ConcurrentMapExample {
// 	public static void main(String[] args) throws InterruptedException {
// 		ConcurrentHashMap <String, Integer> map = new ConcurrentHashMap<>();
// 		Thread t1 = new Thread(() -> map.put("A", 1000));
// 		Thread t2 = new Thread(() -> map.put("b", 2000));
// 		Thread t3 = new Thread(() -> map.put("c", 3000));

// 		t1.start();
// 		t2.start();
// 		t3.start();

// 		t1.join();
// 		t2.join();
// 		t3.join();

// 		System.out.println("ConcurrentHashMap: " + map);
// 	}
// }

// ============================================================
// 11. ExecutorService
// ============================================================
// class ExecutorServiceExample {
// 	public static void main(String[] args) throws RuntimeException {
// 		ExecutorService executor = Executors.newFixedThreadPool(2);

// 		executor.submit (() -> {
// 			System.out.println("Thred 1": Thread.currentThread().getName());
// 		});
// 		executor.submit (() -> {
// 			System.out.println("Thred 2": Thread.currentThread().getName());
// 		});
		
// 		executor.shutDown();
// 	}
// }


// ============================================================
// 12. CompletableFuture
// ============================================================

// class CompletableFutureExample {
// 	public static void main(String[] args) throws RuntimeException {
// 		CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->{
// 			try {
// 				Thread.sleep(5000);
// 			}
// 			catch(Exception e) { }
// 			return "Hello";
// 		}).thenApply(s -> s + " world");
// 		System.out.println(future.join());
// 	}
// }



// ============================================================
// 13. ReentrantLock
// ============================================================
// class ReentrantLockExample {
// 	public static void main(String[] args) throws RuntimeException {
// 		Lock  lock = new ReentrantLock();
// 		lock.lock();
// 		try {
// 			System.out.println("Task inside lock");
// 		} 
// 		finally{
// 			lock.unlock();
// 		}
// 	}
// }


class ReentrantLockExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Runnable task1 = () -> account.withdraw("User-1", 700);
        Runnable task2 = () -> account.withdraw("User-2", 500);

        new Thread(task1).start();
        new Thread(task2).start();
    }
}

class BankAccount {
    private int balance = 1000;
    private final ReentrantLock lock = new ReentrantLock();

    // withdraw money safely
    void withdraw(String user, int amount) {
        lock.lock(); // critical section
        try {
            if (balance >= amount) {
                System.out.println(user + " is withdrawing " + amount);
                Thread.sleep(500); // simulate delay
                balance -= amount;
                System.out.println(user + " completed withdrawal. Remaining: " + balance);
            } else {
                System.out.println(user + " insufficient balance!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock(); // always release
        }
    }
}



// ============================================================
// 15. Race Condition
// ============================================================




// ============================================================
// 16. LRU Cache
// ============================================================
