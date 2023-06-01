package init;

import logic.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IllegalArgumentException {

		Scanner sc = new Scanner(System.in);
		PlayersManagement pManagement = new PlayersManagement(sc);
		pManagement.enterPlayers();
		
		Board board = new Board(pManagement.getnPlayers());
		
		TurnsManagement turnsManagement = new TurnsManagement(pManagement.getPlayers(), board, sc);
		sc.close();
	}
}