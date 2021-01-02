package Graphs_6;

public class Islands {

	static int ROW;
	static int COL;
	public static void main(String[] args) {
		

		int M[][]  = new int[][] 
				{
				{1, 1, 0, 0, 0}, 
				{0, 1, 0, 0, 1}, 
				{1, 1, 1, 1, 1}, 
				{0, 0, 0, 0, 0}, 
				{1, 1, 1, 0, 1}
				};
				
				
		ROW = M.length;
		COL = M[0].length;
		
		System.out.println(CountIslands(M));
		
	}
	private static int CountIslands(int[][] M) {
		
		//visited array to make the visited cells. 
		boolean visited[][] = new boolean[ROW][COL];
		
		int count = 0;
		for(int i = 0; i < ROW; i++) {
			for(int j = 0; j < COL; j++) {
				
				if(M[i][j] == 1 && !visited[i][j]) {
					
					DFS(M, i, j, visited);
					count++;
				}
			}
		}
		
		return count;
	}
	private static void DFS(int[][] M, int row, int col, boolean[][] visited) {
		
		
		//we will define an array to get the row and column number of 8 neighbours of
		// a given cell. 
		
		int rowNbr[] = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
		int colNbr[] = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
				
				
		visited[row][col] = true;	
				
		for(int k = 0; k < 8; k++) {
			if(isSafe(M, row + rowNbr[k], col + colNbr[k], visited))
				DFS(M, row + rowNbr[k], col + colNbr[k], visited);
		}
						
	}
	private static boolean isSafe(int[][] M, int row, int col, boolean[][] visited) {
	
	return (row >= 0) && (row  < ROW) && (col >= 0) && (col < COL) && (M[row][col] == 1 && !visited[row][col]);
		
	}

}
