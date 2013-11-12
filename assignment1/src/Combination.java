public interface Combination {

	/**
	 * combine(x, neutral()) = x = combine(neutral(), x).
	 * 
	 * @return The neutral element of the combination.
	 */
	public int neutral();

	/**
	 * 
	 * combine(combine(x,y),z) = combine(x,combine(y,z))
	 * 
	 * @return The combination of x and y.
	 */
	public int combine(int x, int y);

	/**
	 * @param employee
	 *            An employee.
	 * @return The projection of the property that this combination is defined
	 *         on (e.g. salary or age).
	 */
	public int projectInt(Employee employee);
}
