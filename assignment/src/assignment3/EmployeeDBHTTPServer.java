package assignment3;

import org.eclipse.jetty.server.Server;

/**
 * EmployeeDBHTTPServer is the EmployeeDB server which receives client requests
 * 
 * @author bonii
 * 
 */
public class EmployeeDBHTTPServer {

	public static void main(String[] args) throws Exception {
		Server myServer = new Server(8080);
		EmployeeDBHTTPHandler handler = new EmployeeDBHTTPHandler(); // This
																		// handler
																		// will
																		// handle
																		// all
																		// incoming
																		// HTTP
																		// requests
		myServer.setHandler(handler);
		myServer.start();
		myServer.join();
	}
}
