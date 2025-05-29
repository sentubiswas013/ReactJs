# Top 1000 Java Interview Question & Answers

## Multi-threading
### 144. **How does Multi-threading work in Java?**

Multi-threading in Java allows a program to execute multiple threads concurrently. A **thread** is a lightweight process that shares the resources of its parent process. Java provides built-in support for multi-threading, making it easier to implement parallel processing.

Here's how multi-threading works in Java:

1. **Thread Creation**: You can create a thread in Java in two ways:
   - By **extending the `Thread` class** and overriding its `run()` method.
   - By **implementing the `Runnable` interface** and passing it to a `Thread` object.

2. **Starting a Thread**: Once the thread is created, it can be started using the `start()` method. This invokes the `run()` method, which contains the code that the thread will execute.

3. **Thread Scheduling**: The Java Virtual Machine (JVM) and the operating system handle thread scheduling. Threads are scheduled by the JVM, and they run based on the system's available resources and scheduling algorithms.

4. **Context Switching**: The JVM performs context switching, where it saves the state of a running thread and loads the state of another thread. This gives the illusion of simultaneous execution, even though, in many cases, threads may run on a single processor.

5. **Synchronization**: To avoid race conditions and ensure thread safety, Java provides synchronization mechanisms like the `synchronized` keyword to control access to shared resources.

**Example of creating a thread by extending `Thread`:**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class Example {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();  // Start the thread
    }
}
```

---

### 145. **What are the advantages of Multithreading?**

The main advantages of multi-threading are:

1. **Improved Performance**:
   - Multithreading allows tasks to be executed concurrently, improving the overall performance, especially on multi-core processors where multiple threads can run simultaneously.

2. **Better Resource Utilization**:
   - With multi-threading, resources such as CPU time, memory, and I/O operations are utilized more efficiently, especially when performing tasks that are I/O bound (e.g., file reading, network communication).

3. **Responsiveness**:
   - Multithreading improves the responsiveness of applications. For example, in GUI applications, while one thread handles the user interface, another can handle background tasks like file processing or network communication.

4. **Simplified Program Structure**:
   - Complex tasks like downloading files, processing user input, or handling multiple user requests can be handled more easily by splitting them into smaller, concurrent threads.

5. **Scalability**:
   - Applications that use multi-threading can scale better to handle increased load or more tasks without a significant degradation in performance.

---

### 146. **What are the disadvantages of Multithreading?**

Despite its advantages, multithreading has some disadvantages:

1. **Complexity**:
   - Writing multithreaded programs is complex and error-prone. Issues like race conditions, deadlocks, and thread synchronization can make debugging difficult.

2. **Context Switching Overhead**:
   - Context switching between threads incurs an overhead, as the CPU must save and load the state of each thread. If there are too many threads, this can lead to a performance loss due to excessive switching.

3. **Synchronization Issues**:
   - When multiple threads access shared resources, proper synchronization is required to avoid data inconsistency and corruption. This adds complexity and can lead to performance bottlenecks if not done correctly.

4. **Deadlock**:
   - If two or more threads are waiting on each other to release resources, a deadlock can occur, causing the threads to be stuck indefinitely. Proper management of resources is needed to prevent this.

5. **Increased Memory Usage**:
   - Each thread consumes memory for its stack and execution context. If there are too many threads, it could lead to high memory usage, especially in environments with limited resources.

6. **Difficult to Debug**:
   - Multithreaded applications can be difficult to debug because errors such as race conditions may not always occur consistently, making the problems hard to reproduce.

---

### 147. **What is a Thread in Java?**

A **Thread** in Java is a lightweight process that allows for the concurrent execution of code. Every Java program has at least one thread, known as the **main thread**. In a multithreaded application, multiple threads run independently but share resources such as memory, CPU time, and I/O devices.

Key points about threads in Java:
- **Thread Class**: Java provides a built-in `Thread` class, which is used to create and manage threads.
- **Runnable Interface**: Java also provides the `Runnable` interface, which is used to define the task to be executed by a thread.
- **Thread Lifecycle**: A thread goes through several states during its lifecycle, including:
  - **New**: When a thread is created but not yet started.
  - **Runnable**: When the thread is ready to run or is running.
  - **Blocked**: When a thread is waiting to acquire a lock or resource.
  - **Waiting**: When a thread is waiting for another thread to complete.
  - **Terminated**: When a thread finishes executing.

**Example:**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class Example {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();  // Start the thread
    }
}
```

In this example, `MyThread` extends the `Thread` class, overrides the `run()` method, and starts the thread using `start()`. The `run()` method contains the code that the thread will execute.

---

### Summary:
- **Multi-threading** in Java allows multiple threads to execute concurrently, enabling tasks to run in parallel and improving performance.
- **Advantages** of multi-threading include improved performance, better resource utilization, and scalability.
- **Disadvantages** include complexity, synchronization issues, overhead due to context switching, and the potential for deadlocks.
- A **Thread** in Java is a lightweight unit of execution, and it can be created by either extending the `Thread` class or implementing the `Runnable` interface.

