package commonGoalsPackage;

import logic.*;

public abstract class CommonGoal {
    private int cont;
    private int nPlayers;

    public CommonGoal(int nPlayers) {
        this.nPlayers = nPlayers;
        this.cont = 10;
    }

    // Return the number of points the user will gain
    public abstract int checkCommonGoal(Bookshelf shelf);

    public abstract boolean check();

    // Output of the CommonGoal
    public abstract void printCommonGoal();
    
    public void subtractContOfTwo() {
        this.cont = this.cont - 2;
    }
    
}
