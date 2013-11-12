
public class IncreaseSalary implements Mutation {

	@Override
	public void mutate(Employee employee) {
		if (employee.getAge() > 40)
		{
			employee.setSalary(employee.getSalary() + (int)(employee.getAge() * 0.5));
		}	
	}
}
