package commonGoalsPackage;

import logic.*;

public class CommonGoal11 extends CommonGoal {

	public CommonGoal11(int nPlayers) {
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
				if (shelf[i][j] != Tail.E && shelf[i][j].values().equals(shelf[i + 2][j])
						&& shelf[i][j].values().equals(shelf[i + 2][j + 2])
						&& shelf[i][j].values().equals(shelf[i][j + 2])
						&& shelf[i][j].values().equals(shelf[i + 1][j + 1])) {
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
