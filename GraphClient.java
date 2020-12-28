package Graphs_2;

public class GraphClient {

	public static void main(String[] args) {
		
		Graph g = new Graph();
		
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		
		g.addEdge("A", "B", 8);
		g.addEdge("B", "D", 2);
		g.addEdge("C", "E", 3);
		g.addEdge("A", "D", 1);
		g.addEdge("E", "B", 5);
		
		g.display();
		System.out.println(g.numEdges());
		g.removeEdge("A", "B");
		g.display();
		g.removeVertex("B");
		g.display();

	}

}