### 148. **What is a Thread’s priority and how is it used in scheduling?**

A **Thread’s priority** is a value that determines the relative importance of a thread when it comes to scheduling and execution by the Java Virtual Machine (JVM) and underlying operating system. Threads with higher priority are typically executed before threads with lower priority, but this depends on the thread scheduling policy used by the operating system.

In Java, thread priorities are represented by integers, with a range from `Thread.MIN_PRIORITY` (1) to `Thread.MAX_PRIORITY` (10). The default priority is `Thread.NORM_PRIORITY` (5).

**How thread priority is used in scheduling**:
- Thread priorities are used by the **thread scheduler** to determine the order in which threads are executed. If two or more threads are ready to run, the thread with the higher priority will generally be selected for execution first.
- However, thread priority only affects the **relative priority** of threads. It does not guarantee that a higher-priority thread will always run before a lower-priority thread, as the actual behavior depends on the OS’s scheduling policy and the JVM implementation.

**Example of setting thread priority:**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class ThreadPriorityExample {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        
        thread1.setPriority(Thread.MAX_PRIORITY);  // Set high priority
        thread2.setPriority(Thread.MIN_PRIORITY);  // Set low priority
        
        thread1.start();
        thread2.start();
    }
}
```

---

### 149. **What are the differences between Pre-emptive Scheduling Scheduler and Time Slicing Scheduler?**

**Pre-emptive Scheduling Scheduler**:
- In **pre-emptive scheduling**, the thread with higher priority can **interrupt and preempt** a running thread of lower priority. 
- The system can forcibly stop a currently running thread and give CPU control to the higher-priority thread.
- This is commonly used in operating systems like **Windows** or **Unix** where tasks are prioritized.
- **Advantage**: More efficient for time-critical applications as it ensures high-priority tasks get more CPU time.
- **Disadvantage**: Can lead to **starvation** of lower-priority threads if the higher-priority threads keep executing.

**Time Slicing Scheduler**:
- **Time slicing**, also known as **round-robin scheduling**, divides the CPU time into small intervals (or slices) and assigns each thread a fixed slice of time.
- When a thread’s time slice expires, the CPU is allocated to the next thread in the queue, regardless of the thread’s priority.
- **Advantage**: Ensures **fairness** among threads by giving each thread an equal opportunity to execute, preventing starvation.
- **Disadvantage**: Can be inefficient for tasks that require varying amounts of CPU time since each thread gets the same fixed time slice.

---

### 150. **Is it possible to call the run() method instead of start() on a thread in Java?**

Yes, it is possible to call the `run()` method directly on a thread object, but this is **not recommended** if you want to execute the thread concurrently.

- Calling the `run()` method directly does **not** start a new thread. Instead, it will simply execute the `run()` method in the **current thread** (the thread that called `run()`), which means the code inside `run()` will execute in the same thread.
- To start a new thread and allow it to execute concurrently, you must call `start()`, which internally invokes the `run()` method in a **new thread**.

**Example of calling `run()` directly:**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class RunExample {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.run();  // This will run in the current thread, not a new one
    }
}
```
In the above example, calling `run()` directly will execute the `run()` method on the main thread, not creating a new thread.

---

### 151. **How will you make a user thread into a daemon thread if it has already started?**

To convert a **user thread** into a **daemon thread** after it has started, you can use the `setDaemon()` method of the `Thread` class. The `setDaemon(true)` method must be called **before the thread is started**. If you try to set a daemon thread after the thread has started, it will throw an `IllegalThreadStateException`.

**Example**:
```java
class MyThread extends Thread {
    public void run() {
        while (true) {
            System.out.println("Daemon thread running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class DaemonThreadExample {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.setDaemon(true);  // Set the thread as daemon
        thread.start();  // Start the daemon thread
    }
}
```
In this example, `setDaemon(true)` is called before `thread.start()` to make the thread a **daemon thread**. Once the main thread finishes execution, the daemon thread will automatically terminate.

**Important Notes**:
- Daemon threads are typically used for background tasks that should not block the program from exiting.
- When all **non-daemon threads** in a Java program finish executing, the JVM terminates any **daemon threads**, even if they are still running.


### 152. **Can we start a thread two times in Java?**

No, **we cannot start a thread two times in Java**.

Once a thread's `start()` method is called, the thread enters the **"started"** state and begins execution. After a thread has finished its execution (i.e., when its `run()` method completes or it is terminated), it cannot be started again. Calling `start()` on a thread that has already been started will throw an **IllegalThreadStateException**.

To execute the task again, you would need to create a new instance of the `Thread` class and start that new thread.

