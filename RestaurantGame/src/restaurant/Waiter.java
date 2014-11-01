/**
 * @(#) Waiter.java
 */

package restaurant;

import java.util.ArrayList;

public class Waiter extends Employee
{

	public Waiter() {
		tables=new ArrayList<Table>(3);
	}
	protected ArrayList<Table> tables;
	
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
