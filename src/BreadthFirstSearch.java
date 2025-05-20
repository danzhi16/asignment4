import java.util.*;

public class BreadthFirstSearch<T> implements Search<T> {
    private Map<Vertex<T>, Vertex<T>> edgeTo = new HashMap<>();
    private Set<Vertex<T>> marked = new HashSet<>();
    private Vertex<T> start;

    @Override
    public void search(WeightedGraph<T> graph, Vertex<T> start) {
        this.start = start;
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(start);
        marked.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> v = queue.poll();
            for (Edge<T> edge : graph.getEdges(v)) {
                Vertex<T> w = edge.getDestination();
                if (!marked.contains(w)) {
                    edgeTo.put(w, v);
                    marked.add(w);
                    queue.add(w);
                }
            }
        }
    }

    @Override
    public Iterable<Vertex<T>> pathTo(Vertex<T> v) {
        if (!marked.contains(v))
            return null;
        LinkedList<Vertex<T>> path = new LinkedList<>();
        for (Vertex<T> x = v; x != start; x = edgeTo.get(x))
            path.addFirst(x);
        path.addFirst(start);
        return path;
    }
}