package logic;

import java.util.*;

public class PlayersManagement {

	private Player firstPlayer; // player with chair
	private int nPlayers;

	private List<Player> players;
	Scanner sc;

	public PlayersManagement(Scanner sc) {
		this.players = new ArrayList<>();
		this.sc = sc;

	}

	/*
	 * The user enters the number of players. The user enters a username for each
	 * player, and saves them in "players"
	 */
	public void enterPlayers() {

		nPlayers = 0;
		String testInput;

		while (true) {
			System.out.print("\nEnter the number of players [2-4]: ");
			testInput = sc.nextLine();

			if (testInput.isEmpty()) {
				System.out.println("Error: Please enter a number in the range [2-4].");
				continue;
			}

			try {
				nPlayers = Integer.parseInt(testInput); // Parse string into number
				if (nPlayers < 2 || nPlayers > 4) {
					System.out.println("Error: Please enter a number in the range [2-4].");
					continue;
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("You have to enter a number!");
				continue;
			}
		}

		String playerUsername = "";
		boolean correctUsername = false;

		// Username checks
		for (int i = 0; i < nPlayers; i++) {
			while (!correctUsername) {
				System.out.print("-Enter " + (i + 1) + "° player's name: ");
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

			/*
			 * This piece of comment is used for testing common goals. Keep for testing. If
			 * you want to test when creating a new user at line 88 substitute
			 * "new Booshelf()" with "bs"
			 * 
			 * Tail[][] s = { {Tail.C, Tail.T, Tail.P, Tail.E, Tail.C}, {Tail.C, Tail.T,
			 * Tail.P, Tail.P, Tail.P}, {Tail.T, Tail.P, Tail.P, Tail.P, Tail.P}, {Tail.T,
			 * Tail.P, Tail.P, Tail.T, Tail.P}, {Tail.T, Tail.T, Tail.P, Tail.P, Tail.P},
			 * {Tail.C, Tail.T, Tail.P, Tail.P, Tail.C} }; Bookshelf bs = new Bookshelf();
			 * bs.setShelf(s);
			 */

			Player p;
			// The first user entered is the first player
			p = new Player(playerUsername, new Bookshelf(), new PersonalGoalCard());
			addPlayer(p);

			System.out.println("Added " + (i + 1) + "° player, named: " + playerUsername);
			correctUsername = false;
		}
	}

	// @Return true if username has already been taken
	private boolean checkUsernameAlreadyTaken(String playerName) {
		for (int i = 0; i < players.size(); i++) {
			Player currentPlayer = players.get(i);
			if (currentPlayer.getUsername().equals(playerName)) {
				return true;
			}
		}
		return false;
	}

	// Add the player to the list of players
	private void addPlayer(Player p) {
		if (p == null) {
			throw new NullPointerException("Player is null");
		}
		this.players.add(p);
	}

	
	/* ---------- Getter and setter ---------- */

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
