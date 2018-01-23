package start;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
* The TTT (Tic-Tac-Toe) program is an implementation to an assignment 
* in SE 319. It is a TTT program that fulfills the following requirements...
* 
* <ul>
*  <li>Show which player’s turn</li>
*  <li>Click on the blank cell to mark Xor O.</li>
*  <li>When one player wins, stop the game and show ”Congratulations, Xwin the game” or ”Congratulations, Owin the game” in GUI.</li>
*  <li>When all cells filled and no one wins, stop the game and show ”Draw”</li>
*  <li>When the game is over, show the option to restart a new game</li>
* </ul>
*  
*  Its GUI is created using JavaFX and it attempts to follow MVC (Model, View, Controller) practices
*  
* @author  Jameel Kelley
*/
public class TTT extends Application {
	Board boardModel;
	
	static Player playerModel1;
	static Player playerModel2;
	
	BoardView boardView;
	
	static PlayerController playerController1;
	static PlayerController playerController2;
	static PlayerController currPlayer;
	
	static BoardController boardController;
	
	boolean gameEnd = false;
	
	static Text output;

	public static void main(String[] args) {
		launch(args);
	}
	
	// Initialize the board, player, and their controllers, as well as the view
	public void init() {
		boardModel = new Board();
		playerModel1 = new Player("Player 1", 'X');
		playerModel2 = new Player("Player 2", 'O');
		
		boardView = new BoardView();
		
		boardController = new BoardController(boardModel,boardView);
		playerController1 = new PlayerController(playerModel1, boardController);
		playerController2 = new PlayerController(playerModel2, boardController);
		currPlayer = playerController1;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//====Setup====
		init();
		primaryStage.setTitle("Tic-Tac-Toe");
		primaryStage.setResizable(false);
        Scene scene = new Scene(new VBox(), 300, 400);
        scene.setFill(Color.OLDLACE);
        
        
        
        //====Turn Tracker====
        String append = "'s Turn!";
        Text txt = new Text(currPlayer.getPlayerChar() + append);
        HBox hb = new HBox();
        hb.getChildren().addAll(txt);
        
        //====Canvas====
        Canvas canvas = new Canvas(300,300);
        GraphicsContext gcMain = canvas.getGraphicsContext2D();
        boardView.drawFramework(canvas);
        //----Click Handler----
        canvas.setOnMouseClicked(e -> {
        	if(!gameEnd)
        		gameEnd = boardController.handleCanvasClick(canvas,e.getX(),e.getY(), CONFIG.unitSize, CONFIG.offset, CONFIG.strokeWidth);
        	if(!gameEnd)
        		txt.setText(currPlayer.getPlayerChar() + append);
        	else {
        		txt.setText("");
        		Button reset = new Button("Reset");
        		reset.setOnAction(a -> {
        			doReset(gcMain,canvas,hb);
                	((VBox) scene.getRoot()).getChildren().remove(reset);
        		});
        		((VBox) scene.getRoot()).getChildren().add(reset);
        	}
        	hb.requestFocus();
        });
        
        
        //====Menu====
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem newItem = new MenuItem("New Game");
        newItem.setOnAction(e -> {
        	doReset(gcMain,canvas,hb);
        });
        menuFile.getItems().addAll(newItem);
        menuBar.getMenus().addAll(menuFile);
        
        output = new Text("");
        output.setFont(new Font(20));
        output.setTextAlignment(TextAlignment.CENTER);
        
        //====Create====
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar,hb,canvas,output);
 
        primaryStage.setScene(scene);
        primaryStage.show();
        hb.requestFocus();
		
		
	}
	
	/**
	 * Reset the game
	 * @param gcMain
	 * @param canvas
	 * @param hb
	 */
	private void doReset(GraphicsContext gcMain, Canvas canvas, HBox hb) {
		boardController.clearBoard();
    	boardController.updateView();
    	gcMain.clearRect(0, 0, 300, 300);
    	boardView.drawFramework(canvas);
    	gameEnd = false;
    	currPlayer = playerController1;
    	output.setText("");
    	hb.requestFocus();
		
	}
	
	/**
	 * Get the current PlayerController
	 * @return the current PlayerController who is taking a turn
	 */
	public static PlayerController getCurrPlayer() {
		PlayerController player = currPlayer;
		if(currPlayer == playerController1) 
			currPlayer = playerController2;
		else 
			currPlayer = playerController1;
		return player;
	}
	
	
	
	

}
