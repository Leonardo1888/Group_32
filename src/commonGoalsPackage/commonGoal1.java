package commonGoalsPackage;

import logic.*;

public class commonGoal1 {
	private int cont = 4;
	private int nPlayers;
	
	public commonGoal1(int nPlayers) {
		this.nPlayers = nPlayers;
	}
	
	// @Return the number of points the user gained because he achieved the commonGoal.
	public int checkCommonGoal(Bookshelf shelf) {
		if(check()) {
			subtractCont();
			if(cont == 2)
				return 8;
			if(cont == 1)
				return 6;
			if(cont == 0)
				return 4;
		}
		return 0;
	}
	
	// Check if the user achieved the commonGoal
	private boolean check() {
		// TODO check 
		return true;
	}
	
	public void subtractCont() {
		this.cont--;
	}
}
