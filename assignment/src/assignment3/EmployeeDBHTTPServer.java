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
		// Starting 3 servers to match the servermapping
		Server server1 = new Server(8080);
		Server server2 = new Server(8088);
		Server server3 = new Server(8089);
		EmployeeDBHTTPHandler handler = new EmployeeDBHTTPHandler(); // This
																		// handler
																		// will
																		// handle
																		// all
																		// incoming
																		// HTTP
																		// requests
		server1.setHandler(handler);
		server2.setHandler(handler);
		server3.setHandler(handler);
		
		server1.start();
		server2.start();
		server3.start();
		
		server1.join();
		server2.join();
		server3.join();
	}
}
