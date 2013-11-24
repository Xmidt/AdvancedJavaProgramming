package assignment4.processors;

import java.util.Iterator;
import java.util.Random;

/**
 * See {@link SensorReadingGenerator}. This class is an extension of that
 * producing {@link SensorReadingExtended} instead of {@link SensorReading}.
 * 
 * @author fmma
 * 
 */
public class SensorReadingExtendedGenerator implements
		Processor<StartSignal, SensorReadingExtended> {

	private Random random = new Random();
	Iterator<SensorReading> sg = new SensorReadingGenerator()
			.process(StartSignal.go);

	@Override
	public Iterator<SensorReadingExtended> process(StartSignal in) {
		return new Iterator<SensorReadingExtended>() {

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public SensorReadingExtended next() {
				SensorReading reading = sg.next();
				SensorReadingExtended reading2 = new SensorReadingExtended();
				reading2.setHumidity(reading.getHumidity());
				reading2.setTemperature(reading.getTemperature());
				reading2.setMotion(random.nextBoolean());
				return reading2;
			}
		};
	}

}
