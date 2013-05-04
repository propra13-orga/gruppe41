package dungeonCrowler;

/**
 * A level of dungeon
 * 
 * @author Tissen/Gruppe41
 *
 */
public class Dungeon {
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
}
