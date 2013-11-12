package assignment2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ASensor implements Sensor, Runnable {
	List<Monitor> monitors; //A sensor can push readings to one or many monitors
	
	protected Lock myLock = new ReentrantLock();
	protected Random random = new Random();
	
	
	public SensorReading generateSensorReading() {
		
		// No need for thread safe here, because each thread calls it own method
		// We were lazy and added a max value of +10 to each type
		int temp = random.nextInt(50) + 10;
		int humi = random.nextInt(50) + 50;
		
		SensorReading sR = new SensorReading();
		sR.setHumidity(humi);
		sR.setTemperature(temp);
		
		// Making a more realistic reading :P
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return sR;
	}

	public void run() {
		
		SensorReading reading = null;
		while(true) {
			reading = this.generateSensorReading();
			for (Monitor sm : monitors) {
				sm.pushReading(reading);
			}
		}
		
	}

	// Locks to ensure integrity of monitors
	@Override
	public int registerMonitor(List<Monitor> sm) {

		myLock.lock();
		
		try
		{
			monitors.addAll(sm);
		}
		catch (NullPointerException e)
		{
			monitors = new ArrayList<Monitor>();
			monitors.addAll(sm);
		}
		finally
		{
			myLock.unlock();
		}
		
		return 0;
	}

}
