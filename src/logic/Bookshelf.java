package logic;

import java.util.Arrays;
import java.util.Scanner;

public class Bookshelf implements Matrix {
	private final int ROW = 6;
	private final int COL = 5;

	private Tail[][] shelf;

	public Bookshelf() {
		this.shelf = new Tail[ROW][COL];
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				shelf[i][j] = Tail.E;
				if (i == (ROW - 1))
					shelf[i][j] = Tail.X;
			}
		}
	}

	public void printShelf() {
		Matrix.printMatrix(shelf, ROW, COL);
	}

	/*
	 * The user chooses the order in which he wants to insert the tails in the
	 * shelf. From the one that goes to the bottom to the upper one.
	 */
	public Tail[] orderTailsToInsert(Scanner sc, Tail tails[], int numTails) {
		switch (numTails) {
		case 1:
			System.out.println("Your Tail:\n1°: " + tails[0]);
			Tail[] tailsToInsert1 = new Tail[1];
			tailsToInsert1[0] = tails[0];
			return tailsToInsert1;
		case 2: // If they are the same don't ask the user to order the tails
			if (tails[0] != tails[1]) {
				// Output tails the user selected
				System.out.print("Your Tails:\n1°: ");
				printTail(tails[0]);
				System.out.print("\n2°: ");
				printTail(tails[1]);

				// Output explanation
				System.out.print("\n\nChoose the order of the Tails to insert [1, " + numTails + "]. "
						+ "Write the number: '1' for the tail '");
				printTail(tails[0]);
				System.out.print("' or '2' for the tail '");
				printTail(tails[1]);
				System.out.println("'.");

				int first2 = 0;
				int second2 = 0;
				// Choose order of tails
				while (true) {
					System.out.print("\nChoose the tail that will go on the bottom: ");
					first2 = sc.nextInt();
					sc.nextLine();
					if (first2 == 1 || first2 == 2 || first2 == 3) {
						// If is 1, 2 or 3 exit the while loop
						break;
					}
					System.out.print("Error! You have to enter a number from '1' to '3'.");
				}
				
				// Don't ask for the second tail, it's obvious
				if(first2 == 2)
					second2 = 1;
				if(first2 == 1)
					second2 = 2;

				Tail[] tailsToInsert2 = new Tail[2];

				tailsToInsert2[0] = tails[first2 - 1];
				tailsToInsert2[1] = tails[second2 - 1];
				return tailsToInsert2;
			}
			break;
		case 3: // they are the same don't ask the user to order the tails
			if (tails[0] != tails[1] || tails[0] != tails[2] || tails[1] != tails[2]) {
				// Output tails the user selected
				System.out.print("Your Tails:\n1°: ");
				printTail(tails[0]);
				System.out.print("\n2°: ");
				printTail(tails[1]);
				System.out.print("\n3°: ");
				printTail(tails[2]);

				// Output explanation
				System.out.print("\n\nChoose the order of the Tails to insert [1, " + numTails + "]. "
						+ "\nWrite the number: '1' for the tail '");
				printTail(tails[0]);
				System.out.print("' or '2' for the tail '");
				printTail(tails[1]);
				System.out.print("' or '3' for the tail '");
				printTail(tails[2]);
				System.out.print("'.");

				int first3 = 0;
				int second3 = 0;
				int third3 = 0;
				// Choose order of tails
				while (true) {
					System.out.print("\nChoose the tail that will go on the bottom: ");
					first3 = sc.nextInt();
					sc.nextLine();
					if (first3 == 1 || first3 == 2 || first3 == 3) {
						// If is 1, 2 or 3 exit the while loop
						break;
					}
					System.out.print("Error! You have to enter a number from '1' to '3'.");
				}
				
				if((first3 == 1) && (tails[1] == tails[2])) {
					second3 = 2;
					third3 = 3;
				} else if((first3 == 2) && (tails[0] == tails[2])) {
					second3 = 1;
					third3 = 3;
				} else if((first3 == 3) && (tails[0] == tails[1])) {
					second3 = 1;
					third3 = 2;
				} else {
					while (true) {
						System.out.print("Choose the 2nd tail: ");
						second3 = sc.nextInt();
						sc.nextLine();
						if (second3 == 1 || second3 == 2 || second3 == 3) {
							// If is 1, 2 or 3 exit the while loop
							break;
						}
						System.out.print("Error! You have to enter a number from '1' to '3'.");
					}
				}
				
				// Don't ask for the third tail, it's obvious
				if((first3 == 1 && second3 == 2) || (first3 == 2 && second3 == 1))
					third3 = 3;
				else if((first3 == 2 && second3 == 3) || (first3 == 3 && second3 == 2))
					third3 = 1;
				else if((first3 == 1 && second3 == 3) || (first3 == 3 && second3 == 1))
					third3 = 2;
				
				Tail[] tailsToInsert3 = new Tail[3];

				tailsToInsert3[0] = tails[first3 - 1];
				tailsToInsert3[1] = tails[second3 - 1];
				tailsToInsert3[2] = tails[third3 - 1];
				return tailsToInsert3;
			}
			break;
		}
		return tails;
	}
	
	// Insert Tails in column
	// @Return 0 if success, @Return 1 if failure, @Return
	public int insertTail(Tail[] tails, int col, int numTail) {
		// if num E in selected col < num Tails
		if (numTail > Matrix.countEinCol(this.shelf, col)) {
			System.out.println("Not enough free spaces in the column...");
			return 1; // failure
		}
		// insert
		for (int n = 0; n < numTail; n++) {
			for (int i = 0; i < this.ROW; i++) {
				if (this.shelf[i][col] == Tail.X) {
					this.shelf[i][col] = tails[n]; // insert tail
					if (i == 0) { // if the row is 0 don't write X (because is the upper row)
						System.out.println("The column has been filled entirely");
					} else {
						this.shelf[i - 1][col] = Tail.X; // insert X one row up
					}
				}
			}
		}
		return 0; // success
	}

	/*
	 * START DFS algorithm will count how main tails of the same type are adjacent.
	 * call in actionOfEndGame this function, it will return the number of each
	 * group. Then you have to count the points
	 */
	public int AdjacentPoints() {
		boolean[][] visited = createVisitMatrixShelf();
		int res = 0;
		int[] groupCounts = new int[4]; // Indici: 0 = dimensione 3, 1 = dimensione 4, 2 = dimensione 5, 3 = dimensione
										// 6 e superiori

		for (int row = 0; row < this.shelf.length; row++)
			for (int col = 0; col < this.shelf[row].length; col++)
				if (!visited[row][col])
					res = Math.max(res, dfs(this.shelf, visited, this.shelf[row][col], row, col, groupCounts));

		// sistemo gli output
		int group4 = groupCounts[1] - (groupCounts[2] - groupCounts[3]);
		int group5 = groupCounts[2] - groupCounts[3];
		int group6plus = groupCounts[3];
		int group3 = groupCounts[0] - group4 - group5 - group6plus;
		// stampa il numero di gruppi per ogni dimensione
		System.out.println("Numero di gruppi di dimensione 3: " + group3);
		System.out.println("Numero di gruppi di dimensione 4: " + group4);
		System.out.println("Numero di gruppi di dimensione 5: " + group5);
		System.out.println("Numero di gruppi di dimensione 6 e superiori: " + group6plus);

		return returnPoints(group3, group4, group5, group6plus);
	}

	// Count the adjacent groups total points
	public int returnPoints(int g3, int g4, int g5, int g6) {
		int points = 0;
		for (int i = 0; i < g3; i++) {
			points += 2;
		}
		for (int i = 0; i < g4; i++) {
			points += 3;
		}
		for (int i = 0; i < g5; i++) {
			points += 5;
		}
		for (int i = 0; i < g6; i++) {
			points += 8;
		}
		return points;
	}

	// DFS algorithm for deep search
	public static int dfs(Tail[][] matrixShelf, boolean[][] visited, Tail expected, int row, int col,
			int[] groupCounts) {
		if (row < 0 || row >= matrixShelf.length)
			return 0;
		if (col < 0 || col >= matrixShelf[row].length)
			return 0;
		if (visited[row][col] || matrixShelf[row][col] != expected)
			return 0;

		visited[row][col] = true;

		int depth = 1;
		depth += dfs(matrixShelf, visited, expected, row, col - 1, groupCounts);
		depth += dfs(matrixShelf, visited, expected, row, col + 1, groupCounts);
		depth += dfs(matrixShelf, visited, expected, row - 1, col, groupCounts);
		depth += dfs(matrixShelf, visited, expected, row + 1, col, groupCounts);

		// Aggiorna l'array groupCounts in base alla dimensione del gruppo
		if (matrixShelf[row][col] != Tail.E && matrixShelf[row][col] != Tail.X) {
			if (depth == 3) {
				groupCounts[0]++;
			} else if (depth == 4) {
				groupCounts[1]++;
			} else if (depth == 5) {
				groupCounts[2]++;
			} else if (depth >= 6) {
				groupCounts[3]++;
			}
		}

		return depth;
	}

	public boolean[][] createVisitMatrixShelf() {
		boolean[][] visit = new boolean[shelf.length][];

		for (int row = 0; row < shelf.length; row++)
			visit[row] = new boolean[shelf[row].length];

		return visit;
	}
	/* ---------- END DFS ---------- */

	// Print a single row of the Shelf, used in 'printBoardAndShelfAndPgc()' in Turn
	public void printRowBookshelf(int row) {
		if (row == -1) {
			for (int a = 1; a <= this.COL; a++) {
				System.out.print(a + " ");
			}
		} else {
			for (int j = 0; j < this.COL; j++) {
				printTail(shelf[row][j]);
				System.out.print(" ");
			}
		}
	}

	// Print the Tail in the right color
	static public void printTail(Tail t) {
		if (t == Tail.C) {
			System.out.print(Color.GREEN);
			System.out.print("C");
			System.out.print(Color.RESET);
		}
		if (t == Tail.B) {
			System.out.print(Color.WHITE);
			System.out.print("B");
			System.out.print(Color.RESET);
		}
		if (t == Tail.G) {
			System.out.print(Color.YELLOW);
			System.out.print("G");
			System.out.print(Color.RESET);
		}
		if (t == Tail.F) {
			System.out.print(Color.BLUE);
			System.out.print("F");
			System.out.print(Color.RESET);
		}
		if (t == Tail.T) {
			System.out.print(Color.CYAN);
			System.out.print("T");
			System.out.print(Color.RESET);
		}
		if (t == Tail.P) {
			System.out.print(Color.RED);
			System.out.print("P");
			System.out.print(Color.RESET);
		}
		if (t == Tail.E || t == Tail.X) {
			System.out.print(Color.BLACK);
			System.out.print("#");
			System.out.print(Color.RESET);
		}
	}

	/*
	 * @Return the largest number of free cells (for every column). 
	 * If 0 -> the user can't pick any Tail
	 */
	public int checkFreeSpaces() {
		int[] count = new int[this.COL];
		for (int j = 0; j < COL; j++) {
			count[j] = Matrix.countEinCol(shelf, j);
		}
		return Arrays.stream(count).max().orElse(0);
	}
	

	/* ---------- Getter and setter ---------- */
	
	public int getRow() {
		return this.ROW;
	}

	public int getCol() {
		return this.COL;
	}

	public Tail[][] getShelf() {
		return shelf;
	}

	public void setShelf(Tail[][] shelf) {
		this.shelf = shelf;
	}

}