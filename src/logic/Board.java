package logic;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Board implements Matrix {
	private int nPlayers;

	private int row = 9;
	private int col = 9;

	private int rowTemp;
	private int colTemp;
	private int align; // 0 is horizontal, 1 is vertical

	private Tail[][] board;
	private int[] tailCount;

	public Board(int nPlayers) {
		this.nPlayers = nPlayers;

		if (checkNPlayers(nPlayers)) {
			this.board = new Tail[row][col];
			this.tailCount = new int[Tail.values().length];
			fillBoard(board);
		} else {
			System.exit(0);
		}
	}
	
	//TODO sistemare i controlli, se si sceglie una tail alla prima/ultima riga/colonna allora
		//i controlli attuali generano array index of bound (inserire controllo se questo caso allora salta controllo side free ecc...)
	public Tail selectTails(int row, int col, int index) {
		Tail tail;
		if (index == 0) {
			if (sideFreeTail(row, col) == true && this.board[row][col] != Tail.E) {
				tail = this.board[row][col];
				this.board[row][col] = Tail.E;
				this.rowTemp = row;
				this.colTemp = col;
			} else {
				tail = Tail.E;
				return tail;
			}
		} else {
			if (sideFreeTail(row, col) == true && adjacentTail(row, col, this.rowTemp, this.colTemp) == true
					&& this.board[row][col] != Tail.E) {
				if (index == 3) {
					if (controlAlign(row, col) == true) {
						tail = this.board[row][col];
						this.board[row][col] = Tail.E;
						this.rowTemp = row;
						this.colTemp = col;
					} else {
						tail = Tail.E;
						return tail;
					}
				} else {
					tail = this.board[row][col];
					this.board[row][col] = Tail.E;
					this.rowTemp = row;
					this.colTemp = col;
				}
			} else {
				tail = Tail.E;
				return tail;
			}
		}

		return tail;
	}

	public boolean sideFreeTail(int row, int col) {// TODO non so se è completo come controllo, forse si deve accettare
													// anche == null?
		if (this.board[row - 1][col] == Tail.E || this.board[row + 1][col] == Tail.E
				|| this.board[row][col - 1] == Tail.E || this.board[row][col + 1] == Tail.E) {
			return true;
		}
		return false;
	}

	public boolean adjacentTail(int row1, int col1, int row2, int col2) {
		if (row1 != row2 && col1 == col2) {
			if (row1 - row2 == 1 || row2 - row1 == 1) {
				this.align = 1; // vertical
				return true;
			}
		}
		if (col1 != col2 && row1 == row2) {
			if (col1 - col2 == 1 || col2 - col1 == 1) {
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
			notFillable = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 }, { 1, 0 },
					{ 1, 1 }, { 1, 2 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 2, 0 }, { 2, 1 }, { 2, 7 }, { 2, 8 }, { 3, 0 },
					{ 5, 8 }, { 6, 0 }, { 6, 1 }, { 6, 7 }, { 6, 8 }, { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 6 }, { 7, 7 },
					{ 7, 8 }, { 8, 0 }, { 8, 1 }, { 8, 2 }, { 8, 3 }, { 8, 6 }, { 8, 7 }, { 8, 8 } };

		}

		boolean[][] fillable = new boolean[row][col];
		// set true cells you can't fill
		for (int i = 0; i < notFillable.length; i++) {
			fillable[(notFillable[i][0])][(notFillable[i][1])] = true;
		}

		/*
		 * Takes random Tails from ENUM Tail (EMPTY(E) is excluded) and fills the board,
		 * and counts in array tailIndex how many tails have been filled with that type
		 */
		Random random = new Random();
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				// int tailIndex = ThreadLocalRandom.current().nextInt(min, max + 1); -- >
				// Numeri casuali da 2 a 7 (compresi)
				int tailIndex = ThreadLocalRandom.current().nextInt(2, 7 + 1);

				Tail tail = Tail.values()[tailIndex];
				if (tail != Tail.E && this.tailCount[tailIndex] < 22) {
					this.board[i][j] = tail;
					this.tailCount[tailIndex]++;
				}
				if (fillable[i][j] == true) {
					this.board[i][j] = Tail.E;
				}
			}
		}
	}

	public void printBoard() {
		Matrix.printMatrix(board, row, col);
	}

	public boolean checkNPlayers(int nPlayers) {
		if (nPlayers < 2 || nPlayers > 4) {
			System.out.println("ERROR! Invalid number of players: (" + this.nPlayers
					+ "). Number of player must be between 2 and 4.\n");
			return false;
		}
		return true;
	}

}
