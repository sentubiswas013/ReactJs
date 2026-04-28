
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

## Problem 1 
## Implement a method to find the median of two sorted arrays of different lengths in O(log(min(m,n))) time complexity (hard merge would be too slow for large inputs). 

Return the median as double.
Sample Input/Output:
nums1 = [1,3], nums2 = [2] → 2.0
nums1 = [1,2], nums2 = [3,4] → 2.5
nums1 = [], nums2 = [1] → 1.0
nums1 = [2], nums2 = [] → 2.0

```Java
public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

    if (nums1.length > nums2.length)
        return findMedianSortedArrays(nums2, nums1);

    int m = nums1.length, n = nums2.length;
    int low = 0, high = m;

    while (low <= high) {
        int cut1 = (low + high) / 2;
        int cut2 = (m + n + 1) / 2 - cut1;

        int l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
        int r1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];

        int l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
        int r2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

        if (l1 <= r2 && l2 <= r1) {
            if ((m + n) % 2 == 0)
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            else
                return Math.max(l1, l2);
        } else if (l1 > r2) {
            high = cut1 - 1;
        } else {
            low = cut1 + 1;
        }
    }
    return 0;
}
```

## Problem Statement: An existing Spring Boot service processes a large list of "Blue Card" insurance claims and performs a heavy calculation on each. The current implementation uses a single-threaded approach, leading to high latency in the RTM Portal.
Inefficient Code:
 
public List<ClaimResult> processClaims(List<Claim> claims) {
		return claims.stream()
        .map(claim -> performHeavyValidation(claim)) // Sync execution
        .collect(Collectors.toList());
}


✅ Solution 1: Parallel Stream (Simple)
```Java
public List<ClaimResult> processClaims(List<Claim> claims) {
    return claims.parallelStream()
            .map(this::performHeavyValidation)
            .toList();
}
```


✅ Solution 2: ExecutorService (Best Practice)
```Java
import java.util.concurrent.*;

public List<ClaimResult> processClaims(List<Claim> claims) {

    ExecutorService executor = Executors.newFixedThreadPool(5);

    List<Future<ClaimResult>> futures = claims.stream()
            .map(claim -> executor.submit(() -> performHeavyValidation(claim)))
            .toList();

    List<ClaimResult> results = new ArrayList<>();

    for (Future<ClaimResult> future : futures) {
        try {
            results.add(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    executor.shutdown();
    return results;
}
```

🔥 Solution 3: CompletableFuture (Modern)
```Java
import java.util.concurrent.*;

public List<ClaimResult> processClaims(List<Claim> claims) {

    return claims.stream()
            .map(claim -> CompletableFuture.supplyAsync(
                    () -> performHeavyValidation(claim)))
            .map(CompletableFuture::join)
            .toList();
}
```