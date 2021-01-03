package Graphs_7;

import java.util.LinkedList;
import java.util.Queue;

public class Shortest_Path {

	static int ROW;
	static int COL;

	public static void main(String[] args) {

		int mat[][] = { { 0, 1, 0, 1 }, { 1, 0, 1, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 0 } };
		
		Point source = new Point(0, 3);
		Point dest = new Point(3, 0);
		
		ROW = mat.length;
		COL = mat[0].length;
		
		System.out.println(shortestPath(mat, source, dest));

	}
	
	static class QueueNode {
		Point pt; //coordinates of the cell 
		int dist; //cell's distance from source
		
		public QueueNode(Point pt, int dist) {
			this.pt = pt;
			this.dist = dist;
		}
	}
	
	private static int shortestPath(int[][] mat, Point src, Point dest) {
		
		if(mat[src.x][src.y] != 1 || mat[dest.x][dest.y] != 1) {
			
			return -1;
		}
		
		boolean[][] visited = new boolean[ROW][COL];
		
		//marking the source cell as visited
		visited[src.x][src.y] = true;
		
		Queue<QueueNode> q = new LinkedList<>();
		
		//adding the source cell to the queue
		QueueNode node = new QueueNode(src, 0);
		
		q.add(node);
		
		 int rowNum[] = {-1, 0, 0, 1};
		 int colNum[] = {0, -1, 1, 0};
		while(!q.isEmpty()) {
			
			QueueNode curr = q.peek();
			Point pt = curr.pt;
			
			
			if(pt.x == dest.x && pt.y == dest.y) {
				return curr.dist;
			}
			
			q.remove();
			
			for(int i = 0; i < 4; i++) {
				int row = pt.x + rowNum[i];
				int col = pt.y + colNum[i];
				
				if(isValid(row, col) && mat[row][col] == 1 && !visited[row][col]) {
					//mark that cell as visited and add it to the queue
					
					visited[row][col] = true;
					QueueNode adjCell = new QueueNode(new Point(row, col), curr.dist + 1);
					q.add(adjCell);
				}
			}
		}
		
		//Return -1 if destination is not reachable. 
		return -1;
		
	}

	private static boolean isValid(int row, int col) {
		
		return (row >= 0) && (row < ROW) && (col >= 0)  && (col < COL);
		
	}

	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
