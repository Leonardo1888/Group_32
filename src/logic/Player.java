//
package logic;

public class Player {
	private int id;
	private String username;
	private Bookshelf shelf;
	private PersonalGoalCard personalGoal;
	
	public Player (int id, String username, Bookshelf shelf, PersonalGoalCard personalGoal) {
		this.id = id;
		this.username = username;
		this.shelf = shelf;
		this.personalGoal = personalGoal;
	}
	
	public String getUsername() {
		return this.username;
	}
}