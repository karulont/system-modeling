/**
 * @(#) RankingList.java
 */

package restaurant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RankingList
{
	ArrayList<Player> rankings = new ArrayList<>();

	public void load() throws IOException {
		File f = new File("rankings.txt");
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
	}
	
	public void add(Player player) {
		for (int i = 0; i < rankings.size(); ++i) {
			if (player.score > rankings.get(i).score) {
				rankings.add(i, player);
				break;
			}
		}
		if (rankings.size() == 0) {
			rankings.add(player);
		}
	}

	public void printAndSave() throws IOException {
		System.out.println("Rankings are:");

		BufferedWriter rankWriter = new BufferedWriter(new FileWriter("rankings.txt"));
		for (int i = 0; i < rankings.size() && i < 10; ++i) {
			Player p = rankings.get(i);
			rankWriter.write(p.name);
			rankWriter.write(",");
			rankWriter.write(Integer.toString(p.score));
			rankWriter.newLine();
			System.out.println(p.name + "\t " + p.score);
		}
		rankWriter.flush();
		rankWriter.close();
		
	}
	
	
}
