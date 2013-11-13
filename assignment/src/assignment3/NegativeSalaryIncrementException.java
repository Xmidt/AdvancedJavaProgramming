package assignment3;

/**
 * NegativeSalaryIncrementException is thrown when salary increment is attempted
 * with negative values
 * 
 * @author bonii
 * 
 */
public class NegativeSalaryIncrementException extends Exception {

	public NegativeSalaryIncrementException() {
		// TODO Auto-generated constructor stub
	}

	public NegativeSalaryIncrementException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NegativeSalaryIncrementException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NegativeSalaryIncrementException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
