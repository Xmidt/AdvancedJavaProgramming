package assignment1;

public class AddSalary implements Combination{

	@Override
	public int neutral() {
	
		return 0;
	}

	@Override
	public int combine(int x, int y) {
		
		return x+y;
	}

	@Override
	public int projectInt(Employee employee) {
		
		return employee.getSalary();
	}

}
