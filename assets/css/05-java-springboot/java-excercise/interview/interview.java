import java.util.*;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws IllegalThreadStateException  {
        
		// Section:  Thread
        PrintTask tr1 = new PrintTask();
		SaveTask tr2 = new SaveTask();		
		Thread tr3 = new Thread(new SendEmail());
		
		tr1.run();
		tr2.run();
		tr3.run();
		System.out.println("====================================");
		
		// Section: Singleton
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		
		System.out.println("S1 - " + s1);
		System.out.println("S2 - " + s2);
		System.out.println(s1 == s2);
		System.out.println("====================================");
		
		// Section: Immutable class
		Student st = new Student("Sentu");
		System.out.println("Immutable - " + st.getName());
		System.out.println("====================================");
		
	   Section: This and super
	   Child sup = new Child();
	   sup.name();
	   
	   
	   
	   
    }
}

// Section: Thread
class PrintTask extends Thread {
	public void run() {
		System.out.println("Run print"); 
	}
}

class SaveTask  extends Thread {
	public void run() {
		System.out.println("Save it");
	}
}

class SendEmail implements Runnable  {
	public void run () {
		System.out.println("Send email");
	}
}


// Section: Singleton
class Singleton {
	private static Singleton obj;
	private Singleton() {}
	
	public static Singleton getInstance() {
		if(obj == null) {
			obj = new Singleton();
		}
		return obj;
	}
}

// Section: Immutable class
final class Student {
	private final String name;
	
	
	public Student(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

// Section: This and super
class Parent {
	String name = "Sentu Biswas";
}

class Child extends Parent {
	String name = "Pintu Biswas";

	void name () {
		System.out.println(this.name);
		System.out.println(super.name);
	}
}

// Section:  Collections create
class Collections {
	void ArrayListNum () {
		List<String> names = new ArrayList<>();
		names.add("Sentu");
		names.add("Pintu");
	}
	
	void HashSetNums() {
		Set<String> names = new HashSet<>();
		names.add("Avi");
		names.add("Roop");
	}
	
	void HashMapNums() {
		Map<String, Integer> names = new HashMap<>();
		names.put("Ranku", 1);
		names.put("Biswa", 2);
	}
}
