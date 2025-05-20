import java.util.*;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        Vertex<String> v0 = new Vertex<>("A");
        Vertex<String> v1 = new Vertex<>("B");
        Vertex<String> v2 = new Vertex<>("C");
        Vertex<String> v3 = new Vertex<>("D");

        graph.addEdge(v0, v1, 1.0);
        graph.addEdge(v0, v2, 2.0);
        graph.addEdge(v1, v2, 1.0);
        graph.addEdge(v2, v3, 3.0);
        graph.addEdge(v1, v3, 4.0);

        Search<String> bfs = new BreadthFirstSearch<>();
        bfs.search(graph, v0);
        System.out.println("BFS Path from A to D: " + bfs.pathTo(v3));

        Search<String> dijkstra = new DijkstraSearch<>();
        dijkstra.search(graph, v0);
        System.out.println("Dijkstra Path from A to D: " + dijkstra.pathTo(v3));
    }
}