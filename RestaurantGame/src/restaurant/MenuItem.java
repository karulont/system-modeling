/**
 * @(#) MenuItem.java
 */

package restaurant;

public abstract class MenuItem
{
	protected String name;
	
	protected Quality qualityLevel;
	
	protected int price;
	
	
	public MenuItem( )
	{
		
	}
	
	public abstract int computeProductionPrice( );
	
	public MenuItem( String name ){
		this.name = name;
	}
	
	public void setPrice( int price )
	{
		this.price = price;
	}

	@Override
	public String toString( ) {
		// TODO Auto-generated method stub
		return name;
	}
	
}