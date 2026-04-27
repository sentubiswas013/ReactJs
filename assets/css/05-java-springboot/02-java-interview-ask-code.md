
## 1. How to get UNIQUE emails (important)

```Java
import java.util.*;

class Main {
    public static void main(String[] args) {

        Set<User> userEmail = new LinkedHashSet<>(Arrays.asList(
            new User("abc@gmail.com"),
            new User("abcd@gmail.com"),
            new User("abc@gmail.com") // duplicate
        ));

        System.out.println("Unique Emails:");
        for (User user : userEmail) {
            System.out.println(user);
        }
    }
}

class User {
    String email;

    User(String email) {
        this.email = email;
    }

    // 🔥 Important for uniqueness
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    // For clean printing
    @Override
    public String toString() {
        return email;
    }
}
```

## 2. How to get UNIQUE emails using Java Stream API

```Java
class Test {
    private int count = 0;

    // synchronized method to ensure thread safety
    public synchronized void countTest() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class Main {
    public static void main(String[] args) throws InterruptedException {

        Test test = new Test();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                test.countTest();
            }
            System.out.println("Thread 1 done");
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                test.countTest();
            }
            System.out.println("Thread 2 done");
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                test.countTest();
            }
            System.out.println("Thread 3 done");
        });

        // Start threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for threads to finish
        t1.join();
        t2.join();
        t3.join();

        // Final result
        System.out.println("Final Count: " + test.getCount());
    }
}

// Output
Thread 1 done
Thread 2 done
Thread 3 done
Final Count: 3000
```
                