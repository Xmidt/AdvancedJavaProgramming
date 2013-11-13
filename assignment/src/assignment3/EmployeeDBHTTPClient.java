package assignment3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jetty.client.HttpClient;

/**
 * EmployeeDBHTTPClient implements the client side methods of EmployeeDB
 * interface using HTTP protocol. The methods must send HTTP requests to the
 * EmployeeDBHTTPServer
 * 
 * @author bonii
 * 
 */
public class EmployeeDBHTTPClient implements EmployeeDBClient, EmployeeDB {
	private HttpClient client = null;
	private static final String SPLIT_DEPT = ";";
	private static final String filePath = "/home/mlschmidt/AdvancedJavaProgramming/assignment/src/assignment3/departmentservermapping.properties";
	private Map<Integer, String> departmentServerURLMap;

	public EmployeeDBHTTPClient() throws IOException, IllegalArgumentException {
		initMappings();
		// You need to initiate HTTPClient here
	}

	public void initMappings() throws IOException, IllegalArgumentException {
		Properties props = new Properties();
		departmentServerURLMap = new ConcurrentHashMap<Integer, String>(); // Now
																			// my
																			// lookups
																			// are
																			// thread-safe
																			// even
																			// if
																			// there
																			// is
																			// weird
																			// client
																			// :-)

		props.load(new FileInputStream(filePath));
		for (String serverURL : props.stringPropertyNames()) {
			String departmentString = props.getProperty(serverURL);
			if (!serverURL.startsWith("http://")) {
				serverURL = new String("http://" + serverURL);
			}
			if (!serverURL.endsWith("/")) {
				serverURL = new String(serverURL + "/");
			}

			String[] departments = departmentString.split(SPLIT_DEPT);
			for (String department : departments) {
				int departmentValue = Integer.parseInt(department);
				if (departmentServerURLMap.containsKey(Integer
						.valueOf(departmentValue))) {
					throw new IllegalArgumentException("Duplicate key found");
				}
				departmentServerURLMap.put(Integer.valueOf(departmentValue),
						serverURL);
			}
		}
	}

	@Override
	public void addEmployee(Employee emp) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Employee> listAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> listEmployeesInDept(List<Integer> departmentIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void incrementSalaryOfDepartment(
			List<SalaryIncrement> salaryIncrements)
			throws DepartmentNotFoundException,
			NegativeSalaryIncrementException {
		// TODO Auto-generated method stub
	}

	/**
	 * Returns the server URL (starting with http:// and ending with /) to
	 * contact for a department
	 */
	public String getServerURLForDepartment(int departmentId)
			throws DepartmentNotFoundException {
		if (!departmentServerURLMap.containsKey(departmentId)) {
			throw new DepartmentNotFoundException("department " + departmentId
					+ " does not exist in mapping");
		}
		return departmentServerURLMap.get(departmentId);
	}

}
