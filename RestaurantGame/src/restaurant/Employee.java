/**
 * @(#) Employee.java
 */

package restaurant;

public abstract class Employee extends Person
{
	public Employee() {
		experience = Experience.LOW;
	}
	protected int salary;
	protected Experience experience;
	
	public abstract int getTrainingCost();
	public abstract void computeSalary();
	
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
