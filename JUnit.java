package start;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * The JUnit Test Class to make sure functionality works
 * @author Jameel
 *
 */
public class JUnit {
	
	Board boardModel;
	
	static Player playerModel1;
	static Player playerModel2;
	
	BoardView boardView;
	
	static PlayerController playerController1;
	static PlayerController playerController2;
	static PlayerController currPlayer;
	
	static BoardController boardController;
	
	private void setUp() {
		boardModel = new Board();
		playerModel1 = new Player("Player 1", 'X');
		playerModel2 = new Player("Player 2", 'O');
		
		boardView = new BoardView();
		
		boardController = new BoardController(boardModel,boardView);
		playerController1 = new PlayerController(playerModel1, boardController);
		playerController2 = new PlayerController(playerModel2, boardController);
		currPlayer = playerController1;
	}
	
	@Test
	public void testAddCharToBoard1() {	
		setUp();   
		playerController1.put(0, 0);//X
		char res = boardController.get(0, 0);
		assertEquals(res, 'X');    
	}
	
	@Test
	public void testAddCharToBoard2() {	
		setUp();   
		playerController2.put(0, 0);//O
		char res = boardController.get(0, 0);
		assertEquals(res, 'O');    
	}
	
	@Test
	public void testAddCharToBoardOutOfBounds() {	
		setUp();   
		playerController1.put(-1, 4);//X
		char res = boardController.get(-1, 4);
		assertEquals(res, ' ');    
	}
	
	@Test
	public void testWinX1() {	
		setUp();   
		playerController1.put(0, 0);//X
		playerController1.put(1, 0);//X
		playerController1.put(2, 0);//X
		int win = boardController.checkWin(null, boardModel);
		assertEquals(win, 1);    
	}
	
	@Test
	public void testWinX2() {	
		setUp();   
		playerController1.put(0, 0);//X
		playerController1.put(1, 1);//X
		playerController1.put(2, 2);//X
		int win = boardController.checkWin(null, boardModel);
		assertEquals(win, 1);    
	}
	
	@Test
	public void testNotWinX() {	
		setUp();   
		playerController1.put(0, 0);//X
		playerController1.put(1, 1);//X
		playerController1.put(0, 1);//X
		int win = boardController.checkWin(null, boardModel);
		assertEquals(win, 0);    
	}
	
	@Test
	public void testWinO1() {	
		setUp();   
		playerController2.put(0, 2);//O
		playerController2.put(1, 1);//O
		playerController2.put(2, 0);//O
		int win = boardController.checkWin(null, boardModel);
		assertEquals(win, 2);    
	}
	
	@Test
	public void testWinO2() {	
		setUp();   
		playerController2.put(0, 2);//O
		playerController2.put(1, 2);//O
		playerController2.put(2, 2);//O
		int win = boardController.checkWin(null, boardModel);
		assertEquals(win, 2);    
	}
	
	@Test
	public void testNotWinO() {	
		setUp();   
		playerController2.put(0, 0);//O
		playerController2.put(1, 1);//O
		playerController2.put(0, 1);//O
		int win = boardController.checkWin(null, boardModel);
		assertEquals(win, 0);    
	}
	
	@Test
	public void testDraw1() {	
		setUp(); 
		playerController1.put(0, 0);//X
		playerController2.put(0, 1);//O
		playerController1.put(0, 2);//X
		
		playerController2.put(1, 0);//O
		playerController1.put(1, 1);//X
		playerController1.put(1, 2);//X
		
		playerController2.put(2, 0);//O
		playerController1.put(2, 1);//X
		playerController2.put(2, 2);//O
		int win = boardController.checkWin(null, boardModel);
		assertEquals(win, -1);    
	}
	
	@Test
	public void testDraw2() {	
		setUp(); 
		playerController1.put(0, 0);//X
		playerController2.put(0, 1);//O
		playerController1.put(0, 2);//X
		
		playerController1.put(1, 0);//X
		playerController1.put(1, 1);//X
		playerController2.put(1, 2);//O
		
		playerController2.put(2, 0);//O
		playerController1.put(2, 1);//X
		playerController2.put(2, 2);//O
		int win = boardController.checkWin(null, boardModel);
		assertEquals(win, -1);    
	}
	
}
