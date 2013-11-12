import java.util.List;


public class AggregationSequential implements Aggregation {

	@Override
	public int aggregate(Combination c, List<Employee> employees) {

		int res = c.combine(c.neutral(), c.projectInt(employees.get(0)));
		
		for (int i = 1; i < employees.size(); i++)
		{
			res = c.combine(res, c.projectInt(employees.get(i)));
		}
		return res;
	}

}
