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

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

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
	private static final String filePath = "C:/Users/David/Documents/Datalogi/Advanced Java Programming 2013_WorkshopUge46/Assignment3/assignment3/departmentservermapping.properties";
	private Map<Integer, String> departmentServerURLMap;
	private XStream xmlStream;

	public EmployeeDBHTTPClient() throws IOException, IllegalArgumentException {
		initMappings();
		// You need to initiate HTTPClient here

		client = new HttpClient();
		client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
		client.setMaxConnectionsPerAddress(300);
		client.setThreadPool(new QueuedThreadPool(20));
		client.setTimeout(30000);

		// DG: Prepare a XStream
		xmlStream = new XStream(new StaxDriver()); 

		try {
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */

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
		// Get the URL of the server
		String serverURL= "http://localhost:8080/";
		try {
			serverURL = getServerURLForDepartment(emp.getDepartment());
		} catch (DepartmentNotFoundException e1) {
			e1.printStackTrace();
		}

		// Send a GET request

		// DG: Create xml string from emp
		String xmlString = xmlStream.toXML(emp);


		// Setup the exchange
		ContentExchange exchange = new ContentExchange();
		exchange.setMethod("GET");
		// Use the acquired serverURL + the emp as param
		String requestString = "addemployee?"
				+"id="+emp.getId()
				+"&name="+emp.getName()
				+"&dept="+emp.getDepartment()
				+"&sal="+emp.getSalary(); 
		exchange.setURL(serverURL+requestString);
		// System.out.println(requestString);

		try {
			client.send(exchange);
			int exchangeState = exchange.waitForDone();
			// Do something according to status of exchange.
			if (exchangeState == HttpExchange.STATUS_COMPLETED) {
				System.out.println("Exchange completed, employee added.");
			} else if (exchangeState == HttpExchange.STATUS_EXCEPTED) {
				System.out.println("Error occured");
			} else if (exchangeState == HttpExchange.STATUS_EXPIRED) {
				System.out.println("Request timed out");
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}

	@Override
	public List<Employee> listAllEmployees() {
		// Send a GET request
		ContentExchange exchange = new ContentExchange();
		exchange.setMethod("GET");
		exchange.setURL("http://localhost:8080/listallemployees");

		List<Employee> returnList = new ArrayList<Employee>();

		try{
			client.send(exchange);
			int exchangeState = exchange.waitForDone();


			// Do something with the returned content
			if (exchangeState == HttpExchange.STATUS_COMPLETED) {
				System.out.println("Exchange completed, list of employees received:");
				String responseString = exchange.getResponseContent();
				System.out.println((List<Employee>) xmlStream.fromXML(responseString));
				returnList = (List<Employee>) xmlStream.fromXML(responseString);


			} else if (exchangeState == HttpExchange.STATUS_EXCEPTED) {
				System.out.println("Error occured");
			} else if (exchangeState == HttpExchange.STATUS_EXPIRED) {
				System.out.println("Request timed out");
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return returnList;
	}

	@Override
	public List<Employee> listEmployeesInDept(List<Integer> departmentIds) {
		// Send a POST request. 
		// Get server URL
		String serverURL= "http://localhost:8080/";


		List<Employee> returnList = new ArrayList<Employee>();

		

		/*
		 * Loop over the list of department Ids and send a request to each
		 * relevant server. Currently just sending a list of 1 department
		 * to each server to avoid DepartmentNotFoundException. 
		 */
		for (int i=0;i<departmentIds.size();i++) {
			ContentExchange exchange = new ContentExchange();

			try {
				serverURL = getServerURLForDepartment(departmentIds.get(i));
			} catch (DepartmentNotFoundException e1) {
				e1.printStackTrace();
			}

			exchange.setMethod("POST");
			exchange.setURL(serverURL+"listemployeesindept");

			List<Integer> currentDeptId = new ArrayList<Integer>();
			currentDeptId.add(departmentIds.get(i)); 

			String xmlString = xmlStream.toXML(currentDeptId);
			Buffer postData = new ByteArrayBuffer(xmlString);
			exchange.setRequestContent(postData);

			try {
				client.send(exchange);
				int exchangeState = exchange.waitForDone();

				// Do something with the returned content
				if (exchangeState == HttpExchange.STATUS_COMPLETED) {
					System.out.println("Exchange completed, list of employees in dept received:");
					String responseString = exchange.getResponseContent();
					returnList.addAll((List<Employee>) xmlStream.fromXML(responseString));
					System.out.println((List<Employee>) xmlStream.fromXML(responseString));


				} else if (exchangeState == HttpExchange.STATUS_EXCEPTED) {
					System.out.println("Error occured");
				} else if (exchangeState == HttpExchange.STATUS_EXPIRED) {
					System.out.println("Request timed out");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return returnList;

	}

	@Override
	public void incrementSalaryOfDepartment(
			List<SalaryIncrement> salaryIncrements)
					throws DepartmentNotFoundException,
					NegativeSalaryIncrementException {
		// Send a POST request. 
		
		String serverURL= "http://localhost:8080/";
		ContentExchange exchange = new ContentExchange();

		/*
		 * Loop over the list of salaryIncrements and send a request to each
		 * relevant server. Currently just sending a list of 1 salary increment
		 * to each server to avoid DepartmentNotFoundException. 
		 */
		for (int i=0;i<salaryIncrements.size();i++) {

			try {
				serverURL = getServerURLForDepartment(salaryIncrements.get(i).getDepartment());
			} catch (DepartmentNotFoundException e1) {
				e1.printStackTrace();
			}

			exchange.setMethod("POST");
			exchange.setURL(serverURL+"incrementsalaryofdepartment");

			List<SalaryIncrement> currentSalIncr = new ArrayList<SalaryIncrement>();
			currentSalIncr.add(salaryIncrements.get(i)); 

			String xmlString = xmlStream.toXML(currentSalIncr);
			Buffer postData = new ByteArrayBuffer(xmlString);
			exchange.setRequestContent(postData);

			try {
				client.send(exchange);
				int exchangeState = exchange.waitForDone();

				// Do something with the returned content
				if (exchangeState == HttpExchange.STATUS_COMPLETED) {
					System.out.println("Exchange completed, salaries incremented.");

				} else if (exchangeState == HttpExchange.STATUS_EXCEPTED) {
					System.out.println("Error occured");
				} else if (exchangeState == HttpExchange.STATUS_EXPIRED) {
					System.out.println("Request timed out");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}


		}
		
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
