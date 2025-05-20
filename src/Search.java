public interface Search<T> {
    void search(WeightedGraph<T> graph, Vertex<T> start);
    Iterable<Vertex<T>> pathTo(Vertex<T> v);
}