package logic;

public class Utility {
	static public void printTail(Tail t) {
		if (t == Tail.C) {
			System.out.print(Color.GREEN);
			System.out.print("C");
			System.out.print(Color.RESET);
		}
		if (t == Tail.B) {
			System.out.print(Color.WHITE);
			System.out.print("B");
			System.out.print(Color.RESET);
		}
		if (t == Tail.G) {
			System.out.print(Color.YELLOW);
			System.out.print("G");
			System.out.print(Color.RESET);
		}
		if (t == Tail.F) {
			System.out.print(Color.BLUE);
			System.out.print("F");
			System.out.print(Color.RESET);
		}
		if (t == Tail.T) {
			System.out.print(Color.CYAN);
			System.out.print("T");
			System.out.print(Color.RESET);
		}
		if (t == Tail.P) {
			System.out.print(Color.RED);
			System.out.print("P");
			System.out.print(Color.RESET);
		}
		if (t == Tail.E || t == Tail.X) {
			System.out.print(Color.BLACK);
			System.out.print("#");
			System.out.print(Color.RESET);
		}
	}
}
