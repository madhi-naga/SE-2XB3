/**
 * 
 */
package cas2xb3_A2_nagarajan_m.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cas2xb3_A2_nagarajan_m.ReadData;
import cas2xb3_A2_nagarajan_m.ADT.City;
import cas2xb3_A2_nagarajan_m.Graph.CitiesBFS;

/**
 * @author Madhi
 *
 */
class TestBFS {

	CitiesBFS digraph;
	City bos;
	City min;
	City nyc;
	City chr;
	ArrayList<City> path;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		digraph = new CitiesBFS();
		path = new ArrayList<>();
		bos = ReadData.findCity("Boston");
		min = ReadData.findCity("Minneapolis");
		nyc = ReadData.findCity("New York City");
		chr = ReadData.findCity("Charlotte");
		
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
		nyc = null;
		chr = null;
	}

	/**
	 * Test by checking if size is equal to expected shortest path size 
	 * and checking if each edge is valid
	 */
	@Test
	void testPathBFS() {
		path = digraph.pathBFS(bos, min);
		Collections.reverse(path); 
		
		assertEquals(path.size(), 6); //checking if path is the shortest path (by size) 		

		//checking if a valid edge is done
		for (int i = 0; i < path.size() - 1; i++) {
			assertTrue(digraph.isEdge(path.get(i), path.get(i+1)));
		}
		
		digraph.resetMark();
		path = digraph.pathBFS(nyc, chr);
		Collections.reverse(path);
		assertEquals(path.size(), 5);	

		for (int i = 0; i < path.size() - 1; i++) {
			assertTrue(digraph.isEdge(path.get(i), path.get(i+1)));
		}
			
	}

}
