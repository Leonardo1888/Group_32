package logic;

public class Bookshelf implements Matrix {
	private int row = 5;
	private int col = 6;
	
	private Tail[][] shelf;

	public Bookshelf() {	
		this.shelf = new Tail[row][col];
		for(int i = 0; i < (row); i++) {	
			for(int j = 0; j < col; j++) {
				shelf[i][j] = Tail.E;
				if(i == (row-1)) 
					shelf[i][j] = Tail.X;
			}
		}
	}
	
	public void printBoard() {
		Matrix.printMatrix(shelf, row, col);
	}

}