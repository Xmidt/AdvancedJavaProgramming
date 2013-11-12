
public class MapThread implements Runnable {

	private Mutation mutation;
	private Employee employee;
	
	MapThread(Mutation m, Employee e)
	{
		this.employee = e;
		this.mutation = m;
	}
	
	@Override
	public void run() {
		mutation.mutate(employee);
	}
}
