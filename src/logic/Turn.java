package logic;

import java.util.*;

public class Turn {
	Tail[] tails = new Tail[] { Tail.E, Tail.E, Tail.E };
	int[][] positionTails = new int[3][2]; // Position of the tails the user picked

	private int endTurn = 1; // endTurn=1 -> keep going - endTurn=0 -> stop turn
	private int nOfFreeSpaces = 0; // number of free spaces in Player's shelf
	private boolean canPickTailsBoard = false; // Can user pick a Tail on the same row/col
	int nTailsPicked = 0; // number of tails the user picked

	private Board board;
	private Player currentPlayer;
	private Bookshelf shelf;
	private PersonalGoalCard pgc;
	
	// row and column in numbers
	private int row; // [0-6]
	private int col; // [0-8]

	// row and column selected
	private char tRow; // [a-g]
	private int tCol; // [1-9]

	Scanner sc = new Scanner(System.in);

	public Turn(Board board, Player currentPlayer, Bookshelf shelf, PersonalGoalCard pgc) {
		this.board = board;
		this.currentPlayer = currentPlayer;
		this.shelf = shelf;
		this.pgc = pgc;
	}

	/*
	 * @return true if game is over
	 */
	public boolean playTurn() {
		System.out.println("--- Player turn: " + currentPlayer.getUsername() + ".");
		System.out.println("\n\n---Select the Tails you want to put into your Shelf.");

		// Store the largest number of free cells (for every column of the shelf)
		nOfFreeSpaces = currentPlayer.getShelf().checkFreeSpaces();

		while (nTailsPicked < 3 && endTurn != 0) {

			// Check if board has to be refilled
			board.checkEndBoard();

			// User enters ROW and COL
			selectTail();

			// Stores selected tail in tails[i], if user can't pick tails[i] = Tails.E
			tails[nTailsPicked] = board.selectTails(row, col, nTailsPicked);
			checkIfTailIsSuitable(tails[nTailsPicked]);

			// Check if user can pick another tail (on the board).
			if (nTailsPicked < 3) {
				canPickTailsBoard = board.checkFreeSpaces(positionTails, nTailsPicked);
				if (canPickTailsBoard == false && nTailsPicked > 0) {
					System.out.println("You can't pick any adjacent tail on the board on the same row and column.");
					return false;
				}
			}

			// User decides if he wants to pick another tail
			if (nTailsPicked < 3) {
				endTurn = pickAgain();
			}

			removeTailsInBoard();
			// Print selected tails and the status board
			printSelectedTails(tails, nTailsPicked, positionTails);
			board.printBoard();
			
			// Column where the user wants to put the tail(s)
			int colInsert = selectColumn();
			
			// Insert tail(s)
			tails = shelf.orderTailsToInsert(tails, nTailsPicked);
			int state = shelf.insertTail(tails, colInsert, nTailsPicked);
			shelf.printBoard();
			
			sc.close();
			
			if (nOfFreeSpaces == 0) {
				return true;
			}
		}

		return false;
	}
	
	private void removeTailsInBoard() {
		// Remove the tails the user picked
		this.board.emptyTheBoard(positionTails);
		System.out.println("---------------------------");
	}
	
	private static void printSelectedTails(Tail tails[], int n, int tempPositionTails[][]) {
		System.out.println("\n---The tails you selected are: \n");
		for (int i = 0; i < n; i++) {
			System.out.print((i + 1) + "° Tail: '" + tails[i] + "' in the position: [" + (char)(tempPositionTails[i][0] + 97) + ", " + (tempPositionTails[i][1] + 1) + "] \n");
		}
	}

	private int pickAgain() {
		while (true) {
			System.out.println("\nEnter: \n-0 to stop; \n-1 to pick another.");
			endTurn = sc.nextInt();
			if (endTurn == 0 || endTurn == 1) {
				break;
			} else {
				System.out.println("You must enter 0 or 1.");
			}
		}
		return endTurn;
	}

	private void checkIfTailIsSuitable(Tail t) {
		if (t == Tail.E) {
			System.out.println("The Tail in the position: [" + tRow + ", " + tCol + "] is not suitable.");
		} else {
			System.out.println("You have chosen the tail '" + t + "' in the position: [" + tRow + ", " + tCol + "]");
			positionTails[nTailsPicked][0] = row;
			positionTails[nTailsPicked][1] = col;
			nTailsPicked++;
			nOfFreeSpaces--;

		}
	}

	// User enters ROW and COL
	private void selectTail() {
		System.out.print("\n---Tail number: " + (nTailsPicked + 1) + ".");
		System.out.print("\n-Insert ROW [a-g]: ");
		char tRow = sc.next().charAt(0);
		System.out.print("-Insert COL [1-9]: ");
		int tCol = sc.nextInt();

		// Set ROW and COL to numbers -> [0-6, 0-8]
		this.row = (tRow - 97); // Convert from char to number (ASCII -97)
		this.col = (tCol - 1);
	}

	private int selectColumn() {
		shelf.printBoard();
		int column;
		
		while(true) {
			System.out.print("\nChoose the column where to insert(1-5): ");
			column = (sc.nextInt() - 1);
			if(column >= 0 && column <= 4){
				break;
			} else {
				System.out.println("\nError: choose a column in the range [1-5].");
			}
			System.out.println("");
		}
		return column;
	}
	
	// -------- GETTER AND SETTER --------

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

	public boolean isCanPickTailsBoard() {
		return canPickTailsBoard;
	}

	public void setCanPickTailsBoard(boolean canPickTailsBoard) {
		this.canPickTailsBoard = canPickTailsBoard;
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

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

}