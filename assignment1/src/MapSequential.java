import java.util.List;

public class MapSequential implements Map {

	@Override
	public void map(Mutation m, List<Employee> l) {
		
		for (int i = 0; i < l.size(); i++)
		{
			m.mutate(l.get(i));
		}
	}	
}
