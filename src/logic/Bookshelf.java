package logic;

public class Bookshelf implements Matrix {
	private int row = 6;
	private int col = 5;
	
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
	
	public Tail[][] insertTail(Tail[][] shelf, Tail tail, int col, int numTail) {
		for(int n = 0; n < numTail; n++) {
			for(int i = 0; i < this.row; i++) {
				if(shelf[i][col] == Tail.X) {
					shelf[i][col] = tail;		//insert tail
					shelf[i-1][col] = Tail.X;	//insert X one row up
				}
			}
		}
		return shelf;
	}
	
	public int[] controlEmptyRowsInCols(Tail[][] shelf) {	//returns the number of empty
	int[] emptyRowsInCols = new int[this.col];				//slot of each column
		for(int j = 0; j < this.col; j++) {		
			int cont = 0;
			for(int i = 0; i < this.row; i++) {
				if(shelf[i][j] == Tail.E) {
					cont++;
				}
			}
			emptyRowsInCols[j] = cont;
		}
		return emptyRowsInCols;
	}
	
	
	public String insertMsg() {
		String msg = new String();
		msg = "Choose the column (1-5): ";
		return msg;
	}
}