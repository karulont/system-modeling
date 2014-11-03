/**
 * @(#) Table.java
 */

package restaurant;

import java.util.ArrayList;

public class Table {
	private int number;
	protected ArrayList<Client> clients;
	protected Waiter waiter;

	public Table( int number ) {
		this.number = number;
		clients = new ArrayList<>();
	}

	public void assignToWaiter( Waiter waiter ) {
		waiter.tables.add(this);
		this.waiter = waiter;
	}

	@Override
	public String toString( ) {
		return "table " + number;
	}
	
	
}
