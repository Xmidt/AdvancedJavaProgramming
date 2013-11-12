package assignment1;
import java.util.List;


public class MapChunked implements Map {

	@Override
	public void map(Mutation m, List<Employee> l) {
		
		Thread[] threads = new Thread[3];
		
		for (int i = 0; i < l.size(); i++)
		{
			threads[i % 3] = new Thread(new MapThread(m,l.get(i)));
			threads[i % 3].run();
			
			if (i % 3 == 2){
				try {
					for (int j = 0; j < 3; j++)
					{
						threads[j].join();
					}
				}
				catch (InterruptedException ex) {
					System.out.println("Nothing");
				}
			}
		}
		
		try {
			for (int j = 0; j < 3; j++)
			{
				threads[j].join();
			}
		}
		catch (InterruptedException ex) {
			System.out.println("Nothing");
		}
		
	}
}
