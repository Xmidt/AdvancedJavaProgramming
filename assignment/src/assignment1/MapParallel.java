package assignment1;
import java.util.List;


public class MapParallel implements Map {
	
	@Override
	public void map(Mutation m, List<Employee> l){
		
		Thread[] threads = new Thread[l.size()];
		
		for (int i = 0; i < l.size(); i++)
		{
			threads[i] = new Thread(new MapThread(m,l.get(i)));
			threads[i].run();
		}
		
		try {
			for (int i = 0; i < l.size(); i++)
			{
				threads[i].join();
			}
		}
		catch (InterruptedException ex) {
			System.out.println("Nothing");
		}
	}
}
