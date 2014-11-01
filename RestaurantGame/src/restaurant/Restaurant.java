/**
 * @(#) Restaurant.java
 */

package restaurant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Restaurant {
	private String name;

	private String address;

	private String city;

	private int budget;

	private int reputationPoints;

	private ArrayList<Table> tables;

	protected ArrayList<Employee> employees;

	protected ArrayList<MenuItem> menuItems;

	private Orders orders;

	public Restaurant() {
		name = "Sad Chef's inn";
		address = "Ivory road, 45";
		city = "Burgsburg";
		budget = 10000;
		tables = new ArrayList<>(9);
		for (int i = 0; i < 9; ++i) {
			tables.add(new Table(i+1));
		}
		employees = new ArrayList<>(5);
		employees.add(new Chef());
		employees.add(new Barman());
		for (int i = 0; i < 3; ++i) {
			employees.add(new Waiter());
		}
		
		for (int i = 0; i<5; i++){
			menuItems.add(new MainDish());
			menuItems.add(new Beverage());
		}
	}

	public void paySuppliers(int amount) {

	}

	public void computeReputation(int clientSatisfaction) {

	}

	public void payUtilities(int amount) {

	}

	public void paySalaries(int amount) {

	}

	public void populateTables(Collection<Client> clients) {
		Random ran=new Random();
		clients.size();
	}

	public void computeClientStatistics() {

	}

	public void payTraining(int amount) {

	}

	public void processOrder(MainDish dish, Beverage beverage, Table table) {

	}
	
}
