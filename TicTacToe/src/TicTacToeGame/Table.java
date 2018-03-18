package TicTacToeGame;
import java.util.Scanner;

public class Table {

	public static void main(String[] args) {
		
		int p1Points = 0;
		int p2Points = 0;
		
		Scanner sc = new Scanner(System.in);
		String str = "Y";
		boolean multiplayer;
		
		while(!str.equals("1")&&!str.equals("2")) {					
			System.out.println("Select mode:");	
			System.out.println("1 - single player mode");	
			System.out.println("2 - multy player mode");	
			str = sc.nextLine();
		}
		
		if(str.equals("1")) {
			multiplayer = false;
		}
		else
			multiplayer = true;

		
		TicTacToeGame.TicTacToeBot tttBot = new TicTacToeBot('o', 'x');
		TicTacToeGame.Player p1 = new Player('x',"p1");
		TicTacToeGame.Player p2 = new Player('z',"p2");
		
		System.out.println("Hello player 1! Please enter your name:\n");
		String p1Name = sc.nextLine();		
		System.out.println("Hello " + p1Name +"! Please enter your tag:\n");
		String p1Tag = sc.nextLine();
		while(!CorrectTag(p1Tag)) {
			System.out.println("Hello " + p1Name +"! Please enter your tag:\n");
			p1Tag = sc.nextLine();
		}
		p1 = new Player(p1Tag.toCharArray()[0], p1Name);
				
		if(multiplayer) {
			System.out.println("Hello player 2! Please enter your name:\n");
			String p2Name = sc.nextLine();
			System.out.println("Hello " + p2Name +"! Please enter your tag:\n");
			String p2Tag = sc.nextLine();
			while(!CorrectTag(p2Tag)) {
				System.out.println("Hello " + p2Name +"! Please enter your tag:\n");
				p2Tag = sc.nextLine();
			}
			p2 = new Player(p2Tag.toCharArray()[0], p2Name);

		}
		else {
			System.out.println(p1Name +", please enter your opponent tag:\n");
			String botTag = sc.nextLine();
			while(!CorrectTag(botTag)) {
				System.out.println(p1Name +", please enter your opponent tag:\n");
				botTag = sc.nextLine();
			}
			tttBot = new TicTacToeBot(botTag.toCharArray()[0], p1Tag.toCharArray()[0]);
		}
		
		str = "y";
		while(!str.equals("n")) {
		
			System.out.println("Scores:");	
			System.out.println("Player 1: " + p1Points);	
			System.out.println("Player 2: " + p2Points);	
			System.out.println();	
						
			int result;
			if(multiplayer)
				result = MultiPlayerGame(sc, p1, p2);
			else
				result = SinglePlayerGame(sc, p1, tttBot);
				
			if(result > 0)
			{
				p1Points++;
				System.out.println("Player 1 WON!");
			}
			else if(result < 0)
			{
				p2Points++;
				System.out.println("Player 2 WON!");
			}
			else
				System.out.println("DRAW!");
	
			System.out.println("Press 'n' to STOP the game or any other button to continue.");
	
			str = sc.nextLine();
		} 
		sc.close();
	}
	
	public static int SinglePlayerGame(Scanner sc, Player _p1, TicTacToeBot _tttBot) {
		TicTacToeGame.TicTacToeBot tttBot = _tttBot;
		TicTacToeGame.Player p1 = _p1;
		char[][] boxes = new char[][] {		
			{'|','1','|','2','|','3','|'},
			{'|','4','|','5','|','6','|'},
			{'|','7','|','8','|','9','|'}
		};
		
		Print2DimArray(boxes);
		System.out.println();
		
		String input;
		while(!Draw(boxes)) {
			
			do {
			System.out.println("Player 1 ["+p1.getTag()+"]{"+ p1.getName() +"}:");
			System.out.println("Enter number of the box that u want to check:");
			
			input = sc.nextLine();
			}while(!correctInput(input, boxes));
			ReplaceCharin2DArray(boxes, p1.getTag(), input.toCharArray()[0]);
			Print2DimArray(boxes);
			System.out.println();
			if(GameWon(boxes, p1.getTag())) {

				return 1;
			}
			if(Draw(boxes)) {

				return 0;
			}
			
			tttBot.SeeTheTable(boxes);
			System.out.println("Player 2 [" + tttBot.getTag() + "]{Bot}:");
			ReplaceCharin2DArray(boxes, tttBot.getTag(), tttBot.getDecision());
			Print2DimArray(boxes);
			System.out.println();
			if(GameWon(boxes, tttBot.getTag())) {					
				return -1;
			}
		}		
		return 0;
	}
	
