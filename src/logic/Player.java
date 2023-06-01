package logic;

import java.util.*;

public class Player {
	private int PLAYER_COUNT = 1;
	private final int id;
	
	private String username;
	private Bookshelf shelf;
	private PersonalGoalCard personalGoalCard;
	private int points;
	private boolean isFirstPlayer;
	private boolean checkCommonGoalA;	// If true is checked
	private boolean checkCommonGoalB;	// If true is checked
	/*
	turnsmanagemente -> fine turno
		if(checkCommonGoal1 == false)
			player.checkCommonGoal1 = CommonGoal1.check();
		if(checkCommonGoal2 == false)
			player.checkCommonGoal2 = CommonGoal2.check();
	 */
	
	public Player(String username, Bookshelf shelf, PersonalGoalCard personalGoal, boolean isFirstPlayer) {
		this.id = PLAYER_COUNT++;
		this.username = username;
		this.shelf = shelf;
		this.personalGoalCard = personalGoal;
		this.isFirstPlayer = isFirstPlayer;
		this.checkCommonGoalA = false;
		this.checkCommonGoalB = false;
		this.points = 0;
	}

	public void sumPoints(int addedPoints) {
		this.points = this.points + addedPoints;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public int getPLAYER_COUNT() {
		return PLAYER_COUNT;
	}

	public void setPLAYER_COUNT(int pLAYER_COUNT) {
		PLAYER_COUNT = pLAYER_COUNT;
	}

	public Bookshelf getShelf() {
		return this.shelf;
	}

	public void setShelf(Bookshelf shelf) {
		this.shelf = shelf;
	}

	public PersonalGoalCard getPersonalGoalCard() {
		return personalGoalCard;
	}

	public void setPersonalGoalCard(PersonalGoalCard personalGoal) {
		this.personalGoalCard = personalGoal;
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
	
	public boolean getCommonGoalA(){
		return this.checkCommonGoalA;
	}
	
	public boolean getCommonGoalB(){
		return this.checkCommonGoalB;
	}
	
	public void setCommonGoalA(Boolean t){
		this.checkCommonGoalA = t;
	}
	
	public void setCommonGoalB(Boolean t){
		this.checkCommonGoalB = t;
	}
	
}