public class Main {
    public static void main(String[] args) {
        C c = new C();
        c.f();
        c.g();
        c.h();

        D d = new D();
        d.f();
        d.i();
    }
}

class A {
    void f() {
        System.out.println("A");
    }
}

class B extends A {                  // Single
    void g() {
        System.out.println("B");
    }
}

class C extends B {                  // Multilevel
    void h() {
        System.out.println("C");
    }
}

class D extends A {                  // Hierarchical
    void i() {
        System.out.println("D");
    }
}

