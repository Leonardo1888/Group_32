//
package logic;

import java.util.*;

public class Player {
	private int PLAYER_COUNT = 1;
	private final int id;
	
	private String username;
	private Bookshelf shelf;
	private PersonalGoalCard personalGoal;

	public Player(String username, Bookshelf shelf, PersonalGoalCard personalGoal) {
		this.id = PLAYER_COUNT++;
		this.username = username;
		this.shelf = shelf;
		this.personalGoal = personalGoal;
	}

	public int getPLAYER_COUNT() {
		return PLAYER_COUNT;
	}

	public void setPLAYER_COUNT(int pLAYER_COUNT) {
		PLAYER_COUNT = pLAYER_COUNT;
	}

	public Bookshelf getShelf() {
		return shelf;
	}

	public void setShelf(Bookshelf shelf) {
		this.shelf = shelf;
	}

	public PersonalGoalCard getPersonalGoal() {
		return personalGoal;
	}

	public void setPersonalGoal(PersonalGoalCard personalGoal) {
		this.personalGoal = personalGoal;
	}

	public int getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}
}