package commonGoalsPackage;

import logic.*;

public class CommonGoal6 extends CommonGoal {

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
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
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
				int cont = 0;
				if (shelf[i][j] == Tail.B) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				if (shelf[i][j] == Tail.G) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				if (shelf[i][j] == Tail.F) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				if (shelf[i][j] == Tail.T) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
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
		System.out.println("Printa la commongGoal");
	}
}
