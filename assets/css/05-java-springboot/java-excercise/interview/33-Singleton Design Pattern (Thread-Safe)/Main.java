public class Main {
    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
    }
}

// ─────────────────────────────────────────────────────────────
// SINGLETON RULES
// ─────────────────────────────────────────────────────────────
// 1. PRIVATE constructor         — prevents `new Singleton()` from outside
// 2. PRIVATE STATIC instance     — holds the one and only object
// 3. VOLATILE keyword            — ensures visibility across threads (no stale cache)
// 4. PUBLIC STATIC getInstance() — the only way to access the instance
// 5. DOUBLE-CHECKED LOCKING      — first null check avoids lock overhead after init
//                                  second null check inside sync block prevents
//                                  duplicate creation when two threads pass first check
// 6. SYNCHRONIZED block          — locks only on creation, not on every call (performance)
// ─────────────────────────────────────────────────────────────

class Singleton {

    private static volatile Singleton instance;  // Rule 2 + 3

    private Singleton() {}                       // Rule 1

    public static Singleton getInstance() {      // Rule 4
        if (instance == null) {                  // Rule 5 — first check (no lock)
            synchronized (Singleton.class) {     // Rule 6 — lock only when needed
                if (instance == null) {          // Rule 5 — second check (inside lock)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}


