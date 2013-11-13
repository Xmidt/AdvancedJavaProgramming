package assignment3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Generated JUnit test class to test the EmployeeDB interface. It can be used
 * to test both the client and the server implementation.
 * 
 * @author bonii
 * 
 */
public class EmployeeDBTest {
	private EmployeeDB employeeDB = null;
	private SimpleEmployeeDB server = SimpleEmployeeDB.getInstance();

	@Before
	public void setUp() throws Exception {
		// employeeDB = SimpleEmployeeDB.getInstance();By setting the employeeDB
		// to either the server implementation first, you can write the server
		// implementation and test it. You dont even have to run the Jetty
		// HTTP server, you can just implement SimpleEmployeeDB and test it
		//
		// employeeDB = new EmployeeDBHTTPClient(); Once you have written the
		// client you can test the full system for the same test cases and it
		// should work, welcome to Unit testing :-)
		//
	}

	@Test
	public void testAddEmployee() {
		Employee employee0 = new Employee();
		employee0.setDepartment(0);
		employee0.setId(0);
		employee0.setName("Tom");
		employee0.setSalary(100);
		
		server.addEmployee(employee0);

		Employee employee1 = new Employee();
		employee1.setDepartment(1);
		employee1.setId(1);
		employee1.setName("John");
		employee1.setSalary(200);
		
		server.addEmployee(employee1);
		
		Employee employee2 = new Employee();
		employee2.setDepartment(2);
		employee2.setId(2);
		employee2.setName("Carsten");
		employee2.setSalary(300);
		
		server.addEmployee(employee2);
		
		assertEquals(3,server.listAllEmployees().size());
	}

	@Test
	public void testListAllEmployees() {
		assertEquals(3,server.listAllEmployees().size());
	}

	@Test
	public void testListEmployeesInDept() {
		List<Integer> intList = new ArrayList<Integer>();
		
		intList.add(0);
		intList.add(1);
		intList.add(3);
		
		assertEquals(2,server.listEmployeesInDept(intList).size());
	}

	@Test
	public void testIncrementSalaryOfDepartment() {
		
		SalaryIncrement salary1 = new SalaryIncrement();
		salary1.setDepartment(0);
		salary1.setIncrementBy(100);
		
		List<SalaryIncrement> salaryList = new ArrayList<SalaryIncrement>();
		salaryList.add(salary1);
		
		Employee employee = null;
		
//		salary1.setDepartment(3);		// fails
//		salary1.setIncrementBy(-100);	// fails
	
		try {
			
			server.incrementSalaryOfDepartment(salaryList);
			
			List<Employee> employeeList = server.listAllEmployees();
			
			for (int i = 0; i < employeeList.size(); i++){
				if (employeeList.get(i).getDepartment() == 0){
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
		
		assertEquals((int)200,(int)employee.getSalary());
		
	}

}
