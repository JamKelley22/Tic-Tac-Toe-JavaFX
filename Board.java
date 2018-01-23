package start;

/**
 * The Board Model
 * @author Jameel
 *
 */
public class Board {
	char[][] board;
	
	/**
	 * The Board char[3][3] data structure
	 */
	public Board() {
		board = new char[3][3];
		clearBoard();
	}
	
	/**
	 * Clears the Board data structure
	 */
	public void clearBoard() {
		for(int i = 0; i < this.board.length; i++) {
			for(int j= 0; j < this.board[i].length; j++) {
				this.board[i][j] = CONFIG.BLANK_SPACE;
			}
		}
	}
	
	/**
	 * attempts to place the char c at pos i,j
	 * @param i, row
	 * @param j, col
	 * @param c, char to place
	 * @return True if the spot is in bounds and isn't taken, false otherwise
	 */
	public boolean put(int i, int j, char c) {
		boolean inBounds;
		inBounds = i >= 0 && i < 3 && j >= 0 && j < 3;
		if(inBounds) {
			if(this.board[i][j] == CONFIG.BLANK_SPACE) { 
				this.board[i][j] = c;
				return true;
			}
			return false;// Spot already taken
		}
		return false;
	}
	
	/**
	 * 
	 * @return the board char[3][3]
	 */
	public char[][] getBoard() {
		return this.board;
	}
	
}
