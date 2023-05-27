package logic;

import java.util.*;

public class Turn {
	Tail[] tails = new Tail[] { Tail.E, Tail.E, Tail.E };
	int[][] positionTails = new int[3][2]; // Position of the tails the user picked

	private int endTurn = 1; // endTurn=1 -> keep going - endTurn=0 -> stop turn
	private int nOfFreeSpaces = 0; // number of free spaces in Player's shelf
	private boolean canPickTailsBoard = true; // Can user pick a Tail on the same row/col
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
		System.out.println("\n\n--- Player turn: " + currentPlayer.getUsername() + ".");
		System.out.println("Select the Tails you want to put into your Shelf.");
		
		// Store the largest number of free cells (for every column of the shelf)
		nOfFreeSpaces = currentPlayer.getShelf().checkFreeSpaces();

		while (nTailsPicked < 3 && endTurn != 0) {

			// Check if board has to be refilled
			board.checkEndBoard();

			// User enters ROW and COL
			selectTail();

			// Stores selected tail in tails[nTailsPicked], 
			// if user CAN'T pick --> tails[inTailsPicked] = Tails.E
			tails[nTailsPicked] = board.selectTails(row, col, nTailsPicked);
			
			// If suitable increment nTailsPicked and decrement nOfFreeSpaces (bcs it has to be on same col)
			checkIfTailIsSuitable(tails[nTailsPicked]);

			// Check if user can pick another tail (on the board)
			if (tails[(nTailsPicked-1)] != Tail.E) {
				System.out.println("nTailsPicked: " + nTailsPicked + " and canPickTailsBoard: " + canPickTailsBoard);
				canPickTailsBoard = board.checkFreeSpaces(positionTails, nTailsPicked);
				System.out.println("nTailsPicked: " + nTailsPicked + " and canPickTailsBoard: " + canPickTailsBoard);
				if (canPickTailsBoard == false) {
					System.out.println("You can't pick any adjacent tail on the board on the same row and column.");
					actionsOfEndTurn();
					sc.close();
				}
			}

			// User decides if he wants to pick another tail
			if (canPickTailsBoard == true && nTailsPicked < 3) {
				endTurn = pickAgain();
				if(endTurn == 0) {
					actionsOfEndTurn();
					sc.close();
				}
			}
			
			if (nOfFreeSpaces == 0) {
				return true;
			}
		}

		return false;
	}
	
	private void actionsOfEndTurn() {
		removeTailsInBoard();
		
		// Print selected tails and the board status
		printSelectedTails(tails, nTailsPicked, positionTails);
		board.printBoard();
	
		// Column where the user wants to put the tail(s)
		int colInsert = selectColumn();
	
		// Insert tail(s)
		tails = shelf.orderTailsToInsert(tails, nTailsPicked);
		int state = shelf.insertTail(tails, colInsert, nTailsPicked);
		shelf.printShelf();
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
		this.tRow = sc.next().charAt(0);
		System.out.print("-Insert COL [1-9]: ");
		this.tCol = sc.nextInt();

		// Set ROW and COL to numbers -> [0-6, 0-8]
		this.row = (tRow - 97); // Convert from char to number (ASCII -97)
		this.col = (tCol - 1);
	}

	// Insert in shelf the tails
	private int selectColumn() {
		shelf.printShelf();
		int column;
		
		while(true) {
			System.out.print("\nChoose the column where to insert [1-5]: ");
			column = sc.nextInt();
			column--;
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

}