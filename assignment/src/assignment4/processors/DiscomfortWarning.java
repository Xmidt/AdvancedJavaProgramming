package assignment4.processors;

/**
 * A simple encapsulation of a discomfort warning.
 * 
 * @author fmma
 *
 */
public class DiscomfortWarning {

	private final int newDiscomforLevel;

	public DiscomfortWarning(int newDiscomforLevel) {
		this.newDiscomforLevel = newDiscomforLevel;
	}

	public int getNewDiscomforLevel() {
		return newDiscomforLevel;
	}
	
	@Override
	public String toString() {
		return "Warning: new discomfort level is " + newDiscomforLevel;
	}
}
