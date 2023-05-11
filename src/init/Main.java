package init;
import logic.*;
// import gui.*;

public class Main {

	public static void main(String[] args) {
		final int NPLAYERS = 2;
		Board board = new Board(NPLAYERS);
		board.printBoard();
		
		
		Bookshelf shelf1 = new Bookshelf();
		PersonalGoalCard personalGoal1 = new PersonalGoalCard();
		Player Player1 	 = new Player(1, "Leonardo", shelf1, personalGoal1);
		
		Bookshelf shelf2 = new Bookshelf();
		PersonalGoalCard personalGoal2 = new PersonalGoalCard();
		Player Player2 	 = new Player(2, "Pietro", shelf2, personalGoal2);
		
		
		shelf1.printBoard();
		personalGoal1.printBoard();
	}//ci

}