	public static int MultiPlayerGame(Scanner sc, Player _p1, Player _p2) {
		TicTacToeGame.Player p1 = _p1;
		TicTacToeGame.Player p2 = _p2;
		char[][] boxes = new char[][] {		
			{'|','1','|','2','|','3','|'},
			{'|','4','|','5','|','6','|'},
			{'|','7','|','8','|','9','|'}
		};
		
		Print2DimArray(boxes);
		System.out.println();
		
		String inputX, inputO;
		while(!Draw(boxes)) {
			
			do {
			System.out.println("Player 1 ["+p1.getTag()+"]{"+ p1.getName() +"}:");
			System.out.println("Enter number of the box that u want to check:");
			
			inputX = sc.nextLine();
			}while(!correctInput(inputX, boxes));
			ReplaceCharin2DArray(boxes, p1.getTag(), inputX.toCharArray()[0]);
			Print2DimArray(boxes);
			System.out.println();
			if(GameWon(boxes, p1.getTag())) {

				return 1;
			}
			if(Draw(boxes)) {

				return 0;
			}
			do {
				System.out.println("Player 2 ["+p2.getTag()+"]{"+p2.getName()+"}:");
				System.out.print("Enter number of the box that u want to check:");
				inputO = sc.nextLine();
				}while(!correctInput(inputO, boxes));
				ReplaceCharin2DArray(boxes, p2.getTag(), inputO.toCharArray()[0]);
				Print2DimArray(boxes);
				System.out.println();
				if(GameWon(boxes, p2.getTag())) {
					
					return -1;
				}
		}
		
		return 0;
	}
		
	public static void Print2DimArray(char[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				
				System.out.print(arr[i][j]);
				
			}
			System.out.println();
		}
	}
	
	public static void ReplaceCharin2DArray(char[][] arr, char newChar, char oldChar) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 1; j < arr[i].length; j += 2) {
				
				if(arr[i][j] == oldChar)
					arr[i][j] = newChar;
				
			}
		}
	}
	
	public static boolean correctInput(String input, char[][] arr) {
		
		if (input.length() != 1)
			return false;
		else {
			if(!Character.isDigit(input.toCharArray()[0]))
				return false;
			else
			{
				return CharExistIn2DArray(arr, input.toCharArray()[0]);
			}
		}
			
	}
	
	public static boolean CharExistIn2DArray(char[][] arr, char charToFind) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 1; j < arr[i].length; j += 2) {
				
				if(arr[i][j] == charToFind)
				{
					return true;
				}				
			}
		}
		return false;
	}
	
	public static boolean GameWon(char[][] arr, char tag) {
		boolean end = true;
		for(int i = 0; i < arr.length; i++) {
			end = true;
			for(int j = 1; j < arr[i].length; j += 2) {
				if (arr[i][j] != tag) {
					end = false;
					break;
				}
			}
			if(end)
				return end;
		}
		
		for(int j = 1; j < arr[0].length; j += 2) {
			end = true;
			for(int i = 0; i < arr.length; i++) {
				if (arr[i][j] != tag) {
					end = false;
					break;
				}				
			}
			if(end)
				return end;
		}
		
		for(int i = 0, j = 1; i < arr.length; i++, j += 2) {
			end = true;
			if (arr[i][j] != tag) {
				end = false;
				break;
			}	
		}
		if(end)
			return end;
		
		for(int i = 0, j = 5; i < arr.length; i++, j -= 2) {
			end = true;
			if (arr[i][j] != tag) {
				end = false;
				break;
			}	
		}
		if(end)
			return end;
		
		return false;
	}
	
	public static boolean Draw(char[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 1; j < arr[0].length; j += 2) {
				if(Character.isDigit(arr[i][j]))
					return false;
			}
		}
		return true;
	}
	
	public static boolean CorrectTag(String str) {
		if(str.length()!=1)
			return false;
		else
		{
			if(Character.isAlphabetic(str.toCharArray()[0]))
				return true;
			else 
				return false;
		}
	}
}
