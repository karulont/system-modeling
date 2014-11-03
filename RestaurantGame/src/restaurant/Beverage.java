/**
 * @(#) Beverage.java
 */

package restaurant;

public class Beverage extends MenuItem
{
	public Beverage( String name, int volume ) {
		super(name);
		this.volume = volume;
	}

	protected int volume;
	
	public int computeProductionPrice( )
	{
		switch (qualityLevel) {
		case LOW:
				return 1;
		case HIGH:
			return 3;
		default:
			throw new RuntimeException();
		}
	}
	
}
