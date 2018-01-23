package start;

/**
 * The Controller for the Player Class
 * @author Jameel
 *
 */
public class PlayerController {
	
	private Player playerModel;
	private BoardController boardController;
	
	/**
	 * The Controller for the player
	 * @param model
	 * @param boardController
	 */
	public PlayerController(Player model, BoardController boardController) {
		this.playerModel = model;
		this.boardController = boardController;
	}
	
	/**
	 * 
	 * @param i, row
	 * @param j, col
	 * @return True if in bounds and empty spot availble there, false otherwise
	 */
	public boolean put(int i, int j) {
		return boardController.put(i, j, playerModel.getChar());
	}
	
	/**
	 * 
	 * @return The name of the Player
	 */
	public String getPlayerName() {
		return playerModel.getName();
	}
	
	/**
	 * 
	 * @return The character the player is playing with 'X' or 'O'
	 */
	public char getPlayerChar() {
		return playerModel.getChar();
	}
}
