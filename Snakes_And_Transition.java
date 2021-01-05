package Graphs_8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Snakes_And_Transition {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);

		int test = scn.nextInt();

		while (test-- > 0) {

			int row = scn.nextInt();
			int col = scn.nextInt();

			int[][] matrix = new int[row][col];

			int max_val = Integer.MIN_VALUE;

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					matrix[i][j] = scn.nextInt();
					max_val = Math.max(max_val, matrix[i][j]);
				}
			}

			int[][] dist = new int[row][col];
			Queue<int[]> que = new LinkedList<>();

			for (int i = 0; i < dist.length; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}

			for (int i = 0; i < row; i++) {

				for (int j = 0; j < col; j++) {

					if (matrix[i][j] == max_val) {
						dist[i][j] = 0;
						que.add(new int[] { i, j });
					}
				}
			}

			while (!que.isEmpty()) {

				int[] p = que.peek();
				que.remove();
				for (int dy = -1; dy <= 1; dy++) {
					for (int dx = -1; dx <= 1; dx++) {
						int x = p[0] + dx;
						int y = p[1] + dy;
						if (x >= 0 && x < row && y >= 0 && y < col)
							if (dist[x][y] > dist[p[0]][p[1]] + 1) {

								dist[x][y] = dist[p[0]][p[1]] + 1;
								que.add(new int[] { x, y });
							}
					}
				}
			}

			// The ans will the be min time it took to convert all houses
			// to have equal wealth. So, ans is going to be equal to the
			// max level of the graph
			int ans = 0;
			for (int y = 0; y < row; y++) {
				for (int x = 0; x < col; x++) {
					ans = Math.max(ans, dist[y][x]);
				}
			}

			System.out.println(ans);

		}

	}

}
