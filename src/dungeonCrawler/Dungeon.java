package dungeonCrawler;

/**
 * A level of dungeon
 * 
 * @author Tissen/Gruppe41
 *
 */
public class Dungeon {
	private int width;
	private int height;
	private LevelContent[][] grid; // array of game elements
	private LevelContent player = new LevelContent(1, "Player"); // player element
	private LevelContent exit = new LevelContent(4, "Exit"); // dungeon exit
	// final static direction values
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	// player and dungeon attributes
	public boolean dead = false;
	public boolean complete = false;

	// constructor
	public Dungeon(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new LevelContent[width][height];
		for (int i=0;i<width;i++) {
			for (int j=0;j<height;j++) {
				grid[i][j] = new LevelContent(LevelContent.WALL);
			}
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setWidth(int w) {
		this.width = w;
	}

	public void setHeight(int h) {
		this.height = h;
	}

	public LevelContent getContent(int w, int h) {
		return this.grid[w][h];
	}

	public void setContent(int w, int h, LevelContent c) {
		this.grid[w][h] = c;
	}

	public void setPlayerPosition(int x, int y) {
		this.player.x = x;
		this.player.y = y;
		grid[x][y].setContent(LevelContent.PLAYER);
	}

	public void setExitPosition(int x, int y) {
		this.exit.x = x;
		this.exit.y = y;
		grid[x][y].setContent(LevelContent.EXIT);
	}

	// move a player if possible
	public void move(int direction) {
		int x = 0, y = 0;
		switch (direction) {
		case UP:
			x = player.x;
			y = player.y-1;
			break;
		case RIGHT:
			x = player.x+1;
			y = player.y;
			break;
		case DOWN:
			x = player.x;
			y = player.y+1;
			break;
		case LEFT:
			x = player.x-1;
			y = player.y;
			break;
		}
		try {
			if (grid[x][y].getContent() == LevelContent.SPACE) {
				grid[player.x][player.y].setContent(LevelContent.SPACE);
				setPlayerPosition(x, y);
			}
			else	if (grid[x][y].getContent() == LevelContent.EXIT) {
				grid[player.x][player.y].setContent(LevelContent.SPACE);
				setPlayerPosition(x, y);
				complete = true;
			}
			else	if (grid[x][y].getContent() == LevelContent.COMPUTER) {
				dead = true; // player dies, restart level
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			// do nothing
		}
	}
}
