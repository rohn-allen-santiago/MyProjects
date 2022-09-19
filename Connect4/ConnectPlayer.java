
public abstract class ConnectPlayer {
	public static final int MAX_COL = 7;
	public static final int MIN_COL = 1;
	private int color;
	private String name;
	
	public ConnectPlayer(int color, String name) {
		this.color = color;
		this.name = name;
	}
	
	public abstract int selectColumn(Connect4Grid2DArray grid);
	
	public int getColor() {
		return color;
	}	
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
