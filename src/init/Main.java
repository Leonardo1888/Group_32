package init;

import logic.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		final int NPLAYERS = 3;
		Board board = new Board(NPLAYERS);
		System.out.println("\nBoard:");
		board.printBoard();

		Bookshelf shelf1 = new Bookshelf();
		PersonalGoalCard personalGoal1 = new PersonalGoalCard();
		Player Player1 = new Player(1, "Leonardo", shelf1, personalGoal1);

		Bookshelf shelf2 = new Bookshelf();
		PersonalGoalCard personalGoal2 = new PersonalGoalCard();
		Player Player2 = new Player(2, "Pietro", shelf2, personalGoal2);

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

		Scanner in = new Scanner(System.in);
		Tail[] tails = new Tail[] { Tail.E, Tail.E, Tail.E };
		System.out.println("\nProva inserimento Tail:");

		int n = 0;
		int exit = 1;
		while (n < 3 && exit != 0) {
			System.out.println("Insert row: ");
			char inRow = in.next().charAt(0);
			System.out.println("Insert col: ");
			int col = in.nextInt();
			
			int row = (inRow-97);
			col = col-1;
			
			tails[n] = board.selectTails(row, col, n);
			
			if (tails[n] == Tail.E) {
				System.out.println("Choose a suitable Tail");
			} else {
				n++;
			}
			System.out.println("Enter: '0' to stop or '1' to pick another.");
			exit = in.nextInt();
		}

		// print selected tails
		for (int i = 0; i < 3; i++) {
			System.out.println(tails[i]);
		}

		board.printBoard();
	}
}
