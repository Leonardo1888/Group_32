
package logic;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PersonalGoalCard implements Matrix{
	private int row = 6;
	private int col = 5;
	
	private Tail[][] personalGoalCard;
	private boolean[] controlTail = new boolean[8];

	public PersonalGoalCard() {		//fills matrix with E(mpty) and controlTail cells with false
		this.personalGoalCard = new Tail[row][col];
		for(int i = 0; i < row; i++) {	
			for(int j = 0; j < col; j++) {
				personalGoalCard[i][j] = Tail.E;
			}
		}
		for(int i = 0; i < 8; i++) {
			this.controlTail[i] = false;
		}
		
		generateRandom();
	}
	
	
	public void generateRandom() {
		Random random = new Random();
		
		 while(controlTail[2] == false || controlTail[3] == false || controlTail[4] == false || controlTail[5] == false || controlTail[6] == false || controlTail[7] == false){
			
			int tailIndex = ThreadLocalRandom.current().nextInt(2, 7 + 1);	//random val from 2 to 7 inclusive (e.c. only tails, not empty(E) or fillable(X))
			Tail tail = Tail.values()[tailIndex];
			
			if(this.controlTail[tailIndex] == false) {
				int row = ThreadLocalRandom.current().nextInt(0, 5 + 1);
				int col = ThreadLocalRandom.current().nextInt(0, 4 + 1);
				
				if(personalGoalCard[row][col] == Tail.E) {
					this.personalGoalCard[row][col] = tail;	//insert
					this.controlTail[tailIndex] = true;
				}
			}
			
		}
	}
	
	
	public void printBoard() {
		Matrix.printMatrix(personalGoalCard, row, col);
	}
}