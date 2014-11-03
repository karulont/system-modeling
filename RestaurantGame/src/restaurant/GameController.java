/**
 * @(#) GameController.java
 */

package restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class GameController {

	public GameController() {
		player = new Player();
		restaurant = new Restaurant();
	}

	private Restaurant restaurant;

	private Player player;

	private ArrayList<Client> clients;

	public void chooseName(String name) {
		player.name = name;
	}

	public void startGame() throws IOException {
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.addAll(restaurant.employees);
		persons.addAll(clients);
		generateNames(persons);
	}

	public void trainEmployee(Employee employee) throws GameException {
		int cost = employee.getTrainingCost();
		if (restaurant.budget < cost) {
			throw new GameException("Not enough funds!");
		}
		employee.increaseExperience();
		restaurant.budget -= cost;
	}

	public void makeSelection(ArrayList<Integer> tablesPerWaiter)
			throws GameException {
		if (tablesPerWaiter.size() != 3) {
			throw new GameException("Exactly three numbers should be present!");
		}
		for (Integer w : tablesPerWaiter) {
			if (w > 3 || w < 0) {
				throw new GameException(
						"Table numbers are not in required limits!");
			}
		}
		for (Waiter w : restaurant.waiters) {
			w.tables.clear();
		}
		ArrayList<Table> tables = new ArrayList<>(restaurant.tables);
		Random ran = new Random();
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < tablesPerWaiter.get(i); ++j) {
				restaurant.waiters.get(i).tables.add(tables.remove(ran
						.nextInt(tables.size())));
			}
		}
	}

	public void setDishesQuality(int highNo, int lowNo) {

	}

	public void setDishesQuality(int highNo) {
		int hCount = 0;

		for (MenuItem e : restaurant.menuItems) {

			if (MainDish.class.isInstance(e)) {
				if (hCount++ < highNo)
					e.qualityLevel = Quality.HIGH;
				else
					e.qualityLevel = Quality.LOW;
			}
		}
	}

	public void setBeveragesQuality(int highNo) {
		int hCount = 0;
		for (MenuItem e : restaurant.menuItems) {
			if (Beverage.class.isInstance(e)) {
				if (hCount++ < highNo)
					e.qualityLevel = Quality.HIGH;
				else
					e.qualityLevel = Quality.LOW;
			}
		}

	}

	public void setPrice(int lowDCost, int highDCost, int lowBCost,
			int highBCost) {

		for (MenuItem e : restaurant.menuItems) {
			if (MainDish.class.isInstance(e)) {
				if (e.qualityLevel == Quality.LOW)
					e.setPrice(lowDCost);
				else
					e.setPrice(highDCost);
			} else {
				if (e.qualityLevel == Quality.LOW)
					e.setPrice(lowBCost);
				else
					e.setPrice(highBCost);
			}
		}
	}

	private static void generateNames(ArrayList<Person> persons)
			throws IOException {
		BufferedReader nameReader = new BufferedReader(new FileReader(
				"names.txt"));
		ArrayList<String> names = new ArrayList<>();
		String line;
		while ((line = nameReader.readLine()) != null) {
			names.add(line);
		}
		nameReader.close();
		nameReader = new BufferedReader(new FileReader("surnames.txt"));
		ArrayList<String> surnames = new ArrayList<>();
		while ((line = nameReader.readLine()) != null) {
			surnames.add(line);
		}
		nameReader.close();
		Random r = new Random();
		for (Person p : persons) {
			p.name = names.get(r.nextInt(names.size()));
			p.surname = surnames.get(r.nextInt(surnames.size()));
		}
	}

	public void mainLoop() {

		System.out.println("Enter name!");
		Scanner scn = new Scanner(System.in);
		chooseName(scn.next());

		System.out.println("Enter the number of high quality dishes");
		setDishesQuality(Integer.parseInt(scn.next()));

		System.out.println("Enter the number of high quality beverages");
		setBeveragesQuality(Integer.parseInt(scn.next()));

		int[] costs = new int[4];
		System.out.println("Enter the cost of high quality dishes");
		costs[0] = Integer.parseInt(scn.next());

		System.out.println("Enter the cost of low quality dishes");
		costs[1] = Integer.parseInt(scn.next());

		System.out.println("Enter the cost of high quality beverages");
		costs[2] = Integer.parseInt(scn.next());

		System.out.println("Enter the cost of low quality beverages");
		costs[3] = Integer.parseInt(scn.next());

		setPrice(costs[0], costs[1], costs[2], costs[3]);

		boolean exit = false;
		while (!exit) {
			String line = scn.nextLine();
			Scanner ln = new Scanner(line);
			try {
				String token = ln.next();
				switch (token) {
				case "exit":
					exit = true;
					break;
				case "train":
					Employee emp = findEmployee(ln.nextLine());
					trainEmployee(emp);
					break;
				case "assign":
					ArrayList<Integer> list= new ArrayList<>();
					while (scn.hasNextInt()) {
						list.add(new Integer(scn.nextInt()));
					}
					makeSelection(list);
				default:
					System.out.println("Invalid input: " + line);
					break;
				}
			} catch (NoSuchElementException ex) {
				System.out.println("Invalid input: " + line);
			} catch (GameException e) {
				System.out.println(e.getMessage());
			} finally {
				ln.close();
			}
		}
		scn.close();
	}

	private Employee findEmployee(String nextLine) throws GameException {
		switch (nextLine) {
		case "barman":
			return restaurant.barman;
		case "chef":
			return restaurant.chef;
		case "waiter1":
			return restaurant.waiters.get(0);
		case "waiter2":
			return restaurant.waiters.get(1);
		case "waiter3":
			return restaurant.waiters.get(2);
		default:
			throw new GameException("Please choose either barman, chef, waiter1, waiter2 or waiter3.");
		}
	}

	public static void main(String[] args) throws IOException {
		GameController gc = new GameController();
		gc.mainLoop();
	}
}
