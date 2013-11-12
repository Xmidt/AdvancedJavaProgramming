import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;


public class MapTest {

	private Random random;

	private void testMap(Map map) {
		
		Mutation muta = new IncreaseSalary();
		
		List<Employee> employeeList = dataSet();
		
		map.map(muta, employeeList);
		
		int salary = 0;
		
		for (int i = 0; i < employeeList.size(); i++)
		{
			salary += employeeList.get(i).getSalary();
		}
		
		assertEquals(37545,salary);
	}
	
	@Test
	public void testSequential()
	{
		Map testMap = new MapSequential();
		testMap(testMap);
	}
	
	@Test
	public void testParellel()
	{
		Map testMap = new MapParallel();
		testMap(testMap);
	}
	
	@Test
	public void testChunked()
	{
		Map testMap = new MapChunked();
		testMap(testMap);
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
	
	/*
	 * a3.
	 * 
	 * Equation:
	 * 	Salary_min = 10 * 3000 + 41 * ½
	 *  Salary_max = 10 * 5000 + 10 * ½ * 60 
	 * 
	 * Min: 30,020
	 * Max: 50,300
	 */
	public List<Employee> randomDataSet()
	{
		random = new Random();
		
		List<Employee> list = new ArrayList<Employee>();
		for (int i = 0; i < 10; i++){
			
			int age = random.nextInt(40) + 20;
			if (i == 0){
				
				age = random.nextInt(20) + 40;
			}
			String name = "John Doe";
			
			int salary = random.nextInt(2000) + 3000;
			
			list.add(new Employee(name,age,salary));
		}
		
		return list;
	}
}
