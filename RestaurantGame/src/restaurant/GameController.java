/**
 * @(#) GameController.java
 */

package restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameController {
	public void chooseName(String name) {

	}

	public void startGame() {

	}

	public void trainEmployee(Employee employee) {

	}

	public void makeSelection(Waiter waiter, Table table) {

	}

	public void setDishesQuality(int highNo, int lowNo) {

	}

	public void setBeveragesQuality(int highNo, int lowNo) {

	}

	public void setPrice(float lowDCost, float highDCost, float lowBCost,
			float highBCost) {

	}

	private void generateNames(ArrayList<Person> persons) throws IOException {
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
			names.add(line);
		}
		nameReader.close();
		Random r = new Random();
		for (Person p : persons) {
			p.name = names.get(r.nextInt(names.size()));
			p.surname = names.get(r.nextInt(surnames.size()));
		}
	}
}
