package commonGoalsPackage;

import logic.*;

public class CommonGoal5 extends CommonGoal {

	/*
	  	Three columns each formed by 6 tiles Five tiles of the same type forming an X.
		of maximum three different types. 
		One	column can show the same or a different combination of another column.
	*/
	
	private String msg = "Three columns each formed by 6 tiles Five tiles of the same type forming an X of maximum three different types."
			+ " One	column can show the same or a different combination of another column.";
	
	public CommonGoal5(int nPlayers) {
		super(nPlayers);
	}

	@Override
	public int checkCommonGoal(Bookshelf bs) {
		if (check(bs)) {
			return super.countPoints();
		} else
			return 0;
	}

	@Override // At least 3 same Tails in a column. (x3 cols)
	public boolean check(Bookshelf bs) {
		Tail[][] s = bs.getShelf();
		// A copy of the original shelf we edit
		Tail[][] shelf = s;
		int sameTailsPerCol = 0;
		int numberOfCols = 0;
		Tail t;
		// Columns 0,1,2,..4
		for (int j = 0; j < 5; j++) {
			// Element of col 0,1,2,..5
			for (int i = 0; i < 6; i++) {
				t = shelf[i][j];
				sameTailsPerCol = 0;
				for (int k = 0; k < 6; k++) {
					if(t == Tail.E || t == Tail.X)
						break;
					if (t == shelf[k][j] && shelf[k][j] != Tail.E && shelf[k][j] != Tail.X)
						sameTailsPerCol++;
				}
				if(sameTailsPerCol >= 3) {
					numberOfCols++;
					break;
				}
			}
		}

		if (numberOfCols >= 3)
			return true;
		else
			return false;
	}

	@Override
	public void printCommonGoal() {
		System.out.println(msg);

		int row = 6;
		int col = 1;
		Tail[][] commonGoalCard = new Tail[row][col];
		for (int i = 0; i < row; i++) {
			commonGoalCard[i][0] = Tail.X;
		}
		Matrix.printMatrixSimple(commonGoalCard, row, col);
		System.out.println("\nx3, max 3 different");
		// return commonGoalCard;
	}
}
