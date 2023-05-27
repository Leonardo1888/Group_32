package logic;

import java.util.*;

public class PlayersManagement {

	private Player firstPlayer; // player with chair
	private int nPlayers;

	private List<Player> players;
	private int currentPlayerIndex;

	Scanner sc;
	
	public PlayersManagement(Scanner sc) {
		this.players = new ArrayList<>();
		this.sc = sc;

	}

	// Enter players and saves them in "players"
	public void enterPlayers () {
		while(true) {
			System.out.print("\nEnter the number of players [2-4]: ");
			this.nPlayers = sc.nextInt();
			sc.nextLine();
			if(nPlayers >= 2 && nPlayers <= 4) 
				break;
			else
				System.out.println("ERROR! Number of players must be 2, 3 or 4.");
		}
		
    	String playerUsername = "";
		boolean correctUsername = false;
		
		// username checks
    	for(int i = 0; i < nPlayers; i++) {
    		while(!correctUsername) {
    			System.out.print("-Enter " + (i+1) + "° player's name: ");
    			playerUsername = sc.next();
    			
    			if (playerUsername == null || playerUsername.isEmpty()) {
                    System.out.println("Player name can't be null or empty. Please try again.");
                } else if (playerUsername.length() < 3) {
                    System.out.println("Player name must be at least 3 characters long. Please try again.");
                } else if (playerUsername.length() > 10) {
                    System.out.println("Player name can't exceed 10 characters long. Please try again.");
                } else if (i > 0 && checkUsernameAlreadyTaken(playerUsername)) {
                    System.out.println("Player name has already been taken. Please try again.");
                } else {
                    correctUsername = true;
                }
    		}
    		
    		Player p;
            p = new Player(playerUsername, 
	 					   new Bookshelf(),
	 					   new PersonalGoalCard(),
	 					   true);
            addPlayer(p);
                        
            System.out.println("Added " + (i+1) + "° player, named: " + playerUsername);
            correctUsername = false;
    	}
	}

	// Return true if username has already been taken
	private boolean checkUsernameAlreadyTaken(String playerName) {
		for(int i = 0; i < players.size(); i++) {
			Player currentPlayer = players.get(i);
			if (currentPlayer.getUsername().equals(playerName)) {
				return true;
			}
		}
		return false;
	}
	
	public void addPlayer(Player p) {
		if (p == null) {
			throw new NullPointerException("Player is null");
		}
		this.players.add(p);
	}

	// Getter and setter

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setnPlayers(int nPlayers) {
		this.nPlayers = nPlayers;
	}

	public int getnPlayers() {
		return nPlayers;
	}

	public void setFirstPlayer(Player fp) {
		this.firstPlayer = fp;
	}
}
