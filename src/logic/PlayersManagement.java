package logic;

import java.util.*;

public class PlayersManagement {

	private Player firstPlayer; // player with chair
	private int nPlayers;

	private List<Player> players;

	public PlayersManagement() {
		this.players = new ArrayList<>();
		
	}
	
	public void enterPlayers() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("\nEnter the number of players [2-4]: ");
			int nPlayers = sc.nextInt();
			if(nPlayers < 2 || nPlayers > 4) 
				System.out.println("ERROR! Number of players must be 2, 3 or 4.");
		} while(nPlayers < 2 || nPlayers > 4);
		setnPlayers(nPlayers);   
		
    	String playerName;
    	for(int i = 0; i < nPlayers; i++) {
            System.out.print("Enter " + (i+1) + "° player's name: ");
            playerName = sc.nextLine();
            Player p = new Player(playerName, 
					   new Bookshelf(), 
					   new PersonalGoalCard());
            addPlayer(p);
            if(i==0) 
            	setFirstPlayer(p);
            
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
