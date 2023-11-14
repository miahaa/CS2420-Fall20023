package assign07;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Graph<Type> {

    private List<Vertex<Type>> vertices;
    private List<Edge<Type>> edges;

    private HashMap<Type, Vertex<Type>> map;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        map = new HashMap<>();
    }

    public void addVertex(Type data) {
        Vertex<Type> vertex = map.get(data);
        if (vertex == null) {
            vertex = new Vertex<>(data);
            vertices.add(vertex);
            map.put(data, vertex);
        }
    }

    public void addEdge(Type sourceData, Type destinationData) {
        Vertex<Type> source = map.get(sourceData);
        Vertex<Type> destination = map.get(destinationData);


        if (source != null && destination != null) {
            Edge<Type> edge = new Edge<>(source, destination);
            edges.add(edge);
            source.addEdges(edge);
            destination.setInDegree(destination.getInDegree() + 1);
        }
    }


    public List<Vertex<Type>> getVertices() {
        return vertices;
    }

    public List<Edge<Type>> getEdges() {
        return edges;
    }

    public Vertex<Type> getVertex(Type data)
    {
        if (!map.containsKey(data))
            return null;
        return map.get(data);
    }
}
