package logic;

public class Bookshelf implements Matrix {
	private int row = 6;
	private int col = 5;

	private Tail[][] shelf;

	public Bookshelf() {
		this.shelf = new Tail[row][col];
		for (int i = 0; i < (row); i++) {
			for (int j = 0; j < col; j++) {
				shelf[i][j] = Tail.E;
				if (i == (row - 1))
					shelf[i][j] = Tail.X;
			}
		}
	}

	public void printBoard() {
		Matrix.printMatrix(shelf, row, col);
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

		// TODO inserire controllo se la cella dove si inserisce non è Tail.X
		for (int n = 0; n < numTail; n++) {
			for (int i = 0; i < this.row; i++) {
				if (this.shelf[i][col] == Tail.X) {
					this.shelf[i][col] = tails[n]; // insert tail
					this.shelf[i - 1][col] = Tail.X; // insert X one row up
				}
			}
		}
		return 0; // success
	}

	public static String columnMsg() {
		String msg = new String();
		msg = "Choose the column (1-5): ";
		return msg;
	}
}