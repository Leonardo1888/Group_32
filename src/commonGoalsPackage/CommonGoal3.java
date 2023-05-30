package commonGoalsPackage;

import logic.*;

public class CommonGoal3 extends CommonGoal {

	/*
	 * Four tiles of the same type in the four corners of the bookshelf.
	 */
	public CommonGoal3(int nPlayers) {
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
		if (shelf[0][0] != Tail.E && shelf[0][0] == shelf[0][4] && shelf[0][0] == shelf[5][4]
				&& shelf[0][0] == shelf[5][0]) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void printCommonGoal() {
		int row = 6;
		int col = 5;
		Tail[][] commonGoalCard = new Tail[row][col];
		commonGoalCard = Matrix.FillWithE(commonGoalCard, row, col);
		commonGoalCard[0][0] = Tail.S;
		commonGoalCard[0][4] = Tail.S;
		commonGoalCard[5][0] = Tail.S;
		commonGoalCard[5][4] = Tail.S;
		Matrix.printMatrix(commonGoalCard, row, col);
		// return commonGoalCard;
	}
}
