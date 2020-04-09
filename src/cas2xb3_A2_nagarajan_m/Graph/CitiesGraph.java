package cas2xb3_A2_nagarajan_m.Graph;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import cas2xb3_A2_nagarajan_m.ReadData;
import cas2xb3_A2_nagarajan_m.ADT.City;


public class CitiesGraph {

	private HashMap<City, LinkedList<City>> adjMap;
	
	public CitiesGraph() {
		adjMap = new HashMap<>();
		
		City[][] cc = null;
		try {
			cc = ReadData.readCC();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		for(int i = 0; i < cc.length; i++) {
			this.addEdge(cc[i][0], cc[i][1]);
			cc[i][0].unmark();
			cc[i][1].unmark();
		}
		
	}
	
	public void addEdge(City c1, City c2) {
		//check if the adjacent map contains source city
		if(!(adjMap.keySet().contains(c1)))
			adjMap.put(c1, null);
		
		LinkedList<City> adjc1 = adjMap.get(c1);
		
		//create a new list if source city doesn't have any edges yet
		if(adjc1 == null)
			adjc1 = new LinkedList<>();
		
		adjc1.add(c2);		
		adjMap.put(c1, adjc1);
	}

	public void printEdges() {
	    for (City city : adjMap.keySet()) {
	        System.out.print(city.getCity() + " has an edge towards:   ");
	        LinkedList<City> s = adjMap.get(city);
	        for (City adj : s) {
	            System.out.print(adj.getCity() + "   ");
	        }
	        System.out.println();
	    }
	}
	
	public int numCities() {
		return adjMap.keySet().size();
	}
	
	public boolean isEdge(City c1, City c2) {
		return adjMap.get(c1).contains(c2);		
	}
	
	public void resetMark(){
		for(City c : this.adjMap().keySet()) {
			c.unmark();
			for(City adj : this.adjMap().get(c))
				adj.unmark();
		}
	}
	
	public HashMap<City, LinkedList<City>> adjMap(){
		return adjMap;
	}
	

}
