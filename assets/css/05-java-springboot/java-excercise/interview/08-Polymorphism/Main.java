public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.sound();

        a = new Cat();
        a.sound();
    }
}

class Animal {
    void sound() {
        System.out.println("some sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("bark");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("meow");
    }
}

