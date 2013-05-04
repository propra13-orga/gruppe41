package dungeonCrowler;

public class LevelContent {
	int content;
	// final values declaration
	public static final int SPACE = 0;
	public static final int PLAYER = 1;
	public static final int COMPUTER = 2;
	public static final int WALL = 3;
	// add some more level contens here like trap etc.
	
	// constructor
	public LevelContent(int content) {
		this.content = content;
	}
}
