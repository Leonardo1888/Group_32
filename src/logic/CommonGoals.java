package logic;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CommonGoals implements Matrix { // this class generates an object that contains 2 cards
	private int commonGoal1;
	private int commonGoal2; // int for random choice
	private Tail[][] commonGoalCard1;
	private Tail[][] commonGoalCard2; // matrix card that contains the tail arrangement

	public CommonGoals() {
		Random random = new Random();
		this.commonGoal1 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		this.commonGoal2 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		while (this.commonGoal2 == this.commonGoal1) {
			this.commonGoal2 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		}
		System.out.println("\n" + commonGoal1 + ".");
		commonGoalCard1 = generateCommonGoalCard(this.commonGoal1);
		System.out.println("\n" + getInfo(commonGoal1));
		System.out.println("\n" + commonGoal2 + ".");
		commonGoalCard2 = generateCommonGoalCard(this.commonGoal2);
		System.out.println("\n" + getInfo(commonGoal2));
	}

	public Tail[][] generateCommonGoalCard(int index) {
		Tail[][] commonGoalCard;
		int row = 0;
		int col = 0;
		commonGoalCard = new Tail[row][col];
		switch (index) {
		case 1:
			row = 2;
			col = 1;
			commonGoalCard = new Tail[row][col];
			for (int i = 0; i < row; i++) {
				commonGoalCard[i][0] = Tail.S;
			}
			Matrix.printMatrix(commonGoalCard, row, col);
			System.out.println("\nx6");
			return commonGoalCard;
		case 2:
			row = 4;
			col = 1;
			commonGoalCard = new Tail[row][col];
			for (int i = 0; i < row; i++) {
				commonGoalCard[i][0] = Tail.S;
			}
			Matrix.printMatrix(commonGoalCard, row, col);
			System.out.println("\nx4");
			return commonGoalCard;
		case 3:
			row = 6;
			col = 5;
			commonGoalCard = new Tail[row][col];
			commonGoalCard = Matrix.FillWithE(commonGoalCard, row, col);
			commonGoalCard[0][0] = Tail.S;
			commonGoalCard[0][4] = Tail.S;
			commonGoalCard[5][0] = Tail.S;
			commonGoalCard[5][4] = Tail.S;
			Matrix.printMatrix(commonGoalCard, row, col);
			return commonGoalCard;
		case 4:
			row = 2;
			col = 2;
			commonGoalCard = new Tail[row][col];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					commonGoalCard[i][j] = Tail.S;
				}
			}
			Matrix.printMatrix(commonGoalCard, row, col);
			System.out.println("\nx2");
			return commonGoalCard;
		case 5:
			row = 6;
			col = 1;
			commonGoalCard = new Tail[row][col];
			for (int i = 0; i < row; i++) {
				commonGoalCard[i][0] = Tail.X;
			}
			Matrix.printMatrix(commonGoalCard, row, col);
			System.out.println("\nx3, max 3 different");
			return commonGoalCard;
		case 6:
			row = 3;
			col = 3;
			commonGoalCard = new Tail[row][col];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					commonGoalCard[i][j] = Tail.S;
				}
			}
			commonGoalCard[0][1] = Tail.E;
			Matrix.printMatrix(commonGoalCard, row, col);
			return commonGoalCard;
		case 7:
			row = 5;
			col = 5;
			commonGoalCard = new Tail[row][col];
			commonGoalCard = Matrix.FillWithE(commonGoalCard, row, col);
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (i == j) {
						commonGoalCard[i][j] = Tail.S;
					}
				}
			}
			Matrix.printMatrix(commonGoalCard, row, col);
			return commonGoalCard;
		case 8:
			row = 1;
			col = 5;
			commonGoalCard = new Tail[row][col];
			for (int j = 0; j < col; j++) {
				commonGoalCard[0][j] = Tail.X;
			}
			Matrix.printMatrix(commonGoalCard, row, col);
			System.out.println("\nx4, max 3 different");
			return commonGoalCard;
		case 9:
			row = 6;
			col = 1;
			commonGoalCard = new Tail[row][col];
			for (int i = 0; i < row; i++) {
				commonGoalCard[i][0] = Tail.D;
			}
			Matrix.printMatrix(commonGoalCard, row, col);
			System.out.println("\nx2");
			return commonGoalCard;
		case 10:
			row = 1;
			col = 5;
			commonGoalCard = new Tail[row][col];
			for (int j = 0; j < col; j++) {
				commonGoalCard[0][j] = Tail.D;
			}
			Matrix.printMatrix(commonGoalCard, row, col);
			System.out.println("\nx2");
			return commonGoalCard;
		case 11:
			row = 3;
			col = 3;
			commonGoalCard = new Tail[row][col];
			commonGoalCard = Matrix.FillWithE(commonGoalCard, row, col);
			int cont = 1;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (cont % 2 != 0) {
						commonGoalCard[i][j] = Tail.S;
					}
					cont++;
				}
			}
			Matrix.printMatrix(commonGoalCard, row, col);
			return commonGoalCard;
		case 12:
			row = 5;
			col = 5;
			commonGoalCard = new Tail[row][col];
			commonGoalCard = Matrix.FillWithE(commonGoalCard, row, col);
			for (int i = row - 1; i >= 0; i--) {
				for (int j = 0; j < col; j++) {
					commonGoalCard[i][j] = Tail.X;
				}
				col--;
			}
			col = 5;
			Matrix.printMatrix(commonGoalCard, row, col);
			return commonGoalCard;
		}
		return commonGoalCard;
	}

	public boolean checkCommonGoals(int goal, Tail shelf[][]) {
		switch (goal) {
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

	public boolean check1(Tail shelf[][]) { // TODO non fatto
		Tail[][] mirrorShelf = Matrix.copyMatrix(shelf, 5, 6);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				while (Matrix.checkOrthogonally(mirrorShelf, i, j)[0] == true) {

				}

			}

		}
		return false;
	}

	public boolean check2(Tail shelf[][]) {
		return false;
	}

	public boolean check3(Tail shelf[][]) {
		if (shelf[0][0] != Tail.E && shelf[0][0].equals(shelf[0][4]) && shelf[0][0].equals(shelf[5][4])
				&& shelf[0][0].equals(shelf[5][0])) {
			return true;
		} else {
			return false;
		}
	}

	public boolean check4(Tail shelf[][]) {
		return false;
	}

	public boolean check5(Tail shelf[][]) {
		return false;
	}

	public boolean check6(Tail shelf[][]) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				if (shelf[i][j] == Tail.C) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				if (shelf[i][j] == Tail.B) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				if (shelf[i][j] == Tail.G) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				if (shelf[i][j] == Tail.F) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				if (shelf[i][j] == Tail.T) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int cont = 0;
				if (shelf[i][j] == Tail.P) {
					cont++;
				}
				if (cont >= 8) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean check7(Tail shelf[][]) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (shelf[i][j] != Tail.E && shelf[i][j].equals(shelf[i + 1][j + 1])
						&& shelf[i][j].equals(shelf[i + 2][j + 2]) && shelf[i][j].equals(shelf[i + 3][j + 3])
						&& shelf[i][j].equals(shelf[i + 4][j + 4])) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean check8(Tail shelf[][]) {
		return false;
	}

	public boolean check9(Tail shelf[][]) {
		int cont = 0;
		for (int j = 0; j < 5; j++) {
			Tail[] tails = new Tail[6];
			for (int i = 0; i < 6; i++) {
				tails[i] = shelf[i][j];
			}
			if (Matrix.EinCol(shelf, j) == false && Matrix.differentArray(tails, 6) == true) {
				cont++;
			}
		}
		if (cont >= 2) {
			return true;
		}
		return false;
	}

	public boolean check10(Tail shelf[][]) {
		int cont = 0;
		for (int i = 0; i < 6; i++) {
			Tail[] tails = new Tail[5];
			for (int j = 0; j < 5; j++) {
				tails[j] = shelf[i][j];
			}
			if (Matrix.EinRow(shelf, i) == false && Matrix.differentArray(tails, 5) == true) {
				cont++;
			}
		}
		if (cont >= 2) {
			return true;
		}
		return false;
	}

	public boolean check11(Tail shelf[][]) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				if (shelf[i][j] != Tail.E && shelf[i][j].values().equals(shelf[i + 2][j])
						&& shelf[i][j].values().equals(shelf[i + 2][j + 2])
						&& shelf[i][j].values().equals(shelf[i][j + 2])
						&& shelf[i][j].values().equals(shelf[i + 1][j + 1])) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean check12(Tail shelf[][]) {
		return false;
	}

	public String getInfo(int index) {
		String infoMsg = new String();
		switch (index) {
		case 1:
			infoMsg = ("Six groups each containing at least\r\n" + "2 tiles of the same type (not necessarily\r\n"
					+ "in the depicted shape).\r\n" + "The tiles of one group can be different\r\n"
					+ "from those of another group.\r\n" + "");
		case 2:
			infoMsg = ("Four groups each containing at least\r\n" + "4 tiles of the same type (not necessarily\r\n"
					+ "in the depicted shape).\r\n" + "The tiles of one group can be different\r\n"
					+ "from those of another group.");
		case 3:
			infoMsg = ("Four tiles of the same type in the four\r\n" + "corners of the bookshelf.");
		case 4:
			infoMsg = ("Two groups each containing 4 tiles of\r\n" + "the same type in a 2x2 square. The tiles\r\n"
					+ "of one square can be different from\r\n" + "those of the other square.");
		case 5:
			infoMsg = ("Three columns each formed by 6 tiles Five tiles of the same type forming an X.\r\n"
					+ "of maximum three different types. One\r\n" + "column can show the same or a different\r\n"
					+ "combination of another column.");
		case 6:
			infoMsg = ("Eight tiles of the same type. There’s no\r\n" + "restriction about the position of these\r\n"
					+ "tiles.");
		case 7:
			infoMsg = ("Five tiles of the same type forming a\r\n" + "diagonal.");
		case 8:
			infoMsg = ("Four lines each formed by 5 tiles of\r\n" + "maximum three different types. One\r\n"
					+ "line can show the same or a different\r\n" + "combination of another line.\r\n" + "");
		case 9:
			infoMsg = ("Two columns each formed by 6\r\n" + "different types of tiles.");
		case 10:
			infoMsg = ("Two lines each formed by 5 different\r\n" + "types of tiles. One line can show the\r\n"
					+ "same or a different combination of the\r\n" + "other line.");
		case 11:
			infoMsg = ("Five tiles of the same type forming an X");
		case 12:
			infoMsg = ("Five columns of increasing or decreasing\r\n" + "height. Starting from the first column on\r\n"
					+ "the left or on the right, each next column\r\n" + "must be made of exactly one more tile.\r\n"
					+ "Tiles can be of any type.");
		}
		return infoMsg;
	}
}
