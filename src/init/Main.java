package init;
import logic.*;
// import gui.*;

public class Main {

	public static void main(String[] args) {
		int nPlayers = 4;
		Board board = new Board(nPlayers);
		board.printBoard();
	}

}
