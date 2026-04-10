public class Main {
    public static void main(String[] args) {
        Car a = new Car();
        Car b = new Car("Toyota", "Camry", 2023);
        Car c = new Car(b);

        System.out.println(a.getInfo());
        System.out.println(b.getInfo());
        System.out.println(c.getInfo());
    }
}

class Car {
    String brand;
    String model;
    int year;

    Car() {
        this.brand = "Unknown";
        this.model = "Unknown";
        this.year = 0;
    }

    Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    Car(Car c) {
        this.brand = c.brand;
        this.model = c.model;
        this.year = c.year;
    }

    String getInfo() {
        return brand + " " + model + " (" + year + ")";
    }
}
// Output:
// Unknown Unknown (0)
// Toyota Camry (2023)
// Toyota Camry (2023)


