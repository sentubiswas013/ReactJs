public class Main {

    public static void main(String[] args) {

        // 01. Class, Constructor + Static + Final Variable
        classConstructorDemo();

        // 02. Final Variable + Final Method
        finalVariableMethodDemo();

        // 03. Final Class (cannot be extended)
        finalClassDemo();

        // 04. Interface
        interfaceDemo();

        // 05. this & super
        thisAndSuper();

        // 06. Immutable Class
        immutableClassDemo();
    }

    // ============================================================
    // 01. Class, Constructor + Static + Final Variable
    // ============================================================

    public static void classConstructorDemo() {

    }

    // ============================================================
    // 02. Final Variable + Final Method
    // ============================================================

    public static void finalVariableMethodDemo() {

    }

    // ============================================================
    // 03. Final Class (cannot be extended)
    // ============================================================

    public static void finalClassDemo() {

    }

    // ============================================================
    // 04. Interface
    // ============================================================

    public static void interfaceDemo() {

    }

    // ============================================================
    // 05. this & super
    // ============================================================

    public static void thisAndSuper() {
        User us1 = new User();
        us1.display();

        // Student stu = new Student();
        // stu.Student();
    }

    static class Student {
        int age = 10;
        public Student () {
            System.out.println("My age" + age);
        }
    }

    static class User extends Student{
        int age = 30;

        User() {
            super();
        }
       
        public void display () {
            System.out.println("My Age is 10" + this.age);
            System.out.println("My Age us 12" + super.age);
        }
    }

    // Output
    // Parent Constructor
    // this.msg: Child
    // super.msg: Parent

    // ============================================================
    // 06. Immutable class is a class whose object state
    // cannot be changed after it is created.
    // ============================================================

    public static void immutableClassDemo() {

    }
}