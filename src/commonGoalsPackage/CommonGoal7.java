package commonGoalsPackage;

import logic.*;

public class CommonGoal7 extends CommonGoal {

	/*
	 * Five tiles of the same type forming a diagonal.
	 */

	private String msg = "Five tiles of the same type forming a diagonal.";
	
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
		//check diagonal from (0,0) and from (1,0) from left to the right
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 1; j++) {
				if (shelf[i][j] != Tail.E && shelf[i][j].equals(shelf[i + 1][j + 1])
						&& shelf[i][j].equals(shelf[i + 2][j + 2]) && shelf[i][j].equals(shelf[i + 3][j + 3])
						&& shelf[i][j].equals(shelf[i + 4][j + 4])) {
					return true;
				}
			}
		}
		
		//check diagonal from (0,4) and from (1,4) from right to the left
		for (int i = 0; i < 2; i++) {
			for (int j = 4; j < 1; j++) {
				if (shelf[i][j] != Tail.E && shelf[i][j].equals(shelf[i + 1][j - 1])
						&& shelf[i][j].equals(shelf[i + 2][j - 2]) && shelf[i][j].equals(shelf[i + 3][j - 3])
						&& shelf[i][j].equals(shelf[i + 4][j - 4])) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void printCommonGoal() {
		System.out.println(msg);
		
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
		Matrix.printMatrixSimple(commonGoalCard, row, col);
		// return commonGoalCard;
	}
}
