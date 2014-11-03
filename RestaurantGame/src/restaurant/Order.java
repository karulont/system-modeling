/**
 * @(#) Orders.java
 */

package restaurant;

public class Order {
	public Order(Client cl, Beverage bv, MainDish md, int day, int clSat) {
		client = cl;
		beverage = bv;
		dish = md;
		date = day;
		clientSatisfaction = clSat;
	}

	protected int date;

	protected String orderNo;

	protected int clientSatisfaction;
	protected Client client;
	protected Beverage beverage;
	protected MainDish dish;

}