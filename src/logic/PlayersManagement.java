package logic;

import java.util.*;

public class PlayersManagement {

	private Player firstPlayer; // player with chair
	private int nPlayers;

	private List<Player> players;
	private int currentPlayerIndex;

	Scanner sc = new Scanner(System.in);

	public PlayersManagement() {
		this.players = new ArrayList<>();

	}

	// Enter players and saves them in "players"
	public void enterPlayers () {
		while(true) {
			System.out.print("\nEnter the number of players [2-4]: ");
			this.nPlayers = sc.nextInt();
			if(nPlayers >= 2 && nPlayers <= 4) 
				break;
			else
				System.out.println("ERROR! Number of players must be 2, 3 or 4.");
		}
		
    	String playerName = "";
		boolean correctName = false;
    	for(int i = 0; i < nPlayers; i++) {
    		while(correctName == false) {
    			System.out.print("-Enter " + (i+1) + "° player's name: ");
    			playerName = sc.next();
				if(playerName == null || playerName.isEmpty())
					System.out.println("Player name can't be null or empty . Please try again.");
				else if (playerName.length() < 3)
					System.out.println("Player name must be at least 3 characters long. Please try again.");
				else if (playerName.length() > 10)
					System.out.println("Player name can't exceed 10 characters long. Please try again.");
				else if(i > 0) {
					if(checkUsernameAlreadyTaken(playerName) == true)
						System.out.println("Player name has already been taken. Please try again.");
					else
						correctName = true;
				}
				else 
					correctName = true;
    		}
    		
    		Player p;
            p = new Player(playerName, 
	 					   new Bookshelf(),
	 					   new PersonalGoalCard(),
	 					   true);
            addPlayer(p);
                        
            System.out.println("Added " + (i+1) + "° player, named: " + playerName);
            correctName = false;
    	}
	}

	// Return true if Username has already been taken
	private boolean checkUsernameAlreadyTaken(String playerName) {
		for(int i = 0; i < players.size(); i++) {
			Player currentPlayer = players.get(currentPlayerIndex);
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
