package assignment3;

/**
 * EmployeeDBClient declares the methods that the clients can use to lookup
 * mappings between departments and server URLs
 * 
 * @author bonii
 * 
 */
public interface EmployeeDBClient {

	/**
	 * Returns the server URL where the employee records for the department are
	 * present. All the employee records for a department are on one server.
	 * 
	 * @param departmentId
	 * @return The server URL where the department records are
	 * @throws DepartmentNotFoundException
	 */
	public String getServerURLForDepartment(int departmentId)
			throws DepartmentNotFoundException;

}
