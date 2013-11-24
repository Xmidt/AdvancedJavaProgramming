package assignment4.processors;

import java.util.Iterator;
import java.util.Random;

/**
 * A processor that generates sensor readings. You do not need to understand the
 * internal working of this class. You should understand how to use it though.
 * See the process method {@link SensorReadingGenerator}
 * {@link #process(StartSignal)}.
 * 
 * @author fmma
 * 
 */
public class SensorReadingGenerator implements
		Processor<StartSignal, SensorReading> {

	private Random random = new Random();

	private float humidity = (float) (random.nextGaussian() * 25 + 75);
	private float temperature = (float) (random.nextGaussian() * 25 + 25);

	private float dh = 0;
	private float dt = 0;
	private int tick = 0;

	/**
	 * {@inheritDoc}
	 * 
	 * A sensor reading generator receives a single start signal returns an
	 * infinite iterator that generates sensor readings. The start signal should
	 * be manually pushed to the sensor node.
	 */
	@Override
	public Iterator<SensorReading> process(StartSignal in) {
		return new Iterator<SensorReading>() {

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public SensorReading next() {
				SensorReading reading = new SensorReading();

				if (tick++ == 100000) {
					dh = 0.0001f * (float) (random.nextGaussian() - 0.01f * (humidity - 75));
					dt = 0.0001f * (float) (random.nextGaussian() - 0.01f * (temperature - 25));
					tick = 0;
				}

				humidity += dh;
				temperature += dt;
				reading.setHumidity(humidity);
				reading.setTemperature(temperature);
				return reading;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}
