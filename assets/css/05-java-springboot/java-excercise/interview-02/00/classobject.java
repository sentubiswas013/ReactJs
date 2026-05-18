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

        System.out.println("=========================== classConstructorDemo");

    }

    // ============================================================
    // 02. Final Variable + Final Method
    // ============================================================

    public static void finalVariableMethodDemo() {

        System.out.println("=========================== finalVariableMethodDemo");

    }

    // ============================================================
    // 03. Final Class (cannot be extended)
    // ============================================================

    public static void finalClassDemo() {

        System.out.println("=========================== finalClassDemo");

    }

    // ============================================================
    // 04. Interface
    // ============================================================

    public static void interfaceDemo() {

        System.out.println("=========================== interfaceDemo");

    }

    // ============================================================
    // 05. this & super
    // ============================================================

    public static void thisAndSuper() {

        System.out.println("=========================== thisAndSuper");
        
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

        System.out.println("=========================== immutableClassDemo");

    }
}