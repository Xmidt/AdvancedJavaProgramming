package assignment4.dataflow;

import assignment4.processors.DiscomfortProcessor;
import assignment4.processors.DiscomfortWarning;
import assignment4.processors.PrinterProcessor;
import assignment4.processors.SensorReadingGenerator;
import assignment4.processors.StartSignal;

public class DataflowHarness {
	public static void main(String[] args) {
		DynamicCheckedNode sensor1 = new DynamicCheckedNode(new SensorReadingGenerator());
		//DynamicCheckedNode sensor2 = new DynamicCheckedNode(new SensorReadingExtendedGenerator());
		
		DynamicCheckedNode monitor1 = new DynamicCheckedNode(new DiscomfortProcessor());
		sensor1.subscribe(monitor1);
		//sensor2.subscribe(monitor1);
		
		DynamicCheckedNode subscriber1 = new DynamicCheckedNode(new PrinterProcessor<DiscomfortWarning>("Printer 1"));
		monitor1.subscribe(subscriber1);
		
		//DynamicCheckedNode subscriber2 = new DynamicCheckedNode(new PrinterProcessor<Object>("Printer 2"));
		//monitor1.subscribe(subscriber2);
		
		sensor1.start();
		//sensor2.start();
		monitor1.start();
		subscriber1.start();
		//subscriber2.start();
		
		sensor1.push(StartSignal.go);
		//sensor2.push(StartSignal.go);
	}
}
