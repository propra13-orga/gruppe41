/**
 * 
 */
package dungeonCrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dungeonCrawler.GameElements.*;

/**loads a level from a file.
 * look for a *.lvl file to create your own level.
 * 
 * @author Tissen
 *
 */
public class LevelLoader {
	GameContent level;
	App app;
	boolean loaded;
	private String folder = "Levels";
	private GameElement element;
	private String splitChar;

	// constructor
	public LevelLoader(GameContent lvl, App a) {
		this.level = lvl;
		this.app = a;
		this.loaded = false;
//		this.element = new Wall(new Vector2d(), new Vector2d());
		this.splitChar = ",";
	}

//	public Dungeon getNextLevel() {
//		if (app.currentLevel<app.level-1) app.currentLevel++;
//		return getLevel();
//	}
//
//	public Dungeon getPreviousLevel() {
//		if (app.currentLevel > 0) app.currentLevel--;
//		return getLevel();
//	}

	public GameContent getLevel() {
		String number = getLevelNumber(app.currentLevel);
		String separator = File.separator;
		if (separator.equals(null)) {
			Error err = new Error("File separator not found");
			err.showMe();
		}
		try {
			File file = new File(folder + separator + "level" + number + ".lvl");
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			buffer = new BufferedReader(buffer);
			String input = null;
			while ((input = buffer.readLine()) != null) {
				if (parse(input)) {
//					level.setContent(element); // alte Version, sp�ter entfernen
					if (!level.addGameElement(element)) {
						Error err = new Error("Kann '" + input + "' nicht interpretieren");
						err.showMe();
					}
				}
			}
			buffer.close();
			loaded = true;
		}
		// show an error message
		catch (IOException e) {
			e.printStackTrace();
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
		try {
			String[] param = input.split(splitChar);
			Vector2d position = new Vector2d(Integer.parseInt(param[1]), Integer.parseInt(param[2]));
			Vector2d size = new Vector2d(Integer.parseInt(param[3]), Integer.parseInt(param[4]));
			switch (param[0]) {
			case "WALL":
				element = new Wall(position, size); break;
			case "TRAP":
				element = new Trap(position, size); break;
			case "EXIT":
				element = new Exit(position, size); break;
			case "PLAYER":
				element = new Player(position, size); break;
			case "NPC":
				element = new NPC(position, size); break;
			case "ENEMY":
				element = new Enemy(position, size); break;
			case "CHECKPOINT":
				element = new CheckPoint(position, size); break;
			case "WARPPOINT":
				element = new WarpPoint(position, size);
				((WarpPoint)element).setTarget(new Vector2d(Integer.parseInt(param[5]), Integer.parseInt(param[6])));
				break;
			}
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			Error err = new Error("Kann '" + input + "' nicht interpretieren");
			err.showMe();
			return false;
		}
	}
	
}
