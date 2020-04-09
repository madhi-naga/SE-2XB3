package cas2xb3_A2_nagarajan_m.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import cas2xb3_A2_nagarajan_m.ADT.City;

/*
 *  Creates a digraph with DFS functionality
 */
public class CitiesDFS extends CitiesGraph {

	private static boolean end = false; 
	private ArrayList<City> dfspath = new ArrayList<>();
	
	public CitiesDFS() {
		super();		
	}

	/*
	 *  Computes a DFS path of a whole digraph 
	 *  Even cities not reachable
	 */
	public ArrayList<City> fullDFS(City c) {
		cityDFS(c);
		
		for(City c2 : this.adjMap().keySet()) {
			if(!c2.isMarked()) 
				cityDFS(c2);
		}
		return dfspath;
	}
	
	/*
	 *  Computes a DFS path based on all cities reachable by the source city
	 */
	public void cityDFS(City c) {
		c.mark();
		dfspath.add(c);
		
		LinkedList<City> adjs = this.adjMap().get(c);
		if(adjs == null) return;
		
		for(City adjc : adjs) {
			if(!adjc.isMarked()) cityDFS(adjc);
		}
		
	}

	/*
	 *  Computes shortest BFS path given source and destination
	 *  Returning ArrayList as it enables abstraction when writing to a file etc. 
	 */
	public ArrayList<City> pathDFS(City c, City c2) {
		if(end) return dfspath;
		
		c.mark();
		dfspath.add(c);
		
		if(c == c2) {
			end = true;
			return dfspath;
		}
		
		LinkedList<City> adjs = this.adjMap().get(c);
		if(adjs == null) return null;
		
		for(City adjc : adjs) {
			if(!adjc.isMarked()) pathDFS(adjc, c2);
		}
		return dfspath;
	}
	

	public boolean hasPathTo(City c, City c2) {
		pathDFS(c, c2);
		return c2.isMarked();
	}
	
	//reset all cities to unvisited
	public void resetMark(){
		for(City c : this.adjMap().keySet()) {
			c.unmark();
		}
	}
	

}
