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
	LevelContent[][] grid;

	// constructor
	public Dungeon(int width, int height) {
		// === Test ===
		grid = new LevelContent[width][height];
		for (int i=0;i<width;i++) {
			for (int j=0;j<height;j++) {
				grid[i][j] = new LevelContent(LevelContent.WALL);
			}
		}
		// === Test Ende===
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
}
