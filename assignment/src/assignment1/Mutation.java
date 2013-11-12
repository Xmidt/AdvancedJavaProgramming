package assignment1;
public interface Mutation {

	/**
	 * A mutation of an employee. This method changes the internal state of the
	 * given employee.
	 * 
	 * @param employee
	 *            The employee to mutate.
	 */
	public void mutate(Employee employee);

}