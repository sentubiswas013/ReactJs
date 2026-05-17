import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;

class ThreadOne {
	public static void main(String[] args) throws Exception {		 
		// ThreadclassMain();
		// RunnableInterfaceMain();
		// LambdaExpressionMain();
		// RaceConditionMain();
		// ReentrantLockMain();
		// AtomicIntegerMain();
		// VolatileMain();
		// SleepWaitMain();
		// ConcurrentHashMapMain();
		ExecutorServiceMain();
		CompletableFutureMain();
		LruCacheMain();
	}


// ================================================================================
// 1. Using Thread class
// ================================================================================
static void ThreadclassMain() {
	System.out.println("==================================== Thread class");
	MyThread task1 = new MyThread();
	task1.start();

	MyThread task2 = new MyThread();
	task2.start();

	// System.out.println("Thread: " + Thread.currentThread().getName());
}

static class MyThread extends Thread {
	public void run() {
		System.out.println("Hello Thread " + Thread.currentThread().getName());
	}
}


// ================================================================================
// 2. Using Runnable interface
// ================================================================================
static void RunnableInterfaceMain() {
	System.out.println("==================================== Runnable interface");
	// Thread t1 = new Thread(new MyTask());
	Thread task = new Thread(new MyThreadRun());
	task.start();

}

static class MyThreadRun implements Runnable {
	@Override
	public void run() {
        System.out.println("Runnable thread: " + Thread.currentThread().getName());
    }
}

// ================================================================================
// 3. Using lambda expression (Java 8+)
// ================================================================================
static void LambdaExpressionMain() throws Exception {
	System.out.println("==================================== lambda expression");

	Runnable task = () -> {
		System.out.println("Runnable thread: " + Thread.currentThread().getName());
	};


	Thread t1 = new Thread(task, "Thread 1");
	Thread t2 = new Thread(task, "Thread 2");
	Thread t3 = new Thread(task, "Thread 3");

	t1.start();
	t2.start();
	t3.start();

	// This is more concise and commonly used in modern Java code. You can also reuse the same lambda for multiple threads:



	// Loop to create multiple threads
	Object lock = new Object();
	for(int i = 10; i<15; i++) {	
	int loopId = i;	
		synchronized (lock) {
			Thread task1 = new Thread(() -> {
				System.out.println("Loop thread: " + loopId + "---" + Thread.currentThread().getName());
			});

			task1.start();
			task1.join(); 
		}
	}


	// Loop to create multiple threads using Runable
	for (int i = 0; i<= 5; i++) {
		int id = i;
		Runnable Task = () -> {
			System.out.println("Runnable thread: " + id  + " ---" + Thread.currentThread().getName());
		};
		new Thread(Task).start();
	}

}



// ================================================================================
// 4. Race Condition and Synchronization 
// Synchronization :: prevents multiple threads from accessing shared resources simultaneously,  thread safety
// Use Case: ticket booking system where multiple users try to book the last seat simultaneously
// ================================================================================
// Race Condition :: Two threads trying to withdraw money from the same bank account at the same time, leading to incorrect balance updates.
static void RaceConditionMain() {
	System.out.println("==================================== Race Condition");

}


// To Fix race condition, we can synchronize the method:



// ================================================================================
// 5. ReentrantLock :: is a class in Java (java.util.concurrent.locks) that provides an explicit and more flexible locking mechanism than synchronized.
// Real-world Example: Bank account withdrawal with explicit locking
// ================================================================================
static void ReentrantLockMain() {
	System.out.println("==================================== ReentrantLock");

}



// Output:
// User-1 booked seat
// User-2 booked seat   ❌ (wrong – only 1 seat!)
// User-1 booked seat


// ================================================================================
// 6. AtomicInteger:: provides thread-safe operations without synchronization - useful for counters in concurrent programming
// Use Case: Counting the number of requests handled by a server in a multi-threaded environment without using synchronized blocks.
// Example: Ticket booking system where multiple users try to book the last seat simultaneously
// ================================================================================
static void AtomicIntegerMain() {
	System.out.println("==================================== AtomicInteger");
	TicketBooking ticket = new TicketBooking();

	for (int i = 0; i < 5 ; i++ ) {
		Runnable task = () -> {
			ticket.BookTicket();
		};
		new Thread(task).start();
	}	
}

static class TicketBooking {
	AtomicInteger ticket = new AtomicInteger(3);

