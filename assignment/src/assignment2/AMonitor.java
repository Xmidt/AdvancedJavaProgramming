package assignment2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AMonitor implements Monitor, Runnable {

	// Initiate the average value to the lowest value for the given type
	private float avgTemp = 10;
	private float avgHumi = 50;
	
	// Map subscribers with the given discomfortLevel as value
	Map<Subscriber,Integer> subscriberMap = new HashMap<Subscriber,Integer>();
	protected Lock myLock = new ReentrantLock();
	private BlockingQueue<SensorReading> bQ = new ArrayBlockingQueue<SensorReading>(10);
	
	// We use the blocking queue -> ArrayBlockingQueue
	@Override
	public int pushReading(SensorReading sensorInput) {
		
		// bQ is already thread safety, therefore no need for locking
		try {
			bQ.put(sensorInput);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	/*
	 * At the given implementation, there is no need for locking,
	 * because the monitor only runs in 1 thread.
	 * 
	 * Therefore we have implemented the locking method,
	 * for proof of concept.
	 */
	@Override
	public void processReading(SensorReading sensorInput) {

		myLock.lock();
		
		float temp = sensorInput.getTemperature();
		float humi = sensorInput.getHumidity();
		
		// recalculates the average values for the given types.
		avgTemp = (avgTemp + temp)/2;
		avgHumi = (avgHumi + humi)/2;
		
		int maxReading = 0;
		
		// Find the highest value of temp vs. humi, scaled.
		if ((avgTemp-10) > (avgHumi-50))
		{
			maxReading = (int) Math.floor((avgTemp-10)/10);
		}
		else
		{
			maxReading = (int) Math.floor((avgHumi-50)/10);
		}
		
		// Uses the scaled value to determine discomfortLevel
		int dL;
		switch (maxReading) {
			case 0: dL = 1;
					break;
			case 1: dL = 2;
					break;
			case 2: dL = 3;
					break;
			case 3: dL = 4;
					break;
			case 4: dL = 5;
					break;
			default: dL = 0;
					break;
		}
		
		/*
		 * checking if calculated discomfortLevel is GEQ than the associated discomfortLevel.
		 */
		for(Entry<Subscriber,Integer> entry : subscriberMap.entrySet())
		{
			Subscriber sub = entry.getKey();
			Integer dis = entry.getValue();
			
			if (dL >= dis){
				sub.pushDiscomfortWarning(dL);
			}
		}
		
		myLock.unlock();
	}

	// Locks to ensure integrity of subscribers
	@Override
	public int registerSubscriber(int discomfortLevel, Subscriber subscriber) {
		
		myLock.lock();
		// Add values to our map
		subscriberMap.put(subscriber, discomfortLevel);
		myLock.unlock();
		return 0;
	}

	@Override
	public SensorReading getSensorReading() {
		
		SensorReading sensorReading = null;
		
		// no need for locking, given bQ is thread safe.
		try {
			sensorReading = (SensorReading) bQ.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return sensorReading;
	}

	public void run() {
		SensorReading sensorInput = null;
		while(true) {
			sensorInput = getSensorReading();
			this.processReading(sensorInput);
		}
	}

}
