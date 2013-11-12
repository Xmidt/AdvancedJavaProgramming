package assignment2;
import java.util.List;

public interface Sensor {
	/**
	 * Generates sensor reading comprising of humidity and temperature
	 * @return
	 */
	public SensorReading generateSensorReading();
	
	/**
	 * Registers the list of monitors that should receive sensor data
	 * @param sm
	 * @return
	 */
	public int registerMonitor(List<Monitor> sm);
}
