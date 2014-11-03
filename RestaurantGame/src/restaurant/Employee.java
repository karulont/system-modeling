/**
 * @(#) Employee.java
 */

package restaurant;

public abstract class Employee extends Person
{
	public Employee( ) {
		experience = Experience.LOW;
	}
	protected int salary;
	protected Experience experience;
	
	
	
public abstract int getTrainingCost( );
	public abstract void computeSalary( );
	
	public void increaseExperience( ) throws GameException
	{
		switch(experience) {
		case LOW:
			experience = Experience.AVERAGE;
			break;
		case AVERAGE:
			experience = Experience.HIGH;
			break;
		case HIGH:
			throw new GameException("Employee already at the highest level!");
			default:
				break;
		}
		System.out.println(this + " level is now "+ experience +".");
	}	
}
