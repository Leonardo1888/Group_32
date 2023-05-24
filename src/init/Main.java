package init;

import logic.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Main {
public static void main(String[] args) throws IllegalArgumentException {

	final int NPLAYERS = 4;
	
	Board board = new Board(NPLAYERS);
	System.out.println("\n----------START GAME----------\n");
	System.out.println("\nSTATUS Board:");
	board.printBoard();

	// Create player 1, currentPlayerIndex=1
	Bookshelf shelf1 = new Bookshelf();
	PersonalGoalCard personalGoal1 = new PersonalGoalCard();
	Player Player1 = new Player(1, "Leonardo", shelf1, personalGoal1);

	// Create player 2, currentPlayerIndex=2
	Bookshelf shelf2 = new Bookshelf();
	PersonalGoalCard personalGoal2 = new PersonalGoalCard();
	Player Player2 = new Player(2, "Pietro", shelf2, personalGoal2);

	/* Commong goal cards and shelves.
	System.out.println("\nCommon Goal Cards:");
	CommonGoals commonGoals = new CommonGoals();

	System.out.println("\n" + Player1.getUsername() + "'s Shelf:");
	shelf1.printBoard();
	System.out.println("\n" + Player1.getUsername() + "'s Personal Goal Card:");
	personalGoal1.printBoard();

	System.out.println("\n" + Player2.getUsername() + "'s Shelf:");
	shelf2.printBoard()
	System.out.println("\n" + Player2.getUsername() + "'s Personal Goal Card:");
	personalGoal2.printBoard();
	 */

	boolean gameOver = false;
	
	List<Player> players = new ArrayList<>();
	players.add(Player1);
	players.add(Player2);
	
	int currentPlayerIndex = 1;
	
	while(!gameOver) {
		// -------- START TURN
		Scanner in = new Scanner(System.in);
		
		Tail[] tails = new Tail[] { Tail.E, Tail.E, Tail.E };
		int[][] positionTails = new int[3][2];

		int endTurn = 1;					// endTurn=1 -> keep going - endTurn=0 -> stop turn
		int nOfFreeSpaces = 0;				// nOfFreeSpaces in Player's shelf
		boolean canPickTailsBoard = false;	// Number of tails a user can pick
		int cont = 0;						// cont = number of tails the user picked

		System.out.println("\n\n---Select the Tails you want to put into your Shelf.");

		// tRow = a,b,.. tCol = 1,2,.. ----- row = col = 0,1,..
		while (cont < 3 && endTurn != 0) {

			// Check if board has to be refilled
			board.endBoard();

			// Only the first time store the largest number of free cells (for every column)
			if(cont==0)
				nOfFreeSpaces = shelf1.checkFreeSpaces();
			
			// Check if user has space on his shelf
			if (nOfFreeSpaces == 0) {
				System.out.println("You can't pick any tails. Your shelf is full.");
				// TODO -> Call function "nextTurn()" (end game)
			}

			// User enters ROW and COL
			System.out.print("\n---Tail number: " + (cont + 1) + ".");
			System.out.print("\n-Insert ROW [a-g]: ");
			char tRow = in.next().charAt(0);
			System.out.print("-Insert COL [1-9]: ");
			int col = in.nextInt();
			int tCol = col;

			// Set ROW and COL to numbers -> [0-6, 0-8]
			int row = (tRow - 97); // Convert from char to number (ASCII -97)
			col = col - 1;

			// Stores selected tail in tails[i], if user can't pick tails[i] = Tails.E
			tails[cont] = board.selectTails(row, col, cont);
			if (tails[cont] == Tail.E) {
				System.out.println("The Tail in the position: [" + tRow + ", " + tCol + "] is not suitable.");
			} else {
				System.out.println(
						"You have chosen the tail '" + tails[cont] + 
						"' in the position: [" + tRow + ", " + tCol + "]");
				positionTails[cont][0] = row;
				positionTails[cont][1] = col;
				cont++;
				nOfFreeSpaces--;
				
			}

			// Check if user can pick another tail (on the board).
			if(cont < 3) {				
				canPickTailsBoard = board.checkFreeSpaces(positionTails, cont);
				if (canPickTailsBoard == false && cont > 0) {
					System.out.println("You can't pick any adjacent tail on the board on the same row and column.");
					// TODO -> Call function "nextTurn()"
				}
			}

			// User decides if he wants to pick another tail
			if (cont < 3) {
				System.out.println("\nEnter: \n-0 to stop; \n-1 to pick another.");
				endTurn = in.nextInt();
				if (endTurn != 0 && endTurn != 1) {
					throw new IllegalArgumentException("You must enter 0 or 1.");
				}
			}
			
			
			
			// -------- END TURN
		}
		
		
		// Remove the tails the user picked
		board.emptyTheBoard(positionTails);
		System.out.println("---------------------------");

		// Print selected tails and the status board
		printSelectedTails(tails, cont, positionTails);
		board.printBoard();
		
		Scanner inp = new Scanner(System.in);
		shelf1.printBoard();
		System.out.println("\nChoose the column where to insert(1-5): ");
		int colInsert = inp.nextInt() - 1;
		
		if(colInsert < 0 || colInsert > 4){
			System.out.println("Error: choose a column in the range 1-5");
		}
		
		tails = shelf1.orderTailsToInsert(tails, cont);
		
		int state = shelf1.insertTail(tails, colInsert, cont);
		
		inp.close();
		
		shelf1.printBoard();
		
		in.close();
		
	}
	
}

public static void printSelectedTails(Tail tails[], int n, int tempPositionTails[][]) {
	System.out.println("\n---The tails you selected are: \n");
	for (int i = 0; i < n; i++) {
		System.out.print((i + 1) + "� Tail: '" + tails[i] + "' in the position: [" + (char)(tempPositionTails[i][0] + 97) + ", " + (tempPositionTails[i][1] + 1) + "] \n");
		}
	}
}
