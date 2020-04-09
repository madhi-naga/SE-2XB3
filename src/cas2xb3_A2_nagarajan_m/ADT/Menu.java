package cas2xb3_A2_nagarajan_m.ADT;

public class Menu implements Comparable<Menu>{

	private final String rest;
	private final String meal;
	private final double price;
	private final String comment;
	
	
	public Menu(String restaurant, String mealchoice, double mealprice, String mealcomment) {
		this.rest = restaurant;
		this.meal = mealchoice;
		this.price = mealprice;
		this.comment = mealcomment;
	}
	
	
	public String getRest() {
		return this.rest;
	}
	
	public String getMeal() {
		return this.meal;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getComm() {
		return this.comment;
	}

	@Override
	public int compareTo(Menu that) {
		if(this.price > that.price)
			return 1;
		else
			return -1;
	}
	
}
