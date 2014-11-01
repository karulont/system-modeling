/**
 * @(#) Beverage.java
 */

package restaurant;

public class Beverage extends MenuItem
{
	private int volume;
	
	public int computeProductionPrice( )
	{
		switch (qualityLevel) {
		case LOW:
			price:
				return 1;
		case HIGH:
			return 3;
		default:
			throw new RuntimeException();
		}
	}
	
}
