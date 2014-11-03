/**
 * @(#) MainDish.java
 */

package restaurant;

public class MainDish extends MenuItem {
	public MainDish(String name, int calories) {
		super(name);
		calorieCount = calories;
	}

	protected int calorieCount;

	public int computeProductionPrice() {
		switch (qualityLevel) {
		case LOW:
			return 3;
		case HIGH:
			return 10;
		default:
			throw new RuntimeException();
		}
	}

}
