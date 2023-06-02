package logic;

import java.lang.Math;
import java.util.Random;

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
		this.board = new Tail[ROW][COL];
		this.tailCount = new int[Tail.values().length];
		fillBoard(board);
	}

	// Check if the user can pick the Tail he selected
	public Tail selectTails(int row, int col, int index) {
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
			System.out.println("You have to pick at least one Tail!");
			return Tail.E;
		}

		Tail tail = Tail.E;

		// Index = position of tail, first, second, third tail selected in the board
		switch (index) {
		case 0:
			if (sideFreeTail(row, col)) {
				tail = this.board[row][col];
				setTemp(row, col);
			} else {
				System.out.println("\nChoose a suitable tail.");
				tail = Tail.E;
			}
			break;

		case 1:
			if (sideFreeTail(row, col) && adjacentTail(row, col, this.rowTemp, this.colTemp)) {
				tail = this.board[row][col];
				setTemp(row, col);
			} else {
				System.out.println("\nChoose a suitable tail.");
				tail = Tail.E;
			}
			break;
		case 2:
			if (sideFreeTail(row, col) && adjacentTail(row, col, this.rowTemp, this.colTemp)
					&& controlAlign(row, col)) {
				tail = this.board[row][col];
				setTemp(row, col);
			} else {
				System.out.println("\nChoose a suitable tail.");
				tail = Tail.E;
			}
			break;
		default:
			System.out.println("Errore.");
			break;
		}

		return tail;
	}

	private void setTemp(int row, int col) {
		this.rowTemp = row;
		this.colTemp = col;
	}

	// @Return check if the Tail the user selected has at least one side free (so is
	// pickable)
	public boolean sideFreeTail(int row, int col) {
		if (this.board[row][col] != Tail.E) {
			// If Tail is on the outer square
			if (row == 0 || row == (this.ROW - 1) || col == 0 || col == (this.COL - 1)) {
				return true;
			}

			if (this.board[row - 1][col] == Tail.E || this.board[row + 1][col] == Tail.E
					|| this.board[row][col - 1] == Tail.E || this.board[row][col + 1] == Tail.E) {
				return true;
			}
		}
		return false;
	}

	// Check the adjacent tail, vertically and horizontally
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

	// Return true if the second tail is aligned correctly
	private boolean controlAlign(int row, int col) {
		// If aligned horizontal check row
		if (this.align == 0 && row == this.rowTemp) {
			return true;
		}
		// If aligned vertically check column
		if (this.align == 1 && col == this.colTemp) {
			return true;
		}
		return false;
	}

	/*
	 * Check if the tail is in the outer square of the board
	 * 
	 * @Return: 0 if not 1 if upper 2 if left 3 if bottom 4 if right
	 */
	private int isInTheOuterSquare(int row, int col) {
		if (row == 0) {
			return 1;
		}
		if (col == 0) {
			return 2;
		}
		if (row == this.ROW - 1) {
			return 3;
		}
		if (col == this.COL - 1) {
			return 4;
		}

		return 0;
	}

	/*
	 * Check if user can pick another tail on the same column/row Case:0 didn't pick
	 * any tails, Case:1 Picked 1 tail, Case:2 Picked 2 tails.
	 */
	public boolean checkFreeSpaces(int positionTails[][], int cont) {

		// picked not suitable tail
		if (cont == 0) {
			System.out.println("Pick another tail");
			return true;
		}

		// picked 1 tail
		if (cont == 1) {
			int row = positionTails[0][0]; // get coordinates from array 2-dim
			int col = positionTails[0][1];

			int outer = isInTheOuterSquare(row, col);
			switch (outer) {
			case 0:
				break;
			case 1:
				return upper1(row, col);
			case 2:
				return left1(row, col);
			case 3:
				return bottom1(row, col);
			case 4:
				return right1(row, col);
			}

			if (sideFreeTail(row + 1, col) || sideFreeTail(row - 1, col) || sideFreeTail(row, col + 1)
					|| sideFreeTail(row, col - 1)) {
				if (this.board[row + 1][col] != Tail.E || this.board[row - 1][col] != Tail.E
						|| this.board[row][col + 1] != Tail.E || this.board[row][col - 1] != Tail.E) {
					return true;
				}
			}
		}

		// picked 2 tails
		if (cont == 2) {
			int row1 = positionTails[0][0]; // get 1st coordinates from array 2-dim
			int col1 = positionTails[0][1];

			int row2 = positionTails[1][0]; // get 2nd coordinates from array 2-dim
			int col2 = positionTails[1][1];

			int outer1 = isInTheOuterSquare(row1, col1); // 1 -> true 0 -> false
			int outer2 = isInTheOuterSquare(row2, col2);

			// if both are in the Outer Square there are no more cases in which the user can
			// pick a 3rd tail
			if (outer1 != 0 && outer1 == outer2) {
				return false;
			}

			int addR = addR(row1, row2); // used to find the tail we need to know if is pickable
			int addC = addC(col1, col2);

			switch (outer1) {
			case 0:
				break;
			case 1:
				return upper2(row1, col1, addR);
			case 2:
				return left2(row1, col1, addC);
			case 3:
				return bottom2(row1, col1, addR);
			case 4:
				return right2(row1, col1, addC);
			}

			addR = addR(row2, row2);
			addC = addC(col2, col1);

			switch (outer2) {
			case 0:
				break;
			case 1:
				return upper2(row2, col2, addR);
			case 2:
				return left2(row2, col2, addC);
			case 3:
				return bottom2(row2, col2, addR);
			case 4:
				return right2(row2, col2, addC);
			}

			// from here 2 tails not in outer
			if (align == 0) { // horizontal -> - 1 2 -
				if (col1 < col2) {// From left to right
					if (sideFreeTail(row1, col1 - 1) || sideFreeTail(row2, col2 + 1)) {
						if (this.board[row1][col1 - 1] != Tail.E || this.board[row2][col2 + 1] != Tail.E) {
							return true;
						}
					}
					return false;
				}
				if (col1 > col2) {// From right to left
					if (sideFreeTail(row2, col2 - 1) || sideFreeTail(row1, col1 + 1)) {
						if (this.board[row2][col2 - 1] != Tail.E || this.board[row1][col1 + 1] != Tail.E) {
							return true;
						}
					}
					return false;
				}
			}

			if (align == 1) { // Vertical
				if (row1 < row2) {// From up to down
					if (sideFreeTail(row1 - 1, col1) || sideFreeTail(row2 + 1, col2)) {
						if (this.board[row1 - 1][col1] != Tail.E || this.board[row2 + 1][col2] != Tail.E) {
							return true;
						}
					}
					return false;
				}
				if (row1 > row2) {// From down to up
					if (sideFreeTail(row2 - 1, col2) || sideFreeTail(row1 + 1, col1)) {
						if (this.board[row2 - 1][col2] != Tail.E || this.board[row1 + 1][col1] != Tail.E) {
							return true;
						}
					}
					return false;
				}
			}
		}
		return false;
	}

	// used for tail in outer square with 2 picked tails
	private int addR(int row1, int row2) {
		if (row1 < row2) {
			return 2;
		} else {
			return 1;
		}
	}

	// used for tail in outer square with 2 picked tails
	private int addC(int col1, int col2) {
		if (col1 < col2) {
			return 2;
		} else {
			return 1;
		}
	}

	// Empty the board
	public void emptyTheBoard(int matrix[][]) {
		int row = matrix.length; // How many tails the user selected
		int r = 0;
		int c = 0;

		for (int i = 0; i < row; i++) {
			r = matrix[i][0];
			c = matrix[i][1];

			board[r][c] = Tail.E;
		}
	}

	// Check if board is filled with 1 single tails
	// If the user can only pick one tail for his turn, refill the board
	/*
	 * int rows = matrix.length; int cols = matrix[0].length;
	 * 
	 * for (int i = 0; i < rows; i++) { for (int j = 0; j < cols; j++) { if
	 * (matrix[i][j] != 0 || !hasFourAdjacentZeroes(matrix, i, j)) { return false; }
	 * }
	 */

	public void checkEndBoard() {
	    for (int i = 0; i < this.ROW; i++) {
	        for (int j = 0; j < this.COL; j++) {
	            if (this.board[i][j] != Tail.E && !hasFourAdjacentE(i, j)) {
	                return; // Exit the method as soon as a non-empty cell without four adjacent empty cells is found
	            }
	        }
	    }
	    // If we reach here, it means all cells passed the condition
	    System.out.println("------------ Board was refilled ------------");
	    refillBoard(board);
	}

	private boolean hasFourAdjacentE(int row, int col) {
	    // Check top, bottom, left, and right cells
	    if (row > 0 && board[row - 1][col] != Tail.E) {
	        return false;
	    }
	    if (row < ROW - 1 && board[row + 1][col] != Tail.E) {
	        return false;
	    }
	    if (col > 0 && board[row][col - 1] != Tail.E) {
	        return false;
	    }
	    if (col < COL - 1 && board[row][col + 1] != Tail.E) {
	        return false;
	    }

	    return true;
	}



	/*
	 * Refills the board with new tails, keeping the old ones that weren't selected
	 * on the same spot. This function doesn't override old tails.
	 */
	private void refillBoard(Tail board[][]) {
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
		// Fill the matrix with true elements
		for (int i = 0; i < this.ROW; i++) {
			for (int j = 0; j < this.COL; j++) {
				fillable[i][j] = true;
			}
		}

		// set false cells you can't fill
		for (int i = 0; i < notFillable.length; i++) {
			fillable[(notFillable[i][0])][(notFillable[i][1])] = false;
		}

		/*
		 * Takes random Tails from ENUM Tail (EMPTY(E) is excluded) and fills the board,
		 * and counts in array tailIndex how many tails have been filled with that type
		 */
		Random random = new Random();

		int min = 2;
		int max = 7;

		for (int i = 0; i < this.ROW; i++) {
			for (int j = 0; j < this.COL; j++) {
				int tailIndex = random.nextInt(max - min + 1) + min;
				Tail tail = Tail.values()[tailIndex];

				// If is fillable and there are not any tails in that cell -> fill it
				if (fillable[i][j] == true && tail != Tail.E && this.tailCount[tailIndex] < 22) {
					if (board[i][j] != Tail.C && board[i][j] != Tail.B && board[i][j] != Tail.G && board[i][j] != Tail.F
							&& board[i][j] != Tail.T && board[i][j] != Tail.P) {
						this.board[i][j] = tail;
						this.tailCount[tailIndex]++;
					}
				}
				if (fillable[i][j] == true) { // If not fillable -> Set Empy
					this.board[i][j] = Tail.E;
				}
			}
		}
	}

	// Fill board randomly at the start of the game
	private void fillBoard(Tail board[][]) {
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

		int min = 2;
		int max = 7;

		for (int i = 0; i < this.ROW; i++) {
			for (int j = 0; j < this.COL; j++) {
				int tailIndex = random.nextInt(max - min + 1) + min;
				Tail tail = Tail.values()[tailIndex];

				if (tail != Tail.E && this.tailCount[tailIndex] < 22) { // If fillable and count < 22 -> Fill
					this.board[i][j] = tail;
					this.tailCount[tailIndex]++;
				}
				if (fillable[i][j] == true) { // If not fillable -> Set Empty
					this.board[i][j] = Tail.E;
				}

			}
		}
	}

	// Output of the bord.
	public void printBoard() {
		System.out.println("\n---STATUS BOARD: ");
		Matrix.printMatrix(board, ROW, COL);
	}

	// Print a single row of the Board, used in 'printBoardAndShelfAndPgc()' in Turn
	public void printRowBoard(int row, char indexChar) {
		if (row == -1) {
			System.out.println();
			for (int a = 0; a <= this.COL; a++) {
				if (a == 0)
					System.out.print("/ ");
				else
					System.out.print(a + " ");
			}
		} else {
			System.out.print(indexChar + " ");
			for (int j = 0; j < this.COL; j++) {
				if (board[row][j] == Tail.C) {
					System.out.print(Color.GREEN);
					System.out.print("C");
					System.out.print(Color.RESET);
				}
				if (board[row][j] == Tail.B) {
					System.out.print(Color.WHITE);
					System.out.print("B");
					System.out.print(Color.RESET);
				}
				if (board[row][j] == Tail.G) {
					System.out.print(Color.YELLOW);
					System.out.print("G");
					System.out.print(Color.RESET);
				}
				if (board[row][j] == Tail.F) {
					System.out.print(Color.BLUE);
					System.out.print("F");
					System.out.print(Color.RESET);
				}
				if (board[row][j] == Tail.T) {
					System.out.print(Color.CYAN);
					System.out.print("T");
					System.out.print(Color.RESET);
				}
				if (board[row][j] == Tail.P) {
					System.out.print(Color.RED);
					System.out.print("P");
					System.out.print(Color.RESET);
				}
				if (board[row][j] == Tail.E || board[row][j] == Tail.X) {
					System.out.print(Color.BLACK);
					System.out.print("#");
					System.out.print(Color.RESET);
				}
				System.out.print(" ");
			}
		}
	}

	/*
	 * ----------- Each case is used when only one Tail picked is in the outer
	 * square (to avoid index out of bound exception)
	 */
	private boolean upper1(int row, int col) {
		if (sideFreeTail(row + 1, col) || sideFreeTail(row, col + 1) || sideFreeTail(row, col - 1)) {
			if (this.board[row + 1][col] != Tail.E || this.board[row][col + 1] != Tail.E
					|| this.board[row][col - 1] != Tail.E) {
				return true;
			}
		}
		return false;
	}

	private boolean left1(int row, int col) {
		if (sideFreeTail(row + 1, col) || sideFreeTail(row - 1, col) || sideFreeTail(row, col + 1)) {
			if (this.board[row + 1][col] != Tail.E || this.board[row - 1][col] != Tail.E
					|| this.board[row][col + 1] != Tail.E) {
				return true;
			}
		}
		return false;
	}

	private boolean bottom1(int row, int col) {
		if (sideFreeTail(row - 1, col) || sideFreeTail(row, col + 1) || sideFreeTail(row, col - 1)) {
			if (this.board[row - 1][col] != Tail.E || this.board[row][col + 1] != Tail.E
					|| this.board[row][col - 1] != Tail.E) {
				return true;
			}
		}
		return false;
	}

	private boolean right1(int row, int col) {
		if (sideFreeTail(row + 1, col) || sideFreeTail(row - 1, col) || sideFreeTail(row, col - 1)) {
			if (this.board[row + 1][col] != Tail.E || this.board[row - 1][col] != Tail.E
					|| this.board[row][col - 1] != Tail.E) {
				return true;
			}
		}
		return false;
	}

	/*
	 * ----- Each of the following case when 2 tail picked but only one tail is in
	 * the outer square If in the row is 0 and just one Tail is in the outer, then
	 * the align is obv vertical check
	 */
	private boolean upper2(int row, int col, int add) {
		if (sideFreeTail(row + add, col)) {
			if (this.board[row + add][col] != Tail.E) {
				return true;
			}
		}
		return false;
	}

	private boolean left2(int row, int col, int add) {
		if (sideFreeTail(row, col + add)) {
			if (this.board[row][col + add] != Tail.E) {
				return true;
			}
		}
		return false;
	}

	private boolean bottom2(int row, int col, int add) {
		if (sideFreeTail(row - add, col)) {
			if (this.board[row - add][col] != Tail.E) {
				return true;
			}
		}
		return false;
	}

	private boolean right2(int row, int col, int add) {
		if (sideFreeTail(row, col - add)) {
			if (this.board[row][col - add] != Tail.E) {
				return true;
			}
		}
		return false;
	}

	/* ---------------------- */

	/* ---------- Getter and setter ---------- */

	public Tail[][] getBoard() {
		return board;
	}

	public void setBoard(Tail[][] board) {
		this.board = board;
	}

	public int[] getTailCount() {
		return tailCount;
	}

	public void setTailCount(int[] tailCount) {
		this.tailCount = tailCount;
	}

	public int getRow() {
		return this.ROW;
	}

	public int getCol() {
		return this.COL;
	}
}
