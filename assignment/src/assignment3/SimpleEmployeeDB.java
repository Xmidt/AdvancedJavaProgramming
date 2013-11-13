package assignment3;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmployeeDB implements EmployeeDB {
	private static SimpleEmployeeDB instance = null;
	
	private List<Employee> employeeList = new ArrayList<Employee>();

	private SimpleEmployeeDB() {

	}

	public synchronized static SimpleEmployeeDB getInstance() {
		if (instance == null) {
			instance = new SimpleEmployeeDB();
		}
		return instance;
	}

	@Override
	public synchronized void addEmployee(Employee emp) {
		employeeList.add(emp);
	}

	@Override
	public synchronized List<Employee> listAllEmployees() {
		return employeeList;
	}

	@Override
	public synchronized List<Employee> listEmployeesInDept(List<Integer> departmentIds) {
		
		List<Employee> employeeListTemp = new ArrayList<Employee>();
		
		for (int i = 0; i < departmentIds.size(); i++ ) {
			for (int j = 0; j < employeeList.size(); j++) {
				if (departmentIds.get(i) == employeeList.get(j).getDepartment()){
					employeeListTemp.add(employeeList.get(j));
				}
			}
		}
		
		return employeeListTemp;
	}

	@Override
	public synchronized void incrementSalaryOfDepartment(List<SalaryIncrement> salaryIncrements) throws DepartmentNotFoundException, NegativeSalaryIncrementException {
		
		for (int i = 0; i < salaryIncrements.size(); i++ ) {
			
			int department = salaryIncrements.get(i).getDepartment();
			float incrementBy = salaryIncrements.get(i).getIncrementBy();
			
			if (incrementBy < 0 ){
				throw new NegativeSalaryIncrementException("Negative Salary");
			}
			
			boolean foundDepartment = false;
			
			for (int j = 0; j < employeeList.size(); j++) {
				
				if (department == employeeList.get(j).getDepartment()){
					foundDepartment = true;
					
					float currentSalary = employeeList.get(j).getSalary();
					employeeList.get(j).setSalary(currentSalary + incrementBy);
				}
			}
			
			if (!foundDepartment) {
				throw new DepartmentNotFoundException("No department");
			}
		}
	}

}
