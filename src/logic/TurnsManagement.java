package logic;

import commonGoalsPackage.*;
import java.util.*;

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

	// manages all the turns of the game
	private void startGame() {
		System.out.println("\n------------------------ START GAME OF MY SHELFIE ---------------------------\n");
		initializeCommonGoals();

		while (!gameOver) {
			Player currentPlayer = players.get(currentPlayerIndex);

			Turn t = new Turn(board, currentPlayer, currentPlayer.getShelf(), currentPlayer.getPersonalGoalCard(),
					this.sc, this.turnCounter, this.CommonGoalA, this.CommonGoalB);

			this.turnCounter++;

			t.playTurn();
			turns.add(t);

			if (currentPlayerIndex == (players.size() - 1))
				currentPlayerIndex = 0;
			else
				currentPlayerIndex++;

			this.gameOver = t.isGameOver();
		}
		gameOver();
	}

	// Called when game is over
	private void gameOver() {
		System.out.println("\n------------------------ GAME OVER ---------------------------\n");

		// Call personal goal card points
		for (int i = 0; i < players.size(); i++) {
			Player currentPlayer = players.get(i);
			Bookshelf bookshelf = currentPlayer.getShelf();
			Tail[][] shelfMatrix = bookshelf.getShelf();
			PersonalGoalCard pgc = currentPlayer.getPersonalGoalCard();

			// call adjacent tails groups points
			int pointsPGC = countPersonalGoalPoints(shelfMatrix, pgc);

			// print each group and total points of adjacent groups
			int pointsADJ = currentPlayer.getShelf().AdjacentPoints();

			currentPlayer.sumPoints(pointsPGC);
			currentPlayer.sumPoints(pointsADJ);

			System.out.println("-Player " + currentPlayer.getUsername() + " has a total of: "
					+ currentPlayer.getPoints() + " points.");
		}
		printWinner();
	}

	// Print the winner of the game
	private void printWinner() {
		System.out.println("\n------------------------ WINNER ---------------------------\n");

		int[] listPoints = new int[nPlayers];
		String[] listUsernames = new String[nPlayers];

		String usernameWinner = "";
		boolean drawn = false;
		List<String> winners = new ArrayList<>();

		int indexInList = 0;

		for (int i = 0; i < nPlayers; i++) {
			Player currentPlayer = players.get(i);
			listPoints[i] = currentPlayer.getPoints();
			listUsernames[i] = currentPlayer.getUsername();
		}

		int maxPoints = listPoints[0]; // Initialize maxPoints with the first element

		// Look for the biggest number of points
		for (int i = 1; i < listPoints.length; i++) {
			if (listPoints[i] > maxPoints) {
				maxPoints = listPoints[i];
				indexInList = i;
			}
		}

		// Look for drawn
		for (int i = 0; i < nPlayers; i++) {
			if (i != indexInList && listPoints[i] == maxPoints) {
				drawn = true;
				winners.add(listUsernames[i]);
			}
		}

		if (drawn) {
			// Handle the drawn scenario
			StringBuilder drawnPlayers = new StringBuilder();
			drawnPlayers.append("There is a draw between players ");
			drawnPlayers.append(listUsernames[indexInList]);
			for (String winner : winners) {
				drawnPlayers.append(" and ");
				drawnPlayers.append(winner);
			}
			drawnPlayers.append(". They have ");
			drawnPlayers.append(maxPoints);
			drawnPlayers.append(" points.");
			System.out.println(drawnPlayers.toString());
		} else {
			// Handle the single winner scenario
			usernameWinner = listUsernames[indexInList];
			System.out.println("Player " + usernameWinner + " has won with " + maxPoints + " points.");
		}

	}

	// Count the personal goal card points
	private int countPersonalGoalPoints(Tail[][] shelfMatrix, PersonalGoalCard pgc) {
		int cont = pgc.tailsMatched(shelfMatrix);
		int points = pgc.countPoints(cont);
		return points;
	}

	// Initialize the common goals with explanation for the user
	private void initializeCommonGoals() {
		CommonGoalA = createCommonGoal();
		CommonGoalB = createCommonGoal();

		System.out.println("The common goal cards grant points the players who achieve the illustrated pattern.");
		System.out.println(
				"There are two common goals, and we will call them 'A' and 'B'. \nThose are the two common goals you have to achieve: ");
		System.out.println("\n--Common goal A: \n");
		CommonGoalA.printCommonGoal();

		System.out.println("\n--Common goal B: \n");
		CommonGoalB.printCommonGoal();
	}

	// Create common a single common goal randomly
	private CommonGoal createCommonGoal() {
		Random random = new Random();
		int index = random.nextInt(12) + 1;
		while (index == this.index)
			index = random.nextInt(12) + 1;
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
		 * 
		 * Random random = new Random(); this.commonGoal1 =
		 * ThreadLocalRandom.current().nextInt(1, 12 + 1); this.commonGoal2 =
		 * ThreadLocalRandom.current().nextInt(1, 12 + 1); while (this.commonGoal2 ==
		 * this.commonGoal1) { this.commonGoal2 = ThreadLocalRandom.current().nextInt(1,
		 * 12 + 1); } System.out.println("\n" + CommonGoal1 + "."); commonGoalCard1 =
		 * generateCommonGoalCard(this.commonGoal1); System.out.println("\n" +
		 * getInfo(CommonGoal1)); System.out.println("\n"CommonGoal2al2 + ".");
		 * commonGoalCard2 = generateCommonGoalCard(this.commonGoal2);
		 * System.out.println("\n" + getInCommonGoal2al2));
		 */
	}

	// Add the turn to the list of turns, although it is not really used
	public void addTurn(Turn t) {
		if (t == null) {
			throw new NullPointerException("Turn is null");
		}
		this.turns.add(t);
	}

	/* ---------- Getter and setter ---------- */

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
