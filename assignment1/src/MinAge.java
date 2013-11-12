
public class MinAge implements Combination{

	@Override
	public int neutral() {

		return 0;
	}

	@Override
	public int combine(int age1, int age2) {
		
		if (age1 < age2)
		{
			return age1;
		} else
		{
			return age2;
		}
	}

	@Override
	public int projectInt(Employee employee) {
		
		return employee.getAge();
	}

}
