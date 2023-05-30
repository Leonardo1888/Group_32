package commonGoalsPackage;

import logic.*;

public abstract class CommonGoal {

	private int points;
	private int nPlayers;

	public CommonGoal(int nPlayers) {
		this.nPlayers = nPlayers;
		this.points = 10;
	}

	// Return the number of points the user will gain
	public abstract int checkCommonGoal(Bookshelf shelf);

	public int countPoints() {
		// 8->6->4->2
		if (nPlayers == 4) {
			if (points == 2)
				return 0;
			else
				points = points - 2;
		}
		// 8->6->4
		if (nPlayers == 3) {
			if (points == 4)
				return 0;
			else
				points = points - 2;
		}
		// 8->4
		if (nPlayers == 2) {
			if (points == 8)
				points = points - 4;
			else
				points = points - 2;
			if (points == 4)
				return 0;
		}
		return points;
	}

	// If commonGoal is respected return the points to add the user. If not returns 0
	public abstract boolean check(Bookshelf bs);

	// Output of the CommonGoal
	public abstract void printCommonGoal();

	public int getPoints() {
		return this.points;
	}

}
