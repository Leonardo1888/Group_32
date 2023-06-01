package logic;

import java.util.*;
import commonGoalsPackage.*;

public class Turn {
	Tail[] tails = new Tail[] { Tail.E, Tail.E, Tail.E };
	int[][] positionTails = new int[3][2]; // Position of the tails the user picked

	private int endTurn = 1; 	 	// endTurn=1 -> keep going - endTurn=0 -> stop turn
	private int nOfFreeSpaces = 0;  // number of free spaces in Player's shelf
	private boolean canPickTailsBoard = true; // Can user pick a Tail on the same row/col
	private int nTailsPicked = 0; 	// number of tails the user picked

	private Board board;
	private Player currentPlayer;

	public CommonGoal getCommonGoalA() {
		return commonGoalA;
	}

	public void setCommonGoalA(CommonGoal commonGoalA) {
		this.commonGoalA = commonGoalA;
	}

	public CommonGoal getCommonGoalB() {
		return commonGoalB;
	}

	public void setCommonGoalB(CommonGoal commonGoalB) {
		this.commonGoalB = commonGoalB;
	}

	private Bookshelf shelf;
	private PersonalGoalCard pgc;
	private Scanner sc;
	private CommonGoal commonGoalA;
	private CommonGoal commonGoalB;

	// row and column in numbers
	private int row; // [0-6]
	private int col; // [0-8]

	// row and column selected
	private char tRow; // [a-g]
	private int tCol; // [1-9]

	private int turnCounter;

	private boolean gameOver = false;

	public boolean isGameOver() {
		return gameOver;
	}

	public Turn(Board board, Player currentPlayer, Bookshelf shelf, PersonalGoalCard pgc, Scanner sc, int turnCounter,
			CommonGoal A, CommonGoal B) {
		this.board = board;
		this.currentPlayer = currentPlayer;
		this.shelf = shelf;
		this.pgc = pgc;
		this.sc = sc;
		this.turnCounter = turnCounter;
		this.nTailsPicked = 0;
		this.commonGoalA = A;
		this.commonGoalB = B;
	}

	/*
	 * @return true if turn has ended sets gameOver to true if a shelf is full
	 */
	public boolean playTurn() {
		String testInput;
		if (turnCounter == 0)
			testInput = sc.nextLine();
		System.out.println("\n------------------------ "
				+ "NEXT TURN - PLAYER: [" + currentPlayer.getUsername()
				+ "] ---------------------------");
		printBoardAndShelfAndPgc();

		System.out.println("\n\n'" + currentPlayer.getUsername() 
		+ "' select the Tails you want to put into your Shelf.");

		// Store the biggest number of free cells (for every column of the shelf)
		nOfFreeSpaces = shelf.checkFreeSpaces();
		if (nOfFreeSpaces == 0) {
			this.gameOver = true;
		}

		while (nTailsPicked < 3 && endTurn != 0) {

			// Check if board has to be refilled
			board.checkEndBoard();

			// User enters ROW and COL
			selectTail(sc);

			// Saves selected Tail, else is E
			tails[nTailsPicked] = board.selectTails(row, col, nTailsPicked);

			// If not E: saves in positionTails the coordinates; nTailsPicked++;
			// nOfFreeSpaces-- (it has to be on same col in shelf)
			checkIfNotEmpty(tails[nTailsPicked]);

			// Check if user can pick another tail (on the board).
			if (nTailsPicked > 0 && nTailsPicked < 3) {
				canPickTailsBoard = board.checkFreeSpaces(positionTails, nTailsPicked);
				if (canPickTailsBoard == false && nTailsPicked > 0) {
					System.out.println("You can't pick more Tails on the board on the same row and column.");
					actionsOfEndTurn();
					// TURN FINISHED
					return true;
				}
			}

			// User decides if he wants to pick another tail
			// If the user can't pick he won't be able to choose 1 and pick again.
			if (canPickTailsBoard == true && nTailsPicked > 0 && nTailsPicked < 3 && nOfFreeSpaces != 0) {
				endTurn = pickAgain(sc);
				if (endTurn == 0) {
					actionsOfEndTurn();
					// TURN FINISHED
					return true;
				}
			}

			if (nOfFreeSpaces == 0) {
				System.out.println("You can't pick more Tails because you don't have enough spaces on you bookshelf!");
				actionsOfEndTurn();
				// TURN FINISHED
				return true;
			}
		}
		actionsOfEndTurn();
		return true;
	}

