package logic;
//
import java.lang.Math;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Board implements Matrix {
	private int nPlayers;

	private final int ROW = 9;
	private final int COL = 9;

	private int rowTemp;
	private int colTemp;
	private int align; // 0 is horizontal, 1 is vertical

	private Tail[][] board;
	private int[] tailCount;

	public Board(int nPlayers) {
		this.nPlayers = nPlayers;

		if (checkNPlayers(nPlayers)) {
			this.board = new Tail[ROW][COL];
			this.tailCount = new int[Tail.values().length];
			fillBoard(board);
		} else {
			System.exit(0);
		}
	}

	// index = position of tail, first, second, third tail selected in the board
	public Tail selectTails(int row, int col, int index) throws IllegalArgumentException {
		// Check if the row and column values are within the valid range
		if (row < 0 || row >= this.ROW) {
			System.out.println("Row has to be >= 0 and < " + this.ROW);
			return Tail.E;
		}
		if (col < 0 || col >= this.COL) {
			System.out.println("Col has to be >= 0 and < " + this.COL);
			return Tail.E;
		}
		if (this.board[row][col] == Tail.E) {
			System.out.println("Tail has to be different to E.");
			return Tail.E;
		}

		Tail tail = Tail.E;

		switch (index) {
		case 0:
			if (sideFreeTail(row, col)) {
				tail = this.board[row][col];
				setTemp(row, col);
			} else {
				System.out.println("1-Choose a suitable tail..");
				tail = Tail.E;
			}
			break;

		case 1:
			if (sideFreeTail(row, col) && adjacentTail(row, col, this.rowTemp, this.colTemp)) {
				tail = this.board[row][col];
				setTemp(row, col);
			} else {
				System.out.println("2-Choose a suitable tail..");
				tail = Tail.E;
			}
			break;
		case 2:
			if (sideFreeTail(row, col) && adjacentTail(row, col, this.rowTemp, this.colTemp)
					&& controlAlign(row, col)) {
				tail = this.board[row][col];
				setTemp(row, col);
			} else {
				System.out.println("3-Choose a suitable tail..");
				tail = Tail.E;
			}
			break;
		default: 
			System.out.println("Errore");
			break;
		}

		return tail;
	}

	public void setTemp(int row, int col) {
		this.rowTemp = row;
		this.colTemp = col;
	}

	public boolean sideFreeTail(int row, int col) {
		// If Tail is on the outer square
		if (row == 0 || row == (this.ROW - 1) || col == 0 || col == (this.COL - 1)) {
			return true;
		}

		if (this.board[row - 1][col] == Tail.E || this.board[row + 1][col] == Tail.E
				|| this.board[row][col - 1] == Tail.E || this.board[row][col + 1] == Tail.E) {
			return true;
		}
		return false;
	}

	public boolean adjacentTail(int row1, int col1, int row2, int col2) {
		if (row1 != row2 && col1 == col2) {
			if (Math.abs(row1 - row2) == 1) {
				this.align = 1; // vertical
				return true;
			}
		}
		if (col1 != col2 && row1 == row2) {
			if (Math.abs(col1 - col2) == 1) {
				this.align = 0; // horizontal
				return true;
			}
		}

		return false;
	}

	public boolean controlAlign(int row, int col) {
		if (this.align == 0 && row == this.rowTemp) {
			return true;
		}
		if (this.align == 1 && col == this.colTemp) {
			return true;
		}
		return false;
	}
	
	// Check if user can pick another tail on the same colum/row 
	// Case:0 didn't pick any tails, Case:1 Picked 1 tail, Case 2: Picked 2 tails. 
	public boolean checkFreeSpaces(int positionTails[][], int cont) {
		int row = positionTails[cont - 1][0];	//get coordinates from array 2-dim
		int col = positionTails[cont - 1][1];
		
		// If Tail is on the outer square
		if(row == 0){
			switch(cont){
			case 1:
				if(sideFreeTail(row + 1, col) || sideFreeTail(row, col + 1) || sideFreeTail(row, col - 1)) {
					if(this.board[row + 1][col] != Tail.E || this.board[row][col + 1] != Tail.E || this.board[row][col - 1] != Tail.E){
						return true;
					}
				}	
				break;
			case 2:
				if(align == 0){	//horizontal
					if(sideFreeTail(row, col + 1) || sideFreeTail(row, col - 1)) {
						if(this.board[row][col + 1] != Tail.E || this.board[row][col - 1] != Tail.E){
							return true;
						}
					}		
				}
				if(align == 1){	//vertical
					if(sideFreeTail(row + 1, col)) {
						if(this.board[row + 1][col] != Tail.E ){
							return true;
						}
					}
				}
				break;
		}
		return false;
		}
		if(row == this.ROW - 1){
			switch(cont){
			case 1:
				if(sideFreeTail(row - 1, col) || sideFreeTail(row, col + 1) || sideFreeTail(row, col - 1)) {
					if(this.board[row - 1][col] != Tail.E || this.board[row][col + 1] != Tail.E || this.board[row][col - 1] != Tail.E){
						return true;
					}
				}	
				break;
			case 2:
				if(align == 0){	//horizontal
					if(sideFreeTail(row, col - 1)) {
						if(this.board[row][col + 1] != Tail.E || this.board[row][col - 1] != Tail.E){
							return true;
						}
					}		
				}
				if(align == 1){	//vertical
					if(sideFreeTail(row - 1, col)) {
						if(this.board[row - 1][col] != Tail.E){
							return true;
						}
					}
				}
				break;
		}
		return false;
		}
		if(col == 0){
			switch(cont){
			case 1: // ha preso 1 tail
				if(sideFreeTail(row + 1, col) || sideFreeTail(row - 1, col) || sideFreeTail(row, col + 1)) {
					if(this.board[row + 1][col] != Tail.E || this.board[row - 1][col] != Tail.E || this.board[row][col + 1] != Tail.E || this.board[row][col - 1] != Tail.E){
						return true;
					}
				}	
				break;
			case 2:	// ha preso 2 tail
				if(align == 0){	//horizontal
					if(sideFreeTail(row, col + 1)) {
						if(this.board[row][col + 1] != Tail.E){
							return true;
						}
					}		
				}
				if(align == 1){	//vertical
					if(sideFreeTail(row + 1, col) || sideFreeTail(row - 1, col)) {
						if(this.board[row + 1][col] != Tail.E || this.board[row - 1][col] != Tail.E){
							return true;
						}
					}
				}
				break;
		}
		return false;
		}
		if(col == this.COL - 1){
			switch(cont){
			case 1:
				if(sideFreeTail(row + 1, col) || sideFreeTail(row - 1, col) || sideFreeTail(row, col - 1)) {
					if(this.board[row + 1][col] != Tail.E || this.board[row - 1][col] != Tail.E || this.board[row][col - 1] != Tail.E){
						return true;
					}
				}	
				break;
			case 2:
				if(align == 0){	//horizontal
					if(sideFreeTail(row, col + 1) || sideFreeTail(row, col - 1)) {
						if(this.board[row][col - 1] != Tail.E){
							return true;
						}
					}		
				}
				if(align == 1){	//vertical
					if(sideFreeTail(row + 1, col) || sideFreeTail(row - 1, col)) {
						if(this.board[row + 1][col] != Tail.E || this.board[row - 1][col] != Tail.E){
							return true;
						}
					}
				}
				break;
		}
		return false;
		}
		
		switch(cont){
			case 1:
				if(sideFreeTail(row + 1, col) || sideFreeTail(row - 1, col) || sideFreeTail(row, col + 1) || sideFreeTail(row, col - 1)) {
					if(this.board[row + 1][col] != Tail.E || this.board[row - 1][col] != Tail.E || this.board[row][col + 1] != Tail.E || this.board[row][col - 1] != Tail.E){
						return true;
					}
				}	
				break;
			case 2:
				if(align == 0){	//horizontal
					if(sideFreeTail(row, col + 1) || sideFreeTail(row, col - 1)) {
						if(this.board[row][col + 1] != Tail.E || this.board[row][col - 1] != Tail.E){
							return true;
						}
					}		
				}
				if(align == 1){	//vertical
					if(sideFreeTail(row + 1, col) || sideFreeTail(row - 1, col)) {
						if(this.board[row + 1][col] != Tail.E || this.board[row - 1][col] != Tail.E){
							return true;
						}
					}
				}
				break;
		}
		return false;
	}

	public void emptyTheBoard(int matrix[][]){
		int row = matrix.length;	// How many tails the user selected
		int r = 0;
		int c = 0;
		
		for(int i = 0; i<row; i++){
			r = matrix[i][0];
			c = matrix[i][1];
			
			board[r][c] = Tail.E;
		}
	}
	
	public void endBoard() { // Check if board is filled with 1 tails
		for (int i = 0; i < this.ROW; i++) {
			for (int j = 0; j < this.COL; j++) {
				if (board[i][j] != Tail.E) {
					if (!sideFreeTail(i, j)) {
						return;
					}
				}
			}
		}
		refillBoard(board);
	}

	public void refillBoard(Tail board[][]) {
		int[][] notFillable;
		if (nPlayers == 2) {
			notFillable = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 },
					{ 0, 8 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 2, 0 }, { 2, 1 },
					{ 2, 2 }, { 2, 6 }, { 2, 7 }, { 2, 8 }, { 3, 0 }, { 3, 1 }, { 3, 8 }, { 4, 0 }, { 4, 8 }, { 5, 0 },
					{ 5, 7 }, { 5, 8 }, { 6, 0 }, { 6, 1 }, { 6, 2 }, { 6, 6 }, { 6, 7 }, { 6, 8 }, { 7, 0 }, { 7, 1 },
					{ 7, 2 }, { 7, 3 }, { 7, 6 }, { 7, 7 }, { 7, 8 }, { 8, 0 }, { 8, 1 }, { 8, 2 }, { 8, 3 }, { 8, 4 },
					{ 8, 5 }, { 8, 6 }, { 8, 7 }, { 8, 8 } };
		} else if (nPlayers == 3) {
			notFillable = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 },
					{ 1, 0 }, { 1, 1 }, { 1, 2 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 2, 0 }, { 2, 1 }, { 2, 7 },
					{ 2, 8 }, { 3, 0 }, { 3, 1 }, { 4, 0 }, { 4, 8 }, { 5, 7 }, { 5, 8 }, { 6, 0 }, { 6, 1 }, { 6, 7 },
					{ 6, 8 }, { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 6 }, { 7, 7 }, { 7, 8 }, { 8, 0 }, { 8, 1 },
					{ 8, 2 }, { 8, 3 }, { 8, 4 }, { 8, 6 }, { 8, 7 }, { 8, 8 } };
		} else {
			notFillable = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 }, { 1, 0 },
					{ 1, 1 }, { 1, 2 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 2, 0 }, { 2, 1 }, { 2, 7 }, { 2, 8 }, { 3, 0 },
					{ 5, 8 }, { 6, 0 }, { 6, 1 }, { 6, 7 }, { 6, 8 }, { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 6 }, { 7, 7 },
					{ 7, 8 }, { 8, 0 }, { 8, 1 }, { 8, 2 }, { 8, 3 }, { 8, 6 }, { 8, 7 }, { 8, 8 } };

		}

		boolean[][] fillable = new boolean[ROW][COL];

		// set false cells you can't fill
		for (int i = 0; i < notFillable.length; i++) {
			fillable[(notFillable[i][0])][(notFillable[i][1])] = true;
		}

		/*
		 * Takes random Tails from ENUM Tail (EMPTY(E) is excluded) and fills the board,
		 * and counts in array tailIndex how many tails have been filled with that type
		 */

		Random random = new Random();
		for (int i = 0; i < this.ROW; i++) {
			for (int j = 0; j < this.COL; j++) {
				// int tailIndex = ThreadLocalRandom.current().nextInt(min, max + 1); -- >
				// casual numbers from 2 to 7 (2 and 7 included)
				int tailIndex = ThreadLocalRandom.current().nextInt(2, 7 + 1);

				Tail tail = Tail.values()[tailIndex];

				// If is fillable and there are not any tails in that cell -> fill it
				if (fillable[i][j] == false && board[i][j] != Tail.E && tail != Tail.E && this.tailCount[tailIndex] < 22) { 
					this.board[i][j] = tail;
					this.tailCount[tailIndex]++;
				}
				if (fillable[i][j] == true) { // If not fillable -> Set Empy
					this.board[i][j] = Tail.E;
				}
			}
		}
	}

	public void fillBoard(Tail board[][]) {
		int[][] notFillable;
		if (nPlayers == 2) {
			notFillable = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 },
					{ 0, 8 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 2, 0 }, { 2, 1 },
					{ 2, 2 }, { 2, 6 }, { 2, 7 }, { 2, 8 }, { 3, 0 }, { 3, 1 }, { 3, 8 }, { 4, 0 }, { 4, 8 }, { 5, 0 },
					{ 5, 7 }, { 5, 8 }, { 6, 0 }, { 6, 1 }, { 6, 2 }, { 6, 6 }, { 6, 7 }, { 6, 8 }, { 7, 0 }, { 7, 1 },
					{ 7, 2 }, { 7, 3 }, { 7, 6 }, { 7, 7 }, { 7, 8 }, { 8, 0 }, { 8, 1 }, { 8, 2 }, { 8, 3 }, { 8, 4 },
					{ 8, 5 }, { 8, 6 }, { 8, 7 }, { 8, 8 } };
		} else if (nPlayers == 3) {
			notFillable = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 },
					{ 1, 0 }, { 1, 1 }, { 1, 2 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 2, 0 }, { 2, 1 }, { 2, 7 },
					{ 2, 8 }, { 3, 0 }, { 3, 1 }, { 4, 0 }, { 4, 8 }, { 5, 7 }, { 5, 8 }, { 6, 0 }, { 6, 1 }, { 6, 7 },
					{ 6, 8 }, { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 6 }, { 7, 7 }, { 7, 8 }, { 8, 0 }, { 8, 1 },
					{ 8, 2 }, { 8, 3 }, { 8, 4 }, { 8, 6 }, { 8, 7 }, { 8, 8 } };
		} else {
			notFillable = new int[][] { /* TODO { 0, 0 }, { 0, 1 }, { 0, 2 }, */ { 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 }, { 1, 0 },
					{ 1, 1 }, { 1, 2 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 2, 0 }, { 2, 1 }, { 2, 7 }, { 2, 8 }, { 3, 0 },
					{ 5, 8 }, { 6, 0 }, { 6, 1 }, { 6, 7 }, { 6, 8 }, { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 6 }, { 7, 7 },
					{ 7, 8 }, { 8, 0 }, { 8, 1 }, { 8, 2 }, { 8, 3 }, { 8, 6 }, { 8, 7 }, { 8, 8 } };

		}

		boolean[][] fillable = new boolean[ROW][COL];

		// set false cells you can't fill
		for (int i = 0; i < notFillable.length; i++) {
			fillable[(notFillable[i][0])][(notFillable[i][1])] = true;
		}

		/*
		 * Takes random Tails from ENUM Tail (EMPTY(E) is excluded) and fills the board,
		 * and counts in array tailIndex how many tails have been filled with that type
		 */
		Random random = new Random();
		for (int i = 0; i < this.ROW; i++) {
			for (int j = 0; j < this.COL; j++) {
				// int tailIndex = ThreadLocalRandom.current().nextInt(min, max + 1); -- >
				// casual numbers from 2 to 7 (2 and 7 included)
				int tailIndex = ThreadLocalRandom.current().nextInt(2, 7 + 1);
				Tail tail = Tail.values()[tailIndex];

				if (tail != Tail.E && this.tailCount[tailIndex] < 22) { // If fillable and count < 22 -> Fill
					this.board[i][j] = tail;
					this.tailCount[tailIndex]++;
				}
				if (fillable[i][j] == true) { // If not fillable -> Set Empy
					this.board[i][j] = Tail.E;
				}

			}
		}
	}

	public void printBoard() {
		System.out.println("\n---STATUS BOARD: \n");
		Matrix.printMatrix(board, ROW, COL);
	}

	public boolean checkNPlayers(int nPlayers) throws IllegalArgumentException {
		if (nPlayers < 2 || nPlayers > 4) {
			throw new IllegalArgumentException("Number of player has to be 2, 3 or 4.");
		}
		return true;
	}

}
