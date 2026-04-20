import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;


public class Main {
    public static void main(String[] args) {
        Thread tr = new Thread(new RunThread());
        tr.start();

        // Schroynosized 
        SynchronizationExample syn = new SynchronizationExample();
        
    }
}

// ============================================================
// 1. Using Thread class
// ============================================================
// class ThreadClassExample {
//     public static void main(String[] args) {
//         MyThread t1 = new MyThread();
//         t1.start();

//         MyThread t2 = new MyThread();
//         t2.start();

//         MyThread t3 = new MyThread();
//         t3.start();

//         ExecutorService executor = Executors.newSingleThreadExecutor();
//     }
// }

// class MyThread extends Thread {
//    @Override
//     public void run() {
//         System.out.println("Thread running: " + Thread.currentThread().getName());
//     }
// }

// ============================================================
// 2. Using Runnable interface
// ============================================================

class RunThread implements Runnable {
	public void run() {
		System.out.println("Hell0");
	}
}



// ============================================================
// 2. Synchronization
// ============================================================
class SynchronizationExample {
	private int balance = 1000;

	public synchronized void deposit(int amount) {
		balance += amount;
	}

	public getBanlance () {
		return balance;
	}
}


// ============================================================
// 3. ConcurrentHashMap
// ============================================================



// ============================================================
// 6. wait/notify
// ============================================================



// ============================================================
// 7. ExecutorService
// ============================================================



// ============================================================
// 9. CompletableFuture
// ============================================================




// ============================================================
// 10. ReentrantLock
// ============================================================




// ============================================================
// 11. Volatile
// ============================================================




// ============================================================
// 12. Race Condition
// ============================================================