	// Prints board and player's shelf next to each other
	private void printBoardAndShelfAndPgc() {
		int ROW = board.getRow();
		char indexChar = 'a' - 1;

		System.out.println("\nBoard:                       " + this.currentPlayer.getUsername() + "'s shelf:      "
				+ this.currentPlayer.getUsername() + "'s personal goal card:");

		// i = -1 prints the first row with numbers.
		for (int i = -1; i < ROW; i++) {

			// Print row board
			board.printRowBoard(i, indexChar);
			indexChar++;
			System.out.print("         ");

			// Print row shelf
			if (i < this.shelf.getRow())
				this.shelf.printRowBookshelf(i);

			for (int j = 0; j < currentPlayer.getUsername().length(); j++) {
				System.out.print(" ");
			}
			System.out.print("     ");

			// Print row personalGoalCard
			if (i < this.shelf.getRow())
				this.pgc.printRowPersonalGoalCard(i);
			System.out.println();

		}
	}

	// Run these actions at the end of every turn
	private void actionsOfEndTurn() {
		removeTailsInBoard();

		// Print selected tails and the board status
		printSelectedTails(tails, nTailsPicked, positionTails);

		// Select the Column where the user wants to put the tail(s)
		printBoardAndShelfAndPgc();

		int state = 1;
		while (state == 1) {
			int colInsert = selectColumn(sc);
			// Insert tail(s)
			tails = shelf.orderTailsToInsert(this.sc, tails, nTailsPicked);
			state = shelf.insertTail(tails, colInsert, nTailsPicked); // insertTail returns 0->success
			System.out.println("\n\nThis is " + currentPlayer.getUsername() + "'s shelf updated:");
			shelf.printShelf(); // return 1->E in col < n Tails
		}
		checkCommonGoals();
	}

	// Check the common goal 1 and 2, if they are achieved sum the player's points
	private void checkCommonGoals() {
		// The user can't achieve the same common goal two times
		// Check if the user achieved the commonGoal A in his shelf
		if (this.currentPlayer.getCommonGoalA() == false && this.commonGoalA.checkCommonGoal(this.shelf) != 0) {
			currentPlayer.sumPoints(this.commonGoalA.checkCommonGoal(this.shelf));
			currentPlayer.setCommonGoalA(true);
			System.out.println("You achieved the commongoal 'A' and you gained: "
					+ this.commonGoalA.checkCommonGoal(this.shelf) + " points!");
			System.out.println("Player: " + currentPlayer.getUsername() + " has now a total of "
					+ currentPlayer.getPoints() + " points.");
		}

		// If false check if the user achieved the commonGoal B in his shelf
		if (this.currentPlayer.getCommonGoalB() == false && this.commonGoalB.checkCommonGoal(this.shelf) != 0) {
			currentPlayer.sumPoints(this.commonGoalB.checkCommonGoal(this.shelf));
			currentPlayer.setCommonGoalB(true);
			System.out.println("You achieved the commongoal 'B' and you gained: "
					+ this.commonGoalB.checkCommonGoal(this.shelf) + " points!");
			System.out.println("Player: " + currentPlayer.getUsername() + " has now a total of "
					+ currentPlayer.getPoints() + " points.");
		}

	}

	// Remove the tails the user picked
	private void removeTailsInBoard() {
		this.board.emptyTheBoard(positionTails);
		System.out.println("");
	}

	// Print the tails the user selected
	private void printSelectedTails(Tail tails[], int n, int tempPositionTails[][]) {
		System.out.println("\n---The tails you selected are: \n");
		for (int i = 0; i < n; i++) {
			Tail t = tails[i];
			System.out.print((i + 1) + "° Tail: '");
			printTail(t);
			System.out.println("' in the position: [" + (char) (tempPositionTails[i][0] + 97) + ", "
					+ (tempPositionTails[i][1] + 1) + "] ");
		}
	}

	// Ask the user if he wants to pick again, with "(y)es" or "(n)o"
	private int pickAgain(Scanner sc) {
		int errorCount = 0;
		while (errorCount++ < 30) {
			System.out.println("\nEnter: 'no' to stop or 'yes' if you want to pick another tail.");
			String input = sc.nextLine().toLowerCase().trim();
			if (input.equals("y") || input.equals("yes")) {
				return 1;
			}
			if (input.equals("n") || input.equals("no")) {
				return 0;
			}
			System.out.println("Error! Answer 'yes' or 'no'.");
		}
		throw new RuntimeException("Repeated invalid user input. Restart the program.");
	}