**Example:**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();  // Start the thread
        
        // Trying to start the same thread again will cause an exception
        thread.start();  // Throws IllegalThreadStateException
    }
}
```
In this case, the second `thread.start()` will throw an exception because the thread has already been started.

---

### 153. **In what scenarios can we interrupt a thread?**

In Java, **interrupting a thread** is used to signal to the thread that it should stop what it is doing and perform a task to exit gracefully. The `interrupt()` method is called on a thread to interrupt its execution.

Here are some scenarios in which you can interrupt a thread:

1. **Stopping a thread**: If you want a thread to stop executing a long-running task, you can interrupt it. Threads that are sleeping, waiting, or blocked on I/O operations will respond to interruption, as it will throw an `InterruptedException` in these cases.

2. **Graceful shutdown of threads**: In applications where threads perform continuous tasks (e.g., a server or a background task), you can interrupt threads to stop their work and shut them down gracefully.

3. **Canceling a task**: If a thread is performing a task that takes a long time (e.g., downloading a file or waiting for a response from a server), you can interrupt the thread to stop the task.

**Example:**
```java
class MyThread extends Thread {
    public void run() {
        try {
            System.out.println("Thread is starting...");
            Thread.sleep(10000);  // Simulate long-running task
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }
    }
}

public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        
        Thread.sleep(2000);  // Let the thread run for a while
        thread.interrupt();  // Interrupt the thread
    }
}
```
In this case, the `interrupt()` method is used to interrupt the thread while it is sleeping, causing it to throw an `InterruptedException` and stop its execution.

---

### 154. **In Java, is it possible to lock an object for exclusive use by a thread?**

Yes, in Java, it is possible to lock an object for exclusive use by a thread using **synchronization**. The synchronization mechanism allows only one thread to execute a particular block of code (or method) at a time, ensuring that shared resources are accessed safely.

You can achieve this using the `synchronized` keyword, which can be applied to either:
1. **Methods**: The entire method is synchronized, meaning only one thread can execute the method at a time.
2. **Blocks**: A specific block of code can be synchronized, meaning only one thread can execute that block at a time.

**Example (method synchronization)**:
```java
class Counter {
    private int count = 0;
    
    // Synchronized method to ensure thread-safe increment
    public synchronized void increment() {
        count++;
    }
    
    public int getCount() {
        return count;
    }
}

public class SyncExample {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        
        // Create threads that increment the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        System.out.println("Final count: " + counter.getCount());
    }
}
```
In this example, the `increment()` method is synchronized, meaning only one thread can execute it at a time. This ensures that the counter is incremented correctly without race conditions.

---

### 155. **How `notify()` method is different from `notifyAll()` method?**

In Java, both `notify()` and `notifyAll()` are used to wake up threads that are **waiting** on a shared object’s monitor (using the `wait()` method), but they behave differently:

1. **`notify()`**:
   - The `notify()` method wakes up **one** thread that is currently **waiting** on the monitor of the object. 
   - If multiple threads are waiting, only one thread is chosen (the choice depends on the JVM and operating system’s thread scheduling).
   - The thread that is notified is not guaranteed to start immediately; it just becomes eligible for execution once it gets the lock.

2. **`notifyAll()`**:
   - The `notifyAll()` method wakes up **all** threads that are **waiting** on the monitor of the object.
   - All waiting threads are placed in the "ready" state, but only one thread will actually acquire the lock and proceed. The others will have to wait their turn.

**Example**:
```java
class SharedResource {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
        notify();  // Notify one waiting thread
    }
    
    public synchronized void waitForCount() throws InterruptedException {
        while (count == 0) {
            wait();  // Wait until count is incremented
        }
        System.out.println("Count: " + count);
    }
}

public class NotifyExample {
    public static void main(String[] args) throws InterruptedException {
        SharedResource resource = new SharedResource();
        
        // Create two threads that will wait for the count to be incremented
        Thread t1 = new Thread(() -> {
            try {
                resource.waitForCount();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        Thread t2 = new Thread(() -> {
            try {
                resource.waitForCount();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        t1.start();
        t2.start();
        
        Thread.sleep(1000);
        
        resource.increment();  // Notify one thread
        t1.join();
        t2.join();
    }
}
```
In this example:
- If `notify()` is used, only one thread will be woken up.
- If `notifyAll()` is used, both threads waiting on the `wait()` condition will be woken up.

---

### Summary:
- **Thread’s priority** influences the order in which threads are scheduled, but it doesn’t guarantee thread execution order.
- **Pre-emptive scheduling** allows higher-priority threads to interrupt lower-priority threads, while **time slicing** divides CPU time into fixed slices for each thread.
- A thread can only be started **once**; calling `start()` again on an already started thread will throw an `IllegalThreadStateException`.
- **Interrupting a thread** is commonly used to stop long-running tasks or to cancel a thread’s operation.
- **Object locking** for exclusive access is achieved via synchronization using `synchronized` methods or blocks.
- **`notify()`** wakes up a single waiting thread, while **`notifyAll()`** wakes up all waiting threads.