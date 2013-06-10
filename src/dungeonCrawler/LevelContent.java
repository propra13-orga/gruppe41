package dungeonCrawler;

public class LevelContent {
	private int type;
	String name;
	int x, y; // x, y position on the grid
	// final values declaration
	public static final int SPACE = 0;
	public static final int PLAYER = 1;
	public static final int COMPUTER = 2;
	public static final int WALL = 3;
	public static final int EXIT = 4;
	// add some more level contens here like trap etc.
	
	// constructor
	public LevelContent(int content) {
		this.type = content;
		this.name = "";
	}
	
	public LevelContent(int content, String name) {
		this.type = content;
		this.name = name;
	}
	
	public int getContent() {
		return type;
	}
	
	public void setContent(int content) {
		this.type = content;
	}
}
