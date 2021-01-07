package Graphs_9;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Clone_Graph {

	static class Node {
		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}

		public static void main(String[] args) {

		}

		// return the address of the new cloned graph node.
		public Node cloneGraph(Node node) {

			// Key: old node
			// Value : New Cloned Node
			HashMap<Node, Node> visited = new HashMap<>();

			if (node == null) {
				return node;
			}

			// Check if the node has already been visited or not.
			if (visited.containsKey(node)) {
				//return the cloned neighbour
				return visited.get(node);
			}

			// Create a clone for the node
			Node cloneNode = new Node(node.val, new ArrayList());

			visited.put(node, cloneNode);

			//Depth first search for this. 
			for (Node neighbour : node.neighbors) {
				cloneNode.neighbors.add(cloneGraph(neighbour));
			}
			
			return cloneNode;
		}

	}

}
