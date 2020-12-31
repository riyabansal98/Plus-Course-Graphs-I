package Graphs_2_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {

	// String -> Neighbour Value
	// Integer -> Weight
	private class Vertex {
		HashMap<String, Integer> nbrs = new HashMap<>();
	}

	HashMap<String, Vertex> vtces;

	public Graph() {
		vtces = new HashMap<>();
	}

	public int numVertex() {
		return this.vtces.size();
	}

	public boolean containsVertex(String vname) {
		return this.vtces.containsKey(vname);
	}

	public void addVertex(String vname) {

		if (vtces.containsKey(vname)) {
			return;
		}

		// value of the that vertex
		Vertex vtx = new Vertex();
		vtces.put(vname, vtx);
	}

	public void removeVertex(String vname) {

		Vertex vtx = vtces.get(vname);

		ArrayList<String> keys = new ArrayList<>(vtx.nbrs.keySet());

		for (String key : keys) {

			Vertex nbrVtx = vtces.get(key);
			nbrVtx.nbrs.remove(vname);
		}

		vtces.remove(vname);
	}

	public void addEdge(String vname1, String vname2, int cost) {

		Vertex vtx1 = vtces.get(vname1);
		Vertex vtx2 = vtces.get(vname2);

		if (vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(vname2)) {
			return;
		}

		vtx1.nbrs.put(vname2, cost);
		vtx2.nbrs.put(vname1, cost);
	}

	public void removeEdge(String vname1, String vname2) {

		Vertex vtx1 = vtces.get(vname1);
		Vertex vtx2 = vtces.get(vname2);

		if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)) {
			return;
		}

		vtx1.nbrs.remove(vname2);
		vtx2.nbrs.remove(vname1);
	}

	// O(1)
	public boolean containsEdge(String vname1, String vname2) {

		Vertex vtx1 = vtces.get(vname1);
		Vertex vtx2 = vtces.get(vname2);
		if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)) {

			return false;
		}

		return true;
	}

	// Total no of edges in a graph
	public int numEdges() {

		ArrayList<String> keys = new ArrayList<>(vtces.keySet());

		int count = 0;

		for (String key : keys) {

			Vertex vtx = vtces.get(key);
			count += vtx.nbrs.size();

		}

		return count / 2;
	}

	public void display() {
		System.out.println("-------------------");

		ArrayList<String> vnames = new ArrayList<>(vtces.keySet());

		for (String vname : vnames) {

			String str = vname + "=> ";
			Vertex vtx = vtces.get(vname);
			str += vtx.nbrs;

			System.out.println(str);

		}

		System.out.println("-------------------");
	}

	class Pair {
		String vname;
		// psf -> path so far
		String psf;
	}

	public void depth_first_traversal() {

		// to keep track of processed nodes
		HashMap<String, Boolean> processed = new HashMap<>();

		Stack<Pair> stack = new Stack<>();
		ArrayList<String> keys = new ArrayList<>(vtces.keySet());

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			// create a new pair
			Pair sp = new Pair();
			sp.vname = key;
			sp.psf = key;

			stack.push(sp);

			// while stack is not empty, we keep doing work.

			while (!stack.isEmpty()) {

				Pair rp = stack.pop();

				if (processed.containsKey(rp.vname)) {
					continue;
				}

				processed.put(rp.vname, true);

				System.out.println(rp.vname + " via " + rp.psf);

				Vertex rpvtx = vtces.get(rp.vname);

				ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

				for (String nbr : nbrs) {

					if (!processed.containsKey(nbr)) {
						Pair np = new Pair();
						np.vname = nbr;
						np.psf = rp.psf + nbr;

						stack.push(np);
					}
				}
			}

		}
	}

	public void breadth_first_traversal() {

		HashMap<String, Boolean> processed = new HashMap<>();

		LinkedList<Pair> queue = new LinkedList<>();

		LinkedList<String> q = new LinkedList<>();
		ArrayList<String> keys = new ArrayList<>(vtces.keySet());

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			Pair sp = new Pair();
			sp.vname = key;
			sp.psf = key;
			queue.add(sp);

			while (!queue.isEmpty()) {

				Pair rp = queue.removeFirst();

				if (processed.containsKey(rp.vname)) {
					continue;
				}

				processed.put(rp.vname, true);
				System.out.println(rp.vname + " via " + rp.psf);

				Vertex rpvtx = vtces.get(rp.vname);
				ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

				for (String nbr : nbrs) {

					if (!processed.containsKey(nbr)) {

						Pair np = new Pair();
						np.vname = nbr;
						np.psf = rp.psf + nbr;

						queue.addLast(np);
					}
				}
			}
		}

	}

	public boolean isConnected() {

		int flag = 0;
		// to keep track of processed nodes
		HashMap<String, Boolean> processed = new HashMap<>();

		Stack<Pair> stack = new Stack<>();
		ArrayList<String> keys = new ArrayList<>(vtces.keySet());

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			flag++;

			// create a new pair
			Pair sp = new Pair();
			sp.vname = key;
			sp.psf = key;

			stack.push(sp);

			// while stack is not empty, we keep doing work.

			while (!stack.isEmpty()) {

				Pair rp = stack.pop();

				if (processed.containsKey(rp.vname)) {
					continue;
				}

				processed.put(rp.vname, true);


				Vertex rpvtx = vtces.get(rp.vname);

				ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

				for (String nbr : nbrs) {

					if (!processed.containsKey(nbr)) {
						Pair np = new Pair();
						np.vname = nbr;
						np.psf = rp.psf + nbr;

						stack.push(np);
					}
				}
			}

		}

		if (flag >= 2) {
			return false;
		} else {
			return true;
		}

	}
	
	public ArrayList<ArrayList<String>> getCC(){
		
		ArrayList<ArrayList<String>> ans = new ArrayList<>();
		HashMap<String, Boolean> processed = new HashMap<>();

		LinkedList<Pair> queue = new LinkedList<>();

		LinkedList<String> q = new LinkedList<>();
		ArrayList<String> keys = new ArrayList<>(vtces.keySet());

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			//for new component, create a new arrayList
			ArrayList<String> subans = new ArrayList<>();
			Pair sp = new Pair();
			sp.vname = key;
			sp.psf = key;
			queue.add(sp);

			while (!queue.isEmpty()) {

				Pair rp = queue.removeFirst();

				if (processed.containsKey(rp.vname)) {
					continue;
				}

				processed.put(rp.vname, true);
				
				subans.add(rp.vname);

				Vertex rpvtx = vtces.get(rp.vname);
				ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

				for (String nbr : nbrs) {

					if (!processed.containsKey(nbr)) {

						Pair np = new Pair();
						np.vname = nbr;
						np.psf = rp.psf + nbr;

						queue.addLast(np);
					}
				}
			}
			
			ans.add(subans);
		}
		
		return ans;
	}
	
	public boolean isCyclic() {
		

		HashMap<String, Boolean> processed = new HashMap<>();

		LinkedList<Pair> queue = new LinkedList<>();

		LinkedList<String> q = new LinkedList<>();
		ArrayList<String> keys = new ArrayList<>(vtces.keySet());

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			Pair sp = new Pair();
			sp.vname = key;
			sp.psf = key;
			queue.add(sp);

			while (!queue.isEmpty()) {

				Pair rp = queue.removeFirst();

				if (processed.containsKey(rp.vname)) {
					return true;
				}

				processed.put(rp.vname, true);
				System.out.println(rp.vname + " via " + rp.psf);

				Vertex rpvtx = vtces.get(rp.vname);
				ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

				for (String nbr : nbrs) {

					if (!processed.containsKey(nbr)) {

						Pair np = new Pair();
						np.vname = nbr;
						np.psf = rp.psf + nbr;

						queue.addLast(np);
					}
				}
			}
		}
		
		return false;
	}

}
