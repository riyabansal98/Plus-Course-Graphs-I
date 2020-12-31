package Graphs_2_4;

import java.util.Stack;

public class GraphClient {

	public static void main(String[] args) {
		
		Stack s = new Stack();
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
//		System.out.println(g.numEdges());
//		g.removeEdge("A", "B");
//		g.display();
//		g.removeVertex("B");
//		g.display();
		
		//g.depth_first_traversal();
		//g.breadth_first_traversal();
		System.out.println(g.isCyclic());
		System.out.println(g.isConnected());

	}

}
