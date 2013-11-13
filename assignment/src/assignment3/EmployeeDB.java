package assignment3;

import java.util.List;

/**
 * EmployeeDB declares the methods that the clients can invoke to interact with
 * the employee database server
 * 
 * @author bonii
 * 
 */
public interface EmployeeDB {

	/**
	 * Add an employee in the employee database
	 * 
	 * @param emp
	 */
	public void addEmployee(Employee emp);

	/**
	 * List all employees in the employee database
	 * 
	 * @return
	 */
	public List<Employee> listAllEmployees();

	/**
	 * List all the employees in a department
	 * 
	 * @return
	 */
	public List<Employee> listEmployeesInDept(List<Integer> departmentIds);

	/**
	 * Increments salaries of all employees in the department. Ensure salaries
	 * of all employees are incremented or for none of them are incremented,
	 * errors through appropriate Exception
	 * 
	 * @param salaryIncrements
	 * @return
	 */
	public void incrementSalaryOfDepartment(
			List<SalaryIncrement> salaryIncrement)
			throws DepartmentNotFoundException,
			NegativeSalaryIncrementException;

}
