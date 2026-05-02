import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;
// ============================================================
// 1. Using Thread class
// ============================================================



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
// Main Class (Simulation)





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



// To Fix race condition, we can synchronize the method:



// Output:
// User-1 booked seat
// User-2 booked seat   ❌ (wrong – only 1 seat!)


// ============================================================
// 16. LRU Cache
// ============================================================



// Real-world Example: Caching DB results, API responses, etc.



// Fetching user data from DB is slow → cache recent results.



// Calling external APIs (payment/user service) is expensive → cache response.




// Frequently viewed products → cache to reduce DB load.




// Session Cache: Store recently active user sessions.

class SessionService {
    private final LRUCache<String, String> sessionCache = new LRUCache<>(2);

    public void login(String userId) {
        sessionCache.put(userId, "active");
        System.out.println("User login in " + userId);
    }

    public boolean isActive (String userId) {
        return sessionCache.containsKey(userId);
    }
}

// ============================================================
// 17. You need to implement a caching mechanism without using external libraries. How do you do it?
// ============================================================
