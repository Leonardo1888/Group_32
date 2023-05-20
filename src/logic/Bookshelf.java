package logic;

import java.util.Arrays;

public class Bookshelf implements Matrix {
	private final int ROW = 6;
	private final int COL = 5;

	private Tail[][] shelf;

	public Bookshelf() {
		this.shelf = new Tail[ROW][COL];
		for (int i = 0; i < (ROW); i++) {
			for (int j = 0; j < COL; j++) {
				shelf[i][j] = Tail.E;
				if (i == (ROW - 1))
					shelf[i][j] = Tail.X;
			}
		}
	}

	public void printBoard() {
		Matrix.printMatrix(shelf, ROW, COL);
	}

	public int insertTail(Tail[] tails, int col) {
		int numTail = 3;
		for (int n = 0; n < 3; n++) {
			if (tails[n] == Tail.E) {
				numTail--;
			}
		}

		if (numTail > Matrix.countEinCol(this.shelf, col)) {
			return 1; // failure
		}

		for (int n = 0; n < numTail; n++) {
			for (int i = 0; i < this.ROW; i++) {
				if (this.shelf[i][col] == Tail.X) {
					this.shelf[i][col] = tails[n]; // insert tail
					this.shelf[i - 1][col] = Tail.X; // insert X one row up
				}
			}
		}
		return 0; // success
	}

	// @Return the largest number of free cells (for every column). If 0 -> can't
	// pick Tails
	public int checkFreeSpaces() {
		int[] count = new int[this.COL];
		for (int j = 0; j < COL; j++) {
			count[j] = Matrix.countEinCol(shelf, j);
		}
		return Arrays.stream(count).max().orElse(0);
	}

	public static String columnMsg() {
		String msg = "Choose the column (1-5): ";
		return msg;
	}
}