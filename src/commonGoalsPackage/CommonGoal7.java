package commonGoalsPackage;

import logic.*;

public class CommonGoal7 extends CommonGoal {

	/*
	 * Five tiles of the same type forming a diagonal.
	 */

	public CommonGoal7(int nPlayers) {
		super(nPlayers);
	}

	@Override
	public int checkCommonGoal(Bookshelf bs) {
		if (check(bs)) {
			return super.countPoints();
		} else
			return 0;
	}

	@Override
	public boolean check(Bookshelf bs) {
		Tail[][] shelf = bs.getShelf();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (shelf[i][j] != Tail.E && shelf[i][j].equals(shelf[i + 1][j + 1])
						&& shelf[i][j].equals(shelf[i + 2][j + 2]) && shelf[i][j].equals(shelf[i + 3][j + 3])
						&& shelf[i][j].equals(shelf[i + 4][j + 4])) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void printCommonGoal() {
		int row = 5;
		int col = 5;
		Tail[][] commonGoalCard = new Tail[row][col];
		commonGoalCard = Matrix.FillWithE(commonGoalCard, row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == j) {
					commonGoalCard[i][j] = Tail.S;
				}
			}
		}
		Matrix.printMatrix(commonGoalCard, row, col);
		// return commonGoalCard;
	}
}
