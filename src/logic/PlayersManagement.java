package logic;

import java.util.*;

public class PlayersManagement {

	private Player firstPlayer; // player with chair
	private int nPlayers;

	private List<Player> players;

	public PlayersManagement() {
		this.players = new ArrayList<>();

	}

	// Enter players and saves them in "players"
	public void enterPlayers () throws IllegalArgumentException {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("\nEnter the number of players [2-4]: ");
			int nPlayers = sc.nextInt();
			if(nPlayers >= 2 || nPlayers <= 4) 
				break;
			else
				System.out.println("ERROR! Number of players must be 2, 3 or 4.");
		}
		setnPlayers(nPlayers);   
		
    	String playerName = null;
    	for(int i = 0; i < nPlayers; i++) {
			boolean correctName = false;;
    		while(!correctName) {
	    		System.out.print("Enter " + (i+1) + "° player's name: ");
				playerName = sc.nextLine();
				if(playerName == null || playerName.isEmpty()) 
					System.out.println("Player name can't be null or empty . Please try again.");
				else if (playerName.length() < 3)
					System.out.println("Player name must be at least 3 characters long. Please try again.");
				else if (playerName.length() > 10)
					System.out.println("Player name can't exceed 10 characters long. Please try again.");
				else 
					correctName = true;
    		}
    		Player p;
    		if(i==0) {
            	p = new Player(playerName, 
 					   new Bookshelf(), 
 					   new PersonalGoalCard(),
 					   true);
            } else {
	            p = new Player(playerName, 
					   new Bookshelf(), 
					   new PersonalGoalCard(),
					   false);
            }
            addPlayer(p);
            System.out.println("");
    	}
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
