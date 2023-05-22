package logic;
//matrix
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
		for(int a = 0; a <= col; a++) {
			if(a==0) 
				System.out.print("/ ");
			else 
				System.out.print(a + " ");
		}
		System.out.println();
		char indiceLettera = 'a';
		
		for (int i = 0; i < row; i++) {
			System.out.print(indiceLettera + " ");
			indiceLettera++;
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

	// TODO - Needed function? 
	static boolean[] checkOrthogonally(Tail matrix[][], int row, int col) {
		
		/*
	
	    boolean[] same = new boolean[4];
	
	    // Check right
	    if (col + 1 < matrix[0].length && matrix[row][col] == matrix[row][col + 1]) {
	        same[0] = true;
	    } else {
	        same[0] = false;
	    }
	
	    // Check down
	    if (row + 1 < matrix.length && matrix[row][col] == matrix[row + 1][col]) {
	        same[1] = true;
	    } else {
	        same[1] = false;
	    }
	
	    // Check left
	    if (col - 1 >= 0 && matrix[row][col] == matrix[row][col - 1]) {
	        same[2] = true;
	    } else {
	        same[2] = false;
	    }
	
	    // Check up
	    if (row - 1 >= 0 && matrix[row][col] == matrix[row - 1][col]) {
	        same[3] = true;
	    } else {
	        same[3] = false;
	    }
	
	    return same;
		
		}
		 * 
		 * */
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

	// return true if an array consists of different values
	static boolean differentArray(Tail tails[], int length) { 
		for (int i = 0; i < length; i++) {
			for (int n = 0; n < length; n++) {
				if (tails[i].values().equals(tails[n].values())) {
					return false;
				}
			}
		}
		return true;
	}

	static boolean EinCol(Tail matrix[][], int col) {
		int nRows = matrix.length;
		int nCols = matrix[0].length;
		
		for (int i = 0; i < nRows; i++) {
			if (matrix[i][col].equals(Tail.E)) {
				return true;
			}
		}
		return false;
	}

	static boolean EinRow(Tail matrix[][], int row) {
		int nRows = matrix.length;
		int nCols = matrix[0].length;
		
		for (int j = 0; j < nCols; j++) {
			if (matrix[row][j].equals(Tail.E)) {
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
	
	/*
	 * @return the number of 'E' in every column in array emptyRowsInCols
	 * */
	static int[] controlEmptyRowsInCols(Tail matrix[][], int row, int col) { 
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
		int nRows = matrix.length;
		int nCols = matrix[0].length;
		
		int count = 0;
		for (int i = 0; i < nCols; i++) {
			if (matrix[i][col] == Tail.E) {
				count++;
			}
		}
		return count;
	}
}
