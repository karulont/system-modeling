/**
 * @(#) Restaurant.java
 */

package restaurant;

import java.util.ArrayList;
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

	protected ArrayList<Beverage> beverages;
	protected ArrayList<MainDish> maindishes;

	protected ArrayList<Orders> orders;

	public Restaurant() {
		name = "Sad Chef's inn";
		address = "Ivory road, 45";
		city = "Burgsburg";
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
		maindishes = new ArrayList<>(5);

		for (int i = 0; i < 5; i++) {
			maindishes.add(new MainDish());
			beverages.add(new Beverage());
		}
		
		orders = new ArrayList<>();
	}

	public void paySuppliers(int startDay) {
		int sum = 0;
		for (Orders o : orders) {
			if (o.date > startDay - 7) {
				sum += o.beverage.computeProductionPrice();
				sum += o.dish.computeProductionPrice();
			}
		}
		budget -= sum;
	}

	public void computeReputation(int clientSatisfaction) {

	}

	public void payUtilities(int amount) {

	}

	public void paySalaries(int amount) {

	}

	public void populateTables(ArrayList<Client> clients) {
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
	
	public void clearTables() {
		for (Table t : tables) {
			t.clients.clear();
		}
	}

	public void serviceTables(int day) {
		Random ran = new Random();
		for (Table t : tables) {
			if (t.waiter != null)
				for (Client c : t.clients) {
					int clientSatisfaction = 0;
					Beverage b = beverages.get(ran.nextInt(beverages.size()));
					MainDish d = maindishes.get(ran.nextInt(maindishes.size()));
					budget += b.price;
					budget += d.price;
					double waiterThres = 0;
					double barmanThres = 0;
					double chefThres = 0;
					switch (t.waiter.experience) {
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
					if (b.qualityLevel == Quality.HIGH) {
						barmanThres += 0.2;
					}
					if (d.qualityLevel == Quality.HIGH) {
						chefThres += 0.2;
					}
					barmanThres -= 0.1 * ((b.price - b.computeProductionPrice()) % 3);
					chefThres -= 0.1 * ((b.price - b.computeProductionPrice()) % 3);
					clientSatisfaction += ran.nextDouble() > waiterThres ? -1
							: 1;
					clientSatisfaction += ran.nextDouble() > barmanThres ? -1
							: 1;
					clientSatisfaction += ran.nextDouble() > chefThres ? -1 : 1;
					reputationPoints += clientSatisfaction;
					orders.add(new Orders(c, b, d, day, clientSatisfaction));
				}
		}
	}

	public void computeClientStatistics() {

	}

	public void processOrder(MainDish dish, Beverage beverage, Table table) {

	}

}
