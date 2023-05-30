package commonGoalsPackage;

import logic.*;

public class CommonGoal3 extends CommonGoal {

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
		System.out.println("Printa la commongGoal");
	}
}
