

public class GameLogic {

	private int col1, row1;
	protected int [][] gameBoardFill(int [][] gameBoard , int col, int row , int player ){
		if(player == 1) {
			while(!isValid(gameBoard[col][row])&& row >=0){
				row -=1;
			}
			gameBoard[col][row] = 1;
		}
		if(player == 2) {
			while(!isValid(gameBoard[col][row]) && row >=0){
				row -=1;
			}
			gameBoard[col][row] = 2;
		}
		row1 = row;
		return gameBoard;
	}
	protected boolean isColumnFull(int[][] gameGrid, int col) {
		return gameGrid[col][0] != 0; 
	}

	private boolean isValid(int num) {
		if(num != 0)
			return false;
		return true;
	}
	public boolean checkIfWin(int  [][] grid, int player) {

		for (int col = 0; col < grid[0].length - 3; col++) {
			for (int row = 0; row < grid.length; row++) {
				if (grid[row][col] == player &&
						grid[row][col+1] == player &&
						grid[row][col+2] == player &&
						grid[row][col+3] == player) {
					return true;
				}
			}
		}
		for (int col = 0; col < grid[0].length; col++) {
			for (int row = 0; row < grid.length - 3; row++) {
				if (grid[row][col] == player &&
						grid[row+1][col] == player &&
						grid[row+2][col] == player &&
						grid[row+3][col] == player) {
					return true;
				}
			}
		}
		for (int col = 0; col < grid[0].length - 3; col++) {
			for (int row = 0; row < grid.length - 3; row++) {
				if (grid[row][col] == player &&
						grid[row+1][col+1] == player &&
						grid[row+2][col+2] == player &&
						grid[row+3][col+3] == player) {
					return true;
				}
			}
		}
		for (int col = 0; col < grid[0].length - 3; col++) {
			for (int row = 3; row < grid.length; row++) {
				if (grid[row][col] == player &&
						grid[row-1][col+1] == player &&
						grid[row-2][col+2] == player &&
						grid[row-3][col+3] == player) {
					return true;
				}
			}
		}
		return false;
	}
	public int getRow() {
		return row1;
	}
	public int getCol() {
		return col1;
	}
}
