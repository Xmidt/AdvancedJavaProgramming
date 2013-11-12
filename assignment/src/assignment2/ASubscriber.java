package assignment2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ASubscriber implements Subscriber, Runnable {

	private BlockingQueue<Integer> bQ = new ArrayBlockingQueue<Integer>(10);
	protected Lock myLock = new ReentrantLock();
	
	public void run() {
		while (true) {
			int discomfortLevel = this.getDiscomfortWarning();
			this.processDiscomfortWarning(discomfortLevel);
		}
	}

	// We use the blocking queue -> ArrayBlockingQueue
	@Override
	public void pushDiscomfortWarning(int discomfortlevel) {
		
		// Already thread safe
		try {
			bQ.put(discomfortlevel);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void processDiscomfortWarning(int discomfortLevel) {
		
		// No brownies for us :)
		// http://orteil.dashnet.org/cookieclicker/
		System.out.println("Discomfort level: " + discomfortLevel);
		
	}

	@Override
	public int getDiscomfortWarning() {
		int value = 0;
		
		// Already thread safe
		try {
			value = (Integer) bQ.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return value;
	}



}
