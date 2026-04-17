class A {
    static void print() {
        System.out.println("A");
    }
}
 
class B extends A {
    static void print() {
        System.out.println("B");
    }
}
 
public class Test {
    public static void main(String[] args) {
        A obj = new B();
        obj.print();
    }
}
Output: A
==============================================
class A {
    void print() {
        System.out.println("A");
    }
}
 
class B extends A {
    void print() {
        System.out.println("B");
    }
}
 
public class Test {
    public static void main(String[] args) {
        A obj = new B();
        obj.print();
    }
}
Output: B

==============================================

public class Test1 {
            static int foo() {
                try {
                    return 1;
                } catch (Exception e) {
                    return 2;
                } finally {
                    return 3;
                }
            }
            public static void main(String[] args) {
                System.out.println(Test1.foo());
            }
        }
        
        // Output: 
-----------------------------------------------------------------------
 
        public class Test2 {
            public static void main(String[] args) {
                try {
                    Object o = null;
                    System.out.println(o.toString()); 
                } catch (RuntimeException e) {
                    System.out.println("Runtime");
                } catch (NullPointerException e) {
                    System.out.println("NPE");
                } 
            } 
        }
        // Output: Compile-Time Error (No Output)
        // NullPointerException is a subclass of RuntimeException.
        
        try {
            Object o = null;
            System.out.println(o.toString());
        } catch (NullPointerException e) {
            System.out.println("NPE");
        } catch (RuntimeException e) {
            System.out.println("Runtime");
        }
        // NPE

-----------------------------------------------------------------------
        class Parent {
            String name = "Parent";
            static void who() { System.out.println("Parent.who"); }
            void say() { System.out.println("Parent.say: " + name); }
        }
        class Child extends Parent {
            String name = "Child";
            static void who() { System.out.println("Child.who"); }
            @Override
            void say() { System.out.println("Child.say: " + name); }
        }
        public class Test1 {
            public static void main(String[] args) {
                Parent p = new Child();
                System.out.println(p.name);
                p.say();
                p.who();
            }
        }
        // Output: 
        Parent
        Child.say: Child
        Parent.who
-----------------------------------------------------------------------
 
TreeMap<String, String> map = new TreeMap<>();
       map.put("Rohit", "1");
       map.put("Prathmesh", "2");
       map.put("Ramesh", "3");
       map.put("Rohit", "4");
       System.out.println(map);
       
       {Prathmesh=2, Ramesh=3, Rohit=4}
--------------------------------------------------------

class A{
    A(){
        System.out.println("A"):
    }
}
class B extends A{
    B(){
        System.out.println("B");
    }
}
class C extends B{
      C(){
        System.out.println("C"):
      }
    } 
    
    C c = new C(); 
    c.A();
    c.B();
    c.C();
    
    // Output
    A
    B
    C

===========================================