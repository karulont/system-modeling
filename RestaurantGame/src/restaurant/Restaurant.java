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

	protected int budget;

	protected int reputationPoints;

	protected ArrayList<Table> tables;

	protected ArrayList<Employee> employees;
	protected Barman barman;
	protected Chef chef;
	
	protected ArrayList<Waiter> waiters;

	protected ArrayList<MenuItem> menuItems;

	private Orders orders;

	public Restaurant() {
		name = "Sad Chef's inn";
		address = "Ivory road, 45";
		city = "Burgsburg";
		budget = 10000;
		reputationPoints = 15;
		tables = new ArrayList<>(9);
		for (int i = 0; i < 9; ++i) {
			tables.add(new Table(i+1));
		}
		employees = new ArrayList<>(5);
		barman = new Barman();
		chef = new Chef();
		employees.add(barman);
		employees.add(chef);
		waiters = new ArrayList<>(3);
		for (int i = 0; i < 3; ++i) {
			waiters.add(new Waiter());
		}
		employees.addAll(waiters);

		menuItems = new ArrayList<>(10);


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

	public void processOrder(MainDish dish, Beverage beverage, Table table) {

	}
	
}
