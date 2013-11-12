import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class AggregationTest {

	public void testAggregation(Aggregation a)
	{
		Combination c = new AddSalary();
		List<Employee> employeeList = dataSet();
		
		a.aggregate(c, employeeList);
		
		int salary = 0;
		
		for (int i = 0; i < employeeList.size(); i++)
		{
			salary += employeeList.get(i).getSalary();
		}
		
		assertEquals(37437,salary);
	}
	
	@Test
	public void testSequential()
	{
		Aggregation testAgg = new AggregationSequential();
		testAggregation(testAgg);
	}
	
	@Test
	public void testParallel()
	{
		Aggregation testAgg = new AggregationParallel();
		testAggregation(testAgg);
	}
	
	private List<Employee> dataSet()
	{
		
		List<Employee> list = new ArrayList<Employee>();

		list.add(new Employee("John Doe0",20,3000));
		list.add(new Employee("John Doe1",30,4000));
		list.add(new Employee("John Doe2",40,3300));
		list.add(new Employee("John Doe3",50,4000));
		list.add(new Employee("John Doe4",60,3500));
		list.add(new Employee("John Doe5",23,3800));
		list.add(new Employee("John Doe6",34,5000));
		list.add(new Employee("John Doe7",54,3500));
		list.add(new Employee("John Doe8",33,4000));
		list.add(new Employee("John Doe9",52,3337));
		
		// Base salary: 3000+4000+3300+4000+3500+3800+5000+3500+4000+3337
		// 	37,437
		
		// Added salary: 25+30+27+26
		// 	108
		
		// Total: 
		//	37,545

		return list;
	}

}
