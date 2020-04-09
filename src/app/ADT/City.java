package cas2xb3_A2_nagarajan_m.ADT;

public class City {
	
	private final int id;
	private final int statecode;
	private final int zip;
	private final String stateabb;
	private final String cityname;
	private final double lat;
	private final double lon;
	private boolean marked; //for checking if variable is already visited
	private Menu meal;
	private Menu meal2; //the 2nd cheapest meal the city can offer
	
	public City(int id, int scode, int zipcode, String sabb, String cname, double latitude, double longitude) {
		this.id = id;
		this.zip = zipcode;
		this.statecode = scode;
		this.stateabb = sabb;
		this.cityname = cname;
		this.lat = latitude;
		this.lon = longitude;
		this.meal = null;
		this.meal2 = null;
		marked = false;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getZip() {
		return this.zip;
	}
	
	public int getSC() {
		return this.statecode;
	}
	
	public String getSA() {
		return this.stateabb;
	}
	
	public String getCity() {
		return this.cityname;
	}
	
	public double getLat() {
		return this.lat;
	}
	
	public double getLong() {
		return this.lon;
	}
	
	public Menu getMeal() {
		if(this.meal == null) return new Menu("none", "none", 0, "none");
		return this.meal;
	}
	
	public void setMeal(Menu that) {
		this.meal = that;
	}
	
	public Menu getMeal2() {
		return this.meal2;
	}
	
	public void setMeal2(Menu that) {
		this.meal2 = that;
	}
	
	public void mark() {
		marked = true;
	}
	
	public void unmark() {
		marked = false;
	}

	//checks if city is already visited
	public boolean isMarked() {
		return marked;
	}
	
}
