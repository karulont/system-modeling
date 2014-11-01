/**
 * @(#) Employee.java
 */

package restaurant;

public class Employee extends Person
{
	protected int salary;
	
	protected Experience experience;
	
	public void computeSalary( )
	{
		
	}
	
	public void increaseExperience( )
	{
		switch(experience) {
		case LOW:
			experience = Experience.AVERAGE;
			break;
		case AVERAGE:
			experience = Experience.HIGH;
			default:
				break;
		}
	}
	
	
}
