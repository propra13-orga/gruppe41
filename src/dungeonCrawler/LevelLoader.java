/**
 * 
 */
package dungeonCrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dungeonCrawler.GameElements.Wall;

/**loads a level from a file.
 * look for a *.lvl file to create your own level.
 * 
 * @author Tissen
 *
 */
public class LevelLoader {
	Dungeon level;
	App app;
	boolean loaded;
	String folder = "Levels";
	private GameElement element;

	// constructor
	public LevelLoader(Dungeon lvl, App a) {
		this.level = lvl;
		this.app = a;
		this.loaded = false;
		this.element = new Wall(new Vector2d(), new Vector2d());
	}

	public Dungeon getNextLevel() {
		if (app.currentLevel<app.level-1) app.currentLevel++;
		return getLevel();
	}

	public Dungeon getPreviousLevel() {
		if (app.currentLevel > 0) app.currentLevel--;
		return getLevel();
	}

	public Dungeon getLevel() {
		String nummer = getLevelNumber(app.currentLevel);
		try {
			File file = new File(folder + System.getProperty(File.separator) + "level" + nummer + ".lvl");
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			buffer = new BufferedReader(buffer);
			String input = null;
			while ((input = buffer.readLine()) != null) {
				if (parse(input)) {
					level.setContent(element);
				}
			}
			buffer.close();
			loaded = true;
		}
		// show an error message
		catch (IOException e) {
			loaded = false;
		}
		return level;
	}

	private String getLevelNumber(int currentLevel) {
		String str;
		try {
			str = Integer.toString(currentLevel) + "00";
			str = str.substring(str.length()-2);
		} catch (Exception e) {
			return "";
		}
		return str;
	}

	private boolean parse(String input) {
		// TODO implementieren
		return false;
	}
	
}
