package TicTacToeGame;

public class Player {
	private char tag;
	private String Name;
	
	public Player(char tag, String name){
		this.tag = tag;
		this.Name = name;
	}
	
	public char getTag() {
		return this.tag;
	}
	
	public String getName() {
		return this.Name;
	}
}

