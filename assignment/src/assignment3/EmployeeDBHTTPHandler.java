package assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * EmployeeDBHTTPHandler is invoked when an HTTP request is received by the
 * EmployeeDBHTTPServer
 * 
 * @author bonii
 * 
 */
public class EmployeeDBHTTPHandler extends AbstractHandler {
	
	private XStream xmlStream;

	/**
	 * Although this method is thread-safe, what it invokes is not thread-safe
	 */
	public void handle(String target, Request baseRequest,
			HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		// DG: Get a database instance to work on.
		SimpleEmployeeDB database = SimpleEmployeeDB.getInstance();
		
		String uri = req.getRequestURI().trim().toUpperCase();
		
		xmlStream = new XStream(new StaxDriver());
		
		res.setContentType("text/html;charset=utf-8");
		res.setStatus(HttpServletResponse.SC_OK);
		
		Map<String, String[]> parameterMap = null;
		
		
        if (req.getMethod().equalsIgnoreCase("GET")) {
 
			if (uri.equalsIgnoreCase("/addemployee")) {
				
				/*
				 
				 addemployee?name=Carsten something something&dept=42&sal=1337&id=0
				 
				 */
			
	            Employee emp = new Employee();
	            
	            /*
	             * This is really ugly, but taken from the example code.
	             * 
	             * I would have liked to make something cleaner, but given this course is a prove of concept,
	             * and not about making pretty code, this is significant enough.
	             */
	            parameterMap = req.getParameterMap();
	            Iterator<Map.Entry<String, String[]>> it = parameterMap.entrySet()
	                    .iterator();
	            while (it.hasNext()) {
	                Map.Entry<String, String[]> pairs = it.next();
	                
	                if (pairs.getKey().equalsIgnoreCase("sal")) {
	                	float tempValue = 0;
	                	for (String stringValue : pairs.getValue()) {
	                		float value = Float.valueOf(stringValue.trim());
	                		tempValue += value;
	                	}
	                	emp.setSalary(tempValue);
	                } else if (pairs.getKey().equalsIgnoreCase("dept")) {
	                	int tempValue = 0;
	                	for (String stringValue : pairs.getValue()) {
	                		int value = Integer.valueOf(stringValue.trim());
	                		tempValue += value;
	                	}
	                	emp.setDepartment(tempValue);
	                } else if (pairs.getKey().equalsIgnoreCase("id")) {
	                	int tempValue = 0;
	                	for (String stringValue : pairs.getValue()) {
	                		int value = Integer.valueOf(stringValue.trim());
	                		tempValue += value;
	                	}
	                	emp.setId(tempValue);
	                } else if (pairs.getKey().equalsIgnoreCase("name")) {
	                	String tempName = "";
	                	for (String stringValue : pairs.getValue()) {
	                		tempName += stringValue.trim() + " ";
	                	}
	                	emp.setName(tempName.trim());
	                }
	                
	            }  
				
				//SimpleEmployeeDB.getInstance().addEmployee(emp);
	            database.addEmployee(emp);
				res.getWriter().println("Employee: " + emp.getName() + ", added.");
				

			} else if (uri.equalsIgnoreCase("/listallemployees")) {
				
				/*
				 
				 No argument
				 
				 */
				
				//List<Employee> empList = SimpleEmployeeDB.getInstance().listAllEmployees();
				List<Employee> empList = database.listAllEmployees();
				res.getWriter().println(xmlStream.toXML(empList));

			}
			
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
			
			int len = req.getContentLength();
			BufferedReader reqReader = req.getReader();
			char[] cbuf = new char[len];
			reqReader.read(cbuf);
			reqReader.close();
			String content = new String(cbuf);
			
			if (uri.equalsIgnoreCase("/listEmployeesInDept")) {

				/*
				 
				 <?xml version="1.0" ?>
				 <list>
				     <int>2</int>
				     <int>0</int>
				 </list>
				 
				 */
				
				List<Integer> derList = (List<Integer>) xmlStream.fromXML(content);
				List<Employee> empList = SimpleEmployeeDB.getInstance().listEmployeesInDept(derList);
				res.getWriter().println(xmlStream.toXML(empList));

			} else if (uri.equalsIgnoreCase("/incrementSalaryOfDepartment")) {

				/*
				 
				 <?xml version="1.0" ?>
				 <list>
				     <assignment3.SalaryIncrement>
				         <department>0</department>
				         <incrementBy>100.0</incrementBy>
				     </assignment3.SalaryIncrement>
				     <assignment3.SalaryIncrement>
				         <department>1</department>
				         <incrementBy>200.0</incrementBy>
				     </assignment3.SalaryIncrement>
				 </list>	
				 
				 */
				
				List<SalaryIncrement> derList = (List<SalaryIncrement>) xmlStream.fromXML(content);
				try {
					SimpleEmployeeDB.getInstance().incrementSalaryOfDepartment(derList);
					res.getWriter().println("All salary incremented");
				} catch (DepartmentNotFoundException e) {
					res.getWriter().println("Department Not Found");
				} catch (NegativeSalaryIncrementException e) {
					res.getWriter().println("Negative Salary Increment");
				}
			}
		}
		
		baseRequest.setHandled(true);
	}

}
