package init;
import logic.*;
// import gui.*;

public class Main {

	public static void main(String[] args) {
		final int NPLAYERS = 2;
		Board board = new Board(NPLAYERS);
		System.out.println("\nBoard:");
		board.printBoard();
		
		
		Bookshelf shelf1 = new Bookshelf();
		PersonalGoalCard personalGoal1 = new PersonalGoalCard();
		Player Player1 	 = new Player(1, "Leonardo", shelf1, personalGoal1);
		
		Bookshelf shelf2 = new Bookshelf();
		PersonalGoalCard personalGoal2 = new PersonalGoalCard();
		Player Player2 	 = new Player(2, "Pietro", shelf2, personalGoal2);
		
		System.out.println("\nCommon Goal Cards:");
		CommonGoals commonGoals = new CommonGoals();
		
		System.out.println("\n" + Player1.getUsername() + "'s Shelf:");
		shelf1.printBoard();
		System.out.println("\n" + Player1.getUsername() + "'s Personal Goal Card:");
		personalGoal1.printBoard();
		
		System.out.println("\n" + Player2.getUsername() + "'s Shelf:");
		shelf2.printBoard();
		System.out.println("\n" + Player2.getUsername() + "'s Personal Goal Card:");
		personalGoal2.printBoard();
	}

}

