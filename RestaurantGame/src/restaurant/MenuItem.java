/**
 * @(#) MenuItem.java
 */

package restaurant;

public abstract class MenuItem
{
	protected String name;
	
	protected Quality qualityLevel;
	
	protected int price;
	
	public abstract int computeProductionPrice( );
	
	public MenuItem(){

	}
	
	public void setPrice( int price )
	{
		this.price = price;
	}
	
	
}
