/**
 * @(#) MainDish.java
 */

package restaurant;

public class MainDish extends MenuItem {
	private int calorieCount;

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
