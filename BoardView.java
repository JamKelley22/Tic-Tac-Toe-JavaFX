package start;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

/**
 * The View Updater class for the Board
 * @author Jameel
 *
 */
public class BoardView {
	
	/**
	 * Prints the current board to the console
	 * @param board
	 */
	public void printBoard(char[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				A.SOP(board[i][j]);
				if(j < board[i].length - 1)
					A.SOP("|");
			}
			A.SOP("\n");
			
		}
		A.SOP("----------");
	}
	
	/**
	 * Draws the lines that make up the # symbol in Tic-Tac-Toe
	 * @param canvas, canvas to draw on
	 * @param scale, multiplier
	 * @param offset, amount to offset by
	 */
	public void drawFramework(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		int scale = CONFIG.unitSize;
		int offset = CONFIG.offset;
		gc.setGlobalAlpha(1);
		gc.setStroke(Color.GRAY);
		gc.setLineWidth(10);
		gc.setLineCap(StrokeLineCap.ROUND);
		// 1
		gc.moveTo(scale + offset,0 + offset);
		gc.lineTo(scale + offset,scale*3 + offset);
		//gc.stroke();
		// 2
		gc.moveTo(scale*2 + offset,0 + offset);
		gc.lineTo(scale*2 + offset,scale*3 + offset);
		//gc.stroke();
		// 3
		gc.moveTo(offset,scale + offset);
		gc.lineTo(scale*3 + offset,scale + offset);
		//gc.stroke();
		// 4
		gc.moveTo(offset,scale*2 + offset);
		gc.lineTo(scale*3 + offset,scale*2 + offset);
		gc.stroke();
	}
	
	/**
	 * Draw the shape 'X' in the specified position on the canvas
	 * @param canvas
	 * @param unitSize
	 * @param offset
	 * @param strokeWidth
	 * @param posI, row
	 * @param posJ, col
	 */
	public void drawX(Canvas canvas, int unitSize, int offset, int strokeWidth, int posI, int posJ) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setGlobalAlpha(1);
		gc.setStroke(CONFIG.xColor);
		gc.setLineWidth(5);
		gc.setLineCap(StrokeLineCap.ROUND);
		int extraOffset = 10;
		
		gc.strokeLine(posJ * unitSize + offset + extraOffset, posI * unitSize + offset + extraOffset, posJ * unitSize + offset + unitSize - extraOffset, posI * unitSize + offset + unitSize - extraOffset);
		
		gc.strokeLine(posJ * unitSize + offset + unitSize - extraOffset, posI * unitSize + offset + extraOffset, posJ * unitSize + offset + extraOffset, posI * unitSize + offset + unitSize - extraOffset);
		
	}
	
	/**
	 * Draw the shape 'O' in the specified position on the canvas
	 * @param canvas
	 * @param unitSize
	 * @param offset
	 * @param strokeWidth
	 * @param posI
	 * @param posJ
	 */
	public void drawO(Canvas canvas, int unitSize, int offset, int strokeWidth, int posI, int posJ) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setGlobalAlpha(1);
		gc.setStroke(CONFIG.oColor);
        gc.setLineWidth(5);
		gc.strokeOval(posJ * unitSize + offset + unitSize/4, posI * unitSize + offset + unitSize/4, unitSize/2, unitSize/2);
	}
	
	/**
	 * Highlights an individual unit (Square) on the canvas with CONFIG.winColor
	 * @param canvas
	 * @param i
	 * @param j
	 */
	public void highlightUnit(Canvas canvas, int i, int j) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(CONFIG.winColor);
		gc.setGlobalAlpha(.2);
		int posI = i * CONFIG.unitSize + CONFIG.offset;
		int posJ = j * CONFIG.unitSize + CONFIG.offset;
		gc.fillRect(posJ, posI, CONFIG.unitSize, CONFIG.unitSize);
	}
	
	public void clear(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, 300, 300);
	}
}