package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Contains several methods for solving problems on generic, directed, unweighted, sparse graphs.
 *
 * @author Prof. Parker & Khoa Minh Ngo & Thu Ha
 * @version October 19, 2023
 */
public class GraphUtility {

	/**
	 * This recursive method must use the depth-first search algorithm presented in lecture to determine
	 * whether there is a path from the vertex with srcData to the vertex with dstData in the graph.
	 * Throws an IllegalArgumentException if there does not exist a vertex in the graph with srcData, and likewise for dstData.
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		// Throw exception when sources and destinations have different sizes.
		if (sources.size() != destinations.size())
			throw new IllegalArgumentException("Different size between sources and destinations.");

		// Create a graph and add vertices and edges based on the provided sources and destinations.
		Graph<Type> graph = buildGraph(sources, destinations);

		// Find the source and destination vertices in the graph.
		Vertex<Type> srcVertex = graph.getVertex(srcData);
		Vertex<Type> dstVertex = graph.getVertex(dstData);


		if (srcVertex == null || dstVertex == null)
			throw new IllegalArgumentException("Source or destination vertex not found in the graph.");

		return dfs(srcVertex, dstVertex);
	}

	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		// Throw an exception when sources and destinations have different sizes.
		if (sources.size() != destinations.size())
			throw new IllegalArgumentException("Different size between sources and destinations.");

		// Create a graph and add vertices and edges based on the provided sources and destinations.
		Graph<Type> graph = buildGraph(sources, destinations);

		// Find the source and destination vertices in the graph.
		Vertex<Type> srcVertex = graph.getVertex(srcData);
		Vertex<Type> dstVertex = graph.getVertex(dstData);


		// Throw an exception if either the source or destination vertex is not found.
		if (srcVertex == null || dstVertex == null)
			throw new IllegalArgumentException("Source or destination vertex not found in the graph.");

		List<Type> path = bfs(srcVertex, dstVertex);
		if (path == null)
			throw new IllegalArgumentException("No path exists between these two vertices.");

		return path;
	}

	/**
	 * This method must use the topological sort algorithm presented in lecture to generate a sorted ordering of the
	 * vertices in the graph.  Note that a graph may have more than one valid ordering, and any such ordering is accepted.
	 * Throws an IllegalArgumentException if the graph contains a cycle (recall topological sort works only on acyclic graphs).
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {

		// Throw an exception when sources and destinations have different sizes.
		if (sources.size() != destinations.size())
			throw new IllegalArgumentException("Different size between sources and destinations.");

		// If the graph only have one element
		if (sources.size() == 1)
			if (sources.get(0).equals(destinations.get(0)))
			{
				ArrayList<Type> sortedList = new ArrayList<>();
				sortedList.add(sources.get(0));
				return sortedList;
			}

		// Create a graph and add vertices and edges based on the provided sources and destinations.
		Graph<Type> graph = buildGraph(sources, destinations);
		List<Type> sortedList = topologicalSort(graph);

		for (Vertex<Type> v : graph.getVertices())
			if (v.getInDegree() != 0)
				throw new IllegalArgumentException("There is a cycle.");

		return sortedList;
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 *
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments
	 * --accepts one edge per line or edges terminated with ;
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 *
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 *        "sources" list that can be passed to the public methods in this
	 *        class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *        "destinations" list that can be passed to the public methods in
	 *        this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while(scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if(line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if(edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while(scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for(int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if(vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if(vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if(substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}

	private static<Type> boolean dfs(Vertex<Type> source, Vertex<Type> target)
	{
		// Mark the source vertex as visited
		source.markVisited();

		// If the source vertex is the target, there is a path.
		if (source == target)
			return true;

		// Recursively visit adjacent unvisited vertices.
		for (Vertex<Type> neighbor : source.getNeighbors())
			if (!neighbor.isVisited())
				return dfs(neighbor, target);

		// If no path is found, return false.
		return false;
	}

	private static <Type> List<Type> bfs(Vertex<Type> srcVertex, Vertex<Type> dstVertex)
	{
		if (srcVertex == dstVertex)
		{
			ArrayList<Type> shortestPath = new ArrayList<>();
			shortestPath.add(srcVertex.getData());
			return shortestPath;
		}

		Queue<Vertex<Type>> queue = new LinkedList<>();
		queue.add(srcVertex);

		while (!queue.isEmpty())
		{
			Vertex<Type> startVertex = queue.remove();

			for (Vertex<Type> neighbor : startVertex.getNeighbors())
			{
				if (!neighbor.isVisited())
				{
					neighbor.markVisited();
					neighbor.setPreviousVertex(startVertex);

					if (neighbor.equals(dstVertex))
						return constructShortestPath(srcVertex, dstVertex);

					queue.add(neighbor);
				}
			}
		}
		return null;
	}

	private static <Type> List<Type> constructShortestPath(Vertex<Type> source, Vertex<Type> destination)
	{
		LinkedList<Type> orderedVert = new LinkedList<>();
		Vertex<Type> current = destination;
		while (current != source)
		{
			orderedVert.addFirst(current.getData());
			current = current.getPreviousVertex();
		}

		orderedVert.addFirst(source.getData());
		return orderedVert;
	}

	private static <Type> Graph<Type> buildGraph(List<Type> sources, List<Type> destinations)
	{
		// Create a graph and add vertices and edges based on the provided sources and destinations.
		Graph<Type> graph = new Graph<>();
		for (int i = 0; i < sources.size(); i++) {
			Type source = sources.get(i);
			Type destination = destinations.get(i);
			graph.addVertex(source);
			graph.addVertex(destination);
			graph.addEdge(source, destination);
		}

		return graph;
	}

	private static<Type> List<Type> topologicalSort(Graph<Type> graph)
	{
		Queue<Vertex<Type>> queue = new LinkedList<>();
		List<Type> sortedList = new LinkedList<>();

		for (Vertex<Type> v : graph.getVertices())
			if (v.getInDegree() == 0)
				queue.add(v);

		while (!queue.isEmpty())
		{
			Vertex<Type> v = queue.remove();
			sortedList.add(v.getData());
			for (Vertex<Type> vertex : v.getNeighbors())
			{
				vertex.setInDegree(vertex.getInDegree()-1);
				if (vertex.getInDegree() == 0)
					queue.add(vertex);
			}
		}

		return sortedList;
	}
}