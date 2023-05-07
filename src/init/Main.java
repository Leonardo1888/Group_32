package init;
import logic.*;
// import gui.*;

public class Main {

	public static void main(String[] args) {
		final int NPLAYERS = 3;
		Board board = new Board(NPLAYERS);
		board.printBoard();
		
		
		Bookshelf shelf1 = new Bookshelf();
		Player Player1 	 = new Player(1, "Leonardo", shelf1);
		
		Bookshelf shelf2 = new Bookshelf();
		Player Player2 	 = new Player(2, "Pietro", shelf2);
		
		
		shelf1.printBoard();
	}

}
