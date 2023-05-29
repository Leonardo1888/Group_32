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

	public Tail[] orderTailsToInsert(Scanner sc, Tail tails[], int numTails) {
		switch (numTails) {
		case 0:
			// TODO
		case 1:
			System.out.println("Your Tail:\n1°: " + tails[0]);
			Tail[] tailsToInsert1 = new Tail[1];
			tailsToInsert1[0] = tails[0];
			return tailsToInsert1;
		case 2:	// If they are the same don't ask the user to order the tails
			if (tails[0] != tails[1]) {
				System.out.println("Your Tails: ");
				System.out.println("1°: " + tails[0] + "\n2°: " + tails[1]);
				System.out.println("\nChoose the order of the Tails to insert [1, " + numTails + "]: ");
				Tail[] tailsToInsert2 = new Tail[2];

				System.out.print("First: ");
				int first2 = sc.nextInt();
				sc.nextLine();
				System.out.print("Second: ");
				int second2 = sc.nextInt();
				sc.nextLine();

				tailsToInsert2[0] = tails[first2 - 1];
				tailsToInsert2[1] = tails[second2 - 1];
				return tailsToInsert2;
			}
		case 3:	//  they are the same don't ask the user to order the tails
			if (tails[0] != tails[1] && tails[0] != tails[1]) {
				System.out.println("Your Tails:\n1°: " + tails[0] + "\n2°: " + tails[1] + "\n3°: " + tails[2]);
				System.out.println("\nChoose the order of the Tails to insert [1, " + numTails + "]: ");
				Tail[] tailsToInsert3 = new Tail[3];

				System.out.print("First: ");
				int first3 = sc.nextInt();
				sc.nextLine();
				System.out.print("Second: ");
				int second3 = sc.nextInt();
				sc.nextLine();
				System.out.print("Third: ");
				int third3 = sc.nextInt();
				sc.nextLine();

				tailsToInsert3[0] = tails[first3 - 1];
				tailsToInsert3[1] = tails[second3 - 1];
				tailsToInsert3[2] = tails[third3 - 1];
				return tailsToInsert3;
			}
		}
		return tails;
	}

	// Insert Tails in column
	// @Return 0 if success, @Return 1 if failure, @Return 
	public int insertTail(Tail[] tails, int col, int numTail) {
		//if num E in selected col < num Tails
		if (numTail > Matrix.countEinCol(this.shelf, col)) {
			System.out.println("Not enough free spaces in the column...");
			return 1; // failure
		}
		//insert
		for (int n = 0; n < numTail; n++) {
			for (int i = 0; i < this.ROW; i++) {
				if (this.shelf[i][col] == Tail.X) {
					this.shelf[i][col] = tails[n]; // insert tail
					if (i == 0) { 				   // se la riga è 0 non devo scrivere la X sopra
						System.out.println("The column has been filled entirely");
					} else {
						this.shelf[i - 1][col] = Tail.X; // insert X one row up
					}	
				}
			}
		}
		return 0; // success
	}

	public void printRowBookshelf(int row) {
		if (row == -1) {
			for (int a = 1; a <= this.COL; a++) {
				System.out.print(a + " ");
			}
		} else {

			for (int j = 0; j < this.COL; j++) {
				if (shelf[row][j] == Tail.C) {
					System.out.print(Color.GREEN);
					System.out.print("C");
					System.out.print(Color.RESET);
				}
				if (shelf[row][j] == Tail.B) {
					System.out.print(Color.WHITE);
					System.out.print("B");
					System.out.print(Color.RESET);
				}
				if (shelf[row][j] == Tail.G) {
					System.out.print(Color.YELLOW);
					System.out.print("G");
					System.out.print(Color.RESET);
				}
				if (shelf[row][j] == Tail.F) {
					System.out.print(Color.BLUE);
					System.out.print("F");
					System.out.print(Color.RESET);
				}
				if (shelf[row][j] == Tail.T) {
					System.out.print(Color.CYAN);
					System.out.print("T");
					System.out.print(Color.RESET);
				}
				if (shelf[row][j] == Tail.P) {
					System.out.print(Color.RED);
					System.out.print("P");
					System.out.print(Color.RESET);
				}
				if (shelf[row][j] == Tail.E || shelf[row][j] == Tail.X) {
					System.out.print(Color.BLACK);
					System.out.print("#");
					System.out.print(Color.RESET);
				}
				System.out.print(" ");
			}
		}
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

	public int getRow() {
		return this.ROW;
	}

	public int getCol() {
		return this.COL;
	}

}