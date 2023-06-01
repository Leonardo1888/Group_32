package logic;

import commonGoalsPackage.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TurnsManagement {
    private List<Player> players;
    private List<Turn> turns;
    private Board board;
    
    private int nPlayers;
    private int currentPlayerIndex;
    private int turnCounter;
    private boolean gameOver;
    private Scanner sc;
    private int index;
    
    private CommonGoal CommonGoalA;
    private CommonGoal CommonGoalB;    

	public TurnsManagement(List<Player> players, Board board, Scanner sc) {
        this.players = players;
        this.nPlayers = players.size();
        this.currentPlayerIndex = 0;
        this.board = board;
        this.turnCounter = 0;
        this.gameOver = false;
        this.sc = sc;
        this.index = 0;
        this.turns = new ArrayList<>();

        startGame();
    }
    
    // All the turns of the game
    private void startGame() {
    	initializeCommonGoals();
    	
    	while(!gameOver) {
    		Player currentPlayer = players.get(currentPlayerIndex);
			Turn t = new Turn(board, currentPlayer, currentPlayer.getShelf(), currentPlayer.getPersonalGoalCard(), 
					this.sc, this.turnCounter,
					this.CommonGoalA, this.CommonGoalB);
			
			this.turnCounter++;
					
			// TODO REMOVE ME 
			if(turnCounter == 8) {
				break;
			}
			
			t.playTurn();
			turns.add(t); 
			
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
    	
    	// Call personal goal card points
    	for(int i = 0; i < players.size(); i++) {
    		Player currentPlayer = players.get(i);
    		Bookshelf bookshelf = currentPlayer.getShelf();
    		Tail[][] shelfMatrix = bookshelf.getShelf();
    		PersonalGoalCard pgc = currentPlayer.getPersonalGoalCard();
    		
    		// call adjacent tails groups points
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
    
    private void initializeCommonGoals() {
    	CommonGoalA = createCommonGoal();
    	CommonGoalB = createCommonGoal();
    	
    	System.out.println("\n--- START GAME OF MY SHELFIE ---\n");
    	System.out.println("The common goal cards grant points the players who achieve the illustrated pattern.");
    	System.out.println("There are two common goals, and we will call them 'A' and 'B'. \nThose are the two common goals you have to achieve: ");
    	System.out.println("\nCommon goal A: \n");
    	CommonGoalA.printCommonGoal();
    	
    	System.out.println("\nCommon goal B: \n");
    	CommonGoalB.printCommonGoal();
    }
    
    private CommonGoal createCommonGoal() {
    	
    	Random random = new Random();
    	int index = ThreadLocalRandom.current().nextInt(1, 12 + 1);
    	while(index == this.index)
        	index = ThreadLocalRandom.current().nextInt(1, 12 + 1);
    	this.index = index;	
    	
        switch (index) {
            case 1:
                return new CommonGoal1(nPlayers);
            case 2:
                return new CommonGoal2(nPlayers);
            case 3:
                return new CommonGoal3(nPlayers);
            case 4:
                return new CommonGoal4(nPlayers);
            case 5:
                return new CommonGoal5(nPlayers);
            case 6:
                return new CommonGoal6(nPlayers);
            case 7:
                return new CommonGoal7(nPlayers);
            case 8:
                return new CommonGoal8(nPlayers);
            case 9:
                return new CommonGoal9(nPlayers);
            case 10:
                return new CommonGoal10(nPlayers);
            case 11:
                return new CommonGoal11(nPlayers);
            case 12:
                return new CommonGoal12(nPlayers);
            default:
                throw new IllegalArgumentException("Index: '" + index + "' is not a valid CommonGoal.");
        }

    	
    	/*
    	
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
		System.out.println("\n" + getInCommonGoal2al2));*/
    }
    
    public void addPlayer(Turn t) {
		if (t == null) {
			throw new NullPointerException("Turn is null");
		}
		this.turns.add(t);
	}
    
    private void printWinner() {
    	System.out.println("-------------");
    	int max = 0;
    	String winner = "";
    	
    	Player currentPlayer;
    	for(int i = 0; i < players.size(); i++) {
    		currentPlayer = players.get(i);
    
    		if(currentPlayer.getPoints() > max) {
    			max = currentPlayer.getPoints();
    			winner = currentPlayer.getUsername();
    		}
    	}
    	
    	for(int i = 0; i < players.size(); i++) {
    		currentPlayer = players.get(i);
    	    
    		if(currentPlayer.getPoints() == max && !currentPlayer.equals(winner)) {
    			max = currentPlayer.getPoints();
    			winner = currentPlayer.getUsername();
    		}

    	}	
    	
    	
    	System.out.println("The winner is " + winner + " with " + max + "points.");
    	
    }

    // --- Setter and Getter --- 
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
   
    public CommonGoal getCommonGoalA() {
		return CommonGoalA;
	}

	public void setCommonGoalA(CommonGoal commonGoalA) {
		CommonGoalA = commonGoalA;
	}

	public CommonGoal getCommonGoalB() {
		return CommonGoalB;
	}

	public void setCommonGoalB(CommonGoal commonGoalB) {
		CommonGoalB = commonGoalB;
	}
	
}
