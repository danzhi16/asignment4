java
import java.util.*;

public class DijkstraSearch<T> implements Search<T> {
    private final Map<Vertex<T>, Double> distTo = new HashMap<>();
    private final Map<Vertex<T>, Vertex<T>> edgeTo = new HashMap<>();
    private final PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
    private Vertex<T> start;

    @Override
    public void search(WeightedGraph<T> graph, Vertex<T> start) {
        this.start = start;
        this.distTo.put(start, 0.0);
        this.pq.add(new VertexDistance<>(start, 0.0));

        while (!this.pq.isEmpty()) {
            Vertex<T> v = this.pq.poll().getVertex();
            for (Edge<T> edge : graph.getEdges(v)) {
                this.relax(edge);
            }
        }
    }

    private void relax(Edge<T> edge) {
        Vertex<T> v = edge.getSource();
        Vertex<T> w = edge.getDestination();
        double weight = edge.getWeight();
        double distV = this.distTo.getOrDefault(v, Double.POSITIVE_INFINITY);
        double distW = this.distTo.getOrDefault(w, Double.POSITIVE_INFINITY);
        if (distW > distV + weight) {
            this.distTo.put(w, distV + weight);
            this.edgeTo.put(w, v);
            this.pq.add(new VertexDistance<>(w, this.distTo.get(w)));
        }
    }

    @Override
    public Iterable<Vertex<T>> pathTo(Vertex<T> v) {
        if (!this.distTo.containsKey(v)) {
            return null;
        }
        LinkedList<Vertex<T>> path = new LinkedList<>();
        for (Vertex<T> x = v; x != this.start; x = this.edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(this.start);
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
            return this.vertex;
        }

        public double getDistance() {
            return this.distance;
        }

        @Override
        public int compareTo(VertexDistance<T> other) {
            return Double.compare(this.distance, other.distance);
        }
    }
}