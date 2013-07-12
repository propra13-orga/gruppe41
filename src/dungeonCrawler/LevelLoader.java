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
	private static String splitChar;
	int idCounter;

	/**Constructor
	 * @param level to be loaded as {@link GameContent}
	 * @param a the application as {@link App}
	 */
	public LevelLoader(GameContent level, App a) {
		this.level = level;
		this.app = a;
		this.loaded = false;
		LevelLoader.splitChar = ",";
		this.idCounter = 1000;
	}

	/**Loads a level or saved game
	 * @return level as {@link GameContent}
	 */
	public GameContent getLevel() {
		String number = getLevelNumber(app.currentLevel);
		String separator = File.separator;
		int elementCounter = 0;

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
					elementCounter++;
					System.out.println("Lade Element " + elementCounter + ": " + input);
					if (parse(input) && element != null) {
						if (!level.addGameElement(element)) {
							Error err = new Error("Kann Element '" + input + "' nicht hinzufügen.");
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

	/**Gets level number
	 * @param currentLevel as {@link int}
	 * @return level number as {@link String}
	 */
	private String getLevelNumber(int currentLevel) {
		String str;
		try {
			str = 0 +  Integer.toString(app.currentLevel);
			str = str.substring(str.length()-2);
		} catch (Exception e) {
			return "00";
		}
		return str;
	}

	/**Parses a line of the level file
	 * @param input {@link String} to be parsed
	 * @return {@code true} if a {@link GameElement} was created<br>
	 * {@code false} else
	 */
	private boolean parse(String input) {
		try {
			idCounter++;
			String[] param = input.split(splitChar);
			switch (param[0]) {
			case "BOW":
				element = Bow.createElement(param); break;
			case "BULLET":
				element = Bullet.createElement(param); break;
			case "CHECKPOINT":
				element = CheckPoint.createElement(param); break;
			case "ENEMY":
				element = Enemy.createElement(param); break;
			case "EXIT":
				element = Exit.createElement(param); break;
			case "FIREBOLT":
				element = FireBolt.createElement(param); break;				
			case "FIREFOX":
				element = FireFox.createElement(param); break;				
			case "HEALTHPOT":
				element = Healthpot.createElement(param); break;	
			case "ICEBOLT":
				element = IceBolt.createElement(param); break;				
			case "ICEWEASEL":
				element = IceWeasel.createElement(param); break;				
			case "MAGICSHIELD":
				element = MagicShield.createElement(param); break;
			case "MANAPOT":
				element = Manapot.createElement(param); break;					
			case "MONEY":
				element = Money.createElement(param); break;
			case "NPC":
				element = NPC.createElement(param); break;
			case "PLAYER":
				element = Player.createElement(param); break;
			case "SPELL":
				element = Spell.createElement(param); break;
			case "TRAP":
				element = Trap.createElement(param); break;
			case "WALL":
				element = Wall.createElement(param, idCounter); break;
			case "WARPPOINT":
				element = WarpPoint.createElement(param); break;
			}
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			Error err = new Error("Kann '" + input + "' nicht interpretieren");
			err.showMe();
			return false;
		}
	}
	
	public static String getSplitChar() {
		return splitChar;
	}

}
