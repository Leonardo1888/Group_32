package logic;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CommonGoals implements Matrix {
	private int commonGoal1;
	private int commonGoal2;
	
	public CommonGoals() {
		Random random = new Random();
		this.commonGoal1 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		this.commonGoal2 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		while(this.commonGoal2 == this.commonGoal1) {
			this.commonGoal2 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		}
	}
	
	public boolean checkCommonGoals(int goal, Tail shelf[][]) {
		switch(goal) {
		case 1:
			return check1(shelf);
		case 2:
			return check2(shelf);
		case 3:
			return check3(shelf);
		case 4:
			return check4(shelf);
		case 5:
			return check5(shelf);
		case 6:
			return check6(shelf);
		case 7:
			return check7(shelf);
		case 8:
			return check8(shelf);
		case 9:
			return check9(shelf);
		case 10:
			return check10(shelf);
		case 11:
			return check11(shelf);
		case 12:
			return check12(shelf);
		}
		return false;
	}
	
	public boolean check1(Tail shelf[][]){	//TODO non fatto
		Tail[][] mirrorShelf = Matrix.copyMatrix(shelf, 5,6);
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				int cont = 0;
				while(Matrix.checkOrthogonally(mirrorShelf, i, j)[0] == true) {
					
				}
				
			}
			
		}
		return false;
	}
	
	public boolean check2(Tail shelf[][]){
		return false;
	}
	
	public boolean check3(Tail shelf[][]){
		if(shelf[0][0] != Tail.E && shelf[0][0].equals(shelf[0][4]) && shelf[0][0].equals(shelf[5][4]) && shelf[0][0].equals(shelf[5][0])) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean check4(Tail shelf[][]){
		return false;
	}
	
	public boolean check5(Tail shelf[][]){
		return false;
	}
	
	public boolean check6(Tail shelf[][]){
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				int cont = 0;
				if(shelf[i][j] == Tail.C) {
					cont++;
				}
				if(cont >= 8) {
					return true;
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				int cont = 0;
				if(shelf[i][j] == Tail.B) {
					cont++;
				}
				if(cont >= 8) {
					return true;
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				int cont = 0;
				if(shelf[i][j] == Tail.G) {
					cont++;
				}
				if(cont >= 8) {
					return true;
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				int cont = 0;
				if(shelf[i][j] == Tail.F) {
					cont++;
				}
				if(cont >= 8) {
					return true;
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				int cont = 0;
				if(shelf[i][j] == Tail.T) {
					cont++;
				}
				if(cont >= 8) {
					return true;
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				int cont = 0;
				if(shelf[i][j] == Tail.P) {
					cont++;
				}
				if(cont >= 8) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean check7(Tail shelf[][]){
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				if(shelf[i][j] != Tail.E && shelf[i][j].equals(shelf[i+1][j+1]) && shelf[i][j].equals(shelf[i+2][j+2]) && shelf[i][j].equals(shelf[i+3][j+3]) && shelf[i][j].equals(shelf[i+4][j+4])) {
					return true;	
				}
			}
		}
		return false;
	}
	
	public boolean check8(Tail shelf[][]){
		return false;
	}
	
	public boolean check9(Tail shelf[][]){
		int cont = 0;
		for(int j = 0; j < 5; j++) {
			Tail[] tails = new Tail[6];
			for(int i = 0; i < 6; i++) {
				tails[i] = shelf[i][j];
			}
			if(Matrix.EinCol(shelf, j) == false && Matrix.differentArray(tails, 6) == true) {
				cont++;
			}
		}
		if(cont >= 2) {
			return true;
		}
		return false;
	}
	
	public boolean check10(Tail shelf[][]){
		int cont = 0;
		for(int i = 0; i < 6; i++) {
			Tail[] tails = new Tail[5];
			for(int j = 0; j < 5; j++) {
				tails[j] = shelf[i][j];
			}
			if(Matrix.EinRow(shelf, i) == false && Matrix.differentArray(tails, 5) == true) {
				cont++;
			}
		}
		if(cont >= 2) {
			return true;
		}
		return false;
	}
	
	
	public boolean check11(Tail shelf[][]){
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				if(shelf[i][j] != Tail.E && shelf[i][j].values().equals(shelf[i+2][j]) && shelf[i][j].values().equals(shelf[i+2][j+2]) && shelf[i][j].values().equals(shelf[i][j+2]) && shelf[i][j].values().equals(shelf[i+1][j+1])) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean check12(Tail shelf[][]){
		return false;
	}
}
