package commonGoalsPackage;

import logic.*;

public class CommonGoal5 extends CommonGoal {

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

	@Override	// TODO Almeno 3 tails uguali in una riga. (x3)
	public boolean check(Bookshelf bs) {
		Tail[][] s = bs.getShelf();
		// A copy of the original shelf we edit
		Tail[][] shelf = s;
		int cont = 0;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (shelf[i][j] != Tail.E && shelf[i][j] == shelf[i][j + 1] && shelf[i][j] == shelf[i + 1][j]
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
		System.out.println("Printa la commongGoal");
	}
}
