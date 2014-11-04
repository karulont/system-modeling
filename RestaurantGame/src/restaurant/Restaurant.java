/**
 * @(#) Restaurant.java
 */

package restaurant;

import java.util.ArrayList;
import java.util.Random;

public class Restaurant {
	protected int budget;

	protected int reputationPoints;

	protected ArrayList<Table> tables;

	protected ArrayList<Employee> employees;
	protected Barman barman;
	protected Chef chef;

	protected ArrayList<Waiter> waiters;

	protected ArrayList<Beverage> beverages;
	protected ArrayList<MainDish> maindishes;

	protected ArrayList<Order> orders;

	public Restaurant( ) {
		budget = 10000;
		reputationPoints = 15;
		tables = new ArrayList<>(9);
		for (int i = 0; i < 9; ++i) {
			tables.add(new Table(i + 1));
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

		beverages = new ArrayList<>(5);
		beverages.add(new Beverage("Milk", 330));
		beverages.add(new Beverage("Tea", 275));
		beverages.add(new Beverage("Diet Coke", 500));
		beverages.add(new Beverage("Orange Juice", 330));
		beverages.add(new Beverage("Coffe", 275));
		maindishes = new ArrayList<>(5);
		maindishes.add(new MainDish("Steak", 400));
		maindishes.add(new MainDish("Soup", 240));
		maindishes.add(new MainDish("Club sandwich", 200));
		maindishes.add(new MainDish("Grill salad", 150));
		maindishes.add(new MainDish("Salmon", 300));

		orders = new ArrayList<>();
	}

	public void paySuppliers( int startDay ) {
		int sum = 0;
		for (Order o : orders) {
			if (o.date > startDay - 7) {
				sum += o.beverage.computeProductionPrice();
				sum += o.dish.computeProductionPrice();
			}
		}
		budget -= sum;
	}

	public void populateTables( ArrayList<Client> clients ) {
		int tablesOccupied = reputationPoints >= 30 ? 9
				: (reputationPoints >= 15 ? 5 : 2);
		ArrayList<Table> tables = GameController.getRandomElements(this.tables,
				tablesOccupied);
		ArrayList<Client> chosenClients = GameController.getRandomElements(
				clients, 2 * tablesOccupied);
		for (Table t : tables) {
			t.clients.add(chosenClients.remove(chosenClients.size() - 1));
			t.clients.add(chosenClients.remove(chosenClients.size() - 1));
		}
	}

	public void clearTables( ) {
		for (Table t : tables) {
			t.clients.clear();
		}
	}

	public void serviceTables( int day ) {
		Random ran = new Random();
		for (Table t : tables) {
			for (Client c : t.clients) {
				if (t.waiter == null) {
					System.out.println(c + " is sitting at " + t
							+ " and does not have a waiter!");
					reputationPoints -= 3;
				} else {
					Beverage b = beverages.get(ran.nextInt(beverages.size()));
					MainDish d = maindishes.get(ran.nextInt(maindishes.size()));
					budget += b.price;
					budget += d.price;
					Order o = processOrder(c, d, b, t, day);
					orders.add(o);
					c.orders.add(o);
				}
			}
		}
	}

	public Order processOrder( Client client, MainDish dish, Beverage beverage, Table table, int day ) {
		Random ran = new Random();
		int clientSatisfaction = 0;
		double waiterThres = 0;
		double barmanThres = 0;
		double chefThres = 0;
		switch (table.waiter.experience) {
		case LOW:
			waiterThres = 0.6;
			break;
		case AVERAGE:
			waiterThres = 0.8;
			break;
		case HIGH:
			waiterThres = 0.9;
			break;
		}
		switch (barman.experience) {
		case LOW:
			barmanThres = 0.4;
			break;
		case AVERAGE:
			barmanThres = 0.6;
			break;
		case HIGH:
			barmanThres = 0.8;
			break;
		}
		switch (chef.experience) {
		case LOW:
			chefThres = 0.4;
			break;
		case AVERAGE:
			chefThres = 0.6;
			break;
		case HIGH:
			chefThres = 0.8;
			break;
		}
		if (beverage.qualityLevel.equals(Quality.HIGH)) {
			barmanThres += 0.2;
		}
		if (dish.qualityLevel.equals(Quality.HIGH)) {
			chefThres += 0.2;
		}
		barmanThres -= 0.1 * ((beverage.price - beverage
				.computeProductionPrice()) % 3);
		chefThres -= 0.1 * ((dish.price - dish.computeProductionPrice()) % 3);
		clientSatisfaction += ran.nextDouble() > waiterThres ? -1 : 1;
		clientSatisfaction += ran.nextDouble() > barmanThres ? -1 : 1;
		clientSatisfaction += ran.nextDouble() > chefThres ? -1 : 1;
		System.out.println(client + " at " + table + " ordered " + dish
				+ " with " + beverage + " and gives " + clientSatisfaction
				+ " reputation points.");
		reputationPoints += clientSatisfaction;
		return new Order(client, beverage, dish, day, clientSatisfaction);
	}

}
