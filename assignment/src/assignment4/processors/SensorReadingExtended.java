package assignment4.processors;

/**
 * An extension to {@link SensorReading} that includes a boolean indicating
 * whether motion was detected by the sensor or not (the meaning doesn't really
 * matter for the exercise).
 * 
 * @author fmma
 * 
 */
public class SensorReadingExtended extends SensorReading {

	private boolean motion;

	public boolean isMotion() {
		return motion;
	}

	public void setMotion(boolean motion) {
		this.motion = motion;
	}


}
