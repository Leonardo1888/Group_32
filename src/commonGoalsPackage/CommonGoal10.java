package commonGoalsPackage;

import logic.*;

public class CommonGoal10 extends CommonGoal {

	/*
	 * Two lines each formed by 5 different types of tiles. One line can show the
	 * same or a different combination of the other line.
	 */
	
	private String msg = "Two lines each formed by 5 different types of tiles. One line can show the"
			+ " same or a different combination of the other line.";

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
        boolean checkX = false;
        for (int i = 0; i < 6; i++) {
            Tail[] tails = new Tail[5];
            for (int j = 0; j < 5; j++) {
                tails[j] = shelf[i][j];
                if(shelf[i][j] == Tail.X) {
                    checkX = true;
                }
            }
            if (Matrix.EinRow(shelf, i) == false && differentArray(tails, 5) == true && checkX == false) {
                cont++;
            }
            checkX = false;
        }
        if (cont >= 2) {
            return true;
        }
        return false;
    }

    private boolean differentArray(Tail tails[], int cont) {
        for(int i = 1; i  < cont; i++) {
            if(tails[i] == tails[i-1]) {
                return false;
            }
        }
        return true;
    }

	@Override
	public void printCommonGoal() {
		System.out.println(msg);
		
		int row = 1;
		int col = 5;
		Tail[][] commonGoalCard = new Tail[row][col];
		for (int j = 0; j < col; j++) {
			commonGoalCard[0][j] = Tail.D;
		}
		Matrix.printMatrixSimple(commonGoalCard, row, col);
		System.out.println("\nx2");
		// return commonGoalCard;
	}
}
