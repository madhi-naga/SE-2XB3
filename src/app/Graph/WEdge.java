package cas2xb3_A2_nagarajan_m.Graph;

import java.io.File;
import java.util.ArrayList;

import cas2xb3_A2_nagarajan_m.ReadData;
import cas2xb3_A2_nagarajan_m.ADT.City;
import cas2xb3_A2_nagarajan_m.ADT.Menu;

public class WEdge {
	private final City v; 
	private final City w; 
	private Double weight; 
	private Double weight2; 

	public WEdge(City v, City w) {
		this.v = v;
		this.w = w;
		this.weight = Double.POSITIVE_INFINITY;
		this.weight2 = Double.POSITIVE_INFINITY;
		setWeights();
	}

	public City src() {
		return this.v;
	}

	public City dest() {
		return this.w;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public double getWeight2() {
		return this.weight2;
	}

	public void setWeights() {
		//this.weight = w;
		ArrayList<Menu> meals = ReadData.minMeals();
		//Collections.sort(meals);
		
		if(this.hasMcD()) {
			this.weight = meals.get(0).getPrice();
			this.weight2 = meals.get(1).getPrice();
			this.w.setMeal(meals.get(0));
			this.w.setMeal2(meals.get(1));
		}
		else if (this.hasBK()) {
			this.weight = meals.get(2).getPrice();
			this.weight2 = meals.get(3).getPrice();
			this.w.setMeal(meals.get(2));
			this.w.setMeal2(meals.get(3));
		}
		else if (this.hasWendy()) {
			this.weight = meals.get(4).getPrice();
			this.weight2 = meals.get(5).getPrice();
			this.w.setMeal(meals.get(4));
			this.w.setMeal2(meals.get(5));
		}
		
	}

	public boolean hasMcD() {
		File f = new File("data/mcdonalds.csv");
		return ReadData.checkRest(this.w, f);
	}
	
	public boolean hasBK() {
		File f = new File("data/burgerking.csv");
		return ReadData.checkRest(this.w, f);
	}
	
	public boolean hasWendy() {
		File f = new File("data/wendys.csv");
		return ReadData.checkRest(this.w, f);
	}
		
	public String toString() {
		return String.format(v + "->" + w + ":" + weight);
	}
	
	public int compareTo(WEdge that) {

	    if (this.weight > that.weight)
	        return 1;
	    else return -1;
	}
	

}
