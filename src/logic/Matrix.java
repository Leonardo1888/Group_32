package logic;

public interface Matrix {

	/*
	 * public Tail[][] getMatrix() { return matrix; }
	 * 
	 * public int getRow() { return this.row; }
	 * 
	 * public int getCol() { return this.col; }
	 */

	static void printMatrix(Tail matrix[][], int row, int col) {
		System.out.println();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	static Tail[][] copyMatrix(Tail matrix[][], int row, int col) {
		Tail[][] mirrorMatrix = new Tail[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; i < col; i++) {
				mirrorMatrix[i][j] = matrix[i][j];
			}
		}
		return mirrorMatrix;
	}

	static boolean[] checkOrthogonally(Tail matrix[][], int row, int col) {
		boolean[] same = new boolean[4];
		if (matrix[row][col] == matrix[row + 1][col]) {
			same[0] = true;
		} else {
			same[0] = false;
		}
		if (matrix[row][col] == matrix[row][col + 1]) {
			same[1] = true;
		} else {
			same[1] = false;
		}
		if (matrix[row][col] == matrix[row - 1][col]) {
			same[2] = true;
		} else {
			same[2] = false;
		}
		if (matrix[row][col] == matrix[row][col - 1]) {
			same[3] = true;
		} else {
			same[3] = false;
		}

		return same;
	}

	static boolean differentArray(Tail tails[], int length) { // return true if an array consists of different values
		for (int i = 0; i < length; i++) {
			for (int n = 0; n < length; n++) {
				if (tails[i].values().equals(tails[n].values())) {
					return false;
				}
			}
		}
		return true;
	}

	static boolean EinCol(Tail matrix[][], int ncol) {
		for (int i = 0; i < 6; i++) {
			if (matrix[i][ncol].equals(Tail.E)) {
				return true;
			}
		}
		return false;
	}

	static boolean EinRow(Tail matrix[][], int nrow) {
		for (int j = 0; j < 5; j++) {
			if (matrix[j][nrow].equals(Tail.E)) {
				return true;
			}
		}
		return false;
	}

	static Tail[][] FillWithE(Tail matrix[][], int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Tail.E;
			}
		}
		return matrix;
	}

	static int[] controlEmptyRowsInCols(Tail matrix[][], int row, int col) { // returns the number of empty
		int[] emptyRowsInCols = new int[col]; // slot of each column
		for (int j = 0; j < col; j++) {
			int cont = 0;
			for (int i = 0; i < row; i++) {
				if (matrix[i][j] == Tail.E) {
					cont++;
				}
			}
			emptyRowsInCols[j] = cont;
		}
		return emptyRowsInCols;
	}

	static int countEinCol(Tail matrix[][], int col) {
		int count = 0;
		for (int i = 0; i < 6; i++) {
			if (matrix[i][col] == Tail.E) {
				count++;
			}

		}
		return count;
	}
}