	public void BookTicket () {
		int balanceTicket =  ticket.getAndDecrement();
		if(balanceTicket > 0) {
			try {
				// Thread.sleep(3000);
				wait();
			} catch (Exception e) {

			}
			System.out.println("Ticket is booked. " + Thread.currentThread().getName());
		} else {
			System.out.println("Ticket is not available");
		}
	}
}



// ================================================================================
// 7. Volatile:: ensures variable changes are immediately visible to all threads (prevents caching issues)
// Use Case: A flag to stop a thread gracefully from another thread without using synchronization.
// Example: A background task that should stop when a flag is set to false.
// ================================================================================
// Real-world Example: A flag to stop a thread gracefully from another thread without using synchronization.
static void VolatileMain() throws Exception {
	System.out.println("==================================== Volatile");

	TaskVol task = new TaskVol();
	Thread worker = new Thread(task);
	worker.start();
	Thread.sleep(3000);

	task.stop();
	System.out.println("Stoped by main thread");
}

static class TaskVol implements Runnable {
	private volatile boolean running = false;

	public void stop() {
		running = false;
	}

	public void run () {
		while(running) {
			System.out.println("Task running...");
		}
		System.out.println("Task is not running...");
	}
}


// ================================================================================
// 8. sleep() :: pauses the current thread for a specified time but does not release the lock.
// wait() :: pauses the thread and releases the lock, allowing other threads to execute. It must be used inside a synchronized block.
// Example: printer class where one thread is printing and sleeps in between, while another thread tries to print but has to wait for the lock to be released.
// ================================================================================
static void SleepWaitMain() {
	System.out.println("==================================== sleep, wait, notify");

}



// Output:
// Waiting for paper...
// Paper added
// Printing documents...


// ================================================================================
// 9. ConcurrentHashMap: is a thread-safe implementation of Map that allows multiple threads to read and write data concurrently without locking the entire map.
// Example: A web application that maintains a concurrent cache of user sessions using ConcurrentHashMap.
// ================================================================================
// Real-world Example: Counting word frequency in logs using ConcurrentHashMap
static void ConcurrentHashMapMain() {
	System.out.println("==================================== ConcurrentHashMap");

	SessionManager user = new SessionManager();
	user.login("user1");
	user.login("user2");

	System.out.println("---" + user.getSession("user1").userid);

	user.logut("user1");

}

static class SessionManager {
	private static ConcurrentHashMap<String, UserSession> sessionCache = new ConcurrentHashMap<>();

	public static void login (String userid) {
		sessionCache.put(userid, new UserSession(userid));
	}

	public static UserSession getSession(String userid) {
		return sessionCache.get(userid);
	}

	public static void logut (String userid) {
		sessionCache.remove(userid);
	}
}

static class UserSession  {
	String userid;
	Long loginTime;

	UserSession (String userid) {
		this.userid = userid;
		this.loginTime = System.currentTimeMillis();
	}
}


// ================================================================================
// 10. ExecutorService :: is a Java API to manage thread pools and execute tasks asynchronously, in the background.
// Example: A web server that uses ExecutorService to handle incoming HTTP requests concurrently without blocking the main thread.
// ================================================================================
static void ExecutorServiceMain() {
	System.out.println("==================================== ExecutorService ");
	ExecutorService executor = Executors.newFixedThreadPool(30);

	for(int i = 1; i<=3; i++) {
		int orderid = i;
		executor.submit(() -> {
			System.out.println("Hello == " + orderid + " --- " + Thread.currentThread().getName());
		});

	}
	executor.shutdown();
}



// ================================================================================
// 11. CompletableFuture :: is used to run asynchronous tasks and combine multiple async operations.
// Example: A web application that retrieves user data from a database and then calls an external API to get additional information, all without blocking the main thread.
// ================================================================================
// Real-world Example: Calling multiple APIs in parallel and combining results
static void CompletableFutureMain() {
	System.out.println("==================================== CompletableFuture ");

}



// ================================================================================
// 12. LRU Cache
// ================================================================================
// Calling external APIs (payment/user service) is expensive → cache response.
static void LruCacheMain() {
	System.out.println("==================================== LRU Cache ");
}


// 1. Generic LRU Cache
// LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)



// 2. API Cache Example


// 3. Product Cache (DB Example)



// 4. Session Cache




}