	// Check if the Tail the user selected is not empty
	private void checkIfNotEmpty(Tail t) {
		if (t == Tail.E) {
			System.out.println("The Tail in the position: [" + tRow + ", " + tCol + "] is not suitable.");
		} else {
			System.out.print("You have chosen the tail '");
			printTail(t);
			System.out.println("' in the position: [" + tRow + ", " + tCol + "]");
			this.positionTails[nTailsPicked][0] = row;
			this.positionTails[nTailsPicked][1] = col;
			this.nTailsPicked++;
			this.nOfFreeSpaces--;

		}
	}

	// User enters ROW and COL
	private void selectTail(Scanner sc) {
		System.out.print("\n---Tail number: " + (nTailsPicked + 1) + ".");
		this.tRow = insertRow(sc);
		this.tCol = insertCol(sc);

		// Set ROW and COL to numbers -> [0-6, 0-8]
		this.row = (tRow - 97); // Convert from char to number (ASCII - 97)
		this.col = (tCol - 1);
	}

	// Checks for the row the user entered
	private char insertRow(Scanner sc) {
		int errorCount = 0;
		String input;
		while (errorCount++ < 20) {
			System.out.print("\n-Insert ROW [a-i]: ");
			input = sc.nextLine().toLowerCase().trim();
			if (input.length() == 1 && input.charAt(0) >= 'a' && input.charAt(0) <= 'i') {
				return input.charAt(0);
			}
			System.out.println("Error! You have to enter ROW in the range from 'a' to 'i'.");
		}
		System.out.println(
				"A row is defined by a character. Restart the program and enter the ROW in the range from 'a' to 'i'.");
		throw new RuntimeException("Restart the program.");
	}

	// Checks for the column the user entered
	private int insertCol(Scanner sc) {
		int errorCount = 0;
		String input;
		while (errorCount++ < 20) {
			System.out.print("-Insert COL [1-9]: ");
			input = sc.nextLine().toLowerCase().trim();
			if (input.length() == 1 && input.charAt(0) >= '1' && input.charAt(0) <= '9') {
				return Character.getNumericValue(input.charAt(0));
			}
			System.out.println("Error! You have to enter COLUMN in the range from '1' to '9'.");
		}
		throw new RuntimeException("Repeated invalid user input. Restart the program.");
	}

	// The user chooses the column in his bookshelf where he will put his tails
	private int selectColumn(Scanner sc) {
		int errorCount = 0;
		String input;
		while (errorCount++ < 20) {
			System.out.print("\nChoose the column where to insert the tails [1-5]: ");
			input = sc.nextLine().trim();
			if (input.length() == 1 && input.charAt(0) >= '1' && input.charAt(0) <= '5') {
				return input.charAt(0) - '1';
			}
			System.out.print("Error! You have to enter COLUMN in the range from '1' to '5'.");
		}
		throw new RuntimeException("Repeated invalid user input. Restart the program.");
	}

	// Print colored tail
	static private void printTail(Tail t) {
		if (t == Tail.C) {
			System.out.print(Color.GREEN);
			System.out.print("C");
			System.out.print(Color.RESET);
		}
		if (t == Tail.B) {
			System.out.print(Color.WHITE);
			System.out.print("B");
			System.out.print(Color.RESET);
		}
		if (t == Tail.G) {
			System.out.print(Color.YELLOW);
			System.out.print("G");
			System.out.print(Color.RESET);
		}
		if (t == Tail.F) {
			System.out.print(Color.BLUE);
			System.out.print("F");
			System.out.print(Color.RESET);
		}
		if (t == Tail.T) {
			System.out.print(Color.CYAN);
			System.out.print("T");
			System.out.print(Color.RESET);
		}
		if (t == Tail.P) {
			System.out.print(Color.RED);
			System.out.print("P");
			System.out.print(Color.RESET);
		}
		if (t == Tail.E || t == Tail.X) {
			System.out.print(Color.BLACK);
			System.out.print("#");
			System.out.print(Color.RESET);
		}
	}

	/* ---------- Getter and setter ---------- */

	public Tail[] getTails() {
		return tails;
	}

	public void setTails(Tail[] tails) {
		this.tails = tails;
	}

	public int[][] getPositionTails() {
		return positionTails;
	}

	public void setPositionTails(int[][] positionTails) {
		this.positionTails = positionTails;
	}

	public int getEndTurn() {
		return endTurn;
	}

	public void setEndTurn(int endTurn) {
		this.endTurn = endTurn;
	}

	public int getnOfFreeSpaces() {
		return nOfFreeSpaces;
	}

	public void setnOfFreeSpaces(int nOfFreeSpaces) {
		this.nOfFreeSpaces = nOfFreeSpaces;
	}

	public int getCont() {
		return nTailsPicked;
	}

	public void setCont(int cont) {
		this.nTailsPicked = cont;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}