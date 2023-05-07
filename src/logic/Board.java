package logic;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Board implements Matrix {
	private int nPlayers;

	private int row = 9;
	private int col = 9;

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
				// int tailIndex = ThreadLocalRandom.current().nextInt(min, max + 1); -- > Numeri casuali da 2 a 7 (compresi)
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
