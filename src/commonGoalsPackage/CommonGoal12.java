package commonGoalsPackage;

import logic.*;

public class CommonGoal12 extends CommonGoal {

	/*
	 * Five columns of increasing or decreasing height. \nStarting from the first
	 * column on the left or on the right, each next column must be made of exactly
	 * one more tile. \nTiles can be of any type.
	 */

	private String msg = "Five columns of increasing or decreasing height. "
			+ "\nStarting from the first column on the left or on the right, each next column must be made of exactly "
			+ "one more tile. \nTiles can be of any type.";

	public CommonGoal12(int nPlayers) {
		super(nPlayers);
	}

	@Override
	public int checkCommonGoal(Bookshelf bs) {
		if (check(bs)) {
			return super.countPoints();
		} else
			return 0;
	}

	@Override // TODO
	public boolean check(Bookshelf bs) {
		Tail[][] s = bs.getShelf();
		boolean upperRightE = true;
		boolean lowerLeftT = true;
		
		boolean upperRightSE = true;
		boolean lowerLeftST = true;

		boolean upperLeftSE = true;
		boolean lowerRightST = true;
		
		boolean upperLeftE = true;
		boolean lowerRightT = true;
		
		/* First check */
		// Upper right empty
		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 5; j++) {
				if (s[i][j] != Tail.E || s[i][j] != Tail.X) {
					upperRightE = false;
				}
			}
		}

		// Lower left full of tails
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j <= i; j++) {
				if (s[i][j] == Tail.E || s[i][j] == Tail.X) {
					lowerLeftT = false;
				}
			}
		}

		/* Second check */
		// Upper right empty shifted
		for (int i = 0; i < 5; i++) {
			for (int j = i; j < 5; j++) {
				if (s[i][j] != Tail.E || s[i][j] != Tail.X) {
					upperRightSE = false;
				}
			}
		}

		// Lower left full of tails shifted
		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < i; j++) {
				if (s[i][j] == Tail.E || s[i][j] == Tail.X) {
					lowerLeftST = false;
				}
			}
		}

		/* Third check */
		// Upper left empty shifted
		int cont = 5;
		for (int i = 0; i < 5; i++) {
			cont--;
			for (int j = cont; j >= 0; j--) {
				if (s[i][j] != Tail.E || s[i][j] != Tail.X) {
					upperLeftSE = false;
				}
			}
		}

		// Lower right shifted full of tails
		cont = -1;
		for (int i = 5; i > 0; i--) {
			cont++;
			for (int j = cont; j < 5; j++) {
				if (s[i][j] == Tail.E || s[i][j] == Tail.X) {
					lowerRightST = false;
				}
			}
		}

		
		/* Forth check */
		// Upper left empty 
		cont = 4;
		for (int i = 0; i < 4; i++) {
			cont--;
			for (int j = cont; j >= 0; j--) {
				if (s[i][j] != Tail.E || s[i][j] != Tail.X) {
					upperLeftE = false;
				}
			}
		}

		// Lower right full of tails
		cont = -1;
		for (int i = 4; i >= 0; i--) {
			cont++;
			for (int j = cont; j < 5; j++) {
				if (s[i][j] == Tail.E || s[i][j] == Tail.X) {
					lowerRightT = false;
				}
			}
		}
		
		if (upperRightE && lowerLeftT)
			return true;
		else if (upperRightSE && lowerLeftST)
			return true;
		else if (upperLeftSE && lowerRightST)
			return true;
		else if (upperLeftE && lowerRightT)
			return true;
		else
			return false;
	}

	@Override
	public void printCommonGoal() {
		System.out.println(msg);

		int row = 5;
		int col = 5;
		Tail[][] commonGoalCard = new Tail[row][col];
		commonGoalCard = Matrix.FillWithE(commonGoalCard, row, col);
		for (int i = row - 1; i >= 0; i--) {
			for (int j = 0; j < col; j++) {
				commonGoalCard[i][j] = Tail.X;
			}
			col--;
		}
		col = 5;
		Matrix.printMatrixSimple(commonGoalCard, row, col);
	}
}
