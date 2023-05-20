package init;

import logic.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IllegalArgumentException {
		final int NPLAYERS = 4;
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
		System.out.println("\n\n---Select the Tails you want to put into your Shelf.");

		int exit = 1;
		int canPickTails = 0;
		int cont = 0;

		int[][] tempPositionTails = new int[3][2];

		// tRow = a,b,c,d.. tCol = 1,2,3.. - row = 0,1,2,3,.. col = 0,1,2,3,..
		while (cont < 3 && exit != 0) {
			canPickTails = shelf1.checkFreeSpaces();
			if (canPickTails == 0) {
				System.out.println("You can't pick any tails. Your shelf is full.");
				// TODO -> Call function "nextTurn()"
			}

			System.out.print("\n---Tail number: " + (cont + 1) + ".");
			// Take ROW and COL from user
			System.out.print("\n-Insert ROW [a-g]: ");
			char tRow = in.next().charAt(0);

			System.out.print("-Insert COL [1-9]: ");
			int col = in.nextInt();
			int tCol = col;

			// Set ROW and COL to numbers -> [0-6, 0-8]
			int row = (tRow - 97); // Convert from char to number (ASCII -97)
			col = col - 1;

			tails[cont] = board.selectTails(row, col, cont);

			if (tails[cont] == Tail.E) {
				System.out.println("The Tail in the position: [" + tRow + ", " + tCol + "] is not suitable.");
			} else {
				System.out.println(
						"You have chosen the tail '" + tails[cont] + "' in the position: [" + tRow + ", " + tCol + "]");
				tempPositionTails[cont][0] = row;
				tempPositionTails[cont][1] = col;
				cont++;
			}

			if (cont < 3) {
				System.out.println("\nEnter: \n-0 to stop; \n-1 to pick another.");
				exit = in.nextInt();
				if (exit != 0 && exit != 1) {
					throw new IllegalArgumentException("You must enter 0 or 1.");
				}
			}
		}

		board.emptyTheBox(tempPositionTails);
		System.out.println("---------------------------");

		printSelectedTails(tails, cont);
		board.printBoard();

	}

	public static void printSelectedTails(Tail tails[], int n) {
		System.out.println("\n---The tails you selected are: \n");
		for (int i = 0; i < n; i++) {
			System.out.print("-Tail number -" + (i + 1) + ": " + tails[i] + "\n");
		}
	}
}
