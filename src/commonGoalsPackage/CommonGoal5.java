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

	@Override	// TODO
	public boolean check(Bookshelf bs) {
		return false;
	}

	@Override
	public void printCommonGoal() {
		System.out.println("Printa la commongGoal");
	}
}
