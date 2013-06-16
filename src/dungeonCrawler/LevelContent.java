package dungeonCrawler;

/**
 * This class describes all of contents in a dungeon
 * 
 * @author Tissen
 *
 */
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
	public static final int NPC = 5;
	public static final int Enemy = 6;
	// add some more level contens here like trap etc.
	
	// constructor
	public LevelContent(int content) {
		this.type = content;
		this.name = "";
	}
	
	// another constructor
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
