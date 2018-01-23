package start;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * The Adjustible Config Vars
 * @author Jameel
 *
 */
public class CONFIG {
	// The color of the X shape 
	public static Color xColor = Color.BLUE;
	// The color of the O shape 
	public static Color oColor = Color.RED;
	// The color of the # frame
	public static Color frameColor = Color.BLACK;
	// The size of one Square in the frame, 9 Squares total
	public static int unitSize = 50;
	// The offset to use when aligning from the side and top of the Layout in the stage
	public static int offset = 75;
	// The size of each framework line
	public static int strokeWidth = 10;
	// The color to paint the winning squares with
	public static Paint winColor = Color.GREEN;
	// The character for blank spaces in the data structure
	public static char BLANK_SPACE = '#';
	// Set to true to get console output
	public static boolean DEBUG = false;
}
