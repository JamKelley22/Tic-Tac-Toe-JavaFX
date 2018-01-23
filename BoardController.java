package start;

import javafx.scene.canvas.Canvas;
/**
 * The Controller for the Board Class
 * @author Jameel
 *
 */
public class BoardController {
	private Board model;
	private BoardView view;
	
	/**
	 * The controller for the Board model
	 * @param model
	 * @param view
	 */
	public BoardController(Board model, BoardView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Clears the board Data Structure
	 */
	public void clearBoard() {
		this.model.clearBoard();
	}
	
	
	/**
	 * attempts to place the char c at pos i,j
	 * @param i, row
	 * @param j, col
	 * @param c, char to place
	 * @return True if the spot is in bounds and isn't taken, false otherwise
	 */
	public boolean put(int i, int j, char c) {
		return model.put(i, j, c);
	}
	
	/**
	 * 
	 * @param i, the row
	 * @param j, the column
	 * @return The char on the board @ pos i,j. If out of bounds, returns ' '
	 */
	public char get(int i, int j) {
		boolean inBounds;
		inBounds = i >= 0 && i < 3 && j >= 0 && j < 3;
		if(inBounds)
			return model.getBoard()[i][j];
		else
			return ' ';
	}
	
	/**
	 * Re-prints the board to the console window
	 */
	public void updateView() {
		view.printBoard(this.model.getBoard());
	}
	
	/**
	 * Updates the GUI canvas using the board model, can also clear canvas and redraw framework
	 * @param canvas
	 * @param unitSize
	 * @param offset
	 * @param strokeWidth
	 * @param clear, set to true to also clear the canvas (paint over) and redraw the framework
	 */
	public void updateView(Canvas canvas, int unitSize, int offset, int strokeWidth, boolean clear) {
		if(clear) {
				view.clear(canvas);
				view.drawFramework(canvas);
		}
		for(int i = 0; i < model.board.length; i++) {
			for(int j = 0; j < model.board[i].length; j++) {
				if(model.board[i][j] == 'X') {
					view.drawX(canvas, unitSize, offset, strokeWidth,i,j);
				}
				else if(model.board[i][j] == 'O') {
					view.drawO(canvas, unitSize, offset, strokeWidth,i,j);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param canvas
	 * @param x
	 * @param y
	 * @param unitSize
	 * @param offset
	 * @param strokeWidth
	 * @return 0 if game has not been won, 1 | 2 otherwise (according to what player won)
	 */
	public boolean handleCanvasClick(Canvas canvas, double x, double y, int unitSize, int offset, int strokeWidth) {
		//unitSize, strokeWidth, offset
		if(x < offset || y < offset) 
			return false;
		if(x > offset + 3*unitSize || y > offset + 3*unitSize) 
			return false;
		x -= offset;
		y -= offset;
		//A.SOPL(x + " " + y);
		if(y < unitSize - strokeWidth/2) {// Top
			if(x < unitSize - strokeWidth/2) {// Left
				A.SOPL("TOP_LEFT");
				update(canvas,0, 0);
			}
			else if(x < unitSize*2 - strokeWidth/2) {// Middle
				A.SOPL("TOP_MIDDLE");
				update(canvas,0, 1);
			}
			else if(x > unitSize*2 + strokeWidth/2){// Right
				A.SOPL("TOP_RIGHT");
				update(canvas,0, 2);
			}
		}
		else if(y < unitSize*2 - strokeWidth/2) {
			if(x < unitSize - strokeWidth/2) {// Middle
				A.SOPL("MIDDLE_LEFT");
				update(canvas,1, 0);
			}
			else if(x < unitSize*2 - strokeWidth/2) {// Middle
				A.SOPL("MIDDLE_MIDDLE");
				update(canvas,1, 1);
			}
			else if(x > unitSize*2 + strokeWidth/2){// Right
				A.SOPL("MIDDLE_RIGHT");
				update(canvas,1, 2);
			}
		}
		else if(y < unitSize*3 - strokeWidth/2){
			if(x < unitSize - strokeWidth/2) {// Bottom
				A.SOPL("BOTTOM_LEFT");
				update(canvas,2, 0);
			}
			else if(x < unitSize*2 - strokeWidth/2) {// Middle
				A.SOPL("BOTTOM_MIDDLE");
				update(canvas,2, 1);
			}
			else if(x > unitSize*2 + strokeWidth/2){// Right
				A.SOPL("BOTTOM_RIGHT");
				update(canvas,2, 2);
			}
		}
		int win = checkWin(canvas,model);
		if(win == 1 || win == 2) {
			A.SOPL("WIN");
			char set = (win == 1) ? TTT.playerController1.getPlayerChar() : TTT.playerController2.getPlayerChar();
			TTT.output.setText("Congratulations, " + set + " win the game");
		}
		else if(win == -1) {
			A.SOPL("Draw");
			TTT.output.setText("Draw");
		}
		return win != 0;
	}
	
	/**
	 * Update the board & GUI according to an attempt to place a character
	 * @param canvas
	 * @param posI, row
	 * @param posJ, col
	 */
	private void update(Canvas canvas, int posI,int posJ) {
		if(TTT.getCurrPlayer().put(posI,posJ)) {
			TTT.boardController.updateView();
			TTT.boardController.updateView(canvas,CONFIG.unitSize,CONFIG.offset,CONFIG.strokeWidth,true);
		}
		else {
			TTT.getCurrPlayer();// If put failed we need to Flip Back to the other player
		}
	}
	
	/**
	 * Checks to see of there is a winning configuration on the board
	 * @param canvas
	 * @return True if the game has a winning configuration on it, false otherwise
	 */
	public int checkWin(Canvas canvas, Board model) {
		//Horizontal
		for(int i = 0; i < model.board.length; i++) {
			char firstMark = model.board[i][0];
			char secondMark = model.board[i][1];
			char thirdMark = model.board[i][2];
			if(firstMark != '#' && firstMark == secondMark && secondMark == thirdMark) {
				highlightWin(canvas,i,0,i,1,i,2);
				if(firstMark == 'X')
					return 1;
				if(firstMark == 'O') 
					return 2;
			}
		}
		//Vertical
		for(int j = 0; j < model.board[0].length; j++) {
			char firstMark = model.board[0][j];
			char secondMark = model.board[1][j];
			char thirdMark = model.board[2][j];
			if(firstMark != '#' && firstMark == secondMark && secondMark == thirdMark) {
				highlightWin(canvas,0,j,1,j,2,j);
				if(firstMark == 'X')
					return 1;
				if(firstMark == 'O') 
					return 2;
			}
		}
		//Diags
		char firstMark;
		char secondMark;
		char thirdMark;
		firstMark = model.board[0][0];
		secondMark = model.board[1][1];
		thirdMark = model.board[2][2];
		if(firstMark != '#' && firstMark == secondMark && secondMark == thirdMark) {
			highlightWin(canvas,0,0,1,1,2,2);
			if(firstMark == 'X')
				return 1;
			if(firstMark == 'O') 
				return 2;
		}
		firstMark = model.board[0][2];
		secondMark = model.board[1][1];
		thirdMark = model.board[2][0];
		if(firstMark != '#' && firstMark == secondMark && secondMark == thirdMark) {
			highlightWin(canvas,2,0,1,1,0,2);
			if(firstMark == 'X')
				return 1;
			if(firstMark == 'O') 
				return 2;
		}
		
		boolean boardFull = false;
		for(int i = 0; i < model.board.length; i++) {
			for(int j = 0; j < model.board[i].length; j++) {
				if(model.board[i][j] == '#')
					return 0;
			}
		}
		return -1; // Draw
	}

	/**
	 * Highlights the given i,j positions with CONFIG.winColor
	 * @param canvas
	 * @param i
	 * @param j
	 * @param i2
	 * @param j2
	 * @param i3
	 * @param j3
	 */
	private void highlightWin(Canvas canvas,int i, int j, int i2, int j2, int i3, int j3) {
		if(canvas != null) {
			view.clear(canvas);
			view.highlightUnit(canvas, i, j);
			view.highlightUnit(canvas, i2, j2);
			view.highlightUnit(canvas, i3, j3);
			view.drawFramework(canvas);
			TTT.boardController.updateView(canvas,CONFIG.unitSize,CONFIG.offset,CONFIG.strokeWidth, false);
		}
	}
}
