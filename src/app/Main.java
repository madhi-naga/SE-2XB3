package cas2xb3_A2_nagarajan_m;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import cas2xb3_A2_nagarajan_m.ADT.City;
import cas2xb3_A2_nagarajan_m.Graph.CitiesBFS;
import cas2xb3_A2_nagarajan_m.Graph.CitiesDFS;
import cas2xb3_A2_nagarajan_m.Graph.CitiesDijkstra;

public class Main {
	
	
	//static such that multiple static methods can write to file
	static File output = new File("./a2_out.txt");
	static FileWriter fileWriter;
	static PrintWriter printWriter;
	static {
		try {
			output.createNewFile();
			fileWriter = new FileWriter(output);
			printWriter = new PrintWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	static City bos = ReadData.findCity("BOSTON");
	static City min = ReadData.findCity("MINNEAPOLIS");
	
	
	public static void RunDFS() {

		CitiesDFS dfsG  = new CitiesDFS();

		ArrayList<City> p = dfsG.pathDFS(bos, min);
		
		printWriter.print("DFS: ");
		for(int i = 0; i < p.size(); i++) {
			printWriter.print(p.get(i).getCity() + ", ");
		}
		printWriter.println();
		dfsG.resetMark();
	}
	
	public static void RunBFS() {
		CitiesBFS bfsG  = new CitiesBFS();

		ArrayList<City> p = bfsG.pathBFS(bos, min);
		Collections.reverse(p);
		
		printWriter.print("BFS: ");
		for (int i = 0; i < p.size(); i++) {
			printWriter.print(p.get(i).getCity() + ", ");
		}
		
		printWriter.println();
		bfsG.resetMark();
	}
		
	public static void RunDijkstraPath() {

		CitiesDijkstra dG = new CitiesDijkstra();
		ArrayList<City> path = dG.pathDijkstra(bos, min);
		Collections.reverse(path);
		
		printWriter.println();
		printWriter.println("The Cheapest Cost Path between " + bos.getCity() + " and "
				+ min.getCity() + " is: ");
		printWriter.println();
		printWriter.format("%20s%32s%16s", "City", "Meal Choice", "Cost of Meal");
		printWriter.println();
		
		//City c = null;
		for (City c : path) {
			printWriter.format("%20s%32s%16s", c.getCity(), c.getMeal().getMeal(), c.getMeal().getPrice());
			printWriter.println();
		}
		printWriter.println();
		printWriter.println("The Total Path Cost: $" + dG.spMap().get(min));
		
		printWriter.println();
		dG.resetMark();
	}
	
	public static void main(String[] args) {
		RunBFS();
		RunDFS();
		RunDijkstraPath();
		
		printWriter.close();
	}
	
}
