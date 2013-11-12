package assignment1;

public class AggregationThread implements Runnable {

	private Combination combination;
	private Employee employee;
	private Integer previous;
	private Integer result;
	
	AggregationThread(Combination c, Employee e, Integer r)
	{
		this.employee = e;
		this.combination = c;
		this.previous = r;
	}
	
	@Override
	public void run() {
		result = combination.combine(previous, combination.projectInt(employee));
	}
	
	public Integer getResult()
	{
		return result;
	}
}
