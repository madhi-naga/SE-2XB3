package cas2xb3_A2_nagarajan_m.Graph;

import java.io.IOException;

import java.util.HashMap;
import java.util.LinkedList;

import cas2xb3_A2_nagarajan_m.ReadData;
import cas2xb3_A2_nagarajan_m.ADT.City;


public class WCitiesGraph {

	private HashMap<City, LinkedList<WEdge>> adjMap; 
	
	public WCitiesGraph() {
		adjMap = new HashMap<>();
		
		City[][] cc = null;
		try {
			cc = ReadData.readCC();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		for(int i = 0; i < cc.length; i++) {
			this.addEdge(cc[i][0], cc[i][1]);
			
			//ensures that all cities are unvisited
			cc[i][0].unmark();
			cc[i][1].unmark();
		}
		
	}
	
	public void addEdge(City c1, City c2) {
		//check if the adjacent map contains source city
		if(!(adjMap.keySet().contains(c1)))
			adjMap.put(c1, null);
		
		if(!(adjMap.keySet().contains(c2)))
			adjMap.put(c2, null);
		
		LinkedList<WEdge> adjc1 = adjMap.get(c1);
		LinkedList<WEdge> adjc2 = adjMap.get(c2);
		
		//create a new list if source city doesn't have any edges yet
		if(adjc1 == null)
			adjc1 = new LinkedList<>();
		if(adjc2 == null)
			adjc2 = new LinkedList<>();
		
		//we add corresponding edges to each city
		adjc1.add(new WEdge(c1, c2));
		adjc2.add(new WEdge(c1, c2));
		
		adjMap.put(c1, adjc1);
		adjMap.put(c2, adjc2);
	}

	public void printEdges() {
	    for (City city : adjMap.keySet()) {
	        System.out.print(city.getCity() + " has an edge towards:   ");
	        LinkedList<WEdge> s = adjMap.get(city);
	        for (WEdge adj : s) {
	            System.out.print(adj.dest().getCity() + " - ");
	            System.out.print(adj.getWeight() + "   ");
	        }
	        System.out.println();
	    }
	}
	
	public int numCities() {
		return adjMap.keySet().size();
	}
	

	public HashMap<City, LinkedList<WEdge>> adjMap(){
		return adjMap;
	}

}
