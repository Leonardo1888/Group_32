package logic;

public class Matrix {
    private Tail[][] matrix;
    private int row;
    private int columns;
    
    public Matrix(int rows, int columns) {
        this.row = rows;
        this.columns = columns;
        matrix = new Tail[rows][columns];
    }
    
    public Tail[][] getMatrix() {
        return matrix;
    }
    
    public void printMatrix() {
        System.out.println();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
