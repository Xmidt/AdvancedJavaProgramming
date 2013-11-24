package assignment4.processors;

import java.util.Iterator;

/**
 * A discomfort level processor that keep a running average of temperature and
 * humidity and creates discomfort warnings, when the discomfort level changes.
 * 
 * @author fmma
 * 
 */
public class DiscomfortProcessor implements
		Processor<SensorReading, DiscomfortWarning> {

	/**
	 * Running average temperature.
	 */
	private float averageTemp = 25;

	/**
	 * Running average humidity.
	 */
	private float averageHum = 75;

	/**
	 * Last recorded discomfort level.
	 */
	private int lastDiscomfortLvl = 0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<DiscomfortWarning> process(SensorReading in) {
		averageTemp = 0.9f * averageTemp + 0.1f * in.getTemperature();
		averageHum = 0.9f * averageHum + 0.1f * in.getHumidity();
		int newDiscomfortLevel = calculateDiscomfort(averageTemp, averageHum);
		if (newDiscomfortLevel != lastDiscomfortLvl) {
			lastDiscomfortLvl = newDiscomfortLevel;
			return Iterators
					.singleton(new DiscomfortWarning(newDiscomfortLevel));
		} else {
			return Iterators.empty();
		}
	}

	/**
	 * Calculate discomfort level.
	 * 
	 * @param t Temperature (average).
	 * @param h Humidity (average).
	 * @return Discomfort level.
	 */
	private int calculateDiscomfort(float t, float h) {
		return Math.min(5, Math.max(1, Math.max(Math.round(t) / 10, Math.round(h) / 10 - 4)));
	}

}
