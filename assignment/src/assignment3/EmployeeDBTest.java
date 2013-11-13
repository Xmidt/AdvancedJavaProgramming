package assignment3;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Generated JUnit test class to test the EmployeeDB interface. It can be used
 * to test both the client and the employeeDB implementation.
 * 
 * @author bonii
 * 
 */
public class EmployeeDBTest {
	
	private EmployeeDB employeeDB = null;
	
	//private SimpleEmployeeDB employeeDB = SimpleEmployeeDB.getInstance();

	@Before
	public void setUp() throws Exception {
		// employeeDB = SimpleEmployeeDB.getInstance();By setting the employeeDB
		// to either the employeeDB implementation first, you can write the employeeDB
		// implementation and test it. You dont even have to run the Jetty
		// HTTP employeeDB, you can just implement SimpleEmployeeDB and test it
		//
		// employeeDB = new EmployeeDBHTTPClient(); Once you have written the
		// client you can test the full system for the same test cases and it
		// should work, welcome to Unit testing :-)
		//
		
		// As direct employeeDB test:
		//employeeDB = SimpleEmployeeDB.getInstance();
		
		// As Client test:
		employeeDB = new EmployeeDBHTTPClient();
	}

	@Test
	public void testAddEmployee() {
		
		// Add 3 employees and assert that the size of the list returned is 3.
		 
		Employee employee0 = new Employee();
		employee0.setDepartment(4);
		employee0.setId(1);
		employee0.setName("Tom");
		employee0.setSalary(100);
		
		employeeDB.addEmployee(employee0);

		Employee employee1 = new Employee();
		employee1.setDepartment(2);
		employee1.setId(2);
		employee1.setName("John");
		employee1.setSalary(200);
		
		employeeDB.addEmployee(employee1);
		
		Employee employee2 = new Employee();
		employee2.setDepartment(1);
		employee2.setId(3);
		employee2.setName("Carsten");
		employee2.setSalary(300);
		
		employeeDB.addEmployee(employee2);
		
		assertEquals(3,employeeDB.listAllEmployees().size());
	}

	@Test
	public void testListAllEmployees() {
		// Just check that the list returned has the expected size
		assertEquals(3,employeeDB.listAllEmployees().size());
	}

	@Test
	public void testListEmployeesInDept() {
		// Get the list of employees from 2 different departments with 1 employee each
		// on 2 different servers, aswell as a department with no employees on a 3rd server.
		// Assert the returned list is of size 2.
		List<Integer> intList = new ArrayList<Integer>();
		
		intList.add(1);
		intList.add(4);
		intList.add(8);
		
		assertEquals(2,employeeDB.listEmployeesInDept(intList).size());
	}

	@Test
	public void testIncrementSalaryOfDepartment() {
		// Check that the salary of a department is equal to the expected after
		// incrementing. 
		SalaryIncrement salary1 = new SalaryIncrement();
		salary1.setDepartment(1);
		salary1.setIncrementBy(100);
		
		List<SalaryIncrement> salaryList = new ArrayList<SalaryIncrement>();
		salaryList.add(salary1);
		
		Employee employee = null;
		
//		salary1.setDepartment(3);		// fails
//		salary1.setIncrementBy(-100);	// fails
	
		try {
			
			employeeDB.incrementSalaryOfDepartment(salaryList);
			
			List<Employee> employeeList = employeeDB.listAllEmployees();
			
			for (int i = 0; i < employeeList.size(); i++){
				if (employeeList.get(i).getDepartment() == 1){
					employee = employeeList.get(i);
					break;
				}
			}
			
		} catch (DepartmentNotFoundException e) {
			assertEquals("No department",e.getMessage());
			e.printStackTrace();
		} catch (NegativeSalaryIncrementException e) {
			assertEquals("Negative Salary",e.getMessage());
			e.printStackTrace();
		}
		
		assertEquals((int)400,(int)employee.getSalary());
		
	}

}
