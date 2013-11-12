package assignment2;
public interface Subscriber {
	/**
	 * Pushes a discomfort level reading from the sensor monitor.
	 * 
	 * @param discomfortlevel
	 */
	public void pushDiscomfortWarning(int discomfortlevel);

	/**
	 * Processes a discomfortLevel warning
	 * 
	 * @param discomfortlevel
	 */
	public void processDiscomfortWarning(int discomfortLevel);

	/**
	 * Gets a discomfort level warning, blocks if there are no discomfort level
	 * warnings available
	 * 
	 * @return
	 */
	public int getDiscomfortWarning();
}
