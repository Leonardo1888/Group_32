package logic;

public class Bookshelf extends Matrix {
	private int ROW = 5;
	private int COL = 6;
	private Tail[][] shelf;

	public Bookshelf() {
		shelf = new Tail[ROW][COL];
		for(int i = 0; i < (ROW-1); i++) {	
			for(int j = 0; j < COL; j++) {
				shelf[i][j] = Tail.E;
			}
		}
	}
	
	public void printBoard() {
		System.out.println();
		for (int i = 0; i < this.ROW; i++) {
			for (int j = 0; j < this.COL; j++) {
				System.out.print(this.shelf[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	

}