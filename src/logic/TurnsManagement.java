package logic;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
    	createCommonGoals();
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
    
    private void gameOver() {
    	System.out.println(" --- Game is over ---");
    	
    	
    	
    	//call personal goal card points
    	for(int i = 0; i < players.size(); i++) {
    		Player currentPlayer = players.get(i);
    		Bookshelf bookshelf = currentPlayer.getShelf();
    		Tail[][] shelfMatrix = bookshelf.getShelf();
    		PersonalGoalCard pgc = currentPlayer.getPersonalGoalCard();
    		//call adjacent tails groups points
    		int pointsPGC = countPersonalGoalPoints(shelfMatrix, pgc);
    		System.out.println("Personal Goal Card points: " + pointsPGC);
    		
    		//print each group and total points of adjacent groups
    		int pointsADJ = currentPlayer.getShelf().AdjacentPoints();
    		System.out.println("Adjacent Group Tails points: " + pointsADJ);
    	}
    }
    
    //count the personal goal card points
    private int countPersonalGoalPoints(Tail[][] shelfMatrix, PersonalGoalCard pgc){
        int cont = pgc.tailsMatched(shelfMatrix);
        int points = pgc.countPoints(cont);
        return points;
    }
    
    private void createCommonGoals() {
    	Random random = new Random();
		this.commonGoal1 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		this.commonGoal2 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		while (this.commonGoal2 == this.commonGoal1) {
			this.commonGoal2 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		}
		System.out.println("\n" + CommonGoal1 + ".");
		commonGoalCard1 = generateCommonGoalCard(this.commonGoal1);
		System.out.println("\n" + getInfo(CommonGoal1));
		System.out.println("\n"CommonGoal2al2 + ".");
		commonGoalCard2 = generateCommonGoalCard(this.commonGoal2);
		System.out.println("\n" + getInCommonGoal2al2));
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
