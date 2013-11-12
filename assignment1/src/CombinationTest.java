import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class CombinationTest {

	@Test
	public void testAddSalary() {
		for (int i = 0; i < 1000; i++){
			AddSalary addSalary = new AddSalary();
			
			Employee testSubject1 = genEmployee();
			Employee testSubject2 = genEmployee();
			Employee testSubject3 = genEmployee();
			
			int salary1 =  addSalary.projectInt(testSubject1);
			int salary2 =  addSalary.projectInt(testSubject2);
			int salary3 =  addSalary.projectInt(testSubject3);
			
			assertEquals(addSalary.combine(salary1,addSalary.combine(salary2, salary3)), addSalary.combine(addSalary.combine(salary1,salary2),salary3));
			assertEquals(addSalary.combine(addSalary.neutral(),salary1), addSalary.combine(salary1,addSalary.neutral()));
		}
	}

	@Test
	public void testMinAge() {
		for (int i = 0; i < 1000; i++){
			MinAge minAge = new MinAge();
			
			Employee testSubject1 = genEmployee();
			Employee testSubject2 = genEmployee();
			Employee testSubject3 = genEmployee();
			
			int age1 = minAge.projectInt(testSubject1);
			int age2 = minAge.projectInt(testSubject2);
			int age3 = minAge.projectInt(testSubject3);
			
			assertEquals(minAge.combine(age1,minAge.combine(age2,age3)), minAge.combine(minAge.combine(age1,age2),age3));
			assertEquals(minAge.combine(minAge.neutral(),age1),minAge.combine(age1,minAge.neutral()));
		}
	}
	
		
	private Employee genEmployee()
	{		
		Random random = new Random();
		
		String name = "John Doe";
		int age = random.nextInt(40) + 20;
		int salary = random.nextInt(2000) + 3000;
		
		return new Employee(name,age,salary);
	}
}
