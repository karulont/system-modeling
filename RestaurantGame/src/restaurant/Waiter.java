/**
 * @(#) Waiter.java
 */

package restaurant;

public class Waiter extends Employee
{
	public void computeSalary( )
	{
		switch(experience) {
		case LOW:
			salary = 200;
			break;
		case AVERAGE:
			salary = 300;
			break;
		case HIGH:
			salary = 400;
			break;
		}
	}
}
