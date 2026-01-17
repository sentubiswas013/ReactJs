public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        p.name = "Alice";
        p.age = 25;
        p.introduce();
    }
}
class Person {
    String name;
    int age;

    void introduce() {
        System.out.println(name + ", " + age);
    }
}
