import java.util.*;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<Vertex<T>, List<Edge<T>>> map;

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        this.map = new HashMap<>();
    }

    public void addVertex(Vertex<T> v) {
        this.map.putIfAbsent(v, new LinkedList<>());
    }

    public void addEdge(Vertex<T> source, Vertex<T> dest, double weight) {
        this.addVertex(source);
        this.addVertex(dest);
        this.map.get(source).add(new Edge<>(source, dest, weight));
        if (this.undirected) {
            this.map.get(dest).add(new Edge<>(dest, source, weight));
        }
    }

    public List<Edge<T>> getEdges(Vertex<T> v) {
        return this.map.get(v);
    }
}