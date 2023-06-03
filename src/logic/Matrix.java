package logic;

import java.util.*;

// Three classes inherit from Matrix: Board, Bookshelf and PersonalGoalCard
public interface Matrix {

	/*
	 * CAT -> GEEN BOOK -> WHITE GAME -> YELLOW FRAME -> BLUE TROPHEY -> CYAN PLANT
	 * -> RED
	 */

	// Print matrix for common goals
	static void printMatrixSimple(Tail matrix[][], int row, int col) {
		System.out.print("\n        ");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == Tail.E || matrix[i][j] == Tail.X) {
					System.out.print(Color.BLACK);
					System.out.print("#");
					System.out.print(Color.RESET);
				}
				if (matrix[i][j] == Tail.S) {
					System.out.print(Color.GREEN);
					System.out.print("S");
					System.out.print(Color.RESET);
				}
				if (matrix[i][j] == Tail.D) {
					Random random = new Random();
					int rand = random.nextInt(6) + 1; // Random number between 1 and 6 (inclusive),
					switch (rand) {
					case 1:
						System.out.print(Color.GREEN);
						break;
					case 2:
						System.out.print(Color.BLUE);
						break;
					case 3:
						System.out.print(Color.RED);
						break;
					case 4:
						System.out.print(Color.YELLOW);
						break;
					case 5:
						System.out.print(Color.WHITE);
						break;
					case 6:
						System.out.print(Color.CYAN);
						break;
					}
					System.out.print("D");
					System.out.print(Color.RESET);
				}
				System.out.print(" ");
			}
			System.out.print("\n        ");
		}
		System.out.print("\n");
	}

	// Print matrix (Board/shelf/etc.)
	static void printMatrix(Tail matrix[][], int row, int col) {
		System.out.println();
		for (int a = 0; a <= col; a++) {
			if (a == 0)
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
				if (matrix[i][j] == Tail.C) {
					System.out.print(Color.GREEN);
					System.out.print("C");
					System.out.print(Color.RESET);
				}
				if (matrix[i][j] == Tail.B) {
					System.out.print(Color.WHITE);
					System.out.print("B");
					System.out.print(Color.RESET);
				}
				if (matrix[i][j] == Tail.G) {
					System.out.print(Color.YELLOW);
					System.out.print("G");
					System.out.print(Color.RESET);
				}
				if (matrix[i][j] == Tail.F) {
					System.out.print(Color.BLUE);
					System.out.print("F");
					System.out.print(Color.RESET);
				}
				if (matrix[i][j] == Tail.T) {
					System.out.print(Color.CYAN);
					System.out.print("T");
					System.out.print(Color.RESET);
				}
				if (matrix[i][j] == Tail.P) {
					System.out.print(Color.RED);
					System.out.print("P");
					System.out.print(Color.RESET);
				}
				if (matrix[i][j] == Tail.E || matrix[i][j] == Tail.X) {
					System.out.print(Color.BLACK);
					System.out.print("#");
					System.out.print(Color.RESET);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	// @Return if there is at least one Empty element in the selected row
	static boolean EinRow(Tail matrix[][], int row) {
		int nCols = matrix[0].length;
		for (int j = 0; j < nCols; j++) {
			if (matrix[row][j].equals(Tail.E) || matrix[row][j] == (Tail.X)) {
				return true;
			}
		}
		return false;
	}

	// @Return if there is at least one Empty element in the selected column
	static boolean EinCol(Tail matrix[][], int col) {
		int nRows = matrix.length;
		for (int i = 0; i < nRows; i++) {
			if (matrix[i][col] == (Tail.E) || matrix[i][col] == (Tail.X)) {
				return true;
			}
		}
		return false;
	}

	// Fill the matrix with only Tail.E tails
	static Tail[][] FillWithE(Tail matrix[][], int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				matrix[i][j] = Tail.E;
			}
		}
		return matrix;
	}

	// @return the number of EMPTY elements in every column in array emptyRowsInCols
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

	// @Return the number of Empty elements in the selected column
	static int countEinCol(Tail matrix[][], int col) {
		int nRows = matrix.length;
		int count = 0;
		for (int i = 0; i < nRows; i++) {
			if (matrix[i][col] == Tail.E || matrix[i][col] == Tail.X) {
				count++;
			}
		}
		return count;
	}

}
