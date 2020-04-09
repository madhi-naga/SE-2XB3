package cas2xb3_A2_nagarajan_m.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import cas2xb3_A2_nagarajan_m.ADT.City;

/*
 *  Creates a new Graph with BFS functionality.
 */
public class CitiesBFS extends CitiesGraph {

	static boolean end = false;

	public CitiesBFS() {
		super();
	}

	/*
	 *  Computes shortest BFS path given source and destination
	 *  Returning ArrayList as it enables abstraction when writing to a file etc. 
	 */
	public ArrayList<City> pathBFS(City src, City dest) {
		Queue<City> queue = new LinkedList<>();
		queue.add(src);

		Stack<City> stack = new Stack<City>();
		ArrayList<City> bfspath = new ArrayList<City>();

		boolean atDest = false;
		stack.add(src);
		while (!queue.isEmpty()) {
			if(atDest == true) break;
			City root = queue.poll();

			LinkedList<City> adjs = this.adjMap().get(root);
			
			if (root == dest)
				atDest = true;

			if(adjs == null) continue; 
			for (City adj : adjs) {
				if (!adj.isMarked()) {
					queue.add(adj);
					adj.mark();
					stack.add(adj);
				}
				if(adj == dest) atDest = true;
			}
		}

		
		// To find the path based on the stack
		// We go backwards starting from destination city
		City node;
		City currDest = dest;
		bfspath.add(dest);
		while (!stack.isEmpty()) {
			node = stack.pop();
			
			if (node == src) {
				bfspath.add(node);
				break;		
			}
			
			if (node == currDest) continue; 
			if(this.adjMap().get(node).contains(currDest))
			{
				bfspath.add(node);
				currDest = node;
			}
		}

		return bfspath;
	}

	
	public int pathSize(City c, City c2) {
		ArrayList<City> cities = pathBFS(c, c2);
		return cities.size();
	}
	
}
