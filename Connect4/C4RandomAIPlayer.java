import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer {
	
	public C4RandomAIPlayer(int color, String name) {
		super(color, name);
	}
	
	public int selectColumn(Connect4Grid2DArray grid) {
		Random rand = new Random();
		boolean isDropped = false;
		int column = 0;
		while(!isDropped) {
			column = rand.nextInt(MAX_COL)+1;
			if(!grid.isColumnFull(column)) {
				System.out.println("The AI has picked column " + column);
				isDropped = true;
			}
		}
		return column;
	}

}
