package TicTacToeGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToeBot {
	private char[][] table = new char[][] {
		{'1','2','3'},
		{'4','5','6'},
		{'7','8','9'}
	};
	private char tag;
	private char enemyTag;
	
	public TicTacToeBot(char tag, char _enemyTag){
		this.tag = tag;
		this.enemyTag = _enemyTag;
	}
	
	public char getTag() {
		return this.tag;
	}
	
	public void SeeTheTable(char[][] arr) {
		List<Character> chars = new ArrayList<Character>();
		for(int i = 0; i < arr.length; i++) {
			for(int j = 1; j < arr[i].length; j += 2) {
				chars.add(arr[i][j]);
			}
		}
		int index = 0;
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				table[i][j] = chars.get(index);
				index++;
			}
		}
	}
	
	public char getDecision() {
		
		char position = checkForWinningSituation();
		if (position != '0')
			return position;
		else
			return GetFreePosition();
	}
	
	private char checkForWinningSituation() {
		int enemyTags = 0;
		int botTags = 0;
		char position = '0';
		char safeGamePosition = '0';
		
		//check rows
		
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				if(table[i][j] == this.tag)
					botTags++;
				else if(table[i][j] == this.enemyTag)
					enemyTags++;
				else position = table[i][j];
			}
			if(botTags == 2 && enemyTags == 0)
				return position;
			else if(enemyTags == 2 && botTags == 0)
				safeGamePosition = position;
			botTags = 0;
			enemyTags = 0;
		}
		
		//check columns
		
		for(int j = 0; j < table[0].length; j++) {
			for(int i = 0; i < table.length; i++) {
				if(table[i][j] == this.tag)
					botTags++;
				else if(table[i][j] == this.enemyTag)
					enemyTags++;
				else position = table[i][j];
			}
			if(botTags == 2 && enemyTags == 0)
				return position;
			else if(enemyTags == 2 && botTags == 0)
				safeGamePosition = position;
			botTags = 0;
			enemyTags = 0;
		}
		
		//check first diagonal
		
		for(int i = 0, j = 0; i < table.length; i++, j ++) {
			if(table[i][j] == this.tag)
				botTags++;
			else if(table[i][j] == this.enemyTag)
				enemyTags++;
			else position = table[i][j];
		}
		
		if(botTags == 2 && enemyTags == 0)
			return position;
		else if(enemyTags == 2 && botTags == 0)
			safeGamePosition = position;
		
		botTags = 0;
		enemyTags = 0;
		//check second diagonal
		
		for(int i = 0, j = table.length - 1; i < table.length; i++, j --) {
			if(table[i][j] == this.tag)
				botTags++;
			else if(table[i][j] == this.enemyTag)
				enemyTags++;
			else position = table[i][j];
		}
		
		if(botTags == 2 && enemyTags == 0)
			return position;
		else if(enemyTags == 2 && botTags == 0)
			safeGamePosition = position;
		
		//if no winning situation return safe move
		return safeGamePosition;
	}
	

	private char GetFreePosition(){
		List<Character> freePositions = new ArrayList<Character>();
		
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[i].length; j++) {
				if(Character.isDigit(table[i][j]))
					freePositions.add(table[i][j]);
			}
		}
		
		Random rand = new Random();
		
		return freePositions.get(rand.nextInt(freePositions.size()));
	}
}
