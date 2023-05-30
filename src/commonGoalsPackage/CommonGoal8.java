package commonGoalsPackage;

import logic.*;

public class CommonGoal8 extends CommonGoal {

	/*
	 * FFour lines each formed by 5 tiles of maximum three different types. One line
	 * can show the same or a different combination of another line.
	 */

	public CommonGoal8(int nPlayers) {
		super(nPlayers);
	}

	@Override
	public int checkCommonGoal(Bookshelf bs) {
		if (check(bs)) {
			return super.countPoints();
		} else
			return 0;
	}

	@Override // At least 2 same Tails in a row. (x4 rows)
	public boolean check(Bookshelf bs) {
		Tail[][] s = bs.getShelf();
		// A copy of the original shelf we edit
		Tail[][] shelf = s;
		int sameTailsPerRow = 0;
		int numberOfCols = 0;
		int max = 0;
		Tail t = Tail.E;
		// Row 0,1,2,..4
		for (int i = 0; i < 6; i++) {
			// Element of row 0,1,2,..5
			for (int j = 0; j < 5; j++) {
				t = shelf[i][j];
				sameTailsPerRow = 0;
				for (int k = 0; k < 6; k++) {
					if (t == shelf[k][j])
						sameTailsPerRow++;
				}
				if (sameTailsPerRow >= 2) {
					numberOfCols++;
					break;
				}
			}
		}

		if (numberOfCols >= 4)
			return true;
		else
			return false;
	}

	@Override
	public void printCommonGoal() {
		int row = 1;
		int col = 5;
		Tail[][] commonGoalCard = new Tail[row][col];
		for (int j = 0; j < col; j++) {
			commonGoalCard[0][j] = Tail.X;
		}
		Matrix.printMatrix(commonGoalCard, row, col);
		System.out.println("\nx4, max 3 different");
		// return commonGoalCard;
	}
}
