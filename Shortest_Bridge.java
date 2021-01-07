package Graphs_9;

import java.util.LinkedList;
import java.util.Queue;


public class Shortest_Bridge {

	public static void main(String[] args) {

		int[][] matrix = { { 0, 1, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };
		System.out.println(shortestBridge(matrix));
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static int shortestBridge(int[][] A) {

		// DFS

		boolean[][] visited = new boolean[A.length][A[0].length];
		// iterate over the entire matrix to identify the ones.
		int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		boolean found = false;
		Queue<int[]> q = new LinkedList<>();
		// DFS
		for (int i = 0; i < A.length; i++) {
			if (found) {
				break;
			}

			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] == 1) {
					dfs(A, visited, q, i, j, dirs);
					found = true;
					break;
				}
			}
		}

		// bfs to expand

		int step = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int[] curr = q.poll();
				for (int[] dir : dirs) {
					int i = curr[0] + dir[0];
					int j = curr[1] + dir[1];

					if (i >= 0 && j >= 0 && i < A.length && j < A[0].length && !visited[i][j]) {
						if (A[i][j] == 1) {
							return step;
						}

						q.offer(new int[] { i, j });
						visited[i][j] = true;
					}
				}
			}
		}
		
		return -1;
	}

	public static void dfs(int[][] A, boolean[][] visited, Queue<int[]> q, int i, int j, int[][] dirs) {
		if (i < 0 || j < 0 || i > A.length || j >= A[0].length || visited[i][j] || A[i][j] == 0) {
			return;
		}
		visited[i][j] = true;
		q.offer(new int[] { i, j });
		for (int[] dir : dirs) {
			dfs(A, visited, q, i + dir[0], j + dir[1], dirs);
		}

	}

}
