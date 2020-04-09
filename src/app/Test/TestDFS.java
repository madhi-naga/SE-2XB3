/**
 * 
 */
package cas2xb3_A2_nagarajan_m.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cas2xb3_A2_nagarajan_m.ReadData;
import cas2xb3_A2_nagarajan_m.ADT.City;
import cas2xb3_A2_nagarajan_m.Graph.CitiesDFS;

/**
 * @author Madhi
 *
 */
class TestDFS {
	
	CitiesDFS digraph;
	City bos;
	City min;
	City phi;
	City mia;
	ArrayList<City> path;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		digraph = new CitiesDFS();
		bos = ReadData.findCity("Boston");
		min = ReadData.findCity("Minneapolis");
		phi = ReadData.findCity("PHILADELPHIA");
		mia = ReadData.findCity("Miami");
		path = new ArrayList<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		digraph.resetMark();
		digraph = null;
		bos = null;
		min = null;
		phi = null;
		mia = null;
		path = null;
	}

	/**
	 * Test by checking if the destination city is reachable from each city in the path
	 */
	@Test
	void testPathDFS() {
		path = digraph.pathDFS(bos, min);
		for(City c : path) {
			assertTrue(digraph.hasPathTo(c, min));
		}
		digraph.resetMark();
		
		path = digraph.pathDFS(phi, mia);
		for(City c : path) {
			assertTrue(digraph.hasPathTo(c, mia));
		}
		digraph.resetMark();
	}

	/**
	 * Test by checking if each city in the path is reachable by the source city
	 */
	@Test
	void testFullDFS() {
		path = digraph.fullDFS(bos);
		for(City c : path) {
			assertTrue(digraph.hasPathTo(bos, c));
		}
		digraph.resetMark();
		
		path = digraph.fullDFS(phi);
		for(City c : path) {
			assertTrue(digraph.hasPathTo(bos, c));
		}
		digraph.resetMark();
	}


}
