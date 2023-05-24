package logic;

import java.util.*;

public class TurnManagement {
    private List<Player> players;
    private int currentPlayerIndex;
    
    private Board board;
    
    public TurnManagement(List<Player> players, Board board) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.board = board;
    }

    public void startGame() {
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);

            // Esegui le operazioni per il turno del giocatore corrente
            
            Scanner in = new Scanner(System.in);
    		
    		Tail[] tails = new Tail[] { Tail.E, Tail.E, Tail.E };
    		int[][] positionTails = new int[3][2];

    		int endTurn = 1;					// endTurn=1 -> keep going - endTurn=0 -> stop turn
    		int nOfFreeSpaces = 0;				// nOfFreeSpaces in Player's shelf
    		boolean canPickTailsBoard = false;	// Number of tails a user can pick
    		int cont = 0;						// cont = number of tails the user picked

    		System.out.println("\n\n---Select the Tails you want to put into your Shelf.");

    		// tRow = a,b,.. tCol = 1,2,.. ----- row = col = 0,1,..
    		while (cont < 3 && endTurn != 0) {

    			// Check if board has to be refilled
    			board.endBoard();

    			// Only the first time store the largest number of free cells (for every column)
    			if(cont==0)
    				nOfFreeSpaces = currentPlayer.getShelf().checkFreeSpaces();
    			
    			// Check if user has space on his shelf
    			if (nOfFreeSpaces == 0) {
    				System.out.println("You can't pick any tails. Your shelf is full.");
    				// TODO -> Call function "nextTurn()" (end game)
    			}

    			// User enters ROW and COL
    			System.out.print("\n---Tail number: " + (cont + 1) + ".");
    			System.out.print("\n-Insert ROW [a-g]: ");
    			char tRow = in.next().charAt(0);
    			System.out.print("-Insert COL [1-9]: ");
    			int col = in.nextInt();
    			int tCol = col;

    			// Set ROW and COL to numbers -> [0-6, 0-8]
    			int row = (tRow - 97); // Convert from char to number (ASCII -97)
    			col = col - 1;

    			// Stores selected tail in tails[i], if user can't pick tails[i] = Tails.E
    			tails[cont] = board.selectTails(row, col, cont);
    			if (tails[cont] == Tail.E) {
    				System.out.println("The Tail in the position: [" + tRow + ", " + tCol + "] is not suitable.");
    			} else {
    				System.out.println(
    						"You have chosen the tail '" + tails[cont] + 
    						"' in the position: [" + tRow + ", " + tCol + "]");
    				positionTails[cont][0] = row;
    				positionTails[cont][1] = col;
    				cont++;
    				nOfFreeSpaces--;
    				
    			}

    			// Check if user can pick another tail (on the board).
    			if(cont < 3) {				
    				canPickTailsBoard = board.checkFreeSpaces(positionTails, cont);
    				if (canPickTailsBoard == false && cont > 0) {
    					System.out.println("You can't pick any adjacent tail on the board on the same row and column.");
    					// TODO -> Call function "nextTurn()"
    				}
    			}

    			// User decides if he wants to pick another tail
    			if (cont < 3) {
    				System.out.println("\nEnter: \n-0 to stop; \n-1 to pick another.");
    				endTurn = in.nextInt();
    				if (endTurn != 0 && endTurn != 1) {
    					throw new IllegalArgumentException("You must enter 0 or 1.");
    				}
    			}
    			
    			
    			
    			// -------- END TURN
    		}

            // Passa al prossimo giocatore
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }
}
