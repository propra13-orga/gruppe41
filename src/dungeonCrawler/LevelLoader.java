/**
 * 
 */
package dungeonCrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dungeonCrawler.GameElements.*;

/**Loads a level from a file.<br>
* @author Tissen
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
	 * @param a this application as {@link App}
	 */
	public LevelLoader(GameContent level, App a) {
		this.level = level;
		this.app = a;
		this.loaded = false;
		LevelLoader.splitChar = ",";
		this.idCounter = 1000;
	}
	
	/**Gets level data for client
	 * @param name of the client
	 * @return level as {@link GameContent}
	 */
	public GameContent getClientLevel(String name) {
		String number = getLevelNumber(app.currentLevel);
		String separator = File.separator;
		int elementCounter = 0;

		System.out.println("Lade Level " + number + "...");
		if (separator.equals(null)) {
			Error err = new Error("File separator not found");
			err.showMe();
		}
		try {
			File file = new File(folder + separator + "levelN" + name + number + ".lvl");
			BufferedReader buffer = new BufferedReader(new FileReader(file));
//			buffer = new BufferedReader(buffer);
			String input = null;
			while ((input = buffer.readLine()) != null) {
				if (!input.startsWith(";")) {
					elementCounter++;
					System.out.println("Lade Element " + elementCounter + ": " + input);
					if(input.split(splitChar).length <= 5 && input.split(splitChar).length > 2){
						int f = input.indexOf(splitChar);
						input = input.substring(0, f) + splitChar + level.getNextFreeID() + input.substring(f);
					}
					if (parse(input) && element != null) {
							System.out.println(element.id);
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

					if(input.startsWith("PLAYER")){
						Quest.levelStart = input;
					}
					if(input.split(splitChar).length <= 5 && input.split(splitChar).length > 2){
						int f = input.indexOf(splitChar);
						input = input.substring(0, f) + splitChar + level.getNextFreeID() + input.substring(f);
					}
					if (parse(input) && element != null) {
							System.out.println(element.id);
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
	 * @return {@code true} if a {@link GameElement} was created, {@code false} else
	 */
	private boolean parse(String input) {
		try {
			idCounter = level.getNextFreeID();
			String[] param = input.split(splitChar);
			switch (param[0]) {
			case "BOW":
				element = Bow.createElement(param, idCounter); break;
			case "BULLET":
				element = Bullet.createElement(param, idCounter); break;
			case "CHECKPOINT":
				element = CheckPoint.createElement(param, idCounter); break;
			case "CONVARMOR":
				element = ConvArmor.createElement(param, idCounter); break;
			case "ENDBOSS":
				element = EndBoss.createElement(param, idCounter); break;
			case "ENEMY":
				element = Enemy.createElement(param, idCounter); break;
			case "EXIT":
				element = Exit.createElement(param, idCounter); break;
			case "FIREARMOR":
				element = FireArmor.createElement(param, idCounter); break;
			case "FIREBOLT":
				element = FireBolt.createElement(param, idCounter); break;				
			case "FIREFOX":
				element = FireFox.createElement(param, idCounter); break;				
			case "HEALTHPOT":
				element = Healthpot.createElement(param, idCounter); break;	
			case "ICEARMOR":
				element = IceArmor.createElement(param, idCounter); break;
			case "ICEBOLT":
				element = IceBolt.createElement(param, idCounter); break;				
			case "ICEWEASEL":
				element = IceWeasel.createElement(param, idCounter); break;				
			case "MAGICSHIELD":
				element = MagicShield.createElement(param, idCounter); break;
			case "MANAPOT":
				element = Manapot.createElement(param, idCounter); break;					
			case "MONEY":
				element = Money.createElement(param, idCounter); break;
			case "NETWORKPLAYER":
				element = NetworkPlayer.createElement(param, idCounter); break;
			case "NPC":
				element = NPC.createElement(param, idCounter); break;
			case "PLAYER":
				element = Player.createElement(param, idCounter); break;
			case "SPELL":
				element = Spell.createElement(param, idCounter); break;
			case "TRAP":
				element = Trap.createElement(param, idCounter); break;
			case "WALL":
				element = Wall.createElement(param, idCounter); break;
			case "WARPPOINT":
				element = WarpPoint.createElement(param, idCounter); break;
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
