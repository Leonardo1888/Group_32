package logic;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PersonalGoalCard implements Matrix {

	private final int ROW = 6;
	private final int COL = 5;

	private Tail[][] personalGoalCard;
	private boolean[] controlTail = new boolean[8];

	public PersonalGoalCard() { // fills matrix with E(mpty) and controlTail cells with false
		this.personalGoalCard = new Tail[ROW][COL];
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				personalGoalCard[i][j] = Tail.E;
			}
		}
		for (int i = 0; i < 8; i++) {
			this.controlTail[i] = false;
		}

		generateRandom();
	}

	public void generateRandom() {
		Random random = new Random(); // TODO useless?

		while (controlTail[2] == false || controlTail[3] == false || controlTail[4] == false || controlTail[5] == false
				|| controlTail[6] == false || controlTail[7] == false) {

			int tailIndex = ThreadLocalRandom.current().nextInt(2, 7 + 1); 
			// Random val from 2 to 7 inclusive (e.c. , only tails, not empty(E) or fillable(X))
			Tail tail = Tail.values()[tailIndex];

			if (this.controlTail[tailIndex] == false) {
				int row = ThreadLocalRandom.current().nextInt(0, 5 + 1);
				int col = ThreadLocalRandom.current().nextInt(0, 4 + 1);

				if (personalGoalCard[row][col] == Tail.E) {
					this.personalGoalCard[row][col] = tail; // insert
					this.controlTail[tailIndex] = true;
				}
			}

		}
	}
	
	// @Returns the number of tails matched with the personal card
	public int tailsMatched(Tail shelf[][]){	
		int cont = 0;
		
		for(int i = 0; i < ROW; i++){
			for(int j = 0; j < COL; j++){
				if(shelf[i][j] == this.personalGoalCard[i][j] && this.personalGoalCard[i][j] != Tail.E ){
					cont++;
				}
			}
		}
		return cont;
	}
	
	// @Return the points
	public int countPoints(int cont){
		switch(cont){
			case 0:
				return 0;
			case 1:
				return 1;
			case 2:
				return 2;
			case 3:
				return 4;
			case 4:
				return 6;
			case 5:
				return 9;
			case 6:
				return 12;
		}
		System.out.println("Error countPoints <0 || >6");
		return 0;
	}	
	
	public void printBoard() {
		Matrix.printMatrix(personalGoalCard, ROW, COL);
	}
	
	public void printRowPersonalGoalCard(int row) {
		if (row == -1) {
			for (int a = 1; a <= this.COL; a++) {
				System.out.print(a + " ");
			}
		} else {

			for (int j = 0; j < this.COL; j++) {
				if (personalGoalCard[row][j] == Tail.C) {
					System.out.print(Color.GREEN);
					System.out.print("C");
					System.out.print(Color.RESET);
				}
				if (personalGoalCard[row][j] == Tail.B) {
					System.out.print(Color.WHITE);
					System.out.print("B");
					System.out.print(Color.RESET);
				}
				if (personalGoalCard[row][j] == Tail.G) {
					System.out.print(Color.YELLOW);
					System.out.print("G");
					System.out.print(Color.RESET);
				}
				if (personalGoalCard[row][j] == Tail.F) {
					System.out.print(Color.BLUE);
					System.out.print("F");
					System.out.print(Color.RESET);
				}
				if (personalGoalCard[row][j] == Tail.T) {
					System.out.print(Color.CYAN);
					System.out.print("T");
					System.out.print(Color.RESET);
				}
				if (personalGoalCard[row][j] == Tail.P) {
					System.out.print(Color.RED);
					System.out.print("P");
					System.out.print(Color.RESET);
				}
				if (personalGoalCard[row][j] == Tail.E || personalGoalCard[row][j] == Tail.X) {
					System.out.print(Color.BLACK);
					System.out.print("#");
					System.out.print(Color.RESET);
				}
				System.out.print(" ");
			}
		}
	}
	
	public Tail[][] getPersonalGoalCard() {
		return personalGoalCard;
	}

	public void setPersonalGoalCard(Tail[][] personalGoalCard) {
		this.personalGoalCard = personalGoalCard;
	}
}