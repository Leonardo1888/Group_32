package commonGoalsPackage;

import logic.*;

public class CommonGoal1 extends CommonGoal {

	public CommonGoal1(int nPlayers) {
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
		Tail[][] shelf = bs.getShelf();
		Tail[][] mirrorShelf = Matrix.copyMatrix(shelf, 5, 6);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				while (Matrix.checkOrthogonally(mirrorShelf, i, j)[0] == true) {

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
