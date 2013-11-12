import java.util.List;

public interface Map {

	/**
	 * A mutation map. Mutates each employee in a list by a given mutation.
	 * 
	 * @param m
	 *            The mutations.
	 * @param l
	 *            The list to mutate.
	 */
	public void map(Mutation m, List<Employee> l);

}
