package assignment4.djava;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Employee emp = new Employee("Casten", 42, 1337);
		
		Box box = new Box(emp);
		box.get("assignment4.djava.Employee.salary");
		
//		Box box = new Box("assignment4.djava.Employee",null);
//		box.get("assignment4.djava.Employee.salary");
		
	}

}
