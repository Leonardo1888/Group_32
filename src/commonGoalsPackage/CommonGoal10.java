package commonGoalsPackage;

import logic.*;

public class CommonGoal10 extends CommonGoal {

	public CommonGoal10(int nPlayers) {
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
			Tail[] tails = new Tail[5];
			for (int j = 0; j < 5; j++) {
				tails[j] = shelf[i][j];
			}
			if (Matrix.EinRow(shelf, i) == false && Matrix.differentArray(tails, 5) == true) {
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
