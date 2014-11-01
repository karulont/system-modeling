/**
 * @(#) Chef.java
 */

package restaurant;

public class Chef extends Employee
{
	private String taxCode;
	
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
}
