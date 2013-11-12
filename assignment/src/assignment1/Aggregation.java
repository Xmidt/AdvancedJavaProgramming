package assignment1;
import java.util.List;

public interface Aggregation {

	/**
	 * Compute the aggregate over a list by combining all elements with a given
	 * combination.
	 * 
	 * @param maxThreads
	 *            The maximum number of threads that this method may fork.
	 * @param c
	 *            The combination to aggregate with (*).
	 * @param employees
	 *            A list of employees [e_1,...e_k].
	 * @return The aggregation e_1 * ... e_k.
	 */
	public int aggregate(Combination c, List<Employee> employees);

}