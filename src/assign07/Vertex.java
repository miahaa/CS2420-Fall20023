package assign07;

import java.util.ArrayList;
import java.util.List;
class Vertex<Type> {
    private Type data;
    private List<Edge<Type>> edges;
    private boolean isVisited;
    private Vertex<Type> prev;
    private int inDegree;

    public Vertex(Type data) {
        this.data = data;
        edges = new ArrayList<>();
        isVisited = false;
        inDegree = 0;
    }

    public Type getData() {
        return data;
    }

    public List<Edge<Type>> getEdges() {
        return edges;
    }

    public void addEdges(Edge<Type> edge)
    {
        edges.add(edge);
    }
    public boolean isVisited(){return isVisited;}

    public void markVisited(){isVisited = true;}

    public ArrayList<Vertex<Type>> getNeighbors()
    {
        ArrayList<Vertex<Type>> neighbors = new ArrayList<>();
        for (Edge<Type> edge : getEdges())
            neighbors.add(edge.getDestination());

        return neighbors;
    }

    public Vertex getPreviousVertex() {
        return prev;
    }

    @SuppressWarnings("Unchecked")
    public void setPreviousVertex(Vertex prev) {
        this.prev = prev;
    }

    public int getInDegree() {
        return inDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }
}