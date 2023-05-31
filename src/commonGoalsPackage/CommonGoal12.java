package commonGoalsPackage;

import logic.*;

public class CommonGoal12 extends CommonGoal {

	/*
	 * Five columns of increasing or decreasing height. Starting from the first
	 * column on the left or on the right, each next column must be made of exactly
	 * one more tile. Tiles can be of any type.
	 */
	
	private String msg = "Five columns of increasing or decreasing height. "
			+ "Starting from the first column on the left or on the right, each next column must be made of exactly "
			+ "one more tile. Tiles can be of any type.";

	public CommonGoal12(int nPlayers) {
		super(nPlayers);
	}

	@Override
	public int checkCommonGoal(Bookshelf bs) {
		if (check(bs)) {
			return super.countPoints();
		} else
			return 0;
	}

	@Override // TODO
	public boolean check(Bookshelf bs) {
		Tail[][] s = bs.getShelf();
			
		// Upper right empty
	    for (int i = 0; i < 4; i++) {
	        for (int j = 1; j < 5; j++) {
	            if (s[i][j] != Tail.E) {
	                return false;
	            }
	        }
	    }
	    // Upper right empty shifted 
	    for (int i = 0; i < 5; i++) {
	        for (int j = 0; j < 5; j++) {
	            if (s[i][j] != Tail.E) {
	                return false;
	            }
	        }
	    }
	    // Upper left empty
	    for (int i = 0; i < 4; i++) {
	        for (int j = 0; j < 4; j++) {
	            if (s[i][j] != Tail.E) {
	                return false;
	            }
	        }
	    }
	    // Upper left empty shifted
	    for (int i = 0; i < 5; i++) {
	        for (int j = 0; j < 5; j++) {
	            if (s[i][j] != Tail.E) {
	                return false;
	            }
	        }
	    }
	    
	    return true;
	}

	@Override
	public void printCommonGoal() {
		System.out.println(msg);
		
		int row = 5;
		int col = 5;
		Tail[][] commonGoalCard = new Tail[row][col];
		commonGoalCard = Matrix.FillWithE(commonGoalCard, row, col);
		for (int i = row - 1; i >= 0; i--) {
			for (int j = 0; j < col; j++) {
				commonGoalCard[i][j] = Tail.X;
			}
			col--;
		}
		col = 5;
		Matrix.printMatrixSimple(commonGoalCard, row, col);
		// return commonGoalCard;
	}
}
