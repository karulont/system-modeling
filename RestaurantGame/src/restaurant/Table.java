/**
 * @(#) Table.java
 */

package restaurant;

public class Table {
	private int number;

	public Table(int number) {
		this.number = number;
	}

	public void assignToWaiter(Waiter waiter){
		waiter.tables.add(this);
	}

}
