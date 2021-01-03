package Graphs_7;

import java.util.LinkedList;
import java.util.Queue;

public class Rotten_Oranges {

	public static int R;
	public static int C;

	public static void main(String[] args) {

		int arr[][] = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 }, };

		R = arr.length;
		C = arr[0].length;
		System.out.println(rotOranges(arr));

	}

	static class Ele {

		int x = 0;
		int y = 0;

		public Ele(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isDelimiter(Ele temp) {

		return (temp.x == -1) && (temp.y == -1);
	}

	public static boolean isValid(int i, int j) {

		return (i >= 0) && (j >= 0) && (i < R) && (j < C);
	}
	
	public static boolean checkAll(int arr[][]) {
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == 1) {
					return true;
				}
			}
		}
		
		return false;
	}

	private static int rotOranges(int[][] arr) {

		Queue<Ele> q = new LinkedList<>();

		// store all the rotten oranges at t = 0

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == 2) {
					q.add(new Ele(i, j));
				}
			}
		}

		int ans = 0;
		q.add(new Ele(-1, -1));

		while (!q.isEmpty()) {

			boolean flag = false;

			while (!isDelimiter(q.peek())) {
				Ele temp = q.peek();

				// right adjacent cell
				if (isValid(temp.x + 1, temp.y) && arr[temp.x + 1][temp.y] == 1) {

					if (!flag) {

						ans++;
						flag = true;
					}

					// mark the adjacent orange as rotten

					arr[temp.x + 1][temp.y] = 2;
					// push the adjacent orange to queue

					temp.x++;
					q.add(new Ele(temp.x, temp.y));

					temp.x--;
				}

				// left adjacent cell
				if (isValid(temp.x - 1, temp.y) && arr[temp.x - 1][temp.y] == 1) {

					if (!flag) {

						ans++;
						flag = true;
					}

					// mark the adjacent orange as rotten

					arr[temp.x - 1][temp.y] = 2;
					// push the adjacent orange to queue

					temp.x--;
					q.add(new Ele(temp.x, temp.y));

					temp.x++;
				}

				// top adjacent cell
				if (isValid(temp.x, temp.y + 1) && arr[temp.x][temp.y + 1] == 1) {

					if (!flag) {

						ans++;
						flag = true;
					}

					// mark the adjacent orange as rotten

					arr[temp.x][temp.y + 1] = 2;
					// push the adjacent orange to queue

					temp.y++;
					q.add(new Ele(temp.x, temp.y));

					temp.y--;
				}

				// bottom adjacent cell
				if (isValid(temp.x, temp.y - 1) && arr[temp.x][temp.y - 1] == 1) {

					if (!flag) {

						ans++;
						flag = true;
					}

					// mark the adjacent orange as rotten

					arr[temp.x][temp.y - 1] = 2;
					// push the adjacent orange to queue

					temp.y--;
					q.add(new Ele(temp.x, temp.y));

				}

				q.remove();

			}

			// pop the delimiter
			q.remove();
			// If oranges were rotten in current frame, then separate the rotten
			// oranges using delimiter for the next frame for processing
			if (!q.isEmpty()) {

				q.add(new Ele(-1, -1));
			}

		}
		
		return checkAll(arr) ? -1 : ans;

	}

}
