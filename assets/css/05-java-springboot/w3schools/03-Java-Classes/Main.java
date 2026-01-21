public class Main {

    public static void main(String[] args) {
        Car obj = new Car();
		obj.name = "Tata";
        obj.price = 2342;
        System.out.println("Hello World " +  obj.getCarDetails(obj.name, obj.price));
    }
}


public class Car {
	String name;
	int price;

    static String getCarDetails(String name, int price) {
        return "Engine Started " + name + " " + price;
    }
}