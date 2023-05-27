package logic;

import java.util.*;

public class TurnsManagement {
    private List<Player> players;
    private List<Turn> turns;
    private Board board;
    
    private int currentPlayerIndex;
    // private int turnCounter;
    private boolean gameOver;
    
    
    public TurnsManagement(List<Player> players, Board board) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.board = board;
        // this.turnCounter = 0;
        this.gameOver = false;
        
        startGame();
    }
    
    // All the turns of the game
    private void startGame() {
    	while(!gameOver) {
    		Player currentPlayer = players.get(currentPlayerIndex);
			Turn t = new Turn(board, currentPlayer, currentPlayer.getShelf(), currentPlayer.getPersonalGoalCard());
			gameOver = t.playTurn();
			turns.add(t);
			currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    	}
    	gameOver();
    }
    
    private void gameOver() {
    	System.out.println(" --- Game is over --- ");
    }
    
}
