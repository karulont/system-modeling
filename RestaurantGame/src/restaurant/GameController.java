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
	}
	
	private Restaurant restaurant;
	
	private Player player;
	
	private ArrayList<Client> clients;
	
	public void chooseName( String name ) {
		player.name = name;
	}

	public void startGame( ) throws IOException {
		restaurant = new Restaurant();
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.addAll(restaurant.employees);
		persons.addAll(clients);
		generateNames(persons);
	}

	public void trainEmployee( Employee employee ) {

	}

	public void makeSelection( Waiter waiter, Table table ) {

	}

	public void setDishesQuality( int highNo, int lowNo ) {
		
	}

	public void setBeveragesQuality( int highNo, int lowNo ) {

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
