package assignment2;

import java.util.ArrayList;
import java.util.List;

public class SensorNetworkHarness {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * We have implemented the sensor network architecture, given in the hand-out.
		 * 
		 * A beneficial extension would be to add extra information to the subscribers
		 */
		
		Thread sensorThread1 = new Thread();
		Thread sensorThread2 = new Thread();
		Thread sensorThread3 = new Thread();
		Thread subscriberThread1 = new Thread();
		Thread subscriberThread2 = new Thread();
		Thread monitorThread1 = new Thread();
		Thread monitorThread2 = new Thread();
		
		ASensor sen1 = new ASensor();
		ASensor sen2 = new ASensor();
		ASensor sen3 = new ASensor();
		ASubscriber sub1 = new ASubscriber();
		ASubscriber sub2 = new ASubscriber();
		AMonitor mon1 = new AMonitor();
		AMonitor mon2 = new AMonitor();
		
		sensorThread1 = new Thread(sen1);
		sensorThread2 = new Thread(sen2);
		sensorThread3 = new Thread(sen3);
		subscriberThread1 = new Thread(sub1);
		subscriberThread2 = new Thread(sub2);
		monitorThread1 = new Thread(mon1);
		monitorThread2 = new Thread(mon2);
		
		List<Monitor> monitors1 = new ArrayList<Monitor>();
		List<Monitor> monitors2 = new ArrayList<Monitor>();
		
		monitors1.add(mon1);
		monitors2.add(mon2);
		
		sen1.registerMonitor(monitors1);
		sen2.registerMonitor(monitors1);
		sen2.registerMonitor(monitors2);
		sen3.registerMonitor(monitors2);
		mon1.registerSubscriber(1, sub1);
		
		mon2.registerSubscriber(1, sub1);
		mon2.registerSubscriber(4, sub2);
		
		sensorThread1.start();
		sensorThread2.start();
		sensorThread3.start();
		subscriberThread1.start();
		subscriberThread2.start();
		monitorThread1.start();
		monitorThread2.start();
	}

}
