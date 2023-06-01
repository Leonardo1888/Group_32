package commonGoalsPackage;

import logic.*;

public class CommonGoal4 extends CommonGoal {
	/*
	 * Two groups each containing 4 tiles of the same type in a 2x2 square. The
	 * tiles of one square can be different from those of the other square.
	 */
	
	private String msg = "Two groups each containing 4 tiles of the same type in a 2x2 square. "
			+ "The tiles of one square can be different from those of the other square.";
	
	public CommonGoal4(int nPlayers) {
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
		Tail[][] s = bs.getShelf();
		// A copy of the original shelf we edit
		Tail[][] shelf = s;
		int cont = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (shelf[i][j] != Tail.E && shelf[i][j] != Tail.X && shelf[i][j] == shelf[i][j + 1] && shelf[i][j] == shelf[i + 1][j]
						&& shelf[i][j] == shelf[i + 1][j + 1]) {
					shelf[i][j] = shelf[i][j + 1] = shelf[i + 1][j] = shelf[i + 1][j + 1] = Tail.E;
					cont++;
				}
			}
		}
		if (cont == 2)
			return true;
		else
			return false;
	}

	@Override
	public void printCommonGoal() {
		System.out.println(msg);
		
		int row = 2;
		int col = 2;
		Tail [][] commonGoalCard = new Tail[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				commonGoalCard[i][j] = Tail.S;
			}
		}
		Matrix.printMatrixSimple(commonGoalCard, row, col);
		System.out.println("\nx2");
		// return commonGoalCard;
	}
}
