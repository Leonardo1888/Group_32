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
            if (cont == 2)
                return 8;
            if (cont == 1)
                return 6;
            if (cont == 0)
                return 4;
        }
        return 0;
    }

    @Override
    public boolean check() {
        // Implementazione specifica per il CommonGoal1
        // Esegui il controllo del CommonGoal1
        return true;
    }
}
