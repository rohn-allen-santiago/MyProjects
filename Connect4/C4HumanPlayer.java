import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer{
	
	public C4HumanPlayer(int color, String name) {
		super(color, name);
	}
	
	public int selectColumn(Connect4Grid2DArray grid) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		int column = 0;;
		while(running) {
			System.out.print("Please enter a column number (1-7): ");
			if(scanner.hasNextInt()) {
				column = scanner.nextInt();
				if(column >= MIN_COL && column <= MAX_COL && !grid.isColumnFull(column-1)) {
					running = false;
				}
				else {
					System.out.println("That is not a valid column number. Please try again.");
					scanner.nextLine();
				}
			}
			else {
				System.out.println("That is not a valid input. Please try again.");
				scanner.nextLine();
			}
		}

		return column-1;
	}
	
}
