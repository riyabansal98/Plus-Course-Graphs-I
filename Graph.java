package Graphs_2;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

	//String -> Neighbour Value
	//Integer -> Weight
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
		
		if(vtces.containsKey(vname)) {
			return;
		}
		
		//value of the that vertex
		Vertex vtx = new Vertex();
		vtces.put(vname, vtx);
	}
	
	public void removeVertex(String vname) {
		
		Vertex vtx = vtces.get(vname);
		
		ArrayList<String> keys = new ArrayList<>(vtx.nbrs.keySet());
		
		for(String key: keys) {
			
			Vertex nbrVtx = vtces.get(key);
			nbrVtx.nbrs.remove(vname);
		}
		
		vtces.remove(vname);
	}
	
	public void addEdge(String vname1, String vname2, int cost) {
		
		Vertex vtx1  = vtces.get(vname1);
		Vertex vtx2 = vtces.get(vname2);
		
		if(vtx1 == null ||  vtx2 == null || vtx1.nbrs.containsKey(vname2)) {
			return;
		}
		
		vtx1.nbrs.put(vname2, cost);
		vtx2.nbrs.put(vname1, cost);
	}
	
	
	public void removeEdge(String vname1, String vname2) {
		
		Vertex vtx1 = vtces.get(vname1);
		Vertex vtx2 = vtces.get(vname2);
		
		if(vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)) {
			return;
		}
		
		vtx1.nbrs.remove(vname2);
		vtx2.nbrs.remove(vname1);
	}
	
	//O(1)
	public boolean containsEdge(String vname1, String vname2) {
		
		Vertex vtx1 = vtces.get(vname1);
		Vertex vtx2 = vtces.get(vname2);
		if(vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)) {
		
			return false;
		}
		
		return true;
	}
	
	//Total no of edges in a graph
	public int numEdges() {
		
		ArrayList<String> keys = new ArrayList<>(vtces.keySet());
		
		int count = 0;
		
		for(String key: keys) {
			
			Vertex vtx = vtces.get(key);
			count += vtx.nbrs.size();
					
		}
		
		return count / 2;
	}
	
	public void display() {
		System.out.println("-------------------");
		
		ArrayList<String> vnames = new ArrayList<>(vtces.keySet());
		
		for(String vname: vnames) {
			
			String str = vname + "=> ";
			Vertex vtx = vtces.get(vname);
			str += vtx.nbrs;
			
			System.out.println(str);
			
		}
		
		System.out.println("-------------------");
	}
	
}
