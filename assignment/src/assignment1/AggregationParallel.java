package assignment1;
import java.util.List;


public class AggregationParallel implements Aggregation {

	@Override
	public int aggregate(Combination c, List<Employee> l) {
		
		Thread[] threads = new Thread[l.size()];
		Integer res = 0;
		
		for (int i = 0; i < l.size(); i++)
		{
			AggregationThread aT = new AggregationThread(c,l.get(i),res);
			threads[i] = new Thread(aT);
			threads[i].run();
			
			try {
					threads[i].join();
			}
			catch (InterruptedException ex) {
				System.out.println("Nothing");
			}

			res = aT.getResult();
		}
		return res;
	}

}
