package cas2xb3_A2_nagarajan_m;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import cas2xb3_A2_nagarajan_m.ADT.City;
import cas2xb3_A2_nagarajan_m.ADT.Menu;
import cas2xb3_A2_nagarajan_m.ADT.Restaurant;

public class ReadData {

	private static City[] cities;

	//static so that the array and its objects stay constant in memory
	static {
		try {
			cities = readCities();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static City[] readCities() throws IOException {

		File fcities = new File("data/USCities.csv");
		String line = new String();
		City[] cities = new City[50];
		int x;

		try (BufferedReader br = new BufferedReader(new FileReader(fcities))) {
			br.readLine(); // skipping header line
			x = 0;
			while ((line = br.readLine()) != null) {
				String[] details = line.split(",");
				cities[x] = new City(x, Integer.parseInt(details[0]), Integer.parseInt(details[1]), details[2],
						details[3], Double.parseDouble(details[4]), Double.parseDouble(details[5]));
				x++;
			}
		}
		return cities;
	}

	public static Restaurant[] readRests(File frest, int n) throws IOException {

		String line = new String();
		Restaurant[] rests = new Restaurant[n];
		int x;

		try (BufferedReader br = new BufferedReader(new FileReader(frest))) {
			br.readLine(); // skipping header line
			x = 0;
			while ((line = br.readLine()) != null) {
				String[] details = line.split(",");
				rests[x] = new Restaurant(Double.parseDouble(details[1]), Double.parseDouble(details[0]), details[2],
						details[4], details[5], details[6], details[3]);
				x++;
			}
		}
		return rests;
	}

	//check if a restaurant is in a given city based on their coordinates
	public static boolean checkRest(City c, File f) {
		Restaurant[] rests = null;
		int n = 0;
		try {
			n = fileLines(f);
			rests = readRests(f, n);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i = 0;
		while (i < (n - 1)) {
			if (Math.abs(rests[i].getLat() - c.getLat()) < 0.5 && Math.abs(rests[i].getLong() - c.getLong()) < 0.5)
				return true;
			i++;
		}
		return false;
	}

	public static Menu[] readMenu() throws IOException {
		File fcities = new File("data/menu.csv");
		String line = new String();
		Menu[] items = new Menu[41];
		int x;
		String price = new String();
		try (BufferedReader br = new BufferedReader(new FileReader(fcities))) {
			br.readLine(); // skipping header line
			x = 0;
			while ((line = br.readLine()) != null) {
				String[] details = line.split(",");
				price = details[2].substring(1);
				items[x] = new Menu(details[0], details[1], Double.parseDouble(price), details[3]);
				x++;
			}
		}
		return items;
	}

	public static Menu minMeal(String rest) {
		Menu[] menu = null;
		try {
			menu = readMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double min = 1000;
		Menu minmeal = null;
		for (int i = 0; i < menu.length - 1; i++) {

			if (menu[i].getRest().equalsIgnoreCase(rest)) {
				if (menu[i].getPrice() < min) {
					min = menu[i].getPrice();
					minmeal = menu[i];
				}
			}
		}
		return minmeal;
	}

	//finds the next cheapest meal after the given meal
	public static Menu minMeal(String rest, Menu meal) {
		Menu[] menu = null;
		try {
			menu = readMenu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double min = 1000;
		Menu minmeal = null;
		for (int i = 0; i < menu.length - 1; i++) {
			if (menu[i].getRest().equalsIgnoreCase(rest)) {
				if (menu[i].getPrice() < min && !meal.getMeal().equals(menu[i].getMeal())) {
					min = menu[i].getPrice();
					minmeal = menu[i];
				}
			}
		}
		return minmeal;
	}

	//compiles cheapest meals from all restaurants
	public static ArrayList<Menu> minMeals() {
		ArrayList<Menu> meals = new ArrayList<>();

		meals.add(minMeal("McDonald’s"));
		meals.add(minMeal("McDonald’s", meals.get(0)));
		meals.add(minMeal("Burger King"));
		meals.add(minMeal("Burger King", meals.get(2)));
		meals.add(minMeal("Wendy’s"));
		meals.add(minMeal("Wendy’s", meals.get(4)));

		return meals;
	}

	//processing all edges
	public static City[][] readCC() throws IOException {
		File fcc = new File("data/ConnectedCities.txt");
		int lines = fileLines(fcc);

		String line = new String();
		City[][] cc = new City[lines][2];

		int x = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(fcc))) {
			while ((line = br.readLine()) != null) {
				cc[x][0] = null;
				cc[x][1] = null;
				City c1 = null;
				City c2 = null;

				String[] details = line.split(", ");
				c1 = findCity(details[0]);
				c2 = findCity(details[1]);

				if (c1 == null || c2 == null)
					throw new NullPointerException();

				cc[x][0] = c1;
				cc[x][1] = c2;
				x++;
			}
		}
		return cc;
	}

	public static City findCity(String citystr)  {
		// String str = citystr.toUpperCase();

		for (int i = 0; i < cities.length; i++) {
			if (citystr.equalsIgnoreCase(cities[i].getCity()))
				return cities[i];
		}
		return null;
	}

	public static int fileLines(File f) throws FileNotFoundException, IOException {
		int x = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			while (br.readLine() != null)
				x++;
		}
		return x;
	}

}
