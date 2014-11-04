/**
 * @(#) Client.java
 */

package restaurant;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;

public class Client extends Person {
	public Client() {
		orders = new ArrayList<>();
	}

	protected ArrayList<Order> orders;

	public void computeStatistics() {
		IdentityHashMap<MainDish, Integer> dishmap = new IdentityHashMap<>();
		IdentityHashMap<Beverage, Integer> bevmap = new IdentityHashMap<>();
		double calories = 0;
		double volume = 0;
		int totalcost = 0;
		for (Order o : orders) {
			Integer dishTimes = dishmap.get(o.dish);
			if (dishTimes != null) {
				dishmap.put(o.dish, dishTimes + 1);
			} else {
				dishmap.put(o.dish, new Integer(1));
			}
			Integer bevTimes = bevmap.get(o.beverage);
			if (bevTimes != null) {
				bevmap.put(o.beverage, bevTimes + 1);
			} else {
				bevmap.put(o.beverage, new Integer(1));
			}
			calories += o.dish.calorieCount;
			volume += o.beverage.volume;
			totalcost += o.dish.price + o.beverage.price;
		}
		double avgcalories = calories / orders.size();
		double avgvolume = volume / orders.size();
		System.out.print(this + "\t");
		for (Map.Entry<MainDish, Integer> p : dishmap.entrySet()) {
			System.out.print(p.getKey() + " " + p.getValue() + "\t");
		}
		for (Map.Entry<Beverage, Integer> p : bevmap.entrySet()) {
			System.out.print(p.getKey() + " " + p.getValue() + "\t");
		}
		System.out.println(avgcalories + "\t" + avgvolume + "\t" + totalcost);
	}
}
