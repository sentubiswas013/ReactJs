import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;

class ThreadOne {
	public static void main(String[] args) throws Exception {		 
		ThreadclassMain();
		RunnableInterfaceMain();
		LambdaExpressionMain();
		RaceConditionMain();
		ReentrantLockMain();
		AtomicIntegerMain();
		VolatileMain();
		SleepWaitMain();
		ConcurrentHashMapMain();
		ExecutorServiceMain();
		CompletableFutureMain();
		LruCacheMain();
	}


// ============================================================
// 1. Using Thread class
// ============================================================
static void ThreadclassMain() {
	System.out.println("========================== Thread class");
	
}



// ============================================================
// 2. Using Runnable interface
// ============================================================
static void RunnableInterfaceMain() {
	System.out.println("========================== Runnable interface");
	

}


// ============================================================
// 3. Using lambda expression (Java 8+)
// ============================================================
static void LambdaExpressionMain() {
	System.out.println("========================== lambda expression");



	// This is more concise and commonly used in modern Java code. You can also reuse the same lambda for multiple threads:


	// Loop to create multiple threads



	// Loop to create multiple threads using Runable
}





// ============================================================
// 4. Race Condition and Synchronization 
// Synchronization :: prevents multiple threads from accessing shared resources simultaneously,  thread safety
// Use Case: ticket booking system where multiple users try to book the last seat simultaneously
// ============================================================
// Race Condition :: Two threads trying to withdraw money from the same bank account at the same time, leading to incorrect balance updates.
static void RaceConditionMain() {
	System.out.println("========================== Race Condition");

}


// To Fix race condition, we can synchronize the method:



// ============================================================
// 5. ReentrantLock :: is a class in Java (java.util.concurrent.locks) that provides an explicit and more flexible locking mechanism than synchronized.
// Real-world Example: Bank account withdrawal with explicit locking
// ============================================================
static void ReentrantLockMain() {
	System.out.println("========================== ReentrantLock");

}



// Output:
// User-1 booked seat
// User-2 booked seat   ❌ (wrong – only 1 seat!)
// User-1 booked seat


// ============================================================
// 6. AtomicInteger:: provides thread-safe operations without synchronization - useful for counters in concurrent programming
// Use Case: Counting the number of requests handled by a server in a multi-threaded environment without using synchronized blocks.
// Example: Ticket booking system where multiple users try to book the last seat simultaneously
// ============================================================
static void AtomicIntegerMain() {
	System.out.println("========================== AtomicInteger");

}



// ============================================================
// 7. Volatile:: ensures variable changes are immediately visible to all threads (prevents caching issues)
// Use Case: A flag to stop a thread gracefully from another thread without using synchronization.
// Example: A background task that should stop when a flag is set to false.
// ============================================================
// Real-world Example: A flag to stop a thread gracefully from another thread without using synchronization.
static void VolatileMain() {
	System.out.println("========================== Volatile");

}



// ============================================================
// 8. sleep() :: pauses the current thread for a specified time but does not release the lock.
// wait() :: pauses the thread and releases the lock, allowing other threads to execute. It must be used inside a synchronized block.
// Example: printer class where one thread is printing and sleeps in between, while another thread tries to print but has to wait for the lock to be released.
// ============================================================
static void SleepWaitMain() {
	System.out.println("========================== sleep, wait, notify");

}



// Output:
// Waiting for paper...
// Paper added
// Printing documents...


// ============================================================
// 9. ConcurrentHashMap: is a thread-safe implementation of Map that allows multiple threads to read and write data concurrently without locking the entire map.
// Example: A web application that maintains a concurrent cache of user sessions using ConcurrentHashMap.
// ============================================================
// Real-world Example: Counting word frequency in logs using ConcurrentHashMap
static void ConcurrentHashMapMain() {
	System.out.println("========================== ConcurrentHashMap");

}



// ============================================================
// 10. ExecutorService :: is a Java API to manage thread pools and execute tasks asynchronously, in the background.
// Example: A web server that uses ExecutorService to handle incoming HTTP requests concurrently without blocking the main thread.
// ============================================================
static void ExecutorServiceMain() {
	System.out.println("========================== ExecutorService ");

}



// ============================================================
// 11. CompletableFuture :: is used to run asynchronous tasks and combine multiple async operations.
// Example: A web application that retrieves user data from a database and then calls an external API to get additional information, all without blocking the main thread.
// ============================================================
// Real-world Example: Calling multiple APIs in parallel and combining results
static void CompletableFutureMain() {
	System.out.println("========================== CompletableFuture ");

}



// ============================================================
// 12. LRU Cache
// ============================================================
// Calling external APIs (payment/user service) is expensive → cache response.
static void LruCacheMain() {
	System.out.println("========================== LRU Cache ");
}


// 1. Generic LRU Cache
// LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)



// 2. API Cache Example


// 3. Product Cache (DB Example)



// 4. Session Cache




}