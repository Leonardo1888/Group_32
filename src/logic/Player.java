package logic;

public class Player {
	private int id;
	private String username;
	private Bookshelf shelf;
	
	public Player (int id, String username, Bookshelf shelf) {
		this.id = id;
		this.username = username;
		this.shelf = shelf;
	}
}
