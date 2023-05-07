package logic; 

public interface Matrix {
	   
    /*
    public Tail[][] getMatrix() {
        return matrix;
    } 
    
    public int getRow() {
    	return this.row;
    }
    
    public int getCol() {
    	return this.col;
    } */
    
    static void printMatrix(Tail matrix[][], int row, int col) {
        System.out.println();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
