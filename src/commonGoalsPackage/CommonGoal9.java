package commonGoalsPackage;

import logic.*;

public class CommonGoal9 extends CommonGoal {

	public CommonGoal9(int nPlayers) {
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
		for (int j = 0; j < 5; j++) {
			Tail[] tails = new Tail[6];
			for (int i = 0; i < 6; i++) {
				tails[i] = shelf[i][j];
			}
			if (Matrix.EinCol(shelf, j) == false && Matrix.differentArray(tails, 6) == true) {
				cont++;
			}
		}
		if (cont >= 2) {
			return true;
		}
		return false;
	}

	@Override
	public void printCommonGoal() {
		System.out.println("Printa la commongGoal");
	}
}
