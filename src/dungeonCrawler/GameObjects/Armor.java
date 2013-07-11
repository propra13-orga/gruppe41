package dungeonCrawler.GameObjects;

import dungeonCrawler.GameElement;
import dungeonCrawler.GameObject;
//import dungeonCrawler.GameElements.Enemy;
import dungeonCrawler.GameElements.Player;

/**
 * @author Tissen
 *
 */
public class Armor extends GameObject {
	private int convArmor;
	private int fireArmor;
	private int iceArmor;

	public Armor(int conv, int fire, int ice) {
		this.convArmor = conv;
		this.fireArmor = fire;
		this.iceArmor = ice;
	}

	@Override
	public void performOn(GameElement element) {
		String name = element.getName();
		switch (name) {
		case "PLAYER": ((Player)element).setProtection(this); break;
		}
	}
	
	public int getConvProtection() {
		return convArmor;
	}
	
	public int getFireResist() {
		return fireArmor;
	}
	
	public int getIceResist() {
		return iceArmor;
	}

}