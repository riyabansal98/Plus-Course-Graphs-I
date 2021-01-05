package Graphs_8;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Nearest_Zero {

	static int N;
	static int M;
	public static void main(String[] args) {
		int mat[][] = {
				{1, 1, 1, 0},
				{1, 1, 0, 0},
				{1, 0, 0, 1}
		};

		N = mat.length;
		M = mat[0].length;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		
		
		
		System.out.println();
		findNearestZero(mat);
		
		
	}
	
	public static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void findNearestZero(int[][] matrix) {
		
		if(N == 0) {
			return;
		}
		
		int[][] dist = new int[N][M];
		
		for(int i = 0; i < dist.length; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		Queue<Point> q = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == 0) {
					
					dist[i][j] = 0;
					q.add(new Point(i, j));
				}
			}
		}
		
		int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		
		while(!q.isEmpty()) {
			
			Point curr = q.peek();
			q.remove();
			
			
			for(int i = 0; i < 4; i++)
			{
				int new_r = curr.x + dir[i][0];
				int new_c = curr.y + dir[i][1];
				
				if(new_r >= 0 && new_c >= 0 && new_r < N && new_c < M) {
					
					//Check if the new value that you are about to put
					//is less than the value already present at the cell. 
					if(dist[new_r][new_c] > dist[curr.x][curr.y] + 1) {
						dist[new_r][new_c] = dist[curr.x][curr.y] + 1;
						q.add(new Point(new_r, new_c));
					}
				}
			}
				
		}
		
		//Print the answer - dist
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(dist[i][j] + " ");
			}
			
			System.out.println();
		}
		
	}

}
