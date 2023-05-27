package logic;

import java.util.*;

public class TurnsManagement {
    private List<Player> players;
    private List<Turn> turns;
    private Board board;
    
    private int currentPlayerIndex;
    // private int turnCounter;
    private boolean gameOver;
    private Scanner sc;
    
    public TurnsManagement(List<Player> players, Board board, Scanner sc) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.board = board;
        // this.turnCounter = 0;
        this.gameOver = false;
        this.sc = sc;
        startGame();
    }
    
    // All the turns of the game
    private void startGame() {
    	while(!gameOver) {
    		Player currentPlayer = players.get(currentPlayerIndex);
			Turn t = new Turn(board, currentPlayer, currentPlayer.getShelf(), currentPlayer.getPersonalGoalCard(), this.sc);
			
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
    
    private void gameOver() {
    	System.out.println(" --- Game is over --- : " + gameOver);
    	
    }
    
    public void addPlayer(Turn t) {
		if (t == null) {
			throw new NullPointerException("Turn is null");
		}
		this.turns.add(t);
	}
    
    // count points and set winner
    private void countPoints() {
    	
    }

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
    
}
