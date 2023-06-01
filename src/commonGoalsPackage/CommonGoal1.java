package commonGoalsPackage;

import logic.*;

public class CommonGoal1 extends CommonGoal {

	/*
	 * Six groups each containing at least 2 tiles of the same type (not necessarily
	 * in the depicted shape). The tiles of one group can be different from those of
	 * another group.
	 */

	private String msg = "Six groups each containing at least 2 tiles of the same type (not necessarily in the depicted shape). "
			+ "\nThe tiles of one group can be different from those of another group.";

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

	@Override
	public boolean check(Bookshelf bs) {
		Tail[][] shelf = bs.getShelf();
		int cont = AdjacentPoints(shelf);
		if (cont >= 6) {
			return true;
		}
		return false;
	}

	// START DFS
	public int AdjacentPoints(Tail shelf[][]) {
		boolean[][] visited = createVisitMatrixShelf(shelf);
		int[] groupCount = new int[1]; // counter for >= 2 groups

		for (int row = 0; row < shelf.length; row++) {
			for (int col = 0; col < shelf[row].length; col++) {
				if (!visited[row][col]) {
					dfs(shelf, visited, shelf[row][col], row, col, groupCount);
				}
			}
		}

		return groupCount[0];
	}

	// dfs algorithm for deep search
	public static int dfs(Tail[][] matrixShelf, boolean[][] visited, Tail expected, int row, int col,
			int[] groupCount) {
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
		if (matrixShelf[row][col] != Tail.E && matrixShelf[row][col] != Tail.X) {
			if (depth == 2) {
				groupCount[0]++;
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
	// END DFS

	@Override
	public void printCommonGoal() {
		System.out.println(msg);

		int row = 2;
		int col = 1;
		Tail[][] commonGoalCard = new Tail[row][col];
		for (int i = 0; i < row; i++) {
			commonGoalCard[i][0] = Tail.S;
		}
		Matrix.printMatrixSimple(commonGoalCard, row, col);
		System.out.println("x6");
	}
}
