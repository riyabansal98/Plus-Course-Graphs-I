package Graphs_6;

public class Zeroes_One {

	static int M;
	static int N;
	public static void main(String[] args) {
		
		char[][] mat = {
				{'X', 'O', 'X', 'O', 'X', 'X'}, 
				{'X', 'O', 'X', 'X', 'O', 'X'}, 
				{'X', 'X', 'X', 'O', 'X', 'X'}, 
				{'O', 'X', 'X', 'X', 'X', 'X'}, 
				{'X', 'X', 'X', 'O', 'X', 'O'}, 
				{'O', 'O', 'X', 'O', 'O', 'O'}
		};
		
		M = mat.length;
		N = mat[0].length;
		
		capture(mat);
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(mat[i][j] + " ");
			}
			
			System.out.println("");
		}

	}
	
	private static void capture(char[][] mat) {
		
		//1. Replace all 'O' with '-'
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				
				if(mat[i][j] == 'O') {
					mat[i][j] = '-';
				}
			}
		}
		
		//traversing the left boundary
		for(int i = 0; i < M; i++) {
			if(mat[i][0] == '-') {
				DFS(mat, i, 0, '-', 'O');
			}
		}
		
		//traversing the right boundary
		for(int i = 0; i < M; i++) {
			if(mat[i][N - 1] == '-') {
				DFS(mat, i, N - 1, '-', 'O');
			}
		}
		
		//traversing the top boundary
		for(int i = 0; i < N; i++) {
			if(mat[0][i] == '-') {
				DFS(mat, 0, i, '-', 'O');
			}
		}
		
		//traversing the bottom boundary
		for(int i = 0; i < N; i++) {
			if(mat[M - 1][i] == '-') {
				DFS(mat, M - 1, i, '-', 'O');
			}
		}
		
		//Rest of the cells with - can be replaced. 
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(mat[i][j] == '-') {
					mat[i][j] = 'X';
				}
			}
		}
		
	}

	private static void DFS(char[][] mat, int x, int y, char prev, char newW) {
		
		//Base case
		if(x < 0 || x >= M || y < 0 || y >= M) {
			return;
		}
		
		if(mat[x][y] != prev) {
			return;
		}
		
		mat[x][y] = newW;
		
		//Check for all 4 neighbours
		
		DFS(mat, x + 1, y, prev, newW);
		DFS(mat, x - 1, y, prev, newW);
		DFS(mat, x, y - 1, prev, newW);
		DFS(mat, x, y + 1, prev, newW);
	}

}
