import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;

// ============================================================
// 1. Using Thread class
// ============================================================
class ThreadClassExample {
    public static void main(String[] args) {
        // System.out.println(" Thread class =====================");
        HashMap<MyKey, String> map = new HashMap<>();

        // Add many keys with same hashCode
        for (int i = 1; i <= 10; i++) {
            map.put(new MyKey(i), "Value" + i);
        }

        System.out.println("Size: " + map.size());
    }
}



// ============================================================
// 2. Using Runnable interface
// ============================================================
class RunnableExample {
     public static void main(String[] args) throws InterruptedException {
        System.out.println(" Runnable interface ====================");
        
    }
}



// ============================================================
// 3. Using lambda expression (Java 8+)
// ============================================================
class LambdaThreadExample1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(" lambda ====================");
    }
}

// This is more concise and commonly used in modern Java code. You can also reuse the same lambda for multiple threads:
class LambdaThreadExample2 {
    public static void main(String[] args) {
        System.out.println(" Runnable =====================");
        
    }
}

// ============================================================
// 4. Synchronization prevents multiple threads from accessing shared resources simultaneously, ensuring thread safety
// Use Case : Bank account withdrawal where multiple users try to withdraw money at the same time, leading to inconsistent balance if not synchronized.
// ============================================================
// Real-world Example: Bank account withdrawal where multiple users try to withdraw money at the same time, leading to inconsistent balance if not synchronized.
class SynchronizationExample {
    public static void main(String[] args) {
        System.out.println(" synchronization =====================");
        
    }
}



// ============================================================
// 5. Volatile ensures variable changes are immediately visible to all threads (prevents caching issues)
// Use Case: A flag to stop a thread gracefully from another thread without using synchronization.
// Example: A background task that should stop when a flag is set to false.
// ============================================================
// Real-world Example: A flag to stop a thread gracefully from another thread without using synchronization.
class VolatileExample {
    public static void main(String[] args) throws Exception {
        System.out.println(" Volatile =====================");
        
    }
}



// ============================================================
// 6. AtomicInteger provides thread-safe operations without synchronization - useful for counters in concurrent programming
// Use Case: Counting the number of requests handled by a server in a multi-threaded environment without using synchronized blocks.
// Example: Ticket booking system where multiple users try to book the last seat simultaneously
// ============================================================
class AtomicIntegerExp {
    public static void main(String[] args) throws Exception {
        System.out.println(" AtomicInteger =====================");
        
    }
}


// ============================================================
// 7. Sleep pauses thread execution for specified time but keeps locks (can cause blocking)
// Example: A thread that holds a lock and goes to sleep, preventing other threads from accessing the locked resource.
// ============================================================
class SleepExample {
    public static void main(String[] args) {
        System.out.println(" Sleep =====================");
        
    }
}

// ============================================================
// 8. wait/notify
// Example: message passing between producer and consumer threads using wait/notify for synchronization.
// ============================================================
class WaitNotifyExample {
    public static void main(String[] args) throws Exception {
        System.out.println(" WaitNotify =====================");
    }
}


// ============================================================
// 9. ConcurrentHashMap: Thread-safe HashMap that allows multiple threads to read/write simultaneously without external synchronization
// Example: A web application that maintains a concurrent cache of user sessions using ConcurrentHashMap.
// ============================================================
// Real-world Example: Counting word frequency in logs using ConcurrentHashMap
class SessionManager {
    public static void main(String[] args) {
        System.out.println("ConcurrentHashMap =====================");

    }
}



// ============================================================
// 10. ExecutorService: A thread pool manager that handles task execution without manually creating/managing threads. Provides submit(), execute(), and shutdown() methods for concurrent task processing.
// Example: A web server that uses ExecutorService to handle incoming HTTP requests concurrently without blocking the main thread.
// ============================================================
// Real-world Example: Processing multiple orders in parallel using ExecutorService
class ExecutorServiceRealTimeExample {
    public static void main(String[] args) throws Exception {
        System.out.println("ExecutorService =====================");

        
    }


}

// ============================================================
// 11. CompletableFuture is a powerful class in Java that allows you to write asynchronous, non-blocking code. It provides a way to handle the result of an asynchronous computation and chain multiple computations together.
// Example: A web application that retrieves user data from a database and then calls an external API to get additional information, all without blocking the main thread.
// ============================================================
// Real-world Example: Calling multiple APIs in parallel and combining results
class CompletableFutureRealTimeExample {
    public static void main(String[] args) {
        System.out.println("CompletableFuture =====================");

        
    }

}

// ============================================================
// 12. ReentrantLock
// Real-world Example: Bank account withdrawal with explicit locking
// ============================================================
class ReentrantLockExampleTwo {
    public static void main(String[] args) {
        System.out.println("ReentrantLock =======================");
        

    }
}



// ============================================================
// 13. Race Condition
// ============================================================
// Real-world Example: Booking system where multiple users try to book the last seat simultaneously
class RaceConditionExample {
    public static void main(String[] args) throws Exception {
        System.out.println("Race Condition =======================");


    }
}



// To Fix race condition, we can synchronize the method:



// Output:
// User-1 booked seat
// User-2 booked seat   ❌ (wrong – only 1 seat!)


// ============================================================
// 14. LRU Cache
// ============================================================
// Calling external APIs (payment/user service) is expensive → cache response.

class LRUCacheExample {
    public static void main(String[] args) {
        System.out.println("LRUCache =======================");
        System.out.println("ProductService -----------");

    

        System.out.println("ApiService ----------------");

       

        System.out.println("SessionService -----------");

        
    }
}

// 1. Generic LRU Cache
// LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)


// 2. API Cache Example


// 3. Product Cache (DB Example)


// 4. Session Cache

