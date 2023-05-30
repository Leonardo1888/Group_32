package commonGoalsPackage;

import logic.*;

public class CommonGoal2 extends CommonGoal {

	public CommonGoal2(int nPlayers) {
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
		int cont = AdjacentPoints(shelf);
		if(cont >= 4){
			return true;
		}
		return false;
	}
	
	// START DFS
	// call in actionOfEndGame this function, it will return the number of each group then you have to count the points
	public int AdjacentPoints(Tail shelf[][]){
		boolean[][] visited = createVisitMatrixShelf(shelf);
        int res = 0;
        int groupCount = 0; //counter for >= 2 groups

        for (int row = 0; row < shelf.length; row++)
            for (int col = 0; col < shelf[row].length; col++)
                if (!visited[row][col])
                    res = Math.max(res, dfs(shelf, visited, shelf[row][col], row, col, groupCount));
       
        
        System.out.println("Numero di gruppi: " + groupCount);
        return groupCount;
    }
    
	//dfs algorithm for deep search
    public static int dfs(Tail[][] matrixShelf, boolean[][] visited, Tail expected, int row, int col, int groupCount) {
        if (row < 0 || row >= matrixShelf.length)
            return 0;
        if (col < 0 || col >= matrixShelf[row].length)
            return 0;
        if (visited[row][col] || matrixShelf[row][col] != expected)
            return 0;

        visited[row][col] = true;

        int depth = 1;
        depth += dfs(matrixShelf, visited, expected, row, col - 1, groupCount);
        depth += dfs(matrixShelf, visited, expected, row, col + 1, groupCount);
        depth += dfs(matrixShelf, visited, expected, row - 1, col, groupCount);
        depth += dfs(matrixShelf, visited, expected, row + 1, col, groupCount);

        // Aggiorna l'array groupCounts in base alla dimensione del gruppo
        if(matrixShelf[row][col] != Tail.E && matrixShelf[row][col] != Tail.X){
			if (depth >= 4) {
	            groupCount ++;
	        } 
		}
 	
        return depth;
    }

    public boolean[][] createVisitMatrixShelf(Tail shelf[][]) {
        boolean[][] visit = new boolean[shelf.length][];

        for (int row = 0; row < shelf.length; row++)
            visit[row] = new boolean[shelf[row].length];

        return visit;
    }
	//END DFS	
	
	@Override
	public void printCommonGoal() {
		System.out.println("Printa la commongGoal");
	}
}
