package logic;

import java.util.*;

public class TurnsManagement {
    private List<Player> players;
    private List<Turn> turns;
    private Board board;
    
    private int currentPlayerIndex;
    private int turnCounter;
    private boolean gameOver;
    private Scanner sc;
    
    public TurnsManagement(List<Player> players, Board board, Scanner sc) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.board = board;
        this.turnCounter = 0;
        this.gameOver = false;
        this.sc = sc;
        startGame();
    }
    
    // All the turns of the game
    private void startGame() {
    	while(!gameOver) {
    		Player currentPlayer = players.get(currentPlayerIndex);
			Turn t = new Turn(board, currentPlayer, currentPlayer.getShelf(), currentPlayer.getPersonalGoalCard(), this.sc, this.turnCounter);
			
			this.turnCounter++;
					
			// TODO REMOVE ME 
			if(turnCounter == 8) {
				break;
			}
			
			t.playTurn();
			// turns.add(t);
			
			if(currentPlayerIndex == (players.size()-1))
				currentPlayerIndex=0;
			else
				currentPlayerIndex++;
			
	    	this.gameOver = t.isGameOver();
    	}
    	gameOver();
    }
    
    // TODO count points and set winner
    private void countPoints() {
    	
    }
    
    private void gameOver() {
    	System.out.println(" --- Game is over ---");
    	
    	
    	
    	//call personal goal card points
    	for(int i = 0; i < players.size(); i++) {
    		Player currentPlayer = players.get(i);
    		Bookshelf bookshelf = currentPlayer.getShelf();
    		Tail[][] shelfMatrix = bookshelf.getShelf();
    		PersonalGoalCard pgc = currentPlayer.getPersonalGoalCard();
    		
    		//print each group
    		currentPlayer.getShelf().findMaxAdjacentCount();
    		
    		//call adjacent tails groups points
    		int cont = pgc.tailsMatched(shelfMatrix);
    		int points = pgc.countPoints(cont);
    		System.out.println("punti personal goal card:" + points);
    	}
 
    	
    	countPoints();
    }
    
    public void addPlayer(Turn t) {
		if (t == null) {
			throw new NullPointerException("Turn is null");
		}
		this.turns.add(t);
	}
    
    // TODO
    private void printWinner() {
    	System.out.println("The winner is.. and points.. ");
    }

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
    
}
