public class Connect4Grid2DArray implements Connect4Grid {
	private int[][] grid;
	private int lastRow;
	private int lastCol;
	public final int EMPTY = 0;
	public final int YELLOW = 1;
	public final int RED = 2;
	public static final int MAX_ROW = 6;
	public static final int MIN_ROW = 1;
	public static final int MAX_COL = 7;

	Connect4Grid2DArray() {
		grid = new int[6][7];
		lastRow = 0;
		lastCol = 0;
	}

	public void emptyGrid() {
		for(int r = 0; r < grid.length; r ++) {
			for(int c = 0; c < grid[r].length; c++) {
				grid[r][c] = EMPTY;
			}
		}
	}

	public String toString() {
		String gridDisplay = "";
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				gridDisplay += grid[r][c] + " ";
			}
			if(r != grid.length-1)
				gridDisplay += "\n";
		}
		return gridDisplay;
	}

	public boolean isValidColumn(int column) {
		boolean isValid = false;
		if(column >= 0 && column < grid[0].length)
			isValid = true;
		return isValid;
	}

	public boolean isColumnFull(int column) {
		boolean isFull = true;
		if(grid[0][column] == EMPTY)
			isFull = false;
		return isFull;
	}

	public void dropPiece(ConnectPlayer player, int column) {
		int color = player.getColor();
		boolean isDropped = false;
		if(isValidColumn(column)) {
			if(!isColumnFull(column)) {
				for(int r = MAX_ROW-1; r >= 0; r--) {
					if(grid[r][column] == EMPTY && !isDropped) {
						grid[r][column] = color;
						lastRow = r;
						lastCol = column;
						isDropped = true;
						int prntCol = column+1;
						System.out.println("Successfully dropped piece in column " + prntCol);
					}
				}
			}
			else
				System.out.println("That column is full. Please pick another column.");
		}
		else
			System.out.println("That is not a valid column. Please pick another column.");
	}

	public boolean didLastPieceConnect4() {
		boolean didConnect4 = false;
		int color = grid[lastRow][lastCol];
		int count = 0;
		for(int c = 0; c < grid[lastRow].length; c++) {
			if(grid[lastRow][c]==color) {
				count++;
				if(count >= 4)
					didConnect4 = true;
			}
			else
				count = 0;
		}
		count = 0;
		for(int r = 0; r < grid.length; r++) {
			if(grid[r][lastCol]==color) {
				count++;
				if(count >= 4)
					didConnect4 = true;
			}
			else
				count = 0;
		}
		int copyC = lastCol;
		int copyR = lastRow;
		while(copyC > 0 && copyR > 0) {
			copyC--;
			copyR--;
		}
		count = 0;
		while(copyC < 7 && copyR < 6) {
			if(grid[copyR][copyC]==color) {
				count++;
				if(count >= 4)
					didConnect4 = true;
			}
			else
				count = 0;
			
			copyR++;
			copyC++;
		}
		copyC = lastCol;
		copyR = lastRow;
		while(copyC > 0 && copyR < 5) {
			copyC--;
			copyR++;
		}
		count = 0;
		while(copyC < 7 && copyR >= 0) {
			if(grid[copyR][copyC]==color) {
				count++;
				if(count >= 4)
					didConnect4 = true;
			}
			else
				count = 0;
			
			copyR--;
			copyC++;
		}
		
		return didConnect4;	
	}


public boolean isGridFull() {
	boolean isFull = true;
	for(int c = 0; c < grid[0].length; c++) {
		if(grid[0][c] == EMPTY)
			isFull = false;
	}
	return isFull;
}
}
