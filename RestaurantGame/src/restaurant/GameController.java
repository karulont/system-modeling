/**
 * @(#) GameController.java
 */

package restaurant;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameController {
	
	public GameController() {
		player = new Player();
		restaurant = new Restaurant();
	}
	
	private Restaurant restaurant;
	
	private Player player;
	
	private ArrayList<Client> clients;
	
	public void chooseName( String name ) {
		player.name = name;
	}

	public void startGame() throws IOException {
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.addAll(restaurant.employees);
		persons.addAll(clients);
		generateNames(persons);
	}

	public void trainEmployee( Employee employee ) {

	}

	public void makeSelection( Waiter waiter, Table table ) {

	}

	public void setDishesQuality( int highNo) {
		int hCount=0;

		for(MenuItem e: restaurant.menuItems){

			if(MainDish.class.isInstance(e)){
				if(hCount++<highNo)
					e.qualityLevel=Quality.HIGH;
				else
					e.qualityLevel=Quality.LOW;
			}	
		}
	}

	public void setBeveragesQuality( int highNo) {
		int hCount=0;
		for(MenuItem e: restaurant.menuItems){
			if(Beverage.class.isInstance(e)){
				if(hCount++<highNo)
					e.qualityLevel=Quality.HIGH;
				else
					e.qualityLevel=Quality.LOW;
			}	
		}
	}

	public void setPrice( int lowDCost, int highDCost, int lowBCost, int highBCost ) {
		
		for(MenuItem e: restaurant.menuItems){
			if(MainDish.class.isInstance(e)){
				if(e.qualityLevel==Quality.LOW)
					e.setPrice(lowDCost);
				else
					e.setPrice(highDCost);
			}
			else{
				if(e.qualityLevel==Quality.LOW)
					e.setPrice(lowBCost);
				else
					e.setPrice(highBCost);
			}
		}
	}

	private static void generateNames(ArrayList<Person> persons) throws IOException {
		BufferedReader nameReader = new BufferedReader(new FileReader("names.txt"));
		ArrayList<String> names=new ArrayList<>();
		String line;
		while ((line=nameReader.readLine())!=null) {
			names.add(line);
		}
		nameReader.close();
		nameReader = new BufferedReader(new FileReader("surnames.txt"));
		ArrayList<String> surnames=new ArrayList<>();
		while ((line=nameReader.readLine())!=null) {
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

		int[] costs  = new int[4];
		System.out.println("Enter the cost of high quality dishes");
		costs[0]=Integer.parseInt(scn.next());
		
		System.out.println("Enter the cost of low quality dishes");
		costs[1]=Integer.parseInt(scn.next());
		
		System.out.println("Enter the cost of high quality beverages");
		costs[2]=Integer.parseInt(scn.next());
		
		System.out.println("Enter the cost of low quality beverages");
		costs[3]=Integer.parseInt(scn.next());
		
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
				case "echo":
					System.out.println(ln.next());
					break;
				default:
					System.out.println("Invalid input: " + line);
					break;
				}
			}
			catch (NoSuchElementException ex) {
				System.out.println("Invalid input: " + line);
			}
			finally {
				ln.close();
			}
		}
		scn.close();
	}

	public static void main(String[] args) throws IOException {
		GameController gc=new GameController();
		gc.mainLoop();
		/*
		ArrayList<Client> clients=new ArrayList<>();
		clients.add(new Client());
		clients.add(new Client());
		Barman bm=new Barman();
		ArrayList<Person> pl=new ArrayList<>();
		pl.addAll(clients);
		pl.add(bm);
		generateNames(pl);
		for (Person p : pl) {
			System.out.println(p.name + " " + p.surname);
		}*/
	}
}
