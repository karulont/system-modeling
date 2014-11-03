/**
 * @(#) Barman.java
 */

package restaurant;

public class Barman extends Employee
{
	public void computeSalary( )
	{
		switch(experience) {
		case LOW:
			salary = 300;
			break;
		case AVERAGE:
			salary = 400;
			break;
		case HIGH:
			salary = 500;
			break;
		}
	}
	

	@Override
	public int getTrainingCost( ) {
		return 1200;
	}
}
