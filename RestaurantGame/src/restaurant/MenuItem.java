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
	
	public MenuItem( String name ){
		this.name = name;
		qualityLevel = Quality.LOW;
	}
	
	public void setPrice( int price )
	{
		this.price = price;
	}

	@Override
	public String toString( ) {
		return name;
	}
	
}