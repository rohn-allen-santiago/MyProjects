/* SELF ASSESSMENT
Connect4Game class (35 marks) :35
My class creates references to the Connect 4 Grid and two Connect 4 Players. It asks the user whether he/she would like to play/quit inside a loop. If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 2. the two players are initialised - must specify the type to be ConnectPlayer, and 3. the game starts. In the game, I ask the user where he/she would like to drop the piece. I perform checks by calling methods in the Connect4Grid interface. Finally a check is performed to determine a win. 
Comment: This class works as intended, making the required calls to methods in Connect4Grid2DArray and ConnectPlayer classes

Connect4Grid interface (10 marks) :10
I define all 7 methods within this interface.
Comment: All 7 methods are defined

Connect4Grid2DArray class (25 marks)  :25
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  It provides as implementation of the method to check whether there is a win.
Comment: My class implements the interface correctly, and all methods work as intended

ConnectPlayer abstract class (10 marks) :10
My class provides at lest one non-abstract method and at least one abstract method. 
Comment: My class provides the at least one non-abstract and abstract method

C4HumanPlayer class (10 marks) :10
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides the Human player functionality.
Comment: My class extends the ConnectPlayer class and provides the Human player functionality

C4RandomAIPlayer class (10 marks) :10
My class extends the ConnectPlayer class and overrides the abstract method(s). It provides AI player functionality. 
Comment: My class extends the ConnectPlayer class and provides the AI player functionality

Total Marks out of 100: 100
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Connect4Game {
	
	public static final int EMPTY = 0;
	public static final int YELLOW = 1;
	public static final int RED = 2;
	public static final String PLAYER_ONE = "Player One";
	public static final String PLAYER_TWO = "Player Two";

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean doQuit = false;
		boolean running = true;
		ConnectPlayer playerOne = null;
		ConnectPlayer playerTwo = null;
		
		while(!doQuit) {
			running = true;
			while(running) {
				System.out.print("Connect 4\n"
						+ "1. Player vs Player\n"
						+ "2. Player vs AI\n"
						+ "3. AI vs AI\n"
						+ "Please select an option: ");
				if(input.hasNextInt()) {
					int choice = input.nextInt();
					if(choice == 1) {
						playerOne = new C4HumanPlayer(YELLOW, PLAYER_ONE);
						playerTwo = new C4HumanPlayer(RED, PLAYER_TWO);
						running = false;
					}
					else if(choice == 2) {
						boolean isChoiceValid = false;
						while(!isChoiceValid) {
							System.out.print("What color would you like? ");
							String color = input.next();
							if(color.equalsIgnoreCase("Yellow")) {
								playerOne = new C4HumanPlayer(YELLOW, PLAYER_ONE);
								playerTwo = new C4RandomAIPlayer(RED, PLAYER_TWO);
								isChoiceValid = true;
							}
							else if(color.equalsIgnoreCase("Red")) {
								playerOne = new C4RandomAIPlayer(YELLOW, PLAYER_ONE);
								playerTwo = new C4HumanPlayer(RED, PLAYER_TWO);
								isChoiceValid = true;
							}
							else {
								System.out.println("That was not a valid choice. Please try again.");
								input.next();
							}
						}
						running = false;
					}
					else if(choice == 3) {
						playerOne = new C4RandomAIPlayer(YELLOW, PLAYER_ONE);
						playerTwo = new C4RandomAIPlayer(RED, PLAYER_TWO);
						running = false;
					}
					else {
						System.out.println("That is not a valid choice. Please try again.");
						input.next();
					}
				}
				else {
					System.out.println("That is not a valid input. Please try again.");
					input.next();
				}
			}
			Connect4Grid2DArray grid = new Connect4Grid2DArray();
			grid.emptyGrid();
			String winner = "";
			ArrayList<ConnectPlayer> players = new ArrayList<ConnectPlayer>();
			players.add(playerOne);
			players.add(playerTwo);
			boolean isGameOver = false;
			while(!isGameOver) {
				for(int i = 0; i < players.size(); i++) {
					if(!isGameOver) {
						ConnectPlayer currentPlayer = players.get(i);
						int choice = currentPlayer.selectColumn(grid);
						grid.dropPiece(currentPlayer, choice);
						System.out.println(grid.toString());
						if(grid.didLastPieceConnect4()) {
							winner = currentPlayer.getName();
							isGameOver = true;
						}
						else if(grid.isGridFull()) {
							winner = "neither player";
							isGameOver = true;
						}
					}
				}	
			}
			System.out.println("The winner is " + winner);
			input.nextLine();
			System.out.println("Type gibberish to play again (or 'quit' to quit): ");
			String quit = input.nextLine();
			doQuit = quit.equalsIgnoreCase("Quit");
		}
		input.close();

	}

}
