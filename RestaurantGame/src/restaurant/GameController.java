/**
 * @(#) GameController.java
 */

package restaurant;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileWriter;
import java.io.IOException;

public class GameController {

	private Restaurant restaurant;

	private Player player;

	private ArrayList<Client> clients;

	private int day = 1;

	public GameController( ) {
		restaurant = new Restaurant();
	}

	public void chooseName( String name ) {
		player = new Player(name, 0);
	}

	public boolean simulateDay( ) throws GameException, IOException {
		// every day stuff
		restaurant.populateTables(clients);
		restaurant.serviceTables(day);
		restaurant.clearTables();

		// special cases
		if (day == 7 || day == 14 || day == 21 || day == 28) {
			// pay salary
			for (Employee e : restaurant.employees) {
				e.computeSalary();
				restaurant.budget -= e.salary;
			}
			restaurant.paySuppliers(day);
			System.out.println("End of week, paying supplies and salaries.");
		}
		if (day == 30) {
			// end of game
			player.score = restaurant.budget - 4000;
			System.out
					.println("30 days have passed, game is over, your final score is "
							+ player.score);
			ProcessRankings();
			return false;
		}
		System.out.println("Budget after day " + day + " is "
				+ restaurant.budget + " reputation is "
				+ restaurant.reputationPoints);
		day++;
		if (restaurant.budget > 0) {
			return true;
		}
		else {
			System.out.println("Restaraunt's budget is negative, game over.");
			return false;
		}
	}

	private void ProcessRankings( ) throws IOException {
		File f = new File("rankings.txt");
		ArrayList<Player> rankings = new ArrayList<>();
		if (f.exists()) {
			BufferedReader rankingsReader = new BufferedReader(
					new FileReader(f));
			String line;
			while ((line = rankingsReader.readLine()) != null) {
				if (line.equals("")) {
					continue;
				}
				String[] ps = line.split(",");
				rankings.add(new Player(ps[0], Integer.parseInt(ps[1])));
			}
			rankingsReader.close();
		}
		for (int i = 0; i < rankings.size(); ++i) {
			if (player.score > rankings.get(i).score) {
				rankings.add(i, player);
				break;
			}
		}
		if (rankings.size() == 0) {
			rankings.add(player);
		}

		System.out.println("Rankings are:");

		BufferedWriter rankWriter = new BufferedWriter(new FileWriter(f));
		for (int i = 0; i < rankings.size() && i < 10; ++i) {
			Player p = rankings.get(i);
			rankWriter.write(p.name);
			rankWriter.write(",");
			rankWriter.write(p.score);
			rankWriter.newLine();
			System.out.println(p.name + "\t " + p.score);
		}
		rankWriter.flush();
		rankWriter.close();
	}

	public void trainEmployee( Employee employee ) throws GameException {
		int cost = employee.getTrainingCost();
		if (restaurant.budget < cost) {
			throw new GameException("Not enough funds!");
		}
		employee.increaseExperience();
		restaurant.budget -= cost;
	}

	public void makeSelection( ArrayList<Integer> tablesPerWaiter ) throws GameException {
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
		for (Table t : restaurant.tables) {
			t.waiter = null;
		}
		ArrayList<Table> tables = new ArrayList<>(restaurant.tables);
		Random ran = new Random();
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < tablesPerWaiter.get(i); ++j) {
				tables.remove(ran.nextInt(tables.size())).assignToWaiter(
						restaurant.waiters.get(i));
			}
		}
	}

	public void setDishesQuality( int highNo ) throws GameException {
		if (highNo > 5 || highNo < 0)
			throw new GameException("Number of dishes not within limits!");

		int hCount = 0;

		for (MainDish d : restaurant.maindishes) {
			if (hCount++ < highNo)
				d.qualityLevel = Quality.HIGH;
			else
				d.qualityLevel = Quality.LOW;
		}
	}

	public void setBeveragesQuality( int highNo ) throws GameException {
		if (highNo > 5 || highNo < 0)
			throw new GameException("Number of beverages not within limits!");

		int hCount = 0;
		for (Beverage b : restaurant.beverages) {
			if (hCount++ < highNo)
				b.qualityLevel = Quality.HIGH;
			else
				b.qualityLevel = Quality.LOW;
		}

	}

	public void setPrice( int lowDCost, int highDCost, int lowBCost, int highBCost ) {

		for (MainDish e : restaurant.maindishes) {
			if (e.qualityLevel == Quality.LOW)
				e.setPrice(lowDCost);
			else
				e.setPrice(highDCost);
		}
		for (Beverage b : restaurant.beverages) {
			if (b.qualityLevel == Quality.LOW)
				b.setPrice(lowBCost);
			else
				b.setPrice(highBCost);
		}
	}

	private static void generateNames( ArrayList<Person> persons ) throws IOException {
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

	public void startGame( ) throws IOException {

		System.out.println("Enter name!");
		Scanner scn = new Scanner(System.in);
		chooseName(scn.next());

		clients = new ArrayList<>(18);
		for (int i = 0; i < 18; ++i) {
			clients.add(new Client());
		}
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.addAll(restaurant.employees);
		persons.addAll(clients);
		generateNames(persons);

		boolean menuDefined = false;
		while (!menuDefined) {
			try {

				System.out.println("Enter the number of high quality dishes");
				setDishesQuality(scn.nextInt());

				System.out
						.println("Enter the number of high quality beverages");
				setBeveragesQuality(scn.nextInt());

				int[] costs = new int[4];
				System.out.println("Enter the cost of high quality dishes");
				costs[0] = scn.nextInt();

				System.out.println("Enter the cost of low quality dishes");
				costs[1] = scn.nextInt();

				System.out.println("Enter the cost of high quality beverages");
				costs[2] = scn.nextInt();

				System.out.println("Enter the cost of low quality beverages");
				costs[3] = scn.nextInt();

				setPrice(costs[0], costs[1], costs[2], costs[3]);

				menuDefined = true;

			} catch (GameException e) {
				System.out.println(e.getMessage());
			} catch (NumberFormatException ex) {
				System.out.println("Invalid input");
			}
		}

		try {
			boolean run = true;
			while (run) {
				String line = scn.nextLine();
				if (line == "") {
					continue;
				}
				Scanner ln = new Scanner(line);
				try {
					String token = ln.next();
					switch (token) {
					case "exit":
						run = false;
						break;
					case "train":
						Employee emp = findEmployee(ln.next());
						trainEmployee(emp);
						break;
					case "assign":
						ArrayList<Integer> list = new ArrayList<>();
						while (ln.hasNextInt()) {
							list.add(new Integer(ln.nextInt()));
						}
						makeSelection(list);
						break;
					case "day":
						run = simulateDay();
						break;
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
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
		} finally {
			scn.close();
		}
	}

	private Employee findEmployee( String nextLine ) throws GameException {
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
			throw new GameException(
					"Please choose either barman, chef, waiter1, waiter2 or waiter3.");
		}
	}

	public static void main( String[] args ) throws IOException {
		GameController gc = new GameController();
		gc.startGame();
	}

	public static <E> ArrayList<E> getRandomElements( ArrayList<E> original, int count ) {
		ArrayList<Integer> indexes = new ArrayList<>();
		for (int i = 0; i < original.size(); ++i) {
			indexes.add(new Integer(i));
		}
		ArrayList<E> result = new ArrayList<>(count);
		Random ran = new Random();
		for (int i = 0; i < count; ++i) {
			result.add(original.get(indexes.remove(ran.nextInt(indexes.size()))));
		}
		return result;
	}
}
