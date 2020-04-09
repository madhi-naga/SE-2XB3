package cas2xb3_A2_nagarajan_m.ADT;

public class Restaurant {
	
	private final double lat;
	private final double lon;
	private final String rname;
	private final String stateabb;
	private final String address;
	private final String city;
	private final String phone;
	
	
	public Restaurant(double latitude, double longitude, String name, String addr, String cityname, String sabb, String phonenum) {
		this.lat = latitude;
		this.lon = longitude;
		this.rname = name.substring(1);
		this.stateabb = sabb;
		this.address = addr.substring(1);
		this.city = cityname.substring(1);
		this.phone = phonenum;
	}
	
	public String getAddr() {
		return this.address;
	}
	
	public String getRest() {
		return this.rname;
	}
	
	public double getLat() {
		return this.lat;
	}
	
	public double getLong() {
		return this.lon;
	}
	
	public String getState() {
		return this.stateabb;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
}
