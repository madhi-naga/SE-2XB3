package cas2xb3_A2_nagarajan_m.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cas2xb3_A2_nagarajan_m.ReadData;
import cas2xb3_A2_nagarajan_m.ADT.City;
import cas2xb3_A2_nagarajan_m.ADT.Menu;

/*
 *  Creates a new Weighted Graph with Dijkstra functionality.
 */
public class CitiesDijkstra extends WCitiesGraph {

	private HashMap<City, City> changedAt = new HashMap<>(); //storing the shortest path based on where we move along the cheapest path 
	private HashMap<City, Double> spMap = new HashMap<>(); //shortest path map; corresponding city to its total weight 

	public CitiesDijkstra() {
		super();
	}

	/*
	 *  Determines the cheapest cost path of the Weighted Graph
	 *  Returning ArrayList as it enables abstraction when writing to a file etc. 
	 */
	public ArrayList<City> pathDijkstra(City src, City dest) {

		ArrayList<Menu> meals = ReadData.minMeals();
		Menu prevMeal = null;
		boolean chPrev = false;
		Double weight;

		// initializes all city weights to infinity
		for (City city : this.adjMap().keySet()) {
			city.setMeal(meals.get(0));
			if (city == src) {
				spMap.put(src, 0.0);
				src.setMeal(null);
			} else
				spMap.put(city, Double.POSITIVE_INFINITY);
		}

		
		for (WEdge edge : this.adjMap().get(src)) {
			spMap.put(edge.dest(), edge.getWeight());
			changedAt.put(edge.dest(), src);
		}
		src.mark();


		prevMeal = prevMeal(spMap);

		while (true) {

			City currCity = closestCity(spMap);

			if (currCity.getMeal() == prevMeal)
				chPrev = true;

			//once we reached the destination, we form and return an arraylist 
			if (currCity == dest) {
				City child = dest;
				ArrayList<City> citypath = new ArrayList<>();
				citypath.add(dest);
				while (true) {
					City parent = changedAt.get(child);
					if (parent == null) {
						break;
					}
					citypath.add(parent);
					child = parent;
				}

				return citypath; 
			}
			currCity.mark();

			//finding the cheapest edge
			for (WEdge edge : this.adjMap().get(currCity)) {

				if (edge.dest().isMarked())
					continue;

				if (chPrev) {
					weight = edge.getWeight2();
					edge.dest().setMeal(edge.dest().getMeal2());
				} 
				else {
					weight = edge.getWeight();
					edge.dest().setMeal(edge.dest().getMeal());
				}
				
				if (spMap.get(currCity) + weight < spMap.get(edge.dest())) {
					spMap.put(edge.dest(), spMap.get(currCity) + weight);
					changedAt.put(edge.dest(), currCity);
					prevMeal = edge.dest().getMeal();
				}

			}
			chPrev = false;
		}

	}

	/*
	 *  Finds the closest city that is both adjacent lowest weight
	 */
	private City closestCity(HashMap<City, Double> spMap) {

		double shortestDist = Double.POSITIVE_INFINITY;
		City closeCity = null;
		for (City city : this.adjMap().keySet()) {
			if (city.isMarked())
				continue;

			double currDist = spMap.get(city);
			if (currDist == Double.POSITIVE_INFINITY)
				continue;

			if (currDist < shortestDist) {
				shortestDist = currDist;
				closeCity = city;
			}
		}
		return closeCity;
	}

	/*
	 *  Resets all visited cities to unvisited
	 */
	public void resetMark(){
		for(City c : this.adjMap().keySet()) {
			c.unmark();
		}
	}
	
	private Menu prevMeal(HashMap<City, Double> spMap) {
		City c = closestCity(spMap);
		return c.getMeal();
	}

	public HashMap<City, Double> spMap(){
		return spMap;
	}
	
}
