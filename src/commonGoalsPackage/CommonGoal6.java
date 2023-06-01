package commonGoalsPackage;

import logic.*;

public class CommonGoal6 extends CommonGoal {

	/*
	 * Eight tiles of the same type. There’s no restriction about the position of
	 * these tiles.
	 */

	private String msg = "Eight tiles of the same type. \nThere’s no restriction about the position of these tiles.";
	
	public CommonGoal6(int nPlayers) {
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
		int cont = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				
				if (shelf[i][j] == Tail.C) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				
				if (shelf[i][j] == Tail.B) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		cont = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (shelf[i][j] == Tail.G) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		cont = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (shelf[i][j] == Tail.F) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		cont = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (shelf[i][j] == Tail.T) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		cont = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (shelf[i][j] == Tail.P) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void printCommonGoal() {
		System.out.println(msg);
		
		int row = 3;
		int col = 3;
		Tail[][] commonGoalCard = new Tail[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				commonGoalCard[i][j] = Tail.S;
			}
		}
		commonGoalCard[0][1] = Tail.E;
		Matrix.printMatrixSimple(commonGoalCard, row, col);
	}
}
