package logic;

import java.util.*;

public class Turn {		// DELETE??
    private Board board;
    private Player currentPlayer;	
    private int turnCounter;
    private final int numberOfPlayers;
    private Set<Player> players = new HashSet<>();
	Scanner sc = new Scanner(System.in); 

    public Turn(Board board, Player firstPlayer, Player currentPlayer) {
        this.board = board;
        this.firstPlayer = firstPlayer;
        this.currentPlayer = currentPlayer;
        this.turnCounter=1;
    }
    
    public Turn(Board board, Player currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.turnCounter++;
    	this.players = new HashSet<>();
    }
    
    public void startGame() {

    	System.out.println("\n----------START GAME----------\n");
    	createPlayers();
    	
    	
    }
    
    public void createPlayers() {
    	
        }
    }
    
    public void startTurn() {
    	
    	
    	System.out.println("\nSTATUS Board:");
        System.out.println("\n\n---Select the Tails you want to put into your Shelf.");

        Scanner in = new Scanner(System.in);
        Tail[] tails = new Tail[] { Tail.E, Tail.E, Tail.E };
        int[][] positionTails = new int[3][2];

        int endTurn = 1;
        int nOfFreeSpaces = 0;
        boolean canPickTailsBoard = false;
        int cont = 0;

        while (cont < 3 && endTurn != 0) {
            board.endBoard();

            if (cont == 0)
                nOfFreeSpaces = currentPlayer.getShelf().checkFreeSpaces();

            if (nOfFreeSpaces == 0) {
                System.out.println("You can't pick any tails. Your shelf is full.");
                return; // End the turn
            }

            System.out.print("\n---Tail number: " + (cont + 1) + ".");
            System.out.print("\n-Insert ROW [a-g]: ");
            char tRow = in.next().charAt(0);
            System.out.print("-Insert COL [1-9]: ");
            int col = in.nextInt();
            int tCol = col;

            int row = (tRow - 97);
            col = col - 1;

            tails[cont] = board.selectTails(row, col, cont);
            if (tails[cont] == Tail.E) {
                System.out.println("The Tail in";		
            }
        }
        
}