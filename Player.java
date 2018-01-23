package start;

/**
 * The Player Model
 * @author Jameel
 *
 */
public class Player {
	private String name;
	private char character;
	
	/**
	 * The Player Object to hold player info
	 * @param name
	 * @param character
	 */
	public Player(String name, char character) {
		this.name = name;
		this.character = character;
	}
	
	/**
	 * Get the name of the player
	 * @return Player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get the char the player is using
	 * @return Player's char ( 'X' or 'O' )
	 */
	public char getChar() {
		return this.character;
	}
	
	/**
	 * Set the name of the player
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
