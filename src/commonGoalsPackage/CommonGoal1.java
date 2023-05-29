package commonGoalsPackage;

import logic.*;

public class CommonGoal1 extends CommonGoal {
	public CommonGoal1(int nPlayers) {
		super(nPlayers);
	}

	@Override
	public int checkCommonGoal(Bookshelf shelf) {
		if (check()) {
			subtractContOfTwo();
			if(nPlayers == 4) {
				
			}
			if(nPlayers == 3) {
				
			}
			if(nPlayers == 2) {
				
			}
			if (cont == 2)
				return cont;
			if (cont == 1)
				return cont;
			if (cont == 0)
				return cont;
		}
		return 0;
	}

	@Override
	public boolean check() {
		// Implementazione specifica per il CommonGoal1
		// Esegui il controllo del CommonGoal1
		return true;
	}

	@Override
	public void printCommonGoal() {
		System.out.println("Printa la commongGoal");
	}

}
