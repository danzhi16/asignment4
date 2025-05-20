import java.util.*;

public class DijkstraSearch<T> implements Search<T> {
    private Map<Vertex<T>, Double> distTo = new HashMap<>();
    private Map<Vertex<T>, Vertex<T>> edgeTo = new HashMap<>();
    private PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
    private Vertex<T> start;

    @Override
    public void search(WeightedGraph<T> graph, Vertex<T> start) {
        this.start = start;
        distTo.put(start, 0.0);
        pq.add(new VertexDistance<>(start, 0.0));

        while (!pq.isEmpty()) {
            Vertex<T> v = pq.poll().getVertex();
            for (Edge<T> edge : graph.getEdges(v)) {
                relax(edge);
            }
        }
    }

    private void relax(Edge<T> edge) {
        Vertex<T> v = edge.getSource();
        Vertex<T> w = edge.getDestination();
        double weight = edge.getWeight();
        if (distTo.getOrDefault(w, Double.POSITIVE_INFINITY) > distTo.getOrDefault(v, Double.POSITIVE_INFINITY) + weight) {
            distTo.put(w, distTo.getOrDefault(v, Double.POSITIVE_INFINITY) + weight);
            edgeTo.put(w, v);
            pq.add(new VertexDistance<>(w, distTo.get(w)));
        }
    }

    @Override
    public Iterable<Vertex<T>> pathTo(Vertex<T> v) {
        if (!distTo.containsKey(v))
            return null;
        LinkedList<Vertex<T>> path = new LinkedList<>();
        for (Vertex<T> x = v; x != start; x = edgeTo.get(x))
            path.addFirst(x);
        path.addFirst(start);
        return path;
    }

    private static class VertexDistance<T> implements Comparable<VertexDistance<T>> {
        private final Vertex<T> vertex;
        private final double distance;

        public VertexDistance(Vertex<T> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Vertex<T> getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }

        @Override
        public int compareTo(VertexDistance<T> other) {
            return Double.compare(this.distance, other.distance);
        }
    }
}