package init;

import logic.*;
import commonGoalsPackage.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IllegalArgumentException {

		Scanner sc = new Scanner(System.in);

		PlayersManagement pManagement = new PlayersManagement(sc);
		pManagement.enterPlayers();

		// Generate 2 random commonGoals
		// CommonGoals commonGoals = new CommonGoals();
		
		Board board = new Board(pManagement.getnPlayers());
		
		TurnsManagement turnsManagement = new TurnsManagement(pManagement.getPlayers(), board, sc);
		sc.close();
		
		/*
		 * Common goal cards and shelves. System.out.println("\nCommon Goal Cards:");
		 * CommonGoals commonGoals = new CommonGoals();
		 * 
		 * System.out.println("\n" + Player1.getUsername() + "'s Shelf:");
		 * shelf1.printBoard(); System.out.println("\n" + Player1.getUsername() +
		 * "'s Personal Goal Card:"); personalGoal1.printBoard();
		 * 
		 * System.out.println("\n" + Player2.getUsername() + "'s Shelf:");
		 * shelf2.printBoard() System.out.println("\n" + Player2.getUsername() +
		 * "'s Personal Goal Card:"); personalGoal2.printBoard();
		 */
	}
}