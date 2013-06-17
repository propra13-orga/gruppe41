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
		this.splitChar = ",";
	}

	public GameContent getLevel() {
		String number = getLevelNumber(app.currentLevel);
		String separator = File.separator;

		System.out.println("Lade Level " + number + "...");
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
				if (!input.startsWith(";")) {
					System.out.println("Lade Element: " + input);
					if (parse(input)) {
						if (!level.addGameElement(element)) {
							Error err = new Error("Kann '" + input + "' nicht interpretieren");
							err.showMe();
						}
					}
				}
			}
			buffer.close();
			loaded = true;
			System.out.println("Fertig.");
		}
		// show an error message
		catch (IOException e) {
			e.printStackTrace();
			loaded = false;
			System.out.println("Ladevorgang nicht erfolgreich.");
		}
		return level;
	}

	private String getLevelNumber(int currentLevel) {
		String str;
		try {
			str = 0 +  Integer.toString(app.currentLevel);
			str = str.substring(str.length()-2);
		} catch (Exception e) {
			return "03";
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
			case "HEALTHPOT":
				element = new Healthpot(position, size);
				break;	
			case "MANAPOT":
				element = new Manapot(position, size);
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
